package org.apache.continuum.web.test;

<<<<<<< HEAD
import org.apache.continuum.web.test.parent.AbstractUserRolesManagementTest;
import org.testng.annotations.Test;

@Test( groups = { "userroles" }, dependsOnMethods = { "testWithCorrectUsernamePassword" }, sequential = true )
public class UserRolesManagementTest
    extends AbstractUserRolesManagementTest
{
=======
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

import org.apache.continuum.web.test.parent.AbstractUserRolesManagementTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test( groups = {"userroles"}, sequential = true )
public class UserRolesManagementTest
    extends AbstractUserRolesManagementTest
{

    public static final String TEST_GROUP = "UserRoles Test Group";

    private List<String> usernames = new ArrayList<String>();

>>>>>>> refs/remotes/apache/trunk
    public void testBasicAddDeleteUser()
    {
        username = getProperty( "GUEST_USERNAME" );
        fullname = getProperty( "GUEST_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
        deleteUser( username, fullname, getUserEmail() );
        clickLinkWithText( "Logout" );
        login( getAdminUsername(), getAdminPassword() );
    }
    
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
        deleteUser( username );
        clickLinkWithText( "Logout" );
    }

>>>>>>> refs/remotes/apache/trunk
    /*
     * GUEST USER ROLE
     * Guest Role could only view the About Page. Project Groups should not be shown when clicking
     * Show Project Group link.
    */
<<<<<<< HEAD
    @Test( dependsOnMethods = { "testBasicAddDeleteUser" } )
=======
    @Test( dependsOnMethods = {"testBasicAddDeleteUser"} )
>>>>>>> refs/remotes/apache/trunk
    public void testAddUserWithGuestRole()
    {
        username = getProperty( "GUEST_USERNAME" );
        fullname = getProperty( "GUEST_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        //checkUserRoleWithValue( fullname );
        clickLinkWithLocator( "addRolesToUser_addNDSelectedRoles", false );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        //assertTextPresent( "Password successfully changed" );        
        clickLinkWithText( "Logout" );
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddUserWithGuestRole" } )
=======
    @Test( dependsOnMethods = {"testAddUserWithGuestRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testGuestUserRoleFunction()
    {
        username = getProperty( "GUEST_USERNAME" );
        fullname = getProperty( "GUEST_FULLNAME" );
<<<<<<< HEAD
        login( username, getUserRoleNewPassword() );
=======
        loginAs( username, getUserRoleNewPassword() );
>>>>>>> refs/remotes/apache/trunk
        assertLeftNavMenuWithRole( fullname );
        goToAboutPage();
        clickLinkWithText( "Show Project Groups" );
        assertTextPresent( "Project Groups list is empty" );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
=======
>>>>>>> refs/remotes/apache/trunk
    }


    /*
     * REGISTERED USER ROLE
     * Registered User Role could only view the About Page. Project Groups should not be shown when clicking
     * Show Project Group link.
    */
<<<<<<< HEAD
    @Test( dependsOnMethods = { "testBasicAddDeleteUser" , "testGuestUserRoleFunction" } )
=======
    @Test( dependsOnMethods = {"testBasicAddDeleteUser", "testGuestUserRoleFunction"} )
>>>>>>> refs/remotes/apache/trunk
    public void testAddUserWithRegisteredUserRole()
    {
        username = getProperty( "REGISTERED_USERNAME" );
        fullname = getProperty( "REGISTERED_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );

        clickLinkWithText( "Logout" );
        // assertTextPresent("You are already logged in.");
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddUserWithRegisteredUserRole" } )
=======
    @Test( dependsOnMethods = {"testAddUserWithRegisteredUserRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testRegisteredRoleFunction()
    {
        username = getProperty( "REGISTERED_USERNAME" );
        fullname = getProperty( "REGISTERED_FULLNAME" );
        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        goToAboutPage();
        clickLinkWithText( "Show Project Groups" );
        assertTextPresent( "Project Groups list is empty." );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
=======
>>>>>>> refs/remotes/apache/trunk
    }

    /*
     * SYSTEM ADMINISTRATOR ROLE
     * Has access to all functions in the application.
     *
     * The following tests only asserts elements that could be shown 
     * when system admin user is logged in since the user that is used 
     * to test the other functionalities is a system admin user.
     */
<<<<<<< HEAD
    @Test( dependsOnMethods = { "testBasicAddDeleteUser" , "testRegisteredRoleFunction" } )
=======
    @Test( dependsOnMethods = {"testBasicAddDeleteUser", "testRegisteredRoleFunction"} )
>>>>>>> refs/remotes/apache/trunk
    public void testAddUserWithSystemAdminRole()
    {
        username = getProperty( "SYSAD_USERNAME" );
        fullname = getProperty( "SYSAD_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );

        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );

        clickLinkWithText( "Logout" );
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddUserWithSystemAdminRole" } )
    public void testSystemAdminRoleFunction()
    {
	    username = getProperty( "SYSAD_USERNAME" );
=======
    @Test( dependsOnMethods = {"testAddUserWithSystemAdminRole"} )
    public void testSystemAdminRoleFunction()
    {
        username = getProperty( "SYSAD_USERNAME" );
>>>>>>> refs/remotes/apache/trunk
        fullname = getProperty( "SYSAD_FULLNAME" );
        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Show Project Groups" );
        assertTextNotPresent( "Project Groups list is empty." );
        assertLinkPresent( "Default Project Group" );
<<<<<<< HEAD
	
        clickLinkWithText( "Logout" );
        login( getAdminUsername(), getAdminPassword() );
=======

        clickLinkWithText( "Logout" );
>>>>>>> refs/remotes/apache/trunk
    }

    /* 
     * USER ADMIN ROLE
     * User Admin role could only add/edit/delete users and can view user Roles. Cannot view Project Groups
     * but can assign a User to a project.
     *
     */
<<<<<<< HEAD
    @Test( dependsOnMethods = { "testBasicAddDeleteUser" , "testSystemAdminRoleFunction" } )
=======
    @Test( dependsOnMethods = {"testBasicAddDeleteUser", "testSystemAdminRoleFunction"} )
>>>>>>> refs/remotes/apache/trunk
    public void testAddUserWithUserAdminRole()
    {
        username = getProperty( "USERADMIN_USERNAME" );
        fullname = getProperty( "USERADMIN_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
		clickButtonWithValue( "Submit" );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
>>>>>>> refs/remotes/apache/trunk

        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );

        clickLinkWithText( "Logout" );
    }
<<<<<<< HEAD
    
    @Test( dependsOnMethods = { "testAddUserWithUserAdminRole" } )
=======

    @Test( dependsOnMethods = {"testAddUserWithUserAdminRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserAdminFunction()
    {
        username = getProperty( "USERADMIN_USERNAME" );
        fullname = getProperty( "USERADMIN_FULLNAME" );
        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Show Project Groups" );
        assertTextPresent( "Project Groups list is empty." );
        // add user
        clickLinkWithText( "Users" );
        clickButtonWithValue( "Create New User" );
<<<<<<< HEAD
	    assertCreateUserPage();
	    setFieldValue( "user.username", "guest0" );
=======
        assertCreateUserPage();
        setFieldValue( "user.username", "guest0" );
>>>>>>> refs/remotes/apache/trunk
        setFieldValue( "user.fullName", "guest0" );
        setFieldValue( "user.email", "guest0@guest0.com" );
        setFieldValue( "user.password", "pass" );
        setFieldValue( "user.confirmPassword", "pass" );
        submit();
<<<<<<< HEAD
        assertUserRolesPage( );
=======
        assertUserRolesPage();
>>>>>>> refs/remotes/apache/trunk
        clickButtonWithValue( "Submit" );
        selectValue( "name=ec_rd", "50" );
        waitPage();
        // delete user	
<<<<<<< HEAD
        deleteUser( "guest0", "guest0", "guest0@guest0.com" );	
        // TODO edit user

        clickLinkWithText( "Logout" );
        login( getAdminUsername(), getAdminPassword() );
=======
        deleteUser( "guest0" );
        // TODO edit user

        clickLinkWithText( "Logout" );
>>>>>>> refs/remotes/apache/trunk
    }

    /*
     * CONTINUUM GROUP PROJECT ADMIN
     * - Can Add/Edit/Delete Project Group, can Add/Edit/Delete projects, can assign Users
     *    roles to existing projects, can add/edit/delete schedules, can view existing roles for the
     *    projects, can build/release projects
     * - Cannot add users, --- --- ---
     */
<<<<<<< HEAD
    @Test( dependsOnMethods = { "testBasicAddDeleteUser" , "testUserAdminFunction" } )
=======
    @Test( dependsOnMethods = {"testBasicAddDeleteUser", "testUserAdminFunction"} )
>>>>>>> refs/remotes/apache/trunk
    public void testAddUserWithContinuumGroupProjectAdminRole()
    {
        username = getProperty( "GROUPPROJECTADMIN_USERNAME" );
        fullname = getProperty( "GROUPPROJECTADMIN_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );

        // enable distributed build
        clickLinkWithText( "Configuration" );
<<<<<<< HEAD
        clickLinkWithLocator( "configuration_distributedBuildEnabled", false );
=======
        checkField( "distributedBuildEnabled" );
>>>>>>> refs/remotes/apache/trunk
        clickButtonWithValue( "Save" );

        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );

<<<<<<< HEAD
	    assertProjectAdministratorAccess();

        clickLinkWithText( "Logout" );

        login( getAdminUsername(), getAdminPassword() );
        // disable distributed build
        clickLinkWithText( "Configuration" );
        clickLinkWithLocator( "configuration_distributedBuildEnabled", false );
=======
        assertProjectAdministratorAccess();

        clickLinkWithText( "Logout" );

        loginAsAdmin();
        // disable distributed build
        clickLinkWithText( "Configuration" );
        uncheckField( "distributedBuildEnabled" );
>>>>>>> refs/remotes/apache/trunk
        clickButtonWithValue( "Save" );

        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertProjectAdministratorAccess();

        clickLinkWithText( "Logout" );
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddUserWithContinuumGroupProjectAdminRole" } )
    public void testContinuumGroupProjectAdmin_AddProjectGroup() throws Exception
=======
    @Test( dependsOnMethods = {"testAddUserWithContinuumGroupProjectAdminRole"} )
    public void testContinuumGroupProjectAdmin_AddProjectGroup()
        throws Exception
>>>>>>> refs/remotes/apache/trunk
    {
        username = getProperty( "GROUPPROJECTADMIN_USERNAME" );
        fullname = getProperty( "GROUPPROJECTADMIN_FULLNAME" );
        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Show Project Groups" );
        assertTextNotPresent( "Project Groups list is empty." );
        // test add project group
        clickButtonWithValue( "Add Project Group" );
<<<<<<< HEAD
        setFieldValue( "name", "Test Group" );
        setFieldValue( "groupId", "Test Group" );
=======
        setFieldValue( "name", TEST_GROUP );
        setFieldValue( "groupId", TEST_GROUP );
>>>>>>> refs/remotes/apache/trunk
        setFieldValue( "description", "testing project group" );
        submit();
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testContinuumGroupProjectAdmin_AddProjectGroup" } )
    public void testContinuumGroupProjectAdmin_AddProjectToProjectGroup() throws Exception
    {
        clickLinkWithText( "Test Group" );
        clickButtonWithValue( "Add" );	
=======
    @Test( dependsOnMethods = {"testContinuumGroupProjectAdmin_AddProjectGroup"} )
    public void testContinuumGroupProjectAdmin_AddProjectToProjectGroup()
        throws Exception
    {
        clickLinkWithText( TEST_GROUP );
        clickButtonWithValue( "Add" );
>>>>>>> refs/remotes/apache/trunk
        assertAddMavenTwoProjectPage();
        setFieldValue( "m2PomUrl", getProperty( "M2_POM_URL" ) );
        clickButtonWithValue( "Add" );
        waitAddProject( "Continuum - Project Group" );
        assertTextPresent( "ContinuumBuildQueueTestData" );
        waitForProjectCheckout();
    }
<<<<<<< HEAD
    
    @Test( dependsOnMethods = { "testContinuumGroupProjectAdmin_AddProjectToProjectGroup" } )
    public void testContinuumGroupProjectAdmin_BuildProject() throws Exception
    {
        buildProjectGroup( "Test Group", "Test Group", "testing project group", "ContinuumBuildQueueTestData" );
    }

    @Test( dependsOnMethods = { "testContinuumGroupProjectAdmin_BuildProject" } )
=======

    @Test( dependsOnMethods = {"testContinuumGroupProjectAdmin_AddProjectToProjectGroup"} )
    public void testContinuumGroupProjectAdmin_BuildProject()
        throws Exception
    {
        buildProjectGroup( TEST_GROUP, TEST_GROUP, "testing project group", "ContinuumBuildQueueTestData", true );
    }

    @Test( dependsOnMethods = {"testContinuumGroupProjectAdmin_BuildProject"} )
>>>>>>> refs/remotes/apache/trunk
    public void testContinuumGroupProjectAdmin_AssignUserToAGroup()
    {
        clickLinkWithText( "Users" );
        clickLinkWithText( "guest1" );
        clickLinkWithText( "Edit Roles" );
        checkUserRoleWithValue( "Guest" );
<<<<<<< HEAD
        checkResourceRoleWithValue( "Project Developer - Test Group" );
        submit();
        clickLinkWithText( "Logout" );
        login( getAdminUsername(), getAdminPassword() );
=======
        checkResourceRoleWithValue( "Project Developer - " + TEST_GROUP );
        submit();
        clickLinkWithText( "Logout" );
>>>>>>> refs/remotes/apache/trunk
    }

    /*
     * Uncomment the lines below to release a Project provided that you add
     * the values under RELEASE A PROJECT in testng.properties file (project's pom url, access to project to be released.)
    	
    @Test( dependsOnMethods = { "testContinuumGroupProjectAdmin_AssignUserToAGroup" } )
    public void testContinuumGroupProjectAdmin_ReleaseProject() throws Exception
    {
	String projectUrl = getProperty( "PROJECT_URL_TO_BE_RELEASED" );
	String projectName = getProperty( "PROJECT_NAME_TO_BE_RELEASED" );
	String projectUsername = getProperty( "PROJECT_USERNAME" );
	String projectPassword = getProperty( "PROJECT_USERNAME" );
	// add a project group
	clickLinkWithText( "Show Project Groups" );
	clickButtonWithValue( "Add Project Group" );
	setFieldValue( "name", "Project Group" );
        setFieldValue( "groupId", "Project Group" );
        setFieldValue( "description", "project group for projects to be released" );
	submit();
	// add a project to a project group
	clickLinkWithText( "Project Group" );
	clickButtonWithValue( "Add" );
	assertAddMavenTwoProjectPage();
	setFieldValue( "m2PomUrl", projectUrl );
	// set username and password here
	setFieldValue( "scmUsername", projectUsername );
	setFieldValue( "scmPassword", projectPassword );
	clickButtonWithValue( "Add" );
	String title;
	boolean success = true;
	if ( success )
        {
            title = "Continuum - Project Group";
        }
        else
        {
<<<<<<< HEAD
            title = "Continuum - Add Maven 2 Project";
=======
            title = "Continuum - Add Maven Project";
>>>>>>> refs/remotes/apache/trunk
        }
        waitAddProject( title );
	// build the project added in the project group
	buildProjectGroup( "Project Group", "Project Group", "project group for projects to be released", projectName );
	// release the project
	clickButtonWithValue( "Release" );
	clickLinkWithText( "Logout" );
        login( getAdminUsername(), getAdminPassword() );
    }
    */

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testContinuumGroupProjectAdmin_AssignUserToAGroup" } )
=======
    @Test( dependsOnMethods = {"testContinuumGroupProjectAdmin_AssignUserToAGroup"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumGroupProjectDeveloperRole()
    {
        username = getProperty( "GROUPPROJECTDEVELOPER_USERNAME" );
        fullname = getProperty( "GROUPPROJECTDEVELOPER_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumGroupProjectDeveloperRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumGroupProjectDeveloperRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumGroupProjectUserRole()
    {
        username = getProperty( "GROUPPROJECTUSER_USERNAME" );
        fullname = getProperty( "GROUPPROJECTUSER_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumGroupProjectUserRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumGroupProjectUserRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumManageBuildEnvironmentRole()
    {
        username = getProperty( "MANAGEBUILDENVIRONMENT_USERNAME" );
        fullname = getProperty( "MANAGEBUILDENVIRONMENT_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
<<<<<<< HEAD
        assertTextPresent( "Password successfully changed" );	
=======
        assertTextPresent( "Password successfully changed" );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumManageBuildEnvironmentRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumManageBuildEnvironmentRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumManageBuildTemplatesRole()
    {
        username = getProperty( "MANAGEBUILDTEMPLATES_USERNAME" );
        fullname = getProperty( "MANAGEBUILDTEMPLATES_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumManageBuildTemplatesRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumManageBuildTemplatesRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumManageInstallationsRole()
    {
        username = getProperty( "MANAGEINSTALLATIONS_USERNAME" );
        fullname = getProperty( "MANAGEINSTALLATIONS_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumManageInstallationsRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumManageInstallationsRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumManageLocalRepoRole()
    {
        username = getProperty( "MANAGELOCALREPOS_USERNAME" );
        fullname = getProperty( "MANAGELOCALREPOS_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumManageLocalRepoRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumManageLocalRepoRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumManagePurgingRole()
    {
        username = getProperty( "MANAGEPURGING_USERNAME" );
        fullname = getProperty( "MANAGEPURGING_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumManagePurgingRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumManagePurgingRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumManageQueuesRole()
    {
        username = getProperty( "MANAGEQUEUES_USERNAME" );
        fullname = getProperty( "MANAGEQUEUES_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
	    clickButtonWithValue( "Submit" );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumManageQueuesRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumManageQueuesRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithContinuumManageSchedulingRole()
    {
        username = getProperty( "MANAGESCHEDULING_USERNAME" );
        fullname = getProperty( "MANAGESCHEDULING_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkUserRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithContinuumManageSchedulingRole" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithContinuumManageSchedulingRole"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithProjectAdminDefaultProjectGroup()
    {
        username = getProperty( "PROJECTADMINISTRATOR_DEFAULTPROJECTGROUP_USERNAME" );
        fullname = getProperty( "PROJECTADMINISTRATOR_DEFAULTPROJECTGROUP_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkResourceRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithProjectAdminDefaultProjectGroup" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithProjectAdminDefaultProjectGroup"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithProjectDevDefaultProjectGroup()
    {
        username = getProperty( "PROJECTDEVELOPER_DEFAULTPROJECTGROUP_USERNAME" );
        fullname = getProperty( "PROJECTDEVELOPER_DEFAULTPROJECTGROUP_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkResourceRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

    @Test( dependsOnMethods = { "testUserWithProjectDevDefaultProjectGroup" } )
=======
    }

    @Test( dependsOnMethods = {"testUserWithProjectDevDefaultProjectGroup"} )
>>>>>>> refs/remotes/apache/trunk
    public void testUserWithProjectUserDefaultProjectGroup()
    {
        username = getProperty( "PROJECTUSER_DEFAULTPROJECTGROUP_USERNAME" );
        fullname = getProperty( "PROJECTUSER_DEFAULTPROJECTGROUP_FULLNAME" );

<<<<<<< HEAD
        createUser( username, fullname, getUserEmail(), getUserRolePassword(), true );
=======
        createUser( username, fullname, getUserEmail(), getUserRolePassword() );
>>>>>>> refs/remotes/apache/trunk
        assertCreatedUserInfo( username );
        checkResourceRoleWithValue( fullname );
        clickButtonWithValue( "Submit" );
        clickLinkWithText( "Logout" );

        login( username, getUserRolePassword() );
        changePassword( getUserRolePassword(), getUserRoleNewPassword() );
        assertTextPresent( "Password successfully changed" );
        clickLinkWithText( "Logout" );

        login( username, getUserRoleNewPassword() );
        assertLeftNavMenuWithRole( fullname );
        clickLinkWithText( "Logout" );
<<<<<<< HEAD
        login( getAdminUsername(), getAdminPassword() );
    }

=======
    }

    @AfterMethod
    public void trackUserToDelete()
    {
        // record to delete at end, as some are used across dependent tests
        // TODO: refactor!
        usernames.add( username );
    }

    @AfterClass
    public void cleanup()
    {
        loginAsAdmin();
        if ( !isTextPresent( "List of Users" ) )
        {
            clickLinkWithText( "Users" );
        }

        for ( String username : usernames )
        {
            deleteUser( username, false );
        }

        removeProjectGroup( TEST_GROUP, false );
    }
>>>>>>> refs/remotes/apache/trunk
}
