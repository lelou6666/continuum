package org.apache.continuum.installation;

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

import org.apache.continuum.dao.InstallationDao;
import org.apache.continuum.utils.shell.ExecutionResult;
import org.apache.continuum.utils.shell.ListOutputConsumer;
import org.apache.continuum.utils.shell.OutputConsumer;
import org.apache.continuum.utils.shell.ShellCommandHelper;
import org.apache.maven.continuum.execution.ExecutorConfigurator;
import org.apache.maven.continuum.installation.AlreadyExistsInstallationException;
import org.apache.maven.continuum.installation.InstallationException;
import org.apache.maven.continuum.installation.InstallationService;
import org.apache.maven.continuum.model.system.Installation;
import org.apache.maven.continuum.model.system.Profile;
import org.apache.maven.continuum.profile.AlreadyExistsProfileException;
import org.apache.maven.continuum.profile.ProfileException;
import org.apache.maven.continuum.profile.ProfileService;
import org.apache.maven.continuum.store.ContinuumStoreException;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;

/**
 * @author <a href="mailto:olamy@codehaus.org">olamy</a>
 *          TODO use some cache mechanism to prevent always reading from store ?
 * @since 13 juin 07
 */
@Service( "installationService" )
public class DefaultInstallationService
    implements InstallationService, Initializable
{
    private static final Logger log = LoggerFactory.getLogger( DefaultInstallationService.class );

    @Resource
    private InstallationDao installationDao;

    @Resource
    private ProfileService profileService;

    @Resource
    private ShellCommandHelper shellCommandHelper;

    private Map<String, ExecutorConfigurator> typesValues;

    // ---------------------------------------------
    // Plexus lifecycle
    // ---------------------------------------------

    public void initialize()
        throws InitializationException
    {
        this.typesValues = new HashMap<String, ExecutorConfigurator>();
        this.typesValues.put( InstallationService.ANT_TYPE, new ExecutorConfigurator( "ant", "bin", "ANT_HOME",
                                                                                      "-version" ) );

        this.typesValues.put( InstallationService.ENVVAR_TYPE, null );
        this.typesValues.put( InstallationService.JDK_TYPE, new ExecutorConfigurator( "java", "bin", "JAVA_HOME",
                                                                                      "-version" ) );
        this.typesValues.put( InstallationService.MAVEN1_TYPE, new ExecutorConfigurator( "maven", "bin", "MAVEN_HOME",
                                                                                         "-v" ) );
        this.typesValues.put( InstallationService.MAVEN2_TYPE, new ExecutorConfigurator( "mvn", "bin", "M2_HOME",
                                                                                         "-v" ) );
    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#add(org.apache.maven.continuum.model.system.Installation)
     */
    public Installation add( Installation installation )
        throws InstallationException, AlreadyExistsInstallationException
    {
        try
        {
            return this.add( installation, false );
        }
        catch ( AlreadyExistsProfileException e )
        {
            // normally cannot happend here but anyway we throw the exception
            throw new InstallationException( e.getMessage(), e );
        }
    }

    public Installation add( Installation installation, boolean automaticProfile )
        throws InstallationException, AlreadyExistsProfileException, AlreadyExistsInstallationException
    {
        if ( alreadyExistInstallationName( installation ) )
        {
            throw new AlreadyExistsInstallationException(
                "Installation with name " + installation.getName() + " already exists" );
        }
        // TODO must be done in the same transaction
        Installation storedOne;
        try
        {
            String envVarName = this.getEnvVar( installation.getType() );
            // override with the defined var name for defined types
            if ( StringUtils.isNotEmpty( envVarName ) )
            {
                installation.setVarName( envVarName );
            }
            storedOne = installationDao.addInstallation( installation );
        }
        catch ( ContinuumStoreException e )
        {
            throw new InstallationException( e.getMessage(), e );
        }
        try
        {
            if ( automaticProfile )
            {
                Profile profile = new Profile();
                profile.setName( storedOne.getName() );
                profile = profileService.addProfile( profile );
                profileService.addInstallationInProfile( profile, storedOne );
            }
        }
        catch ( ProfileException e )
        {
            throw new InstallationException( "failed to create automatic Profile " + e.getMessage(), e );
        }
        return storedOne;
    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#delete(org.apache.maven.continuum.model.system.Installation)
     */
    public void delete( Installation installation )
        throws InstallationException
    {
        try
        {
            installationDao.removeInstallation( installation );
        }
        catch ( ContinuumStoreException e )
        {
            throw new InstallationException( e.getMessage(), e );
        }

    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#getAllInstallations()
     */
    @SuppressWarnings( "unchecked" )
    public List<Installation> getAllInstallations()
        throws InstallationException
    {
        try
        {
            List<Installation> installations = installationDao.getAllInstallations();
            return installations == null ? Collections.EMPTY_LIST : installations;
        }
        catch ( ContinuumStoreException e )
        {
            throw new InstallationException( e.getMessage(), e );
        }
    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#getInstallation(int)
     */
    public Installation getInstallation( int installationId )
        throws InstallationException
    {
        try
        {
            return installationDao.getInstallation( installationId );
        }
        catch ( ContinuumStoreException e )
        {
            throw new InstallationException( e.getMessage(), e );
        }
    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#getInstallation(String)
     */
    public Installation getInstallation( String installationName )
        throws InstallationException
    {
        try
        {
            return installationDao.getInstallation( installationName );
        }
        catch ( ContinuumStoreException e )
        {
            throw new InstallationException( e.getMessage(), e );
        }
    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#update(org.apache.maven.continuum.model.system.Installation)
     */
    public void update( Installation installation )
        throws InstallationException, AlreadyExistsInstallationException
    {
        try
        {
            Installation stored = getInstallation( installation.getInstallationId() );
            if ( stored == null )
            {
                throw new InstallationException( "installation with name " + installation.getName() + " not exists" );
            }

            stored.setName( installation.getName() );
            if ( alreadyExistInstallationName( installation ) )
            {
                throw new AlreadyExistsInstallationException(
                    "Installation with name " + installation.getName() + " already exists" );
            }
            stored.setType( installation.getType() );
            String envVarName = this.getEnvVar( installation.getType() );
            // override with the defined var name for defined types
            if ( StringUtils.isNotEmpty( envVarName ) )
            {
                installation.setVarName( envVarName );
            }
            else
            {
                stored.setVarName( installation.getVarName() );
            }
            stored.setVarValue( installation.getVarValue() );
            installationDao.updateInstallation( stored );
        }
        catch ( ContinuumStoreException e )
        {
            throw new InstallationException( e.getMessage(), e );
        }

    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#getExecutorConfigurator(java.lang.String)
     */
    public ExecutorConfigurator getExecutorConfigurator( String type )
    {
        return this.typesValues.get( type );
    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#getEnvVar(java.lang.String)
     */
    public String getEnvVar( String type )
    {
        ExecutorConfigurator executorConfigurator = this.typesValues.get( type );
        return executorConfigurator == null ? null : executorConfigurator.getEnvVar();
    }

    // -------------------------------------------------------------
    // versions informations on jdk and builders (mvn, maven, ant )
    // -------------------------------------------------------------

    /**
     * TODO replace with calling getExecutorVersionInfo
     *
     * @see org.apache.maven.continuum.installation.InstallationService#getDefaultJavaVersionInfo()
     */
    public List<String> getDefaultJavaVersionInfo()
        throws InstallationException
    {
        try
        {
            Properties systemEnvVars = shellCommandHelper.getSystemEnvVars();
            String javaHome = (String) systemEnvVars.get( "JAVA_HOME" );
            // olamy : JAVA_HOME may not exist for mac users
            if ( StringUtils.isEmpty( javaHome ) )
            {
                return getJavaVersionInfo( System.getProperty( "java.home" ) );
            }
            return getJavaVersionInfo( javaHome );

        }
        catch ( Exception e )
        {
            throw new InstallationException( e.getMessage(), e );
        }
    }

    /**
     * TODO replace with calling getExecutorVersionInfo
     *
     * @see org.apache.maven.continuum.installation.InstallationService#getJavaVersionInfo(org.apache.maven.continuum.model.system.Installation)
     */
    public List<String> getJavaVersionInfo( Installation installation )
        throws InstallationException
    {
        if ( installation == null )
        {
            return getDefaultJavaVersionInfo();
        }
        if ( StringUtils.isEmpty( installation.getVarValue() ) )
        {
            return getDefaultJavaVersionInfo();
        }
        try
        {
            return getJavaVersionInfo( installation.getVarValue() );
        }
        catch ( Exception e )
        {
            throw new InstallationException( e.getMessage(), e );
        }
    }

    /**
     * @param homePath
     * @return
     * @throws Exception
     */
    private List<String> getJavaVersionInfo( String homePath )
        throws Exception
    {

        String executable = homePath + File.separator + "bin" + File.separator + "java";
        ListOutputConsumer outputConsumer = new ListOutputConsumer();
        ExecutionResult result = shellCommandHelper.executeShellCommand( null, executable, new String[] { "-version" },
                                                                         outputConsumer, -1, null );
        int exitCode = result.getExitCode();
        if ( exitCode != 0 )
        {
            throw new Exception(
                String.format( "failed to get java version information, %s returned exit code %s", executable,
                               exitCode ) );
        }
        return outputConsumer.getList();
    }

    private Map<String, String> getEnvVars( Profile profile )
    {
        Map<String, String> environnments = new HashMap<String, String>();
        if ( profile == null )
        {
            return environnments;
        }
        if ( profile.getBuilder() != null )
        {
            environnments.put( profile.getBuilder().getVarName(), profile.getBuilder().getVarValue() );
        }
        if ( profile.getJdk() != null )
        {
            environnments.put( profile.getJdk().getVarName(), profile.getJdk().getVarValue() );
        }
        if ( profile.getEnvironmentVariables() != null )
        {
            for ( Installation installation : (List<Installation>) profile.getEnvironmentVariables() )
            {
                environnments.put( installation.getVarName(), installation.getVarValue() );
            }
        }
        return environnments;
    }

    /**
     * @see org.apache.maven.continuum.installation.InstallationService#getExecutorVersionInfo(java.lang.String, org.apache.maven.continuum.execution.ExecutorConfigurator, Profile)
     */
    public List<String> getExecutorVersionInfo( String path, ExecutorConfigurator executorConfigurator,
                                                Profile profile )
        throws InstallationException
    {
        if ( executorConfigurator == null || executorConfigurator.getExecutable() == null )
        {
            return Collections.EMPTY_LIST;
        }

        StringBuilder executable = new StringBuilder();

        Map<String, String> env = new HashMap<String, String>();

        if ( StringUtils.isNotEmpty( path ) )
        {
            executable.append( path ).append( File.separator );
            executable.append( executorConfigurator.getRelativePath() ).append( File.separator );
            env.put( executorConfigurator.getEnvVar(), path );
        }
        executable = executable.append( executorConfigurator.getExecutable() );

        env.putAll( getEnvVars( profile ) );

        ListOutputConsumer outputConsumer = new ListOutputConsumer();
        ExecutionResult result;
        try
        {
            result = shellCommandHelper.executeShellCommand( null, executable.toString(),
                                                             new String[] { executorConfigurator.getVersionArgument() },
                                                             outputConsumer, -1, null );
        }
        catch ( Exception e )
        {
            log.error( "failed to get executor version info", e );
            throw new InstallationException( e.getMessage(), e );
        }
        int exitCode = result.getExitCode();
        if ( exitCode != 0 )
        {

            throw new InstallationException(
                String.format( "failed to get executor version info, %s returned exit code %s", executable,
                               exitCode ) );
        }
        return outputConsumer.getList();
    }

    private boolean alreadyExistInstallationName( Installation installation )
        throws InstallationException
    {
        List<Installation> all = getAllInstallations();
        for ( Installation install : all )
        {
            if ( org.apache.commons.lang.StringUtils.equals( installation.getName(), install.getName() ) &&
                ( installation.getInstallationId() == 0 ||
                    installation.getInstallationId() != install.getInstallationId() ) )
            {
                return true;
            }
        }
        return false;
    }

}
