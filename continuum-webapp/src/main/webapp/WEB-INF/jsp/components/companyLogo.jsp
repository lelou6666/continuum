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
<s:if test="companyLogo.length() > 0">
    <s:if test="companyUrl.length() > 0">
      <a href="<s:property value="companyUrl"/>">
        <img src="<s:property value="companyLogo"/>"
             title="<s:property value="companyName"/>"
             alt="<s:property value="companyName"/>"
             border="0" />
      </a>
    </s:if>
    <s:else>
      <img src="<s:property value="companyLogo"/>"
           title="<s:property value="companyName"/>"
           alt="<s:property value="companyName"/>"
           border="0"/>
    </s:else>
</s:if>
