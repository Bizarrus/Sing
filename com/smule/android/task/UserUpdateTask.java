package com.smule.android.task;

import android.os.AsyncTask;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import com.smule.android.C3482R;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.UserManager;

public class UserUpdateTask extends AsyncTask<Void, Void, NetworkResponse> {
    private static final String f17659a = UserUpdateTask.class.getName();
    private String f17660b;
    private String f17661c;
    private String f17662d;
    private UpdateListener f17663e;

    public interface UpdateListener {
        void m18828a(NetworkResponse networkResponse, Boolean bool, int i);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m18832a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m18833a((NetworkResponse) obj);
    }

    protected void onPreExecute() {
    }

    protected NetworkResponse m18832a(Void... voidArr) {
        return UserManager.a().a(this.f17661c, this.f17660b, this.f17662d, null);
    }

    protected void m18833a(NetworkResponse networkResponse) {
        int i;
        boolean z = false;
        if (networkResponse != null && networkResponse.a == NetworkResponse$Status.OK) {
            switch (networkResponse.b) {
                case 0:
                    z = true;
                    boolean z2 = false;
                    break;
                case 10:
                    if (!TextUtils.isEmpty(this.f17662d) || !TextUtils.isEmpty(this.f17660b)) {
                        if (!TextUtils.isEmpty(this.f17662d) || !TextUtils.isEmpty(this.f17661c)) {
                            if (!TextUtils.isEmpty(this.f17660b) || !TextUtils.isEmpty(this.f17661c)) {
                                i = C3482R.string.cm_facebook_generic_profile_error;
                                break;
                            } else {
                                i = C3482R.string.cm_password_invalid;
                                break;
                            }
                        }
                        i = C3482R.string.cm_email_invalid;
                        break;
                    }
                    i = C3482R.string.cm_handle_invalid;
                    break;
                    break;
                case 50:
                    if (!TextUtils.isEmpty(this.f17662d) || !TextUtils.isEmpty(this.f17660b)) {
                        if (!TextUtils.isEmpty(this.f17662d) || !TextUtils.isEmpty(this.f17661c)) {
                            if (!TextUtils.isEmpty(this.f17660b) || !TextUtils.isEmpty(this.f17661c)) {
                                i = C3482R.string.cm_bad_format;
                                break;
                            } else {
                                i = C3482R.string.cm_password_invalid;
                                break;
                            }
                        }
                        i = C3482R.string.cm_email_invalid;
                        break;
                    }
                    i = C3482R.string.cm_handle_invalid;
                    break;
                    break;
                case 56:
                    i = C3482R.string.cm_email_invalid;
                    break;
                case PointerIconCompat.TYPE_CELL /*1006*/:
                    i = m18830c(networkResponse);
                    break;
                case PointerIconCompat.TYPE_CROSSHAIR /*1007*/:
                    i = m18831d(networkResponse);
                    break;
                case PointerIconCompat.TYPE_TEXT /*1008*/:
                    i = m18829b(networkResponse);
                    break;
                default:
                    i = -1;
                    break;
            }
        }
        MagicNetwork.d().showConnectionError();
        i = C3482R.string.cm_login_cannot_connect_to_smule;
        if (this.f17663e != null) {
            this.f17663e.m18828a(networkResponse, Boolean.valueOf(z), i);
        }
    }

    private int m18829b(NetworkResponse networkResponse) {
        if (networkResponse.b != PointerIconCompat.TYPE_TEXT) {
            return -1;
        }
        switch (networkResponse.f) {
            case 30:
                return C3482R.string.cm_password_short;
            default:
                return C3482R.string.cm_password_invalid;
        }
    }

    private int m18830c(NetworkResponse networkResponse) {
        if (networkResponse.b != PointerIconCompat.TYPE_CELL) {
            return -1;
        }
        switch (networkResponse.f) {
            case 10:
                return C3482R.string.cm_email_invalid;
            case 11:
                return C3482R.string.cm_email_short;
            case 12:
                return C3482R.string.cm_email_long;
            case 13:
                return C3482R.string.cm_email_taken;
            default:
                return C3482R.string.cm_email_invalid;
        }
    }

    private int m18831d(NetworkResponse networkResponse) {
        if (networkResponse.b != PointerIconCompat.TYPE_CROSSHAIR) {
            return -1;
        }
        switch (networkResponse.f) {
            case 20:
                return C3482R.string.cm_handle_invalid;
            case 21:
                return C3482R.string.cm_handle_short;
            case 22:
                return C3482R.string.cm_handle_long;
            case 23:
                return C3482R.string.cm_handle_taken;
            default:
                return C3482R.string.cm_handle_invalid;
        }
    }
}
