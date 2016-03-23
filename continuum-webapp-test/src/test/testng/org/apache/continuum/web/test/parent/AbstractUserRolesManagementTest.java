package org.apache.continuum.web.test.parent;

<<<<<<< HEAD
import java.io.File;

import org.apache.continuum.web.test.XPathExpressionUtil;

public abstract class AbstractUserRolesManagementTest
	extends AbstractContinuumTest
{
	protected String username;
	protected String fullname;

	public String getUserEmail()
	{
		String email = getProperty( "USERROLE_EMAIL" );
		return email;
	}

	public String getUserRolePassword()
	{
		String password = getProperty( "USERROLE_PASSWORD" );
		return password;
	}

	public String getUserRoleNewPassword()
	{
		String password_new = getProperty( "NEW_USERROLE_PASSWORD" );
		return password_new;
	}

	public String getBasedir()
    {
        String basedir = System.getProperty( "basedir" );

        if ( basedir == null )
        {
            basedir = new File( "" ).getAbsolutePath();
        }

        return basedir;
    }

	public String getAdminUsername()
	{
		String adminUsername = getProperty( "ADMIN_USERNAME" );
		return adminUsername;
	}

	public String getAdminPassword()
	{
		String adminPassword = getProperty( "ADMIN_PASSWORD" );
		return adminPassword;
	}

	////////////////////////////
	// Assertions
	////////////////////////////
	public void assertCreateUserPage()
	{
		assertPage( "[Admin] User Create" );
		assertTextPresent( "[Admin] User Create" );
		assertTextPresent( "Username*:" );
		assertElementPresent( "user.username" );
		assertTextPresent( "Full Name*:");
		assertElementPresent( "user.fullName" );
		assertTextPresent( "Email Address*:" );
		assertElementPresent( "user.email" );
		assertTextPresent( "Password*:" );
		assertElementPresent( "user.password" );
		assertTextPresent( "Confirm Password*:" );
		assertElementPresent( "user.confirmPassword" );
		assertButtonWithValuePresent( "Create User" );
	}

	public void assertUserRolesPage()
	{
		assertPage( "[Admin] User Edit" );
		assertTextPresent( "[Admin] User Roles" );
		String userRoles = "Username,Full Name,Email,redback-xwork-integration-core,Redback XWork Integration Security Core,Guest,Registered User,System Administrator,User Administrator,Continuum Group Project Administrator,Continuum Group Project Developer,Continuum Group Project User,Continuum Manage Build Environments,Continuum Manage Build Templates,Continuum Manage Installations,Continuum Manage Local Repositories,Continuum Manage Purging,Continuum Manage Queues,Continuum Manage Scheduling,Project Administrator,Project Developer,Project User,Default Project Group";
		String[] arrayUserRoles = userRoles.split( "," );
			for ( String userroles : arrayUserRoles )
				assertTextPresent( userroles );
	}

	   public void assertCreatedUserInfo( String username )
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

public abstract class AbstractUserRolesManagementTest
    extends AbstractAdminTest
{
    protected String username;

    protected String fullname;

    protected String getUserEmail()
    {
        return getProperty( "USERROLE_EMAIL" );
    }

    protected String getUserRolePassword()
    {
        return getProperty( "USERROLE_PASSWORD" );
    }

    protected String getUserRoleNewPassword()
    {
        return getProperty( "NEW_USERROLE_PASSWORD" );
    }

    ////////////////////////////
    // Assertions
    ////////////////////////////
    protected void assertCreateUserPage()
    {
        assertPage( "[Admin] User Create" );
        assertTextPresent( "[Admin] User Create" );
        assertTextPresent( "Username*:" );
        assertElementPresent( "user.username" );
        assertTextPresent( "Full Name*:" );
        assertElementPresent( "user.fullName" );
        assertTextPresent( "Email Address*:" );
        assertElementPresent( "user.email" );
        assertTextPresent( "Password*:" );
        assertElementPresent( "user.password" );
        assertTextPresent( "Confirm Password*:" );
        assertElementPresent( "user.confirmPassword" );
        assertButtonWithValuePresent( "Create User" );
    }

    protected void assertUserRolesPage()
    {
        assertPage( "[Admin] User Edit" );
        assertTextPresent( "[Admin] User Roles" );
        String userRoles =
            "Username,Full Name,Email,Guest,Registered User,System Administrator,User Administrator,Continuum Group Project Administrator,Continuum Group Project Developer,Continuum Group Project User,Continuum Manage Build Environments,Continuum Manage Build Templates,Continuum Manage Installations,Continuum Manage Local Repositories,Continuum Manage Purging,Continuum Manage Queues,Continuum Manage Scheduling,Project Administrator,Project Developer,Project User,Default Project Group";
        String[] arrayUserRoles = userRoles.split( "," );
        for ( String userroles : arrayUserRoles )
        {
            assertTextPresent( userroles );
        }
    }

    protected void assertCreatedUserInfo( String username )
>>>>>>> refs/remotes/apache/trunk
    {
        selectValue( "name=ec_rd", "50" );
        waitPage();
        clickLinkWithText( username );
        clickLinkWithText( "Edit Roles" );
    }

<<<<<<< HEAD
	public void assertUserRoleCheckBoxPresent( String value )
    {
    	getSelenium().isElementPresent( "xpath=//input[@id='addRolesToUser_addNDSelectedRoles' and @name='addNDSelectedRoles' and @value='"+ value + "']" );
    }

    public void assertResourceRolesCheckBoxPresent( String value )
    {
    	getSelenium().isElementPresent( "xpath=//input[@name='addDSelectedRoles' and @value='" + value + "']" );
    }

    public void checkUserRoleWithValue( String value )
    {
    	assertUserRoleCheckBoxPresent( value );
    	getSelenium().click( "xpath=//input[@id='addRolesToUser_addNDSelectedRoles' and @name='addNDSelectedRoles' and @value='"+ value + "']" );
    }

    public void checkResourceRoleWithValue( String value )
    {
    	assertResourceRolesCheckBoxPresent( value );
    	getSelenium().click( "xpath=//input[@name='addDSelectedRoles' and @value='" + value + "']" );
    }

	public void assertLeftNavMenuWithRole( String role )
	{
		if( role == "System Administrator" )
    	{
    		String navMenu = "About,Show Project Groups,Maven 2.0.x Project,Maven 1.x Project,Ant Project,Shell Project,Local Repositories,Purge Configurations,Schedules,Installations,Build Environments,Queues,Build Definition Templates,Configuration,Appearance,Users,Roles,Build Queue";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "User Administrator" )
    	{
    		String navMenu = "About,Show Project Groups,Users,Roles";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Group Project Administrator" )
    	{
    		String navMenu = "About,Show Project Groups,Maven 2.0.x Project,Maven 1.x Project,Ant Project,Shell Project,Schedules,Queues,Users,Roles";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Group Project Developer" )
    	{
    		String navMenu = "About,Show Project Groups,Queues";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Group Project User" )
    	{
    		String navMenu = "About,Show Project Groups,Queues";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Manage Build Environments" )
    	{
    		String navMenu = "About,Show Project Groups,Build Environments";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Manage Build Templates" )
    	{
    		String navMenu = "About,Show Project Groups,Build Definition Templates";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Manage Installations" )
    	{
    		String navMenu = "About,Show Project Groups,Installations";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Manage Local Repositories" )
    	{
    		String navMenu = "About,Show Project Groups,Local Repositories";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Manage Purging" )
    	{
    		String navMenu = "About,Show Project Groups,Purge Configurations";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Manage Queues" )
    	{
    		String navMenu = "About,Show Project Groups,Queues";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Continuum Manage Scheduling" )
    	{
    		String navMenu = "About,Show Project Groups,Schedules";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Project Administrator - Default Project Group" )
    	{
    		String navMenu = "About,Show Project Groups,Queues,Users,Roles";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else if( role == "Project Developer - Default Project Group" || role == "Project User - Default Project Group" )
    	{
    		String navMenu = "About,Show Project Groups,Queues";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    	}
    	else
    	{
    		String navMenu = "About,Show Project Groups";
    		String[] arrayNavMenu = navMenu.split( "," );
    		for( String navmenu : arrayNavMenu )
    			assertLinkPresent( navmenu );
    		assertTextPresent( "Project Groups" );
    		//assertTextPresent( "Project Groups list is empty." );
    	}

	}

    public void assertDeleteUserPage( String username )
    {
        assertPage( "[Admin] User Delete" ); //TODO
=======
    void assertUserRoleCheckBoxPresent( String value )
    {
        getSelenium().isElementPresent(
            "xpath=//input[@id='addRolesToUser_addNDSelectedRoles' and @name='addNDSelectedRoles' and @value='" +
                value + "']" );
    }

    void assertResourceRolesCheckBoxPresent( String value )
    {
        getSelenium().isElementPresent( "xpath=//input[@name='addDSelectedRoles' and @value='" + value + "']" );
    }

    protected void checkUserRoleWithValue( String value )
    {
        assertUserRoleCheckBoxPresent( value );
        getSelenium().click(
            "xpath=//input[@id='addRolesToUser_addNDSelectedRoles' and @name='addNDSelectedRoles' and @value='" +
                value + "']" );
    }

    protected void checkResourceRoleWithValue( String value )
    {
        assertResourceRolesCheckBoxPresent( value );
        getSelenium().click( "xpath=//input[@name='addDSelectedRoles' and @value='" + value + "']" );
    }

    protected void assertLeftNavMenuWithRole( String role )
    {
        if ( "System Administrator".equals( role ) )
        {
            String navMenu =
                "About,Show Project Groups,Maven Project,Maven 1.x Project,Ant Project,Shell Project,Local Repositories,Purge Configurations,Schedules,Installations,Build Environments,Build Definition Templates,Configuration,Appearance,Users,Roles,Build Queue";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "User Administrator".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Users,Roles";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Group Project Administrator".equals( role ) )
        {
            String navMenu =
                "About,Show Project Groups,Maven Project,Maven 1.x Project,Ant Project,Shell Project,Schedules,Users,Roles";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Group Project Developer".equals( role ) )
        {
            String navMenu = "About,Show Project Groups";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Group Project User".equals( role ) )
        {
            String navMenu = "About,Show Project Groups";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Manage Build Environments".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Build Environments";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Manage Build Templates".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Build Definition Templates";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Manage Installations".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Installations";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Manage Local Repositories".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Local Repositories";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Manage Purging".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Purge Configurations";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Manage Queues".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Queues";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Continuum Manage Scheduling".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Schedules";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Project Administrator - Default Project Group".equals( role ) )
        {
            String navMenu = "About,Show Project Groups,Users,Roles";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else if ( "Project Developer - Default Project Group".equals( role ) ||
            "Project User - Default Project Group".equals( role ) )
        {
            String navMenu = "About,Show Project Groups";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
        }
        else
        {
            String navMenu = "About,Show Project Groups";
            String[] arrayNavMenu = navMenu.split( "," );
            for ( String navmenu : arrayNavMenu )
            {
                assertLinkPresent( navmenu );
            }
            assertTextPresent( "Project Groups" );
            //assertTextPresent( "Project Groups list is empty." );
        }

    }

    void assertDeleteUserPage( String username )
    {
        assertPage( "[Admin] User Delete" );
>>>>>>> refs/remotes/apache/trunk
        assertTextPresent( "[Admin] User Delete" );
        assertTextPresent( "The following user will be deleted:" );
        assertTextPresent( "Username: " + username );
        assertButtonWithValuePresent( "Delete User" );
    }

<<<<<<< HEAD
	public void assertProjectAdministratorAccess()
    {
        assertLinkPresent( "About" );
        assertLinkPresent( "Show Project Groups" );
        assertLinkPresent( "Maven 2.0.x Project" );
=======
    protected void assertProjectAdministratorAccess()
    {
        assertLinkPresent( "About" );
        assertLinkPresent( "Show Project Groups" );
        assertLinkPresent( "Maven Project" );
>>>>>>> refs/remotes/apache/trunk
        assertLinkPresent( "Maven 1.x Project" );
        assertLinkPresent( "Ant Project" );
        assertLinkPresent( "Shell Project" );
        assertLinkPresent( "Schedules" );
<<<<<<< HEAD
        assertLinkPresent( "Queues" );
=======
>>>>>>> refs/remotes/apache/trunk
        assertLinkPresent( "Users" );
        assertLinkPresent( "Roles" );
        assertLinkNotPresent( "Local Repositories" );
        assertLinkNotPresent( "Purge Configurations" );
        assertLinkNotPresent( "Installations" );
        assertLinkNotPresent( "Build Environments" );
        assertLinkNotPresent( "Build Definition Templates" );
        assertLinkNotPresent( "Configuration" );
        assertLinkNotPresent( "Appearance" );
        assertLinkNotPresent( "Build Queue" );
        assertLinkNotPresent( "Build Agent" );
    }

<<<<<<< HEAD
	/////////////////////////////////////////
	// User Roles Management
	/////////////////////////////////////////
    public void changePassword( String oldPassword, String newPassword )
	{
		assertPage( "Change Password" );
		setFieldValue( "existingPassword", oldPassword );
		setFieldValue( "newPassword", newPassword );
		setFieldValue( "newPasswordConfirm", newPassword );
		clickButtonWithValue( "Change Password" );
	}

	public void createUser( String userName, String fullName, String email, String password, boolean valid )
	{
		createUser( userName, fullName, email, password, password, valid );
	}

	private void createUser( String userName, String fullName, String emailAd, String password, String confirmPassword,
                             boolean valid )
	{
		login( getAdminUsername() , getAdminPassword() );
		clickLinkWithText( "Users" );
		clickButtonWithValue( "Create New User" );
		assertCreateUserPage();
=======
    /////////////////////////////////////////
    // User Roles Management
    /////////////////////////////////////////
    protected void changePassword( String oldPassword, String newPassword )
    {
        assertPage( "Change Password" );
        setFieldValue( "existingPassword", oldPassword );
        setFieldValue( "newPassword", newPassword );
        setFieldValue( "newPasswordConfirm", newPassword );
        clickButtonWithValue( "Change Password" );
    }

    protected void createUser( String userName, String fullName, String email, String password )
    {
        createUser( userName, fullName, email, password, password );
    }

    private void createUser( String userName, String fullName, String emailAd, String password, String confirmPassword )
    {
        loginAsAdmin();
        clickLinkWithText( "Users" );
        clickButtonWithValue( "Create New User" );
        assertCreateUserPage();
>>>>>>> refs/remotes/apache/trunk
        setFieldValue( "user.username", userName );
        setFieldValue( "user.fullName", fullName );
        setFieldValue( "user.email", emailAd );
        setFieldValue( "user.password", password );
        setFieldValue( "user.confirmPassword", confirmPassword );
        submit();

<<<<<<< HEAD
        assertUserRolesPage( );
        clickButtonWithValue( "Submit" );

        /*if (valid )
        {
        	String[] columnValues = {userName, fullName, emailAd};
            assertElementPresent( XPathExpressionUtil.getTableRow( columnValues ) );
        }
        else
        {
            assertCreateUserPage();
        }*/
	}


	public void login( String username, String password )
	{
	    login( username, password, true, "Login Page" );
	}

    public void login( String username, String password, boolean valid, String assertReturnPage )
	{
        if ( isLinkPresent( "Login" ) )
	    {
            goToLoginPage();

            submitLoginPage( username, password, false, valid, assertReturnPage );
	    }
    }

    public void submitLoginPage( String username, String password )
    {
        submitLoginPage( username, password, false, true, "Login Page" );
    }

    public void submitLoginPage( String username, String password, boolean validUsernamePassword )
    {
        submitLoginPage( username, password, false, validUsernamePassword, "Login Page" );
    }

    public void submitLoginPage( String username, String password, boolean rememberMe, boolean validUsernamePassword,
                                 String assertReturnPage )
    {
        assertLoginPage();
        setFieldValue( "username", username );
        setFieldValue( "password", password );
        if ( rememberMe )
        {
            checkField( "rememberMe" );
        }
        clickButtonWithValue( "Login" );

        if ( validUsernamePassword )
        {
            assertTextPresent( "Current User:" );
            assertTextPresent( username );
            assertLinkPresent( "Edit Details" );
            assertLinkPresent( "Logout" );
        }
        else
        {
            if ( "Login Page".equals( assertReturnPage ) )
            {
                assertLoginPage();
            }
            else
            {
                assertPage( assertReturnPage );
            }
        }
    }

	public void deleteUser( String userName, String fullName, String emailAdd )
    {
        deleteUser( userName, fullName, emailAdd, false, false );
    }

    public void deleteUser( String userName, String fullName, String emailAd, boolean validated, boolean locked )
    {
	    //clickLinkWithText( "userlist" );
        clickLinkWithXPath( "//table[@id='ec_table']/tbody[2]/tr[3]/td[7]/a/img" );
        assertDeleteUserPage( userName );
        submit();
        assertElementNotPresent( userName );
=======
        assertUserRolesPage();
        clickButtonWithValue( "Submit" );
    }

    protected void deleteUser( String userName )
    {
        deleteUser( userName, true );
    }

    protected void deleteUser( String userName, boolean failIfMissing )
    {
        String xpath = "//tr[.//a[text()='" + userName + "']]/td/a[@title='delete user']";
        if ( failIfMissing || isElementPresent( "xpath=" + xpath ) )
        {
            clickLinkWithXPath( xpath );
            assertDeleteUserPage( userName );
            submit();
            assertElementNotPresent( userName );
        }
>>>>>>> refs/remotes/apache/trunk
    }
}
