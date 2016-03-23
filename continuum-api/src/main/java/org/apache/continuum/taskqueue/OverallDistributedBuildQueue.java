package org.apache.continuum.taskqueue;

<<<<<<< HEAD
import java.util.List;
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
>>>>>>> refs/remotes/apache/trunk

import org.apache.continuum.builder.distributed.executor.DistributedBuildTaskQueueExecutor;
import org.codehaus.plexus.taskqueue.Task;
import org.codehaus.plexus.taskqueue.TaskQueue;
import org.codehaus.plexus.taskqueue.TaskQueueException;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> refs/remotes/apache/trunk
public interface OverallDistributedBuildQueue
{
    String getBuildAgentUrl();

    void setBuildAgentUrl( String buildAgentUrl );

    TaskQueue getDistributedBuildQueue();

    void addToDistributedBuildQueue( Task distributedBuildTask )
        throws TaskQueueException;

    List<PrepareBuildProjectsTask> getProjectsInQueue()
        throws TaskQueueException;

    boolean isInDistributedBuildQueue( int projectGroupId, int scmRootId )
        throws TaskQueueException;

    void removeFromDistributedBuildQueue( int projectGroupId, int scmRootId )
        throws TaskQueueException;

    void removeFromDistributedBuildQueue( int[] hashCodes )
        throws TaskQueueException;

    void removeFromDistributedBuildQueueByHashCode( int hashCode )
        throws TaskQueueException;

    DistributedBuildTaskQueueExecutor getDistributedBuildTaskQueueExecutor();
}
