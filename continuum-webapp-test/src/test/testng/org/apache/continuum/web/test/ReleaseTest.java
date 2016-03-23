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
<<<<<<< HEAD
 *  http://www.apache.org/licenses/LICENSE-2.0
=======
 *   http://www.apache.org/licenses/LICENSE-2.0
>>>>>>> refs/remotes/apache/trunk
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
import org.apache.continuum.web.test.parent.AbstractReleaseTest;
import org.testng.annotations.Test;

@Test( groups = { "release" }, dependsOnMethods = { "testWithCorrectUsernamePassword" } )
public class ReleaseTest
    extends AbstractReleaseTest
{
    @Test( dependsOnMethods = { "testProjectGroupAllBuildSuccessWithDistributedBuilds" } )
    public void testReleasePrepareProjectWithInvalidUsernamePasswordInDistributedBuilds()
        throws Exception
    {
        String M2_PROJ_GRP_NAME = getProperty( "M2_DELETE_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_DELETE_PROJ_GRP_ID" );

        String M2_PROJ_USERNAME = "invalid";
        String M2_PROJ_PASSWORD = "invalid";
        String M2_PROJ_TAGBASE = getProperty( "M2_DELETE_PROJ_TAGBASE" );
        String M2_PROJ_TAG = getProperty( "M2_DELETE_PROJ_TAG" );
        String M2_PROJ_RELEASE_VERSION = getProperty( "M2_DELETE_PROJ_RELEASE_VERSION" );
        String M2_PROJ_DEVELOPMENT_VERSION = getProperty( "M2_DELETE_PROJ_DEVELOPMENT_VERSION" );

        init();

        try
        {
            enableDistributedBuilds();
        
            String M2_PROJECT_BUILD_ENV = getProperty( "M2_RELEASE_BUILD_ENV" );
            createBuildEnvAndBuildagentGroup();
            
            showProjectGroup( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, "" );
            clickButtonWithValue( "Release" );
            assertReleaseSuccess();
            releasePrepareProject( M2_PROJ_USERNAME, M2_PROJ_PASSWORD, M2_PROJ_TAGBASE, M2_PROJ_TAG,
                                   M2_PROJ_RELEASE_VERSION, M2_PROJ_DEVELOPMENT_VERSION, M2_PROJECT_BUILD_ENV, false );
            assertPreparedReleasesFileCreated();
        }
        finally
        {
            disableDistributedBuilds();
        }
    }
    
    /*
     * Test release prepare with no build agent configured in the selected build environment.
     */
    @Test( dependsOnMethods = { "testReleasePrepareProjectWithInvalidUsernamePasswordInDistributedBuilds" } )
    public void testReleasePrepareProjectWithNoBuildagentInBuildEnvironment()
        throws Exception
    {
        String M2_PROJECT_POM_URL = getProperty( "M2_RELEASE_POM_URL" );
        String M2_PROJECT_NAME = getProperty( "M2_RELEASE_PROJECT_NAME" );
        String M2_PROJECT_GROUP_NAME = getProperty( "M2_RELEASE_GRP_NAME" );
        String M2_PROJECT_GROUP_ID = getProperty( "M2_RELEASE_GRP_ID" );
        String M2_PROJECT_DESCRIPTION = getProperty( "M2_RELEASE_GRP_DESCRIPTION" );
        String M2_PROJECT_TAGBASE = getProperty( "M2_RELEASE_TAGBASE_URL" );
        String M2_PROJECT_TAG = getProperty( "M2_RELEASE_TAG" );
        String M2_PROJECT_RELEASE_VERSION = getProperty( "M2_RELEASE_RELEASE_VERSION" );
        String M2_PROJECT_DEVELOPMENT_VERSION = getProperty( "M2_RELEASE_DEVELOPMENT_VERSION" );
        String ERROR_TEXT = getProperty( "M2_RELEASE_NO_AGENT_MESSAGE" );
        
        addProjectGroup( M2_PROJECT_GROUP_NAME, M2_PROJECT_GROUP_ID, M2_PROJECT_DESCRIPTION, true );
        addMavenTwoProject( M2_PROJECT_POM_URL, "", "", M2_PROJECT_GROUP_NAME, true );

        init();

        try
        {
            enableDistributedBuilds();
        
            String M2_PROJECT_BUILD_ENV = getProperty( "M2_RELEASE_BUILD_ENV" );
            createBuildEnvAndBuildagentGroup();
            detachBuildagentFromGroup();
            
            buildProjectGroup( M2_PROJECT_GROUP_NAME, M2_PROJECT_GROUP_ID, M2_PROJECT_DESCRIPTION, M2_PROJECT_NAME );
            
            clickButtonWithValue( "Release" );
            assertReleaseSuccess();
            releasePrepareProject( "", "", M2_PROJECT_TAGBASE, M2_PROJECT_TAG, M2_PROJECT_RELEASE_VERSION,
                                   M2_PROJECT_DEVELOPMENT_VERSION, M2_PROJECT_BUILD_ENV );
            
            assertTextPresent( "Release Error" );
            assertTextPresent( ERROR_TEXT );
        }
        finally
        {
            attachBuildagentInGroup();
            disableDistributedBuilds();
        }
    }
    
    /*
     * Test release prepare with no build agent group in the selected build environment.
     */
    @Test( dependsOnMethods = { "testReleasePrepareProjectWithNoBuildagentInBuildEnvironment" } )
    public void testReleasePrepareProjectWithNoBuildagentGroupInBuildEnvironment()
        throws Exception
    {
        String M2_PROJECT_NAME = getProperty( "M2_RELEASE_PROJECT_NAME" );
        String M2_PROJECT_GROUP_NAME = getProperty( "M2_RELEASE_GRP_NAME" );
        String M2_PROJECT_GROUP_ID = getProperty( "M2_RELEASE_GRP_ID" );
        String M2_PROJECT_DESCRIPTION = getProperty( "M2_RELEASE_GRP_DESCRIPTION" );
        String M2_PROJECT_TAGBASE = getProperty( "M2_RELEASE_TAGBASE_URL" );
        String M2_PROJECT_TAG = getProperty( "M2_RELEASE_TAG" );
        String M2_PROJECT_RELEASE_VERSION = getProperty( "M2_RELEASE_RELEASE_VERSION" );
        String M2_PROJECT_DEVELOPMENT_VERSION = getProperty( "M2_RELEASE_DEVELOPMENT_VERSION" );
        String ERROR_TEXT = getProperty( "M2_RELEASE_NO_AGENT_MESSAGE" );

        init();

        try
        {
            enableDistributedBuilds();
            
            String M2_PROJECT_BUILD_ENV = getProperty( "M2_RELEASE_BUILD_ENV" );
            createBuildEnvAndBuildagentGroup();
            removeBuildagentGroupFromBuildEnv();
            
            showProjectGroup( M2_PROJECT_GROUP_NAME, M2_PROJECT_GROUP_ID, M2_PROJECT_GROUP_ID );
            
            clickButtonWithValue( "Release" );
            assertReleaseSuccess();
            releasePrepareProject( "", "", M2_PROJECT_TAGBASE, M2_PROJECT_TAG, M2_PROJECT_RELEASE_VERSION,
                                   M2_PROJECT_DEVELOPMENT_VERSION, M2_PROJECT_BUILD_ENV );
            
            assertTextPresent( "Release Error" );
            assertTextPresent( ERROR_TEXT );
        }
        finally
        {
            attachBuildagentGroupToBuildEnv();
            disableDistributedBuilds();
        }
    }
    
    /*
     * Test release prepare with no build environment selected.
     */
    @Test( dependsOnMethods = { "testReleasePrepareProjectWithNoBuildagentGroupInBuildEnvironment" } )
    public void testReleasePrepareProjectWithNoBuildEnvironment()
        throws Exception
    {
        String M2_PROJECT_NAME = getProperty( "M2_RELEASE_PROJECT_NAME" );
        String M2_PROJECT_GROUP_NAME = getProperty( "M2_RELEASE_GRP_NAME" );
        String M2_PROJECT_GROUP_ID = getProperty( "M2_RELEASE_GRP_ID" );
        String M2_PROJECT_DESCRIPTION = getProperty( "M2_RELEASE_GRP_DESCRIPTION" );
        String M2_PROJECT_TAGBASE = getProperty( "M2_RELEASE_TAGBASE_URL" );
        String M2_PROJECT_TAG = getProperty( "M2_RELEASE_TAG" );
        String M2_PROJECT_RELEASE_VERSION = getProperty( "M2_RELEASE_RELEASE_VERSION" );
        String M2_PROJECT_DEVELOPMENT_VERSION = getProperty( "M2_RELEASE_DEVELOPMENT_VERSION" );
        String ERROR_TEXT = getProperty( "M2_RELEASE_NO_AGENT_MESSAGE" );

        init();

        try
        {
            enableDistributedBuilds();
            
            showProjectGroup( M2_PROJECT_GROUP_NAME, M2_PROJECT_GROUP_ID, M2_PROJECT_GROUP_ID );
            
            clickButtonWithValue( "Release" );
            assertReleaseSuccess();
            releasePrepareProject( "", "", M2_PROJECT_TAGBASE, M2_PROJECT_TAG, M2_PROJECT_RELEASE_VERSION,
                                   M2_PROJECT_DEVELOPMENT_VERSION, "" );
            
            assertTextPresent( "Release Error" );
            assertTextPresent( ERROR_TEXT );
        }
        finally
        {
            disableDistributedBuilds();
        }
    }

    @Test( dependsOnMethods = { "testReleasePrepareProjectWithNoBuildEnvironment" } )
    public void testReleasePerformUsingProvideParamtersWithDistributedBuilds()
        throws Exception
    {
        String M2_PROJ_GRP_NAME = getProperty( "M2_DELETE_PROJ_GRP_NAME" );
        String M2_PROJ_GRP_ID = getProperty( "M2_DELETE_PROJ_GRP_ID" );

        String M2_PROJ_USERNAME = "invalid";
        String M2_PROJ_PASSWORD = "invalid";
        String M2_PROJ_TAGBASE = getProperty( "M2_DELETE_PROJ_TAGBASE_PERFORM" );
        String M2_PROJ_TAG = getProperty( "M2_DELETE_PROJ_TAG" );
        String M2_PROJ_SCM_URL = getProperty( "M2_DELETE_PROJ_GRP_SCM_ROOT_URL" );

        init();
        
        try
        {
            enableDistributedBuilds();
        
            String M2_PROJECT_BUILD_ENV = getProperty( "M2_RELEASE_BUILD_ENV" );
            createBuildEnvAndBuildagentGroup();
            
            showProjectGroup( M2_PROJ_GRP_NAME, M2_PROJ_GRP_ID, "" );
            clickButtonWithValue( "Release" );
            assertReleaseSuccess();
            releasePerformProjectWithProvideParameters( M2_PROJ_USERNAME, M2_PROJ_PASSWORD, M2_PROJ_TAGBASE, M2_PROJ_TAG, 
                                                        M2_PROJ_SCM_URL, M2_PROJECT_BUILD_ENV, false );
            assertPreparedReleasesFileCreated();

            removeProjectGroup( M2_PROJ_GRP_NAME );
            assertLinkNotPresent( M2_PROJ_GRP_NAME );
        }
        finally
        {
            disableDistributedBuilds();
        }
    }

    private void init()
    {
        File file = new File( "target/conf/prepared-releases.xml" );

        if ( file.exists() )
        {
            file.delete();
        }
    }
    
    private void createBuildEnvAndBuildagentGroup()
        throws Exception
    {
        String M2_PROJECT_BUILD_ENV = getProperty( "M2_RELEASE_BUILD_ENV" );
        String M2_PROJECT_AGENT_GROUP = getProperty( "M2_RELEASE_AGENT_GROUP" );
        
        // add build agent group no agents
        goToBuildAgentPage();
        if ( !isTextPresent( M2_PROJECT_AGENT_GROUP ) )
        {
            clickAndWait( "//input[@id='editBuildAgentGroup_0']" );
            setFieldValue( "saveBuildAgentGroup_buildAgentGroup_name", M2_PROJECT_AGENT_GROUP );
            clickButtonWithValue( "Save" );
        }
            
        // add build environment with build agent group
        clickLinkWithText( "Build Environments" );
        if ( !isTextPresent( M2_PROJECT_BUILD_ENV ) )
        {
            clickAndWait( "//input[@id='addBuildEnv_0']" );
            setFieldValue( "saveBuildEnv_profile_name", M2_PROJECT_BUILD_ENV );
            clickButtonWithValue( "Save" );
            attachBuildagentGroupToBuildEnv();
        }
        
        // attach build agent in build agent group created
        attachBuildagentInGroup();
    }
    
    private void attachBuildagentGroupToBuildEnv()
    {
        String M2_PROJECT_BUILD_ENV = getProperty( "M2_RELEASE_BUILD_ENV" );
        String M2_PROJECT_AGENT_GROUP = getProperty( "M2_RELEASE_AGENT_GROUP" );
        
        clickLinkWithText( "Build Environments" );
        String xPath = "//preceding::td[text()='" + M2_PROJECT_BUILD_ENV + "']//following::img[@alt='Edit']";
        clickLinkWithXPath( xPath );
        selectValue( "profile.buildAgentGroup", M2_PROJECT_AGENT_GROUP );
        clickButtonWithValue( "Save" );
    }
    
    private void removeBuildagentGroupFromBuildEnv()
    {
        String M2_PROJECT_BUILD_ENV = getProperty( "M2_RELEASE_BUILD_ENV" );
        
        clickLinkWithText( "Build Environments" );
        String xPath = "//preceding::td[text()='" + M2_PROJECT_BUILD_ENV + "']//following::img[@alt='Edit']";
        clickLinkWithXPath( xPath );
        selectValue( "profile.buildAgentGroup", "" );
        clickButtonWithValue( "Save" );
    }
    
    private void attachBuildagentInGroup()
        throws Exception
    {
        String M2_PROJECT_AGENT_GROUP = getProperty( "M2_RELEASE_AGENT_GROUP" );
        String buildAgent = getProperty( "BUILD_AGENT_NAME2" );
        
        clickLinkWithText( "Build Agents" );
        String xPath = "//preceding::td[text()='" + M2_PROJECT_AGENT_GROUP + "']//following::img[@alt='Edit']";
        clickLinkWithXPath( xPath );
        
        if ( isElementPresent( "xpath=//select[@id='saveBuildAgentGroup_buildAgentIds']/option[@value='" + buildAgent + "']" ) )
        {
            selectValue( "buildAgentIds", buildAgent );
            clickLinkWithXPath( "//input[@value='->']", false );
            submit();
        }
    }
    
    private void detachBuildagentFromGroup()
        throws Exception
    {
        String M2_PROJECT_AGENT_GROUP = getProperty( "M2_RELEASE_AGENT_GROUP" );
        String buildAgent = getProperty( "BUILD_AGENT_NAME2" );
        
        clickLinkWithText( "Build Agents" );
        String xPath = "//preceding::td[text()='" + M2_PROJECT_AGENT_GROUP + "']//following::img[@alt='Edit']";
        clickLinkWithXPath( xPath );
        
        if ( isElementPresent( "xpath=//select[@id='saveBuildAgentGroup_selectedBuildAgentIds']/option[@value='" + buildAgent + "']" ) )
        {
            selectValue( "selectedBuildAgentIds", buildAgent );
            clickLinkWithXPath( "//input[@value='<-']", false );
            submit();
        }
    }
=======
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

@Test( groups = {"release"} )
public class ReleaseTest
    extends AbstractReleaseTest
{
    private String projectGroupName;

    private String projectGroupId;

    private String tagBase;

    private String tag;

    private String releaseVersion;

    private String developmentVersion;

    private String releaseProjectScmUrl;

    @BeforeClass
    public void createAndBuildProject()
    {
        projectGroupName = getProperty( "RELEASE_PROJECT_GROUP_NAME" );
        projectGroupId = getProperty( "RELEASE_PROJECT_GROUP_ID" );
        String description = "Release test projects";

        loginAsAdmin();

        String pomUrl = getProperty( "MAVEN2_POM_URL" );
        String pomUsername = getProperty( "MAVEN2_POM_USERNAME" );
        String pomPassword = getProperty( "MAVEN2_POM_PASSWORD" );
        String projectName = getProperty( "MAVEN2_POM_PROJECT_NAME" );

        removeProjectGroup( projectGroupName, false );

        addProjectGroup( projectGroupName, projectGroupId, description, true, false );
        clickLinkWithText( projectGroupName );

        if ( !isLinkPresent( projectName ) )
        {
            addMavenTwoProject( pomUrl, pomUsername, pomPassword, projectGroupName, true );

            buildProjectGroup( projectGroupName, projectGroupId, "", projectName, true );
        }
    }

    @BeforeMethod
    public void setUp()
        throws IOException
    {
        tagBase = getProperty( "RELEASE_PROJECT_TAGBASE" );
        tag = getProperty( "RELEASE_PROJECT_TAG" );
        releaseVersion = getProperty( "RELEASE_PROJECT_VERSION" );
        developmentVersion = getProperty( "RELEASE_PROJECT_DEVELOPMENT_VERSION" );
        releaseProjectScmUrl = getProperty( "RELEASE_PROJECT_SCM_URL" );
    }

    @AfterMethod
    public void tearDown()
        throws Exception
    {
    }

    // can't test u/p locally and don't have a suitable SVN server to test against
    @Test( enabled = false )
    public void testReleasePrepareProjectWithInvalidUsernamePassword()
        throws Exception
    {
        String releaseUsername = "invalid";
        String releasePassword = "invalid";

        showProjectGroup( projectGroupName, projectGroupId, "" );
        clickButtonWithValue( RELEASE_BUTTON_TEXT );
        assertReleaseChoicePage();
        releasePrepareProject( releaseUsername, releasePassword, tagBase, "simple-example-13.0", "13.0",
                               "13.1-SNAPSHOT", null );
        assertReleasePhaseError();
    }

    public void testReleasePrepareProject()
        throws Exception
    {
        showProjectGroup( projectGroupName, projectGroupId, projectGroupId );

        clickButtonWithValue( RELEASE_BUTTON_TEXT );
        assertReleaseChoicePage();
        releasePrepareProject( "", "", tagBase, tag, releaseVersion, developmentVersion, "" );

        assertReleasePhaseSuccess();
    }

    @Test( dependsOnMethods = {"testReleasePrepareProject"} )
    public void testReleasePerformUsingProvidedParameters()
        throws Exception
    {
        String releaseUsername = "invalid";
        String releasePassword = "invalid";

        showProjectGroup( projectGroupName, projectGroupId, "" );
        clickButtonWithValue( RELEASE_BUTTON_TEXT );
        assertReleaseChoicePage();
        releasePerformProjectWithProvideParameters( releaseUsername, releasePassword, tagBase, tag,
                                                    releaseProjectScmUrl, "" );
    }

    // avoid the above test running between these so that the list of prepared releases is correct
    @Test( dependsOnMethods = {"testReleasePrepareProject"} )
    public void testReleasePrepareWithoutInterveningPerform()
        throws IOException
    {
        // CONTINUUM-2687
        showProjectGroup( projectGroupName, projectGroupId, "" );
        clickButtonWithValue( RELEASE_BUTTON_TEXT );
        assertReleaseChoicePage();

        // first attempt
        releasePrepareProject( "", "", tagBase, "simple-example-10.1", "10.1", "10.2-SNAPSHOT", "" );
        assertReleasePhaseSuccess();
        clickButtonWithValue( "Done" );

        // second attempt
        releasePrepareProject( "", "", tagBase, "simple-example-10.2", "10.2", "10.3-SNAPSHOT", "" );
        assertReleasePhaseSuccess();
        clickButtonWithValue( "Done" );

        // check that two versions are present
        Assert.assertEquals( Arrays.asList( getSelenium().getSelectOptions( "preparedReleaseId" ) ), Arrays.asList(
            "10.0", "10.1", "10.2", PROVIDE_RELEASE_PARAMETERS_TEXT ) );

        // check that 10.2 is selected by default
        Assert.assertEquals( getSelenium().getSelectedLabel( "preparedReleaseId" ), "10.2" );

        // test perform on 10.2
        selectPerformAndSubmit();

        setFieldValue( "goals", "clean validate" );
        submit();

        waitForRelease();

        assertReleasePhaseSuccess();
        clickButtonWithValue( "Done" );

        // verify that it is removed from the list again
        showProjectGroup( projectGroupName, projectGroupId, "" );
        clickButtonWithValue( RELEASE_BUTTON_TEXT );
        assertReleaseChoicePage();
        Assert.assertEquals( Arrays.asList( getSelenium().getSelectOptions( "preparedReleaseId" ) ), Arrays.asList(
            "10.0", "10.1", PROVIDE_RELEASE_PARAMETERS_TEXT ) );
        Assert.assertEquals( getSelenium().getSelectedLabel( "preparedReleaseId" ), "10.1" );
    }

>>>>>>> refs/remotes/apache/trunk
}
