package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.facebook.internal.AnalyticsEvents;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.MagicDevice;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.DeepLink.Hosts;
import com.smule.singandroid.utils.SingAnalytics;
import java.net.URLDecoder;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class WebViewFragment extends BaseFragment {
    public static final String f20489e = f20490h;
    private static final String f20490h = WebViewFragment.class.getName();
    @ViewById
    protected FrameLayout f20491f;
    @ViewById
    protected View f20492g;
    private WebView f20493i;
    private String f20494j;
    private boolean f20495k;
    private String f20496l;
    private boolean f20497m;
    private boolean f20498n;
    private boolean f20499o;
    private long f20500p = 0;

    class C42101 extends WebChromeClient {
        final /* synthetic */ WebViewFragment f20487a;

        C42101(WebViewFragment webViewFragment) {
            this.f20487a = webViewFragment;
        }

        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            final Context activity = this.f20487a.getActivity();
            if (activity == null) {
                return false;
            }
            WebView webView2 = new WebView(activity);
            webView2.setWebViewClient(new WebViewClient(this) {
                final /* synthetic */ C42101 f20486b;

                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                }
            });
            ((WebViewTransport) message.obj).setWebView(webView2);
            message.sendToTarget();
            return true;
        }
    }

    private class UrlOverrideLoadingWebViewClient extends WebViewClient {
        final /* synthetic */ WebViewFragment f20488a;

        private UrlOverrideLoadingWebViewClient(WebViewFragment webViewFragment) {
            this.f20488a = webViewFragment;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Log.a(WebViewFragment.f20490h, "onPageStarted - url: " + this.f20488a.f20494j);
            if (this.f20488a.isAdded()) {
                this.f20488a.f20492g.setVisibility(0);
            }
            super.onPageStarted(webView, str, bitmap);
        }

        @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
        public void onPageFinished(WebView webView, String str) {
            Log.a(WebViewFragment.f20490h, "onPageFinished - url: " + this.f20488a.f20494j);
            if (this.f20488a.isAdded()) {
                this.f20488a.f20492g.setVisibility(8);
                if (str.contains("#support")) {
                    MailTo parse = MailTo.parse("mailto:" + this.f20488a.getString(C1947R.string.support_email) + "?subject=" + this.f20488a.getString(C1947R.string.support_subject, new Object[]{MagicNetwork.d().getAppVersion()}));
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("plain/text").putExtra("android.intent.extra.EMAIL", new String[]{parse.getTo()});
                    intent.putExtra("android.intent.extra.SUBJECT", parse.getSubject());
                    intent.putExtra("android.intent.extra.CC", parse.getCc());
                    intent.putExtra("android.intent.extra.TEXT", this.f20488a.m22009A());
                    try {
                        this.f20488a.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        this.f20488a.m19841a(this.f20488a.getResources().getString(C1947R.string.web_view_no_email_client), Toaster$Duration.LONG);
                    }
                }
                if (str.contains("/s/promotions/")) {
                    SingAnalytics.m26125b(Long.valueOf(Long.parseLong(Uri.parse(str).getLastPathSegment())).longValue());
                }
            }
            super.onPageFinished(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri uri;
            Log.a(WebViewFragment.f20490h, "shouldOverrideUrlLoading - clicked url: " + str);
            if (!this.f20488a.isAdded()) {
                return false;
            }
            Uri parse = Uri.parse(str);
            if (parse.getHost() == null || !parse.getHost().equals("t.umblr.com")) {
                uri = parse;
            } else {
                str = URLDecoder.decode(parse.getQueryParameter("z"));
                uri = Uri.parse(str);
            }
            String scheme = uri.getScheme();
            if (scheme.equals("market")) {
                try {
                    if (this.f20488a.m22011C()) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(str));
                        this.f20488a.startActivity(intent);
                        return true;
                    }
                    Log.b(WebViewFragment.f20490h, "shouldOverrideUrlLoading: market url already processed");
                    return true;
                } catch (ActivityNotFoundException e) {
                    webView.loadUrl("http://play.google.com/store/apps/" + uri.getHost() + "?" + uri.getQuery());
                    return true;
                }
            } else if (scheme.equals("smulesing")) {
                if (this.f20488a.m22011C()) {
                    try {
                        DeepLink deepLink = new DeepLink(uri);
                        ((MasterActivity) this.f20488a.getActivity()).a(deepLink, false);
                        if (this.f20488a.f20495k && (deepLink.f24741c == Hosts.Purchase || deepLink.f24741c == Hosts.Subscription)) {
                            this.f20488a.f20499o = SubscriptionManager.a().b();
                            this.f20488a.f20498n = true;
                        }
                    } catch (IllegalArgumentException e2) {
                        Log.e(WebViewFragment.f20490h, "No match for URI: " + uri);
                    }
                    return true;
                }
                Log.b(WebViewFragment.f20490h, "shouldOverrideUrlLoading: smulesing url already processed");
                return true;
            } else if (this.f20488a.f20495k) {
                if (this.f20488a.m22011C()) {
                    this.f20488a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                }
                Log.b(WebViewFragment.f20490h, "shouldOverrideUrlLoading: promotions url already processed");
                return true;
            } else if (scheme.equals("mailto")) {
                try {
                    if (this.f20488a.m22011C()) {
                        MailTo parse2 = MailTo.parse(str);
                        Intent intent2 = new Intent("android.intent.action.SEND");
                        intent2.setType("text/plain");
                        intent2.putExtra("android.intent.extra.EMAIL", new String[]{parse2.getTo()});
                        intent2.putExtra("android.intent.extra.SUBJECT", parse2.getSubject());
                        intent2.putExtra("android.intent.extra.CC", parse2.getCc());
                        intent2.putExtra("android.intent.extra.TEXT", parse2.getBody());
                        this.f20488a.startActivity(intent2);
                        return true;
                    }
                    Log.b(WebViewFragment.f20490h, "shouldOverrideUrlLoading: mailto url already processed");
                    return true;
                } catch (ActivityNotFoundException e3) {
                    this.f20488a.m19841a(this.f20488a.getResources().getString(C1947R.string.web_view_no_email_client), Toaster$Duration.LONG);
                    return true;
                }
            } else {
                webView.loadUrl(str);
                return false;
            }
        }
    }

    public static WebViewFragment m22014a(String str, boolean z) {
        return m22013a(str, null, z);
    }

    public static WebViewFragment m22012a(String str, String str2) {
        return m22013a(str, str2, false);
    }

    public static WebViewFragment m22013a(String str, String str2, boolean z) {
        WebViewFragment webViewFragment_ = new WebViewFragment_();
        Bundle bundle = new Bundle();
        bundle.putString("URL_KEY", str);
        bundle.putString("TOP_BAR_TITLE_KEY", str2);
        bundle.putBoolean("USE_APPLICATION_CONTEXT", z);
        webViewFragment_.setArguments(bundle);
        return webViewFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f20494j = getArguments().getString("URL_KEY");
        this.f20495k = this.f20494j.contains("/s/promotions/");
        this.f20496l = getArguments().getString("TOP_BAR_TITLE_KEY");
        this.f20497m = getArguments().getBoolean("USE_APPLICATION_CONTEXT");
    }

    @AfterViews
    @SuppressLint({"SetJavaScriptEnabled", "NewAPI"})
    protected void m22022a() {
        Log.a(f20490h, "updateFollowingViewBinding - loading url at: " + this.f20494j);
        Context applicationContext = this.f20497m ? getActivity().getApplicationContext() : getActivity();
        m22010B();
        this.f20493i = new WebView(applicationContext);
        this.f20491f.addView(this.f20493i);
        this.f20493i.clearCache(true);
        this.f20493i.clearHistory();
        this.f20493i.getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT > 16) {
            this.f20493i.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.f20493i.getSettings().setDomStorageEnabled(true);
        this.f20493i.getSettings().setDatabaseEnabled(true);
        if (VERSION.SDK_INT < 19) {
            this.f20493i.getSettings().setDatabasePath(applicationContext.getDatabasePath(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB).getParent());
        }
        this.f20493i.setWebViewClient(new UrlOverrideLoadingWebViewClient());
        this.f20493i.getSettings().setSupportMultipleWindows(true);
        this.f20493i.setWebChromeClient(new C42101(this));
        Object f = MagicNetwork.f();
        if (!TextUtils.isEmpty(f)) {
            CookieManager.getInstance().setCookie("smule.com", f);
        }
        this.f20493i.loadUrl(this.f20494j);
    }

    public void onStart() {
        super.onStart();
        if (this.f20496l != null) {
            mo6861a(this.f20496l);
        } else {
            m19850c((int) C1947R.string.full_app_name);
        }
    }

    public void onResume() {
        super.onResume();
        this.f20493i.onResume();
        if (this.f20495k) {
            m19862m().a();
        }
        if (this.f20498n && !this.f20499o && SubscriptionManager.a().b()) {
            getActivity().onBackPressed();
        }
    }

    public void onPause() {
        super.onPause();
        this.f20493i.onPause();
    }

    public String mo6383s() {
        return f20490h;
    }

    private String m22009A() {
        String a;
        Context activity = getActivity();
        String str = ((((("\n\n" + "App Version: " + MagicNetwork.d().getAppVersion() + "\n") + "API: " + MagicNetwork.c() + "\n") + "AccountId: " + UserManager.a().f() + "\n") + "Email: " + UserManager.a().j() + "\n") + "Device ID: " + MagicDevice.m19003a(getActivity()) + "\n") + "Android ID: " + MagicDevice.m19005b(getActivity()) + "\n";
        if (activity != null) {
            a = MagicDevice.m19004a(activity, true);
            if (a != null) {
                str = str + "Device Unique ID: " + a + "\n";
            }
        }
        a = (((((str + "Device Model: " + Build.MODEL + "\n") + "Device OS: " + VERSION.RELEASE + "\n") + "OS Version: " + System.getProperty("os.version") + "(" + VERSION.INCREMENTAL + ")\n") + "OS API Level: " + VERSION.SDK_INT + "\n") + "Device: " + Build.DEVICE + "\n") + "Model (and Product): " + Build.MODEL + " (" + Build.PRODUCT + ")\n";
        if (activity != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return a + "Active Network: none\n";
            }
            str = ((a + "Active Network: " + activeNetworkInfo.getTypeName() + "\n") + "Network Connected: " + activeNetworkInfo.isConnected() + "\n") + "Network Detailed State: " + activeNetworkInfo.getDetailedState() + "\n";
        } else {
            str = a;
        }
        return (str + "OpenSL Stream Version: " + DeviceSettings.h() + "\n") + "Build time: 2017/06/14 06:04:16 PM\n";
    }

    private void m22010B() {
        if (this.f20493i != null) {
            this.f20493i.clearHistory();
            this.f20493i.clearCache(true);
            this.f20493i.loadUrl("about:blank");
            this.f20493i.stopLoading();
            this.f20493i.setWebChromeClient(null);
            this.f20493i.setWebViewClient(null);
            this.f20493i.destroy();
            this.f20493i = null;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.f20491f != null) {
            this.f20491f.removeAllViews();
            this.f20491f = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        m22010B();
    }

    private boolean m22011C() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f20500p == 0) {
            this.f20500p = currentTimeMillis;
            return true;
        } else if (currentTimeMillis - this.f20500p <= 500) {
            return false;
        } else {
            this.f20500p = currentTimeMillis;
            return true;
        }
    }
}
