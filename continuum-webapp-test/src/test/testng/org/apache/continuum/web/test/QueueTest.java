package org.apache.continuum.web.test;

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

import org.apache.continuum.web.test.parent.AbstractAdminTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.continuum.web.test.ScheduleTest;



/**
 * @author José Morales Martínez
 */
<<<<<<< HEAD


@Test( groups = { "queue" }, dependsOnMethods = { "testWithCorrectUsernamePassword" } )
=======
@Test( groups = { "queue" } )
>>>>>>> refs/remotes/apache/trunk
public class QueueTest
    extends AbstractAdminTest
{
    private String buildQueueName;

    @BeforeMethod
    protected void setUp()
        throws Exception
    {
        buildQueueName = getProperty( "BUILD_QUEUE_NAME" );
    }

    @AfterClass
    protected void tearDown()
    {
        goToBuildQueuePage();
        removeBuildQueue( buildQueueName );
    }

    public void testAddBuildQueue()
    {
        setMaxBuildQueue( 2 );
<<<<<<< HEAD
        String BUILD_QUEUE_NAME = getProperty( "BUILD_QUEUE_NAME" );
        addBuildQueue( BUILD_QUEUE_NAME, true );
=======
        addBuildQueue( buildQueueName );
    }

    public void testQueuePageWithoutBuild()
    {
        clickAndWait( "link=Queues" );
        assertPage( "Continuum - Build Queue" );
        assertTextPresent( "Nothing is building" );
        assertTextNotPresent( "Project Name* Build Definition" );
        assertTextPresent( "Current Build" );
        assertTextPresent( "Build Queue" );
        assertTextPresent( "Current Checkout" );
        assertTextPresent( "Checkout Queue " );
        assertTextPresent( "Current Prepare Build" );
        assertTextPresent( "Prepare Build Queue" );
    }

    @Test( dependsOnMethods = { "testAddBuildQueue" } )
    public void testAddBuildQueueToSchedule()
    {
        String scheduleName = getProperty( "QUEUE_SCHEDULE_NAME" );
        String scheduleDescription = getProperty( "SCHEDULE_DESCRIPTION" );
        String second = getProperty( "SCHEDULE_EXPR_SECOND" );
        String minute = getProperty( "SCHEDULE_EXPR_MINUTE" );
        String hour = getProperty( "SCHEDULE_EXPR_HOUR" );
        String dayOfMonth = getProperty( "SCHEDULE_EXPR_DAY_MONTH" );
        String month = getProperty( "SCHEDULE_EXPR_MONTH" );
        String dayOfWeek = getProperty( "SCHEDULE_EXPR_DAY_WEEK" );
        String year = getProperty( "SCHEDULE_EXPR_YEAR" );
        String maxTime = getProperty( "SCHEDULE_MAX_TIME" );
        String period = getProperty( "SCHEDULE_PERIOD" );

        goToAddSchedule();
        addEditSchedule( scheduleName, scheduleDescription, second, minute, hour, dayOfMonth, month, dayOfWeek, year,
                         maxTime, period, true, true );
        try {
            goToEditSchedule( scheduleName, scheduleDescription, second, minute, hour, dayOfMonth, month, dayOfWeek, year,
                              maxTime, period );

            getSelenium().addSelection( "saveSchedule_availableBuildQueuesIds", "label=" + buildQueueName );
            getSelenium().click( "//input[@value='->']" );
            submit();
        } finally {
            removeSchedule( scheduleName );
        }
>>>>>>> refs/remotes/apache/trunk
    }

	@Test( dependsOnMethods = { "testAddBuildQueue" } ) //"testDeleteBuildQueue" } )
    public void testQueuePageWithoutBuild()
    {
        clickAndWait( "link=Queues"  );
        assertPage( "Continuum - Build Queue" );
        assertTextPresent( "Nothing is building" );
        assertTextNotPresent( "Project Name* Build Definition" );
        assertTextPresent( "Current Build" );
	    assertTextPresent( "Build Queue" );
	    assertTextPresent( "Current Checkout" );
	    assertTextPresent( "Checkout Queue " );
	    assertTextPresent( "Current Prepare Build" );
	    assertTextPresent( "Prepare Build Queue" );
    }

	@Test( dependsOnMethods = { "testAddBuildQueue", "testAddSchedule" } )
    public void testAddBuildQueueToSchedule()
  {
	    ScheduleTest sched = new ScheduleTest();

	    String SCHEDULE_NAME = getProperty( "SCHEDULE_NAME" );
        String SCHEDULE_DESCRIPTION = getProperty( "SCHEDULE_DESCRIPTION" );
        String SCHEDULE_EXPR_SECOND = getProperty( "SCHEDULE_EXPR_SECOND" );
        String SCHEDULE_EXPR_MINUTE = getProperty( "SCHEDULE_EXPR_MINUTE" );
        String SCHEDULE_EXPR_HOUR = getProperty( "SCHEDULE_EXPR_HOUR" );
        String SCHEDULE_EXPR_DAY_MONTH = getProperty( "SCHEDULE_EXPR_DAY_MONTH" );
        String SCHEDULE_EXPR_MONTH = getProperty( "SCHEDULE_EXPR_MONTH" );
        String SCHEDULE_EXPR_DAY_WEEK = getProperty( "SCHEDULE_EXPR_DAY_WEEK" );
        String SCHEDULE_EXPR_YEAR = getProperty( "SCHEDULE_EXPR_YEAR" );
        String SCHEDULE_MAX_TIME = getProperty( "SCHEDULE_MAX_TIME" );
        String SCHEDULE_PERIOD = getProperty( "SCHEDULE_PERIOD" );

        String BUILD_QUEUE_NAME = getProperty( "BUILD_QUEUE_NAME" );


      sched.goToEditSchedule( SCHEDULE_NAME, SCHEDULE_DESCRIPTION, SCHEDULE_EXPR_SECOND, SCHEDULE_EXPR_MINUTE,
              SCHEDULE_EXPR_HOUR, SCHEDULE_EXPR_DAY_MONTH, SCHEDULE_EXPR_MONTH, SCHEDULE_EXPR_DAY_WEEK,
              SCHEDULE_EXPR_YEAR, SCHEDULE_MAX_TIME, SCHEDULE_PERIOD );
	  getSelenium().addSelection("saveSchedule_availableBuildQueuesIds", "label="+BUILD_QUEUE_NAME);
	  getSelenium().click("//input[@value='->']");
	  submit();

  }

	@Test( dependsOnMethods = { "testAddBuildQueue" } )
    public void testAddNotAllowedBuildQueue()
    {
        setMaxBuildQueue( 1 );
        String secondQueue = "second_queue_name";
        addBuildQueue( secondQueue, false );
        assertTextPresent( "You are only allowed 1 number of builds in parallel." );
    }

    @Test( dependsOnMethods = { "testAddBuildQueue" } )
    public void testAddAlreadyExistBuildQueue()
    {
        setMaxBuildQueue( 3 );
<<<<<<< HEAD
        String BUILD_QUEUE_NAME = getProperty( "BUILD_QUEUE_NAME" );
        addBuildQueue( BUILD_QUEUE_NAME, false );
=======
        addBuildQueue( buildQueueName, false );
>>>>>>> refs/remotes/apache/trunk
        assertTextPresent( "Build queue name already exists." );
    }

    @Test( dependsOnMethods = { "testAddAlreadyExistBuildQueue" } )
    public void testAddEmptyBuildQueue()
    {
        setMaxBuildQueue( 3 );
        addBuildQueue( "", false, false );
        assertTextPresent( "You must define a name" );
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddBuildQueueToSchedule" } )
=======
>>>>>>> refs/remotes/apache/trunk
    public void testDeleteBuildQueue()
    {
        setMaxBuildQueue( 3 );
        goToBuildQueuePage();
<<<<<<< HEAD
        String BUILD_QUEUE_NAME = getProperty( "BUILD_QUEUE_NAME" );
        removeBuildQueue( BUILD_QUEUE_NAME );
        assertTextNotPresent( BUILD_QUEUE_NAME );
    }

    @Test( dependsOnMethods = { "testAddMavenTwoProject" } )
    public void testQueuePageWithProjectCurrentlyBuilding()
        throws Exception
    {
    	  //build a project
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_PROJ_GRP_ID" );
        String M2_PROJ_GRP_DESCRIPTION = getProperty( "M2_PROJ_GRP_DESCRIPTION" );
        buildProjectForQueuePageTest( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION, M2_PROJ_GRP_NAME );
        String location = getSelenium().getLocation();

        //check queue page while building
        getSelenium().open( "/continuum/admin/displayQueues!display.action" );
=======
        String testBuildQueue = "test_build_queue";
        addBuildQueue( testBuildQueue );

        removeBuildQueue( testBuildQueue );
        assertTextNotPresent( testBuildQueue );
    }

    @Test( dependsOnMethods = { "testQueuePageWithoutBuild" } )
    public void testQueuePageWithProjectCurrentlyBuilding()
        throws Exception
    {
        String pomUrl = getProperty( "MAVEN2_QUEUE_TEST_POM_URL" );
        String pomUsername = getProperty( "MAVEN2_QUEUE_TEST_POM_USERNAME" );
        String pomPassword = getProperty( "MAVEN2_QUEUE_TEST_POM_PASSWORD" );

        String projectGroupName = getProperty( "MAVEN2_QUEUE_TEST_POM_PROJECT_GROUP_NAME" );
        String projectGroupId = getProperty( "MAVEN2_QUEUE_TEST_POM_PROJECT_GROUP_ID" );
        String projectGroupDescription = getProperty( "MAVEN2_QUEUE_TEST_POM_PROJECT_GROUP_DESCRIPTION" );

        goToProjectGroupsSummaryPage();
        if ( !isLinkPresent( projectGroupName ) )
        {
            //build a project
            goToAddMavenTwoProjectPage();
            addMavenTwoProject( pomUrl, pomUsername, pomPassword, null, true );
        }

        buildProjectForQueuePageTest( projectGroupName, projectGroupId, projectGroupDescription );
        String location = getSelenium().getLocation();

        //check queue page while building
        getSelenium().open( baseUrl + "/admin/displayQueues.action" );
>>>>>>> refs/remotes/apache/trunk
        assertPage( "Continuum - Build Queue" );
        assertTextPresent( "Current Build" );
        assertTextPresent( "Build Queue" );
        assertTextPresent( "Current Checkout" );
        assertTextPresent( "Checkout Queue " );
        assertTextPresent( "Current Prepare Build" );
        assertTextPresent( "Prepare Build Queue" );
<<<<<<< HEAD
        assertElementPresent("//table[@id='ec_table']/tbody/tr/td[4]");
        assertTextPresent( M2_PROJ_GRP_NAME );
        getSelenium().open( location );
        waitPage();
        waitForElementPresent( "//img[@alt='Success']" );
=======
        assertElementPresent( "//table[@id='ec_table']/tbody/tr/td[4]" );
        assertTextPresent( projectGroupName );
        getSelenium().open( location );
        waitPage();
        waitForElementPresent( "//img[@alt='Success']" );
    }

    protected void goToBuildQueuePage()
    {
        clickLinkWithText( "Build Queue" );

        assertBuildQueuePage();
    }

    void assertBuildQueuePage()
    {
        assertPage( "Continuum - Parallel Build Queue" );
        assertTextPresent( "Parallel Build Queue" );
        assertTextPresent( "Name" );
        assertTextPresent( "DEFAULT_BUILD_QUEUE" );
        assertButtonWithValuePresent( "Add" );
    }

    protected void removeBuildQueue( String queueName )
    {
        clickLinkWithXPath(
            "(//a[contains(@href,'deleteBuildQueue.action') and contains(@href, '" + queueName + "')])//img" );
        assertTextPresent( "Delete Parallel Build Queue" );
        assertTextPresent( "Are you sure you want to delete the build queue \"" + queueName + "\"?" );
        assertButtonWithValuePresent( "Delete" );
        assertButtonWithValuePresent( "Cancel" );
        clickButtonWithValue( "Delete" );
        assertBuildQueuePage();
    }

    void assertAddBuildQueuePage()
    {
        assertPage( "Continuum - Add/Edit Parallel Build Queue" );
        assertTextPresent( "Add/Edit Parallel Build Queue" );
        assertTextPresent( "Name*" );
        assertElementPresent( "name" );
        assertButtonWithValuePresent( "Save" );
        assertButtonWithValuePresent( "Cancel" );
    }

    protected void addBuildQueue( String name )
    {
        addBuildQueue( name, true );
    }

    protected void addBuildQueue( String name, boolean success )
    {
        addBuildQueue( name, success, true );
    }

    protected void addBuildQueue( String name, boolean success, boolean waitForError )
    {
        goToBuildQueuePage();
        assertBuildQueuePage();
        submit();
        assertAddBuildQueuePage();
        setFieldValue( "name", name );
        if ( success )
        {
            submit();
            assertBuildQueuePage();
            assertTextPresent( name );
        }
        else
        {
            submit( waitForError );
            assertAddBuildQueuePage();
        }
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testQueuePageWithProjectCurrentlyBuilding", "testAddBuildAgent" } )
    public void testQueuePageWithProjectCurrentlyBuildingInDistributedBuilds()
        throws Exception
    {
    	  String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_PROJ_GRP_ID" );
        String M2_PROJ_GRP_DESCRIPTION = getProperty( "M2_PROJ_GRP_DESCRIPTION" );
        
        try
        {
            enableDistributedBuilds();
            buildProjectForQueuePageTest( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION, M2_PROJ_GRP_NAME );

            //check queue page while building
            getSelenium().open( "/continuum/admin/displayQueues!display.action" );
            assertPage( "Continuum - View Distributed Builds" );
            assertTextPresent( "Current Build" );
            assertTextPresent( "Build Queue" );
            assertTextPresent( "Current Prepare Build" );
            assertTextPresent( "Prepare Build Queue" );
            assertTextPresent( M2_PROJ_GRP_NAME );
            assertTextPresent( "Build Agent URL" );
	      }
	      finally
	      {
	          disableDistributedBuilds();
	      }
    }

 }
