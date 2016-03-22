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

import org.apache.commons.lang.StringUtils;
import org.apache.continuum.buildmanager.BuildsManager;
import org.apache.continuum.dao.ProjectDao;
<<<<<<< HEAD
import org.apache.continuum.taskqueue.manager.TaskQueueManager;
=======
import org.apache.maven.continuum.model.project.BuildDefinition;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.utils.WorkingDirectoryService;
<<<<<<< HEAD
=======
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
>>>>>>> refs/remotes/apache/trunk

import java.util.Map;

/**
 * @author <a href="mailto:trygvis@inamo.no">Trygve Laugst&oslash;l</a>
 */
@Component( role = org.codehaus.plexus.action.Action.class, hint = "add-project-to-checkout-queue" )
public class AddProjectToCheckOutQueueAction
    extends AbstractContinuumAction
{
    @Requirement
    private WorkingDirectoryService workingDirectoryService;

<<<<<<< HEAD
    /**
     * @plexus.requirement
     */
    private ProjectDao projectDao;

    /**
     * @plexus.requirement
     */
    private TaskQueueManager taskQueueManager;
    
    @SuppressWarnings("unchecked")
=======
    @Requirement
    private ProjectDao projectDao;

    @Requirement( hint = "parallel" )
    private BuildsManager parallelBuildsManager;

    @SuppressWarnings( "unchecked" )
>>>>>>> refs/remotes/apache/trunk
    public void execute( Map context )
        throws Exception
    {
        Project project = getProject( context, null );
        if ( project == null )
        {
            project = projectDao.getProject( getProjectId( context ) );
        }

        String scmUsername = project.getScmUsername();
        String scmPassword = project.getScmPassword();

        if ( scmUsername == null || StringUtils.isEmpty( scmUsername ) )
        {
            scmUsername = CheckoutProjectContinuumAction.getScmUsername( context, null );
        }

        if ( scmPassword == null || StringUtils.isEmpty( scmPassword ) )
        {
            scmPassword = CheckoutProjectContinuumAction.getScmPassword( context, null );
        }

        String scmRootUrl = getProjectScmRootUrl( context, null );

<<<<<<< HEAD
        taskQueueManager.getCheckoutQueue().put( checkOutTask );
=======
        BuildDefinition defaultBuildDefinition = getBuildDefinition( context );
        parallelBuildsManager.checkoutProject( project.getId(), project.getName(),
                                               workingDirectoryService.getWorkingDirectory( project ), scmRootUrl,
                                               scmUsername, scmPassword, defaultBuildDefinition,
                                               getListOfProjectsInGroupWithCommonScmRoot( context ) );
>>>>>>> refs/remotes/apache/trunk
    }
}
