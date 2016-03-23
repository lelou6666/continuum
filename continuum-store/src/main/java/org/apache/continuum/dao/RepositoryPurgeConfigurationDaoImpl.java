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

import org.apache.continuum.model.repository.RepositoryPurgeConfiguration;
import org.apache.maven.continuum.store.ContinuumStoreException;
<<<<<<< HEAD

=======
import org.codehaus.plexus.component.annotations.Component;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
>>>>>>> refs/remotes/apache/trunk
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
<<<<<<< HEAD
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 * @plexus.component role="org.apache.continuum.dao.RepositoryPurgeConfigurationDao"
 */
=======

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 */
@Repository( "repositoryPurgeConfigurationDao" )
@Component( role = org.apache.continuum.dao.RepositoryPurgeConfigurationDao.class )
>>>>>>> refs/remotes/apache/trunk
public class RepositoryPurgeConfigurationDaoImpl
    extends AbstractDao
    implements RepositoryPurgeConfigurationDao
{
    public List<RepositoryPurgeConfiguration> getAllRepositoryPurgeConfigurations()
    {
        return getAllObjectsDetached( RepositoryPurgeConfiguration.class );
    }

    public List<RepositoryPurgeConfiguration> getRepositoryPurgeConfigurationsBySchedule( int scheduleId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( RepositoryPurgeConfiguration.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int scheduleId" );

            query.setFilter( "this.schedule.id == scheduleId" );

<<<<<<< HEAD
            List result = (List) query.execute( new Integer( scheduleId ) );
=======
            List result = (List) query.execute( scheduleId );

            return result == null ? Collections.EMPTY_LIST : (List) pm.detachCopyAll( result );
        }
        finally
        {
            tx.commit();

            rollback( tx );
        }
    }

    public List<RepositoryPurgeConfiguration> getEnableRepositoryPurgeConfigurationsBySchedule( int scheduleId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( RepositoryPurgeConfiguration.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int scheduleId" );

            query.setFilter( "this.schedule.id == scheduleId  && this.enabled == true" );

            List result = (List) query.execute( scheduleId );
>>>>>>> refs/remotes/apache/trunk

            return result == null ? Collections.EMPTY_LIST : (List) pm.detachCopyAll( result );
        }
        finally
        {
            tx.commit();

            rollback( tx );
        }
    }

    public List<RepositoryPurgeConfiguration> getRepositoryPurgeConfigurationsByLocalRepository( int repositoryId )
    {
        PersistenceManager pm = getPersistenceManager();

        Transaction tx = pm.currentTransaction();

        try
        {
            tx.begin();

            Extent extent = pm.getExtent( RepositoryPurgeConfiguration.class, true );

            Query query = pm.newQuery( extent );

            query.declareParameters( "int repositoryId" );

            query.setFilter( "this.repository.id == repositoryId" );

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

    public RepositoryPurgeConfiguration getRepositoryPurgeConfiguration( int configurationId )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (RepositoryPurgeConfiguration) getObjectById( RepositoryPurgeConfiguration.class, configurationId );
=======
        return getObjectById( RepositoryPurgeConfiguration.class, configurationId );
>>>>>>> refs/remotes/apache/trunk
    }

    public RepositoryPurgeConfiguration addRepositoryPurgeConfiguration(
        RepositoryPurgeConfiguration purgeConfiguration )
        throws ContinuumStoreException
    {
<<<<<<< HEAD
        return (RepositoryPurgeConfiguration) addObject( purgeConfiguration );
=======
        return addObject( purgeConfiguration );
>>>>>>> refs/remotes/apache/trunk
    }

    public void updateRepositoryPurgeConfiguration( RepositoryPurgeConfiguration purgeConfiguration )
        throws ContinuumStoreException
    {
        updateObject( purgeConfiguration );
    }

    public void removeRepositoryPurgeConfiguration( RepositoryPurgeConfiguration purgeConfiguration )
        throws ContinuumStoreException
    {
        removeObject( purgeConfiguration );
    }

}
