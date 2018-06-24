package com.smule.singandroid.task;

import android.os.AsyncTask;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.EmailOptIn;
import com.smule.singandroid.C1947R;

public class UserUpdateTask extends AsyncTask<Void, Void, NetworkResponse> {
    private static final String f24485a = UserUpdateTask.class.getName();
    private String f24486b;
    private String f24487c;
    private String f24488d;
    private EmailOptIn f24489e;
    private String f24490f;
    private String f24491g;
    private UpdateListener f24492h;
    private boolean f24493i = true;

    public interface UpdateListener {
        void mo6597a(NetworkResponse networkResponse, Boolean bool, int i);

        void mo6598b(NetworkResponse networkResponse, Boolean bool, int i);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25695a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25696a((NetworkResponse) obj);
    }

    public UserUpdateTask(String str, String str2, String str3, EmailOptIn emailOptIn, UpdateListener updateListener) {
        this.f24487c = str;
        this.f24486b = str2;
        this.f24488d = str3;
        this.f24489e = emailOptIn;
        this.f24492h = updateListener;
    }

    public UserUpdateTask(String str, String str2, UpdateListener updateListener) {
        this.f24490f = str;
        this.f24491g = str2;
        this.f24492h = updateListener;
    }

    protected void onPreExecute() {
    }

    protected NetworkResponse m25695a(Void... voidArr) {
        if (this.f24493i) {
            return UserManager.a().c(this.f24490f, this.f24491g);
        }
        return UserManager.a().a(this.f24487c, this.f24486b, this.f24488d, this.f24489e);
    }

    protected void m25696a(NetworkResponse networkResponse) {
        boolean z = false;
        int i = C1947R.string.settings_email_invalid;
        if (networkResponse != null && networkResponse.a == NetworkResponse$Status.OK) {
            switch (networkResponse.b) {
                case 0:
                    z = true;
                    boolean z2 = false;
                    break;
                case 10:
                    if (!TextUtils.isEmpty(this.f24488d) || !TextUtils.isEmpty(this.f24486b)) {
                        if (!(TextUtils.isEmpty(this.f24488d) && TextUtils.isEmpty(this.f24487c))) {
                            if (!TextUtils.isEmpty(this.f24486b) || !TextUtils.isEmpty(this.f24487c)) {
                                i = C1947R.string.facebook_generic_profile_error;
                                break;
                            } else {
                                i = C1947R.string.settings_password_length_invalid;
                                break;
                            }
                        }
                    }
                    i = C1947R.string.settings_handle_invalid;
                    break;
                    break;
                case 50:
                    if (!TextUtils.isEmpty(this.f24488d) || !TextUtils.isEmpty(this.f24486b)) {
                        if (!(TextUtils.isEmpty(this.f24488d) && TextUtils.isEmpty(this.f24487c))) {
                            if (!TextUtils.isEmpty(this.f24486b) || !TextUtils.isEmpty(this.f24487c)) {
                                i = C1947R.string.settings_bad_format;
                                break;
                            } else {
                                i = C1947R.string.settings_password_length_invalid;
                                break;
                            }
                        }
                    }
                    i = C1947R.string.settings_handle_invalid;
                    break;
                    break;
                case 56:
                    break;
                case PointerIconCompat.TYPE_CELL /*1006*/:
                    i = m25692b(networkResponse);
                    break;
                case PointerIconCompat.TYPE_CROSSHAIR /*1007*/:
                    i = m25694d(networkResponse);
                    break;
                case PointerIconCompat.TYPE_TEXT /*1008*/:
                    i = m25693c(networkResponse);
                    break;
                default:
                    i = -1;
                    break;
            }
        }
        MagicNetwork.d().showConnectionError();
        i = C1947R.string.login_cannot_connect_to_smule;
        if (this.f24492h == null) {
            return;
        }
        if (this.f24493i) {
            this.f24492h.mo6598b(networkResponse, Boolean.valueOf(z), i);
        } else {
            this.f24492h.mo6597a(networkResponse, Boolean.valueOf(z), i);
        }
    }

    private int m25693c(NetworkResponse networkResponse) {
        if (networkResponse.b != PointerIconCompat.TYPE_TEXT) {
            return -1;
        }
        return C1947R.string.settings_password_length_invalid;
    }

    public static int m25692b(NetworkResponse networkResponse) {
        if (networkResponse.b != PointerIconCompat.TYPE_CELL) {
            return -1;
        }
        switch (networkResponse.f) {
            case 10:
                return C1947R.string.settings_email_invalid;
            case 11:
                return C1947R.string.settings_email_short;
            case 12:
                return C1947R.string.settings_email_long;
            case 13:
                return C1947R.string.login_email_taken;
            default:
                return C1947R.string.settings_email_invalid;
        }
    }

    private int m25694d(NetworkResponse networkResponse) {
        if (networkResponse.b != PointerIconCompat.TYPE_CROSSHAIR) {
            return -1;
        }
        switch (networkResponse.f) {
            case 20:
                return C1947R.string.settings_handle_invalid;
            case 21:
                return C1947R.string.settings_handle_short;
            case 22:
                return C1947R.string.settings_handle_long;
            case 23:
                return C1947R.string.settings_handle_taken;
            default:
                return C1947R.string.settings_handle_invalid;
        }
    }
}
