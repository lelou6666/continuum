package org.apache.continuum.release.distributed.manager;

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
<<<<<<< HEAD
import org.apache.maven.continuum.model.project.BuildResult;
import org.codehaus.plexus.spring.PlexusInSpringTestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
=======
import org.apache.maven.continuum.PlexusSpringTestCase;
import org.apache.maven.continuum.model.project.BuildResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
>>>>>>> refs/remotes/apache/trunk

/**
 * DefaultDistributedReleaseManagerTest
 */
public class DefaultDistributedReleaseManagerTest
<<<<<<< HEAD
    extends PlexusInSpringTestCase
=======
    extends PlexusSpringTestCase
>>>>>>> refs/remotes/apache/trunk
{
    private DefaultDistributedReleaseManager distributedReleaseManager;

    private BuildResultDao buildResultDao;
<<<<<<< HEAD
    
    private BuildResult buildResult;
    
    private Mockery context;
    
    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();

        context = new JUnit3Mockery();
        context.setImposteriser( ClassImposteriser.INSTANCE );
        
        distributedReleaseManager = new DefaultDistributedReleaseManager();
        
        buildResultDao = context.mock( BuildResultDao.class );
        distributedReleaseManager.setBuildResultDao( buildResultDao );
    }
    
=======

    @Before
    public void setUp()
        throws Exception
    {
        distributedReleaseManager = new DefaultDistributedReleaseManager();
        buildResultDao = mock( BuildResultDao.class );
        distributedReleaseManager.setBuildResultDao( buildResultDao );
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testGetDefaultBuildagent()
        throws Exception
    {
        String defaultBuildagentUrl = "http://localhost:8181/continuum-buildagent/xmlrpc";
<<<<<<< HEAD
        
        buildResult = new BuildResult();
        buildResult.setBuildUrl( defaultBuildagentUrl );
        
        contextBuildResultDaoExpectations();
        
        String returnedBuildagent = distributedReleaseManager.getDefaultBuildagent( 0 );
        
        assertNotNull( returnedBuildagent );
        assertEquals( returnedBuildagent, defaultBuildagentUrl );

        context.assertIsSatisfied();
    }
    
    public void testGetDefaultBuildagentNullBuildResult()
        throws Exception
    {
        buildResult = null;
        
        contextBuildResultDaoExpectations();
        
        String returnedBuildagent = distributedReleaseManager.getDefaultBuildagent( 0 );
        
        assertNull( returnedBuildagent );

        context.assertIsSatisfied();
    }

    private void contextBuildResultDaoExpectations()
        throws Exception
    {
        context.checking( new Expectations()
        {
            {
                one( buildResultDao ).getLatestBuildResultForProject( 0 );
                will( returnValue( buildResult )  );
            }
        } );
=======

        BuildResult buildResult = new BuildResult();
        buildResult.setBuildUrl( defaultBuildagentUrl );

        when( buildResultDao.getLatestBuildResultForProject( 0 ) ).thenReturn( buildResult );

        String returnedBuildagent = distributedReleaseManager.getDefaultBuildagent( 0 );

        assertNotNull( returnedBuildagent );
        assertEquals( returnedBuildagent, defaultBuildagentUrl );
    }

    @Test
    public void testGetDefaultBuildagentNullBuildResult()
        throws Exception
    {
        BuildResult buildResult = null;

        when( buildResultDao.getLatestBuildResultForProject( 0 ) ).thenReturn( buildResult );

        String returnedBuildagent = distributedReleaseManager.getDefaultBuildagent( 0 );

        assertNull( returnedBuildagent );
>>>>>>> refs/remotes/apache/trunk
    }
}
