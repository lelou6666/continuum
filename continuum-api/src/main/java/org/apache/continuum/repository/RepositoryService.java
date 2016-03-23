package org.apache.continuum.repository;

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
import java.util.List;

import org.apache.continuum.model.repository.LocalRepository;

/**
 * @author Maria Catherine Tan
 * @version $Id$
=======
import org.apache.continuum.model.repository.LocalRepository;

import java.util.List;

/**
 * @author Maria Catherine Tan
>>>>>>> refs/remotes/apache/trunk
 * @since 25 jul 07
 */
public interface RepositoryService
{
    String ROLE = RepositoryService.class.getName();
<<<<<<< HEAD
    
    // ------------------------------------------------------
    //  LocalRepository
    // ------------------------------------------------------
    
    /**
     * Add the local repository
     * 
=======

    // ------------------------------------------------------
    //  LocalRepository
    // ------------------------------------------------------

    /**
     * Add the local repository
     *
>>>>>>> refs/remotes/apache/trunk
     * @param repository the local repository to add
     * @return LocalRepository the local repository
     * @throws RepositoryServiceException
     */
    LocalRepository addLocalRepository( LocalRepository repository )
        throws RepositoryServiceException;

    /**
     * Update the local repository
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> refs/remotes/apache/trunk
     * @param repository the local repository to update
     * @throws RepositoryServiceException
     */
    void updateLocalRepository( LocalRepository repository )
        throws RepositoryServiceException;

    /**
     * Remove the local repository
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> refs/remotes/apache/trunk
     * @param repositoryId the id of the local repository to remove
     * @throws RepositoryServiceException
     */
    void removeLocalRepository( int repositoryId )
        throws RepositoryServiceException;

    /**
     * Retrieve all local repositories
<<<<<<< HEAD
     * 
     * @return list of all local repositories
     */
    List<LocalRepository> getAllLocalRepositories();
    
    /**
     * Retrieve local repository
     * 
=======
     *
     * @return list of all local repositories
     */
    List<LocalRepository> getAllLocalRepositories();

    /**
     * Retrieve local repository
     *
>>>>>>> refs/remotes/apache/trunk
     * @param location the system file path of the repository
     * @return LocalRepository the local repository
     * @throws RepositoryServiceException
     */
    LocalRepository getLocalRepositoryByLocation( String location )
        throws RepositoryServiceException;
<<<<<<< HEAD
    
    /**
     * Retrieve list of local repositories with the specified layout
=======

    /**
     * Retrieve list of local repositories with the specified layout
     *
>>>>>>> refs/remotes/apache/trunk
     * @param layout the layout of the repository. "default" or "legacy"
     * @return List of local repositories
     * @throws RepositoryServiceException
     */
    List<LocalRepository> getLocalRepositoriesByLayout( String layout );
<<<<<<< HEAD
    
    /**
     * Retrieve local repository
     * 
=======

    /**
     * Retrieve local repository
     *
>>>>>>> refs/remotes/apache/trunk
     * @param repositoryId the id of the local repository
     * @return LocalRepository the local repository
     * @throws RepositoryServiceException
     */
    LocalRepository getLocalRepository( int repositoryId )
        throws RepositoryServiceException;
<<<<<<< HEAD
=======

    /**
     * Retrieve local repository
     *
     * @param repositoryName
     * @return
     * @throws RepositoryServiceException
     */
    LocalRepository getLocalRepositoryByName( String repositoryName )
        throws RepositoryServiceException;
>>>>>>> refs/remotes/apache/trunk
}