package com.smule.android.google.plus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager.LoginResponse;

@SuppressLint({"MissingPermission"})
@Deprecated
public class MagicGooglePlus implements ConnectionCallbacks, OnConnectionFailedListener {
    private static final String f6691a = MagicGooglePlus.class.getName();
    private GoogleApiClient f6692b;
    private ConnectionResult f6693c;
    private Activity f6694d;
    private Runnable f6695e;
    private String f6696f = null;
    private boolean f6697g = false;
    private boolean f6698h = false;
    private boolean f6699i = true;

    public void onConnected(Bundle bundle) {
        Log.m7770b(f6691a, "onConnected - called with bundle: " + bundle + " / " + this.f6696f);
        this.f6697g = false;
        if (this.f6695e == null || this.f6696f == null) {
            m7710e();
        } else {
            m7711f();
        }
    }

    public void onConnectionSuspended(int i) {
        Log.m7770b(f6691a, "onConnectionSuspended - called");
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.m7770b(f6691a, "onConnectionFailed - called - " + connectionResult);
        if (connectionResult != null && connectionResult.hasResolution()) {
            this.f6693c = connectionResult;
            if (this.f6697g) {
                m7713a();
            }
        }
    }

    public LoginResponse m7712a(boolean z) {
        LoginResponse loginResponse = null;
        Log.m7770b(f6691a, "getUserLoginInfo - begin. Token: " + this.f6696f);
        Person currentPerson = Plus.PeopleApi.getCurrentPerson(this.f6692b);
        if (currentPerson == null) {
            Log.m7776e(f6691a, "getUserLoginInfo - getCurrentPerson() returned null");
        } else {
            String id = currentPerson.getId();
            String accountName = Plus.AccountApi.getAccountName(this.f6692b);
            String str = currentPerson.getGender() == 0 ? "male" : "female";
            String birthday = currentPerson.getBirthday();
            String givenName = currentPerson.getName().getGivenName();
            String familyName = currentPerson.getName().getFamilyName();
            if (this.f6696f != null || m7709d()) {
                loginResponse = UserManager.m8111a().m8164a(id, this.f6696f, accountName, str, birthday, givenName, familyName, UserManager.m8105I(), z);
                if (loginResponse == null || !loginResponse.a.m7850c()) {
                    Log.m7776e(f6691a, "getUserLoginInfo - failed to login to SNP after Google Plus authentication");
                }
            }
        }
        return loginResponse;
    }

    public boolean m7713a() {
        this.f6698h = true;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.f6694d) != 0) {
            Log.m7776e(f6691a, "login - GooglePlayServicesUtil - services are NOT available");
            this.f6694d.showDialog(1);
        } else {
            try {
                if (!this.f6692b.isConnected()) {
                    Log.m7776e(f6691a, "login - Google Plus client is not connected");
                    this.f6697g = true;
                    if (this.f6693c != null) {
                        Log.m7776e(f6691a, "login - mConnectionResult is non-null");
                        this.f6693c.startResolutionForResult(this.f6694d, 11111);
                    } else {
                        Log.m7776e(f6691a, "login - mConnectionResult is null");
                        if (!this.f6692b.isConnecting()) {
                            Log.m7776e(f6691a, "login - Google Plus client is not connecting");
                            this.f6692b.connect();
                        }
                    }
                } else if (this.f6696f != null) {
                    Log.m7776e(f6691a, "login - calling run");
                    m7711f();
                } else {
                    m7710e();
                }
            } catch (SendIntentException e) {
                this.f6692b.connect();
            }
        }
        return false;
    }

    public boolean m7714b() {
        Log.m7770b(f6691a, "disconnect - begin");
        Log.m7770b(f6691a, "disconnect - default account cleared");
        Plus.AccountApi.clearDefaultAccount(this.f6692b);
        Plus.AccountApi.revokeAccessAndDisconnect(this.f6692b).setResultCallback(new 1(this));
        Log.m7770b(f6691a, "disconnect - access revoked");
        UserManager.m8111a().m8150N();
        this.f6692b.disconnect();
        this.f6692b.connect();
        return true;
    }

    private boolean m7709d() {
        Log.m7772c(f6691a, "getAuthTokenBlocking called");
        if (this.f6692b.isConnected()) {
            try {
                this.f6696f = GoogleAuthUtil.getToken(this.f6694d, Plus.AccountApi.getAccountName(this.f6692b), "oauth2:https://www.googleapis.com/auth/plus.login profile");
                Log.m7772c(f6691a, "Token = " + this.f6696f);
                return true;
            } catch (Throwable e) {
                Log.m7771b(f6691a, "GooglePlayServicesAvailabilityException caught", e);
                GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(), this.f6694d, 11113).show();
                return false;
            } catch (Throwable e2) {
                Log.m7771b(f6691a, "UserRecoverableAuthException caught", e2);
                this.f6699i = false;
                this.f6694d.startActivityForResult(e2.getIntent(), 11114);
                return false;
            } catch (Throwable e22) {
                Log.m7775d(f6691a, "getUserLoginInfo - error getting token", e22);
                return false;
            }
        }
        Log.m7772c(f6691a, "Plus Client is not connected.");
        return false;
    }

    private void m7710e() {
        Log.m7772c(f6691a, "getAuthTokenAsync called");
        new 2(this).execute(new Object[]{(Void) null});
    }

    private void m7711f() {
        if (this.f6695e != null && this.f6692b.isConnected()) {
            this.f6695e.run();
        }
    }
}
