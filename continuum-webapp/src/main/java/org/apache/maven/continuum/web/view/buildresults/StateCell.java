package org.apache.maven.continuum.web.view.buildresults;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.continuum.model.project.BuildResult;
import org.apache.maven.continuum.web.util.StateGenerator;
import org.apache.maven.continuum.web.util.UrlHelperFactory;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.DisplayCell;
import org.extremecomponents.table.core.TableModel;

import java.util.HashMap;

/**
 * Used in BuildResults
 *
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @deprecated use of cells is discouraged due to lack of i18n and design in java code.
 * Use jsp:include instead.
 */
public class StateCell
    extends DisplayCell
{
    protected String getCellValue( TableModel tableModel, Column column )
    {
        final Object rowObject = tableModel.getCurrentRowBean();
        final Object colObject = column.getPropertyValue();

        final String markedUpValue = iconifyResult( rowObject, colObject );

        column.setPropertyValue( markedUpValue );

        return markedUpValue;
    }

    public static String iconifyResult( Object rowObject, Object colObject )
    {
        final int state = colObject instanceof Integer ? (Integer) colObject : -1;
        final String img = StateGenerator.generate( state, ServletActionContext.getRequest().getContextPath() );
        return rowObject instanceof BuildResult ? createActionLink( "buildResult", (BuildResult) rowObject, img ) : img;
    }

    private static String createActionLink( String action, BuildResult result, String linkText )
    {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put( "projectId", result.getProject().getId() );
        params.put( "buildId", result.getId() );
        String url = UrlHelperFactory.getInstance().buildUrl( "/" + action + ".action",
                                                              ServletActionContext.getRequest(),
                                                              ServletActionContext.getResponse(), params );
        return String.format( "<a href='%s''>%s</a>", url, linkText );
    }
}