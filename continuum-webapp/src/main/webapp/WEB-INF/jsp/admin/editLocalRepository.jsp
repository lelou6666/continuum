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
<title><s:text name="repository.page.title"/></title>
</head>
<body>
<div class="app">
  <div id="axial" class="h3">
    <h3><s:text name="repository.section.title"/></h3>

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

    <div class="axial">
      <s:form action="saveRepository" method="post" validate="true">
          <table>
            <s:textfield label="%{getText('repository.name.label')}" name="repository.name" requiredLabel="true" disabled="%{defaultRepo}" size="100"/>
            <s:textfield label="%{getText('repository.location.label')}" name="repository.location" requiredLabel="true" disabled="%{defaultRepo}" size="100"/>
            <s:select label="%{getText('repository.layout.label')}" name="repository.layout" list="layouts" disabled="%{defaultRepo}"/>
          </table>
          <s:hidden name="repository.id"/>
          <s:if test="defaultRepo">
            <s:hidden name="repository.name"/>
            <s:hidden name="repository.location"/>
            <s:hidden name="repository.layout"/>
          </s:if>
          <div class="functnbar3">
            <s:submit value="%{getText('save')}" theme="simple"/>
            <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
          </div>
        
      </s:form>
    </div>
  </div>
</div>

</body>
</s:i18n>
</html>
