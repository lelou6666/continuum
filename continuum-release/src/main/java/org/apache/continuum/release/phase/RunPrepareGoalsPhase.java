package org.apache.continuum.release.phase;

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

import org.apache.maven.shared.release.ReleaseExecutionException;
import org.apache.maven.shared.release.ReleaseFailureException;
import org.apache.maven.shared.release.ReleaseResult;
import org.apache.maven.shared.release.config.ReleaseDescriptor;
import org.apache.maven.shared.release.env.ReleaseEnvironment;
<<<<<<< HEAD
=======
import org.codehaus.plexus.component.annotations.Component;

import java.io.File;
import java.util.List;
>>>>>>> refs/remotes/apache/trunk

/**
 * Run Release Preparation Goals
 */
@Component( role = org.apache.maven.shared.release.phase.ReleasePhase.class, hint = "run-release-prepare-goals" )
public class RunPrepareGoalsPhase
    extends AbstractContinuumRunGoalsPhase
{
    @Override
    protected String getGoals( ReleaseDescriptor releaseDescriptor )
    {
        return releaseDescriptor.getPreparationGoals();
    }

<<<<<<< HEAD
    public ReleaseResult execute(ReleaseDescriptor releaseDescriptor,
			ReleaseEnvironment releaseEnvironment, List reactorProjects)
			throws ReleaseExecutionException, ReleaseFailureException {
		
    	return execute( releaseDescriptor, new File( releaseDescriptor.getWorkingDirectory() ), 
                releaseDescriptor.getAdditionalArguments() );
	}
=======
    public ReleaseResult execute( ReleaseDescriptor releaseDescriptor, ReleaseEnvironment releaseEnvironment,
                                  List reactorProjects )
        throws ReleaseExecutionException, ReleaseFailureException
    {

        return execute( releaseDescriptor, new File( releaseDescriptor.getWorkingDirectory() ),
                        releaseDescriptor.getAdditionalArguments() );
    }
>>>>>>> refs/remotes/apache/trunk

    public ReleaseResult simulate( ReleaseDescriptor releaseDescriptor, ReleaseEnvironment releaseEnvironment,
                                   List reactorProjects )
        throws ReleaseExecutionException, ReleaseFailureException
    {
        ReleaseResult result = new ReleaseResult();

        logInfo( result, "Executing preparation goals - since this is simulation mode it is running against the " +
            "original project, not the rewritten ones" );

        execute( releaseDescriptor, releaseEnvironment, reactorProjects );

        return result;
    }
}
