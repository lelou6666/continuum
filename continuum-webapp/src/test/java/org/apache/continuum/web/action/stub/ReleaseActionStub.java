package org.apache.continuum.web.action.stub;

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
import java.util.HashMap;
import java.util.Map;

import org.apache.continuum.web.action.AbstractReleaseAction;
import org.apache.maven.continuum.model.system.Profile;

=======
import org.apache.continuum.web.action.AbstractReleaseAction;
import org.apache.maven.continuum.model.system.Profile;

import java.util.HashMap;
import java.util.Map;

>>>>>>> refs/remotes/apache/trunk
public class ReleaseActionStub
    extends AbstractReleaseAction
{
    private Map<String, String> environmentVariables;
<<<<<<< HEAD
    
    private Profile profile;
    
    private String defaultBuildagent;
    
=======

    private Profile profile;

    private String defaultBuildagent;

>>>>>>> refs/remotes/apache/trunk
    public ReleaseActionStub()
    {
        this.environmentVariables = new HashMap<String, String>();
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void getEnvironments()
    {
        this.environmentVariables = getEnvironments( profile, defaultBuildagent );
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public Map<String, String> getEnvironmentVariables()
    {
        return this.environmentVariables;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setProfile( Profile profile )
    {
        this.profile = profile;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDefaultBuildagent( String defaultBuildagent )
    {
        this.defaultBuildagent = defaultBuildagent;
    }
}
