/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 *  android.text.TextUtils
 */
package com.smule.android.task;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.smule.android.R;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.EmailOptIn;

public class UserUpdateTask
extends AsyncTask<Void, Void, com.smule.android.network.core.NetworkResponse> {
    private static final String a = UserUpdateTask.class.getName();
    private String b;
    private String c;
    private String d;
    private UpdateListener e;

    private int b(com.smule.android.network.core.NetworkResponse networkResponse) {
        if (networkResponse.b != 1008) {
            return -1;
        }
        switch (networkResponse.f) {
            default: {
                return R.string.cm_password_invalid;
            }
            case 30: 
        }
        return R.string.cm_password_short;
    }

    private int c(com.smule.android.network.core.NetworkResponse networkResponse) {
        if (networkResponse.b != 1006) {
            return -1;
        }
        switch (networkResponse.f) {
            default: {
                return R.string.cm_email_invalid;
            }
            case 10: {
                return R.string.cm_email_invalid;
            }
            case 11: {
                return R.string.cm_email_short;
            }
            case 12: {
                return R.string.cm_email_long;
            }
            case 13: 
        }
        return R.string.cm_email_taken;
    }

    private int d(com.smule.android.network.core.NetworkResponse networkResponse) {
        if (networkResponse.b != 1007) {
            return -1;
        }
        switch (networkResponse.f) {
            default: {
                return R.string.cm_handle_invalid;
            }
            case 20: {
                return R.string.cm_handle_invalid;
            }
            case 22: {
                return R.string.cm_handle_long;
            }
            case 21: {
                return R.string.cm_handle_short;
            }
            case 23: 
        }
        return R.string.cm_handle_taken;
    }

    protected /* varargs */ com.smule.android.network.core.NetworkResponse a(Void ... arrvoid) {
        return UserManager.a().a(this.c, this.b, this.d, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(com.smule.android.network.core.NetworkResponse networkResponse) {
        boolean bl;
        int n;
        block10 : {
            block9 : {
                bl = false;
                if (networkResponse == null || networkResponse.a != NetworkResponse.a) break block9;
                switch (networkResponse.b) {
                    default: {
                        n = -1;
                        break block10;
                    }
                    case 1006: {
                        n = this.c(networkResponse);
                        break block10;
                    }
                    case 56: {
                        n = R.string.cm_email_invalid;
                        break block10;
                    }
                    case 1007: {
                        n = this.d(networkResponse);
                        break block10;
                    }
                    case 1008: {
                        n = this.b(networkResponse);
                        break block10;
                    }
                    case 10: {
                        n = TextUtils.isEmpty((CharSequence)this.d) && TextUtils.isEmpty((CharSequence)this.b) ? R.string.cm_handle_invalid : (TextUtils.isEmpty((CharSequence)this.d) && TextUtils.isEmpty((CharSequence)this.c) ? R.string.cm_email_invalid : (TextUtils.isEmpty((CharSequence)this.b) && TextUtils.isEmpty((CharSequence)this.c) ? R.string.cm_password_invalid : R.string.cm_facebook_generic_profile_error));
                        break block10;
                    }
                    case 0: {
                        bl = true;
                        n = 0;
                        break block10;
                    }
                    case 50: 
                }
                n = TextUtils.isEmpty((CharSequence)this.d) && TextUtils.isEmpty((CharSequence)this.b) ? R.string.cm_handle_invalid : (TextUtils.isEmpty((CharSequence)this.d) && TextUtils.isEmpty((CharSequence)this.c) ? R.string.cm_email_invalid : (TextUtils.isEmpty((CharSequence)this.b) && TextUtils.isEmpty((CharSequence)this.c) ? R.string.cm_password_invalid : R.string.cm_bad_format));
                break block10;
            }
            MagicNetwork.d().showConnectionError();
            n = R.string.cm_login_cannot_connect_to_smule;
        }
        if (this.e == null) return;
        this.e.a(networkResponse, bl, n);
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a((Void[])arrobject);
    }

    protected /* synthetic */ void onPostExecute(Object object) {
        this.a((com.smule.android.network.core.NetworkResponse)((Object)object));
    }

    protected void onPreExecute() {
    }

    public static interface UpdateListener {
        public void a(com.smule.android.network.core.NetworkResponse var1, Boolean var2, int var3);
    }

}

