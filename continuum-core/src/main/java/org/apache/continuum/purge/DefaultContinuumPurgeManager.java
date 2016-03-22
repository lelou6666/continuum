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
import org.apache.continuum.dao.ProjectDao;
=======
import org.apache.continuum.buildmanager.BuildsManager;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.model.repository.DirectoryPurgeConfiguration;
import org.apache.continuum.model.repository.DistributedDirectoryPurgeConfiguration;
import org.apache.continuum.model.repository.DistributedRepositoryPurgeConfiguration;
import org.apache.continuum.model.repository.LocalRepository;
import org.apache.continuum.model.repository.RepositoryPurgeConfiguration;
import org.apache.continuum.purge.task.PurgeTask;
import org.apache.continuum.taskqueue.manager.TaskQueueManager;
import org.apache.continuum.taskqueue.manager.TaskQueueManagerException;
<<<<<<< HEAD
import org.apache.maven.continuum.model.project.Schedule;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.taskqueue.TaskQueueException;
=======
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
 * @since 25 jul 07
 */
@Component( role = org.apache.continuum.purge.ContinuumPurgeManager.class, hint = "default" )
public class DefaultContinuumPurgeManager
    implements ContinuumPurgeManager
{
<<<<<<< HEAD
    /**
     * @plexus.requirement
     */
    private ProjectDao projectDao;
/*
    /**
     * @plexus.requirement role-hint="purge"
     */
//    private TaskQueue purgeQueue;
=======
    private static final Logger log = LoggerFactory.getLogger( DefaultContinuumPurgeManager.class );

    @Requirement
    private SchedulesActivator schedulesActivator;
>>>>>>> refs/remotes/apache/trunk

    @Requirement
    private PurgeConfigurationService purgeConfigurationService;

<<<<<<< HEAD
    /**
     * @plexus.requirement
     */
    private TaskQueueManager taskQueueManager;
    
=======
    @Requirement
    private TaskQueueManager taskQueueManager;

    @Requirement( hint = "parallel" )
    private BuildsManager parallelBuildsManager;

>>>>>>> refs/remotes/apache/trunk
    public void purge( Schedule schedule )
        throws ContinuumPurgeManagerException
    {
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
        {
            for ( RepositoryPurgeConfiguration repoPurge : repoPurgeList )
            {
                purgeRepository( repoPurge );
            }
        }

        if ( hasDirPurge )
        {
            for ( DirectoryPurgeConfiguration dirPurge : dirPurgeList )
            {
                purgeDirectory( dirPurge );
            }
        }
<<<<<<< HEAD
    }
/*
    public boolean isRepositoryInPurgeQueue( int repositoryId )
        throws ContinuumPurgeManagerException
    {
        List<RepositoryPurgeConfiguration> repoPurgeConfigs =
            purgeConfigurationService.getRepositoryPurgeConfigurationsByRepository( repositoryId );

        for ( RepositoryPurgeConfiguration repoPurge : repoPurgeConfigs )
        {
            try
            {
                if ( taskQueueManager.isInPurgeQueue( repoPurge.getId() ) )
                {
                    return true;
                }
            }
            catch ( TaskQueueManagerException e )
            {
                throw new ContinuumPurgeManagerException( e.getMessage(), e );
=======

        if ( hasDitributedDirPurge )
        {
            for ( DistributedDirectoryPurgeConfiguration dirPurge : distributedDirPurgeList )
            {
                purgeDistributedDirectory( dirPurge );
>>>>>>> refs/remotes/apache/trunk
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
    }
*/
    public void purgeRepository( RepositoryPurgeConfiguration repoPurge )
        throws ContinuumPurgeManagerException
    {
        try
        {
            LocalRepository repository = repoPurge.getRepository();

            // do not purge if repository is in use and if repository is already in purge queue
<<<<<<< HEAD
            if ( !taskQueueManager.isRepositoryInUse( repository.getId() ) && 
                 !taskQueueManager.isInPurgeQueue( repoPurge.getId() ) )
=======
            if ( !taskQueueManager.isRepositoryInUse( repository.getId() ) && !taskQueueManager.isInPurgeQueue(
                repoPurge.getId() ) )
>>>>>>> refs/remotes/apache/trunk
            {
                taskQueueManager.getPurgeQueue().put( new PurgeTask( repoPurge.getId() ) );
            }
        }
        catch ( TaskQueueException e )
        {
            throw new ContinuumPurgeManagerException( "Error while enqueuing repository", e );
        }
        catch ( TaskQueueManagerException e )
        {
            throw new ContinuumPurgeManagerException( e.getMessage(), e );
        }
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
                if ( !taskQueueManager.releaseInProgress() && 
                     !taskQueueManager.isInPurgeQueue( dirPurge.getId() ) )
=======
                if ( !taskQueueManager.releaseInProgress() && !taskQueueManager.isInPurgeQueue( dirPurge.getId() ) )
>>>>>>> refs/remotes/apache/trunk
                {
                    taskQueueManager.getPurgeQueue().put( new PurgeTask( dirPurge.getId() ) );
                }
            }
            else if ( "buildOutput".equals( dirPurge.getDirectoryType() ) )
            {
                // do not purge if build in progress
<<<<<<< HEAD
                if ( !taskQueueManager.buildInProgress() && 
                     !taskQueueManager.isInPurgeQueue( dirPurge.getId() ) )
=======
                if ( !parallelBuildsManager.isBuildInProgress() && !taskQueueManager.isInPurgeQueue(
                    dirPurge.getId() ) )
>>>>>>> refs/remotes/apache/trunk
                {
                    taskQueueManager.getPurgeQueue().put( new PurgeTask( dirPurge.getId() ) );
                }
            }

        }
        catch ( TaskQueueException e )
        {
            throw new ContinuumPurgeManagerException( "Error while enqueuing directory", e );
        }
        catch ( TaskQueueManagerException e )
<<<<<<< HEAD
        {
            throw new ContinuumPurgeManagerException( e.getMessage(), e );
        }
    }

/*
    private boolean isInPurgeQueue( int purgeConfigId )
        throws ContinuumPurgeManagerException
    {
        List<PurgeTask> queue = getAllPurgeConfigurationsInPurgeQueue();

        for ( PurgeTask task : queue )
=======
>>>>>>> refs/remotes/apache/trunk
        {
            throw new ContinuumPurgeManagerException( e.getMessage(), e );
        }
    }

    public void purgeDistributedDirectory( DistributedDirectoryPurgeConfiguration dirPurge )
        throws ContinuumPurgeManagerException
    {
        try
        {
            taskQueueManager.getPurgeQueue().put( new PurgeTask( dirPurge.getId() ) );
        }
        catch ( TaskQueueException e )
        {
            throw new ContinuumPurgeManagerException( "Error while enqueuing distributed directory", e );
        }
    }

    public void purgeDistributedRepository( DistributedRepositoryPurgeConfiguration repoPurgeConfig )
        throws ContinuumPurgeManagerException
    {
        try
        {
            taskQueueManager.getPurgeQueue().put( new PurgeTask( repoPurgeConfig.getId() ) );
        }
        catch ( TaskQueueException e )
        {
            throw new ContinuumPurgeManagerException( "Error while enqueuing distributed repository", e );
        }
    }

<<<<<<< HEAD
    private boolean releaseInProgress()
        throws ContinuumPurgeManagerException
    {
        Task task = getCurrentTask( "perform-release" );

        if ( task != null && task instanceof PerformReleaseProjectTask )
        {
            return true;
        }

        return false;
    }*/
=======
>>>>>>> refs/remotes/apache/trunk
}