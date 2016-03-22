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
<html>
  <s:i18n name="localization.Continuum">
    <head>
        <title><s:text name="releaseProject.page.title"/></title>
        <meta http-equiv="refresh" content="10;url=<s:url includeParams="all" />"/>
    </head>
    <body>
      <h2><s:text name="releaseInProgress.section.title"/></h2>
      <h3><s:property value="name"/></h3>
<<<<<<< HEAD
      <p>
        The release goal is currently initializing...
      </p>
      <p>
        Please wait while the server prepares your project for release.
      </p>
=======
      <p><s:text name="releaseInProgress.currently.initializing"/></p>
      <p><s:text name="releaseInProgress.please.wait"/></p>
>>>>>>> refs/remotes/apache/trunk
      <s:form action="releaseInProgress" method="get">
        <s:hidden name="projectId"/>
        <s:hidden name="releaseId"/>
        <s:hidden name="releaseGoal"/>
<<<<<<< HEAD
        <s:submit value="Refresh"/>
=======
        <s:submit value="%{getText('refresh')}"/>
>>>>>>> refs/remotes/apache/trunk
      </s:form>
    </body>
  </s:i18n>
</html>
