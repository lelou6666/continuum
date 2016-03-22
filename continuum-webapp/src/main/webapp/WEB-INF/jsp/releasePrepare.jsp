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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
=======
>>>>>>> refs/remotes/apache/trunk
<html>
  <s:i18n name="localization.Continuum">
    <head>
      <title><s:text name="releaseProject.page.title"/></title>
<<<<<<< HEAD
      <s:head />
    </head>
    <body>
      <h2><s:text name="releasePrepare.section.title"/></h2>
      <s:form action="releasePrepare" method="post">
        <h3><s:text name="releasePrepare.parameters"/></h3>
        <input type="hidden" name="projectId" value="<s:property value="projectId"/>"/>
        <div class="axial">
          <table border="1" cellspacing="2" cellpadding="3" width="100%">
            <s:textfield label="%{getText('releasePrepare.scmUsername.label')}" name="scmUsername" required="true"/>
            <s:password label="%{getText('releasePrepare.scmPassword.label')}" name="scmPassword" required="true"/>
            <s:textfield label="%{getText('releasePrepare.scmTag.label')}" name="scmTag" required="true"/>
            <c:if test="${!empty (scmTagBase)}">
              <s:textfield label="%{getText('releasePrepare.scmTagBase.label')}" name="scmTagBase"/>
            </c:if>
            <s:textfield label="%{getText('releasePrepare.prepareGoals.label')}" name="prepareGoals" required="true"/>
=======
    </head>
    <body>

      <h3><s:text name="releasePrepare.section.title"/></h3>

      <s:form action="releasePrepare">

        <tr><td>
          <s:hidden name="projectId" />
          <s:hidden name="autoVersionSubmodules" />
        </td></tr>

        <tr><td>
        <div class="axial">
          <table border="1" cellspacing="2" cellpadding="3" width="100%">
            <s:textfield label="%{getText('releasePrepare.scmUsername.label')}" name="scmUsername" size="100"/>
            <s:password label="%{getText('releasePrepare.scmPassword.label')}" name="scmPassword" size="100"/>
            <s:textfield label="%{getText('releasePrepare.scmTag.label')}" name="scmTag" requiredLabel="true" size="100"/>
            <s:if test="scmTagBase.length() > 0">
              <s:textfield label="%{getText('releasePrepare.scmTagBase.label')}" name="scmTagBase" size="100"/>
            </s:if>
            <s:textfield label="%{getText('releasePrepare.scmCommentPrefix.label')}" name="scmCommentPrefix" size="100"/>
            <s:textfield label="%{getText('releasePrepare.prepareGoals.label')}" name="prepareGoals" requiredLabel="true" size="100"/>
            <s:textfield label="%{getText('releasePrepare.arguments.label')}" name="arguments" size="100"/>
>>>>>>> refs/remotes/apache/trunk
            <s:select label="%{getText('releasePrepare.buildEnvironment.label')}" name="profileId" list="profiles" listValue="name"
                       listKey="id" headerKey="-1" headerValue=""/>
			      <s:checkbox label="%{getText('releasePrepare.useEditMode.label')}" name="scmUseEditMode" fieldValue="false"/>
            <s:checkbox label="%{getText('releasePrepare.addSchema.label')}" name="addSchema" fieldValue="false"/>
            <s:if test="autoVersionSubmodules">
              <s:checkbox label="%{getText('releasePrepare.autoVersionSubmodules.label')}" name="autoVersionSubmodules" disabled="true" fieldValue="false"/>
            </s:if>
          </table>
        </div>
        </td></tr>

        <s:iterator value="projects">
<<<<<<< HEAD
=======
          <tr><td>
>>>>>>> refs/remotes/apache/trunk
          <h3><s:property value="name"/></h3>
          <input type="hidden" name="projectKeys" value="<s:property value="key"/>">
          <div class="axial">
            <table border="1" cellspacing="2" cellpadding="3" width="100%">
              <tr>
                <th><s:text name="releasePrepare.releaseVersion"/></th>
                <td>
                  <input type=text name="relVersions"
                         value="<s:property value="release"/>" size="100">
                </td>
              </tr>
              <tr>
                <th><s:text name="releasePrepare.nextDevelopmentVersion"/></th>
                <td>
                  <input type=text name="devVersions"
                         value="<s:property value="dev"/>" size="100">
                </td>
              </tr>
             </table>
           </div>
<<<<<<< HEAD
        </s:iterator>

        <s:submit/>
=======
          </td></tr>
        </s:iterator>

        <s:submit value="%{getText('submit')}"/>
>>>>>>> refs/remotes/apache/trunk
      </s:form>
    </body>
  </s:i18n>
</html>
