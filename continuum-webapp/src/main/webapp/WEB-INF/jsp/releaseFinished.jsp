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
<<<<<<< HEAD
      <h2><s:text name="releaseInProgress.section.title"/></h2>
      <h3><s:property value="name"/></h3>
=======
      <h3><s:text name="releaseInProgress.section.title"/> - <s:property value="projectName"/></h3>
>>>>>>> refs/remotes/apache/trunk
      <div class="axial">
        <table class="releasePhases">
          <tr>
            <th><s:text name="releaseInProgress.status"/></th>
            <th width="100%"><s:text name="releaseInProgress.phase"/></th>
          </tr>
<<<<<<< HEAD
          <s:iterator value="listener.phases">
            <tr>
              <td>
              <s:if test="listener.completedPhases.contains( top )">
                <img src="<s:url value='/images/icon_success_sml.gif' includeParams="none"/>"
                     alt="Done" title="Done" border="0">
              </s:if>
              <s:elseif test="listener.inProgress.equals( top )">
                <s:if test="listener.error == null">
                  <img src="<s:url value='/images/building.gif' includeParams="none"/>"
                       alt="In Progress" title="In Progress" border="0">
                </s:if>
                <s:else>
                  <img src="<s:url value='/images/icon_error_sml.gif' includeParams="none"/>"
                       alt="Error" title="Error" border="0">
=======
          <s:iterator value="listenerSummary.phases">
            <tr>
              <td>
              <s:if test="listenerSummary.completedPhases.contains( top )">
                <img src="<s:url value='/images/icon_success_sml.gif' includeParams="none"/>"
                     alt="<s:text name="done"/>" title="<s:text name="done"/>" border="0">
              </s:if>
              <s:elseif test="listenerSummary.inProgress.equals( top )">
                <s:if test="listenerSummary.error == null">
                  <img src="<s:url value='/images/building.gif' includeParams="none"/>"
                       alt="<s:text name="in.progress"/>" title="<s:text name="in.progress"/>" border="0">
                </s:if>
                <s:else>
                  <img src="<s:url value='/images/icon_error_sml.gif' includeParams="none"/>"
                       alt="<s:text name="error"/>" title="<s:text name="error"/>" border="0">
>>>>>>> refs/remotes/apache/trunk
                </s:else>
              </s:elseif>
              </td>
              <td><s:property/></td>
            </tr>
          </s:iterator>
        </table>
      </div>

      <p>
        <s:url id="releaseViewResultUrl" action="releaseViewResult" namespace="/">
          <s:param name="releaseId" value="releaseId"/>
<<<<<<< HEAD
=======
          <s:param name="projectId" value="projectId"/>
>>>>>>> refs/remotes/apache/trunk
        </s:url>
        <s:a href="%{releaseViewResultUrl}"><s:text name="releaseInProgress.viewOutput"/></s:a>
      </p>

      <table>
        <tr>
          <td>
            <s:form action="releaseRollbackWarning" method="post">
              <s:hidden name="projectId"/>
              <s:hidden name="releaseId"/>
              <s:hidden name="releaseGoal"/>
<<<<<<< HEAD
              <s:submit value="Rollback Changes"/>
=======
              <s:submit value="%{getText('rollback')}" theme="simple"/>
>>>>>>> refs/remotes/apache/trunk
            </s:form>
          </td>
          <td>
            <s:form action="releaseCleanup" method="post">
              <s:hidden name="projectId"/>
              <s:hidden name="releaseId"/>
<<<<<<< HEAD
              <s:submit value="Done"/>
=======
              <s:submit value="%{getText('done')}" theme="simple"/>
>>>>>>> refs/remotes/apache/trunk
            </s:form>
          </td>
        </tr>
      </table>
    </body>
  </s:i18n>
</html>
