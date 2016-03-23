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

import java.io.File;
<<<<<<< HEAD
=======
import java.util.Collection;
>>>>>>> refs/remotes/apache/trunk
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.continuum.buildqueue.BuildQueueService;
import org.apache.continuum.buildqueue.BuildQueueServiceException;
import org.apache.continuum.dao.BuildDefinitionDao;
<<<<<<< HEAD
import org.apache.continuum.taskqueue.OverallBuildQueue;
import org.apache.continuum.taskqueueexecutor.ParallelBuildsThreadedTaskQueueExecutor;
import org.apache.maven.continuum.buildqueue.BuildProjectTask;
=======
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.taskqueue.BuildProjectTask;
import org.apache.continuum.taskqueue.CheckOutTask;
import org.apache.continuum.taskqueue.OverallBuildQueue;
import org.apache.continuum.taskqueue.PrepareBuildProjectsTask;
import org.apache.continuum.taskqueueexecutor.ParallelBuildsThreadedTaskQueueExecutor;
import org.apache.continuum.utils.build.BuildTrigger;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildQueue;
import org.apache.maven.continuum.model.project.Project;
<<<<<<< HEAD
import org.apache.maven.continuum.scm.queue.CheckOutTask;
import org.apache.maven.continuum.scm.queue.PrepareBuildProjectsTask;
=======
import org.apache.maven.continuum.model.scm.ScmResult;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.StoppingException;
<<<<<<< HEAD
import org.codehaus.plexus.taskqueue.Task;
=======
>>>>>>> refs/remotes/apache/trunk
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.codehaus.plexus.taskqueue.TaskQueueException;
import org.codehaus.plexus.taskqueue.execution.TaskQueueExecutor;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
<<<<<<< HEAD
 * Parallel builds manager. 
 * 
 * @author <a href="mailto:oching@apache.org">Maria Odea Ching</a>
 * @version $Id$
 */
public class ParallelBuildsManager    
    implements BuildsManager, Contextualizable
{
    private Logger log = LoggerFactory.getLogger( ParallelBuildsManager.class );

    // map must be synchronized!
    private Map<Integer, OverallBuildQueue> overallBuildQueues =
        Collections.synchronizedMap( new HashMap<Integer, OverallBuildQueue>() );
=======
 * Parallel builds manager.
 *
 * @author <a href="mailto:oching@apache.org">Maria Odea Ching</a>
 */
public class ParallelBuildsManager
    implements BuildsManager, Contextualizable
{
    private static final Logger log = LoggerFactory.getLogger( ParallelBuildsManager.class );

    // map must be synchronized!
    private Map<Integer, OverallBuildQueue> overallBuildQueues = Collections.synchronizedMap(
        new HashMap<Integer, OverallBuildQueue>() );
>>>>>>> refs/remotes/apache/trunk

    private static final int BUILD_QUEUE = 1;

    private static final int CHECKOUT_QUEUE = 2;

<<<<<<< HEAD
    @Resource
    private BuildDefinitionDao buildDefinitionDao;

    private TaskQueue prepareBuildQueue;

    @Resource
    private ConfigurationService configurationService;
        
=======
    private static final int PREPARE_BUILD_QUEUE = 3;

    @Resource
    private BuildDefinitionDao buildDefinitionDao;

    @Resource
    private ProjectDao projectDao;

    @Resource
    private ConfigurationService configurationService;

>>>>>>> refs/remotes/apache/trunk
    @Resource
    private BuildQueueService buildQueueService;

    private PlexusContainer container;
<<<<<<< HEAD
    
    /**
     * @see BuildsManager#buildProject(int, BuildDefinition, String, int)
     */
    public void buildProject( int projectId, BuildDefinition buildDefinition, String projectName, int trigger )
=======

    /**
     * @see BuildsManager#buildProject(int, BuildDefinition, String, BuildTrigger, ScmResult, int)
     */
    public void buildProject( int projectId, BuildDefinition buildDefinition, String projectName,
                              BuildTrigger buildTrigger, ScmResult scmResult, int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws BuildManagerException
    {
        try
        {
            if ( isInQueue( projectId, BUILD_QUEUE, -1 ) )
            {
                log.warn( "Project already queued." );
                return;
            }
<<<<<<< HEAD
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error occurred while checking if the project is already in queue: " +
                e.getMessage() );
        }
        
        OverallBuildQueue overallBuildQueue =
            getOverallBuildQueue( projectId, BUILD_QUEUE, buildDefinition.getSchedule().getBuildQueues() );

        String buildDefinitionLabel = buildDefinition.getDescription();
        if ( StringUtils.isEmpty( buildDefinitionLabel ) )
        {
            buildDefinitionLabel = buildDefinition.getGoals();
        }

        Task buildTask =
            new BuildProjectTask( projectId, buildDefinition.getId(), trigger, projectName, buildDefinitionLabel );
        try
        {
            log.info( "Project '" + projectName + "' added to overall build queue '" + overallBuildQueue.getName() + "'." );
            overallBuildQueue.addToBuildQueue( buildTask );
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error occurred while adding project to build queue: " + e.getMessage() );
=======
            else if ( isProjectInAnyCurrentBuild( projectId ) )
            {
                log.warn( "Project is already building." );
                return;
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException(
                "Error occurred while checking if the project is already in queue: " + e.getMessage() );
        }

        OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectsInGroupAreQueued( projectGroupId );

        if ( overallBuildQueue == null )
        {
            overallBuildQueue = getOverallBuildQueue( BUILD_QUEUE, buildDefinition.getSchedule().getBuildQueues() );
        }

        if ( overallBuildQueue != null )
        {
            String buildDefinitionLabel = buildDefinition.getDescription();

            if ( StringUtils.isEmpty( buildDefinitionLabel ) )
            {
                buildDefinitionLabel = buildDefinition.getGoals();
            }

            BuildProjectTask buildTask = new BuildProjectTask( projectId, buildDefinition.getId(), buildTrigger,
                                                               projectName, buildDefinitionLabel, scmResult,
                                                               projectGroupId );
            try
            {
                log.info( "Project '" + projectName + "' added to overall build queue '" + overallBuildQueue.getName() +
                              "'." );
                overallBuildQueue.addToBuildQueue( buildTask );
            }
            catch ( TaskQueueException e )
            {
                throw new BuildManagerException(
                    "Error occurred while adding project to build queue: " + e.getMessage() );
            }
        }
        else
        {
            log.warn( "No build queue configured. Not building." );
>>>>>>> refs/remotes/apache/trunk
        }
    }

    /**
<<<<<<< HEAD
     * @see BuildsManager#buildProjects(List, Map, int)
     */
    public void buildProjects( List<Project> projects, Map<Integer, BuildDefinition> projectsBuildDefinitionsMap,
                               int trigger )
        throws BuildManagerException
    {
        int firstProjectId = 0;
=======
     * @see BuildsManager#buildProjects(List, Map, BuildTrigger, Map, int)
     */
    public void buildProjects( List<Project> projects, Map<Integer, BuildDefinition> projectsBuildDefinitionsMap,
                               BuildTrigger buildTrigger, Map<Integer, ScmResult> scmResultMap, int projectGroupId )
        throws BuildManagerException
    {
        Project firstBuildableProject = null;
>>>>>>> refs/remotes/apache/trunk
        // get id of the first project in the list that is not yet in the build queue
        for ( Project project : projects )
        {
            try
            {
<<<<<<< HEAD
                if ( !isInQueue( project.getId(), BUILD_QUEUE, -1 ) )
                {
                    firstProjectId = project.getId();
=======
                if ( !isInQueue( project.getId(), BUILD_QUEUE, -1 ) && !isProjectInAnyCurrentBuild( project.getId() ) )
                {
                    firstBuildableProject = project;
>>>>>>> refs/remotes/apache/trunk
                    break;
                }
            }
            catch ( TaskQueueException e )
            {
                log.warn( "Error occurred while verifying if project is already queued." );
<<<<<<< HEAD
                continue;
            }
        }

        if ( firstProjectId != 0 )
        {
            BuildDefinition buildDef = projectsBuildDefinitionsMap.get( firstProjectId ); 
            OverallBuildQueue overallBuildQueue =
                getOverallBuildQueue( firstProjectId, BUILD_QUEUE, buildDef.getSchedule().getBuildQueues() );

            if ( overallBuildQueue != null )
            {                
=======
            }
        }

        boolean projectsToBuild = firstBuildableProject != null;

        if ( projectsToBuild )
        {
            BuildDefinition buildDef = projectsBuildDefinitionsMap.get( firstBuildableProject.getId() );

            if ( buildDef.getSchedule() == null )
            {
                String msg = String.format( "Invalid data, null schedule for builddef id=%s/project id=%s",
                                            buildDef.getId(), firstBuildableProject.getId() );
                log.error( msg );
                throw new BuildManagerException( msg + ", please notify your system adminitrator" );
            }
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectsInGroupAreQueued( projectGroupId );

            if ( overallBuildQueue == null )
            {
                overallBuildQueue = getOverallBuildQueue( BUILD_QUEUE, buildDef.getSchedule().getBuildQueues() );
            }

            if ( overallBuildQueue != null )
            {
>>>>>>> refs/remotes/apache/trunk
                for ( Project project : projects )
                {
                    try
                    {
<<<<<<< HEAD
                        if ( isInQueue( project.getId(), BUILD_QUEUE, projectsBuildDefinitionsMap.get( project.getId() ).getId() ) )
                        {
                            log.warn( "Project '" + project.getId() + "' - '" + project.getName() +
                                "' is already in build queue." );
=======
                        if ( isInQueue( project.getId(), BUILD_QUEUE, projectsBuildDefinitionsMap.get(
                            project.getId() ).getId() ) )
                        {
                            log.warn( "Project '" + project.getId() + "' - '" + project.getName() +
                                          "' is already in build queue." );
                            continue;
                        }
                        else if ( isProjectInAnyCurrentBuild( project.getId() ) )
                        {
                            log.warn( "Project '" + project.getId() + "' - '" + project.getName() +
                                          "' is already building." );
>>>>>>> refs/remotes/apache/trunk
                            continue;
                        }
                    }
                    catch ( TaskQueueException e )
                    {
                        log.warn( "Error occurred while verifying if project is already queued." );
                        continue;
                    }

                    BuildDefinition buildDefinition = projectsBuildDefinitionsMap.get( project.getId() );
                    String buildDefinitionLabel = buildDefinition.getDescription();
                    if ( StringUtils.isEmpty( buildDefinitionLabel ) )
                    {
                        buildDefinitionLabel = buildDefinition.getGoals();
                    }

<<<<<<< HEAD
                    BuildProjectTask buildTask =
                        new BuildProjectTask( project.getId(), buildDefinition.getId(), trigger, project.getName(),
                                              buildDefinitionLabel );
                    buildTask.setMaxExecutionTime( buildDefinition.getSchedule().getMaxJobExecutionTime() * 1000 );
=======
                    ScmResult scmResult = scmResultMap.get( project.getId() );
                    BuildProjectTask buildTask = new BuildProjectTask( project.getId(), buildDefinition.getId(),
                                                                       buildTrigger, project.getName(),
                                                                       buildDefinitionLabel, scmResult,
                                                                       projectGroupId );

                    if ( buildDefinition.getSchedule() == null )
                    {
                        log.warn( String.format( "Invalid data, null schedule for builddef id=%s/project id=%s",
                                                 buildDef.getId(), project.getId() ) );
                    }
                    else
                    {
                        buildTask.setMaxExecutionTime( buildDefinition.getSchedule().getMaxJobExecutionTime() * 1000 );
                    }
>>>>>>> refs/remotes/apache/trunk

                    try
                    {
                        log.info( "Project '" + project.getId() + "' - '" + project.getName() +
<<<<<<< HEAD
                            "' added to overall build queue '" + overallBuildQueue.getName() + "'." );
                        
=======
                                      "' added to overall build queue '" + overallBuildQueue.getName() + "'." );

>>>>>>> refs/remotes/apache/trunk
                        overallBuildQueue.addToBuildQueue( buildTask );
                    }
                    catch ( TaskQueueException e )
                    {
<<<<<<< HEAD
                        throw new BuildManagerException( "Error occurred while adding project to build queue: " +
                            e.getMessage() );
                    }
                }
            }
=======
                        throw new BuildManagerException(
                            "Error occurred while adding project to build queue: " + e.getMessage() );
                    }
                }
            }
            else
            {
                log.warn( "No build queue configured. Not building" );
            }
>>>>>>> refs/remotes/apache/trunk
        }
        else
        {
            log.error( "Projects are already in build queue." );
<<<<<<< HEAD
            throw new BuildManagerException( "Projects are already in build queue." );
=======
>>>>>>> refs/remotes/apache/trunk
        }
    }

    /**
     * @see BuildsManager#cancelBuildInQueue(int)
     */
    public boolean cancelBuildInQueue( int buildQueueId )
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
<<<<<<< HEAD
            OverallBuildQueue overallBuildQueue = null;            
=======
            OverallBuildQueue overallBuildQueue;
>>>>>>> refs/remotes/apache/trunk
            overallBuildQueue = overallBuildQueues.get( buildQueueId );
            if ( overallBuildQueue != null )
            {
                overallBuildQueue.cancelCurrentBuild();
            }
            else
            {
                log.warn( "Project not found in any of the build queues." );
            }

            return true;
        }
    }

    /**
     * @see BuildsManager#cancelAllBuilds()
     */
    public boolean cancelAllBuilds()
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
            Set<Integer> keySet = overallBuildQueues.keySet();
            OverallBuildQueue overallBuildQueue = null;
            for ( Integer key : keySet )
            {
                overallBuildQueue = overallBuildQueues.get( key );
                overallBuildQueue.cancelCurrentBuild();
            }

            return true;
        }
    }

    /**
     * @see BuildsManager#cancelAllCheckouts()
     */
    public boolean cancelAllCheckouts()
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
            Set<Integer> keySet = overallBuildQueues.keySet();
<<<<<<< HEAD
            OverallBuildQueue overallBuildQueue = null;
=======
            OverallBuildQueue overallBuildQueue;
>>>>>>> refs/remotes/apache/trunk
            for ( Integer key : keySet )
            {
                overallBuildQueue = overallBuildQueues.get( key );
                overallBuildQueue.cancelCurrentCheckout();
            }

            return true;
        }
    }

    /**
     * @see BuildsManager#cancelBuild(int)
     */
    public boolean cancelBuild( int projectId )
        throws BuildManagerException
    {
        try
        {
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectIsQueued( projectId, BUILD_QUEUE );
            if ( overallBuildQueue != null )
            {
                overallBuildQueue.cancelBuildTask( projectId );
            }
            else
            {
<<<<<<< HEAD
                synchronized( overallBuildQueues )
                {
                    Set<Integer> keySet = overallBuildQueues.keySet();
                    for( Integer key : keySet )
                    {
                        overallBuildQueue = overallBuildQueues.get( key );
                        BuildProjectTask buildTask = (BuildProjectTask) overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask();
                        if( buildTask != null && buildTask.getProjectId() == projectId )
=======
                synchronized ( overallBuildQueues )
                {
                    Set<Integer> keySet = overallBuildQueues.keySet();
                    for ( Integer key : keySet )
                    {
                        overallBuildQueue = overallBuildQueues.get( key );
                        BuildProjectTask buildTask =
                            (BuildProjectTask) overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask();
                        if ( buildTask != null && buildTask.getProjectId() == projectId )
>>>>>>> refs/remotes/apache/trunk
                        {
                            overallBuildQueue.cancelBuildTask( projectId );
                            return true;
                        }
                    }
                    log.error( "Project '" + projectId + "' not found in any of the builds queues." );
                }
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error occurred while cancelling build: " + e.getMessage() );
        }
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        return true;
    }

    /**
     * @see BuildsManager#cancelCheckout(int)
     */
    public boolean cancelCheckout( int projectId )
        throws BuildManagerException
    {
        try
        {
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectIsQueued( projectId, CHECKOUT_QUEUE );
            if ( overallBuildQueue != null )
            {
                overallBuildQueue.cancelCheckoutTask( projectId );
            }
            else
            {
<<<<<<< HEAD
                synchronized( overallBuildQueues )
                {
                    Set<Integer> keySet = overallBuildQueues.keySet();
                    for( Integer key : keySet )
                    {
                        overallBuildQueue = overallBuildQueues.get( key );
                        CheckOutTask checkoutTask = (CheckOutTask) overallBuildQueue.getCheckoutTaskQueueExecutor().getCurrentTask();                        
                        if( checkoutTask != null && checkoutTask.getProjectId() == projectId )
=======
                synchronized ( overallBuildQueues )
                {
                    Set<Integer> keySet = overallBuildQueues.keySet();
                    for ( Integer key : keySet )
                    {
                        overallBuildQueue = overallBuildQueues.get( key );
                        CheckOutTask checkoutTask =
                            (CheckOutTask) overallBuildQueue.getCheckoutTaskQueueExecutor().getCurrentTask();
                        if ( checkoutTask != null && checkoutTask.getProjectId() == projectId )
>>>>>>> refs/remotes/apache/trunk
                        {
                            overallBuildQueue.cancelCheckoutTask( projectId );
                            return true;
                        }
                    }
                    log.info( "Project '" + projectId + "' not found in any of the checkout queues." );
                }
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error occurred while cancelling build: " + e.getMessage() );
        }

        return true;
    }

<<<<<<< HEAD
    /**
     * @see BuildsManager#checkoutProject(int, String, File, String, String, BuildDefinition)
     */
    public void checkoutProject( int projectId, String projectName, File workingDirectory, String scmUsername,
                                 String scmPassword, BuildDefinition defaultBuildDefinition )
=======
    public boolean cancelPrepareBuild( int projectGroupId, int scmRootId )
        throws BuildManagerException
    {
        try
        {
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectGroupIsQueued( projectGroupId,
                                                                                                 scmRootId );

            if ( overallBuildQueue != null )
            {
                overallBuildQueue.cancelPrepareBuildTask( projectGroupId, scmRootId );
            }
            else
            {
                synchronized ( overallBuildQueues )
                {
                    Set<Integer> keySet = overallBuildQueues.keySet();
                    for ( Integer key : keySet )
                    {
                        overallBuildQueue = overallBuildQueues.get( key );
                        PrepareBuildProjectsTask task =
                            (PrepareBuildProjectsTask) overallBuildQueue.getPrepareBuildTaskQueueExecutor().getCurrentTask();
                        if ( task != null && task.getProjectGroupId() == projectGroupId &&
                            task.getProjectScmRootId() == scmRootId )
                        {
                            overallBuildQueue.cancelPrepareBuildTask( projectGroupId, scmRootId );
                            return true;
                        }
                    }
                    log.error( "Project group '{}' with scm root '{}' not found in any of the builds queues.",
                               projectGroupId, scmRootId );
                }
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error occurred while cancelling build: " + e.getMessage() );
        }

        return true;
    }

    public boolean cancelPrepareBuild( int projectId )
        throws BuildManagerException
    {
        try
        {
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectIsQueued( projectId,
                                                                                            PREPARE_BUILD_QUEUE );

            if ( overallBuildQueue != null )
            {
                overallBuildQueue.cancelPrepareBuildTask( projectId );
            }
            else
            {
                synchronized ( overallBuildQueues )
                {
                    Set<Integer> keySet = overallBuildQueues.keySet();
                    for ( Integer key : keySet )
                    {
                        overallBuildQueue = overallBuildQueues.get( key );
                        PrepareBuildProjectsTask task =
                            (PrepareBuildProjectsTask) overallBuildQueue.getPrepareBuildTaskQueueExecutor().getCurrentTask();
                        if ( task != null )
                        {
                            Map<Integer, Integer> map = task.getProjectsBuildDefinitionsMap();

                            if ( map.size() > 0 )
                            {
                                Set<Integer> projectIds = map.keySet();

                                if ( projectIds.contains( new Integer( projectId ) ) )
                                {
                                    overallBuildQueue.cancelPrepareBuildTask( projectId );
                                    return true;
                                }
                            }
                        }
                    }
                    log.error( "Project '{}' not found in any of the builds queues.", projectId );
                }
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error occurred while cancelling build: " + e.getMessage() );
        }

        return true;
    }

    /**
     * @see BuildsManager#checkoutProject(int, String, File, String, String, String, BuildDefinition, List)
     */
    public void checkoutProject( int projectId, String projectName, File workingDirectory, String scmRootUrl,
                                 String scmUsername, String scmPassword, BuildDefinition defaultBuildDefinition,
                                 List<Project> subProjects )
>>>>>>> refs/remotes/apache/trunk
        throws BuildManagerException
    {
        try
        {
            if ( isInQueue( projectId, CHECKOUT_QUEUE, -1 ) )
            {
                log.warn( "Project already in checkout queue." );
                return;
            }
        }
        catch ( TaskQueueException e )
        {
<<<<<<< HEAD
            throw new BuildManagerException( "Error occurred while checking if the project is already in queue: " +
                e.getMessage() );
        }

        OverallBuildQueue overallBuildQueue =
            getOverallBuildQueue( projectId, CHECKOUT_QUEUE, defaultBuildDefinition.getSchedule().getBuildQueues() );
        CheckOutTask checkoutTask =
            new CheckOutTask( projectId, workingDirectory, projectName, scmUsername, scmPassword );
        try
        {
            if( overallBuildQueue != null )
            {
                log.info( "Project '" + projectName + "' added to overall build queue '" + overallBuildQueue.getName() + "'." );
=======
            throw new BuildManagerException(
                "Error occurred while checking if the project is already in queue: " + e.getMessage() );
        }

        OverallBuildQueue overallBuildQueue = getOverallBuildQueue( CHECKOUT_QUEUE,
                                                                    defaultBuildDefinition.getSchedule().getBuildQueues() );
        CheckOutTask checkoutTask = new CheckOutTask( projectId, workingDirectory, projectName, scmUsername,
                                                      scmPassword, scmRootUrl, subProjects );
        try
        {
            if ( overallBuildQueue != null )
            {
                log.info( "Project '" + projectName + "' added to overall build queue '" + overallBuildQueue.getName() +
                              "'." );
>>>>>>> refs/remotes/apache/trunk
                overallBuildQueue.addToCheckoutQueue( checkoutTask );
            }
            else
            {
<<<<<<< HEAD
                throw new BuildManagerException( "Unable to add project to checkout queue. No overall build queue configured." );
=======
                throw new BuildManagerException(
                    "Unable to add project to checkout queue. No overall build queue configured." );
>>>>>>> refs/remotes/apache/trunk
            }
        }
        catch ( TaskQueueException e )
        {
<<<<<<< HEAD
            throw new BuildManagerException( "Error occurred while adding project to checkout queue: " + e.getMessage() );
=======
            throw new BuildManagerException(
                "Error occurred while adding project to checkout queue: " + e.getMessage() );
>>>>>>> refs/remotes/apache/trunk
        }
    }

    /**
     * @see BuildsManager#isInAnyBuildQueue(int)
     */
    public boolean isInAnyBuildQueue( int projectId )
        throws BuildManagerException
    {
        try
        {
            return isInQueue( projectId, BUILD_QUEUE, -1 );
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( e.getMessage() );
        }
    }

    /**
     * @see BuildsManager#isInAnyBuildQueue(int, int)
     */
    public boolean isInAnyBuildQueue( int projectId, int buildDefinitionId )
        throws BuildManagerException
    {
        try
        {
            return isInQueue( projectId, BUILD_QUEUE, buildDefinitionId );
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( e.getMessage() );
        }
    }

    /**
     * @see BuildsManager#isInAnyCheckoutQueue(int)
     */
    public boolean isInAnyCheckoutQueue( int projectId )
        throws BuildManagerException
    {
        try
        {
            return isInQueue( projectId, CHECKOUT_QUEUE, -1 );
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( e.getMessage() );
        }
    }

    /**
     * @see BuildsManager#isAnyProjectCurrentlyBeingCheckedOut(int[])
     */
    public boolean isAnyProjectCurrentlyBeingCheckedOut( int[] projectIds )
        throws BuildManagerException
<<<<<<< HEAD
    {        
        for( int i = 0; i < projectIds.length; i++ )
        {
            Map<String, Task> checkouts = getCurrentCheckouts();
            Set<String> keySet = checkouts.keySet();
            for( String key : keySet )
            {
                CheckOutTask task = (CheckOutTask) checkouts.get( key );
                if( task.getProjectId() == projectIds[i] )
                {
=======
    {
        for ( int projectId : projectIds )
        {
            Map<String, CheckOutTask> checkouts = getCurrentCheckouts();
            Set<String> keySet = checkouts.keySet();
            for ( String key : keySet )
            {
                CheckOutTask task = checkouts.get( key );
                if ( task.getProjectId() == projectId )
                {
                    log.info( "Project " + projectId + " is currently being checked out" );
>>>>>>> refs/remotes/apache/trunk
                    return true;
                }
            }
        }
        return false;
    }
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/apache/trunk
    /**
     * @see BuildsManager#isInPrepareBuildQueue(int)
     */
    public boolean isInPrepareBuildQueue( int projectId )
        throws BuildManagerException
    {
        try
        {
<<<<<<< HEAD
            List<PrepareBuildProjectsTask> queue = prepareBuildQueue.getQueueSnapshot();
            for ( PrepareBuildProjectsTask task : queue )
            {
                if ( task != null )
                {
                    Map<Integer, Integer> map = ( (PrepareBuildProjectsTask) task ).getProjectsBuildDefinitionsMap();

                    if ( map.size() > 0 )
                    {
                        Set<Integer> projectIds = map.keySet();

                        if ( projectIds.contains( new Integer( projectId ) ) )
                        {
                            return true;
                        }
                    }
                }
=======
            synchronized ( overallBuildQueues )
            {
                Set<Integer> keySet = overallBuildQueues.keySet();
                for ( Integer key : keySet )
                {
                    OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );

                    if ( overallBuildQueue.isInPrepareBuildQueue( projectId ) )
                    {
                        return true;
                    }
                }

                return false;
>>>>>>> refs/remotes/apache/trunk
            }
        }
        catch ( TaskQueueException e )
        {
<<<<<<< HEAD
            throw new BuildManagerException( e.getMessage() );
        }

        return false;
=======
            throw new BuildManagerException( "Unable to check if projectId " + projectId + " is in prepare build queue",
                                             e );
        }
    }

    /**
     * @param projectGroupId
     * @param scmRootId
     * @return
     * @see BuildsManager#isInPrepareBuildQueue(int, int)
     */
    public boolean isInPrepareBuildQueue( int projectGroupId, int scmRootId )
        throws BuildManagerException
    {
        try
        {
            synchronized ( overallBuildQueues )
            {
                Set<Integer> keySet = overallBuildQueues.keySet();
                for ( Integer key : keySet )
                {
                    OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );

                    if ( overallBuildQueue.isInPrepareBuildQueue( projectGroupId, scmRootId ) )
                    {
                        return true;
                    }
                }

                return false;
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException(
                "Unable to check if projectGroupId " + projectGroupId + " with scmRootId " + scmRootId +
                    " is in prepare build queue", e );
        }
>>>>>>> refs/remotes/apache/trunk
    }

    /**
     * @see BuildsManager#isProjectInAnyCurrentBuild(int)
     */
    public boolean isProjectInAnyCurrentBuild( int projectId )
        throws BuildManagerException
    {
<<<<<<< HEAD
        synchronized( overallBuildQueues )
        {
            Set<Integer> keys = overallBuildQueues.keySet();
            for( Integer key : keys )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                BuildProjectTask task = (BuildProjectTask) overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask();
                if( task != null && task.getProjectId() == projectId )
                {
=======
        synchronized ( overallBuildQueues )
        {
            Set<Integer> keys = overallBuildQueues.keySet();
            for ( Integer key : keys )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                BuildProjectTask task =
                    (BuildProjectTask) overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask();
                if ( task != null && task.getProjectId() == projectId )
                {
                    log.info( "Project " + projectId + " is currently building in " + overallBuildQueue.getName() );
>>>>>>> refs/remotes/apache/trunk
                    return true;
                }
            }
            return false;
        }
    }

    /**
<<<<<<< HEAD
     * @see BuildsManager#prepareBuildProjects(Map, int, int, String)
     */
    public void prepareBuildProjects( Map<Integer, Integer> projectsBuildDefinitionsMap, int trigger, int projectGroupId, String scmRootAddress )
        throws BuildManagerException
    {
        try
        {            
            PrepareBuildProjectsTask task =
                new PrepareBuildProjectsTask( projectsBuildDefinitionsMap, trigger, projectGroupId, scmRootAddress );
            
            log.info( "Queueing prepare-build-project task '" + task + "' to prepare-build queue." );
            prepareBuildQueue.put( task );
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error occurred while creating prepare-build-project task: " +
                e.getMessage() );
=======
     * @see BuildsManager#prepareBuildProjects(Map, BuildTrigger, int, String, String, int)
     */
    public void prepareBuildProjects( Map<Integer, Integer> projectsBuildDefinitionsMap, BuildTrigger buildTrigger,
                                      int projectGroupId, String projectGroupName, String scmRootAddress,
                                      int scmRootId )
        throws BuildManagerException
    {
        if ( isInPrepareBuildQueue( projectGroupId, scmRootId ) )
        {
            log.warn(
                "Project group {} with scm root id {} is already in prepare build queue. Will not queue anymore." );
            return;
        }

        Collection<Integer> buildDefs = projectsBuildDefinitionsMap.values();
        BuildDefinition buildDef = null;

        // get the first build definition
        try
        {
            for ( Integer buildDefId : buildDefs )
            {
                buildDef = buildDefinitionDao.getBuildDefinition( buildDefId );
            }
        }
        catch ( ContinuumStoreException e )
        {
            throw new BuildManagerException(
                "Error occurred while retrieving build definition of project group " + projectGroupId, e );
        }

        OverallBuildQueue overallBuildQueue = getOverallBuildQueue( PREPARE_BUILD_QUEUE,
                                                                    buildDef.getSchedule().getBuildQueues() );

        PrepareBuildProjectsTask task = new PrepareBuildProjectsTask( projectsBuildDefinitionsMap, buildTrigger,
                                                                      projectGroupId, projectGroupName, scmRootAddress,
                                                                      scmRootId );

        try
        {
            if ( overallBuildQueue != null )
            {
                log.info( "Project group '{}' added to overall build queue '{}'", projectGroupId,
                          overallBuildQueue.getName() );
                overallBuildQueue.addToPrepareBuildQueue( task );
            }
            else
            {
                throw new BuildManagerException(
                    "Unable to add project to prepare build queue. No overall build queue configured." );
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException(
                "Error occurred while adding project to prepare build queue: " + e.getMessage() );
>>>>>>> refs/remotes/apache/trunk
        }
    }

    /**
     * @see BuildsManager#removeProjectFromBuildQueue(int)
     */
    public void removeProjectFromBuildQueue( int projectId )
        throws BuildManagerException
    {
        try
        {
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectIsQueued( projectId, BUILD_QUEUE );
            if ( overallBuildQueue != null )
            {
                overallBuildQueue.removeProjectFromBuildQueue( projectId );
            }
            else
            {
                log.info( "Project '" + projectId + "' not found in any of the build queues." );
            }
        }
        catch ( TaskQueueException e )
        {
<<<<<<< HEAD
            throw new BuildManagerException( "Error occurred while removing project from build queue: " +
                e.getMessage() );
=======
            throw new BuildManagerException(
                "Error occurred while removing project from build queue: " + e.getMessage() );
>>>>>>> refs/remotes/apache/trunk
        }
    }

    /**
<<<<<<< HEAD
     * @see BuildsManager#removeProjectFromBuildQueue(int, int, int, String)
     */
    public void removeProjectFromBuildQueue( int projectId, int buildDefinitionId, int trigger, String projectName )
=======
     * @see BuildsManager#removeProjectFromBuildQueue(int, int, BuildTrigger, String, int)
     */
    public void removeProjectFromBuildQueue( int projectId, int buildDefinitionId, BuildTrigger buildTrigger,
                                             String projectName, int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws BuildManagerException
    {
        try
        {
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectIsQueued( projectId, BUILD_QUEUE );
            if ( overallBuildQueue != null )
            {
<<<<<<< HEAD
                overallBuildQueue.removeProjectFromBuildQueue( projectId, buildDefinitionId, trigger, projectName );
=======
                overallBuildQueue.removeProjectFromBuildQueue( projectId, buildDefinitionId, buildTrigger, projectName,
                                                               projectGroupId );
>>>>>>> refs/remotes/apache/trunk
            }
            else
            {
                log.info( "Project '" + projectId + "' not found in any of the build queues." );
            }
        }
        catch ( TaskQueueException e )
        {
<<<<<<< HEAD
            throw new BuildManagerException( "Error occurred while removing project from build queue: " +
                e.getMessage() );
=======
            throw new BuildManagerException(
                "Error occurred while removing project from build queue: " + e.getMessage() );
>>>>>>> refs/remotes/apache/trunk
        }
    }

    /**
     * @see BuildsManager#removeProjectFromCheckoutQueue(int)
     */
    public void removeProjectFromCheckoutQueue( int projectId )
        throws BuildManagerException
    {
        try
        {
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectIsQueued( projectId, CHECKOUT_QUEUE );
            if ( overallBuildQueue != null )
            {
                overallBuildQueue.removeProjectFromCheckoutQueue( projectId );
            }
            else
            {
                log.info( "Project '" + projectId + "' not found in any of the checkout queues." );
            }
        }
        catch ( TaskQueueException e )
        {
<<<<<<< HEAD
            throw new BuildManagerException( "Error occurred while removing project from checkout queue: " +
                e.getMessage() );
=======
            throw new BuildManagerException(
                "Error occurred while removing project from checkout queue: " + e.getMessage() );
>>>>>>> refs/remotes/apache/trunk
        }
    }

    /**
     * @see BuildsManager#removeProjectsFromBuildQueue(int[])
     */
    public void removeProjectsFromBuildQueue( int[] projectIds )
    {
<<<<<<< HEAD
        for ( int i = 0; i < projectIds.length; i++ )
        {
            try
            {
                OverallBuildQueue overallBuildQueue =
                    getOverallBuildQueueWhereProjectIsQueued( projectIds[i], BUILD_QUEUE );
                if ( overallBuildQueue != null )
                {
                    overallBuildQueue.removeProjectFromBuildQueue( projectIds[i] );
                }
                else
                {
                    log.error( "Project '" + projectIds[i] + "' not found in any of the build queues." );
                    continue;
=======
        for ( int projectId : projectIds )
        {
            try
            {
                OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectIsQueued( projectId,
                                                                                                BUILD_QUEUE );
                if ( overallBuildQueue != null )
                {
                    overallBuildQueue.removeProjectFromBuildQueue( projectId );
                }
                else
                {
                    log.error( "Project '" + projectId + "' not found in any of the build queues." );
>>>>>>> refs/remotes/apache/trunk
                }
            }
            catch ( TaskQueueException e )
            {
<<<<<<< HEAD
                log.error( "Error occurred while removing project '" + projectIds[i] + "' from build queue." );
                continue;
=======
                log.error( "Error occurred while removing project '" + projectId + "' from build queue." );
>>>>>>> refs/remotes/apache/trunk
            }
        }
    }

    /**
     * @see BuildsManager#removeProjectsFromCheckoutQueue(int[])
     */
    public void removeProjectsFromCheckoutQueue( int[] projectIds )
    {
<<<<<<< HEAD
        for ( int i = 0; i < projectIds.length; i++ )
        {
            try
            {
                OverallBuildQueue overallBuildQueue =
                    getOverallBuildQueueWhereProjectIsQueued( projectIds[i], CHECKOUT_QUEUE );
                if ( overallBuildQueue != null )
                {
                    overallBuildQueue.removeProjectFromCheckoutQueue( projectIds[i] );
                }
                else
                {
                    log.error( "Project '" + projectIds[i] + "' not found in any of the checkout queues." );
                    continue;
=======
        for ( int projectId : projectIds )
        {
            try
            {
                OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectIsQueued( projectId,
                                                                                                CHECKOUT_QUEUE );
                if ( overallBuildQueue != null )
                {
                    overallBuildQueue.removeProjectFromCheckoutQueue( projectId );
                }
                else
                {
                    log.error( "Project '" + projectId + "' not found in any of the checkout queues." );
>>>>>>> refs/remotes/apache/trunk
                }
            }
            catch ( TaskQueueException e )
            {
<<<<<<< HEAD
                log.error( "Error occurred while removing project '" + projectIds[i] + "' from checkout queue." );
                continue;
=======
                log.error( "Error occurred while removing project '" + projectId + "' from checkout queue." );
>>>>>>> refs/remotes/apache/trunk
            }
        }
    }

    /**
     * @see BuildsManager#removeProjectsFromCheckoutQueueWithHashcodes(int[])
     */
    public void removeProjectsFromCheckoutQueueWithHashcodes( int[] hashcodes )
        throws BuildManagerException
    {
        try
        {
            synchronized ( overallBuildQueues )
            {
                Set<Integer> keySet = overallBuildQueues.keySet();
                for ( Integer key : keySet )
                {
                    OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
<<<<<<< HEAD
                    overallBuildQueue.removeTasksFromCheckoutQueueWithHashCodes( hashcodes );                   
=======
                    overallBuildQueue.removeTasksFromCheckoutQueueWithHashCodes( hashcodes );
>>>>>>> refs/remotes/apache/trunk
                }
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error encountered while removing project(s) from checkout queue.", e );
        }
    }

    /**
     * @see BuildsManager#removeProjectsFromBuildQueueWithHashcodes(int[])
     */
    public void removeProjectsFromBuildQueueWithHashcodes( int[] hashcodes )
        throws BuildManagerException
    {
        try
        {
            synchronized ( overallBuildQueues )
            {
                Set<Integer> keySet = overallBuildQueues.keySet();
                for ( Integer key : keySet )
                {
                    OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                    overallBuildQueue.removeProjectsFromBuildQueueWithHashCodes( hashcodes );
                }
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error encountered while removing project(s) from build queue.", e );
        }
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public boolean removeProjectGroupFromPrepareBuildQueue( int projectGroupId, String scmRootAddress )
        throws BuildManagerException
    {
        try
        {
<<<<<<< HEAD
            List<PrepareBuildProjectsTask> queue = prepareBuildQueue.getQueueSnapshot();

            for ( PrepareBuildProjectsTask task : queue )
            {
                if ( task != null && task.getProjectGroupId() == projectGroupId &&
                    task.getScmRootAddress().equals( scmRootAddress ) )
                {
                    return prepareBuildQueue.remove( task );
                }
            }
            return false;
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error while getting the prepare build projects task in queue", e );
        }
    }
    
=======
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectGroupIsQueued( projectGroupId,
                                                                                                 scmRootAddress );
            if ( overallBuildQueue != null )
            {
                overallBuildQueue.removeProjectFromPrepareBuildQueue( projectGroupId, scmRootAddress );
            }
            else
            {
                log.info( "Project group '{}' with scm '{}' not found in any of the build queues.", projectGroupId,
                          scmRootAddress );
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException(
                "Error occurred while removing project group from prepare build queue: " + e.getMessage() );
        }

        return true;
    }

>>>>>>> refs/remotes/apache/trunk
    /**
     * @see BuildsManager#addOverallBuildQueue(BuildQueue)
     */
    public void addOverallBuildQueue( BuildQueue buildQueue )
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
            try
            {
                OverallBuildQueue overallBuildQueue = (OverallBuildQueue) container.lookup( OverallBuildQueue.class );
                overallBuildQueue.setId( buildQueue.getId() );
                overallBuildQueue.setName( buildQueue.getName() );
<<<<<<< HEAD
                
=======

>>>>>>> refs/remotes/apache/trunk
                if ( overallBuildQueues.get( buildQueue.getId() ) == null )
                {
                    log.info( "Adding overall build queue to map : " + overallBuildQueue.getName() );
                    overallBuildQueues.put( overallBuildQueue.getId(), overallBuildQueue );
                }
                else
                {
                    log.warn( "Overall build queue already in the map." );
                }
            }
            catch ( ComponentLookupException e )
<<<<<<< HEAD
            {                
=======
            {
>>>>>>> refs/remotes/apache/trunk
                throw new BuildManagerException( "Error creating overall build queue.", e );
            }
        }
    }

    /**
     * @see BuildsManager#removeOverallBuildQueue(int)
     */
    public void removeOverallBuildQueue( int overallBuildQueueId )
        throws BuildManagerException
    {
<<<<<<< HEAD
        List<Task> tasks = null;
        List<CheckOutTask> checkoutTasks = null;

        synchronized ( overallBuildQueues )
        {                        
=======
        List<BuildProjectTask> tasks;
        List<CheckOutTask> checkoutTasks;
        List<PrepareBuildProjectsTask> prepareBuildTasks;

        synchronized ( overallBuildQueues )
        {
>>>>>>> refs/remotes/apache/trunk
            OverallBuildQueue overallBuildQueue = overallBuildQueues.get( overallBuildQueueId );
            if ( overallBuildQueue.getName().equals( ConfigurationService.DEFAULT_BUILD_QUEUE_NAME ) )
            {
                throw new BuildManagerException( "Cannot remove default build queue." );
            }

            try
            {
<<<<<<< HEAD
                if( overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask() != null ||
                                overallBuildQueue.getCheckoutTaskQueueExecutor().getCurrentTask() != null )
=======
                if ( overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask() != null ||
                    overallBuildQueue.getCheckoutTaskQueueExecutor().getCurrentTask() != null ||
                    overallBuildQueue.getPrepareBuildTaskQueueExecutor().getCurrentTask() != null )
>>>>>>> refs/remotes/apache/trunk
                {
                    throw new BuildManagerException( "Cannot remove build queue. A task is currently executing." );
                }

                tasks = overallBuildQueue.getProjectsInBuildQueue();
                checkoutTasks = overallBuildQueue.getProjectsInCheckoutQueue();
<<<<<<< HEAD

                overallBuildQueue.getBuildQueue().removeAll( tasks );
                overallBuildQueue.getCheckoutQueue().removeAll( checkoutTasks );

                ( (ParallelBuildsThreadedTaskQueueExecutor) overallBuildQueue.getBuildTaskQueueExecutor() ).stop();
                ( (ParallelBuildsThreadedTaskQueueExecutor) overallBuildQueue.getCheckoutTaskQueueExecutor() ).stop();
=======
                prepareBuildTasks = overallBuildQueue.getProjectsInPrepareBuildQueue();

                overallBuildQueue.getBuildQueue().removeAll( tasks );
                overallBuildQueue.getCheckoutQueue().removeAll( checkoutTasks );
                overallBuildQueue.getPrepareBuildQueue().removeAll( prepareBuildTasks );

                ( (ParallelBuildsThreadedTaskQueueExecutor) overallBuildQueue.getBuildTaskQueueExecutor() ).stop();
                ( (ParallelBuildsThreadedTaskQueueExecutor) overallBuildQueue.getCheckoutTaskQueueExecutor() ).stop();
                ( (ParallelBuildsThreadedTaskQueueExecutor) overallBuildQueue.getPrepareBuildTaskQueueExecutor() ).stop();
>>>>>>> refs/remotes/apache/trunk
                container.release( overallBuildQueue );
            }
            catch ( TaskQueueException e )
            {
                throw new BuildManagerException(
<<<<<<< HEAD
                                                 "Cannot remove build queue. An error occurred while retrieving queued tasks." );
=======
                    "Cannot remove build queue. An error occurred while retrieving queued tasks." );
>>>>>>> refs/remotes/apache/trunk
            }
            catch ( ComponentLifecycleException e )
            {
                throw new BuildManagerException(
<<<<<<< HEAD
                                                 "Cannot remove build queue. An error occurred while destroying the build queue: " +
                                                     e.getMessage() );
=======
                    "Cannot remove build queue. An error occurred while destroying the build queue: " +
                        e.getMessage() );
>>>>>>> refs/remotes/apache/trunk
            }
            catch ( StoppingException e )
            {
                throw new BuildManagerException(
<<<<<<< HEAD
                                                "Cannot remove build queue. An error occurred while stopping the build queue: " +
                                                    e.getMessage() );
=======
                    "Cannot remove build queue. An error occurred while stopping the build queue: " + e.getMessage() );
>>>>>>> refs/remotes/apache/trunk
            }

            this.overallBuildQueues.remove( overallBuildQueueId );
            log.info( "Removed overall build queue '" + overallBuildQueueId + "' from build queues map." );
        }

<<<<<<< HEAD
        
        for ( Task task : tasks )
        {            
            BuildProjectTask buildTask = (BuildProjectTask) task;
            try
            {
                BuildDefinition buildDefinition =
                    buildDefinitionDao.getBuildDefinition( buildTask.getBuildDefinitionId() );
            
                buildProject( buildTask.getProjectId(), buildDefinition, buildTask.getProjectName(),
                              buildTask.getTrigger() );
=======
        for ( BuildProjectTask buildTask : tasks )
        {
            try
            {
                BuildDefinition buildDefinition = buildDefinitionDao.getBuildDefinition(
                    buildTask.getBuildDefinitionId() );

                buildProject( buildTask.getProjectId(), buildDefinition, buildTask.getProjectName(),
                              buildTask.getBuildTrigger(), buildTask.getScmResult(), buildTask.getProjectGroupId() );
>>>>>>> refs/remotes/apache/trunk
            }
            catch ( ContinuumStoreException e )
            {
                log.error( "Unable to queue build task for project '" + buildTask.getProjectName() + "'" );
<<<<<<< HEAD
                continue;
=======
>>>>>>> refs/remotes/apache/trunk
            }
        }

        for ( CheckOutTask task : checkoutTasks )
        {
            try
            {
                BuildDefinition buildDefinition = buildDefinitionDao.getDefaultBuildDefinition( task.getProjectId() );
                checkoutProject( task.getProjectId(), task.getProjectName(), task.getWorkingDirectory(),
<<<<<<< HEAD
                                 task.getScmUserName(), task.getScmPassword(), buildDefinition );            
=======
                                 task.getScmRootUrl(), task.getScmUserName(), task.getScmPassword(), buildDefinition,
                                 task.getProjectsWithCommonScmRoot() );
>>>>>>> refs/remotes/apache/trunk
            }
            catch ( ContinuumStoreException e )
            {
                log.error( "Unable to queue checkout task for project '" + task.getProjectName() + "'" );
<<<<<<< HEAD
                continue;
            }
        }        
=======
            }
        }

        for ( PrepareBuildProjectsTask prepareTask : prepareBuildTasks )
        {
            prepareBuildProjects( prepareTask.getProjectsBuildDefinitionsMap(), prepareTask.getBuildTrigger(),
                                  prepareTask.getProjectGroupId(), prepareTask.getProjectGroupName(),
                                  prepareTask.getScmRootAddress(), prepareTask.getProjectScmRootId() );
        }
>>>>>>> refs/remotes/apache/trunk
    }

    public Map<Integer, OverallBuildQueue> getOverallBuildQueues()
    {
        return overallBuildQueues;
    }

    /**
     * @see BuildsManager#getCurrentBuilds()
     */
<<<<<<< HEAD
    public Map<String, Task> getCurrentBuilds()
        throws BuildManagerException
    {
        synchronized( overallBuildQueues )
        {
            Map<String, Task> currentBuilds = new HashMap<String, Task>();            
            Set<Integer> keys = overallBuildQueues.keySet();
            for( Integer key : keys )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );                
                Task task = overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask();
                if( task != null )
                {
                    currentBuilds.put( overallBuildQueue.getName(), task );
                }                
            }            
=======
    public Map<String, BuildProjectTask> getCurrentBuilds()
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
            Map<String, BuildProjectTask> currentBuilds = new HashMap<String, BuildProjectTask>();
            Set<Integer> keys = overallBuildQueues.keySet();
            for ( Integer key : keys )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                BuildProjectTask task =
                    (BuildProjectTask) overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask();
                if ( task != null )
                {
                    currentBuilds.put( overallBuildQueue.getName(), task );
                }
            }
>>>>>>> refs/remotes/apache/trunk
            return currentBuilds;
        }
    }

    /**
     * @see BuildsManager#getCurrentCheckouts()
     */
<<<<<<< HEAD
    public Map<String, Task> getCurrentCheckouts()
        throws BuildManagerException
    {
        synchronized( overallBuildQueues )
        {
            Map<String, Task> currentCheckouts = new HashMap<String, Task>();
            Set<Integer> keys = overallBuildQueues.keySet();
            for( Integer key : keys )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );                
                Task task = overallBuildQueue.getCheckoutTaskQueueExecutor().getCurrentTask();
                if( task != null )
                {
                    currentCheckouts.put( overallBuildQueue.getName(), task );
                }                
            }            
            return currentCheckouts;           
=======
    public Map<String, CheckOutTask> getCurrentCheckouts()
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
            Map<String, CheckOutTask> currentCheckouts = new HashMap<String, CheckOutTask>();
            Set<Integer> keys = overallBuildQueues.keySet();
            for ( Integer key : keys )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                CheckOutTask task = (CheckOutTask) overallBuildQueue.getCheckoutTaskQueueExecutor().getCurrentTask();
                if ( task != null )
                {
                    currentCheckouts.put( overallBuildQueue.getName(), task );
                }
            }
            return currentCheckouts;
>>>>>>> refs/remotes/apache/trunk
        }
    }

    /**
     * @see BuildsManager#getProjectsInBuildQueues()
     */
<<<<<<< HEAD
    public Map<String, List<Task>> getProjectsInBuildQueues()
=======
    public Map<String, List<BuildProjectTask>> getProjectsInBuildQueues()
>>>>>>> refs/remotes/apache/trunk
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
<<<<<<< HEAD
            Map<String, List<Task>> queuedBuilds = new HashMap<String, List<Task>>();
=======
            Map<String, List<BuildProjectTask>> queuedBuilds = new HashMap<String, List<BuildProjectTask>>();
>>>>>>> refs/remotes/apache/trunk
            Set<Integer> keySet = overallBuildQueues.keySet();
            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                try
                {
                    queuedBuilds.put( overallBuildQueue.getName(), overallBuildQueue.getProjectsInBuildQueue() );
                }
                catch ( TaskQueueException e )
                {
<<<<<<< HEAD
                    throw new BuildManagerException( "Error occurred while getting projects in build queue '" +
                        overallBuildQueue.getName() + "'.", e );
=======
                    throw new BuildManagerException(
                        "Error occurred while getting projects in build queue '" + overallBuildQueue.getName() + "'.",
                        e );
>>>>>>> refs/remotes/apache/trunk
                }
            }
            return queuedBuilds;
        }
    }

    /**
     * @see BuildsManager#getProjectsInCheckoutQueues()
     */
<<<<<<< HEAD
    public Map<String, List<Task>> getProjectsInCheckoutQueues()
=======
    public Map<String, List<CheckOutTask>> getProjectsInCheckoutQueues()
>>>>>>> refs/remotes/apache/trunk
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
<<<<<<< HEAD
            Map<String, List<Task>> queuedCheckouts = new HashMap<String, List<Task>>();
=======
            Map<String, List<CheckOutTask>> queuedCheckouts = new HashMap<String, List<CheckOutTask>>();
>>>>>>> refs/remotes/apache/trunk
            Set<Integer> keySet = overallBuildQueues.keySet();
            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                try
                {
                    queuedCheckouts.put( overallBuildQueue.getName(), overallBuildQueue.getProjectsInCheckoutQueue() );
                }
                catch ( TaskQueueException e )
                {
<<<<<<< HEAD
                    throw new BuildManagerException( "Error occurred while getting projects in build queue '" +
                        overallBuildQueue.getName() + "'.", e );
=======
                    throw new BuildManagerException(
                        "Error occurred while getting projects in build queue '" + overallBuildQueue.getName() + "'.",
                        e );
>>>>>>> refs/remotes/apache/trunk
                }
            }
            return queuedCheckouts;
        }
    }
<<<<<<< HEAD
    
    /**
     * @see BuildsManager#cancelAllPrepareBuilds()
     */
    public boolean cancelAllPrepareBuilds() throws BuildManagerException
    {
        try
        {
            TaskQueueExecutor executor = ( TaskQueueExecutor ) container.lookup( TaskQueueExecutor.class, "prepare-build-project" );
            Task task = executor.getCurrentTask();            
            if( task != null )
            {
                executor.cancelTask( task );
            }
        }
        catch ( ComponentLookupException e )
        {
            throw new BuildManagerException( "Error looking up prepare-build-queue.", e );
        }
        
        return false;
=======

    /**
     * @see BuildsManager#cancelAllPrepareBuilds()
     */
    public boolean cancelAllPrepareBuilds()
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
            Set<Integer> keySet = overallBuildQueues.keySet();
            OverallBuildQueue overallBuildQueue = null;
            for ( Integer key : keySet )
            {
                overallBuildQueue = overallBuildQueues.get( key );
                overallBuildQueue.cancelCurrentPrepareBuild();
            }

            return true;
        }
>>>>>>> refs/remotes/apache/trunk
    }

    /**
     * @see BuildsManager#isBuildInProgress()
     */
    public boolean isBuildInProgress()
<<<<<<< HEAD
    {        
        synchronized( overallBuildQueues )
        {
            Set<Integer> keySet = overallBuildQueues.keySet();
            for( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                if( overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask() != null )
=======
    {
        synchronized ( overallBuildQueues )
        {
            Set<Integer> keySet = overallBuildQueues.keySet();
            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                if ( overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask() != null )
>>>>>>> refs/remotes/apache/trunk
                {
                    return true;
                }
            }
            return false;
        }
    }

<<<<<<< HEAD
=======
    public boolean isProjectCurrentlyPreparingBuild( int projectId )
        throws BuildManagerException
    {
        Map<String, PrepareBuildProjectsTask> tasks = getCurrentProjectInPrepareBuild();

        if ( tasks != null )
        {
            for ( PrepareBuildProjectsTask task : tasks.values() )
            {
                Map<Integer, Integer> map = task.getProjectsBuildDefinitionsMap();

                if ( map.size() > 0 )
                {
                    Set<Integer> projectIds = map.keySet();

                    if ( projectIds.contains( new Integer( projectId ) ) )
                    {
                        log.info( "Project '{}' is currently preparing build", projectId );
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isProjectGroupCurrentlyPreparingBuild( int projectGroupId, int scmRootId )
        throws BuildManagerException
    {
        Map<String, PrepareBuildProjectsTask> tasks = getCurrentProjectInPrepareBuild();

        if ( tasks != null )
        {
            for ( PrepareBuildProjectsTask task : tasks.values() )
            {
                if ( task != null && task.getProjectGroupId() == projectGroupId &&
                    task.getProjectScmRootId() == scmRootId )
                {
                    log.info( "Project group '{}' with scm root '{}' is currently preparing build", projectGroupId,
                              scmRootId );
                    return true;
                }
            }
        }

        return false;
    }

    public Map<String, PrepareBuildProjectsTask> getCurrentProjectInPrepareBuild()
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
            Map<String, PrepareBuildProjectsTask> currentBuilds = new HashMap<String, PrepareBuildProjectsTask>();
            Set<Integer> keys = overallBuildQueues.keySet();
            for ( Integer key : keys )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                PrepareBuildProjectsTask task =
                    (PrepareBuildProjectsTask) overallBuildQueue.getPrepareBuildTaskQueueExecutor().getCurrentTask();
                if ( task != null )
                {
                    currentBuilds.put( overallBuildQueue.getName(), task );
                }
            }
            return currentBuilds;
        }
    }

    public Map<String, List<PrepareBuildProjectsTask>> getProjectsInPrepareBuildQueue()
        throws BuildManagerException
    {
        synchronized ( overallBuildQueues )
        {
            Map<String, List<PrepareBuildProjectsTask>> queuedPrepareBuilds =
                new HashMap<String, List<PrepareBuildProjectsTask>>();
            Set<Integer> keySet = overallBuildQueues.keySet();
            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                try
                {
                    queuedPrepareBuilds.put( overallBuildQueue.getName(),
                                             overallBuildQueue.getProjectsInPrepareBuildQueue() );
                }
                catch ( TaskQueueException e )
                {
                    throw new BuildManagerException(
                        "Error occurred while getting projects in prepare build queue '" + overallBuildQueue.getName() +
                            "'.", e );
                }
            }
            return queuedPrepareBuilds;
        }
    }

    public boolean removeProjectFromPrepareBuildQueue( int projectGroupId, int scmRootId )
        throws BuildManagerException
    {
        try
        {
            OverallBuildQueue overallBuildQueue = getOverallBuildQueueWhereProjectGroupIsQueued( projectGroupId,
                                                                                                 scmRootId );
            if ( overallBuildQueue != null )
            {
                overallBuildQueue.removeProjectFromPrepareBuildQueue( projectGroupId, scmRootId );
            }
            else
            {
                log.info( "Project group '{}' with scm '{}' not found in any of the build queues.", projectGroupId,
                          scmRootId );
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException(
                "Error occurred while removing project group from prepare build queue: " + e.getMessage() );
        }

        return true;
    }

    public void removeProjectsFromPrepareBuildQueueWithHashCodes( int[] hashCodes )
        throws BuildManagerException
    {
        try
        {
            synchronized ( overallBuildQueues )
            {
                Set<Integer> keySet = overallBuildQueues.keySet();
                for ( Integer key : keySet )
                {
                    OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                    overallBuildQueue.removeProjectsFromPrepareBuildQueueWithHashCodes( hashCodes );
                }
            }
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException( "Error encountered while removing project group(s) from build queue.", e );
        }
    }

>>>>>>> refs/remotes/apache/trunk
    private boolean isInQueue( int projectId, int typeOfQueue, int buildDefinitionId )
        throws TaskQueueException
    {
        synchronized ( overallBuildQueues )
        {
            Set<Integer> keySet = overallBuildQueues.keySet();
            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                if ( typeOfQueue == BUILD_QUEUE )
                {
                    if ( buildDefinitionId < 0 )
                    {
                        if ( overallBuildQueue.isInBuildQueue( projectId ) )
                        {
<<<<<<< HEAD
=======
                            log.info( "Project " + projectId + " is in build queue " + overallBuildQueue.getName() );
>>>>>>> refs/remotes/apache/trunk
                            return true;
                        }
                    }
                    else
                    {
                        if ( overallBuildQueue.isInBuildQueue( projectId, buildDefinitionId ) )
                        {
<<<<<<< HEAD
=======
                            log.info( "Project " + projectId + " is in build queue " + overallBuildQueue.getName() );
>>>>>>> refs/remotes/apache/trunk
                            return true;
                        }
                    }
                }
                else if ( typeOfQueue == CHECKOUT_QUEUE )
                {
                    if ( overallBuildQueue.isInCheckoutQueue( projectId ) )
                    {
<<<<<<< HEAD
=======
                        log.info( "Project " + projectId + " is in checkout queue " + overallBuildQueue.getName() );
>>>>>>> refs/remotes/apache/trunk
                        return true;
                    }
                }
            }

            return false;
        }
    }

    // get overall queue where project is queued
    private OverallBuildQueue getOverallBuildQueueWhereProjectIsQueued( int projectId, int typeOfQueue )
        throws TaskQueueException
    {
        synchronized ( overallBuildQueues )
        {
            OverallBuildQueue whereQueued = null;
            Set<Integer> keySet = overallBuildQueues.keySet();

            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                if ( typeOfQueue == BUILD_QUEUE )
                {
                    if ( overallBuildQueue.isInBuildQueue( projectId ) )
                    {
                        whereQueued = overallBuildQueue;
                        break;
                    }
                }
                else if ( typeOfQueue == CHECKOUT_QUEUE )
                {
                    if ( overallBuildQueue.isInCheckoutQueue( projectId ) )
                    {
                        whereQueued = overallBuildQueue;
                        break;
                    }
                }
<<<<<<< HEAD
=======
                else if ( typeOfQueue == PREPARE_BUILD_QUEUE )
                {
                    if ( overallBuildQueue.isInPrepareBuildQueue( projectId ) )
                    {
                        whereQueued = overallBuildQueue;
                        break;
                    }
                }
>>>>>>> refs/remotes/apache/trunk
            }

            return whereQueued;
        }
    }

    // get overall queue where project will be queued
<<<<<<< HEAD
    private OverallBuildQueue getOverallBuildQueue( int projectId, int typeOfQueue, List<BuildQueue> buildQueues )
=======
    private OverallBuildQueue getOverallBuildQueue( int typeOfQueue, List<BuildQueue> buildQueues )
>>>>>>> refs/remotes/apache/trunk
        throws BuildManagerException
    {
        OverallBuildQueue whereToBeQueued = null;
        synchronized ( overallBuildQueues )
        {
            if ( overallBuildQueues == null || overallBuildQueues.isEmpty() )
            {
                throw new BuildManagerException( "No build queues configured." );
            }

            int size = 0;
<<<<<<< HEAD
            int idx = 0;            
            int allowedBuilds = configurationService.getNumberOfBuildsInParallel();
            
            try
            {
                int count = 1;             
                for ( BuildQueue buildQueue : buildQueues )
                {            
                    if( count <= allowedBuilds )
                    {   
=======
            int idx = 0;
            int allowedBuilds = configurationService.getNumberOfBuildsInParallel();

            try
            {
                int count = 1;
                for ( BuildQueue buildQueue : buildQueues )
                {
                    if ( count <= allowedBuilds )
                    {
>>>>>>> refs/remotes/apache/trunk
                        OverallBuildQueue overallBuildQueue = overallBuildQueues.get( buildQueue.getId() );
                        if ( overallBuildQueue != null )
                        {
                            TaskQueue taskQueue = null;
<<<<<<< HEAD
                            if ( typeOfQueue == BUILD_QUEUE )
                            {
                                taskQueue = overallBuildQueue.getBuildQueue();
=======
                            TaskQueueExecutor taskQueueExecutor = null;
                            int tempSize = 0;
                            if ( typeOfQueue == BUILD_QUEUE )
                            {
                                taskQueue = overallBuildQueue.getBuildQueue();
                                taskQueueExecutor = overallBuildQueue.getBuildTaskQueueExecutor();
>>>>>>> refs/remotes/apache/trunk
                            }
                            else if ( typeOfQueue == CHECKOUT_QUEUE )
                            {
                                taskQueue = overallBuildQueue.getCheckoutQueue();
<<<<<<< HEAD
                            }
                            
                            if ( idx == 0 )
                            {
                                size = taskQueue.getQueueSnapshot().size();
                                whereToBeQueued = overallBuildQueue;
                            }
    
                            if ( taskQueue.getQueueSnapshot().size() < size )
                            {
                                whereToBeQueued = overallBuildQueue;
                                size = taskQueue.getQueueSnapshot().size();
                            }
    
=======
                                taskQueueExecutor = overallBuildQueue.getCheckoutTaskQueueExecutor();
                            }
                            else if ( typeOfQueue == PREPARE_BUILD_QUEUE )
                            {
                                taskQueue = overallBuildQueue.getPrepareBuildQueue();
                                taskQueueExecutor = overallBuildQueue.getPrepareBuildTaskQueueExecutor();
                            }

                            tempSize = taskQueue.getQueueSnapshot().size();
                            if ( taskQueueExecutor.getCurrentTask() != null )
                            {
                                tempSize++;
                            }

                            if ( idx == 0 )
                            {
                                whereToBeQueued = overallBuildQueue;
                                size = tempSize;
                            }

                            if ( tempSize < size )
                            {
                                whereToBeQueued = overallBuildQueue;
                                size = tempSize;
                            }

>>>>>>> refs/remotes/apache/trunk
                            idx++;
                        }
                        else
                        {
<<<<<<< HEAD
                            log.error( "Build queue not found." );                            
=======
                            log.error( "Build queue not found." );
>>>>>>> refs/remotes/apache/trunk
                        }
                        count++;
                    }
                    else
                    {
                        break;
<<<<<<< HEAD
                    }   
=======
                    }
>>>>>>> refs/remotes/apache/trunk
                }
            }
            catch ( TaskQueueException e )
            {
                throw new BuildManagerException( "Error occurred while retrieving task quueue: " + e.getMessage() );
            }
        }

        // use default overall build queue if none is configured
        if ( whereToBeQueued == null )
<<<<<<< HEAD
        {                
            Set<Integer> keySet = overallBuildQueues.keySet();
            for( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );                
                if( overallBuildQueue.getName().equals( ConfigurationService.DEFAULT_BUILD_QUEUE_NAME ) )
=======
        {
            Set<Integer> keySet = overallBuildQueues.keySet();
            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                if ( overallBuildQueue.getName().equals( ConfigurationService.DEFAULT_BUILD_QUEUE_NAME ) )
>>>>>>> refs/remotes/apache/trunk
                {
                    return overallBuildQueue;
                }
            }
        }

        return whereToBeQueued;
    }
<<<<<<< HEAD
    
    public void contextualize( Context context )
        throws ContextException
    {
        container = (PlexusContainer) context.get( PlexusConstants.PLEXUS_KEY ); 
        
=======

    public OverallBuildQueue getOverallBuildQueueWhereProjectsInGroupAreQueued( int projectGroupId )
        throws BuildManagerException
    {
        OverallBuildQueue whereToBeQueued = null;

        try
        {
            List<Project> projects = projectDao.getProjectsInGroup( projectGroupId );

            if ( projects != null )
            {
                for ( Project project : projects )
                {
                    whereToBeQueued = getOverallBuildQueueWhereProjectIsQueued( project.getId(), BUILD_QUEUE );

                    if ( whereToBeQueued == null )
                    {
                        whereToBeQueued = getOverallBuildQueueWhereProjectIsBuilding( project.getId() );
                    }

                    if ( whereToBeQueued != null )
                    {
                        break;
                    }
                }
            }
        }
        catch ( ContinuumStoreException e )
        {
            throw new BuildManagerException(
                "Error while retrieving overall build queue for project: " + e.getMessage() );
        }
        catch ( TaskQueueException e )
        {
            throw new BuildManagerException(
                "Error while retrieving overall build queue for project: " + e.getMessage() );
        }

        return whereToBeQueued;
    }

    private OverallBuildQueue getOverallBuildQueueWhereProjectGroupIsQueued( int projectGroupId, int scmRootId )
        throws TaskQueueException
    {
        synchronized ( overallBuildQueues )
        {
            OverallBuildQueue whereQueued = null;
            Set<Integer> keySet = overallBuildQueues.keySet();

            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );

                if ( overallBuildQueue.isInPrepareBuildQueue( projectGroupId, scmRootId ) )
                {
                    whereQueued = overallBuildQueue;
                }
            }

            return whereQueued;
        }
    }

    private OverallBuildQueue getOverallBuildQueueWhereProjectGroupIsQueued( int projectGroupId, String scmRootAddress )
        throws TaskQueueException
    {
        synchronized ( overallBuildQueues )
        {
            OverallBuildQueue whereQueued = null;
            Set<Integer> keySet = overallBuildQueues.keySet();

            for ( Integer key : keySet )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );

                if ( overallBuildQueue.isInPrepareBuildQueue( projectGroupId, scmRootAddress ) )
                {
                    whereQueued = overallBuildQueue;
                }
            }

            return whereQueued;
        }
    }

    private OverallBuildQueue getOverallBuildQueueWhereProjectIsBuilding( int projectId )
    {
        synchronized ( overallBuildQueues )
        {
            for ( Integer key : overallBuildQueues.keySet() )
            {
                OverallBuildQueue overallBuildQueue = overallBuildQueues.get( key );
                BuildProjectTask task =
                    (BuildProjectTask) overallBuildQueue.getBuildTaskQueueExecutor().getCurrentTask();
                if ( task != null && task.getProjectId() == projectId )
                {
                    return overallBuildQueue;
                }
            }
            return null;
        }
    }

    public boolean isProjectCurrentlyBeingCheckedOut( int projectId )
        throws BuildManagerException
    {
        Map<String, CheckOutTask> checkouts = getCurrentCheckouts();
        for ( String key : checkouts.keySet() )
        {
            CheckOutTask task = checkouts.get( key );
            if ( task.getProjectId() == projectId )
            {
                return true;
            }
        }

        return false;
    }

    public boolean isAnyProjectCurrentlyBuilding( int[] projectIds )
        throws BuildManagerException
    {
        for ( int i = 0; i < projectIds.length; i++ )
        {
            if ( isProjectInAnyCurrentBuild( projectIds[i] ) )
            {
                return true;
            }
        }

        return false;
    }

    public boolean isAnyProjectCurrentlyPreparingBuild( int[] projectIds )
        throws BuildManagerException
    {
        for ( int i = 0; i < projectIds.length; i++ )
        {
            if ( isProjectCurrentlyPreparingBuild( projectIds[i] ) )
            {
                return true;
            }
        }

        return false;
    }

    public void contextualize( Context context )
        throws ContextException
    {
        container = (PlexusContainer) context.get( PlexusConstants.PLEXUS_KEY );

>>>>>>> refs/remotes/apache/trunk
        synchronized ( overallBuildQueues )
        {
            try
            {
                // create all the build queues configured in the database, not just the default!               
                List<BuildQueue> buildQueues = buildQueueService.getAllBuildQueues();
<<<<<<< HEAD
                for( BuildQueue buildQueue : buildQueues )
                {   
                    createOverallBuildQueue( buildQueue );
                }               

                // add default overall build queue if not yet added to the map
                BuildQueue defaultBuildQueue = configurationService.getDefaultBuildQueue();
                if( overallBuildQueues.get( defaultBuildQueue.getId() ) == null )
=======
                for ( BuildQueue buildQueue : buildQueues )
                {
                    createOverallBuildQueue( buildQueue );
                }

                // add default overall build queue if not yet added to the map
                BuildQueue defaultBuildQueue = configurationService.getDefaultBuildQueue();
                if ( overallBuildQueues.get( defaultBuildQueue.getId() ) == null )
>>>>>>> refs/remotes/apache/trunk
                {
                    createOverallBuildQueue( defaultBuildQueue );
                }
            }
            catch ( ComponentLookupException e )
            {
                log.error( "Cannot create overall build queue: " + e.getMessage() );
            }
            catch ( BuildQueueServiceException e )
            {
                log.error( "Cannot create overall build queue: " + e.getMessage() );
            }
        }
    }

    public void setContainer( PlexusContainer container )
    {
        this.container = container;
    }
<<<<<<< HEAD
    
    private OverallBuildQueue createOverallBuildQueue( BuildQueue defaultBuildQueue )
        throws ComponentLookupException
    {
        OverallBuildQueue overallBuildQueue =
            (OverallBuildQueue) container.lookup( OverallBuildQueue.class );
        overallBuildQueue.setId( defaultBuildQueue.getId() );
        overallBuildQueue.setName( defaultBuildQueue.getName() );
    
        overallBuildQueues.put( overallBuildQueue.getId(), overallBuildQueue );
        
        return overallBuildQueue;
    }
    
    public TaskQueue getPrepareBuildQueue()
    {
        return prepareBuildQueue;
    }
    
    public void setPrepareBuildQueue( TaskQueue prepareBuildQueue )
    {
        this.prepareBuildQueue = prepareBuildQueue;
    }
    
    // for unit tests.. 
    
=======

    private void createOverallBuildQueue( BuildQueue defaultBuildQueue )
        throws ComponentLookupException
    {
        OverallBuildQueue overallBuildQueue = (OverallBuildQueue) container.lookup( OverallBuildQueue.class );
        overallBuildQueue.setId( defaultBuildQueue.getId() );
        overallBuildQueue.setName( defaultBuildQueue.getName() );

        overallBuildQueues.put( overallBuildQueue.getId(), overallBuildQueue );
    }

    // for unit tests.. 

>>>>>>> refs/remotes/apache/trunk
    public void setOverallBuildQueues( Map<Integer, OverallBuildQueue> overallBuildQueues )
    {
        this.overallBuildQueues = overallBuildQueues;
    }

    public void setConfigurationService( ConfigurationService configurationService )
    {
        this.configurationService = configurationService;
    }

    public void setBuildQueueService( BuildQueueService buildQueueService )
    {
        this.buildQueueService = buildQueueService;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setBuildDefinitionDao( BuildDefinitionDao buildDefinitionDao )
    {
        this.buildDefinitionDao = buildDefinitionDao;
    }
<<<<<<< HEAD
=======

    public void setProjectDao( ProjectDao projectDao )
    {
        this.projectDao = projectDao;
    }
>>>>>>> refs/remotes/apache/trunk
}
