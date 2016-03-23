package org.apache.maven.continuum.release.executors;

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

import org.apache.continuum.release.config.ContinuumReleaseDescriptor;
import org.apache.continuum.utils.file.FileSystemManager;
import org.apache.maven.continuum.PlexusSpringTestCase;
import org.apache.maven.continuum.release.ContinuumReleaseManager;
import org.apache.maven.continuum.release.tasks.PerformReleaseProjectTask;
import org.apache.maven.continuum.release.tasks.PrepareReleaseProjectTask;
import org.apache.maven.continuum.release.tasks.RollbackReleaseProjectTask;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.manager.NoSuchScmProviderException;
import org.apache.maven.scm.manager.ScmManager;
import org.apache.maven.scm.repository.ScmRepository;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.shared.release.ReleaseResult;
import org.apache.maven.shared.release.config.ReleaseDescriptor;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.execution.TaskExecutionException;
import org.codehaus.plexus.taskqueue.execution.TaskExecutor;
import org.codehaus.plexus.util.IOUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author Edwin Punzalan
 */
public class ReleaseTaskExecutorTest
    extends PlexusSpringTestCase
{
    private ScmManager scmManager;

    private TaskExecutor prepareExec;

    private TaskExecutor performExec;

    private TaskExecutor rollbackExec;

    private ContinuumReleaseManager releaseManager;

    private FileSystemManager fsManager;

    @Before
    public void setUp()
        throws Exception
    {
        if ( scmManager == null )
        {
            scmManager = lookup( ScmManager.class );
        }

        if ( prepareExec == null )
        {
            prepareExec = lookup( TaskExecutor.class, "prepare-release" );
        }

        if ( performExec == null )
        {
            performExec = lookup( TaskExecutor.class, "perform-release" );
        }

        if ( rollbackExec == null )
        {
            rollbackExec = lookup( TaskExecutor.class, "rollback-release" );
        }

        if ( releaseManager == null )
        {
            releaseManager = lookup( ContinuumReleaseManager.class );
        }

        if ( fsManager == null )
        {
            fsManager = lookup( FileSystemManager.class );
        }

        File scmPath = new File( getBasedir(), "target/scm-src" ).getAbsoluteFile();
        File scmTargetPath = new File( getBasedir(), "target/scm-test" ).getAbsoluteFile();
        fsManager.copyDir( scmPath, scmTargetPath );
    }

    public void releaseSimpleProject()
        throws Exception
    {
        String scmPath = new File( getBasedir(), "target/scm-test" ).getAbsolutePath().replace( '\\', '/' );
        File workDir = new File( getBasedir(), "target/test-classes/work-dir" );
        fsManager.removeDir( workDir );
        File testDir = new File( getBasedir(), "target/test-classes/test-dir" );
        fsManager.removeDir( testDir );

        ContinuumReleaseDescriptor descriptor = new ContinuumReleaseDescriptor();
        descriptor.setInteractive( false );
        descriptor.setScmSourceUrl( "scm:svn:file://localhost/" + scmPath + "/trunk" );
        descriptor.setWorkingDirectory( workDir.getAbsolutePath() );

        ScmRepository repository = getScmRepositorty( descriptor.getScmSourceUrl() );
        ScmFileSet fileSet = new ScmFileSet( workDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        String pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test dev version", pom.indexOf( "<version>1.0-SNAPSHOT</version>" ) > 0 );

        doPrepareWithNoError( descriptor );

        pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test version increment", pom.indexOf( "<version>1.1-SNAPSHOT</version>" ) > 0 );

        repository = getScmRepositorty( "scm:svn:file://localhost/" + scmPath + "/tags/test-artifact-1.0" );
        fileSet = new ScmFileSet( testDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        pom = fsManager.fileContents( new File( testDir, "pom.xml" ) );
        assertTrue( "Test released version", pom.indexOf( "<version>1.0</version>" ) > 0 );
    }

    @Test
    public void testReleases()
        throws Exception
    {
        releaseSimpleProject();
        releaseAndRollbackProject();
        releaseSimpleProjectWithNextVersion();
        releasePerformWithExecutableInDescriptor();
        releaseProjectWithDependencyOfCustomPackagingType();
        releaseProjectWithProfile();
    }

    public void releaseSimpleProjectWithNextVersion()
        throws Exception
    {
        String scmPath = new File( getBasedir(), "target/scm-test" ).getAbsolutePath().replace( '\\', '/' );
        File workDir = new File( getBasedir(), "target/test-classes/work-dir" );
        fsManager.removeDir( workDir );
        File testDir = new File( getBasedir(), "target/test-classes/test-dir" );
        fsManager.removeDir( testDir );

        ContinuumReleaseDescriptor descriptor = new ContinuumReleaseDescriptor();
        descriptor.setInteractive( false );
        descriptor.setScmSourceUrl( "scm:svn:file://localhost/" + scmPath + "/trunk" );
        descriptor.setWorkingDirectory( workDir.getAbsolutePath() );
        descriptor.mapReleaseVersion( "test-group:test-artifact", "2.0" );
        descriptor.mapDevelopmentVersion( "test-group:test-artifact", "2.1-SNAPSHOT" );

        ScmRepository repository = getScmRepositorty( descriptor.getScmSourceUrl() );
        ScmFileSet fileSet = new ScmFileSet( workDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        String pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test dev version", pom.indexOf( "<version>1.1-SNAPSHOT</version>" ) > 0 );

        doPrepareWithNoError( descriptor );

        pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test version increment", pom.indexOf( "<version>2.1-SNAPSHOT</version>" ) > 0 );

        repository = getScmRepositorty( "scm:svn:file://localhost/" + scmPath + "/tags/test-artifact-2.0" );
        fileSet = new ScmFileSet( testDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        pom = fsManager.fileContents( new File( testDir, "pom.xml" ) );
        assertTrue( "Test released version", pom.indexOf( "<version>2.0</version>" ) > 0 );

/* CONTINUUM-2559
        performExec.executeTask(
            getPerformTask( "testRelease", descriptor, new File( getBasedir(), "target/test-classes/build-dir" ) ) );

        ReleaseResult result = (ReleaseResult) releaseManager.getReleaseResults().get( "testRelease" );
        if ( result.getResultCode() != ReleaseResult.SUCCESS )
        {
            fail( "Error in release:perform. Release output follows:\n" + result.getOutput() );
        }
*/
    }

    public void releaseAndRollbackProject()
        throws Exception
    {
        String scmPath = new File( getBasedir(), "target/scm-test" ).getAbsolutePath().replace( '\\', '/' );
        File workDir = new File( getBasedir(), "target/test-classes/work-dir" );
        fsManager.removeDir( workDir );
        File testDir = new File( getBasedir(), "target/test-classes/test-dir" );
        fsManager.removeDir( testDir );

        ContinuumReleaseDescriptor descriptor = new ContinuumReleaseDescriptor();
        descriptor.setInteractive( false );
        descriptor.setScmSourceUrl( "scm:svn:file://localhost/" + scmPath + "/trunk" );
        descriptor.setWorkingDirectory( workDir.getAbsolutePath() );

        ScmRepository repository = getScmRepositorty( descriptor.getScmSourceUrl() );
        ScmFileSet fileSet = new ScmFileSet( workDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        String pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test dev version", pom.indexOf( "<version>1.1-SNAPSHOT</version>" ) > 0 );

        doPrepareWithNoError( descriptor );

        pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test version increment", pom.indexOf( "<version>1.2-SNAPSHOT</version>" ) > 0 );

        repository = getScmRepositorty( "scm:svn:file://localhost/" + scmPath + "/tags/test-artifact-1.1" );
        fileSet = new ScmFileSet( testDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        pom = fsManager.fileContents( new File( testDir, "pom.xml" ) );
        assertTrue( "Test released version", pom.indexOf( "<version>1.1</version>" ) > 0 );

        rollbackExec.executeTask( new RollbackReleaseProjectTask( "testRelease", descriptor, null ) );

        pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test rollback version", pom.indexOf( "<version>1.1-SNAPSHOT</version>" ) > 0 );

        assertFalse( "Test that release.properties has been cleaned", new File( workDir,
                                                                                "release.properties" ).exists() );
        assertFalse( "Test that backup file has been cleaned", new File( workDir, "pom.xml.releaseBackup" ).exists() );

        //@todo when implemented already, check if tag was also removed
    }

    public void releasePerformWithExecutableInDescriptor()
        throws Exception
    {
        String scmPath = new File( getBasedir(), "target/scm-test" ).getAbsolutePath().replace( '\\', '/' );
        File workDir = new File( getBasedir(), "target/test-classes/work-dir" );
        fsManager.removeDir( workDir );
        File testDir = new File( getBasedir(), "target/test-classes/test-dir" );
        fsManager.removeDir( testDir );

        ContinuumReleaseDescriptor descriptor = new ContinuumReleaseDescriptor();
        descriptor.setInteractive( false );
        descriptor.setScmSourceUrl( "scm:svn:file://localhost/" + scmPath + "/trunk" );
        descriptor.setWorkingDirectory( workDir.getAbsolutePath() );

        ScmRepository repository = getScmRepositorty( descriptor.getScmSourceUrl() );
        ScmFileSet fileSet = new ScmFileSet( workDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        String pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test dev version", pom.indexOf( "<version>2.1-SNAPSHOT</version>" ) > 0 );

        doPrepareWithNoError( descriptor );

        pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test version increment", pom.indexOf( "<version>2.2-SNAPSHOT</version>" ) > 0 );

        repository = getScmRepositorty( "scm:svn:file://localhost/" + scmPath + "/tags/test-artifact-2.1" );
        fileSet = new ScmFileSet( testDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        pom = fsManager.fileContents( new File( testDir, "pom.xml" ) );
        assertTrue( "Test released version", pom.indexOf( "<version>2.1</version>" ) > 0 );

        File file = new File( descriptor.getWorkingDirectory(), "release.properties" );
        assertTrue( "release.properties file does not exist", file.exists() );

        Properties properties = new Properties();

        InputStream inStream = null;
        OutputStream outStream = null;

        try
        {
            inStream = new FileInputStream( file );

            properties.load( inStream );

            properties.setProperty( "build.executable", "test/executable/mvn" );

            outStream = new FileOutputStream( file );

            properties.store( outStream, "release configuration" );
        }
        finally
        {
            IOUtil.close( inStream );
        }

        performExec.executeTask( getPerformTask( "testRelease", descriptor, new File( getBasedir(),
                                                                                      "target/test-classes/build-dir" ) ) );

        ReleaseResult result = (ReleaseResult) releaseManager.getReleaseResults().get( "testRelease" );

        if ( !result.getOutput().replace( "\\", "/" ).contains( "test/executable/mvn" ) )
        {
            fail( "Error in release:perform. Missing executable" );
        }
    }

    // CONTINUUM-1814
    public void releaseProjectWithDependencyOfCustomPackagingType()
        throws Exception
    {
        String scmPath = new File( getBasedir(), "target/scm-test/continuum-1814" ).getAbsolutePath().replace( '\\',
                                                                                                               '/' );
        File workDir = new File( getBasedir(), "target/test-classes/continuum-1814" );
        fsManager.removeDir( workDir );
        File testDir = new File( getBasedir(), "target/test-classes/test-dir" );
        fsManager.removeDir( testDir );

        ContinuumReleaseDescriptor descriptor = new ContinuumReleaseDescriptor();
        descriptor.setInteractive( false );
        descriptor.setScmSourceUrl( "scm:svn:file://localhost/" + scmPath + "/trunk" );
        descriptor.setWorkingDirectory( workDir.getAbsolutePath() );

        ScmRepository repository = getScmRepositorty( descriptor.getScmSourceUrl() );
        ScmFileSet fileSet = new ScmFileSet( workDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        String pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test dev version", pom.indexOf( "<version>1.6-SNAPSHOT</version>" ) > 0 );

        doPrepareWithNoError( descriptor );

        pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test version increment", pom.indexOf( "<version>1.7-SNAPSHOT</version>" ) > 0 );

        repository = getScmRepositorty( "scm:svn:file://localhost/" + scmPath + "/tags/continuum-1814-1.6" );
        fileSet = new ScmFileSet( testDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        pom = fsManager.fileContents( new File( testDir, "pom.xml" ) );
        assertTrue( "Test released version", pom.indexOf( "<version>1.6</version>" ) > 0 );

/* CONTINUUM-2559
        performExec.executeTask(
                getPerformTask( "testRelease", descriptor, new File( getBasedir(), "target/test-classes/build-dir" ) ) );

        ReleaseResult result = (ReleaseResult) releaseManager.getReleaseResults().get( "testRelease" );
        if ( result.getResultCode() != ReleaseResult.SUCCESS )
        {
            fail( "Error in release:perform. Release output follows:\n" + result.getOutput() );
        }
*/
    }

    // CONTINUUM-2610
    public void releaseProjectWithProfile()
        throws Exception
    {
        String scmPath = new File( getBasedir(), "target/scm-test/continuum-2610" ).getAbsolutePath().replace( '\\',
                                                                                                               '/' );
        File workDir = new File( getBasedir(), "target/test-classes/continuum-2610" );
        fsManager.removeDir( workDir );
        File testDir = new File( getBasedir(), "target/test-classes/test-dir" );
        fsManager.removeDir( testDir );

        ContinuumReleaseDescriptor descriptor = new ContinuumReleaseDescriptor();
        descriptor.setInteractive( false );
        descriptor.setScmSourceUrl( "scm:svn:file://localhost/" + scmPath + "/trunk" );
        descriptor.setWorkingDirectory( workDir.getAbsolutePath() );
        descriptor.setAdditionalArguments( "-Pall" );

        ScmRepository repository = getScmRepositorty( descriptor.getScmSourceUrl() );
        ScmFileSet fileSet = new ScmFileSet( workDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        String pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test root dev version", pom.indexOf( "<version>1.0-SNAPSHOT</version>" ) > 0 );
        String moduleAPom = fsManager.fileContents( new File( workDir, "module-A/pom.xml" ) );
        assertTrue( "Test module A dev version", moduleAPom.indexOf( "<version>1.0-SNAPSHOT</version>" ) > 0 );
        String moduleBPom = fsManager.fileContents( new File( workDir, "module-B/pom.xml" ) );
        assertTrue( "Test module B dev version", moduleBPom.indexOf( "<version>1.0-SNAPSHOT</version>" ) > 0 );

        doPrepareWithNoError( descriptor );

        pom = fsManager.fileContents( new File( workDir, "pom.xml" ) );
        assertTrue( "Test root version increment", pom.indexOf( "<version>1.1-SNAPSHOT</version>" ) > 0 );
        moduleAPom = fsManager.fileContents( new File( workDir, "module-A/pom.xml" ) );
        assertTrue( "Test module A version increment", moduleAPom.indexOf( "<version>1.1-SNAPSHOT</version>" ) > 0 );
        moduleBPom = fsManager.fileContents( new File( workDir, "module-B/pom.xml" ) );
        assertTrue( "Test module B version increment", moduleBPom.indexOf( "<version>1.1-SNAPSHOT</version>" ) > 0 );

        repository = getScmRepositorty( "scm:svn:file://localhost/" + scmPath + "/tags/continuum-2610-1.0" );
        fileSet = new ScmFileSet( testDir );
        scmManager.getProviderByRepository( repository ).checkOut( repository, fileSet, (ScmVersion) null );

        pom = fsManager.fileContents( new File( testDir, "pom.xml" ) );
        assertTrue( "Test root released version", pom.indexOf( "<version>1.0</version>" ) > 0 );
        moduleAPom = fsManager.fileContents( new File( testDir, "module-A/pom.xml" ) );
        assertTrue( "Test module A released version", moduleAPom.indexOf( "<version>1.0</version>" ) > 0 );
        moduleBPom = fsManager.fileContents( new File( testDir, "module-B/pom.xml" ) );
        assertTrue( "Test module B released version", moduleBPom.indexOf( "<version>1.0</version>" ) > 0 );
    }

    private void doPrepareWithNoError( ReleaseDescriptor descriptor )
        throws TaskExecutionException
    {
        prepareExec.executeTask( getPrepareTask( "testRelease", descriptor ) );

        ReleaseResult result = (ReleaseResult) releaseManager.getReleaseResults().get( "testRelease" );
        if ( result.getResultCode() != ReleaseResult.SUCCESS )
        {
            fail( "Error in release:prepare. Release output follows:\n" + result.getOutput() );
        }
    }

    private Task getPrepareTask( String releaseId, ReleaseDescriptor descriptor )
    {
        return new PrepareReleaseProjectTask( releaseId, descriptor, null );
    }

    private Task getPerformTask( String releaseId, ReleaseDescriptor descriptor, File buildDir )
    {
        return new PerformReleaseProjectTask( releaseId, descriptor, buildDir, "package", true, null );
    }

    private ScmRepository getScmRepositorty( String scmUrl )
        throws ScmRepositoryException, NoSuchScmProviderException
    {
        ScmRepository repository = scmManager.makeScmRepository( scmUrl.trim() );

        repository.getProviderRepository().setPersistCheckout( true );

        return repository;
    }
}
