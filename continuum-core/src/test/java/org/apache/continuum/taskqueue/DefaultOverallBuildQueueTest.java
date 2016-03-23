package org.apache.continuum.taskqueue;

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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.continuum.dao.BuildDefinitionDao;
import org.apache.continuum.taskqueueexecutor.ParallelBuildsThreadedTaskQueueExecutor;
import org.apache.maven.continuum.buildqueue.BuildProjectTask;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.scm.queue.CheckOutTask;
import org.codehaus.plexus.spring.PlexusInSpringTestCase;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

/**
 * DefaultOverallBuildQueueTest
 * 
 * @author <a href="mailto:oching@apache.org">Maria Odea Ching</a>
 *
 */
public class DefaultOverallBuildQueueTest
    extends PlexusInSpringTestCase
{
    private DefaultOverallBuildQueue overallQueue;

    private Mockery context;

=======
import org.apache.continuum.dao.BuildDefinitionDao;
import org.apache.continuum.taskqueueexecutor.ParallelBuildsThreadedTaskQueueExecutor;
import org.apache.continuum.utils.build.BuildTrigger;
import org.apache.maven.continuum.PlexusSpringTestCase;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * DefaultOverallBuildQueueTest
 *
 * @author <a href="mailto:oching@apache.org">Maria Odea Ching</a>
 */
public class DefaultOverallBuildQueueTest
    extends PlexusSpringTestCase
{
    private DefaultOverallBuildQueue overallQueue;

>>>>>>> refs/remotes/apache/trunk
    private BuildDefinitionDao buildDefinitionDao;

    private ParallelBuildsThreadedTaskQueueExecutor buildTaskQueueExecutor;

    private ParallelBuildsThreadedTaskQueueExecutor checkoutTaskQueueExecutor;

<<<<<<< HEAD
    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();

        overallQueue = new DefaultOverallBuildQueue();

        context = new JUnit3Mockery();

        buildDefinitionDao = context.mock( BuildDefinitionDao.class );
        context.setImposteriser( ClassImposteriser.INSTANCE );

        buildTaskQueueExecutor = context.mock( ParallelBuildsThreadedTaskQueueExecutor.class, "build-queue-executor" );

        checkoutTaskQueueExecutor =
            context.mock( ParallelBuildsThreadedTaskQueueExecutor.class, "checkout-queue-executor" );

        overallQueue.setBuildDefinitionDao( buildDefinitionDao );

        overallQueue.setBuildTaskQueueExecutor( buildTaskQueueExecutor );

        overallQueue.setCheckoutTaskQueueExecutor( checkoutTaskQueueExecutor );
    }

    // checkout queue

    public void testAddToCheckoutQueue()
        throws Exception
    {
        final Task checkoutTask =
            new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ), "continuum-project-test-1",
                              "dummy", "dummypass" );
        final TaskQueue checkoutQueue = context.mock( TaskQueue.class, "checkout-queue" );

        context.checking( new Expectations()
        {
            {
                one( checkoutTaskQueueExecutor ).getQueue();
                will( returnValue( checkoutQueue ) );

                one( checkoutQueue ).put( checkoutTask );
            }
        } );

        overallQueue.addToCheckoutQueue( checkoutTask );
        context.assertIsSatisfied();
    }

    public void testGetProjectsInCheckoutQueue()
        throws Exception
    {
        final TaskQueue checkoutQueue = context.mock( TaskQueue.class, "checkout-queue" );
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add( new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ),
                                     "continuum-project-test-1", "dummy", "dummypass" ) );

        context.checking( new Expectations()
        {
            {
                one( checkoutTaskQueueExecutor ).getQueue();
                will( returnValue( checkoutQueue ) );

                one( checkoutQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );
            }
        } );

        List<CheckOutTask> returnedTasks = overallQueue.getProjectsInCheckoutQueue();
        context.assertIsSatisfied();
=======
    private ParallelBuildsThreadedTaskQueueExecutor prepareBuildTaskQueueExecutor;

    @Before
    public void setUp()
        throws Exception
    {
        overallQueue = new DefaultOverallBuildQueue();

        buildDefinitionDao = mock( BuildDefinitionDao.class );
        buildTaskQueueExecutor = mock( ParallelBuildsThreadedTaskQueueExecutor.class, "build-queue-executor" );
        checkoutTaskQueueExecutor = mock( ParallelBuildsThreadedTaskQueueExecutor.class, "checkout-queue-executor" );
        prepareBuildTaskQueueExecutor = mock( ParallelBuildsThreadedTaskQueueExecutor.class,
                                              "prepare-build-queue-executor" );

        overallQueue.setBuildDefinitionDao( buildDefinitionDao );
        overallQueue.setBuildTaskQueueExecutor( buildTaskQueueExecutor );
        overallQueue.setCheckoutTaskQueueExecutor( checkoutTaskQueueExecutor );
        overallQueue.setPrepareBuildTaskQueueExecutor( prepareBuildTaskQueueExecutor );
    }

    // checkout queue
    @Test
    public void testAddToCheckoutQueue()
        throws Exception
    {
        CheckOutTask checkoutTask = new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ),
                                                      "continuum-project-test-1", "dummy", "dummypass", null,
                                                      null );
        TaskQueue checkoutQueue = mock( TaskQueue.class, "checkout-queue" );
        when( checkoutTaskQueueExecutor.getQueue() ).thenReturn( checkoutQueue );

        overallQueue.addToCheckoutQueue( checkoutTask );

        verify( checkoutQueue ).put( checkoutTask );
    }

    @Test
    public void testGetProjectsInCheckoutQueue()
        throws Exception
    {
        TaskQueue checkoutQueue = mock( TaskQueue.class, "checkout-queue" );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ),
                                     "continuum-project-test-1", "dummy", "dummypass", null, null ) );
        when( checkoutTaskQueueExecutor.getQueue() ).thenReturn( checkoutQueue );
        when( checkoutQueue.getQueueSnapshot() ).thenReturn( tasks );

        List<CheckOutTask> returnedTasks = overallQueue.getProjectsInCheckoutQueue();
>>>>>>> refs/remotes/apache/trunk

        assertNotNull( returnedTasks );
        assertEquals( 1, returnedTasks.size() );
    }

<<<<<<< HEAD
    public void testIsInCheckoutQueue()
        throws Exception
    {
        final TaskQueue checkoutQueue = context.mock( TaskQueue.class, "checkout-queue" );
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add( new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ),
                                     "continuum-project-test-1", "dummy", "dummypass" ) );

        context.checking( new Expectations()
        {
            {
                one( checkoutTaskQueueExecutor ).getQueue();
                will( returnValue( checkoutQueue ) );

                one( checkoutQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );
            }
        } );

        assertTrue( overallQueue.isInCheckoutQueue( 1 ) );
        context.assertIsSatisfied();
    }

    public void testRemoveProjectFromCheckoutQueue()
        throws Exception
    {
        final Task checkoutTask =
            new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ), "continuum-project-test-1",
                              "dummy", "dummypass" );
        final TaskQueue checkoutQueue = context.mock( TaskQueue.class, "checkout-queue" );
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add( checkoutTask );

        context.checking( new Expectations()
        {
            {
                one( checkoutTaskQueueExecutor ).getQueue();
                will( returnValue( checkoutQueue ) );

                one( checkoutQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );

                one( checkoutTaskQueueExecutor ).getQueue();
                will( returnValue( checkoutQueue ) );

                one( checkoutQueue ).remove( checkoutTask );
            }
        } );

        overallQueue.removeProjectFromCheckoutQueue( 1 );
        context.assertIsSatisfied();
    }

    // build queue

    public void testAddToBuildQueue()
        throws Exception
    {
        final Task buildTask = new BuildProjectTask( 2, 1, 1, "continuum-project-test-2", "BUILD_DEF" );
        final TaskQueue buildQueue = context.mock( TaskQueue.class, "build-queue" );

        context.checking( new Expectations()
        {
            {
                one( buildTaskQueueExecutor ).getQueue();
                will( returnValue( buildQueue ) );

                one( buildQueue ).put( buildTask );
            }
        } );

        overallQueue.addToBuildQueue( buildTask );
        context.assertIsSatisfied();
    }

    public void testGetProjectsFromBuildQueue()
        throws Exception
    {
        final TaskQueue buildQueue = context.mock( TaskQueue.class, "build-queue" );
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add( new BuildProjectTask( 2, 1, 1, "continuum-project-test-2", "BUILD_DEF" ) );

        context.checking( new Expectations()
        {
            {
                one( buildTaskQueueExecutor ).getQueue();
                will( returnValue( buildQueue ) );

                one( buildQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );
            }
        } );

        List<Task> returnedTasks = overallQueue.getProjectsInBuildQueue();
        context.assertIsSatisfied();
=======
    @Test
    public void testIsInCheckoutQueue()
        throws Exception
    {
        TaskQueue checkoutQueue = mock( TaskQueue.class, "checkout-queue" );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ),
                                     "continuum-project-test-1", "dummy", "dummypass", null, null ) );
        when( checkoutTaskQueueExecutor.getQueue() ).thenReturn( checkoutQueue );
        when( checkoutQueue.getQueueSnapshot() ).thenReturn( tasks );

        assertTrue( overallQueue.isInCheckoutQueue( 1 ) );
    }

    @Test
    public void testRemoveProjectFromCheckoutQueue()
        throws Exception
    {
        Task checkoutTask = new CheckOutTask( 1, new File( getBasedir(), "/target/test-working-dir/1" ),
                                              "continuum-project-test-1", "dummy", "dummypass", null, null );
        TaskQueue checkoutQueue = mock( TaskQueue.class, "checkout-queue" );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( checkoutTask );
        when( checkoutTaskQueueExecutor.getQueue() ).thenReturn( checkoutQueue );
        when( checkoutQueue.getQueueSnapshot() ).thenReturn( tasks );

        overallQueue.removeProjectFromCheckoutQueue( 1 );

        verify( checkoutQueue ).remove( checkoutTask );
    }

    // build queue
    @Test
    public void testAddToBuildQueue()
        throws Exception
    {
        BuildProjectTask buildTask = new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ),
                                                           "continuum-project-test-2", "BUILD_DEF", null, 2 );
        TaskQueue buildQueue = mock( TaskQueue.class, "build-queue" );
        when( buildTaskQueueExecutor.getQueue() ).thenReturn( buildQueue );

        overallQueue.addToBuildQueue( buildTask );

        verify( buildQueue ).put( buildTask );
    }

    @Test
    public void testGetProjectsFromBuildQueue()
        throws Exception
    {
        TaskQueue buildQueue = mock( TaskQueue.class, "build-queue" );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2",
                                         "BUILD_DEF", null, 2 ) );
        when( buildTaskQueueExecutor.getQueue() ).thenReturn( buildQueue );
        when( buildQueue.getQueueSnapshot() ).thenReturn( tasks );

        List<BuildProjectTask> returnedTasks = overallQueue.getProjectsInBuildQueue();
>>>>>>> refs/remotes/apache/trunk

        assertNotNull( returnedTasks );
        assertEquals( 1, returnedTasks.size() );
    }

<<<<<<< HEAD
    public void testIsInBuildQueue()
        throws Exception
    {
        final TaskQueue buildQueue = context.mock( TaskQueue.class, "build-queue" );
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add( new BuildProjectTask( 2, 1, 1, "continuum-project-test-2", "BUILD_DEF" ) );

        context.checking( new Expectations()
        {
            {
                one( buildTaskQueueExecutor ).getQueue();
                will( returnValue( buildQueue ) );

                one( buildQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );
            }
        } );

        assertTrue( overallQueue.isInBuildQueue( 2 ) );
        context.assertIsSatisfied();
    }

    public void testCancelBuildTask()
        throws Exception
    {
        final Task buildTask = new BuildProjectTask( 2, 1, 1, "continuum-project-test-2", "BUILD_DEF" );

        context.checking( new Expectations()
        {
            {
                one( buildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( buildTask ) );

                one( buildTaskQueueExecutor ).cancelTask( buildTask );
            }
        } );

        overallQueue.cancelBuildTask( 2 );
        context.assertIsSatisfied();
    }

    public void testCancelCurrentBuild()
        throws Exception
    {
        final Task buildTask = new BuildProjectTask( 2, 1, 1, "continuum-project-test-2", "BUILD_DEF" );

        context.checking( new Expectations()
        {
            {
                one( buildTaskQueueExecutor ).getCurrentTask();
                will( returnValue( buildTask ) );

                one( buildTaskQueueExecutor ).cancelTask( buildTask );
            }
        } );

        overallQueue.cancelCurrentBuild();
        context.assertIsSatisfied();
    }

    public void testRemoveProjectFromBuildQueueWithGivenBuildDefinition()
        throws Exception
    {
        final BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setDescription( "Test build definition" );

        final TaskQueue buildQueue = context.mock( TaskQueue.class, "build-queue" );

        context.checking( new Expectations()
        {
            {
                one( buildDefinitionDao ).getBuildDefinition( 1 );
                will( returnValue( buildDef ) );

                one( buildTaskQueueExecutor ).getQueue();
                will( returnValue( buildQueue ) );

                one( buildQueue ).remove( with( any( Task.class ) ) );
            }
        } );

        overallQueue.removeProjectFromBuildQueue( 1, 1, 1, "continuum-project-test-1" );
        context.assertIsSatisfied();
    }

    public void testRemoveProjectFromBuildQueue()
        throws Exception
    {
        final Task buildTask = new BuildProjectTask( 1, 1, 1, "continuum-project-test-2", "BUILD_DEF" );

        final TaskQueue buildQueue = context.mock( TaskQueue.class, "build-queue" );
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add( buildTask );

        context.checking( new Expectations()
        {
            {
                one( buildTaskQueueExecutor ).getQueue();
                will( returnValue( buildQueue ) );

                one( buildQueue ).getQueueSnapshot();
                will( returnValue( tasks ) );

                one( buildTaskQueueExecutor ).getQueue();
                will( returnValue( buildQueue ) );

                one( buildQueue ).remove( buildTask );
            }
        } );

        overallQueue.removeProjectFromBuildQueue( 1 );
        context.assertIsSatisfied();
=======
    @Test
    public void testIsInBuildQueue()
        throws Exception
    {
        TaskQueue buildQueue = mock( TaskQueue.class, "build-queue" );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-2",
                                         "BUILD_DEF", null, 2 ) );
        when( buildTaskQueueExecutor.getQueue() ).thenReturn( buildQueue );
        when( buildQueue.getQueueSnapshot() ).thenReturn( tasks );

        assertTrue( overallQueue.isInBuildQueue( 2 ) );
    }

    @Test
    public void testCancelBuildTask()
        throws Exception
    {
        Task buildTask = new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ),
                                               "continuum-project-test-2", "BUILD_DEF", null, 2 );
        when( buildTaskQueueExecutor.getCurrentTask() ).thenReturn( buildTask );

        overallQueue.cancelBuildTask( 2 );

        verify( buildTaskQueueExecutor ).cancelTask( buildTask );

    }

    @Test
    public void testCancelCurrentBuild()
        throws Exception
    {
        Task buildTask = new BuildProjectTask( 2, 1, new BuildTrigger( 1, "test-user" ),
                                               "continuum-project-test-2", "BUILD_DEF", null, 2 );
        when( buildTaskQueueExecutor.getCurrentTask() ).thenReturn( buildTask );

        overallQueue.cancelCurrentBuild();

        verify( buildTaskQueueExecutor ).cancelTask( buildTask );
    }

    @Test
    public void testRemoveProjectFromBuildQueueWithGivenBuildDefinition()
        throws Exception
    {
        BuildDefinition buildDef = new BuildDefinition();
        buildDef.setId( 1 );
        buildDef.setDescription( "Test build definition" );
        when( buildDefinitionDao.getBuildDefinition( 1 ) ).thenReturn( buildDef );
        TaskQueue buildQueue = mock( TaskQueue.class, "build-queue" );
        when( buildTaskQueueExecutor.getQueue() ).thenReturn( buildQueue );

        overallQueue.removeProjectFromBuildQueue( 1, 1, new BuildTrigger( 1, "test-user" ), "continuum-project-test-1",
                                                  1 );
        verify( buildQueue ).remove( any( Task.class ) );
    }

    @Test
    public void testRemoveProjectFromBuildQueue()
        throws Exception
    {
        TaskQueue buildQueue = mock( TaskQueue.class, "build-queue" );
        when( buildTaskQueueExecutor.getQueue() ).thenReturn( buildQueue );
        Task buildTask = new BuildProjectTask( 1, 1, new BuildTrigger( 1, "test-user" ),
                                               "continuum-project-test-2", "BUILD_DEF", null, 1 );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( buildTask );
        when( buildQueue.getQueueSnapshot() ).thenReturn( tasks );

        overallQueue.removeProjectFromBuildQueue( 1 );

        verify( buildQueue ).remove( buildTask );
    }

    // prepare build queue
    @Test
    public void testAddToPrepareBuildQueue()
        throws Exception
    {
        PrepareBuildProjectsTask prepareBuildTask = new PrepareBuildProjectsTask( new HashMap<Integer, Integer>(),
                                                                                  new BuildTrigger( 1,
                                                                                                    "test-user" ),
                                                                                  1, "Project Group A",
                                                                                  "http://scmRootAddress", 1 );
        TaskQueue prepareBuildQueue = mock( TaskQueue.class, "prepare-build-queue" );
        when( prepareBuildTaskQueueExecutor.getQueue() ).thenReturn( prepareBuildQueue );

        overallQueue.addToPrepareBuildQueue( prepareBuildTask );

        verify( prepareBuildQueue ).put( prepareBuildTask );
    }

    @Test
    public void testCancelCurrentPrepareBuild()
        throws Exception
    {
        Task prepareBuildTask = new PrepareBuildProjectsTask( new HashMap<Integer, Integer>(),
                                                              new BuildTrigger( 1, "test-user" ), 1,
                                                              "Project Group A", "http://scm.root.address", 1 );
        when( prepareBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( prepareBuildTask );

        overallQueue.cancelCurrentPrepareBuild();

        verify( prepareBuildTaskQueueExecutor ).cancelTask( prepareBuildTask );
    }

    @Test
    public void testCancelPrepareBuildTaskByProject()
        throws Exception
    {
        Map<Integer, Integer> buildDefMap = new HashMap<Integer, Integer>();
        buildDefMap.put( 1, 1 );
        Task prepareBuildTask = new PrepareBuildProjectsTask( buildDefMap, new BuildTrigger( 1, "test-user" ), 1,
                                                              "Project Group A", "http://scm.root.address", 1 );
        when( prepareBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( prepareBuildTask );

        overallQueue.cancelPrepareBuildTask( 1 );

        verify( prepareBuildTaskQueueExecutor ).cancelTask( prepareBuildTask );
    }

    @Test
    public void testCancelPrepareBuildTaskByProjectGroup()
        throws Exception
    {
        Map<Integer, Integer> buildDefMap = new HashMap<Integer, Integer>();
        buildDefMap.put( 1, 1 );
        Task prepareBuildTask = new PrepareBuildProjectsTask( buildDefMap, new BuildTrigger( 1, "test-user" ), 1,
                                                              "Project Group A", "http://scm.root.address", 2 );
        when( prepareBuildTaskQueueExecutor.getCurrentTask() ).thenReturn( prepareBuildTask );

        overallQueue.cancelPrepareBuildTask( 1, 2 );

        verify( prepareBuildTaskQueueExecutor ).cancelTask( prepareBuildTask );
    }

    @Test
    public void testGetProjectsFromPrepareBuildQueue()
        throws Exception
    {
        TaskQueue prepareBuildQueue = mock( TaskQueue.class, "prepare-build-queue" );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( new PrepareBuildProjectsTask( new HashMap<Integer, Integer>(), new BuildTrigger( 1, "test-user" ), 2,
                                                 "Project Group A", "http://scm.root.address", 2 ) );
        when( prepareBuildTaskQueueExecutor.getQueue() ).thenReturn( prepareBuildQueue );
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasks );

        List<PrepareBuildProjectsTask> returnedTasks = overallQueue.getProjectsInPrepareBuildQueue();

        assertNotNull( returnedTasks );
        assertEquals( 1, returnedTasks.size() );
    }

    @Test
    public void testIsInPrepareBuildQueueByProject()
        throws Exception
    {
        TaskQueue prepareBuildQueue = mock( TaskQueue.class, "prepare-build-queue" );
        Map<Integer, Integer> buildDefMap = new HashMap<Integer, Integer>();
        buildDefMap.put( 2, 1 );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( new PrepareBuildProjectsTask( buildDefMap, new BuildTrigger( 1, "test-user" ), 1, "Project Group A",
                                                 "http://scm.root.address", 2 ) );
        when( prepareBuildTaskQueueExecutor.getQueue() ).thenReturn( prepareBuildQueue );
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasks );

        assertTrue( overallQueue.isInPrepareBuildQueue( 2 ) );
    }

    @Test
    public void testIsInPrepareBuildQueueByProjectGroupAndScmRootId()
        throws Exception
    {
        TaskQueue prepareBuildQueue = mock( TaskQueue.class, "prepare-build-queue" );
        Map<Integer, Integer> buildDefMap = new HashMap<Integer, Integer>();
        buildDefMap.put( 2, 1 );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( new PrepareBuildProjectsTask( buildDefMap, new BuildTrigger( 1, "test-user" ), 1, "Project Group A",
                                                 "http://scm.root.address", 2 ) );
        when( prepareBuildTaskQueueExecutor.getQueue() ).thenReturn( prepareBuildQueue );
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasks );

        assertTrue( overallQueue.isInPrepareBuildQueue( 1, 2 ) );
    }

    @Test
    public void testIsInPrepareBuildQueueByProjectGroupAndScmRootAddress()
        throws Exception
    {
        TaskQueue prepareBuildQueue = mock( TaskQueue.class, "prepare-build-queue" );
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put( 2, 1 );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( new PrepareBuildProjectsTask( map, new BuildTrigger( 1, "test-user" ), 1, "Project Group A",
                                                 "http://scm.root.address", 2 ) );
        when( prepareBuildTaskQueueExecutor.getQueue() ).thenReturn( prepareBuildQueue );
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasks );

        assertTrue( overallQueue.isInPrepareBuildQueue( 1, "http://scm.root.address" ) );
    }

    @Test
    public void testRemoveProjectsFromPrepareBuildQueueByProjectGroupAndScmRootId()
        throws Exception
    {
        Task prepareBuildTask = new PrepareBuildProjectsTask( new HashMap<Integer, Integer>(), new BuildTrigger(
            1, "test-user" ), 1, "Project Group A", "http://scm.root.address", 1 );
        TaskQueue prepareBuildQueue = mock( TaskQueue.class, "prepare-build-queue" );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( prepareBuildTask );
        when( prepareBuildTaskQueueExecutor.getQueue() ).thenReturn( prepareBuildQueue );
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasks );

        overallQueue.removeProjectFromPrepareBuildQueue( 1, 1 );

        verify( prepareBuildQueue ).remove( prepareBuildTask );
    }

    @Test
    public void testRemoveProjectsFromPrepareBuildQueueByProjectGroupAndScmRootAddress()
        throws Exception
    {
        Task prepareBuildTask = new PrepareBuildProjectsTask( new HashMap<Integer, Integer>(), new BuildTrigger(
            1, "test-user" ), 1, "Project Group A", "http://scm.root.address", 1 );
        TaskQueue prepareBuildQueue = mock( TaskQueue.class, "prepare-build-queue" );
        List<Task> tasks = new ArrayList<Task>();
        tasks.add( prepareBuildTask );
        when( prepareBuildTaskQueueExecutor.getQueue() ).thenReturn( prepareBuildQueue );
        when( prepareBuildQueue.getQueueSnapshot() ).thenReturn( tasks );

        overallQueue.removeProjectFromPrepareBuildQueue( 1, "http://scm.root.address" );

        verify( prepareBuildQueue ).remove( prepareBuildTask );
>>>>>>> refs/remotes/apache/trunk
    }
}
