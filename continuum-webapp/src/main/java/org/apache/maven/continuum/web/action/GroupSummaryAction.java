package org.apache.maven.continuum.web.action;

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

import org.apache.continuum.model.project.ProjectGroupSummary;
import org.apache.maven.continuum.ContinuumException;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.web.exception.AuthorizationRequiredException;
import org.apache.maven.continuum.web.model.GroupSummary;
import org.codehaus.plexus.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 */
@Component( role = com.opensymphony.xwork2.Action.class, hint = "groupSummary", instantiationStrategy = "per-lookup"  )
public class GroupSummaryAction
    extends ContinuumActionSupport
{
    private static final Logger logger = LoggerFactory.getLogger( GroupSummaryAction.class );

    private String infoMessage;

    private List<GroupSummary> groups;

    public String browse()
        throws ContinuumException
    {
        groups = new ArrayList<GroupSummary>();

        //TODO: Merge this two requests to one
        Collection<ProjectGroup> projectGroups = getContinuum().getAllProjectGroups();
        Map<Integer, ProjectGroupSummary> summaries = getContinuum().getProjectsSummaryByGroups();

        for ( ProjectGroup projectGroup : projectGroups )
        {

            if ( isAuthorized( projectGroup.getName() ) )
            {
                if ( logger.isDebugEnabled() )
                {
                    logger.debug( "GroupSummaryAction: building group " + projectGroup.getName() );
                }

                GroupSummary groupModel = new GroupSummary();
                groupModel.setId( projectGroup.getId() );
                groupModel.setGroupId( projectGroup.getGroupId() );
                groupModel.setName( projectGroup.getName() );
                groupModel.setDescription( projectGroup.getDescription() );

                ProjectGroupSummary summary = summaries.get( projectGroup.getId() );

                if ( summary != null )
                {
<<<<<<< HEAD
                    Project project = (Project) i.next();

                    if ( groupModel.getProjectType() == null )
                    {
                        groupModel.setProjectType( project.getExecutorId() );
                    }

                    ProjectSummary model = new ProjectSummary();

                    getLogger().debug( "GroupSummaryAction: building project model " + project.getName() );

                    model.setId( project.getId() );

                    model.setName( project.getName() );

                    model.setVersion( project.getVersion() );

                    model.setProjectGroupId( project.getProjectGroup().getId() );

                    model.setProjectGroupName( project.getProjectGroup().getName() );

                    if ( getContinuum().isInBuildingQueue( project.getId() ) )
                    {
                        model.setInBuildingQueue( true );
                    }
                    else if ( getContinuum().isInCheckoutQueue( project.getId() ) )
                    {
                        model.setInCheckoutQueue( true );
                    }
                    else
                    {
                        model.setInBuildingQueue( false );
                        model.setInCheckoutQueue( false );
                    }

                    model.setState( project.getState() );

                    if ( project.getState() == 2 )
                    {
                        numSuccesses++;
                    }
                    else if ( project.getState() == 3 )
                    {
                        numFailures++;
                    }
                    else if ( project.getState() == 4 )
                    {
                        numErrors++;
                    }

                    model.setBuildNumber( project.getBuildNumber() );

                    if ( buildResultsInSuccess != null )
                    {
                        BuildResult buildInSuccess =
                            (BuildResult) buildResultsInSuccess.get( new Long( project.getId() ) );

                        if ( buildInSuccess != null )
                        {
                            model.setBuildInSuccessId( buildInSuccess.getId() );
                        }
                    }

                    if ( buildResults != null )
                    {
                        BuildResult latestBuild = (BuildResult) buildResults.get( new Long( project.getId() ) );

                        if ( latestBuild != null )
                        {
                            model.setLatestBuildId( latestBuild.getId() );
                        }
                    }
                    getLogger().debug( "GroupSummaryAction: adding model to group " + model.getName() );
                    projectModels.add( model );
=======
                    groupModel.setNumProjects( summary.getNumberOfProjects() );
                    groupModel.setNumErrors( summary.getNumberOfErrors() );
                    groupModel.setNumFailures( summary.getNumberOfFailures() );
                    groupModel.setNumSuccesses( summary.getNumberOfSuccesses() );
>>>>>>> refs/remotes/apache/trunk
                }

                //todo wire in the next scheduled build for the project group and a meaningful status message
                //groupModel.setNextScheduledBuild( "unknown" );
                //groupModel.setStatusMessage( "none" );
                if ( logger.isDebugEnabled() )
                {
                    logger.debug( "GroupSummaryAction: adding group to groups list " + groupModel.getName() );
                }
                groups.add( groupModel );
            }
        }

        return SUCCESS;
    }

    public List<GroupSummary> getGroups()
    {
        return groups;
    }

    public String getInfoMessage()
    {
        return infoMessage;
    }

    public void setInfoMessage( String infoMessage )
    {
        this.infoMessage = infoMessage;
    }

    private boolean isAuthorized( String projectGroupName )
    {
        try
        {
            checkViewProjectGroupAuthorization( projectGroupName );
            return true;
        }
        catch ( AuthorizationRequiredException authzE )
        {
            return false;
        }
    }
}
