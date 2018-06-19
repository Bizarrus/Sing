package com.smule.android.twitter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.smule.android.logging.Log;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterOAuthView extends WebView {
    private TwitterOAuthTask f17691a;
    private boolean f17692b = true;
    private boolean f17693c = false;

    public interface Listener {
        void mo6676a(TwitterOAuthView twitterOAuthView, Result result);

        void mo6677a(TwitterOAuthView twitterOAuthView, AccessToken accessToken);
    }

    public enum Result {
        SUCCESS,
        CANCELLATION,
        REQUEST_TOKEN_ERROR,
        AUTHORIZATION_ERROR,
        ACCESS_TOKEN_ERROR
    }

    private class TwitterOAuthTask extends AsyncTask<Object, Void, Result> {
        final /* synthetic */ TwitterOAuthView f17682a;
        private String f17683b;
        private boolean f17684c;
        private Listener f17685d;
        private Twitter f17686e;
        private RequestToken f17687f;
        private volatile boolean f17688g;
        private volatile String f17689h;
        private AccessToken f17690i;

        private class LocalWebViewClient extends WebViewClient {
            final /* synthetic */ TwitterOAuthTask f17681a;

            private LocalWebViewClient(TwitterOAuthTask twitterOAuthTask) {
                this.f17681a = twitterOAuthTask;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                Log.e("TwitterOAuthView", "onReceivedError: [" + i + "] " + str);
                this.f17681a.m18861f();
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.cancel();
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                if (VERSION.SDK_INT < 11 && shouldOverrideUrlLoading(webView, str)) {
                    this.f17681a.f17682a.stopLoading();
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null || this.f17681a.f17683b == null || !str.startsWith(this.f17681a.f17683b)) {
                    Log.b("TwitterOAuthView", "URL is NOT the callback URL: " + str);
                    return false;
                }
                if (this.f17681a.f17682a.m18872b()) {
                    Log.b("TwitterOAuthView", "Detected the callback URL: " + str);
                }
                this.f17681a.f17689h = Uri.parse(str).getQueryParameter("oauth_verifier");
                if (this.f17681a.f17682a.m18872b()) {
                    Log.b("TwitterOAuthView", "oauth_verifier = " + this.f17681a.f17689h);
                }
                this.f17681a.m18861f();
                return this.f17681a.f17684c;
            }
        }

        private TwitterOAuthTask(TwitterOAuthView twitterOAuthView) {
            this.f17682a = twitterOAuthView;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m18863a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m18864a((Result) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            m18865a((Void[]) objArr);
        }

        private boolean m18851a(String str) {
            if (!isCancelled()) {
                return false;
            }
            if (this.f17682a.m18872b()) {
                Log.b("TwitterOAuthView", "Cancellation was detected in the context of " + str);
            }
            return true;
        }

        protected void onPreExecute() {
            this.f17682a.setWebViewClient(new LocalWebViewClient());
        }

        protected Result m18863a(Object... objArr) {
            if (m18851a("doInBackground() [on entry]")) {
                return Result.CANCELLATION;
            }
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            this.f17683b = (String) objArr[2];
            this.f17684c = ((Boolean) objArr[3]).booleanValue();
            this.f17685d = (Listener) objArr[4];
            if (this.f17682a.m18872b()) {
                m18855b(objArr);
            }
            this.f17686e = new TwitterFactory().getInstance();
            this.f17686e.setOAuthConsumer(str, str2);
            this.f17687f = m18857c();
            if (this.f17687f == null) {
                return Result.REQUEST_TOKEN_ERROR;
            }
            m18858d();
            if (m18860e()) {
                return Result.CANCELLATION;
            }
            if (this.f17689h == null) {
                return Result.AUTHORIZATION_ERROR;
            }
            if (m18851a("doInBackground() [before getAccessToken()]")) {
                return Result.CANCELLATION;
            }
            this.f17690i = m18862g();
            if (this.f17690i == null) {
                return Result.ACCESS_TOKEN_ERROR;
            }
            return Result.SUCCESS;
        }

        private void m18855b(Object... objArr) {
            Log.b("TwitterOAuthView", "CONSUMER KEY = " + ((String) objArr[0]));
            Log.b("TwitterOAuthView", "CONSUMER SECRET = " + ((String) objArr[1]));
            Log.b("TwitterOAuthView", "CALLBACK URL = " + ((String) objArr[2]));
            Log.b("TwitterOAuthView", "DUMMY CALLBACK URL = " + ((Boolean) objArr[3]));
            System.setProperty("twitter4j.debug", "true");
        }

        protected void m18865a(Void... voidArr) {
            if (!m18851a("onProgressUpdate()")) {
                String authorizationURL = this.f17687f.getAuthorizationURL();
                if (this.f17682a.m18872b()) {
                    Log.b("TwitterOAuthView", "Loading the authorization URL: " + authorizationURL);
                }
                this.f17682a.loadUrl(authorizationURL);
            }
        }

        protected void m18864a(Result result) {
            if (this.f17682a.m18872b()) {
                Log.b("TwitterOAuthView", "onPostExecute: result = " + result);
            }
            if (result == null) {
                result = Result.CANCELLATION;
            }
            if (result == Result.SUCCESS) {
                m18849a();
            } else {
                m18854b(result);
            }
            m18853b();
        }

        protected void onCancelled() {
            super.onCancelled();
            m18854b(Result.CANCELLATION);
            m18853b();
        }

        private void m18849a() {
            if (this.f17682a.m18872b()) {
                Log.b("TwitterOAuthView", "Calling Listener.onSuccess");
            }
            this.f17685d.mo6677a(this.f17682a, this.f17690i);
        }

        private void m18854b(Result result) {
            if (this.f17682a.m18872b()) {
                Log.b("TwitterOAuthView", "Calling Listener.onFailure, result = " + result);
            }
            if (this.f17685d != null) {
                this.f17685d.mo6676a(this.f17682a, result);
            }
        }

        private void m18853b() {
            synchronized (this.f17682a) {
                if (this.f17682a.f17691a == this) {
                    this.f17682a.f17691a = null;
                }
            }
        }

        private RequestToken m18857c() {
            try {
                RequestToken oAuthRequestToken = this.f17686e.getOAuthRequestToken();
                if (!this.f17682a.m18872b()) {
                    return oAuthRequestToken;
                }
                Log.b("TwitterOAuthView", "Got a request token.");
                return oAuthRequestToken;
            } catch (Throwable e) {
                e.printStackTrace();
                Log.d("TwitterOAuthView", "Failed to get a request token.", e);
                return null;
            }
        }

        private void m18858d() {
            publishProgress(new Void[0]);
        }

        private boolean m18860e() {
            while (!this.f17688g) {
                if (m18851a("waitForAuthorization()")) {
                    return true;
                }
                synchronized (this) {
                    try {
                        if (this.f17682a.m18872b()) {
                            Log.b("TwitterOAuthView", "Waiting for the authorization step to be done.");
                        }
                        wait();
                    } catch (InterruptedException e) {
                        if (this.f17682a.m18872b()) {
                            Log.b("TwitterOAuthView", "Interrupted while waiting for the authorization step to be done.");
                        }
                    }
                }
            }
            if (this.f17682a.m18872b()) {
                Log.b("TwitterOAuthView", "Finished waiting for the authorization step to be done.");
            }
            return false;
        }

        private void m18861f() {
            this.f17688g = true;
            synchronized (this) {
                if (this.f17682a.m18872b()) {
                    Log.b("TwitterOAuthView", "Notifying that the authorization step was done.");
                }
                notify();
            }
        }

        private AccessToken m18862g() {
            try {
                AccessToken oAuthAccessToken = this.f17686e.getOAuthAccessToken(this.f17687f, this.f17689h);
                if (!this.f17682a.m18872b()) {
                    return oAuthAccessToken;
                }
                Log.b("TwitterOAuthView", "Got an access token for " + oAuthAccessToken.getScreenName());
                return oAuthAccessToken;
            } catch (Throwable e) {
                e.printStackTrace();
                Log.d("TwitterOAuthView", "Failed to get an access token.", e);
                return null;
            }
        }
    }

    public TwitterOAuthView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m18869d();
    }

    public TwitterOAuthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m18869d();
    }

    public TwitterOAuthView(Context context) {
        super(context);
        m18869d();
    }

    private void m18869d() {
        WebSettings settings = getSettings();
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        setScrollBarStyle(0);
    }

    public void m18871a(String str, String str2, String str3, boolean z, Listener listener) {
        if (str == null || str2 == null || str3 == null || listener == null) {
            throw new IllegalArgumentException();
        }
        TwitterOAuthTask twitterOAuthTask;
        TwitterOAuthTask twitterOAuthTask2;
        Boolean valueOf = Boolean.valueOf(z);
        synchronized (this) {
            twitterOAuthTask = this.f17691a;
            twitterOAuthTask2 = new TwitterOAuthTask();
            this.f17691a = twitterOAuthTask2;
        }
        m18868a(twitterOAuthTask);
        twitterOAuthTask2.execute(new Object[]{str, str2, str3, valueOf, listener});
    }

    public void m18870a() {
        TwitterOAuthTask twitterOAuthTask;
        synchronized (this) {
            twitterOAuthTask = this.f17691a;
            this.f17691a = null;
        }
        m18868a(twitterOAuthTask);
    }

    private void m18868a(TwitterOAuthTask twitterOAuthTask) {
        if (twitterOAuthTask != null) {
            if (!twitterOAuthTask.isCancelled()) {
                if (m18872b()) {
                    Log.b("TwitterOAuthView", "Cancelling a task.");
                }
                twitterOAuthTask.cancel(true);
            }
            synchronized (twitterOAuthTask) {
                if (m18872b()) {
                    Log.b("TwitterOAuthView", "Notifying a task of cancellation.");
                }
                twitterOAuthTask.notify();
            }
        }
    }

    public boolean m18872b() {
        return this.f17693c;
    }

    public void setDebugEnabled(boolean z) {
        this.f17693c = z;
    }

    public boolean m18873c() {
        return this.f17692b;
    }

    public void setCancelOnDetachedFromWindow(boolean z) {
        this.f17692b = z;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (m18873c()) {
            m18870a();
        }
    }
}
