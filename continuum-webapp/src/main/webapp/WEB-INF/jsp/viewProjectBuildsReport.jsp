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
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>

<html>
  <s:i18n name="localization.Continuum">
  <head>
    <title><s:text name="projectBuilds.report.title"/></title>
    <s:head/>
    <link rel="stylesheet" href="<s:url value='/css/no-theme/jquery-ui-1.7.2.custom.css'/>" type="text/css" />
    <script type="text/javascript" src="<s:url value='/js/jquery-ui-1.7.2.custom.min.js'/>"></script>
    <script type="text/javascript">
      jQuery(document).ready(function($)
      {
        $('#startDate').datepicker()
        $('#endDate').datepicker()

        $('#resetFilter').click(function()
        {
          $('#startDate').val('')
          $('#endDate').val('')
          $('#triggeredBy').val('')
          $("#buildStatus option[value='0']").attr('selected', 'selected')
          $("#projectGroupId option[value='0']").attr('selected', 'selected')
        });
      });
    </script>
    <style>
      form#generateProjectBuildsReport table.wwFormTable {
        width: 100%;
      }
    </style>
  </head>
  
  <body>
    <h3><s:text name="projectBuilds.report.section.title"/></h3>
 
    <s:form name="generateReportForm" action="generateProjectBuildsReport" method="GET">

      <tr><td>
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
      </td></tr>

      <tr><td>
      <div class="axial">
        <table>
            <s:select label="%{getText('projectBuilds.report.project.group')}" name="projectGroupId" id="projectGroupId" list="projectGroups"/>
            <s:textfield label="%{getText('projectBuilds.report.startDate')}" name="startDate" id="startDate" size="20"/>
            <s:textfield label="%{getText('projectBuilds.report.endDate')}" name="endDate" id="endDate" size="20"/>
            <s:select label="%{getText('projectBuilds.report.buildStatus')}" name="buildStatus" id="buildStatus" list="buildStatuses"/>
            <s:textfield label="%{getText('projectBuilds.report.triggeredBy')}" name="triggeredBy" id="triggeredBy" size="40"/>
        </table>
        <div class="functnbar3">
          <s:submit value="%{getText('projectBuilds.report.view')}" theme="simple" />
          <input type="button" id="resetFilter" value="<s:text name='projectBuilds.report.button.reset' />" />
        </div>
      </div>
      </td></tr>
    </s:form>

    <s:if test="filteredResults != null">
      <div id="h3">
     	  <h3>Results</h3>
       	  <s:if test="filteredResults.size() > 0">
            <%@include file="viewProjectBuildsReportPager.jspf"%>
            <s:set value="filteredResults" name="buildResults" scope="page"/>
            <ec:table items="buildResults"
                        var="buildResult"
                        showExports="false"
                        showPagination="false"
                        showStatusBar="false"
                        sortable="false"
                        filterable="false">
              <ec:row highlightRow="true">
                <ec:column property="project.projectGroup.name" title="projectBuilds.report.projectGroup"/>
                <ec:column property="project.name" title="projectBuilds.report.project"/>
                <ec:column property="buildNumber" title="projectBuilds.report.buildNumber"/>
                <ec:column property="startTime" title="projectBuilds.report.buildDate" cell="date"/>
                <ec:column property="username" title="projectBuilds.report.triggeredBy"/>
                <ec:column property="state" title="projectBuilds.report.buildStatus" cell="org.apache.maven.continuum.web.view.buildresults.StateCell"/>
              </ec:row>
            </ec:table>
            <%@include file="viewProjectBuildsReportPager.jspf"%>
          </s:if>
          <s:else>
            <s:text name="projectBuilds.report.noResult"/></p>
          </s:else>
      </div>
    </s:if>
  </body>
  </s:i18n>
</html>
