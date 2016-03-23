package org.apache.maven.continuum.scm.queue;

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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.continuum.buildmanager.BuildsManager;
import org.apache.continuum.buildmanager.ParallelBuildsManager;
=======
import org.apache.continuum.buildmanager.BuildsManager;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.dao.ProjectScmRootDao;
import org.apache.continuum.model.project.ProjectScmRoot;
import org.apache.continuum.taskqueue.BuildProjectTask;
import org.apache.continuum.taskqueue.PrepareBuildProjectsTask;
<<<<<<< HEAD
import org.apache.continuum.utils.build.BuildTrigger;
=======
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.AbstractContinuumTest;
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.apache.maven.continuum.core.action.AbstractContinuumAction;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.apache.maven.continuum.project.builder.ContinuumProjectBuilder;
import org.apache.maven.continuum.project.builder.ContinuumProjectBuilderException;
import org.apache.maven.continuum.project.builder.ContinuumProjectBuildingResult;
import org.apache.maven.continuum.project.builder.maven.MavenTwoContinuumProjectBuilder;
import org.codehaus.plexus.action.ActionManager;
<<<<<<< HEAD
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.codehaus.plexus.taskqueue.execution.TaskQueueExecutor;
import org.codehaus.plexus.util.StringUtils;
=======
import org.codehaus.plexus.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
>>>>>>> refs/remotes/apache/trunk

public class PrepareBuildProjectsTaskExecutorTest
    extends AbstractContinuumTest
{
    private ContinuumProjectBuilder projectBuilder;

<<<<<<< HEAD
    private TaskQueue prepareBuildQueue;

    private TaskQueueExecutor prepareBuildTaskQueueExecutor;

=======
>>>>>>> refs/remotes/apache/trunk
    private ActionManager actionManager;

    private ProjectScmRootDao projectScmRootDao;

    private ConfigurationService configurationService;

    private BuildsManager buildsManager;

<<<<<<< HEAD
    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();

        projectBuilder =
            (ContinuumProjectBuilder) lookup( ContinuumProjectBuilder.ROLE, MavenTwoContinuumProjectBuilder.ID );

        prepareBuildQueue = (TaskQueue) lookup( TaskQueue.ROLE, "prepare-build-project" );

        prepareBuildTaskQueueExecutor = (TaskQueueExecutor) lookup( TaskQueueExecutor.ROLE, "prepare-build-project" );

        projectScmRootDao = (ProjectScmRootDao) lookup( ProjectScmRootDao.class.getName() );

        actionManager = (ActionManager) lookup( ActionManager.ROLE );

        configurationService =  (ConfigurationService ) lookup( "configurationService" );

        buildsManager = (ParallelBuildsManager) lookup( BuildsManager.class, "parallel" );
    }

=======
    @Before
    public void setUp()
        throws Exception
    {
        projectBuilder = lookup( ContinuumProjectBuilder.class, MavenTwoContinuumProjectBuilder.ID );
        projectScmRootDao = lookup( ProjectScmRootDao.class );
        actionManager = lookup( ActionManager.class );
        configurationService = (ConfigurationService) lookup( "configurationService" );
        buildsManager = lookup( BuildsManager.class, "parallel" );
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testCheckoutPrepareBuildMultiModuleProject()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/multi-module/pom.xml", false, false );

<<<<<<< HEAD
        this.prepareBuildQueue.put( task );

=======
>>>>>>> refs/remotes/apache/trunk
        List<Project> projects = getProjectDao().getProjectsInGroup( task.getProjectGroupId() );

        assertEquals( "failed to add all projects", 4, projects.size() );

        Project rootProject = getProjectDao().getProjectByName( "multi-module-parent" );
        Project moduleA = getProjectDao().getProjectByName( "module-A" );
        Project moduleB = getProjectDao().getProjectByName( "module-B" );
        Project moduleD = getProjectDao().getProjectByName( "module-D" );

<<<<<<< HEAD
        // wait while task finishes prepare build
        while( !prepareBuildQueue.getQueueSnapshot().isEmpty() || 
                        prepareBuildTaskQueueExecutor.getCurrentTask() != null )
        {
            Thread.sleep( 10 );
        }
=======
        buildsManager.prepareBuildProjects( task.getProjectsBuildDefinitionsMap(), task.getBuildTrigger(),
                                            task.getProjectGroupId(), task.getProjectGroupName(),
                                            task.getScmRootAddress(), task.getProjectScmRootId() );

        // wait while task finishes prepare build
        waitForPrepareBuildToFinish( task.getProjectGroupId(), task.getProjectScmRootId() );
>>>>>>> refs/remotes/apache/trunk

        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );

        File workingDir = configurationService.getWorkingDirectory();

<<<<<<< HEAD
        assertTrue( "checkout directory of project 'multi-module-parent' does not exist.", new File( workingDir, Integer.toString( rootProject.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-A' does not exist.", new File( workingDir, Integer.toString( moduleA.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-B' does not exist.", new File( workingDir, Integer.toString( moduleB.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-D' does not exist.", new File( workingDir, Integer.toString( moduleD.getId() ) ).exists() );
 
        assertTrue( "failed to checkout project 'multi-module-parent'", new File( workingDir, Integer.toString( rootProject.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-A'", new File( workingDir, Integer.toString( moduleA.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-B'", new File( workingDir, Integer.toString( moduleB.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-D'", new File( workingDir, Integer.toString( moduleD.getId() ) ).list().length > 0 );

        while( !buildsManager.getCurrentBuilds().isEmpty() ||
                        isAnyProjectInBuildQueue() )
        {
            Thread.sleep( 10 );
        }
    }

=======
        assertTrue( "checkout directory of project 'multi-module-parent' does not exist.", new File( workingDir,
                                                                                                     Integer.toString(
                                                                                                         rootProject.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-A' does not exist.", new File( workingDir, Integer.toString(
            moduleA.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-B' does not exist.", new File( workingDir, Integer.toString(
            moduleB.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-D' does not exist.", new File( workingDir, Integer.toString(
            moduleD.getId() ) ).exists() );

        assertTrue( "failed to checkout project 'multi-module-parent'", new File( workingDir, Integer.toString(
            rootProject.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-A'", new File( workingDir, Integer.toString(
            moduleA.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-B'", new File( workingDir, Integer.toString(
            moduleB.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-D'", new File( workingDir, Integer.toString(
            moduleD.getId() ) ).list().length > 0 );

        // wait while task finished build
        waitForBuildToFinish();
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testCheckoutPrepareBuildMultiModuleProjectFreshBuild()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/multi-module/pom.xml", false, true );

<<<<<<< HEAD
        this.prepareBuildQueue.put( task );

=======
>>>>>>> refs/remotes/apache/trunk
        List<Project> projects = getProjectDao().getProjectsInGroup( task.getProjectGroupId() );

        assertEquals( "failed to add all projects", 4, projects.size() );

        Project rootProject = getProjectDao().getProjectByName( "multi-module-parent" );
        Project moduleA = getProjectDao().getProjectByName( "module-A" );
        Project moduleB = getProjectDao().getProjectByName( "module-B" );
        Project moduleD = getProjectDao().getProjectByName( "module-D" );

<<<<<<< HEAD
        // wait while task finishes prepare build
        while( !prepareBuildQueue.getQueueSnapshot().isEmpty() || 
                        prepareBuildTaskQueueExecutor.getCurrentTask() != null )
        {
            Thread.sleep( 10 );
        }
=======
        buildsManager.prepareBuildProjects( task.getProjectsBuildDefinitionsMap(), task.getBuildTrigger(),
                                            task.getProjectGroupId(), task.getProjectGroupName(),
                                            task.getScmRootAddress(), task.getProjectScmRootId() );

        // wait while task finishes prepare build
        waitForPrepareBuildToFinish( task.getProjectGroupId(), task.getProjectScmRootId() );
>>>>>>> refs/remotes/apache/trunk

        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );

        File workingDir = configurationService.getWorkingDirectory();

<<<<<<< HEAD
        assertTrue( "checkout directory of project 'multi-module-parent' does not exist.", new File( workingDir, Integer.toString( rootProject.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-A' does not exist.", new File( workingDir, Integer.toString( moduleA.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-B' does not exist.", new File( workingDir, Integer.toString( moduleB.getId() ) ).exists() );
        
        assertTrue( "checkout directory of project 'module-D' does not exist.", new File( workingDir, Integer.toString( moduleD.getId() ) ).exists() );
 
        assertTrue( "failed to checkout project 'multi-module-parent'", new File( workingDir, Integer.toString( rootProject.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-A'", new File( workingDir, Integer.toString( moduleA.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-B'", new File( workingDir, Integer.toString( moduleB.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-D'", new File( workingDir, Integer.toString( moduleD.getId() ) ).list().length > 0 );
 
        while( !buildsManager.getCurrentBuilds().isEmpty() ||
                        isAnyProjectInBuildQueue() )
        {
            Thread.sleep( 10 );
        }
    }

=======
        assertTrue( "checkout directory of project 'multi-module-parent' does not exist.", new File( workingDir,
                                                                                                     Integer.toString(
                                                                                                         rootProject.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-A' does not exist.", new File( workingDir, Integer.toString(
            moduleA.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-B' does not exist.", new File( workingDir, Integer.toString(
            moduleB.getId() ) ).exists() );

        assertTrue( "checkout directory of project 'module-D' does not exist.", new File( workingDir, Integer.toString(
            moduleD.getId() ) ).exists() );

        assertTrue( "failed to checkout project 'multi-module-parent'", new File( workingDir, Integer.toString(
            rootProject.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-A'", new File( workingDir, Integer.toString(
            moduleA.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-B'", new File( workingDir, Integer.toString(
            moduleB.getId() ) ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-D'", new File( workingDir, Integer.toString(
            moduleD.getId() ) ).list().length > 0 );

        // wait while task finished build
        waitForBuildToFinish();
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testCheckoutPrepareBuildSingleCheckedoutMultiModuleProject()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/multi-module/pom.xml", true, false );

<<<<<<< HEAD
        this.prepareBuildQueue.put( task );

=======
>>>>>>> refs/remotes/apache/trunk
        List<Project> projects = getProjectDao().getProjectsInGroup( task.getProjectGroupId() );

        assertEquals( "failed to add all projects", 4, projects.size() );

        Project rootProject = getProjectDao().getProjectByName( "multi-module-parent" );

<<<<<<< HEAD
        // wait while task finishes prepare build
        while( !prepareBuildQueue.getQueueSnapshot().isEmpty() || 
                        prepareBuildTaskQueueExecutor.getCurrentTask() != null )
        {
            Thread.sleep( 10 );
        }
=======
        buildsManager.prepareBuildProjects( task.getProjectsBuildDefinitionsMap(), task.getBuildTrigger(),
                                            task.getProjectGroupId(), task.getProjectGroupName(),
                                            task.getScmRootAddress(), task.getProjectScmRootId() );

        // wait while task finishes prepare build
        waitForPrepareBuildToFinish( task.getProjectGroupId(), task.getProjectScmRootId() );
>>>>>>> refs/remotes/apache/trunk

        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );

<<<<<<< HEAD
        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString( rootProject.getId() ) );

        assertTrue( "checkout directory of project 'multi-module-parent' does not exist.", checkedOutDir.exists() );

        assertTrue( "module-A was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-A" ).exists() );

        assertTrue( "module-B was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-B" ).exists() );
       
        assertTrue( "module-D was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-C/module-D" ).exists() );

        assertTrue( "failed to checkout project 'multi-module-parent'", checkedOutDir.list().length > 0 );
   
            assertTrue( "failed to checkout project 'module-A'", new File( checkedOutDir, "module-A" ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-B'", new File( checkedOutDir, "module-B" ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-D'", new File( checkedOutDir, "module-C/module-D" ).list().length > 0 );

        while( !buildsManager.getCurrentBuilds().isEmpty() ||
                        isAnyProjectInBuildQueue() )
        {
            Thread.sleep( 10 );
        }
    }

=======
        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString(
            rootProject.getId() ) );

        assertTrue( "checkout directory of project 'multi-module-parent' does not exist.", checkedOutDir.exists() );

        assertTrue( "module-A was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-A" ).exists() );

        assertTrue( "module-B was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-B" ).exists() );

        assertTrue( "module-D was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-C/module-D" ).exists() );

        assertTrue( "failed to checkout project 'multi-module-parent'", checkedOutDir.list().length > 0 );

        assertTrue( "failed to checkout project 'module-A'", new File( checkedOutDir, "module-A" ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-B'", new File( checkedOutDir, "module-B" ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-D'", new File( checkedOutDir,
                                                                       "module-C/module-D" ).list().length > 0 );

        // wait while task finishes build
        waitForBuildToFinish();
    }

    @Test
>>>>>>> refs/remotes/apache/trunk
    public void testCheckoutPrepareBuildSingleCheckedoutMultiModuleProjectFreshBuild()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/multi-module/pom.xml", true, true );

<<<<<<< HEAD
        this.prepareBuildQueue.put( task );

=======
>>>>>>> refs/remotes/apache/trunk
        List<Project> projects = getProjectDao().getProjectsInGroup( task.getProjectGroupId() );

        assertEquals( "failed to add all projects", 4, projects.size() );

        Project rootProject = getProjectDao().getProjectByName( "multi-module-parent" );

<<<<<<< HEAD
        // wait while task finishes prepare build
        while( !prepareBuildQueue.getQueueSnapshot().isEmpty() || 
                        prepareBuildTaskQueueExecutor.getCurrentTask() != null )
        {
            Thread.sleep( 10 );
        }
=======
        buildsManager.prepareBuildProjects( task.getProjectsBuildDefinitionsMap(), task.getBuildTrigger(),
                                            task.getProjectGroupId(), task.getProjectGroupName(),
                                            task.getScmRootAddress(), task.getProjectScmRootId() );

        // wait while task finishes prepare build
        waitForPrepareBuildToFinish( task.getProjectGroupId(), task.getProjectScmRootId() );
>>>>>>> refs/remotes/apache/trunk

        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );

<<<<<<< HEAD
        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString( rootProject.getId() ) );

        assertTrue( "checkout directory of project 'multi-module-parent' does not exist.", checkedOutDir.exists() );

        assertTrue( "module-A was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-A" ).exists() );

        assertTrue( "module-B was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-B" ).exists() );
        
        assertTrue( "module-D was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-C/module-D" ).exists() );
=======
        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString(
            rootProject.getId() ) );

        assertTrue( "checkout directory of project 'multi-module-parent' does not exist.", checkedOutDir.exists() );

        assertTrue( "module-A was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-A" ).exists() );

        assertTrue( "module-B was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-B" ).exists() );

        assertTrue( "module-D was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-C/module-D" ).exists() );
>>>>>>> refs/remotes/apache/trunk

        assertTrue( "failed to checkout project 'multi-module-parent'", checkedOutDir.list().length > 0 );

        assertTrue( "failed to checkout project 'module-A'", new File( checkedOutDir, "module-A" ).list().length > 0 );

        assertTrue( "failed to checkout project 'module-B'", new File( checkedOutDir, "module-B" ).list().length > 0 );

<<<<<<< HEAD
        assertTrue( "failed to checkout project 'module-D'", new File( checkedOutDir, "module-C/module-D" ).list().length > 0 );

        while( !buildsManager.getCurrentBuilds().isEmpty() ||
                        isAnyProjectInBuildQueue() )
        {
            Thread.sleep( 10 );
        }
    }

    public void testCheckoutPrepareBuildSingleCheckoutFlatMultiModuleProject()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/flat-multi-module/parent-project/pom.xml", true, false );

        this.prepareBuildQueue.put( task );
=======
        assertTrue( "failed to checkout project 'module-D'", new File( checkedOutDir,
                                                                       "module-C/module-D" ).list().length > 0 );

        // wait while task finishes build
        waitForBuildToFinish();
    }

    @Test
    public void testCheckoutPrepareBuildSingleCheckoutFlatMultiModuleProject()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/flat-multi-module/parent-project/pom.xml", true,
                                                    false );
>>>>>>> refs/remotes/apache/trunk

        List<Project> projects = getProjectDao().getProjectsInGroup( task.getProjectGroupId() );

        assertEquals( "failed to add all projects", 4, projects.size() );
<<<<<<< HEAD
        
        Project rootProject = getProjectDao().getProjectByName( "parent-project" );

        // wait while task finishes prepare build
        while( !prepareBuildQueue.getQueueSnapshot().isEmpty() || 
                        prepareBuildTaskQueueExecutor.getCurrentTask() != null )
        {
            Thread.sleep( 10 );
        }
=======

        Project rootProject = getProjectDao().getProjectByName( "parent-project" );

        buildsManager.prepareBuildProjects( task.getProjectsBuildDefinitionsMap(), task.getBuildTrigger(),
                                            task.getProjectGroupId(), task.getProjectGroupName(),
                                            task.getScmRootAddress(), task.getProjectScmRootId() );

        // wait while task finishes prepare build
        waitForPrepareBuildToFinish( task.getProjectGroupId(), task.getProjectScmRootId() );
>>>>>>> refs/remotes/apache/trunk

        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );

<<<<<<< HEAD
        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString( rootProject.getId() ) );

        assertTrue( "checkout directory of project 'parent-project' does not exist.", new File( checkedOutDir, "parent-project" ).exists() );

        assertTrue( "module-a was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-a" ).exists() );

        assertTrue( "module-b was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-b" ).exists() );

        assertTrue( "module-d was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-c/module-d" ).exists() );

        assertTrue( "failed to checkout parent-project", new File( checkedOutDir, "parent-project" ).list().length > 0 );

        assertTrue( "failed to checkout module-a", new File( checkedOutDir, "module-a" ).list().length > 0 );
        
        assertTrue( "failed to checkout module-b", new File( checkedOutDir, "module-b" ).list().length > 0 );
        
        assertTrue( "failed to checkout module-d", new File( checkedOutDir, "module-c/module-d" ).list().length > 0 );

        while( !buildsManager.getCurrentBuilds().isEmpty() ||
                        isAnyProjectInBuildQueue() )
        {
            Thread.sleep( 10 );
        }
    }

    public void testCheckoutPrepareBuildSingleCheckoutFlatMultiModuleProjectBuildFresh()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/flat-multi-module/parent-project/pom.xml", true, true );

        this.prepareBuildQueue.put( task );
=======
        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString(
            rootProject.getId() ) );

        assertTrue( "checkout directory of project 'parent-project' does not exist.", new File( checkedOutDir,
                                                                                                "parent-project" ).exists() );

        assertTrue( "module-a was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-a" ).exists() );

        assertTrue( "module-b was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-b" ).exists() );

        assertTrue( "module-d was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-c/module-d" ).exists() );

        assertTrue( "failed to checkout parent-project", new File( checkedOutDir, "parent-project" ).list().length >
            0 );

        assertTrue( "failed to checkout module-a", new File( checkedOutDir, "module-a" ).list().length > 0 );

        assertTrue( "failed to checkout module-b", new File( checkedOutDir, "module-b" ).list().length > 0 );

        assertTrue( "failed to checkout module-d", new File( checkedOutDir, "module-c/module-d" ).list().length > 0 );

        // wait while task finishes build
        waitForPrepareBuildToFinish( task.getProjectGroupId(), task.getProjectScmRootId() );
    }

    @Test
    public void testCheckoutPrepareBuildSingleCheckoutFlatMultiModuleProjectBuildFresh()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/flat-multi-module/parent-project/pom.xml", true,
                                                    true );
>>>>>>> refs/remotes/apache/trunk

        List<Project> projects = getProjectDao().getProjectsInGroup( task.getProjectGroupId() );

        assertEquals( "failed to add all projects", 4, projects.size() );

        Project rootProject = getProjectDao().getProjectByName( "parent-project" );

<<<<<<< HEAD
        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        // wait while task finishes prepare build
        while( !prepareBuildQueue.getQueueSnapshot().isEmpty() || 
                        prepareBuildTaskQueueExecutor.getCurrentTask() != null || scmRoot.getState() == ContinuumProjectState.UPDATING )
        {
            Thread.sleep( 10 );

            scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        }

        scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );

        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString( rootProject.getId() ) );

        assertTrue( "checkout directory of project 'parent-project' does not exist.", new File( checkedOutDir, "parent-project" ).exists() );

        assertTrue( "module-a was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-a" ).exists() );

        assertTrue( "module-b was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-b" ).exists() );

        assertTrue( "module-d was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-c/module-d" ).exists() );

        assertTrue( "failed to checkout parent-project", new File( checkedOutDir, "parent-project" ).list().length > 0 );

        assertTrue( "failed to checkout module-a", new File( checkedOutDir, "module-a" ).list().length > 0 );
        
        assertTrue( "failed to checkout module-b", new File( checkedOutDir, "module-b" ).list().length > 0 );
       
        assertTrue( "failed to checkout module-d", new File( checkedOutDir, "module-c/module-d" ).list().length > 0 );

        while( !buildsManager.getCurrentBuilds().isEmpty() ||
                        isAnyProjectInBuildQueue() )
        {
            Thread.sleep( 10 );
        }
    }
    
    public void testCheckoutPrepareBuildSingleCheckoutFlatMultiModuleProjectBuildFreshAfterRemovingWorkingCopy()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/flat-multi-module/parent-project/pom.xml", true, true );
     
        List<Project> projects = getProjectDao().getProjectsInGroup( task.getProjectGroupId() );
     
        assertEquals( "failed to add all projects", 4, projects.size() );
    
        Project rootProject = getProjectDao().getProjectByName( "parent-project" );
    
        File rootProjectDir = new File( configurationService.getWorkingDirectory(), Integer.toString( rootProject.getId() ) );
        rootProjectDir = new File( rootProjectDir, "parent-project" );
    
       rootProject.setWorkingDirectory( rootProjectDir.getAbsolutePath() );
    
        getProjectDao().updateProject( rootProject );
   
        this.prepareBuildQueue.put( task );
    
        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        // wait while task finishes prepare build
        while( !prepareBuildQueue.getQueueSnapshot().isEmpty() || 
                        prepareBuildTaskQueueExecutor.getCurrentTask() != null || scmRoot.getState() == ContinuumProjectState.UPDATING )
        {
            Thread.sleep( 10 );

            scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        }
    
        scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );
    
        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString( rootProject.getId() ) );
    
        assertTrue( "checkout directory of project 'parent-project' does not exist.", new File( checkedOutDir, "parent-project" ).exists() );
    
        assertTrue( "module-a was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-a" ).exists() );
    
        assertTrue( "module-b was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-b" ).exists() );

        assertTrue( "module-d was not checked out in the same directory as it's parent.", new File( checkedOutDir, "module-c/module-d" ).exists() );

        assertTrue( "failed to checkout parent-project", new File( checkedOutDir, "parent-project" ).list().length > 0 );

        assertTrue( "failed to checkout module-a", new File( checkedOutDir, "module-a" ).list().length > 0 );
        
        assertTrue( "failed to checkout module-b", new File( checkedOutDir, "module-b" ).list().length > 0 );
        
        assertTrue( "failed to checkout module-d", new File( checkedOutDir, "module-c/module-d" ).list().length > 0 );

        while( !buildsManager.getCurrentBuilds().isEmpty() ||
                        isAnyProjectInBuildQueue() )
        {
            Thread.sleep( 10 );
        }
=======
        buildsManager.prepareBuildProjects( task.getProjectsBuildDefinitionsMap(), task.getBuildTrigger(),
                                            task.getProjectGroupId(), task.getProjectGroupName(),
                                            task.getScmRootAddress(), task.getProjectScmRootId() );

        // wait while task finishes prepare build
        waitForPrepareBuildToFinish( task.getProjectGroupId(), task.getProjectScmRootId() );

        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );

        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString(
            rootProject.getId() ) );

        assertTrue( "checkout directory of project 'parent-project' does not exist.", new File( checkedOutDir,
                                                                                                "parent-project" ).exists() );

        assertTrue( "module-a was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-a" ).exists() );

        assertTrue( "module-b was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-b" ).exists() );

        assertTrue( "module-d was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-c/module-d" ).exists() );

        assertTrue( "failed to checkout parent-project", new File( checkedOutDir, "parent-project" ).list().length >
            0 );

        assertTrue( "failed to checkout module-a", new File( checkedOutDir, "module-a" ).list().length > 0 );

        assertTrue( "failed to checkout module-b", new File( checkedOutDir, "module-b" ).list().length > 0 );

        assertTrue( "failed to checkout module-d", new File( checkedOutDir, "module-c/module-d" ).list().length > 0 );

        // wait while task finishes build
        waitForBuildToFinish();
    }

    @Test
    public void testCheckoutPrepareBuildSingleCheckoutFlatMultiModuleProjectBuildFreshAfterRemovingWorkingCopy()
        throws Exception
    {
        PrepareBuildProjectsTask task = createTask( "src/test-projects/flat-multi-module/parent-project/pom.xml", true,
                                                    true );

        List<Project> projects = getProjectDao().getProjectsInGroup( task.getProjectGroupId() );

        assertEquals( "failed to add all projects", 4, projects.size() );

        Project rootProject = getProjectDao().getProjectByName( "parent-project" );

        File rootProjectDir = new File( configurationService.getWorkingDirectory(), Integer.toString(
            rootProject.getId() ) );
        rootProjectDir = new File( rootProjectDir, "parent-project" );

        rootProject.setWorkingDirectory( rootProjectDir.getAbsolutePath() );

        getProjectDao().updateProject( rootProject );

        buildsManager.prepareBuildProjects( task.getProjectsBuildDefinitionsMap(), task.getBuildTrigger(),
                                            task.getProjectGroupId(), task.getProjectGroupName(),
                                            task.getScmRootAddress(), task.getProjectScmRootId() );

        // wait while task finishes prepare build
        waitForPrepareBuildToFinish( task.getProjectGroupId(), task.getProjectScmRootId() );

        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRoot( task.getProjectScmRootId() );
        assertEquals( "Failed to update multi-module project", ContinuumProjectState.UPDATED, scmRoot.getState() );

        File checkedOutDir = new File( configurationService.getWorkingDirectory(), Integer.toString(
            rootProject.getId() ) );

        assertTrue( "checkout directory of project 'parent-project' does not exist.", new File( checkedOutDir,
                                                                                                "parent-project" ).exists() );

        assertTrue( "module-a was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-a" ).exists() );

        assertTrue( "module-b was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-b" ).exists() );

        assertTrue( "module-d was not checked out in the same directory as it's parent.", new File( checkedOutDir,
                                                                                                    "module-c/module-d" ).exists() );

        assertTrue( "failed to checkout parent-project", new File( checkedOutDir, "parent-project" ).list().length >
            0 );

        assertTrue( "failed to checkout module-a", new File( checkedOutDir, "module-a" ).list().length > 0 );

        assertTrue( "failed to checkout module-b", new File( checkedOutDir, "module-b" ).list().length > 0 );

        assertTrue( "failed to checkout module-d", new File( checkedOutDir, "module-c/module-d" ).list().length > 0 );

        // wait while task finishes build
        waitForBuildToFinish();
>>>>>>> refs/remotes/apache/trunk
    }

    private PrepareBuildProjectsTask createTask( String pomResource, boolean singleCheckout, boolean buildFresh )
        throws Exception
    {
        ProjectGroup projectGroup = getProjectGroup( pomResource, singleCheckout );

        BuildDefinition buildDefinition = new BuildDefinition();
        buildDefinition.setId( 0 );
        buildDefinition.setGoals( "clean" );
        buildDefinition.setBuildFresh( buildFresh );
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        projectGroup.addBuildDefinition( buildDefinition );

        Map<String, Object> pgContext = new HashMap<String, Object>();

<<<<<<< HEAD
        AbstractContinuumAction.setWorkingDirectory( pgContext, configurationService.getWorkingDirectory().getAbsolutePath() );
=======
        AbstractContinuumAction.setWorkingDirectory( pgContext,
                                                     configurationService.getWorkingDirectory().getAbsolutePath() );
>>>>>>> refs/remotes/apache/trunk

        AbstractContinuumAction.setUnvalidatedProjectGroup( pgContext, projectGroup );

        actionManager.lookup( "validate-project-group" ).execute( pgContext );

        actionManager.lookup( "store-project-group" ).execute( pgContext );

        int projectGroupId = AbstractContinuumAction.getProjectGroupId( pgContext );

        projectGroup = getProjectGroupDao().getProjectGroupWithBuildDetailsByProjectGroupId( projectGroupId );

        String scmRootUrl = getScmRootUrl( projectGroup );

        assertNotNull( scmRootUrl );

        ProjectScmRoot scmRoot = getProjectScmRoot( projectGroup, scmRootUrl );

        assertNotNull( scmRoot );

        buildDefinition = (BuildDefinition) projectGroup.getBuildDefinitions().get( 0 );

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        Project rootProject = null;

        List<Project> projects = (List<Project>) projectGroup.getProjects();

        for ( Project project : projects )
        {
            assertFalse( project.getId() == 0 );
<<<<<<< HEAD
            
=======

>>>>>>> refs/remotes/apache/trunk
            map.put( project.getId(), buildDefinition.getId() );

            if ( rootProject == null || rootProject.getId() > project.getId() )
            {
                rootProject = project;
            }
        }

        assertEquals( 4, map.size() );
<<<<<<< HEAD
        PrepareBuildProjectsTask task = new PrepareBuildProjectsTask( map, new org.apache.continuum.utils.build.BuildTrigger( 1, "user" ),
                                                                               projectGroupId, projectGroup.getName(), 
                                                                               scmRoot.getScmRootAddress(), scmRoot.getId() );
=======
        PrepareBuildProjectsTask task = new PrepareBuildProjectsTask( map,
                                                                      new org.apache.continuum.utils.build.BuildTrigger(
                                                                          1, "user" ), projectGroupId,
                                                                      projectGroup.getName(),
                                                                      scmRoot.getScmRootAddress(), scmRoot.getId() );
>>>>>>> refs/remotes/apache/trunk

        return task;
    }

    private ProjectGroup getProjectGroup( String pomResource, boolean singleCheckout )
        throws ContinuumProjectBuilderException, IOException
    {
        File pom = getTestFile( pomResource );
<<<<<<< HEAD
    
        assertNotNull( "Can't find project " + pomResource, pom );

        //ContinuumProjectBuildingResult result = projectBuilder.buildProjectsFromMetadata( pom.toURL(), null, null, true );
        ContinuumProjectBuildingResult result = projectBuilder.buildProjectsFromMetadata( pom.toURL(), null, null, true, singleCheckout );

        // some assertions to make sure our expectations match. This is NOT
        // meant as a unit test for the projectbuilder!
    
        assertNotNull( "Project list not null", result.getProjects() );
    
        assertEquals( "#Projectgroups", 1, result.getProjectGroups().size() );
    
        ProjectGroup pg = result.getProjectGroups().get( 0 );
    
=======

        assertNotNull( "Can't find project " + pomResource, pom );

        //ContinuumProjectBuildingResult result = projectBuilder.buildProjectsFromMetadata( pom.toURL(), null, null, true );
        ContinuumProjectBuildingResult result = projectBuilder.buildProjectsFromMetadata( pom.toURL(), null, null, true,
                                                                                          singleCheckout );

        // some assertions to make sure our expectations match. This is NOT
        // meant as a unit test for the projectbuilder!

        assertNotNull( "Project list not null", result.getProjects() );

        assertEquals( "#Projectgroups", 1, result.getProjectGroups().size() );

        ProjectGroup pg = result.getProjectGroups().get( 0 );

>>>>>>> refs/remotes/apache/trunk
        if ( !result.getProjects().isEmpty() )
        {
            for ( Project p : result.getProjects() )
            {
                pg.addProject( p );
            }
        }

        return pg;
    }

<<<<<<< HEAD
    private ProjectScmRoot getProjectScmRoot ( ProjectGroup pg, String url )
=======
    private ProjectScmRoot getProjectScmRoot( ProjectGroup pg, String url )
>>>>>>> refs/remotes/apache/trunk
        throws Exception
    {
        if ( StringUtils.isEmpty( url ) )
        {
            return null;
        }

<<<<<<< HEAD
        ProjectScmRoot scmRoot =
            projectScmRootDao.getProjectScmRootByProjectGroupAndScmRootAddress( pg.getId(), url );
=======
        ProjectScmRoot scmRoot = projectScmRootDao.getProjectScmRootByProjectGroupAndScmRootAddress( pg.getId(), url );
>>>>>>> refs/remotes/apache/trunk

        if ( scmRoot != null )
        {
            return scmRoot;
        }

        ProjectScmRoot projectScmRoot = new ProjectScmRoot();

        projectScmRoot.setProjectGroup( pg );

        projectScmRoot.setScmRootAddress( url );

<<<<<<< HEAD
        //projectScmRoot.setState( ContinuumProjectState.ERROR );

=======
>>>>>>> refs/remotes/apache/trunk
        return projectScmRootDao.addProjectScmRoot( projectScmRoot );
    }

    private String getScmRootUrl( ProjectGroup pg )
    {
        String scmRootUrl = null;

<<<<<<< HEAD
        for ( Project project : (List<Project>) pg.getProjects() )
=======
        for ( Project project : pg.getProjects() )
>>>>>>> refs/remotes/apache/trunk
        {
            String scmUrl = project.getScmUrl();

            scmRootUrl = getCommonPath( scmUrl, scmRootUrl );
        }

        return scmRootUrl;
    }

    private String getCommonPath( String path1, String path2 )
    {
        if ( path2 == null || path2.equals( "" ) )
        {
            return path1;
        }
        else
        {
            int indexDiff = StringUtils.differenceAt( path1, path2 );
            return path1.substring( 0, indexDiff );
        }
    }

<<<<<<< HEAD
=======
    private void waitForPrepareBuildToFinish( int projectGroupId, int scmRootId )
        throws Exception
    {
        while ( buildsManager.isInPrepareBuildQueue( projectGroupId, scmRootId ) ||
            buildsManager.isProjectGroupCurrentlyPreparingBuild( projectGroupId, scmRootId ) )
        {
            Thread.sleep( 10 );
        }
    }

    private void waitForBuildToFinish()
        throws Exception
    {
        while ( buildsManager.isBuildInProgress() || isAnyProjectInBuildQueue() )
        {
            Thread.sleep( 10 );
        }
    }

>>>>>>> refs/remotes/apache/trunk
    private boolean isAnyProjectInBuildQueue()
        throws Exception
    {
        Map<String, List<BuildProjectTask>> buildTasks = buildsManager.getProjectsInBuildQueues();

        for ( String queue : buildTasks.keySet() )
        {
            if ( !buildTasks.get( queue ).isEmpty() )
            {
                return true;
            }
        }

        return false;
    }
}
