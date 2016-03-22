package org.apache.maven.continuum.scm.queue;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.continuum.dao.BuildDefinitionDao;
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.dao.ProjectScmRootDao;
import org.apache.continuum.model.project.ProjectScmRoot;
import org.apache.maven.continuum.core.action.AbstractContinuumAction;
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

import org.apache.continuum.dao.BuildDefinitionDao;
import org.apache.continuum.dao.BuildResultDao;
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.dao.ProjectGroupDao;
import org.apache.continuum.dao.ProjectScmRootDao;
import org.apache.continuum.model.project.ProjectScmRoot;
import org.apache.continuum.taskqueue.PrepareBuildProjectsTask;
import org.apache.continuum.utils.ContinuumUtils;
import org.apache.continuum.utils.ProjectSorter;
import org.apache.continuum.utils.build.BuildTrigger;
import org.apache.maven.continuum.core.action.AbstractContinuumAction;
import org.apache.maven.continuum.core.action.CheckWorkingDirectoryAction;
import org.apache.maven.continuum.core.action.CheckoutProjectContinuumAction;
import org.apache.maven.continuum.core.action.UpdateWorkingDirectoryFromScmContinuumAction;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildResult;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.model.scm.ChangeSet;
import org.apache.maven.continuum.model.scm.ScmResult;
import org.apache.maven.continuum.notification.ContinuumNotificationDispatcher;
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.apache.maven.continuum.store.ContinuumStoreException;
<<<<<<< HEAD
import org.apache.maven.continuum.utils.ContinuumUtils;
import org.apache.maven.continuum.utils.ProjectSorter;
import org.apache.maven.continuum.utils.WorkingDirectoryService;
import org.codehaus.plexus.action.ActionManager;
import org.codehaus.plexus.action.ActionNotFoundException;
import org.codehaus.plexus.logging.AbstractLogEnabled;
=======
import org.apache.maven.continuum.utils.WorkingDirectoryService;
import org.codehaus.plexus.action.ActionManager;
import org.codehaus.plexus.action.ActionNotFoundException;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
>>>>>>> refs/remotes/apache/trunk
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.execution.TaskExecutionException;
import org.codehaus.plexus.taskqueue.execution.TaskExecutor;
import org.codehaus.plexus.util.StringUtils;
<<<<<<< HEAD
import org.codehaus.plexus.util.dag.CycleDetectedException;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
 * @plexus.component role="org.codehaus.plexus.taskqueue.execution.TaskExecutor"
 * role-hint="prepare-build-project"
 */
public class PrepareBuildProjectsTaskExecutor
    extends AbstractLogEnabled
    implements TaskExecutor
{
    /**
     * @plexus.requirement
     */
    private ActionManager actionManager;

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
    private ProjectScmRootDao projectScmRootDao;
    
    /**
     * @plexus.requirement
     */
    private WorkingDirectoryService workingDirectoryService;

    /**
     * @plexus.requirement
     */
    private ContinuumNotificationDispatcher notifierDispatcher;

=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
 */
@Component( role = org.codehaus.plexus.taskqueue.execution.TaskExecutor.class, hint = "prepare-build-project" )
public class PrepareBuildProjectsTaskExecutor
    implements TaskExecutor
{
    private static final Logger log = LoggerFactory.getLogger( PrepareBuildProjectsTaskExecutor.class );

    @Requirement
    private ActionManager actionManager;

    @Requirement
    private ProjectDao projectDao;

    @Requirement
    private BuildDefinitionDao buildDefinitionDao;

    @Requirement
    private ProjectScmRootDao projectScmRootDao;

    @Requirement
    private BuildResultDao buildResultDao;

    @Requirement
    private WorkingDirectoryService workingDirectoryService;

    @Requirement
    private ContinuumNotificationDispatcher notifierDispatcher;

    @Requirement
    private ProjectGroupDao projectGroupDao;

>>>>>>> refs/remotes/apache/trunk
    public void executeTask( Task task )
        throws TaskExecutionException
    {
        PrepareBuildProjectsTask prepareTask = (PrepareBuildProjectsTask) task;
<<<<<<< HEAD
        
        Map<Integer, Integer> projectsBuildDefinitionsMap = prepareTask.getProjectsBuildDefinitionsMap();
        int trigger = prepareTask.getTrigger();
        Set<Integer> projectsId = projectsBuildDefinitionsMap.keySet();
        Map context = new HashMap();

        try
        {
            for ( Integer projectId : projectsId )
            {
                int buildDefinitionId = projectsBuildDefinitionsMap.get( projectId );
                
                getLogger().info( "Initializing prepare build" );
                context = initializeContext( projectId, buildDefinitionId );
                
                getLogger().info( "Starting prepare build of project: " + AbstractContinuumAction.getProject( context ).getName() );
                startPrepareBuild( context );
                
                if ( !checkProjectScmRoot( context ) )
                {
                    break;
                }
                
                try
                {
                    if ( AbstractContinuumAction.getBuildDefinition( context ).isBuildFresh() )
                    {
                        getLogger().info( "Purging existing working copy" );
                        cleanWorkingDirectory( context );
                    }
                    
                    // ----------------------------------------------------------------------
                    // TODO: Centralize the error handling from the SCM related actions.
                    // ContinuumScmResult should return a ContinuumScmResult from all
                    // methods, even in a case of failure.
                    // ----------------------------------------------------------------------
                    getLogger().info( "Updating working dir" );
                    updateWorkingDirectory( context );
            
                    getLogger().info( "Merging SCM results" );
                    //CONTINUUM-1393
                    if ( !AbstractContinuumAction.getBuildDefinition( context ).isBuildFresh() )
                    {
                        mergeScmResults( context );
                    }
                }
                finally
                {
                    getLogger().info( "Ending prepare build of project: " + AbstractContinuumAction.getProject( context).getName() );
                    endProjectPrepareBuild( context );
                }
            }
        }
        finally
        {
            getLogger().info( "Ending prepare build" );
            endPrepareBuild( context );
        }

        int projectGroupId = AbstractContinuumAction.getProjectGroupId( context );
        buildProjects( projectGroupId, projectsBuildDefinitionsMap, trigger );
    }
    
    private Map initializeContext( int projectId, int buildDefinitionId )
        throws TaskExecutionException
    {
        Map context = new HashMap();

        try
        {
            Project project = projectDao.getProjectWithScmDetails( projectId );
            ProjectGroup projectGroup = project.getProjectGroup();
            
            List<ProjectScmRoot> scmRoots = projectScmRootDao.getProjectScmRootByProjectGroup( projectGroup.getId() );
            String projectScmUrl = project.getScmUrl();
            
            for ( ProjectScmRoot projectScmRoot : scmRoots )
            {
                if ( projectScmUrl.contains( projectScmRoot.getScmRootAddress() ) )
                {
                    context.put( AbstractContinuumAction.KEY_PROJECT_SCM_ROOT, projectScmRoot );
=======

        Map<Integer, Integer> projectsBuildDefinitionsMap = prepareTask.getProjectsBuildDefinitionsMap();
        BuildTrigger buildTrigger = prepareTask.getBuildTrigger();
        Set<Integer> projectsId = projectsBuildDefinitionsMap.keySet();
        Map<String, Object> context = new HashMap<String, Object>();
        Map<Integer, ScmResult> scmResultMap = new HashMap<Integer, ScmResult>();
        List<Project> projectList = new ArrayList<Project>();
        int projectGroupId = 0;

        try
        {
            if ( !projectsId.isEmpty() )
            {
                int projectId = projectsId.iterator().next();
                Project project = projectDao.getProject( projectId );
                ProjectGroup projectGroup = project.getProjectGroup();
                projectGroupId = projectGroup.getId();

                List<Project> projects = projectDao.getProjectsWithDependenciesByGroupId( projectGroupId );
                projectList = ProjectSorter.getSortedProjects( projects, log );
            }

            Project rootProject = null;

            for ( Project project : projectList )
            {
                if ( rootProject == null )
                {
                    // first project is the root project.
                    rootProject = project;
                }

                int projectId = project.getId();
                int buildDefinitionId;

                if ( projectsBuildDefinitionsMap.get( projectId ) != null )
                {
                    buildDefinitionId = projectsBuildDefinitionsMap.get( projectId );

                    log.info( "Initializing prepare build" );
                    context = initializeContext( project, buildDefinitionId, prepareTask.getBuildTrigger() );

                    log.info( "Starting prepare build of project: " + AbstractContinuumAction.getProject(
                        context ).getName() );
                    startPrepareBuild( context );

                    if ( !checkProjectScmRoot( context ) )
                    {
                        break;
                    }

                    try
                    {
                        if ( AbstractContinuumAction.getBuildDefinition( context ).isBuildFresh() )
                        {
                            log.info( "Purging existing working copy" );
                            cleanWorkingDirectory( context );
                        }

                        // ----------------------------------------------------------------------
                        // TODO: Centralize the error handling from the SCM related actions.
                        // ContinuumScmResult should return a ContinuumScmResult from all
                        // methods, even in a case of failure.
                        // ----------------------------------------------------------------------
                        log.info( "Updating working dir" );
                        updateWorkingDirectory( context, rootProject );

                        log.info( "Merging SCM results" );
                        //CONTINUUM-1393
                        if ( !AbstractContinuumAction.getBuildDefinition( context ).isBuildFresh() )
                        {
                            mergeScmResults( context );
                        }
                    }
                    finally
                    {
                        log.info( "Ending prepare build of project: " + AbstractContinuumAction.getProject(
                            context ).getName() );
                        scmResultMap.put( AbstractContinuumAction.getProjectId( context ),
                                          AbstractContinuumAction.getScmResult( context, null ) );
                        endProjectPrepareBuild( context );
                    }
                }
            }
        }
        catch ( ContinuumStoreException e )
        {
            throw new TaskExecutionException( "Failed to prepare build project group: " + projectGroupId, e );
        }
        finally
        {
            log.info( "Ending prepare build" );
            endPrepareBuild( context );
        }

        if ( checkProjectScmRoot( context ) )
        {
            projectGroupId = AbstractContinuumAction.getProjectGroupId( context );
            buildProjects( projectGroupId, projectList, projectsBuildDefinitionsMap, buildTrigger, scmResultMap );
        }
    }

    private Map<String, Object> initializeContext( Project project, int buildDefinitionId, BuildTrigger buildTrigger )
        throws TaskExecutionException
    {
        Map<String, Object> context = new HashMap<String, Object>();

        try
        {
            ProjectGroup projectGroup = project.getProjectGroup();

            List<ProjectScmRoot> scmRoots = projectScmRootDao.getProjectScmRootByProjectGroup( projectGroup.getId() );
            String projectScmUrl = project.getScmUrl();
            String projectScmRootAddress = "";

            for ( ProjectScmRoot projectScmRoot : scmRoots )
            {
                projectScmRootAddress = projectScmRoot.getScmRootAddress();

                if ( projectScmUrl.startsWith( projectScmRoot.getScmRootAddress() ) )
                {
                    AbstractContinuumAction.setProjectScmRoot( context, projectScmRoot );
                    AbstractContinuumAction.setProjectScmRootUrl( context, projectScmRootAddress );
>>>>>>> refs/remotes/apache/trunk
                    break;
                }
            }

<<<<<<< HEAD
            context.put( AbstractContinuumAction.KEY_PROJECT_GROUP_ID, projectGroup.getId() );
            context.put( AbstractContinuumAction.KEY_PROJECT_ID, projectId );
            context.put( AbstractContinuumAction.KEY_PROJECT, project );
    
            context.put( AbstractContinuumAction.KEY_BUILD_DEFINITION_ID, buildDefinitionId );
            context.put( AbstractContinuumAction.KEY_BUILD_DEFINITION, buildDefinitionDao.getBuildDefinition( buildDefinitionId ) );
            
            context.put( AbstractContinuumAction.KEY_OLD_SCM_RESULT, project.getScmResult() );
=======
            AbstractContinuumAction.setProjectGroupId( context, projectGroup.getId() );
            AbstractContinuumAction.setProjectId( context, project.getId() );
            AbstractContinuumAction.setProject( context, project );
            AbstractContinuumAction.setBuildTrigger( context, buildTrigger );

            AbstractContinuumAction.setBuildDefinitionId( context, buildDefinitionId );
            AbstractContinuumAction.setBuildDefinition( context, buildDefinitionDao.getBuildDefinition(
                buildDefinitionId ) );

            if ( project.isCheckedOutInSingleDirectory() )
            {
                List<Project> projectsInGroup = projectGroupDao.getProjectGroupWithProjects(
                    projectGroup.getId() ).getProjects();
                List<Project> projectsWithCommonScmRoot = new ArrayList<Project>();
                for ( Project projectInGroup : projectsInGroup )
                {
                    if ( projectInGroup.getScmUrl().startsWith( projectScmRootAddress ) )
                    {
                        projectsWithCommonScmRoot.add( projectInGroup );
                    }
                }
                AbstractContinuumAction.setListOfProjectsInGroupWithCommonScmRoot( context, projectsWithCommonScmRoot );
            }

            BuildResult oldBuildResult = buildResultDao.getLatestBuildResultForBuildDefinition( project.getId(),
                                                                                                buildDefinitionId );

            if ( oldBuildResult != null )
            {
                AbstractContinuumAction.setOldScmResult( context, getOldScmResults( project.getId(),
                                                                                    oldBuildResult.getBuildNumber(),
                                                                                    oldBuildResult.getEndTime() ) );
            }
            else
            {
                AbstractContinuumAction.setOldScmResult( context, null );
            }
>>>>>>> refs/remotes/apache/trunk
        }
        catch ( ContinuumStoreException e )
        {
            throw new TaskExecutionException( "Error initializing pre-build context", e );
        }
<<<<<<< HEAD
        
        return context;
    }
    
    private void cleanWorkingDirectory( Map context )
=======

        return context;
    }

    private void cleanWorkingDirectory( Map<String, Object> context )
>>>>>>> refs/remotes/apache/trunk
        throws TaskExecutionException
    {
        performAction( "clean-working-directory", context );
    }
<<<<<<< HEAD
    
    private void updateWorkingDirectory( Map context )
        throws TaskExecutionException
    {
        performAction( "check-working-directory", context );
    
        boolean workingDirectoryExists =
            AbstractContinuumAction.getBoolean( context, AbstractContinuumAction.KEY_WORKING_DIRECTORY_EXISTS );
    
        ScmResult scmResult;
    
        if ( workingDirectoryExists )
        {
            performAction( "update-working-directory-from-scm", context );
    
            scmResult = AbstractContinuumAction.getUpdateScmResult( context, null );
=======

    private void updateWorkingDirectory( Map<String, Object> context, Project rootProject )
        throws TaskExecutionException
    {
        performAction( "check-working-directory", context );

        boolean workingDirectoryExists = CheckWorkingDirectoryAction.isWorkingDirectoryExists( context );

        ScmResult scmResult;

        if ( workingDirectoryExists )
        {
            performAction( "update-working-directory-from-scm", context );

            scmResult = UpdateWorkingDirectoryFromScmContinuumAction.getUpdateScmResult( context, null );
>>>>>>> refs/remotes/apache/trunk
        }
        else
        {
            Project project = AbstractContinuumAction.getProject( context );
<<<<<<< HEAD
    
            context.put( AbstractContinuumAction.KEY_WORKING_DIRECTORY,
                               workingDirectoryService.getWorkingDirectory( project ).getAbsolutePath() );
    
            performAction( "checkout-project", context );
    
            scmResult = AbstractContinuumAction.getCheckoutResult( context, null );
        }
    
        context.put( AbstractContinuumAction.KEY_SCM_RESULT, scmResult );
    }
    
    private boolean checkProjectScmRoot( Map context )
        throws TaskExecutionException
    {
        ProjectScmRoot projectScmRoot = AbstractContinuumAction.getProjectScmRoot( context );
        
        // check state of scm root
        if ( projectScmRoot.getState() == ContinuumProjectState.ERROR )
        {
            return false;
        }
        
        return true;
    }
    
    private void startPrepareBuild( Map context )
=======

            AbstractContinuumAction.setWorkingDirectory( context, workingDirectoryService.getWorkingDirectory(
                project ).getAbsolutePath() );

            List<Project> projectsWithCommonScmRoot = AbstractContinuumAction.getListOfProjectsInGroupWithCommonScmRoot(
                context );
            String projectScmRootUrl = AbstractContinuumAction.getProjectScmRootUrl( context, project.getScmUrl() );
            String workingDir = null;

            if ( rootProject.getId() == project.getId() )
            {
                workingDir = workingDirectoryService.getWorkingDirectory( project, false ).getAbsolutePath();

                if ( project.isCheckedOutInSingleDirectory() )
                {
                    File parentDir = new File( workingDir );

                    while ( !isRootDirectory( parentDir.getAbsolutePath(), project ) )
                    {
                        parentDir = parentDir.getParentFile();
                    }

                    if ( !parentDir.exists() )
                    {
                        workingDir = parentDir.getAbsolutePath();
                    }
                }
            }

            if ( workingDir == null || new File( workingDir ).exists() )
            {
                workingDir = workingDirectoryService.getWorkingDirectory( project, projectScmRootUrl,
                                                                          projectsWithCommonScmRoot ).getAbsolutePath();
            }

            AbstractContinuumAction.setWorkingDirectory( context, workingDir );

            if ( rootProject.getId() != project.getId() || ( rootProject.getId() == project.getId() && !isRootDirectory(
                workingDir, rootProject ) ) )
            {
                AbstractContinuumAction.setRootDirectory( context, false );
            }

            performAction( "checkout-project", context );

            scmResult = CheckoutProjectContinuumAction.getCheckoutScmResult( context, null );
        }

        // [CONTINUUM-2207] when returned scmResult is null, this causes a problem when building the project 
        if ( scmResult == null )
        {
            log.debug( "Returned ScmResult is null when updating the working directory" );
            scmResult = new ScmResult();
        }

        AbstractContinuumAction.setScmResult( context, scmResult );
    }

    private boolean checkProjectScmRoot( Map<String, Object> context )
        throws TaskExecutionException
    {
        ProjectScmRoot projectScmRoot = AbstractContinuumAction.getProjectScmRoot( context );

        // check state of scm root
        return projectScmRoot.getState() != ContinuumProjectState.ERROR;

    }

    private void startPrepareBuild( Map<String, Object> context )
>>>>>>> refs/remotes/apache/trunk
        throws TaskExecutionException
    {
        ProjectScmRoot projectScmRoot = AbstractContinuumAction.getProjectScmRoot( context );
        if ( projectScmRoot.getState() != ContinuumProjectState.UPDATING )
        {
            try
            {
                projectScmRoot.setOldState( projectScmRoot.getState() );
                projectScmRoot.setState( ContinuumProjectState.UPDATING );
                projectScmRootDao.updateProjectScmRoot( projectScmRoot );
            }
            catch ( ContinuumStoreException e )
            {
                throw new TaskExecutionException( "Error persisting projectScmRoot", e );
            }
        }
    }
<<<<<<< HEAD
    
    private void endPrepareBuild( Map context )
        throws TaskExecutionException
    {
        ProjectScmRoot projectScmRoot = AbstractContinuumAction.getProjectScmRoot( context );
        
=======

    private void endPrepareBuild( Map<String, Object> context )
        throws TaskExecutionException
    {
        ProjectScmRoot projectScmRoot = AbstractContinuumAction.getProjectScmRoot( context );

>>>>>>> refs/remotes/apache/trunk
        if ( projectScmRoot.getState() != ContinuumProjectState.ERROR )
        {
            projectScmRoot.setState( ContinuumProjectState.UPDATED );
            projectScmRoot.setError( null );
<<<<<<< HEAD
            
=======

>>>>>>> refs/remotes/apache/trunk
            try
            {
                projectScmRootDao.updateProjectScmRoot( projectScmRoot );
            }
            catch ( ContinuumStoreException e )
            {
                throw new TaskExecutionException( "Error persisting projectScmRoot", e );
            }
        }

        notifierDispatcher.prepareBuildComplete( projectScmRoot );
    }
<<<<<<< HEAD
    
    /**
     *  @param context
     * @throws TaskExecutionException
     */
    private void endProjectPrepareBuild( Map context )
        throws TaskExecutionException
    {
        ScmResult scmResult = AbstractContinuumAction.getScmResult( context, null );
        Project project = AbstractContinuumAction.getProject( context );
        
        if ( scmResult == null || !scmResult.isSuccess() )
        {
            String error = convertScmResultToError( scmResult );
            
            updateProjectScmRoot( context, error );
        }
        
        try
        {
            project.setScmResult( scmResult );
            
            projectDao.updateProject( project );
        }
        catch ( ContinuumStoreException e )
        {
            throw new TaskExecutionException( "Error storing the project", e );
        }
    }
    
=======

    /**
     * @param context
     * @throws TaskExecutionException
     */
    private void endProjectPrepareBuild( Map<String, Object> context )
        throws TaskExecutionException
    {
        ScmResult scmResult = AbstractContinuumAction.getScmResult( context, null );

        if ( scmResult == null || !scmResult.isSuccess() )
        {
            String error = convertScmResultToError( scmResult );

            updateProjectScmRoot( context, error );
        }
    }

    private ScmResult getOldScmResults( int projectId, long startId, long fromDate )
        throws ContinuumStoreException
    {
        List<BuildResult> results = buildResultDao.getBuildResultsForProjectFromId( projectId, startId );

        ScmResult res = new ScmResult();

        if ( results != null && results.size() > 0 )
        {
            for ( BuildResult result : results )
            {
                ScmResult scmResult = result.getScmResult();

                if ( scmResult != null )
                {
                    List<ChangeSet> changes = scmResult.getChanges();

                    if ( changes != null )
                    {
                        for ( ChangeSet changeSet : changes )
                        {
                            if ( changeSet.getDate() < fromDate )
                            {
                                continue;
                            }
                            if ( !res.getChanges().contains( changeSet ) )
                            {
                                res.addChange( changeSet );
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

>>>>>>> refs/remotes/apache/trunk
    /**
     * Merges scm results so we'll have all changes since last execution of current build definition
     *
     * @param context The build context
     */
<<<<<<< HEAD
    private void mergeScmResults( Map context )
    {
        ScmResult oldScmResult = AbstractContinuumAction.getOldScmResult( context, null );
=======
    private void mergeScmResults( Map<String, Object> context )
    {
        ScmResult oldScmResult = AbstractContinuumAction.getOldScmResult( context );
>>>>>>> refs/remotes/apache/trunk
        ScmResult newScmResult = AbstractContinuumAction.getScmResult( context, null );

        if ( oldScmResult != null )
        {
            if ( newScmResult == null )
            {
<<<<<<< HEAD
                context.put( AbstractContinuumAction.KEY_SCM_RESULT, oldScmResult );
=======
                AbstractContinuumAction.setScmResult( context, oldScmResult );
>>>>>>> refs/remotes/apache/trunk
            }
            else
            {
                List<ChangeSet> oldChanges = oldScmResult.getChanges();

                List<ChangeSet> newChanges = newScmResult.getChanges();

                for ( ChangeSet change : newChanges )
                {
                    if ( !oldChanges.contains( change ) )
                    {
                        oldChanges.add( change );
                    }
                }

                newScmResult.setChanges( oldChanges );
            }
        }
    }
<<<<<<< HEAD
    
    private void performAction( String actionName, Map context )
        throws TaskExecutionException
    {
        TaskExecutionException exception = null;

        try
        {
            getLogger().info( "Performing action " + actionName );
=======

    private void performAction( String actionName, Map<String, Object> context )
        throws TaskExecutionException
    {
        TaskExecutionException exception;

        try
        {
            log.info( "Performing action " + actionName );
>>>>>>> refs/remotes/apache/trunk
            actionManager.lookup( actionName ).execute( context );
            return;
        }
        catch ( ActionNotFoundException e )
        {
            exception = new TaskExecutionException( "Error looking up action '" + actionName + "'", e );
        }
        catch ( Exception e )
        {
            exception = new TaskExecutionException( "Error executing action '" + actionName + "'", e );
        }
<<<<<<< HEAD
        
        ScmResult result = new ScmResult();
        
        result.setSuccess( false );
        
        result.setException( ContinuumUtils.throwableToString( exception ) );
        
        context.put( AbstractContinuumAction.KEY_SCM_RESULT, result );
        
        throw exception;
    }
    
=======

        ScmResult result = new ScmResult();

        result.setSuccess( false );

        result.setException( ContinuumUtils.throwableToString( exception ) );

        AbstractContinuumAction.setScmResult( context, result );

        throw exception;
    }

>>>>>>> refs/remotes/apache/trunk
    private String convertScmResultToError( ScmResult result )
    {
        String error = "";

        if ( result == null )
        {
            error = "Scm result is null.";
        }
        else
        {
            if ( result.getCommandLine() != null )
            {
                error = "Command line: " + StringUtils.clean( result.getCommandLine() ) +
                    System.getProperty( "line.separator" );
            }

            if ( result.getProviderMessage() != null )
            {
                error = "Provider message: " + StringUtils.clean( result.getProviderMessage() ) +
                    System.getProperty( "line.separator" );
            }

            if ( result.getCommandOutput() != null )
            {
                error += "Command output: " + System.getProperty( "line.separator" );
                error += "-------------------------------------------------------------------------------" +
                    System.getProperty( "line.separator" );
                error += StringUtils.clean( result.getCommandOutput() ) + System.getProperty( "line.separator" );
                error += "-------------------------------------------------------------------------------" +
                    System.getProperty( "line.separator" );
            }

            if ( result.getException() != null )
            {
                error += "Exception:" + System.getProperty( "line.separator" );
                error += result.getException();
            }
        }

        return error;
    }
<<<<<<< HEAD
    
    private void updateProjectScmRoot( Map context, String error )
        throws TaskExecutionException
    {
        ProjectScmRoot projectScmRoot = AbstractContinuumAction.getProjectScmRoot( context );
        
=======

    private void updateProjectScmRoot( Map<String, Object> context, String error )
        throws TaskExecutionException
    {
        ProjectScmRoot projectScmRoot = AbstractContinuumAction.getProjectScmRoot( context );

>>>>>>> refs/remotes/apache/trunk
        try
        {
            projectScmRoot.setState( ContinuumProjectState.ERROR );
            projectScmRoot.setError( error );
<<<<<<< HEAD
            
            projectScmRootDao.updateProjectScmRoot( projectScmRoot );
            
            context.put( AbstractContinuumAction.KEY_PROJECT_SCM_ROOT, projectScmRoot );
=======

            projectScmRootDao.updateProjectScmRoot( projectScmRoot );

            AbstractContinuumAction.setProjectScmRoot( context, projectScmRoot );
>>>>>>> refs/remotes/apache/trunk
        }
        catch ( ContinuumStoreException e )
        {
            throw new TaskExecutionException( "Error storing project scm root", e );
        }
    }

<<<<<<< HEAD
    private void buildProjects( int projectGroupId, Map<Integer, Integer> projectsAndBuildDefinitionsMap, int trigger )
        throws TaskExecutionException
    {
        List<Project> projects = projectDao.getProjectsWithDependenciesByGroupId( projectGroupId );
        List<Project> projectList;
        
        try
        {
            projectList = ProjectSorter.getSortedProjects( projects, getLogger() );
        }
        catch ( CycleDetectedException e )
        {
            projectList = projectDao.getAllProjectsByName();
        }

        for ( Project project : projectList )
        {
            boolean shouldBuild = false;
            int buildDefinitionId = 0;
            
            if ( projectsAndBuildDefinitionsMap.get( project.getId() ) != null )
            {
                buildDefinitionId = projectsAndBuildDefinitionsMap.get( project.getId() );
                shouldBuild = true;
            }
            else if ( project.getState() == ContinuumProjectState.CHECKEDOUT || project.getState() == ContinuumProjectState.NEW ) //check if no build result yet for project
            {
                try
                {
                    //get default build definition for project
                    buildDefinitionId = buildDefinitionDao.getDefaultBuildDefinition( project.getId() ).getId();
                }
                catch ( ContinuumStoreException e )
                {
                    getLogger().error( "Error while creating build object", e );
                    throw new TaskExecutionException( "Error while creating build object", e );
                }
                shouldBuild = true;
            }

            if ( shouldBuild )
            {
                try
                {
                    Map context = new HashMap();
                    context.put( AbstractContinuumAction.KEY_PROJECT, project );
                    context.put( AbstractContinuumAction.KEY_BUILD_DEFINITION_ID, buildDefinitionId );
                    context.put( AbstractContinuumAction.KEY_TRIGGER, trigger );
                    
                    getLogger().info( "Performing action create-build-project-task" );
                    actionManager.lookup( "create-build-project-task" ).execute( context );
                }
                catch ( ActionNotFoundException e )
                {
                   getLogger().error( "Error looking up action 'build-project'" );
                   throw new TaskExecutionException( "Error looking up action 'build-project'", e );
                }
                catch ( Exception e )
                {
                    getLogger().error( e.getMessage(), e );
                    throw new TaskExecutionException( "Error executing action 'build-project'", e );
                }
            }
        }
=======
    private void buildProjects( int projectGroupId, List<Project> projectList,
                                Map<Integer, Integer> projectsAndBuildDefinitionsMap, BuildTrigger buildTrigger,
                                Map<Integer, ScmResult> scmResultMap )
        throws TaskExecutionException
    {
        List<Project> projectsToBeBuilt = new ArrayList<Project>();
        Map<Integer, BuildDefinition> projectsBuildDefinitionsMap = new HashMap<Integer, BuildDefinition>();

        for ( Project project : projectList )
        {
            int buildDefinitionId;

            if ( projectsAndBuildDefinitionsMap.get( project.getId() ) != null )
            {
                buildDefinitionId = projectsAndBuildDefinitionsMap.get( project.getId() );

                try
                {
                    BuildDefinition buildDefinition = buildDefinitionDao.getBuildDefinition( buildDefinitionId );
                    projectsBuildDefinitionsMap.put( project.getId(), buildDefinition );
                    projectsToBeBuilt.add( project );
                }
                catch ( ContinuumStoreException e )
                {
                    log.error( "Error while creating build object", e );
                    throw new TaskExecutionException( "Error while creating build object", e );
                }
            }
        }

        try
        {
            Map<String, Object> context = new HashMap<String, Object>();
            AbstractContinuumAction.setListOfProjects( context, projectsToBeBuilt );
            AbstractContinuumAction.setProjectsBuildDefinitionsMap( context, projectsBuildDefinitionsMap );
            AbstractContinuumAction.setBuildTrigger( context, buildTrigger );
            AbstractContinuumAction.setScmResultMap( context, scmResultMap );
            AbstractContinuumAction.setProjectGroupId( context, projectGroupId );

            log.info( "Performing action create-build-project-task" );
            actionManager.lookup( "create-build-project-task" ).execute( context );
        }
        catch ( ActionNotFoundException e )
        {
            log.error( "Error looking up action 'build-project'" );
            throw new TaskExecutionException( "Error looking up action 'build-project'", e );
        }
        catch ( Exception e )
        {
            log.error( e.getMessage(), e );
            throw new TaskExecutionException( "Error executing action 'build-project'", e );
        }
    }

    private boolean isRootDirectory( String workingDir, Project rootProject )
    {
        return workingDir.endsWith( Integer.toString( rootProject.getId() ) + System.getProperty(
            "line.separator" ) ) || workingDir.endsWith( Integer.toString( rootProject.getId() ) );
>>>>>>> refs/remotes/apache/trunk
    }
}
