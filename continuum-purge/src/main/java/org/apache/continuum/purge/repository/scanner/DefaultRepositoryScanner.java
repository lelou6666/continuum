package org.apache.continuum.purge.repository.scanner;

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

import org.apache.commons.collections.CollectionUtils;
<<<<<<< HEAD
import org.apache.continuum.model.repository.LocalRepository;
import org.apache.continuum.purge.controller.PurgeController;
import org.apache.continuum.purge.executor.ContinuumPurgeExecutorException;
import org.apache.continuum.purge.repository.utils.FileTypes;
import org.codehaus.plexus.util.DirectoryWalker;
=======
import org.apache.continuum.purge.executor.ContinuumPurgeExecutorException;
import org.apache.continuum.purge.repository.utils.FileTypes;
import org.apache.maven.archiva.common.utils.BaseFile;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.util.DirectoryWalkListener;
import org.codehaus.plexus.util.DirectoryWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> refs/remotes/apache/trunk

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Codes were taken from Archiva and made some changes.
<<<<<<< HEAD
 *
 * @plexus.component role="org.apache.continuum.purge.repository.scanner.RepositoryScanner" role-hint="repository-scanner"
 */
public class DefaultRepositoryScanner
    implements RepositoryScanner
{
    /**
     * @plexus.requirement role-hint="file-types"
     */
    private FileTypes filetypes;

    public void scan( LocalRepository repository, PurgeController purgeController )
        throws ContinuumPurgeExecutorException
    {
        List<String> ignoredPatterns = filetypes.getIgnoredFileTypePatterns();
        scan( repository, purgeController, ignoredPatterns );
    }

    public void scan( LocalRepository repository, PurgeController purgeController, List<String> ignoredContentPatterns )
        throws ContinuumPurgeExecutorException
    {
        File repositoryBase = new File( repository.getLocation() );

        if ( !repositoryBase.exists() )
        {
            throw new UnsupportedOperationException(
                "Unable to scan a repository, directory " + repositoryBase.getAbsolutePath() + " does not exist." );
        }

        if ( !repositoryBase.isDirectory() )
        {
            throw new UnsupportedOperationException(
                "Unable to scan a repository, path " + repositoryBase.getAbsolutePath() + " is not a directory." );
=======
 */
@Component( role = RepositoryScanner.class, hint = "purge" )
public class DefaultRepositoryScanner
    implements RepositoryScanner
{

    @Requirement( hint = "file-types" )
    private FileTypes filetypes;

    public void scan( File repoLocation, ScannerHandler handler )
        throws ContinuumPurgeExecutorException
    {
        List<String> ignoredPatterns = filetypes.getIgnoredFileTypePatterns();
        scan( repoLocation, handler, ignoredPatterns );
    }

    public void scan( File repositoryLocation, ScannerHandler handler, List<String> ignoredContentPatterns )
        throws ContinuumPurgeExecutorException
    {

        if ( !repositoryLocation.exists() )
        {
            throw new UnsupportedOperationException(
                "Unable to scan a repository, directory " + repositoryLocation.getAbsolutePath() + " does not exist." );
        }

        if ( !repositoryLocation.isDirectory() )
        {
            throw new UnsupportedOperationException(
                "Unable to scan a repository, path " + repositoryLocation.getAbsolutePath() + " is not a directory." );
>>>>>>> refs/remotes/apache/trunk
        }

        // Setup Includes / Excludes.

        List<String> allExcludes = new ArrayList<String>();
        List<String> allIncludes = new ArrayList<String>();

        if ( CollectionUtils.isNotEmpty( ignoredContentPatterns ) )
        {
            allExcludes.addAll( ignoredContentPatterns );
        }

        // Scan All Content. (intentional)
        allIncludes.add( "**/*" );

        // Setup Directory Walker
        DirectoryWalker dirWalker = new DirectoryWalker();

<<<<<<< HEAD
        dirWalker.setBaseDir( repositoryBase );
=======
        dirWalker.setBaseDir( repositoryLocation );
>>>>>>> refs/remotes/apache/trunk

        dirWalker.setIncludes( allIncludes );
        dirWalker.setExcludes( allExcludes );

<<<<<<< HEAD
        RepositoryScannerInstance scannerInstance = new RepositoryScannerInstance( repository, purgeController );

        dirWalker.addDirectoryWalkListener( scannerInstance );

        // Execute scan.
        dirWalker.scan();

=======
        ScanListener listener = new ScanListener( repositoryLocation, handler );

        dirWalker.addDirectoryWalkListener( listener );

        // Execute scan.
        dirWalker.scan();
    }
}

class ScanListener
    implements DirectoryWalkListener
{
    private static final Logger log = LoggerFactory.getLogger( ScanListener.class );

    private final File repository;

    private final ScannerHandler handler;

    public ScanListener( File repoLocation, ScannerHandler handler )
    {
        this.repository = repoLocation;
        this.handler = handler;
    }

    public void debug( String message )
    {
        log.debug( "repo scan: {}", message );
    }

    public void directoryWalkFinished()
    {
        log.debug( "finished walk: {}", repository );
    }

    public void directoryWalkStarting( File file )
    {
        log.debug( "starting walk: {}", repository );
    }

    public void directoryWalkStep( int percentage, File file )
    {
        BaseFile basefile = new BaseFile( repository, file );
        handler.handle( basefile.getRelativePath() );
>>>>>>> refs/remotes/apache/trunk
    }
}