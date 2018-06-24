/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.ActivityNotFoundException
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.net.ConnectivityManager
 *  android.net.MailTo
 *  android.net.NetworkInfo
 *  android.net.NetworkInfo$DetailedState
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Message
 *  android.text.TextUtils
 *  android.view.View
 *  android.webkit.CookieManager
 *  android.webkit.WebChromeClient
 *  android.webkit.WebSettings
 *  android.webkit.WebView
 *  android.webkit.WebView$WebViewTransport
 *  android.webkit.WebViewClient
 *  android.widget.FrameLayout
 *  com.smule.singandroid.utils.DeepLink
 *  com.smule.singandroid.utils.DeepLink$Hosts
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.MagicDevice;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.WebViewFragment_;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.SingAnalytics;
import java.io.File;
import java.net.URLDecoder;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class WebViewFragment
extends BaseFragment {
    public static final String g = WebViewFragment.j = WebViewFragment.class.getName();
    private static final String j;
    @ViewById
    protected FrameLayout h;
    @ViewById
    protected View i;
    private WebView k;
    private String l;
    private boolean m;
    private String n;
    private boolean o;
    private boolean p;
    private boolean q;
    private long r = 0;

    private String F() {
        String string2;
        Activity activity = this.getActivity();
        DeviceSettings deviceSettings = new DeviceSettings();
        String string3 = "\n\n" + "App Version: " + MagicNetwork.d().getAppVersion() + "\n";
        string3 = string3 + "API: " + MagicNetwork.c() + "\n";
        string3 = string3 + "AccountId: " + UserManager.a().f() + "\n";
        string3 = string3 + "Email: " + UserManager.a().j() + "\n";
        string3 = string3 + "Device ID: " + MagicDevice.a((Context)this.getActivity()) + "\n";
        string3 = string2 = string3 + "Android ID: " + MagicDevice.b((Context)this.getActivity()) + "\n";
        if (activity != null) {
            String string4 = MagicDevice.a((Context)activity, true);
            string3 = string2;
            if (string4 != null) {
                string3 = string2 + "Device Unique ID: " + string4 + "\n";
            }
        }
        string3 = string3 + "Device Model: " + Build.MODEL + "\n";
        string3 = string3 + "Device OS: " + Build.VERSION.RELEASE + "\n";
        string3 = string3 + "OS Version: " + System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")\n";
        string3 = string3 + "OS API Level: " + Build.VERSION.SDK_INT + "\n";
        string3 = string3 + "Device: " + Build.DEVICE + "\n";
        string3 = string3 + "Model (and Product): " + Build.MODEL + " (" + Build.PRODUCT + ")\n";
        if (activity != null) {
            string2 = ((ConnectivityManager)activity.getSystemService("connectivity")).getActiveNetworkInfo();
            if (string2 == null) {
                return string3 + "Active Network: none\n";
            }
            string3 = string3 + "Active Network: " + string2.getTypeName() + "\n";
            string3 = string3 + "Network Connected: " + string2.isConnected() + "\n";
            string3 = string3 + "Network Detailed State: " + (Object)string2.getDetailedState() + "\n";
        }
        string3 = string3 + "OpenSL Stream Version: " + deviceSettings.i() + "\n";
        return string3 + "Build time: 2018/06/20 09:59:28 PM\n";
    }

    private void G() {
        if (this.k != null) {
            this.k.clearHistory();
            this.k.clearCache(true);
            this.k.loadUrl("about:blank");
            this.k.stopLoading();
            this.k.setWebChromeClient(null);
            this.k.setWebViewClient(null);
            this.k.destroy();
            this.k = null;
        }
    }

    private boolean H() {
        long l = System.currentTimeMillis();
        if (this.r == 0) {
            this.r = l;
            return true;
        }
        if (l - this.r > 500) {
            this.r = l;
            return true;
        }
        return false;
    }

    public static WebViewFragment a(String string2, String string3) {
        return WebViewFragment.a(string2, string3, false);
    }

    public static WebViewFragment a(String string2, String string3, boolean bl) {
        WebViewFragment_ webViewFragment_ = new WebViewFragment_();
        Bundle bundle = new Bundle();
        bundle.putString("URL_KEY", string2);
        bundle.putString("TOP_BAR_TITLE_KEY", string3);
        bundle.putBoolean("USE_APPLICATION_CONTEXT", bl);
        webViewFragment_.setArguments(bundle);
        return webViewFragment_;
    }

    public static WebViewFragment a(String string2, boolean bl) {
        return WebViewFragment.a(string2, null, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"SetJavaScriptEnabled", "NewAPI"})
    @AfterViews
    protected void a() {
        Log.a(j, "updateFollowingViewBinding - loading url at: " + this.l);
        Object object = this.o ? this.getActivity().getApplicationContext() : this.getActivity();
        this.G();
        this.k = new WebView((Context)object);
        this.h.addView((View)this.k);
        this.k.clearCache(true);
        this.k.clearHistory();
        this.k.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT > 16) {
            this.k.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.k.getSettings().setDomStorageEnabled(true);
        this.k.getSettings().setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT < 19) {
            this.k.getSettings().setDatabasePath(object.getDatabasePath("web").getParent());
        }
        this.k.setWebViewClient((WebViewClient)new UrlOverrideLoadingWebViewClient());
        this.k.getSettings().setSupportMultipleWindows(true);
        this.k.setWebChromeClient(new WebChromeClient(){

            public boolean onCreateWindow(WebView webView, boolean bl, boolean bl2, Message message) {
                webView = WebViewFragment.this.getActivity();
                if (webView == null) {
                    return false;
                }
                WebView webView2 = new WebView((Context)webView);
                webView2.setWebViewClient(new WebViewClient((Activity)webView){
                    final /* synthetic */ Activity a;
                    {
                        this.a = activity;
                    }

                    public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
                        webView = new Intent("android.intent.action.VIEW", Uri.parse((String)string2));
                        this.a.startActivity((Intent)webView);
                        return true;
                    }
                });
                ((WebView.WebViewTransport)message.obj).setWebView(webView2);
                message.sendToTarget();
                return true;
            }

        });
        object = MagicNetwork.f();
        if (!TextUtils.isEmpty((CharSequence)object)) {
            CookieManager.getInstance().setCookie("smule.com", (String)object);
        }
        this.k.loadUrl(this.l);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.l = this.getArguments().getString("URL_KEY");
        this.m = this.l.contains("/s/promotions/");
        this.n = this.getArguments().getString("TOP_BAR_TITLE_KEY");
        this.o = this.getArguments().getBoolean("USE_APPLICATION_CONTEXT");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.G();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.h != null) {
            this.h.removeAllViews();
            this.h = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.k.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.k.onResume();
        if (this.m) {
            this.p().m_();
        }
        if (this.p && !this.q && SubscriptionManager.a().b()) {
            this.getActivity().onBackPressed();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (this.n != null) {
            this.a((CharSequence)this.n);
            return;
        }
        this.c(2131296848);
    }

    @Override
    public String x() {
        return j;
    }

    private class UrlOverrideLoadingWebViewClient
    extends WebViewClient {
        private UrlOverrideLoadingWebViewClient() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
        public void onPageFinished(WebView webView, String string2) {
            Log.a(j, "onPageFinished - url: " + WebViewFragment.this.l);
            if (WebViewFragment.this.isAdded()) {
                WebViewFragment.this.i.setVisibility(8);
                if (string2.contains("#support")) {
                    String string3 = WebViewFragment.this.getString(2131297962, new Object[]{MagicNetwork.d().getAppVersion()});
                    String string4 = WebViewFragment.this.getString(2131297961);
                    string3 = MailTo.parse((String)("mailto:" + string4 + "?subject=" + string3));
                    string4 = new Intent("android.intent.action.SEND");
                    string4.setType("plain/text").putExtra("android.intent.extra.EMAIL", new String[]{string3.getTo()});
                    string4.putExtra("android.intent.extra.SUBJECT", string3.getSubject());
                    string4.putExtra("android.intent.extra.CC", string3.getCc());
                    string4.putExtra("android.intent.extra.TEXT", WebViewFragment.this.F());
                    try {
                        WebViewFragment.this.startActivity((Intent)string4);
                    }
                    catch (ActivityNotFoundException activityNotFoundException) {
                        WebViewFragment.this.a(WebViewFragment.this.getResources().getString(2131297624), Toaster.b);
                    }
                }
                if (string2.contains("/s/promotions/")) {
                    SingAnalytics.b((long)Long.parseLong(Uri.parse((String)string2).getLastPathSegment()));
                }
            }
            super.onPageFinished(webView, string2);
        }

        public void onPageStarted(WebView webView, String string2, Bitmap bitmap) {
            Log.a(j, "onPageStarted - url: " + WebViewFragment.this.l);
            if (WebViewFragment.this.isAdded()) {
                WebViewFragment.this.i.setVisibility(0);
            }
            super.onPageStarted(webView, string2, bitmap);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
            String string3;
            Log.a(j, "shouldOverrideUrlLoading - clicked url: " + string2);
            if (!WebViewFragment.this.isAdded()) {
                return false;
            }
            Uri uri = Uri.parse((String)string2);
            if (uri.getHost() != null && uri.getHost().equals("t.umblr.com")) {
                string2 = URLDecoder.decode(uri.getQueryParameter("z"));
                uri = Uri.parse((String)string2);
            }
            if ((string3 = uri.getScheme()).equals("market")) {
                block18 : {
                    try {
                        if (WebViewFragment.this.H()) break block18;
                        Log.b(j, "shouldOverrideUrlLoading: market url already processed");
                        return true;
                    }
                    catch (ActivityNotFoundException activityNotFoundException) {
                        webView.loadUrl("http://play.google.com/store/apps/" + uri.getHost() + "?" + uri.getQuery());
                        return true;
                    }
                }
                string3 = new Intent("android.intent.action.VIEW");
                string3.setData(Uri.parse((String)string2));
                WebViewFragment.this.startActivity((Intent)string3);
                return true;
            }
            if (string3.equals("smulesing")) {
                if (!WebViewFragment.this.H()) {
                    Log.b(j, "shouldOverrideUrlLoading: smulesing url already processed");
                    return true;
                }
                try {
                    webView = new DeepLink(uri);
                    ((MasterActivity)WebViewFragment.this.getActivity()).a((DeepLink)webView, false);
                    if (WebViewFragment.this.m && (webView.c == DeepLink.Hosts.X || webView.c == DeepLink.Hosts.W)) {
                        WebViewFragment.this.q = SubscriptionManager.a().b();
                        WebViewFragment.this.p = true;
                    }
                    do {
                        return true;
                        break;
                    } while (true);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    Log.e(j, "No match for URI: " + (Object)uri);
                    return true;
                }
            }
            if (WebViewFragment.this.m) {
                if (!WebViewFragment.this.H()) {
                    Log.b(j, "shouldOverrideUrlLoading: promotions url already processed");
                    return true;
                }
                WebViewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)string2)));
                return true;
            }
            if (string3.equals("mailto")) {
                block19 : {
                    try {
                        if (WebViewFragment.this.H()) break block19;
                        Log.b(j, "shouldOverrideUrlLoading: mailto url already processed");
                        return true;
                    }
                    catch (ActivityNotFoundException activityNotFoundException) {
                        WebViewFragment.this.a(WebViewFragment.this.getResources().getString(2131297624), Toaster.b);
                        return true;
                    }
                }
                webView = MailTo.parse((String)string2);
                string2 = new Intent("android.intent.action.SEND");
                string2.setType("text/plain");
                string2.putExtra("android.intent.extra.EMAIL", new String[]{webView.getTo()});
                string2.putExtra("android.intent.extra.SUBJECT", webView.getSubject());
                string2.putExtra("android.intent.extra.CC", webView.getCc());
                string2.putExtra("android.intent.extra.TEXT", webView.getBody());
                WebViewFragment.this.startActivity((Intent)string2);
                return true;
            }
            webView.loadUrl(string2);
            return false;
        }
    }

}

