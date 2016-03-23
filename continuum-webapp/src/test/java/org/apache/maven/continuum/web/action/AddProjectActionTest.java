package org.apache.maven.continuum.web.action;

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
import org.apache.continuum.web.action.AbstractActionTest;
import org.apache.maven.continuum.Continuum;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.web.action.stub.AddProjectActionStub;
import org.junit.Before;
import org.junit.Test;

>>>>>>> refs/remotes/apache/trunk
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

<<<<<<< HEAD
import org.apache.maven.continuum.web.action.stub.AddProjectActionStub;
import org.apache.maven.continuum.Continuum;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
=======
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
>>>>>>> refs/remotes/apache/trunk

/**
 * Test for {@link AddProjectAction}
 *
 * @author <a href="mailto:jzurbano@apache.org">jzurbano</a>
 */
public class AddProjectActionTest
<<<<<<< HEAD
    extends MockObjectTestCase
{
    private final AddProjectActionStub action;

    private final Mock continuumMock;

    public AddProjectActionTest()
    {
        action = new AddProjectActionStub();
        continuumMock = new Mock( Continuum.class );
        action.setContinuum( (Continuum) continuumMock.proxy() );
        
        Collection<ProjectGroup> projectGroups = new ArrayList<ProjectGroup>();
        ProjectGroup projectGroup = new ProjectGroup();
        projectGroups.add( projectGroup );
        
        action.setProjectGroups( projectGroups );
    }

=======
    extends AbstractActionTest
{
    private AddProjectActionStub action;

    private Continuum continuum;

    private static final String VALID_NAME_CHARACTER = "abcABC123whitespaces_.:-";

    private static final String VALID_VERSION_CHARACTER = "abcABC123.-";

    private static final String VALID_SCM_URL_CHARACTER = "abcABC123_.:-#~=@\\/|[]";

    private static final String VALID_SCM_TAG_CHARACTER = "abcABC123_.:-#~=@\\/|[]";

    private static final String VALID_DESCRIPTION_CHARACTER = "abcABC123whitespaces_.-";

    @Before
    public void setUp()
        throws Exception
    {
        continuum = mock( Continuum.class );

        action = new AddProjectActionStub();
        action.setContinuum( continuum );

        Collection<ProjectGroup> projectGroups = new ArrayList<ProjectGroup>();
        ProjectGroup projectGroup = new ProjectGroup();
        projectGroups.add( projectGroup );

        action.setProjectGroups( projectGroups );

        List<Project> projects = createProjectList();
        when( continuum.getProjects() ).thenReturn( projects );
        when( continuum.addProject( any( Project.class ), anyString(), anyInt(), anyInt() ) ).thenReturn( 3 );
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testAddProjectNullValues()
        throws Exception
    {
        action.setProjectName( null );
        action.setProjectVersion( null );
        action.setProjectScmUrl( null );
<<<<<<< HEAD
        
        action.validate();
    }
    
=======

        action.validate();
    }

>>>>>>> refs/remotes/apache/trunk
    /**
     * Test add of Ant project
     *
     * @throws Exception
     */
<<<<<<< HEAD
=======
    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testAddAntProject()
        throws Exception
    {
        String scmUrl = "scm:svn:http://project/scm/url/test/build.xml";
<<<<<<< HEAD
        
        List<Project> projects = createProjectList();
        continuumMock.expects( once() ).method( "getProjects" ).will( returnValue( projects ) );
        continuumMock.expects( once() ).method( "addProject" ).will( returnValue( 3 ) );
        
=======

>>>>>>> refs/remotes/apache/trunk
        action.setProjectName( "Ant Test Project" );
        action.setProjectVersion( "1.0-SNAPSHOT" );
        action.setProjectScmUrl( scmUrl );
        action.setProjectType( "ant" );
        action.setSelectedProjectGroup( 1 );
        action.setBuildDefintionTemplateId( 1 );
<<<<<<< HEAD
        
        action.validate();
        action.add();
        continuumMock.verify();
        
=======

        action.validate();
        action.add();
    }

    @Test
    public void testAddAntProjectWithValidValues()
        throws Exception
    {
        action.setProjectName( VALID_NAME_CHARACTER );
        action.setProjectDescription( VALID_DESCRIPTION_CHARACTER );
        action.setProjectVersion( VALID_VERSION_CHARACTER );
        action.setProjectScmUrl( VALID_SCM_URL_CHARACTER );
        action.setProjectScmTag( VALID_SCM_TAG_CHARACTER );
        action.setProjectType( "ant" );
        action.setSelectedProjectGroup( 1 );
        action.setBuildDefintionTemplateId( 1 );

        // validate
        action.validate();

        // verify
        assertFalse( action.hasActionErrors() );
        assertEquals( 0, action.getActionErrors().size() );

        // add
        action.add();
>>>>>>> refs/remotes/apache/trunk
    }

    /**
     * Test add of Shell project
     *
     * @throws Exception
     */
<<<<<<< HEAD
=======
    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testAddShellProject()
        throws Exception
    {
        String scmUrl = "scm:svn:http://project/scm/url/test/run.sh";
<<<<<<< HEAD
        
        List<Project> projects = createProjectList();
        continuumMock.expects( once() ).method( "getProjects" ).will( returnValue( projects ) );
        continuumMock.expects( once() ).method( "addProject" ).will( returnValue( 3 ) );
        
=======

>>>>>>> refs/remotes/apache/trunk
        action.setProjectName( "Shell Test Project" );
        action.setProjectVersion( "1.0-SNAPSHOT" );
        action.setProjectScmUrl( scmUrl );
        action.setProjectType( "shell" );
        action.setSelectedProjectGroup( 1 );
        action.setBuildDefintionTemplateId( 1 );
<<<<<<< HEAD
        
        action.validate();
        action.add();
        continuumMock.verify();
    }
    
    private List<Project> createProjectList()
    {
        List<Project> projects = new ArrayList<Project>();
        
        Project project1 = createProject( "scm:svn:http://project/scm/url/test-1/run.sh", "Shell Test Project 1", "1.0-SNAPSHOT", 1 );
        Project project2 = createProject( "scm:svn:http://project/scm/url/test-2/build.xml", "Ant Test Project 1", "1.0-SNAPSHOT", 2 );
        
        projects.add( project1 );
        projects.add( project2 );
        
        return projects;
    }
    
=======

        action.validate();
        action.add();
    }

    @Test
    public void testAddShellProjectWithValidValues()
        throws Exception
    {
        action.setProjectName( VALID_NAME_CHARACTER );
        action.setProjectDescription( VALID_DESCRIPTION_CHARACTER );
        action.setProjectVersion( VALID_VERSION_CHARACTER );
        action.setProjectScmUrl( VALID_SCM_URL_CHARACTER );
        action.setProjectScmTag( VALID_SCM_TAG_CHARACTER );
        action.setProjectType( "shell" );
        action.setSelectedProjectGroup( 1 );
        action.setBuildDefintionTemplateId( 1 );

        // validate
        action.validate();

        // verify
        assertFalse( action.hasActionErrors() );
        assertEquals( 0, action.getActionErrors().size() );

        // add
        action.add();
    }

    private List<Project> createProjectList()
    {
        List<Project> projects = new ArrayList<Project>();

        Project project1 = createProject( "scm:svn:http://project/scm/url/test-1/run.sh", "Shell Test Project 1",
                                          "1.0-SNAPSHOT", 1 );
        Project project2 = createProject( "scm:svn:http://project/scm/url/test-2/build.xml", "Ant Test Project 1",
                                          "1.0-SNAPSHOT", 2 );

        projects.add( project1 );
        projects.add( project2 );

        return projects;
    }

>>>>>>> refs/remotes/apache/trunk
    private Project createProject( String scmUrl, String name, String version, int id )
    {
        Project project = new Project();
        project.setId( id );
        project.setName( name );
        project.setVersion( version );
        project.setScmUrl( scmUrl );
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        return project;
    }
}
