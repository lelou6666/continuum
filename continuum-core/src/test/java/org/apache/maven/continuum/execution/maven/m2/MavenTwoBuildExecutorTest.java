package org.apache.maven.continuum.execution.maven.m2;

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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.AbstractContinuumTest;
import org.apache.maven.continuum.execution.ContinuumBuildExecutor;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.scm.ChangeFile;
import org.apache.maven.continuum.model.scm.ChangeSet;
<<<<<<< HEAD
=======
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
>>>>>>> refs/remotes/apache/trunk

/**
 * @author olamy
 * @since 1.2.3
<<<<<<< HEAD
 * @version $Id$
=======
>>>>>>> refs/remotes/apache/trunk
 */
public class MavenTwoBuildExecutorTest
    extends AbstractContinuumTest
{
<<<<<<< HEAD
       
    
=======
>>>>>>> refs/remotes/apache/trunk
    @Override
    protected String getSpringConfigLocation()
    {
        return "applicationContextSlf4jPlexusLogger.xml";
    }

<<<<<<< HEAD
    public void testShouldNotBuildNonRecursive()
        throws Exception
    {
        MavenTwoBuildExecutor executor = (MavenTwoBuildExecutor) lookup( ContinuumBuildExecutor.class, "maven2" );
        BuildDefinition buildDefinition = new BuildDefinition();
        buildDefinition.setArguments( "-N" );
        Project continuumProject = new Project(){
=======
    @Test
    public void testShouldNotBuildNonRecursive()
        throws Exception
    {
        ContinuumBuildExecutor executor = lookup( ContinuumBuildExecutor.class, "maven2" );
        BuildDefinition buildDefinition = new BuildDefinition();
        buildDefinition.setArguments( "-N" );
        Project continuumProject = new Project()
        {
>>>>>>> refs/remotes/apache/trunk
            {
                setVersion( "1.0.3" );
            }
        };
<<<<<<< HEAD
        assertFalse( executor.shouldBuild( new ArrayList<ChangeSet>(), continuumProject, new File( "target/test-classes/projects/continuum" ),
                                           buildDefinition ) );
    }
    
    public void testShouldNotBuildNonRecursiveChangeInAModule()
        throws Exception
    {
        MavenTwoBuildExecutor executor = (MavenTwoBuildExecutor) lookup( ContinuumBuildExecutor.class, "maven2" );
=======
        assertFalse( executor.shouldBuild( new ArrayList<ChangeSet>(), continuumProject, new File(
            "target/test-classes/projects/continuum" ), buildDefinition ) );
    }

    @Test
    public void testShouldNotBuildNonRecursiveChangeInAModule()
        throws Exception
    {
        ContinuumBuildExecutor executor = lookup( ContinuumBuildExecutor.class, "maven2" );
>>>>>>> refs/remotes/apache/trunk
        BuildDefinition buildDefinition = new BuildDefinition();
        buildDefinition.setArguments( "-N -Dfoo=bar" );
        Project continuumProject = new Project()
        {
            {
                setVersion( "1.0.3" );
            }
        };
        final ChangeFile changeFile = new ChangeFile();
<<<<<<< HEAD
        changeFile.setName( "continuum-notifiers/pom.xml");
=======
        changeFile.setName( "continuum-notifiers/pom.xml" );
>>>>>>> refs/remotes/apache/trunk
        ChangeSet changeSet = new ChangeSet()
        {
            {
                addFile( changeFile );
            }
        };
        List<ChangeSet> changeSets = new ArrayList<ChangeSet>();
        changeSets.add( changeSet );
<<<<<<< HEAD
        assertFalse( executor.shouldBuild(changeSets , continuumProject,
                                           new File( "target/test-classes/projects/continuum" ), buildDefinition ) );
    }    
    
    public void testShouldBuildRecursiveChangeInAModule()
        throws Exception
    {
        MavenTwoBuildExecutor executor = (MavenTwoBuildExecutor) lookup( ContinuumBuildExecutor.class, "maven2" );
=======
        assertFalse( executor.shouldBuild( changeSets, continuumProject, new File(
            "target/test-classes/projects/continuum" ), buildDefinition ) );
    }

    @Test
    public void testShouldBuildRecursiveChangeInAModule()
        throws Exception
    {
        ContinuumBuildExecutor executor = lookup( ContinuumBuildExecutor.class, "maven2" );
>>>>>>> refs/remotes/apache/trunk
        BuildDefinition buildDefinition = new BuildDefinition();
        buildDefinition.setArguments( "-Dfoo=bar" );
        Project continuumProject = new Project()
        {
            {
                setVersion( "1.0.3" );
            }
        };
        final ChangeFile changeFile = new ChangeFile();
        changeFile.setName( "continuum-notifiers/pom.xml" );
        ChangeSet changeSet = new ChangeSet()
        {
            {
                addFile( changeFile );
            }
        };
        List<ChangeSet> changeSets = new ArrayList<ChangeSet>();
        changeSets.add( changeSet );
<<<<<<< HEAD
        assertTrue( executor.shouldBuild( changeSets, continuumProject,
                                          new File( "target/test-classes/projects/continuum" ), buildDefinition ) );
    }       
=======
        assertTrue( executor.shouldBuild( changeSets, continuumProject, new File(
            "target/test-classes/projects/continuum" ), buildDefinition ) );
    }
>>>>>>> refs/remotes/apache/trunk
}
