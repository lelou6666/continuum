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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author José Morales Martínez
 */
<<<<<<< HEAD
@Test( groups = { "notifier" }, dependsOnMethods = { "testAddMavenTwoProjectFromRemoteSourceToNonDefaultProjectGroup" } )
=======
@Test( groups = { "notifier" } )
>>>>>>> refs/remotes/apache/trunk
public class NotifierTest
    extends AbstractAdminTest
{
    private String projectGroupName;

    private String projectGroupId;

    private String projectGroupDescription;

    private String projectName;

    private String mailNotifierAddress;

    private String ircNotifierHost;

    private String ircNotifierChannel;

    private String jabberNotifierHost;

    private String jabberNotifierLogin;

    private String jabberNotifierPassword;

    private String jabberNotifierAddress;

    private String msnNotifierAddress;

    private String msnNotifierLogin;

    private String msnNotifierPassword;

    private String wagonNotifierUrl;

    private String wagonServerId;

    @BeforeClass
    public void createProject()
    {
        projectGroupName = getProperty( "NOTIFIER_PROJECT_GROUP_NAME" );
        projectGroupId = getProperty( "NOTIFIER_PROJECT_GROUP_ID" );
        projectGroupDescription = getProperty( "NOTIFIER_PROJECT_GROUP_DESCRIPTION" );

        projectName = getProperty( "MAVEN2_POM_PROJECT_NAME" );
        String projectPomUrl = getProperty( "MAVEN2_POM_URL" );
        String pomUsername = getProperty( "MAVEN2_POM_USERNAME" );
        String pomPassword = getProperty( "MAVEN2_POM_PASSWORD" );

        loginAsAdmin();

        removeProjectGroup( projectGroupName, false );

        addProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, true, true );
        clickLinkWithText( projectGroupName );

        addMavenTwoProject( projectPomUrl, pomUsername, pomPassword, projectGroupName, true );
    }

    @BeforeMethod
    protected void setUp()
        throws Exception
    {
        mailNotifierAddress = getProperty( "MAIL_NOTIFIER_ADDRESS" );
        ircNotifierHost = getProperty( "IRC_NOTIFIER_HOST" );
        ircNotifierChannel = getProperty( "IRC_NOTIFIER_CHANNEL" );
        jabberNotifierHost = getProperty( "JABBER_NOTIFIER_HOST" );
        jabberNotifierLogin = getProperty( "JABBER_NOTIFIER_LOGIN" );
        jabberNotifierPassword = getProperty( "JABBER_NOTIFIER_PASSWORD" );
        jabberNotifierAddress = getProperty( "JABBER_NOTIFIER_ADDRESS" );
        msnNotifierAddress = getProperty( "MSN_NOTIFIER_ADDRESS" );
        msnNotifierLogin = getProperty( "MSN_NOTIFIER_LOGIN" );
        msnNotifierPassword = getProperty( "MSN_NOTIFIER_PASSWORD" );
        wagonNotifierUrl = getProperty( "WAGON_NOTIFIER_URL" );
        wagonServerId = getProperty( "WAGON_SERVER_ID" );
    }

    public void testAddValidMailProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String MAIL_NOTIFIER_ADDRESS = getProperty( "MAIL_NOTIFIER_ADDRESS" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addMailNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, MAIL_NOTIFIER_ADDRESS, true );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addMailNotifier( projectGroupName, projectName, mailNotifierAddress, true );
    }

    public void testAddValidMailProjectNotifierWithInvalidValue()
        throws Exception
    {
        String mailNotifierAddress = "<script>alert('xss')</script>";
        goToProjectNotifier( projectGroupName, projectName );
        addMailNotifier( projectGroupName, projectName, mailNotifierAddress, false );
        assertTextPresent( "Address is invalid" );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidMailProjectNotifier" } )
    public void testEditValidMailProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String MAIL_NOTIFIER_ADDRESS = getProperty( "MAIL_NOTIFIER_ADDRESS" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newMail = "newmail@mail.com";
        goToProjectInformationPage( projectGroupName, projectName );
        editMailNotifier( projectGroupName, projectName, mailNotifierAddress, newMail, true );
        editMailNotifier( projectGroupName, projectName, newMail, mailNotifierAddress, true );
    }

    @Test( dependsOnMethods = { "testAddValidMailProjectNotifier" } )
    public void testEditInvalidMailProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String MAIL_NOTIFIER_ADDRESS = getProperty( "MAIL_NOTIFIER_ADDRESS" );
        goToProjectInformationPage( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        editMailNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, MAIL_NOTIFIER_ADDRESS, "invalid_email_add", false );
=======
        goToProjectInformationPage( projectGroupName, projectName );
        editMailNotifier( projectGroupName, projectName, mailNotifierAddress, "invalid_email_add", false );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddInvalidMailProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addMailNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, "invalid_email_add", false );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addMailNotifier( projectGroupName, projectName, "invalid_email_add", false );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidMailGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String MAIL_NOTIFIER_ADDRESS = getProperty( "MAIL_NOTIFIER_ADDRESS" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addMailNotifier( TEST_PROJ_GRP_NAME, null, MAIL_NOTIFIER_ADDRESS, true );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addMailNotifier( projectGroupName, null, mailNotifierAddress, true );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidMailGroupNotifier" } )
    public void testEditValidMailGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String MAIL_NOTIFIER_ADDRESS = getProperty( "MAIL_NOTIFIER_ADDRESS" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newMail = "newmail@mail.com";
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editMailNotifier( projectGroupName, null, mailNotifierAddress, newMail, true );
        editMailNotifier( projectGroupName, null, newMail, mailNotifierAddress, true );
    }

    @Test( dependsOnMethods = { "testAddValidMailGroupNotifier" } )
    public void testEditInvalidMailGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String MAIL_NOTIFIER_ADDRESS = getProperty( "MAIL_NOTIFIER_ADDRESS" );
        showProjectGroup( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
=======
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editMailNotifier( projectGroupName, null, mailNotifierAddress, "invalid_email_add", false );
    }

    public void testAddInvalidMailGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addMailNotifier( TEST_PROJ_GRP_NAME, null, "invalid_email_add", false );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addMailNotifier( projectGroupName, null, "invalid_email_add", false );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidIrcProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String IRC_NOTIFIER_HOST = getProperty( "IRC_NOTIFIER_HOST" );
        String IRC_NOTIFIER_CHANNEL = getProperty( "IRC_NOTIFIER_CHANNEL" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addIrcNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, IRC_NOTIFIER_HOST, IRC_NOTIFIER_CHANNEL, true );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addIrcNotifier( projectGroupName, projectName, ircNotifierHost, ircNotifierChannel, true );
    }

    public void testAddProjectNotifierWithInvalidValues()
        throws Exception
    {
        String ircNotifierHost = "!@#$<>?etc";
        String ircNotifierChannel = "!@#$<>?etc";
        goToProjectNotifier( projectGroupName, projectName );
        addIrcNotifier( projectGroupName, projectName, ircNotifierHost, ircNotifierChannel, false );
        assertTextPresent( "Host contains invalid character" );
        assertTextPresent( "Channel contains invalid character" );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidIrcProjectNotifier" } )
    public void testEditValidIrcProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String IRC_NOTIFIER_HOST = getProperty( "IRC_NOTIFIER_HOST" );
        String IRC_NOTIFIER_CHANNEL = getProperty( "IRC_NOTIFIER_CHANNEL" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newHost = "new.test.com";
        String newChannel = "new_test_channel";
        goToProjectInformationPage( projectGroupName, projectName );
        editIrcNotifier( projectGroupName, projectName, ircNotifierHost, ircNotifierChannel, newHost, newChannel, true );
        editIrcNotifier( projectGroupName, projectName, newHost, newChannel, ircNotifierHost, ircNotifierChannel, true );
    }

    @Test( dependsOnMethods = { "testAddValidIrcProjectNotifier" } )
    public void testEditInvalidIrcProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String IRC_NOTIFIER_HOST = getProperty( "IRC_NOTIFIER_HOST" );
        String IRC_NOTIFIER_CHANNEL = getProperty( "IRC_NOTIFIER_CHANNEL" );
        goToProjectInformationPage( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        editIrcNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, IRC_NOTIFIER_HOST, IRC_NOTIFIER_CHANNEL, "", "", false );
=======
        goToProjectInformationPage( projectGroupName, projectName );
        editIrcNotifier( projectGroupName, projectName, ircNotifierHost, ircNotifierChannel, "", "", false );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddInvalidIrcProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addIrcNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, "", "", false );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addIrcNotifier( projectGroupName, projectName, "", "", false );
        assertTextPresent( "Host is required" );
        assertTextPresent( "Channel is required" );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidIrcGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String IRC_NOTIFIER_HOST = getProperty( "IRC_NOTIFIER_HOST" );
        String IRC_NOTIFIER_CHANNEL = getProperty( "IRC_NOTIFIER_CHANNEL" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addIrcNotifier( TEST_PROJ_GRP_NAME, null, IRC_NOTIFIER_HOST, IRC_NOTIFIER_CHANNEL, true );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addIrcNotifier( projectGroupName, null, ircNotifierHost, ircNotifierChannel, true );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidIrcGroupNotifier" } )
    public void testEditValidIrcGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String IRC_NOTIFIER_HOST = getProperty( "IRC_NOTIFIER_HOST" );
        String IRC_NOTIFIER_CHANNEL = getProperty( "IRC_NOTIFIER_CHANNEL" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newHost = "new.test.com";
        String newChannel = "new_test_channel";
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editIrcNotifier( projectGroupName, null, ircNotifierHost, ircNotifierChannel, newHost, newChannel, true );
        editIrcNotifier( projectGroupName, null, newHost, newChannel, ircNotifierHost, ircNotifierChannel, true );
    }

    @Test( dependsOnMethods = { "testAddValidIrcGroupNotifier" } )
    public void testEditInvalidIrcGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String IRC_NOTIFIER_HOST = getProperty( "IRC_NOTIFIER_HOST" );
        String IRC_NOTIFIER_CHANNEL = getProperty( "IRC_NOTIFIER_CHANNEL" );
        showProjectGroup( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
=======
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editIrcNotifier( projectGroupName, null, ircNotifierHost, ircNotifierChannel, "", "", false );
    }

    public void testAddInvalidIrcGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addIrcNotifier( TEST_PROJ_GRP_NAME, null, "", "", false );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addIrcNotifier( projectGroupName, null, "", "", false );
        assertTextPresent( "Host is required" );
        assertTextPresent( "Channel is required" );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidJabberProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String JABBER_NOTIFIER_HOST = getProperty( "JABBER_NOTIFIER_HOST" );
        String JABBER_NOTIFIER_LOGIN = getProperty( "JABBER_NOTIFIER_LOGIN" );
        String JABBER_NOTIFIER_PASSWORD = getProperty( "JABBER_NOTIFIER_PASSWORD" );
        String JABBER_NOTIFIER_ADDRESS = getProperty( "JABBER_NOTIFIER_ADDRESS" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addJabberNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, JABBER_NOTIFIER_HOST, JABBER_NOTIFIER_LOGIN,
                           JABBER_NOTIFIER_PASSWORD, JABBER_NOTIFIER_ADDRESS, true );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addJabberNotifier( projectGroupName, projectName, jabberNotifierHost, jabberNotifierLogin,
                           jabberNotifierPassword, jabberNotifierAddress, true );
    }

    public void testAddJabberProjectNotifierWithInvalidValues()
        throws Exception
    {
        String jabberNotifierHost = "!@#$<>?etc";
        String jabberNotifierAddress = "!@#$<>?etc";
        goToProjectNotifier( projectGroupName, projectName );
        addJabberNotifier( projectGroupName, projectName, jabberNotifierHost, jabberNotifierLogin,
                           jabberNotifierPassword, jabberNotifierAddress, false );
        assertTextPresent( "Host contains invalid characters" );
        assertTextPresent( "Address is invalid" );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidJabberProjectNotifier" } )
    public void testEditValidJabberProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String JABBER_NOTIFIER_HOST = getProperty( "JABBER_NOTIFIER_HOST" );
        String JABBER_NOTIFIER_LOGIN = getProperty( "JABBER_NOTIFIER_LOGIN" );
        String JABBER_NOTIFIER_PASSWORD = getProperty( "JABBER_NOTIFIER_PASSWORD" );
        String JABBER_NOTIFIER_ADDRESS = getProperty( "JABBER_NOTIFIER_ADDRESS" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newHost = "new_test";
        String newLogin = "new_test_login";
        String newPassword = "new_password";
        String newAddress = "new.test@mail.com";
        goToProjectInformationPage( projectGroupName, projectName );
        editJabberNotifier( projectGroupName, projectName, jabberNotifierHost, jabberNotifierLogin,
                            jabberNotifierAddress, newHost, newLogin, newPassword, newAddress, true );
        editJabberNotifier( projectGroupName, projectName, newHost, newLogin, newAddress, jabberNotifierHost,
                            jabberNotifierLogin, jabberNotifierPassword, jabberNotifierAddress, true );
    }

    @Test( dependsOnMethods = { "testAddValidJabberProjectNotifier" } )
    public void testEditInvalidJabberProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String JABBER_NOTIFIER_HOST = getProperty( "JABBER_NOTIFIER_HOST" );
        String JABBER_NOTIFIER_LOGIN = getProperty( "JABBER_NOTIFIER_LOGIN" );
        String JABBER_NOTIFIER_ADDRESS = getProperty( "JABBER_NOTIFIER_ADDRESS" );
        goToProjectInformationPage( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        editJabberNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, JABBER_NOTIFIER_HOST, JABBER_NOTIFIER_LOGIN,
                            JABBER_NOTIFIER_ADDRESS, "", "", "", "", false );
=======
        goToProjectInformationPage( projectGroupName, projectName );
        editJabberNotifier( projectGroupName, projectName, jabberNotifierHost, jabberNotifierLogin,
                            jabberNotifierAddress, "", "", "", "", false );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddInvalidJabberProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addJabberNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, "", "", "", "", false );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addJabberNotifier( projectGroupName, projectName, "", "", "", "", false );
        assertTextPresent( "Host is required" );
        assertTextPresent( "Login is required" );
        assertTextPresent( "Password is required" );
        assertTextPresent( "Address is required" );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidJabberGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String JABBER_NOTIFIER_HOST = getProperty( "JABBER_NOTIFIER_HOST" );
        String JABBER_NOTIFIER_LOGIN = getProperty( "JABBER_NOTIFIER_LOGIN" );
        String JABBER_NOTIFIER_PASSWORD = getProperty( "JABBER_NOTIFIER_PASSWORD" );
        String JABBER_NOTIFIER_ADDRESS = getProperty( "JABBER_NOTIFIER_ADDRESS" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addJabberNotifier( TEST_PROJ_GRP_NAME, null, JABBER_NOTIFIER_HOST, JABBER_NOTIFIER_LOGIN,
                           JABBER_NOTIFIER_PASSWORD, JABBER_NOTIFIER_ADDRESS, true );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addJabberNotifier( projectGroupName, null, jabberNotifierHost, jabberNotifierLogin, jabberNotifierPassword,
                           jabberNotifierAddress, true );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidJabberGroupNotifier" } )
    public void testEditValidJabberGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String JABBER_NOTIFIER_HOST = getProperty( "JABBER_NOTIFIER_HOST" );
        String JABBER_NOTIFIER_LOGIN = getProperty( "JABBER_NOTIFIER_LOGIN" );
        String JABBER_NOTIFIER_PASSWORD = getProperty( "JABBER_NOTIFIER_PASSWORD" );
        String JABBER_NOTIFIER_ADDRESS = getProperty( "JABBER_NOTIFIER_ADDRESS" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newHost = "new_test";
        String newLogin = "new_test_login";
        String newPassword = "new_password";
        String newAddress = "new.test@mail.com";
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editJabberNotifier( projectGroupName, null, jabberNotifierHost, jabberNotifierLogin, jabberNotifierAddress,
                            newHost, newLogin, newPassword, newAddress, true );
        editJabberNotifier( projectGroupName, null, newHost, newLogin, newAddress, jabberNotifierHost,
                            jabberNotifierLogin, jabberNotifierPassword, jabberNotifierAddress, true );
    }

    @Test( dependsOnMethods = { "testAddValidJabberGroupNotifier" } )
    public void testEditInvalidJabberGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String JABBER_NOTIFIER_HOST = getProperty( "JABBER_NOTIFIER_HOST" );
        String JABBER_NOTIFIER_LOGIN = getProperty( "JABBER_NOTIFIER_LOGIN" );
        String JABBER_NOTIFIER_ADDRESS = getProperty( "JABBER_NOTIFIER_ADDRESS" );
        showProjectGroup( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
=======
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editJabberNotifier( projectGroupName, null, jabberNotifierHost, jabberNotifierLogin, jabberNotifierAddress, "",
                            "", "", "", false );
    }

    public void testAddInvalidJabberGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addJabberNotifier( TEST_PROJ_GRP_NAME, null, "", "", "", "", false );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addJabberNotifier( projectGroupName, null, "", "", "", "", false );
        assertTextPresent( "Host is required" );
        assertTextPresent( "Login is required" );
        assertTextPresent( "Password is required" );
        assertTextPresent( "Address is required" );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidMsnProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String MSN_NOTIFIER_ADDRESS = getProperty( "MSN_NOTIFIER_ADDRESS" );
        String MSN_NOTIFIER_LOGIN = getProperty( "MSN_NOTIFIER_LOGIN" );
        String MSN_NOTIFIER_PASSWORD = getProperty( "MSN_NOTIFIER_PASSWORD" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addMsnNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, MSN_NOTIFIER_LOGIN, MSN_NOTIFIER_PASSWORD,
                        MSN_NOTIFIER_ADDRESS, true );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addMsnNotifier( projectGroupName, projectName, msnNotifierLogin, msnNotifierPassword, msnNotifierAddress, true );
    }

    public void testAddMsnProjectNotifierWithInvalidValues()
        throws Exception
    {
        String msnNotifierAddress = "!@#$<>?etc";
        goToProjectNotifier( projectGroupName, projectName );
        addMsnNotifier( projectGroupName, projectName, msnNotifierLogin, msnNotifierPassword, msnNotifierAddress,
                        false );
        assertTextPresent( "Address is invalid" );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidMsnProjectNotifier" } )
    public void testEditValidMsnProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String MSN_NOTIFIER_ADDRESS = getProperty( "MSN_NOTIFIER_ADDRESS" );
        String MSN_NOTIFIER_LOGIN = getProperty( "MSN_NOTIFIER_LOGIN" );
        String MSN_NOTIFIER_PASSWORD = getProperty( "MSN_NOTIFIER_PASSWORD" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newLogin = "new_test_login";
        String newPassword = "new_password";
        String newAddress = "new.test@mail.com";
        goToProjectInformationPage( projectGroupName, projectName );
        editMsnNotifier( projectGroupName, projectName, msnNotifierLogin, msnNotifierAddress, newLogin, newPassword,
                         newAddress, true );
        editMsnNotifier( projectGroupName, projectName, newLogin, newAddress, msnNotifierLogin, msnNotifierPassword,
                         msnNotifierAddress, true );
    }

    @Test( dependsOnMethods = { "testAddValidMsnProjectNotifier" } )
    public void testEditInvalidMsnProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String MSN_NOTIFIER_ADDRESS = getProperty( "MSN_NOTIFIER_ADDRESS" );
        String MSN_NOTIFIER_LOGIN = getProperty( "MSN_NOTIFIER_LOGIN" );
        goToProjectInformationPage( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        editMsnNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, MSN_NOTIFIER_LOGIN, MSN_NOTIFIER_ADDRESS, "", "", "",
                         false );
=======
        goToProjectInformationPage( projectGroupName, projectName );
        editMsnNotifier( projectGroupName, projectName, msnNotifierLogin, msnNotifierAddress, "", "", "", false );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddInvalidMsnProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addMsnNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, "", "", "", false );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addMsnNotifier( projectGroupName, projectName, "", "", "", false );
        assertTextPresent( "Login is required" );
        assertTextPresent( "Password is required" );
        assertTextPresent( "Address is required" );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidMsnGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String MSN_NOTIFIER_ADDRESS = getProperty( "MSN_NOTIFIER_ADDRESS" );
        String MSN_NOTIFIER_LOGIN = getProperty( "MSN_NOTIFIER_LOGIN" );
        String MSN_NOTIFIER_PASSWORD = getProperty( "MSN_NOTIFIER_PASSWORD" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addMsnNotifier( TEST_PROJ_GRP_NAME, null, MSN_NOTIFIER_LOGIN, MSN_NOTIFIER_PASSWORD, MSN_NOTIFIER_ADDRESS, true );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addMsnNotifier( projectGroupName, null, msnNotifierLogin, msnNotifierPassword, msnNotifierAddress, true );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidMsnGroupNotifier" } )
    public void testEditValidMsnGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String MSN_NOTIFIER_ADDRESS = getProperty( "MSN_NOTIFIER_ADDRESS" );
        String MSN_NOTIFIER_LOGIN = getProperty( "MSN_NOTIFIER_LOGIN" );
        String MSN_NOTIFIER_PASSWORD = getProperty( "MSN_NOTIFIER_PASSWORD" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newLogin = "new_test_login";
        String newPassword = "new_password";
        String newAddress = "new.test@mail.com";
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editMsnNotifier( projectGroupName, null, msnNotifierLogin, msnNotifierAddress, newLogin, newPassword,
                         newAddress, true );
        editMsnNotifier( projectGroupName, null, newLogin, newAddress, msnNotifierLogin, msnNotifierPassword,
                         msnNotifierAddress, true );
    }

    @Test( dependsOnMethods = { "testAddValidMsnGroupNotifier" } )
    public void testEditInvalidMsnGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String MSN_NOTIFIER_ADDRESS = getProperty( "MSN_NOTIFIER_ADDRESS" );
        String MSN_NOTIFIER_LOGIN = getProperty( "MSN_NOTIFIER_LOGIN" );
        showProjectGroup( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
=======
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editMsnNotifier( projectGroupName, null, msnNotifierLogin, msnNotifierAddress, "", "", "", false );
    }

    public void testAddInvalidMsnGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addMsnNotifier( TEST_PROJ_GRP_NAME, null, "", "", "", false );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addMsnNotifier( projectGroupName, null, "", "", "", false );
        assertTextPresent( "Login is required" );
        assertTextPresent( "Password is required" );
        assertTextPresent( "Address is required" );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidWagonProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String WAGON_NOTIFIER_URL = getProperty( "WAGON_NOTIFIER_URL" );
        String WAGON_SERVER_ID = getProperty( "WAGON_SERVER_ID" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addWagonNotifierPage( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, WAGON_NOTIFIER_URL, WAGON_SERVER_ID, true );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addWagonNotifierPage( projectGroupName, projectName, wagonNotifierUrl, wagonServerId, true );
    }

    public void testAddInvalidURLWagonProjectNotifier()
        throws Exception
    {
        String wagonNotifierUrl = "!@#$<>?etc";
        goToProjectNotifier( projectGroupName, projectName );
        addWagonNotifierPage( projectGroupName, projectName, wagonNotifierUrl, wagonServerId, false );
        assertTextPresent( "Destination URL is invalid" );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidWagonProjectNotifier" } )
    public void testEditValidWagonProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String WAGON_NOTIFIER_URL = getProperty( "WAGON_NOTIFIER_URL" );
        String WAGON_SERVER_ID = getProperty( "WAGON_SERVER_ID" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newId = "newId";
        goToProjectInformationPage( projectGroupName, projectName );
        editWagonNotifier( projectGroupName, projectName, wagonNotifierUrl, wagonServerId, wagonNotifierUrl, newId,
                           true );
        editWagonNotifier( projectGroupName, projectName, wagonNotifierUrl, newId, wagonNotifierUrl, wagonServerId,
                           true );
    }

    @Test( dependsOnMethods = { "testAddValidWagonProjectNotifier" } )
    public void testEditInvalidWagonProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        String WAGON_NOTIFIER_URL = getProperty( "WAGON_NOTIFIER_URL" );
        String WAGON_SERVER_ID = getProperty( "WAGON_SERVER_ID" );
        goToProjectInformationPage( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        editWagonNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, WAGON_NOTIFIER_URL, WAGON_SERVER_ID, "", "", false );
=======
        goToProjectInformationPage( projectGroupName, projectName );
        editWagonNotifier( projectGroupName, projectName, wagonNotifierUrl, wagonServerId, "", "", false );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddInvalidWagonProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        goToProjectNotifier( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
        addWagonNotifierPage( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME, "", "", false );
=======
        goToProjectNotifier( projectGroupName, projectName );
        addWagonNotifierPage( projectGroupName, projectName, "", "", false );
        assertTextPresent( "Destination URL is required" );
        assertTextPresent( "Server Id is required" );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddValidWagonGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String WAGON_NOTIFIER_URL = getProperty( "WAGON_NOTIFIER_URL" );
        String WAGON_SERVER_ID = getProperty( "WAGON_SERVER_ID" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addWagonNotifierPage( TEST_PROJ_GRP_NAME, null, WAGON_NOTIFIER_URL, WAGON_SERVER_ID, true );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addWagonNotifierPage( projectGroupName, null, wagonNotifierUrl, wagonServerId, true );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testAddValidWagonGroupNotifier" } )
    public void testEditValidWagonGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String WAGON_NOTIFIER_URL = getProperty( "WAGON_NOTIFIER_URL" );
        String WAGON_SERVER_ID = getProperty( "WAGON_SERVER_ID" );
=======
>>>>>>> refs/remotes/apache/trunk
        String newId = "newId";
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editWagonNotifier( projectGroupName, null, wagonNotifierUrl, wagonServerId, wagonNotifierUrl, newId, true );
        editWagonNotifier( projectGroupName, null, wagonNotifierUrl, newId, wagonNotifierUrl, wagonServerId, true );
    }

    @Test( dependsOnMethods = { "testAddValidWagonGroupNotifier" } )
    public void testEditInvalidWagonGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String WAGON_NOTIFIER_URL = getProperty( "WAGON_NOTIFIER_URL" );
        String WAGON_SERVER_ID = getProperty( "WAGON_SERVER_ID" );
        showProjectGroup( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
=======
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        editWagonNotifier( projectGroupName, null, wagonNotifierUrl, wagonServerId, "", "", false );
    }

    public void testAddInvalidWagonGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        goToGroupNotifier( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
        addWagonNotifierPage( TEST_PROJ_GRP_NAME, null, "", "", false );
=======
        goToGroupNotifier( projectGroupName, projectGroupId, projectGroupDescription );
        addWagonNotifierPage( projectGroupName, null, "", "", false );
        assertTextPresent( "Destination URL is required" );
        assertTextPresent( "Server Id is required" );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = { "testEditValidMailGroupNotifier", "testEditInvalidMailGroupNotifier" } )
    public void testDeleteGroupNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        showProjectGroup( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
=======
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        clickLinkWithXPath( "(//a[contains(@href,'deleteProjectGroupNotifier') and contains(@href,'mail')])//img" );
        assertButtonWithValuePresent( "Delete" );
        assertButtonWithValuePresent( "Cancel" );
        clickButtonWithValue( "Delete" );
        assertGroupNotifierPage( projectGroupName );
    }

    @Test( dependsOnMethods = { "testEditValidMailProjectNotifier", "testEditInvalidMailProjectNotifier" } )
    public void testDeleteProjectNotifier()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_NAME = getProperty( "M2_PROJ_GRP_NAME" );
        goToProjectInformationPage( TEST_PROJ_GRP_NAME, M2_PROJ_GRP_NAME );
=======
        goToProjectInformationPage( projectGroupName, projectName );
>>>>>>> refs/remotes/apache/trunk
        // Delete
        clickLinkWithXPath( "(//a[contains(@href,'deleteProjectNotifier') and contains(@href,'mail')])//img" );
        assertButtonWithValuePresent( "Delete" );
        assertButtonWithValuePresent( "Cancel" );
        clickButtonWithValue( "Delete" );
        assertProjectInformationPage();
    }

    public void testDeleteProjectNotifierFromGroupNotifierPage()
        throws Exception
    {
        String mailNotifierAddress = "testDeleteProjectNotifierFromGroupNotifierPage@test.com";

        goToProjectGroupsSummaryPage();
        goToProjectNotifier( projectGroupName, projectName );
        addMailNotifier( projectGroupName, projectName, mailNotifierAddress, true );

        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );

        // Delete
        clickLinkWithXPath( "//preceding::td[text()='" + mailNotifierAddress + "']//following::img[@alt='Delete']" );
        assertButtonWithValuePresent( "Delete" );
        assertButtonWithValuePresent( "Cancel" );
        clickButtonWithValue( "Delete" );
        assertGroupNotifierPage( projectGroupName );

        assertTextNotPresent( mailNotifierAddress );
    }

    protected void assertGroupNotifierPage( String projectGroupName )
    {
        assertTextPresent( "Project Group Notifiers of group " + projectGroupName );
    }

    void assertAddNotifierPage()
    {
        assertPage( "Continuum - Add Notifier" );
        assertTextPresent( "Add Notifier" );
        assertTextPresent( "Type" );
        assertElementPresent( "notifierType" );
        assertElementPresent( "Cancel" );
    }

    void assertAddEditMailNotifierPage()
    {
        assertPage( "Continuum - Add/Edit Mail Notifier" );
        assertTextPresent( "Add/Edit Mail Notifier" );
        assertTextPresent( "Mail Recipient Address" );
        assertTextPresent( "Send a mail to latest committers" );
        assertTextPresent( "Send on Success" );
        assertTextPresent( "Send on Failure" );
        assertTextPresent( "Send on Error" );
        assertTextPresent( "Send on Warning" );
        assertTextPresent( "Send on SCM Failure" );
        assertElementPresent( "address" );
        assertElementPresent( "Cancel" );
    }

    void assertAddEditIrcNotifierPage()
    {
        assertPage( "Continuum - Add/Edit IRC Notifier" );

        assertTextPresent( "IRC Host" );
        assertElementPresent( "host" );

        assertTextPresent( "IRC port" );
        assertElementPresent( "port" );

        assertTextPresent( "IRC channel" );
        assertElementPresent( "channel" );

        assertTextPresent( "Nick Name" );
        assertElementPresent( "nick" );

        assertTextPresent( "Alternate Nick Name" );
        assertElementPresent( "alternateNick" );

        assertTextPresent( "User Name" );
        assertElementPresent( "username" );

        assertTextPresent( "Full Name" );
        assertElementPresent( "fullName" );

        assertTextPresent( "Password" );
        assertElementPresent( "password" );

        assertTextPresent( "SSL" );
        assertTextPresent( "Send on Success" );
        assertTextPresent( "Send on Failure" );
        assertTextPresent( "Send on Error" );
        assertTextPresent( "Send on Warning" );
        assertTextPresent( "Send on SCM Failure" );
    }

    void assertAddEditJabberPage()
    {
        assertPage( "Continuum - Add/Edit Jabber Notifier" );

        assertTextPresent( "Jabber Host" );
        assertElementPresent( "host" );
        assertTextPresent( "Jabber port" );
        assertElementPresent( "port" );
        assertTextPresent( "Jabber login" );
        assertElementPresent( "login" );
        assertTextPresent( "Jabber Password" );
        assertElementPresent( "password" );
        assertTextPresent( "Jabber Domain Name" );
        assertElementPresent( "domainName" );
        assertTextPresent( "Jabber Recipient Address" );
        assertElementPresent( "address" );

        assertTextPresent( "Is it a SSL connection?" );
        assertTextPresent( "Is it a Jabber group?" );
        assertTextPresent( "Send on Success" );
        assertTextPresent( "Send on Failure" );
        assertTextPresent( "Send on Error" );
        assertTextPresent( "Send on Warning" );
        assertTextPresent( "Send on SCM Failure" );
    }

    void assertAddEditMsnPage()
    {
        assertPage( "Continuum - Add/Edit MSN Notifier" );

        assertTextPresent( "MSN login" );
        assertElementPresent( "login" );
        assertTextPresent( "MSN Password" );
        assertElementPresent( "password" );
        assertTextPresent( "MSN Recipient Address" );
        assertElementPresent( "address" );

        assertTextPresent( "Send on Success" );
        assertTextPresent( "Send on Failure" );
        assertTextPresent( "Send on Error" );
        assertTextPresent( "Send on Warning" );
        assertTextPresent( "Send on SCM Failure" );
    }

    void assertAddEditWagonPage()
    {
        assertPage( "Continuum - Add/Edit Wagon Notifier" );

        assertTextPresent( "Project Site URL" );
        assertTextPresent( "Server Id (defined in your settings.xml for authentication)" );
        assertElementPresent( "url" );
        assertElementPresent( "id" );
        assertTextPresent( "Send on Success" );
        assertTextPresent( "Send on Failure" );
        assertTextPresent( "Send on Error" );
        assertTextPresent( "Send on Warning" );
    }

    protected void goToGroupNotifier( String projectGroupName, String projectGroupId, String projectGroupDescription )
    {
        showProjectGroup( projectGroupName, projectGroupId, projectGroupDescription );
        clickLinkWithText( "Notifiers" );
        assertGroupNotifierPage( projectGroupName );
        clickButtonWithValue( "Add" );
        assertAddNotifierPage();
    }

    protected void goToProjectNotifier( String projectGroupName, String projectName )
    {
        goToProjectInformationPage( projectGroupName, projectName );
        clickLinkWithXPath( "//input[contains(@id,'addProjectNotifier') and @type='submit']" );
        assertAddNotifierPage();
    }

    protected void addMailNotifier( String projectGroupName, String projectName, String email, boolean isValid )
    {
        selectValue( "//select", "Mail" );
        clickButtonWithValue( "Submit" );
        assertAddEditMailNotifierPage();
        setFieldValue( "address", email );
        clickButtonWithValue( "Save" );

        if ( !isValid )
        {
            assertTextPresent( "Address is invalid" );
        }
        else if ( projectName != null )
        {
            assertProjectInformationPage();
        }
        else
        {
            assertGroupNotifierPage( projectGroupName );
        }
    }

    protected void editMailNotifier( String projectGroupName, String projectName, String oldMail, String newMail,
                                     boolean isValid )
    {
        if ( projectName == null )
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectGroupNotifier') and contains(@href,'mail')])//img" );
        }
        else
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectNotifier') and contains(@href,'mail')])//img" );
        }
        assertAddEditMailNotifierPage();
        assertFieldValue( oldMail, "address" );
        setFieldValue( "address", newMail );
        clickButtonWithValue( "Save" );

        if ( !isValid )
        {
            assertTextPresent( "Address is invalid" );
        }
        else if ( projectName != null )
        {
            assertProjectInformationPage();
        }
        else
        {
            assertGroupNotifierPage( projectGroupName );
        }
    }

    protected void addIrcNotifier( String projectGroupName, String projectName, String host, String channel,
                                   boolean isValid )
    {
        selectValue( "//select", "IRC" );
        clickButtonWithValue( "Submit" );
        assertAddEditIrcNotifierPage();
        setFieldValue( "host", host );
        setFieldValue( "channel", channel );

        clickButtonWithValue( "Save" );
        if ( isValid )
        {
            if ( projectName != null )
            {
                assertProjectInformationPage();
            }
            else
            {
                assertGroupNotifierPage( projectGroupName );
            }
        }
    }

    protected void editIrcNotifier( String projectGroupName, String projectName, String oldHost, String oldChannel,
                                    String newHost, String newChannel, boolean isValid )
    {
        if ( projectName == null )
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectGroupNotifier') and contains(@href,'irc')])//img" );
        }
        else
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectNotifier') and contains(@href,'irc')])//img" );
        }
        assertAddEditIrcNotifierPage();
        assertFieldValue( oldHost, "host" );
        assertFieldValue( oldChannel, "channel" );
        setFieldValue( "host", newHost );
        setFieldValue( "channel", newChannel );
        clickButtonWithValue( "Save" );

        if ( !isValid )
        {
            assertTextPresent( "Host is required" );
            assertTextPresent( "Channel is required" );
        }
        else if ( projectName != null )
        {
            assertProjectInformationPage();
        }
        else
        {
            assertGroupNotifierPage( projectGroupName );
        }
    }

    protected void addJabberNotifier( String projectGroupName, String projectName, String host, String login,
                                      String password, String address, boolean isValid )
    {
        selectValue( "//select", "Jabber" );
        clickButtonWithValue( "Submit" );
        assertAddEditJabberPage();
        setFieldValue( "host", host );
        setFieldValue( "login", login );
        setFieldValue( "password", password );
        setFieldValue( "address", address );
        clickButtonWithValue( "Save" );

        if ( isValid )
        {
            if ( projectName != null )
            {
                assertProjectInformationPage();
            }
            else
            {
                assertGroupNotifierPage( projectGroupName );
            }
        }
    }

    protected void editJabberNotifier( String projectGroupName, String projectName, String oldHost, String oldLogin,
                                       String oldAddress, String newHost, String newLogin, String newPassword,
                                       String newAddress, boolean isValid )
    {
        if ( projectName == null )
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectGroupNotifier') and contains(@href,'jabber')])//img" );
        }
        else
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectNotifier') and contains(@href,'jabber')])//img" );
        }
        assertAddEditJabberPage();
        assertFieldValue( oldHost, "host" );
        assertFieldValue( oldLogin, "login" );
        assertFieldValue( oldAddress, "address" );
        setFieldValue( "host", newHost );
        setFieldValue( "login", newLogin );
        setFieldValue( "password", newPassword );
        setFieldValue( "address", newAddress );
        clickButtonWithValue( "Save" );

        if ( !isValid )
        {
            assertTextPresent( "Host is required" );
            assertTextPresent( "Login is required" );
            assertTextPresent( "Password is required" );
            assertTextPresent( "Address is required" );
        }
        else if ( projectName != null )
        {
            assertProjectInformationPage();
        }
        else
        {
            assertGroupNotifierPage( projectGroupName );
        }
    }

    protected void addMsnNotifier( String projectGroupName, String projectName, String login, String password,
                                   String recipientAddress, boolean isValid )
    {
        selectValue( "//select", "MSN" );
        clickButtonWithValue( "Submit" );
        assertAddEditMsnPage();
        setFieldValue( "login", login );
        setFieldValue( "password", password );
        setFieldValue( "address", recipientAddress );
        clickButtonWithValue( "Save" );

        if ( isValid )
        {
            if ( projectName != null )
            {
                assertProjectInformationPage();
            }
            else
            {
                assertGroupNotifierPage( projectGroupName );
            }
        }
    }

    protected void editMsnNotifier( String projectGroupName, String projectName, String oldLogin, String oldAddress,
                                    String newLogin, String newPassword, String newAddress, boolean isValid )
    {
        if ( projectName == null )
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectGroupNotifier') and contains(@href,'msn')])//img" );
        }
        else
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectNotifier') and contains(@href,'msn')])//img" );
        }
        assertAddEditMsnPage();
        assertFieldValue( oldLogin, "login" );
        assertFieldValue( oldAddress, "address" );
        setFieldValue( "login", newLogin );
        setFieldValue( "password", newPassword );
        setFieldValue( "address", newAddress );
        clickButtonWithValue( "Save" );

        if ( !isValid )
        {
            assertTextPresent( "Login is required" );
            assertTextPresent( "Password is required" );
            assertTextPresent( "Address is required" );
        }
        else if ( projectName != null )
        {
            assertProjectInformationPage();
        }
        else
        {
            assertGroupNotifierPage( projectGroupName );
        }
    }

    protected void addWagonNotifierPage( String projectGroupName, String projectName, String siteUrl, String serverId,
                                         boolean isValid )
    {
        selectValue( "//select", "Wagon" );
        clickButtonWithValue( "Submit" );
        assertAddEditWagonPage();
        setFieldValue( "url", siteUrl );
        setFieldValue( "id", serverId );
        clickButtonWithValue( "Save" );

        if ( isValid )
        {
            if ( projectName != null )
            {
                assertProjectInformationPage();
            }
            else
            {
                assertGroupNotifierPage( projectGroupName );
            }
        }
    }

    protected void editWagonNotifier( String projectGroupName, String projectName, String oldUrl, String oldId,
                                      String newUrl, String newId, boolean isValid )
    {
        if ( projectName == null )
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectGroupNotifier') and contains(@href,'wagon')])//img" );
        }
        else
        {
            clickLinkWithXPath( "(//a[contains(@href,'editProjectNotifier') and contains(@href,'wagon')])//img" );
        }
        assertAddEditWagonPage();
        assertFieldValue( oldUrl, "url" );
        assertFieldValue( oldId, "id" );
        setFieldValue( "url", newUrl );
        setFieldValue( "id", newId );
        clickButtonWithValue( "Save" );

        if ( !isValid )
        {
            assertTextPresent( "Destination URL is required" );
            assertTextPresent( "Server Id is required" );
        }
        else if ( projectName != null )
        {
            assertProjectInformationPage();
        }
        else
        {
            assertGroupNotifierPage( projectGroupName );
        }
    }
}
