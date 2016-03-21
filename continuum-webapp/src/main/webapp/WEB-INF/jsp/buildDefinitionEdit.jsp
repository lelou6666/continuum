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
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
  <s:i18n name="localization.Continuum">
    <head>
        <title><s:text name="buildDefinition.page.title"/></title>
    </head>
    <body>
      <div id="axial" class="h3">
        <h3><s:text name="buildDefinition.section.title"/></h3>

        <div class="axial">
          <s:form action="saveBuildDefinition" method="post">

              <s:if test="hasActionErrors()">
                <div class="errormessage">
                  <s:actionerror/>
                </div>
                <input type="button" value="Back" onClick="history.go(-1)">
              </s:if>
              <s:else>
                <table>
                  <tbody>
                    <s:if test="executor == 'ant' or buildDefinitionType == 'ant'">
                      <s:textfield label="%{getText('buildDefinition.buildFile.ant.label')}" name="buildFile"  requiredLabel="true" size="100"/>
                    </s:if>
                    <s:elseif test="executor == 'shell' or buildDefinitionType == 'shell'">
                      <s:textfield label="%{getText('buildDefinition.buildFile.shell.label')}" name="buildFile" requiredLabel="true" size="100"/>
                    </s:elseif>
                    <s:else>
                      <s:textfield label="%{getText('buildDefinition.buildFile.maven.label')}" name="buildFile" requiredLabel="true" size="100"/>
                    </s:else>
    
                    <s:if test="executor == 'ant' or buildDefinitionType == 'ant'">
                      <s:textfield label="%{getText('buildDefinition.goals.ant.label')}" name="goals" size="100"/>
                    </s:if>
                    <s:elseif test="executor == 'shell' or buildDefinitionType == 'shell'">
                    </s:elseif>
                    <s:else>
                      <s:textfield label="%{getText('buildDefinition.goals.maven.label')}" name="goals" requiredLabel="true" size="100"/>
                    </s:else>
    
                    <s:textfield label="%{getText('buildDefinition.arguments.label')}" name="arguments" size="100"/>
                    <s:checkbox label="%{getText('buildDefinition.buildFresh.label')}" id="buildFresh" name="buildFresh" value="buildFresh" fieldValue="true"/>
                    <s:checkbox label="%{getText('buildDefinition.alwaysBuild.label')}" id="alwaysBuild" name="alwaysBuild" value="alwaysBuild" fieldValue="true"/>

                    <s:if test="defaultBuildDefinition">
                      <s:label label="%{getText('buildDefinition.defaultForProject.label')}" value="true"/>
                    </s:if>
                    <s:else>
                      <s:checkbox label="%{getText('buildDefinition.defaultForProject.label')}" name="defaultBuildDefinition" value="defaultBuildDefinition" fieldValue="true"/>
                    </s:else>

                    <s:select label="%{getText('buildDefinition.schedule.label')}" name="scheduleId" list="schedules"/>
                    <s:select label="%{getText('buildDefinition.profile.label')}" name="profileId" list="profiles" listValue="name"
                               listKey="id" headerKey="-1" headerValue=""/>
                    <s:select label="%{getText('buildDefinition.type.label')}" name="buildDefinitionType" list="buildDefinitionTypes"/>
                    <s:if test="executor != 'ant' && executor != 'shell'">
                        <s:select label="%{getText('buildDefinition.updatePolicy.label')}" name="updatePolicy" list="buildDefinitionUpdatePolicies"/>
                    </s:if>
                    <s:textfield label="%{getText('buildDefinition.description.label')}" name="description" size="100" />
                  </tbody>
                </table>
                <div class="functnbar3">
                  <s:submit value="%{getText('save')}" theme="simple"/>
                  <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
                </div>

                <s:hidden name="buildDefinitionId"/>
                <s:hidden name="projectId"/>
                <s:hidden name="projectGroupId"/>
                <s:hidden name="groupBuildDefinition"/>
                <s:if test="groupBuildView">
                  <s:hidden name="groupBuildView" value="true"/>
                </s:if>
                <s:if test="defaultBuildDefinition">
                  <s:hidden name="defaultBuildDefinition" value="true"/>
                </s:if>
              </s:else>
          </s:form>
        </div>
      </div>
    </body>
  </s:i18n>
</html>
