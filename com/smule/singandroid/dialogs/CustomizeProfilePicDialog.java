/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.support.annotation.NonNull
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.smule.android.network.managers.UserManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import java.util.Set;

public class CustomizeProfilePicDialog
extends SmuleDialog {
    Runnable a;
    private ViewGroup b;
    private Button c;
    private Button d;
    private TextView e;
    private View f;
    private TextView g;
    private TextView h;

    /*
     * Enabled aggressive block sorting
     */
    public CustomizeProfilePicDialog(final BaseActivity baseActivity, boolean bl, final Runnable runnable, final Runnable runnable2, final Runnable runnable3) {
        super((Context)baseActivity, 16973830);
        this.b = (ViewGroup)LayoutInflater.from((Context)baseActivity).inflate(2130903196, null, false);
        this.c = (Button)this.b.findViewById(2131755655);
        this.d = (Button)this.b.findViewById(2131755656);
        this.e = (TextView)this.b.findViewById(2131755376);
        this.f = this.b.findViewById(2131755370);
        this.g = (TextView)this.b.findViewById(2131755654);
        this.h = (TextView)this.b.findViewById(2131755653);
        this.a = runnable3;
        if (bl) {
            this.h.setText(2131296738);
        } else {
            this.h.setText(2131296739);
        }
        new Handler().post(new Runnable(){

            @Override
            public void run() {
                CustomizeProfilePicDialog.this.g.setText((CharSequence)UserManager.a().i());
            }
        });
        this.c.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                baseActivity.a(SingPermissionRequests.b, new RunTimePermissionsRequester.ResultCallback(){

                    @Override
                    public void a(boolean bl, @NonNull Set<String> set) {
                        if (bl) {
                            CustomizeProfilePicDialog.this.f.setVisibility(0);
                            runnable.run();
                        }
                    }
                });
            }

        });
        this.d.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                baseActivity.a(SingPermissionRequests.b, new RunTimePermissionsRequester.ResultCallback(){

                    @Override
                    public void a(boolean bl, @NonNull Set<String> set) {
                        if (bl) {
                            CustomizeProfilePicDialog.this.f.setVisibility(0);
                            runnable2.run();
                        }
                    }
                });
            }

        });
        this.e.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                runnable3.run();
            }
        });
        this.setContentView((View)this.b);
    }

    public void a() {
        new Handler().post(new Runnable(){

            @Override
            public void run() {
                CustomizeProfilePicDialog.this.f.setVisibility(8);
            }
        });
    }

    public void onBackPressed() {
        this.a.run();
    }

}

