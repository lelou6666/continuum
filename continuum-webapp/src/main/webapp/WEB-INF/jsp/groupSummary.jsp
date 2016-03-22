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
    <title><s:text name="groups.page.title"/></title>
    <meta http-equiv="refresh" content="300"/>
  </head>

  <body>
  <div id="h3">

<<<<<<< HEAD
    <s:if test="infoMessage != null">
       <p>${infoMessage}</p>
    </s:if>
    <s:else>
       <h3><s:text name="groups.page.section.title"/></h3>
    </s:else>
  
    <c:if test="${empty groups}">
      <s:text name="groups.page.list.empty"/>
    </c:if>

    <c:if test="${not empty groups}">
=======
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

    <s:if test="infoMessage != null">
       <p><s:property value="infoMessage"/></p>
    </s:if>
    <s:else>
       <h3><s:text name="groups.page.section.title"/></h3>
    </s:else>
>>>>>>> refs/remotes/apache/trunk

    <s:if test="groups == null || groups.size() == 0">
      <s:text name="groups.page.list.empty"/>
    </s:if>
    <s:else>
    <ec:table items="groups"
              var="group"
              autoIncludeParameters="false"
              showExports="false"
              showPagination="false"
              showStatusBar="false"
              sortable="false"
              filterable="false">
      <ec:row highlightRow="true">
<<<<<<< HEAD
        <ec:column property="name" title="groups.table.name" width="20%" style="white-space: nowrap">
          <a href="<s:url  action="projectGroupSummary" namespace="/"><s:param name="projectGroupId">${group.id}</s:param></s:url>">${group.name}</a>
        </ec:column>
        <ec:column property="groupId" title="groups.table.groupId" width="20%"/>
        <ec:column property="repositoryName" title="groups.table.repositoryName" width="20%">
          <redback:ifAuthorized permission="continuum-manage-repositories">
            <s:url id="editRepositoryUrl" action="editRepository" namespace="/admin" includeParams="none">
              <s:param name="repository.id">${pageScope.group.repositoryId}</s:param>
            </s:url>
            <s:a href="%{editRepositoryUrl}">${pageScope.group.repositoryName}</s:a>
          </redback:ifAuthorized>
          <redback:elseAuthorized>
            ${pageScope.group.repositoryName}
          </redback:elseAuthorized>
=======
        <ec:column property="name" title="groups.table.name" width="40%" style="white-space: nowrap">
          <s:url id="projectGroupSummaryUrl" action="projectGroupSummary" namespace="/">
            <s:param name="projectGroupId" value="#attr['group'].id" />
          </s:url>
          <a href="${projectGroupSummaryUrl}"><s:property value="#attr['group'].name"/></a>
>>>>>>> refs/remotes/apache/trunk
        </ec:column>
        <ec:column property="groupId" title="groups.table.groupId" width="40%"/>
        <ec:column property="buildGroupNowAction" title="&nbsp;" width="1%">
          <redback:ifAuthorized permission="continuum-build-group" resource="${group.name}">
            <s:url id="buildProjectGroupUrl" action="buildProjectGroup" namespace="/" includeParams="none">
<<<<<<< HEAD
              <s:param name="projectGroupId">${group.id}</s:param>
=======
              <s:param name="projectGroupId" value="#attr['group'].id" />
>>>>>>> refs/remotes/apache/trunk
              <s:param name="buildDefinitionId" value="-1"/>
              <s:param name="fromSummaryPage" value="true"/>
            </s:url>
            <s:a href="%{buildProjectGroupUrl}">
              <img src="<s:url value='/images/buildnow.gif'/>" alt="<s:text name="projectGroup.buildGroup"/>" title="<s:text name="projectGroup.buildGroup"/>" border="0">
            </s:a>
          </redback:ifAuthorized>
          <redback:elseAuthorized>
            <img src="<s:url value='/images/buildnow_disabled.gif'/>" alt="<s:text name="projectGroup.buildGroup"/>" title="<s:text name="projectGroup.buildGroup"/>" border="0">
          </redback:elseAuthorized>
        </ec:column>
        <ec:column property="releaseProjectGroupAction" title="&nbsp;" width="1%">
          <redback:ifAuthorized permission="continuum-build-group" resource="${group.name}">
            <s:url id="releaseProjectGroupUrl" action="releaseProjectGroup" namespace="/" includeParams="none">
<<<<<<< HEAD
              <s:param name="projectGroupId">${group.id}</s:param>
=======
              <s:param name="projectGroupId" value="#attr['group'].id"/>
>>>>>>> refs/remotes/apache/trunk
            </s:url>
            <s:a href="%{releaseProjectGroupUrl}">
              <img src="<s:url value='/images/releaseproject.gif'/>" alt="<s:text name="projectGroup.releaseNow"/>" title="<s:text name="projectGroup.releaseNow"/>" border="0">
            </s:a>
          </redback:ifAuthorized>
          <redback:elseAuthorized>
            <img src="<s:url value='/images/releaseproject_disabled.gif'/>" alt="<s:text name="projectGroup.releaseNow"/>" title="<s:text name="projectGroup.releaseNow"/>" border="0">
          </redback:elseAuthorized>
        </ec:column>
        <ec:column property="removeProjectGroupAction" title="&nbsp;" width="1%">
          <redback:ifAuthorized permission="continuum-remove-group" resource="${group.name}">
<<<<<<< HEAD
            <s:url id="removeProjectGroupUrl" action="removeProjectGroup" namespace="/" includeParams="none">
              <s:param name="projectGroupId">${group.id}</s:param>
=======
            <s:set var="tname" value="'remProjectToken' + #attr['group'].id" scope="page"/>
            <s:token name="%{#attr['tname']}"/>
            <s:url id="removeProjectGroupUrl" action="confirmRemoveProjectGroup" namespace="/" includeParams="none">
              <s:param name="projectGroupId" value="#attr['group'].id"/>
              <s:param name="struts.token.name" value="#attr['tname']"/>
              <s:param name="%{#attr['tname']}" value="#session['struts.tokens.' + #attr['tname']]"/>
>>>>>>> refs/remotes/apache/trunk
            </s:url>
            <s:a href="%{removeProjectGroupUrl}">
              <img src="<s:url value='/images/delete.gif'/>" alt="<s:text name="projectGroup.deleteGroup"/>" title="<s:text name="projectGroup.deleteGroup"/>" border="0">
            </s:a>
          </redback:ifAuthorized>
          <redback:elseAuthorized>
            <img src="<s:url value='/images/delete_disabled.gif'/>" alt="<s:text name="projectGroup.deleteGroup"/>" title="<s:text name="projectGroup.deleteGroup"/>" border="0">
          </redback:elseAuthorized>
        </ec:column>
        <ec:column property="numSuccesses" title="&nbsp;" format="0" width="2%" style="text-align: right" headerClass="calcHeaderSucces" calc="total" calcTitle="groups.table.summary"/>
        <ec:column property="numFailures" title="&nbsp;" format="0" width="2%" style="text-align: right" headerClass="calcHeaderFailure" calc="total" />
        <ec:column property="numErrors" title="&nbsp;" format="0" width="2%" style="text-align: right" headerClass="calcHeaderError" calc="total"/>
        <ec:column property="numProjects" title="groups.table.totalProjects" format="0" width="1%" style="text-align: right" headerStyle="text-align: center" calc="total"/>
      </ec:row>
    </ec:table>
<<<<<<< HEAD
    </c:if>
=======
    </s:else>
>>>>>>> refs/remotes/apache/trunk
    <redback:ifAuthorized permission="continuum-add-group">
      <div class="functnbar3">
        <table>
          <tr>
            <td>
<<<<<<< HEAD
              <form action="<s:url  action='addProjectGroup' method='input' namespace='/' />" method="post">
                <input type="submit" name="addProjectGroup" value="<s:text name="projectGroup.add.section.title"/>"/>
              </form>
=======
              <s:form action="addProjectGroup_input" theme="simple">
                <s:submit name="addProjectGroup" value="%{getText('projectGroup.add.section.title')}" />
              </s:form>
>>>>>>> refs/remotes/apache/trunk
            </td>
          </tr>
        </table>
      </div>
    </redback:ifAuthorized>
  </div>
  </body>
</s:i18n>
</html>
