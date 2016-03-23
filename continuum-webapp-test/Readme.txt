Test with Firefox and Selenium IDE
 * Start Continuum
 * Open Firefox and navigate to Continuum (it should be on the "Create Admin User" page.)
 * in Firefox, Tools -> Selenium IDE 
 * in Selenium IDE, File -> Open Test Suite and choose src/test/selenium-ide/continuum_test_suite.html
 * in Selenium IDE, modify the Base URL if necessary (for example, http://localhost:8080/continuum)
 * in Selenium IDE, click the 'Play entire test suite' icon

Run Selenium tests in src/test/testNG with Maven, TestNG and Cargo
 * mvn clean install

Note that while tomcat7x is the default container, if you have activated any
other profiles on the command line or in your settings.xml, you need to
specify it explicitly:

 * mvn clean install -Ptomcat7x

The tests require Ant and Maven to be on your PATH. If they are not, pass the plexus.system.path argument:
 * mvn clean install -Dplexus.system.path=/path/to/apache-maven-3.0.4/bin:/path/to/apache-ant-1.8.1/bin

Run Selenium tests against an existing Continuum instance
  * mvn clean install -DbaseUrl=http://localhost:9090 -DbuildAgentUrl=http://localhost:9191/xmlrpc \
        -DappserverBase=$PWD/../continuum-webapp/target/appserver-base

  This skips the Cargo plugin configuration that starts a container with the
  Continuum webapp deployed. Note that you will need to have set this on the
  existing Continuum instance:
  
    -Dsvn.base.url=file://localhost${PWD}/../continuum-webapp-test/target/example-svn

Run Selenium tests in an alternate browser
  * mvn clean install -Ptomcat7x -Dbrowser=iexplore  (or -Dbrowser=safari or -Dbrowser=other -DbrowserPath=/path/to/browser)

(Note that you must specify the container in this case, as the browser profile disables the default container profile)

Change the port the embedded selenium runs on
  * mvn clean install -DseleniumPort=4444

Run Selenium tests in an running Selenium server or hub
  * mvn clean install -DseleniumHost=localhost -DseleniumPort=4444

 Note that this does not install anything, it simply runs through the lifecycle including the integration test phases.
 More properly it would be 'mvn clean post-integration-test', but install is much shorter to type. :)

If you'd like to run the tests from your IDE, you can start the container from Maven using:

    mvn clean test-compile selenium:start-server cargo:run

The server will run until you press Ctrl-C, and you can run the tests from the IDE.

To attach a debugger to the same process, run:

    mvn -Ptomcat7x,debug test-compile selenium:start-server cargo:run

You can also run the Selenium server against your development instance, so
that you can run the tests in your IDE:

    mvn -Ptomcat7x,debug test-compile selenium:start-server cargo:run -DbaseUrl=http://localhost:9090

The remaining properties appserverBase and buildAgentUrl need to be supplied to the running tests, along with the same
baseUrl value.

Note, the tests currently fail with "Illegal value" errors under newer
versions of Selenium and Firefox. The tests run successfully with Selenium
2.21.0 and Firefox 17.
