/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.support.annotation.NonNull
 *  android.support.v4.app.FragmentActivity
 *  android.support.v7.app.AppCompatActivity
 *  com.google.android.gms.auth.api.Auth
 *  com.google.android.gms.auth.api.signin.GoogleSignInAccount
 *  com.google.android.gms.auth.api.signin.GoogleSignInApi
 *  com.google.android.gms.auth.api.signin.GoogleSignInOptions
 *  com.google.android.gms.auth.api.signin.GoogleSignInOptions$Builder
 *  com.google.android.gms.auth.api.signin.GoogleSignInResult
 *  com.google.android.gms.common.ConnectionResult
 *  com.google.android.gms.common.api.Api
 *  com.google.android.gms.common.api.Api$ApiOptions
 *  com.google.android.gms.common.api.Api$ApiOptions$HasOptions
 *  com.google.android.gms.common.api.GoogleApiClient
 *  com.google.android.gms.common.api.GoogleApiClient$Builder
 *  com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener
 *  com.google.android.gms.common.api.Status
 */
package com.smule.android.google;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.smule.android.logging.Log;

public class MagicGoogleSignIn
implements GoogleApiClient.OnConnectionFailedListener {
    public static final String a = MagicGoogleSignIn.class.getName();
    private AppCompatActivity b;
    private SignInListener c;
    private GoogleApiClient d;

    public MagicGoogleSignIn(AppCompatActivity appCompatActivity, String string2, SignInListener signInListener) {
        this.b = appCompatActivity;
        this.c = signInListener;
        string2 = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken(string2).build();
        this.d = new GoogleApiClient.Builder((Context)appCompatActivity).enableAutoManage((FragmentActivity)appCompatActivity, (GoogleApiClient.OnConnectionFailedListener)this).addApi(Auth.GOOGLE_SIGN_IN_API, (Api.ApiOptions.HasOptions)string2).build();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(GoogleSignInResult googleSignInResult) {
        block5 : {
            block4 : {
                if (this.c == null) break block4;
                if (googleSignInResult.isSuccess()) {
                    this.c.a(googleSignInResult.getSignInAccount());
                    return;
                }
                if (googleSignInResult.getStatus().getStatusCode() != 12501) break block5;
            }
            return;
        }
        Log.d(a, "Failed sign-in: " + (Object)googleSignInResult.getStatus());
        this.c.a(googleSignInResult.getStatus().toString());
    }

    public void a() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(this.d);
        this.b.startActivityForResult(intent, 4386);
    }

    public boolean a(int n, int n2, Intent intent) {
        if (n == 4386) {
            this.a(Auth.GoogleSignInApi.getSignInResultFromIntent(intent));
            return true;
        }
        return false;
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (this.c != null) {
            this.c.a(connectionResult.getErrorMessage());
        }
    }

    public static interface SignInListener {
        public void a(GoogleSignInAccount var1);

        public void a(String var1);
    }

}

