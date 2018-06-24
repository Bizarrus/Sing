package com.smule.singandroid.registration;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import com.facebook.share.internal.ShareConstants;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.HandleUpdateType;
import com.smule.android.logging.Analytics.ProfilePicType;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.android.logging.Log;
import com.smule.android.logging.TrackedActivity;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.EmailOptIn;
import com.smule.android.utils.SoftInputBehaviorListener;
import com.smule.android.utils.SoftInputBehaviorListener.OnSoftInputBehaveListener;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.PhotoTakingActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.task.UserUpdateTask;
import com.smule.singandroid.task.UserUpdateTask.UpdateListener;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.NavigationUtils;
import java.io.IOException;
import java.util.Collections;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EActivity
public class NewHandleActivity extends PhotoTakingActivity implements TrackedActivity, UpdateListener {
    private static final String f23865k = NewHandleActivity.class.getName();
    @InstanceState
    protected HandleUpdateType f23866g = HandleUpdateType.UNCHANGED;
    @InstanceState
    protected ProfilePicType f23867h = ProfilePicType.NONE;
    @InstanceState
    protected boolean f23868i = true;
    @ViewById
    protected CheckBox f23869j;
    private ViewGroup f23870l;
    private EditText f23871m;
    private Button f23872n;
    private View f23873o;
    private View f23874p;
    private BusyDialog f23875q;
    private ImageView f23876r;

    class C48281 implements OnClickListener {
        final /* synthetic */ NewHandleActivity f23851a;

        C48281(NewHandleActivity newHandleActivity) {
            this.f23851a = newHandleActivity;
        }

        public void onClick(View view) {
            this.f23851a.f23876r.callOnClick();
        }
    }

    class C48292 implements AnimatorUpdateListener {
        final /* synthetic */ NewHandleActivity f23852a;
        private int f23853b = -1;
        private int f23854c = -1;

        C48292(NewHandleActivity newHandleActivity) {
            this.f23852a = newHandleActivity;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (this.f23853b == -1 || this.f23854c == -1) {
                this.f23853b = this.f23852a.f23876r.getWidth();
                this.f23854c = this.f23852a.f23876r.getHeight();
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            LayoutParams layoutParams = this.f23852a.f23876r.getLayoutParams();
            layoutParams.width = (int) (((float) this.f23853b) * floatValue);
            layoutParams.height = (int) (floatValue * ((float) this.f23854c));
            this.f23852a.f23876r.setLayoutParams(layoutParams);
        }
    }

    class C48314 implements OnClickListener {
        final /* synthetic */ NewHandleActivity f23859a;

        C48314(NewHandleActivity newHandleActivity) {
            this.f23859a = newHandleActivity;
        }

        public void onClick(View view) {
            String obj = this.f23859a.f23871m.getText().toString();
            this.f23859a.f23875q = new BusyDialog(this.f23859a, this.f23859a.getResources().getString(C1947R.string.core_saving));
            this.f23859a.f23875q.show();
            if (!this.f23859a.f23871m.getText().toString().equals(UserManager.a().i())) {
                this.f23859a.f23866g = HandleUpdateType.CHANGED;
            }
            if (obj.length() < 2) {
                Analytics.m17871a("sign_up", "snp_error", Integer.valueOf(PointerIconCompat.TYPE_CROSSHAIR), Integer.valueOf(21), "username");
                this.f23859a.f23875q.m23574a(2, this.f23859a.getString(C1947R.string.login_handle_invalid_title), this.f23859a.getString(C1947R.string.login_handle_invalid), true, "OK");
                return;
            }
            EmailOptIn emailOptIn = (!this.f23859a.f23868i || this.f23859a.f23869j.isChecked()) ? EmailOptIn.YES : EmailOptIn.NO;
            new UserUpdateTask(this.f23859a.f23871m.getText().toString(), "", "", emailOptIn, this.f23859a).execute(new Void[0]);
        }
    }

    class C48325 implements Runnable {
        final /* synthetic */ NewHandleActivity f23860a;

        C48325(NewHandleActivity newHandleActivity) {
            this.f23860a = newHandleActivity;
        }

        public void run() {
            if (this.f23860a.f23875q != null) {
                this.f23860a.f23875q.dismiss();
            }
            RegistrationFlow registrationFlow = RegistrationFlow.EMAIL;
            if (this.f23860a.getIntent() != null && this.f23860a.getIntent().hasExtra("PARAM_LOGIN_METHOD")) {
                registrationFlow = (RegistrationFlow) this.f23860a.getIntent().getSerializableExtra("PARAM_LOGIN_METHOD");
            }
            RegistrationContext.a(this.f23860a, false, registrationFlow);
        }
    }

    protected void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        setContentView(C1947R.layout.new_handle_activity);
        this.f23870l = (ViewGroup) findViewById(C1947R.id.root);
        this.f23876r = (ImageView) findViewById(C1947R.id.profile_pic);
        this.f23873o = findViewById(C1947R.id.edit_photo_cta);
        this.f23871m = (EditText) findViewById(C1947R.id.handleEditText);
        this.f23874p = findViewById(C1947R.id.bottom_spacer);
        String stringExtra = getIntent().getBooleanExtra("param_handle_prefill", true) ? getIntent().getStringExtra("param_handle") : "";
        if (stringExtra != null) {
            this.f23871m.setText(stringExtra);
        } else {
            stringExtra = UserManager.a().i();
            if (stringExtra != null) {
                this.f23871m.setText(stringExtra);
            }
        }
        if (stringExtra != null && stringExtra.length() > 0) {
            this.f23871m.setSelection(stringExtra.length());
        }
        stringExtra = UserManager.a().n();
        if (stringExtra == null || stringExtra.isEmpty()) {
            z = false;
        }
        m20856a(this.f23876r, this.f23876r, z, 200, 200);
        this.f23873o.setOnClickListener(new C48281(this));
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.5f});
        ofFloat.addUpdateListener(new C48292(this));
        ofFloat.setDuration(250);
        LayoutUtils.m25854a(this.f23870l, new OnGlobalLayoutListener(this, new SoftInputBehaviorListener(this.f23870l, new OnSoftInputBehaveListener(this) {
            final /* synthetic */ NewHandleActivity f23856b;
            private int f23857c = -1;
            private boolean f23858d = false;

            public void mo6810a() {
                if (this.f23857c == -1) {
                    this.f23857c = this.f23856b.m25135a(this.f23856b.f23874p) + this.f23856b.f23874p.getHeight();
                }
                if (this.f23856b.f23870l.getHeight() < this.f23857c && !this.f23858d) {
                    ofFloat.start();
                    this.f23858d = true;
                }
            }

            public void mo6811b() {
                if (this.f23858d) {
                    ofFloat.reverse();
                    this.f23858d = false;
                }
            }
        })));
        this.f23868i = getIntent().getBooleanExtra("SHOW_EMAIL_OPT", this.f23868i);
        if (this.f23868i) {
            this.f23869j.setVisibility(0);
            this.f23869j.setChecked(false);
        } else {
            this.f23869j.setVisibility(4);
        }
        this.f23872n = (Button) findViewById(C1947R.id.saveButton);
        this.f23872n.setOnClickListener(new C48314(this));
        NavigationUtils.m25909a(this.f23871m, this.f23872n);
    }

    protected void m25147e() {
        Analytics.m17906m();
    }

    public void mo6597a(NetworkResponse networkResponse, Boolean bool, int i) {
        if (bool.booleanValue()) {
            Analytics.m17844a(this.f23866g, this.f23867h);
            final Runnable c48325 = new C48325(this);
            if (!RegistrationContext.e() || AppSettingsManager.a().c()) {
                c48325.run();
                return;
            } else {
                SingApplication.d().a("NEW_HANDLE_WAIT_FOR_SETTINGS", Collections.singletonList("InitAppTask.OP_LOAD_SETTINGS"), new Runnable(this) {
                    int f23862a = 0;
                    final /* synthetic */ NewHandleActivity f23864c;

                    class C48331 implements Runnable {
                        final /* synthetic */ C48346 f23861a;

                        C48331(C48346 c48346) {
                            this.f23861a = c48346;
                        }

                        public void run() {
                            this.f23861a.f23864c.mo6597a(null, Boolean.valueOf(false), -1);
                        }
                    }

                    public void run() {
                        if (AppSettingsManager.a().c()) {
                            SingApplication.d().a("NEW_HANDLE_WAIT_FOR_SETTINGS");
                            c48325.run();
                        } else if (this.f23862a >= 2) {
                            SingApplication.d().a("NEW_HANDLE_WAIT_FOR_SETTINGS");
                            new UiHandler(this.f23864c).m19081a(new C48331(this));
                        } else {
                            SingApplication.d().b("InitAppTask.OP_LOAD_SETTINGS");
                            this.f23862a++;
                        }
                    }
                });
                return;
            }
        }
        String string;
        String str = null;
        if (i != -1) {
            switch (i) {
                case C1947R.string.settings_handle_invalid:
                    str = getString(C1947R.string.login_handle_invalid_title);
                    string = getString(C1947R.string.login_handle_invalid);
                    break;
                case C1947R.string.settings_handle_taken:
                    str = getString(C1947R.string.login_handle_invalid_title);
                    string = getString(C1947R.string.settings_handle_taken);
                    break;
                default:
                    string = getResources().getString(i);
                    break;
            }
        }
        string = getResources().getString(C1947R.string.settings_update_fail);
        MagicNetwork.a(networkResponse);
        if (networkResponse != null) {
            Analytics.m17871a("sign_up", "snp_error", Integer.valueOf(networkResponse.b), Integer.valueOf(networkResponse.f), "username");
        }
        if (this.f23875q != null) {
            this.f23875q.m23574a(2, str, string, true, "OK");
        }
    }

    public void mo6598b(NetworkResponse networkResponse, Boolean bool, int i) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            Log.e(f23865k, "Bad result code, " + i2 + ", returned for request code: " + i);
            return;
        }
        switch (i) {
            case 2201:
                this.f23867h = ProfilePicType.PHOTO;
                return;
            case 2202:
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    Bitmap bitmap;
                    if (extras.getParcelable(ShareConstants.WEB_DIALOG_PARAM_DATA) != null) {
                        bitmap = (Bitmap) extras.getParcelable(ShareConstants.WEB_DIALOG_PARAM_DATA);
                    } else {
                        if (intent.getData() != null) {
                            try {
                                bitmap = Media.getBitmap(getContentResolver(), intent.getData());
                            } catch (IOException e) {
                                Log.e(f23865k, "IOException when attempting to load uri from CROP_PHOTO_INTENT_CODE " + e.getMessage());
                            }
                        }
                        bitmap = null;
                    }
                    if (bitmap != null) {
                        m20853a(bitmap, this.f23876r);
                        mo6750a(bitmap, null);
                        return;
                    }
                    Log.e(f23865k, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
                    return;
                }
                return;
            case 2203:
                this.f23867h = ProfilePicType.EXISTING;
                return;
            default:
                return;
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.f23875q != null) {
            this.f23875q.dismiss();
            this.f23875q = null;
        }
        SingApplication.d().a("NEW_HANDLE_WAIT_FOR_SETTINGS");
    }

    public void onBackPressed() {
    }

    public boolean mo6612a() {
        return true;
    }

    public String mo6751b() {
        return f23865k;
    }

    private int m25135a(View view) {
        if (!(view.getParent() instanceof ViewGroup)) {
            return view.getTop();
        }
        return m25135a((ViewGroup) view.getParent()) + view.getTop();
    }
}
