package org.apache.maven.continuum.web.view.commons;

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

import org.codehaus.plexus.util.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.DisplayCell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.ExtremeUtils;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @deprecated use of cells is discouraged due to lack of i18n and design in java code.
 *             Use jsp:include instead.
 */
public class DateCell
    extends DisplayCell
{
    protected String getCellValue( TableModel tableModel, Column column )
    {
        String valueString = column.getPropertyValueAsString();

        if ( !StringUtils.isEmpty( valueString ) && !"0".equals( valueString ) )
        {
            Locale locale = tableModel.getLocale();

            Object value = column.getPropertyValue();

            if ( value instanceof Long )
            {
                Calendar cal = Calendar.getInstance();

                cal.setTimeInMillis( (Long) value );

                value = cal.getTime();
            }

            String format = column.getFormat();

            if ( StringUtils.isEmpty( format ) )
            {
                format = "MMM dd, yyyy hh:mm:ss aaa z";
            }

            value = ExtremeUtils.formatDate( column.getParse(), format, value, locale );

            column.setPropertyValue( value );

            return value.toString();
        }
        else
        {
            return "&nbsp;";
        }
    }
}