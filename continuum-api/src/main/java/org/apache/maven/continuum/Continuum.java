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
import org.apache.maven.continuum.installation.InstallationService;
import org.apache.maven.continuum.model.project.BuildDefinition;
import org.apache.maven.continuum.model.project.BuildQueue;
import org.apache.maven.continuum.model.project.BuildResult;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.model.project.ProjectNotifier;
import org.apache.maven.continuum.model.project.Schedule;
import org.apache.maven.continuum.model.scm.ChangeSet;
import org.apache.maven.continuum.profile.ProfileService;
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

    public ProjectGroup getProjectGroup( long projectGroupId )
=======
    public ProjectGroup getProjectGroup( int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    public List<ProjectGroup> getAllProjectGroupsWithBuildDetails();

    public List<ProjectGroup> getAllProjectGroups();

    public ProjectGroup getProjectGroupByProjectId( long projectId )
        throws ContinuumException;

<<<<<<< HEAD
    public Collection getProjectsInGroup( long projectGroupId )
=======
    public Collection<Project> getProjectsInGroup( int projectGroupId )
        throws ContinuumException;

    public Collection<Project> getProjectsInGroupWithDependencies( int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    public void removeProjectGroup( long projectGroupId )
        throws ContinuumException;

    public void addProjectGroup( ProjectGroup projectGroup )
        throws ContinuumException;

    public ProjectGroup getProjectGroupWithProjects( long projectGroupId )
        throws ContinuumException;

    public ProjectGroup getProjectGroupWithBuildDetails( int projectGroupId )
        throws ContinuumException;

    public ProjectGroup getProjectGroupByGroupId( String groupId )
        throws ContinuumException;

    public ProjectGroup getProjectGroupByGroupIdWithBuildDetails( String groupId )
        throws ContinuumException;

    public List<ProjectGroup> getAllProjectGroupsWithRepository( int repositoryId );

    // ----------------------------------------------------------------------
    // Project
    // ----------------------------------------------------------------------

    void removeProject( long projectId )
        throws ContinuumException;

<<<<<<< HEAD
    void checkoutProject( long projectId )
=======
    /**
     * @param projectId
     * @throws ContinuumException
     * @deprecated
     */
    @Deprecated
    void checkoutProject( int projectId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    Project getProject( long projectId )
        throws ContinuumException;

    Project getProjectWithBuildDetails( long projectId )
        throws ContinuumException;

    Collection<Project> getProjects()
        throws ContinuumException;

    Collection<Project> getProjectsWithDependencies()
        throws ContinuumException;

    BuildResult getLatestBuildResultForProject( long projectId );

    Map<Integer, BuildResult> getLatestBuildResults( int projectGroupId );

    Map<Integer, BuildResult> getBuildResultsInSuccess( int projectGroupId );

<<<<<<< HEAD
    // ----------------------------------------------------------------------
    // Queues
    // ----------------------------------------------------------------------

    boolean isInBuildingQueue( long projectId )
        throws ContinuumException;

    boolean isInBuildingQueue( long projectId, long buildDefinitionId )
        throws ContinuumException;

    boolean isInCheckoutQueue( long projectId )
        throws ContinuumException;
=======
    Map<Integer, ProjectGroupSummary> getProjectsSummaryByGroups();
>>>>>>> refs/remotes/apache/trunk

    // ----------------------------------------------------------------------
    // Building
    // ----------------------------------------------------------------------

    /**
     * take a collection of projects and sort for order
     *
     * @param projects
     * @return
     */
    List<Project> getProjectsInBuildOrder( Collection<Project> projects );

    void buildProjects( String username )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

<<<<<<< HEAD
    void buildProjectsWithBuildDefinition( long buildDefinitionId )
        throws ContinuumException;
=======
    void buildProjectsWithBuildDefinition( List<Project> projects, List<BuildDefinition> bds )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;
>>>>>>> refs/remotes/apache/trunk

    void buildProjectsWithBuildDefinition( List<Project> projects, int buildDefinitionId )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

<<<<<<< HEAD
    void buildProjects( int trigger, long buildDefinitionId )
        throws ContinuumException;
=======
    void buildProjects( BuildTrigger buildTrigger )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;
>>>>>>> refs/remotes/apache/trunk

    void buildProjects( Schedule schedule )
        throws ContinuumException;

<<<<<<< HEAD
    void buildProject( long projectId )
        throws ContinuumException;

    void buildProject( long projectId, int trigger )
        throws ContinuumException;

    void buildProjectWithBuildDefinition( long projectId, long buildDefinitionId )
        throws ContinuumException;

    void buildProject( long projectId, long buildDefinitionId, int trigger )
        throws ContinuumException;

    public void buildProjectGroup( long projectGroupId )
        throws ContinuumException;
=======
    void buildProject( int projectId, String username )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

    void buildProject( int projectId, BuildTrigger buildTrigger )
        throws ContinuumException, NoBuildAgentException, NoBuildAgentInGroupException;

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

    BuildResult getBuildResult( long buildId )
        throws ContinuumException;

<<<<<<< HEAD
    BuildResult getBuildResultByBuildNumber( long projectId, long buildNumber )
        throws ContinuumException;

    String getBuildOutput( long projectId, long buildId )
        throws ContinuumException;

    Collection getBuildResultsForProject( long projectId )
        throws ContinuumException;

    List getChangesSinceLastSuccess( long projectId, long buildResultId )
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
     * @param project        the project to add
     * @param executorId     the id of an {@link org.apache.maven.continuum.execution.ContinuumBuildExecutor}, eg. <code>ant</code> or <code>shell</code>
     * @param projectGroupId
     * @return id of the project
     * @throws ContinuumException
     */
<<<<<<< HEAD
    long addProject( Project project, String executorId )
=======
    int addProject( Project project, String executorId, int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    /**
     * Add a project to the list of building projects (ant, shell,...)
     *
     * @param project        the project to add
     * @param executorId     the id of an {@link org.apache.maven.continuum.execution.ContinuumBuildExecutor}, eg. <code>ant</code> or <code>shell</code>
     * @param projectGroupId
     * @return id of the project
     * @throws ContinuumException
     */
<<<<<<< HEAD
    long addProject( Project project, String executorId, long projectGroupId )
=======
    int addProject( Project project, String executorId, int projectGroupId, int buildDefintionTemplateId )
>>>>>>> refs/remotes/apache/trunk
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
    ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl, long projectGroupId )
        throws ContinuumException;

    /**
     * Add a Maven 2 project to the list of projects.
     *
     * @param metadataUrl    url of the pom.xml
     * @param projectGroupId id of the project group to use
     * @param checkProtocol  check if the protocol is allowed, use false if the pom is uploaded
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
    ContinuumProjectBuildingResult addMavenTwoProject( String metadataUrl, long projectGroupId, boolean checkProtocol )
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
     * @param metadataUrl    url of the project.xml
     * @param projectGroupId id of the project group to use
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
<<<<<<< HEAD
    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, long projectGroupId )
         throws ContinuumException;
=======
    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, int projectGroupId )
        throws ContinuumException;
>>>>>>> refs/remotes/apache/trunk

    /**
     * Add a Maven 1 project to the list of projects.
     *
     * @param metadataUrl    url of the project.xml
     * @param projectGroupId id of the project group to use
     * @param checkProtocol  check if the protocol is allowed, use false if the pom is uploaded
     * @return a holder with the projects, project groups and errors occurred during the project adding
     * @throws ContinuumException
     */
<<<<<<< HEAD
    ContinuumProjectBuildingResult addMavenOneProject( String metadataUrl, long projectGroupId, boolean checkProtocol )
         throws ContinuumException;
=======
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

    Project getProjectWithCheckoutResult( long projectId )
        throws ContinuumException;

    Project getProjectWithAllDetails( long projectId )
        throws ContinuumException;

    Project getProjectWithBuilds( long projectId )
        throws ContinuumException;

    // ----------------------------------------------------------------------
    // Notification
    // ----------------------------------------------------------------------

    ProjectNotifier getNotifier( long projectId, long notifierId )
        throws ContinuumException;

    ProjectNotifier updateNotifier( long projectId, ProjectNotifier notifier )
        throws ContinuumException;

    ProjectNotifier addNotifier( long projectId, ProjectNotifier notifier )
        throws ContinuumException;

    void removeNotifier( long projectId, long notifierId )
        throws ContinuumException;

    ProjectNotifier getGroupNotifier( long projectGroupId, long notifierId )
        throws ContinuumException;

    ProjectNotifier updateGroupNotifier( long projectGroupId, ProjectNotifier notifier )
        throws ContinuumException;

    ProjectNotifier addGroupNotifier( long projectGroupId, ProjectNotifier notifier )
        throws ContinuumException;

    void removeGroupNotifier( long projectGroupId, long notifierId )
        throws ContinuumException;

    // ----------------------------------------------------------------------
    // Build Definition
    // ----------------------------------------------------------------------

    /**
     * @deprecated
     */
<<<<<<< HEAD
    List getBuildDefinitions( long projectId )
=======
    @Deprecated
    List<BuildDefinition> getBuildDefinitions( int projectId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    /**
     * @deprecated
     */
<<<<<<< HEAD
    BuildDefinition getBuildDefinition( long projectId, long buildDefinitionId )
=======
    @Deprecated
    BuildDefinition getBuildDefinition( int projectId, int buildDefinitionId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    /**
     * @deprecated
     */
<<<<<<< HEAD
    void removeBuildDefinition( long projectId, long buildDefinitionId )
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
    BuildDefinition getBuildDefinition( long buildDefinitionId )
        throws ContinuumException;

    /**
     * returns the default build definition for the project
     * 1) if project has default build definition, return that
     * 2) otherwise return default build definition for parent project group
     *
     * @param projectId
     * @return
     * @throws ContinuumException
     */
    BuildDefinition getDefaultBuildDefinition( long projectId )
        throws ContinuumException;

<<<<<<< HEAD
    BuildDefinition addBuildDefinitionToProject( long projectId, BuildDefinition buildDefinition )
=======
    public List<BuildDefinition> getDefaultBuildDefinitionsForProjectGroup( int projectGroupId )
        throws ContinuumException;

    BuildDefinition addBuildDefinitionToProject( int projectId, BuildDefinition buildDefinition )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    BuildDefinition addBuildDefinitionToProjectGroup( long projectGroupId, BuildDefinition buildDefinition )
        throws ContinuumException;

<<<<<<< HEAD
    List getBuildDefinitionsForProject( long projectId )
        throws ContinuumException;

    List getBuildDefinitionsForProjectGroup( long projectGroupId )
=======
    List<BuildDefinition> getBuildDefinitionsForProject( int projectId )
        throws ContinuumException;

    List<BuildDefinition> getBuildDefinitionsForProjectGroup( int projectGroupId )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    void removeBuildDefinitionFromProject( long projectId, long buildDefinitionId )
        throws ContinuumException;

    void removeBuildDefinitionFromProjectGroup( long projectGroupId, long buildDefinitionId )
        throws ContinuumException;

    BuildDefinition updateBuildDefinitionForProject( long projectId, BuildDefinition buildDefinition )
        throws ContinuumException;

    BuildDefinition updateBuildDefinitionForProjectGroup( long projectGroupId, BuildDefinition buildDefinition )
        throws ContinuumException;

    // ----------------------------------------------------------------------
    // Schedule
    // ----------------------------------------------------------------------

<<<<<<< HEAD
    Schedule getSchedule( long id )
=======
    Schedule getScheduleByName( String scheduleName )
        throws ContinuumException;

    Schedule getSchedule( int id )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    Collection<Schedule> getSchedules()
        throws ContinuumException;

    void addSchedule( Schedule schedule )
        throws ContinuumException;

    void updateSchedule( Schedule schedule )
        throws ContinuumException;

<<<<<<< HEAD
    void updateSchedule( long scheduleId, Map configuration )
=======
    void updateSchedule( int scheduleId, Map<String, String> configuration )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumException;

    void removeSchedule( long scheduleId )
        throws ContinuumException;

    void activePurgeSchedule( Schedule schedule );

    void activeBuildDefinitionSchedule( Schedule schedule );

    // ----------------------------------------------------------------------
    // Working copy
    // ----------------------------------------------------------------------

    File getWorkingDirectory( long projectId )
        throws ContinuumException;

    String getFileContent( long projectId, String directory, String filename )
        throws ContinuumException;

    List<File> getFiles( int projectId, String currentDirectory )
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
