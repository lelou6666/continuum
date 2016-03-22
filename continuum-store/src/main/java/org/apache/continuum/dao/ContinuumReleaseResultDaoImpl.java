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
import java.util.Collection;
import java.util.List;
=======
import org.apache.continuum.model.release.ContinuumReleaseResult;
import org.apache.maven.continuum.store.ContinuumObjectNotFoundException;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.component.annotations.Component;
import org.springframework.stereotype.Repository;
>>>>>>> refs/remotes/apache/trunk

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
<<<<<<< HEAD

import org.apache.continuum.model.release.ContinuumReleaseResult;
import org.apache.maven.continuum.store.ContinuumObjectNotFoundException;
import org.apache.maven.continuum.store.ContinuumStoreException;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
 * @plexus.component role="org.apache.continuum.dao.ContinuumReleaseResultDao"
 */
=======
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:ctan@apache.org">Maria Catherine Tan</a>
 */
@Repository( "continuumReleaseResultDao" )
@Component( role = org.apache.continuum.dao.ContinuumReleaseResultDao.class )
>>>>>>> refs/remotes/apache/trunk
public class ContinuumReleaseResultDaoImpl
    extends AbstractDao
    implements ContinuumReleaseResultDao
{
    public ContinuumReleaseResult addContinuumReleaseResult( ContinuumReleaseResult releaseResult )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (ContinuumReleaseResult) addObject( releaseResult );
=======
        return addObject( releaseResult );
>>>>>>> refs/remotes/apache/trunk
    }

    public List<ContinuumReleaseResult> getAllContinuumReleaseResults()
    {
        return getAllObjectsDetached( ContinuumReleaseResult.class );
    }

    public ContinuumReleaseResult getContinuumReleaseResult( int releaseResultId )
        throws ContinuumObjectNotFoundException, ContinuumStoreException
    {
<<<<<<< HEAD
        return (ContinuumReleaseResult) getObjectById( ContinuumReleaseResult.class, releaseResultId );
    }

    public ContinuumReleaseResult getContinuumReleaseResult( int projectId, String releaseGoal, long startTime, long endTime )
=======
        return getObjectById( ContinuumReleaseResult.class, releaseResultId );
    }

    public ContinuumReleaseResult getContinuumReleaseResult( int projectId, String releaseGoal, long startTime,
                                                             long endTime )
>>>>>>> refs/remotes/apache/trunk
        throws ContinuumStoreException
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();
<<<<<<< HEAD
            
=======

>>>>>>> refs/remotes/apache/trunk
            Extent extent = pm.getExtent( ContinuumReleaseResult.class, true );

            Query query = pm.newQuery( extent );

            query.declareImports( "import java.lang.String" );
<<<<<<< HEAD
            
            query.declareParameters( "int projectId, String releaseGoal, long startTime, long endTime" );
            
            query.setFilter( "this.project.id == projectId && this.releaseGoal == releaseGoal && this.startTime == startTime && this.endTime == endTime" );
            
=======

            query.declareParameters( "int projectId, String releaseGoal, long startTime, long endTime" );

            query.setFilter(
                "this.project.id == projectId && this.releaseGoal == releaseGoal && this.startTime == startTime && this.endTime == endTime" );

>>>>>>> refs/remotes/apache/trunk
            Object[] params = new Object[4];
            params[0] = projectId;
            params[1] = releaseGoal;
            params[2] = startTime;
            params[3] = endTime;

            Collection result = (Collection) query.executeWithArray( params );

            if ( result.size() == 0 )
            {
                tx.commit();

                return null;
            }

            Object object = pm.detachCopy( result.iterator().next() );

            tx.commit();
<<<<<<< HEAD
            
=======

>>>>>>> refs/remotes/apache/trunk
            return (ContinuumReleaseResult) object;
        }
        finally
        {
            rollback( tx );
        }

    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public List<ContinuumReleaseResult> getContinuumReleaseResultsByProjectGroup( int projectGroupId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( ContinuumReleaseResult.class, true );

            Query query = pm.newQuery( extent, "projectGroup.id == " + projectGroupId );

            List result = (List) query.execute();

            result = (List) pm.detachCopyAll( result );

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
    }

    public List<ContinuumReleaseResult> getContinuumReleaseResultsByProject( int projectId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( ContinuumReleaseResult.class, true );

            Query query = pm.newQuery( extent, "project.id == " + projectId );

            List result = (List) query.execute();

            result = (List) pm.detachCopyAll( result );

            tx.commit();

            return result;
        }
        finally
        {
            rollback( tx );
        }
    }

    public void removeContinuumReleaseResult( ContinuumReleaseResult releaseResult )
        throws ContinuumStoreException
    {
        removeObject( releaseResult );
    }

}
