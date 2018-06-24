/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorInflater
 *  android.app.Activity
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Looper
 *  android.support.design.widget.TextInputLayout
 *  android.text.Editable
 *  android.text.Html
 *  android.text.Html$ImageGetter
 *  android.text.Html$TagHandler
 *  android.text.InputFilter
 *  android.text.InputFilter$LengthFilter
 *  android.text.Spannable
 *  android.text.SpannableString
 *  android.text.Spanned
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.text.style.URLSpan
 *  android.text.util.Linkify
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.widget.Button
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  android.widget.ToggleButton
 *  com.facebook.CallbackManager
 *  com.facebook.FacebookCallback
 *  com.facebook.FacebookException
 *  com.facebook.login.LoginManager
 *  com.facebook.login.LoginResult
 *  com.smule.singandroid.survey.SurveyContext
 *  com.smule.singandroid.survey.SurveyContext$EntryPoint
 *  com.smule.singandroid.survey.SurveyPresenter
 *  com.smule.singandroid.task.UserUpdateTask
 *  com.smule.singandroid.task.UserUpdateTask$ErrorType
 *  com.smule.singandroid.task.UserUpdateTask$UpdateListener
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.LayoutUtils$SmuleHtmlTagHandler
 *  com.smule.singandroid.utils.LayoutUtils$SmuleTagSpan
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$ProfileCustomizationFeature
 *  com.smule.singandroid.utils.URLSpanNoUnderline
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  twitter4j.AccountSettings
 */
package com.smule.singandroid;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.smule.android.debug.DiagnosticActivity;
import com.smule.android.debug.DiagnosticTapRecognizer;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Log;
import com.smule.android.network.api.OfferAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.OfferManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.network.models.OfferModel;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.network.models.UserInfo;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.utils.EmailOptIn;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.SimpleBarrier;
import com.smule.android.utils.StringUtils;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.WeakListener;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.ContactsSettingsFragment;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.LanguagesFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PhotoTakingFragment;
import com.smule.singandroid.SettingsFragment_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.TwitterOAuthActivity_;
import com.smule.singandroid.WebViewFragment;
import com.smule.singandroid.chat.BlockedUsersFragment;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.dialogs.VerifyEmailMessageDialog;
import com.smule.singandroid.survey.SurveyContext;
import com.smule.singandroid.survey.SurveyPresenter;
import com.smule.singandroid.task.UserUpdateTask;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.URLSpanNoUnderline;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.AccountSettings;

@EFragment
public class SettingsFragment
extends PhotoTakingFragment {
    public static final String g = SettingsFragment.class.getName();
    @ViewById
    protected EditText A;
    @ViewById
    protected TextInputLayout B;
    @ViewById
    protected EditText C;
    @ViewById
    protected EditText D;
    @ViewById
    protected View E;
    @ViewById
    protected View F;
    @ViewById
    protected View G;
    @ViewById
    protected View H;
    @ViewById
    protected View I;
    @ViewById
    protected View J;
    @ViewById
    protected LinearLayout K;
    @ViewById
    protected TextView L;
    @ViewById
    protected ToggleButton M;
    @ViewById
    protected TextView N;
    @ViewById
    protected ToggleButton O;
    @ViewById
    protected ProgressBar P;
    @ViewById
    protected ViewGroup Q;
    @ViewById
    protected TextView R;
    @ViewById
    protected ToggleButton S;
    @ViewById
    protected View T;
    @ViewById
    protected ToggleButton U;
    @ViewById
    protected ToggleButton V;
    @ViewById
    protected View W;
    @ViewById
    protected ToggleButton X;
    @ViewById
    protected TextView Y;
    @ViewById
    protected TextView Z;
    private DiagnosticTapRecognizer aA;
    private Animator aB;
    private Animator aC;
    private Animator aD;
    private Animator aE;
    private Animator aF;
    private Animator aG;
    private Animator aI;
    private Animator aJ;
    private Animator aK;
    private Animator aL;
    private FileUploaderService aM;
    private boolean aN = false;
    private boolean aO = false;
    private SimpleBarrier aP;
    private ServiceConnection aQ;
    private CompoundButton.OnCheckedChangeListener aR;
    private CompoundButton.OnCheckedChangeListener aS;
    @ViewById
    protected TextView aa;
    @ViewById
    protected LinearLayout ab;
    @ViewById
    protected TextView ac;
    @ViewById
    protected IconFontView ad;
    @ViewById
    protected View ae;
    @ViewById
    protected View af;
    @ViewById
    protected TextView ag;
    @ViewById
    protected RelativeLayout ah;
    protected boolean ai = false;
    protected String aj = null;
    protected Integer ak = 0;
    CompoundButton.OnCheckedChangeListener al;
    CompoundButton.OnCheckedChangeListener am;
    private BusyDialog an;
    private String ao;
    private Boolean ap = false;
    private String aq = "";
    private String ar = "";
    private String as = "";
    private String at = "";
    private String au = "";
    private String av = "";
    private String aw = "";
    private EmailOptIn ax;
    private boolean ay = false;
    private boolean az;
    @ViewById
    LinearLayout h;
    @ViewById
    protected ToggleButton i;
    @ViewById
    protected ToggleButton j;
    @ViewById
    protected View k;
    @ViewById
    protected TextInputLayout l;
    @ViewById
    protected EditText m;
    @ViewById
    protected TextInputLayout n;
    @ViewById
    protected EditText o;
    @ViewById
    protected TextInputLayout p;
    @ViewById
    protected EditText q;
    @ViewById
    protected TextInputLayout r;
    @ViewById
    protected EditText s;
    @ViewById
    protected TextInputLayout t;
    @ViewById
    protected EditText u;
    @ViewById
    protected TextInputLayout v;
    @ViewById
    protected EditText w;
    @ViewById
    protected TextInputLayout x;
    @ViewById
    protected EditText y;
    @ViewById
    protected Button z;

    public SettingsFragment() {
        this.aQ = new ServiceConnection(){

            public void onServiceConnected(ComponentName object, IBinder iBinder) {
                object = (FileUploaderService.FileUploaderBinder)iBinder;
                SettingsFragment.this.aM = object.a();
                SettingsFragment.this.aN = true;
                SettingsFragment.this.Y();
                Log.b(SettingsFragment.g, "Binding service end - connected");
            }

            public void onServiceDisconnected(ComponentName componentName) {
                SettingsFragment.this.aN = false;
                Log.b(SettingsFragment.g, "Binding service end - disconnected");
            }
        };
        this.al = new CompoundButton.OnCheckedChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (!SettingsFragment.this.isAdded()) {
                    return;
                }
                if (SettingsFragment.this.an != null && SettingsFragment.this.an.isShowing()) {
                    SettingsFragment.this.M.setChecked(SettingsFragment.this.ax.equals((Object)EmailOptIn.c));
                    return;
                }
                if (bl) {
                    SettingsFragment.this.ax = EmailOptIn.c;
                } else {
                    SettingsFragment.this.ax = EmailOptIn.b;
                }
                SettingsFragment.this.an = new BusyDialog(SettingsFragment.this.getActivity(), SettingsFragment.this.getResources().getString(2131297373));
                SettingsFragment.this.an.show();
                new UserUpdateTask(com.smule.android.network.managers.UserManager.a().i(), com.smule.android.network.managers.UserManager.a().j(), com.smule.android.network.managers.UserManager.a().m(), SettingsFragment.this.ax, new UserUpdateTask.UpdateListener(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void a(NetworkResponse networkResponse, Boolean bl, int n, UserUpdateTask.ErrorType errorType) {
                        if (bl.booleanValue()) {
                            if (SettingsFragment.this.ax.equals((Object)EmailOptIn.b)) {
                                SettingsFragment.this.b(2131297349);
                            } else {
                                SettingsFragment.this.b(2131297347);
                            }
                        }
                        SettingsFragment.this.a(networkResponse, bl, n);
                    }

                    public void b(NetworkResponse networkResponse, Boolean bl, int n, UserUpdateTask.ErrorType errorType) {
                    }
                }).execute((Object[])new Void[0]);
            }

        };
        this.aR = new CompoundButton.OnCheckedChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onCheckedChanged(CompoundButton object, boolean bl) {
                object = SettingsFragment.this;
                bl = !bl;
                object.c(bl);
            }
        };
        this.aS = new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                SettingsFragment.this.d(bl);
            }
        };
        this.am = new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (!SettingsFragment.this.isAdded()) {
                    return;
                }
                SingApplication.g().getSharedPreferences("sing_prefs", 0).edit().putBoolean("SAMSUNG_RTM_ENABLED_IN_SETTINGS", bl).apply();
            }
        };
    }

    private void V() {
        if (this.aO) {
            return;
        }
        SingApplication.d().a("INIT_FILE_UPLOADER_SERVICE_KEY", Collections.singletonList("InitAppTask.OP_USER_LOGGED_IN"), new OperationLoader.Operation(){

            @Override
            public void a(OperationLoader object) {
                if (com.smule.android.network.managers.UserManager.a().y()) {
                    SettingsFragment.this.t();
                    object.c(this.f);
                    return;
                }
                object = new Observer((OperationLoader)object){
                    final /* synthetic */ OperationLoader a;
                    {
                        this.a = operationLoader;
                    }

                    @Override
                    public void update(Observable observable, Object object) {
                        if (com.smule.android.network.managers.UserManager.a().y()) {
                            NotificationCenter.a().b("USER_LOGGED_IN_EVENT", this);
                            NotificationCenter.a().b("USER_RE_LOGGED_IN_EVENT", this);
                            SettingsFragment.this.t();
                            this.a.c(1.this.f);
                        }
                    }
                };
                NotificationCenter.a().a("USER_LOGGED_IN_EVENT", (Observer)object);
                NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", (Observer)object);
            }

        }).a();
    }

    private void W() {
        this.aq = com.smule.android.network.managers.UserManager.a().i();
        this.ar = com.smule.android.network.managers.UserManager.a().j();
        this.as = com.smule.android.network.managers.UserManager.a().k();
        this.at = com.smule.android.network.managers.UserManager.a().l();
        this.au = com.smule.android.network.managers.UserManager.a().m();
        if (this.aq == null) {
            this.aq = "";
        }
        if (this.ar == null) {
            this.ar = "";
        }
        if (this.as == null) {
            this.as = "";
        }
        if (this.at == null) {
            this.at = "";
        }
        if (this.au == null) {
            this.au = "";
        }
    }

    private void X() {
        Object object = new TextWatcher(){

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                block7 : {
                    block6 : {
                        if (!SettingsFragment.this.isAdded()) break block6;
                        n = !SettingsFragment.this.o.getText().toString().equals(SettingsFragment.this.aq) || !SettingsFragment.this.q.getText().toString().equals(SettingsFragment.this.ar) || !SettingsFragment.this.s.getText().toString().isEmpty() || !SettingsFragment.this.w.getText().toString().equals(SettingsFragment.this.as) || !SettingsFragment.this.y.getText().toString().equals(SettingsFragment.this.at) ? 1 : 0;
                        if (n != 0) {
                            SettingsFragment.this.z.setEnabled(true);
                        } else {
                            SettingsFragment.this.z.setEnabled(false);
                        }
                        if (!SettingsFragment.this.s.getText().toString().isEmpty()) {
                            SettingsFragment.this.C.setText((CharSequence)SettingsFragment.this.s.getText());
                        }
                        if (!SettingsFragment.this.u.getText().toString().isEmpty()) break block7;
                    }
                    return;
                }
                SettingsFragment.this.D.setText((CharSequence)SettingsFragment.this.u.getText());
            }
        };
        WeakListener.a(this.o, (TextWatcher)object);
        WeakListener.a(this.q, (TextWatcher)object);
        WeakListener.a(this.s, (TextWatcher)object);
        WeakListener.a(this.w, (TextWatcher)object);
        WeakListener.a(this.y, (TextWatcher)object);
        object = this.getResources().getString(2131297344, new Object[]{Integer.toString(50)});
        this.a(this.w, this.v, (String)object);
        this.a(this.y, this.x, (String)object);
        this.Z();
        this.aa();
        this.ab();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void Y() {
        if (this.aO && this.aN) {
            View view = this.k;
            int n = new SingServerValues().w() ? 0 : 8;
            view.setVisibility(n);
            this.j.setChecked(this.aM.a());
        }
        this.j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (SettingsFragment.this.aO && SettingsFragment.this.aN) {
                    SettingsFragment.this.aM.b(bl);
                }
            }
        });
    }

    private void Z() {
        if (!SubscriptionManager.a().b()) {
            this.i.setChecked(false);
            this.i.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    SettingsFragment.this.ap();
                    SettingsFragment.this.i.setChecked(false);
                    SettingsFragment.this.a(UpsellManager.a());
                }
            });
            return;
        }
        boolean bl = true;
        if (com.smule.android.network.managers.UserManager.a().v() != null) {
            bl = com.smule.android.network.managers.UserManager.a().v().displayMentions;
        }
        this.i.setChecked(bl);
        this.i.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                SettingsFragment.this.ap();
                SettingsFragment.this.e(bl);
                NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
            }
        });
    }

    private Spannable a(SpannableString spannableString) {
        spannableString = new SpannableString((CharSequence)spannableString);
        for (URLSpan uRLSpan : (URLSpan[])spannableString.getSpans(0, spannableString.length(), URLSpan.class)) {
            int n = spannableString.getSpanStart((Object)uRLSpan);
            int n2 = spannableString.getSpanEnd((Object)uRLSpan);
            spannableString.removeSpan((Object)uRLSpan);
            spannableString.setSpan((Object)new URLSpanNoUnderline(uRLSpan.getURL()), n, n2, 0);
        }
        return spannableString;
    }

    public static SettingsFragment a() {
        return SettingsFragment.a(null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static SettingsFragment a(FocusField focusField) {
        SettingsFragment_ settingsFragment_ = new SettingsFragment_();
        if (focusField != null) {
            Bundle bundle = new Bundle();
            switch (.a[focusField.ordinal()]) {
                case 1: {
                    bundle.putBoolean("SETTINGS_GOTO_BLURB", true);
                }
                default: {
                    break;
                }
                case 2: {
                    bundle.putBoolean("SETTINGS_GOTO_WIFI_ONLY", true);
                }
            }
            settingsFragment_.setArguments(bundle);
        }
        return settingsFragment_;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void a(int var1_1, UserUpdateTask.ErrorType var2_2) {
        block6 : {
            switch (.c[var2_2.ordinal()]) {
                case 1: {
                    this.p.setError((CharSequence)this.getString(var1_1));
                    ** break;
                }
                case 2: {
                    this.n.setError((CharSequence)this.getString(var1_1));
                    ** break;
                }
                case 3: {
                    this.r.setError((CharSequence)this.getString(var1_1));
                }
lbl10: // 4 sources:
                default: {
                    break block6;
                }
                case 4: 
            }
            this.r.setError((CharSequence)this.getString(var1_1));
            this.t.setError((CharSequence)this.getString(var1_1));
        }
        this.S();
    }

    private void a(View view, Animator animator2, float f) {
        animator2.end();
        animator2.cancel();
        view.setVisibility(0);
        view.setAlpha(f);
        animator2.setTarget((Object)view);
        animator2.start();
    }

    private void a(EditText editText, final TextInputLayout textInputLayout, final String string2) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50){

            public CharSequence filter(CharSequence charSequence, int n, int n2, Spanned spanned, int n3, int n4) {
                if ((charSequence = super.filter(charSequence, n, n2, spanned, n3, n4)) != null) {
                    textInputLayout.setError((CharSequence)string2);
                    return charSequence;
                }
                textInputLayout.setError(null);
                return charSequence;
            }
        }});
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(boolean bl, int n, int n2, NetworkResponse networkResponse, UserUpdateTask.ErrorType errorType) {
        String string2;
        String string3;
        String string4;
        this.ad();
        String string5 = com.smule.android.network.managers.UserManager.a().i();
        Object object = string5;
        if (string5 == null) {
            object = "";
        }
        string5 = string3 = com.smule.android.network.managers.UserManager.a().j();
        if (string3 == null) {
            string5 = "";
        }
        if ((string3 = com.smule.android.network.managers.UserManager.a().m()) == null) {
            string3 = "";
        }
        if ((string4 = com.smule.android.network.managers.UserManager.a().k()) == null) {
            string4 = "";
        }
        if ((string2 = com.smule.android.network.managers.UserManager.a().l()) == null) {
            string2 = "";
        }
        if (!this.q.getText().toString().equals(string5)) {
            this.q.requestFocus();
        } else if (!this.o.getText().toString().equals(object)) {
            this.o.requestFocus();
        } else if (!this.s.getText().toString().equals("") && !this.s.getText().toString().equals(string3)) {
            this.s.requestFocus();
        } else if (!this.w.getText().toString().equals(string4) && !TextUtils.isEmpty((CharSequence)this.w.getText().toString().trim())) {
            this.w.requestFocus();
        } else if (!this.y.getText().toString().equals(string2) && !TextUtils.isEmpty((CharSequence)this.y.getText().toString().trim())) {
            this.y.requestFocus();
        }
        final boolean bl2 = !this.aq.equals(object);
        final boolean bl3 = !this.ar.equals(string5);
        final boolean bl4 = !this.au.equals(string3);
        final boolean bl5 = !this.as.equals(string4);
        final boolean bl6 = !this.at.equals(string2);
        final boolean bl7 = bl && !this.s.getText().toString().isEmpty();
        if (this.an != null) {
            this.an.setOnDismissListener(new DialogInterface.OnDismissListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void onDismiss(DialogInterface object) {
                    object = SettingsFragment.this;
                    boolean bl = bl2;
                    boolean bl22 = bl3;
                    boolean bl32 = bl4 || bl7;
                    ((SettingsFragment)((Object)object)).a(bl, bl22, bl32, bl5, bl6);
                }
            });
        }
        if (bl) {
            this.z.setEnabled(false);
            if (this.ap.booleanValue() && this.an != null) {
                this.an.a(2, this.getResources().getString(2131297331), this.ao, null, this.getString(2131296705));
                this.ap = false;
                this.ao = null;
            } else {
                this.a(1, -1, 2131297363, null);
            }
        } else {
            object = null;
            if (networkResponse != null) {
                object = networkResponse.h();
            }
            if (n == -1) {
                if (errorType == UserUpdateTask.ErrorType.e) {
                    this.a(2, 2131297372, n2, (Integer)object);
                } else {
                    this.a(n2, errorType);
                }
                MagicNetwork.a(networkResponse);
            } else if (errorType == UserUpdateTask.ErrorType.e) {
                this.a(2, 2131297372, n, (Integer)object);
            } else {
                this.a(n, errorType);
            }
        }
        if (bl2 || bl3 || bl4 || bl5 || bl6) {
            NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
        }
    }

    private void a(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        if (bl) {
            if (this.aB == null) {
                this.aB = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034115);
            }
            this.a(this.E, this.aB, 1.0f);
        }
        if (bl2) {
            if (this.aC == null) {
                this.aC = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034115);
            }
            this.a(this.F, this.aC, 1.0f);
        }
        if (bl3) {
            if (this.aD == null) {
                this.aD = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034115);
            }
            if (this.aE == null) {
                this.aE = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034115);
            }
            if (this.aF == null) {
                this.aF = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034114);
            }
            if (this.aG == null) {
                this.aG = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034114);
            }
            if (this.aI == null) {
                this.aI = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034115);
            }
            if (this.aJ == null) {
                this.aJ = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034115);
            }
            this.a(this.G, this.aD, 1.0f);
            this.a(this.H, this.aE, 1.0f);
            this.a((View)this.s, this.aF, 0.0f);
            this.a((View)this.u, this.aG, 0.0f);
            this.a((View)this.C, this.aI, 1.0f);
            this.a((View)this.D, this.aJ, 1.0f);
        }
        if (bl4) {
            if (this.aK == null) {
                this.aK = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034115);
            }
            this.a(this.I, this.aK, 1.0f);
        }
        if (bl5) {
            if (this.aL == null) {
                this.aL = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)2131034115);
            }
            this.a(this.J, this.aL, 1.0f);
        }
    }

    private void aa() {
        String string2;
        if (SubscriptionManager.a().b() && com.smule.android.network.managers.UserManager.a().v() != null && !TextUtils.isEmpty((CharSequence)(string2 = com.smule.android.network.managers.UserManager.a().v().displayName))) {
            this.m.setText((CharSequence)string2);
            this.aw = string2;
        }
        this.m.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onFocusChange(View view, boolean bl) {
                if (bl) {
                    SettingsFragment.this.aw = SettingsFragment.this.m.getText().toString();
                }
                if (bl && !SubscriptionManager.a().b()) {
                    SettingsFragment.this.aq();
                    SettingsFragment.this.a(UpsellManager.a());
                    return;
                } else {
                    if (bl || !SubscriptionManager.a().b()) return;
                    {
                        SettingsFragment.this.aq();
                        SettingsFragment.this.c(SettingsFragment.this.aw);
                        NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
                        return;
                    }
                }
            }
        });
        this.m.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                if (!SettingsFragment.this.isAdded() || n != 3 && n != 6) {
                    return false;
                }
                SettingsFragment.this.R();
                return false;
            }
        });
        string2 = this.getResources().getString(2131297320, new Object[]{Integer.toString(50)});
        this.a(this.m, this.l, string2);
    }

    private void ab() {
        this.A.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            public void onFocusChange(View view, boolean bl) {
                if (!bl) {
                    SettingsFragment.this.ac();
                }
            }
        });
        this.A.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                if (!SettingsFragment.this.isAdded() || n != 3 && n != 6) {
                    return false;
                }
                SettingsFragment.this.ac();
                SettingsFragment.this.R();
                return false;
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ac() {
        String string2;
        block5 : {
            block4 : {
                boolean bl = false;
                if (this.ay) return;
                if (this.A == null) break block4;
                string2 = this.A.getText().toString();
                if (this.av == null && string2.length() > 0 || !string2.equals(this.av)) {
                    bl = true;
                }
                if (bl) break block5;
            }
            return;
        }
        this.ar();
        this.ay = true;
        Log.b(g, "Updating blurb");
        com.smule.android.network.managers.UserManager.a().a(string2, new UserManager(){

            @Override
            public void handleResponse(NetworkResponse networkResponse) {
                SettingsFragment.this.av = string2;
                SettingsFragment.this.b(2131297363);
                NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
                SettingsFragment.this.ay = false;
            }
        });
    }

    private void ad() {
        this.l.setError(null);
        this.B.setError(null);
        this.p.setError(null);
        this.n.setError(null);
        this.r.setError(null);
        this.t.setError(null);
        this.v.setError(null);
        this.x.setError(null);
    }

    private void aj() {
        com.smule.android.facebook.MagicFacebook.a().a(new MagicFacebook(){

            @Override
            public void a(MagicFacebook facebookUserInfo) {
                if (!SettingsFragment.this.isAdded()) {
                    return;
                }
                SettingsFragment.this.N.setText((CharSequence)facebookUserInfo.g);
                SettingsFragment.this.O.setChecked(true);
                SettingsFragment.this.d(facebookUserInfo.b);
                SettingsFragment.this.getActivity().getSharedPreferences("sing_prefs", 0).edit().putBoolean("facebook.enabled", true).apply();
            }

            @Override
            public void b(MagicFacebook facebookUserInfo) {
                if (!SettingsFragment.this.isAdded()) {
                    return;
                }
                SettingsFragment.this.N.setText(2131296772);
                SettingsFragment.this.O.setChecked(false);
            }
        });
        ((MasterActivity)this.getActivity()).P();
    }

    private void ak() {
        this.O.setChecked(false);
        this.N.setText((CharSequence)this.getString(2131296772));
        this.as();
        this.getActivity().getSharedPreferences("sing_prefs", 0).edit().putBoolean("facebook.enabled", false).apply();
    }

    private void al() {
        MagicTwitter magicTwitter = MiscUtils.a();
        if (magicTwitter == null) {
            Log.e(g, "Twitter instance not ready");
            return;
        }
        magicTwitter.a(new MagicTwitter.TwitterAccountCallback(){

            @Override
            public void a(final AccountSettings accountSettings) {
                new Handler(Looper.getMainLooper()).post(new Runnable(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void run() {
                        if (!SettingsFragment.this.isAdded()) {
                            return;
                        }
                        if (accountSettings == null) {
                            SettingsFragment.this.R.setText(2131297568);
                            SettingsFragment.this.S.setChecked(false);
                        } else {
                            SettingsFragment.this.S.setChecked(true);
                            SettingsFragment.this.R.setText((CharSequence)accountSettings.getScreenName());
                        }
                        SettingsFragment.this.Q.forceLayout();
                    }
                });
            }

        });
    }

    private void am() {
        Locale locale = ((BaseActivity)this.getActivity()).c();
        this.L.setText((CharSequence)StringUtils.a(locale.getDisplayLanguage(locale)));
    }

    private void an() {
        String string2 = "sing_android-qa-release-prod".replace("sing_android-qa-", "");
        String string3 = "5.7.5" + " Build 1465";
        string2 = string3 + " " + string2;
        this.Z.setText((CharSequence)string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ao() {
        int n;
        if (new DeviceSettings().e()) {
            n = 1;
            this.ab.setVisibility(0);
            this.ad.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment.this.a(WebViewFragment.a(SettingsFragment.this.getResources().getString(2131297960), SettingsFragment.this.getResources().getString(2131297958)));
                }
            });
        } else {
            n = 0;
        }
        if (n <= 0) return;
        this.aa.setVisibility(0);
    }

    private void ap() {
        SingAnalytics.a((String)null, (String)null, (SingAnalytics.ProfileCustomizationFeature[])new SingAnalytics.ProfileCustomizationFeature[]{SingAnalytics.ProfileCustomizationFeature.f});
    }

    private void aq() {
        SingAnalytics.a((String)null, (String)null, (SingAnalytics.ProfileCustomizationFeature[])new SingAnalytics.ProfileCustomizationFeature[]{SingAnalytics.ProfileCustomizationFeature.e});
    }

    private void ar() {
        SingAnalytics.a((String)null, (String)null, (SingAnalytics.ProfileCustomizationFeature[])new SingAnalytics.ProfileCustomizationFeature[]{SingAnalytics.ProfileCustomizationFeature.b});
    }

    private void as() {
        com.smule.android.network.managers.UserManager.a().S();
    }

    private void at() {
        com.smule.android.network.managers.UserManager userManager = com.smule.android.network.managers.UserManager.a();
        UserManager emailStatusResponseCallback = new UserManager(){

            @Override
            public void handleResponse(UserInfo userInfo) {
                SettingsFragment.this.aP.a();
            }
        };
        userManager.a(new String[]{"email", "emailStatus"}, emailStatusResponseCallback);
    }

    private void au() {
        OfferManager.a().a(OfferAPI.EMAIL_CONFIRM, new OfferManager.OfferEligibilityResponseCallback(){

            @Override
            public void handleResponse(OfferModel offerModel) {
                if (offerModel.a()) {
                    Log.c(SettingsFragment.g, offerModel.a.j);
                    SettingsFragment.this.ai = offerModel.eligible;
                    SettingsFragment.this.ak = offerModel.reward;
                    SettingsFragment.this.aj = offerModel.trigger;
                    SettingsFragment.this.aP.a();
                }
            }
        });
    }

    private void av() {
        this.aP = new SimpleBarrier(3, new Runnable(){

            @Override
            public void run() {
                if (com.smule.android.network.managers.UserManager.a().T() == null || !SettingsFragment.this.ai || !SettingsFragment.this.isAdded()) {
                    return;
                }
                if (com.smule.android.network.managers.UserManager.a().T() == UserManager.b) {
                    SettingsFragment.this.ag.setText((CharSequence)SettingsFragment.this.getResources().getString(2131297327, new Object[]{Integer.toString(SettingsFragment.this.ak)}));
                    SettingsFragment.this.ah.setVisibility(0);
                    SettingsFragment.this.p.setError((CharSequence)SettingsFragment.this.getResources().getString(2131297322));
                    return;
                }
                if (com.smule.android.network.managers.UserManager.a().j() == null) {
                    SettingsFragment.this.ag.setText((CharSequence)SettingsFragment.this.getResources().getString(2131297321, new Object[]{Integer.toString(SettingsFragment.this.ak)}));
                    SettingsFragment.this.ah.setVisibility(0);
                    SettingsFragment.this.p.setError((CharSequence)SettingsFragment.this.getResources().getString(2131297322));
                    return;
                }
                SettingsFragment.this.ah.setVisibility(0);
                SettingsFragment.this.f(com.smule.android.network.managers.UserManager.a().j());
            }
        });
    }

    private void c(String object) {
        if (!this.m.getText().toString().equals(object)) {
            object = new NetworkResponseCallback(){

                @Override
                public void handleResponse(NetworkResponse networkResponse) {
                    if (networkResponse.c()) {
                        Log.b(SettingsFragment.g, "Successfully updated display name.");
                        return;
                    }
                    Log.e(SettingsFragment.g, "Error updating display name: " + networkResponse.b + "; " + networkResponse.m);
                }
            };
            com.smule.android.network.managers.UserManager.a().a(null, this.m.getText().toString(), null, null, (NetworkResponseCallback)object);
        }
    }

    private void d(String string2) {
        com.smule.android.network.managers.UserManager.a().n(string2);
    }

    private void d(final boolean bl) {
        SingApplication.k().b(bl, new Completion<ChatStatus>(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(ChatStatus object) {
                if (object != ChatStatus.a && SettingsFragment.this.isAdded()) {
                    SettingsFragment.this.a(new Runnable((ChatStatus)((Object)object)){
                        final /* synthetic */ ChatStatus a;
                        {
                            this.a = chatStatus;
                        }

                        @Override
                        public void run() {
                            SettingsFragment.this.V.setOnCheckedChangeListener(null);
                            SettingsFragment.this.V.setChecked(bl);
                            SettingsFragment.this.V.setOnCheckedChangeListener(SettingsFragment.this.aS);
                            ChatUtils.a((Context)SettingsFragment.this.getActivity(), 2131296499, this.a);
                        }
                    });
                    return;
                }
                object = SettingsFragment.this;
                int n = bl ? 2131296591 : 2131296590;
                object.b(n);
                object = bl ? ChatAnalytics.SettingToggleType.a : ChatAnalytics.SettingToggleType.b;
                ChatAnalytics.b((ChatAnalytics.SettingToggleType)object);
            }

        });
    }

    private void e(String object) {
        object = new SpannableString((CharSequence)this.getResources().getString(2131297375, new Object[]{object}));
        Linkify.addLinks((Spannable)object, (int)2);
        object = new VerifyEmailMessageDialog(this.getActivity(), 2131297376, this.a((SpannableString)object), 2131297354, 2131296674);
        object.a(new Runnable(){

            @Override
            public void run() {
                Intent intent = Intent.makeMainSelectorActivity((String)"android.intent.action.MAIN", (String)"android.intent.category.APP_EMAIL");
                intent.addFlags(268435456);
                SettingsFragment.this.startActivity(intent);
            }
        });
        object.show();
    }

    private void e(boolean bl) {
        NetworkResponseCallback networkResponseCallback = new NetworkResponseCallback(){

            @Override
            public void handleResponse(NetworkResponse networkResponse) {
                if (networkResponse.c()) {
                    Log.b(SettingsFragment.g, "Successfully updated display mentions toggle.");
                    return;
                }
                Log.e(SettingsFragment.g, "Error updating display mentions toggle: " + networkResponse.b + "; " + networkResponse.m);
            }
        };
        com.smule.android.network.managers.UserManager.a().a(null, null, null, bl, networkResponseCallback);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void f(String object) {
        int n;
        int n2;
        Object var4_2 = null;
        SpannableString spannableString = new SpannableString((CharSequence)Html.fromHtml((String)this.getResources().getString(2131297377), (Html.ImageGetter)null, (Html.TagHandler)new LayoutUtils.SmuleHtmlTagHandler((Context)this.getActivity())));
        LayoutUtils.SmuleTagSpan smuleTagSpan = LayoutUtils.SmuleTagSpan.a((SpannableString)spannableString, (String)"sm-agg");
        if (smuleTagSpan != null) {
            n = spannableString.getSpanStart((Object)smuleTagSpan);
            n2 = spannableString.getSpanEnd((Object)smuleTagSpan);
            object = new ClickableSpan((String)object){
                final /* synthetic */ String a;
                {
                    this.a = string2;
                }

                public void onClick(View view) {
                    com.smule.android.network.managers.UserManager.a().a(com.smule.android.network.managers.UserManager.a().f(), new UserManager(){

                        @Override
                        public void handleResponse(NetworkResponse networkResponse) {
                            if (networkResponse.c()) {
                                Log.c(SettingsFragment.g, networkResponse.j);
                                SettingsFragment.this.e(35.this.a);
                                return;
                            }
                            Log.e(SettingsFragment.g, networkResponse.m);
                            Toaster.a((Context)SettingsFragment.this.getActivity(), 2131297366);
                        }
                    });
                }

                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(SettingsFragment.this.getResources().getColor(2131689544));
                }

            };
        } else {
            n2 = 0;
            n = 0;
            object = var4_2;
        }
        spannableString.setSpan(object, n, n2, 33);
        this.ag.setText((CharSequence)spannableString);
        this.ag.setHighlightColor(0);
        this.ag.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void F() {
        Bundle bundle;
        Log.b(g, "Begin updateFollowingViewBinding()");
        this.aP.a();
        if (com.smule.android.network.managers.UserManager.a().s() != null) {
            this.av = com.smule.android.network.managers.UserManager.a().s().trim();
            this.A.setText((CharSequence)this.av);
        } else {
            Log.b(g, "Getting blurb for account: " + com.smule.android.network.managers.UserManager.a().f());
            com.smule.android.network.managers.UserManager.a().a(com.smule.android.network.managers.UserManager.a().f(), new UserManager(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void handleResponse(UserManager.UserBlurbResponse userBlurbResponse) {
                    block5 : {
                        block4 : {
                            if (!SettingsFragment.this.isAdded()) break block4;
                            if (!userBlurbResponse.a.c()) {
                                Log.e(SettingsFragment.g, "errorGettingBlurb - called in updateFollowingViewBinding");
                                return;
                            }
                            SettingsFragment.this.av = userBlurbResponse.mBlurb;
                            if (SettingsFragment.this.av != null && SettingsFragment.this.av.trim().length() > 0) break block5;
                        }
                        return;
                    }
                    SettingsFragment.this.A.setText((CharSequence)SettingsFragment.this.av.trim());
                }
            });
        }
        if ((bundle = this.getArguments()) != null && bundle.getBoolean("SETTINGS_GOTO_BLURB", false)) {
            bundle.putBoolean("SETTINGS_GOTO_BLURB", false);
            this.A.setFocusableInTouchMode(true);
            this.A.requestFocus();
            Activity activity = this.getActivity();
            if (activity != null) {
                MiscUtils.a((Activity)activity, (EditText)this.A);
            }
        }
        if (bundle != null && bundle.getBoolean("SETTINGS_GOTO_WIFI_ONLY", false)) {
            bundle.putBoolean("SETTINGS_GOTO_WIFI_ONLY", false);
            new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    SettingsFragment.this.af.requestFocus();
                }
            }, 200);
        }
        bundle = (ViewGroup)this.M.getParent();
        bundle.removeView((View)this.M);
        this.ax = com.smule.android.network.managers.UserManager.a().t();
        this.M.setChecked(this.ax.equals((Object)EmailOptIn.c));
        bundle.addView((View)this.M);
        this.M.setOnCheckedChangeListener(this.al);
        if (new DeviceSettings().m()) {
            boolean bl = SingApplication.g().getSharedPreferences("sing_prefs", 0).getBoolean("SAMSUNG_RTM_ENABLED_IN_SETTINGS", new SingServerValues().z());
            this.X.setChecked(bl);
            this.X.setOnCheckedChangeListener(this.am);
            this.W.setVisibility(0);
        } else {
            this.W.setVisibility(8);
        }
        this.X();
        this.T();
        if (ChatUtils.b()) {
            this.H();
            this.I();
            this.T.setVisibility(0);
        } else {
            this.T.setVisibility(8);
        }
        this.aA = new DiagnosticTapRecognizer((View)this.Y, new Runnable(){

            @Override
            public void run() {
                Intent intent = new Intent(SingApplication.g(), DiagnosticActivity.class);
                SettingsFragment.this.startActivity(intent);
            }
        });
        this.am();
        this.an();
        this.ao();
    }

    @Click
    protected void G() {
        ((MasterActivity)this.getActivity()).u();
    }

    /*
     * Enabled aggressive block sorting
     */
    void H() {
        this.U.setOnCheckedChangeListener(null);
        ToggleButton toggleButton = this.U;
        boolean bl = !SingApplication.k().f();
        toggleButton.setChecked(bl);
        this.U.setOnCheckedChangeListener(this.aR);
    }

    void I() {
        this.V.setOnCheckedChangeListener(null);
        this.V.setChecked(SingApplication.k().g());
        this.V.setOnCheckedChangeListener(this.aS);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void J() {
        if (SingApplication.h.booleanValue()) {
            return;
        }
        boolean bl = !this.O.isChecked();
        this.O.setChecked(bl);
        if (bl) {
            TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296776), this.getString(2131296775), true, true);
            textAlertDialog.a(new CustomAlertDialog.CustomAlertDialogListener(){

                @Override
                public void a(CustomAlertDialog customAlertDialog) {
                    SettingsFragment.this.ak();
                }

                @Override
                public void b(CustomAlertDialog customAlertDialog) {
                }
            });
            textAlertDialog.c(true);
            textAlertDialog.b(true);
            textAlertDialog.show();
            return;
        }
        if (com.smule.android.facebook.MagicFacebook.a().c()) {
            this.aj();
            return;
        }
        LoginManager.getInstance().registerCallback(this.p().h(), (FacebookCallback)new FacebookCallback<LoginResult>(){

            public void a(LoginResult loginResult) {
                if (!SettingsFragment.this.isAdded()) {
                    return;
                }
                Log.b(SettingsFragment.g, "onSuccess called; session state is open: " + (Object)loginResult);
                com.smule.android.facebook.MagicFacebook.a().g();
                SettingsFragment.this.aj();
            }

            public void onCancel() {
                Log.b(SettingsFragment.g, "onCancel.");
                if (!SettingsFragment.this.isAdded()) {
                    return;
                }
                SettingsFragment.this.N.setText(2131296772);
                SettingsFragment.this.O.setChecked(false);
            }

            public void onError(FacebookException facebookException) {
                Log.b(SettingsFragment.g, "onError.");
                if (!SettingsFragment.this.isAdded()) {
                    return;
                }
                SettingsFragment.this.N.setText(2131296772);
                SettingsFragment.this.O.setChecked(false);
            }

            public /* synthetic */ void onSuccess(Object object) {
                this.a((LoginResult)object);
            }
        });
        LoginManager.getInstance().logInWithReadPermissions(this.getActivity(), MagicNetwork.d().getFacebookReadPermissions());
    }

    @Click
    protected void K() {
        if (SingApplication.h.booleanValue()) {
            return;
        }
        Object object = MiscUtils.a();
        if (object == null) {
            Log.e(g, "Twitter instance not ready");
            this.S.setChecked(false);
            return;
        }
        boolean bl = object.c();
        this.S.setChecked(bl);
        if (bl) {
            object = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297570), this.getString(2131297569), true, true);
            object.a(new CustomAlertDialog.CustomAlertDialogListener(){

                @Override
                public void a(CustomAlertDialog customAlertDialog) {
                    MagicTwitter.a().b();
                    SettingsFragment.this.R.setText(2131297568);
                    SettingsFragment.this.S.setChecked(false);
                }

                @Override
                public void b(CustomAlertDialog customAlertDialog) {
                }
            });
            object.c(true);
            object.b(true);
            object.show();
            return;
        }
        this.startActivityForResult(new Intent((Context)this.getActivity(), TwitterOAuthActivity_.class), 32487);
    }

    @Click
    protected void L() {
        this.a(ContactsSettingsFragment.a());
    }

    @Click
    protected void M() {
        this.a(BlockedUsersFragment.a());
    }

    @Click
    protected void N() {
        Log.b(g, "showPrivacyPolicy");
        this.a(WebViewFragment.a(com.smule.android.network.managers.UserManager.a().W(), this.getResources().getString(2131297361)));
    }

    @Click
    protected void O() {
        Log.b(g, "showTermsOfService");
        this.a(WebViewFragment.a(com.smule.android.network.managers.UserManager.a().X(), this.getResources().getString(2131297371)));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Click
    protected void P() {
        block36 : {
            if (this.an != null && this.an.isShowing()) {
                return;
            }
            MiscUtils.a((Activity)this.getActivity(), (boolean)false);
            this.ae.requestFocus();
            var16_1 = new ArrayList<ErrorType>();
            var7_2 = false;
            var11_3 = com.smule.android.network.managers.UserManager.a();
            var6_5 = var5_4 = var11_3.j();
            if (var5_4 == null) {
                var6_5 = "";
            }
            var8_6 = var5_4 = var11_3.i();
            if (var5_4 == null) {
                var8_6 = "";
            }
            var5_4 = var9_7 = var11_3.m();
            if (var9_7 == null) {
                var5_4 = "";
            }
            if ((var10_8 = var11_3.k()) == null) {
                var10_8 = "";
            }
            if ((var9_7 = var11_3.l()) == null) {
                var9_7 = "";
            }
            if (!(var11_3 = this.s.getText().toString()).equals(this.u.getText().toString())) {
                var13_9 = false;
                var11_3 = false;
                var1_10 = 0;
                var7_2 = var5_4;
                var5_4 = var11_3;
            } else {
                var13_9 = true;
                if (var11_3.length() >= 6 && var11_3.length() <= 16) {
                    var5_4 = true;
                    var1_10 = 0;
                    var7_2 = var11_3;
                } else if (var11_3.length() > 0) {
                    var16_1.add(ErrorType.c);
                    var1_10 = 1;
                    var11_3 = var5_4;
                    var5_4 = var7_2;
                    var7_2 = var11_3;
                } else {
                    var11_3 = var5_4;
                    var1_10 = 0;
                    var5_4 = var7_2;
                    var7_2 = var11_3;
                }
            }
            if ((var11_3 = this.o.getText().toString()).compareTo((String)var8_6) != 0) {
                if (TextUtils.isEmpty((CharSequence)var11_3) || TextUtils.getTrimmedLength((CharSequence)var11_3) == 0) {
                    var16_1.add(ErrorType.b);
                    this.o.setText((CharSequence)var8_6);
                    var11_3 = var8_6;
                    var1_10 = 1;
                    var5_4 = false;
                    var2_11 = false;
                } else {
                    var2_11 = true;
                }
            } else {
                var11_3 = var8_6;
                var2_11 = false;
            }
            if ((var15_12 = this.q.getText().toString()).compareTo((String)var6_5) != 0) {
                if (TextUtils.isEmpty((CharSequence)var15_12) || TextUtils.getTrimmedLength((CharSequence)var15_12) == 0) {
                    var16_1.add(ErrorType.a);
                    this.q.setText((CharSequence)var6_5);
                    var12_13 = false;
                    var1_10 = 1;
                    var4_14 = false;
                    var5_4 = var6_5;
                } else {
                    var4_14 = true;
                    var14_15 = var15_12;
                    var12_13 = var5_4;
                    var5_4 = var14_15;
                }
            } else {
                var4_14 = false;
                var14_15 = var6_5;
                var12_13 = var5_4;
                var5_4 = var14_15;
            }
            if (var1_10 == 0 && (var2_11 || var4_14)) {
                var6_5 = true;
            } else {
                this.o.setText((CharSequence)var8_6);
                this.q.setText((CharSequence)var6_5);
                var6_5 = var12_13;
            }
            var8_6 = var13_9 == false ? Boolean.valueOf(false) : var6_5;
            var6_5 = this.w.getText().toString().trim();
            if (var6_5.compareTo((String)var10_8) != 0) {
                var10_8 = var6_5;
                var6_5 = true;
            } else {
                var6_5 = false;
            }
            if ((var12_13 = this.y.getText().toString().trim()).compareTo((String)var9_7) != 0) {
                var6_5 = true;
                var9_7 = var12_13;
            }
            if (var1_10 == 0 || !var13_9.booleanValue()) break block36;
            this.s.setText((CharSequence)"");
            this.u.setText((CharSequence)"");
            var12_13 = new StringBuilder();
            var2_11 = false;
            for (var1_10 = 0; var1_10 < var16_1.size(); ++var1_10) {
                block37 : {
                    switch (.b[((ErrorType)var16_1.get(var1_10)).ordinal()]) {
                        case 1: {
                            this.p.setError((CharSequence)this.getString(2131297328));
                            var12_13.append(this.getString(2131297328));
                            ** break;
                        }
                        case 2: {
                            this.n.setError((CharSequence)this.getString(2131297329));
                            var12_13.append(this.getString(2131297329));
                        }
lbl106: // 3 sources:
                        default: {
                            var3_16 = var2_11;
                            if (var1_10 < var16_1.size() - 1) {
                                var12_13.append("\n\n");
                                var3_16 = var2_11;
                            }
                            break block37;
                        }
                        case 3: 
                    }
                    var3_16 = true;
                }
                var2_11 = var3_16;
            }
            if (var6_5.booleanValue()) {
                this.ao = var12_13.toString();
                this.ap = true;
            }
        }
        if (!var8_6.booleanValue() && var13_9.booleanValue() && !var6_5.booleanValue()) {
            this.w.setText((CharSequence)this.w.getText().toString().trim());
            this.y.setText((CharSequence)this.y.getText().toString().trim());
            this.b(2131297350);
            return;
        }
        this.an = new BusyDialog(this.getActivity(), this.getResources().getString(2131297373));
        this.an.show();
        var12_13 = new UserUpdateTask.UpdateListener((Boolean)var6_5, (Boolean)var8_6, var13_9, var4_14, var15_12){
            final /* synthetic */ Boolean a;
            final /* synthetic */ Boolean b;
            final /* synthetic */ Boolean c;
            final /* synthetic */ boolean d;
            final /* synthetic */ String e;
            private boolean g;
            private NetworkResponse h;
            private boolean i;
            private int j;
            {
                this.a = bl;
                this.b = bl2;
                this.c = bl3;
                this.d = bl4;
                this.e = string2;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void a(NetworkResponse networkResponse, Boolean bl, int n, UserUpdateTask.ErrorType errorType) {
                boolean bl2 = true;
                if (!this.a.booleanValue() || this.g) {
                    SettingsFragment settingsFragment = SettingsFragment.this;
                    boolean bl3 = this.b;
                    boolean bl4 = this.a;
                    NetworkResponse networkResponse2 = this.c == false ? NetworkResponse.b() : networkResponse;
                    if (!bl.booleanValue() || !this.c.booleanValue()) {
                        bl2 = false;
                    }
                    if (bl.booleanValue()) {
                        n = 2131297355;
                    }
                    settingsFragment.a(bl3, bl4, networkResponse2, bl2, n, this.h, this.i, this.j, errorType);
                } else {
                    this.g = true;
                    this.h = networkResponse;
                    this.i = bl;
                    this.j = n;
                }
                if (networkResponse.b == 0 && this.d) {
                    if (SettingsFragment.this.ah.getVisibility() != 0) {
                        SettingsFragment.this.ah.setVisibility(0);
                    }
                    SettingsFragment.this.e(this.e);
                    SettingsFragment.this.f(this.e);
                }
            }

            /*
             * Enabled aggressive block sorting
             */
            public void b(NetworkResponse networkResponse, Boolean bl, int n, UserUpdateTask.ErrorType errorType) {
                boolean bl2 = false;
                if (this.b.booleanValue() && !this.g) {
                    this.g = true;
                    this.h = networkResponse;
                    this.i = bl;
                    this.j = n;
                    return;
                }
                SettingsFragment settingsFragment = SettingsFragment.this;
                boolean bl3 = this.b != false || this.c == false;
                boolean bl4 = this.a;
                NetworkResponse networkResponse2 = this.c == false ? NetworkResponse.b() : this.h;
                boolean bl5 = bl2;
                if (this.i) {
                    bl5 = bl2;
                    if (this.c.booleanValue()) {
                        bl5 = true;
                    }
                }
                int n2 = this.c == false ? 2131297355 : this.j;
                settingsFragment.a(bl3, bl4, networkResponse2, bl5, n2, networkResponse, bl, n, errorType);
            }
        };
        Log.b(SettingsFragment.g, "Updating profile");
        var5_4 = new UserUpdateTask((String)var11_3, (String)var5_4, (String)var7_2, null, (UserUpdateTask.UpdateListener)var12_13);
        var7_2 = new UserUpdateTask((String)var10_8, (String)var9_7, (UserUpdateTask.UpdateListener)var12_13);
        this.an.a(new BusyDialog.BusyDialogListener((Boolean)var8_6, (UserUpdateTask)var5_4, (Boolean)var6_5, (UserUpdateTask)var7_2){
            final /* synthetic */ Boolean a;
            final /* synthetic */ UserUpdateTask b;
            final /* synthetic */ Boolean c;
            final /* synthetic */ UserUpdateTask d;
            {
                this.a = bl;
                this.b = userUpdateTask;
                this.c = bl2;
                this.d = userUpdateTask2;
            }

            @Override
            public void a() {
                if (this.a.booleanValue()) {
                    this.b.cancel(true);
                }
                if (this.c.booleanValue()) {
                    this.d.cancel(true);
                }
            }
        });
        if (var8_6.booleanValue()) {
            var5_4.execute((Object[])new Void[0]);
        }
        if (var6_5.booleanValue()) {
            var7_2.execute((Object[])new Void[0]);
        }
        if (var8_6.booleanValue() != false) return;
        if (var6_5.booleanValue() != false) return;
        if (var13_9 != false) return;
        this.a(true, false, NetworkResponse.b(), false, 2131297355, null, true, 0, UserUpdateTask.ErrorType.d);
    }

    @Click
    protected void Q() {
        Log.b(g, "launchAVQualitySurvey");
        SurveyContext surveyContext = new SurveyContext();
        surveyContext.b = SurveyContext.EntryPoint.i;
        SurveyPresenter.a().c(this.getActivity(), surveyContext);
    }

    @UiThread
    protected void R() {
        if (!this.isAdded()) {
            return;
        }
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
        this.h.requestFocus();
    }

    protected void S() {
        if (this.an != null) {
            this.an.dismiss();
            this.an = null;
        }
    }

    public void T() {
        Object object = com.smule.android.network.managers.UserManager.a();
        this.s.setText((CharSequence)"");
        this.u.setText((CharSequence)"");
        String string2 = object.j();
        if (string2 != null) {
            this.q.setText((CharSequence)string2);
            if (this.q.hasFocus()) {
                this.q.setSelection(this.q.getText().length());
            }
        }
        if ((string2 = object.i()) != null) {
            this.o.setText((CharSequence)string2);
            if (this.o.hasFocus()) {
                this.o.setSelection(this.o.getText().length());
            }
        }
        if ((string2 = object.k()) != null) {
            this.w.setText((CharSequence)string2);
            if (this.w.hasFocus()) {
                this.w.setSelection(this.w.getText().length());
            }
        }
        if ((object = object.l()) != null) {
            this.y.setText((CharSequence)object);
            if (this.y.hasFocus()) {
                this.y.setSelection(this.y.getText().length());
            }
        }
    }

    @Click
    protected void U() {
        SingAnalytics.T();
        this.a(LanguagesFragment.a(((BaseActivity)this.getActivity()).c()), LanguagesFragment.g);
    }

    @SupposeUiThread
    protected void a(int n, int n2, int n3, Integer n4) {
        if (this.an == null) {
            Log.e(g, "Trying to call setBusyDialogStatus on a null BusyDialog!");
            return;
        }
        if (n2 == -1) {
            this.an.a(n, this.getString(n3), n4, this.getString(2131296705));
            return;
        }
        this.an.a(n, this.getString(n2), this.getString(n3), n4, this.getString(2131296705));
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void a(NetworkResponse networkResponse, Boolean bl, int n) {
        if (!this.isAdded()) {
            return;
        }
        if (bl.booleanValue()) {
            this.a(1, -1, 2131297363, null);
            NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
        } else {
            void var3_6;
            void var2_4;
            if (networkResponse != null) {
                Integer n2 = networkResponse.h();
            } else {
                Object var2_5 = null;
            }
            if (var3_6 == -1) {
                this.a(2, 2131297372, 2131297372, (Integer)var2_4);
                MagicNetwork.a(networkResponse);
            } else {
                this.a(2, 2131297372, (int)var3_6, (Integer)var2_4);
            }
            this.M.setOnCheckedChangeListener(null);
            this.M.setChecked(com.smule.android.network.managers.UserManager.a().t().equals((Object)EmailOptIn.c));
            this.M.setOnCheckedChangeListener(this.al);
        }
        this.T();
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void a(boolean bl, boolean bl2, NetworkResponse networkResponse, boolean bl3, int n, NetworkResponse networkResponse2, boolean bl4, int n2, UserUpdateTask.ErrorType errorType) {
        int n3 = 2131297372;
        if (!this.isAdded()) {
            this.S();
            return;
        }
        if (bl && bl2) {
            bl = bl3 && bl4;
            if (bl3) {
                n = n2;
            }
            n2 = !bl3 ? n3 : 2131297345;
            if (bl3) {
                networkResponse = networkResponse2;
            }
            this.a(bl, n, n2, networkResponse, errorType);
        } else if (bl) {
            this.a(bl3, n, 2131297372, networkResponse, errorType);
        } else if (bl2) {
            this.a(bl4, n2, 2131297345, networkResponse2, errorType);
        }
        this.W();
        this.T();
    }

    protected void c(final boolean bl) {
        if (bl == SingApplication.k().f()) {
            return;
        }
        SingApplication.k().a(bl, new Completion<ChatStatus>(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(ChatStatus object) {
                if (object != ChatStatus.a && SettingsFragment.this.isAdded()) {
                    SettingsFragment.this.a(new Runnable((ChatStatus)((Object)object)){
                        final /* synthetic */ ChatStatus a;
                        {
                            this.a = chatStatus;
                        }

                        @Override
                        public void run() {
                            SettingsFragment.this.H();
                            ChatUtils.a((Context)SettingsFragment.this.getActivity(), 2131296498, this.a);
                        }
                    });
                    return;
                }
                object = SettingsFragment.this;
                int n = bl ? 2131296492 : 2131296494;
                object.b(n);
                object = bl ? ChatAnalytics.SettingToggleType.b : ChatAnalytics.SettingToggleType.a;
                ChatAnalytics.a((ChatAnalytics.SettingToggleType)object);
            }

        });
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 != -1) {
            Log.e(g, "Bad result code, " + n2 + ", returned for request code: " + n);
            return;
        } else {
            if (n != 32487) return;
            {
                this.al();
                return;
            }
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SingAppboy.a().d();
        this.av();
        this.at();
        this.au();
        this.W();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SingAppboy.a().e();
        this.A.setOnFocusChangeListener(null);
        this.an = null;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        SingAppboy.a().d();
        this.u();
        if (this.A.hasFocus()) {
            MiscUtils.a((Activity)this.getActivity(), (EditText)this.A);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onStart() {
        Log.b(g, "Begin onStart()");
        super.onStart();
        this.V();
        this.c(2131296718);
        this.az = this.getActivity().getSharedPreferences("sing_prefs", 0).getBoolean("facebook.enabled", true);
        if (com.smule.android.facebook.MagicFacebook.a().c()) {
            this.O.setVisibility(4);
            this.P.setVisibility(0);
            com.smule.android.facebook.MagicFacebook.a().a(new MagicFacebook(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void a(MagicFacebook object) {
                    if (!SettingsFragment.this.isAdded()) {
                        return;
                    }
                    SettingsFragment.this.O.setVisibility(0);
                    SettingsFragment.this.P.setVisibility(4);
                    if (object != null && object.a()) {
                        Log.a(SettingsFragment.g, "Connected to Facebook as '" + object.g + "'");
                        TextView textView = SettingsFragment.this.N;
                        object = SettingsFragment.this.az ? object.g : SettingsFragment.this.getString(2131296772);
                        textView.setText((CharSequence)object);
                        SettingsFragment.this.O.setChecked(SettingsFragment.this.az);
                        return;
                    }
                    SettingsFragment.this.O.setChecked(false);
                    Log.a(SettingsFragment.g, "User has not connected to Facebook");
                }

                @Override
                public void b(MagicFacebook facebookUserInfo) {
                    this.a(facebookUserInfo);
                }
            });
        } else {
            this.O.setChecked(false);
            Log.a(g, "User has not connected to Facebook");
        }
        this.al();
        SingAnalytics.b();
    }

    @Override
    public void onStop() {
        super.onStop();
        this.S();
        MiscUtils.a((Activity)this.getActivity(), (boolean)false);
        this.ac();
        if (this.aO) {
            SingApplication.g().unbindService(this.aQ);
            this.aN = false;
            this.aO = false;
        }
        SingApplication.d().a("INIT_FILE_UPLOADER_SERVICE_KEY");
    }

    @UiThread
    protected void t() {
        if (!this.isAdded()) {
            return;
        }
        Intent intent = new Intent(SingApplication.g(), FileUploaderService.class);
        Log.b(g, "Binding service");
        this.aO = SingApplication.g().bindService(intent, this.aQ, 1);
    }

    private static enum ErrorType {
        a,
        b,
        c;
        

        private ErrorType() {
        }
    }

    public static enum FocusField {
        a,
        b;
        

        private FocusField() {
        }
    }

}

