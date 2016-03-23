package org.apache.continuum.dao;

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
=======
import org.apache.continuum.model.project.ProjectGroupSummary;
import org.apache.continuum.model.project.ProjectSummaryResult;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.store.ContinuumObjectNotFoundException;
import org.apache.maven.continuum.store.ContinuumStoreException;
<<<<<<< HEAD

=======
import org.codehaus.plexus.component.annotations.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> refs/remotes/apache/trunk
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 * @plexus.component role="org.apache.continuum.dao.ProjectDao"
 */
=======

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 */
@Repository( "projectDao" )
@Component( role = org.apache.continuum.dao.ProjectDao.class )
>>>>>>> refs/remotes/apache/trunk
public class ProjectDaoImpl
    extends AbstractDao
    implements ProjectDao
{
    public void removeProject( Project project )
    {
        removeObject( project );
    }

    public void updateProject( Project project )
        throws ContinuumStoreException
    {
        updateObject( project );
    }

    public Project getProject( int projectId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (Project) getObjectById( Project.class, projectId );
=======
        return getObjectById( Project.class, projectId );
>>>>>>> refs/remotes/apache/trunk
    }

    public Project getProject( String groupId, String artifactId, String version )
        throws ContinuumStoreException
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( Project.class, true );

            Query query = pm.newQuery( extent );

            query.declareImports( "import java.lang.String" );

            query.declareParameters( "String groupId, String artifactId, String version" );

            query.setFilter( "this.groupId == groupId && this.artifactId == artifactId && this.version == version" );

            Object[] params = new Object[3];
            params[0] = groupId;
            params[1] = artifactId;
            params[2] = version;

            Collection result = (Collection) query.executeWithArray( params );

            if ( result.size() == 0 )
            {
                tx.commit();

                return null;
            }

            Object object = pm.detachCopy( result.iterator().next() );

            tx.commit();

            return (Project) object;
        }
        finally
        {
            rollback( tx );
        }
    }

    public Project getProjectByName( String name )
        throws ContinuumStoreException
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( Project.class, true );

            Query query = pm.newQuery( extent );

            query.declareImports( "import java.lang.String" );

            query.declareParameters( "String name" );

            query.setFilter( "this.name == name" );

            Collection result = (Collection) query.execute( name );

            if ( result.size() == 0 )
            {
                tx.commit();

                return null;
            }

            Object object = pm.detachCopy( result.iterator().next() );

            tx.commit();

            return (Project) object;
        }
        finally
        {
            rollback( tx );
        }
    }

<<<<<<< HEAD
    // todo get this natively supported in the store
    public List<Project> getProjectsWithDependenciesByGroupId( int projectGroupId )
    {
        List<Project> allProjects =
            getAllObjectsDetached( Project.class, "name ascending", PROJECT_DEPENDENCIES_FETCH_GROUP );

        List<Project> groupProjects = new ArrayList<Project>();

        for ( Project project : allProjects )
        {
            if ( project.getProjectGroup().getId() == projectGroupId )
            {
                groupProjects.add( project );
            }
        }
        return groupProjects;
=======
    public List<Project> getProjectsWithDependenciesByGroupId( int projectGroupId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( Project.class, true );

            Query query = pm.newQuery( extent, "projectGroup.id == " + projectGroupId );

            pm.getFetchPlan().addGroup( PROJECT_DEPENDENCIES_FETCH_GROUP );
            List<Project> result = (List<Project>) query.execute();

            result = (List<Project>) pm.detachCopyAll( result );

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
>>>>>>> refs/remotes/apache/trunk
    }

    public Project getProjectWithBuilds( int projectId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (Project) getObjectById( Project.class, projectId, PROJECT_WITH_BUILDS_FETCH_GROUP );
=======
        return getObjectById( Project.class, projectId, PROJECT_WITH_BUILDS_FETCH_GROUP );
>>>>>>> refs/remotes/apache/trunk
    }

    public Project getProjectWithBuildDetails( int projectId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (Project) getObjectById( Project.class, projectId, PROJECT_BUILD_DETAILS_FETCH_GROUP );
=======
        return getObjectById( Project.class, projectId, PROJECT_BUILD_DETAILS_FETCH_GROUP );
>>>>>>> refs/remotes/apache/trunk
    }

    public Project getProjectWithCheckoutResult( int projectId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (Project) getObjectById( Project.class, projectId, PROJECT_WITH_CHECKOUT_RESULT_FETCH_GROUP );
=======
        return getObjectById( Project.class, projectId, PROJECT_WITH_CHECKOUT_RESULT_FETCH_GROUP );
>>>>>>> refs/remotes/apache/trunk
    }

    public List<Project> getProjectsInGroup( int projectGroupId )
        throws ContinuumStoreException
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( Project.class, true );

            Query query = pm.newQuery( extent, "projectGroup.id == " + projectGroupId );

            query.setOrdering( "name ascending" );

<<<<<<< HEAD
            List result = (List) query.execute();

            result = (List) pm.detachCopyAll( result );
=======
            List<Project> result = (List<Project>) query.execute();

            result = (List<Project>) pm.detachCopyAll( result );
>>>>>>> refs/remotes/apache/trunk

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
    }

    public List<Project> getProjectsInGroupWithDependencies( int projectGroupId )
        throws ContinuumStoreException
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( Project.class, true );

            Query query = pm.newQuery( extent, "projectGroup.id == " + projectGroupId );

            query.setOrdering( "name ascending" );

            pm.getFetchPlan().addGroup( PROJECT_DEPENDENCIES_FETCH_GROUP );

            pm.getFetchPlan().addGroup( PROJECTGROUP_PROJECTS_FETCH_GROUP );

<<<<<<< HEAD
            List result = (List) query.execute();

            result = (List) pm.detachCopyAll( result );
=======
            List<Project> result = (List<Project>) query.execute();

            result = (List<Project>) pm.detachCopyAll( result );
>>>>>>> refs/remotes/apache/trunk

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
    }

<<<<<<< HEAD

    public List<Project> getAllProjectsWithAllDetails()
    {
        return getAllObjectsDetached( Project.class, "name ascending", PROJECT_ALL_DETAILS_FETCH_GROUP );
    }

    public Project getProjectWithAllDetails( int projectId )
        throws ContinuumStoreException
    {
        return (Project) getObjectById( Project.class, projectId, PROJECT_ALL_DETAILS_FETCH_GROUP );
=======
    public Project getProjectWithAllDetails( int projectId )
        throws ContinuumStoreException
    {
        return getObjectById( Project.class, projectId, PROJECT_ALL_DETAILS_FETCH_GROUP );
>>>>>>> refs/remotes/apache/trunk
    }

    public List<Project> getAllProjectsByName()
    {
        return getAllObjectsDetached( Project.class, "name ascending", null );
    }

<<<<<<< HEAD

=======
>>>>>>> refs/remotes/apache/trunk
    public List<Project> getAllProjectsByNameWithDependencies()
    {
        return getAllObjectsDetached( Project.class, "name ascending", PROJECT_DEPENDENCIES_FETCH_GROUP );
    }

    public List<Project> getAllProjectsByNameWithBuildDetails()
    {
        return getAllObjectsDetached( Project.class, "name ascending", PROJECT_BUILD_DETAILS_FETCH_GROUP );
    }

    public ProjectGroup getProjectGroupByProjectId( int projectId )
        throws ContinuumObjectNotFoundException
    {
        try
        {
            return getProject( projectId ).getProjectGroup();
        }
        catch ( ContinuumStoreException e )
        {
            throw new ContinuumObjectNotFoundException(
                "unable to find project group containing project with id: " + projectId );

        }
    }
<<<<<<< HEAD
=======

    public Project getProjectWithDependencies( int projectId )
        throws ContinuumStoreException
    {
        return getObjectById( Project.class, projectId, PROJECT_DEPENDENCIES_FETCH_GROUP );
    }

    public Map<Integer, ProjectGroupSummary> getProjectsSummary()
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( Project.class );

            Query query = pm.newQuery( extent );

            query.setResult( "projectGroup.id as projectGroupId, state as projectState, count(state) as size" );

            query.setResultClass( ProjectSummaryResult.class );

            query.setGrouping( "projectGroup.id, state" );

            List<ProjectSummaryResult> results = (List<ProjectSummaryResult>) query.execute();

            Map<Integer, ProjectGroupSummary> summaries = processProjectGroupSummary( results );

            tx.commit();

            return summaries;
        }
        finally
        {
            rollback( tx );
        }
    }

    private Map<Integer, ProjectGroupSummary> processProjectGroupSummary( List<ProjectSummaryResult> results )
    {
        Map<Integer, ProjectGroupSummary> map = new HashMap<Integer, ProjectGroupSummary>();

        for ( ProjectSummaryResult result : results )
        {
            ProjectGroupSummary summary;
            int projectGroupId = result.getProjectGroupId();
            int size = new Long( result.getSize() ).intValue();
            int state = result.getProjectState();

            if ( map.containsKey( projectGroupId ) )
            {
                summary = map.get( projectGroupId );
            }
            else
            {
                summary = new ProjectGroupSummary( projectGroupId );
            }

            summary.addProjects( size );

            if ( state == 2 )
            {
                summary.addNumberOfSuccesses( size );
            }
            else if ( state == 3 )
            {
                summary.addNumberOfFailures( size );
            }
            else if ( state == 4 )
            {
                summary.addNumberOfErrors( size );
            }

            map.put( projectGroupId, summary );
        }
        return map;
    }
>>>>>>> refs/remotes/apache/trunk
}
