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
 * Based on AddMavenOneProjectTestCase of Emmanuel Venisse.
 *
 * @author José Morales Martínez
 */
@Test( groups = {"mavenOneProject"} )
public class MavenOneProjectTest
    extends AbstractAdminTest
{
<<<<<<< HEAD
    public void testAddMavenOneProjectWithNoDefaultBuildDefinitionFromTemplate()
        throws Exception
    {
        String M1_POM_URL = getProperty( "M1_POM_URL" );
        String M1_POM_USERNAME = getProperty( "M1_POM_USERNAME" );
        String M1_POM_PASSWORD = getProperty( "M1_POM_PASSWORD" );
        String M1_PROJ_GRP_NAME = getProperty( "M1_PROJ_GRP_NAME" );
        String M1_PROJ_GRP_ID = getProperty( "M1_PROJ_GRP_ID" );
        String M1_PROJ_GRP_DESCRIPTION = getProperty( "M1_PROJ_GRP_DESCRIPTION" );

        removeDefaultBuildDefinitionFromTemplate( "maven1" );
        
        goToAddMavenOneProjectPage();
        addMavenOneProject( M1_POM_URL, M1_POM_USERNAME, M1_POM_PASSWORD, null, null, true );
        assertProjectGroupSummaryPage( M1_PROJ_GRP_NAME, M1_PROJ_GRP_ID, M1_PROJ_GRP_DESCRIPTION );

        // Delete project group
        removeProjectGroup( M1_PROJ_GRP_NAME );

        // Re-add default build definition of template
        addDefaultBuildDefinitionFromTemplate( "maven1" );
    }

    /**
     * test with valid pom url
     */
    @Test( dependsOnMethods = { "testAddMavenOneProjectWithNoDefaultBuildDefinitionFromTemplate" } )
    public void testValidPomUrl()
        throws Exception
    {
        String M1_POM_URL = getProperty( "M1_POM_URL" );
        String M1_POM_USERNAME = getProperty( "M1_POM_USERNAME" );
        String M1_POM_PASSWORD = getProperty( "M1_POM_PASSWORD" );
        String M1_PROJ_GRP_NAME = getProperty( "M1_PROJ_GRP_NAME" );
        String M1_PROJ_GRP_ID = getProperty( "M1_PROJ_GRP_ID" );
        String M1_PROJ_GRP_DESCRIPTION = getProperty( "M1_PROJ_GRP_DESCRIPTION" );
        // Enter values into Add Maven Two Project fields, and submit
=======
    private String pomUrl;

    private String pomUsername;

    private String projectGroupId;

    private String projectGroupDescription;

    private String projectGroupName;

    private String pomPassword;

    private String pomUrlMissingElement;

    private String pomUrlWithExtend;

    private String pomUrlUnparseableContent;

    private String malformedPomUrl;

    private String inaccessiblePomUrl;

    @BeforeMethod
    protected void setUp()
        throws Exception
    {
        pomUrl = getProperty( "MAVEN1_POM_URL" );
        pomUsername = getProperty( "MAVEN1_POM_USERNAME" );
        pomPassword = getProperty( "MAVEN1_POM_PASSWORD" );
        projectGroupName = getProperty( "MAVEN1_PROJECT_GROUP_NAME" );
        projectGroupId = getProperty( "MAVEN1_PROJECT_GROUP_ID" );
        projectGroupDescription = getProperty( "MAVEN1_PROJECT_GROUP_DESCRIPTION" );

        pomUrlMissingElement = getProperty( "MAVEN1_MISSING_REPO_POM_URL" );
        pomUrlWithExtend = getProperty( "MAVEN1_EXTENDED_POM_URL" );
        pomUrlUnparseableContent = getProperty( "MAVEN1_UNPARSEABLE_POM_URL" );

        malformedPomUrl = "aaa";
        inaccessiblePomUrl = baseUrl + "/inaccessible-pom/";
    }

    @AfterMethod
    protected void tearDown()
    {
        removeProjectGroup( projectGroupName, false );
    }

    public void testAddMavenOneProjectToProjectGroup()
        throws Exception
    {
        goToProjectGroupsSummaryPage();
        String defaultProjectGroupName = getProperty( "DEFAULT_PROJECT_GROUP_NAME" );
        clickLinkWithText( defaultProjectGroupName );
        selectValue( "preferredExecutor", "Add M1 Project" );
        clickButtonWithValue( "Add" );
        assertAddMavenOneProjectPage( defaultProjectGroupName );
        // rest is tested by other methods
    }

    public void testAddMavenOneProjectWithNoDefaultBuildDefinitionFromTemplate()
        throws Exception
    {
        removeDefaultBuildDefinitionFromTemplate( "maven1" );

>>>>>>> refs/remotes/apache/trunk
        goToAddMavenOneProjectPage();
        addMavenOneProject( pomUrl, pomUsername, pomPassword, null, true );
        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );

        // Re-add default build definition of template
        addDefaultBuildDefinitionFromTemplate( "maven1" );
    }

    /**
     * test with valid pom url
     */
    public void testValidPomUrl()
        throws Exception
    {
<<<<<<< HEAD
        String TEST_PROJ_GRP_NAME = getProperty( "TEST_PROJ_GRP_NAME" );
        String TEST_PROJ_GRP_ID = getProperty( "TEST_PROJ_GRP_ID" );
        String TEST_PROJ_GRP_DESCRIPTION = getProperty( "TEST_PROJ_GRP_DESCRIPTION" );
        String M1_POM_URL = getProperty( "M1_POM_URL" );
        String M1_POM_USERNAME = getProperty( "M1_POM_USERNAME" );
        String M1_POM_PASSWORD = getProperty( "M1_POM_PASSWORD" );
=======
        // Enter values into Add Maven Two Project fields, and submit
>>>>>>> refs/remotes/apache/trunk
        goToAddMavenOneProjectPage();
        addMavenOneProject( pomUrl, pomUsername, pomPassword, null, true );
        assertProjectGroupSummaryPage( projectGroupName, projectGroupId, projectGroupDescription );
    }

    /**
     * test with no pom file or pom url specified
     */
    public void testNoPomSpecified()
        throws Exception
    {
        goToAddMavenOneProjectPage();
        addMavenOneProject( "", "", "", null, false );
        assertTextPresent( "Either POM URL or Upload POM is required." );
    }

    /**
     * test with missing <repository> element in the pom file
     */
    public void testMissingElementInPom()
        throws Exception
    {
<<<<<<< HEAD
        String M1_MISS_REPO_POM_URL = getProperty( "M1_MISS_REPO_POM_URL" );
        String M1_POM_USERNAME = getProperty( "M1_POM_USERNAME" );
        String M1_POM_PASSWORD = getProperty( "M1_POM_PASSWORD" );
=======
>>>>>>> refs/remotes/apache/trunk
        goToAddMavenOneProjectPage();
        addMavenOneProject( pomUrlMissingElement, pomUsername, pomPassword, null, false );
        assertTextPresent( "Missing 'repository' element in the POM." );
    }

    /**
     * test with <extend> element present in pom file
     */
    public void testWithExtendElementPom()
        throws Exception
    {
<<<<<<< HEAD
        String M1_EXTENDED_POM_URL = getProperty( "M1_EXTENDED_POM_URL" );
        String M1_POM_USERNAME = getProperty( "M1_POM_USERNAME" );
        String M1_POM_PASSWORD = getProperty( "M1_POM_PASSWORD" );
=======
>>>>>>> refs/remotes/apache/trunk
        goToAddMavenOneProjectPage();
        addMavenOneProject( pomUrlWithExtend, pomUsername, pomPassword, null, false );
        assertTextPresent( "Cannot use a POM with an 'extend' element" );
    }

    /**
     * test with unparseable xml content for pom file
     */
    public void testUnparseableXmlContent()
        throws Exception
    {
<<<<<<< HEAD
        String M1_UNPARSEABLE_POM_URL = getProperty( "M1_UNPARSEABLE_POM_URL" );
        String M1_POM_USERNAME = getProperty( "M1_POM_USERNAME" );
        String M1_POM_PASSWORD = getProperty( "M1_POM_PASSWORD" );
=======
>>>>>>> refs/remotes/apache/trunk
        goToAddMavenOneProjectPage();
        addMavenOneProject( pomUrlUnparseableContent, pomUsername, pomPassword, null, false );
        assertTextPresent( "The XML content of the POM can not be parsed." );
    }

    /**
     * test with a malformed pom url
     */
    public void testMalformedPomUrl()
        throws Exception
    {
        goToAddMavenOneProjectPage();
        addMavenOneProject( malformedPomUrl, "", "", null, false );
        assertTextPresent(
            "The specified resource cannot be accessed. Please try again later or contact your administrator." );
    }

    /**
     * test with an inaccessible pom url
     */
    public void testInaccessiblePomUrl()
        throws Exception
    {
<<<<<<< HEAD
        String pomUrl = "http://localhost:9595/";
=======
>>>>>>> refs/remotes/apache/trunk
        goToAddMavenOneProjectPage();
        addMavenOneProject( inaccessiblePomUrl, "", "", null, false );
        assertTextPresent(
            "POM file does not exist. Either the POM you specified or one of its modules does not exist." );
    }

    /**
     * test cancel button
     */
    public void testCancelButton()
    {
        goToAboutPage();
        goToAddMavenOneProjectPage();
        clickButtonWithValue( "Cancel" );
        assertAboutPage();
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testValidPomUrl" } )
    public void testDeleteMavenOneProject()
        throws Exception
    {
        boolean isExisting = false;
        String M1_PROJ_GRP_NAME = getProperty( "M1_DELETE_PROJ_GRP_NAME" );
        goToProjectGroupsSummaryPage();
        
        if ( isLinkPresent( M1_PROJ_GRP_NAME ) )
        {
            isExisting = true;
        }
        else
        {
            addMaven1Project( M1_PROJ_GRP_NAME );
        }
        
        // delete project - delete icon
        clickLinkWithText( M1_PROJ_GRP_NAME );
=======
    public void testDeleteMavenOneProject()
        throws Exception
    {
        // setup
        goToProjectGroupsSummaryPage();
        addMaven1Project( projectGroupName, pomUrl, pomUsername, pomPassword );

        // delete project - delete icon
        clickLinkWithText( projectGroupName );
>>>>>>> refs/remotes/apache/trunk
        clickLinkWithXPath( "//tbody/tr['0']/td['10']/a/img[@alt='Delete']" );
        assertTextPresent( "Delete Continuum Project" );
        clickButtonWithValue( "Delete" );
        assertPage( "Continuum - Project Group" );
<<<<<<< HEAD
        assertLinkNotPresent( M1_PROJ_GRP_NAME );
        
        // remove group for next test
        removeProjectGroup( M1_PROJ_GRP_NAME );
        
        // delete project - "Delete Project(s)" button
        addMaven1Project( M1_PROJ_GRP_NAME );
        clickLinkWithText( M1_PROJ_GRP_NAME );
=======
        assertLinkNotPresent( projectGroupName );
    }

    public void testDeleteMavenOneProjects()
        throws Exception
    {
        // setup
        goToProjectGroupsSummaryPage();
        addMaven1Project( projectGroupName, pomUrl, pomUsername, pomPassword );

        // delete project - "Delete Project(s)" button
        clickLinkWithText( projectGroupName );
>>>>>>> refs/remotes/apache/trunk
        checkField( "//tbody/tr['0']/td['0']/input[@name='selectedProjects']" );
        clickButtonWithValue( "Delete Project(s)" );
        assertTextPresent( "Delete Continuum Projects" );
        clickButtonWithValue( "Delete" );
        assertPage( "Continuum - Project Group" );
<<<<<<< HEAD
        assertLinkNotPresent( M1_PROJ_GRP_NAME );
        
        if ( !isExisting )
        {
            removeProjectGroup( M1_PROJ_GRP_NAME );
        }
    }
    
    private void addMaven1Project( String groupName )
        throws Exception
    {
        String M1_POM_URL = getProperty( "M1_DELETE_POM_URL" );
        String M1_POM_USERNAME = getProperty( "M1_POM_USERNAME" );
        String M1_POM_PASSWORD = getProperty( "M1_POM_PASSWORD" );
        String M1_PROJ_GRP_ID = getProperty( "M1_DELETE_PROJ_GRP_ID" );
        String M1_PROJ_GRP_DESCRIPTION = getProperty( "M1_DELETE_PROJ_GRP_DESCRIPTION" );
        
        goToAddMavenOneProjectPage();
        assertLinkNotPresent( groupName );
        addMavenOneProject( M1_POM_URL, M1_POM_USERNAME, M1_POM_PASSWORD, null, null, true );
=======
        assertLinkNotPresent( projectGroupName );
    }

    private void addMaven1Project( String groupName, String pomUrl, String pomUsername, String pomPassword )
    {
        assertLinkNotPresent( groupName );
        goToAddMavenOneProjectPage();
        addMavenOneProject( pomUrl, pomUsername, pomPassword, null, true );
>>>>>>> refs/remotes/apache/trunk
        goToProjectGroupsSummaryPage();
        assertLinkPresent( groupName );
    }
}
