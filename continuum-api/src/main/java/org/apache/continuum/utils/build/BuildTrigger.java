package org.apache.continuum.utils.build;

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

/**
 * @author Jevica Arianne B. Zurbano
<<<<<<< HEAD
 * @version $Id: BuildTrigger.java
=======
>>>>>>> refs/remotes/apache/trunk
 * @since 19 jun 09
 */
public class BuildTrigger
{
    private int trigger;
<<<<<<< HEAD
    
    private String username;
    
=======

    private String triggeredBy;

>>>>>>> refs/remotes/apache/trunk
    public BuildTrigger( int trigger )
    {
        this.trigger = trigger;
    }
<<<<<<< HEAD
    
    public BuildTrigger( String username )
    {
        this.username = username;
    }
    
    public BuildTrigger( int trigger, String username )
    {
        this.trigger = trigger;
        this.username = username;
    }
    
=======

    public BuildTrigger( String triggeredBy )
    {
        this.triggeredBy = triggeredBy;
    }

    public BuildTrigger( int trigger, String triggeredBy )
    {
        this.trigger = trigger;
        this.triggeredBy = triggeredBy;
    }

>>>>>>> refs/remotes/apache/trunk
    public void setTrigger( int trigger )
    {
        this.trigger = trigger;
    }
<<<<<<< HEAD
    
=======

>>>>>>> refs/remotes/apache/trunk
    public int getTrigger()
    {
        return this.trigger;
    }
<<<<<<< HEAD
    
    public void setUsername( String username )
    {
        this.username = username;
    }
    
    public String getUsername()
    {
        return this.username;
=======

    public void setTriggeredBy( String triggeredBy )
    {
        this.triggeredBy = triggeredBy;
    }

    public String getTriggeredBy()
    {
        return this.triggeredBy;
>>>>>>> refs/remotes/apache/trunk
    }
}

