package org.apache.continuum.webdav;

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

import org.apache.continuum.utils.file.FileSystemManager;
import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.DavResource;
import org.apache.jackrabbit.webdav.DavResourceFactory;
import org.apache.jackrabbit.webdav.DavResourceLocator;
import org.apache.jackrabbit.webdav.DavServletRequest;
import org.apache.jackrabbit.webdav.DavServletResponse;
import org.apache.jackrabbit.webdav.DavSession;
import org.apache.maven.continuum.PlexusSpringTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

import static org.junit.Assert.*;

public class ContinuumBuildAgentDavResourceTest
    extends PlexusSpringTestCase
{
    private DavSession session;

    private DavResourceFactory resourceFactory;

    private ContinuumBuildAgentDavResourceLocator resourceLocator;

    private DavResource resource;

    private MimetypesFileTypeMap mimeTypes;

    private FileSystemManager fsManager;

    private File baseDir;

    private File resourceFile;

    private final String RESOURCE_FILE = "resource.jar";

    @Before
    public void setUp()
        throws Exception
    {
        session = new ContinuumBuildAgentDavSession();

        mimeTypes = new MimetypesFileTypeMap();
        mimeTypes.addMimeTypes( "application/java-archive jar war ear" );
        mimeTypes.addMimeTypes( "application/java-class class" );
        mimeTypes.addMimeTypes( "image/png png" );

        fsManager = lookup( FileSystemManager.class );

        baseDir = getTestFile( "target/DavResourceTest" );
        baseDir.mkdirs();
        resourceFile = new File( baseDir, RESOURCE_FILE );
        resourceFile.createNewFile();

        resourceFactory = new RootContextDavResourceFactory();
        resourceLocator = (ContinuumBuildAgentDavResourceLocator) new ContinuumBuildAgentDavLocatorFactory()
            .createResourceLocator( "/", RESOURCE_FILE );
        resource = getDavResource( resourceLocator.getHref( false ), resourceFile );
    }

    @After
    public void tearDown()
        throws Exception
    {
        if ( baseDir.exists() )
        {
            fsManager.removeDir( baseDir );
        }
    }

    @Test
    public void testAddResource()
        throws Exception
    {
        File newResource = new File( baseDir, "newResource.jar" );
        assertFalse( newResource.exists() );
        try
        {
            resource.getCollection().addMember( resource, null );
            fail( "Should have thrown an UnsupportedOperationException" );
        }
        catch ( UnsupportedOperationException e )
        {
            assertFalse( newResource.exists() );
        }
    }

    @Test
    public void testDeleteCollection()
        throws Exception
    {
        File dir = new File( baseDir, "testdir" );
        try
        {
            assertTrue( dir.mkdir() );
            DavResource directoryResource = getDavResource( "/testdir", dir );
            directoryResource.getCollection().removeMember( directoryResource );
            fail( "Should have thrown an UnsupportedOperationException" );
        }
        catch ( UnsupportedOperationException e )
        {
            assertTrue( dir.exists() );
        }
        finally
        {
            fsManager.removeDir( dir );
        }
    }

    @Test
    public void testDeleteResource()
        throws Exception
    {
        assertTrue( resourceFile.exists() );
        try
        {
            resource.getCollection().removeMember( resource );
            fail( "Should have thrown an UnsupportedOperationException" );
        }
        catch ( UnsupportedOperationException e )
        {
            assertTrue( resourceFile.exists() );
        }
    }

    private DavResource getDavResource( String logicalPath, File file )
    {
        return new ContinuumBuildAgentDavResource( file.getAbsolutePath(), logicalPath, session, resourceLocator,
                                                   resourceFactory, mimeTypes );
    }

    private class RootContextDavResourceFactory
        implements DavResourceFactory
    {
        public DavResource createResource( DavResourceLocator locator, DavServletRequest request,
                                           DavServletResponse response )
            throws DavException
        {
            throw new UnsupportedOperationException( "Not supported yet." );
        }

        public DavResource createResource( DavResourceLocator locator, DavSession session )
            throws DavException
        {
            return new ContinuumBuildAgentDavResource( baseDir.getAbsolutePath(), "/", session, resourceLocator,
                                                       resourceFactory, mimeTypes );
        }
    }
}
