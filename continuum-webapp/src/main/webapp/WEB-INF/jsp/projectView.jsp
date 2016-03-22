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
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib uri="http://plexus.codehaus.org/redback/taglib-1.0" prefix="redback" %>

<html>
  <s:i18n name="localization.Continuum">
    <head>
        <title><s:text name="projectView.page.title"/></title>
    </head>
    <body>
      <div id="h3">

        <jsp:include page="/WEB-INF/jsp/navigations/ProjectMenu.jsp">
          <jsp:param name="tab" value="view"/>
        </jsp:include>

        <h3><s:text name="projectView.section.title"><s:param value="project.name"/></s:text></h3>

        <s:if test="hasActionErrors()">
          <div class="errormessage">
            <s:actionerror/>
          </div>
        </s:if>
        <s:if test="hasActionMessages()">
          <div class="warningmessage">
            <s:actionmessage/>
          </div>
        </s:if>

        <div class="axial">
          <table border="1" cellspacing="2" cellpadding="3" width="100%">
            <tr class="b">
              <th><label class="label"><s:text name='projectView.project.name'/>:</label></th>
              <td><s:property value="project.name"/></td>
            </tr>
            <tr class="b">
              <th><label class="label"><s:text name='projectView.project.description'/>:</label></th>
              <td><s:property value="project.description"/></td>
            </tr>
            <tr class="b">
              <th><label class="label"><s:text name='projectView.project.version'/>:</label></th>
              <td><s:property value="project.version"/></td>
            </tr>
            <tr class="b">
              <th><label class="label"><s:text name='projectView.project.scmUrl'/>:</label></th>
              <td><s:property value="project.scmUrl"/></td>
            </tr>
            <tr class="b">
              <th><label class="label"><s:text name='projectView.project.scmTag'/>:</label></th>
              <td><s:property value="project.scmTag"/></td>
            </tr>
            <s:url id="projectGroupSummaryUrl" action="projectGroupSummary">
                <s:param name="projectGroupId" value="project.projectGroup.id"/>
            </s:url>
            <tr class="b">
              <th><label class="label"><s:text name='projectView.project.group'/>:</label></th>
              <td><a href="${projectGroupSummaryUrl}"><s:property value="project.projectGroup.name"/></a></td>
            </tr>
            <tr class="b">
              <th><label class="label"><s:text name='projectView.project.lastBuildDateTime'/>:</label></th>
              <td><s:date name="timeToDate(mapZeroTime(latestResult.endTime))"/></td>
            </tr>
            <tr class="b">
              <th><label class="label"><s:text name='buildResults.result'/>:</label></th>
              <td><s:property value="resultIcon(latestResult)" escape="false"/></td>
            </tr>
          </table>

          <redback:ifAuthorized permission="continuum-modify-group" resource="${project.projectGroup.name}">
          <div class="functnbar3">
            <table>
              <tbody>
              <tr>
                <td>
                  <s:form action="projectEdit" theme="simple">
                    <s:hidden name="projectId" />
                    <input type="submit" name="edit-project" value="<s:text name="edit"/>"/>
                  </s:form>
                </td>
                <td>
                  <s:form action="buildProjectViaProject" theme="simple">
                    <s:hidden name="projectId" />
                    <s:submit name="build-project" value="%{getText('summary.buildNow')}"/>
                  </s:form>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          </redback:ifAuthorized>
        </div>

        <h3><s:text name="projectView.buildDefinitions"/></h3>

        <s:action name="buildDefinitionSummary" id="summary" namespace="component" executeResult="true">
          <s:param name="projectId" value="project.id"/>
          <s:param name="projectGroupId" value="project.projectGroup.id"/>
        </s:action>

        <div class="functnbar3">
           <redback:ifAuthorized permission="continuum-modify-group" resource="${project.projectGroup.name}">
          <s:form action="buildDefinition" method="post">
            <input type="hidden" name="projectId" value="<s:property value="project.id"/>"/>
            <input type="hidden" name="projectGroupId" value="<s:property value="project.projectGroup.id"/>"/>
            <s:submit value="%{getText('add')}" theme="simple"/>
          </s:form>
          </redback:ifAuthorized>
        </div>

        <h3><s:text name="projectView.notifiers"/></h3>
        <s:if test="project.notifiers.size() > 0">
          <s:set name="notifiers" value="project.notifiers" scope="request"/>
          <ec:table items="notifiers"
                    var="notifier"
                    autoIncludeParameters="false"
                    showExports="false"
                    showPagination="false"
                    showStatusBar="false"
                    filterable="false"
                    sortable="false">
            <ec:row>
              <ec:column property="type" title="projectView.notifier.type"/>
              <ec:column property="recipient" title="projectView.notifier.recipient" cell="org.apache.maven.continuum.web.view.projectview.NotifierRecipientCell"/>
              <ec:column property="events" title="projectView.notifier.events" cell="org.apache.maven.continuum.web.view.projectview.NotifierEventCell"/>
              <ec:column property="from" title="projectView.notifier.from" cell="org.apache.maven.continuum.web.view.projectview.NotifierFromCell"/>
              <ec:column property="editAction" title="&nbsp;" width="1%">
                <redback:ifAuthorized permission="continuum-modify-group" resource="${project.projectGroup.name}">
                  <s:if test="!#attr['notifier'].fromProject">
                    <s:url id="editUrl" action="editProjectNotifier" namespace="/" includeParams="none">
                      <s:param name="notifierId" value="#attr['notifier'].id"/>
                      <s:param name="projectId" value="project.id"/>
                      <s:param name="projectGroupId" value="project.projectGroup.id"/>
                      <s:param name="notifierType" value="#attr['notifier'].type"/>
                    </s:url>
                    <s:a href="%{editUrl}">
                      <img src="<s:url value='/images/edit.gif' includeParams="none"/>" alt="<s:text name="edit"/>" title="<s:text name="edit"/>" border="0">
                    </s:a>
                  </s:if>
                  <s:else>
                    <img src="<s:url value='/images/edit_disabled.gif' includeParams="none"/>" alt="<s:text name='edit'/>" title="<s:text name='edit'/>" border="0" />
                  </s:else>
                </redback:ifAuthorized>
                <redback:elseAuthorized>
                  <img src="<s:url value='/images/edit_disabled.gif' includeParams="none"/>" alt="<s:text name='edit'/>" title="<s:text name='edit'/>" border="0" />
                </redback:elseAuthorized>
              </ec:column>
              <ec:column property="deleteAction" title="&nbsp;" width="1%">
                <redback:ifAuthorized permission="continuum-modify-group" resource="${project.projectGroup.name}">
                  <s:if test="!#attr['notifier'].fromProject">
                    <s:url id="removeUrl" action="deleteProjectNotifier_default" namespace="/">
                      <s:param name="projectId" value="project.id"/>
                      <s:param name="projectGroupId" value="project.projectGroup.id"/>
                      <s:param name="notifierType" value="#attr['notifier'].type"/>
                      <s:param name="notifierId" value="#attr['notifier'].id"/>
                    </s:url>
                    <s:a href="%{removeUrl}">
                      <img src="<s:url value='/images/delete.gif' includeParams="none"/>" alt="<s:text name="delete"/>" title="<s:text name="delete"/>" border="0">
                    </s:a>
                  </s:if>
                  <s:else>
                    <img src="<s:url value='/images/delete_disabled.gif' includeParams="none"/>" alt="<s:text name='edit'/>" title="<s:text name='edit'/>" border="0" />
                  </s:else>
                </redback:ifAuthorized>
                <redback:elseAuthorized>
                  <img src="<s:url value='/images/delete_disabled.gif' includeParams="none"/>" alt="<s:text name='edit'/>" title="<s:text name='edit'/>" border="0" />
                </redback:elseAuthorized>
              </ec:column>
            </ec:row>
          </ec:table>
        </s:if>
        <div class="functnbar3">
           <redback:ifAuthorized permission="continuum-modify-group" resource="${project.projectGroup.name}">
          <s:form action="addProjectNotifier" method="post">
            <input type="hidden" name="projectId" value="<s:property value="project.id"/>"/>
            <input type="hidden" name="projectGroupId" value="<s:property value="project.projectGroup.id"/>"/>
            <s:submit value="%{getText('add')}" theme="simple"/>
          </s:form>
          </redback:ifAuthorized>
        </div>

        <h3><s:text name="projectView.dependencies"/></h3>
        <s:set name="dependencies" value="project.dependencies" scope="request"/>
        <ec:table items="dependencies"
                  var="dep"
                  autoIncludeParameters="false"
                  showExports="false"
                  showPagination="false"
                  showStatusBar="false"
                  filterable="false"
                  sortable="false">
          <ec:row>
            <ec:column property="groupId" title="projectView.dependency.groupId"/>
            <ec:column property="artifactId" title="projectView.dependency.artifactId"/>
            <ec:column property="version" title="projectView.dependency.version"/>
          </ec:row>
        </ec:table>

        <h3><s:text name="projectView.developers"/></h3>
        <s:set name="developers" value="project.developers" scope="request"/>
        <ec:table items="developers"
                  autoIncludeParameters="false"
                  showExports="false"
                  showPagination="false"
                  showStatusBar="false"
                  filterable="false"
                  sortable="false">
          <ec:row>
            <ec:column property="name" title="projectView.developer.name"/>
            <ec:column property="email" title="projectView.developer.email"/>
          </ec:row>
        </ec:table>

      </div>
    </body>
  </s:i18n>
</html>
