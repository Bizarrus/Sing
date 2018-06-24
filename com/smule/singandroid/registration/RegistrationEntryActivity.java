package com.smule.singandroid.registration;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitActivity.ResponseType;
import com.facebook.accountkit.ui.AccountKitActivity.TitleType;
import com.facebook.accountkit.ui.AccountKitConfiguration.AccountKitConfigurationBuilder;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.google.MagicGoogleSignIn;
import com.smule.android.google.MagicGoogleSignIn.SignInListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.android.logging.Analytics.RegistrationType;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$LoginResponse;
import com.smule.android.network.managers.UserManager$LoginResponseCallback;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.task.AccountKitLoginTask;
import com.smule.singandroid.task.FacebookLoginTask;
import com.smule.singandroid.task.GoogleLoginTask;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.AccountKitFlow;

public class RegistrationEntryActivity extends BaseActivity {
    public static final String f23912g = RegistrationEntryActivity.class.getName();
    public static int f23913h = 4933;
    private static boolean f23914i;
    private static boolean f23915j;
    private boolean f23916k = true;
    private boolean f23917l;
    private boolean f23918m;
    private boolean f23919n = false;
    private Dialog f23920o;
    private OnClickListener f23921p;
    private OnClickListener f23922q;
    private CallbackManager f23923r;
    private MagicGoogleSignIn f23924s;
    private int f23925t;
    private String f23926u;
    private String f23927v;
    private String f23928w;
    private Handler f23929x = new Handler();

    class C48401 implements SignInListener {
        final /* synthetic */ RegistrationEntryActivity f23898a;

        C48401(RegistrationEntryActivity registrationEntryActivity) {
            this.f23898a = registrationEntryActivity;
        }

        public void mo6919a(GoogleSignInAccount googleSignInAccount) {
            Log.b(RegistrationEntryActivity.f23912g, "MagicGoogleSignIn.onConnectSuccess: " + googleSignInAccount.getDisplayName());
            Analytics.m17899f();
            new GoogleLoginTask(this.f23898a, googleSignInAccount, false, false).execute(new Void[0]);
        }

        public void mo6920a(String str) {
            Analytics.m17890b("goog", "goog_error", str, null);
        }
    }

    class C48412 implements FacebookCallback<LoginResult> {
        final /* synthetic */ RegistrationEntryActivity f23899a;

        C48412(RegistrationEntryActivity registrationEntryActivity) {
            this.f23899a = registrationEntryActivity;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            m25164a((LoginResult) obj);
        }

        public void m25164a(LoginResult loginResult) {
            Log.b("Success", "Login");
            Analytics.m17896d();
            this.f23899a.m25184s();
        }

        public void onCancel() {
            Toaster.a(this.f23899a, "Login Cancel", Toaster$Duration.LONG);
        }

        public void onError(FacebookException facebookException) {
            Toaster.a(this.f23899a, facebookException.getMessage(), Toaster$Duration.LONG);
        }
    }

    class C48423 implements OnClickListener {
        final /* synthetic */ RegistrationEntryActivity f23900a;

        C48423(RegistrationEntryActivity registrationEntryActivity) {
            this.f23900a = registrationEntryActivity;
        }

        public void onClick(View view) {
            Log.b(RegistrationEntryActivity.f23912g, "Facebook button clicked");
            Analytics.m17892c();
            LoginManager.getInstance().logInWithReadPermissions(this.f23900a, MagicNetwork.d().getFacebookReadPermissions());
        }
    }

    class C48434 implements OnClickListener {
        final /* synthetic */ RegistrationEntryActivity f23901a;

        C48434(RegistrationEntryActivity registrationEntryActivity) {
            this.f23901a = registrationEntryActivity;
        }

        public void onClick(View view) {
            Log.b(RegistrationEntryActivity.f23912g, "googleButtonView - calling MagicGoogleSignIn - login");
            this.f23901a.f23919n = true;
            Analytics.m17898e();
            this.f23901a.f23924s.m17769a();
        }
    }

    class C48455 implements OnClickListener {
        final /* synthetic */ RegistrationEntryActivity f23903a;

        class C48441 implements Runnable {
            final /* synthetic */ C48455 f23902a;

            C48441(C48455 c48455) {
                this.f23902a = c48455;
            }

            public void run() {
                new AccountKitLoginTask(this.f23902a.f23903a, false).execute(new Void[0]);
            }
        }

        C48455(RegistrationEntryActivity registrationEntryActivity) {
            this.f23903a = registrationEntryActivity;
        }

        public void onClick(View view) {
            Log.b(RegistrationEntryActivity.f23912g, "phoneButtonView - calling AccountKit - login");
            SingAnalytics.m26155s();
            if (AccountKit.getCurrentAccessToken() == null) {
                Intent intent = new Intent(this.f23903a, AccountKitActivity.class);
                intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, new AccountKitConfigurationBuilder(LoginType.PHONE, ResponseType.TOKEN).setAdvancedUIManager(new SmuleAdvancedUIManager()).setTitleType(TitleType.APP_NAME).build());
                this.f23903a.startActivityForResult(intent, RegistrationEntryActivity.f23913h);
                return;
            }
            this.f23903a.f23929x.post(new C48441(this));
        }
    }

    class C48466 implements OnClickListener {
        final /* synthetic */ RegistrationEntryActivity f23904a;

        C48466(RegistrationEntryActivity registrationEntryActivity) {
            this.f23904a = registrationEntryActivity;
        }

        public void onClick(View view) {
            this.f23904a.m25183r();
        }
    }

    class C48477 implements OnClickListener {
        final /* synthetic */ RegistrationEntryActivity f23905a;

        C48477(RegistrationEntryActivity registrationEntryActivity) {
            this.f23905a = registrationEntryActivity;
        }

        public void onClick(View view) {
            this.f23905a.m25176c(null);
        }
    }

    class C48499 implements OnCancelListener {
        final /* synthetic */ RegistrationEntryActivity f23911a;

        C48499(RegistrationEntryActivity registrationEntryActivity) {
            this.f23911a = registrationEntryActivity;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.f23911a.finish();
        }
    }

    public static Intent m25166a(Activity activity, boolean z, boolean z2, String str, String str2, String str3) {
        Intent intent = new Intent(activity, RegistrationEntryActivity.class);
        intent.putExtra("DEVICE_CHECK", true);
        intent.putExtra("DEVICE_FOUND", z);
        intent.putExtra("EMAIL_CHECK", false);
        intent.putExtra("HANDLE", str);
        intent.putExtra("EMAIL", str2);
        intent.putExtra("BACK_ALLOWED", z2);
        intent.putExtra("EXTRA_PIC_URL", str3);
        return intent;
    }

    public static Intent m25165a(Activity activity, String str, String str2, String str3) {
        Intent intent = new Intent(activity, RegistrationEntryActivity.class);
        intent.putExtra("DEVICE_CHECK", false);
        intent.putExtra("EMAIL_CHECK", true);
        intent.putExtra("HANDLE", str);
        intent.putExtra("EMAIL", str2);
        intent.putExtra("EXTRA_PIC_URL", str3);
        return intent;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1947R.layout.registration_entry_activity);
        this.f23924s = new MagicGoogleSignIn(this, getString(C1947R.string.default_web_client_id), new C48401(this));
        this.f23926u = getIntent().getStringExtra("HANDLE");
        this.f23927v = getIntent().getStringExtra("EMAIL");
        this.f23928w = getIntent().getStringExtra("EXTRA_PIC_URL");
        boolean booleanExtra = getIntent().getBooleanExtra("DEVICE_CHECK", true);
        boolean booleanExtra2 = getIntent().getBooleanExtra("EMAIL_CHECK", false);
        boolean booleanExtra3 = getIntent().getBooleanExtra("DEVICE_FOUND", false);
        this.f23917l = getIntent().getBooleanExtra("BACK_ALLOWED", false);
        this.f23918m = true;
        this.f23916k = booleanExtra;
        if (bundle != null) {
            this.f23918m = bundle.getBoolean("IS_THIS_YOU", true);
            this.f23919n = bundle.getBoolean("GPLUS_LOGIN_PRESSED", false);
            f23914i = bundle.getBoolean("GPLUS_LOGIN_PRESSED", false);
            f23915j = bundle.getBoolean("BUNDLE_PARAM_EMAIL_EXPANDED", false);
        } else if (this.f23916k && booleanExtra3) {
            this.f23918m = true;
        } else if (this.f23916k) {
            this.f23918m = false;
        } else if (booleanExtra2) {
            this.f23918m = true;
        } else {
            Log.e(f23912g, "unexpected state!!");
        }
        if (!f23914i) {
            String installerPackageName;
            PackageManager packageManager = getPackageManager();
            if (packageManager != null) {
                installerPackageName = packageManager.getInstallerPackageName(getPackageName());
            } else {
                installerPackageName = null;
            }
            Analytics.m17880a(this.f23918m, installerPackageName);
            f23914i = true;
        }
    }

    private void m25174b() {
        Analytics.m17903j();
        this.f23918m = false;
        this.f23920o = new Dialog(this, C1947R.style.MagicModal);
        View inflate = getLayoutInflater().inflate(C1947R.layout.sign_in_options_dialog, null, false);
        this.f23920o.setContentView(inflate);
        this.f23920o.setCancelable(this.f23917l);
        this.f23923r = Factory.create();
        LoginManager.getInstance().registerCallback(this.f23923r, new C48412(this));
        TextView textView = (TextView) inflate.findViewById(C1947R.id.facebook_button_view);
        m25167a(textView);
        textView.setOnClickListener(new C48423(this));
        textView = (TextView) inflate.findViewById(C1947R.id.google_button_view);
        m25167a(textView);
        if (this.f23925t == 0) {
            textView.setOnClickListener(new C48434(this));
        } else {
            textView.setEnabled(false);
        }
        textView = (TextView) inflate.findViewById(C1947R.id.phone_button_view);
        m25167a(textView);
        textView.setOnClickListener(new C48455(this));
        inflate.findViewById(C1947R.id.register_button).setOnClickListener(new C48466(this));
        inflate.findViewById(C1947R.id.login_button).setOnClickListener(new C48477(this));
        View findViewById = inflate.findViewById(C1947R.id.buttonDivider);
        ObjectAnimator duration = ObjectAnimator.ofFloat(findViewById, "alpha", new float[]{0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}).setDuration(250);
        View findViewById2 = inflate.findViewById(C1947R.id.email_buttons_container);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(findViewById2, "translationY", new float[]{getResources().getDimension(C1947R.dimen.registration_email_translation), 0.0f}).setDuration(250);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{duration, duration2});
        final View findViewById3 = inflate.findViewById(C1947R.id.email_login_container);
        final View findViewById4 = inflate.findViewById(C1947R.id.connect_with_email);
        final TextView textView2 = (TextView) inflate.findViewById(C1947R.id.disclaimer);
        textView2.setText(MiscUtils.m25877a((Activity) this, getString(C1947R.string.login_sign_up_terms)));
        textView2.setLinkTextColor(getResources().getColor(C1947R.color.white));
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView2.setHighlightColor(0);
        if (f23915j) {
            findViewById4.setVisibility(8);
            textView2.setVisibility(8);
            findViewById3.setVisibility(0);
            findViewById.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            findViewById2.setTranslationY(0.0f);
        } else {
            findViewById4.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RegistrationEntryActivity f23910e;

                public void onClick(View view) {
                    SingAnalytics.m26154r();
                    findViewById4.setVisibility(8);
                    textView2.setVisibility(8);
                    findViewById3.setVisibility(0);
                    animatorSet.start();
                    RegistrationEntryActivity.f23915j = true;
                }
            });
        }
        this.f23920o.setOnCancelListener(new C48499(this));
        this.f23920o.show();
    }

    private void m25167a(TextView textView) {
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        if (compoundDrawables.length >= 1) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), compoundDrawables[0].getIntrinsicWidth() + textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    private void m25170a(String str, final String str2, String str3) {
        Analytics.m17846a(RegistrationType.EMAIL);
        this.f23921p = new OnClickListener(this) {
            final /* synthetic */ RegistrationEntryActivity f23893b;

            public void onClick(View view) {
                SingAnalytics.m26139c(true);
                if (this.f23893b.f23916k) {
                    final BusyDialog busyDialog = new BusyDialog(this.f23893b, this.f23893b.getResources().getString(C1947R.string.login_));
                    busyDialog.show();
                    UserManager.a().a(new UserManager$LoginResponseCallback(this) {
                        final /* synthetic */ AnonymousClass10 f23891b;

                        public void handleResponse(UserManager$LoginResponse userManager$LoginResponse) {
                            if (userManager$LoginResponse.a.a != NetworkResponse$Status.OK) {
                                busyDialog.m23576a(2, this.f23891b.f23893b.getResources().getString(C1947R.string.login_cannot_connect_to_smule), true);
                            } else if (userManager$LoginResponse.a()) {
                                if (this.f23891b.f23893b.f23920o != null) {
                                    this.f23891b.f23893b.f23920o.dismiss();
                                }
                                busyDialog.dismiss();
                                RegistrationContext.d(userManager$LoginResponse.f17333p.booleanValue());
                                RegistrationContext.b();
                                RegistrationContext.a(this.f23891b.f23893b, true, RegistrationFlow.DEVICE_FOUND);
                            } else {
                                busyDialog.m23576a(2, this.f23891b.f23893b.getResources().getString(C1947R.string.login_error_with_servers), true);
                                MagicNetwork.a(userManager$LoginResponse.a);
                            }
                        }
                    });
                    return;
                }
                this.f23893b.m25176c(str2);
                this.f23893b.f23920o.dismiss();
            }
        };
        this.f23922q = new OnClickListener(this) {
            final /* synthetic */ RegistrationEntryActivity f23894a;

            {
                this.f23894a = r1;
            }

            public void onClick(View view) {
                this.f23894a.m25182q();
            }
        };
        this.f23920o = NavigationUtils.m25904a((Context) this, str, str2, str3, this.f23921p, this.f23922q);
        this.f23920o.setCancelable(true);
        this.f23920o.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ RegistrationEntryActivity f23895a;

            {
                this.f23895a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.f23895a.m25182q();
            }
        });
        ((RelativeLayout) this.f23920o.findViewById(C1947R.id.root)).setOnClickListener(this.f23922q);
    }

    private void m25182q() {
        SingAnalytics.m26139c(false);
        this.f23920o.dismiss();
        if (this.f23916k) {
            this.f23920o.dismiss();
            m25174b();
            return;
        }
        m25183r();
    }

    private void m25176c(String str) {
        Intent intent = new Intent(this, LoginActivity.class);
        if (str != null) {
            intent.putExtra("param_email", str);
        }
        startActivity(intent);
        finish();
    }

    private void m25183r() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    protected void onStart() {
        int i;
        super.onStart();
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (instance == null) {
            i = 1;
        } else {
            i = instance.isGooglePlayServicesAvailable(this);
        }
        this.f23925t = i;
        if (this.f23918m) {
            m25170a(this.f23926u, this.f23927v, this.f23928w);
        } else {
            m25174b();
        }
        SingAnalytics.m26152p();
    }

    public void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
        if (this.f23920o != null) {
            this.f23920o.dismiss();
            this.f23920o = null;
        }
    }

    private void m25184s() {
        this.f23929x.post(new Runnable(this) {
            final /* synthetic */ RegistrationEntryActivity f23896a;

            {
                this.f23896a = r1;
            }

            public void run() {
                new FacebookLoginTask(this.f23896a, Boolean.valueOf(false)).execute(new Void[0]);
            }
        });
    }

    public void onDestroy() {
        if (this.f23920o != null) {
            this.f23920o.dismiss();
            this.f23920o = null;
        }
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("IS_THIS_YOU", this.f23918m);
        bundle.putBoolean("GPLUS_LOGIN_PRESSED", this.f23919n);
        bundle.putBoolean("GPLUS_LOGIN_PRESSED", f23914i);
        bundle.putBoolean("BUNDLE_PARAM_EMAIL_EXPANDED", f23915j);
    }

    public void onBackPressed() {
        Log.c(f23912g, "Back pressed!");
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.f23924s != null && this.f23924s.m17770a(i, i2, intent)) {
            Log.b(f23912g, "onActivityResult - result handled by Google Plus");
        } else if (i == f23913h) {
            AccountKitLoginResult accountKitLoginResult = (AccountKitLoginResult) intent.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (accountKitLoginResult.getError() != null) {
                BusyDialog busyDialog = new BusyDialog((Activity) this, getResources().getString(C1947R.string.login_));
                busyDialog.m23576a(2, getResources().getString(C1947R.string.login_error_with_servers), true);
                busyDialog.show();
                AccountKitError error = accountKitLoginResult.getError();
                Analytics.m17890b(RegistrationFlow.PHONE.mo6235a(), "acctkit_error", error.getErrorType().name().toLowerCase(), "" + error.getDetailErrorCode());
            } else if (!accountKitLoginResult.wasCancelled()) {
                SingAnalytics.m26076a(AccountKitFlow.PHONE);
                this.f23929x.post(new Runnable(this) {
                    final /* synthetic */ RegistrationEntryActivity f23897a;

                    {
                        this.f23897a = r1;
                    }

                    public void run() {
                        new AccountKitLoginTask(this.f23897a, false).execute(new Void[0]);
                    }
                });
            }
        } else if (this.f23923r != null) {
            this.f23923r.onActivityResult(i, i2, intent);
        }
    }

    public static boolean m25171a() {
        return f23914i;
    }
}
