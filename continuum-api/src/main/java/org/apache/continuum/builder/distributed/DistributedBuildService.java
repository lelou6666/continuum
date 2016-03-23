package org.apache.continuum.builder.distributed;

<<<<<<< HEAD
import java.util.Map;

import org.apache.maven.continuum.ContinuumException;

=======
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

import org.apache.maven.continuum.ContinuumException;

import java.util.Map;

>>>>>>> refs/remotes/apache/trunk
public interface DistributedBuildService
{
    String ROLE = DistributedBuildService.class.getName();

    void prepareBuildFinished( Map<String, Object> context )
        throws ContinuumException;

    boolean shouldBuild( Map<String, Object> context );

    void startPrepareBuild( Map<String, Object> context )
        throws ContinuumException;

<<<<<<< HEAD
    void startProjectBuild( int projectId )
        throws ContinuumException;
    
=======
    void startProjectBuild( int projectId, int buildDefinitionId )
        throws ContinuumException;

>>>>>>> refs/remotes/apache/trunk
    void updateBuildResult( Map<String, Object> context )
        throws ContinuumException;

    void updateProject( Map<String, Object> context )
        throws ContinuumException;

    Map<String, String> getEnvironments( int buildDefinitionId, String installationType )
        throws ContinuumException;
}
