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
        <title><s:text name="notifier.page.add.title"/></title>
    </head>
    <body>
      <div id="axial" class="h3">
<<<<<<< HEAD
      
        <s:if test="projectId > 0">
            <s:url id="actionUrl" value="addProjectNotifier!execute" includeParams="none" />
        </s:if>
        <s:else>
            <s:url id="actionUrl" value="addProjectGroupNotifier!execute" includeParams="none" />
        </s:else>
=======
>>>>>>> refs/remotes/apache/trunk
       
        <h3><s:text name="notifier.section.add.title"/></h3>

        <div class="axial">
                
<<<<<<< HEAD
          <s:form action="%{actionUrl}" method="post">
=======
          <s:form action="%{projectId > 0? 'addProjectNotifier_submit' : 'addProjectGroupNotifier_submit'}" method="post">
>>>>>>> refs/remotes/apache/trunk
            <s:hidden name="projectId"/>
            <s:hidden name="projectGroupId"/>
            <table>
              <tbody>
                <s:select label="%{getText('notifier.type.label')}" name="notifierType"
                           list="#@java.util.LinkedHashMap@{'mail':'Mail', 'irc':'IRC', 'jabber':'Jabber', 'msn':'MSN', 'wagon':'Wagon'}"/>
              </tbody>
            </table>
            <div class="functnbar3">
              <s:submit value="%{getText('submit')}" theme="simple"/>
              <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
            </div>
          </s:form>
        </div>
      </div>
    </body>
  </s:i18n>
</html>
