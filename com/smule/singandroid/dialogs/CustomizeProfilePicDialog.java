package com.smule.singandroid.dialogs;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.smule.android.network.managers.UserManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import java.util.Set;

public class CustomizeProfilePicDialog extends SmuleDialog {
    Runnable f22258a;
    private ViewGroup f22259b;
    private Button f22260c = ((Button) this.f22259b.findViewById(C1947R.id.choose_from_library_button));
    private Button f22261d = ((Button) this.f22259b.findViewById(C1947R.id.take_photo_button));
    private TextView f22262e = ((TextView) this.f22259b.findViewById(C1947R.id.cancel_button));
    private View f22263f = this.f22259b.findViewById(C1947R.id.loading_view);
    private TextView f22264g = ((TextView) this.f22259b.findViewById(C1947R.id.username_text));
    private TextView f22265h = ((TextView) this.f22259b.findViewById(C1947R.id.customize_profile_pic_subtitle));

    class C44901 implements Runnable {
        final /* synthetic */ CustomizeProfilePicDialog f22246a;

        C44901(CustomizeProfilePicDialog customizeProfilePicDialog) {
            this.f22246a = customizeProfilePicDialog;
        }

        public void run() {
            this.f22246a.f22264g.setText(UserManager.a().i());
        }
    }

    class C44965 implements Runnable {
        final /* synthetic */ CustomizeProfilePicDialog f22257a;

        C44965(CustomizeProfilePicDialog customizeProfilePicDialog) {
            this.f22257a = customizeProfilePicDialog;
        }

        public void run() {
            this.f22257a.f22263f.setVisibility(8);
        }
    }

    public CustomizeProfilePicDialog(final BaseActivity baseActivity, boolean z, final Runnable runnable, final Runnable runnable2, final Runnable runnable3) {
        super(baseActivity, 16973830);
        this.f22259b = (ViewGroup) LayoutInflater.from(baseActivity).inflate(C1947R.layout.customize_profile_pic_activity, null, false);
        this.f22258a = runnable3;
        if (z) {
            this.f22265h.setText(C1947R.string.customize_profile_pic_subtitle);
        } else {
            this.f22265h.setText(C1947R.string.customize_profile_pic_subtitle_two);
        }
        new Handler().post(new C44901(this));
        this.f22260c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CustomizeProfilePicDialog f22250c;

            class C44911 implements ResultCallback {
                final /* synthetic */ C44922 f22247a;

                C44911(C44922 c44922) {
                    this.f22247a = c44922;
                }

                public void mo6372a(boolean z, @NonNull Set<String> set) {
                    if (z) {
                        this.f22247a.f22250c.f22263f.setVisibility(0);
                        runnable.run();
                    }
                }
            }

            public void onClick(View view) {
                baseActivity.a(SingPermissionRequests.f23948b, new C44911(this));
            }
        });
        this.f22261d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CustomizeProfilePicDialog f22254c;

            class C44931 implements ResultCallback {
                final /* synthetic */ C44943 f22251a;

                C44931(C44943 c44943) {
                    this.f22251a = c44943;
                }

                public void mo6372a(boolean z, @NonNull Set<String> set) {
                    if (z) {
                        this.f22251a.f22254c.f22263f.setVisibility(0);
                        runnable2.run();
                    }
                }
            }

            public void onClick(View view) {
                baseActivity.a(SingPermissionRequests.f23948b, new C44931(this));
            }
        });
        this.f22262e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CustomizeProfilePicDialog f22256b;

            public void onClick(View view) {
                runnable3.run();
            }
        });
        setContentView(this.f22259b);
    }

    public void mo6374a() {
        new Handler().post(new C44965(this));
    }

    public void onBackPressed() {
        this.f22258a.run();
    }
}
