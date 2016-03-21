package org.apache.continuum.webdav;

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

import org.apache.continuum.webdav.util.WorkingCopyPathUtil;
import org.apache.jackrabbit.util.Text;
import org.apache.jackrabbit.webdav.DavLocatorFactory;
import org.apache.jackrabbit.webdav.DavResourceLocator;

public class ContinuumBuildAgentDavLocatorFactory
    implements DavLocatorFactory
{
    public DavResourceLocator createResourceLocator( String prefix, String href )
    {
        // build prefix string and remove all prefixes from the given href.
        StringBuilder b = new StringBuilder();
        if ( prefix != null && prefix.length() > 0 )
        {
            b.append( prefix );
            if ( !prefix.endsWith( "/" ) )
            {
                b.append( '/' );
            }
            if ( href.startsWith( prefix ) )
            {
                href = href.substring( prefix.length() );
            }
        }

        // special treatment for root item, that has no name but '/' path.
        if ( href == null || "".equals( href ) )
        {
            href = "/";
        }

        final int projectId = WorkingCopyPathUtil.getProjectId( href );
        return new ContinuumBuildAgentDavResourceLocator( b.toString(), Text.unescape( href ), this, projectId );
    }

    public DavResourceLocator createResourceLocator( String prefix, String workspacePath, String resourcePath )
    {
        return createResourceLocator( prefix, workspacePath, resourcePath, true );
    }

    public DavResourceLocator createResourceLocator( String prefix, String workspacePath, String path,
                                                     boolean isResourcePath )
    {
        final int projectId = WorkingCopyPathUtil.getProjectId( path );
        return new ContinuumBuildAgentDavResourceLocator( prefix, path, this, projectId );
    }

}
