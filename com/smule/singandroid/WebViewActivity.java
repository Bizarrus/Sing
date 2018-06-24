package com.smule.singandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.smule.android.logging.Log;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EActivity
public class WebViewActivity extends BaseActivity {
    private static final String f20477k = WebViewActivity.class.getName();
    @ViewById
    protected View f20478g;
    @InstanceState
    protected String f20479h;
    @InstanceState
    protected boolean f20480i;
    @InstanceState
    protected boolean f20481j;

    class C42081 implements OnClickListener {
        final /* synthetic */ WebViewActivity f20476a;

        C42081(WebViewActivity webViewActivity) {
            this.f20476a = webViewActivity;
        }

        public void onClick(View view) {
            this.f20476a.finish();
        }
    }

    public static Intent m22001a(Context context, String str, boolean z, boolean z2) {
        Intent intent = new Intent(context, WebViewActivity_.class);
        intent.putExtra("URL_KEY", str);
        intent.putExtra("SHOW_CLOSE_VIEW_KEY", z);
        intent.putExtra("USE_APPLICATION_CONTEXT", z2);
        return intent;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.f20479h = getIntent().getStringExtra("URL_KEY");
            this.f20480i = getIntent().getBooleanExtra("SHOW_CLOSE_VIEW_KEY", true);
            this.f20481j = getIntent().getBooleanExtra("USE_APPLICATION_CONTEXT", false);
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        WebViewFragment webViewFragment = (WebViewFragment) fragmentManager.findFragmentByTag(WebViewFragment.f20489e);
        if (webViewFragment != null) {
            beginTransaction.detach(webViewFragment);
        }
        beginTransaction.add(C1947R.id.web_view_fragment_container_view, WebViewFragment.m22014a(this.f20479h, this.f20481j), WebViewFragment.f20489e);
        beginTransaction.commit();
    }

    protected void m22002d() {
        super.d();
        Log.b(f20477k, "updateFollowingViewBinding - loading url at: " + this.f20479h);
        this.f20478g.setVisibility(this.f20480i ? 0 : 8);
        this.f20478g.setOnClickListener(new C42081(this));
    }
}
