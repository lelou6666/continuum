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
<%@ taglib uri="continuum" prefix="c1" %>
=======
>>>>>>> refs/remotes/apache/trunk
<html>
  <s:i18n name="localization.Continuum">
    <head>
        <title><s:text name="deleteProjects.page.title"/></title>
    </head>
    <body>
      <div id="axial" class="h3">
        <h3><s:text name="deleteProjects.section.title"/></h3>

        <div class="warningmessage">
          <p>
            <strong>
                <s:text name="deleteProjects.confirmation.message">
<<<<<<< HEAD
                    <s:param><s:property value="selectedProjectsNames"/></s:param>
=======
                    <s:param value="selectedProjectsNames"/>
>>>>>>> refs/remotes/apache/trunk
                </s:text>
            </strong>
          </p>
        </div>
        <div class="functnbar3">
<<<<<<< HEAD
          <s:form action="ProjectsList.action" method="post">
            <s:iterator value="selectedProjects">
              <input type="hidden" value="<s:property/>" name="selectedProjects"/>
            </s:iterator>
            <input type="hidden" name="projectGroupId" value="${projectGroupId}" />
            <input type="hidden" name="methodToCall" value="remove" />
            <c1:submitcancel value="%{getText('delete')}" cancel="%{getText('cancel')}"/>
=======
          <s:form action="projectsList" theme="simple">
            <s:iterator value="selectedProjects" var="selectedProject">
              <s:hidden name="selectedProjects" value="%{top}"/>
            </s:iterator>
            <s:hidden name="projectGroupId" value="%{projectGroupId}" />
            <s:hidden name="methodToCall" value="remove"/>
            <s:submit value="%{getText('delete')}"/>
            <s:submit type="button" name="Cancel" value="%{getText('cancel')}" onclick="history.back();"/>
>>>>>>> refs/remotes/apache/trunk
          </s:form>
        </div>
      </div>
    </body>
  </s:i18n>
</html>
