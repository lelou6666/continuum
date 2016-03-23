package org.apache.continuum.builder.distributed.manager;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.continuum.builder.distributed.executor.ThreadedDistributedBuildTaskQueueExecutor;
import org.apache.continuum.builder.distributed.stubs.SlaveBuildAgentTransportClientStub;
=======
import org.apache.continuum.builder.distributed.executor.ThreadedDistributedBuildTaskQueueExecutor;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.builder.distributed.stubs.DefaultDistributedBuildManagerStub;
import org.apache.continuum.configuration.BuildAgentConfiguration;
import org.apache.continuum.configuration.BuildAgentGroupConfiguration;
import org.apache.continuum.dao.BuildDefinitionDao;
import org.apache.continuum.dao.BuildResultDao;
import org.apache.continuum.dao.ProjectDao;
<<<<<<< HEAD
import org.apache.continuum.distributed.transport.slave.SlaveBuildAgentTransportService;
=======
import org.apache.continuum.dao.ProjectScmRootDao;
import org.apache.continuum.model.project.ProjectRunSummary;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.model.project.ProjectScmRoot;
import org.apache.continuum.taskqueue.BuildProjectTask;
import org.apache.continuum.taskqueue.OverallDistributedBuildQueue;
import org.apache.continuum.taskqueue.PrepareBuildProjectsTask;
import org.apache.continuum.utils.build.BuildTrigger;
<<<<<<< HEAD
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.model.system.Profile;
import org.codehaus.plexus.spring.PlexusInSpringTestCase;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

public class DefaultDistributedBuildManagerTest
    extends PlexusInSpringTestCase
=======
import org.apache.maven.continuum.PlexusSpringTestCase;
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildResult;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.model.system.Profile;
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DefaultDistributedBuildManagerTest
    extends PlexusSpringTestCase
>>>>>>> refs/remotes/apache/trunk
{
    private final String TEST_BUILD_AGENT1 = "http://sampleagent";

    private final String TEST_BUILD_AGENT2 = "http://testagent";
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    private final String TEST_BUILD_AGENT_GROUP1 = "buildAgentGroup1";

    private DefaultDistributedBuildManager distributedBuildManager;

    private DefaultDistributedBuildManager distributedBuildManagerStub = new DefaultDistributedBuildManagerStub();

<<<<<<< HEAD
    private Mockery context;

=======
>>>>>>> refs/remotes/apache/trunk
    private OverallDistributedBuildQueue overallDistributedBuildQueue1;

    private OverallDistributedBuildQueue overallDistributedBuildQueue2;

    private BuildDefinitionDao buildDefinitionDao;

    private BuildResultDao buildResultDao;

    private ProjectDao projectDao;

<<<<<<< HEAD
=======
    private ProjectScmRootDao projectScmRootDao;

>>>>>>> refs/remotes/apache/trunk
    private ConfigurationService configurationService;

    private List<BuildAgentConfiguration> buildAgents;

    private BuildAgentConfiguration buildAgent1;

    private BuildAgentConfiguration buildAgent2;

    private ThreadedDistributedBuildTaskQueueExecutor distributedBuildTaskQueueExecutor;

    private TaskQueue distributedBuildQueue;

    private Project project;

    private Project project2;

    private ProjectGroup projectGroup;

    private BuildDefinition buildDefinition;

    private BuildAgentGroupConfiguration buildAgentGroup;

<<<<<<< HEAD
    @Override
    public void setUp()
        throws Exception
    {
        super.setUp();

        context = new JUnit3Mockery();
        context.setImposteriser( ClassImposteriser.INSTANCE );

        distributedBuildManager = (DefaultDistributedBuildManager) lookup( DistributedBuildManager.class );

        buildDefinitionDao = context.mock( BuildDefinitionDao.class );
        distributedBuildManager.setBuildDefinitionDao( buildDefinitionDao );
        distributedBuildManagerStub.setBuildDefinitionDao( buildDefinitionDao );

        buildResultDao = context.mock( BuildResultDao.class );
        distributedBuildManager.setBuildResultDao( buildResultDao );
        distributedBuildManagerStub.setBuildResultDao( buildResultDao );

        projectDao = context.mock( ProjectDao.class );
        distributedBuildManager.setProjectDao( projectDao );
        distributedBuildManagerStub.setProjectDao( projectDao );

        distributedBuildManagerStub.setContainer( getContainer() );

        configurationService = context.mock( ConfigurationService.class );
=======
    @Before
    public void setUp()
        throws Exception
    {
        distributedBuildManager = (DefaultDistributedBuildManager) lookup( DistributedBuildManager.class );

        buildDefinitionDao = mock( BuildDefinitionDao.class );
        distributedBuildManager.setBuildDefinitionDao( buildDefinitionDao );
        distributedBuildManagerStub.setBuildDefinitionDao( buildDefinitionDao );

        buildResultDao = mock( BuildResultDao.class );
        distributedBuildManager.setBuildResultDao( buildResultDao );
        distributedBuildManagerStub.setBuildResultDao( buildResultDao );

        projectDao = mock( ProjectDao.class );
        distributedBuildManager.setProjectDao( projectDao );
        distributedBuildManagerStub.setProjectDao( projectDao );

        projectScmRootDao = mock( ProjectScmRootDao.class );
        distributedBuildManager.setProjectScmRootDao( projectScmRootDao );
        distributedBuildManagerStub.setProjectScmRootDao( projectScmRootDao );

        distributedBuildManagerStub.setContainer( getContainer() );

        configurationService = mock( ConfigurationService.class );
>>>>>>> refs/remotes/apache/trunk

        distributedBuildManager.setConfigurationService( configurationService );
        distributedBuildManagerStub.setConfigurationService( configurationService );

<<<<<<< HEAD
        distributedBuildTaskQueueExecutor = (ThreadedDistributedBuildTaskQueueExecutor) context.mock( ThreadedDistributedBuildTaskQueueExecutor.class, "distributed-build-project" );

        distributedBuildQueue = context.mock( TaskQueue.class, "distributed-build-queue" );

        overallDistributedBuildQueue1 = context.mock( OverallDistributedBuildQueue.class, TEST_BUILD_AGENT1 );
        overallDistributedBuildQueue2 = context.mock( OverallDistributedBuildQueue.class, TEST_BUILD_AGENT2 );
=======
        distributedBuildTaskQueueExecutor =
            mock( ThreadedDistributedBuildTaskQueueExecutor.class, "distributed-build-project" );

        distributedBuildQueue = mock( TaskQueue.class, "distributed-build-queue" );

        overallDistributedBuildQueue1 = mock( OverallDistributedBuildQueue.class, TEST_BUILD_AGENT1 );
        overallDistributedBuildQueue2 = mock( OverallDistributedBuildQueue.class, TEST_BUILD_AGENT2 );
>>>>>>> refs/remotes/apache/trunk

        init();
    }

    private void init()
    {
        buildAgent1 = new BuildAgentConfiguration();
        buildAgent1.setEnabled( true );
        buildAgent1.setUrl( TEST_BUILD_AGENT1 );

        buildAgent2 = new BuildAgentConfiguration();
        buildAgent2.setEnabled( true );
        buildAgent2.setUrl( TEST_BUILD_AGENT2 );

        List<BuildAgentConfiguration> buildAgents = new ArrayList<BuildAgentConfiguration>();
        buildAgents.add( buildAgent1 );
        buildAgents.add( buildAgent2 );
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        setUpBuildAgentGroup( buildAgents );
        setupBuildDefinition();

        projectGroup = new ProjectGroup();
        projectGroup.setId( 1 );
        projectGroup.setGroupId( "group" );

        project = new Project();
        project.setId( 1 );
        project.setGroupId( "groupId" );
        project.setArtifactId( "artifactId" );
        project.setVersion( "1.0" );
        project.setProjectGroup( projectGroup );

        project2 = new Project();
        project2.setId( 2 );
        project2.setGroupId( "groupId" );
        project2.setArtifactId( "artifactId" );
        project2.setVersion( "1.0" );
        project2.setProjectGroup( projectGroup );
    }

<<<<<<< HEAD
=======
    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testViewQueuesAfterBuildAgentIsLost()
        throws Exception
    {
        distributedBuildManager.setOverallDistributedBuildQueues( getMockOverallDistributedBuildQueues( 1 ) );

<<<<<<< HEAD
        recordViewQueuesAfterBuildAgentIsLost();

        Map<String, List<PrepareBuildProjectsTask>> prepareBuildQueues = distributedBuildManager.getProjectsInPrepareBuildQueue();
        Map<String, List<BuildProjectTask>> buildQueues = distributedBuildManager.getProjectsInBuildQueue();
        Map<String, PrepareBuildProjectsTask> currentPrepareBuild = distributedBuildManager.getProjectsCurrentlyPreparingBuild();
=======
        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
        when( configurationService.getSharedSecretPassword() ).thenReturn( null );
        when( overallDistributedBuildQueue1.getDistributedBuildTaskQueueExecutor() ).thenReturn(
            distributedBuildTaskQueueExecutor );
        when( distributedBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallDistributedBuildQueue1.getProjectsInQueue() ).thenReturn(
            new ArrayList<PrepareBuildProjectsTask>() );
        when( overallDistributedBuildQueue1.getDistributedBuildQueue() ).thenReturn( distributedBuildQueue );

        Map<String, List<PrepareBuildProjectsTask>> prepareBuildQueues =
            distributedBuildManager.getProjectsInPrepareBuildQueue();
        Map<String, List<BuildProjectTask>> buildQueues = distributedBuildManager.getProjectsInBuildQueue();
        Map<String, PrepareBuildProjectsTask> currentPrepareBuild =
            distributedBuildManager.getProjectsCurrentlyPreparingBuild();
>>>>>>> refs/remotes/apache/trunk
        Map<String, BuildProjectTask> currentBuild = distributedBuildManager.getProjectsCurrentlyBuilding();

        assertEquals( prepareBuildQueues.size(), 0 );
        assertEquals( buildQueues.size(), 0 );
        assertEquals( currentPrepareBuild.size(), 0 );
        assertEquals( currentBuild.size(), 0 );

<<<<<<< HEAD
        context.assertIsSatisfied();
    }

=======
        verify( configurationService ).updateBuildAgent( buildAgent1 );
        verify( configurationService ).store();
        verify( distributedBuildQueue ).removeAll( anyList() );
        verify( distributedBuildTaskQueueExecutor ).stop();
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testDisableBuildAgentWhenUnavailableToPing()
        throws Exception
    {
        distributedBuildManager.setOverallDistributedBuildQueues( getMockOverallDistributedBuildQueues( 1 ) );
<<<<<<< HEAD
        
        recordDisableOfBuildAgent();

        distributedBuildManager.isAgentAvailable( TEST_BUILD_AGENT1 );
        
        context.assertIsSatisfied();
    }

=======

        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
        when( configurationService.getSharedSecretPassword() ).thenReturn( null );

        distributedBuildManager.isAgentAvailable( TEST_BUILD_AGENT1 );

        verify( configurationService ).updateBuildAgent( buildAgent1 );
        verify( configurationService ).store();

        assertFalse( "build agent should have been disabled", buildAgent1.isEnabled() );
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testViewQueuesWhen2BuildAgentsAreLost()
        throws Exception
    {
        distributedBuildManager.setOverallDistributedBuildQueues( getMockOverallDistributedBuildQueues( 2 ) );

<<<<<<< HEAD
        recordViewQueuesAfter2BuildAgentsAreLost();

        Map<String, List<PrepareBuildProjectsTask>> prepareBuildQueues = distributedBuildManager.getProjectsInPrepareBuildQueue();
        Map<String, List<BuildProjectTask>> buildQueues = distributedBuildManager.getProjectsInBuildQueue();
        Map<String, PrepareBuildProjectsTask> currentPrepareBuild = distributedBuildManager.getProjectsCurrentlyPreparingBuild();
=======
        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
        when( configurationService.getSharedSecretPassword() ).thenReturn( null );
        when( overallDistributedBuildQueue1.getDistributedBuildTaskQueueExecutor() ).thenReturn(
            distributedBuildTaskQueueExecutor );
        when( overallDistributedBuildQueue2.getDistributedBuildTaskQueueExecutor() ).thenReturn(
            distributedBuildTaskQueueExecutor );
        when( distributedBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallDistributedBuildQueue1.getProjectsInQueue() ).thenReturn(
            new ArrayList<PrepareBuildProjectsTask>() );
        when( overallDistributedBuildQueue2.getProjectsInQueue() ).thenReturn(
            new ArrayList<PrepareBuildProjectsTask>() );
        when( overallDistributedBuildQueue1.getDistributedBuildQueue() ).thenReturn( distributedBuildQueue );
        when( overallDistributedBuildQueue2.getDistributedBuildQueue() ).thenReturn( distributedBuildQueue );

        Map<String, List<PrepareBuildProjectsTask>> prepareBuildQueues =
            distributedBuildManager.getProjectsInPrepareBuildQueue();
        Map<String, List<BuildProjectTask>> buildQueues = distributedBuildManager.getProjectsInBuildQueue();
        Map<String, PrepareBuildProjectsTask> currentPrepareBuild =
            distributedBuildManager.getProjectsCurrentlyPreparingBuild();
>>>>>>> refs/remotes/apache/trunk
        Map<String, BuildProjectTask> currentBuild = distributedBuildManager.getProjectsCurrentlyBuilding();

        assertEquals( prepareBuildQueues.size(), 0 );
        assertEquals( buildQueues.size(), 0 );
        assertEquals( currentPrepareBuild.size(), 0 );
        assertEquals( currentBuild.size(), 0 );

<<<<<<< HEAD
        context.assertIsSatisfied();        
    }

=======
        verify( configurationService ).updateBuildAgent( buildAgent1 );
        verify( configurationService ).updateBuildAgent( buildAgent2 );
        verify( configurationService, times( 2 ) ).store();
        verify( distributedBuildQueue, times( 2 ) ).removeAll( anyList() );
        verify( distributedBuildTaskQueueExecutor, times( 2 ) ).stop();
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testBuildProjectWithBuildAgentGroupWithNoCurrentBuilds()
        throws Exception
    {
        distributedBuildManagerStub.setOverallDistributedBuildQueues( getMockOverallDistributedBuildQueues( 2 ) );

        Map<Integer, Integer> projectsBuildDefinitionsMap = new HashMap<Integer, Integer>();
        projectsBuildDefinitionsMap.put( 1, 1 );
        projectsBuildDefinitionsMap.put( 2, 1 );

        BuildTrigger buildTrigger = new BuildTrigger( 1 );

<<<<<<< HEAD
        recordBuildOfProjectWithBuildAgentGroupWithNoCurrentBuilds();
=======
        when( projectDao.getProjectWithDependencies( 1 ) ).thenReturn( project );
        when( projectDao.getProjectWithDependencies( 2 ) ).thenReturn( project2 );
        when( buildDefinitionDao.getBuildDefinition( 1 ) ).thenReturn( buildDefinition );
        when( configurationService.getBuildAgentGroup( TEST_BUILD_AGENT_GROUP1 ) ).thenReturn( buildAgentGroup );
        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
>>>>>>> refs/remotes/apache/trunk

        List<ProjectScmRoot> scmRoots = new ArrayList<ProjectScmRoot>();
        ProjectScmRoot scmRoot = new ProjectScmRoot();
        scmRoot.setId( 1 );
        scmRoot.setProjectGroup( projectGroup );
        scmRoot.setScmRootAddress( "scmRootAddress1" );
        scmRoots.add( scmRoot );

        scmRoot = new ProjectScmRoot();
        scmRoot.setId( 2 );
        scmRoot.setProjectGroup( projectGroup );
        scmRoot.setScmRootAddress( "scmRootAddress2" );
        scmRoots.add( scmRoot );

<<<<<<< HEAD
        distributedBuildManagerStub.prepareBuildProjects( projectsBuildDefinitionsMap, buildTrigger, 1, "sample", "scmRootAddress1", 1, scmRoots );

        context.assertIsSatisfied();
    }

=======
        distributedBuildManagerStub.prepareBuildProjects( projectsBuildDefinitionsMap, buildTrigger, 1, "sample",
                                                          "scmRootAddress1", 1, scmRoots );

        verify( overallDistributedBuildQueue1 ).getBuildAgentUrl();
        verify( overallDistributedBuildQueue1 ).addToDistributedBuildQueue( any( Task.class ) );
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testBuildProjectWithBuildAgentGroupWithCurrentBuild()
        throws Exception
    {
        distributedBuildManagerStub.setOverallDistributedBuildQueues( getMockOverallDistributedBuildQueues( 2 ) );

        Map<Integer, Integer> projectsBuildDefinitionsMap = new HashMap<Integer, Integer>();
        projectsBuildDefinitionsMap.put( 1, 1 );

        BuildTrigger buildTrigger = new BuildTrigger( 1 );

<<<<<<< HEAD
        recordBuildOfProjectWithBuildAgentGroupWithCurrentBuild();
=======
        when( overallDistributedBuildQueue1.getDistributedBuildTaskQueueExecutor() ).thenReturn(
            distributedBuildTaskQueueExecutor );
        when( distributedBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( projectDao.getProjectsInGroup( 1 ) ).thenReturn( new ArrayList<Project>() );
        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
>>>>>>> refs/remotes/apache/trunk

        List<ProjectScmRoot> scmRoots = new ArrayList<ProjectScmRoot>();
        ProjectScmRoot scmRoot = new ProjectScmRoot();
        scmRoot.setId( 2 );
        scmRoot.setProjectGroup( projectGroup );
        scmRoot.setScmRootAddress( "scmRootAddress2" );
        scmRoots.add( scmRoot );

        scmRoot = new ProjectScmRoot();
        scmRoot.setId( 1 );
        scmRoot.setProjectGroup( projectGroup );
        scmRoot.setScmRootAddress( "scmRootAddress1" );
        scmRoots.add( scmRoot );

<<<<<<< HEAD
        distributedBuildManagerStub.prepareBuildProjects( projectsBuildDefinitionsMap, buildTrigger, 1, "sample", "scmRootAddress1", 1, scmRoots );

        context.assertIsSatisfied();
    }

    // CONTINUUM-2494
=======
        distributedBuildManagerStub.prepareBuildProjects( projectsBuildDefinitionsMap, buildTrigger, 1, "sample",
                                                          "scmRootAddress1", 1, scmRoots );

        verify( overallDistributedBuildQueue1 ).getProjectsInQueue();
        verify( overallDistributedBuildQueue1 ).getBuildAgentUrl();
        verify( overallDistributedBuildQueue1 ).addToDistributedBuildQueue( any( Task.class ) );
    }

    // CONTINUUM-2494
    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testBuildProjectWithTheSecondBuildAgentAttachedToTheBuildAgentGroup()
        throws Exception
    {
        distributedBuildManagerStub.setOverallDistributedBuildQueues( getMockOverallDistributedBuildQueues( 2 ) );
<<<<<<< HEAD
        
        List<BuildAgentConfiguration> buildAgents = new ArrayList<BuildAgentConfiguration>();        
        buildAgents.add( buildAgent2 );
        
=======

        final List<BuildAgentConfiguration> buildAgents = new ArrayList<BuildAgentConfiguration>();
        buildAgents.add( buildAgent2 );

>>>>>>> refs/remotes/apache/trunk
        setUpBuildAgentGroup( buildAgents );
        setupBuildDefinition();

        Map<Integer, Integer> projectsBuildDefinitionsMap = new HashMap<Integer, Integer>();
        projectsBuildDefinitionsMap.put( 1, 1 );

        BuildTrigger buildTrigger = new BuildTrigger( 1 );

<<<<<<< HEAD
        recordBuildProjectWithTheSecondBuildAgentAttachedToTheBuildAgentGroup();
=======
        when( projectDao.getProjectWithDependencies( 1 ) ).thenReturn( project );
        when( buildDefinitionDao.getBuildDefinition( 1 ) ).thenReturn( buildDefinition );
        when( configurationService.getBuildAgentGroup( TEST_BUILD_AGENT_GROUP1 ) ).thenReturn( buildAgentGroup );
        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
>>>>>>> refs/remotes/apache/trunk

        List<ProjectScmRoot> scmRoots = new ArrayList<ProjectScmRoot>();
        ProjectScmRoot scmRoot = new ProjectScmRoot();
        scmRoot.setId( 1 );
        scmRoot.setProjectGroup( projectGroup );
        scmRoot.setScmRootAddress( "scmRootAddress1" );
        scmRoots.add( scmRoot );

<<<<<<< HEAD
        distributedBuildManagerStub.prepareBuildProjects( projectsBuildDefinitionsMap, buildTrigger, 1, "sample", "scmRootAddress", 1, scmRoots );
    }
    
    private Map<String, OverallDistributedBuildQueue> getMockOverallDistributedBuildQueues( int size )
    {
        Map<String, OverallDistributedBuildQueue> overallDistributedBuildQueues =
            Collections.synchronizedMap( new LinkedHashMap<String, OverallDistributedBuildQueue>() );

        buildAgents = new ArrayList<BuildAgentConfiguration>();
        buildAgents.add( buildAgent1 );

        overallDistributedBuildQueues.put( TEST_BUILD_AGENT1, overallDistributedBuildQueue1 );

        if( size == 2 )
        {
            buildAgents.add( buildAgent2 );
            overallDistributedBuildQueues.put( TEST_BUILD_AGENT2, overallDistributedBuildQueue2 );
        }
                
        return overallDistributedBuildQueues;
    }
    
    private void setUpBuildAgentGroup( List<BuildAgentConfiguration> buildAgents )
    {
        buildAgentGroup = new BuildAgentGroupConfiguration();
        buildAgentGroup.setName( TEST_BUILD_AGENT_GROUP1 );
        buildAgentGroup.setBuildAgents( buildAgents );
    }
    
    private void setupBuildDefinition()
    {
        Profile buildEnv1 = new Profile();
        buildEnv1.setBuildAgentGroup( TEST_BUILD_AGENT_GROUP1 );

        buildDefinition = new BuildDefinition();
        buildDefinition.setId( 1 );
        buildDefinition.setProfile( buildEnv1 );
    }

    private void recordDisableOfBuildAgent()
        throws Exception
    {
        context.checking( new Expectations()
        {
            {
                one( configurationService ).getBuildAgents();
                will( returnValue( buildAgents ) );

                one( configurationService ).updateBuildAgent( buildAgent1 );
                one( configurationService ).store();
            }
        } );
    }

    private void recordViewQueuesAfterBuildAgentIsLost()
        throws Exception
    {
        context.checking( new Expectations()
        {
            {
                exactly( 5 ).of( configurationService ).getBuildAgents();
                will( returnValue( buildAgents ) );

                one( configurationService ).updateBuildAgent( buildAgent1 );
                one( configurationService ).store();

                exactly( 2 ).of( overallDistributedBuildQueue1 ).getDistributedBuildTaskQueueExecutor();
                will( returnValue( distributedBuildTaskQueueExecutor ) );

                one( distributedBuildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( overallDistributedBuildQueue1 ).getProjectsInQueue();
                will( returnValue( new ArrayList<PrepareBuildProjectsTask>() ) );

                one( overallDistributedBuildQueue1 ).getDistributedBuildQueue();
                will( returnValue( distributedBuildQueue ) );

                one( distributedBuildQueue ).removeAll( new ArrayList<PrepareBuildProjectsTask>() );

                one( distributedBuildTaskQueueExecutor ).stop();
            }
        } );
    }

    private void recordViewQueuesAfter2BuildAgentsAreLost()
        throws Exception
    {
        context.checking( new Expectations()
        {
            {
                exactly( 6 ).of( configurationService ).getBuildAgents();
                will( returnValue( buildAgents ) );

                one( configurationService ).updateBuildAgent( buildAgent1 );
                one( configurationService ).updateBuildAgent( buildAgent2 );
                exactly( 2 ).of( configurationService ).store();

                exactly( 2 ).of( overallDistributedBuildQueue1 ).getDistributedBuildTaskQueueExecutor();
                will( returnValue( distributedBuildTaskQueueExecutor ) );

                exactly( 2 ).of( overallDistributedBuildQueue2 ).getDistributedBuildTaskQueueExecutor();
                will( returnValue( distributedBuildTaskQueueExecutor ) );

                exactly( 2 ).of( distributedBuildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( overallDistributedBuildQueue1 ).getProjectsInQueue();
                will( returnValue( new ArrayList<PrepareBuildProjectsTask>() ) );

                one( overallDistributedBuildQueue2 ).getProjectsInQueue();
                will( returnValue( new ArrayList<PrepareBuildProjectsTask>() ) );

                one( overallDistributedBuildQueue1 ).getDistributedBuildQueue();
                will( returnValue( distributedBuildQueue ) );

                one( overallDistributedBuildQueue2 ).getDistributedBuildQueue();
                will( returnValue( distributedBuildQueue ) );

                exactly( 2 ).of( distributedBuildQueue ).removeAll( new ArrayList<PrepareBuildProjectsTask>() );

                exactly( 2 ).of( distributedBuildTaskQueueExecutor ).stop();
            }
        } );
    }

    private void recordBuildOfProjectWithBuildAgentGroupWithNoCurrentBuilds()
        throws Exception
    {
        context.checking( new Expectations()
        {
            {
                exactly( 3 ).of( projectDao ).getProjectWithDependencies( 1 );
                will( returnValue( project ) );

                exactly( 3 ).of( projectDao ).getProjectWithDependencies( 2 );
                will( returnValue( project2) );

                exactly( 3 ).of( buildDefinitionDao ).getBuildDefinition( 1 );
                will( returnValue( buildDefinition ) );

                exactly( 3 ).of( configurationService ).getBuildAgentGroup( TEST_BUILD_AGENT_GROUP1 );
                will( returnValue( buildAgentGroup ) );

                one( configurationService ).getBuildAgents();
                will( returnValue( buildAgents ) );

                one( overallDistributedBuildQueue1 ).addToDistributedBuildQueue( with( any( Task.class ) ) );
            }
        } );
    }

    private void recordBuildOfProjectWithBuildAgentGroupWithCurrentBuild()
        throws Exception
    {
        context.checking( new Expectations()
        {
            {
                one( overallDistributedBuildQueue1 ).getProjectsInQueue();

                one( overallDistributedBuildQueue1 ).getDistributedBuildTaskQueueExecutor();
                will( returnValue( distributedBuildTaskQueueExecutor ) );

                one( distributedBuildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( projectDao ).getProjectsInGroup( 1 );
                will( returnValue( new ArrayList<Project>() ) );

                one( configurationService ).getBuildAgents();
                will( returnValue( buildAgents ) );

                one( overallDistributedBuildQueue1 ).addToDistributedBuildQueue( with( any( Task.class ) ) );
            }
        } );
    }

    private void recordBuildProjectWithTheSecondBuildAgentAttachedToTheBuildAgentGroup()
        throws Exception
    {
        context.checking( new Expectations()
        {
            {
                exactly( 3 ).of( projectDao ).getProjectWithDependencies( 1 );
                will( returnValue( project ) );

                exactly( 3 ).of( buildDefinitionDao ).getBuildDefinition( 1 );
                will( returnValue( buildDefinition ) );

                exactly( 3 ).of( configurationService ).getBuildAgentGroup( TEST_BUILD_AGENT_GROUP1 );
                will( returnValue( buildAgentGroup ) );

                one( configurationService ).getBuildAgents();
                will( returnValue( buildAgents ) );

                one( overallDistributedBuildQueue2 ).addToDistributedBuildQueue( with( any( Task.class ) ) );
            }
        } );
=======
        distributedBuildManagerStub.prepareBuildProjects( projectsBuildDefinitionsMap, buildTrigger, 1, "sample",
                                                          "scmRootAddress", 1, scmRoots );

        verify( overallDistributedBuildQueue2 ).getBuildAgentUrl();
        verify( overallDistributedBuildQueue2 ).addToDistributedBuildQueue( any( Task.class ) );
    }

    @Test
    public void testGetBuildAgentPlatform()
        throws Exception
    {
        distributedBuildManager.setOverallDistributedBuildQueues( getMockOverallDistributedBuildQueues( 1 ) );

        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
        when( configurationService.getSharedSecretPassword() ).thenReturn( null );
        when( overallDistributedBuildQueue1.getDistributedBuildTaskQueueExecutor() ).thenReturn(
            distributedBuildTaskQueueExecutor );
        when( distributedBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallDistributedBuildQueue1.getProjectsInQueue() ).thenReturn(
            new ArrayList<PrepareBuildProjectsTask>() );
        when( overallDistributedBuildQueue1.getDistributedBuildQueue() ).thenReturn( distributedBuildQueue );

        assertEquals( distributedBuildManager.getBuildAgentPlatform( TEST_BUILD_AGENT1 ), "" );

        verify( configurationService ).updateBuildAgent( buildAgent1 );
        verify( configurationService ).store();
        verify( distributedBuildQueue ).removeAll( anyList() );
        verify( distributedBuildTaskQueueExecutor ).stop();
    }

    @Test
    public void testBuildAgentIsAvailable()
        throws Exception
    {
        assertTrue( distributedBuildManagerStub.isAgentAvailable( TEST_BUILD_AGENT1 ) );

        verify( configurationService, never() ).getBuildAgents();
        verify( configurationService, never() ).updateBuildAgent( buildAgent1 );
        verify( configurationService, never() ).store();
    }

    @Test
    public void testCancelBuildStuckUpdate()
        throws Exception
    {
        distributedBuildManagerStub.setCurrentRuns( getCurrentRuns() );

        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
        ProjectScmRoot scmRootUpdating = getScmRoot( ContinuumProjectState.UPDATING );
        when( projectScmRootDao.getProjectScmRoot( 1 ) ).thenReturn( scmRootUpdating,
                                                                     getScmRoot( ContinuumProjectState.ERROR ) );

        distributedBuildManagerStub.cancelBuild( 1 );

        verify( projectScmRootDao ).updateProjectScmRoot( scmRootUpdating );
    }

    @Test
    public void testCancelBuildStuckBuild()
        throws Exception
    {
        distributedBuildManagerStub.setCurrentRuns( getCurrentRuns() );

        when( configurationService.getBuildAgents() ).thenReturn( buildAgents );
        when( projectScmRootDao.getProjectScmRoot( 1 ) ).thenReturn( getScmRoot( ContinuumProjectState.OK ) );
        Project proj1 = getProject( 1, ContinuumProjectState.BUILDING );
        when( projectDao.getProject( 1 ) ).thenReturn( proj1 );
        when( buildDefinitionDao.getBuildDefinition( 1 ) ).thenReturn( new BuildDefinition() );
        when( projectDao.getProject( 2 ) ).thenReturn( getProject( 2, ContinuumProjectState.OK ) );

        distributedBuildManagerStub.cancelBuild( 1 );

        verify( buildResultDao ).addBuildResult( any( Project.class ), any( BuildResult.class ) );
        verify( projectDao ).updateProject( proj1 );
    }

    private List<ProjectRunSummary> getCurrentRuns()
    {
        List<ProjectRunSummary> runs = new ArrayList<ProjectRunSummary>();

        ProjectRunSummary run1 = new ProjectRunSummary();
        run1.setProjectId( 1 );
        run1.setBuildDefinitionId( 1 );
        run1.setProjectGroupId( 1 );
        run1.setProjectScmRootId( 1 );
        run1.setTrigger( 1 );
        run1.setTriggeredBy( "user" );
        run1.setBuildAgentUrl( "http://localhost:8181/continuum-buildagent/xmlrpc" );
        runs.add( run1 );

        ProjectRunSummary run2 = new ProjectRunSummary();
        run2.setProjectId( 2 );
        run2.setBuildDefinitionId( 2 );
        run2.setProjectGroupId( 1 );
        run2.setProjectScmRootId( 1 );
        run2.setTrigger( 1 );
        run2.setTriggeredBy( "user" );
        run2.setBuildAgentUrl( "http://localhost:8181/continuum-buildagent/xmlrpc" );
        runs.add( run2 );

        return runs;
    }

    private ProjectScmRoot getScmRoot( int state )
    {
        ProjectScmRoot scmRoot = new ProjectScmRoot();
        scmRoot.setState( state );
        return scmRoot;
    }

    private Project getProject( int projectId, int state )
    {
        Project project = new Project();
        project.setId( projectId );
        project.setState( state );
        return project;
    }

    private Map<String, OverallDistributedBuildQueue> getMockOverallDistributedBuildQueues( int size )
    {
        Map<String, OverallDistributedBuildQueue> overallDistributedBuildQueues = Collections.synchronizedMap(
            new LinkedHashMap<String, OverallDistributedBuildQueue>() );

        buildAgents = new ArrayList<BuildAgentConfiguration>();
        buildAgents.add( buildAgent1 );

        overallDistributedBuildQueues.put( TEST_BUILD_AGENT1, overallDistributedBuildQueue1 );

        if ( size == 2 )
        {
            buildAgents.add( buildAgent2 );
            overallDistributedBuildQueues.put( TEST_BUILD_AGENT2, overallDistributedBuildQueue2 );
        }

        return overallDistributedBuildQueues;
    }

    private void setUpBuildAgentGroup( List<BuildAgentConfiguration> buildAgents )
    {
        buildAgentGroup = new BuildAgentGroupConfiguration();
        buildAgentGroup.setName( TEST_BUILD_AGENT_GROUP1 );
        buildAgentGroup.setBuildAgents( buildAgents );
    }

    private void setupBuildDefinition()
    {
        Profile buildEnv1 = new Profile();
        buildEnv1.setBuildAgentGroup( TEST_BUILD_AGENT_GROUP1 );

        buildDefinition = new BuildDefinition();
        buildDefinition.setId( 1 );
        buildDefinition.setProfile( buildEnv1 );
>>>>>>> refs/remotes/apache/trunk
    }
}