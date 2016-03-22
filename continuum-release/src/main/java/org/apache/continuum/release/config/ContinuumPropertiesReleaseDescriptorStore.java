package org.apache.continuum.release.config;

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
import org.apache.maven.model.Scm;
import org.apache.maven.shared.release.config.PropertiesReleaseDescriptorStore;
import org.apache.maven.shared.release.config.ReleaseDescriptor;
import org.apache.maven.shared.release.config.ReleaseDescriptorStoreException;
import org.apache.maven.shared.release.config.ReleaseUtils;
import org.codehaus.plexus.util.IOUtil;
import org.eclipse.jetty.util.security.Password;

>>>>>>> refs/remotes/apache/trunk
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
<<<<<<< HEAD
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.maven.model.Scm;
import org.apache.maven.shared.release.config.PropertiesReleaseDescriptorStore;
import org.apache.maven.shared.release.config.ReleaseDescriptor;
import org.apache.maven.shared.release.config.ReleaseDescriptorStoreException;
import org.apache.maven.shared.release.config.ReleaseUtils;
import org.codehaus.plexus.util.IOUtil;
=======
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
>>>>>>> refs/remotes/apache/trunk

public class ContinuumPropertiesReleaseDescriptorStore
    extends PropertiesReleaseDescriptorStore
{
    public ReleaseDescriptor read( ReleaseDescriptor mergeDescriptor, File file )
        throws ReleaseDescriptorStoreException
    {
        Properties properties = new Properties();
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        InputStream inStream = null;
        try
        {
            inStream = new FileInputStream( file );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
            properties.load( inStream );
        }
        catch ( FileNotFoundException e )
        {
            getLogger().debug( file.getName() + " not found - using empty properties" );
        }
        catch ( IOException e )
        {
            throw new ReleaseDescriptorStoreException(
                "Error reading properties file '" + file.getName() + "': " + e.getMessage(), e );
        }
        finally
        {
            IOUtil.close( inStream );
        }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        ContinuumReleaseDescriptor releaseDescriptor = new ContinuumReleaseDescriptor();
        releaseDescriptor.setCompletedPhase( properties.getProperty( "completedPhase" ) );
        releaseDescriptor.setScmSourceUrl( properties.getProperty( "scm.url" ) );
        releaseDescriptor.setScmUsername( properties.getProperty( "scm.username" ) );
<<<<<<< HEAD
        releaseDescriptor.setScmPassword( properties.getProperty( "scm.password" ) );
=======

        String password = properties.getProperty( "scm.password" );
        if ( password != null && password.startsWith( "OBF:" ) )
        {
            releaseDescriptor.setScmPassword( Password.deobfuscate( password ) );
        }
        else
        {
            releaseDescriptor.setScmPassword( password );
        }
>>>>>>> refs/remotes/apache/trunk
        releaseDescriptor.setScmPrivateKey( properties.getProperty( "scm.privateKey" ) );
        releaseDescriptor.setScmPrivateKeyPassPhrase( properties.getProperty( "scm.passphrase" ) );
        releaseDescriptor.setScmTagBase( properties.getProperty( "scm.tagBase" ) );
        releaseDescriptor.setScmReleaseLabel( properties.getProperty( "scm.tag" ) );
        releaseDescriptor.setScmCommentPrefix( properties.getProperty( "scm.commentPrefix" ) );
        releaseDescriptor.setAdditionalArguments( properties.getProperty( "exec.additionalArguments" ) );
        releaseDescriptor.setPomFileName( properties.getProperty( "exec.pomFileName" ) );
        releaseDescriptor.setPreparationGoals( properties.getProperty( "preparationGoals" ) );
<<<<<<< HEAD
    
        loadResolvedDependencies( properties, releaseDescriptor );
    
        // boolean properties are not written to the properties file because the value from the caller is always used
    
        for ( Iterator i = properties.keySet().iterator(); i.hasNext(); )
        {
            String property = (String) i.next();
=======
        releaseDescriptor.setExecutable( properties.getProperty( "build.executable" ) );
        releaseDescriptor.setReleaseBy( properties.getProperty( "release.by" ) );

        loadResolvedDependencies( properties, releaseDescriptor );

        // boolean properties are not written to the properties file because the value from the caller is always used

        for ( Object o : properties.keySet() )
        {
            String property = (String) o;
>>>>>>> refs/remotes/apache/trunk
            if ( property.startsWith( "project.rel." ) )
            {
                releaseDescriptor.mapReleaseVersion( property.substring( "project.rel.".length() ),
                                                     properties.getProperty( property ) );
            }
            else if ( property.startsWith( "project.dev." ) )
            {
                releaseDescriptor.mapDevelopmentVersion( property.substring( "project.dev.".length() ),
                                                         properties.getProperty( property ) );
            }
            else if ( property.startsWith( "project.scm." ) )
            {
                int index = property.lastIndexOf( '.' );
                if ( index > "project.scm.".length() )
                {
                    String key = property.substring( "project.scm.".length(), index );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
                    if ( !releaseDescriptor.getOriginalScmInfo().containsKey( key ) )
                    {
                        if ( properties.getProperty( "project.scm." + key + ".empty" ) != null )
                        {
                            releaseDescriptor.mapOriginalScmInfo( key, null );
                        }
                        else
                        {
                            Scm scm = new Scm();
                            scm.setConnection( properties.getProperty( "project.scm." + key + ".connection" ) );
<<<<<<< HEAD
                            scm.setDeveloperConnection(
                                properties.getProperty( "project.scm." + key + ".developerConnection" ) );
                            scm.setUrl( properties.getProperty( "project.scm." + key + ".url" ) );
                            scm.setTag( properties.getProperty( "project.scm." + key + ".tag" ) );
    
=======
                            scm.setDeveloperConnection( properties.getProperty(
                                "project.scm." + key + ".developerConnection" ) );
                            scm.setUrl( properties.getProperty( "project.scm." + key + ".url" ) );
                            scm.setTag( properties.getProperty( "project.scm." + key + ".tag" ) );

>>>>>>> refs/remotes/apache/trunk
                            releaseDescriptor.mapOriginalScmInfo( key, scm );
                        }
                    }
                }
            }
            else if ( property.startsWith( "build.env." ) )
            {
<<<<<<< HEAD
                releaseDescriptor.mapEnvironments( property.substring( "build.env.".length() ),
                                                   properties.getProperty( property ) );
            }
        }
    
        if ( mergeDescriptor != null )
        {
            releaseDescriptor = (ContinuumReleaseDescriptor) ReleaseUtils.merge( releaseDescriptor, mergeDescriptor );
            releaseDescriptor.setEnvironments( ( (ContinuumReleaseDescriptor)mergeDescriptor ).getEnvironments() );
        }
    
=======
                releaseDescriptor.mapEnvironments( property.substring( "build.env.".length() ), properties.getProperty(
                    property ) );
            }
        }

        if ( mergeDescriptor != null )
        {
            releaseDescriptor = (ContinuumReleaseDescriptor) ReleaseUtils.merge( releaseDescriptor, mergeDescriptor );
            releaseDescriptor.setEnvironments( ( (ContinuumReleaseDescriptor) mergeDescriptor ).getEnvironments() );
        }

>>>>>>> refs/remotes/apache/trunk
        return releaseDescriptor;
    }

    public void write( ReleaseDescriptor configFile, File file )
        throws ReleaseDescriptorStoreException
    {
        ContinuumReleaseDescriptor config = (ContinuumReleaseDescriptor) configFile;
        Properties properties = new Properties();
        properties.setProperty( "completedPhase", config.getCompletedPhase() );
        properties.setProperty( "scm.url", config.getScmSourceUrl() );
        if ( config.getScmUsername() != null )
        {
            properties.setProperty( "scm.username", config.getScmUsername() );
        }
        if ( config.getScmPassword() != null )
        {
<<<<<<< HEAD
            properties.setProperty( "scm.password", config.getScmPassword() );
=======
            // obfuscate password
            properties.setProperty( "scm.password", Password.obfuscate( config.getScmPassword() ) );
>>>>>>> refs/remotes/apache/trunk
        }
        if ( config.getScmPrivateKey() != null )
        {
            properties.setProperty( "scm.privateKey", config.getScmPrivateKey() );
        }
        if ( config.getScmPrivateKeyPassPhrase() != null )
        {
            properties.setProperty( "scm.passphrase", config.getScmPrivateKeyPassPhrase() );
        }
        if ( config.getScmTagBase() != null )
        {
            properties.setProperty( "scm.tagBase", config.getScmTagBase() );
        }
        if ( config.getScmReleaseLabel() != null )
        {
            properties.setProperty( "scm.tag", config.getScmReleaseLabel() );
        }
        if ( config.getScmCommentPrefix() != null )
        {
            properties.setProperty( "scm.commentPrefix", config.getScmCommentPrefix() );
        }
        if ( config.getAdditionalArguments() != null )
        {
            properties.setProperty( "exec.additionalArguments", config.getAdditionalArguments() );
        }
        if ( config.getPomFileName() != null )
        {
            properties.setProperty( "exec.pomFileName", config.getPomFileName() );
        }
        if ( config.getPreparationGoals() != null )
        {
            properties.setProperty( "preparationGoals", config.getPreparationGoals() );
        }
<<<<<<< HEAD
    
        // boolean properties are not written to the properties file because the value from the caller is always used
    
        for ( Iterator i = config.getReleaseVersions().entrySet().iterator(); i.hasNext(); )
        {
            Map.Entry entry = (Map.Entry) i.next();
            properties.setProperty( "project.rel." + entry.getKey(), (String) entry.getValue() );
        }
    
        for ( Iterator i = config.getDevelopmentVersions().entrySet().iterator(); i.hasNext(); )
        {
            Map.Entry entry = (Map.Entry) i.next();
            properties.setProperty( "project.dev." + entry.getKey(), (String) entry.getValue() );
        }
    
        for ( Iterator i = config.getOriginalScmInfo().entrySet().iterator(); i.hasNext(); )
        {
            Map.Entry entry = (Map.Entry) i.next();
=======

        // boolean properties are not written to the properties file because the value from the caller is always used

        for ( Object o : config.getReleaseVersions().entrySet() )
        {
            Entry entry = (Entry) o;
            properties.setProperty( "project.rel." + entry.getKey(), (String) entry.getValue() );
        }

        for ( Object o : config.getDevelopmentVersions().entrySet() )
        {
            Entry entry = (Entry) o;
            properties.setProperty( "project.dev." + entry.getKey(), (String) entry.getValue() );
        }

        for ( Object o : config.getOriginalScmInfo().entrySet() )
        {
            Entry entry = (Entry) o;
>>>>>>> refs/remotes/apache/trunk
            Scm scm = (Scm) entry.getValue();
            String prefix = "project.scm." + entry.getKey();
            if ( scm != null )
            {
                if ( scm.getConnection() != null )
                {
                    properties.setProperty( prefix + ".connection", scm.getConnection() );
                }
                if ( scm.getDeveloperConnection() != null )
                {
                    properties.setProperty( prefix + ".developerConnection", scm.getDeveloperConnection() );
                }
                if ( scm.getUrl() != null )
                {
                    properties.setProperty( prefix + ".url", scm.getUrl() );
                }
                if ( scm.getTag() != null )
                {
                    properties.setProperty( prefix + ".tag", scm.getTag() );
                }
            }
            else
            {
                properties.setProperty( prefix + ".empty", "true" );
            }
        }

<<<<<<< HEAD
        for ( Iterator i = config.getEnvironments().entrySet().iterator(); i.hasNext(); )
        {
            Map.Entry entry = (Map.Entry) i.next();
            properties.setProperty( "build.env." + entry.getKey(), (String) entry.getValue() );
        }
    
=======
        for ( Object o : config.getEnvironments().entrySet() )
        {
            Entry entry = (Entry) o;
            properties.setProperty( "build.env." + entry.getKey(), (String) entry.getValue() );
        }

>>>>>>> refs/remotes/apache/trunk
        if ( ( config.getResolvedSnapshotDependencies() != null ) &&
            ( config.getResolvedSnapshotDependencies().size() > 0 ) )
        {
            processResolvedDependencies( properties, config.getResolvedSnapshotDependencies() );
        }
<<<<<<< HEAD
    
=======

        // executables
        if ( config.getExecutable() != null )
        {
            properties.setProperty( "build.executable", config.getExecutable() );
        }

        // release by
        if ( config.getReleaseBy() != null )
        {
            properties.setProperty( "release.by", config.getReleaseBy() );
        }

>>>>>>> refs/remotes/apache/trunk
        OutputStream outStream = null;
        //noinspection OverlyBroadCatchBlock
        try
        {
            outStream = new FileOutputStream( file );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
            properties.store( outStream, "release configuration" );
        }
        catch ( IOException e )
        {
            throw new ReleaseDescriptorStoreException(
                "Error writing properties file '" + file.getName() + "': " + e.getMessage(), e );
        }
        finally
        {
            IOUtil.close( outStream );
        }
<<<<<<< HEAD
    
    }
    
=======

    }

>>>>>>> refs/remotes/apache/trunk
    private void processResolvedDependencies( Properties prop, Map resolvedDependencies )
    {
        Set entries = resolvedDependencies.entrySet();
        Iterator iterator = entries.iterator();
        Entry currentEntry;
<<<<<<< HEAD
    
        while ( iterator.hasNext() )
        {
            currentEntry = (Entry) iterator.next();
    
            Map versionMap = (Map) currentEntry.getValue();
    
            prop.setProperty( "dependency." + currentEntry.getKey() + ".release",
                              (String) versionMap.get( ReleaseDescriptor.RELEASE_KEY ) );
            prop.setProperty( "dependency." + currentEntry.getKey() + ".development",
                              (String) versionMap.get( ReleaseDescriptor.DEVELOPMENT_KEY ) );
        }
    }
    
    private static File getDefaultReleasePropertiesFile( ReleaseDescriptor mergeDescriptor )
    {
        return new File( mergeDescriptor.getWorkingDirectory(), "release.properties" );
    }
    
    private void loadResolvedDependencies( Properties prop, ReleaseDescriptor descriptor )
    {
        Map resolvedDependencies = new HashMap();
    
=======

        while ( iterator.hasNext() )
        {
            currentEntry = (Entry) iterator.next();

            Map versionMap = (Map) currentEntry.getValue();

            prop.setProperty( "dependency." + currentEntry.getKey() + ".release", (String) versionMap.get(
                ReleaseDescriptor.RELEASE_KEY ) );
            prop.setProperty( "dependency." + currentEntry.getKey() + ".development", (String) versionMap.get(
                ReleaseDescriptor.DEVELOPMENT_KEY ) );
        }
    }

    private void loadResolvedDependencies( Properties prop, ReleaseDescriptor descriptor )
    {
        Map<String, Map<String, Object>> resolvedDependencies = new HashMap<String, Map<String, Object>>();

>>>>>>> refs/remotes/apache/trunk
        Set entries = prop.entrySet();
        Iterator iterator = entries.iterator();
        String propertyName;
        Entry currentEntry;
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        while ( iterator.hasNext() )
        {
            currentEntry = (Entry) iterator.next();
            propertyName = (String) currentEntry.getKey();
<<<<<<< HEAD
    
            if ( propertyName.startsWith( "dependency." ) )
            {
                Map versionMap;
=======

            if ( propertyName.startsWith( "dependency." ) )
            {
                Map<String, Object> versionMap;
>>>>>>> refs/remotes/apache/trunk
                String artifactVersionlessKey;
                int startIndex;
                int endIndex;
                String versionType;
<<<<<<< HEAD
    
                versionMap = new HashMap();
                startIndex = propertyName.lastIndexOf( "dependency." );
    
=======

                startIndex = propertyName.lastIndexOf( "dependency." );

>>>>>>> refs/remotes/apache/trunk
                if ( propertyName.indexOf( ".development" ) != -1 )
                {
                    endIndex = propertyName.indexOf( ".development" );
                    versionType = ReleaseDescriptor.DEVELOPMENT_KEY;
                }
                else
                {
                    endIndex = propertyName.indexOf( ".release" );
                    versionType = ReleaseDescriptor.RELEASE_KEY;
                }
<<<<<<< HEAD
    
                artifactVersionlessKey = propertyName.substring( startIndex, endIndex );
    
                if ( resolvedDependencies.containsKey( artifactVersionlessKey ) )
                {
                    versionMap = (Map) resolvedDependencies.get( artifactVersionlessKey );
                }
                else
                {
                    versionMap = new HashMap();
                    resolvedDependencies.put( artifactVersionlessKey, versionMap );
                }
    
                versionMap.put( versionType, currentEntry.getValue() );
            }
        }
    
=======

                artifactVersionlessKey = propertyName.substring( startIndex, endIndex );

                if ( resolvedDependencies.containsKey( artifactVersionlessKey ) )
                {
                    versionMap = resolvedDependencies.get( artifactVersionlessKey );
                }
                else
                {
                    versionMap = new HashMap<String, Object>();
                    resolvedDependencies.put( artifactVersionlessKey, versionMap );
                }

                versionMap.put( versionType, currentEntry.getValue() );
            }
        }

>>>>>>> refs/remotes/apache/trunk
        descriptor.setResolvedSnapshotDependencies( resolvedDependencies );
    }
}
