package org.apache.continuum.distributed.transport.master;

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

import com.atlassian.xmlrpc.Binder;
import com.atlassian.xmlrpc.BindingException;
import com.atlassian.xmlrpc.ConnectionInfo;
import org.apache.continuum.distributed.commons.utils.ContinuumDistributedUtil;
import org.apache.continuum.distributed.commons.utils.ContinuumXmlRpcBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Map;
import java.util.TimeZone;

/**
 * MasterBuildAgentTransportClient
 */
public class MasterBuildAgentTransportClient
    implements MasterBuildAgentTransportService
{
    private static final Logger log = LoggerFactory.getLogger( MasterBuildAgentTransportClient.class );

    MasterBuildAgentTransportService master;

    private String masterServerUrl;

    public MasterBuildAgentTransportClient( URL serviceUrl )
        throws Exception
    {
        this( serviceUrl, null, null );
    }

    public MasterBuildAgentTransportClient( URL serviceUrl, String login, String password )
        throws Exception
    {
        Binder binder = ContinuumXmlRpcBinder.getInstance();

        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setUsername( login );
        connectionInfo.setPassword( password );
        connectionInfo.setTimeZone( TimeZone.getDefault() );

        this.masterServerUrl = serviceUrl.toString();

        try
        {
            master = binder.bind( MasterBuildAgentTransportService.class, serviceUrl, connectionInfo );
        }
        catch ( BindingException e )
        {
            log.error( "Can't bind service interface " + MasterBuildAgentTransportService.class.getName() + " to " +
                           serviceUrl.toExternalForm() + " using " + connectionInfo.getUsername() + ", " +
                           connectionInfo.getPassword(), e );
            throw new Exception(
                "Can't bind service interface " + MasterBuildAgentTransportService.class.getName() + " to " +
                    serviceUrl.toExternalForm() + " using " + connectionInfo.getUsername() + ", " +
                    connectionInfo.getPassword(), e );
        }
    }

    public Boolean returnBuildResult( Map<String, Object> buildResult, String buildAgentUrl )
        throws Exception
    {
        Boolean result;
        String projectInfo = ContinuumDistributedUtil.getProjectNameAndId( buildResult );

        try
        {
            result = master.returnBuildResult( buildResult, buildAgentUrl );
            log.info( "Build finished. Returning the build result for project {} to master {}", projectInfo,
                      masterServerUrl );
        }
        catch ( Exception e )
        {
            log.error(
                "Failed to finish the build and return the build result for project " + projectInfo + " to master " +
                    masterServerUrl, e );
            throw new Exception(
                "Failed to finish the build and return the build result for project " + projectInfo + " to master " +
                    masterServerUrl, e );
        }

        return result;
    }

    public Boolean ping()
        throws Exception
    {
        Boolean result;

        try
        {
            result = master.ping();
            log.debug( "Ping Master {} : {}", masterServerUrl, ( result ? "ok" : "failed" ) );
        }
        catch ( Exception e )
        {
            log.error( "Ping Master " + masterServerUrl + " error", e );
            throw new Exception( "Ping Master " + masterServerUrl + " error", e );
        }

        return result;
    }

    public Boolean prepareBuildFinished( Map<String, Object> prepareBuildResult, String buildAgentUrl )
        throws Exception
    {
        Boolean result;
        String projectInfo = ContinuumDistributedUtil.getProjectNameAndId( prepareBuildResult );

        try
        {
            result = master.prepareBuildFinished( prepareBuildResult, buildAgentUrl );
            log.info( "Prepare build finished for project '{}'", projectInfo );
        }
        catch ( Exception e )
        {
            log.error( "Failed to finish prepare build for project {}", projectInfo );
            throw new Exception( "Failed to finish prepare build for project " + projectInfo + ".", e );
        }

        return result;
    }

    public Boolean startProjectBuild( Integer projectId, Integer buildDefinitionId, String buildAgentUrl )
        throws Exception
    {
        Boolean result;

        try
        {
            result = master.startProjectBuild( projectId, buildDefinitionId, buildAgentUrl );
            log.info( "Start project {}, buildDef {} build", projectId, buildDefinitionId );
        }
        catch ( Exception e )
        {
            log.error( "Failed to start build of projectId=" + projectId + " to master " + masterServerUrl, e );
            throw new Exception( "Failed to start build of projectId=" + projectId + " to master " + masterServerUrl,
                                 e );
        }

        return result;
    }

    public Boolean startPrepareBuild( Map<String, Object> prepareBuildResult, String buildAgentUrl )
        throws Exception
    {
        Boolean result;
        String projectInfo = ContinuumDistributedUtil.getProjectNameAndId( prepareBuildResult );

        try
        {
            result = master.startPrepareBuild( prepareBuildResult, buildAgentUrl );
            log.info( "Start prepare build for project {}", projectInfo );
        }
        catch ( Exception e )
        {
            log.error( "Failed to start prepare build for project {}", projectInfo, e );
            throw new Exception( "Failed to start prepare build for project " + projectInfo, e );
        }

        return result;
    }

    public Map<String, String> getEnvironments( Integer buildDefinitionId, String installationType )
        throws Exception
    {
        Map<String, String> result;
        try
        {
            result = master.getEnvironments( buildDefinitionId, installationType );
            log.debug( "Retrieved environments. buildDefinitionId={}, installationType={} from master {}",
                       new Object[]{buildDefinitionId, installationType, masterServerUrl} );
        }
        catch ( Exception e )
        {
            log.error( "Failed to retrieve environments. buildDefinitionId=" + buildDefinitionId +
                           ", installationType=" + installationType + " from master " + masterServerUrl, e );
            throw new Exception( "Failed to retrieve environments. buildDefinitionId=" +
                                     buildDefinitionId + ", installationType=" + installationType + " from master " +
                                     masterServerUrl, e );
        }

        return result;
    }

    public Boolean updateProject( Map<String, Object> project )
        throws Exception
    {
        Boolean result;
        String projectInfo = ContinuumDistributedUtil.getProjectNameAndId( project );

        try
        {
            result = master.updateProject( project );
            log.debug( "Updating project {} in master {}", projectInfo, masterServerUrl );
        }
        catch ( Exception e )
        {
            log.error( "Failed to update project " + projectInfo + " in master " + masterServerUrl, e );
            throw new Exception( "Failed to update project " + projectInfo + " in master " + masterServerUrl, e );
        }

        return result;
    }

    public Boolean shouldBuild( Map<String, Object> context, String buildAgentUrl )
        throws Exception
    {
        Boolean result;
        String projectInfo = ContinuumDistributedUtil.getProjectNameAndId( context );

        try
        {
            result = master.shouldBuild( context, buildAgentUrl );
            log.debug( "Checking if project {} should build from master {}", projectInfo, masterServerUrl );
        }
        catch ( Exception e )
        {
            log.error( "Failed to determine if project " + projectInfo + " should build from master " + masterServerUrl,
                       e );
            throw new Exception(
                "Failed to determine if project " + projectInfo + " should build from master " + masterServerUrl, e );
        }

        return result;
    }
}
