/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.net.http.SslError
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.webkit.SslErrorHandler
 *  android.webkit.WebSettings
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  twitter4j.Twitter
 *  twitter4j.TwitterException
 *  twitter4j.TwitterFactory
 *  twitter4j.auth.AccessToken
 *  twitter4j.auth.RequestToken
 */
package com.smule.android.twitter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.smule.android.logging.Log;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterOAuthView
extends WebView {
    private TwitterOAuthTask a;
    private boolean b = true;
    private boolean c = false;

    public TwitterOAuthView(Context context) {
        super(context);
        this.d();
    }

    public TwitterOAuthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d();
    }

    public TwitterOAuthView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.d();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(TwitterOAuthTask twitterOAuthTask) {
        if (twitterOAuthTask == null) {
            return;
        }
        if (!twitterOAuthTask.isCancelled()) {
            if (this.b()) {
                Log.b("TwitterOAuthView", "Cancelling a task.");
            }
            twitterOAuthTask.cancel(true);
        }
        synchronized (twitterOAuthTask) {
            if (this.b()) {
                Log.b("TwitterOAuthView", "Notifying a task of cancellation.");
            }
            twitterOAuthTask.notify();
            return;
        }
    }

    private void d() {
        WebSettings webSettings = this.getSettings();
        webSettings.setCacheMode(2);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        this.setScrollBarStyle(0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a() {
        TwitterOAuthTask twitterOAuthTask;
        synchronized (this) {
            twitterOAuthTask = this.a;
            this.a = null;
        }
        this.a(twitterOAuthTask);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2, String string3, String string4, boolean bl, Listener listener) {
        if (string2 != null && string3 != null && string4 != null && listener != null) {
            TwitterOAuthTask twitterOAuthTask;
            TwitterOAuthTask twitterOAuthTask2;
            synchronized (this) {
                twitterOAuthTask = this.a;
                this.a = twitterOAuthTask2 = new TwitterOAuthTask();
            }
            this.a(twitterOAuthTask);
            twitterOAuthTask2.execute(new Object[]{string2, string3, string4, bl, listener});
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean b() {
        return this.c;
    }

    public boolean c() {
        return this.b;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c()) {
            this.a();
        }
    }

    public void setCancelOnDetachedFromWindow(boolean bl) {
        this.b = bl;
    }

    public void setDebugEnabled(boolean bl) {
        this.c = bl;
    }

    public static interface Listener {
        public void a(TwitterOAuthView var1, Result var2);

        public void a(TwitterOAuthView var1, AccessToken var2);
    }

    public static enum Result {
        a,
        b,
        c,
        d,
        e;
        

        private Result() {
        }
    }

    private class TwitterOAuthTask
    extends AsyncTask<Object, Void, Result> {
        private String b;
        private boolean c;
        private Listener d;
        private Twitter e;
        private RequestToken f;
        private volatile boolean g;
        private volatile String h;
        private AccessToken i;

        private TwitterOAuthTask() {
        }

        private void a() {
            if (TwitterOAuthView.this.b()) {
                Log.b("TwitterOAuthView", "Calling Listener.onSuccess");
            }
            this.d.a(TwitterOAuthView.this, this.i);
        }

        private boolean a(String string2) {
            if (!this.isCancelled()) {
                return false;
            }
            if (TwitterOAuthView.this.b()) {
                Log.b("TwitterOAuthView", "Cancellation was detected in the context of " + string2);
            }
            return true;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void b() {
            TwitterOAuthView twitterOAuthView = TwitterOAuthView.this;
            synchronized (twitterOAuthView) {
                if (TwitterOAuthView.this.a == this) {
                    TwitterOAuthView.this.a = null;
                }
                return;
            }
        }

        private void b(Result result) {
            if (TwitterOAuthView.this.b()) {
                Log.b("TwitterOAuthView", "Calling Listener.onFailure, result = " + (Object)((Object)result));
            }
            if (this.d != null) {
                this.d.a(TwitterOAuthView.this, result);
            }
        }

        private /* varargs */ void b(Object ... arrobject) {
            Log.b("TwitterOAuthView", "CONSUMER KEY = " + (String)arrobject[0]);
            Log.b("TwitterOAuthView", "CONSUMER SECRET = " + (String)arrobject[1]);
            Log.b("TwitterOAuthView", "CALLBACK URL = " + (String)arrobject[2]);
            Log.b("TwitterOAuthView", "DUMMY CALLBACK URL = " + (Boolean)arrobject[3]);
            System.setProperty("twitter4j.debug", "true");
        }

        private RequestToken c() {
            try {
                RequestToken requestToken = this.e.getOAuthRequestToken();
                if (TwitterOAuthView.this.b()) {
                    Log.b("TwitterOAuthView", "Got a request token.");
                }
                return requestToken;
            }
            catch (TwitterException twitterException) {
                twitterException.printStackTrace();
                Log.d("TwitterOAuthView", "Failed to get a request token.", (Throwable)twitterException);
                return null;
            }
        }

        private void d() {
            this.publishProgress((Object[])new Void[0]);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private boolean e() {
            while (!this.g) {
                if (this.a("waitForAuthorization()")) {
                    return true;
                }
                synchronized (this) {
                    block9 : {
                        try {
                            if (TwitterOAuthView.this.b()) {
                                Log.b("TwitterOAuthView", "Waiting for the authorization step to be done.");
                            }
                            this.wait();
                        }
                        catch (InterruptedException interruptedException) {
                            if (!TwitterOAuthView.this.b()) break block9;
                            Log.b("TwitterOAuthView", "Interrupted while waiting for the authorization step to be done.");
                        }
                    }
                }
            }
            if (TwitterOAuthView.this.b()) {
                Log.b("TwitterOAuthView", "Finished waiting for the authorization step to be done.");
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void f() {
            this.g = true;
            synchronized (this) {
                if (TwitterOAuthView.this.b()) {
                    Log.b("TwitterOAuthView", "Notifying that the authorization step was done.");
                }
                this.notify();
                return;
            }
        }

        private AccessToken g() {
            try {
                AccessToken accessToken = this.e.getOAuthAccessToken(this.f, this.h);
                if (TwitterOAuthView.this.b()) {
                    Log.b("TwitterOAuthView", "Got an access token for " + accessToken.getScreenName());
                }
                return accessToken;
            }
            catch (TwitterException twitterException) {
                twitterException.printStackTrace();
                Log.d("TwitterOAuthView", "Failed to get an access token.", (Throwable)twitterException);
                return null;
            }
        }

        protected /* varargs */ Result a(Object ... arrobject) {
            if (this.a("doInBackground() [on entry]")) {
                return Result.b;
            }
            String string2 = (String)arrobject[0];
            String string3 = (String)arrobject[1];
            this.b = (String)arrobject[2];
            this.c = (Boolean)arrobject[3];
            this.d = (Listener)arrobject[4];
            if (TwitterOAuthView.this.b()) {
                this.b(arrobject);
            }
            this.e = new TwitterFactory().getInstance();
            this.e.setOAuthConsumer(string2, string3);
            this.f = this.c();
            if (this.f == null) {
                return Result.c;
            }
            this.d();
            if (this.e()) {
                return Result.b;
            }
            if (this.h == null) {
                return Result.d;
            }
            if (this.a("doInBackground() [before getAccessToken()]")) {
                return Result.b;
            }
            this.i = this.g();
            if (this.i == null) {
                return Result.e;
            }
            return Result.a;
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void a(Result result) {
            if (TwitterOAuthView.this.b()) {
                Log.b("TwitterOAuthView", "onPostExecute: result = " + (Object)((Object)result));
            }
            Result result2 = result;
            if (result == null) {
                result2 = Result.b;
            }
            if (result2 == Result.a) {
                this.a();
            } else {
                this.b(result2);
            }
            this.b();
        }

        protected /* varargs */ void a(Void ... object) {
            if (this.a("onProgressUpdate()")) {
                return;
            }
            object = this.f.getAuthorizationURL();
            if (TwitterOAuthView.this.b()) {
                Log.b("TwitterOAuthView", "Loading the authorization URL: " + (String)object);
            }
            TwitterOAuthView.this.loadUrl((String)object);
        }

        protected /* synthetic */ Object doInBackground(Object[] arrobject) {
            return this.a(arrobject);
        }

        protected void onCancelled() {
            super.onCancelled();
            this.b(Result.b);
            this.b();
        }

        protected /* synthetic */ void onPostExecute(Object object) {
            this.a((Result)((Object)object));
        }

        protected void onPreExecute() {
            TwitterOAuthView.this.setWebViewClient((WebViewClient)new LocalWebViewClient());
        }

        protected /* synthetic */ void onProgressUpdate(Object[] arrobject) {
            this.a((Void[])arrobject);
        }

        private class LocalWebViewClient
        extends WebViewClient {
            private LocalWebViewClient() {
            }

            public void onPageStarted(WebView webView, String string2, Bitmap bitmap) {
                super.onPageStarted(webView, string2, bitmap);
                if (Build.VERSION.SDK_INT < 11 && this.shouldOverrideUrlLoading(webView, string2)) {
                    TwitterOAuthView.this.stopLoading();
                }
            }

            public void onReceivedError(WebView webView, int n, String string2, String string3) {
                Log.e("TwitterOAuthView", "onReceivedError: [" + n + "] " + string2);
                TwitterOAuthTask.this.f();
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.cancel();
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
                if (string2 == null || TwitterOAuthTask.this.b == null || !string2.startsWith(TwitterOAuthTask.this.b)) {
                    Log.b("TwitterOAuthView", "URL is NOT the callback URL: " + string2);
                    return false;
                }
                if (TwitterOAuthView.this.b()) {
                    Log.b("TwitterOAuthView", "Detected the callback URL: " + string2);
                }
                webView = Uri.parse((String)string2);
                TwitterOAuthTask.this.h = webView.getQueryParameter("oauth_verifier");
                if (TwitterOAuthView.this.b()) {
                    Log.b("TwitterOAuthView", "oauth_verifier = " + TwitterOAuthTask.this.h);
                }
                TwitterOAuthTask.this.f();
                return TwitterOAuthTask.this.c;
            }
        }

    }

}

