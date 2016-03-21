package org.apache.continuum.purge.task;

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

import org.codehaus.plexus.taskqueue.Task;

/**
 * @author Maria Catherine Tan
 */
public class PurgeTask
    implements Task
{
<<<<<<< HEAD:continuum-core/src/main/java/org/apache/maven/continuum/buildqueue/BuildProjectTask.java
    private long projectId;

    private long buildDefinitionId;

    private long timestamp;

    private int trigger;

    private long maxExecutionTime;

    public BuildProjectTask( long projectId, long buildDefinitionId, int trigger )
=======
    private int purgeConfigurationId;

    private final long timestamp;

    private long maxExecutionTime;

    public PurgeTask( int purgeConfigurationId )
>>>>>>> refs/remotes/apache/trunk:continuum-core/src/main/java/org/apache/continuum/purge/task/PurgeTask.java
    {
        this.purgeConfigurationId = purgeConfigurationId;

        this.timestamp = System.currentTimeMillis();
    }

<<<<<<< HEAD:continuum-core/src/main/java/org/apache/maven/continuum/buildqueue/BuildProjectTask.java
    public long getProjectId()
=======
    public int getPurgeConfigurationId()
>>>>>>> refs/remotes/apache/trunk:continuum-core/src/main/java/org/apache/continuum/purge/task/PurgeTask.java
    {
        return purgeConfigurationId;
    }

<<<<<<< HEAD:continuum-core/src/main/java/org/apache/maven/continuum/buildqueue/BuildProjectTask.java
    public long getBuildDefinitionId()
=======
    public void setPurgeConfigurationId( int purgeConfigurationId )
>>>>>>> refs/remotes/apache/trunk:continuum-core/src/main/java/org/apache/continuum/purge/task/PurgeTask.java
    {
        this.purgeConfigurationId = purgeConfigurationId;
    }

    public void setMaxExecutionTime( long maxExecutionTime )
    {
        this.maxExecutionTime = maxExecutionTime;
    }

    public long getMaxExecutionTime()
    {
        return maxExecutionTime;
    }

    public long getTimestamp()
    {
        return timestamp;
    }
}
