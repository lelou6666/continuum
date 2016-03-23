package org.apache.maven.continuum.builddefinition;

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
package org.apache.maven.continuum.builddefinition;

import org.apache.log4j.Logger;
=======

import org.apache.continuum.dao.DaoUtils;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.AbstractContinuumTest;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildDefinitionTemplate;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
<<<<<<< HEAD
import org.apache.continuum.dao.DaoUtils;

import java.util.List;

/**
 * @author <a href="mailto:olamy@apache.org">olamy</a>
 * @version $Id$
=======
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:olamy@apache.org">olamy</a>
>>>>>>> refs/remotes/apache/trunk
 * @since 15 sept. 07
 */
public class DefaultBuildDefinitionServiceTest
    extends AbstractContinuumTest
{
<<<<<<< HEAD
    private Logger logger = Logger.getLogger( getClass() );

    private ProjectGroup projectGroup;

    private Project project;

    private BuildDefinition buildDefinition;

=======
    private static final Logger logger = LoggerFactory.getLogger( DefaultBuildDefinitionServiceTest.class );

    private Project project;

>>>>>>> refs/remotes/apache/trunk
    private BuildDefinitionTemplate buildDefinitionTemplate;

    @Before
    public void setUp()
        throws Exception
    {
<<<<<<< HEAD
        super.setUp();
        DaoUtils daoUtils = (DaoUtils) lookup( DaoUtils.class.getName() );
=======
        DaoUtils daoUtils = lookup( DaoUtils.class );
>>>>>>> refs/remotes/apache/trunk
        daoUtils.eraseDatabase();

        ProjectGroup projectGroup = new ProjectGroup();
        projectGroup.setName( "test" );
        projectGroup = getProjectGroupDao().addProjectGroup( projectGroup );

        project = new Project();
        project.setGroupId( "foo" );
        project.setArtifactId( "bar" );
        project.setVersion( "0.1-alpha-1-SNAPSHOT" );
        projectGroup.addProject( project );
        getProjectGroupDao().updateProjectGroup( projectGroup );

        BuildDefinition buildDefinition = new BuildDefinition();
        buildDefinition.setTemplate( true );
        buildDefinition.setArguments( "-N" );
        buildDefinition.setGoals( "clean test-compile" );
        buildDefinition.setBuildFile( "pom.xml" );
        buildDefinition.setDescription( "desc template" );
        buildDefinition = getBuildDefinitionService().addBuildDefinition( buildDefinition );

        buildDefinitionTemplate = new BuildDefinitionTemplate();
        buildDefinitionTemplate.setName( "test" );
        buildDefinitionTemplate = getBuildDefinitionService().addBuildDefinitionTemplate( buildDefinitionTemplate );
<<<<<<< HEAD
        buildDefinitionTemplate =
            getBuildDefinitionService().addBuildDefinitionInTemplate( buildDefinitionTemplate, buildDefinition, false );

=======
        buildDefinitionTemplate = getBuildDefinitionService().addBuildDefinitionInTemplate( buildDefinitionTemplate,
                                                                                            buildDefinition, false );
>>>>>>> refs/remotes/apache/trunk

    }

    protected BuildDefinitionService getBuildDefinitionService()
        throws Exception
    {
        return lookup( BuildDefinitionService.class );
    }

    @Test
    public void testAddTemplateInProject()
        throws Exception
    {
        try
        {
            List<BuildDefinitionTemplate> templates = getBuildDefinitionService().getAllBuildDefinitionTemplate();
            assertEquals( 5, templates.size() );
            assertEquals( 5, getBuildDefinitionService().getAllBuildDefinitions().size() );

            getBuildDefinitionService().addTemplateInProject( buildDefinitionTemplate.getId(), project );
            project = getProjectDao().getProjectWithAllDetails( project.getId() );
            templates = getBuildDefinitionService().getAllBuildDefinitionTemplate();
            assertEquals( 1, project.getBuildDefinitions().size() );
            assertEquals( 5, templates.size() );
            List<BuildDefinition> all = getBuildDefinitionService().getAllBuildDefinitions();
            assertEquals( 6, all.size() );

            getBuildDefinitionService().addTemplateInProject( buildDefinitionTemplate.getId(), project );

            project = getProjectDao().getProjectWithAllDetails( project.getId() );
            templates = getBuildDefinitionService().getAllBuildDefinitionTemplate();
            assertEquals( 2, project.getBuildDefinitions().size() );
            assertEquals( 5, templates.size() );
            all = getBuildDefinitionService().getAllBuildDefinitions();
            assertEquals( 7, all.size() );

        }
        catch ( Exception e )
        {
            logger.error( e.getMessage(), e );
            throw e;
        }
    }

<<<<<<< HEAD

=======
    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testGetDefaultBuildDef()
        throws Exception
    {
        BuildDefinition bd =
            getBuildDefinitionService().getDefaultAntBuildDefinitionTemplate().getBuildDefinitions().get(
                0 );
        assertNotNull( bd );
        assertEquals( "build.xml", bd.getBuildFile() );

        bd =
            getBuildDefinitionService().getDefaultMavenTwoBuildDefinitionTemplate().getBuildDefinitions().get(
                0 );
        BuildDefinitionService buildDefinitionService = lookup( BuildDefinitionService.class );

        assertEquals( 5, buildDefinitionService.getAllBuildDefinitionTemplate().size() );
        assertNotNull( bd );
        assertEquals( "pom.xml", bd.getBuildFile() );
        assertEquals( "clean install", bd.getGoals() );
    }

<<<<<<< HEAD

=======
    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testAddBuildDefinitionTemplate()
        throws Exception
    {
        BuildDefinitionTemplate template = new BuildDefinitionTemplate();
        template.setName( "testTemplate" );

        template = getBuildDefinitionService().addBuildDefinitionTemplate( template );
        template = getBuildDefinitionService().getBuildDefinitionTemplate( template.getId() );
        assertNotNull( template );
        assertEquals( "testTemplate", template.getName() );
        List<BuildDefinition> all = getBuildDefinitionService().getAllBuildDefinitions();
        assertEquals( 5, all.size() );
        BuildDefinition bd =
            getBuildDefinitionService().getDefaultMavenTwoBuildDefinitionTemplate().getBuildDefinitions().get(
                0 );
        template = getBuildDefinitionService().addBuildDefinitionInTemplate( template, bd, false );

        assertEquals( true, getBuildDefinitionService().isBuildDefinitionInUse( bd ) );

        assertEquals( 1, template.getBuildDefinitions().size() );
        all = getBuildDefinitionService().getAllBuildDefinitions();
        assertEquals( 5, all.size() );

        template = getBuildDefinitionService().getBuildDefinitionTemplate( template.getId() );
        template = getBuildDefinitionService().updateBuildDefinitionTemplate( template );
        template = getBuildDefinitionService().removeBuildDefinitionFromTemplate( template, bd );
        assertEquals( 0, template.getBuildDefinitions().size() );
        all = getBuildDefinitionService().getAllBuildDefinitions();
        assertEquals( 5, all.size() );

<<<<<<< HEAD
=======
    }

    @Test
    public void testAddDuplicateBuildDefinitionTemplate()
        throws Exception
    {
        BuildDefinitionTemplate template = new BuildDefinitionTemplate();
        template.setName( "test" );

        template = getBuildDefinitionService().addBuildDefinitionTemplate( template );
        assertNull( template );
    }

    @Test
    public void testUnusedBuildDefinition()
        throws Exception
    {
        BuildDefinition unused = new BuildDefinition();

        unused.setTemplate( true );
        unused.setArguments( "-N" );
        unused.setGoals( "clean test-compile" );
        unused.setBuildFile( "pom.xml" );
        unused.setDescription( "desc template" );

        assertFalse( getBuildDefinitionService().isBuildDefinitionInUse( unused ) );
>>>>>>> refs/remotes/apache/trunk
    }
}
