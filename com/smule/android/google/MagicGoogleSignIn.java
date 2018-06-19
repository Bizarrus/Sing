package com.smule.android.google;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.smule.android.logging.Log;

public class MagicGoogleSignIn implements OnConnectionFailedListener {
    public static final String f15995a = MagicGoogleSignIn.class.getName();
    private AppCompatActivity f15996b;
    private SignInListener f15997c;
    private GoogleApiClient f15998d;

    public interface SignInListener {
        void mo6919a(GoogleSignInAccount googleSignInAccount);

        void mo6920a(String str);
    }

    public MagicGoogleSignIn(AppCompatActivity appCompatActivity, String str, SignInListener signInListener) {
        this.f15996b = appCompatActivity;
        this.f15997c = signInListener;
        this.f15998d = new Builder(appCompatActivity).enableAutoManage(appCompatActivity, this).addApi(Auth.GOOGLE_SIGN_IN_API, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken(str).build()).build();
    }

    public void m17769a() {
        this.f15996b.startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.f15998d), 4386);
    }

    public boolean m17770a(int i, int i2, Intent intent) {
        if (i != 4386) {
            return false;
        }
        m17768a(Auth.GoogleSignInApi.getSignInResultFromIntent(intent));
        return true;
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (this.f15997c != null) {
            this.f15997c.mo6920a(connectionResult.getErrorMessage());
        }
    }

    private void m17768a(GoogleSignInResult googleSignInResult) {
        if (this.f15997c != null) {
            if (googleSignInResult.isSuccess()) {
                this.f15997c.mo6919a(googleSignInResult.getSignInAccount());
            } else if (googleSignInResult.getStatus().getStatusCode() != GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
                Log.d(f15995a, "Failed sign-in: " + googleSignInResult.getStatus());
                this.f15997c.mo6920a(googleSignInResult.getStatus().toString());
            }
        }
    }
}
