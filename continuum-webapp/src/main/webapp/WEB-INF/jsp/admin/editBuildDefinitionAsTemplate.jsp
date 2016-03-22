<%--
  ~ Licensed to the Apache Software Foundation (ASF) under one
  ~ or more contributor license agreements.  See the NOTICE file
  ~ distributed with this work for additional information
  ~ regarding copyright ownership.  The ASF licenses this file
  ~ to you under the Apache License, Version 2.0 (the
  ~ "License"); you may not use this file except in compliance
  ~ with the License.  You may obtain a copy of the License at
  ~
  ~   http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing,
  ~ software distributed under the License is distributed on an
  ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  ~ KIND, either express or implied.  See the License for the
  ~ specific language governing permissions and limitations
  ~ under the License.
  --%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<<<<<<< HEAD
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="continuum" prefix="c1" %>
=======
>>>>>>> refs/remotes/apache/trunk
<html>
  <s:i18n name="localization.Continuum">
    <head>
        <title><s:text name="buildDefinition.template.page.title"/></title>
    </head>
    <body>
      <div id="axial" class="h3">
        <h3><s:text name="buildDefinition.template.section.title"/></h3>
<<<<<<< HEAD

        <div class="axial">
          <s:form action="saveBuildDefinitionAsTemplate" method="post" validate="true">
            <c:choose>
            
              <c:when test="${!empty actionErrors}">
                <div class="errormessage">
                  <s:iterator value="actionErrors">
                    <p><s:text name="<s:property/>" /></p>
                  </s:iterator>
                </div>
                <input type="button" value="Back" onClick="history.go(-1)">
              </c:when>
  
              <c:when test="${empty actionErrors}">
                <table>
                  <tbody>
                    <s:if test="buildDefinition.type == 'ant'">
                      <s:textfield label="%{getText('buildDefinition.buildFile.ant.label')}" name="buildDefinition.buildFile"  required="true"/>
                    </s:if>
                    <s:elseif test="buildDefinition.type == 'shell'">
                      <s:textfield label="%{getText('buildDefinition.buildFile.shell.label')}" name="buildDefinition.buildFile" required="true"/>
                    </s:elseif>
                    <s:else>
                      <s:textfield label="%{getText('buildDefinition.buildFile.maven.label')}" name="buildDefinition.buildFile" required="true"/>
                    </s:else>
    
                    <s:if test="buildDefinition.type == 'ant'">
                      <s:textfield label="%{getText('buildDefinition.goals.ant.label')}" name="buildDefinition.goals"/>
=======

        <s:if test="hasActionErrors()">
          <div class="errormessage">
            <s:actionerror/>
          </div>
          <input type="button" value="Back" onClick="history.go(-1)">
        </s:if>
        <s:if test="hasActionMessages()">
          <div class="warningmessage">
            <s:actionmessage/>
          </div>
        </s:if>

        <div class="axial">
          <s:form action="saveBuildDefinitionAsTemplate" method="post" validate="true">
              <s:if test="!hasActionErrors()">
                <table>
                  <tbody>
                    <s:if test="buildDefinition.type == 'ant'">
                      <s:textfield label="%{getText('buildDefinition.buildFile.ant.label')}" name="buildDefinition.buildFile"  requiredLabel="true" size="100"/>
                    </s:if>
                    <s:elseif test="buildDefinition.type == 'shell'">
                      <s:textfield label="%{getText('buildDefinition.buildFile.shell.label')}" name="buildDefinition.buildFile" requiredLabel="true" size="100"/>
                    </s:elseif>
                    <s:else>
                      <s:textfield label="%{getText('buildDefinition.buildFile.maven.label')}" name="buildDefinition.buildFile" requiredLabel="true" size="100"/>
                    </s:else>
    
                    <s:if test="buildDefinition.type == 'ant'">
                      <s:textfield label="%{getText('buildDefinition.goals.ant.label')}" name="buildDefinition.goals" size="100"/>
>>>>>>> refs/remotes/apache/trunk
                    </s:if>
                    <s:elseif test="buildDefinition.type == 'shell'">
                    </s:elseif>
                    <s:else>
<<<<<<< HEAD
                      <s:textfield label="%{getText('buildDefinition.goals.maven.label')}" name="buildDefinition.goals"/>
                    </s:else>
    
                    <s:textfield label="%{getText('buildDefinition.arguments.label')}" name="buildDefinition.arguments"/>
=======
                      <s:textfield label="%{getText('buildDefinition.goals.maven.label')}" name="buildDefinition.goals" requiredLabel="true" size="100"/>
                    </s:else>
    
                    <s:textfield label="%{getText('buildDefinition.arguments.label')}" name="buildDefinition.arguments" size="100"/>
>>>>>>> refs/remotes/apache/trunk
                    <s:checkbox label="%{getText('buildDefinition.buildFresh.label')}" name="buildDefinition.buildFresh"/>
                    <s:checkbox label="%{getText('buildDefinition.alwaysBuild.label')}" name="buildDefinition.alwaysBuild" />
                    <s:checkbox label="%{getText('buildDefinition.defaultForProject.label')}" name="buildDefinition.defaultForProject" />
                    <s:select label="%{getText('buildDefinition.schedule.label')}" name="buildDefinition.schedule.id" list="schedules" listValue="name"
                               listKey="id"/>
                    <s:if test="buildDefinition.profile == null">
                      <s:select label="%{getText('buildDefinition.profile.label')}" name="buildDefinition.profile.id" list="profiles" listValue="name"
                                 value="-1" listKey="id" headerKey="-1" headerValue=""/>
                    </s:if>
                    <s:else>
                      <s:select label="%{getText('buildDefinition.profile.label')}" name="buildDefinition.profile.id" list="profiles" listValue="name"
                                 listKey="id" headerKey="-1" headerValue=""/>
                    </s:else>
                    <s:select label="%{getText('buildDefinition.type.label')}" name="buildDefinition.type" list="buildDefinitionTypes"/>
<<<<<<< HEAD
                    <s:textfield label="%{getText('buildDefinition.description.label')}" name="buildDefinition.description" required="true"/>
=======
                    <s:if test="buildDefinition.type != 'ant' || buildDefinition.type != 'shell'">
                        <s:select label="%{getText('buildDefinition.updatePolicy.label')}" name="buildDefinition.updatePolicy" list="buildDefinitionUpdatePolicies"/>
                    </s:if>
                    <s:textfield label="%{getText('buildDefinition.description.label')}" name="buildDefinition.description" requiredLabel="true" size="100"/>
>>>>>>> refs/remotes/apache/trunk
                  </tbody>
                </table>
                <div class="functnbar3">
                  <s:submit value="%{getText('save')}" theme="simple"/>
                  <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
                </div>

                <s:hidden name="buildDefinition.id"/>
                <s:hidden name="buildDefinition.template" value="true"/>
<<<<<<< HEAD
              </c:when>
            
            </c:choose>
=======
              </s:if>
>>>>>>> refs/remotes/apache/trunk
          </s:form>
        </div>
      </div>
    </body>
  </s:i18n>
</html>
