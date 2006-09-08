package org.apache.maven.continuum.web.action;

/*
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.continuum.release.ContinuumReleaseManager;

import java.io.File;

/**
 * @author Edwin Punzalan
 *
 * @plexus.component
 *   role="com.opensymphony.xwork.Action"
 *   role-hint="performRelease"
 */
public class PerformReleaseAction
    extends ContinuumActionSupport
{
    private String releaseId;

    private String scmUrl;

    private String scmUsername;

    private String scmPassword;

    private String scmTag;

    private String scmTagBase;

    private String goals;

    private boolean useReleaseProfile;

    public String execute()
        throws Exception
    {
        return "prompt";
    }

    public String doPerform()
        throws Exception
    {
        ContinuumReleaseManager releaseManager = getContinuum().getReleaseManager();

        releaseManager.perform( releaseId, File.createTempFile( "", "" ), goals, useReleaseProfile );

        return SUCCESS;
    }
}
