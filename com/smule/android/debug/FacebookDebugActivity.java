/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.os.Bundle
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.TextView
 *  com.facebook.AccessToken
 *  com.facebook.FacebookSdk
 */
package com.smule.android.debug;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.smule.android.R;
import com.smule.android.network.managers.UserManager;
import java.util.Date;

public class FacebookDebugActivity
extends Activity {
    public static final String a = FacebookDebugActivity.class.getName();
    protected TextView b;
    protected TextView c;
    protected TextView d;
    protected TextView e;
    protected TextView f;
    protected TextView g;
    protected TextView h;
    protected TextView i;
    protected Button j;
    protected Button k;

    /*
     * Enabled aggressive block sorting
     */
    protected void a() {
        Object object = AccessToken.getCurrentAccessToken();
        Object object2 = this.j;
        boolean bl = object != null;
        object2.setEnabled(bl);
        float f = object == null ? 0.33f : 1.0f;
        this.j.setAlpha(f);
        if (object == null) {
            this.h.setText((CharSequence)"FB Token:  NULL");
            this.i.setText((CharSequence)"Expires:  --");
            return;
        }
        Date date = object.getExpires();
        object = date.before(new Date()) ? "EXPIRED" : "CURRENT";
        object2 = date.before(new Date()) ? "Expired" : "Expires";
        this.h.setText((CharSequence)("FB Token:  " + (String)object));
        this.i.setText((CharSequence)((String)object2 + ":  " + date.toString()));
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b() {
        boolean bl = UserManager.a().y();
        this.k.setEnabled(bl);
        float f = !bl ? 0.33f : 1.0f;
        this.k.setAlpha(f);
        String string2 = bl ? "YES" : "NO";
        this.e.setText((CharSequence)("Is Logged In?  " + string2));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.facebook_debug_activity);
        this.b = (TextView)this.findViewById(R.id.fb_sdk);
        this.c = (TextView)this.findViewById(R.id.user_handle);
        this.d = (TextView)this.findViewById(R.id.user_email);
        this.e = (TextView)this.findViewById(R.id.logged_in);
        this.f = (TextView)this.findViewById(R.id.login_type);
        this.g = (TextView)this.findViewById(R.id.fb_user);
        this.h = (TextView)this.findViewById(R.id.fb_token);
        this.i = (TextView)this.findViewById(R.id.fb_expires);
        this.j = (Button)this.findViewById(R.id.force_null_button);
        this.j.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                AccessToken.setCurrentAccessToken((AccessToken)null);
                FacebookDebugActivity.this.a();
            }
        });
        this.k = (Button)this.findViewById(R.id.force_logout_button);
        this.k.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                UserManager.a().A();
                FacebookDebugActivity.this.b();
            }
        });
    }

    protected void onRestart() {
        super.onRestart();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onResume() {
        String string2;
        super.onResume();
        this.b.setText((CharSequence)("FB SDK:  v" + FacebookSdk.getSdkVersion()));
        this.c.setText((CharSequence)("Smule User:  " + UserManager.a().i()));
        this.d.setText((CharSequence)("(" + UserManager.a().j() + ")"));
        String string3 = Boolean.valueOf(UserManager.a().E()) != false ? "YES" : "NO";
        this.f.setText((CharSequence)("Is FB Login Type?  " + string3));
        string3 = string2 = UserManager.a().n();
        if (string2 == null) {
            string3 = "--";
        }
        this.g.setText((CharSequence)("Linked FB Account:   " + string3));
        this.a();
        this.b();
    }

}

