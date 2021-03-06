package org.apache.maven.continuum.web.interceptor;

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

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.maven.continuum.Continuum;
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

/**
 * ForceContinuumConfigurationInterceptor:
 *
 * @author: Jesse McConnell <jmcconnell@apache.org>
 */
@Component( role = com.opensymphony.xwork2.interceptor.Interceptor.class, hint = "forceContinuumConfigurationInterceptor" )
public class ForceContinuumConfigurationInterceptor
    implements Interceptor
{
    private static boolean checked = false;

    @Requirement
    private Continuum continuum;

    public void destroy()
    {
        // no-op
    }

    public void init()
    {

    }

    /**
     * 1) check to see if this interceptor has been successfully executed
     * 2) check if the configuration service is initialized
     * 3) load the configuration and see if that is initialized (addresses restore on empty db)
     * 4) force the configuration screen
     *
     * @param invocation
     * @return
     * @throws Exception
     */
    public String intercept( ActionInvocation invocation )
        throws Exception
    {
        if ( checked )
        {
            return invocation.invoke();
        }

        ConfigurationService configuration = continuum.getConfiguration();

        if ( configuration.isInitialized() )
        {
            checked = true;
            return invocation.invoke();
        }

        configuration.reload();

        if ( configuration.isInitialized() )
        {
            checked = true;
            return invocation.invoke();
        }
        else
        {
            return "continuum-configuration-required";
        }

    }
}
