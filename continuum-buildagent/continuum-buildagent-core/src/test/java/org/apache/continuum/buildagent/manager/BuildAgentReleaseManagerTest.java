package org.apache.continuum.buildagent.manager;

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
=======
import org.apache.continuum.buildagent.configuration.BuildAgentConfigurationException;
import org.apache.continuum.buildagent.configuration.BuildAgentConfigurationService;
import org.apache.continuum.buildagent.model.LocalRepository;
import org.apache.continuum.buildagent.utils.ContinuumBuildAgentUtil;
import org.apache.maven.continuum.PlexusSpringTestCase;
import org.apache.maven.continuum.release.ContinuumReleaseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

>>>>>>> refs/remotes/apache/trunk
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD

import org.apache.continuum.buildagent.configuration.BuildAgentConfigurationService;
import org.apache.continuum.buildagent.model.LocalRepository;
import org.apache.continuum.buildagent.utils.ContinuumBuildAgentUtil;
import org.apache.maven.continuum.release.ContinuumReleaseException;
import org.codehaus.plexus.spring.PlexusInSpringTestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
=======
import java.util.Properties;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
>>>>>>> refs/remotes/apache/trunk

/**
 * For the CONTINUUM-2391 tests, checking of the local repository details is in ContinuumReleaseManagerStub. An
 * exception is thrown if the set local repository in the repository map is incorrect.
 */
public class BuildAgentReleaseManagerTest
<<<<<<< HEAD
    extends PlexusInSpringTestCase
{
    private Mockery context;
=======
    extends PlexusSpringTestCase
{
    public static final String DEFAULT_NAME = "DEFAULT";

    public static final String UNKNOWN_NAME = "NON-EXISTENT";
>>>>>>> refs/remotes/apache/trunk

    private BuildAgentConfigurationService buildAgentConfigurationService;

    private DefaultBuildAgentReleaseManager releaseManager;

<<<<<<< HEAD
    protected void setUp()
        throws Exception
    {
        super.setUp();

        context = new JUnit3Mockery();
        context.setImposteriser( ClassImposteriser.INSTANCE );

        releaseManager = (DefaultBuildAgentReleaseManager) lookup( BuildAgentReleaseManager.class );

        buildAgentConfigurationService = context.mock( BuildAgentConfigurationService.class );

        releaseManager.setBuildAgentConfigurationService( buildAgentConfigurationService );
    }

    protected void tearDown()
        throws Exception
    {
        releaseManager = null;

        super.tearDown();
    }

    // CONTINUUM-2391
    public void testLocalRepositoryInReleasePrepare()
        throws Exception
    {
        final List<LocalRepository> localRepos = createLocalRepositories();
        final File workingDir = new File( getBasedir(), "target/test-classes/working-dir" );

        context.checking( new Expectations()
        {
            {
                one( buildAgentConfigurationService ).getLocalRepositories();
                will( returnValue( localRepos ) );

                one( buildAgentConfigurationService ).getWorkingDirectory( 1 );
                will( returnValue( workingDir ) );
            }
        } );

        try
        {
            releaseManager.releasePrepare( createProjectMap(), createPropertiesMap(), createReleaseVersionMap(),
=======
    @Before
    public void setUp()
        throws Exception
    {
        releaseManager = (DefaultBuildAgentReleaseManager) lookup( BuildAgentReleaseManager.class );
        buildAgentConfigurationService = mock( BuildAgentConfigurationService.class );
        releaseManager.setBuildAgentConfigurationService( buildAgentConfigurationService );

        final List<LocalRepository> localRepos = createLocalRepositories();
        final File workingDir = new File( getBasedir(), "target/test-classes/working-dir" );

        for ( LocalRepository localRepo : localRepos )
        {
            String repoName = localRepo.getName();
            when( buildAgentConfigurationService.getLocalRepositoryByName( repoName ) ).thenReturn( localRepo );
            when( buildAgentConfigurationService.getLocalRepositoryByName( repoName.toUpperCase() ) ).thenReturn(
                localRepo );
        }
        when( buildAgentConfigurationService.getLocalRepositoryByName( UNKNOWN_NAME ) ).thenThrow(
            new BuildAgentConfigurationException( "could not locate the repo" ) );

        when( buildAgentConfigurationService.getWorkingDirectory( 1 ) ).thenReturn( workingDir );
        when( buildAgentConfigurationService.getWorkingDirectory() ).thenReturn( workingDir );
    }

    @After
    public void tearDown()
        throws Exception
    {
        releaseManager = null;
    }

    // CONTINUUM-2391
    @Test
    public void testLocalRepositoryInReleasePrepare()
        throws Exception
    {
        when( buildAgentConfigurationService.getAvailableInstallations() ).thenReturn( null );
        try
        {
            releaseManager.releasePrepare( createProjectMap(), createProperties(), createReleaseVersionMap(),
>>>>>>> refs/remotes/apache/trunk
                                           createDevVersionMap(), createEnvironmentsMap(), "user" );
        }
        catch ( ContinuumReleaseException e )
        {
            fail( "An exception should not have been thrown!" );
        }
<<<<<<< HEAD
    }    

    // CONTINUUM-2391
    public void testLocalRepositoryNameMismatchedCaseInReleasePrepare()
        throws Exception
    {
        final List<LocalRepository> localRepos = createLocalRepositories();
        final File workingDir = new File( getBasedir(), "target/test-classes/working-dir" );

        context.checking( new Expectations()
        {
            {
                one( buildAgentConfigurationService ).getLocalRepositories();
                will( returnValue( localRepos ) );

                one( buildAgentConfigurationService ).getWorkingDirectory( 1 );
                will( returnValue( workingDir ) );
            }
        } );

        Map<String, Object> map = createProjectMap();

        try
        {
            releaseManager.releasePrepare( map, createPropertiesMap(), createReleaseVersionMap(),
                                           createDevVersionMap(), createEnvironmentsMap(), "user" );
=======
    }

    // CONTINUUM-2391
    @Test
    public void testLocalRepositoryNameMismatchedCaseInReleasePrepare()
        throws Exception
    {
        when( buildAgentConfigurationService.getAvailableInstallations() ).thenReturn( null );
        Map<String, Object> map = createProjectMap();
        map.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, DEFAULT_NAME );
        try
        {
            releaseManager.releasePrepare( map, createProperties(), createReleaseVersionMap(), createDevVersionMap(),
                                           createEnvironmentsMap(), "user" );
        }
        catch ( ContinuumReleaseException e )
        {
            fail( "An exception should not have been thrown!" );
        }
    }

    @Test
    public void testUnknownRepositoryNameInReleasePrepare()
        throws Exception
    {
        when( buildAgentConfigurationService.getAvailableInstallations() ).thenReturn( null );
        Map<String, Object> map = createProjectMap();
        map.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, DEFAULT_NAME );
        try
        {
            releaseManager.releasePrepare( map, createProperties(), createReleaseVersionMap(), createDevVersionMap(),
                                           createEnvironmentsMap(), "user" );
>>>>>>> refs/remotes/apache/trunk
        }
        catch ( ContinuumReleaseException e )
        {
            fail( "An exception should not have been thrown!" );
        }
    }

    // CONTINUUM-2391
<<<<<<< HEAD
=======

    @Test
>>>>>>> refs/remotes/apache/trunk
    @SuppressWarnings( "unchecked" )
    public void testLocalRepositoryInReleasePerform()
        throws Exception
    {
<<<<<<< HEAD
        final List<LocalRepository> localRepos = createLocalRepositories();
        final File workingDir = new File( getBasedir(), "target/test-classes/working-dir" );

        context.checking( new Expectations()
        {
            {
                one( buildAgentConfigurationService ).getLocalRepositories();
                will( returnValue( localRepos ) );

                one( buildAgentConfigurationService ).getWorkingDirectory();
                will( returnValue( workingDir ) );
            }
        } );

        Map repository = createRepositoryMap();
        repository.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, "DEFAULT" );

        try
        {
            releaseManager.releasePerform( "1", "clean deploy", "", true, repository, "user" );
=======
        try
        {
            releaseManager.releasePerform( "1", "clean deploy", "", true, createRepositoryMap(), "user" );
>>>>>>> refs/remotes/apache/trunk
        }
        catch ( ContinuumReleaseException e )
        {
            fail( "An exception should not have been thrown!" );
        }
    }

    // CONTINUUM-2391
<<<<<<< HEAD
    public void testLocalRepositoryNameMismatchedCaseInReleasePerform()
        throws Exception
    {
        final List<LocalRepository> localRepos = createLocalRepositories();
        final File workingDir = new File( getBasedir(), "target/test-classes/working-dir" );

        context.checking( new Expectations()
        {
            {
                one( buildAgentConfigurationService ).getLocalRepositories();
                will( returnValue( localRepos ) );

                one( buildAgentConfigurationService ).getWorkingDirectory();
                will( returnValue( workingDir ) );
            }
        } );

        try
        {
            releaseManager.releasePerform( "1", "clean deploy", "", true, createRepositoryMap(), "user" );
=======
    @Test
    public void testLocalRepositoryNameMismatchedCaseInReleasePerform()
        throws Exception
    {
        Map repoMap = createRepositoryMap();
        repoMap.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, DEFAULT_NAME );
        try
        {
            releaseManager.releasePerform( "1", "clean deploy", "", true, repoMap, "user" );
        }
        catch ( ContinuumReleaseException e )
        {
            fail( "An exception should not have been thrown!" );
        }
    }

    @Test
    public void testUnknownRepositoryNameInReleasePerform()
        throws Exception
    {
        Map repoMap = createRepositoryMap();
        repoMap.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, DEFAULT_NAME );
        try
        {
            releaseManager.releasePerform( "1", "clean deploy", "", true, repoMap, "user" );
>>>>>>> refs/remotes/apache/trunk
        }
        catch ( ContinuumReleaseException e )
        {
            fail( "An exception should not have been thrown!" );
        }
    }

    // CONTINUUM-2391
<<<<<<< HEAD
=======
    @Test
>>>>>>> refs/remotes/apache/trunk
    @SuppressWarnings( "unchecked" )
    public void testLocalRepositoryInReleasePerformFromScm()
        throws Exception
    {
<<<<<<< HEAD
        final List<LocalRepository> localRepos = createLocalRepositories();
        final File workingDir = new File( getBasedir(), "target/test-classes/working-dir" );

        context.checking( new Expectations()
        {
            {
                one( buildAgentConfigurationService ).getLocalRepositories();
                will( returnValue( localRepos ) );

                one( buildAgentConfigurationService ).getWorkingDirectory();
                will( returnValue( workingDir ) );
            }
        } );

        Map repository = new HashMap();
        repository.put( ContinuumBuildAgentUtil.KEY_USERNAME, "user" );
        repository.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, "default" );

        try
        {
            releaseManager.releasePerformFromScm(
                                                  "clean deploy",
                                                  "",
                                                  true,
                                                  repository,
                                                  "scm:svn:http://svn.example.com/repos/test-project",
                                                  "user",
=======
        Map repository = new HashMap();
        repository.put( ContinuumBuildAgentUtil.KEY_USERNAME, "user" );
        repository.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, "default" );
        try
        {
            releaseManager.releasePerformFromScm( "clean deploy", "", true, repository,
                                                  "scm:svn:http://svn.example.com/repos/test-project", "user",
>>>>>>> refs/remotes/apache/trunk
                                                  "mypasswrd",
                                                  "scm:svn:http://svn.example.com/repos/test-project/tags/test-project-1.0",
                                                  "scm:svn:http://svn.example.com/repos/test-project/tags", null,
                                                  "user" );
        }
        catch ( ContinuumReleaseException e )
        {
            fail( "An exception should not have been thrown!" );
        }
    }

    private List<LocalRepository> createLocalRepositories()
    {
        List<LocalRepository> localRepos = new ArrayList<LocalRepository>();
        LocalRepository localRepo = new LocalRepository();
        localRepo.setName( "temp" );
        localRepo.setLocation( "/tmp/.m2/repository" );
        localRepo.setLayout( "default" );

        localRepos.add( localRepo );

        localRepo = new LocalRepository();
        localRepo.setName( "default" );
        localRepo.setLocation( "/home/user/.m2/repository" );
        localRepo.setLayout( "default" );

        localRepos.add( localRepo );

        return localRepos;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    private Map<String, String> createEnvironmentsMap()
    {
        Map<String, String> environments = new HashMap<String, String>();
        environments.put( "M2_HOME", "/tmp/bin/apache-maven-2.2.1" );
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        return environments;
    }

    private Map<String, String> createDevVersionMap()
    {
        Map<String, String> devVersion = new HashMap<String, String>();
        devVersion.put( "1.1-SNAPSHOT", "1.1-SNAPSHOT" );
<<<<<<< HEAD
     
=======

>>>>>>> refs/remotes/apache/trunk
        return devVersion;
    }

    private Map<String, String> createReleaseVersionMap()
    {
        Map<String, String> releaseVersion = new HashMap<String, String>();
        releaseVersion.put( "1.0", "1.0" );
<<<<<<< HEAD
        
        return releaseVersion;
    }

    private Map<String, Object> createPropertiesMap()
    {
        Map<String, Object> properties = new HashMap<String, Object>();
=======

        return releaseVersion;
    }

    private Properties createProperties()
    {
        Properties properties = new Properties();
>>>>>>> refs/remotes/apache/trunk
        properties.put( ContinuumBuildAgentUtil.KEY_SCM_USERNAME, "scmusername" );
        properties.put( ContinuumBuildAgentUtil.KEY_SCM_PASSWORD, "scmpassword" );
        properties.put( ContinuumBuildAgentUtil.KEY_SCM_TAGBASE,
                        "scm:svn:http://svn.example.com/repos/test-project/tags" );
        properties.put( ContinuumBuildAgentUtil.KEY_PREPARE_GOALS, "clean install" );
        properties.put( ContinuumBuildAgentUtil.KEY_ARGUMENTS, "" );
        properties.put( ContinuumBuildAgentUtil.KEY_SCM_TAG, "test-project-1.0" );
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        return properties;
    }

    private Map<String, Object> createProjectMap()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, "default" );
        map.put( ContinuumBuildAgentUtil.KEY_PROJECT_ID, 1 );
        map.put( ContinuumBuildAgentUtil.KEY_GROUP_ID, "1" );
        map.put( ContinuumBuildAgentUtil.KEY_ARTIFACT_ID, "test-project" );
        map.put( ContinuumBuildAgentUtil.KEY_SCM_URL, "scm:svn:http://svn.example.com/repos/test-project/trunk" );
<<<<<<< HEAD
        
        return map;
    }
    
=======

        return map;
    }

>>>>>>> refs/remotes/apache/trunk
    @SuppressWarnings( "unchecked" )
    private Map createRepositoryMap()
    {
        Map repository = new HashMap();
        repository.put( ContinuumBuildAgentUtil.KEY_USERNAME, "user" );
        repository.put( ContinuumBuildAgentUtil.KEY_LOCAL_REPOSITORY_NAME, "default" );
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        return repository;
    }
}
