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
<html>
<s:i18n name="localization.Continuum">
<head>
<<<<<<< HEAD
  <title>Edit Company POM</title>
  <s:head/>
=======
  <title><s:text name="companyPom.page.title"/></title>
>>>>>>> refs/remotes/apache/trunk
</head>

<body>
<h3><s:text name="companyPom.section.title"/></h3>

<s:actionmessage/>
<<<<<<< HEAD
<s:form method="post" action="saveCompanyPom" namespace="/admin" validate="true" theme="xhtml">
  <s:label name="companyModel.groupId" label="Group ID"/>
  <s:label name="companyModel.artifactId" label="Artifact ID"/>
  <tr>
    <td>Version</td>
    <td>
      <s:property value="companyModel.version"/>
      <i>(The version will automatically be incremented when you save this form)</i>
    </td>
  </tr>
=======
<s:form method="post" action="saveCompanyPom" namespace="/admin" validate="true">
  <s:token/>
  <s:label name="companyModel.groupId" label="%{getText('appearance.companyPom.groupId')}"/>
  <s:label name="companyModel.artifactId" label="%{getText('appearance.companyPom.artifactId')}"/>
  <s:label name="companyModel.version" label="%{getText('appearance.companyPom.version')}">
    <s:param name="after">
      &nbsp;<i>(<s:text name="companyPom.autoIncrementVersion"/>)</i>
    </s:param>
  </s:label>
>>>>>>> refs/remotes/apache/trunk
  <tr>
    <td></td>
    <td><h2><s:text name="companyPom.organization"/></h2></td>
  </tr>
<<<<<<< HEAD
  <s:textfield name="companyModel.organization.name" size="40" label="Name"/>
  <s:textfield name="companyModel.organization.url" size="70" label="URL"/>
  <%-- TODO: how to get it to be a string, not a String[]? --%>
  <s:textfield name="companyModel.properties['organization.logo']" size="70" label="Logo URL"/>
  <s:submit value="Save"/>
=======
  <s:textfield name="companyModel.organization.name" size="40" label="%{getText('appearance.companyPom.organizationName.label')}"/>
  <s:textfield name="companyModel.organization.url" size="70" label="%{getText('appearance.companyPom.organizationUrl.label')}"/>
  <s:textfield name="organizationLogo" size="70" label="%{getText('appearance.companyPom.organizationLogoUrl.label')}"/>
  <s:submit value="%{getText('save')}"/>
>>>>>>> refs/remotes/apache/trunk
</s:form>

</body>
</s:i18n>
</html>
