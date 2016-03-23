package org.apache.continuum.builder;

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

import org.apache.continuum.builder.distributed.work.BuildStatusUpdater;
import org.apache.continuum.dao.BuildResultDao;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Configuration;
import org.codehaus.plexus.component.annotations.Requirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@Component( role = BuildStatusUpdater.class, hint = "orphans" )
public class OrphanBuildStatusUpdater
    implements BuildStatusUpdater
{
    private static final Logger log = LoggerFactory.getLogger( OrphanBuildStatusUpdater.class );

    @Requirement
    private BuildResultDao buildResultDao;

    @Configuration( "24" )
    private int orphanAfterHours;

    public void setOrphanAfterHours( int orphanAfterHours )
    {
        this.orphanAfterHours = orphanAfterHours;
    }

    public void performScan()
    {
        if ( orphanAfterHours <= 0 )
        {
            log.warn( "disabled by configuration: orphan-after-hours = {}", orphanAfterHours );
            return;
        }

        try
        {
            long hourInMillis = 1000 * 60 * 60, now = System.currentTimeMillis();
            long orphanCutoff = now - ( orphanAfterHours * hourInMillis );
            log.info( "attempting to cancel results in-progress for more than {} hours", orphanAfterHours );
            Set<Integer> orphans = buildResultDao.resolveOrphanedInProgressResults( orphanCutoff );
            if ( orphans.isEmpty() )
            {
                log.info( "marked no results as canceled" );
            }
            else
            {
                log.info( "marked {} results as canceled: {}", orphans.size(), orphans );
            }

        }
        catch ( ContinuumStoreException e )
        {
            log.warn( "failed to resolve orphaned build results: ", e.getMessage() );
        }
    }
}
