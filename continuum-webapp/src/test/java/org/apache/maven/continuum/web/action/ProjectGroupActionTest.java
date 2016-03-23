package org.apache.maven.continuum.web.action;

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

import com.opensymphony.xwork2.Action;
import org.apache.continuum.web.action.AbstractActionTest;
import org.apache.maven.continuum.Continuum;
import org.apache.maven.continuum.model.project.ProjectGroup;
import org.apache.maven.continuum.web.action.stub.ProjectGroupActionStub;
import org.apache.maven.continuum.web.bean.ProjectGroupUserBean;
import org.codehaus.plexus.redback.rbac.RBACManager;
import org.codehaus.plexus.redback.rbac.Role;
import org.codehaus.plexus.redback.rbac.UserAssignment;
import org.codehaus.plexus.redback.rbac.jdo.JdoRole;
import org.codehaus.plexus.redback.rbac.jdo.JdoUserAssignment;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProjectGroupActionTest
    extends AbstractActionTest
{
    private ProjectGroupActionStub action;

    private Continuum continuum;

    private RBACManager rbac;

    @Before
    public void setUp()
        throws Exception
    {
        continuum = mock( Continuum.class );
        rbac = mock( RBACManager.class );

        action = new ProjectGroupActionStub();
        action.setContinuum( continuum );
        action.setRbacManager( rbac );
    }

    @Test
    public void testViewMembersWithProjectAdminRole()
        throws Exception
    {
        ProjectGroup group = new ProjectGroup();
        group.setName( "Project A" );

        List<Role> roles = new ArrayList<Role>();
        Role role1 = new JdoRole();
        role1.setName( "Project User - Project A" );
        roles.add( role1 );

        Role role2 = new JdoRole();
        role2.setName( "Continuum Manage Scheduling" );
        roles.add( role2 );

        Role role3 = new JdoRole();
        role3.setName( "Project Developer - Project A" );
        roles.add( role3 );

        Role role4 = new JdoRole();
        role4.setName( "Project Administrator - Project A" );
        roles.add( role4 );

        List<UserAssignment> userAssignments = new ArrayList<UserAssignment>();
        UserAssignment ua1 = new JdoUserAssignment();
        ua1.setPrincipal( "user1" );
        userAssignments.add( ua1 );

        List<Role> eRoles = roles;

        when( continuum.getProjectGroupWithProjects( anyInt() ) ).thenReturn( group );
        when( rbac.getAllRoles() ).thenReturn( roles );
        when( rbac.getUserAssignmentsForRoles( anyCollection() ) ).thenReturn( userAssignments );
        when( rbac.getEffectivelyAssignedRoles( anyString() ) ).thenReturn( eRoles );

        assertEquals( Action.SUCCESS, action.members() );

        List<ProjectGroupUserBean> users = action.getProjectGroupUsers();
        assertEquals( 1, users.size() );
        assertTrue( users.get( 0 ).isAdministrator() );
        assertTrue( users.get( 0 ).isDeveloper() );
        assertTrue( users.get( 0 ).isUser() );
    }

    @Test
    public void testViewMembersWithProjectUserRole()
        throws Exception
    {
        ProjectGroup group = new ProjectGroup();
        group.setName( "Project A" );

        List<Role> roles = new ArrayList<Role>();
        Role role1 = new JdoRole();
        role1.setName( "Project User - Project A" );
        roles.add( role1 );

        Role role2 = new JdoRole();
        role2.setName( "Continuum Manage Scheduling" );
        roles.add( role2 );

        Role role3 = new JdoRole();
        role3.setName( "Project Developer - test-group" );
        roles.add( role3 );

        Role role4 = new JdoRole();
        role4.setName( "Project Administrator - test-group" );
        roles.add( role4 );

        Role role5 = new JdoRole();
        role5.setName( "Project Administrator - Project C" );
        roles.add( role5 );

        List<UserAssignment> userAssignments = new ArrayList<UserAssignment>();
        UserAssignment ua1 = new JdoUserAssignment();
        ua1.setPrincipal( "user1" );
        userAssignments.add( ua1 );

        List<Role> eRoles = new ArrayList<Role>();
        eRoles.add( role1 );
        eRoles.add( role2 );
        eRoles.add( role5 );

        when( continuum.getProjectGroupWithProjects( anyInt() ) ).thenReturn( group );
        when( rbac.getAllRoles() ).thenReturn( roles );
        when( rbac.getUserAssignmentsForRoles( anyCollection() ) ).thenReturn( userAssignments );
        when( rbac.getEffectivelyAssignedRoles( anyString() ) ).thenReturn( eRoles );

        assertEquals( Action.SUCCESS, action.members() );

        List<ProjectGroupUserBean> users = action.getProjectGroupUsers();
        assertEquals( 1, users.size() );
        assertFalse( users.get( 0 ).isAdministrator() );
        assertFalse( users.get( 0 ).isDeveloper() );
        assertTrue( users.get( 0 ).isUser() );
    }
}
