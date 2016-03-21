package org.apache.continuum.web.test.listener;

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

import com.thoughtworks.selenium.Selenium;
import org.apache.commons.io.FileUtils;
import org.apache.continuum.web.test.parent.AbstractSeleniumTest;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CaptureScreenShotsListener
    extends TestListenerAdapter
{
    @Override
    public void onTestStart( ITestResult tr )
    {
        System.out.print( "Test " + tr.getName() + "... " );
        super.onTestStart( tr );
    }

    @Override
    public void onTestSkipped( ITestResult tr )
    {
        System.out.println( "Skipped" );
        super.onTestSkipped( tr );
    }

    @Override
    public void onTestFailure( ITestResult tr )
    {
        captureError( tr );
        System.out.println( "Failed" );
        super.onTestFailure( tr );
    }

    @Override
    public void onTestSuccess( ITestResult tr )
    {
        System.out.println( "Success" );
        super.onTestFailure( tr );
    }

    private void captureError( ITestResult tr )
    {
        captureScreenshotAndSource( tr.getTestClass().getName(), tr.getThrowable() );
    }

    public static void captureScreenshotAndSource( String cName, Throwable throwable )
    {
        Selenium selenium = AbstractSeleniumTest.getSelenium();
        if ( selenium == null )
        {
            // avoid swallowing exception
            System.err.println( "Not capturing screenshot as Selenium is not initialised" );
            return;
        }

        String locator = "link=Show/hide Stack Trace";
        if ( selenium.isElementPresent( locator ) )
        {
            selenium.click( locator );
        }

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy.MM.dd-HH_mm_ss" );
        String time = sdf.format( new Date() );
        File targetPath = new File( "target", "screenshots" );
        StackTraceElement trace = getStackTraceOfCallingClass( cName, throwable.getStackTrace() );
        String methodName;
        int lNumber;
        if ( trace == null )
        {
            System.err.println( "Unable to determine the calling method from class " + cName );
            throwable.printStackTrace();
            methodName = "unknown";
            lNumber = 0;
        }
        else
        {
            methodName = trace.getMethodName();
            lNumber = trace.getLineNumber();
        }
        String lineNumber = Integer.toString( lNumber );
        String className = cName.substring( cName.lastIndexOf( '.' ) + 1 );
        if ( !targetPath.exists() && !targetPath.mkdirs() )
        {
            System.out.println( "Unable to create screenshots directory" );
            return;
        }
        String fileBaseName = methodName + "_" + className + ".java_" + lineNumber + "-" + time;

        System.out.println( "Capturing screenshot at " + fileBaseName + ".png" );

        try
        {
            selenium.windowMaximize();
            File fileName = getFileName( targetPath, fileBaseName, ".png" );
            selenium.captureEntirePageScreenshot( fileName.getAbsolutePath(), "" );
        }
        catch ( RuntimeException e )
        {
            System.out.println( "Error when take screenshot of error: " + e.getMessage() );
        }
        try
        {
            File fileName = getFileName( targetPath, fileBaseName, ".html" );
            FileUtils.writeStringToFile( fileName, selenium.getHtmlSource() );
        }
        catch ( IOException ioe )
        {
            System.out.println( "Error writing HTML of error: " + ioe.getMessage() );
        }
    }

    private static File getFileName( File targetPath, String fileBaseName, String ext )
    {
        File fileName = new File( targetPath, fileBaseName + ext );
        int count = 0;
        while ( fileName.exists() )
        {
            count++;
            fileName = new File( targetPath, fileBaseName + "_" + count + ext );
        }
        return fileName;
    }

    private static StackTraceElement getStackTraceOfCallingClass( String nameOfClass, StackTraceElement stackTrace[] )
    {
        StackTraceElement lastMatch = null;
        for ( StackTraceElement el : stackTrace )
        {
            String className = el.getClassName();
            if ( Pattern.matches( nameOfClass, className ) )
            {
                lastMatch = el;
            }
        }
        return lastMatch;
    }
}
