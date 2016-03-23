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

import org.apache.continuum.web.test.parent.AbstractInstallationTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author José Morales Martínez
 */
@Test( groups = {"installation"} )
public class InstallationTest
    extends AbstractInstallationTest
{
<<<<<<< HEAD
    public void testAddJdkToolWithoutBuildEnvironment()
    {
        String INSTALL_TOOL_JDK_NAME = getProperty( "INSTALL_TOOL_JDK_NAME" );
		String INSTALL_TOOL_JDK_PATH = isWindows() ? getProperty( "INSTALL_TOOL_JDK_PATH" ) :  getEscapeProperty( "INSTALL_TOOL_JDK_PATH" );
		goToAddInstallationTool();
		addInstallation( INSTALL_TOOL_JDK_NAME, "JDK", INSTALL_TOOL_JDK_PATH, false, true, true );
=======
    private static final String JDK_VAR_NAME = "JDK";

    private static final String MAVEN_VAR_NAME = "Maven";

    private String jdkName;

    private String jdkPath;

    private String varName;

    private String varVariableName;

    private String varPath;

    private String mavenName;

    private String mavenPath;

    private String varNameNoBE;

    private String varNameOptions;

    @BeforeMethod
    protected void setUp()
        throws Exception
    {
        jdkName = getProperty( "INSTALL_TOOL_JDK_NAME" );
        jdkPath = getProperty( "INSTALL_TOOL_JDK_PATH" );
        varName = getProperty( "INSTALL_VAR_NAME" );
        varVariableName = getProperty( "INSTALL_VAR_VARIABLE_NAME" );
        varPath = getProperty( "INSTALL_VAR_PATH" );
        mavenName = getProperty( "INSTALL_TOOL_MAVEN_NAME" );
        mavenPath = getProperty( "INSTALL_TOOL_MAVEN_PATH" );
        varNameNoBE = "var_without_build_environment";
        varNameOptions = "var_with_options";
    }

    @AfterClass
    public void cleanup()
    {
        for ( String installation : Arrays.asList( jdkName, varName, mavenName, varNameNoBE, varNameOptions ) )
        {
            removeInstallation( installation, false );
            removeBuildEnvironment( installation, false );
        }
    }

    public void testAddJdkToolWithoutBuildEnvironment()
    {
        goToAddInstallationTool();
        addInstallation( jdkName, JDK_VAR_NAME, jdkPath, false, true, true );
    }

    public void testAddJdkToolWithoutBuildEnvironmentWithInvalidValues()
    {
        String jdkName = "!@#$<>?etc";
        String jdkPath = "!@#$<>?etc";
        goToAddInstallationTool();
        addInstallation( jdkName, JDK_VAR_NAME, jdkPath, false, true, false );
        assertTextPresent( "Installation name contains invalid characters." );
        assertTextPresent( "Installation value contains invalid characters." );
>>>>>>> refs/remotes/apache/trunk
    }

    public void testAddMavenToolWithBuildEnvironment()
    {
<<<<<<< HEAD
        String INSTALL_TOOL_MAVEN_NAME = getProperty( "INSTALL_TOOL_MAVEN_NAME" );
		String INSTALL_TOOL_MAVEN_PATH = isWindows() ? getProperty( "INSTALL_TOOL_MAVEN_PATH" ) :  getEscapeProperty( "INSTALL_TOOL_MAVEN_PATH" );
		goToAddInstallationTool();
		addInstallation( INSTALL_TOOL_MAVEN_NAME, "Maven 2", INSTALL_TOOL_MAVEN_PATH, true, true, true );
		// TODO: Validate build environment
		
    }

    public void testAddInstallationVariableWithBuildEnvironment()
    {
        String INSTALL_VAR_NAME = getProperty( "INSTALL_VAR_NAME" );
        String INSTALL_VAR_VARIABLE_NAME = getProperty( "INSTALL_VAR_VARIABLE_NAME" );
        String INSTALL_VAR_PATH = getProperty( "INSTALL_VAR_PATH" );
        goToAddInstallationVariable();
        addInstallation( INSTALL_VAR_NAME, INSTALL_VAR_VARIABLE_NAME, INSTALL_VAR_PATH, true, false, true );
        // TODO: Validate build environment
    }

    public void testAddInstallationVariableWithoutBuildEnvironment()
    {
        String INSTALL_VAR_NAME = "var_without_build_environment";
        String INSTALL_VAR_VARIABLE_NAME = "var_name";
        String INSTALL_VAR_PATH = "path";
=======
        goToAddInstallationTool();
        addInstallation( mavenName, MAVEN_VAR_NAME, mavenPath, true, true, true );
        // TODO: Validate build environment

    }

    public void testAddInstallationVariableWithBuildEnvironment()
    {
        goToAddInstallationVariable();
        addInstallation( varName, varVariableName, varPath, true, false, true );
        // TODO: Validate build environment
    }

    public void testAddInstallationVariableWithoutBuildEnvironment()
    {
        String varVariableName = "var_name";
        String varPath = "path";
        goToAddInstallationVariable();
        addInstallation( varNameNoBE, varVariableName, varPath, false, false, true );
    }

    public void testAddInstallationVariableWithOtherOptions()
    {
        String varVariableName = "JAVA_OPTS";
        String varPath = "-XX:+CompressedOops";
        goToAddInstallationVariable();
        addInstallation( varNameOptions, varVariableName, varPath, false, false, true );
    }

    public void testAddInstallationVariableWithoutBuildEnvironmentWithInvalidValues()
    {
        String varName = "!@#$<>?etc";
        String varVariableName = "!@#$<>?etc";
        String varPath = "!@#$<>?etc";
>>>>>>> refs/remotes/apache/trunk
        goToAddInstallationVariable();
        addInstallation( varName, varVariableName, varPath, false, false, false );
        assertTextPresent( "Installation name contains invalid characters." );
        assertTextPresent( "Environment variable name contains invalid characters." );
        assertTextPresent( "Installation value contains invalid characters." );
    }

    public void testAddInvalidInstallationTool()
    {
        goToAddInstallationTool();
        addInstallation( "", JDK_VAR_NAME, "", false, true, false );
        assertTextPresent( "You must define a name" );
        assertTextPresent( "You must define a value" );
    }

    public void testAddInvalidPathInstallationTool()
    {
        goToAddInstallationTool();
        addInstallation( "name", JDK_VAR_NAME, "invalid_path", false, true, false );
        assertTextPresent( "Failed to validate installation, check server log" );
    }

    public void testAddInvalidInstallationVariable()
    {
        goToAddInstallationVariable();
        addInstallation( "", "", "", false, false, false );
        assertTextPresent( "You must define a name" );
        assertTextPresent( "You must define a value" );
    }

    public void testAddInvalidVarNameInstallationVariable()
    {
        goToAddInstallationVariable();
        addInstallation( "name", "", "path", false, false, false );
        assertTextPresent( "You must define an environment variable" );
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddJdkToolWithoutBuildEnvironment" } )
    public void testAddDuplicatedInstallationTool()
    {
        String INSTALL_TOOL_JDK_NAME = getProperty( "INSTALL_TOOL_JDK_NAME" );
		String INSTALL_TOOL_JDK_PATH = isWindows() ? getProperty( "INSTALL_TOOL_JDK_PATH" ) :  getEscapeProperty( "INSTALL_TOOL_JDK_PATH" );
		goToAddInstallationTool();
		addInstallation( INSTALL_TOOL_JDK_NAME, "JDK", INSTALL_TOOL_JDK_PATH, false, true, false );
		assertTextPresent( "Installation name already exists" );
		
    }

    @Test( dependsOnMethods = { "testAddInstallationVariableWithBuildEnvironment" } )
    public void testAddDuplicatedInstallationVariable()
    {
        String INSTALL_VAR_NAME = getProperty( "INSTALL_VAR_NAME" );
        String INSTALL_VAR_VARIABLE_NAME = getProperty( "INSTALL_VAR_VARIABLE_NAME" );
        String INSTALL_VAR_PATH = getProperty( "INSTALL_VAR_PATH" );
=======
    @Test( dependsOnMethods = {"testAddJdkToolWithoutBuildEnvironment"} )
    public void testAddDuplicatedInstallationTool()
    {
        goToAddInstallationTool();
        addInstallation( jdkName, JDK_VAR_NAME, jdkPath, false, true, false );
        assertTextPresent( "Installation name already exists" );

    }

    @Test( dependsOnMethods = {"testAddInstallationVariableWithBuildEnvironment"} )
    public void testAddDuplicatedInstallationVariable()
    {
>>>>>>> refs/remotes/apache/trunk
        goToAddInstallationVariable();
        addInstallation( varName, varVariableName, varPath, false, false, false );
        assertTextPresent( "Installation name already exists" );
    }

<<<<<<< HEAD
    @Test( dependsOnMethods = { "testAddJdkToolWithoutBuildEnvironment" } )
    public void testEditInstallationTool()
    {
        String INSTALL_TOOL_JDK_NAME = getProperty( "INSTALL_TOOL_JDK_NAME" );
		String INSTALL_TOOL_JDK_PATH = isWindows() ? getProperty( "INSTALL_TOOL_JDK_PATH" ) :  getEscapeProperty( "INSTALL_TOOL_JDK_PATH" );
		String newName = "new_name";
		goToEditInstallation( INSTALL_TOOL_JDK_NAME, "JDK", INSTALL_TOOL_JDK_PATH, true );
		editInstallation( newName, "JDK", INSTALL_TOOL_JDK_PATH, true, true );
		goToEditInstallation( newName, "JDK", INSTALL_TOOL_JDK_PATH, true );
		editInstallation( INSTALL_TOOL_JDK_NAME, "JDK", INSTALL_TOOL_JDK_PATH, true, true );
    }

    @Test( dependsOnMethods = { "testAddInstallationVariableWithBuildEnvironment" } )
    public void testEditInstallationVariable()
    {
        String INSTALL_VAR_NAME = getProperty( "INSTALL_VAR_NAME" );
        String INSTALL_VAR_VARIABLE_NAME = getProperty( "INSTALL_VAR_VARIABLE_NAME" );
        String INSTALL_VAR_PATH = getProperty( "INSTALL_VAR_PATH" );
=======
    @Test( dependsOnMethods = {"testAddJdkToolWithoutBuildEnvironment"} )
    public void testEditInstallationTool()
    {
        String newName = "new_name";
        goToEditInstallation( jdkName, JDK_VAR_NAME, jdkPath, true );
        editInstallation( newName, JDK_VAR_NAME, jdkPath, true, true );
        goToEditInstallation( newName, JDK_VAR_NAME, jdkPath, true );
        editInstallation( jdkName, JDK_VAR_NAME, jdkPath, true, true );
    }

    @Test( dependsOnMethods = {"testAddInstallationVariableWithBuildEnvironment"} )
    public void testEditInstallationVariable()
    {
>>>>>>> refs/remotes/apache/trunk
        String newName = "new_name";
        String newVarName = "new_var_name";
        String newPath = "new_path";
        goToEditInstallation( varName, varVariableName, varPath, false );
        editInstallation( newName, newVarName, newPath, false, true );
        goToEditInstallation( newName, newVarName, newPath, false );
        editInstallation( varName, varVariableName, varPath, false, true );
    }

    @Test( dependsOnMethods = {"testEditInstallationTool", "testAddDuplicatedInstallationTool"} )
    public void testDeleteInstallationTool()
    {
<<<<<<< HEAD
        String INSTALL_TOOL_JDK_NAME = getProperty( "INSTALL_TOOL_JDK_NAME" );
        removeInstallation( INSTALL_TOOL_JDK_NAME );
=======
        removeInstallation( jdkName, true );
>>>>>>> refs/remotes/apache/trunk
    }

    @Test( dependsOnMethods = {"testEditInstallationVariable", "testAddDuplicatedInstallationVariable"} )
    public void testDeleteInstallationVariable()
    {
<<<<<<< HEAD
        String INSTALL_VAR_NAME = getProperty( "INSTALL_VAR_NAME" );
        removeInstallation( INSTALL_VAR_NAME );
=======
        removeInstallation( varName, true );
>>>>>>> refs/remotes/apache/trunk
    }

	public static boolean isWindows()
	{
		String os = System.getProperty("os.name").toLowerCase();
		//windows
	    return (os.indexOf( "win" ) >= 0); 
	}
}
