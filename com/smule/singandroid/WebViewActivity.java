/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.view.View
 *  android.view.View$OnClickListener
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.smule.android.logging.Log;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.WebViewActivity_;
import com.smule.singandroid.WebViewFragment;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@SuppressLint(value={"Registered"})
@EActivity
public class WebViewActivity
extends BaseActivity {
    private static final String k = WebViewActivity.class.getName();
    @ViewById
    protected View g;
    @InstanceState
    protected String h;
    @InstanceState
    protected boolean i;
    @InstanceState
    protected boolean j;

    public static Intent a(Context context, String string2, boolean bl, boolean bl2) {
        context = new Intent(context, WebViewActivity_.class);
        context.putExtra("URL_KEY", string2);
        context.putExtra("SHOW_CLOSE_VIEW_KEY", bl);
        context.putExtra("USE_APPLICATION_CONTEXT", bl2);
        return context;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void f() {
        super.f();
        Log.b(k, "updateFollowingViewBinding - loading url at: " + this.h);
        View view = this.g;
        int n = this.i ? 0 : 8;
        view.setVisibility(n);
        this.g.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                WebViewActivity.this.finish();
            }
        });
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.h = this.getIntent().getStringExtra("URL_KEY");
            this.i = this.getIntent().getBooleanExtra("SHOW_CLOSE_VIEW_KEY", true);
            this.j = this.getIntent().getBooleanExtra("USE_APPLICATION_CONTEXT", false);
        }
        Object object = this.getFragmentManager();
        bundle = object.beginTransaction();
        if ((object = (WebViewFragment)object.findFragmentByTag(WebViewFragment.g)) != null) {
            bundle.detach((Fragment)object);
        }
        bundle.add(2131756817, (Fragment)WebViewFragment.a(this.h, this.j), WebViewFragment.g);
        bundle.commit();
    }

}

