/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.support.annotation.StringRes
 *  android.text.TextUtils
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import com.smule.android.logging.Log;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import java.lang.ref.WeakReference;

public class AudioErrorHandler {
    private static final String a = AudioErrorHandler.class.getName();
    private WeakReference<Activity> b;
    private Runnable c;
    private TextAlertDialog d;

    public AudioErrorHandler(Activity activity, Runnable runnable) {
        this.b = new WeakReference<Activity>(activity);
        this.c = runnable;
    }

    private void a(final String string2, final @StringRes int n, final @StringRes int n2) {
        this.b.get().runOnUiThread(new Runnable(){

            @Override
            public void run() {
                if (AudioErrorHandler.this.b.get() == null || ((Activity)AudioErrorHandler.this.b.get()).isFinishing()) {
                    return;
                }
                if (AudioErrorHandler.this.d != null) {
                    Log.d(a, "recording error dialog showing");
                    Log.e(a, string2);
                    return;
                }
                AudioErrorHandler.this.d = new TextAlertDialog((Context)AudioErrorHandler.this.b.get(), n, n2, true, false);
                AudioErrorHandler.this.d.a(((Activity)AudioErrorHandler.this.b.get()).getString(2131296705), "");
                AudioErrorHandler.this.d.a(new CustomAlertDialog.CustomAlertDialogListener(){

                    @Override
                    public void a(CustomAlertDialog customAlertDialog) {
                        if (AudioErrorHandler.this.d != null) {
                            AudioErrorHandler.this.d.dismiss();
                            AudioErrorHandler.this.d = null;
                        }
                        AudioErrorHandler.this.c.run();
                    }

                    @Override
                    public void b(CustomAlertDialog customAlertDialog) {
                        this.a(customAlertDialog);
                    }
                });
                Log.e(a, string2);
                AudioErrorHandler.this.d.show();
            }

        });
    }

    private void c() {
        this.a("Failed to obtain audio focus", 2131297445, 2131297444);
    }

    public void a(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            this.c();
            return;
        }
        this.a(string2, 2131297447, 2131297446);
    }

    public boolean a() {
        if (this.d != null) {
            return true;
        }
        return false;
    }

}

