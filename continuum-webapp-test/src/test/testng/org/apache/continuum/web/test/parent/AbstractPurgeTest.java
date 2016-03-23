package org.apache.continuum.web.test.parent;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author José Morales Martínez
 */
public abstract class AbstractPurgeTest
    extends AbstractAdminTest
{
    protected void goToGeneralPurgePage()
    {
        clickLinkWithText( "Purge Configurations" );
        assertGeneralPurgePage();
    }

    protected void assertGeneralPurgePage()
    {
        assertPage( "Continuum - Purge Configurations" );
        assertTextPresent( "Repository Purge Configurations" );
        assertTextPresent( "Directory Purge Configurations" );
        assertButtonWithValuePresent( "Add" );
    }

    protected void removeRepositoryPurge( String purgeDescription )
    {
        removeRepositoryPurge( purgeDescription, false );
    }

    protected void removeRepositoryPurge( String purgeDescription, boolean distributed )
    {
        goToGeneralPurgePage();
        String action = distributed ? "removeDistributedPurgeConfig" : "removePurgeConfig";

        String xpath = String.format( "(//a[contains(@href,'%s.action') and contains(@href, '%s')])//img",
                                      action, urlEncode( purgeDescription ) );
        clickLinkWithXPath( xpath );
        assertTextPresent( "Delete Purge Configuration" );
        assertTextPresent( "Are you sure you want to delete Purge Configuration \"" + purgeDescription + "\"?" );
        assertButtonWithValuePresent( "Delete" );
        assertButtonWithValuePresent( "Cancel" );
        clickButtonWithValue( "Delete" );
        assertGeneralPurgePage();
    }

    private String urlEncode( String s )
    {
        try
        {
            return URLEncoder.encode( s, "UTF-8" );
        }
        catch ( UnsupportedEncodingException e )
        {
            return s;
        }
    }

    protected void removeDirectoryPurge( String purgeDescription )
    {
        goToGeneralPurgePage();
        clickLinkWithXPath(
            "(//a[contains(@href,'removePurgeConfig.action') and contains(@href, '" + purgeDescription + "')])//img" );
        assertTextPresent( "Delete Purge Configuration" );
        assertTextPresent( "Are you sure you want to delete Purge Configuration \"" + purgeDescription + "\"?" );
        assertButtonWithValuePresent( "Delete" );
        assertButtonWithValuePresent( "Cancel" );
        clickButtonWithValue( "Delete" );
        assertGeneralPurgePage();
    }

    void assertAddRepositoryPurgePage()
    {
        assertAddRepositoryPurgePage( false );
    }

    void assertAddRepositoryPurgePage( boolean distributed )
    {
        assertPage( "Continuum - Add/Edit Purge Configuration" );
        assertTextPresent( "Add/Edit Purge Configuration" );
        assertTextPresent( "Repository" );
        if ( distributed )
        {
            assertElementPresent( "repositoryName" );
        }
        else
        {
            assertElementPresent( "repositoryId" );
        }
        assertTextPresent( "Days Older" );
        assertElementPresent( "daysOlder" );
        assertTextPresent( "Retention Count" );
        assertElementPresent( "retentionCount" );
        assertElementPresent( "deleteAll" );
        assertElementPresent( "deleteReleasedSnapshots" );
        if ( !distributed )
        {
            assertElementPresent( "defaultPurgeConfiguration" );
        }
        assertTextPresent( "Schedule" );
        assertElementPresent( "scheduleId" );
        assertTextPresent( "Description" );
        assertElementPresent( "description" );
        assertButtonWithValuePresent( "Save" );
        assertButtonWithValuePresent( "Cancel" );
    }

    void assertAddEditDirectoryPurgePage()
    {
        assertPage( "Continuum - Add/Edit Purge Configuration" );
        assertTextPresent( "Add/Edit Purge Configuration" );
        assertTextPresent( "Directory Type" );
        assertElementPresent( "directoryType" );
        assertTextPresent( "Days Older" );
        assertElementPresent( "daysOlder" );
        assertTextPresent( "Retention Count" );
        assertElementPresent( "retentionCount" );
        assertElementPresent( "deleteAll" );
        assertElementPresent( "defaultPurgeConfiguration" );
        assertTextPresent( "Schedule" );
        assertElementPresent( "scheduleId" );
        assertTextPresent( "Description" );
        assertElementPresent( "description" );
        assertButtonWithValuePresent( "Save" );
        assertButtonWithValuePresent( "Cancel" );
    }

    protected void goToAddRepositoryPurge()
    {
        goToAddRepositoryPurge( false );
    }

    protected void goToAddRepositoryPurge( boolean distributed )
    {
        goToGeneralPurgePage();
        clickLinkWithXPath( "//form[@name='addRepoPurgeConfig']/input[@type='submit']" );
        assertAddRepositoryPurgePage( distributed );
    }

    protected void goToEditRepositoryPurge( String daysOlder, String retentionCount, String description )
    {
        goToGeneralPurgePage();
        String xPath = "//preceding::td[text()='" + description + "']//following::img[@alt='Edit']";
        clickLinkWithXPath( xPath );
        assertAddRepositoryPurgePage();
        assertFieldValue( daysOlder, "daysOlder" );
        assertFieldValue( retentionCount, "retentionCount" );
        assertFieldValue( description, "description" );
    }

    protected void goToEditDirectoryPurge( String daysOlder, String retentionCount, String description )
    {
        goToGeneralPurgePage();
        String xPath = "//preceding::td[text()='" + description + "']//following::img[@alt='Edit']";
        clickLinkWithXPath( xPath );
        assertAddEditDirectoryPurgePage();
        assertFieldValue( daysOlder, "daysOlder" );
        assertFieldValue( retentionCount, "retentionCount" );
        assertFieldValue( description, "description" );
    }

    protected void addEditRepositoryPurge( String daysOlder, String retentionCount, String description,
                                           boolean success )
    {
        setFieldValue( "daysOlder", daysOlder );
        setFieldValue( "retentionCount", retentionCount );
        setFieldValue( "description", description );
        submit();
        if ( success )
        {
            assertGeneralPurgePage();
        }
        else
        {
            assertAddRepositoryPurgePage();
        }
    }

    protected void goToAddDirectoryPurge()
    {
        goToGeneralPurgePage();
        clickLinkWithXPath( "//form[@name='addDirPurgeConfig']/input[@type='submit']" );
        assertAddEditDirectoryPurgePage();
    }

    protected void addEditDirectoryPurge( String daysOlder, String retentionCount, String description, boolean success )
    {
        setFieldValue( "daysOlder", daysOlder );
        setFieldValue( "retentionCount", retentionCount );
        setFieldValue( "description", description );
        submit();
        if ( success )
        {
            assertGeneralPurgePage();
        }
        else
        {
            assertAddEditDirectoryPurgePage();
        }
    }
}
