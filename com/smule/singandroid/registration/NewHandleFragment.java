/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.app.Activity
 *  android.content.ContentResolver
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Color
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.provider.MediaStore
 *  android.provider.MediaStore$Images
 *  android.provider.MediaStore$Images$Media
 *  android.text.Editable
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewParent
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.CheckBox
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 *  com.smule.singandroid.task.UserUpdateTask
 *  com.smule.singandroid.task.UserUpdateTask$ErrorType
 *  com.smule.singandroid.task.UserUpdateTask$UpdateListener
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$RegistrationNewHandleScreenType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.registration;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.utils.EmailOptIn;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.SoftInputBehaviorListener;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.PhotoTakingFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.task.UserUpdateTask;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class NewHandleFragment
extends PhotoTakingFragment
implements UserUpdateTask.UpdateListener {
    private static final String g = NewHandleFragment.class.getName();
    @FragmentArg
    protected String A;
    @FragmentArg
    protected boolean B;
    @FragmentArg
    protected Boolean C;
    @InstanceState
    protected Analytics D = Analytics.a;
    @InstanceState
    protected Analytics E = Analytics.a;
    @InstanceState
    protected boolean F = true;
    private BusyDialog h;
    protected final SingServerValues s = new SingServerValues();
    @ViewById
    protected ViewGroup t;
    @ViewById
    protected EditText u;
    @ViewById
    protected TextView v;
    @ViewById
    protected TextView w;
    @ViewById
    protected View x;
    @ViewById
    protected CheckBox y;
    @ViewById
    protected ImageView z;

    private int b(View view) {
        if (view.getParent() instanceof ViewGroup) {
            int n = view.getTop();
            return this.b((View)((ViewGroup)view.getParent())) + n;
        }
        return view.getTop();
    }

    private void t() {
        RegistrationContext.b(this.E.a());
    }

    public void F() {
    }

    public void G() {
    }

    @Click
    protected void L() {
        this.z.callOnClick();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void M() {
        String string2 = this.u.getText().toString();
        Object object = this.getResources().getString(2131296716);
        this.h = new BusyDialog(this.getActivity(), (String)object);
        this.h.show();
        if (!string2.equals(UserManager.a().i())) {
            this.D = Analytics.b;
        }
        if (string2.length() < 2) {
            com.smule.android.logging.Analytics.a("sign_up", "snp_error", (Integer)1007, (Integer)21, "username");
            this.h.a(2, this.getString(2131296918), this.getString(2131296917), null, "OK");
            return;
        }
        object = !this.F || this.y.isChecked() ? EmailOptIn.c : EmailOptIn.b;
        new UserUpdateTask(string2, "", "", (EmailOptIn)((Object)object), (UserUpdateTask.UpdateListener)this).execute((Object[])new Void[0]);
    }

    protected SingAnalytics.RegistrationNewHandleScreenType N() {
        return SingAnalytics.RegistrationNewHandleScreenType.a((String)this.s.an());
    }

    protected void O() {
        if (this.E == Analytics.a) {
            this.E = Analytics.d;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @AfterViews
    protected void a() {
        Object object = this.B ? this.A : "";
        if (object != null) {
            this.u.setText((CharSequence)object);
        } else {
            String string2 = UserManager.a().i();
            object = string2;
            if (string2 != null) {
                this.u.setText((CharSequence)string2);
                object = string2;
            }
        }
        if (object != null && object.length() > 0) {
            this.u.setSelection(object.length());
        }
        boolean bl = (object = UserManager.a().n()) != null && !object.isEmpty();
        if (!ImageUtils.a(UserManager.a().h())) {
            this.z.post(new Runnable(){

                @Override
                public void run() {
                    if (NewHandleFragment.this.z != null) {
                        ImageUtils.a(UserManager.a().h(), NewHandleFragment.this.z, 2130837899, true, -1, NewHandleFragment.this.getResources().getDimensionPixelSize(2131428171), (ImageLoadingListener)new SimpleImageLoadingListener(){

                            public void a(String string2, View view, Bitmap bitmap) {
                                if (NewHandleFragment.this.z != null) {
                                    ImageUtils.a(NewHandleFragment.this.z, ((BitmapDrawable)NewHandleFragment.this.z.getDrawable()).getBitmap(), Color.parseColor((String)"#222222"), false);
                                }
                            }
                        }, true);
                    }
                }

            });
        }
        this.a((View)this.z, this.z, bl, false, 200, 200, 2131297136, SingPermissionRequests.b);
        object = ValueAnimator.ofFloat((float[])new float[]{1.0f, 0.5f});
        object.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            private int b;
            private int c;
            private int d;
            {
                this.b = -1;
                this.c = -1;
                this.d = -1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (NewHandleFragment.this.z != null) {
                    if (this.b == -1 || this.c == -1) {
                        this.b = NewHandleFragment.this.z.getWidth();
                        this.c = NewHandleFragment.this.z.getHeight();
                    }
                    float f = ((Float)valueAnimator.getAnimatedValue()).floatValue();
                    valueAnimator = NewHandleFragment.this.z.getLayoutParams();
                    valueAnimator.width = (int)((float)this.b * f);
                    valueAnimator.height = (int)((float)this.c * f);
                    NewHandleFragment.this.z.setLayoutParams((ViewGroup.LayoutParams)valueAnimator);
                    valueAnimator = (ViewGroup.MarginLayoutParams)NewHandleFragment.this.w.getLayoutParams();
                    if (this.d == -1) {
                        this.d = valueAnimator.topMargin;
                    }
                    valueAnimator.setMargins(0, (int)(f * (float)this.d), 0, valueAnimator.bottomMargin);
                    NewHandleFragment.this.w.setLayoutParams((ViewGroup.LayoutParams)valueAnimator);
                }
            }
        });
        object.setDuration(250);
        object = new SoftInputBehaviorListener((View)this.t, new SoftInputBehaviorListener.OnSoftInputBehaveListener((ValueAnimator)object){
            final /* synthetic */ ValueAnimator a;
            private int c;
            private boolean d;
            {
                this.a = valueAnimator;
                this.c = -1;
                this.d = false;
            }

            @Override
            public void a() {
                if (this.c == -1) {
                    this.c = NewHandleFragment.this.b(NewHandleFragment.this.x) + NewHandleFragment.this.x.getHeight();
                }
                if (NewHandleFragment.this.t.getHeight() < this.c && !this.d) {
                    this.a.start();
                    this.d = true;
                }
            }

            @Override
            public void b() {
                if (this.d) {
                    this.a.reverse();
                    this.d = false;
                }
            }
        });
        if (this.N() != SingAnalytics.RegistrationNewHandleScreenType.c) {
            object = new WeakListener.OnGlobalLayoutListener((Object)this, (ViewTreeObserver.OnGlobalLayoutListener)object);
            LayoutUtils.a((View)this.t, (WeakListener.OnGlobalLayoutListener)object);
        }
        bl = this.C != null ? this.C : this.F;
        this.F = bl;
        if (this.F) {
            this.y.setVisibility(0);
            this.y.setChecked(false);
        } else {
            this.y.setVisibility(4);
        }
        try {
            ((AttachListener)this.getActivity()).a(this.u);
        }
        catch (ClassCastException classCastException) {
            throw new ClassCastException(this.getActivity().toString() + " must implement AttachListener");
        }
        if (this.N() == SingAnalytics.RegistrationNewHandleScreenType.b) {
            this.v.setVisibility(8);
            this.w.setVisibility(0);
            return;
        } else {
            if (this.N() != SingAnalytics.RegistrationNewHandleScreenType.d) return;
            {
                this.v.setText((CharSequence)this.getString(2131296927));
                MiscUtils.a((Activity)this.getActivity(), (EditText)this.u);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void a(NetworkResponse object, Boolean object2, int n, UserUpdateTask.ErrorType object3) {
        Integer n2 = null;
        if (object2.booleanValue()) {
            if (this.N() != SingAnalytics.RegistrationNewHandleScreenType.c) {
                SingAnalytics.a(this.D, this.E);
            }
            object = new Runnable(){

                @Override
                public void run() {
                    if (NewHandleFragment.this.h != null) {
                        NewHandleFragment.this.h.dismiss();
                    }
                    if (NewHandleFragment.this.N() == SingAnalytics.RegistrationNewHandleScreenType.c) {
                        NewHandleFragment.this.F();
                        return;
                    }
                    ((UserUpdateListener)NewHandleFragment.this.getActivity()).B_();
                }
            };
            if (RegistrationContext.e() && !AppSettingsManager.a().c()) {
                SingApplication.d().a("NEW_HANDLE_WAIT_FOR_SETTINGS", Collections.singletonList("InitAppTask.OP_LOAD_SETTINGS"), new Runnable((UserUpdateTask.ErrorType)object3, (Runnable)object){
                    int a;
                    final /* synthetic */ UserUpdateTask.ErrorType b;
                    final /* synthetic */ Runnable c;
                    {
                        this.b = errorType;
                        this.c = runnable;
                        this.a = 0;
                    }

                    @Override
                    public void run() {
                        if (!AppSettingsManager.a().c()) {
                            if (this.a >= 2) {
                                SingApplication.d().a("NEW_HANDLE_WAIT_FOR_SETTINGS");
                                new UiHandler(NewHandleFragment.this.getActivity()).a(new Runnable(){

                                    @Override
                                    public void run() {
                                        NewHandleFragment.this.a(null, false, -1, 5.this.b);
                                    }
                                });
                                return;
                            }
                            SingApplication.d().b("InitAppTask.OP_LOAD_SETTINGS");
                            ++this.a;
                            return;
                        }
                        SingApplication.d().a("NEW_HANDLE_WAIT_FOR_SETTINGS");
                        this.c.run();
                    }

                });
                return;
            }
            object.run();
            return;
        }
        if (n == -1) {
            object3 = this.getResources().getString(2131297372);
            MagicNetwork.a((NetworkResponse)((Object)object));
            object2 = null;
        } else {
            switch (n) {
                default: {
                    object3 = this.getResources().getString(n);
                    object2 = null;
                    break;
                }
                case 2131297332: {
                    object2 = this.getString(2131296918);
                    object3 = this.getString(2131296917);
                    break;
                }
                case 2131297335: {
                    object2 = this.getString(2131296918);
                    object3 = this.getString(2131297335);
                }
            }
        }
        if (object != null) {
            com.smule.android.logging.Analytics.a("sign_up", "snp_error", (Integer)object.b, (Integer)object.f, "username");
        }
        if (this.h == null) return;
        if (object != null) {
            n2 = object.h();
        }
        this.h.a(2, (String)object2, (String)object3, n2, "OK");
    }

    @Override
    protected void ah() {
        com.smule.android.logging.Analytics.o();
    }

    @Override
    public void ai() {
    }

    public void b(NetworkResponse networkResponse, Boolean bl, int n, UserUpdateTask.ErrorType errorType) {
    }

    @Override
    public boolean d() {
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void onActivityResult(int var1_1, int var2_2, Intent var3_3) {
        super.onActivityResult(var1_1, var2_2, var3_3);
        if (var2_2 != -1) {
            Log.e(NewHandleFragment.g, "Bad result code, " + var2_2 + ", returned for request code: " + var1_1);
            return;
        }
        switch (var1_1) {
            default: {
                return;
            }
            case 2201: {
                this.E = Analytics.b;
                this.t();
                return;
            }
            case 2202: {
                var4_5 = var3_3.getExtras();
                if (var4_5 == null) return;
                if (var4_5.getParcelable("data") == null) ** GOTO lbl18
                var3_3 = (Bitmap)var4_5.getParcelable("data");
                ** GOTO lbl25
lbl18: // 1 sources:
                if (var3_3.getData() == null) ** GOTO lbl24
                try {
                    var3_3 = MediaStore.Images.Media.getBitmap((ContentResolver)this.getActivity().getContentResolver(), (Uri)var3_3.getData());
                    ** GOTO lbl25
                }
                catch (IOException var3_4) {
                    Log.e(NewHandleFragment.g, "IOException when attempting to load uri from CROP_PHOTO_INTENT_CODE " + var3_4.getMessage());
                }
lbl24: // 2 sources:
                var3_3 = null;
lbl25: // 3 sources:
                if (var3_3 == null) {
                    Log.e(NewHandleFragment.g, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
                    return;
                }
                if (this.z != null) {
                    ImageUtils.a(this.z, (Bitmap)var3_3, Color.parseColor((String)"#222222"), false);
                }
                this.a((Bitmap)var3_3, null);
                this.G();
                return;
            }
            case 2203: 
        }
        this.E = Analytics.c;
        this.t();
    }

    @Override
    public void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        object = RegistrationContext.h();
        if (object != null) {
            this.E = Analytics.a((String)object);
        }
        if (this.E == null) {
            this.E = Analytics.a;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        SingAnalytics.n();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.h != null) {
            this.h.dismiss();
            this.h = null;
        }
        SingApplication.d().a("NEW_HANDLE_WAIT_FOR_SETTINGS");
    }

    @Override
    public String x() {
        return g;
    }

    public static interface AttachListener {
        public void a(EditText var1);
    }

    public static interface UserUpdateListener {
        public void B_();

        public void C_();
    }

}

