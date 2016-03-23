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

import org.apache.maven.continuum.model.project.BuildResult;
import org.apache.maven.continuum.model.project.Project;
import org.apache.maven.continuum.project.ContinuumProjectState;
import org.apache.maven.continuum.store.ContinuumStoreException;
<<<<<<< HEAD
=======
import org.codehaus.plexus.component.annotations.Component;
import org.springframework.stereotype.Repository;
>>>>>>> refs/remotes/apache/trunk

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 * @plexus.component role="org.apache.continuum.dao.BuildResultDao"
 */
=======
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.maven.continuum.project.ContinuumProjectState.BUILDING;
import static org.apache.maven.continuum.project.ContinuumProjectState.CANCELLED;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 */
@Repository( "buildResultDao" )
@Component( role = org.apache.continuum.dao.BuildResultDao.class )
>>>>>>> refs/remotes/apache/trunk
public class BuildResultDaoImpl
    extends AbstractDao
    implements BuildResultDao
{
    public void updateBuildResult( BuildResult build )
        throws ContinuumStoreException
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        Project project = build.getProject();
        try
        {
            tx.begin();

            if ( !JDOHelper.isDetached( build ) )
            {
                throw new ContinuumStoreException( "Not detached: " + build );
            }

            pm.makePersistent( build );

            if ( !JDOHelper.isDetached( project ) )
            {
                throw new ContinuumStoreException( "Not detached: " + project );
            }

            project.setState( build.getState() );

<<<<<<< HEAD
=======
            //TODO: Use projectDao
>>>>>>> refs/remotes/apache/trunk
            pm.makePersistent( project );

            tx.commit();
        }
        finally
        {
            rollback( tx );
        }
    }

    public void addBuildResult( Project project, BuildResult build )
        throws ContinuumStoreException
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            pm.getFetchPlan().addGroup( PROJECT_WITH_BUILDS_FETCH_GROUP );

            Object objectId = pm.newObjectIdInstance( Project.class, project.getId() );

            project = (Project) pm.getObjectById( objectId );

<<<<<<< HEAD
            build = (BuildResult) makePersistent( pm, build, false );
=======
            build = makePersistent( pm, build, false );
>>>>>>> refs/remotes/apache/trunk

            // TODO: these are in the wrong spot - set them on success (though
            // currently some depend on latest build being the one in progress)
            project.setLatestBuildId( build.getId() );

            project.setState( build.getState() );

            project.addBuildResult( build );

            tx.commit();
        }
        finally
        {
            rollback( tx );
        }
    }

    public BuildResult getLatestBuildResultForProject( int projectId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int projectId" );

            query.setFilter( "this.project.id == projectId && this.project.latestBuildId == this.id" );

            List<BuildResult> result = (List<BuildResult>) query.execute( projectId );

            result = (List<BuildResult>) pm.detachCopyAll( result );

            tx.commit();

            if ( result != null && !result.isEmpty() )
            {
<<<<<<< HEAD
                return (BuildResult) result.get( 0 );
=======
                return result.get( 0 );
            }
        }
        finally
        {
            rollback( tx );
        }
        return null;
    }

    public BuildResult getLatestBuildResultForProjectWithDetails( int projectId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            pm.getFetchPlan().addGroup( BUILD_RESULT_WITH_DETAILS_FETCH_GROUP );

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int projectId" );

            query.setFilter( "this.project.id == projectId && this.project.latestBuildId == this.id" );

            List<BuildResult> result = (List<BuildResult>) query.execute( projectId );

            result = (List<BuildResult>) pm.detachCopyAll( result );

            tx.commit();

            if ( result != null && !result.isEmpty() )
            {
                return result.get( 0 );
>>>>>>> refs/remotes/apache/trunk
            }
        }
        finally
        {
            rollback( tx );
        }
        return null;
    }

    public BuildResult getLatestBuildResultForBuildDefinition( int projectId, int buildDefinitionId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int projectId, int buildDefinitionId" );

            query.setFilter( "this.project.id == projectId && this.buildDefinition.id == buildDefinitionId" );
<<<<<<< HEAD
            query.setOrdering( "id descending" );
=======
            query.setRange( 0, 1 );
            query.setOrdering( "this.id descending" );
>>>>>>> refs/remotes/apache/trunk

            Object[] params = new Object[2];
            params[0] = projectId;
            params[1] = buildDefinitionId;

            List<BuildResult> result = (List<BuildResult>) query.executeWithArray( params );

            result = (List<BuildResult>) pm.detachCopyAll( result );

            tx.commit();

            if ( result != null && !result.isEmpty() )
            {
<<<<<<< HEAD
                return (BuildResult) result.get( 0 );
=======
                return result.get( 0 );
            }
        }
        finally
        {
            rollback( tx );
        }
        return null;
    }

    public BuildResult getPreviousBuildResultForBuildDefinition( int projectId, int buildDefinitionId,
                                                                 int buildResultId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int projectId, int buildDefinitionId, int buildResultId" );
            query.setFilter( "this.project.id == projectId && this.buildDefinition.id == buildDefinitionId "
                                 + "&& this.id < buildResultId" );
            query.setRange( 0, 1 );
            query.setOrdering( "this.id descending" );

            Object[] params = new Object[3];
            params[0] = projectId;
            params[1] = buildDefinitionId;
            params[2] = buildResultId;

            List<BuildResult> result = (List<BuildResult>) query.executeWithArray( params );

            result = (List<BuildResult>) pm.detachCopyAll( result );

            tx.commit();

            if ( result != null && !result.isEmpty() )
            {
                return result.get( 0 );
>>>>>>> refs/remotes/apache/trunk
            }
        }
        finally
        {
            rollback( tx );
        }
        return null;
    }

    public Map<Integer, BuildResult> getLatestBuildResultsByProjectGroupId( int projectGroupId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

            String filter = "this.project.latestBuildId == this.id";

            if ( projectGroupId > 0 )
            {
                query.declareParameters( "int projectGroupId" );
                filter += " && this.project.projectGroup.id == projectGroupId";
            }

            query.setFilter( filter );

            List<BuildResult> result;
            if ( projectGroupId > 0 )
            {
                result = (List<BuildResult>) query.execute( projectGroupId );
            }
            else
            {
                result = (List<BuildResult>) query.execute();
            }

            result = (List<BuildResult>) pm.detachCopyAll( result );

            tx.commit();

            if ( result != null && !result.isEmpty() )
            {
                Map<Integer, BuildResult> builds = new HashMap<Integer, BuildResult>();

                for ( BuildResult br : result )
                {
                    builds.put( br.getProject().getId(), br );
                }

                return builds;
            }
        }
        finally
        {
            rollback( tx );
        }

        return null;
    }

<<<<<<< HEAD
    public Map<Integer, BuildResult> getLatestBuildResults()
    {
        return getLatestBuildResultsByProjectGroupId( -1 );
    }

=======
>>>>>>> refs/remotes/apache/trunk
    public void removeBuildResult( BuildResult buildResult )
    {
        removeObject( buildResult );
    }

    public List<BuildResult> getAllBuildsForAProjectByDate( int projectId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Query query = pm.newQuery( "SELECT FROM " + BuildResult.class.getName() +
<<<<<<< HEAD
                " WHERE project.id == projectId PARAMETERS int projectId ORDER BY endTime DESC" );
=======
                                           " WHERE project.id == projectId PARAMETERS int projectId ORDER BY endTime DESC" );
>>>>>>> refs/remotes/apache/trunk

            query.declareImports( "import java.lang.Integer" );

            query.declareParameters( "Integer projectId" );

<<<<<<< HEAD
            List result = (List) query.execute( projectId );

            result = (List) pm.detachCopyAll( result );
=======
            List<BuildResult> result = (List<BuildResult>) query.execute( projectId );

            result = (List<BuildResult>) pm.detachCopyAll( result );
>>>>>>> refs/remotes/apache/trunk

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
    }

    public BuildResult getBuildResult( int buildId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (BuildResult) getObjectById( BuildResult.class, buildId, BUILD_RESULT_WITH_DETAILS_FETCH_GROUP );
    }

    public List<BuildResult> getBuildResultByBuildNumber( int projectId, int buildNumber )
=======
        return getObjectById( BuildResult.class, buildId, BUILD_RESULT_WITH_DETAILS_FETCH_GROUP );
    }

    public List<BuildResult> getBuildResultsByBuildDefinition( int projectId, int buildDefinitionId )
    {
        return getBuildResultsByBuildDefinition( projectId, buildDefinitionId, -1, -1 );
    }

    public List<BuildResult> getBuildResultsByBuildDefinition( int projectId, int buildDefinitionId, long startIndex,
                                                               long endIndex )
>>>>>>> refs/remotes/apache/trunk
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

<<<<<<< HEAD
            query.declareParameters( "int projectId, int buildNumber" );

            query.setFilter( "this.project.id == projectId && this.buildNumber == buildNumber" );

            List result = (List) query.execute( projectId, buildNumber );

            result = (List) pm.detachCopyAll( result );
=======
            if ( startIndex >= 0 && endIndex >= 0 )
            {
                query.setRange( startIndex, endIndex );
            }

            query.declareParameters( "int projectId, int buildDefinitionId" );

            query.setFilter( "this.project.id == projectId && this.buildDefinition.id == buildDefinitionId" );

            query.setOrdering( "this.id descending" );

            List<BuildResult> result = (List<BuildResult>) query.execute( projectId, buildDefinitionId );

            result = (List<BuildResult>) pm.detachCopyAll( result );
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
    public List<BuildResult> getBuildResultsByBuildDefinition( int projectId, int buildDefinitionId )
    {
        return getBuildResultsByBuildDefinition( projectId, buildDefinitionId, -1, -1 );
    }

    public List<BuildResult> getBuildResultsByBuildDefinition( int projectId, int buildDefinitionId, long startIndex,
                                                               long endIndex )
=======
    public long getNbBuildResultsForProject( int projectId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Query query = pm.newQuery( BuildResult.class, "project.id == projectId" );

            query.declareParameters( "int projectId" );

            query.setResult( "count(this)" );

            long result = (Long) query.execute( projectId );

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
    }

    public long getNbBuildResultsInSuccessForProject( int projectId, long fromDate )
>>>>>>> refs/remotes/apache/trunk
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

<<<<<<< HEAD
            if ( startIndex >= 0 && endIndex >= 0 )
            {
                query.setRange( startIndex, endIndex );
            }

            query.declareParameters( "int projectId, int buildDefinitionId" );

            query.setFilter( "this.project.id == projectId && this.buildDefinition.id == buildDefinitionId" );

            query.setOrdering( "this.id descending" );

            List<BuildResult> result = (List<BuildResult>) query.execute( projectId, buildDefinitionId );
=======
            query.declareParameters( "int projectId, long fromDate, int state" );

            query.setFilter( "this.project.id == projectId && this.startTime > fromDate && this.state == state" );

            query.setResult( "count(this)" );

            long result = (Long) query.execute( projectId, fromDate, ContinuumProjectState.OK );

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
    }

    public List<BuildResult> getBuildResultsForProjectWithDetails( int projectId, int fromResultId, int toResultId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );
            pm.getFetchPlan().addGroup( BUILD_RESULT_WITH_DETAILS_FETCH_GROUP );

            Query query = pm.newQuery( extent );

            String parameters = "int projectId, int fromResultId";
            String filter = "this.project.id == projectId && this.id > fromResultId";

            if ( toResultId > 0 )
            {
                parameters += ", int toResultId";
                filter += " && this.id < toResultId";
            }

            query.declareParameters( parameters );
            query.setFilter( filter );
            query.setOrdering( "this.id ascending" );

            List<BuildResult> result;

            if ( toResultId > 0 )
            {
                result = (List<BuildResult>) query.execute( projectId, fromResultId, toResultId );
            }
            else
            {
                result = (List<BuildResult>) query.execute( projectId, fromResultId );
            }
>>>>>>> refs/remotes/apache/trunk

            result = (List<BuildResult>) pm.detachCopyAll( result );

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
    }

<<<<<<< HEAD
    public long getNbBuildResultsForProject( int projectId )
    {
        PersistenceManager pm = getPersistenceManager();

=======
    public List<BuildResult> getBuildResultsForProject( int projectId, long startIndex, long endIndex,
                                                        boolean fullDetails )
    {
        PersistenceManager pm = getPersistenceManager();
>>>>>>> refs/remotes/apache/trunk
        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

<<<<<<< HEAD
            Query query = pm.newQuery( BuildResult.class, "project.id == projectId" );

            query.declareParameters( "int projectId" );

            query.setResult( "count(this)" );

            long result = (Long) query.execute( projectId );
=======
            Extent extent = pm.getExtent( BuildResult.class, true );

            if ( fullDetails )
            {
                pm.getFetchPlan().addGroup( BUILD_RESULT_WITH_DETAILS_FETCH_GROUP );
            }

            Query query = pm.newQuery( extent );

            query.declareParameters( "int projectId" );
            query.setFilter( "this.project.id == projectId" );
            query.setOrdering( "this.id descending" );
            query.setRange( startIndex, endIndex );

            List<BuildResult> result = (List<BuildResult>) query.execute( projectId );

            result = (List<BuildResult>) pm.detachCopyAll( result );
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
    public List<BuildResult> getBuildResultsForProject( int projectId )
    {
        return getBuildResultsForProject( projectId, -1, -1 );
    }

    public List<BuildResult> getBuildResultsForProject( int projectId, long startIndex, long endIndex )
=======
    public List<BuildResult> getBuildResultsForProjectFromId( int projectId, long startId )
        throws ContinuumStoreException
>>>>>>> refs/remotes/apache/trunk
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

<<<<<<< HEAD
=======
        pm.getFetchPlan().addGroup( BUILD_RESULT_WITH_DETAILS_FETCH_GROUP );

>>>>>>> refs/remotes/apache/trunk
        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

<<<<<<< HEAD
            query.declareParameters( "int projectId" );

            query.setFilter( "this.project.id == projectId" );

            query.setOrdering( "this.startTime descending" );

            if ( startIndex >= 0 )
            {
                query.setRange( startIndex, endIndex );
            }

            List result = (List) query.execute( projectId );

            result = (List) pm.detachCopyAll( result );
=======
            query.declareParameters( "int projectId, int buildNumber" );

            query.setFilter( "this.project.id == projectId && this.buildNumber >= buildNumber" );

            query.setOrdering( "this.startTime descending" );

            List<BuildResult> result = (List<BuildResult>) query.execute( projectId, startId );

            result = (List<BuildResult>) pm.detachCopyAll( result );
>>>>>>> refs/remotes/apache/trunk

            tx.commit();

            return result;
        }
<<<<<<< HEAD
=======
        catch ( Exception e )
        {
            throw new ContinuumStoreException( e.getMessage(), e );
        }
>>>>>>> refs/remotes/apache/trunk
        finally
        {
            rollback( tx );
        }
    }

<<<<<<< HEAD
    public List<BuildResult> getBuildResultsForProject( int projectId, long fromDate )
=======
    public BuildResult getLatestBuildResultInSuccess( int projectId )
>>>>>>> refs/remotes/apache/trunk
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

<<<<<<< HEAD
        pm.getFetchPlan().addGroup( BUILD_RESULT_WITH_DETAILS_FETCH_GROUP );

=======
>>>>>>> refs/remotes/apache/trunk
        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

<<<<<<< HEAD
            query.declareParameters( "int projectId, long fromDate" );

            query.setFilter( "this.project.id == projectId && this.startTime > fromDate" );

            List result = (List) query.execute( projectId, fromDate );

            result = (List) pm.detachCopyAll( result );
=======
            query.declareParameters( "int projectId" );

            String filter = "this.project.buildNumber == this.buildNumber && this.project.id == projectId";

            query.setFilter( filter );

            query.setUnique( true );

            BuildResult result = (BuildResult) query.execute( projectId );

            result = (BuildResult) pm.detachCopy( result );
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
    public List<BuildResult> getBuildResultsInSuccessForProject( int projectId, long fromDate )
    {
        List<BuildResult> buildResults = getBuildResultsForProject( projectId, fromDate );

        List<BuildResult> results = new ArrayList<BuildResult>();

        if ( buildResults != null )
        {
            for ( BuildResult res : buildResults )
            {
                if ( res.getState() == ContinuumProjectState.OK )
                {
                    results.add( res );
                }
            }
        }
        return results;
=======
    public Set<Integer> resolveOrphanedInProgressResults( long ageCutoff )
        throws ContinuumStoreException
    {
        PersistenceManager pm = getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
            Query query = pm.newQuery( BuildResult.class );
            query.declareParameters( "long orphanCutoff" );
            String filter = String.format( " this.state == %s && this.startTime < orphanCutoff", BUILDING );
            query.setFilter( filter );
            List<BuildResult> orphans = (List<BuildResult>) pm.detachCopyAll( (List) query.execute( ageCutoff ) );
            Set<Integer> updatedIds = new HashSet<Integer>();
            for ( BuildResult orphan : orphans )
            {
                if ( updatedIds.contains( orphan.getId() ) )
                    continue;
                orphan.setState( CANCELLED );
                orphan.setError( "Build appears to have been orphaned, final status is unknown." );
                orphan.setEndTime( System.currentTimeMillis() );
                updateObject( orphan );
                updatedIds.add( orphan.getId() );
            }
            tx.commit();
            return updatedIds;
        }
        finally
        {
            rollback( tx );
        }
    }

    public BuildResult getPreviousBuildResultInSuccess( int projectId, int buildResultId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int projectId, int buildResultId" );

            String filter = "this.project.id == projectId"
                + " && this.state == " + ContinuumProjectState.OK
                + " && this.id < buildResultId";

            query.setFilter( filter );
            query.setOrdering( "this.id descending" );
            query.setRange( 0, 1 );

            List<BuildResult> results = (List<BuildResult>) query.execute( projectId, buildResultId );

            tx.commit();

            return results.size() > 0 ? results.get( 0 ) : null;
        }
        finally
        {
            rollback( tx );
        }
>>>>>>> refs/remotes/apache/trunk
    }

    public Map<Integer, BuildResult> getBuildResultsInSuccessByProjectGroupId( int projectGroupId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

            String filter = "this.project.buildNumber == this.buildNumber";

            if ( projectGroupId > 0 )
            {
                query.declareParameters( "int projectGroupId" );
                filter += " && this.project.projectGroup.id == projectGroupId";
            }

            query.setFilter( filter );

            List<BuildResult> result;

            if ( projectGroupId > 0 )
            {
                result = (List<BuildResult>) query.execute( projectGroupId );
            }
            else
            {
                result = (List<BuildResult>) query.execute();
            }

            result = (List<BuildResult>) pm.detachCopyAll( result );

            tx.commit();

            if ( result != null && !result.isEmpty() )
            {
                Map<Integer, BuildResult> builds = new HashMap<Integer, BuildResult>();

                for ( BuildResult br : result )
                {
                    builds.put( br.getProject().getId(), br );
                }

                return builds;
            }
        }
        finally
        {
            rollback( tx );
        }

        return null;
    }

<<<<<<< HEAD
    public Map<Integer, BuildResult> getBuildResultsInSuccess()
    {
        return getBuildResultsInSuccessByProjectGroupId( -1 );
    }

=======
    public List<BuildResult> getBuildResultsInRange( Date fromDate, Date toDate, int state, String triggeredBy,
                                                     Collection<Integer> projectGroupIds, int offset, int length )
    {
        PersistenceManager pm = getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            pm.getFetchPlan().addGroup( BUILD_RESULT_WITH_DETAILS_FETCH_GROUP );

            Extent extent = pm.getExtent( BuildResult.class, true );

            Query query = pm.newQuery( extent );

            InRangeQueryAttrs inRangeQueryAttrs =
                new InRangeQueryAttrs( fromDate, toDate, state, triggeredBy, projectGroupIds, query ).build();

            String parameters = inRangeQueryAttrs.getParameters();
            String filter = inRangeQueryAttrs.getFilter();
            Map params = inRangeQueryAttrs.getParams();

            query.declareParameters( parameters );
            query.setFilter( filter );
            query.setRange( offset, offset + length );

            List<BuildResult> result = (List<BuildResult>) query.executeWithMap( params );

            result = (List<BuildResult>) pm.detachCopyAll( result );

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }

    }

    private class InRangeQueryAttrs
    {
        private Date fromDate;

        private Date toDate;

        private int state;

        private String triggeredBy;

        private Collection<Integer> projectGroupIds;

        private Query query;

        private String parameters;

        private String filter;

        private Map params;

        public InRangeQueryAttrs( Date fromDate, Date toDate, int state, String triggeredBy,
                                  Collection<Integer> projectGroupIds, Query query )
        {
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.state = state;
            this.triggeredBy = triggeredBy;
            this.projectGroupIds = projectGroupIds;
            this.query = query;
        }

        public String getParameters()
        {
            return parameters;
        }

        public String getFilter()
        {
            return filter;
        }

        public Map getParams()
        {
            return params;
        }

        public InRangeQueryAttrs build()
        {
            parameters = "";
            filter = "";

            params = new HashMap();

            if ( state > 0 )
            {
                params.put( "state", state );
                parameters += "int state, ";
                filter += "this.state == state && ";
            }

            if ( projectGroupIds != null && !projectGroupIds.isEmpty() )
            {
                params.put( "projectGroupIds", projectGroupIds );
                query.declareImports( "import java.util.Collection" );
                parameters += "Collection projectGroupIds, ";
                filter += "projectGroupIds.contains(this.project.projectGroup.id)  && ";
            }

            if ( triggeredBy != null && !triggeredBy.equals( "" ) )
            {
                params.put( "triggeredBy", triggeredBy );
                query.declareImports( "import java.lang.String" );
                parameters += "String triggeredBy, ";
                filter += "this.username == triggeredBy && ";
            }

            if ( fromDate != null )
            {
                params.put( "fromDate", fromDate.getTime() );
                parameters += "long fromDate, ";
                filter += "this.startTime >= fromDate && ";
            }

            if ( toDate != null )
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime( toDate );
                cal.add( Calendar.DAY_OF_MONTH, 1 );

                params.put( "toDate", cal.getTimeInMillis() );
                parameters += "long toDate";
                filter += "this.startTime < toDate";
            }

            if ( filter.endsWith( "&& " ) )
            {
                filter = filter.substring( 0, filter.length() - 3 );
                parameters = parameters.substring( 0, parameters.length() - 2 );
            }
            return this;
        }
    }
>>>>>>> refs/remotes/apache/trunk
}
