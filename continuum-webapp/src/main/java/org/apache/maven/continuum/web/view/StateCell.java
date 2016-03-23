package org.apache.maven.continuum.web.view;

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

import com.opensymphony.xwork2.ActionContext;
import org.apache.continuum.model.project.ProjectScmRoot;
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.apache.maven.continuum.security.ContinuumRoleConstants;
import org.apache.maven.continuum.web.model.ProjectSummary;
import org.apache.maven.continuum.web.util.StateGenerator;
import org.apache.maven.continuum.web.util.UrlHelperFactory;
import org.apache.struts2.ServletActionContext;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.redback.authorization.AuthorizationException;
import org.codehaus.plexus.redback.system.SecuritySession;
import org.codehaus.plexus.redback.system.SecuritySystem;
import org.codehaus.plexus.redback.system.SecuritySystemConstants;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.DisplayCell;
import org.extremecomponents.table.core.TableModel;

import java.util.HashMap;

/**
 * Used in Summary view
 *
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @deprecated use of cells is discouraged due to lack of i18n and design in java code.
 * Use jsp:include instead.
 */
public class StateCell
    extends DisplayCell
{
    protected String getCellValue( TableModel tableModel, Column column )
    {
        String contextPath = tableModel.getContext().getContextPath();

        if ( tableModel.getCurrentRowBean() instanceof ProjectSummary )
        {
            ProjectSummary project = (ProjectSummary) tableModel.getCurrentRowBean();
            String state = StateGenerator.generate( project.getState(), contextPath );

            if ( project.getLatestBuildId() != -1 && project.getState() != ContinuumProjectState.NEW &&
                project.getState() != ContinuumProjectState.UPDATING && isAuthorized( project.getProjectGroupName() ) )
            {
                return createActionLink( "buildResult", project, state );
            }
            return state;
        }

        if ( tableModel.getCurrentRowBean() instanceof ProjectScmRoot )
        {
            ProjectScmRoot projectScmRoot = (ProjectScmRoot) tableModel.getCurrentRowBean();
            String state = StateGenerator.generate( projectScmRoot.getState(), contextPath );
            if ( projectScmRoot.getState() != ContinuumProjectState.NEW
                && isAuthorized( projectScmRoot.getProjectGroup().getName() )
                && projectScmRoot.getState() == ContinuumProjectState.ERROR )
            {
                return createActionLink( "scmResult", projectScmRoot, state );
            }
            return state;
        }

        return StateGenerator.generate( StateGenerator.UNKNOWN_STATE, contextPath );
    }

    private static String createActionLink( String action, ProjectSummary project, String state )
    {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put( "projectId", project.getId() );

        params.put( "projectName", project.getName() );

        params.put( "buildId", project.getLatestBuildId() );

        params.put( "projectGroupId", project.getProjectGroupId() );

        String url =
            UrlHelperFactory.getInstance().buildUrl( "/" + action + ".action", ServletActionContext.getRequest(),
                                                     ServletActionContext.getResponse(), params );

        return "<a href=\"" + url + "\">" + state + "</a>";
    }

    private static String createActionLink( String action, ProjectScmRoot scmRoot, String state )
    {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put( "projectGroupId", scmRoot.getProjectGroup().getId() );

        params.put( "projectScmRootId", scmRoot.getId() );

        String url =
            UrlHelperFactory.getInstance().buildUrl( "/" + action + ".action", ServletActionContext.getRequest(),
                                                     ServletActionContext.getResponse(), params );

        return "<a href=\"" + url + "\">" + state + "</a>";
    }

    private boolean isAuthorized( String projectGroupName )
    {
        // do the authz bit
        ActionContext context = ActionContext.getContext();

        PlexusContainer container = (PlexusContainer) context.getApplication().get( PlexusConstants.PLEXUS_KEY );
        SecuritySession securitySession = (SecuritySession) context.getSession().get(
            SecuritySystemConstants.SECURITY_SESSION_KEY );

        try
        {
            SecuritySystem securitySystem = (SecuritySystem) container.lookup( SecuritySystem.ROLE );

            if ( !securitySystem.isAuthorized( securitySession, ContinuumRoleConstants.CONTINUUM_VIEW_GROUP_OPERATION,
                                               projectGroupName ) )
            {
                return false;
            }
        }
        catch ( ComponentLookupException cle )
        {
            return false;
        }
        catch ( AuthorizationException ae )
        {
            return false;
        }

        return true;
    }
}
