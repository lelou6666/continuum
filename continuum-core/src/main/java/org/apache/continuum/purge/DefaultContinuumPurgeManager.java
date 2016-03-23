package org.apache.continuum.purge;

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
import org.apache.commons.lang.ArrayUtils;
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.model.repository.DirectoryPurgeConfiguration;
import org.apache.continuum.model.repository.LocalRepository;
import org.apache.continuum.model.repository.RepositoryPurgeConfiguration;
import org.apache.continuum.purge.task.PurgeTask;
import org.apache.maven.continuum.buildqueue.BuildProjectTask;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.Schedule;
import org.apache.maven.continuum.release.tasks.PerformReleaseProjectTask;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.codehaus.plexus.taskqueue.TaskQueueException;
import org.codehaus.plexus.taskqueue.execution.TaskQueueExecutor;

import java.util.ArrayList;
=======
import org.apache.continuum.buildmanager.BuildsManager;
import org.apache.continuum.model.repository.DirectoryPurgeConfiguration;
import org.apache.continuum.model.repository.DistributedDirectoryPurgeConfiguration;
import org.apache.continuum.model.repository.DistributedRepositoryPurgeConfiguration;
import org.apache.continuum.model.repository.LocalRepository;
import org.apache.continuum.model.repository.RepositoryPurgeConfiguration;
import org.apache.continuum.purge.task.PurgeTask;
import org.apache.continuum.taskqueue.manager.TaskQueueManager;
import org.apache.continuum.taskqueue.manager.TaskQueueManagerException;
import org.apache.maven.continuum.build.settings.SchedulesActivationException;
import org.apache.maven.continuum.build.settings.SchedulesActivator;
import org.apache.maven.continuum.model.project.Schedule;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.taskqueue.TaskQueueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

>>>>>>> refs/remotes/apache/trunk
import java.util.List;

/**
 * DefaultContinuumPurgeManager
 *
 * @author Maria Catherine Tan
<<<<<<< HEAD
 * @version $Id$
 * @plexus.component role="org.apache.continuum.purge.ContinuumPurgeManager" role-hint="default"
 * @since 25 jul 07
 */
public class DefaultContinuumPurgeManager
    implements ContinuumPurgeManager, Contextualizable
{
    /**
     * @plexus.requirement
     */
    private ProjectDao projectDao;

    /**
     * @plexus.requirement role-hint="purge"
     */
    private TaskQueue purgeQueue;

    /**
     * @plexus.requirement
     */
    private PurgeConfigurationService purgeConfigurationService;

    private PlexusContainer container;
=======
 * @since 25 jul 07
 */
@Component( role = org.apache.continuum.purge.ContinuumPurgeManager.class, hint = "default" )
public class DefaultContinuumPurgeManager
    implements ContinuumPurgeManager
{
    private static final Logger log = LoggerFactory.getLogger( DefaultContinuumPurgeManager.class );

    @Requirement
    private SchedulesActivator schedulesActivator;

    @Requirement
    private PurgeConfigurationService purgeConfigurationService;

    @Requirement
    private TaskQueueManager taskQueueManager;

    @Requirement( hint = "parallel" )
    private BuildsManager parallelBuildsManager;
>>>>>>> refs/remotes/apache/trunk

    public void purge( Schedule schedule )
        throws ContinuumPurgeManagerException
    {
<<<<<<< HEAD
        List<RepositoryPurgeConfiguration> repoPurgeList = null;
        List<DirectoryPurgeConfiguration> dirPurgeList = null;

        repoPurgeList = purgeConfigurationService.getRepositoryPurgeConfigurationsBySchedule( schedule.getId() );
        dirPurgeList = purgeConfigurationService.getDirectoryPurgeConfigurationsBySchedule( schedule.getId() );

        if ( repoPurgeList != null && repoPurgeList.size() > 0 )
=======
        List<RepositoryPurgeConfiguration> repoPurgeList;
        List<DirectoryPurgeConfiguration> dirPurgeList;
        List<DistributedDirectoryPurgeConfiguration> distributedDirPurgeList;
        List<DistributedRepositoryPurgeConfiguration> distributedRepoPurgeList;

        repoPurgeList = purgeConfigurationService.getEnableRepositoryPurgeConfigurationsBySchedule( schedule.getId() );
        dirPurgeList = purgeConfigurationService.getEnableDirectoryPurgeConfigurationsBySchedule( schedule.getId() );
        distributedDirPurgeList = purgeConfigurationService.getEnableDistributedDirectoryPurgeConfigurationsBySchedule(
            schedule.getId() );
        distributedRepoPurgeList =
            purgeConfigurationService.getEnableDistributedRepositoryPurgeConfigurationsBySchedule(
                schedule.getId() );

        boolean hasRepoPurge = repoPurgeList != null && repoPurgeList.size() > 0;
        boolean hasDirPurge = dirPurgeList != null && dirPurgeList.size() > 0;
        boolean hasDitributedDirPurge = distributedDirPurgeList != null && distributedDirPurgeList.size() > 0;
        boolean hasDistributedRepoPurge = distributedRepoPurgeList != null && distributedRepoPurgeList.size() > 0;

        if ( hasRepoPurge )
>>>>>>> refs/remotes/apache/trunk
        {
            for ( RepositoryPurgeConfiguration repoPurge : repoPurgeList )
            {
                purgeRepository( repoPurge );
            }
        }

<<<<<<< HEAD
        if ( dirPurgeList != null && dirPurgeList.size() > 0 )
=======
        if ( hasDirPurge )
>>>>>>> refs/remotes/apache/trunk
        {
            for ( DirectoryPurgeConfiguration dirPurge : dirPurgeList )
            {
                purgeDirectory( dirPurge );
            }
        }
<<<<<<< HEAD
    }

    public boolean isRepositoryInPurgeQueue( int repositoryId )
        throws ContinuumPurgeManagerException
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
        throws ContinuumPurgeManagerException
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
            throw new ContinuumPurgeManagerException( e.getMessage(), e );
        }
    }

    public void removeRepositoryFromPurgeQueue( int repositoryId )
        throws ContinuumPurgeManagerException
    {
        List<RepositoryPurgeConfiguration> repoPurgeConfigs =
            purgeConfigurationService.getRepositoryPurgeConfigurationsByRepository( repositoryId );

        for ( RepositoryPurgeConfiguration repoPurge : repoPurgeConfigs )
        {
            removeFromPurgeQueue( repoPurge.getId() );
        }
    }

    public boolean removeFromPurgeQueue( int[] purgeConfigIds )
        throws ContinuumPurgeManagerException
    {
        if ( purgeConfigIds == null )
        {
            return false;
        }

        if ( purgeConfigIds.length < 1 )
        {
            return false;
        }

        List<PurgeTask> queue = getAllPurgeConfigurationsInPurgeQueue();

        List<PurgeTask> tasks = new ArrayList<PurgeTask>();

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

        if ( !tasks.isEmpty() )
        {
            return purgeQueue.removeAll( tasks );
        }

        return false;
    }

    public boolean removeFromPurgeQueue( int purgeConfigId )
        throws ContinuumPurgeManagerException
    {
        List<PurgeTask> queue = getAllPurgeConfigurationsInPurgeQueue();

        for ( PurgeTask task : queue )
        {
            if ( task != null && task.getPurgeConfigurationId() == purgeConfigId )
            {
                return purgeQueue.remove( task );
            }
        }
        return false;
=======

        if ( hasDitributedDirPurge )
        {
            for ( DistributedDirectoryPurgeConfiguration dirPurge : distributedDirPurgeList )
            {
                purgeDistributedDirectory( dirPurge );
            }
        }

        if ( hasDistributedRepoPurge )
        {
            for ( DistributedRepositoryPurgeConfiguration repoPurge : distributedRepoPurgeList )
            {
                purgeDistributedRepository( repoPurge );
            }
        }

        if ( !hasRepoPurge && !hasDirPurge && !hasDitributedDirPurge && !hasDistributedRepoPurge )
        {
            // This purge is not enable for a purge process.
            try
            {
                schedulesActivator.unactivateOrphanPurgeSchedule( schedule );
            }
            catch ( SchedulesActivationException e )
            {
                log.debug( String.format( "Can't unactivate orphan schedule '%s' for purgeConfiguration",
                                          schedule.getName() ) );
            }
        }
>>>>>>> refs/remotes/apache/trunk
    }

    public void purgeRepository( RepositoryPurgeConfiguration repoPurge )
        throws ContinuumPurgeManagerException
    {
        try
        {
            LocalRepository repository = repoPurge.getRepository();

            // do not purge if repository is in use and if repository is already in purge queue
<<<<<<< HEAD
            if ( !isRepositoryInUse( repository.getId() ) && !isInPurgeQueue( repoPurge.getId() ) )
            {
                purgeQueue.put( new PurgeTask( repoPurge.getId() ) );
=======
            if ( !taskQueueManager.isRepositoryInUse( repository.getId() ) && !taskQueueManager.isInPurgeQueue(
                repoPurge.getId() ) )
            {
                taskQueueManager.getPurgeQueue().put( new PurgeTask( repoPurge.getId() ) );
>>>>>>> refs/remotes/apache/trunk
            }
        }
        catch ( TaskQueueException e )
        {
            throw new ContinuumPurgeManagerException( "Error while enqueuing repository", e );
        }
<<<<<<< HEAD
=======
        catch ( TaskQueueManagerException e )
        {
            throw new ContinuumPurgeManagerException( e.getMessage(), e );
        }
>>>>>>> refs/remotes/apache/trunk
    }

    public void purgeDirectory( DirectoryPurgeConfiguration dirPurge )
        throws ContinuumPurgeManagerException
    {
        try
        {
            if ( "releases".equals( dirPurge.getDirectoryType() ) )
            {
                // do not purge if release in progress
<<<<<<< HEAD
                if ( !releaseInProgress() && !isInPurgeQueue( dirPurge.getId() ) )
                {
                    purgeQueue.put( new PurgeTask( dirPurge.getId() ) );
=======
                if ( !taskQueueManager.releaseInProgress() && !taskQueueManager.isInPurgeQueue( dirPurge.getId() ) )
                {
                    taskQueueManager.getPurgeQueue().put( new PurgeTask( dirPurge.getId() ) );
>>>>>>> refs/remotes/apache/trunk
                }
            }
            else if ( "buildOutput".equals( dirPurge.getDirectoryType() ) )
            {
                // do not purge if build in progress
<<<<<<< HEAD
                if ( !buildInProgress() && !isInPurgeQueue( dirPurge.getId() ) )
                {
                    purgeQueue.put( new PurgeTask( dirPurge.getId() ) );
=======
                if ( !parallelBuildsManager.isBuildInProgress() && !taskQueueManager.isInPurgeQueue(
                    dirPurge.getId() ) )
                {
                    taskQueueManager.getPurgeQueue().put( new PurgeTask( dirPurge.getId() ) );
>>>>>>> refs/remotes/apache/trunk
                }
            }

        }
        catch ( TaskQueueException e )
        {
<<<<<<< HEAD
            throw new ContinuumPurgeManagerException( "Error while enqueuing repository", e );
        }
    }

    public void contextualize( Context context )
        throws ContextException
    {
        container = (PlexusContainer) context.get( PlexusConstants.PLEXUS_KEY );
    }

    private boolean isInPurgeQueue( int purgeConfigId )
        throws ContinuumPurgeManagerException
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

    private List<PurgeTask> getAllPurgeConfigurationsInPurgeQueue()
=======
            throw new ContinuumPurgeManagerException( "Error while enqueuing directory", e );
        }
        catch ( TaskQueueManagerException e )
        {
            throw new ContinuumPurgeManagerException( e.getMessage(), e );
        }
    }

    public void purgeDistributedDirectory( DistributedDirectoryPurgeConfiguration dirPurge )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumPurgeManagerException
    {
        try
        {
<<<<<<< HEAD
            return purgeQueue.getQueueSnapshot();
        }
        catch ( TaskQueueException e )
        {
            throw new ContinuumPurgeManagerException( "Error while getting the purge configs in purge queue", e );
        }
    }

    private Task getCurrentTask( String task )
=======
            taskQueueManager.getPurgeQueue().put( new PurgeTask( dirPurge.getId() ) );
        }
        catch ( TaskQueueException e )
        {
            throw new ContinuumPurgeManagerException( "Error while enqueuing distributed directory", e );
        }
    }

    public void purgeDistributedRepository( DistributedRepositoryPurgeConfiguration repoPurgeConfig )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumPurgeManagerException
    {
        try
        {
<<<<<<< HEAD
            TaskQueueExecutor executor = (TaskQueueExecutor) container.lookup( TaskQueueExecutor.class, task );
            return executor.getCurrentTask();
        }
        catch ( ComponentLookupException e )
        {
            throw new ContinuumPurgeManagerException( "Unable to lookup current task", e );
        }
    }

    private boolean buildInProgress()
        throws ContinuumPurgeManagerException
    {
        Task task = getCurrentTask( "build-project" );

        if ( task != null && task instanceof BuildProjectTask )
        {
            return true;
        }

        return false;
    }

    private boolean releaseInProgress()
        throws ContinuumPurgeManagerException
    {
        Task task = getCurrentTask( "perform-release" );

        if ( task != null && task instanceof PerformReleaseProjectTask )
        {
            return true;
        }

        return false;
    }
=======
            taskQueueManager.getPurgeQueue().put( new PurgeTask( repoPurgeConfig.getId() ) );
        }
        catch ( TaskQueueException e )
        {
            throw new ContinuumPurgeManagerException( "Error while enqueuing distributed repository", e );
        }
    }

>>>>>>> refs/remotes/apache/trunk
}