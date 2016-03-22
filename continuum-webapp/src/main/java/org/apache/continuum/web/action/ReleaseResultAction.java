package org.apache.continuum.web.action;

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

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.continuum.model.release.ContinuumReleaseResult;
<<<<<<< HEAD
=======
import org.apache.continuum.utils.file.FileSystemManager;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.ContinuumException;
import org.apache.maven.continuum.configuration.ConfigurationException;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.web.action.ContinuumConfirmAction;
import org.apache.maven.continuum.web.exception.AuthorizationRequiredException;
import org.apache.maven.shared.release.ReleaseResult;
<<<<<<< HEAD
import org.codehaus.plexus.util.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
=======
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
>>>>>>> refs/remotes/apache/trunk
import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
<<<<<<< HEAD
 * @plexus.component role="com.opensymphony.xwork.Action" role-hint="releaseResult"
 */
public class ReleaseResultAction
    extends ContinuumConfirmAction
{
    private int projectGroupId;
    
    private int releaseResultId;
    
    private List<ContinuumReleaseResult> releaseResults;
    
    private List<String> selectedReleaseResults;
    
    private ProjectGroup projectGroup;
    
    private ReleaseResult result;
    
=======
 */
@Component( role = com.opensymphony.xwork2.Action.class, hint = "releaseResult", instantiationStrategy = "per-lookup" )
public class ReleaseResultAction
    extends ContinuumConfirmAction
{
    private static final Logger logger = LoggerFactory.getLogger( ReleaseResultAction.class );

    @Requirement
    private FileSystemManager fsManager;

    private int projectGroupId;

    private int releaseResultId;

    private List<ContinuumReleaseResult> releaseResults;

    private List<String> selectedReleaseResults;

    private ProjectGroup projectGroup;

    private ReleaseResult result;

>>>>>>> refs/remotes/apache/trunk
    private boolean confirmed;

    private String projectName;

    private String releaseGoal;

<<<<<<< HEAD
=======
    private String username;

>>>>>>> refs/remotes/apache/trunk
    public String list()
        throws ContinuumException
    {
        try
        {
            checkViewProjectGroupAuthorization( getProjectGroupName() );
        }
        catch ( AuthorizationRequiredException authzE )
        {
            addActionError( authzE.getMessage() );
            return REQUIRES_AUTHORIZATION;
        }
<<<<<<< HEAD
        
        releaseResults = getContinuum().getContinuumReleaseResultsByProjectGroup( projectGroupId );
        
        return SUCCESS;

    }
    
=======

        releaseResults = getContinuum().getContinuumReleaseResultsByProjectGroup( projectGroupId );

        return SUCCESS;

    }

>>>>>>> refs/remotes/apache/trunk
    public String remove()
        throws ContinuumException
    {
        try
        {
            checkModifyProjectGroupAuthorization( getProjectGroupName() );
        }
        catch ( AuthorizationRequiredException e )
        {
            return REQUIRES_AUTHORIZATION;
        }
<<<<<<< HEAD
     
=======

>>>>>>> refs/remotes/apache/trunk
        if ( confirmed )
        {
            if ( selectedReleaseResults != null && !selectedReleaseResults.isEmpty() )
            {
                for ( String id : selectedReleaseResults )
                {
                    int resultId = Integer.parseInt( id );

                    try
                    {
<<<<<<< HEAD
                        getLogger().info( "Removing ContinuumReleaseResult with id=" + resultId );
=======
                        logger.info( "Removing ContinuumReleaseResult with id=" + resultId );
>>>>>>> refs/remotes/apache/trunk

                        getContinuum().removeContinuumReleaseResult( resultId );
                    }
                    catch ( ContinuumException e )
                    {
<<<<<<< HEAD
                        getLogger().error( "Error removing ContinuumReleaseResult with id=" + resultId );
                        addActionError( "Unable to remove ContinuumReleaseResult with id=" + resultId );
=======
                        logger.error( "Error removing ContinuumReleaseResult with id=" + resultId );
                        addActionError( getText( "Unable to remove ContinuumReleaseResult with id=" + resultId ) );
>>>>>>> refs/remotes/apache/trunk
                    }
                }
            }
            return SUCCESS;
        }
<<<<<<< HEAD
        
        return CONFIRM;
    }
    
=======

        return CONFIRM;
    }

>>>>>>> refs/remotes/apache/trunk
    public String viewResult()
        throws ContinuumException
    {
        try
        {
            checkViewProjectGroupAuthorization( getProjectGroupName() );
        }
        catch ( AuthorizationRequiredException authzE )
        {
            addActionError( authzE.getMessage() );
            return REQUIRES_AUTHORIZATION;
        }
<<<<<<< HEAD
        
        ContinuumReleaseResult releaseResult = getContinuum().getContinuumReleaseResult( releaseResultId );
        
=======

        ContinuumReleaseResult releaseResult = getContinuum().getContinuumReleaseResult( releaseResultId );

>>>>>>> refs/remotes/apache/trunk
        result = new ReleaseResult();
        result.setStartTime( releaseResult.getStartTime() );
        result.setEndTime( releaseResult.getEndTime() );
        result.setResultCode( releaseResult.getResultCode() );

        releaseGoal = releaseResult.getReleaseGoal();
        projectName = releaseResult.getProject().getName();
<<<<<<< HEAD

        try
        {
            File releaseOutputFile = getContinuum().getConfiguration().getReleaseOutputFile( projectGroupId, "releases-" + releaseResult.getStartTime() );

            if ( releaseOutputFile.exists() )
            {
                String str = StringEscapeUtils.escapeHtml( FileUtils.fileRead( releaseOutputFile ) );
=======
        username = releaseResult.getUsername();

        try
        {
            File releaseOutputFile = getContinuum().getConfiguration().getReleaseOutputFile( projectGroupId,
                                                                                             "releases-" +
                                                                                                 releaseResult.getStartTime() );

            if ( releaseOutputFile.exists() )
            {
                String str = StringEscapeUtils.escapeHtml( fsManager.fileContents( releaseOutputFile ) );
>>>>>>> refs/remotes/apache/trunk
                result.appendOutput( str );
            }
        }
        catch ( ConfigurationException e )
        {
            //getLogger().error( "" );
        }
        catch ( IOException e )
        {
            //getLogger().error( "" );
        }
<<<<<<< HEAD
        
        return SUCCESS;
    }
    
    public String getProjectGroupName()
        throws ContinuumException
    {
    
        return getProjectGroup( projectGroupId ).getName();
    }
    
=======

        return SUCCESS;
    }

    public String getProjectGroupName()
        throws ContinuumException
    {

        return getProjectGroup( projectGroupId ).getName();
    }

>>>>>>> refs/remotes/apache/trunk
    public ProjectGroup getProjectGroup( int projectGroupId )
        throws ContinuumException
    {
        if ( projectGroup == null )
        {
            projectGroup = getContinuum().getProjectGroup( projectGroupId );
        }
        else
        {
            if ( projectGroup.getId() != projectGroupId )
            {
                projectGroup = getContinuum().getProjectGroup( projectGroupId );
            }
        }
<<<<<<< HEAD
    
        return projectGroup;
    }
    
=======

        return projectGroup;
    }

>>>>>>> refs/remotes/apache/trunk
    public ProjectGroup getProjectGroup()
    {
        return projectGroup;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setProjectGroup( ProjectGroup projectGroup )
    {
        this.projectGroup = projectGroup;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public int getProjectGroupId()
    {
        return projectGroupId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setProjectGroupId( int projectGroupId )
    {
        this.projectGroupId = projectGroupId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public int getReleaseResultId()
    {
        return releaseResultId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setReleaseResultId( int releaseResultId )
    {
        this.releaseResultId = releaseResultId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public List<ContinuumReleaseResult> getReleaseResults()
    {
        return releaseResults;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setReleaseResults( List<ContinuumReleaseResult> releaseResults )
    {
        this.releaseResults = releaseResults;
    }

    public List<String> getSelectedReleaseResults()
    {
        return selectedReleaseResults;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setSelectedReleaseResults( List<String> selectedReleaseResults )
    {
        this.selectedReleaseResults = selectedReleaseResults;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public ReleaseResult getResult()
    {
        return result;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setResult( ReleaseResult result )
    {
        this.result = result;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public boolean isConfirmed()
    {
        return confirmed;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setConfirmed( boolean confirmed )
    {
        this.confirmed = confirmed;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName( String projectName )
    {
        this.projectName = projectName;
    }

    public String getReleaseGoal()
    {
        return releaseGoal;
    }

    public void setReleaseGoal( String releaseGoal )
    {
        this.releaseGoal = releaseGoal;
    }
<<<<<<< HEAD
=======

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
>>>>>>> refs/remotes/apache/trunk
}
