package org.apache.continuum.buildmanager;

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

import org.apache.continuum.buildqueue.BuildQueueService;
import org.apache.continuum.dao.BuildDefinitionDao;
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.taskqueue.BuildProjectTask;
import org.apache.continuum.taskqueue.CheckOutTask;
import org.apache.continuum.taskqueue.OverallBuildQueue;
import org.apache.continuum.taskqueue.PrepareBuildProjectsTask;
import org.apache.continuum.taskqueueexecutor.ParallelBuildsThreadedTaskQueueExecutor;
import org.apache.continuum.utils.build.BuildTrigger;
<<<<<<< HEAD
=======
import org.apache.maven.continuum.PlexusSpringTestCase;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildQueue;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.Schedule;
import org.apache.maven.continuum.store.ContinuumStoreException;
<<<<<<< HEAD
import org.codehaus.plexus.spring.PlexusInSpringTestCase;
=======
>>>>>>> refs/remotes/apache/trunk
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.codehaus.plexus.taskqueue.TaskQueueException;
import org.codehaus.plexus.taskqueue.execution.TaskQueueExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * ParallelBuildsManagerTest
 *
 * @author <a href="mailto:oching@apache.org">Maria Odea Ching</a>
 */
public class ParallelBuildsManagerTest
    extends PlexusSpringTestCase
{
    private ParallelBuildsManager buildsManager;

    private BuildDefinitionDao buildDefinitionDao;

    private ProjectDao projectDao;

    private ConfigurationService configurationService;

    private OverallBuildQueue overallBuildQueue;

    private TaskQueue buildQueue;

    private TaskQueue checkoutQueue;

<<<<<<< HEAD
    private List<Project> projects;

    private TaskQueueExecutor buildTaskQueueExecutor;

    private TaskQueueExecutor checkoutTaskQueueExecutor;

    @Override
    public void setUp()
        throws Exception
    {
        super.setUp();
=======
    private TaskQueue prepareBuildQueue;
>>>>>>> refs/remotes/apache/trunk

    private List<Project> projects;

    private TaskQueueExecutor buildTaskQueueExecutor;

    private TaskQueueExecutor checkoutTaskQueueExecutor;

    private TaskQueueExecutor prepareBuildTaskQueueExecutor;

    @Before
    public void setUp()
        throws Exception
    {
        buildsManager = (ParallelBuildsManager) lookup( BuildsManager.class, "parallel" );

        buildDefinitionDao = mock( BuildDefinitionDao.class );
        configurationService = mock( ConfigurationService.class );
        prepareBuildTaskQueueExecutor = mock( TaskQueueExecutor.class, "prepare-build-task-queue" );
        buildQueue = mock( TaskQueue.class, "build-queue" );
        checkoutQueue = mock( TaskQueue.class, "checkout-queue" );
        prepareBuildQueue = mock( TaskQueue.class, "prepare-build-queue" );
        projectDao = mock( ProjectDao.class );
        buildTaskQueueExecutor = mock( TaskQueueExecutor.class, "build-task-queue" );
        checkoutTaskQueueExecutor = mock( TaskQueueExecutor.class, "checkout-task-queue" );
        BuildQueueService buildQueueService = mock( BuildQueueService.class );

        buildsManager.setBuildDefinitionDao( buildDefinitionDao );
        buildsManager.setConfigurationService( configurationService );
        buildsManager.setBuildQueueService( buildQueueService );
<<<<<<< HEAD

        buildQueue = context.mock( TaskQueue.class, "build-queue" );

        checkoutQueue = context.mock( TaskQueue.class, "checkout-queue" );

        projectDao = context.mock( ProjectDao.class );

        buildsManager.setProjectDao( projectDao );

        buildTaskQueueExecutor = context.mock( TaskQueueExecutor.class, "build-task-queue" );

        checkoutTaskQueueExecutor = context.mock( TaskQueueExecutor.class, "checkout-task-queue" );
=======
        buildsManager.setProjectDao( projectDao );
>>>>>>> refs/remotes/apache/trunk
    }

    @After
    public void tearDown()
        throws Exception
    {
        buildsManager = null;
    }

    private List<BuildQueue> getBuildQueues( int start, int end )
    {
        List<BuildQueue> buildQueues = new ArrayList<BuildQueue>();
        for ( int i = start; i <= end; i++ )
        {
            BuildQueue buildQueue = new BuildQueue();
            buildQueue.setId( i );
            if ( i == 1 )
            {
                buildQueue.setName( ConfigurationService.DEFAULT_BUILD_QUEUE_NAME );
            }
            else
            {
                buildQueue.setName( "BUILD_QUEUE_" + String.valueOf( i ) );
            }
            buildQueues.add( buildQueue );
        }

        return buildQueues;
    }

    private Schedule getSchedule( int id, int start, int end )
    {
        Schedule schedule = new Schedule();
        schedule.setId( id );
        schedule.setName( "DEFAULT_SCHEDULE" );
        schedule.setCronExpression( "0 0 * * * ?" );
        schedule.setDelay( 100 );
        schedule.setMaxJobExecutionTime( 10000 );
        schedule.setBuildQueues( getBuildQueues( start, end ) );

        return schedule;
    }

    public void setupMockOverallBuildQueues()
        throws Exception
    {
        Map<Integer, OverallBuildQueue> overallBuildQueues = Collections.synchronizedMap(
            new HashMap<Integer, OverallBuildQueue>() );
        overallBuildQueue = mock( OverallBuildQueue.class );
        for ( int i = 1; i <= 5; i++ )
        {
            overallBuildQueues.put( i, overallBuildQueue );
        }

        buildsManager.setOverallBuildQueues( overallBuildQueues );
    }

    // build project recordings
<<<<<<< HEAD
    private void recordStartOfBuildProjectSequence()
        throws TaskQueueException, ContinuumStoreException
    {
        context.checking( new Expectations()
        {
            {
                exactly( 5 ).of( overallBuildQueue ).isInBuildQueue( with( any( int.class ) ) );
                will( returnValue( false ) );

                exactly( 5 ).of( buildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( projectDao ).getProjectsInGroup( with( any( int.class ) ) );
                will( returnValue( projects ) );

                one( configurationService ).getNumberOfBuildsInParallel();
                will( returnValue( 2 ) );

                exactly( 2 ).of( overallBuildQueue ).getBuildQueue();
                will( returnValue( buildQueue ) );

                exactly( 7 ).of( overallBuildQueue ).getBuildTaskQueueExecutor();
                will( returnValue( buildTaskQueueExecutor ) );
            }} );
    }

    private void recordBuildProjectBuildQueuesAreEmpty()
        throws TaskQueueException, ContinuumStoreException
    {
        // shouldn't only the build queues attached to the schedule be checked?
        recordStartOfBuildProjectSequence();

        final List<Task> tasks = new ArrayList<Task>();
        context.checking( new Expectations()
        {
            {
                exactly( 2 ).of( buildQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );
                
                exactly( 2 ).of( buildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );
=======
    private void setupStartOfBuildProjectSequence()
        throws TaskQueueException, ContinuumStoreException
    {
        when( overallBuildQueue.isInBuildQueue( anyInt() ) ).thenReturn( false );
        when( buildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( projectDao.getProjectsInGroup( anyInt() ) ).thenReturn( projects );
        when( configurationService.getNumberOfBuildsInParallel() ).thenReturn( 2 );
        when( overallBuildQueue.getBuildQueue() ).thenReturn( buildQueue );
        when( overallBuildQueue.getBuildTaskQueueExecutor() ).thenReturn( buildTaskQueueExecutor );
    }

    private void setupBuildProjectBuildQueuesAreEmpty()
        throws TaskQueueException, ContinuumStoreException
    {
        // shouldn't only the build queues attached to the schedule be checked?
        setupStartOfBuildProjectSequence();
>>>>>>> refs/remotes/apache/trunk

        List<Task> tasks = new ArrayList<Task>();
        when( buildQueue.getQueueSnapshot() ).thenReturn( tasks );
        when( buildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_2" );
    }

    // checkout project recordings
    private void recordStartOfCheckoutProjectSequence()
        throws TaskQueueException
    {
<<<<<<< HEAD
        context.checking( new Expectations()
        {
            {
                exactly( 5 ).of( overallBuildQueue ).isInCheckoutQueue( with( any( int.class ) ) );
                will( returnValue( false ) );

                one( configurationService ).getNumberOfBuildsInParallel();
                will( returnValue( 2 ) );

                exactly( 2 ).of( overallBuildQueue ).getCheckoutQueue();
                will( returnValue( checkoutQueue ) );

                exactly( 2 ).of( overallBuildQueue ).getCheckoutTaskQueueExecutor();
                will( returnValue( checkoutTaskQueueExecutor ) );
            }} );

=======
        when( overallBuildQueue.isInCheckoutQueue( anyInt() ) ).thenReturn( false );
        when( configurationService.getNumberOfBuildsInParallel() ).thenReturn( 2 );
        when( overallBuildQueue.getCheckoutQueue() ).thenReturn( checkoutQueue );
        when( overallBuildQueue.getCheckoutTaskQueueExecutor() ).thenReturn( checkoutTaskQueueExecutor );
>>>>>>> refs/remotes/apache/trunk
    }

    private void setupCheckoutProjectBuildQueuesAreEmpty()
        throws TaskQueueException
    {
<<<<<<< HEAD
        recordStartOfCheckoutProjectSequence();

        final List<Task> tasks = new ArrayList<Task>();
        context.checking( new Expectations()
        {
            {
                exactly( 2 ).of( checkoutQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );

                exactly( 2 ).of( checkoutTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( overallBuildQueue ).getName();
                will( returnValue( "BUILD_QUEUE_2" ) );
            }} );

        recordAddToCheckoutQueue();
=======
        when( overallBuildQueue.isInCheckoutQueue( anyInt() ) ).thenReturn( false );
        when( configurationService.getNumberOfBuildsInParallel() ).thenReturn( 2 );
        when( overallBuildQueue.getCheckoutQueue() ).thenReturn( checkoutQueue );
        when( overallBuildQueue.getCheckoutTaskQueueExecutor() ).thenReturn( checkoutTaskQueueExecutor );
        when( checkoutQueue.getQueueSnapshot() ).thenReturn( new ArrayList<Task>() );
        when( checkoutTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_2" );
>>>>>>> refs/remotes/apache/trunk
    }

    // prepare build project recordings
    private void setupStartOfPrepareBuildProjectSequence()
        throws TaskQueueException, ContinuumStoreException
    {
        final BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setSchedule( getSchedule( 1, 1, 2 ) );

        when( overallBuildQueue.isInPrepareBuildQueue( anyInt(), anyInt() ) ).thenReturn( false );
        when( buildDefinitionDao.getBuildDefinition( 1 ) ).thenReturn( buildDef );
        when( configurationService.getNumberOfBuildsInParallel() ).thenReturn( 2 );
        when( overallBuildQueue.getPrepareBuildQueue() ).thenReturn( prepareBuildQueue );
        when( overallBuildQueue.getPrepareBuildTaskQueueExecutor() ).thenReturn( prepareBuildTaskQueueExecutor );
    }

    // start of test cases..

    @Test
    public void testContainer()
        throws Exception
    {
        buildsManager.setContainer( getContainer() );
        buildsManager.isProjectInAnyCurrentBuild( 1 );
        assertTrue( true ); // why is this necessary?
    }

    @Test
    public void testBuildProjectNoProjectQueuedInAnyOverallBuildQueues()
        throws Exception
    {
        setupMockOverallBuildQueues();

        BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setSchedule( getSchedule( 1, 1, 2 ) );

        setupBuildProjectBuildQueuesAreEmpty();

<<<<<<< HEAD
        buildsManager.buildProject( 1, buildDef, "continuum-project-test-1", new BuildTrigger( 1, "test-user" ), null, 1 );
=======
        buildsManager.buildProject( 1, buildDef, "continuum-project-test-1", new BuildTrigger( 1, "test-user" ), null,
                                    1 );
>>>>>>> refs/remotes/apache/trunk

        verify( overallBuildQueue ).addToBuildQueue( any( BuildProjectTask.class ) );
    }

    @Test
    public void testBuildProjectProjectsAreAlreadyQueuedInOverallBuildQueues()
        throws Exception
    {
        setupMockOverallBuildQueues();

        BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setSchedule( getSchedule( 1, 1, 2 ) );

        setupBuildProjectBuildQueuesAreEmpty();

<<<<<<< HEAD
        buildsManager.buildProject( 1, buildDef, "continuum-project-test-1", new BuildTrigger( 1, "test-user" ), null, 1 );
        context.assertIsSatisfied();
=======
        buildsManager.buildProject( 1, buildDef, "continuum-project-test-1", new BuildTrigger( 1, "test-user" ), null,
                                    1 );
>>>>>>> refs/remotes/apache/trunk

        verify( overallBuildQueue ).addToBuildQueue( any( BuildProjectTask.class ) );

        //queue second project - 1st queue is not empty, 2nd queue is empty
        reset( overallBuildQueue );
        setupStartOfBuildProjectSequence();

        // the first build queue already has a task queued
        final List<Task> tasks = new ArrayList<Task>();
        final List<Task> tasksOfFirstBuildQueue = new ArrayList<Task>();
<<<<<<< HEAD
        tasksOfFirstBuildQueue.add(
        		new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2", buildDef.getDescription(), null, 2 ) );
        context.checking( new Expectations()
        {
            {
                one( buildQueue ).getQueueSnapshot();
                will( returnValue( tasksOfFirstBuildQueue ) );

                // the second build queue has no tasks queued, so it should return 0
                one( buildQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );

                exactly( 2 ).of( buildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( overallBuildQueue ).getName();
                will( returnValue( "BUILD_QUEUE_3" ) );
            }} );

        recordAddToBuildQueue();

        buildsManager.buildProject( 2, buildDef, "continuum-project-test-2", new BuildTrigger( 1, "test-user" ), null, 2 );
        context.assertIsSatisfied();

        // queue third project - both queues have 1 task queued each
        recordStartOfBuildProjectSequence();

        // both queues have 1 task each        
        context.checking( new Expectations()
        {
            {
                exactly( 2 ).of( buildQueue ).getQueueSnapshot();
                will( returnValue( tasksOfFirstBuildQueue ) );

                exactly( 2 ).of( buildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );
                
                one( overallBuildQueue ).getName();
                will( returnValue( "BUILD_QUEUE_2" ) );
            }} );
=======
        tasksOfFirstBuildQueue.add( new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ),
                                                          "continuum-project-test-2", buildDef.getDescription(), null,
                                                          2 ) );

        when( buildQueue.getQueueSnapshot() ).thenReturn( tasksOfFirstBuildQueue, tasks );
        when( buildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_3" );

        buildsManager.buildProject( 2, buildDef, "continuum-project-test-2", new BuildTrigger( 1, "test-user" ), null,
                                    2 );

        verify( overallBuildQueue ).addToBuildQueue( any( BuildProjectTask.class ) );

        // queue third project - both queues have 1 task queued each
        reset( overallBuildQueue );
        setupStartOfBuildProjectSequence();

        // both queues have 1 task each
        when( buildQueue.getQueueSnapshot() ).thenReturn( tasksOfFirstBuildQueue, tasksOfFirstBuildQueue );
        when( buildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_2" );
>>>>>>> refs/remotes/apache/trunk

        buildsManager.buildProject( 3, buildDef, "continuum-project-test-3", new BuildTrigger( 1, "test-user" ), null,
                                    3 );

<<<<<<< HEAD
        buildsManager.buildProject( 3, buildDef, "continuum-project-test-3", new BuildTrigger( 1, "test-user" ), null, 3 );
        context.assertIsSatisfied();
=======
        verify( overallBuildQueue ).addToBuildQueue( any( BuildProjectTask.class ) );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test
    public void testRemoveProjectFromBuildQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();
        when( overallBuildQueue.isInBuildQueue( 1 ) ).thenReturn( true );

        buildsManager.removeProjectFromBuildQueue( 1 );

        verify( overallBuildQueue ).removeProjectFromBuildQueue( 1 );
    }

    @Test
    public void testRemoveProjectsFromBuildQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();
        when( overallBuildQueue.isInBuildQueue( anyInt() ) ).thenReturn( true );

        int[] projectIds = new int[] { 1, 2, 3 };
        buildsManager.removeProjectsFromBuildQueue( projectIds );

        for ( int projectId : projectIds )
            verify( overallBuildQueue ).removeProjectFromBuildQueue( projectId );
    }

    @Test
    public void testCheckoutProjectSingle()
        throws Exception
    {
        setupMockOverallBuildQueues();

        BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setSchedule( getSchedule( 1, 1, 2 ) );

        setupCheckoutProjectBuildQueuesAreEmpty();

        buildsManager.checkoutProject( 1, "continuum-project-test-1",
<<<<<<< HEAD
        			new File( getBasedir(), "/target/test-working-dir/1" ), null, "dummy", "dummypass",
        			buildDef, null );
        context.assertIsSatisfied();
=======
                                       new File( getBasedir(), "/target/test-working-dir/1" ), null,
                                       "dummy", "dummypass", buildDef, null );

        verify( overallBuildQueue ).addToCheckoutQueue( any( CheckOutTask.class ) );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test
    public void testCheckoutProjectMultiple()
        throws Exception
    {
        setupMockOverallBuildQueues();

        BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setSchedule( getSchedule( 1, 1, 2 ) );

        setupCheckoutProjectBuildQueuesAreEmpty();

        buildsManager.checkoutProject( 1, "continuum-project-test-1",
<<<<<<< HEAD
	        		new File( getBasedir(), "/target/test-working-dir/1" ), null, "dummy", "dummypass",
	    			buildDef, null );
        context.assertIsSatisfied();

        // queue second project - 1st queue has 1 task while 2nd queue is empty; project should be queued in
        //      2nd queue
        recordStartOfCheckoutProjectSequence();

        final List<Task> tasks = new ArrayList<Task>();

        final List<Task> tasksInFirstCheckoutQueue = new ArrayList<Task>();
        tasksInFirstCheckoutQueue.add(
            new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ), "continuum-project-test-1",
            			"dummy", "dummypass", null, null ) );

        context.checking( new Expectations()
        {
            {
                one( checkoutQueue ).getQueueSnapshot();
                will( returnValue( tasksInFirstCheckoutQueue ) );

                one( checkoutQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );

                exactly( 2 ).of( checkoutTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );
                
                one( overallBuildQueue ).getName();
                will( returnValue( "BUILD_QUEUE_3" ) );
            }} );
=======
                                       new File( getBasedir(), "/target/test-working-dir/1" ), null,
                                       "dummy", "dummypass", buildDef, null );

        verify( overallBuildQueue ).addToCheckoutQueue( any( CheckOutTask.class ) );

        // queue second project - 1st queue has 1 task while 2nd queue is empty; project should be queued in 2nd queue
        reset( overallBuildQueue );
        recordStartOfCheckoutProjectSequence();

        List<Task> tasks = new ArrayList<Task>();
        List<Task> tasksInFirstCheckoutQueue = new ArrayList<Task>();
        tasksInFirstCheckoutQueue.add( new CheckOutTask( 1,
                                                         new File( getBasedir(), "/target/test-working-dir/1" ),
                                                         "continuum-project-test-1", "dummy", "dummypass", null,
                                                         null ) );

        when( checkoutQueue.getQueueSnapshot() ).thenReturn( tasksInFirstCheckoutQueue, tasks );
        when( checkoutTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_3" );
>>>>>>> refs/remotes/apache/trunk

        buildsManager.checkoutProject( 2, "continuum-project-test-2", new File( getBasedir(),
                                                                                "/target/test-working-dir/1" ), null,
                                       "dummy", "dummypass", buildDef, null );

<<<<<<< HEAD
        buildsManager.checkoutProject( 2, "continuum-project-test-2",
	        		new File( getBasedir(), "/target/test-working-dir/1" ), null, "dummy", "dummypass",
	    			buildDef, null );
        context.assertIsSatisfied();
=======
        verify( overallBuildQueue ).addToCheckoutQueue( any( CheckOutTask.class ) );
>>>>>>> refs/remotes/apache/trunk

        reset( overallBuildQueue );
        recordStartOfCheckoutProjectSequence();

<<<<<<< HEAD
        context.checking( new Expectations()
        {
            {
                exactly( 2 ).of( checkoutQueue ).getQueueSnapshot();
                will( returnValue( tasksInFirstCheckoutQueue ) );

                exactly( 2 ).of( checkoutTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );
                
                one( overallBuildQueue ).getName();
                will( returnValue( "BUILD_QUEUE_2" ) );
            }} );

        recordAddToCheckoutQueue();

        buildsManager.checkoutProject( 3, "continuum-project-test-3",
	        		new File( getBasedir(), "/target/test-working-dir/1" ), null, "dummy", "dummypass",
	    			buildDef, null );
        context.assertIsSatisfied();
=======
        // queue third project - both queues have 1 task queued each; third project should be queued in 1st queue
        when( checkoutQueue.getQueueSnapshot() ).thenReturn( tasksInFirstCheckoutQueue );
        when( checkoutTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_2" );

        buildsManager.checkoutProject( 3, "continuum-project-test-3",
                                       new File( getBasedir(), "/target/test-working-dir/1" ), null,
                                       "dummy", "dummypass", buildDef, null );

        verify( overallBuildQueue ).addToCheckoutQueue( any( CheckOutTask.class ) );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test
    public void testRemoveProjectFromCheckoutQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();
        when( overallBuildQueue.isInCheckoutQueue( 1 ) ).thenReturn( true );

        buildsManager.removeProjectFromCheckoutQueue( 1 );

        verify( overallBuildQueue ).removeProjectFromCheckoutQueue( 1 );
    }

    @Test
    public void testRemoveProjectsFromCheckoutQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();
        when( overallBuildQueue.isInCheckoutQueue( anyInt() ) ).thenReturn( true );

        int[] projectIds = new int[] { 1, 2, 3 };
        buildsManager.removeProjectsFromCheckoutQueue( projectIds );

        for ( int projectId : projectIds )
            verify( overallBuildQueue ).removeProjectFromCheckoutQueue( projectId );
    }

    @Test
    public void testRemoveProjectFromCheckoutQueueProjectNotFound()
        throws Exception
    {
        setupMockOverallBuildQueues();
        when( overallBuildQueue.isInCheckoutQueue( 1 ) ).thenReturn( false );

        // shouldn't only the project's build queues be checked instead of all the overall build queues?
        buildsManager.removeProjectFromCheckoutQueue( 1 );

        verify( overallBuildQueue, never() ).removeProjectFromCheckoutQueue( 1 );
    }

    @Test
    public void testRemoveDefaultOverallBuildQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();
        when( overallBuildQueue.getName() ).thenReturn( ConfigurationService.DEFAULT_BUILD_QUEUE_NAME );

        try
        {
            buildsManager.removeOverallBuildQueue( 1 );
            fail( "An exception should have been thrown." );
        }
        catch ( BuildManagerException e )
        {
            assertEquals( "Cannot remove default build queue.", e.getMessage() );
        }
    }

    @Test
    public void testRemoveOverallBuildQueueNoTasksCurrentlyExecuting()
        throws Exception
    {
        // queued tasks (both checkout & build tasks) must be transferred to the other queues!
        setupMockOverallBuildQueues();

        BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setSchedule( getSchedule( 1, 1, 2 ) );

        List<BuildProjectTask> buildTasks = new ArrayList<BuildProjectTask>();
        buildTasks.add( new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2",
                                              "BUILD_DEF", null, 2 ) );

<<<<<<< HEAD
        final List<Task> buildTasks = new ArrayList<Task>();
        buildTasks.add( new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2", "BUILD_DEF", null, 2 ) );

        final List<CheckOutTask> checkoutTasks = new ArrayList<CheckOutTask>();
        checkoutTasks.add(
            new CheckOutTask( 2, new File( getBasedir(), "/target/test-working-dir/1" ), "continuum-project-test-2",
            			"dummy", "dummypass", null, null ) );
=======
        List<CheckOutTask> checkoutTasks = new ArrayList<CheckOutTask>();
        checkoutTasks.add( new CheckOutTask( 2, new File( getBasedir(), "/target/test-working-dir/1" ),
                                             "continuum-project-test-2", "dummy", "dummypass", null, null ) );

        List<PrepareBuildProjectsTask> prepareBuildTasks = new ArrayList<PrepareBuildProjectsTask>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put( 1, 1 );
        prepareBuildTasks.add( new PrepareBuildProjectsTask( map, new BuildTrigger( 1, "test-user" ), 1,
                                                             "Project Group A", "http://scm.root.address", 2 ) );
>>>>>>> refs/remotes/apache/trunk

        ParallelBuildsThreadedTaskQueueExecutor buildTaskQueueExecutor = mock(
            ParallelBuildsThreadedTaskQueueExecutor.class, "parallel-build-task-executor" );
        ParallelBuildsThreadedTaskQueueExecutor checkoutTaskQueueExecutor = mock(
            ParallelBuildsThreadedTaskQueueExecutor.class, "parallel-checkout-task-executor" );
        ParallelBuildsThreadedTaskQueueExecutor prepareBuildTaskQueueExecutor = mock(
            ParallelBuildsThreadedTaskQueueExecutor.class, "parallel-prepare-build-task-executor" );

        List<Task> tasks = new ArrayList<Task>();

        when( configurationService.getNumberOfBuildsInParallel() ).thenReturn( 2 );

        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_5", "BUILD_QUEUE_2" );

        // get all queued build tasks & remove them
        when( overallBuildQueue.getProjectsInBuildQueue() ).thenReturn( buildTasks );
        when( overallBuildQueue.getBuildQueue() ).thenReturn( buildQueue );
        when( buildQueue.getQueueSnapshot() ).thenReturn( tasks );

        // get all queued checkout tasks & remove them
        when( overallBuildQueue.getProjectsInCheckoutQueue() ).thenReturn( checkoutTasks );
        when( overallBuildQueue.getCheckoutQueue() ).thenReturn( checkoutQueue );
        when( checkoutQueue.getQueueSnapshot() ).thenReturn( tasks );

        // get all queued prepare build tasks & remove them
        when( overallBuildQueue.getProjectsInPrepareBuildQueue() ).thenReturn( prepareBuildTasks );
        when( overallBuildQueue.getPrepareBuildQueue() ).thenReturn( prepareBuildQueue );
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasks );

        // stop the build & checkout task queue executors
        when( overallBuildQueue.getBuildTaskQueueExecutor() ).thenReturn( buildTaskQueueExecutor );
        when( overallBuildQueue.getCheckoutTaskQueueExecutor() ).thenReturn( checkoutTaskQueueExecutor );
        when( overallBuildQueue.getPrepareBuildTaskQueueExecutor() ).thenReturn( prepareBuildTaskQueueExecutor );

        // TODO: test scenario when there are no longer build queues configured aside from the one removed?
        //      - the behaviour should be that the default build queue will be used!

        when( projectDao.getProjectsInGroup( anyInt() ) ).thenReturn( projects );
        when( buildDefinitionDao.getBuildDefinition( 1 ) ).thenReturn( buildDef );
        when( buildDefinitionDao.getDefaultBuildDefinition( 2 ) ).thenReturn( buildDef );

        when( overallBuildQueue.isInBuildQueue( anyInt() ) ).thenReturn( false );
        when( overallBuildQueue.isInCheckoutQueue( anyInt() ) ).thenReturn( false );
        when( overallBuildQueue.isInPrepareBuildQueue( anyInt(), anyInt() ) ).thenReturn( false );

<<<<<<< HEAD
                // queue to other build queue
                exactly( 4 ).of( overallBuildQueue ).isInBuildQueue( with( any( int.class ) ) );
                will( returnValue( false ) );

                exactly( 4 ).of( buildQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( projectDao ).getProjectsInGroup( with( any( int.class ) ) );
                will( returnValue( projects ) );

                one( configurationService ).getNumberOfBuildsInParallel();
                will( returnValue( 2 ) );

                exactly( 2 ).of( overallBuildQueue ).getBuildQueue();
                will( returnValue( buildQueue ) );

                exactly( 6 ).of( overallBuildQueue ).getBuildTaskQueueExecutor();
                will( returnValue( buildQueueExecutor ) );
                
                exactly( 2 ).of( buildQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );

                exactly( 2 ).of( buildQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );
                
                one( overallBuildQueue ).getName();
                will( returnValue( "BUILD_QUEUE_2" ) );

                recordAddToBuildQueue();

                // re-queue projects in the checkout queue of the deleted overall build queue
                one( buildDefinitionDao ).getDefaultBuildDefinition( 2 );
                will( returnValue( buildDef ) );

                // queue to other checkout queues
                exactly( 4 ).of( overallBuildQueue ).isInCheckoutQueue( with( any( int.class ) ) );
                will( returnValue( false ) );

                one( configurationService ).getNumberOfBuildsInParallel();
                will( returnValue( 2 ) );

                exactly( 2 ).of( overallBuildQueue ).getCheckoutQueue();
                will( returnValue( checkoutQueue ) );

                exactly( 2 ).of( overallBuildQueue ).getCheckoutTaskQueueExecutor();
                will( returnValue( checkoutQueueExecutor ) );

                exactly( 2 ).of( checkoutQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );

                exactly( 2 ).of( checkoutQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( overallBuildQueue ).getName();
                will( returnValue( "BUILD_QUEUE_2" ) );
=======
        buildsManager.removeOverallBuildQueue( 5 );

        verify( buildQueue ).removeAll( buildTasks );
        verify( checkoutQueue ).removeAll( checkoutTasks );
        verify( prepareBuildQueue ).removeAll( prepareBuildTasks );
>>>>>>> refs/remotes/apache/trunk

        verify( buildTaskQueueExecutor ).stop();
        verify( checkoutTaskQueueExecutor ).stop();
        verify( prepareBuildTaskQueueExecutor ).stop();

        verify( overallBuildQueue ).addToBuildQueue( any( BuildProjectTask.class ) );
        verify( overallBuildQueue ).addToCheckoutQueue( any( CheckOutTask.class ) );
        verify( overallBuildQueue ).addToPrepareBuildQueue( any( PrepareBuildProjectsTask.class ) );

        Map<Integer, OverallBuildQueue> overallBuildQueues = buildsManager.getOverallBuildQueues();
        assertNull( overallBuildQueues.get( 5 ) );
    }

    @Test
    public void testRemoveOverallBuildQueueTasksCurrentlyExecuting()
        throws Exception
    {
        setupMockOverallBuildQueues();

        BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setSchedule( getSchedule( 1, 1, 2 ) );

<<<<<<< HEAD
        final TaskQueueExecutor buildQueueExecutor = context.mock( TaskQueueExecutor.class, "build-queue-executor" );
        final Task buildTask = new BuildProjectTask( 1, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-1", "BUILD_DEF", null, 1 );

        final List<BuildProjectTask> buildTasks = new ArrayList<BuildProjectTask>();
        buildTasks.add( new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2", "BUILD_DEF", null, 2 ) );

        final List<CheckOutTask> checkoutTasks = new ArrayList<CheckOutTask>();
        checkoutTasks.add(
            new CheckOutTask( 2, new File( getBasedir(), "/target/test-working-dir/1" ), "continuum-project-test-2",
            			"dummy", "dummypass", null, null ) );
=======
        TaskQueueExecutor buildQueueExecutor = mock( TaskQueueExecutor.class, "build-queue-executor" );
        Task buildTask = new BuildProjectTask( 1, 1, new BuildTrigger( 1, "test-user" ),
                                               "continuum-project-test-1", "BUILD_DEF", null, 1 );

        List<BuildProjectTask> buildTasks = new ArrayList<BuildProjectTask>();
        buildTasks.add( new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2",
                                              "BUILD_DEF", null, 2 ) );

        List<CheckOutTask> checkoutTasks = new ArrayList<CheckOutTask>();
        checkoutTasks.add( new CheckOutTask( 2, new File( getBasedir(), "/target/test-working-dir/1" ),
                                             "continuum-project-test-2", "dummy", "dummypass", null, null ) );

        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_5" );
        when( overallBuildQueue.getBuildTaskQueueExecutor() ).thenReturn( buildQueueExecutor );
        when( buildQueueExecutor.getCurrentTask() ).thenReturn( buildTask );
>>>>>>> refs/remotes/apache/trunk

        try
        {
            buildsManager.removeOverallBuildQueue( 5 );
            fail( "An exception should have been thrown." );
        }
        catch ( BuildManagerException e )
        {
            assertEquals( "Cannot remove build queue. A task is currently executing.", e.getMessage() );
        }
    }

    @Test
    public void testNoBuildQueuesConfigured()
        throws Exception
    {
        Map<Integer, OverallBuildQueue> overallBuildQueues =
            Collections.synchronizedMap( new HashMap<Integer, OverallBuildQueue>() );
        overallBuildQueue = mock( OverallBuildQueue.class );
        overallBuildQueues.put( 1, overallBuildQueue );
        buildsManager.setOverallBuildQueues( overallBuildQueues );

        Schedule schedule = new Schedule();
        schedule.setId( 1 );
        schedule.setName( "DEFAULT_SCHEDULE" );
        schedule.setCronExpression( "0 0 * * * ?" );
        schedule.setDelay( 100 );
        schedule.setMaxJobExecutionTime( 10000 );

        BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setSchedule( schedule );

<<<<<<< HEAD
        context.checking( new Expectations()
        {
            {
                one( overallBuildQueue ).isInBuildQueue( with( any( int.class ) ) );
                will( returnValue( false ) );

                one( overallBuildQueue ).getBuildTaskQueueExecutor();
                will( returnValue( buildTaskQueueExecutor ) );

                one( buildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( null ) );

                one( projectDao ).getProjectsInGroup( with( any( int.class ) ) );
                will( returnValue( projects ) );

                one( configurationService ).getNumberOfBuildsInParallel();
                will( returnValue( 2 ) );

                exactly( 2 ).of( overallBuildQueue ).getName();
                will( returnValue( ConfigurationService.DEFAULT_BUILD_QUEUE_NAME ) );
=======
        when( overallBuildQueue.isInBuildQueue( anyInt() ) ).thenReturn( false );
        when( overallBuildQueue.getBuildTaskQueueExecutor() ).thenReturn( buildTaskQueueExecutor );
        when( buildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( projectDao.getProjectsInGroup( anyInt() ) ).thenReturn( projects );
        when( configurationService.getNumberOfBuildsInParallel() ).thenReturn( 2 );
        when( overallBuildQueue.getName() ).thenReturn( ConfigurationService.DEFAULT_BUILD_QUEUE_NAME );
>>>>>>> refs/remotes/apache/trunk

        buildsManager.buildProject( 1, buildDef, "continuum-project-test-1", new BuildTrigger( 1, "test-user" ), null,
                                    1 );

<<<<<<< HEAD
        buildsManager.buildProject( 1, buildDef, "continuum-project-test-1", new BuildTrigger( 1, "test-user" ), null, 1 );
        context.assertIsSatisfied();
=======
        verify( overallBuildQueue ).addToBuildQueue( any( BuildProjectTask.class ) );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test
    public void testGetProjectsInBuildQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();

<<<<<<< HEAD
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add( new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2", "BUILD_DEF", null, 2  ) );
=======
        List<BuildProjectTask> tasks = new ArrayList<BuildProjectTask>();
        tasks.add(
            new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2", "BUILD_DEF",
                                  null, 2 ) );
>>>>>>> refs/remotes/apache/trunk

        String queueName = "BUILD_QUEUE";
        when( overallBuildQueue.getName() ).thenReturn( queueName );
        when( overallBuildQueue.getProjectsInBuildQueue() ).thenReturn( tasks );

        Map<String, List<BuildProjectTask>> result = buildsManager.getProjectsInBuildQueues();

        assertEquals( 1, result.size() );
        assertTrue( queueName + " should be present in result", result.containsKey( queueName ) );
        assertEquals( tasks, result.get( queueName ) );
    }

    @Test
    public void testGetProjectsInCheckoutQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();

<<<<<<< HEAD
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add(
            new CheckOutTask( 2, new File( getBasedir(), "/target/test-working-dir/1" ), "continuum-project-test-2",
            			"dummy", "dummypass", null, null ) );
=======
        List<CheckOutTask> tasks = new ArrayList<CheckOutTask>();
        tasks.add( new CheckOutTask( 2, new File( getBasedir(), "/target/test-working-dir/1" ),
                                     "continuum-project-test-2", "dummy", "dummypass", null, null ) );
>>>>>>> refs/remotes/apache/trunk

        String queueName = "BUILD_QUEUE";
        when( overallBuildQueue.getName() ).thenReturn( queueName );
        when( overallBuildQueue.getProjectsInCheckoutQueue() ).thenReturn( tasks );

        Map<String, List<CheckOutTask>> result = buildsManager.getProjectsInCheckoutQueues();

        assertEquals( 1, result.size() );
        assertTrue( queueName + " should be present in result", result.containsKey( queueName ) );
        assertEquals( tasks, result.get( queueName ) );
    }

    // prepare build queue
    @Test
    public void testPrepareBuildProjectNoProjectQueuedInAnyOverallBuildQueues()
        throws Exception
    {
        setupMockOverallBuildQueues();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put( 1, 1 );

        setupStartOfPrepareBuildProjectSequence();

        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( new ArrayList<Task>() );
        when( prepareBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_2" );

        buildsManager.prepareBuildProjects( map, new BuildTrigger( 1, "test-user" ), 1, "Project Group A",
                                            "http://scm.root.address", 1 );

        verify( overallBuildQueue ).addToPrepareBuildQueue( any( PrepareBuildProjectsTask.class ) );
    }

    @Test
    public void testPrepareBuildProjectsAlreadyQueued()
        throws Exception
    {
        setupMockOverallBuildQueues();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put( 1, 1 );

        //queue second project - 1st queue is not empty, 2nd queue is empty 
        setupStartOfPrepareBuildProjectSequence();

        List<Task> tasks = new ArrayList<Task>();
        List<Task> tasksOfFirstPrepareBuildQueue = new ArrayList<Task>();
        tasksOfFirstPrepareBuildQueue.add( new PrepareBuildProjectsTask( new HashMap<Integer, Integer>(),
                                                                         new BuildTrigger( 1, "test-user" ), 1,
                                                                         "Project Group B", "http://scm.root.address2",
                                                                         2 ) );

        // the first prepare build queue already has a task queued
        // the second prepare build queue has no tasks queued, so it should return 0
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasksOfFirstPrepareBuildQueue, tasks );
        when( prepareBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_3" );

        buildsManager.prepareBuildProjects( map, new BuildTrigger( 1, "test-user" ), 1, "Project Group A",
                                            "http://scm.root.address", 1 );

        verify( overallBuildQueue ).addToPrepareBuildQueue( any( PrepareBuildProjectsTask.class ) );

        // queue third project - both queues have 1 task queued each
        reset( overallBuildQueue );
        setupStartOfPrepareBuildProjectSequence(); // Redundant

        // both queues have 1 task each
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasksOfFirstPrepareBuildQueue );
        when( prepareBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( null );
        when( overallBuildQueue.getName() ).thenReturn( "BUILD_QUEUE_2" );

        buildsManager.prepareBuildProjects( map, new BuildTrigger( 1, "test-user" ), 1, "Project Group A",
                                            "http://scm.root.address", 1 );

        verify( overallBuildQueue ).addToPrepareBuildQueue( any( PrepareBuildProjectsTask.class ) );
    }

    @Test
    public void testGetProjectsInPrepareBuildQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();

        List<PrepareBuildProjectsTask> tasks = new ArrayList<PrepareBuildProjectsTask>();
        tasks.add( new PrepareBuildProjectsTask( new HashMap<Integer, Integer>(), new BuildTrigger( 1, "test-user" ), 1,
                                                 "Project Group A", "http://scm.root.address", 2 ) );

        String queueName = "PREPARE_BUILD_QUEUE";
        when( overallBuildQueue.getName() ).thenReturn( queueName );
        when( overallBuildQueue.getProjectsInPrepareBuildQueue() ).thenReturn( tasks );

        Map<String, List<PrepareBuildProjectsTask>> result = buildsManager.getProjectsInPrepareBuildQueue();

        assertEquals( 1, result.size() );
        assertTrue( queueName + " should be present in result", result.containsKey( queueName ) );
        assertEquals( tasks, result.get( queueName ) );
    }

    @Test
    public void testRemoveProjectFromPrepareBuildQueue()
        throws Exception
    {
        setupMockOverallBuildQueues();
        when( overallBuildQueue.isInPrepareBuildQueue( 1, 2 ) ).thenReturn( true );

        buildsManager.removeProjectFromPrepareBuildQueue( 1, 2 );

        verify( overallBuildQueue ).removeProjectFromPrepareBuildQueue( 1, 2 );
    }

    /*
    public void testNumOfAllowedParallelBuildsIsLessThanConfiguredBuildQueues()
        throws Exception
    {
    
    }
    
    public void testPrepareBuildProjects()
        throws Exception
    {
    
    }
    
    public void testRemoveProjectFromPrepareBuildQueue()
        throws Exception
    {

    }
    */
}
