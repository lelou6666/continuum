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
 * Based on AddAntProjectTestCase of Emmanuel Venisse.
 *
 * @author José Morales Martínez
 */
@Test( groups = {"antProject"} )
public class AntProjectTest
    extends AbstractAdminTest
{
    private String projectName;

    private String projectDescription;

    private String projectVersion;

    private String projectTag;

    private String scmUrl;

    private String scmUsername;

    private String scmPassword;

    private String projectGroupName;

    private String projectGroupId;

    private String projectGroupDescription;

    @BeforeMethod
    protected void setUp()
        throws Exception
    {
        projectName = getProperty( "ANT_NAME" );
        projectDescription = getProperty( "ANT_DESCRIPTION" );
        projectVersion = getProperty( "ANT_VERSION" );
        projectTag = getProperty( "ANT_TAG" );
        scmUrl = getProperty( "ANT_SCM_URL" );
        scmUsername = getProperty( "ANT_SCM_USERNAME" );
        scmPassword = getProperty( "ANT_SCM_PASSWORD" );

        projectGroupName = getProperty( "ANT_PROJECT_GROUP_NAME" );
        projectGroupId = getProperty( "ANT_PROJECT_GROUP_ID" );
        projectGroupDescription = getProperty( "ANT_PROJECT_GROUP_DESCRIPTION" );

        // create project group, if it doesn't exist
        addProjectGroup( projectGroupName, projectGroupId, projectGroupDescription, true, false );
    }

    @AfterMethod
    public void tearDown()
        throws Throwable
    {
        removeProjectGroup( projectGroupName, false );
    }

    public void testAddAntProject()
        throws Exception
    {
<<<<<<< HEAD
        String ANT_NAME = getProperty( "ANT_NAME" );
        String ANT_DESCRIPTION = getProperty( "ANT_DESCRIPTION" );
        String ANT_VERSION = getProperty( "ANT_VERSION" );
        String ANT_TAG = getProperty( "ANT_TAG" );
        String ANT_SCM_URL = getProperty( "ANT_SCM_URL" );
        String ANT_SCM_USERNAME = getProperty( "ANT_SCM_USERNAME" );
        String ANT_SCM_PASSWORD = getProperty( "ANT_SCM_PASSWORD" );
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        goToAddAntProjectPage();
        addProject( ANT_NAME, ANT_DESCRIPTION, ANT_VERSION, ANT_SCM_URL, ANT_SCM_USERNAME, ANT_SCM_PASSWORD, ANT_TAG,
                    false, TEST_PROJ_GRP_NAME, null, true, "ant" );
        assertProjectGroupSummaryPage( TEST_PROJ_GRP_NAME, TEST_PROJ_GRP_ID, TEST_PROJ_GRP_DESCRIPTION );
=======
        goToAddAntProjectPage();
        addProject( projectName, projectDescription, projectVersion, scmUrl, scmUsername, scmPassword, projectTag,
                    projectGroupName, true, "ant" );
        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );
    }

    public void testAddAntProjectWithInvalidValues()
        throws Exception
    {
        String name = "!@#$<>?etc";
        String description = "![]<>'^&etc";
        String version = "<>whitespaces!#etc";
        String tag = "!<>*%etc";
        String scmUrl = "!<>*%etc";
        goToAddAntProjectPage();
        addProject( name, description, version, scmUrl, scmUsername, scmPassword, tag, projectGroupName, false, "ant" );
        assertTextPresent( "Name contains invalid characters." );
        assertTextPresent( "Version contains invalid characters." );
        assertTextPresent( "SCM Url contains invalid characters." );
        assertTextPresent( "SCM Tag contains invalid characters." );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testSubmitEmptyForm()
    {
        goToAddAntProjectPage();
        submit();
        assertAddProjectPage( "ant" );
        assertTextPresent( "Name is required and cannot contain null or spaces only" );
        assertTextPresent( "Version is required and cannot contain null or spaces only" );
        assertTextPresent( "SCM Url is required and cannot contain null or spaces only" );
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddAntProject" } )
    public void testAddDuplicateAntProject()
        throws Exception
    {
        String ANT_NAME = getProperty( "ANT_NAME" );
        String ANT_DESCRIPTION = getProperty( "ANT_DESCRIPTION" );
        String ANT_VERSION = getProperty( "ANT_VERSION" );
        String ANT_TAG = getProperty( "ANT_TAG" );
        String ANT_SCM_URL = getProperty( "ANT_SCM_URL" );
        String ANT_SCM_USERNAME = getProperty( "ANT_SCM_USERNAME" );
        String ANT_SCM_PASSWORD = getProperty( "ANT_SCM_PASSWORD" );
        goToAddAntProjectPage();
        addProject( ANT_NAME, ANT_DESCRIPTION, ANT_VERSION, ANT_SCM_URL, ANT_SCM_USERNAME, ANT_SCM_PASSWORD, ANT_TAG,
                    false, null, null, false, "ant" );
=======
    public void testAddDuplicateAntProject()
        throws Exception
    {
        testAddAntProject();

        goToAddAntProjectPage();
        addProject( projectName, projectDescription, projectVersion, scmUrl, scmUsername, scmPassword, projectTag, null,
                    false, "ant" );
>>>>>>> refs/remotes/apache/trunk
        assertTextPresent( "Project name already exist" );
    }
}
