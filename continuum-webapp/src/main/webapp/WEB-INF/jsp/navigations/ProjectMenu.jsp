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
<s:set var="tab">${param.tab}</s:set>
<s:set var="textStyle" value="'border: 1px solid #DFDEDE; padding-left: 1em; padding-right: 1em;'"/>
<s:set var="linkStyle" value="%{#textStyle + ' text-decoration: none;'}"/>

<s:url var="projectGroupSummaryUrl" action="projectGroupSummary" includeParams="none">
  <s:param name="projectGroupId" value="project.projectGroup.id"/>
</s:url>
<s:url var="viewUrl" action="projectView" includeParams="none">
  <s:param name="projectId" value="projectId"/>
  <s:param name="projectGroupId" value="project.projectGroup.id"/>
</s:url>
<s:url var="buildResultsUrl" action="buildResults" includeParams="none">
  <s:param name="projectId" value="projectId"/>
  <s:param name="projectGroupId" value="project.projectGroup.id"/>
</s:url>
<s:url var="workingCopyUrl" action="workingCopy" includeParams="none">
  <s:param name="projectId" value="projectId"/>
  <s:param name="projectGroupId" value="project.projectGroup.id"/>
</s:url>
>>>>>>> refs/remotes/apache/trunk

<div>
  <p style="border-top: 1px solid transparent; border-bottom: 1px solid #DFDEDE;">

<<<<<<< HEAD
    <s:url id="projectGroupSummaryUrl" action="projectGroupSummary" includeParams="none">
        <s:param name="projectGroupId" value="project.projectGroup.id"/>
    </s:url>
    <s:url id="viewUrl" action="projectView" includeParams="none">
        <s:param name="projectId" value="projectId"/>
        <s:param name="tab" value="view"/>
        <s:param name="projectGroupId" value="project.projectGroup.id"/>
    </s:url>
    <s:url id="buildResultsUrl" action="buildResults" includeParams="none">
        <s:param name="projectId" value="projectId"/>
        <s:param name="tab" value="buildResults"/>
        <s:param name="projectGroupId" value="project.projectGroup.id"/>
    </s:url>
    <s:url id="workingCopyUrl" action="workingCopy" includeParams="none">
        <s:param name="projectId" value="projectId"/>
        <s:param name="tab" value="workingCopy"/>
        <s:param name="projectGroupId" value="project.projectGroup.id"/>
    </s:url>

    <a style="border: 1px solid #DFDEDE; padding-left: 1em; padding-right: 1em; text-decoration: none;" href="${projectGroupSummaryUrl}"><s:text name="projectGroup.tab.summary"/></a>
    <c:choose>
      <c:when test="${param.tab == 'view'}">
        <b style="border: 1px solid #DFDEDE; padding-left: 1em; padding-right: 1em;"><s:text name="info"/></b>
      </c:when>
      <c:otherwise>
        <a style="border: 1px solid #DFDEDE; padding-left: 1em; padding-right: 1em; text-decoration: none;" href="${viewUrl}"><s:text name="info"/></a>
      </c:otherwise>
    </c:choose>

    <c:choose>
      <c:when test="${param.tab == 'buildResults'}">
        <b style="border: 1px solid #DFDEDE; padding-left: 1em; padding-right: 1em;"><s:text name="builds"/></b>
      </c:when>
      <c:otherwise>
        <a style="border: 1px solid #DFDEDE; padding-left: 1em; padding-right: 1em; text-decoration: none;" href="${buildResultsUrl}"><s:text name="builds"/></a>
      </c:otherwise>
    </c:choose>

    <c:choose>
      <c:when test="${param.tab == 'workingCopy'}">
        <b style="border: 1px solid #DFDEDE; padding-left: 1em; padding-right: 1em;"><s:text name="workingCopy"/></b>
      </c:when>
      <c:otherwise>
        <a style="border: 1px solid #DFDEDE; padding-left: 1em; padding-right: 1em; text-decoration: none;" href="${workingCopyUrl}"><s:text name="workingCopy"/></a>
      </c:otherwise>
    </c:choose>
=======
    <s:a href="%{projectGroupSummaryUrl}" cssStyle="%{#linkStyle}"><s:text name="projectGroup.tab.summary"/></s:a>

    <s:if test="#tab == 'view'">
      <b style="<s:property value="#textStyle"/>"><s:text name="info"/></b>
    </s:if>
    <s:else>
      <s:a href="%{viewUrl}" cssStyle="%{#linkStyle}"><s:text name="info"/></s:a>
    </s:else>

    <s:if test="#tab == 'buildResults'">
      <b style="<s:property value="#textStyle"/>"><s:text name="builds"/></b>
    </s:if>
    <s:else>
      <s:a href="%{buildResultsUrl}" cssStyle="%{#linkStyle}"><s:text name="builds"/></s:a>
    </s:else>

    <s:if test="#tab == 'workingCopy'">
      <b style="<s:property value="#textStyle"/>"><s:text name="workingCopy"/></b>
    </s:if>
    <s:else>
      <s:a href="%{workingCopyUrl}" cssStyle="%{#linkStyle}"><s:text name="workingCopy"/></s:a>
    </s:else>
>>>>>>> refs/remotes/apache/trunk

  </p>
</div>
