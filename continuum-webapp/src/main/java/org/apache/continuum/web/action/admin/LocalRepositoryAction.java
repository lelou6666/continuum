package org.apache.continuum.web.action.admin;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

=======
import com.opensymphony.xwork2.Preparable;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.model.repository.LocalRepository;
import org.apache.continuum.model.repository.RepositoryPurgeConfiguration;
import org.apache.continuum.purge.ContinuumPurgeManager;
import org.apache.continuum.purge.PurgeConfigurationService;
import org.apache.continuum.repository.RepositoryService;
<<<<<<< HEAD
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.security.ContinuumRoleConstants;
import org.apache.maven.continuum.web.action.ContinuumConfirmAction;
import org.codehaus.plexus.redback.rbac.Resource;
import org.codehaus.plexus.redback.xwork.interceptor.SecureAction;
import org.codehaus.plexus.redback.xwork.interceptor.SecureActionBundle;
import org.codehaus.plexus.redback.xwork.interceptor.SecureActionException;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.Preparable;

/**
 * @author Maria Catherine Tan
 * @version $Id$
 * @since 25 jul 07
 * @plexus.component role="com.opensymphony.xwork.Action" role-hint="localRepository"
 */
=======
import org.apache.continuum.taskqueue.manager.TaskQueueManager;
import org.apache.continuum.web.util.AuditLog;
import org.apache.continuum.web.util.AuditLogConstants;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.security.ContinuumRoleConstants;
import org.apache.maven.continuum.web.action.ContinuumConfirmAction;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.redback.rbac.Resource;
import org.codehaus.redback.integration.interceptor.SecureAction;
import org.codehaus.redback.integration.interceptor.SecureActionBundle;
import org.codehaus.redback.integration.interceptor.SecureActionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maria Catherine Tan
 * @since 25 jul 07
 */
@Component( role = com.opensymphony.xwork2.Action.class, hint = "localRepository", instantiationStrategy = "per-lookup" )
>>>>>>> refs/remotes/apache/trunk
public class LocalRepositoryAction
    extends ContinuumConfirmAction
    implements Preparable, SecureAction
{
    private static final String LAYOUT_DEFAULT = "default";
<<<<<<< HEAD
    
    private static final String LAYOUT_LEGACY = "legacy";
    
    private boolean confirmed;

    private boolean defaultRepo;
    
    private LocalRepository repository;
    
    private List<LocalRepository> repositories;
    
    private List<ProjectGroup> groups;
    
    private List<String> layouts;
    
    private Map<String, Boolean> defaultPurgeMap;
    
    private String message;
    
    /**
     * @plexus.requirement
     */
    private RepositoryService repositoryService;
    
    /**
     * @plexus.requirement
     */
    private PurgeConfigurationService purgeConfigService;
    
=======

    private static final String LAYOUT_LEGACY = "legacy";

    private boolean confirmed;

    private boolean defaultRepo;

    private LocalRepository repository;

    private List<LocalRepository> repositories;

    private List<ProjectGroup> groups;

    private List<String> layouts;

    private Map<String, Boolean> defaultPurgeMap;

    @Requirement
    private RepositoryService repositoryService;

    @Requirement
    private PurgeConfigurationService purgeConfigService;

>>>>>>> refs/remotes/apache/trunk
    public void prepare()
        throws Exception
    {
        super.prepare();
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        layouts = new ArrayList<String>();
        layouts.add( LAYOUT_DEFAULT );
        layouts.add( LAYOUT_LEGACY );
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public String input()
        throws Exception
    {
        defaultRepo = false;
<<<<<<< HEAD
        
        if ( repository != null && repository.getId() > 0 )
        {
            repository = repositoryService.getLocalRepository( repository.getId() );
            
=======

        if ( repository != null && repository.getId() > 0 )
        {
            repository = repositoryService.getLocalRepository( repository.getId() );

>>>>>>> refs/remotes/apache/trunk
            if ( repository.getName().equals( "DEFAULT" ) )
            {
                defaultRepo = true;
            }
        }
<<<<<<< HEAD
        
        return INPUT;
    }
    
    public String list()
        throws Exception
    {
        String errorMessage = ServletActionContext.getRequest().getParameter( "errorMessage" );
        
        if ( errorMessage != null )
        {
            addActionError( errorMessage );
        }
        
        repositories = repositoryService.getAllLocalRepositories();
        
        defaultPurgeMap = new HashMap<String, Boolean>();
        
        for ( LocalRepository repo : repositories )
        {
            // get default purge config of repository
            RepositoryPurgeConfiguration purgeConfig = purgeConfigService.getDefaultPurgeConfigurationForRepository( repo.getId() );
            
            if ( purgeConfig == null )
            {
                defaultPurgeMap.put(  repo.getName(), Boolean.FALSE );
            }
            else
            {
                defaultPurgeMap.put(  repo.getName(), Boolean.TRUE );
            }
        }
        
        return SUCCESS;
    }
    
=======

        return INPUT;
    }

    public String list()
        throws Exception
    {
        repositories = repositoryService.getAllLocalRepositories();

        defaultPurgeMap = new HashMap<String, Boolean>();

        for ( LocalRepository repo : repositories )
        {
            // get default purge config of repository
            RepositoryPurgeConfiguration purgeConfig = purgeConfigService.getDefaultPurgeConfigurationForRepository(
                repo.getId() );

            if ( purgeConfig == null )
            {
                defaultPurgeMap.put( repo.getName(), Boolean.FALSE );
            }
            else
            {
                defaultPurgeMap.put( repo.getName(), Boolean.TRUE );
            }
        }

        return SUCCESS;
    }

>>>>>>> refs/remotes/apache/trunk
    public String save()
        throws Exception
    {
        List<LocalRepository> allRepositories = repositoryService.getAllLocalRepositories();
<<<<<<< HEAD
        
        for( LocalRepository repo : allRepositories )
        {
            if ( repository.getId() != repo.getId() )
            {
                if ( repository.getName().equals( repo.getName() ) )
                {
                    addActionError( "repository.error.name.unique" );
                }
                
                if ( repository.getLocation().equals( repo.getLocation() ) )
                {
                    addActionError( "repository.error.location.unique" );
                }
            }
        }
        
        if ( repository.getName().trim().equals( "" ) )
        {
            addActionError( "repository.error.name.cannot.be.spaces" );
        }
        
        if ( repository.getLocation().trim().equals( "" ) )
        {
            addActionError( "repository.error.location.cannot.be.spaces" );
        }
        
=======

        for ( LocalRepository repo : allRepositories )
        {
            if ( repository.getId() != repo.getId() )
            {
                if ( repository.getName().trim().equals( repo.getName() ) )
                {
                    addActionError( getText( "repository.error.name.unique" ) );
                }

                if ( repository.getLocation().trim().equals( repo.getLocation() ) )
                {
                    addActionError( getText( "repository.error.location.unique" ) );
                }
            }
        }

        if ( repository.getName().trim().equals( "" ) )
        {
            addActionError( getText( "repository.error.name.cannot.be.spaces" ) );
        }

        if ( repository.getLocation().trim().equals( "" ) )
        {
            addActionError( getText( "repository.error.location.cannot.be.spaces" ) );
        }

>>>>>>> refs/remotes/apache/trunk
        if ( hasActionErrors() )
        {
            return INPUT;
        }
<<<<<<< HEAD
        
        if ( repository.getId() == 0 )
        {
            repository = repositoryService.addLocalRepository( repository );
            
=======

        // trim repository name and location before saving
        repository.setName( repository.getName().trim() );
        repository.setLocation( repository.getLocation().trim() );

        if ( repository.getId() == 0 )
        {
            repository = repositoryService.addLocalRepository( repository );

>>>>>>> refs/remotes/apache/trunk
            createDefaultPurgeConfiguration();
        }
        else
        {
            // check if repository is in use
<<<<<<< HEAD
            ContinuumPurgeManager purgeManager = getContinuum().getPurgeManager();
            if ( purgeManager.isRepositoryInUse( repository.getId() ) )
            {
                addActionError( "repository.error.save.in.use" );
                return ERROR;
            }
            
            LocalRepository retrievedRepo = repositoryService.getLocalRepository( repository.getId() );
            
            retrievedRepo.setName( repository.getName() );
            retrievedRepo.setLocation( repository.getLocation() );
            retrievedRepo.setLayout( repository.getLayout() );
            
            repositoryService.updateLocalRepository( retrievedRepo );
        }
        
        return SUCCESS;
    }
    
    public String remove()
        throws Exception
    {
        ContinuumPurgeManager purgeManager = getContinuum().getPurgeManager();
        if ( purgeManager.isRepositoryInUse( repository.getId() ) )
        {
            message = "repository.error.remove.in.use";
            return ERROR;
        }
        
        repository = repositoryService.getLocalRepository( repository.getId() );
        
        if ( repository.getName().equals( "DEFAULT" ) )
        {
            message = "repository.error.remove.default";
            return ERROR;
        }
        
        if ( confirmed )
        {
            repositoryService.removeLocalRepository( repository.getId() );
        }
        else
        {
            return CONFIRM;
        }
        
        return SUCCESS;
    }
    
=======
            TaskQueueManager taskQueueManager = getContinuum().getTaskQueueManager();
            if ( taskQueueManager.isRepositoryInUse( repository.getId() ) )
            {
                addActionError( getText( "repository.error.save.in.use" ) );
                return ERROR;
            }

            LocalRepository retrievedRepo = repositoryService.getLocalRepository( repository.getId() );

            retrievedRepo.setName( repository.getName() );
            retrievedRepo.setLocation( repository.getLocation() );
            retrievedRepo.setLayout( repository.getLayout() );

            repositoryService.updateLocalRepository( retrievedRepo );
        }

        return SUCCESS;
    }

    public String remove()
        throws Exception
    {
        TaskQueueManager taskQueueManager = getContinuum().getTaskQueueManager();

        repository = repositoryService.getLocalRepository( repository.getId() );

        if ( taskQueueManager.isRepositoryInUse( repository.getId() ) )
        {
            addActionError( getText( "repository.error.remove.in.use",
                                     "Unable to remove local repository because it is in use" ) );
            return ERROR;
        }

        if ( repository.getName().equals( "DEFAULT" ) )
        {
            addActionError( getText( "repository.error.remove.default", "Unable to remove default local repository" ) );
            return ERROR;
        }

        if ( !confirmed )
        {
            return CONFIRM;
        }

        repositoryService.removeLocalRepository( repository.getId() );
        addActionMessage( getText( "repository.remove.success" ) );
        return SUCCESS;
    }

>>>>>>> refs/remotes/apache/trunk
    public String doPurge()
        throws Exception
    {
        ContinuumPurgeManager purgeManager = getContinuum().getPurgeManager();
<<<<<<< HEAD
    
        // check if repository is in use
        if ( purgeManager.isRepositoryInUse( repository.getId() ) )
        {
            message = "repository.error.purge.in.use";
            return ERROR;
        }
        
        // get default purge configuration for repository
        RepositoryPurgeConfiguration purgeConfig = purgeConfigService.getDefaultPurgeConfigurationForRepository( repository.getId() );
        
        if ( purgeConfig != null )
        {
            purgeManager.purgeRepository( purgeConfig );
        }
        
        return SUCCESS;
    }
    
=======
        TaskQueueManager taskQueueManager = getContinuum().getTaskQueueManager();

        // check if repository is in use
        if ( taskQueueManager.isRepositoryInUse( repository.getId() ) )
        {
            addActionError( getText( "repository.error.purge.in.use",
                                     "Unable to purge repository because it is in use" ) );
            return ERROR;
        }

        // get default purge configuration for repository
        RepositoryPurgeConfiguration purgeConfig = purgeConfigService.getDefaultPurgeConfigurationForRepository(
            repository.getId() );

        if ( purgeConfig != null )
        {
            purgeManager.purgeRepository( purgeConfig );

            AuditLog event = new AuditLog( "Repository id=" + repository.getId(),
                                           AuditLogConstants.PURGE_LOCAL_REPOSITORY );
            event.setCategory( AuditLogConstants.LOCAL_REPOSITORY );
            event.setCurrentUser( getPrincipal() );
            event.log();

            addActionMessage( getText( "repository.purge.success" ) );
            return SUCCESS;
        }

        addActionError(
            getText( "repository.error.not.found", new String[] { Integer.toString( repository.getId() ) } ) );
        return ERROR;
    }

>>>>>>> refs/remotes/apache/trunk
    public LocalRepository getRepository()
    {
        return this.repository;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setRepository( LocalRepository repository )
    {
        this.repository = repository;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public List<LocalRepository> getRepositories()
    {
        return this.repositories;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setRepositories( List<LocalRepository> repositories )
    {
        this.repositories = repositories;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public List<ProjectGroup> getGroups()
    {
        return this.groups;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setGroups( List<ProjectGroup> groups )
    {
        this.groups = groups;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public boolean isConfirmed()
    {
        return this.confirmed;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setConfirmed( boolean confirmed )
    {
        this.confirmed = confirmed;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public boolean isDefaultRepo()
    {
        return this.defaultRepo;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDefaultRepo( boolean defaultRepo )
    {
        this.defaultRepo = defaultRepo;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public List<String> getLayouts()
    {
        return this.layouts;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public Map<String, Boolean> getDefaultPurgeMap()
    {
        return this.defaultPurgeMap;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDefaultPurgeMap( Map<String, Boolean> defaultPurgeMap )
    {
        this.defaultPurgeMap = defaultPurgeMap;
    }
<<<<<<< HEAD
    
    public String getMessage()
    {
        return this.message;
    }
    
    public void setMessage( String message )
    {
        this.message = message;
    }
    
=======

>>>>>>> refs/remotes/apache/trunk
    private void createDefaultPurgeConfiguration()
        throws Exception
    {
        RepositoryPurgeConfiguration repoPurge = new RepositoryPurgeConfiguration();
<<<<<<< HEAD
        
        repoPurge.setRepository( repository );
        repoPurge.setDefaultPurge( true );
        
=======

        repoPurge.setRepository( repository );
        repoPurge.setDefaultPurge( true );

>>>>>>> refs/remotes/apache/trunk
        purgeConfigService.addRepositoryPurgeConfiguration( repoPurge );
    }

    public SecureActionBundle getSecureActionBundle()
        throws SecureActionException
    {
        SecureActionBundle bundle = new SecureActionBundle();
        bundle.setRequiresAuthentication( true );
        bundle.addRequiredAuthorization( ContinuumRoleConstants.CONTINUUM_MANAGE_REPOSITORIES, Resource.GLOBAL );

        return bundle;
    }
}
