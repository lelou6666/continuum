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
import org.apache.maven.continuum.model.project.BuildDefinition;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.store.ContinuumObjectNotFoundException;
import org.apache.maven.continuum.store.ContinuumStoreException;
<<<<<<< HEAD
import org.codehaus.plexus.jdo.PlexusJdoUtils;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 * @plexus.component role="org.apache.continuum.dao.ProjectGroupDao"
 */
=======
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.jdo.PlexusJdoUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 */
@Repository( "projectGroupDao" )
@Component( role = org.apache.continuum.dao.ProjectGroupDao.class )
>>>>>>> refs/remotes/apache/trunk
public class ProjectGroupDaoImpl
    extends AbstractDao
    implements ProjectGroupDao
{
<<<<<<< HEAD
    /**
     * @plexus.requirement role=org.apache.continuum.dao.ProjectDao"
     */
=======

    @Resource
    @Requirement( role = org.apache.continuum.dao.ProjectDao.class )
>>>>>>> refs/remotes/apache/trunk
    private ProjectDao projectDao;

    public ProjectGroup addProjectGroup( ProjectGroup group )
    {
<<<<<<< HEAD
        return (ProjectGroup) addObject( group );
=======
        return addObject( group );
>>>>>>> refs/remotes/apache/trunk
    }

    public void removeProjectGroup( ProjectGroup projectGroup )
    {
        ProjectGroup pg = null;
        try
        {
<<<<<<< HEAD
            pg = getProjectGroupWithProjects( projectGroup.getId() );
=======
            pg = getProjectGroupWithBuildDetailsByProjectGroupId( projectGroup.getId() );
>>>>>>> refs/remotes/apache/trunk
        }
        catch ( Exception e )
        {
            // Do nothing
        }

        if ( pg != null )
        {
            // TODO: why do we need to do this? if not - build results are not
            // removed and a integrity constraint is violated. I assume its
            // because of the fetch groups
            for ( Project p : (List<Project>) pg.getProjects() )
            {
                projectDao.removeProject( p );
            }
<<<<<<< HEAD
=======

            List<BuildDefinition> buildDefs = new ArrayList<BuildDefinition>();
            Iterator<BuildDefinition> it = pg.getBuildDefinitions().listIterator();
            boolean template = false;
            while ( it.hasNext() )
            {
                BuildDefinition bd = it.next();
                if ( bd.isTemplate() )
                {
                    template = true;
                }
                else
                {
                    buildDefs.add( bd );
                }
            }
            if ( template )
            {
                try
                {
                    pg.setBuildDefinitions( buildDefs );
                    updateProjectGroup( pg );
                }
                catch ( ContinuumStoreException e )
                {
                    // Do nothing
                }
            }

>>>>>>> refs/remotes/apache/trunk
            removeObject( pg );
        }
    }

    public ProjectGroup getProjectGroup( int projectGroupId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (ProjectGroup) getObjectById( ProjectGroup.class, projectGroupId );
=======
        return getObjectById( ProjectGroup.class, projectGroupId );
>>>>>>> refs/remotes/apache/trunk
    }

    public ProjectGroup getProjectGroupByGroupId( String groupId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (ProjectGroup) getObjectFromQuery( ProjectGroup.class, "groupId", groupId, null );
=======
        return getObjectFromQuery( ProjectGroup.class, "groupId", groupId, null );
>>>>>>> refs/remotes/apache/trunk
    }

    public ProjectGroup getProjectGroupByGroupIdWithBuildDetails( String groupId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (ProjectGroup) getObjectFromQuery( ProjectGroup.class, "groupId", groupId,
                                                  PROJECT_BUILD_DETAILS_FETCH_GROUP );
=======
        return getObjectFromQuery( ProjectGroup.class, "groupId", groupId, PROJECT_BUILD_DETAILS_FETCH_GROUP );
>>>>>>> refs/remotes/apache/trunk
    }

    public ProjectGroup getProjectGroupByGroupIdWithProjects( String groupId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (ProjectGroup) getObjectFromQuery( ProjectGroup.class, "groupId", groupId,
                                                  PROJECTGROUP_PROJECTS_FETCH_GROUP );
=======
        return getObjectFromQuery( ProjectGroup.class, "groupId", groupId, PROJECTGROUP_PROJECTS_FETCH_GROUP );
>>>>>>> refs/remotes/apache/trunk
    }

    public ProjectGroup getProjectGroupByProjectId( int projectId )
        throws ContinuumObjectNotFoundException
    {
        try
        {
            return projectDao.getProject( projectId ).getProjectGroup();
        }
        catch ( ContinuumStoreException e )
        {
            throw new ContinuumObjectNotFoundException(
                "unable to find project group containing project with id: " + projectId );

        }
    }

    public ProjectGroup getProjectGroupByProject( Project project )
        throws ContinuumObjectNotFoundException
    {
        return getProjectGroupByProjectId( project.getId() );
    }

    public void updateProjectGroup( ProjectGroup group )
        throws ContinuumStoreException
    {
        updateObject( group );
    }

    public ProjectGroup getProjectGroupWithProjects( int projectGroupId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (ProjectGroup) getObjectById( ProjectGroup.class, projectGroupId, PROJECTGROUP_PROJECTS_FETCH_GROUP );
=======
        return getObjectById( ProjectGroup.class, projectGroupId, PROJECTGROUP_PROJECTS_FETCH_GROUP );
>>>>>>> refs/remotes/apache/trunk
    }

    public Collection<ProjectGroup> getAllProjectGroupsWithProjects()
    {
        return getAllObjectsDetached( ProjectGroup.class, "name ascending", PROJECTGROUP_PROJECTS_FETCH_GROUP );
    }

    public List<ProjectGroup> getAllProjectGroupsWithBuildDetails()
    {
        return getAllObjectsDetached( ProjectGroup.class, "name ascending", PROJECT_BUILD_DETAILS_FETCH_GROUP );
    }

    public List<ProjectGroup> getAllProjectGroups()
    {
        return getAllObjectsDetached( ProjectGroup.class, "name ascending", null );
    }

    public List<ProjectGroup> getAllProjectGroupsWithTheLot()
    {
<<<<<<< HEAD
        List fetchGroups = Arrays.asList( new String[]{PROJECT_WITH_BUILDS_FETCH_GROUP,
            PROJECTGROUP_PROJECTS_FETCH_GROUP, BUILD_RESULT_WITH_DETAILS_FETCH_GROUP,
            PROJECT_WITH_CHECKOUT_RESULT_FETCH_GROUP, PROJECT_ALL_DETAILS_FETCH_GROUP,
            PROJECT_BUILD_DETAILS_FETCH_GROUP} );
=======
        List fetchGroups = Arrays.asList(
            new String[] { PROJECT_WITH_BUILDS_FETCH_GROUP, PROJECTGROUP_PROJECTS_FETCH_GROUP,
                BUILD_RESULT_WITH_DETAILS_FETCH_GROUP, PROJECT_WITH_CHECKOUT_RESULT_FETCH_GROUP,
                PROJECT_ALL_DETAILS_FETCH_GROUP, PROJECT_BUILD_DETAILS_FETCH_GROUP } );
>>>>>>> refs/remotes/apache/trunk
        return PlexusJdoUtils.getAllObjectsDetached( getPersistenceManager(), ProjectGroup.class, "name ascending",
                                                     fetchGroups );
    }

    public ProjectGroup getProjectGroupWithBuildDetailsByProjectGroupId( int projectGroupId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (ProjectGroup) getObjectById( ProjectGroup.class, projectGroupId, PROJECT_BUILD_DETAILS_FETCH_GROUP );
=======
        return getObjectById( ProjectGroup.class, projectGroupId, PROJECT_BUILD_DETAILS_FETCH_GROUP );
>>>>>>> refs/remotes/apache/trunk
    }

    public List<ProjectGroup> getProjectGroupByRepository( int repositoryId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( ProjectGroup.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int repositoryId" );

            query.setFilter( "this.localRepository.id == repositoryId" );

<<<<<<< HEAD
            List result = (List) query.execute( new Integer( repositoryId ) );
=======
            List result = (List) query.execute( repositoryId );
>>>>>>> refs/remotes/apache/trunk

            return result == null ? Collections.EMPTY_LIST : (List) pm.detachCopyAll( result );
        }
        finally
        {
            tx.commit();

            rollback( tx );
        }
    }

}
