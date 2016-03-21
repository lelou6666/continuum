package org.apache.continuum.web.action.error;

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

import org.apache.maven.continuum.web.action.ContinuumActionSupport;
import org.codehaus.plexus.component.annotations.Component;

/**
 * AboutAction:
 *
 * @author: Emmanuel Venisse <evenisse@apache.org>
 */
@Component( role = com.opensymphony.xwork2.Action.class, hint = "httpError", instantiationStrategy = "per-lookup"  )
public class HttpError
    extends ContinuumActionSupport
{
<<<<<<< HEAD:continuum-core/src/main/java/org/apache/maven/continuum/scm/queue/CheckOutTask.java
    private long projectId;

    private File workingDirectory;

    public CheckOutTask( long projectId, File workingDirectory )
    {
        this.projectId = projectId;

        this.workingDirectory = workingDirectory;
    }

    public long getProjectId()
=======
    private int errorCode;

    public String execute()
        throws Exception
>>>>>>> refs/remotes/apache/trunk:continuum-webapp/src/main/java/org/apache/continuum/web/action/error/HttpError.java
    {
        return SUCCESS;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode( int errorCode )
    {
        this.errorCode = errorCode;
    }
}
