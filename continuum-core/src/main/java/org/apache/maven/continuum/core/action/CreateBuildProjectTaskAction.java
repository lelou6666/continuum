package org.apache.maven.continuum.core.action;

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
import java.util.Map;

import org.apache.continuum.dao.BuildDefinitionDao;
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.taskqueue.manager.TaskQueueManager;
import org.apache.maven.continuum.ContinuumException;
import org.apache.maven.continuum.buildqueue.BuildProjectTask;
=======
import org.apache.continuum.buildmanager.BuildsManager;
import org.apache.continuum.dao.ProjectDao;
import org.apache.continuum.utils.build.BuildTrigger;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.execution.ContinuumBuildExecutor;
import org.apache.maven.continuum.execution.manager.BuildExecutorManager;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.Project;
<<<<<<< HEAD
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.taskqueue.TaskQueueException;
import org.codehaus.plexus.util.StringUtils;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
 * @plexus.component role="org.codehaus.plexus.action.Action" role-hint="create-build-project-task"
 */
public class CreateBuildProjectTaskAction
    extends AbstractContinuumAction
{
    /**
     * @plexus.requirement
     */
    private TaskQueueManager taskQueueManager;

    /**
     * @plexus.requirement
     */
    private BuildExecutorManager executorManager;

    /**
     * @plexus.requirement
     */
    private ProjectDao projectDao;

    /**
     * @plexus.requirement
     */
    private BuildDefinitionDao buildDefinitionDao;
    
    public synchronized void execute( Map context )
        throws Exception
    {
        Project project = AbstractContinuumAction.getProject( context );
        int buildDefinitionId = AbstractContinuumAction.getBuildDefinitionId( context );
        int trigger = AbstractContinuumAction.getTrigger( context );
        
        if ( taskQueueManager.isInBuildingQueue( project.getId(), buildDefinitionId ) )
        {
            return;
        }

        if ( taskQueueManager.isInCheckoutQueue( project.getId() ) )
        {
            taskQueueManager.removeProjectFromCheckoutQueue( project.getId() );
        }
        
        try
        {
            if ( project.getState() != ContinuumProjectState.NEW &&
                project.getState() != ContinuumProjectState.CHECKEDOUT &&
                project.getState() != ContinuumProjectState.OK && project.getState() != ContinuumProjectState.FAILED &&
                project.getState() != ContinuumProjectState.ERROR )
            {
                ContinuumBuildExecutor executor = executorManager.getBuildExecutor( project.getExecutorId() );

                if ( executor.isBuilding( project ) || project.getState() == ContinuumProjectState.UPDATING )
                {
                    // project is building
                    getLogger().info( "Project '" + project.getName() + "' already being built." );

                    return;
                }
                else
                {
                    project.setOldState( project.getState() );

                    project.setState( ContinuumProjectState.ERROR );

                    projectDao.updateProject( project );

                    project = projectDao.getProject( project.getId() );
                }
            }
            else
            {
=======
import org.apache.maven.continuum.model.scm.ScmResult;
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
 */
@Component( role = org.codehaus.plexus.action.Action.class, hint = "create-build-project-task" )
public class CreateBuildProjectTaskAction
    extends AbstractContinuumAction
{
    @Requirement
    private BuildExecutorManager executorManager;

    @Requirement
    private ProjectDao projectDao;

    @Requirement( hint = "parallel" )
    private BuildsManager parallelBuildsManager;

    public synchronized void execute( Map context )
        throws Exception
    {
        List<Project> projects = AbstractContinuumAction.getListOfProjects( context );
        Map<Integer, BuildDefinition> projectsBuildDefinitionsMap =
            AbstractContinuumAction.getProjectsBuildDefinitionsMap( context );
        Map<Integer, ScmResult> scmResultMap = AbstractContinuumAction.getScmResultMap( context );
        List<Project> projectsToBeBuilt = new ArrayList<Project>();
        BuildTrigger buildTrigger = AbstractContinuumAction.getBuildTrigger( context );
        int projectGroupId = AbstractContinuumAction.getProjectGroupId( context );

        // update state of each project first
        for ( Project project : projects )
        {
            BuildDefinition buildDefinition = projectsBuildDefinitionsMap.get( project.getId() );

            if ( parallelBuildsManager.isInAnyBuildQueue( project.getId(), buildDefinition.getId() ) )
            {
                getLogger().info( "Project '" + project.getName() + "' is already in build queue." );
                continue;
            }

            if ( parallelBuildsManager.isInAnyCheckoutQueue( project.getId() ) )
            {
                parallelBuildsManager.removeProjectFromCheckoutQueue( project.getId() );
            }

            try
            {
                /**
                 * The following can (and probably should) be simplified to:
                 *
                 * If project is building in executor or its state is UPDATING:
                 *   * Skip the project and log it
                 *
                 * If project state is not in { NEW, CHECKEDOUT, OK, FAILED, ERROR }:
                 *   * Set the project's state to ERROR
                 *
                 * Lastly, record the project's original state and add it to the list of projects to build
                 */
                if ( project.getState() != ContinuumProjectState.NEW &&
                    project.getState() != ContinuumProjectState.CHECKEDOUT &&
                    project.getState() != ContinuumProjectState.OK &&
                    project.getState() != ContinuumProjectState.FAILED &&
                    project.getState() != ContinuumProjectState.ERROR )
                {
                    ContinuumBuildExecutor executor = executorManager.getBuildExecutor( project.getExecutorId() );

                    if ( executor.isBuilding( project ) || project.getState() == ContinuumProjectState.UPDATING )
                    {
                        // project is building
                        getLogger().info( "Project '" + project.getName() + "' already being built." );

                        continue;
                    }
                    else
                    {
                        project.setState( ContinuumProjectState.ERROR );
                    }
                }
>>>>>>> refs/remotes/apache/trunk
                project.setOldState( project.getState() );

                projectDao.updateProject( project );

                project = projectDao.getProject( project.getId() );
<<<<<<< HEAD
            }

            BuildDefinition buildDefinition = buildDefinitionDao.getBuildDefinition( buildDefinitionId );
            String buildDefinitionLabel = buildDefinition.getDescription();
            if ( StringUtils.isEmpty( buildDefinitionLabel ) )
            {
                buildDefinitionLabel = buildDefinition.getGoals();
            }

            getLogger().info( "Enqueuing '" + project.getName() + "' with build definition '" + buildDefinitionLabel +
                "' - id=" + buildDefinitionId + ")." );

            BuildProjectTask task = new BuildProjectTask( project.getId(), buildDefinitionId, trigger, project
                .getName(), buildDefinitionLabel );

            task.setMaxExecutionTime( buildDefinition.getSchedule()
                .getMaxJobExecutionTime() * 1000 );

            taskQueueManager.getBuildQueue().put( task );
        }
        catch ( ContinuumStoreException e )
        {
            getLogger().error( "Error while creating build object", e );
            throw new ContinuumException( "Error while creating build object.", e );
        }
        catch ( TaskQueueException e )
        {
            getLogger().error( "Error while enqueuing object", e );
            throw new ContinuumException( "Error while enqueuing object.", e );
        }
=======

                projectsToBeBuilt.add( project );
            }
            catch ( ContinuumStoreException e )
            {
                getLogger().error( "Error while creating build object", e );
            }
        }

        parallelBuildsManager.buildProjects( projectsToBeBuilt, projectsBuildDefinitionsMap, buildTrigger, scmResultMap,
                                             projectGroupId );
>>>>>>> refs/remotes/apache/trunk
    }
}
