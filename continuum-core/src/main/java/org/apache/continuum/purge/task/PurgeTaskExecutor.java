package org.apache.continuum.purge.task;

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

import org.apache.continuum.model.repository.AbstractPurgeConfiguration;
import org.apache.continuum.model.repository.DirectoryPurgeConfiguration;
import org.apache.continuum.model.repository.DistributedDirectoryPurgeConfiguration;
import org.apache.continuum.model.repository.DistributedRepositoryPurgeConfiguration;
import org.apache.continuum.model.repository.RepositoryPurgeConfiguration;
import org.apache.continuum.purge.PurgeConfigurationService;
import org.apache.continuum.purge.controller.PurgeController;
import org.apache.continuum.purge.executor.ContinuumPurgeExecutorException;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.execution.TaskExecutionException;
import org.codehaus.plexus.taskqueue.execution.TaskExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maria Catherine Tan
 */
@Component( role = org.codehaus.plexus.taskqueue.execution.TaskExecutor.class, hint = "purge" )
public class PurgeTaskExecutor
    implements TaskExecutor, Contextualizable
{

    static final Map<Class, String> configControllerHints = new HashMap<Class, String>();

    static
    {
        configControllerHints.put( RepositoryPurgeConfiguration.class, "purge-repository" );
        configControllerHints.put( DirectoryPurgeConfiguration.class, "purge-directory" );
        configControllerHints.put( DistributedRepositoryPurgeConfiguration.class, "purge-distributed-repository" );
        configControllerHints.put( DistributedDirectoryPurgeConfiguration.class, "purge-distributed-directory" );
    }

    @Requirement
    private PurgeConfigurationService purgeConfigurationService;

    private PlexusContainer container;

    public void executeTask( Task task )
        throws TaskExecutionException
    {
        PurgeTask purgeTask = (PurgeTask) task;

        AbstractPurgeConfiguration purgeConfig = purgeConfigurationService.getPurgeConfiguration(
            purgeTask.getPurgeConfigurationId() );

        try
        {
            if ( purgeConfig != null )
            {
                performPurge( purgeConfig );
            }
        }
        catch ( ComponentLookupException e )
        {
            throw new TaskExecutionException( "Error while executing purge task", e );
        }
        catch ( ContinuumPurgeExecutorException e )
        {
            throw new TaskExecutionException( "Error while executing purge task", e );
        }
    }

    private void performPurge( AbstractPurgeConfiguration config )
        throws ContinuumPurgeExecutorException, ComponentLookupException
    {
        getController( config.getClass() ).purge( config );
    }

    private PurgeController getController( Class configClass )
        throws ComponentLookupException
    {
        String controllerHint = configControllerHints.get( configClass );
        return (PurgeController) container.lookup( PurgeController.ROLE, controllerHint );
    }

    public void contextualize( Context context )
        throws ContextException
    {
        container = (PlexusContainer) context.get( PlexusConstants.PLEXUS_KEY );
    }
}