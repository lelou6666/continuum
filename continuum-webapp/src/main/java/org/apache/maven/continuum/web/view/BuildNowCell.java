package org.apache.maven.continuum.web.view;

/*
 * Copyright 2004-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.continuum.web.model.SummaryProjectModel;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.DisplayCell;
import org.extremecomponents.table.core.BaseModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Used in Summary view
 *
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 */
public class BuildNowCell
    extends DisplayCell
{
    public void init( BaseModel model, Column column )
    {
        super.init( model, column );

        SummaryProjectModel project = (SummaryProjectModel) model.getCurrentCollectionBean();

        HttpServletRequest request = (HttpServletRequest) model.getPageContext().getRequest();

        if ( !project.isInQueue()
            && ( project.getState() == 1 || project.getState() == 2 || project.getState() == 3 || project.getState() == 4 ) )
        {
            column.setValue( "<a href=\"" + request.getContextPath() + "/buildProject.action?projectId="
                + project.getId() + "\">Build Now</a>" );
        }
    }
}