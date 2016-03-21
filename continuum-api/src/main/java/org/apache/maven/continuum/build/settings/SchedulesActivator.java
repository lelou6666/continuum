package org.apache.maven.continuum.build.settings;

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

import org.apache.maven.continuum.Continuum;
import org.apache.maven.continuum.model.project.Schedule;

/**
 * @author <a href="mailto:jason@maven.org">Jason van Zyl</a>
 */
public interface SchedulesActivator
{
    String ROLE = SchedulesActivator.class.getName();

    /**
     * Grab all the stored {@link org.apache.maven.continuum.model.project.Schedule} objects
     * and activate them by looking at the scheduling information contained within and submitting a
     * Job to the scheduler.
     *
     * @throws SchedulesActivationException
     */
    void activateSchedules( Continuum continuum )
        throws SchedulesActivationException;

    /**
     * Activate schedule by looking at the scheduling information contained within and submitting a
     * Job to the scheduler.
     *
     * @throws SchedulesActivationException
     */
    void activateSchedule( Schedule schedule, Continuum continuum )
        throws SchedulesActivationException;

    /**
     * Activate schedule only for PurgeConfiguration associated.
     *
     * @throws SchedulesActivationException
     */
    void activatePurgeSchedule( Schedule schedule, Continuum continuum )
        throws SchedulesActivationException;

    /**
     * Activate schedule only for BuildDefinitions associated.
     *
     * @throws SchedulesActivationException
     */
    void activateBuildSchedule( Schedule schedule, Continuum continuum )
        throws SchedulesActivationException;

    /**
     * Unactivate schedule by looking at the scheduling information contained within.
     *
     * @throws SchedulesActivationException
     */
    void unactivateSchedule( Schedule schedule, Continuum continuum )
        throws SchedulesActivationException;

    /**
     * Unactivate schedule purgeConfigurations if it is not in a purgeConfiguration.
     */
    void unactivateOrphanPurgeSchedule( Schedule schedule )
        throws SchedulesActivationException;

    /**
     * Unactivate schedule buildDefinitions if it is not in a buildDefinition.
     *
     * @throws SchedulesActivationException
     */
    void unactivateOrphanBuildSchedule( Schedule schedule )
        throws SchedulesActivationException;
}
