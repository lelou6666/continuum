package org.apache.continuum.purge.controller;

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
import org.apache.continuum.purge.executor.ContinuumPurgeExecutor;
import org.apache.continuum.purge.executor.ContinuumPurgeExecutorException;
<<<<<<< HEAD:continuum-purge/src/main/java/org/apache/continuum/purge/controller/DirectoryPurgeController.java
import org.apache.continuum.purge.executor.DaysOldDirectoryPurgeExecutor;
import org.apache.continuum.purge.executor.RetentionCountDirectoryPurgeExecutor;
=======
import org.apache.continuum.purge.executor.dir.DirectoryPurgeExecutorFactory;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
>>>>>>> refs/remotes/apache/trunk:continuum-core/src/main/java/org/apache/continuum/purge/controller/DirectoryPurgeController.java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DirectoryPurgeController
 *
 * @author Maria Catherine Tan
 */
@Component( role = org.apache.continuum.purge.controller.PurgeController.class, hint = "purge-directory" )
public class DirectoryPurgeController
    implements PurgeController
{
<<<<<<< HEAD:continuum-purge/src/main/java/org/apache/continuum/purge/controller/DirectoryPurgeController.java
    private Logger log = LoggerFactory.getLogger( DirectoryPurgeController.class );

    private ContinuumPurgeExecutor purgeExecutor;
=======
    private static final Logger log = LoggerFactory.getLogger( DirectoryPurgeController.class );
>>>>>>> refs/remotes/apache/trunk:continuum-core/src/main/java/org/apache/continuum/purge/controller/DirectoryPurgeController.java

    @Requirement
    private DirectoryPurgeExecutorFactory executorFactory;

    public void purge( AbstractPurgeConfiguration config )
    {
        DirectoryPurgeConfiguration dirPurge = (DirectoryPurgeConfiguration) config;
        String path = dirPurge.getLocation();
        ContinuumPurgeExecutor executor = executorFactory.create( dirPurge.isDeleteAll(), dirPurge.getDaysOlder(),
                                                                  dirPurge.getRetentionCount(),
                                                                  dirPurge.getDirectoryType() );
        try
        {
            log.info( "purging directory '{}'", path );
            executor.purge( path );
            log.info( "purge complete '{}'", path );
        }
        catch ( ContinuumPurgeExecutorException e )
        {
            log.error( e.getMessage(), e );
<<<<<<< HEAD:continuum-purge/src/main/java/org/apache/continuum/purge/controller/DirectoryPurgeController.java
        }
    }

    public void initializeExecutors( AbstractPurgeConfiguration purgeConfig )
        throws ContinuumPurgeExecutorException
    {
        DirectoryPurgeConfiguration dirPurge = (DirectoryPurgeConfiguration) purgeConfig;

        if ( dirPurge.isDeleteAll() )
        {
            purgeExecutor = new CleanAllPurgeExecutor( dirPurge.getDirectoryType() );
        }
        else if ( dirPurge.getDaysOlder() > 0 )
        {
            purgeExecutor = new DaysOldDirectoryPurgeExecutor( dirPurge.getDaysOlder(), dirPurge.getRetentionCount(),
                                                               dirPurge.getDirectoryType() );
        }
        else
        {
            purgeExecutor =
                new RetentionCountDirectoryPurgeExecutor( dirPurge.getRetentionCount(), dirPurge.getDirectoryType() );
=======
>>>>>>> refs/remotes/apache/trunk:continuum-core/src/main/java/org/apache/continuum/purge/controller/DirectoryPurgeController.java
        }
    }
}

