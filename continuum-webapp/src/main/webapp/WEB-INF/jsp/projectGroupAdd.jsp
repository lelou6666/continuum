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
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
  <s:i18n name="localization.Continuum">
    <head>
        <title><s:text name="projectGroup.add.page.title"/></title>
    </head>
    <body>
      <div id="axial" class="h3">
        <h3><s:text name="projectGroup.add.section.title"/></h3>

        <div class="axial">
          <s:form action="addProjectGroup" method="post" validate="true">

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

            <table>
              <tbody>
                <s:textfield label="%{getText('projectGroup.name.label')}" name="name"  requiredLabel="true" size="100"/>
                <s:textfield label="%{getText('projectGroup.groupId.label')}" name="groupId" requiredLabel="true" size="100"/>
                <s:textfield label="%{getText('projectGroup.description.label')}" name="description" size="100"/>
                <s:select label="%{getText('projectGroup.repository.label')}" name="repositoryId" list="repositories"
                		   listKey="id" listValue="name"/>
              </tbody>
            </table>
            <div class="functnbar3">
              <s:submit value="%{getText('save')}" theme="simple"/>
              <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
            </div>
          </s:form>
        </div>
      </div>
    </body>
  </s:i18n>
</html>
