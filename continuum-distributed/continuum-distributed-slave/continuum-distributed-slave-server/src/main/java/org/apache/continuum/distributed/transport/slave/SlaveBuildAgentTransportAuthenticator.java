package org.apache.continuum.distributed.transport.slave;

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

import org.apache.continuum.buildagent.configuration.BuildAgentConfigurationService;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.common.XmlRpcHttpRequestConfigImpl;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping.AuthenticationHandler;
import org.codehaus.plexus.util.StringUtils;

public class SlaveBuildAgentTransportAuthenticator
    implements AuthenticationHandler
{
    private BuildAgentConfigurationService buildAgentConfigurationService;

    public SlaveBuildAgentTransportAuthenticator( BuildAgentConfigurationService buildAgentConfigurationService )
    {
        this.buildAgentConfigurationService = buildAgentConfigurationService;
    }

    public boolean isAuthorized( XmlRpcRequest pRequest )
        throws XmlRpcException
    {
        if ( pRequest.getConfig() instanceof XmlRpcHttpRequestConfigImpl )
        {
            XmlRpcHttpRequestConfigImpl config = (XmlRpcHttpRequestConfigImpl) pRequest.getConfig();

            if ( config.getBasicPassword() == null || StringUtils.isBlank( config.getBasicPassword() ) )
            {
                throw new XmlRpcException( "Shared Secret Password is not present in the server request" );
            }

            if ( buildAgentConfigurationService.getSharedSecretPassword() == null || StringUtils.isBlank(
                buildAgentConfigurationService.getSharedSecretPassword() ) )
            {
                throw new XmlRpcException( "Shared Secret Password is not configured properly on the build agent" );
            }

            return buildAgentConfigurationService.getSharedSecretPassword().equals( config.getBasicPassword() );
        }

        throw new XmlRpcException( "Unsupported transport (must be http)" );
    }


}
