/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.os.Bundle
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.ViewById
 *  twitter4j.auth.AccessToken
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.smule.android.logging.Log;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.twitter.TwitterOAuthView;
import com.smule.singandroid.BaseActivity;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import twitter4j.auth.AccessToken;

@SuppressLint(value={"Registered"})
@EActivity
public class TwitterOAuthActivity
extends BaseActivity
implements TwitterOAuthView.Listener {
    public static final String g = TwitterOAuthActivity.class.getName();
    @ViewById
    protected TwitterOAuthView h;

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(TwitterOAuthView object, TwitterOAuthView.Result result) {
        if (result != TwitterOAuthView.Result.b) {
            String string2 = g;
            StringBuilder stringBuilder = new StringBuilder().append("TwitterOAuthView returned result code '");
            object = result != null ? result.name() : "NULL";
            Log.d(string2, stringBuilder.append((String)object).append("'").toString());
        }
        this.setResult(0);
        this.finish();
    }

    @Override
    public void a(TwitterOAuthView twitterOAuthView, AccessToken accessToken) {
        MagicTwitter.a().a(accessToken);
        this.setResult(-1);
        this.finish();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.h.setDebugEnabled(true);
        this.h.a(this.getString(2131297966), this.getString(2131297967), this.getString(2131297965), true, this);
    }
}

