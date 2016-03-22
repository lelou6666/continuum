package org.apache.continuum.taskqueue.manager;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.continuum.dao.BuildDefinitionDao;
=======
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

import org.apache.commons.lang.ArrayUtils;
import org.apache.continuum.buildmanager.BuildManagerException;
import org.apache.continuum.buildmanager.BuildsManager;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.model.repository.LocalRepository;
import org.apache.continuum.model.repository.RepositoryPurgeConfiguration;
import org.apache.continuum.purge.PurgeConfigurationService;
import org.apache.continuum.purge.task.PurgeTask;
<<<<<<< HEAD
import org.apache.maven.continuum.buildqueue.BuildProjectTask;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.release.tasks.PerformReleaseProjectTask;
import org.apache.maven.continuum.scm.queue.CheckOutTask;
import org.apache.maven.continuum.scm.queue.PrepareBuildProjectsTask;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.logging.AbstractLogEnabled;
=======
import org.apache.continuum.taskqueue.BuildProjectTask;
import org.apache.continuum.taskqueue.PrepareBuildProjectsTask;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.release.tasks.PerformReleaseProjectTask;
import org.apache.maven.continuum.release.tasks.PrepareReleaseProjectTask;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
>>>>>>> refs/remotes/apache/trunk
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.codehaus.plexus.taskqueue.TaskQueueException;
import org.codehaus.plexus.taskqueue.execution.TaskQueueExecutor;
<<<<<<< HEAD
import org.codehaus.plexus.util.StringUtils;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
 * @plexus.component role="org.apache.continuum.taskqueue.manager.TaskQueueManager" role-hint="default"
 */
public class DefaultTaskQueueManager
    extends AbstractLogEnabled
    implements TaskQueueManager, Contextualizable
{
    /**
     * @plexus.requirement role-hint="build-project"
     */
    private TaskQueue buildQueue;

    /**
     * @plexus.requirement role-hint="check-out-project"
     */
    private TaskQueue checkoutQueue;

    /**
     * @plexus.requirement role-hint="prepare-build-project"
     */
    private TaskQueue prepareBuildQueue;

    /**
     * @plexus.requirement role-hint="purge"
     */
    private TaskQueue purgeQueue;
    
    /**
     * @plexus.requirement
     */
    private BuildDefinitionDao buildDefinitionDao;
    
    /**
     * @plexus.requirement
     */
    private ProjectDao projectDao;

    /**
     * @plexus.requirement
     */
    private PurgeConfigurationService purgeConfigurationService;
    
    private PlexusContainer container;

    public boolean buildInProgress()
        throws TaskQueueManagerException
    {
        Task task = getCurrentTask( "build-project" );
    
        if ( task != null && task instanceof BuildProjectTask )
        {
            return true;
        }
    
        return false;
    }
    
    public void cancelBuildTask( int projectId )
        throws TaskQueueManagerException
    {
        Task currentTask = getBuildTaskQueueExecutor().getCurrentTask();
        
        if ( currentTask instanceof BuildProjectTask )
        {
            if ( ( (BuildProjectTask) currentTask ).getProjectId() == projectId )
            {
                getLogger().info( "Cancelling task for project " + projectId );
                getBuildTaskQueueExecutor().cancelTask( currentTask );
            }
        }
    }

    public boolean cancelCurrentBuild()
        throws TaskQueueManagerException
    {
        Task task = getBuildTaskQueueExecutor().getCurrentTask();
        
        if ( task != null )
        {
            if ( task instanceof BuildProjectTask )
            {
                getLogger().info( "Cancelling current build task" );
                return getBuildTaskQueueExecutor().cancelTask( task );
            }
            else
            {
                getLogger().warn( "Current task not a BuildProjectTask - not cancelling" );
            }
        }
        else
        {
            getLogger().warn( "No task running - not cancelling" );
        }
        return false;
    }

    public TaskQueue getBuildQueue()
    {
        return buildQueue;
    }

    public TaskQueueExecutor getBuildTaskQueueExecutor()
        throws TaskQueueManagerException
    {
        try
        {
            return (TaskQueueExecutor) container.lookup( TaskQueueExecutor.class, "build-project" );
        }
        catch ( ComponentLookupException e )
        {
            throw new TaskQueueManagerException( e.getMessage(), e );
        }
    }

    public TaskQueueExecutor getCheckoutTaskQueueExecutor()
        throws TaskQueueManagerException
    {
        try
        {
            return (TaskQueueExecutor) container.lookup( TaskQueueExecutor.class, "check-out-project" );
        }
        catch ( ComponentLookupException e )
        {
            throw new TaskQueueManagerException( e.getMessage(), e );
        }
    }

    public TaskQueue getCheckoutQueue()
    {
        return checkoutQueue;
    }
    
    public List<CheckOutTask> getCheckOutTasksInQueue()
        throws TaskQueueManagerException
    {
        try
        {
            return checkoutQueue.getQueueSnapshot();
        }
        catch ( TaskQueueException e )
        {
            throw new TaskQueueManagerException( "Error while getting the checkout queue.", e );
        }
    }

    public int getCurrentProjectIdBuilding()
        throws TaskQueueManagerException
    {
        Task task = getBuildTaskQueueExecutor().getCurrentTask();
        if ( task != null )
        {
            if ( task instanceof BuildProjectTask )
            {
                return ( (BuildProjectTask) task ).getProjectId();
            }
        }
        return -1;
    }

    public TaskQueue getPrepareBuildQueue()
    {
        return prepareBuildQueue;
    }   

    public TaskQueueExecutor getPrepareBuildTaskQueueExecutor()
        throws TaskQueueManagerException
    {
        try
        {
            return (TaskQueueExecutor) container.lookup( TaskQueueExecutor.class, "prepare-build-project" );
        }
        catch ( ComponentLookupException e )
        {
            throw new TaskQueueManagerException( e.getMessage(), e );
        }
    }

    public List<BuildProjectTask> getProjectsInBuildQueue()
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
 */
@Component( role = org.apache.continuum.taskqueue.manager.TaskQueueManager.class, hint = "default" )
public class DefaultTaskQueueManager
    implements TaskQueueManager, Contextualizable
{
    private static final Logger log = LoggerFactory.getLogger( DefaultTaskQueueManager.class );

    @Requirement( hint = "distributed-build-project" )
    private TaskQueue distributedBuildQueue;

    @Requirement( hint = "purge" )
    private TaskQueue purgeQueue;

    @Requirement( hint = "prepare-release" )
    private TaskQueue prepareReleaseQueue;

    @Requirement( hint = "perform-release" )
    private TaskQueue performReleaseQueue;

    @Requirement
    private ProjectDao projectDao;

    @Requirement
    private PurgeConfigurationService purgeConfigurationService;

    @Requirement( hint = "parallel" )
    private BuildsManager buildsManager;

    private PlexusContainer container;

    public TaskQueue getDistributedBuildQueue()
    {
        return distributedBuildQueue;
    }

    public List<PrepareBuildProjectsTask> getDistributedBuildProjectsInQueue()
>>>>>>> refs/remotes/apache/trunk
        throws TaskQueueManagerException
    {
        try
        {
<<<<<<< HEAD
            return buildQueue.getQueueSnapshot();
        }
        catch ( TaskQueueException e )
        {
            throw new TaskQueueManagerException( "Error while getting the building queue.", e );
        }
    }
    
=======
            return distributedBuildQueue.getQueueSnapshot();
        }
        catch ( TaskQueueException e )
        {
            throw new TaskQueueManagerException( "Error while getting the distributed building queue", e );
        }
    }

>>>>>>> refs/remotes/apache/trunk
    public TaskQueue getPurgeQueue()
    {
        return purgeQueue;
    }
<<<<<<< HEAD
    
    public boolean isInBuildingQueue( int projectId )
        throws TaskQueueManagerException
    {
        return isInBuildingQueue( projectId, -1 );
    }
    
    public boolean isInBuildingQueue( int projectId, int buildDefinitionId )
        throws TaskQueueManagerException
    {
        List<BuildProjectTask> queue = getProjectsInBuildQueue();
    
        for ( BuildProjectTask task : queue )
        {
            if ( task != null )
            {
                if ( buildDefinitionId < 0 )
                {
                    if ( task.getProjectId() == projectId )
                    {
                        return true;
                    }
                }
                else
                {
                    if ( task.getProjectId() == projectId && task.getBuildDefinitionId() == buildDefinitionId )
=======

    public boolean isInDistributedBuildQueue( int projectGroupId, String scmRootAddress )
        throws TaskQueueManagerException
    {
        try
        {
            List<PrepareBuildProjectsTask> queue = distributedBuildQueue.getQueueSnapshot();

            for ( PrepareBuildProjectsTask task : queue )
            {
                if ( task != null )
                {
                    if ( task.getProjectGroupId() == projectGroupId && task.getScmRootAddress().equals(
                        scmRootAddress ) )
>>>>>>> refs/remotes/apache/trunk
                    {
                        return true;
                    }
                }
            }
<<<<<<< HEAD
        }
    
        return false;
    }
    
    public boolean isInCheckoutQueue( int projectId )
        throws TaskQueueManagerException
    {
        List<CheckOutTask> queue = getCheckOutTasksInQueue();
    
        for ( CheckOutTask task : queue )
        {
            if ( task != null && task.getProjectId() == projectId )
=======

            return false;
        }
        catch ( TaskQueueException e )
        {
            throw new TaskQueueManagerException( "Error while getting the tasks in distributed build queue", e );
        }
    }

    public boolean isInPurgeQueue( int purgeConfigId )
        throws TaskQueueManagerException
    {
        List<PurgeTask> queue = getAllPurgeConfigurationsInPurgeQueue();

        for ( PurgeTask task : queue )
        {
            if ( task != null && task.getPurgeConfigurationId() == purgeConfigId )
>>>>>>> refs/remotes/apache/trunk
            {
                return true;
            }
        }
<<<<<<< HEAD
    
        return false;
    }

    public boolean isInCurrentPrepareBuildTask( int projectId )
        throws TaskQueueManagerException
    {
        Task task = getPrepareBuildTaskQueueExecutor().getCurrentTask();

        if ( task != null &&  task instanceof PrepareBuildProjectsTask )
        {
            Map<Integer, Integer> map = ( (PrepareBuildProjectsTask) task).getProjectsBuildDefinitionsMap();
            
            if ( map.size() > 0 )
            {
                Set<Integer> projectIds = map.keySet();
                
                if ( projectIds.contains( new Integer( projectId ) ) )
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isInPrepareBuildQueue( int projectId )
=======
        return false;
    }

    public boolean isRepositoryInPurgeQueue( int repositoryId )
        throws TaskQueueManagerException
    {
        List<RepositoryPurgeConfiguration> repoPurgeConfigs =
            purgeConfigurationService.getRepositoryPurgeConfigurationsByRepository( repositoryId );

        for ( RepositoryPurgeConfiguration repoPurge : repoPurgeConfigs )
        {
            if ( isInPurgeQueue( repoPurge.getId() ) )
            {
                return true;
            }
        }
        return false;
    }

    public boolean isRepositoryInUse( int repositoryId )
>>>>>>> refs/remotes/apache/trunk
        throws TaskQueueManagerException
    {
        try
        {
<<<<<<< HEAD
            List<PrepareBuildProjectsTask> queue = prepareBuildQueue.getQueueSnapshot();
            
            for ( PrepareBuildProjectsTask task : queue )
            {
                if ( task != null )
                {
                    Map<Integer, Integer> map = ( (PrepareBuildProjectsTask) task).getProjectsBuildDefinitionsMap();
                    
                    if ( map.size() > 0 )
                    {
                        Set<Integer> projectIds = map.keySet();
                        
                        if ( projectIds.contains( new Integer( projectId ) ) )
                        {
                            return true;
                        }
                    }
                }
            }
            
            return false;
        }
        catch ( TaskQueueException e )
        {
            throw new TaskQueueManagerException( "Error while getting the tasks in prepare build queue", e );
        }
    }
    
    public boolean isInPurgeQueue( int purgeConfigId )
        throws TaskQueueManagerException
    {
        List<PurgeTask> queue = getAllPurgeConfigurationsInPurgeQueue();
    
        for ( PurgeTask task : queue )
        {
            if ( task != null && task.getPurgeConfigurationId() == purgeConfigId )
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isRepositoryInPurgeQueue( int repositoryId )
        throws TaskQueueManagerException
    {
        List<RepositoryPurgeConfiguration> repoPurgeConfigs =
            purgeConfigurationService.getRepositoryPurgeConfigurationsByRepository( repositoryId );
    
        for ( RepositoryPurgeConfiguration repoPurge : repoPurgeConfigs )
        {
            if ( isInPurgeQueue( repoPurge.getId() ) )
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isRepositoryInUse( int repositoryId )
        throws TaskQueueManagerException
    {
        try
        {
            Task task = getCurrentTask( "build-project" );
    
            if ( task != null && task instanceof BuildProjectTask )
            {
                int projectId = ( (BuildProjectTask) task ).getProjectId();
    
                Project project = projectDao.getProject( projectId );
                LocalRepository repository = project.getProjectGroup().getLocalRepository();
    
                if ( repository != null && repository.getId() == repositoryId )
                {
                    return true;
                }
            }
            return false;
        }
        catch ( ContinuumStoreException e )
        {
            throw new TaskQueueManagerException( e.getMessage(), e );
        }
=======
            Map<String, BuildProjectTask> currentBuilds = buildsManager.getCurrentBuilds();
            Set<String> keys = currentBuilds.keySet();

            for ( String key : keys )
            {
                BuildProjectTask task = currentBuilds.get( key );
                if ( task != null )
                {
                    int projectId = task.getProjectId();

                    Project project = projectDao.getProject( projectId );
                    LocalRepository repository = project.getProjectGroup().getLocalRepository();

                    if ( repository != null && repository.getId() == repositoryId )
                    {
                        return true;
                    }
                }
            }

            return false;
        }
        catch ( BuildManagerException e )
        {
            log.error( "Error occured while getting current builds: " + e.getMessage() );
            throw new TaskQueueManagerException( e.getMessage(), e );
        }
        catch ( ContinuumStoreException e )
        {
            log.error( "Error occured while getting project details: " + e.getMessage() );
            throw new TaskQueueManagerException( e.getMessage(), e );
        }
    }

    public boolean isProjectInReleaseStage( String releaseId )
        throws TaskQueueManagerException
    {
        Task prepareTask = getCurrentTask( "prepare-release" );
        if ( prepareTask != null && prepareTask instanceof PrepareReleaseProjectTask )
        {
            if ( ( (PrepareReleaseProjectTask) prepareTask ).getReleaseId().equals( releaseId ) )
            {
                return true;
            }
            else
            {
                try
                {
                    // check if in queue
                    List<Task> tasks = prepareReleaseQueue.getQueueSnapshot();
                    for ( Task prepareReleaseTask : tasks )
                    {
                        if ( ( (PrepareReleaseProjectTask) prepareReleaseTask ).getReleaseId().equals( releaseId ) )
                        {
                            return true;
                        }
                    }
                }
                catch ( TaskQueueException e )
                {
                    throw new TaskQueueManagerException( e );
                }
            }
        }

        Task performTask = getCurrentTask( "perform-release" );
        if ( performTask != null && performTask instanceof PerformReleaseProjectTask )
        {
            if ( ( (PerformReleaseProjectTask) performTask ).getReleaseId().equals( releaseId ) )
            {
                return true;
            }
            else
            {
                try
                {
                    // check if in queue
                    List<Task> tasks = performReleaseQueue.getQueueSnapshot();
                    for ( Task performReleaseTask : tasks )
                    {
                        if ( ( (PerformReleaseProjectTask) performReleaseTask ).getReleaseId().equals( releaseId ) )
                        {
                            return true;
                        }
                    }
                }
                catch ( TaskQueueException e )
                {
                    throw new TaskQueueManagerException( e );
                }
            }
        }

        return false;
>>>>>>> refs/remotes/apache/trunk
    }

    public boolean releaseInProgress()
        throws TaskQueueManagerException
    {
        Task task = getCurrentTask( "perform-release" );
<<<<<<< HEAD
    
        if ( task != null && task instanceof PerformReleaseProjectTask )
        {
            return true;
        }
    
        return false;
    }

    public boolean removeFromBuildingQueue( int projectId, int buildDefinitionId, int trigger, String projectName )
        throws TaskQueueManagerException
    {
        BuildDefinition buildDefinition;
        
        try
        {
            buildDefinition = buildDefinitionDao.getBuildDefinition( buildDefinitionId );
        }
        catch ( ContinuumStoreException e )
        {
            throw new TaskQueueManagerException( "Error while removing project from build queue: " + projectName, e );
        }
        
        String buildDefinitionLabel = buildDefinition.getDescription();
        if ( StringUtils.isEmpty( buildDefinitionLabel ) )
        {
            buildDefinitionLabel = buildDefinition.getGoals();
        }
        BuildProjectTask buildProjectTask =
            new BuildProjectTask( projectId, buildDefinitionId, trigger, projectName, buildDefinitionLabel );
        return this.buildQueue.remove( buildProjectTask );
=======

        return task != null && task instanceof PerformReleaseProjectTask;
    }

    public void removeFromDistributedBuildQueue( int projectGroupId, String scmRootAddress )
        throws TaskQueueManagerException
    {
        List<PrepareBuildProjectsTask> queue = getDistributedBuildProjectsInQueue();

        for ( PrepareBuildProjectsTask task : queue )
        {
            if ( task.getProjectGroupId() == projectGroupId && task.getScmRootAddress().equals( scmRootAddress ) )
            {
                distributedBuildQueue.remove( task );
            }
        }
>>>>>>> refs/remotes/apache/trunk
    }

    public boolean removeFromPurgeQueue( int purgeConfigId )
        throws TaskQueueManagerException
    {
        List<PurgeTask> queue = getAllPurgeConfigurationsInPurgeQueue();
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        for ( PurgeTask task : queue )
        {
            if ( task != null && task.getPurgeConfigurationId() == purgeConfigId )
            {
                return purgeQueue.remove( task );
            }
        }
        return false;
    }

<<<<<<< HEAD

=======
>>>>>>> refs/remotes/apache/trunk
    public boolean removeFromPurgeQueue( int[] purgeConfigIds )
        throws TaskQueueManagerException
    {
        if ( purgeConfigIds == null )
        {
            return false;
        }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        if ( purgeConfigIds.length < 1 )
        {
            return false;
        }
<<<<<<< HEAD
    
        List<PurgeTask> queue = getAllPurgeConfigurationsInPurgeQueue();
    
        List<PurgeTask> tasks = new ArrayList<PurgeTask>();
    
=======

        List<PurgeTask> queue = getAllPurgeConfigurationsInPurgeQueue();

        List<PurgeTask> tasks = new ArrayList<PurgeTask>();

>>>>>>> refs/remotes/apache/trunk
        for ( PurgeTask task : queue )
        {
            if ( task != null )
            {
                if ( ArrayUtils.contains( purgeConfigIds, task.getPurgeConfigurationId() ) )
                {
                    tasks.add( task );
                }
            }
        }
<<<<<<< HEAD
    
        if ( !tasks.isEmpty() )
        {
            return purgeQueue.removeAll( tasks );
        }
    
        return false;
    }
    
    public boolean removeProjectsFromBuildingQueue( int[] projectsId )
        throws TaskQueueManagerException
    {
        if ( projectsId == null )
        {
            return false;
        }
        if ( projectsId.length < 1 )
        {
            return false;
        }
        List<BuildProjectTask> queue = getProjectsInBuildQueue();
    
        List<BuildProjectTask> tasks = new ArrayList<BuildProjectTask>();
    
        for ( BuildProjectTask task : queue )
        {
            if ( task != null )
            {
                if ( ArrayUtils.contains( projectsId, task.getProjectId() ) )
                {
                    tasks.add( task );
                }
            }
        }

        for ( BuildProjectTask buildProjectTask : tasks )
        {
            getLogger().info( "cancel build for project " + buildProjectTask.getProjectId() );
        }
        if ( !tasks.isEmpty() )
        {
            return buildQueue.removeAll( tasks );
        }
    
        return false;
    }
    
    public boolean removeProjectFromBuildingQueue( int projectId )
        throws TaskQueueManagerException
    {
        List<BuildProjectTask> queue = getProjectsInBuildQueue();
    
        for ( BuildProjectTask task : queue )
        {
            if ( task != null && task.getProjectId() == projectId )
            {
                return buildQueue.remove( task );
            }
        }
    
        return false;
    }
    
    public boolean removeProjectsFromCheckoutQueue( int[] projectsId )
        throws TaskQueueManagerException
    {
        if ( projectsId == null )
        {
            return false;
        }
        if ( projectsId.length < 1 )
        {
            return false;
        }
        List<CheckOutTask> queue = getCheckOutTasksInQueue();
    
        List<CheckOutTask> tasks = new ArrayList<CheckOutTask>();
    
        for ( CheckOutTask task : queue )
        {
            if ( task != null )
            {
                if ( ArrayUtils.contains( projectsId, task.getProjectId() ) )
                {
                    tasks.add( task );
                }
            }
        }
        if ( !tasks.isEmpty() )
        {
            return checkoutQueue.removeAll( tasks );
        }
        return false;
    }
    
    public void removeProjectsFromBuildingQueueWithHashCodes( int[] hashCodes )
        throws TaskQueueManagerException
    {
        List<BuildProjectTask> queue = getProjectsInBuildQueue();
    
        for ( BuildProjectTask task : queue )
        {
            if ( ArrayUtils.contains( hashCodes, task.hashCode() ) )
            {
                buildQueue.remove( task );
            }
        }
    }
    
    public boolean removeProjectFromCheckoutQueue( int projectId )
        throws TaskQueueManagerException
    {
        List<CheckOutTask> queue = getCheckOutTasksInQueue();
    
        for ( CheckOutTask task : queue )
        {
            if ( task != null && task.getProjectId() == projectId )
            {
                return checkoutQueue.remove( task );
            }
        }
    
        return false;
    }
    
=======

        return !tasks.isEmpty() && purgeQueue.removeAll( tasks );
    }

>>>>>>> refs/remotes/apache/trunk
    public void removeRepositoryFromPurgeQueue( int repositoryId )
        throws TaskQueueManagerException
    {
        List<RepositoryPurgeConfiguration> repoPurgeConfigs =
            purgeConfigurationService.getRepositoryPurgeConfigurationsByRepository( repositoryId );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        for ( RepositoryPurgeConfiguration repoPurge : repoPurgeConfigs )
        {
            removeFromPurgeQueue( repoPurge.getId() );
        }
    }

<<<<<<< HEAD
    public void removeTasksFromCheckoutQueueWithHashCodes( int[] hashCodes )
        throws TaskQueueManagerException
    {
        List<CheckOutTask> queue = getCheckOutTasksInQueue();
    
        for ( CheckOutTask task : queue )
        {
            if ( ArrayUtils.contains( hashCodes, task.hashCode() ) )
            {
                checkoutQueue.remove( task );
=======
    public void removeTasksFromDistributedBuildQueueWithHashCodes( int[] hashCodes )
        throws TaskQueueManagerException
    {
        List<PrepareBuildProjectsTask> queue = getDistributedBuildProjectsInQueue();

        for ( PrepareBuildProjectsTask task : queue )
        {
            if ( ArrayUtils.contains( hashCodes, task.hashCode() ) )
            {
                distributedBuildQueue.remove( task );
>>>>>>> refs/remotes/apache/trunk
            }
        }
    }

    public void contextualize( Context context )
        throws ContextException
    {
        container = (PlexusContainer) context.get( PlexusConstants.PLEXUS_KEY );
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    private List<PurgeTask> getAllPurgeConfigurationsInPurgeQueue()
        throws TaskQueueManagerException
    {
        try
        {
            return purgeQueue.getQueueSnapshot();
        }
        catch ( TaskQueueException e )
        {
            throw new TaskQueueManagerException( "Error while getting the purge configs in purge queue", e );
        }
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    private Task getCurrentTask( String task )
        throws TaskQueueManagerException
    {
        try
        {
            TaskQueueExecutor executor = (TaskQueueExecutor) container.lookup( TaskQueueExecutor.class, task );
            return executor.getCurrentTask();
        }
        catch ( ComponentLookupException e )
        {
            throw new TaskQueueManagerException( "Unable to lookup current task", e );
        }
    }
}
