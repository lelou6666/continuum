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
<<<<<<< HEAD
=======
import org.codehaus.plexus.component.annotations.Component;
>>>>>>> refs/remotes/apache/trunk

/**
 * AboutAction:
 *
 * @author: Emmanuel Venisse <evenisse@apache.org>
<<<<<<< HEAD
 * @version: $Id:$
 * @plexus.component role="com.opensymphony.xwork2.Action" role-hint="httpError"
 */
=======
 */
@Component( role = com.opensymphony.xwork2.Action.class, hint = "httpError", instantiationStrategy = "per-lookup"  )
>>>>>>> refs/remotes/apache/trunk
public class HttpError
    extends ContinuumActionSupport
{
    private int errorCode;

    public String execute()
        throws Exception
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
