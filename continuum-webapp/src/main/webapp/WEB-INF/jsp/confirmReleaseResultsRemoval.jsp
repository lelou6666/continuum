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
<%@ taglib uri="/webwork" prefix="ww" %>
<%@ taglib uri="continuum" prefix="c1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <ww:i18n name="localization.Continuum">
    <head>
        <title><ww:text name="deleteReleaseResults.page.title"/></title>
    </head>
    <body>
      <div id="axial" class="h3">
        <h3><ww:text name="deleteReleseResults.section.title"/></h3>
=======
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <s:i18n name="localization.Continuum">
    <head>
        <title><s:text name="deleteReleaseResults.page.title"/></title>
    </head>
    <body>
      <div id="axial" class="h3">
        <h3><s:text name="deleteReleseResults.section.title"/></h3>
>>>>>>> refs/remotes/apache/trunk

        <div class="warningmessage">
          <p>
            <strong>
<<<<<<< HEAD
                <ww:text name="deleteReleaseResults.confirmation.message">
                    <ww:param><ww:property value="%{selectedReleaseResults.size}"/></ww:param>
                </ww:text>
=======
                <s:text name="deleteReleaseResults.confirmation.message">
                    <s:param><s:property value="%{selectedReleaseResults.size}"/></s:param>
                </s:text>
>>>>>>> refs/remotes/apache/trunk
            </strong>
          </p>
        </div>
        
        <div class="functnbar3">
<<<<<<< HEAD
          <ww:form action="removeReleaseResults" method="post">
            <ww:iterator value="selectedReleaseResults">
              <input type="hidden" value="<ww:property/>" name="selectedReleaseResults"/>
            </ww:iterator>
            <input type="hidden" name="projectGroupId" value="${projectGroupId}" />
            <ww:hidden name="confirmed" value="true"/>
            <c1:submitcancel value="%{getText('delete')}" cancel="%{getText('cancel')}"/>
          </ww:form>
        </div>
      </div>
    </body>
  </ww:i18n>
=======
          <s:form action="removeReleaseResults" method="post">
            <s:token/>
            <s:iterator value="selectedReleaseResults">
              <input type="hidden" value="<s:property/>" name="selectedReleaseResults"/>
            </s:iterator>
            <input type="hidden" name="projectGroupId" value="${projectGroupId}" />
            <s:hidden name="confirmed" value="true"/>
            <s:submit value="%{getText('delete')}" theme="simple"/>
            <input type="button" name="Cancel" value="<s:text name='cancel'/>" onclick="history.back();"/>
          </s:form>
        </div>
      </div>
    </body>
  </s:i18n>
>>>>>>> refs/remotes/apache/trunk
</html>