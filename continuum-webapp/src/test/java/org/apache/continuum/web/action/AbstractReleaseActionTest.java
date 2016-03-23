<<<<<<< HEAD
package org.apache.maven.continuum.web.action;
=======
package org.apache.continuum.web.action;
>>>>>>> refs/remotes/apache/trunk

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

=======
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.configuration.BuildAgentConfiguration;
import org.apache.continuum.configuration.BuildAgentGroupConfiguration;
import org.apache.continuum.release.distributed.DistributedReleaseUtil;
import org.apache.continuum.web.action.stub.ReleaseActionStub;
import org.apache.maven.continuum.Continuum;
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.apache.maven.continuum.model.system.Profile;
<<<<<<< HEAD
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

public class AbstractReleaseActionTest
    extends MockObjectTestCase
{
    private ReleaseActionStub action;

    private Mock continuumMock;

    private Mock configurationServiceMock;
    
    private String defaultBuildagentUrl = "http://localhost:8181/continuum-buildagent/xmlrpc";

    protected void setUp()
        throws Exception
    {
        continuumMock = mock( Continuum.class );
        configurationServiceMock = mock( ConfigurationService.class );

        Profile profile = new Profile();
        profile.setBuildAgentGroup( "BUILDAGENT_GROUP" );
        
        action = new ReleaseActionStub();
        action.setProfile( profile );
        action.setDefaultBuildagent( defaultBuildagentUrl );
        action.setContinuum( (Continuum) continuumMock.proxy() );
    }
    
=======
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AbstractReleaseActionTest
    extends AbstractActionTest
{
    private ReleaseActionStub action;

    private Continuum continuum;

    private ConfigurationService configurationService;

    private String defaultBuildagentUrl = "http://localhost:8181/continuum-buildagent/xmlrpc";

    @Before
    public void setUp()
        throws Exception
    {
        continuum = mock( Continuum.class );
        configurationService = mock( ConfigurationService.class );

        Profile profile = new Profile();
        profile.setBuildAgentGroup( "BUILDAGENT_GROUP" );

        action = new ReleaseActionStub();
        action.setProfile( profile );
        action.setDefaultBuildagent( defaultBuildagentUrl );
        action.setContinuum( continuum );
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testGetEnvironmentsDefaultAgentInGroup()
        throws Exception
    {
        BuildAgentGroupConfiguration buildAgentGroup = createBuildAgentGroupConfiguration( true );
<<<<<<< HEAD
        buildAgentGroup.addBuildAgent( new BuildAgentConfiguration( defaultBuildagentUrl, "Default Build Agent", true ) );
        
        continuumMock.expects( atLeastOnce() ).method( "getConfiguration" ).will( returnValue( configurationServiceMock.proxy() ) );
        configurationServiceMock.expects( atLeastOnce() ).method( "getBuildAgentGroup" ).will( returnValue( buildAgentGroup ) );
        
        action.getEnvironments();
        Map<String, String> envVars = action.getEnvironmentVariables();
        String buildagent = envVars.get( DistributedReleaseUtil.KEY_BUILD_AGENT_URL );
        
        assertNotNull( envVars );
        assertTrue( "Default build agent is expected to be used.", defaultBuildagentUrl.equals( buildagent ) );
    }
    
=======
        buildAgentGroup.addBuildAgent( new BuildAgentConfiguration( defaultBuildagentUrl, "Default Build Agent",
                                                                    true ) );

        when( continuum.getConfiguration() ).thenReturn( configurationService );
        when( configurationService.getBuildAgentGroup( anyString() ) ).thenReturn( buildAgentGroup );

        action.getEnvironments();
        Map<String, String> envVars = action.getEnvironmentVariables();
        String buildagent = envVars.get( DistributedReleaseUtil.KEY_BUILD_AGENT_URL );

        assertNotNull( envVars );
        assertTrue( "Default build agent is expected to be used.", defaultBuildagentUrl.equals( buildagent ) );
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testGetEnvironmentsDefaultAgentNotInGroup()
        throws Exception
    {
        BuildAgentGroupConfiguration buildAgentGroup = createBuildAgentGroupConfiguration( true );
<<<<<<< HEAD
        
        continuumMock.expects( atLeastOnce() ).method( "getConfiguration" ).will( returnValue( configurationServiceMock.proxy() ) );
        configurationServiceMock.expects( atLeastOnce() ).method( "getBuildAgentGroup" ).will( returnValue( buildAgentGroup ) );
        
        action.getEnvironments();
        Map<String, String> envVars = action.getEnvironmentVariables();
        String buildagent = envVars.get( DistributedReleaseUtil.KEY_BUILD_AGENT_URL );
        
        assertNotNull( envVars );
        assertFalse( "Default build agent is not expected to be used.", defaultBuildagentUrl.equals( buildagent ) );
    }
    
=======

        when( continuum.getConfiguration() ).thenReturn( configurationService );
        when( configurationService.getBuildAgentGroup( anyString() ) ).thenReturn( buildAgentGroup );

        action.getEnvironments();
        Map<String, String> envVars = action.getEnvironmentVariables();
        String buildagent = envVars.get( DistributedReleaseUtil.KEY_BUILD_AGENT_URL );

        assertNotNull( envVars );
        assertFalse( "Default build agent is not expected to be used.", defaultBuildagentUrl.equals( buildagent ) );
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testGetEnvironmentsNoEnabledAgentInGroup()
        throws Exception
    {
        BuildAgentGroupConfiguration buildAgentGroup = createBuildAgentGroupConfiguration( false );
<<<<<<< HEAD
        buildAgentGroup.addBuildAgent( new BuildAgentConfiguration( defaultBuildagentUrl, "Default Build Agent", false ) );
        
        continuumMock.expects( atLeastOnce() ).method( "getConfiguration" ).will( returnValue( configurationServiceMock.proxy() ) );
        configurationServiceMock.expects( atLeastOnce() ).method( "getBuildAgentGroup" ).will( returnValue( buildAgentGroup ) );
        
        action.getEnvironments();
        Map<String, String> envVars = action.getEnvironmentVariables();
        String buildagent = envVars.get( DistributedReleaseUtil.KEY_BUILD_AGENT_URL );
        
=======
        buildAgentGroup.addBuildAgent( new BuildAgentConfiguration( defaultBuildagentUrl, "Default Build Agent",
                                                                    false ) );

        when( continuum.getConfiguration() ).thenReturn( configurationService );
        when( configurationService.getBuildAgentGroup( anyString() ) ).thenReturn( buildAgentGroup );

        action.getEnvironments();
        Map<String, String> envVars = action.getEnvironmentVariables();
        String buildagent = envVars.get( DistributedReleaseUtil.KEY_BUILD_AGENT_URL );

>>>>>>> refs/remotes/apache/trunk
        assertNotNull( envVars );
        assertFalse( "Default build agent is not expected to be used.", defaultBuildagentUrl.equals( buildagent ) );
        assertNull( "Build agent should be empty.", buildagent );
    }
<<<<<<< HEAD
    
=======

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testGetEnvironmentsNoAgentInGroup()
        throws Exception
    {
        BuildAgentGroupConfiguration buildAgentGroup = new BuildAgentGroupConfiguration();
<<<<<<< HEAD
        
        continuumMock.expects( atLeastOnce() ).method( "getConfiguration" ).will( returnValue( configurationServiceMock.proxy() ) );
        configurationServiceMock.expects( atLeastOnce() ).method( "getBuildAgentGroup" ).will( returnValue( buildAgentGroup ) );
        
        action.getEnvironments();
        Map<String, String> envVars = action.getEnvironmentVariables();
        String buildagent = envVars.get( DistributedReleaseUtil.KEY_BUILD_AGENT_URL );
        
=======

        when( continuum.getConfiguration() ).thenReturn( configurationService );
        when( configurationService.getBuildAgentGroup( anyString() ) ).thenReturn( buildAgentGroup );

        action.getEnvironments();
        Map<String, String> envVars = action.getEnvironmentVariables();
        String buildagent = envVars.get( DistributedReleaseUtil.KEY_BUILD_AGENT_URL );

>>>>>>> refs/remotes/apache/trunk
        assertNotNull( envVars );
        assertFalse( "Default build agent is not expected to be used.", defaultBuildagentUrl.equals( buildagent ) );
        assertNull( "Build agent should be empty.", buildagent );
    }
<<<<<<< HEAD
    
    private BuildAgentGroupConfiguration createBuildAgentGroupConfiguration( boolean isAgentEnabled )
    {
        BuildAgentConfiguration buildagent1 = new BuildAgentConfiguration( "http://localhost:9191/continuum-buildagent/xmlrpc",
                                                                               "Other Build Agent", isAgentEnabled );
        BuildAgentConfiguration buildagent2 = new BuildAgentConfiguration( "http://localhost:9292/continuum-buildagent/xmlrpc",
                                                                               "Other Build Agent", isAgentEnabled );
        
        List<BuildAgentConfiguration> buildAgents = new ArrayList<BuildAgentConfiguration>();
        buildAgents.add( buildagent1 );
        buildAgents.add( buildagent2 );
        
        BuildAgentGroupConfiguration buildAgentGroup = new BuildAgentGroupConfiguration( "BUILDAGENT_GROUP", buildAgents );
        
=======

    private BuildAgentGroupConfiguration createBuildAgentGroupConfiguration( boolean isAgentEnabled )
    {
        BuildAgentConfiguration buildagent1 = new BuildAgentConfiguration(
            "http://localhost:9191/continuum-buildagent/xmlrpc", "Other Build Agent", isAgentEnabled );
        BuildAgentConfiguration buildagent2 = new BuildAgentConfiguration(
            "http://localhost:9292/continuum-buildagent/xmlrpc", "Other Build Agent", isAgentEnabled );

        List<BuildAgentConfiguration> buildAgents = new ArrayList<BuildAgentConfiguration>();
        buildAgents.add( buildagent1 );
        buildAgents.add( buildagent2 );

        BuildAgentGroupConfiguration buildAgentGroup = new BuildAgentGroupConfiguration( "BUILDAGENT_GROUP",
                                                                                         buildAgents );

>>>>>>> refs/remotes/apache/trunk
        return buildAgentGroup;
    }
}
