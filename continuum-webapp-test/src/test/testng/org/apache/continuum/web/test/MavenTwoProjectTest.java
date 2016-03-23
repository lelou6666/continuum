package org.apache.continuum.web.test;

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

import org.apache.continuum.web.test.parent.AbstractAdminTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Based on AddMavenTwoProjectTest of Emmanuel Venisse test.
 *
 * @author José Morales Martínez
 */
@Test( groups = { "mavenTwoProject" } )
public class MavenTwoProjectTest
    extends AbstractAdminTest
{
    private String pomUrl;

<<<<<<< HEAD
    public void testAddMavenTwoProjectWithNoDefaultBuildDefinitionInTemplate()
        throws Exception
    {
        String M2_POM_URL = getProperty( "M2_POM_URL" );
        String M2_POM_USERNAME = getProperty( "M2_POM_USERNAME" );
        String M2_POM_PASSWORD = getProperty( "M2_POM_PASSWORD" );

        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_PROJ_GRP_ID" );
        String M2_PROJ_GRP_DESCRIPTION = getProperty( "M2_PROJ_GRP_DESCRIPTION" );
        String M2_PROJ_GRP_SCM_ROOT_URL = getProperty( "M2_PROJ_GRP_SCM_ROOT_URL" );

        removeDefaultBuildDefinitionFromTemplate( "maven2" );

        addMavenTwoProject( M2_POM_URL, M2_POM_USERNAME, M2_POM_PASSWORD, null, true );

        assertProjectGroupSummaryPage( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION );

        assertTextPresent( M2_PROJ_GRP_SCM_ROOT_URL );

        // Delete project group
        removeProjectGroup( M2_PROJ_GRP_NAME );

        // Re-add default build definition of template
        addDefaultBuildDefinitionFromTemplate( "maven2" );
    }

    @Test( dependsOnMethods = { "testAddMavenTwoProjectWithNoDefaultBuildDefinitionInTemplate" } )
    public void testAddMavenTwoProject()
        throws Exception
    {
        String M2_POM_URL = getProperty( "M2_POM_URL" );
        String M2_POM_USERNAME = getProperty( "M2_POM_USERNAME" );
        String M2_POM_PASSWORD = getProperty( "M2_POM_PASSWORD" );

        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_PROJ_GRP_ID" );
        String M2_PROJ_GRP_DESCRIPTION = getProperty( "M2_PROJ_GRP_DESCRIPTION" );
        String M2_PROJ_GRP_SCM_ROOT_URL = getProperty( "M2_PROJ_GRP_SCM_ROOT_URL" );
        
        // Enter values into Add Maven Two Project fields, and submit
        addMavenTwoProject( M2_POM_URL, M2_POM_USERNAME, M2_POM_PASSWORD, null, true );
        // Wait Struct Listener
        assertProjectGroupSummaryPage( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION );

        assertTextPresent( M2_PROJ_GRP_SCM_ROOT_URL );
=======
    private String pomUsername;

    private String pomPassword;

    private String projectGroupName;

    private String projectGroupId;

    private String projectGroupDescription;

    private String projectGroupScmRootUrl;

    private String projectName;

    @BeforeMethod
    protected void setUp()
        throws Exception
    {
        pomUrl = getProperty( "MAVEN2_POM_URL" );
        pomUsername = getProperty( "MAVEN2_POM_USERNAME" );
        pomPassword = getProperty( "MAVEN2_POM_PASSWORD" );

        projectName = getProperty( "MAVEN2_POM_PROJECT_NAME" );
        projectGroupName = getProperty( "MAVEN2_POM_PROJECT_GROUP_NAME" );
        projectGroupId = getProperty( "MAVEN2_POM_PROJECT_GROUP_ID" );
        projectGroupDescription = getProperty( "MAVEN2_POM_PROJECT_GROUP_DESCRIPTION" );
        projectGroupScmRootUrl = getProperty( "MAVEN2_POM_PROJECT_GROUP_SCM_ROOT_URL" );
    }

    @AfterMethod
    public void tearDown()
    {
        removeProjectGroup( projectGroupName, false );
    }

    public void testAddMavenTwoProject()
        throws Exception
    {
        // Enter values into Add Maven Two Project fields, and submit
        addMavenTwoProject( pomUrl, pomUsername, pomPassword, null, true );

        // Wait Struts Listener
        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );

        assertTextPresent( projectGroupScmRootUrl );
    }

    @Test( dependsOnMethods = { "testAddMavenTwoProject" } )
    public void testEditProjectName()
         throws Exception
    {
        // Create a project to use
        testAddMavenTwoProject();

        // Navigate to project's edit page
        clickLinkWithText(projectName);
        clickButtonWithValue("Edit");
        assertPage("Continuum - Update Continuum Project");

        // Edit the name of the project and save it
        String newName = "New Name";
        setFieldValue("projectSave_name", newName);
        clickButtonWithValue("Save");

        // Verify that the save succeeded
        assertPage("Continuum - Continuum Project");
        assertTextPresent(String.format("Continuum Project \"%s\"", newName));
>>>>>>> refs/remotes/apache/trunk
    }

    /**
     * Test flat multi module project with names that start with the same letter
     */
    public void testAddMavenTwoProjectModuleNameWithSameLetter()
        throws Exception
    {
<<<<<<< HEAD
        String M2_POM_URL = getProperty( "M2_SAME_LETTER_POM_URL" );
        String M2_POM_USERNAME = getProperty( "M2_POM_USERNAME" );
        String M2_POM_PASSWORD = getProperty( "M2_POM_PASSWORD" );

        String M2_PROJ_GRP_NAME = getProperty( "M2_SAME_LETTER_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_SAME_LETTER_PROJ_GRP_ID" );
        String M2_PROJ_GRP_DESCRIPTION = getProperty( "M2_SAME_LETTER_PROJ_GRP_DESCRIPTION" );

        String M2_PROJ_GRP_SCM_ROOT_URL = getProperty( "M2_SAME_LETTER_PROJ_GRP_SCM_ROOT_URL" );

        addMavenTwoProject( M2_POM_URL, M2_POM_USERNAME, M2_POM_PASSWORD, null, true );

        assertProjectGroupSummaryPage( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION );

        assertTextPresent( M2_PROJ_GRP_SCM_ROOT_URL );
=======
        pomUrl = getProperty( "MAVEN2_SAME_LETTER_FLAT_POM_URL" );
        pomUsername = "";
        pomPassword = "";

        projectGroupName = getProperty( "MAVEN2_SAME_LETTER_FLAT_PROJECT_GROUP_NAME" );
        projectGroupId = getProperty( "MAVEN2_SAME_LETTER_FLAT_PROJECT_GROUP_ID" );
        projectGroupDescription = getProperty( "MAVEN2_SAME_LETTER_FLAT_PROJECT_GROUP_DESCRIPTION" );
        projectGroupScmRootUrl = getProperty( "MAVEN2_SAME_LETTER_FLAT_PROJECT_GROUP_SCM_ROOT_URL" );

        addMavenTwoProject( pomUrl, pomUsername, pomPassword, null, true );

        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );

        assertTextPresent( projectGroupScmRootUrl );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddMavenTwoProjectFromRemoteSourceToNonDefaultProjectGroup()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String TEST_PROJ_GRP_SCM_ROOT_URL = getProperty( "M2_PROJ_GRP_SCM_ROOT_URL" );

        String M2_POM_URL = getProperty( "M2_POM_URL" );
        String M2_POM_USERNAME = getProperty( "M2_POM_USERNAME" );
        String M2_POM_PASSWORD = getProperty( "M2_POM_PASSWORD" );
        
        addMavenTwoProject( M2_POM_URL, M2_POM_USERNAME, M2_POM_PASSWORD, TEST_PROJ_GRP_NAME, true );

        assertProjectGroupSummaryPage( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );

        assertTextPresent( TEST_PROJ_GRP_SCM_ROOT_URL );
=======
        projectGroupName = getProperty( "MAVEN2_NON_DEFAULT_PROJECT_GROUP_NAME" );
        projectGroupId = getProperty( "MAVEN2_NON_DEFAULT_PROJECT_GROUP_ID" );
        projectGroupDescription = getProperty( "MAVEN2_NON_DEFAULT_PROJECT_GROUP_DESCRIPTION" );

        addProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, true );

        addMavenTwoProject( pomUrl, pomUsername, pomPassword, projectGroupName, true );

        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );

        assertTextPresent( projectGroupScmRootUrl );
    }

    public void testMoveProject()
        throws Exception
    {
        addMavenTwoProject( pomUrl, pomUsername, pomPassword, null, true );
        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );
        assertTextPresent( projectGroupScmRootUrl );

        String targetGroupName = getProperty( "MAVEN2_MOVE_PROJECT_TARGET_PROJECT_GROUP_NAME" );
        String targetGroupId = getProperty( "MAVEN2_MOVE_PROJECT_TARGET_PROJECT_GROUP_ID" );
        String targetGroupDescription = getProperty( "MAVEN2_MOVE_PROJECT_TARGET_PROJECT_GROUP_DESCRIPTION" );
        addProjectGroup( targetGroupName, targetGroupId, targetGroupDescription, true );

        try {
            // Move the project
            moveProjectToProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, projectName,
                                       targetGroupName );
            showProjectGroup( targetGroupName, targetGroupId, targetGroupDescription );
            assertTextPresent( "Member Projects" );
            assertTextPresent( projectName );

            showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
            assertTextNotPresent( "Member Projects" );
        } finally {
            removeProjectGroup( targetGroupName, false );
        }
>>>>>>> refs/remotes/apache/trunk
    }

    /**
     * Test invalid pom url
     */
    public void testNoPomSpecified()
        throws Exception
    {
        submitAddMavenTwoProjectPage( "" );
        assertTextPresent( "Either POM URL or Upload POM is required." );
    }

    /**
     * Test when scm element is missing from pom
     */
    public void testMissingScmElementPom()
        throws Exception
    {
<<<<<<< HEAD
        String pomUrl = getProperty( "NOT_SCM_POM_URL" );
        submitAddMavenTwoProjectPage( pomUrl, false );
=======
        String pomUrl = getProperty( "MAVEN2_NO_SCM_POM_URL" );
        submitAddMavenTwoProjectPage( pomUrl );
>>>>>>> refs/remotes/apache/trunk
        assertTextPresent( "Missing ''scm'' element in the POM, project Maven Two Project" );
    }

    /**
     * test with a malformed pom url
     */
    public void testMalformedPomUrl()
        throws Exception
    {
        String pomUrl = "aaa";
        submitAddMavenTwoProjectPage( pomUrl );
        assertTextPresent(
            "The specified resource cannot be accessed. Please try again later or contact your administrator." );
    }

    /**
     * Test when the connection element is missing from the scm tag
     */
    public void testMissingConnectionElement()
        throws Exception
    {
<<<<<<< HEAD
        String pomUrl = getProperty( "MISS_CONECT_POM_URL" );
        submitAddMavenTwoProjectPage( pomUrl, false );
=======
        String pomUrl = getProperty( "MAVEN2_MISS_CONNECTION_POM_URL" );
        submitAddMavenTwoProjectPage( pomUrl );
>>>>>>> refs/remotes/apache/trunk
        assertTextPresent( "Missing 'connection' sub-element in the 'scm' element in the POM." );
    }

    /**
     * test unallowed file protocol
     */
    public void testNotAllowedProtocol()
        throws Exception
    {
        String pomUrl = "file:///pom.xml";
        submitAddMavenTwoProjectPage( pomUrl );
        assertTextPresent( "The specified resource isn't a file or the protocol used isn't allowed." );
    }

    /**
     * Test when the parent pom is missing or not yet added in continuum
     */
    public void testMissingParentPom()
        throws Exception
    {
<<<<<<< HEAD
        String pomUrl = getProperty( "MISS_PARENT_POM_URL" );
        submitAddMavenTwoProjectPage( pomUrl, false );
        assertTextPresent( "Missing artifact trying to build the POM. Check that its parent POM is available or add it first in Continuum." );
=======
        String pomUrl = getProperty( "MAVEN2_MISS_PARENT_POM_URL" );
        submitAddMavenTwoProjectPage( pomUrl );
        assertTextPresent(
            "Missing artifact trying to build the POM. Check that its parent POM is available or add it first in Continuum." );
>>>>>>> refs/remotes/apache/trunk
    }

    /**
     * Test when the modules/subprojects specified in the pom are not found
     */
    public void testMissingModules()
        throws Exception
    {
<<<<<<< HEAD
        String pomUrl = getProperty( "MISS_SUBPRO_POM_URL" );
        submitAddMavenTwoProjectPage( pomUrl, false );
=======
        String pomUrl = getProperty( "MAVEN2_MISS_SUBPRO_POM_URL" );
        submitAddMavenTwoProjectPage( pomUrl );
>>>>>>> refs/remotes/apache/trunk
        assertTextPresent( "Unknown error trying to build POM." );
    }

    /**
     * test with an inaccessible pom url
     */
    public void testInaccessiblePomUrl()
        throws Exception
    {
<<<<<<< HEAD
        String pomUrl = "http://localhost:9595/";
        submitAddMavenTwoProjectPage( pomUrl, false );
        assertTextPresent( "POM file does not exist. Either the POM you specified or one of its modules does not exist." );
=======
        String pomUrl = baseUrl + "/inaccessible-pom/";
        submitAddMavenTwoProjectPage( pomUrl );
        assertTextPresent(
            "POM file does not exist. Either the POM you specified or one of its modules does not exist." );
>>>>>>> refs/remotes/apache/trunk
    }

    /**
     * test cancel button
     */
    public void testCancelButton()
    {
        goToAboutPage();
        goToAddMavenTwoProjectPage();
        clickButtonWithValue( "Cancel" );
        assertAboutPage();
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddMavenTwoProject" } )
    public void testDeleteMavenTwoProject()
        throws Exception
    {
        String M2_PROJ_GRP_NAME = getProperty( "M2_DELETE_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_SCM_ROOT_URL = getProperty( "M2_DELETE_PROJ_GRP_SCM_ROOT_URL" );
        goToProjectGroupsSummaryPage();
        
        // delete project - delete icon
        addMaven2Project( M2_PROJ_GRP_NAME );
        clickLinkWithText( M2_PROJ_GRP_NAME );

        assertPage( "Continuum - Project Group" );
        assertTextPresent( M2_PROJ_GRP_SCM_ROOT_URL );

        // TODO: this doesn't always seem to work, perhaps because of changes in the way icons are displayed
        // wait for project to finish checkout
        waitForProjectCheckout();
=======
    public void testDeleteMavenTwoProject()
        throws Exception
    {
        goToProjectGroupsSummaryPage();

        addMavenTwoProject( pomUrl, pomUsername, pomPassword, null, true );
        goToProjectGroupsSummaryPage();
        assertLinkPresent( projectGroupName );
        clickLinkWithText( projectGroupName );

        assertPage( "Continuum - Project Group" );
        assertTextPresent( projectGroupScmRootUrl );

        // wait for project to finish checkout
        waitForProjectCheckout();
        waitPage();
>>>>>>> refs/remotes/apache/trunk

        clickLinkWithXPath( "//tbody/tr['0']/td['10']/a/img[@alt='Delete']" );
        assertTextPresent( "Delete Continuum Project" );
        clickButtonWithValue( "Delete" );
        assertPage( "Continuum - Project Group" );
        assertTextNotPresent( "Unable to delete project" );
<<<<<<< HEAD
        assertLinkNotPresent( M2_PROJ_GRP_NAME );
        assertTextNotPresent( M2_PROJ_GRP_SCM_ROOT_URL );

        // remove group for next test
        removeProjectGroup( M2_PROJ_GRP_NAME );
        assertLinkNotPresent( M2_PROJ_GRP_NAME );

        // delete project - "Delete Project(s)" button
        addMaven2Project( M2_PROJ_GRP_NAME );
        clickLinkWithText( M2_PROJ_GRP_NAME );

        assertPage( "Continuum - Project Group" );
        //wait for project to finish checkout
        waitForProjectCheckout();
=======
        assertLinkNotPresent( projectGroupName );
        assertTextNotPresent( projectGroupScmRootUrl );
    }

    public void testDeleteMavenTwoProjects()
        throws Exception
    {
        goToProjectGroupsSummaryPage();

        addMavenTwoProject( pomUrl, pomUsername, pomPassword, null, true );
        goToProjectGroupsSummaryPage();
        assertLinkPresent( projectGroupName );
        clickLinkWithText( projectGroupName );

        assertPage( "Continuum - Project Group" );

        //wait for project to finish checkout
        waitForProjectCheckout();
        waitPage();
>>>>>>> refs/remotes/apache/trunk

        checkField( "//tbody/tr['0']/td['0']/input[@name='selectedProjects']" );
        clickButtonWithValue( "Delete Project(s)" );
        assertTextPresent( "Delete Continuum Projects" );
        clickButtonWithValue( "Delete" );
        assertPage( "Continuum - Project Group" );
        assertTextNotPresent( "Unable to delete project" );
<<<<<<< HEAD
        assertLinkNotPresent( M2_PROJ_GRP_NAME );
        assertTextNotPresent( M2_PROJ_GRP_SCM_ROOT_URL );

        // remove project group
        removeProjectGroup( M2_PROJ_GRP_NAME );
        assertLinkNotPresent( M2_PROJ_GRP_NAME );
    }

    public void testBuildProjectGroupNoBuildAgentConfigured()
        throws Exception
    {
        String M2_PROJ_GRP_NAME = getProperty( "M2_DELETE_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_DELETE_PROJ_GRP_ID" );
    
        try
        {
            enableDistributedBuilds();
            addMaven2Project( M2_PROJ_GRP_NAME );
            clickLinkWithText( M2_PROJ_GRP_NAME );
    
            assertPage( "Continuum - Project Group" );
    
            showProjectGroup( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, "" );
            clickButtonWithValue( "Build all projects" );

            assertTextPresent( "Unable to build projects because no build agent is configured" );

            removeProjectGroup( M2_PROJ_GRP_NAME );
            assertLinkNotPresent( M2_PROJ_GRP_NAME );
        }
        finally
        {
            disableDistributedBuilds();
        }
    }

    @Test( dependsOnMethods = { "testDeleteMavenTwoProject", "testAddBuildAgent" } )
    public void testProjectGroupAllBuildSuccessWithDistributedBuilds()
        throws Exception
    {
        String M2_PROJ_GRP_NAME = getProperty( "M2_DELETE_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_DELETE_PROJ_GRP_ID" );

        try
        {
            enableDistributedBuilds();

            addMaven2Project( M2_PROJ_GRP_NAME );
            clickLinkWithText( M2_PROJ_GRP_NAME );
    
            assertPage( "Continuum - Project Group" );
    
            showProjectGroup( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, "" );
            clickButtonWithValue( "Build all projects" );

            buildProjectGroup( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, "", M2_PROJ_GRP_NAME );
        }
        finally
        {
            disableDistributedBuilds();   
        }
    }

    @Test( dependsOnMethods = { "testAddBuildAgentGroupWithEmptyBuildAgent", "testAddBuildEnvironmentWithBuildAgentGroup" } )
    public void testProjectGroupNoBuildAgentConfiguredInBuildAgentGroup()
        throws Exception
    {
        String M2_PROJ_GRP_NAME = getProperty( "M2_DELETE_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_DELETE_PROJ_GRP_ID" );
        String BUILD_ENV_NAME = getProperty( "BUILD_ENV_NAME" );

        try
        {
            enableDistributedBuilds();
            addMaven2Project( M2_PROJ_GRP_NAME );
            clickLinkWithText( M2_PROJ_GRP_NAME );

            assertPage( "Continuum - Project Group" );

            goToGroupBuildDefinitionPage( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, "" );
            clickImgWithAlt( "Edit" );
            assertAddEditBuildDefinitionPage();
            selectValue( "profileId", BUILD_ENV_NAME );
            submit();
            assertGroupBuildDefinitionPage( M2_PROJ_GRP_NAME );

            clickLinkWithText( "Project Group Summary" );
            clickButtonWithValue( "Build all projects" );

            assertTextPresent( "Unable to build projects because no build agent is configured in the build agent group" );

            removeProjectGroup( M2_PROJ_GRP_NAME );
            assertLinkNotPresent( M2_PROJ_GRP_NAME );
        }
        finally
        {
            disableDistributedBuilds();
        }
    }

    public void testBuildMaven2ProjectWithTag()
        throws Exception
    {
        String M2_POM_URL = getProperty( "M2_PROJ_WITH_TAG_POM_URL" );
        String M2_POM_USERNAME = getProperty( "M2_POM_USERNAME" );
        String M2_POM_PASSWORD = getProperty( "M2_POM_PASSWORD" );
    
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_WITH_TAG_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_PROJ_WITH_TAG_PROJ_GRP_ID" );
        String M2_PROJ_GRP_DESCRIPTION = "";
    
        addMavenTwoProject( M2_POM_URL, M2_POM_USERNAME, M2_POM_PASSWORD, null, true );
        assertProjectGroupSummaryPage( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION );
    
        buildProjectGroup( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION, M2_PROJ_GRP_NAME );
    
        removeProjectGroup( M2_PROJ_GRP_NAME );
        assertLinkNotPresent( M2_PROJ_GRP_NAME );
    }
    
    @Test( dependsOnMethods = { "testAddBuildAgent" } )
    public void testBuildMaven2ProjectWithTagDistributedBuild()
        throws Exception
    {
        String M2_POM_URL = getProperty( "M2_PROJ_WITH_TAG_POM_URL" );
        String M2_POM_USERNAME = getProperty( "M2_POM_USERNAME" );
        String M2_POM_PASSWORD = getProperty( "M2_POM_PASSWORD" );
    
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_WITH_TAG_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_PROJ_WITH_TAG_PROJ_GRP_ID" );
        String M2_PROJ_GRP_DESCRIPTION = "";
    
        try
        {
            enableDistributedBuilds();
        
            addMavenTwoProject( M2_POM_URL, M2_POM_USERNAME, M2_POM_PASSWORD, null, true );
            assertProjectGroupSummaryPage( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION );
        
            buildProjectGroup( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION, M2_PROJ_GRP_NAME );
        
            removeProjectGroup( M2_PROJ_GRP_NAME );
            assertLinkNotPresent( M2_PROJ_GRP_NAME );
        }
        finally
        {
            disableDistributedBuilds();
        }
    }

    private void addMaven2Project( String groupName )
        throws Exception
    {
        String M2_POM_URL = getProperty( "M2_DELETE_POM_URL" );
        String M2_POM_USERNAME = getProperty( "M2_POM_USERNAME" );
        String M2_POM_PASSWORD = getProperty( "M2_POM_PASSWORD" );
        String M2_PROJ_GRP_DESCRIPTION = getProperty( "M2_DELETE_PROJ_GRP_DESCRIPTION" );
        
        addMavenTwoProject( M2_POM_URL, M2_POM_USERNAME, M2_POM_PASSWORD, null, true );
        goToProjectGroupsSummaryPage();
        assertLinkPresent( groupName );
=======
        assertLinkNotPresent( projectGroupName );
        assertTextNotPresent( projectGroupScmRootUrl );
    }

    public void testBuildMaven2ProjectWithTag()
        throws Exception
    {
        pomUrl = getProperty( "MAVEN2_PROJECT_WITH_TAG_POM_URL" );
        pomUsername = "";
        pomPassword = "";

        projectGroupName = getProperty( "MAVEN2_PROJECT_WITH_TAG_POM_PROJECT_GROUP_NAME" );
        projectGroupId = getProperty( "MAVEN2_PROJECT_WITH_TAG_POM_PROJECT_GROUP_ID" );
        projectGroupDescription = getProperty( "MAVEN2_PROJECT_WITH_TAG_POM_PROJECT_GROUP_DESCRIPTION" );

        addMavenTwoProject( pomUrl, pomUsername, pomPassword, null, true );
        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );

        buildProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, projectGroupName, true );
>>>>>>> refs/remotes/apache/trunk
    }
}
