package org.apache.geronimo.gbuild.agent;
/**
 * @version $Rev$ $Date$
 */

import junit.framework.*;
import org.apache.geronimo.gbuild.agent.ContinuumBuildAgent;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildResult;
import org.apache.maven.continuum.execution.shell.ShellBuildExecutor;
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.apache.maven.continuum.store.ContinuumStore;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.embed.Embedder;
import org.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.DeliveryMode;
import javax.jms.Topic;
import javax.jms.Queue;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ContinuumBuildAgentTest extends TestCase {

    private CVS cvs;
    private File shellScript;

    protected void setUp() throws Exception {

        File root = new File("target/it").getCanonicalFile();
        File cvsroot = new File(root, "cvs-root");
        File module = new File(root, "shell");

        deleteAndCreateDirectory( module );

        shellScript = createScript(module);

        cvs = new CVS(cvsroot);
        cvs.init();
        cvs._import(module, "shell");

    }

    public void testBuild() throws Exception {

        Project project = new Project();
        project.setId(10);
        project.setScmUrl("scm|cvs|local|" + cvs.getCvsroot().getAbsolutePath() + "|shell");
        project.setName("Shell Project");
        project.setVersion("3.0");
        project.setExecutorId(ShellBuildExecutor.ID);
        project.setState(ContinuumProjectState.OK);

        BuildDefinition bd = new BuildDefinition();
        bd.setId(20);
        bd.setBuildFile(shellScript.getAbsolutePath());
        bd.setArguments("");

        project.addBuildDefinition(bd);

        MapContinuumStore store = new MapContinuumStore();

        store.updateProject(project);
        store.storeBuildDefinition(bd);

        ThreadContextContinuumStore.setStore(store);

        Embedder embedder = new Embedder();
        embedder.start();
        ContinuumBuildAgent buildAgent = (ContinuumBuildAgent) embedder.lookup(BuildAgent.ROLE);

        buildAgent.init();
        buildAgent.build(project.getId(), bd.getId(), 1);

        int latestBuildId = project.getLatestBuildId();
        BuildResult buildResult = store.getBuildResult(latestBuildId);

        assertNotNull("buildResult",buildResult);
        assertEquals("buildResult.getState",ContinuumProjectState.OK, buildResult.getState());
        assertEquals("project.getState",ContinuumProjectState.OK, project.getState());

        embedder.stop();
        ThreadContextContinuumStore.setStore(null);
    }


    public static class TestBroker implements Runnable {
        private final String[] args;

        public TestBroker(String[] args) {
            this.args = args;
        }

        public void run() {
            org.activemq.broker.impl.Main.main(args);
        }

        public static void main(String[] args) {
            org.apache.log4j.BasicConfigurator.configure();
            new TestBroker(args).run();
        }
    }

    // need to throw in a Broker
    public void testBuildQueue() throws Exception {

        Thread broker = new Thread(new TestBroker(new String[]{}));
        broker.setDaemon(true);
        broker.start();
        Thread.sleep(5000);

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue buildQueue = session.createQueue("BUILD.TASKS");
        MessageProducer producer = session.createProducer(buildQueue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        MapContinuumStore store = new MapContinuumStore();

        Project project = new Project();
        project.setId(12);
        project.setScmUrl("scm|cvs|local|" + cvs.getCvsroot().getAbsolutePath() + "|shell");
        project.setName("Shell Project");
        project.setVersion("3.0");
        project.setExecutorId(ShellBuildExecutor.ID);
        project.setState(ContinuumProjectState.OK);
        store.updateProject(project);

        String[] goals = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};

        for (int i = 0; i < goals.length; i++) {
            String goal = goals[i];
            BuildDefinition bd = new BuildDefinition();
            bd.setId(i);
            bd.setBuildFile(shellScript.getAbsolutePath());
            bd.setArguments(goal);
            project.addBuildDefinition(bd);
            store.storeBuildDefinition(bd);

            HashMap map = new HashMap();

            ContinuumBuildAgent.setStore(map, store);
            ContinuumBuildAgent.setProjectId(map, project.getId());
            ContinuumBuildAgent.setBuildDefinitionId(map, bd.getId());
            ContinuumBuildAgent.setTrigger(map, 0);

            producer.send(session.createObjectMessage(map));
        }

        Topic resultsTopic = session.createTopic("BUILD.RESULTS");
        MessageConsumer resultsConsumer = session.createConsumer(resultsTopic);

        Embedder embedder = new Embedder();
        embedder.start();

        ContinuumBuildAgent buildAgent = (ContinuumBuildAgent) embedder.lookup(BuildAgent.ROLE);
        Thread agentThread = new Thread(buildAgent);
        agentThread.setDaemon(false);
        agentThread.start();

        // Consume twelve responses
        for (int i = 0; i < goals.length; i++) {
            ObjectMessage objectMessage = (ObjectMessage) resultsConsumer.receive();
            Map results = (Map) objectMessage.getObject();
            ContinuumStore store2 = ContinuumBuildAgent.getContinuumStore(results);

            int buildDefinitionId = ContinuumBuildAgent.getBuildDefinitionId(results);
            int projectId = ContinuumBuildAgent.getProjectId(results);
            Project project2 = store2.getProject(projectId);
            int latestBuildId = project2.getLatestBuildId();
            BuildResult buildResult = store2.getBuildResult(latestBuildId);

            System.out.println("[RESULT] "+ project2.getName() +" : "+goals[buildDefinitionId]+" : "+buildResult.getBuildNumber());

            assertNotNull("buildResult",buildResult);
            assertEquals("buildResult.getState", ContinuumProjectState.OK, buildResult.getState());
            assertEquals("project.getState", ContinuumProjectState.OK, project.getState());
        }


        buildAgent.stop();

        session.close();
        connection.close();
    }


    public static void deleteAndCreateDirectory(File directory)
            throws IOException {
        if (directory.isDirectory()) {
            FileUtils.deleteDirectory(directory);
        }

        assertTrue("Could not make directory " + directory, directory.mkdirs());
    }

    private File createScript(File module) throws IOException, CommandLineException {
        File script;

        String EOL = System.getProperty( "line.separator" );

        boolean isWindows = System.getProperty("os.name").startsWith("Windows");

        boolean isCygwin = "true".equals(System.getProperty("cygwin"));

        if ( isWindows && !isCygwin ) {

            script = new File( module, "script.bat" );

            String content = "@ECHO OFF" + EOL
                    + "IF \"%*\" == \"\" GOTO end" + EOL
                    + "FOR %%a IN (%*) DO ECHO %%a" + EOL
                    + ":end" + EOL;

            FileUtils.fileWrite( script.getAbsolutePath(), content );

        } else {

            script = new File( module, "script.sh" );

            String content = "#!/bin/bash" + EOL + "for arg in \"$@\"; do echo $arg ; done"+EOL;

            FileUtils.fileWrite( script.getAbsolutePath(), content );

            Chmod.exec(module, "+x", script);
        }

        return script;
    }

}