package org.apache.maven.continuum;

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

import org.apache.continuum.buildagent.NoBuildAgentException;
import org.apache.continuum.buildagent.NoBuildAgentInGroupException;
import org.apache.continuum.builder.distributed.manager.DistributedBuildManager;
import org.apache.continuum.buildmanager.BuildsManager;
import org.apache.continuum.model.project.ProjectGroupSummary;
import org.apache.continuum.model.project.ProjectScmRoot;
import org.apache.continuum.model.release.ContinuumReleaseResult;
import org.apache.continuum.purge.ContinuumPurgeManager;
import org.apache.continuum.purge.PurgeConfigurationService;
import org.apache.continuum.release.distributed.manager.DistributedReleaseManager;
import org.apache.continuum.repository.RepositoryService;
import org.apache.continuum.taskqueue.manager.TaskQueueManager;
import org.apache.continuum.utils.build.BuildTrigger;
import org.apache.maven.continuum.builddefinition.BuildDefinitionService;
import org.apache.maven.continuum.configuration.ConfigurationService;
<<<<<<< HEAD
import org.apache.maven.continuum.execution.ContinuumBuildExecutor;
import org.apache.maven.continuum.key.GroupProjectKey;
import org.apache.maven.continuum.model.project.BuildDefinition;
=======
import org.apache.maven.continuum.installation.InstallationService;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildQueue;
import org.apache.maven.continuum.model.project.BuildResult;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.model.project.ProjectNotifier;
import org.apache.maven.continuum.model.project.Schedule;
<<<<<<< HEAD
import org.apache.maven.continuum.model.project.BuildResult;
=======
import org.apache.maven.continuum.model.scm.ChangeSet;
import org.apache.maven.continuum.profile.ProfileService;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.project.builder.ContinuumProjectBuildingResult;
import org.apache.maven.continuum.release.ContinuumReleaseManager;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:jason@maven.org">Jason van Zyl</a>
 * @author <a href="mailto:trygvis@inamo.no">Trygve Laugst&oslash;l</a>
 */
public interface Continuum
{
    String ROLE = Continuum.class.getName();

    // ----------------------------------------------------------------------
    // Project Groups
    // ----------------------------------------------------------------------

<<<<<<< HEAD
    public static final String DEFAULT_PROJECT_GROUP_GROUP_ID = "default";

    public ProjectGroup getProjectGroup( GroupProjectKey groupProjectKey )
=======
    public ProjectGroup getProjectGroup( int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    public List<ProjectGroup> getAllProjectGroupsWithBuildDetails();

    public List<ProjectGroup> getAllProjectGroups();

    public ProjectGroup getProjectGroupByProjectId( GroupProjectKey groupProjectKey )
        throws ContinuumException;

<<<<<<< HEAD
    public Collection getProjectsInGroup( GroupProjectKey groupProjectKey )
=======
    public Collection<Project> getProjectsInGroup( int projectGroupId )
        throws ContinuumException;

    public Collection<Project> getProjectsInGroupWithDependencies( int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    public void removeProjectGroup( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    public void addProjectGroup( ProjectGroup projectGroup )
        throws ContinuumException;
<<<<<<< HEAD
    
    public ProjectGroup getProjectGroupWithProjects( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    /**
     * @deprecated not in use
     * @param groupId
     * @return
     * @throws ContinuumException
     */
    public ProjectGroup getProjectGroupByGroupId( String groupId )
        throws ContinuumException;

    /**
     * @deprecated not in use
     * @param groupId
     * @return
     * @throws ContinuumException
     */
=======

    public ProjectGroup getProjectGroupWithProjects( int projectGroupId )
        throws ContinuumException;

    public ProjectGroup getProjectGroupWithBuildDetails( int projectGroupId )
        throws ContinuumException;

    public ProjectGroup getProjectGroupByGroupId( String groupId )
        throws ContinuumException;

>>>>>>> refs/remotes/apache/trunk
    public ProjectGroup getProjectGroupByGroupIdWithBuildDetails( String groupId )
        throws ContinuumException;

    public List<ProjectGroup> getAllProjectGroupsWithRepository( int repositoryId );

    // ----------------------------------------------------------------------
    // Project
    // ----------------------------------------------------------------------

    void removeProject( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    /**
<<<<<<< HEAD
     * @deprecate not in use
     * @param projectId
     * @throws ContinuumException
     */
=======
     * @param projectId
     * @throws ContinuumException
     * @deprecated
     */
    @Deprecated
>>>>>>> refs/remotes/apache/trunk
    void checkoutProject( int projectId )
        throws ContinuumException;


    Project getProject( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    Project getProjectWithBuildDetails( GroupProjectKey groupProjectKey )
        throws ContinuumException;

<<<<<<< HEAD
    /**
     * @deprecated validate usage and refactor or remove based on keys
     * @param start
     * @param end
     * @return
     */
    List getAllProjectsWithAllDetails( int start, int end );

    /**
     * @deprecated validate usage and refactor or removed based on keys
     * @param start
     * @param end
     * @return
     * @throws ContinuumException
     */
    Collection getAllProjects( int start, int end )
        throws ContinuumException;

    Collection getProjects()
=======
    Collection<Project> getProjects()
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    Collection<Project> getProjectsWithDependencies()
        throws ContinuumException;

    BuildResult getLatestBuildResultForProject( GroupProjectKey groupProjectKey );

    Map<Integer, BuildResult> getLatestBuildResults( int projectGroupId );

    Map<Integer, BuildResult> getBuildResultsInSuccess( int projectGroupId );

    Map<Integer, ProjectGroupSummary> getProjectsSummaryByGroups();

    // ----------------------------------------------------------------------
    // Building
    // ----------------------------------------------------------------------

<<<<<<< HEAD
    boolean isInBuildingQueue( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    boolean isInBuildingQueue( GroupProjectKey groupProjectKey, int buildDefinitionId )
        throws ContinuumException;

    boolean isInCheckoutQueue( GroupProjectKey groupProjectKey )
        throws ContinuumException;
=======
    /**
     * take a collection of projects and sort for order
     *
     * @param projects
     * @return
     */
    List<Project> getProjectsInBuildOrder( Collection<Project> projects );

    void buildProjects( String username )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

    void buildProjectsWithBuildDefinition( List<Project> projects, List<BuildDefinition> bds )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;
>>>>>>> refs/remotes/apache/trunk

    void buildProjectsWithBuildDefinition( List<Project> projects, int buildDefinitionId )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

    void buildProjects( BuildTrigger buildTrigger )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

    void buildProjects( Schedule schedule )
        throws ContinuumException;

    void buildProject( int projectId, String username )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

    void buildProject( int projectId, BuildTrigger buildTrigger )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

<<<<<<< HEAD
    void buildProject( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    void buildProject( GroupProjectKey groupProjectKey, int trigger )
        throws ContinuumException;

    void buildProject( GroupProjectKey groupProjectKey, int buildDefinitionId, int trigger )
        throws ContinuumException;

    public void buildProjectGroup( GroupProjectKey groupProjectKey )
        throws ContinuumException;
=======
    void buildProjectWithBuildDefinition( int projectId, int buildDefinitionId, BuildTrigger buildTrigger )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

    void buildProject( int projectId, int buildDefinitionId, BuildTrigger buildTrigger )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

    public void buildProjectGroup( int projectGroupId, BuildTrigger buildTrigger )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

    public void buildProjectGroupWithBuildDefinition( int projectGroupId, int buildDefinitionId,
                                                      BuildTrigger buildTrigger )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;
>>>>>>> refs/remotes/apache/trunk

    // ----------------------------------------------------------------------
    // Build information
    // ----------------------------------------------------------------------

    BuildResult getBuildResult( int buildId )
        throws ContinuumException;

<<<<<<< HEAD
    /**
     * @deprecated not in use
     * @param projectId
     * @param buildNumber
     * @return
     * @throws ContinuumException
     */
    BuildResult getBuildResultByBuildNumber( int projectId, int buildNumber )
        throws ContinuumException;


    String getBuildOutput( GroupProjectKey groupProjectKey, int buildId )
        throws ContinuumException;

    Collection getBuildResultsForProject( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    List getChangesSinceLastSuccess( GroupProjectKey groupProjectKey, int buildResultId )
=======
    String getBuildOutput( int projectId, int buildId )
        throws ContinuumException;

    long getNbBuildResultsForProject( int projectId );

    Collection<BuildResult> getBuildResultsForProject( int projectId, int offset, int length )
        throws ContinuumException;

    List<ChangeSet> getChangesSinceLastSuccess( int projectId, int buildResultId )
        throws ContinuumException;

    void removeBuildResult( int buildId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    List<BuildResult> getBuildResultsInRange( Collection<Integer> projectGroupId, Date fromDate, Date toDate, int state,
                                              String triggeredBy, int offset, int length );

    // ----------------------------------------------------------------------
    // Projects
    // ----------------------------------------------------------------------

    /**
     * Add a project to the list of building projects (ant, shell,...)
     *
<<<<<<< HEAD
     * @TODO fix for key based project addition
     *
     * @param project the project to add
     * @param executorId the id of an {@link ContinuumBuildExecutor}, eg. <code>ant</code> or <code>shell</code> 
=======
     * @param project        the project to add
     * @param executorId     the id of an {@link org.apache.maven.continuum.execution.ContinuumBuildExecutor}, eg. <code>ant</code> or <code>shell</code>
     * @param projectGroupId
     * @return id of the project
     * @throws ContinuumException
     */
    int addProject( Project project, String executorId, int projectGroupId )
        throws ContinuumException;

    /**
     * Add a project to the list of building projects (ant, shell,...)
     *
     * @param project        the project to add
     * @param executorId     the id of an {@link org.apache.maven.continuum.execution.ContinuumBuildExecutor}, eg. <code>ant</code> or <code>shell</code>
     * @param projectGroupId
>>>>>>> refs/remotes/apache/trunk
     * @return id of the project
     * @throws ContinuumException
     */
    int addProject( Project project, String executorId, int projectGroupId, int buildDefintionTemplateId )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
     * @param metadataUrl url of the pom.xml
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
     * @param metadataUrl   url of the pom.xml
     * @param checkProtocol check if the protocol is allowed, use false if the pom is uploaded
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl, boolean checkProtocol )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
     * @param metadataUrl    url of the pom.xml
     * @param projectGroupId id of the project group to use
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl, int projectGroupId )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
<<<<<<< HEAD
     * @param metadataUrl url of the pom.xml
     * @param groupProjectKey
     *@param checkProtocol check if the protocol is allowed, use false if the pom is uploaded @return a holder with the projects, project groups and errors occurred during the project adding
=======
     * @param metadataUrl    url of the pom.xml
     * @param projectGroupId id of the project group to use
     * @param checkProtocol  check if the protocol is allowed, use false if the pom is uploaded
     * @return a holder with the projects, project groups and errors occurred during the project adding
>>>>>>> refs/remotes/apache/trunk
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl, GroupProjectKey groupProjectKey, boolean checkProtocol )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
     * @param metadataUrl         url of the pom.xml
     * @param projectGroupId      id of the project group to use
     * @param checkProtocol       check if the protocol is allowed, use false if the pom is uploaded
     * @param useCredentialsCache whether to use cached scm account credentials or not
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl, int projectGroupId, boolean checkProtocol,
                                                       boolean useCredentialsCache )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
     * @param metadataUrl           url of the pom.xml
     * @param projectGroupId        id of the project group to use
     * @param checkProtocol         check if the protocol is allowed, use false if the pom is uploaded
     * @param useCredentialsCache   whether to use cached scm account credentials or not
     * @param loadRecursiveProjects if multi modules project record all projects (if false only root project added)
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    public ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl, int projectGroupId,
                                                              boolean checkProtocol, boolean useCredentialsCache,
                                                              boolean loadRecursiveProjects )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
     * @param metadataUrl               url of the pom.xml
     * @param projectGroupId            id of the project group to use
     * @param checkProtocol             check if the protocol is allowed, use false if the pom is uploaded
     * @param useCredentialsCache       whether to use cached scm account credentials or not
     * @param loadRecursiveProjects     if multi modules project record all projects (if false only root project added)
     * @param buildDefintionTemplateId  buildDefintionTemplateId
     * @param checkoutInSingleDirectory TODO
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    public ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl, int projectGroupId,
                                                              boolean checkProtocol, boolean useCredentialsCache,
                                                              boolean loadRecursiveProjects,
                                                              int buildDefintionTemplateId,
                                                              boolean checkoutInSingleDirectory )
        throws ContinuumException;

    /**
     * Add a Maven 1 project to the list of projects.
     *
<<<<<<< HEAD
     * @param metadataUrl url of the project.xml
     * @param groupProjectKey
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, GroupProjectKey groupProjectKey )
         throws ContinuumException;
=======
     * @param metadataUrl    url of the project.xml
     * @param projectGroupId id of the project group to use
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, int projectGroupId )
        throws ContinuumException;
>>>>>>> refs/remotes/apache/trunk

    /**
     * Add a Maven 1 project to the list of projects.
     *
<<<<<<< HEAD
     * @param metadataUrl url of the project.xml
     * @param groupProjectKey
     *@param checkProtocol check if the protocol is allowed, use false if the pom is uploaded @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, GroupProjectKey groupProjectKey, boolean checkProtocol )
         throws ContinuumException;
=======
     * @param metadataUrl    url of the project.xml
     * @param projectGroupId id of the project group to use
     * @param checkProtocol  check if the protocol is allowed, use false if the pom is uploaded
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, int projectGroupId, boolean checkProtocol )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
     * @param metadataUrl         url of the pom.xml
     * @param projectGroupId      id of the project group to use
     * @param checkProtocol       check if the protocol is allowed, use false if the pom is uploaded
     * @param useCredentialsCache whether to use cached scm account credentials or not
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, int projectGroupId, boolean checkProtocol,
                                                       boolean useCredentialsCache )
        throws ContinuumException;

    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, int projectGroupId, boolean checkProtocol,
                                                       boolean useCredentialsCache, int buildDefintionTemplateId )
        throws ContinuumException;
>>>>>>> refs/remotes/apache/trunk

    void updateProject( Project project )
        throws ContinuumException;

    void updateProjectGroup( ProjectGroup projectGroup )
        throws ContinuumException;

    Project getProjectWithCheckoutResult( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    Project getProjectWithAllDetails( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    Project getProjectWithBuilds( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    // ----------------------------------------------------------------------
    // Notification
    // ----------------------------------------------------------------------

    /**
     * Get a particular notifier.
     *
     * @param groupProjectKey
     * @param notifierId
     * @return
     * @throws ContinuumException
     */
    ProjectNotifier getNotifier( GroupProjectKey groupProjectKey, int notifierId )
        throws ContinuumException;

    /**
     * update the notifier and return the updated notifier
     *
     * @param groupProjectKey
     * @param notifier
     * @return
     * @throws ContinuumException
     */
    ProjectNotifier updateNotifier( GroupProjectKey groupProjectKey, ProjectNotifier notifier )
        throws ContinuumException;

    /**
     * Add a notifier.
     *
     * * if groupProjectKey has projectKey defined, add notifier to project
     * * otherwise add to project group for child project inheritence
     *
     * @param groupProjectKey
     * @param notifier
     * @return
     * @throws ContinuumException
     */
    ProjectNotifier addNotifier( GroupProjectKey groupProjectKey, ProjectNotifier notifier )
        throws ContinuumException;

    /**
     *
     * @param groupProjectKey
     * @param notifierId
     * @throws ContinuumException
     */
    void removeNotifier( GroupProjectKey groupProjectKey, int notifierId )
        throws ContinuumException;

    /*
    ProjectNotifier getGroupNotifier( GroupProjectKey groupProjectKey, int notifierId )
        throws ContinuumException;

    ProjectNotifier updateGroupNotifier( GroupProjectKey groupProjectKey, ProjectNotifier notifier )
        throws ContinuumException;

    ProjectNotifier addGroupNotifier( GroupProjectKey groupProjectKey, ProjectNotifier notifier )
        throws ContinuumException;

    void removeGroupNotifier( GroupProjectKey groupProjectKey, int notifierId )
        throws ContinuumException;
    */
    // ----------------------------------------------------------------------
    // Build Definition
    // ----------------------------------------------------------------------


    /**
     * Get the list of {@see BuildDefinitions} that are present based on the {@see GroupProjectKey}
     * being passed in.  If the groupProjectKey has a project key specified then return the list of
     * project level build definitions, otherwise return the project group lvl build definitions.
     *
     * @param groupProjectKey
     * @return
     * @throws ContinuumException
     */
<<<<<<< HEAD
    List getBuildDefinitions( GroupProjectKey groupProjectKey )
=======
    @Deprecated
    List<BuildDefinition> getBuildDefinitions( int projectId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    /**
     * Get a particular build definition from the group or project, context depending on contents of
     * the groupProjectKey.
     *
     * @param groupProjectKey
     * @param buildDefinitionId
     * @return
     * @throws ContinuumException
     */
<<<<<<< HEAD
    BuildDefinition getBuildDefinition( GroupProjectKey groupProjectKey, int buildDefinitionId )
=======
    @Deprecated
    BuildDefinition getBuildDefinition( int projectId, int buildDefinitionId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    /**
     *
     * @param groupProjectKey
     * @param buildDefinitionId
     * @throws ContinuumException
     */
<<<<<<< HEAD
    void removeBuildDefinition( GroupProjectKey groupProjectKey, int buildDefinitionId )
=======
    @Deprecated
    void removeBuildDefinition( int projectId, int buildDefinitionId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    /**
     * returns the build definition from either the project or the project group it is a part of
     *
     * @param buildDefinitionId
     * @return
     */
    BuildDefinition getBuildDefinition( int buildDefinitionId )
        throws ContinuumException;

    /**
     * returns the default build definition for the project
     * 1) if project has default build definition, return that
     * 2) otherwise return default build definition for parent project group
     *
     * @param groupProjectKey
     * @return default build definition for project or group
     * @throws ContinuumException
     */

    BuildDefinition getDefaultBuildDefinition( GroupProjectKey groupProjectKey )
        throws ContinuumException;
    /*

<<<<<<< HEAD
    BuildDefinition addBuildDefinitionToProject( GroupProjectKey groupProjectKey, BuildDefinition buildDefinition )
        throws ContinuumException;

    BuildDefinition addBuildDefinitionToProjectGroup( GroupProjectKey groupProjectKey, BuildDefinition buildDefinition )
        throws ContinuumException;    

    List getBuildDefinitionsForProject( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    List getBuildDefinitionsForProjectGroup( GroupProjectKey groupProjectKey )
=======
    public List<BuildDefinition> getDefaultBuildDefinitionsForProjectGroup( int projectGroupId )
        throws ContinuumException;

    BuildDefinition addBuildDefinitionToProject( int projectId, BuildDefinition buildDefinition )
        throws ContinuumException;

    BuildDefinition addBuildDefinitionToProjectGroup( int projectGroupId, BuildDefinition buildDefinition )
        throws ContinuumException;

    List<BuildDefinition> getBuildDefinitionsForProject( int projectId )
        throws ContinuumException;

    List<BuildDefinition> getBuildDefinitionsForProjectGroup( int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    void removeBuildDefinitionFromProject( GroupProjectKey groupProjectKey, int buildDefinitionId )
        throws ContinuumException;

    void removeBuildDefinitionFromProjectGroup( int projectGroupId, int buildDefinitionId )
        throws ContinuumException;

    BuildDefinition updateBuildDefinitionForProject( int projectId, BuildDefinition buildDefinition )
        throws ContinuumException;

    BuildDefinition updateBuildDefinitionForProjectGroup( int projectGroupId, BuildDefinition buildDefinition )
        throws ContinuumException;
<<<<<<< HEAD
    */
=======
>>>>>>> refs/remotes/apache/trunk

    // ----------------------------------------------------------------------
    // Schedule
    // ----------------------------------------------------------------------

    Schedule getScheduleByName( String scheduleName )
        throws ContinuumException;

    Schedule getSchedule( int id )
        throws ContinuumException;

    Collection<Schedule> getSchedules()
        throws ContinuumException;

    void addSchedule( Schedule schedule )
        throws ContinuumException;

    void updateSchedule( Schedule schedule )
        throws ContinuumException;

    void updateSchedule( int scheduleId, Map<String, String> configuration )
        throws ContinuumException;

    void removeSchedule( int scheduleId )
        throws ContinuumException;

    void activePurgeSchedule( Schedule schedule );

    void activeBuildDefinitionSchedule( Schedule schedule );

    // ----------------------------------------------------------------------
    // Working copy
    // ----------------------------------------------------------------------

    File getWorkingDirectory( GroupProjectKey groupProjectKey )
        throws ContinuumException;

    String getFileContent( GroupProjectKey groupProjectKey, String directory, String filename )
        throws ContinuumException;

<<<<<<< HEAD
    List getFiles( GroupProjectKey groupProjectKey, String currentDirectory )
=======
    List<File> getFiles( int projectId, String currentDirectory )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    ConfigurationService getConfiguration();

    void reloadConfiguration()
        throws ContinuumException;

    // ----------------------------------------------------------------------
    // Continuum Release
    // ----------------------------------------------------------------------
    ContinuumReleaseManager getReleaseManager();

    // ----------------------------------------------------------------------
    // Installation
    // ----------------------------------------------------------------------    

    InstallationService getInstallationService();

    ProfileService getProfileService();

    BuildDefinitionService getBuildDefinitionService();

    // ----------------------------------------------------------------------
    // Continuum Purge
    // ----------------------------------------------------------------------
    ContinuumPurgeManager getPurgeManager();

    PurgeConfigurationService getPurgeConfigurationService();

    // ----------------------------------------------------------------------
    // Repository Service
    // ----------------------------------------------------------------------
    RepositoryService getRepositoryService();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    List<ProjectScmRoot> getProjectScmRootByProjectGroup( int projectGroupId );

    ProjectScmRoot getProjectScmRoot( int projectScmRootId )
        throws ContinuumException;

    ProjectScmRoot getProjectScmRootByProject( int projectId )
        throws ContinuumException;

    ProjectScmRoot getProjectScmRootByProjectGroupAndScmRootAddress( int projectGroupId, String scmRootAddress )
        throws ContinuumException;

    // ----------------------------------------------------------------------
    // Task Queue Manager
    // ----------------------------------------------------------------------
    TaskQueueManager getTaskQueueManager();

    // ----------------------------------------------------------------------
    // Builds Manager
    // ----------------------------------------------------------------------
    BuildsManager getBuildsManager();

    // ----------------------------------------------------------------------
    // Build Queue
    // ----------------------------------------------------------------------

    BuildQueue addBuildQueue( BuildQueue buildQueue )
        throws ContinuumException;

    BuildQueue getBuildQueue( int buildQueueId )
        throws ContinuumException;

    BuildQueue getBuildQueueByName( String buildQueueName )
        throws ContinuumException;

    void removeBuildQueue( BuildQueue buildQueue )
        throws ContinuumException;

    BuildQueue storeBuildQueue( BuildQueue buildQueue )
        throws ContinuumException;

    List<BuildQueue> getAllBuildQueues()
        throws ContinuumException;

    public void startup()
        throws ContinuumException;

    ContinuumReleaseResult addContinuumReleaseResult( int projectId, String releaseId, String releaseType )
        throws ContinuumException;

    ContinuumReleaseResult addContinuumReleaseResult( ContinuumReleaseResult releaseResult )
        throws ContinuumException;

    void removeContinuumReleaseResult( int releaseResultId )
        throws ContinuumException;

    ContinuumReleaseResult getContinuumReleaseResult( int releaseResultId )
        throws ContinuumException;

    List<ContinuumReleaseResult> getContinuumReleaseResultsByProjectGroup( int projectGroupId );

    List<ContinuumReleaseResult> getAllContinuumReleaseResults();

    ContinuumReleaseResult getContinuumReleaseResult( int projectId, String releaseGoal, long startTime, long endTime )
        throws ContinuumException;

    String getReleaseOutput( int releaseResultId )
        throws ContinuumException;

    DistributedBuildManager getDistributedBuildManager();

    DistributedReleaseManager getDistributedReleaseManager();
}
