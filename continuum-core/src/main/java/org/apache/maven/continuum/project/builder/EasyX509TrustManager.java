package org.apache.maven.continuum.project.builder;

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

<<<<<<< HEAD
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

>>>>>>> refs/remotes/apache/trunk
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/apache/trunk
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author olamy
 * @since 1.2.3
 * @version $Id$
 */
public class EasyX509TrustManager implements X509TrustManager
{
    private X509TrustManager standardTrustManager = null;

    private Logger log = LoggerFactory.getLogger( getClass() );
=======
/**
 * @author olamy
 * @since 1.2.3
 */
public class EasyX509TrustManager
    implements X509TrustManager
{
    private static final Logger log = LoggerFactory.getLogger( EasyX509TrustManager.class );

    private X509TrustManager standardTrustManager = null;
>>>>>>> refs/remotes/apache/trunk

    /**
     * Constructor for EasyX509TrustManager.
     */
    public EasyX509TrustManager( KeyStore keystore )
        throws NoSuchAlgorithmException, KeyStoreException
    {
        super();
        TrustManagerFactory factory = TrustManagerFactory.getInstance( TrustManagerFactory.getDefaultAlgorithm() );
        factory.init( keystore );
        TrustManager[] trustmanagers = factory.getTrustManagers();
        if ( trustmanagers.length == 0 )
        {
            throw new NoSuchAlgorithmException( "no trust manager found" );
        }
        this.standardTrustManager = (X509TrustManager) trustmanagers[0];
    }

    /**
<<<<<<< HEAD
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[],String authType)
=======
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[], String authType)
>>>>>>> refs/remotes/apache/trunk
     */
    public void checkClientTrusted( X509Certificate[] certificates, String authType )
        throws CertificateException
    {
        standardTrustManager.checkClientTrusted( certificates, authType );
    }

    /**
<<<<<<< HEAD
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[],String authType)
=======
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[], String authType)
>>>>>>> refs/remotes/apache/trunk
     */
    public void checkServerTrusted( X509Certificate[] certificates, String authType )
        throws CertificateException
    {
        if ( ( certificates != null ) && log.isDebugEnabled() )
        {
            log.debug( "Server certificate chain:" );
            for ( int i = 0; i < certificates.length; i++ )
            {
                log.debug( "X509Certificate[" + i + "]=" + certificates[i] );
            }
        }
        if ( ( certificates != null ) && ( certificates.length == 1 ) )
        {
            certificates[0].checkValidity();
        }
        else
        {
            standardTrustManager.checkServerTrusted( certificates, authType );
        }
    }

    /**
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public X509Certificate[] getAcceptedIssuers()
    {
        return this.standardTrustManager.getAcceptedIssuers();
    }

}
