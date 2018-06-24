/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentSender
 *  android.content.IntentSender$SendIntentException
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  com.google.android.gms.auth.GoogleAuthUtil
 *  com.google.android.gms.auth.GooglePlayServicesAvailabilityException
 *  com.google.android.gms.auth.UserRecoverableAuthException
 *  com.google.android.gms.common.ConnectionResult
 *  com.google.android.gms.common.GooglePlayServicesUtil
 *  com.google.android.gms.common.api.GoogleApiClient
 *  com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks
 *  com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener
 *  com.google.android.gms.common.api.PendingResult
 *  com.google.android.gms.common.api.ResultCallback
 *  com.google.android.gms.common.api.Status
 *  com.google.android.gms.plus.Account
 *  com.google.android.gms.plus.People
 *  com.google.android.gms.plus.Plus
 *  com.google.android.gms.plus.model.people.Person
 *  com.google.android.gms.plus.model.people.Person$Name
 */
package com.smule.android.google.plus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.smule.android.google.plus.MagicGooglePlus;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;

@Deprecated
@SuppressLint(value={"MissingPermission"})
public class MagicGooglePlus
implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener {
    private static final String a = MagicGooglePlus.class.getName();
    private GoogleApiClient b;
    private ConnectionResult c;
    private Activity d;
    private Runnable e;
    private String f = null;
    private boolean g = false;
    private boolean h = false;
    private boolean i = true;

    static /* synthetic */ GoogleApiClient a(MagicGooglePlus magicGooglePlus) {
        return magicGooglePlus.b;
    }

    static /* synthetic */ boolean b(MagicGooglePlus magicGooglePlus) {
        return magicGooglePlus.d();
    }

    static /* synthetic */ String c() {
        return a;
    }

    static /* synthetic */ void c(MagicGooglePlus magicGooglePlus) {
        magicGooglePlus.f();
    }

    private boolean d() {
        Log.c(a, "getAuthTokenBlocking called");
        if (!this.b.isConnected()) {
            Log.c(a, "Plus Client is not connected.");
            return false;
        }
        String string2 = Plus.AccountApi.getAccountName(this.b);
        try {
            this.f = GoogleAuthUtil.getToken((Context)this.d, (String)string2, (String)"oauth2:https://www.googleapis.com/auth/plus.login profile");
            Log.c(a, "Token = " + this.f);
            return true;
        }
        catch (GooglePlayServicesAvailabilityException googlePlayServicesAvailabilityException) {
            Log.b(a, "GooglePlayServicesAvailabilityException caught", (Throwable)googlePlayServicesAvailabilityException);
            GooglePlayServicesUtil.getErrorDialog((int)googlePlayServicesAvailabilityException.getConnectionStatusCode(), (Activity)this.d, (int)11113).show();
            return false;
        }
        catch (UserRecoverableAuthException userRecoverableAuthException) {
            Log.b(a, "UserRecoverableAuthException caught", (Throwable)userRecoverableAuthException);
            this.i = false;
            this.d.startActivityForResult(userRecoverableAuthException.getIntent(), 11114);
            return false;
        }
        catch (Exception exception) {
            Log.d(a, "getUserLoginInfo - error getting token", exception);
            return false;
        }
    }

    private void e() {
        Log.c(a, "getAuthTokenAsync called");
        new AsyncTask<Object, Object, Boolean>(this){
            final /* synthetic */ MagicGooglePlus a;
            {
                this.a = magicGooglePlus;
            }

            protected Boolean a(Object[] arrobject) {
                return MagicGooglePlus.b(this.a);
            }

            protected void a(Boolean bl) {
                if (bl.booleanValue()) {
                    MagicGooglePlus.c(this.a);
                }
            }

            protected /* synthetic */ Object doInBackground(Object[] arrobject) {
                return this.a(arrobject);
            }

            protected /* synthetic */ void onPostExecute(Object object) {
                this.a((Boolean)object);
            }
        }.execute(new Object[]{null});
    }

    private void f() {
        if (this.e != null && this.b.isConnected()) {
            this.e.run();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public UserManager a(boolean bl) {
        Object object = null;
        Log.b(a, "getUserLoginInfo - begin. Token: " + this.f);
        Object object2 = Plus.PeopleApi.getCurrentPerson(this.b);
        if (object2 == null) {
            Log.e(a, "getUserLoginInfo - getCurrentPerson() returned null");
            return object;
        }
        String string2 = object2.getId();
        String string3 = Plus.AccountApi.getAccountName(this.b);
        Object object3 = object2.getGender() == 0 ? "male" : "female";
        String string4 = object2.getBirthday();
        String string5 = object2.getName().getGivenName();
        object2 = object2.getName().getFamilyName();
        if (this.f == null && !this.d()) return object;
        object3 = com.smule.android.network.managers.UserManager.a().a(string2, this.f, string3, (String)object3, string4, string5, (String)object2, com.smule.android.network.managers.UserManager.H(), bl);
        if (object3 != null) {
            object = object3;
            if (object3.a.c()) return object;
        }
        Log.e(a, "getUserLoginInfo - failed to login to SNP after Google Plus authentication");
        return object3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a() {
        block6 : {
            this.h = true;
            if (GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)this.d) != 0) {
                Log.e(a, "login - GooglePlayServicesUtil - services are NOT available");
                this.d.showDialog(1);
                return false;
            }
            try {
                if (this.b.isConnected()) break block6;
                Log.e(a, "login - Google Plus client is not connected");
                this.g = true;
                if (this.c != null) {
                    Log.e(a, "login - mConnectionResult is non-null");
                    this.c.startResolutionForResult(this.d, 11111);
                    return false;
                }
                Log.e(a, "login - mConnectionResult is null");
                if (this.b.isConnecting()) return false;
                Log.e(a, "login - Google Plus client is not connecting");
                this.b.connect();
                return false;
            }
            catch (IntentSender.SendIntentException sendIntentException) {
                this.b.connect();
                return false;
            }
        }
        if (this.f != null) {
            Log.e(a, "login - calling run");
            this.f();
            return false;
        }
        this.e();
        return false;
    }

    public boolean b() {
        Log.b(a, "disconnect - begin");
        Log.b(a, "disconnect - default account cleared");
        Plus.AccountApi.clearDefaultAccount(this.b);
        Plus.AccountApi.revokeAccessAndDisconnect(this.b).setResultCallback((ResultCallback)new ResultCallback<Status>(this){
            final /* synthetic */ MagicGooglePlus a;
            {
                this.a = magicGooglePlus;
            }

            public void a(Status status) {
                Log.e(MagicGooglePlus.c(), "User access revoked!");
                if (!status.isSuccess()) {
                    MagicGooglePlus.a(this.a).disconnect();
                }
                MagicGooglePlus.a(this.a).connect();
            }

            public /* synthetic */ void onResult(com.google.android.gms.common.api.Result result) {
                this.a((Status)result);
            }
        });
        Log.b(a, "disconnect - access revoked");
        com.smule.android.network.managers.UserManager.a().M();
        this.b.disconnect();
        this.b.connect();
        return true;
    }

    public void onConnected(Bundle bundle) {
        Log.b(a, "onConnected - called with bundle: " + (Object)bundle + " / " + this.f);
        this.g = false;
        if (this.e != null && this.f != null) {
            this.f();
            return;
        }
        this.e();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.b(a, "onConnectionFailed - called - " + (Object)connectionResult);
        if (connectionResult != null && connectionResult.hasResolution()) {
            this.c = connectionResult;
            if (this.g) {
                this.a();
            }
        }
    }

    public void onConnectionSuspended(int n) {
        Log.b(a, "onConnectionSuspended - called");
    }
}

