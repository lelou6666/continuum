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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

=======
import com.opensymphony.xwork2.Preparable;
import org.apache.commons.lang.StringEscapeUtils;
>>>>>>> refs/remotes/apache/trunk
import org.apache.continuum.model.repository.AbstractPurgeConfiguration;
import org.apache.continuum.model.repository.DirectoryPurgeConfiguration;
import org.apache.continuum.model.repository.LocalRepository;
import org.apache.continuum.model.repository.RepositoryPurgeConfiguration;
import org.apache.continuum.purge.ContinuumPurgeManager;
import org.apache.continuum.purge.PurgeConfigurationService;
import org.apache.continuum.repository.RepositoryService;
<<<<<<< HEAD
=======
import org.apache.continuum.taskqueue.manager.TaskQueueManager;
import org.apache.continuum.web.util.AuditLog;
import org.apache.continuum.web.util.AuditLogConstants;
>>>>>>> refs/remotes/apache/trunk
import org.apache.maven.continuum.configuration.ConfigurationService;
import org.apache.maven.continuum.model.project.Schedule;
import org.apache.maven.continuum.security.ContinuumRoleConstants;
import org.apache.maven.continuum.web.action.ContinuumConfirmAction;
<<<<<<< HEAD
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
 * @plexus.component role="com.opensymphony.xwork.Action" role-hint="purgeConfiguration"
 *
 */
=======
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.redback.rbac.Resource;
import org.codehaus.redback.integration.interceptor.SecureAction;
import org.codehaus.redback.integration.interceptor.SecureActionBundle;
import org.codehaus.redback.integration.interceptor.SecureActionException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maria Catherine Tan
 * @since 25 jul 07
 */
@Component( role = com.opensymphony.xwork2.Action.class, hint = "purgeConfiguration", instantiationStrategy = "per-lookup" )
>>>>>>> refs/remotes/apache/trunk
public class PurgeConfigurationAction
    extends ContinuumConfirmAction
    implements Preparable, SecureAction
{
<<<<<<< HEAD
    private static final String PURGE_TYPE_REPOSITORY = "repository";
    
    private static final String PURGE_TYPE_DIRECTORY = "directory";
    
    private static final String PURGE_DIRECTORY_RELEASES = "releases";
    
    private static final String PURGE_DIRECTORY_BUILDOUTPUT = "buildOutput";
    
    private static final int DEFAULT_RETENTION_COUNT = 2;
    
    private static final int DEFAULT_DAYS_OLDER = 100;
    
    private String purgeType;
    
    private String directoryType;
    
    private String description;
    
    private String message;
    
    private boolean deleteAll;
    
    private boolean deleteReleasedSnapshots;
    
    private boolean enabled;
    
    private boolean confirmed;
    
    private boolean defaultPurgeConfiguration;
    
    private int retentionCount;
    
    private int daysOlder;
    
    private int repositoryId;
    
    private int scheduleId;
    
    private int purgeConfigId;
    
    private AbstractPurgeConfiguration purgeConfig;
    
    private Map<Integer, String> repositories;
    
    private Map<Integer, String> schedules;
    
    private List<RepositoryPurgeConfiguration> repoPurgeConfigs;
    
    private List<DirectoryPurgeConfiguration> dirPurgeConfigs;
    
    private List<String> directoryTypes;
    
    /**
     * @plexus.requirement
     */
    private PurgeConfigurationService purgeConfigService;
    
    /**
     * @plexus.requirement
     */
    private RepositoryService repositoryService;
    
=======

    private static final String PURGE_TYPE_REPOSITORY = "repository";

    private static final String PURGE_TYPE_DIRECTORY = "directory";

    private static final String PURGE_DIRECTORY_RELEASES = "releases";

    private static final String PURGE_DIRECTORY_BUILDOUTPUT = "buildOutput";

    private static final int DEFAULT_RETENTION_COUNT = 2;

    private static final int DEFAULT_DAYS_OLDER = 100;

    private String purgeType;

    private String directoryType;

    private String description;

    private boolean deleteAll;

    private boolean deleteReleasedSnapshots;

    private boolean enabled;

    private boolean confirmed;

    private boolean defaultPurgeConfiguration;

    private int retentionCount;

    private int daysOlder;

    private int repositoryId;

    private int scheduleId;

    private int purgeConfigId;

    private AbstractPurgeConfiguration purgeConfig;

    private Map<Integer, String> repositories;

    private Map<Integer, String> schedules;

    private List<RepositoryPurgeConfiguration> repoPurgeConfigs;

    private List<DirectoryPurgeConfiguration> dirPurgeConfigs;

    private List<String> directoryTypes;

    @Requirement
    private PurgeConfigurationService purgeConfigService;

    @Requirement
    private RepositoryService repositoryService;

    @Override
>>>>>>> refs/remotes/apache/trunk
    public void prepare()
        throws Exception
    {
        super.prepare();
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        // build schedules
        if ( schedules == null )
        {
            schedules = new HashMap<Integer, String>();

            Collection<Schedule> allSchedules = getContinuum().getSchedules();

            for ( Schedule schedule : allSchedules )
            {
<<<<<<< HEAD
                schedules.put( new Integer( schedule.getId() ), schedule.getName() );
            }
        }
        
=======
                schedules.put( schedule.getId(), schedule.getName() );
            }
        }

>>>>>>> refs/remotes/apache/trunk
        // build repositories
        if ( repositories == null )
        {
            repositories = new HashMap<Integer, String>();
<<<<<<< HEAD
            
            List<LocalRepository> allRepositories = repositoryService.getAllLocalRepositories();
            
            for ( LocalRepository repository : allRepositories )
            {
                repositories.put( new Integer( repository.getId() ), repository.getName() );
            }
        }
        
=======

            List<LocalRepository> allRepositories = repositoryService.getAllLocalRepositories();

            for ( LocalRepository repository : allRepositories )
            {
                repositories.put( repository.getId(), repository.getName() );
            }
        }

>>>>>>> refs/remotes/apache/trunk
        directoryTypes = new ArrayList<String>();
        directoryTypes.add( PURGE_DIRECTORY_RELEASES );
        directoryTypes.add( PURGE_DIRECTORY_BUILDOUTPUT );
    }
<<<<<<< HEAD
    
=======

    @Override
>>>>>>> refs/remotes/apache/trunk
    public String input()
        throws Exception
    {
        if ( purgeConfigId != 0 )
        {
            purgeConfig = purgeConfigService.getPurgeConfiguration( purgeConfigId );
<<<<<<< HEAD
            
=======

>>>>>>> refs/remotes/apache/trunk
            if ( purgeConfig instanceof RepositoryPurgeConfiguration )
            {
                RepositoryPurgeConfiguration repoPurge = (RepositoryPurgeConfiguration) purgeConfig;

                this.purgeType = PURGE_TYPE_REPOSITORY;
                this.daysOlder = repoPurge.getDaysOlder();
                this.retentionCount = repoPurge.getRetentionCount();
                this.deleteAll = repoPurge.isDeleteAll();
                this.deleteReleasedSnapshots = repoPurge.isDeleteReleasedSnapshots();
                this.enabled = repoPurge.isEnabled();
                this.defaultPurgeConfiguration = repoPurge.isDefaultPurge();
                this.description = repoPurge.getDescription();
<<<<<<< HEAD
                
=======

>>>>>>> refs/remotes/apache/trunk
                if ( repoPurge.getRepository() != null )
                {
                    this.repositoryId = repoPurge.getRepository().getId();
                }
<<<<<<< HEAD
                
=======

>>>>>>> refs/remotes/apache/trunk
                if ( repoPurge.getSchedule() != null )
                {
                    this.scheduleId = repoPurge.getSchedule().getId();
                }
            }
            else if ( purgeConfig instanceof DirectoryPurgeConfiguration )
            {
                DirectoryPurgeConfiguration dirPurge = (DirectoryPurgeConfiguration) purgeConfig;
<<<<<<< HEAD
                
=======

>>>>>>> refs/remotes/apache/trunk
                this.purgeType = PURGE_TYPE_DIRECTORY;
                this.daysOlder = dirPurge.getDaysOlder();
                this.retentionCount = dirPurge.getRetentionCount();
                this.directoryType = dirPurge.getDirectoryType();
                this.deleteAll = dirPurge.isDeleteAll();
                this.enabled = dirPurge.isEnabled();
                this.defaultPurgeConfiguration = dirPurge.isDefaultPurge();
                this.description = dirPurge.getDescription();
<<<<<<< HEAD
                
=======

>>>>>>> refs/remotes/apache/trunk
                if ( dirPurge.getSchedule() != null )
                {
                    this.scheduleId = dirPurge.getSchedule().getId();
                }
            }
        }
        else
        {
            this.retentionCount = DEFAULT_RETENTION_COUNT;
            this.daysOlder = DEFAULT_DAYS_OLDER;
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
        
        repoPurgeConfigs = purgeConfigService.getAllRepositoryPurgeConfigurations();
        dirPurgeConfigs = purgeConfigService.getAllDirectoryPurgeConfigurations();
        
        return SUCCESS;
    }
    
=======

        return INPUT;
    }

    public String list()
        throws Exception
    {
        repoPurgeConfigs = purgeConfigService.getAllRepositoryPurgeConfigurations();
        dirPurgeConfigs = purgeConfigService.getAllDirectoryPurgeConfigurations();
        return SUCCESS;
    }

>>>>>>> refs/remotes/apache/trunk
    public String save()
        throws Exception
    {
        if ( purgeConfigId == 0 )
        {
            if ( purgeType.equals( PURGE_TYPE_REPOSITORY ) )
            {
                purgeConfig = new RepositoryPurgeConfiguration();
            }
            else
            {
                purgeConfig = new DirectoryPurgeConfiguration();
            }
<<<<<<< HEAD
            
            purgeConfig = setupPurgeConfiguration( purgeConfig );
            
=======

            purgeConfig = setupPurgeConfiguration( purgeConfig );

>>>>>>> refs/remotes/apache/trunk
            purgeConfig = purgeConfigService.addPurgeConfiguration( purgeConfig );
        }
        else
        {
            purgeConfig = purgeConfigService.getPurgeConfiguration( purgeConfigId );
            purgeConfig = setupPurgeConfiguration( purgeConfig );
<<<<<<< HEAD
            
            purgeConfigService.updatePurgeConfiguration( purgeConfig );
        }
        
=======

            purgeConfigService.updatePurgeConfiguration( purgeConfig );
        }

>>>>>>> refs/remotes/apache/trunk
        if ( purgeConfig.isDefaultPurge() )
        {
            updateDefaultPurgeConfiguration();
        }
<<<<<<< HEAD
        
        return SUCCESS;
    }
    
    public String remove()
        throws Exception
    {
        purgeConfigService.removePurgeConfiguration( purgeConfigId );
        
        return SUCCESS;
    }
    
=======

        if ( purgeConfig.isEnabled() && purgeConfig.getSchedule() != null )
        {
            getContinuum().activePurgeSchedule( purgeConfig.getSchedule() );
        }

        return SUCCESS;
    }

    public String remove()
        throws Exception
    {
        if ( !confirmed )
        {
            return CONFIRM;
        }
        purgeConfigService.removePurgeConfiguration( purgeConfigId );
        addActionMessage( getText( "purgeConfig.removeSuccess" ) );
        return SUCCESS;
    }

>>>>>>> refs/remotes/apache/trunk
    public String purge()
        throws Exception
    {
        ContinuumPurgeManager purgeManager = getContinuum().getPurgeManager();
<<<<<<< HEAD
        
        if ( purgeConfigId > 0 )
        {
            purgeConfig = purgeConfigService.getPurgeConfiguration( purgeConfigId );
            
            if ( purgeConfig instanceof RepositoryPurgeConfiguration )
            {
                RepositoryPurgeConfiguration repoPurge = (RepositoryPurgeConfiguration) purgeConfig;
                
                // check if repository is in use
                if ( purgeManager.isRepositoryInUse( repoPurge.getRepository().getId() ) )
                {
                    message = "repository.error.purge.in.use";
                    return ERROR;
                }
                
                purgeManager.purgeRepository( repoPurge );
            }
            else
            {
                DirectoryPurgeConfiguration dirPurge = (DirectoryPurgeConfiguration) purgeConfig;
                purgeManager.purgeDirectory( dirPurge );
            }
        }
        
        return SUCCESS;
    }
    
=======
        TaskQueueManager taskQueueManager = getContinuum().getTaskQueueManager();

        if ( purgeConfigId > 0 )
        {
            purgeConfig = purgeConfigService.getPurgeConfiguration( purgeConfigId );

            AuditLog event;

            if ( purgeConfig instanceof RepositoryPurgeConfiguration )
            {
                RepositoryPurgeConfiguration repoPurge = (RepositoryPurgeConfiguration) purgeConfig;

                // check if repository is in use
                if ( taskQueueManager.isRepositoryInUse( repoPurge.getRepository().getId() ) )
                {
                    addActionError( getText( "repository.error.purge.in.use" ) );
                    return ERROR;
                }

                purgeManager.purgeRepository( repoPurge );

                event = new AuditLog( repoPurge.getRepository().getName(), AuditLogConstants.PURGE_LOCAL_REPOSITORY );
                event.setCategory( AuditLogConstants.LOCAL_REPOSITORY );
            }
            else if ( purgeConfig instanceof DirectoryPurgeConfiguration )
            {
                DirectoryPurgeConfiguration dirPurge = (DirectoryPurgeConfiguration) purgeConfig;
                purgeManager.purgeDirectory( dirPurge );

                if ( dirPurge.getDirectoryType().equals( PURGE_DIRECTORY_RELEASES ) )
                {
                    event = new AuditLog( dirPurge.getLocation(), AuditLogConstants.PURGE_DIRECTORY_RELEASES );
                }
                else
                {
                    event = new AuditLog( dirPurge.getLocation(), AuditLogConstants.PURGE_DIRECTORY_BUILDOUTPUT );
                }

                event.setCategory( AuditLogConstants.DIRECTORY );
            }
            else
            {
                addActionError( getText( "purgeConfig.unknownType" ) );
                return ERROR;
            }

            addActionMessage( getText( "purgeConfig.purgeSuccess" ) );
            event.setCurrentUser( getPrincipal() );
            event.log();
        }
        return SUCCESS;
    }

>>>>>>> refs/remotes/apache/trunk
    public String getPurgeType()
    {
        return this.purgeType;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setPurgeType( String purgeType )
    {
        this.purgeType = purgeType;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public String getDirectoryType()
    {
        return this.directoryType;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDirectoryType( String directoryType )
    {
        this.directoryType = directoryType;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public String getDescription()
    {
        return this.description;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDescription( String description )
    {
        this.description = description;
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
    public boolean isDeleteAll()
    {
        return this.deleteAll;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDeleteAll( boolean deleteAll )
    {
        this.deleteAll = deleteAll;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public boolean isDeleteReleasedSnapshots()
    {
        return this.deleteReleasedSnapshots;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDeleteReleasedSnapshots( boolean deleteReleasedSnapshots )
    {
        this.deleteReleasedSnapshots = deleteReleasedSnapshots;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public boolean isEnabled()
    {
        return this.enabled;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setEnabled( boolean enabled )
    {
        this.enabled = enabled;
    }
<<<<<<< HEAD
    
=======

    @Override
>>>>>>> refs/remotes/apache/trunk
    public boolean isConfirmed()
    {
        return this.confirmed;
    }
<<<<<<< HEAD
    
=======

    @Override
>>>>>>> refs/remotes/apache/trunk
    public void setConfirmed( boolean confirmed )
    {
        this.confirmed = confirmed;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public boolean isDefaultPurgeConfiguration()
    {
        return this.defaultPurgeConfiguration;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDefaultPurgeConfiguration( boolean defaultPurgeConfiguration )
    {
        this.defaultPurgeConfiguration = defaultPurgeConfiguration;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public int getRetentionCount()
    {
        return this.retentionCount;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setRetentionCount( int retentionCount )
    {
        this.retentionCount = retentionCount;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public int getDaysOlder()
    {
        return this.daysOlder;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDaysOlder( int daysOlder )
    {
        this.daysOlder = daysOlder;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public int getRepositoryId()
    {
        return this.repositoryId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setRepositoryId( int repositoryId )
    {
        this.repositoryId = repositoryId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public int getScheduleId()
    {
        return this.scheduleId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setScheduleId( int scheduleId )
    {
        this.scheduleId = scheduleId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public int getPurgeConfigId()
    {
        return purgeConfigId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setPurgeConfigId( int purgeConfigId )
    {
        this.purgeConfigId = purgeConfigId;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public AbstractPurgeConfiguration getPurgeConfig()
    {
        return this.purgeConfig;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setPurgeConfig( AbstractPurgeConfiguration purgeConfig )
    {
        this.purgeConfig = purgeConfig;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public Map<Integer, String> getRepositories()
    {
        return this.repositories;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setRepositories( Map<Integer, String> repositories )
    {
        this.repositories = repositories;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public Map<Integer, String> getSchedules()
    {
        return this.schedules;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setSchedules( Map<Integer, String> schedules )
    {
        this.schedules = schedules;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public List<RepositoryPurgeConfiguration> getRepoPurgeConfigs()
    {
        return this.repoPurgeConfigs;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setRepoPurgeConfigs( List<RepositoryPurgeConfiguration> repoPurgeConfigs )
    {
        this.repoPurgeConfigs = repoPurgeConfigs;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public List<DirectoryPurgeConfiguration> getDirPurgeConfigs()
    {
        return this.dirPurgeConfigs;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDirPurgeConfigs( List<DirectoryPurgeConfiguration> dirPurgeConfigs )
    {
        this.dirPurgeConfigs = dirPurgeConfigs;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public List<String> getDirectoryTypes()
    {
        return this.directoryTypes;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public void setDirectoryTypes( List<String> directoryTypes )
    {
        this.directoryTypes = directoryTypes;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    private AbstractPurgeConfiguration setupPurgeConfiguration( AbstractPurgeConfiguration purgeConfiguration )
        throws Exception
    {
        if ( purgeConfiguration instanceof RepositoryPurgeConfiguration )
        {
            return buildRepoPurgeConfiguration();
        }
        else
        {
            return buildDirPurgeConfiguration();
        }
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    private RepositoryPurgeConfiguration buildRepoPurgeConfiguration()
        throws Exception
    {
        RepositoryPurgeConfiguration repoPurge = (RepositoryPurgeConfiguration) purgeConfig;
        repoPurge.setDeleteAll( this.deleteAll );
        repoPurge.setDeleteReleasedSnapshots( this.deleteReleasedSnapshots );
        repoPurge.setDaysOlder( this.daysOlder );
        repoPurge.setRetentionCount( this.retentionCount );
        repoPurge.setEnabled( this.enabled );
        repoPurge.setDefaultPurge( this.defaultPurgeConfiguration );
<<<<<<< HEAD
        repoPurge.setDescription( this.description );
        repoPurge.setDefaultPurge( this.defaultPurgeConfiguration );
        
=======
        // escape xml to prevent xss attacks
        repoPurge.setDescription( StringEscapeUtils.escapeXml( StringEscapeUtils.unescapeXml( this.description ) ) );
        repoPurge.setDefaultPurge( this.defaultPurgeConfiguration );

>>>>>>> refs/remotes/apache/trunk
        if ( repositoryId != 0 )
        {
            LocalRepository repository = repositoryService.getLocalRepository( repositoryId );
            repoPurge.setRepository( repository );
        }
<<<<<<< HEAD
        
=======

>>>>>>> refs/remotes/apache/trunk
        if ( scheduleId > 0 )
        {
            Schedule schedule = getContinuum().getSchedule( scheduleId );
            repoPurge.setSchedule( schedule );
        }
<<<<<<< HEAD
        
        return repoPurge;
    }
    
=======

        return repoPurge;
    }

>>>>>>> refs/remotes/apache/trunk
    private DirectoryPurgeConfiguration buildDirPurgeConfiguration()
        throws Exception
    {
        DirectoryPurgeConfiguration dirPurge = (DirectoryPurgeConfiguration) purgeConfig;
        dirPurge.setDeleteAll( this.deleteAll );
        dirPurge.setEnabled( this.enabled );
        dirPurge.setDaysOlder( this.daysOlder );
        dirPurge.setRetentionCount( this.retentionCount );
<<<<<<< HEAD
        dirPurge.setDescription( this.description );
        dirPurge.setDirectoryType( this.directoryType );
        dirPurge.setDefaultPurge( this.defaultPurgeConfiguration );
        
=======
        // escape xml to prevent xss attacks
        dirPurge.setDescription( StringEscapeUtils.escapeXml( StringEscapeUtils.unescapeXml( this.description ) ) );
        dirPurge.setDirectoryType( this.directoryType );
        dirPurge.setDefaultPurge( this.defaultPurgeConfiguration );

>>>>>>> refs/remotes/apache/trunk
        if ( scheduleId > 0 )
        {
            Schedule schedule = getContinuum().getSchedule( scheduleId );
            dirPurge.setSchedule( schedule );
        }
<<<<<<< HEAD
        
        ConfigurationService configService = getContinuum().getConfiguration();
        String path = null;
        
=======

        ConfigurationService configService = getContinuum().getConfiguration();
        String path = null;

>>>>>>> refs/remotes/apache/trunk
        if ( this.directoryType.equals( PURGE_DIRECTORY_RELEASES ) )
        {
            path = configService.getWorkingDirectory().getAbsolutePath();
        }
        else if ( this.directoryType.equals( PURGE_DIRECTORY_BUILDOUTPUT ) )
        {
            path = configService.getBuildOutputDirectory().getAbsolutePath();
        }
<<<<<<< HEAD
        
        dirPurge.setLocation( path );
        
        return dirPurge;
    }
    
=======

        dirPurge.setLocation( path );

        return dirPurge;
    }

>>>>>>> refs/remotes/apache/trunk
    private void updateDefaultPurgeConfiguration()
        throws Exception
    {
        if ( purgeConfig instanceof RepositoryPurgeConfiguration )
        {
<<<<<<< HEAD
            RepositoryPurgeConfiguration repoPurge = purgeConfigService.getDefaultPurgeConfigurationForRepository( repositoryId );
            
=======
            RepositoryPurgeConfiguration repoPurge = purgeConfigService.getDefaultPurgeConfigurationForRepository(
                repositoryId );

>>>>>>> refs/remotes/apache/trunk
            if ( repoPurge != null && repoPurge.getId() != purgeConfig.getId() )
            {
                repoPurge.setDefaultPurge( false );
                purgeConfigService.updateRepositoryPurgeConfiguration( repoPurge );
            }
        }
        else if ( purgeConfig instanceof DirectoryPurgeConfiguration )
        {
<<<<<<< HEAD
            DirectoryPurgeConfiguration dirPurge = purgeConfigService.getDefaultPurgeConfigurationForDirectoryType( directoryType );
            
=======
            DirectoryPurgeConfiguration dirPurge = purgeConfigService.getDefaultPurgeConfigurationForDirectoryType(
                directoryType );

>>>>>>> refs/remotes/apache/trunk
            if ( dirPurge != null && dirPurge.getId() != purgeConfig.getId() )
            {
                dirPurge.setDefaultPurge( false );
                purgeConfigService.updateDirectoryPurgeConfiguration( dirPurge );
            }
        }
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public SecureActionBundle getSecureActionBundle()
        throws SecureActionException
    {
        SecureActionBundle bundle = new SecureActionBundle();
        bundle.setRequiresAuthentication( true );
        bundle.addRequiredAuthorization( ContinuumRoleConstants.CONTINUUM_MANAGE_PURGING, Resource.GLOBAL );

        return bundle;
    }
}
