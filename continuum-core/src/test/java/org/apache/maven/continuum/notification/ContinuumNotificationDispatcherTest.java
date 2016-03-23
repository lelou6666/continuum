package org.apache.maven.continuum.notification;

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

import org.apache.continuum.dao.BuildResultDao;
import org.apache.maven.continuum.AbstractContinuumTest;
import org.apache.maven.continuum.model.project.BuildResult;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.project.ContinuumProjectState;
<<<<<<< HEAD
=======
import org.junit.Before;
import org.junit.Test;
>>>>>>> refs/remotes/apache/trunk

/**
 * @author <a href="mailto:trygvis@inamo.no">Trygve Laugst&oslash;l</a>
 */
public class ContinuumNotificationDispatcherTest
    extends AbstractContinuumTest
{
    private BuildResultDao buildResultDao;

<<<<<<< HEAD
    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();
        buildResultDao = (BuildResultDao) lookup( BuildResultDao.class.getName() );
    }

    public void testNotificationDispatcher()
=======
    @Before
    public void setUp()
>>>>>>> refs/remotes/apache/trunk
        throws Exception
    {
        buildResultDao = lookup( BuildResultDao.class );
    }

<<<<<<< HEAD
=======
    @Test
    public void testNotificationDispatcher()
        throws Exception
    {
        ContinuumNotificationDispatcher notificationDispatcher = lookup( ContinuumNotificationDispatcher.class );

>>>>>>> refs/remotes/apache/trunk
        Project project = addProject( "Notification Dispatcher Test Project" );

        project = getProjectDao().getProjectWithBuildDetails( project.getId() );

        BuildResult build = new BuildResult();

        build.setStartTime( System.currentTimeMillis() );

        build.setState( ContinuumProjectState.BUILDING );

        build.setTrigger( ContinuumProjectState.TRIGGER_SCHEDULED );

        buildResultDao.addBuildResult( project, build );

        build = buildResultDao.getBuildResult( build.getId() );

        notificationDispatcher.buildComplete( project, null, build );
    }
}
