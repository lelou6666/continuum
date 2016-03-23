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

<<<<<<< HEAD
import org.apache.continuum.web.test.parent.AbstractContinuumTest;
import org.apache.continuum.web.test.parent.AbstractSeleniumTest;
=======
import org.apache.continuum.web.test.parent.AbstractAdminTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
>>>>>>> refs/remotes/apache/trunk
import org.testng.annotations.Test;

/**
 * Based on ProjectGroupTest of Emmanuel Venisse test.
 *
 * @author José Morales Martínez
 */
@Test( groups = {"projectGroup"} )
public class ProjectGroupTest
    extends AbstractAdminTest
{

    public static final String TEST_PROJECT_NAME = "ContinuumBuildQueueTestData";

    private String projectGroupName;

    private String projectGroupId;

    private String projectGroupDescription;

    @BeforeMethod
    protected void setUp()
        throws Exception
    {
        projectGroupName = getProperty( "TEST_PROJ_GRP_NAME" );
        projectGroupId = getProperty( "TEST_PROJ_GRP_ID" );
        projectGroupDescription = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
    }

    @AfterClass
    public void tearDown()
    {
        removeProjectGroup( projectGroupName, false );
    }

    public void testAddProjectGroup()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
=======
        addProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, true );
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
    }

    public void testAddProjectGroupWithInvalidValues()
        throws Exception
    {
        String name = "!@#$<>?etch";
        String groupId = "-!@#<>etc";
        String description = "![]<>'^&etc";
>>>>>>> refs/remotes/apache/trunk

        addProjectGroup( name, groupId, description, false );
        assertTextPresent( "Name contains invalid characters." );
        assertTextPresent( "Id contains invalid characters." );
    }

    public void testAddProjectGroupWithDashedGroupId()
        throws Exception
    {
<<<<<<< HEAD
        String TEST2_PROJ_GRP_NAME = getProperty( "TEST2_PROJ_GRP_NAME" );
        String TEST2_PROJ_GRP_ID = getProperty( "TEST2_PROJ_GRP_ID" );
        String TEST2_PROJ_GRP_DESCRIPTION = getProperty( "TEST2_PROJ_GRP_DESCRIPTION" );
=======
        String name = "Test Project Group with Dashes";
        String groupId = "com.example.this-is-a-long-group-id";
        String description = "";
>>>>>>> refs/remotes/apache/trunk

        try {
            addProjectGroup( name, groupId, description, true );
        } finally {
            removeProjectGroup( name, false );
        }
    }

    public void testAddProjectGroupWithPunctuation()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String DEFAULT_PROJ_GRP_NAME = getProperty( "DEFAULT_PROJ_GRP_NAME" );
        String DEFAULT_PROJ_GRP_ID = getProperty( "DEFAULT_PROJ_GRP_NAME" );
        String DEFAULT_PROJ_GRP_DESCRIPTION = getProperty( "DEFAULT_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );

        // TODO: need to wait for checkout to complete. Can we add a special IT type of project that doesn't require checkout?
        //       currently we get away with it due to the usualy duration between the dependant test and this test
        // move the project of the test project group to the default project group
        moveProjectToProjectGroup( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION, M2_PROJ_GRP_NAME,
                                   DEFAULT_PROJ_GRP_NAME );
        showProjectGroup( DEFAULT_PROJ_GRP_NAME, DEFAULT_PROJ_GRP_ID, DEFAULT_PROJ_GRP_DESCRIPTION );
        assertTextPresent( "Member Projects" );
        // Restore project to test project group
        moveProjectToProjectGroup( DEFAULT_PROJ_GRP_NAME, DEFAULT_PROJ_GRP_ID, DEFAULT_PROJ_GRP_DESCRIPTION,
                                   M2_PROJ_GRP_NAME, TEST_PROJ_GRP_NAME );
        showProjectGroup( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        assertTextPresent( "Member Projects" );
=======
        String name = "Test :: Test Project Group (with Punctuation)";
        String groupId = "com.example.test";
        String description = "";

        try {
            addProjectGroup( name, groupId, description, true );
        } finally {
            removeProjectGroup( name, false );
        }
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddProjectGroupWithEmptyString()
        throws Exception
    {
        addProjectGroup( "", "", "", false );
        assertTextPresent( "Project Group Name is required" );
        assertTextPresent( "Project Group ID is required" );
    }

    public void testAddProjectGroupWithWhitespaceString()
        throws Exception
    {
        addProjectGroup( " ", " ", " ", false );
        assertTextPresent( "Project Group Name is required" );
        assertTextPresent( "Project Group ID is required" );
    }

    @Test( dependsOnMethods = {"testAddProjectGroup"} )
    public void testEditProjectGroupWithValidValues()
        throws Exception
    {
<<<<<<< HEAD
        final String sNewProjectName = "New Project Group Name";
        final String sNewProjectDescription = "New Project Group Description";

        String TEST2_PROJ_GRP_NAME = getProperty( "TEST2_PROJ_GRP_NAME" );
        String TEST2_PROJ_GRP_ID = getProperty( "TEST2_PROJ_GRP_ID" );
        String TEST2_PROJ_GRP_DESCRIPTION = getProperty( "TEST2_PROJ_GRP_DESCRIPTION" );
=======
        final String newName = "Test :: New Project Group Name (with valid values)";
        final String newDescription = "New Project Group Description";
>>>>>>> refs/remotes/apache/trunk

        editProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, newName, newDescription );
        assertProjectGroupSummaryPage( newName, projectGroupId, newDescription );

        editProjectGroup( newName, projectGroupId, newDescription, projectGroupName, projectGroupDescription );
        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );
    }

    @Test( dependsOnMethods = {"testAddProjectGroup"} )
    public void testEditProjectGroupWithInvalidValues()
        throws Exception
    {
        editProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, " ", projectGroupDescription );
        assertTextPresent( "Project Group Name is required" );
    }

    @Test( dependsOnMethods = {"testAddProjectGroup"} )
    public void testEditProjectGroupWithXSS()
        throws Exception
    {
        String newName = "<script>alert('XSS')</script>";
        String newDescription = "<script>alert('XSS')</script>";
        editProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, newName, newDescription );
        assertTextPresent( "Name contains invalid characters." );
    }

    public void testDeleteProjectGroup()
        throws Exception
    {
        String name = getProperty( "TEST_DELETE_GRP_NAME" );
        String groupId = getProperty( "TEST_DELETE_GRP_ID" );
        String description = getProperty( "TEST_DELETE_GRP_DESCRIPTION" );

        // delete group - delete icon
        addProjectGroup( name, groupId, description, true );
        assertLinkPresent( name );
        clickLinkWithXPath( "//tbody/tr['0']/td['4']/a/img[@alt='Delete Group']" );
        assertTextPresent( "Project Group Removal" );
        clickButtonWithValue( "Delete" );
        assertProjectGroupsSummaryPage();
        assertLinkNotPresent( name );

<<<<<<< HEAD
        String TEST2_PROJ_GRP_NAME = getProperty( "TEST2_PROJ_GRP_NAME" );
        String TEST2_PROJ_GRP_ID = getProperty( "TEST2_PROJ_GRP_ID" );
        String TEST2_PROJ_GRP_DESCRIPTION = getProperty( "TEST2_PROJ_GRP_DESCRIPTION" );
        editProjectGroup( TEST2_PROJ_GRP_NAME, TEST2_PROJ_GRP_ID, TEST2_PROJ_GRP_DESCRIPTION, " ",
                          TEST2_PROJ_GRP_DESCRIPTION );
        assertTextPresent( "Project Group Name cannot contain spaces only" );
=======
        // delete group - "Delete Group" button
        addProjectGroup( name, groupId, description, true );
        assertLinkPresent( name );
        removeProjectGroup( name );
        assertLinkNotPresent( name );
        assertProjectGroupsSummaryPage();
        assertLinkNotPresent( name );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testProjectGroupMembers()
        throws Exception
    {
<<<<<<< HEAD
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_PROJ_GRP_ID" );
        String M2_PROJ_GRP_DESCRIPTION = getProperty( "M2_PROJ_GRP_DESCRIPTION" );
        buildProjectGroup( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, M2_PROJ_GRP_DESCRIPTION, M2_PROJ_GRP_NAME );
        clickButtonWithValue( "Release" );
        assertReleaseSuccess();
=======
        String name1 = getProperty( "TEST_PROJ_GRP_NAME_ONE" );
        String groupId1 = getProperty( "TEST_PROJ_GRP_ID_ONE" );
        String description1 = getProperty( "TEST_PROJ_GRP_DESCRIPTION_ONE" );
        String name2 = getProperty( "TEST_PROJ_GRP_NAME_TWO" );
        String groupId2 = getProperty( "TEST_PROJ_GRP_ID_TWO" );
        String description2 = getProperty( "TEST_PROJ_GRP_DESCRIPTION_TWO" );
        String name3 = getProperty( "TEST_PROJ_GRP_NAME_THREE" );
        String groupId3 = getProperty( "TEST_PROJ_GRP_ID_THREE" );
        String description3 = getProperty( "TEST_PROJ_GRP_DESCRIPTION_THREE" );

        addProjectGroup( name1, groupId1, description1, true, false );
        assertLinkPresent( name1 );

        addProjectGroup( name2, groupId2, description2, true, false );
        assertLinkPresent( name2 );

        addProjectGroup( name3, groupId3, description3, true, false );
        assertLinkPresent( name3 );

        createAndAddUserAsDeveloperToGroup( "username1", "user1", "user1@something.com", name1 );
        createAndAddUserAsDeveloperToGroup( "username2", "user2", "user2@something.com", name1 );
        createAndAddUserAsDeveloperToGroup( "username3", "user3", "user3@something.com", name2 );
        createAndAddUserAsDeveloperToGroup( "username4", "user4", "user4@something.com", name3 );

        showMembers( name1, groupId1, description1 );
        assertUserPresent( "username1", "user1", "user1@something.com" );
        assertUserPresent( "username2", "user2", "user2@something.com" );
        assertUserNotPresent( "username3", "user3", "user3@something.com" );
        assertUserNotPresent( "username4", "user4", "user4@something.com" );

        showMembers( name2, groupId2, description2 );
        assertUserNotPresent( "username1", "user1", "user1@something.com" );
        assertUserNotPresent( "username2", "user2", "user2@something.com" );
        assertUserPresent( "username3", "user3", "user3@something.com" );
        assertUserNotPresent( "username4", "user4", "user4@something.com" );

        showMembers( name3, groupId3, description3 );
        assertUserNotPresent( "username1", "user1", "user1@something.com" );
        assertUserNotPresent( "username2", "user2", "user2@something.com" );
        assertUserNotPresent( "username3", "user3", "user3@something.com" );
        assertUserPresent( "username4", "user4", "user4@something.com" );

        removeProjectGroup( name1 );
        assertLinkNotPresent( name1 );
        removeProjectGroup( name2 );
        assertLinkNotPresent( name2 );
        removeProjectGroup( name3 );
        assertLinkNotPresent( name3 );
    }

    public void testRemoveProjectFromMembers()
    {
        goToProjectGroupsSummaryPage();
        addProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, true, false );
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );

        if ( !isLinkPresent( TEST_PROJECT_NAME ) )
        {
            clickButtonWithValue( "Add" );
            assertAddMavenTwoProjectPage();
            setFieldValue( "m2PomUrl", getProperty( "M2_POM_URL" ) );
            clickButtonWithValue( "Add" );
            waitAddProject( "Continuum - Project Group" );
            assertTextPresent( TEST_PROJECT_NAME );
            waitForProjectCheckout();
        }

        clickLinkWithText( "Members" );
        assertTextPresent( TEST_PROJECT_NAME );
        clickImgWithAlt( "Delete" );

        assertTextPresent( "Delete Continuum Project" );
        clickButtonWithValue( "Delete" );

        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );
        assertTextNotPresent( TEST_PROJECT_NAME );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddProjectGroup" } )
    public void testDeleteProjectGroup()
        throws Exception
    {
        String TEST_GRP_NAME = getProperty( "TEST_DELETE_GRP_NAME" );
        String TEST_GRP_ID = getProperty( "TEST_DELETE_GRP_ID" );
        String TEST_GRP_DESCRIPTION = getProperty( "TEST_DELETE_GRP_DESCRIPTION" );
        
        // delete group - delete icon
        addProjectGroup( TEST_GRP_NAME, TEST_GRP_ID, TEST_GRP_DESCRIPTION, true );
        assertLinkPresent( TEST_GRP_NAME );
        clickLinkWithXPath( "//tbody/tr['0']/td['4']/a/img[@alt='Delete Group']" );
        assertTextPresent( "Project Group Removal" );
        clickButtonWithValue( "Delete" );
        assertProjectGroupsSummaryPage();
        assertLinkNotPresent( TEST_GRP_NAME );
        
        // delete group - "Delete Group" button
        addProjectGroup( TEST_GRP_NAME, TEST_GRP_ID, TEST_GRP_DESCRIPTION, true );
        assertLinkPresent( TEST_GRP_NAME );
        removeProjectGroup( TEST_GRP_NAME );
        assertLinkNotPresent( TEST_GRP_NAME );
        assertProjectGroupsSummaryPage();
        assertLinkNotPresent( TEST_GRP_NAME );
    }

    public void testProjectGroupMembers()
        throws Exception
    {
        String GRP_NAME_ONE = getProperty( "TEST_PROJ_GRP_NAME_ONE" );
        String GRP_ID_ONE = getProperty( "TEST_PROJ_GRP_ID_ONE" );
        String GRP_DESCRIPTION_ONE = getProperty( "TEST_PROJ_GRP_DESCRIPTION_ONE" );
        String GRP_NAME_TWO = getProperty( "TEST_PROJ_GRP_NAME_TWO" );
        String GRP_ID_TWO = getProperty( "TEST_PROJ_GRP_ID_TWO" );
        String GRP_DESCRIPTION_TWO = getProperty( "TEST_PROJ_GRP_DESCRIPTION_TWO" );
        String GRP_NAME_THREE = getProperty( "TEST_PROJ_GRP_NAME_THREE" );
        String GRP_ID_THREE = getProperty( "TEST_PROJ_GRP_ID_THREE" );
        String GRP_DESCRIPTION_THREE = getProperty( "TEST_PROJ_GRP_DESCRIPTION_THREE" );

        addProjectGroup( GRP_NAME_ONE, GRP_ID_ONE, GRP_DESCRIPTION_ONE, true );
        assertLinkPresent( GRP_NAME_ONE );

        addProjectGroup( GRP_NAME_TWO, GRP_ID_TWO, GRP_DESCRIPTION_TWO, true );
        assertLinkPresent( GRP_NAME_TWO );

        addProjectGroup( GRP_NAME_THREE, GRP_ID_THREE, GRP_DESCRIPTION_THREE, true );
        assertLinkPresent( GRP_NAME_THREE );

        createAndAddUserAsDeveloperToGroup( "username1", "user1", "user1@something.com", "password123", GRP_NAME_ONE );
        createAndAddUserAsDeveloperToGroup( "username2", "user2", "user2@something.com", "password123", GRP_NAME_ONE );
        createAndAddUserAsDeveloperToGroup( "username3", "user3", "user3@something.com", "password123", GRP_NAME_TWO );
        createAndAddUserAsDeveloperToGroup( "username4", "user4", "user4@something.com", "password123", GRP_NAME_THREE );

        showMembers( GRP_NAME_ONE, GRP_ID_ONE, GRP_DESCRIPTION_ONE );
        assertUserPresent( "username1", "user1", "user1@something.com" );
        assertUserPresent( "username2", "user2", "user2@something.com" );
        assertUserNotPresent( "username3", "user3", "user3@something.com" );
        assertUserNotPresent( "username4", "user4", "user4@something.com" );

        showMembers( GRP_NAME_TWO, GRP_ID_TWO, GRP_DESCRIPTION_TWO );
        assertUserNotPresent( "username1", "user1", "user1@something.com" );
        assertUserNotPresent( "username2", "user2", "user2@something.com" );
        assertUserPresent( "username3", "user3", "user3@something.com" );
        assertUserNotPresent( "username4", "user4", "user4@something.com" );

        showMembers( GRP_NAME_THREE, GRP_ID_THREE, GRP_DESCRIPTION_THREE );
        assertUserNotPresent( "username1", "user1", "user1@something.com" );
        assertUserNotPresent( "username2", "user2", "user2@something.com" );
        assertUserNotPresent( "username3", "user3", "user3@something.com" );
        assertUserPresent( "username4", "user4", "user4@something.com" );

        removeProjectGroup( GRP_NAME_ONE );
        assertLinkNotPresent( GRP_NAME_ONE );
        removeProjectGroup( GRP_NAME_TWO );
        assertLinkNotPresent( GRP_NAME_TWO );
        removeProjectGroup( GRP_NAME_THREE );
        assertLinkNotPresent( GRP_NAME_THREE );
    }
}
