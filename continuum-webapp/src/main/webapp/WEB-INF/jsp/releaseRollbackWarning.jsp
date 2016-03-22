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
    </head>
    <body>
      <h3>
<<<<<<< HEAD
        <s:text name="releaseProject.section.title">
          <s:param><s:property value="projectName"/></s:param>
        </s:text>
=======
        <s:text name="releaseProject.rollbackWarning.section.title"/>
>>>>>>> refs/remotes/apache/trunk
      </h3>
      <p><s:text name="releaseProject.rollbackWarning"/></p>
      <table>
        <tr>
          <td>
            <s:form action="releaseRollback" method="post">
              <s:hidden name="projectId"/>
              <s:hidden name="releaseId"/>
<<<<<<< HEAD
              <s:submit value="Rollback Changes"/>
=======
              <s:submit value="%{getText('rollback')}" theme="simple"/>
>>>>>>> refs/remotes/apache/trunk
            </s:form>
          </td>
          <td>
            <s:form action="releaseInProgress" method="post">
              <s:hidden name="projectId"/>
              <s:hidden name="releaseId"/>
              <s:hidden name="releaseGoal"/>
<<<<<<< HEAD
              <s:submit value="Cancel"/>
=======
              <s:submit value="%{getText('cancel')}" theme="simple"/>
>>>>>>> refs/remotes/apache/trunk
            </s:form>
          </td>
        </tr>
      </table>
    </body>
  </s:i18n>
</html>
