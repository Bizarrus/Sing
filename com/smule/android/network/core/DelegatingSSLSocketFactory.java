/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  retrofit2.SSLHandshakeInterceptor
 */
package com.smule.android.network.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import retrofit2.SSLHandshakeInterceptor;

public class DelegatingSSLSocketFactory
extends SSLSocketFactory {
    protected final SSLSocketFactory delegate;
    private SSLHandshakeInterceptor mHandshakeInterceptor;

    DelegatingSSLSocketFactory(SSLSocketFactory sSLSocketFactory, SSLHandshakeInterceptor sSLHandshakeInterceptor) {
        this.delegate = sSLSocketFactory;
        this.mHandshakeInterceptor = sSLHandshakeInterceptor;
    }

    static SSLSocketFactory defaultSslSocketFactory(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, new TrustManager[]{x509TrustManager}, null);
        return sSLContext.getSocketFactory();
    }

    static X509TrustManager defaultTrustManager() throws GeneralSecurityException {
        Object[] arrobject = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        arrobject.init((KeyStore)null);
        arrobject = arrobject.getTrustManagers();
        if (arrobject.length != 1 || !(arrobject[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(arrobject));
        }
        return (X509TrustManager)arrobject[0];
    }

    protected SSLSocket configureSocket(SSLSocket sSLSocket) throws IOException {
        sSLSocket.addHandshakeCompletedListener(new HandshakeCompletedListener(){

            @Override
            public void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
                DelegatingSSLSocketFactory.this.mHandshakeInterceptor.setSSLSession(handshakeCompletedEvent.getSession());
            }
        });
        return sSLSocket;
    }

    @Override
    public Socket createSocket(String string2, int n) throws IOException {
        return this.configureSocket((SSLSocket)this.delegate.createSocket(string2, n));
    }

    @Override
    public Socket createSocket(String string2, int n, InetAddress inetAddress, int n2) throws IOException {
        return this.configureSocket((SSLSocket)this.delegate.createSocket(string2, n, inetAddress, n2));
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int n) throws IOException {
        return this.configureSocket((SSLSocket)this.delegate.createSocket(inetAddress, n));
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int n, InetAddress inetAddress2, int n2) throws IOException {
        return this.configureSocket((SSLSocket)this.delegate.createSocket(inetAddress, n, inetAddress2, n2));
    }

    @Override
    public Socket createSocket(Socket socket, String string2, int n, boolean bl) throws IOException {
        return this.configureSocket((SSLSocket)this.delegate.createSocket(socket, string2, n, bl));
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

}

