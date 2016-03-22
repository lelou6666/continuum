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
        <title><s:text name="confirmGroupRemoval.page.title"/></title>
    </head>
    <body>
      <div id="axial" class="h3">
        <h3><s:text name="confirmGroupRemoval.section.title"/></h3>
        <div class="axial">
        <s:form action="removeProjectGroup" method="post">
          <s:hidden name="projectGroupId"/>
<<<<<<< HEAD
          <s:hidden name="confirmed" value="true"/>
=======
          <s:token/>
>>>>>>> refs/remotes/apache/trunk
          <s:actionerror/>

          <div class="warningmessage">
            <p>
              <strong>
<<<<<<< HEAD
                <s:text name="groups.confirmation.message">
=======
                <s:text name="confirmGroupRemoval.confirmation.message">
>>>>>>> refs/remotes/apache/trunk
                  <s:param><s:property value="%{name}"/></s:param>
                </s:text>
              </strong>
            </p>
          </div>

          <div class="functnbar3">
            <s:submit value="%{getText('delete')}" theme="simple"/>
            <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
          </div>
        </s:form>
        </div>
      </div>
    </body>
  </s:i18n>
</html>
