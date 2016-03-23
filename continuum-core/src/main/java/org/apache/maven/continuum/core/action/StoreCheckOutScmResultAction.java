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

import java.util.Map;

import org.apache.continuum.dao.ProjectDao;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.scm.ScmResult;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.taskqueue.execution.TaskExecutionException;

/**
 * @author <a href="mailto:trygvis@inamo.no">Trygve Laugst&oslash;l</a>
 */
@Component( role = org.codehaus.plexus.action.Action.class, hint = "store-checkout-scm-result" )
public class StoreCheckOutScmResultAction
    extends AbstractContinuumAction
{

    @Requirement
    private ProjectDao projectDao;

    public void execute( Map context )
        throws TaskExecutionException
    {
        try
        {
            // ----------------------------------------------------------------------
            //
            // ----------------------------------------------------------------------

<<<<<<< HEAD
            ScmResult scmResult = CheckoutProjectContinuumAction.getCheckoutResult( context, null );
=======
            ScmResult scmResult = CheckoutProjectContinuumAction.getCheckoutScmResult( context, null );
>>>>>>> refs/remotes/apache/trunk

            Project project = projectDao.getProject( getProjectId( context ) );

            project.setCheckoutResult( scmResult );

            projectDao.updateProject( project );
        }
        catch ( ContinuumStoreException e )
        {
            throw new TaskExecutionException( "Error while storing the checkout result.", e );
        }
    }
}
