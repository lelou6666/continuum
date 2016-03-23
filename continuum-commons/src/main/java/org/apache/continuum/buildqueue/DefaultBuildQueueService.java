package org.apache.continuum.buildqueue;

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
import java.util.List;

=======
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.dao.BuildQueueDao;
import org.apache.continuum.dao.ScheduleDao;
import org.apache.maven.continuum.model.project.BuildQueue;
import org.apache.maven.continuum.model.project.Schedule;
import org.apache.maven.continuum.store.ContinuumStoreException;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> refs/remotes/apache/trunk
import javax.annotation.Resource;

/**
 * DefaultBuildQueueService
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> refs/remotes/apache/trunk
 * @author <a href="mailto:oching@apache.org">Maria Odea Ching</a>
 */
public class DefaultBuildQueueService
    implements BuildQueueService
{
    @Resource
    private BuildQueueDao buildQueueDao;
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    @Resource
    private ScheduleDao scheduleDao;

    public BuildQueue addBuildQueue( BuildQueue buildQueue )
        throws BuildQueueServiceException
<<<<<<< HEAD
    {        
        try
        {
            return buildQueueDao.addBuildQueue( buildQueue );
        } 
=======
    {
        try
        {
            return buildQueueDao.addBuildQueue( buildQueue );
        }
>>>>>>> refs/remotes/apache/trunk
        catch ( ContinuumStoreException e )
        {
            throw new BuildQueueServiceException( e );
        }
    }

    public List<BuildQueue> getAllBuildQueues()
        throws BuildQueueServiceException
    {
        try
        {
            return buildQueueDao.getAllBuildQueues();
        }
        catch ( ContinuumStoreException e )
        {
            throw new BuildQueueServiceException( e );
        }
    }

    public BuildQueue getBuildQueue( int buildQueueId )
        throws BuildQueueServiceException
    {
        try
        {
            return buildQueueDao.getBuildQueue( buildQueueId );
        }
        catch ( ContinuumStoreException e )
        {
            throw new BuildQueueServiceException( e );
        }
    }

    public BuildQueue getBuildQueueByName( String buildQueueName )
        throws BuildQueueServiceException
    {
        try
        {
            return buildQueueDao.getBuildQueueByName( buildQueueName );
        }
        catch ( ContinuumStoreException e )
        {
            throw new BuildQueueServiceException( e );
        }
    }

    public void removeBuildQueue( BuildQueue buildQueue )
        throws BuildQueueServiceException
<<<<<<< HEAD
    {   
=======
    {
>>>>>>> refs/remotes/apache/trunk
        try
        {
            // detach from schedule(s) first
            List<Schedule> schedules = scheduleDao.getAllSchedulesByName();
<<<<<<< HEAD
            for( Schedule schedule : schedules )
            {
                List<BuildQueue> buildQueues = schedule.getBuildQueues();
                if( buildQueues.contains( buildQueue ) )
=======
            for ( Schedule schedule : schedules )
            {
                List<BuildQueue> buildQueues = schedule.getBuildQueues();
                if ( buildQueues.contains( buildQueue ) )
>>>>>>> refs/remotes/apache/trunk
                {
                    buildQueues.remove( buildQueue );
                }
                schedule.setBuildQueues( buildQueues );
<<<<<<< HEAD
                
                scheduleDao.updateSchedule( schedule );
            }        
        
=======

                scheduleDao.updateSchedule( schedule );
            }

>>>>>>> refs/remotes/apache/trunk
            buildQueueDao.removeBuildQueue( buildQueue );
        }
        catch ( ContinuumStoreException e )
        {
            throw new BuildQueueServiceException( e );
        }
    }

    public BuildQueue updateBuildQueue( BuildQueue buildQueue )
        throws BuildQueueServiceException
    {
        try
        {
            return buildQueueDao.storeBuildQueue( buildQueue );
        }
        catch ( ContinuumStoreException e )
        {
            throw new BuildQueueServiceException( e );
        }
    }

    public BuildQueueDao getBuildQueueDao()
    {
        return buildQueueDao;
    }

    public void setBuildQueueDao( BuildQueueDao buildQueueDao )
    {
        this.buildQueueDao = buildQueueDao;
    }

    public ScheduleDao getScheduleDao()
    {
        return scheduleDao;
    }

    public void setScheduleDao( ScheduleDao scheduleDao )
    {
        this.scheduleDao = scheduleDao;
    }

}
