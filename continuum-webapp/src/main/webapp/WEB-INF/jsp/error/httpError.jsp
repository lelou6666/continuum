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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

=======
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="/struts-tags" prefix="s" %>
>>>>>>> refs/remotes/apache/trunk
<s:i18n name="localization.Continuum">
<html>
<head>
  <title>
<<<<<<< HEAD
    <c:choose>
      <c:when test="${param.errorCode == 403 || param.errorCode == 404 || param.errorCode == 500}">
        <s:text name="error.%{param.errorCode}.title"/>
      </c:when>
      <c:otherwise>
        <s:text name="error.page.title"/>
      </c:otherwise>
    </c:choose>
  </title>
  <link rel="stylesheet" type="text/css" href="<s:url value="/css/tigris.css" includeParams="none"/>" media="screen"/>
  <link rel="stylesheet" type="text/css" href="<s:url value="/css/print.css" includeParams="none"/>" media="print"/>
  <link rel="stylesheet" type="text/css" href="<s:url value="/css/extremecomponents.css" includeParams="none"/>" media="screen"/>
  <link rel="shortcut icon" href="<s:url value="/favicon.ico" includeParams="none"/>" type="image/x-icon"/>

  <script src="<s:url value="/scripts/tigris.js" includeParams="none"/>" type="text/javascript"></script>
</head>

<body onload="focus()" marginwidth="0" marginheight="0" class="composite">
<%@ include file="/WEB-INF/jsp/navigations/DefaultTop.jsp" %>

<table id="main" border="0" cellpadding="4" cellspacing="0" width="100%">
  <tbody>
    <tr valign="top">
      <td id="leftcol" width="180">
        <br/> <br/>
        <%@ include file="/WEB-INF/jsp/navigations/Menu.jsp" %>
      </td>
      <td width="86%">
        <br/>

        <div id="bodycol">
          <div class="app">
            <div id="axial" class="h3">
              <h3>
                <c:choose>
                  <c:when test="${param.errorCode == 403 || param.errorCode == 404 || param.errorCode == 500}">
                    <s:text name="error.%{param.errorCode}.section.title"/>
                  </c:when>
                  <c:otherwise>
                    The URL requested results to an unknown error.
                  </c:otherwise>
                </c:choose>
              </h3>

              <div class="errormessage">
                <c:choose>
                  <c:when test="${param.errorCode == 403 || param.errorCode == 404 || param.errorCode == 500}">
                    <s:text name="error.%{param.errorCode}.message"/>
                  </c:when>
                  <c:otherwise>
                    The URL requested results to an unknown error.
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
          </div>
        </div>
      </td>
    </tr>
  </tbody>
</table>
=======
      <s:if test="#parameters.errorCode == 403">
        <s:text name="error.403.title"/>
      </s:if>
      <s:elseif test="#parameters.errorCode == 404">
        <s:text name="error.404.title"/>
      </s:elseif>
      <s:elseif test="#parameters.errorCode == 500">
        <s:text name="error.500.title"/>
      </s:elseif>
      <s:else>
        <s:text name="error.page.title"/>
      </s:else>
  </title>
</head>

<body>
  <div id="h3">
    <h3>
        <s:if test="#parameters.errorCode == 403">
          <s:text name="error.403.section.title"/>
        </s:if>
        <s:elseif test="#parameters.errorCode == 404">
          <s:text name="error.404.section.title"/>
        </s:elseif>
        <s:elseif test="#parameters.errorCode == 500">
          <s:text name="error.500.section.title"/>
        </s:elseif>
        <s:else>
          The URL requested results to an unknown error (Error <s:property value="errorCode"/>).
        </s:else>
    </h3>
>>>>>>> refs/remotes/apache/trunk

    <div class="errormessage">
        <s:if test="#parameters.errorCode == 403">
          <s:text name="error.403.message"/>
        </s:if>
        <s:elseif test="#parameters.errorCode == 404">
          <s:text name="error.404.message"/>
        </s:elseif>
        <s:elseif test="#parameters.errorCode == 500">
          <s:text name="error.500.message"/>
        </s:elseif>
        <s:else>
          The URL requested results to an unknown error (Error <s:property value="errorCode"/>).
        </s:else>
    </div>
  </div>
</body>
</html>
</s:i18n>