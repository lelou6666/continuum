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

<<<<<<< HEAD
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="continuum" prefix="c1" %>
=======
<%@ taglib uri="/struts-tags" prefix="s" %>
>>>>>>> refs/remotes/apache/trunk
<s:i18n name="localization.Continuum">
<html>
    <head>
        <title><s:text name="add.m2.project.page.title"/></title>
<<<<<<< HEAD
=======
        <style>
            table.addProject td:nth-child(1) {
                width: 162px;
                text-align: right;
            }
        </style>
        <script>
            function enablePomMethod(method) {
                var $urlRow = jQuery('form tr:has(#pomUrlInput)');
                var $fileRow = jQuery('form tr:has(#pomFileInput)');
                if (method == 'FILE') {
                    $urlRow.hide();
                    $fileRow.show();
                } else {
                    $fileRow.hide();
                    $urlRow.show();
                }
            }
            jQuery(document).ready(function($) {
                var selectedMethod = $('input[name=pomMethod]:checked').val();
                enablePomMethod(selectedMethod);
                $('input[name=pomMethod]').click(function() {
                    enablePomMethod($(this).val());
                });
            });
        </script>
>>>>>>> refs/remotes/apache/trunk
    </head>
    <body>
        <div class="app">
            <div id="axial" class="h3">
                <h3><s:text name="add.m2.project.section.title"/></h3>
                <div class="axial">
<<<<<<< HEAD
                    <s:form method="post" action="addMavenTwoProject.action" name="addMavenTwoProject" enctype="multipart/form-data">
                        <c:if test="${!empty actionErrors || !empty errorMessages}">
=======
                    <s:form method="post" action="addMavenTwoProject" name="addMavenTwoProject" enctype="multipart/form-data">
                        <s:if test="hasActionErrors() || errorMessages.size() > 0">
>>>>>>> refs/remotes/apache/trunk
                          <div class="errormessage">
                            <s:iterator value="actionErrors">
                              <p><s:property/></p>
                            </s:iterator>
<<<<<<< HEAD
                            <c:forEach items="${errorMessages}" var="errorMessage">
                              <p>${errorMessage}</p>
                            </c:forEach>
=======
                            <s:iterator value="errorMessages">
                              <p><s:property/></p>
                            </s:iterator>
>>>>>>> refs/remotes/apache/trunk
                          </div>
                        </s:if>
                        <table class="addProject">
                          <tbody>
<<<<<<< HEAD
                            <s:textfield label="%{getText('add.m2.project.m2PomUrl.label')}" name="m2PomUrl">
                                <s:param name="desc">
                                <table cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td><s:text name="add.m2.project.m2PomUrl.username.label"/>: </td>
                                      <td><input type="text" name="scmUsername" size="20" id="addMavenTwoProject_scmUsername"/><td>
                                    </tr>  
                                    <tr>
                                      <td><s:text name="add.m2.project.m2PomUrl.password.label"/>: </td>
                                      <td><input type="password" name="scmPassword" size="20" id="addMavenTwoProject_scmPassword"/><td>
                                    </tr>  
                                  </tbody>
                                    <tr>
                                      <td></td>
                                      <td><s:checkbox label="%{getText('projectEdit.project.scmUseCache.label')}" name="scmUseCache"/><td>
                                    </tr>
                                </table>  
                                  <p><s:text name="add.m2.project.m2PomUrl.message"/></p>
                                </s:param>
                            </s:textfield>
                            <s:label>
                              <s:param name="after"><strong><s:text name="or"/></strong></s:param>
                            </s:label>
                            <s:file label="%{getText('add.m2.project.m2PomFile.label')}" name="m2PomFile">
                                <s:param name="desc"><p><s:text name="add.m2.project.m2PomFile.message"/></p></s:param>
                            </s:file>
                            <c:choose>
                            <c:when test="${disableGroupSelection == true}">
                              <s:hidden name="selectedProjectGroup"/>
                              <s:hidden name="disableGroupSelection"/>
                              <s:textfield label="%{getText('add.m2.project.projectGroup')}" name="projectGroupName" disabled="true"/>
                            </c:when>
                            <c:otherwise>
                              <s:select label="%{getText('add.m2.project.projectGroup')}" name="selectedProjectGroup"
                                         list="projectGroups" listKey="id" listValue="name"/>
                            </c:otherwise>
                            </c:choose>
                            <s:checkbox label="%{getText('add.m2.project.nonRecursiveProject')}" name="nonRecursiveProject" />
=======
                            <s:radio name="pomMethod" list="pomMethodOptions"  label="%{getText('add.maven.project.pomMethod')}" />
                            <s:textfield id="pomUrlInput" label="%{getText('add.m2.project.m2PomUrl.label')}" requiredLabel="true" name="m2PomUrl" size="100">
	                            <s:param name="after">
		                            <table cellspacing="0" cellpadding="0">
		                              <tbody>
		                                <tr>
		                                  <td><s:text name="add.m2.project.m2PomUrl.username.label"/>: </td>
		                                  <td><s:textfield name="scmUsername" size="20" id="addMavenTwoProject_scmUsername" theme="simple"/></td>
		                                </tr>  
		                                <tr>
		                                  <td><s:text name="add.m2.project.m2PomUrl.password.label"/>: </td>
		                                  <td><s:password name="scmPassword" size="20" id="addMavenTwoProject_scmPassword" theme="simple"/></td>
		                                </tr>  
		                              </tbody>
		                                <tr>
		                                  <td></td>
		                                  <td><s:checkbox label="%{getText('projectEdit.project.scmUseCache.label')}" name="scmUseCache"/></td>
		                                </tr>
		                            </table>  
	                            <p><s:text name="add.m2.project.m2PomUrl.message"/></p>
	                            </s:param>
                            </s:textfield>
                            <s:file id="pomFileInput" label="%{getText('add.m2.project.m2PomFile.label')}" requiredLabel="true" name="m2PomFile" size="100" accept="application/xml,text/xml">
                                <s:param name="after"><p><s:text name="add.m2.project.m2PomFile.message"/></p></s:param>
                            </s:file>
                            <s:if test="disableGroupSelection">
                              <s:hidden name="selectedProjectGroup"/>
                              <s:hidden name="disableGroupSelection"/>
                              <s:textfield label="%{getText('add.m2.project.projectGroup')}" name="projectGroupName" disabled="true" size="100"/>
                            </s:if>
                            <s:else>
                              <s:select label="%{getText('add.m2.project.projectGroup')}" name="selectedProjectGroup"
                                         list="projectGroups" listKey="id" listValue="name"/>
                            </s:else>

                            <s:radio label="%{getText('add.m2.project.importType')}" name="importType" list="importOptions" />

>>>>>>> refs/remotes/apache/trunk
                            <s:select label="%{getText('add.m2.project.buildDefinitionTemplate')}" name="buildDefinitionTemplateId"
                                       list="buildDefinitionTemplates" listKey="id" listValue="name" headerKey="-1" 
                                       headerValue="%{getText('add.m2.project.defaultBuildDefinition')}"/> 
                          </tbody>
                        </table>
                        <div class="functnbar3">
                          <s:submit value="%{getText('add')}" theme="simple"/>
                          <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
</s:i18n>
