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

<%@ taglib prefix="s" uri="/struts-tags" %>
<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="continuum" prefix="c1" %>
=======
>>>>>>> refs/remotes/apache/trunk
<html>
<s:i18n name="localization.Continuum">
<head>
<<<<<<< HEAD
  <title>Configure Appearance</title>
  <s:head/>
=======
  <title><s:text name="appearance.page.title"/></title>
>>>>>>> refs/remotes/apache/trunk
</head>

<body>

<div class="h3">
<h3><s:text name="appearance.companyDetails"/></h3>

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

<div style="float: right">
<<<<<<< HEAD
  <a href="<s:url action='editAppearance' />">Edit</a>
=======
  <a href="<s:url action='editAppearance' />"><s:text name="edit"/></a>
>>>>>>> refs/remotes/apache/trunk
</div>

<p>
  <s:text name="appearance.description"/>
</p>

<s:set name="companyPom" value="companyPom"/>

<<<<<<< HEAD
<c:if test="${empty (companyPom.groupId) || empty (companyPom.artifactId)}">
  <p>
    You have not yet specified a company POM. <a href="<s:url action='editAppearance' />">Select a Company POM</a>
  </p>
</c:if>
=======
<s:if test="companyPom.groupId.length() > 0 && companyPom.artifactId.length() > 0">
>>>>>>> refs/remotes/apache/trunk

  <p>
<<<<<<< HEAD
    Your selected company POM is below. If you would like to change the organization name, url or logo, you can
    <a href="<s:url action='editCompanyPom'/>">edit the POM</a>.
=======
    <s:text name="appearance.detailsIntroduction"/> <s:text name="appearance.maybeChange"/>
    <a href="<s:url action='editCompanyPom'/>"><s:text name="appearance.editThePomLink"/></a>.
>>>>>>> refs/remotes/apache/trunk
  </p>

  <s:set name="companyModel" value="companyModel"/>
  <table>
<<<<<<< HEAD
    <s:label name="companyPom.groupId" label="Group ID"/>
    <s:label name="companyPom.artifactId" label="Artifact ID"/>
    <c:if test="${companyModel != null}">
      <s:label name="companyModel.version" label="Version"/>
    </c:if>
  </table>

  <div style="float: right">
    <a href="<s:url action='editCompanyPom' />">Edit Company POM</a>
=======
    <s:label name="companyPom.groupId" label="%{getText('appearance.companyPom.groupId')}"/>
    <s:label name="companyPom.artifactId" label="%{getText('appearance.companyPom.artifactId')}"/>
    <s:if test="companyModel != null">
      <s:label name="companyModel.version" label="%{getText('appearance.companyPom.version')}"/>
    </s:if>
  </table>

  <div style="float: right">
    <a href="<s:url action='editCompanyPom' />"><s:text name="appearance.editCompanyPom"/></a>
>>>>>>> refs/remotes/apache/trunk
  </div>
  <h3><s:text name="appearance.companyPom.section.title"/></h3>

    <s:if test="companyModel != null">
      <table>
        <tr>
          <th><s:text name="appearance.companyPom.organizationName.label"/></th>
          <td><s:property value="companyModel.organization.name"/></td>
        </tr>
        <tr>
          <th><s:text name="appearance.companyPom.organizationUrl.label"/></th>
          <s:set var="companyOrgUrl" value="companyModel.organization.url" />
          <td><s:a href="%{#companyOrgUrl}" target="_blank">
            <code><s:property value="#companyOrgUrl"/></code>
          </s:a></td>
        </tr>
        <tr>
          <th><s:text name="appearance.companyPom.organizationLogoUrl.label"/></th>
          <td>
            <code><s:property value="companyModel.properties['organization.logo']"/></code>
          </td>
        </tr>
      </table>
<<<<<<< HEAD
    </c:when>
    <c:otherwise>
      Company POM '${companyPom.groupId}:${companyPom.artifactId}' doesn't exist.
      <a href="<s:url action='editCompanyPom' />">Create company POM</a>
    </c:otherwise>
  </c:choose>
</c:if>
<s:form action="saveFooter!saveFooter.action" method="get" namespace="/admin">
=======
    </s:if>
    <s:else>
      <s:text name="appearance.companyPomDoesNotExist">
        <s:param>
          <s:property value="companyPom.groupId + ':' + companyPom.artifactId"/>
        </s:param>
      </s:text>
      <a href="<s:url action='editCompanyPom' />"><s:text name="appearance.createCompanyPom"/></a>
    </s:else>
</s:if>
<s:else>
    <p>
      <s:text name="appearance.noCompanyPom"/> <a href="<s:url action='editAppearance' />"><s:text name="appearance.selectCompanyPom"/></a>
    </p>
</s:else>
</div>

<s:form action="saveFooter" method="post" namespace="/admin">
  <s:token/>
>>>>>>> refs/remotes/apache/trunk
  <div id="axial" class="h3">
    <h3><s:text name="appearance.footerContent"/></h3>
    <div class="axial">
      <table>
        <tbody>  
<<<<<<< HEAD
          <s:textarea cols="120" rows="3" label="HTML Content" name="footer" />
=======
          <s:textarea cols="120" rows="3" label="%{getText('appearance.htmlContent.label')}" name="footer" />
>>>>>>> refs/remotes/apache/trunk
        </tbody>
      </table>
      <div class="functnbar3">
        <s:submit value="%{getText('save')}" theme="simple"/>
        <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
      </div>      
    </div>
  </div>
</s:form>
</body>
</s:i18n>
</html>
