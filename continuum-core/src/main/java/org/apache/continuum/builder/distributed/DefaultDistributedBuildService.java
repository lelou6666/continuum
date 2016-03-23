package org.apache.continuum.builder.distributed;

<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

=======
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

import org.apache.commons.io.IOUtils;
import org.apache.continuum.builder.distributed.manager.DistributedBuildManager;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.builder.distributed.util.DistributedBuildUtil;
import org.apache.continuum.builder.utils.ContinuumBuildConstant;
import org.apache.continuum.dao.BuildDefinitionDao;
import org.apache.continuum.dao.BuildResultDao;
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.dao.ProjectScmRootDao;
<<<<<<< HEAD
=======
import org.apache.continuum.model.project.ProjectRunSummary;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.model.project.ProjectScmRoot;
import org.apache.maven.continuum.ContinuumException;
import org.apache.maven.continuum.configuration.ConfigurationException;
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.apache.maven.continuum.execution.ContinuumBuildExecutorConstants;
import org.apache.maven.continuum.installation.InstallationService;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildResult;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectDependency;
import org.apache.maven.continuum.model.project.ProjectDeveloper;
import org.apache.maven.continuum.model.project.ProjectNotifier;
import org.apache.maven.continuum.model.scm.ChangeFile;
import org.apache.maven.continuum.model.scm.ChangeSet;
import org.apache.maven.continuum.model.system.Installation;
import org.apache.maven.continuum.model.system.Profile;
import org.apache.maven.continuum.notification.ContinuumNotificationDispatcher;
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.apache.maven.continuum.store.ContinuumStoreException;
<<<<<<< HEAD
=======
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
>>>>>>> refs/remotes/apache/trunk
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

<<<<<<< HEAD
/**
 * @plexus.component role="org.apache.continuum.builder.distributed.DistributedBuildService"
 */
=======
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component( role = org.apache.continuum.builder.distributed.DistributedBuildService.class )
>>>>>>> refs/remotes/apache/trunk
public class DefaultDistributedBuildService
    implements DistributedBuildService
{
    private static final Logger log = LoggerFactory.getLogger( DefaultDistributedBuildService.class );

<<<<<<< HEAD
    /**
     * @plexus.requirement
     */
    private ProjectDao projectDao;

    /**
     * @plexus.requirement
     */
    private BuildDefinitionDao buildDefinitionDao;

    /**
     * @plexus.requirement
     */
    private BuildResultDao buildResultDao;

    /**
     * @plexus.requirement
     */
    private ProjectScmRootDao projectScmRootDao;

    /**
     * @plexus.requirement
     */
    private ConfigurationService configurationService;

    /**
     * @plexus.requirement
     */
    private InstallationService installationService;

    /**
     * @plexus.requirement
     */
    private ContinuumNotificationDispatcher notifierDispatcher;

    /**
     * @plexus.requirement
     */
    private DistributedBuildUtil distributedBuildUtil;

=======
    @Requirement
    private ProjectDao projectDao;

    @Requirement
    private BuildDefinitionDao buildDefinitionDao;

    @Requirement
    private BuildResultDao buildResultDao;

    @Requirement
    private ProjectScmRootDao projectScmRootDao;

    @Requirement
    private ConfigurationService configurationService;

    @Requirement
    private InstallationService installationService;

    @Requirement
    private ContinuumNotificationDispatcher notifierDispatcher;

    @Requirement
    private DistributedBuildUtil distributedBuildUtil;

    @Requirement
    private DistributedBuildManager distributedBuildManager;

>>>>>>> refs/remotes/apache/trunk
    public void updateBuildResult( Map<String, Object> context )
        throws ContinuumException
    {
        try
        {
            int projectId = ContinuumBuildConstant.getProjectId( context );
            int buildDefinitionId = ContinuumBuildConstant.getBuildDefinitionId( context );
<<<<<<< HEAD
    
            log.info( "update build result of project '" + projectId + "'" );
    
            Project project = projectDao.getProjectWithAllDetails( projectId );
            BuildDefinition buildDefinition = buildDefinitionDao.getBuildDefinition( buildDefinitionId );
    
            BuildResult oldBuildResult =
                buildResultDao.getLatestBuildResultForBuildDefinition( projectId, buildDefinitionId );
    
            int buildNumber;
    
            if ( ContinuumBuildConstant.getBuildState( context ) == ContinuumProjectState.OK )
            {
                buildNumber = project.getBuildNumber() + 1;
            }
            else
            {
                buildNumber = project.getBuildNumber();
            }
    
            // ----------------------------------------------------------------------
            // Make the buildResult
            // ----------------------------------------------------------------------
    
            BuildResult buildResult = distributedBuildUtil.convertMapToBuildResult( context );
    
            if ( buildResult.getState() != ContinuumProjectState.CANCELLED )
            {
                buildResult.setBuildDefinition( buildDefinition );
                buildResult.setBuildNumber( buildNumber );
                buildResult.setModifiedDependencies( distributedBuildUtil.getModifiedDependencies( oldBuildResult, context ) );
                buildResult.setScmResult( distributedBuildUtil.getScmResult( context ) );
    
                Date date = ContinuumBuildConstant.getLatestUpdateDate( context );
                if ( date != null )
                {
                    buildResult.setLastChangedDate( date.getTime() );
                }
                else if ( oldBuildResult != null )
                {
                    buildResult.setLastChangedDate( oldBuildResult.getLastChangedDate() );
                }
    
                buildResultDao.addBuildResult( project, buildResult );

                buildResult = buildResultDao.getBuildResult( buildResult.getId() );

                project.setOldState( project.getState() );
                project.setState( ContinuumBuildConstant.getBuildState( context ) );
                project.setBuildNumber( buildNumber );
                project.setLatestBuildId( buildResult.getId() );
            }
            else
            {
                project.setState( project.getOldState() );
                project.setOldState( 0 );
            }
    
            projectDao.updateProject( project );
    
            File buildOutputFile = configurationService.getBuildOutputFile( buildResult.getId(), project.getId() );
    
            FileWriter fstream = new FileWriter( buildOutputFile );
            BufferedWriter out = new BufferedWriter( fstream );
            out.write( ContinuumBuildConstant.getBuildOutput( context ) == null ? ""
                : ContinuumBuildConstant.getBuildOutput( context ) );
            out.close();
    
            if ( buildResult.getState() != ContinuumProjectState.CANCELLED )
            {
                notifierDispatcher.buildComplete( project, buildDefinition, buildResult );
=======

            Project project = projectDao.getProjectWithAllDetails( projectId );
            BuildDefinition buildDefinition = buildDefinitionDao.getBuildDefinition( buildDefinitionId );
            BuildResult buildResult, oldBuildResult;

            log.info( "update build result of project '{}'", projectId );

            // Get the result id for this build, it may be current or canceled
            ProjectRunSummary canceledRun = null;
            int existingResultId = 0;
            try
            {
                existingResultId =
                    distributedBuildManager.getCurrentRun( projectId, buildDefinitionId ).getBuildResultId();
            }
            catch ( ContinuumException e )
            {
                try
                {
                    log.debug( "no current run summary found, attempting to find canceled run" );
                    canceledRun = distributedBuildManager.getCanceledRun( projectId, buildDefinitionId );
                    existingResultId = canceledRun.getBuildResultId();
                }
                catch ( ContinuumException e1 )
                {
                    log.warn( "failed to find result for remote build {}", e.getMessage() );
                }
            }

            boolean existingResult = existingResultId > 0;

            int buildNumber = project.getBuildNumber();

            if ( existingResult )
            {
                buildResult = buildResultDao.getBuildResult( existingResultId );
                distributedBuildUtil.updateBuildResultFromMap( buildResult, context );
                oldBuildResult =
                    buildResultDao.getPreviousBuildResultForBuildDefinition( projectId, buildDefinitionId,
                                                                             existingResultId );
            }
            else
            {
                buildNumber += 1;
                buildResult = distributedBuildUtil.convertMapToBuildResult( context );
                oldBuildResult = buildResultDao.getLatestBuildResultForBuildDefinition( projectId, buildDefinitionId );
                buildResult.setBuildNumber( buildNumber );
                buildResult.setBuildDefinition( buildDefinition );
            }

            // Set the complete contents of the build result...

            buildResult.setModifiedDependencies(
                distributedBuildUtil.getModifiedDependencies( oldBuildResult, context ) );
            buildResult.setScmResult( distributedBuildUtil.getScmResult( context ) );

            Date date = ContinuumBuildConstant.getLatestUpdateDate( context );
            if ( date != null )
            {
                buildResult.setLastChangedDate( date.getTime() );
            }
            else if ( oldBuildResult != null )
            {
                buildResult.setLastChangedDate( oldBuildResult.getLastChangedDate() );
            }

            if ( existingResult )
            {
                buildResultDao.updateBuildResult( buildResult );
            }
            else
            {
                buildResultDao.addBuildResult( project, buildResult );
                buildResult = buildResultDao.getBuildResult( buildResult.getId() );
            }

            project.setOldState( project.getState() );
            project.setState( ContinuumBuildConstant.getBuildState( context ) );
            project.setBuildNumber( buildNumber );
            project.setLatestBuildId( buildResult.getId() );
            projectDao.updateProject( project );

            File buildOutputFile = configurationService.getBuildOutputFile( buildResult.getId(), project.getId() );
            FileWriter fileWriter = null;
            try
            {
                fileWriter = new FileWriter( buildOutputFile );
                String output = ContinuumBuildConstant.getBuildOutput( context );
                fileWriter.write( output == null ? "" : output );
            }
            finally
            {
                IOUtils.closeQuietly( fileWriter );
            }

            if ( canceledRun != null )
            {
                // We have joined the cancellation with the result, time to clean it up
                distributedBuildManager.removeCanceledRun( canceledRun );
            }
            else
            {
                // Build was successful, notify and cleanup associated run
                notifierDispatcher.buildComplete( project, buildDefinition, buildResult );
                distributedBuildManager.removeCurrentRun( projectId, buildDefinitionId );
>>>>>>> refs/remotes/apache/trunk
            }
        }
        catch ( ContinuumStoreException e )
        {
            throw new ContinuumException( "Error while updating build result for project", e );
        }
        catch ( ConfigurationException e )
        {
            throw new ContinuumException( "Error retrieving build output file", e );
        }
        catch ( IOException e )
        {
            throw new ContinuumException( "Error while writing build output to file", e );
        }
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void prepareBuildFinished( Map<String, Object> context )
        throws ContinuumException
    {
        int projectGroupId = ContinuumBuildConstant.getProjectGroupId( context );
        String scmRootAddress = ContinuumBuildConstant.getScmRootAddress( context );
<<<<<<< HEAD
    
        try
        {
            ProjectScmRoot scmRoot =
                projectScmRootDao.getProjectScmRootByProjectGroupAndScmRootAddress( projectGroupId, scmRootAddress );
    
            String error = ContinuumBuildConstant.getScmError( context );
    
=======

        try
        {
            ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRootByProjectGroupAndScmRootAddress( projectGroupId,
                                                                                                         scmRootAddress );

            String error = ContinuumBuildConstant.getScmError( context );

>>>>>>> refs/remotes/apache/trunk
            if ( StringUtils.isEmpty( error ) )
            {
                scmRoot.setState( ContinuumProjectState.UPDATED );
            }
            else
            {
                scmRoot.setState( ContinuumProjectState.ERROR );
                scmRoot.setError( error );
            }
<<<<<<< HEAD
    
            projectScmRootDao.updateProjectScmRoot( scmRoot );
    
=======

            projectScmRootDao.updateProjectScmRoot( scmRoot );

>>>>>>> refs/remotes/apache/trunk
            notifierDispatcher.prepareBuildComplete( scmRoot );
        }
        catch ( ContinuumStoreException e )
        {
            throw new ContinuumException( "Error while updating project scm root '" + scmRootAddress + "'", e );
        }
    }
<<<<<<< HEAD
    
    public void startProjectBuild( int projectId )
=======

    public void startProjectBuild( int projectId, int buildDefinitionId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException
    {
        try
        {
<<<<<<< HEAD
            Project project = projectDao.getProject( projectId );
            project.setState( ContinuumProjectState.BUILDING );
            projectDao.updateProject( project );
=======
            createNewBuildResult( distributedBuildManager.getCurrentRun( projectId, buildDefinitionId ) );
>>>>>>> refs/remotes/apache/trunk
        }
        catch ( ContinuumStoreException e )
        {
            log.error( "Error while updating project's state (projectId=" + projectId + ")", e );
            throw new ContinuumException( "Error while updating project's state (projectId=" + projectId + ")", e );
        }
    }
<<<<<<< HEAD
    
=======

    private void createNewBuildResult( ProjectRunSummary summary )
        throws ContinuumStoreException
    {
        long now = System.currentTimeMillis();

        Project project = projectDao.getProject( summary.getProjectId() );
        BuildDefinition buildDef = buildDefinitionDao.getBuildDefinition( summary.getBuildDefinitionId() );

        project.setBuildNumber( project.getBuildNumber() + 1 ); // starting build, create a new build number

        BuildResult buildResult = new BuildResult();
        buildResult.setBuildNumber( project.getBuildNumber() );
        buildResult.setBuildUrl( summary.getBuildAgentUrl() );
        buildResult.setState( ContinuumProjectState.BUILDING );
        buildResult.setBuildDefinition( buildDef );
        buildResult.setTrigger( summary.getTrigger() );
        buildResult.setUsername( summary.getTriggeredBy() );
        buildResult.setStartTime( now );
        buildResultDao.addBuildResult( project, buildResult );

        project.setLatestBuildId( buildResult.getId() );
        project.setOldState( project.getState() );
        project.setState( ContinuumProjectState.BUILDING );
        projectDao.updateProject( project );

        summary.setBuildResultId( buildResult.getId() );
    }

>>>>>>> refs/remotes/apache/trunk
    public void startPrepareBuild( Map<String, Object> context )
        throws ContinuumException
    {
        int projectGroupId = ContinuumBuildConstant.getProjectGroupId( context );
<<<<<<< HEAD
    	
        try
        {
            String scmRootAddress = ContinuumBuildConstant.getScmRootAddress( context );
    
            ProjectScmRoot scmRoot =
                projectScmRootDao.getProjectScmRootByProjectGroupAndScmRootAddress( projectGroupId, scmRootAddress );
=======

        try
        {
            String scmRootAddress = ContinuumBuildConstant.getScmRootAddress( context );

            ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRootByProjectGroupAndScmRootAddress( projectGroupId,
                                                                                                         scmRootAddress );
>>>>>>> refs/remotes/apache/trunk
            scmRoot.setOldState( scmRoot.getState() );
            scmRoot.setState( ContinuumProjectState.UPDATING );
            projectScmRootDao.updateProjectScmRoot( scmRoot );
        }
        catch ( ContinuumStoreException e )
        {
            log.error( "Error while updating project group'" + projectGroupId + "' scm root's state", e );
<<<<<<< HEAD
            throw new ContinuumException( "Error while updating project group'" + projectGroupId + "' scm root's state", e );
        }
    }
    
=======
            throw new ContinuumException( "Error while updating project group'" + projectGroupId + "' scm root's state",
                                          e );
        }
    }

>>>>>>> refs/remotes/apache/trunk
    public Map<String, String> getEnvironments( int buildDefinitionId, String installationType )
        throws ContinuumException
    {
        BuildDefinition buildDefinition;
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        try
        {
            buildDefinition = buildDefinitionDao.getBuildDefinition( buildDefinitionId );
        }
        catch ( ContinuumStoreException e )
        {
            throw new ContinuumException( "Failed to retrieve build definition: " + buildDefinitionId, e );
        }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        Profile profile = buildDefinition.getProfile();
        if ( profile == null )
        {
            return Collections.EMPTY_MAP;
        }
        Map<String, String> envVars = new HashMap<String, String>();
        String javaHome = getJavaHomeValue( buildDefinition );
        if ( !StringUtils.isEmpty( javaHome ) )
        {
            envVars.put( installationService.getEnvVar( InstallationService.JDK_TYPE ), javaHome );
        }
        Installation builder = profile.getBuilder();
        if ( builder != null )
        {
            envVars.put( installationService.getEnvVar( installationType ), builder.getVarValue() );
        }
        envVars.putAll( getEnvironmentVariables( buildDefinition ) );
        return envVars;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void updateProject( Map<String, Object> context )
        throws ContinuumException
    {
        try
        {
<<<<<<< HEAD
            Project project = projectDao.getProject( ContinuumBuildConstant.getProjectId( context ) );
    
=======
            Project project = projectDao.getProjectWithAllDetails( ContinuumBuildConstant.getProjectId( context ) );

>>>>>>> refs/remotes/apache/trunk
            if ( StringUtils.isNotBlank( ContinuumBuildConstant.getGroupId( context ) ) )
            {
                project.setGroupId( ContinuumBuildConstant.getGroupId( context ) );
            }
            if ( StringUtils.isNotBlank( ContinuumBuildConstant.getArtifactId( context ) ) )
            {
                project.setArtifactId( ContinuumBuildConstant.getArtifactId( context ) );
            }
            if ( StringUtils.isNotBlank( ContinuumBuildConstant.getVersion( context ) ) )
            {
                project.setVersion( ContinuumBuildConstant.getVersion( context ) );
            }
            if ( StringUtils.isNotBlank( ContinuumBuildConstant.getProjectName( context ) ) )
            {
                project.setName( ContinuumBuildConstant.getProjectName( context ) );
            }
            if ( StringUtils.isNotBlank( ContinuumBuildConstant.getProjectDescription( context ) ) )
            {
                project.setDescription( ContinuumBuildConstant.getProjectDescription( context ) );
            }
            if ( StringUtils.isNotBlank( ContinuumBuildConstant.getProjectUrl( context ) ) )
            {
                project.setUrl( ContinuumBuildConstant.getProjectUrl( context ) );
            }
            if ( StringUtils.isNotBlank( ContinuumBuildConstant.getScmUrl( context ) ) )
            {
                project.setScmUrl( ContinuumBuildConstant.getScmUrl( context ) );
            }
            if ( StringUtils.isNotBlank( ContinuumBuildConstant.getScmTag( context ) ) )
            {
                project.setScmTag( ContinuumBuildConstant.getScmTag( context ) );
            }
            project.setParent( getProjectParent( context ) );
            project.setDependencies( getProjectDependencies( context ) );
            project.setDevelopers( getProjectDevelopers( context ) );
<<<<<<< HEAD
            project.setNotifiers( getProjectNotifiers( context ) );
    
=======

            List<ProjectNotifier> userNotifiers = new ArrayList<ProjectNotifier>();

            if ( project.getNotifiers() != null )
            {
                for ( ProjectNotifier notifier : project.getNotifiers() )
                {
                    if ( notifier.isFromUser() )
                    {
                        ProjectNotifier userNotifier = new ProjectNotifier();

                        userNotifier.setType( notifier.getType() );

                        userNotifier.setEnabled( notifier.isEnabled() );

                        userNotifier.setConfiguration( notifier.getConfiguration() );

                        userNotifier.setFrom( notifier.getFrom() );

                        userNotifier.setRecipientType( notifier.getRecipientType() );

                        userNotifier.setSendOnError( notifier.isSendOnError() );

                        userNotifier.setSendOnFailure( notifier.isSendOnFailure() );

                        userNotifier.setSendOnSuccess( notifier.isSendOnSuccess() );

                        userNotifier.setSendOnWarning( notifier.isSendOnWarning() );

                        userNotifier.setSendOnScmFailure( notifier.isSendOnScmFailure() );

                        userNotifiers.add( userNotifier );
                    }
                }
            }

            project.setNotifiers( getProjectNotifiers( context ) );

            for ( ProjectNotifier userNotifier : userNotifiers )
            {
                project.addNotifier( userNotifier );
            }

>>>>>>> refs/remotes/apache/trunk
            projectDao.updateProject( project );
        }
        catch ( ContinuumStoreException e )
        {
<<<<<<< HEAD
            throw new ContinuumException( "Unable to update project '" + ContinuumBuildConstant.getProjectId( context ) +
        	                              "' from working copy", e );
=======
            throw new ContinuumException( "Unable to update project '" + ContinuumBuildConstant.getProjectId(
                context ) +
                                              "' from working copy", e );
>>>>>>> refs/remotes/apache/trunk
        }
    }

    public boolean shouldBuild( Map<String, Object> context )
    {
        int projectId = ContinuumBuildConstant.getProjectId( context );
<<<<<<< HEAD
        
        try
        {
            int buildDefinitionId = ContinuumBuildConstant.getBuildDefinitionId( context );
    
            int trigger = ContinuumBuildConstant.getTrigger( context );
    
            Project project = projectDao.getProjectWithAllDetails( projectId );
    
            BuildDefinition buildDefinition = buildDefinitionDao.getBuildDefinition( buildDefinitionId );
    
            BuildResult oldBuildResult =
                buildResultDao.getLatestBuildResultForBuildDefinition( projectId, buildDefinitionId );
    
            List<ProjectDependency> modifiedDependencies = distributedBuildUtil.getModifiedDependencies( oldBuildResult, context );
    
            List<ChangeSet> changes = distributedBuildUtil.getScmChanges( context );
    
            if ( buildDefinition.isBuildFresh() )
            {
                log.info( "FreshBuild configured, building (projectId=" + projectId + ")" );
                return true;
            }
=======

        try
        {
            int buildDefinitionId = ContinuumBuildConstant.getBuildDefinitionId( context );

            int trigger = ContinuumBuildConstant.getTrigger( context );

            Project project = projectDao.getProjectWithAllDetails( projectId );

            BuildDefinition buildDefinition = buildDefinitionDao.getBuildDefinition( buildDefinitionId );

            BuildResult oldBuildResult = buildResultDao.getLatestBuildResultForBuildDefinition( projectId,
                                                                                                buildDefinitionId );

            List<ProjectDependency> modifiedDependencies = distributedBuildUtil.getModifiedDependencies( oldBuildResult,
                                                                                                         context );

            List<ChangeSet> changes = distributedBuildUtil.getScmChanges( context );

>>>>>>> refs/remotes/apache/trunk
            if ( buildDefinition.isAlwaysBuild() )
            {
                log.info( "AlwaysBuild configured, building (projectId=" + projectId + ")" );
                return true;
            }
            if ( oldBuildResult == null )
            {
<<<<<<< HEAD
                log.info( "The project '" +  projectId + "' was never built with the current build definition, building" );
                return true;
            }
    
=======
                log.info(
                    "The project '" + projectId + "' was never built with the current build definition, building" );
                return true;
            }

>>>>>>> refs/remotes/apache/trunk
            //CONTINUUM-1428
            if ( project.getOldState() == ContinuumProjectState.ERROR ||
                oldBuildResult.getState() == ContinuumProjectState.ERROR )
            {
                log.info( "Latest state was 'ERROR', building (projectId=" + projectId + ")" );
                return true;
            }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
            if ( trigger == ContinuumProjectState.TRIGGER_FORCED )
            {
                log.info( "The project '" + projectId + "' build is forced, building" );
                return true;
            }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
            Date date = ContinuumBuildConstant.getLatestUpdateDate( context );
            if ( date != null && oldBuildResult.getLastChangedDate() >= date.getTime() )
            {
                log.info( "No changes found, not building (projectId=" + projectId + ")" );
                return false;
            }
            else if ( date != null && changes.isEmpty() )
            {
                // fresh checkout from build agent that's why changes is empty
                log.info( "Changes found in the current project, building (projectId=" + projectId + ")" );
                return true;
            }
<<<<<<< HEAD
    
            boolean shouldBuild = false;
    
            boolean allChangesUnknown = true;
    
=======

            boolean shouldBuild = false;

            boolean allChangesUnknown = true;

>>>>>>> refs/remotes/apache/trunk
            if ( project.getOldState() != ContinuumProjectState.NEW &&
                project.getOldState() != ContinuumProjectState.CHECKEDOUT &&
                project.getState() != ContinuumProjectState.NEW &&
                project.getState() != ContinuumProjectState.CHECKEDOUT )
            {
                // Check SCM changes
                allChangesUnknown = checkAllChangesUnknown( changes );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
                if ( allChangesUnknown )
                {
                    if ( !changes.isEmpty() )
                    {
                        log.info( "The project '" + projectId +
<<<<<<< HEAD
                            "' was not built because all changes are unknown (maybe local modifications or ignored files not defined in your SCM tool." );
=======
                                      "' was not built because all changes are unknown (maybe local modifications or ignored files not defined in your SCM tool." );
>>>>>>> refs/remotes/apache/trunk
                    }
                    else
                    {
                        log.info( "The project '" + projectId +
<<<<<<< HEAD
                            "' was not built because no changes were detected in sources since the last build." );
                    }
                }
    
=======
                                      "' was not built because no changes were detected in sources since the last build." );
                    }
                }

>>>>>>> refs/remotes/apache/trunk
                // Check dependencies changes
                if ( modifiedDependencies != null && !modifiedDependencies.isEmpty() )
                {
                    log.info( "Found dependencies changes, building (projectId=" + projectId + ")" );
                    shouldBuild = true;
                }
            }
<<<<<<< HEAD
    
            // Check changes
            if ( !shouldBuild && ( ( !allChangesUnknown && !changes.isEmpty() ) ||
                project.getExecutorId().equals( ContinuumBuildExecutorConstants.MAVEN_TWO_BUILD_EXECUTOR ) ) )
=======

            // Check changes
            if ( !shouldBuild && ( ( !allChangesUnknown && !changes.isEmpty() ) || project.getExecutorId().equals(
                ContinuumBuildExecutorConstants.MAVEN_TWO_BUILD_EXECUTOR ) ) )
>>>>>>> refs/remotes/apache/trunk
            {
                shouldBuild = shouldBuild( changes, buildDefinition, project, getMavenProjectVersion( context ),
                                           getMavenProjectModules( context ) );
            }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
            if ( shouldBuild )
            {
                log.info( "Changes found in the current project, building (projectId=" + projectId + ")" );
            }
            else
            {
                log.info( "No changes in the current project, not building (projectId=" + projectId + ")" );
            }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
            return shouldBuild;
        }
        catch ( ContinuumStoreException e )
        {
            log.error( "Failed to determine if project '" + projectId + "' should build", e );
        }
        catch ( ContinuumException e )
        {
            log.error( "Failed to determine if project '" + projectId + "' should build", e );
        }
<<<<<<< HEAD
    
        return false;
    }
    
=======

        return false;
    }

>>>>>>> refs/remotes/apache/trunk
    private boolean shouldBuild( List<ChangeSet> changes, BuildDefinition buildDefinition, Project project,
                                 String mavenProjectVersion, List<String> mavenProjectModules )
    {
        //Check if it's a recursive build
        boolean isRecursive = false;
        if ( StringUtils.isNotEmpty( buildDefinition.getArguments() ) )
        {
<<<<<<< HEAD
            isRecursive = buildDefinition.getArguments().indexOf( "-N" ) < 0 &&
                buildDefinition.getArguments().indexOf( "--non-recursive" ) < 0;
        }
    
=======
            isRecursive = buildDefinition.getArguments().indexOf( "-N" ) < 0 && buildDefinition.getArguments().indexOf(
                "--non-recursive" ) < 0;
        }

>>>>>>> refs/remotes/apache/trunk
        if ( isRecursive && changes != null && !changes.isEmpty() )
        {
            if ( log.isInfoEnabled() )
            {
                log.info( "recursive build and changes found --> building (projectId=" + project.getId() + ")" );
            }
            return true;
        }
<<<<<<< HEAD
    
        if ( !project.getVersion().equals( mavenProjectVersion ) )
        {
            log.info( "Found changes in project's version ( maybe project '" + project.getId() +
            		  "' was recently released ), building" );
            return true;
        }
    
=======

        if ( !project.getVersion().equals( mavenProjectVersion ) )
        {
            log.info( "Found changes in project's version ( maybe project '" + project.getId() +
                          "' was recently released ), building" );
            return true;
        }

>>>>>>> refs/remotes/apache/trunk
        if ( changes == null || changes.isEmpty() )
        {
            if ( log.isInfoEnabled() )
            {
                log.info( "Found no changes, not building (projectId=" + project.getId() + ")" );
            }
            return false;
        }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        //check if changes are only in sub-modules or not
        List<ChangeFile> files = new ArrayList<ChangeFile>();
        for ( ChangeSet changeSet : changes )
        {
            files.addAll( changeSet.getFiles() );
        }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        int i = 0;
        while ( i <= files.size() - 1 )
        {
            ChangeFile file = files.get( i );
            if ( log.isDebugEnabled() )
            {
                log.debug( "changeFile.name " + file.getName() );
                log.debug( "check in modules " + mavenProjectModules );
            }
            boolean found = false;
            if ( mavenProjectModules != null )
            {
                for ( String module : mavenProjectModules )
                {
                    if ( file.getName().indexOf( module ) >= 0 )
                    {
                        if ( log.isDebugEnabled() )
                        {
                            log.debug( "changeFile.name " + file.getName() + " removed because in a module" );
                        }
                        files.remove( file );
                        found = true;
                        break;
                    }
                    if ( log.isDebugEnabled() )
                    {
                        log.debug( "not removing file " + file.getName() + " not in module " + module );
                    }
                }
            }
            if ( !found )
            {
                i++;
            }
        }
<<<<<<< HEAD
    
        boolean shouldBuild = !files.isEmpty();
    
=======

        boolean shouldBuild = !files.isEmpty();

>>>>>>> refs/remotes/apache/trunk
        if ( !shouldBuild )
        {
            log.info( "Changes are only in sub-modules (projectId=" + project.getId() + ")." );
        }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        if ( log.isDebugEnabled() )
        {
            log.debug( "shoulbuild = " + shouldBuild );
        }
<<<<<<< HEAD
    
        return shouldBuild;
    }
    
=======

        return shouldBuild;
    }

>>>>>>> refs/remotes/apache/trunk
    private boolean checkAllChangesUnknown( List<ChangeSet> changes )
    {
        for ( ChangeSet changeSet : changes )
        {
            List<ChangeFile> changeFiles = changeSet.getFiles();
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
            for ( ChangeFile changeFile : changeFiles )
            {
                if ( !"unknown".equalsIgnoreCase( changeFile.getStatus() ) )
                {
                    return false;
                }
            }
        }
<<<<<<< HEAD
    
        return true;
    }
    
=======

        return true;
    }
>>>>>>> refs/remotes/apache/trunk

    private String getJavaHomeValue( BuildDefinition buildDefinition )
    {
        Profile profile = buildDefinition.getProfile();
        if ( profile == null )
        {
            return null;
        }
        Installation jdk = profile.getJdk();
        if ( jdk == null )
        {
            return null;
        }
        return jdk.getVarValue();
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    private Map<String, String> getEnvironmentVariables( BuildDefinition buildDefinition )
    {
        Profile profile = buildDefinition.getProfile();
        Map<String, String> envVars = new HashMap<String, String>();
        if ( profile == null )
        {
            return envVars;
        }
        List<Installation> environmentVariables = profile.getEnvironmentVariables();
        if ( environmentVariables.isEmpty() )
        {
            return envVars;
        }
        for ( Installation installation : environmentVariables )
        {
            envVars.put( installation.getVarName(), installation.getVarValue() );
        }
        return envVars;
    }

    private ProjectDependency getProjectParent( Map<String, Object> context )
    {
        Map<String, Object> map = ContinuumBuildConstant.getProjectParent( context );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        if ( map != null && map.size() > 0 )
        {
            ProjectDependency parent = new ProjectDependency();
            parent.setGroupId( ContinuumBuildConstant.getGroupId( map ) );
            parent.setArtifactId( ContinuumBuildConstant.getArtifactId( map ) );
            parent.setVersion( ContinuumBuildConstant.getVersion( map ) );
<<<<<<< HEAD
    
            return parent;
        }
    
        return null;
    }
    
    private List<ProjectDependency> getProjectDependencies( Map<String, Object> context )
    {
        List<ProjectDependency> projectDependencies = new ArrayList<ProjectDependency>();
    
        List<Map<String, Object>> dependencies = ContinuumBuildConstant.getProjectDependencies( context );
    
=======

            return parent;
        }

        return null;
    }

    private List<ProjectDependency> getProjectDependencies( Map<String, Object> context )
    {
        List<ProjectDependency> projectDependencies = new ArrayList<ProjectDependency>();

        List<Map<String, Object>> dependencies = ContinuumBuildConstant.getProjectDependencies( context );

>>>>>>> refs/remotes/apache/trunk
        if ( dependencies != null )
        {
            for ( Map<String, Object> map : dependencies )
            {
                ProjectDependency dependency = new ProjectDependency();
                dependency.setGroupId( ContinuumBuildConstant.getGroupId( map ) );
                dependency.setArtifactId( ContinuumBuildConstant.getArtifactId( map ) );
                dependency.setVersion( ContinuumBuildConstant.getVersion( map ) );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
                projectDependencies.add( dependency );
            }
        }
        return projectDependencies;
    }
<<<<<<< HEAD
    
    private List<ProjectDeveloper> getProjectDevelopers( Map<String, Object> context )
    {
        List<ProjectDeveloper> projectDevelopers = new ArrayList<ProjectDeveloper>();
    
        List<Map<String, Object>> developers = ContinuumBuildConstant.getProjectDevelopers( context );
    
=======

    private List<ProjectDeveloper> getProjectDevelopers( Map<String, Object> context )
    {
        List<ProjectDeveloper> projectDevelopers = new ArrayList<ProjectDeveloper>();

        List<Map<String, Object>> developers = ContinuumBuildConstant.getProjectDevelopers( context );

>>>>>>> refs/remotes/apache/trunk
        if ( developers != null )
        {
            for ( Map<String, Object> map : developers )
            {
                ProjectDeveloper developer = new ProjectDeveloper();
                developer.setName( ContinuumBuildConstant.getDeveloperName( map ) );
                developer.setEmail( ContinuumBuildConstant.getDeveloperEmail( map ) );
                developer.setScmId( ContinuumBuildConstant.getDeveloperScmId( map ) );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
                projectDevelopers.add( developer );
            }
        }
        return projectDevelopers;
    }
<<<<<<< HEAD
    
    private List<ProjectNotifier> getProjectNotifiers( Map<String, Object> context )
    {
        List<ProjectNotifier> projectNotifiers = new ArrayList<ProjectNotifier>();
    
        List<Map<String, Object>> notifiers = ContinuumBuildConstant.getProjectNotifiers( context );
    
=======

    private List<ProjectNotifier> getProjectNotifiers( Map<String, Object> context )
    {
        List<ProjectNotifier> projectNotifiers = new ArrayList<ProjectNotifier>();

        List<Map<String, Object>> notifiers = ContinuumBuildConstant.getProjectNotifiers( context );

>>>>>>> refs/remotes/apache/trunk
        if ( notifiers != null )
        {
            for ( Map<String, Object> map : notifiers )
            {
                ProjectNotifier notifier = new ProjectNotifier();
                notifier.setConfiguration( ContinuumBuildConstant.getNotifierConfiguration( map ) );
                notifier.setEnabled( ContinuumBuildConstant.isNotifierEnabled( map ) );
                notifier.setFrom( ContinuumBuildConstant.getNotifierFrom( map ) );
                notifier.setRecipientType( ContinuumBuildConstant.getNotifierRecipientType( map ) );
                notifier.setSendOnError( ContinuumBuildConstant.isNotifierSendOnError( map ) );
                notifier.setSendOnFailure( ContinuumBuildConstant.isNotifierSendOnFailure( map ) );
                notifier.setSendOnScmFailure( ContinuumBuildConstant.isNotifierSendOnScmFailure( map ) );
                notifier.setSendOnSuccess( ContinuumBuildConstant.isNotifierSendOnSuccess( map ) );
                notifier.setSendOnWarning( ContinuumBuildConstant.isNotifierSendOnWarning( map ) );
                notifier.setType( ContinuumBuildConstant.getNotifierType( map ) );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
                projectNotifiers.add( notifier );
            }
        }
        return projectNotifiers;
    }

    private String getMavenProjectVersion( Map<String, Object> context )
    {
        Map<String, Object> map = ContinuumBuildConstant.getMavenProject( context );
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        if ( !map.isEmpty() )
        {
            return ContinuumBuildConstant.getVersion( map );
        }
<<<<<<< HEAD
    
        return null;
    }
    
    private List<String> getMavenProjectModules( Map<String, Object> context )
    {
        Map<String, Object> map = ContinuumBuildConstant.getMavenProject( context );
    
=======

        return null;
    }

    private List<String> getMavenProjectModules( Map<String, Object> context )
    {
        Map<String, Object> map = ContinuumBuildConstant.getMavenProject( context );

>>>>>>> refs/remotes/apache/trunk
        if ( !map.isEmpty() )
        {
            return ContinuumBuildConstant.getProjectModules( map );
        }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
        return null;
    }
}
