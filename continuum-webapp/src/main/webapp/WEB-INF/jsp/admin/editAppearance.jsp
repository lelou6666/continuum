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
  <title>Configure Appearance</title>
  <s:head/>
=======
  <title><s:text name="appearance.page.title"/></title>
>>>>>>> refs/remotes/apache/trunk
</head>

<body>
  <div class="h3">
    <h3><s:text name="appearance.companyDetails"/></h3>

    <p>
      <s:text name="appearance.enterCompanyPom"/>
    </p>

<<<<<<< HEAD
  <p>
    Enter the details of the company super POM below. If it exists, the organization name, URL and logo will be read
    from it.
  </p>

  <s:actionmessage/>
  <s:form method="post" action="saveAppearance" namespace="/admin" validate="true" theme="xhtml">
    <s:textfield name="companyPom.groupId" label="Group ID"/>
    <s:textfield name="companyPom.artifactId" label="Artifact ID"/>
    <s:submit value="Save"/>
  </s:form>
=======
    <s:actionmessage/>
    <s:form method="post" action="saveAppearance" namespace="/admin" validate="true">
      <s:textfield name="companyPom.groupId" label="%{getText('appearance.companyPom.groupId')}" size="100"/>
      <s:textfield name="companyPom.artifactId" label="%{getText('appearance.companyPom.artifactId')}" size="100"/>
      <s:submit value="%{getText('save')}"/>
    </s:form>
  </div>
>>>>>>> refs/remotes/apache/trunk
</body>
</s:i18n>
</html>
