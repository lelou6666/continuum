package org.apache.continuum.buildagent.utils;

import org.apache.continuum.buildagent.buildcontext.BuildContext;
import org.apache.maven.continuum.model.project.Project;

/**
 * @author Jan Stevens Ancajas
 */
public class BuildContextToProject
{
    public static Project getProject( BuildContext buildContext )
    {
        Project project = new Project();    

        project.setId( buildContext.getProjectId() );

        project.setName( buildContext.getProjectName() );

        project.setScmUrl( buildContext.getScmUrl() );

        project.setScmUsername( buildContext.getScmUsername() );

        project.setScmPassword( buildContext.getScmPassword() );

        project.setExecutorId( buildContext.getExecutorId() );

        project.setState( buildContext.getProjectState() );
        
        return project;
    }
}