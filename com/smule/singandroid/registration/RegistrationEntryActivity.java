/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorSet
 *  android.animation.ObjectAnimator
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Parcelable
 *  android.support.v7.app.AppCompatActivity
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.facebook.CallbackManager
 *  com.facebook.CallbackManager$Factory
 *  com.facebook.FacebookCallback
 *  com.facebook.FacebookException
 *  com.facebook.accountkit.AccountKit
 *  com.facebook.accountkit.AccountKitError
 *  com.facebook.accountkit.AccountKitError$Type
 *  com.facebook.accountkit.AccountKitLoginResult
 *  com.facebook.accountkit.ui.AccountKitActivity
 *  com.facebook.accountkit.ui.AccountKitActivity$ResponseType
 *  com.facebook.accountkit.ui.AccountKitActivity$TitleType
 *  com.facebook.accountkit.ui.AccountKitConfiguration
 *  com.facebook.accountkit.ui.AccountKitConfiguration$AccountKitConfigurationBuilder
 *  com.facebook.accountkit.ui.AdvancedUIManager
 *  com.facebook.accountkit.ui.LoginType
 *  com.facebook.login.LoginManager
 *  com.facebook.login.LoginResult
 *  com.google.android.gms.auth.api.signin.GoogleSignInAccount
 *  com.google.android.gms.common.GoogleApiAvailability
 *  com.smule.singandroid.task.AccountKitLoginTask
 *  com.smule.singandroid.task.FacebookLoginTask
 *  com.smule.singandroid.task.GoogleLoginTask
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$AccountKitFlow
 */
package com.smule.singandroid.registration;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.AdvancedUIManager;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.GoogleApiAvailability;
import com.smule.android.google.MagicGoogleSignIn;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.StringUtils;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.common.snackbar.BottomSnackbar;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.LanguagesDialog;
import com.smule.singandroid.registration.LoginActivity;
import com.smule.singandroid.registration.RegisterActivity;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.registration.SmuleAdvancedUIManager;
import com.smule.singandroid.task.AccountKitLoginTask;
import com.smule.singandroid.task.FacebookLoginTask;
import com.smule.singandroid.task.GoogleLoginTask;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

public class RegistrationEntryActivity
extends BaseActivity {
    public static final String g = RegistrationEntryActivity.class.getName();
    public static int h = 4933;
    private static boolean i;
    private static boolean j;
    private boolean k = true;
    private boolean l;
    private boolean m;
    private boolean n = false;
    private Dialog o;
    private View.OnClickListener p;
    private View.OnClickListener q;
    private CallbackManager r;
    private MagicGoogleSignIn s;
    private int t;
    private String u;
    private String v;
    private String w;
    private Handler x = new Handler();

    public static Intent a(Activity activity, String string2, String string3, String string4) {
        activity = new Intent((Context)activity, RegistrationEntryActivity.class);
        activity.putExtra("DEVICE_CHECK", false);
        activity.putExtra("EMAIL_CHECK", true);
        activity.putExtra("HANDLE", string2);
        activity.putExtra("EMAIL", string3);
        activity.putExtra("EXTRA_PIC_URL", string4);
        return activity;
    }

    public static Intent a(Activity activity, boolean bl, boolean bl2, String string2, String string3, String string4) {
        activity = new Intent((Context)activity, RegistrationEntryActivity.class);
        activity.putExtra("DEVICE_CHECK", true);
        activity.putExtra("DEVICE_FOUND", bl);
        activity.putExtra("EMAIL_CHECK", false);
        activity.putExtra("HANDLE", string2);
        activity.putExtra("EMAIL", string3);
        activity.putExtra("BACK_ALLOWED", bl2);
        activity.putExtra("EXTRA_PIC_URL", string4);
        return activity;
    }

    private void a(TextView textView) {
        Drawable[] arrdrawable = textView.getCompoundDrawables();
        if (arrdrawable.length < 1) {
            return;
        }
        int n = arrdrawable[0].getIntrinsicWidth();
        textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), n + textView.getPaddingRight(), textView.getPaddingBottom());
    }

    private void a(String string2, final String string3, String string4) {
        SingAnalytics.a(Analytics.a);
        this.p = new View.OnClickListener(){

            public void onClick(View object) {
                SingAnalytics.d((boolean)true);
                if (!RegistrationEntryActivity.this.k) {
                    RegistrationEntryActivity.this.c(string3);
                    RegistrationEntryActivity.this.o.dismiss();
                    return;
                }
                object = new BusyDialog((Activity)RegistrationEntryActivity.this, RegistrationEntryActivity.this.getResources().getString(2131296892));
                object.show();
                com.smule.android.network.managers.UserManager.a().a(new UserManager((BusyDialog)((Object)object)){
                    final /* synthetic */ BusyDialog a;
                    {
                        this.a = busyDialog;
                    }

                    @Override
                    public void handleResponse(UserManager loginResponse) {
                        if (loginResponse.a.a != NetworkResponse.a) {
                            this.a.a(2, RegistrationEntryActivity.this.getResources().getString(2131296895), null);
                            return;
                        }
                        if (loginResponse.a()) {
                            if (RegistrationEntryActivity.this.o != null) {
                                RegistrationEntryActivity.this.o.dismiss();
                            }
                            this.a.dismiss();
                            RegistrationContext.d(loginResponse.o);
                            RegistrationContext.b();
                            RegistrationContext.a((Activity)RegistrationEntryActivity.this, true, Analytics.c);
                            return;
                        }
                        this.a.a(2, RegistrationEntryActivity.this.getResources().getString(2131296908), loginResponse.a.h());
                        MagicNetwork.a(loginResponse.a);
                    }
                });
            }

        };
        this.q = new View.OnClickListener(){

            public void onClick(View view) {
                RegistrationEntryActivity.this.t();
            }
        };
        this.o = NavigationUtils.a((Context)this, (String)string2, (String)string3, (String)string4, (View.OnClickListener)this.p, (View.OnClickListener)this.q);
        this.o.setCancelable(true);
        this.o.setOnCancelListener(new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                RegistrationEntryActivity.this.t();
            }
        });
        ((RelativeLayout)this.o.findViewById(2131755221)).setOnClickListener(this.q);
    }

    public static boolean a() {
        return i;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b() {
        SingAnalytics.k();
        this.m = false;
        this.o = new Dialog((Context)this, 2131493244);
        Object object = this.getLayoutInflater().inflate(2130903407, null, false);
        this.o.setContentView((View)object);
        this.o.setCancelable(this.l);
        this.r = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(this.r, (FacebookCallback)new FacebookCallback<LoginResult>(){

            public void a(LoginResult loginResult) {
                Log.b("Success", "Login");
                SingAnalytics.e();
                RegistrationEntryActivity.this.v();
            }

            public void onCancel() {
                com.smule.android.utils.Toaster.a((Context)RegistrationEntryActivity.this, "Login Cancel", Toaster.b);
            }

            public void onError(FacebookException facebookException) {
                com.smule.android.utils.Toaster.a((Context)RegistrationEntryActivity.this, facebookException.getMessage(), Toaster.b);
            }

            public /* synthetic */ void onSuccess(Object object) {
                this.a((LoginResult)object);
            }
        });
        TextView textView = (TextView)object.findViewById(2131756586);
        this.a(textView);
        textView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Log.b(RegistrationEntryActivity.g, "Facebook button clicked");
                SingAnalytics.d();
                LoginManager.getInstance().logInWithReadPermissions((Activity)RegistrationEntryActivity.this, MagicNetwork.d().getFacebookReadPermissions());
            }
        });
        textView = (TextView)object.findViewById(2131756587);
        this.a(textView);
        if (this.t == 0) {
            textView.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    Log.b(RegistrationEntryActivity.g, "googleButtonView - calling MagicGoogleSignIn - login");
                    RegistrationEntryActivity.this.n = true;
                    com.smule.android.logging.Analytics.f();
                    RegistrationEntryActivity.this.s.a();
                }
            });
        } else {
            textView.setEnabled(false);
        }
        textView = (TextView)object.findViewById(2131756588);
        this.a(textView);
        textView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Log.b(RegistrationEntryActivity.g, "phoneButtonView - calling AccountKit - login");
                SingAnalytics.x();
                if (AccountKit.getCurrentAccessToken() == null) {
                    view = new Intent((Context)RegistrationEntryActivity.this, AccountKitActivity.class);
                    AccountKitConfiguration.AccountKitConfigurationBuilder accountKitConfigurationBuilder = new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN).setAdvancedUIManager((AdvancedUIManager)new SmuleAdvancedUIManager()).setTitleType(AccountKitActivity.TitleType.APP_NAME);
                    view.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, (Parcelable)accountKitConfigurationBuilder.build());
                    RegistrationEntryActivity.this.startActivityForResult((Intent)view, RegistrationEntryActivity.h);
                    return;
                }
                RegistrationEntryActivity.this.x.post(new Runnable(){

                    @Override
                    public void run() {
                        new AccountKitLoginTask((Activity)RegistrationEntryActivity.this, false).execute((Object[])new Void[0]);
                    }
                });
            }

        });
        object.findViewById(2131756596).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                RegistrationEntryActivity.this.u();
            }
        });
        object.findViewById(2131755940).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                RegistrationEntryActivity.this.c(null);
            }
        });
        textView = object.findViewById(2131756591);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat((Object)textView, (String)"alpha", (float[])new float[]{0.0f, 1.0f}).setDuration(250);
        View view = object.findViewById(2131756595);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat((Object)view, (String)"translationY", (float[])new float[]{this.getResources().getDimension(2131427779), 0.0f}).setDuration(250);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{objectAnimator, objectAnimator2});
        objectAnimator = object.findViewById(2131756590);
        objectAnimator2 = object.findViewById(2131756597);
        TextView textView2 = (TextView)object.findViewById(2131756585);
        String string2 = "href=\"" + com.smule.android.network.managers.UserManager.a().W() + "\"";
        textView2.setText((CharSequence)MiscUtils.a((Activity)this, (String)this.getString(2131296936, new Object[]{"href=\"" + com.smule.android.network.managers.UserManager.a().X() + "\"", string2})));
        textView2.setLinkTextColor(this.getResources().getColor(2131689991));
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView2.setHighlightColor(0);
        if (j) {
            objectAnimator2.setVisibility(8);
            objectAnimator.setVisibility(0);
            textView.setAlpha(1.0f);
            view.setTranslationY(0.0f);
        } else {
            objectAnimator2.setOnClickListener(new View.OnClickListener((View)objectAnimator2, (View)objectAnimator, animatorSet){
                final /* synthetic */ View a;
                final /* synthetic */ View b;
                final /* synthetic */ AnimatorSet c;
                {
                    this.a = view;
                    this.b = view2;
                    this.c = animatorSet;
                }

                public void onClick(View view) {
                    SingAnalytics.w();
                    this.a.setVisibility(8);
                    this.b.setVisibility(0);
                    this.c.start();
                    j = true;
                }
            });
        }
        this.o.setOnCancelListener(new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                RegistrationEntryActivity.this.finish();
            }
        });
        if (new SingServerValues().ao()) {
            object = BottomSnackbar.a((ViewGroup)object, this.getString(2131296943, new Object[]{StringUtils.a(this.c().getDisplayLanguage(this.c()))}), -2);
            object.a(2131296921, new View.OnClickListener((BottomSnackbar)((Object)object)){
                final /* synthetic */ BottomSnackbar a;
                {
                    this.a = bottomSnackbar;
                }

                public void onClick(View object) {
                    SingAnalytics.U();
                    object = new LanguagesDialog((Activity)RegistrationEntryActivity.this, RegistrationEntryActivity.this.c());
                    object.b(new Runnable(){

                        @Override
                        public void run() {
                            10.this.a.show();
                        }
                    });
                    object.show();
                    this.a.show();
                }

            });
            this.a(new Runnable((BottomSnackbar)((Object)object)){
                final /* synthetic */ BottomSnackbar a;
                {
                    this.a = bottomSnackbar;
                }

                @Override
                public void run() {
                    this.a.show();
                }
            }, 200);
        }
        this.o.show();
    }

    private void c(String string2) {
        Intent intent = new Intent((Context)this, LoginActivity.class);
        if (string2 != null) {
            intent.putExtra("param_email", string2);
        }
        this.startActivity(intent);
        this.finish();
    }

    private void t() {
        SingAnalytics.d((boolean)false);
        this.o.dismiss();
        if (this.k) {
            this.o.dismiss();
            this.b();
            return;
        }
        this.u();
    }

    private void u() {
        this.startActivity(new Intent((Context)this, RegisterActivity.class));
        this.finish();
    }

    private void v() {
        this.x.post(new Runnable(){

            @Override
            public void run() {
                new FacebookLoginTask((Activity)RegistrationEntryActivity.this, Boolean.valueOf(false)).execute((Object[])new Void[0]);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (this.s != null && this.s.a(n, n2, intent)) {
            Log.b(g, "onActivityResult - result handled by Google Plus");
            return;
        } else if (n == h) {
            if ((intent = (AccountKitLoginResult)intent.getParcelableExtra("account_kit_log_in_result")).getError() != null) {
                BusyDialog busyDialog = new BusyDialog((Activity)this, this.getResources().getString(2131296892));
                busyDialog.a(2, this.getResources().getString(2131296908), null);
                busyDialog.show();
                intent = intent.getError();
                com.smule.android.logging.Analytics.b(Analytics.f.a(), "acctkit_error", intent.getErrorType().name().toLowerCase(), "" + intent.getDetailErrorCode());
                return;
            }
            if (intent.wasCancelled()) return;
            {
                SingAnalytics.a((SingAnalytics.AccountKitFlow)SingAnalytics.AccountKitFlow.a);
                this.x.post(new Runnable(){

                    @Override
                    public void run() {
                        new AccountKitLoginTask((Activity)RegistrationEntryActivity.this, false).execute((Object[])new Void[0]);
                    }
                });
                return;
            }
        } else {
            if (this.r == null) return;
            {
                this.r.onActivityResult(n, n2, intent);
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Log.c(g, "Back pressed!");
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.setContentView(2130903388);
        this.s = new MagicGoogleSignIn(this, this.getString(2131297706), new MagicGoogleSignIn.SignInListener(){

            @Override
            public void a(GoogleSignInAccount googleSignInAccount) {
                Log.b(RegistrationEntryActivity.g, "MagicGoogleSignIn.onConnectSuccess: " + googleSignInAccount.getDisplayName());
                com.smule.android.logging.Analytics.g();
                new GoogleLoginTask((Activity)RegistrationEntryActivity.this, googleSignInAccount, false, false).execute((Object[])new Void[0]);
            }

            @Override
            public void a(String string2) {
                com.smule.android.logging.Analytics.b("goog", "goog_error", string2, null);
            }
        });
        this.u = this.getIntent().getStringExtra("HANDLE");
        this.v = this.getIntent().getStringExtra("EMAIL");
        this.w = this.getIntent().getStringExtra("EXTRA_PIC_URL");
        boolean bl = this.getIntent().getBooleanExtra("DEVICE_CHECK", true);
        boolean bl2 = this.getIntent().getBooleanExtra("EMAIL_CHECK", false);
        boolean bl3 = this.getIntent().getBooleanExtra("DEVICE_FOUND", false);
        this.l = this.getIntent().getBooleanExtra("BACK_ALLOWED", false);
        this.m = true;
        this.k = bl;
        if (object != null) {
            this.m = object.getBoolean("IS_THIS_YOU", true);
            this.n = object.getBoolean("GPLUS_LOGIN_PRESSED", false);
            i = object.getBoolean("GPLUS_LOGIN_PRESSED", false);
            j = object.getBoolean("BUNDLE_PARAM_EMAIL_EXPANDED", false);
        } else if (this.k && bl3) {
            this.m = true;
        } else if (this.k) {
            this.m = false;
        } else if (bl2) {
            this.m = true;
        } else {
            Log.e(g, "unexpected state!!");
        }
        if (!i) {
            object = this.getPackageManager();
            object = object != null ? object.getInstallerPackageName(this.getPackageName()) : null;
            com.smule.android.logging.Analytics.b(this.m, (String)object);
            i = true;
        }
    }

    @Override
    public void onDestroy() {
        if (this.o != null) {
            this.o.dismiss();
            this.o = null;
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("IS_THIS_YOU", this.m);
        bundle.putBoolean("GPLUS_LOGIN_PRESSED", this.n);
        bundle.putBoolean("GPLUS_LOGIN_PRESSED", i);
        bundle.putBoolean("BUNDLE_PARAM_EMAIL_EXPANDED", j);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onStart() {
        super.onStart();
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int n = googleApiAvailability == null ? 1 : googleApiAvailability.isGooglePlayServicesAvailable((Context)this);
        this.t = n;
        if (this.m) {
            this.a(this.u, this.v, this.w);
        } else {
            this.b();
        }
        SingAnalytics.a((BottomNavView.Tab)null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.o != null) {
            this.o.dismiss();
            this.o = null;
        }
    }

}

