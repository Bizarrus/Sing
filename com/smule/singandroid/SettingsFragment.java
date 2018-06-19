package com.smule.singandroid;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.ToggleButton;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.debug.DiagnosticActivity;
import com.smule.android.debug.DiagnosticTapRecognizer;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.facebook.MagicFacebook$FacebookUserInfo;
import com.smule.android.facebook.MagicFacebook$FacebookUserInfoListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$GetUserBlurbResponseCallback;
import com.smule.android.network.managers.UserManager$UpdateUserBlurbResponseCallback;
import com.smule.android.network.managers.UserManager.UserBlurbResponse;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.twitter.MagicTwitter.TwitterAccountCallback;
import com.smule.android.utils.EmailOptIn;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.WeakListener;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.singandroid.chat.BlockedUsersFragment;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatAnalytics.SettingToggleType;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyDialog.BusyDialogListener;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.survey.SurveyContext;
import com.smule.singandroid.survey.SurveyContext.EntryPoint;
import com.smule.singandroid.survey.SurveyPresenter;
import com.smule.singandroid.task.UserUpdateTask;
import com.smule.singandroid.task.UserUpdateTask.UpdateListener;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.AccountSettings;

@EFragment
public class SettingsFragment extends PhotoTakingFragment {
    public static final String f19809e = SettingsFragment.class.getName();
    @ViewById
    protected ProgressBar f19810A;
    @ViewById
    protected ViewGroup f19811B;
    @ViewById
    protected TextView f19812C;
    @ViewById
    protected ToggleButton f19813D;
    @ViewById
    protected View f19814E;
    @ViewById
    protected ToggleButton f19815F;
    @ViewById
    protected ToggleButton f19816G;
    @ViewById
    ProfileImageWithVIPBadge f19817H;
    @ViewById
    protected View f19818I;
    @ViewById
    protected ToggleButton f19819J;
    @ViewById
    protected TextView f19820K;
    @ViewById
    protected TextView f19821L;
    @ViewById
    protected View f19822M;
    OnCheckedChangeListener f19823N = new C40714(this);
    OnCheckedChangeListener f19824O = new C40768(this);
    private BusyDialog f19825P;
    private OnFocusChangeListener f19826Q;
    private String f19827R;
    private Boolean f19828S = Boolean.valueOf(false);
    private String f19829T = "";
    private String f19830U = "";
    private String f19831V = "";
    private String f19832W = "";
    private String f19833X = "";
    private String f19834Y = "";
    private EmailOptIn f19835Z;
    private boolean aa = false;
    private boolean ab;
    private DiagnosticTapRecognizer ac;
    private Animator ad;
    private Animator ae;
    private Animator af;
    private Animator ag;
    private Animator ah;
    private Animator ai;
    private Animator aj;
    private Animator ak;
    private Animator al;
    private Animator am;
    private OnCheckedChangeListener an = new C40725(this);
    private OnCheckedChangeListener ao = new C40736(this);
    @ViewById
    LinearLayout f19836f;
    @ViewById
    protected EditText f19837g;
    @ViewById
    protected EditText f19838h;
    @ViewById
    protected EditText f19839i;
    @ViewById
    protected EditText f19840j;
    @ViewById
    protected EditText f19841k;
    @ViewById
    protected EditText f19842l;
    @ViewById
    protected TextView f19843m;
    @ViewById
    protected EditText f19844n;
    @ViewById
    protected EditText f19845o;
    @ViewById
    protected EditText f19846p;
    @ViewById
    protected View f19847q;
    @ViewById
    protected View f19848r;
    @ViewById
    protected View f19849s;
    @ViewById
    protected View f19850t;
    @ViewById
    protected View f19851u;
    @ViewById
    protected View f19852v;
    @ViewById
    protected ToggleButton f19853w;
    @ViewById
    protected TextView f19854x;
    @ViewById
    protected TextView f19855y;
    @ViewById
    protected ToggleButton f19856z;

    class C40661 implements UserManager$GetUserBlurbResponseCallback {
        final /* synthetic */ SettingsFragment f19780a;

        C40661(SettingsFragment settingsFragment) {
            this.f19780a = settingsFragment;
        }

        public void handleResponse(UserBlurbResponse userBlurbResponse) {
            if (!this.f19780a.isAdded()) {
                return;
            }
            if (userBlurbResponse.a.c()) {
                this.f19780a.f19834Y = userBlurbResponse.mBlurb;
                if (this.f19780a.f19834Y != null && this.f19780a.f19834Y.trim().length() > 0) {
                    this.f19780a.f19844n.setText(this.f19780a.f19834Y.trim());
                    return;
                }
                return;
            }
            Log.e(SettingsFragment.f19809e, "errorGettingBlurb - called in updateFollowingViewBinding");
        }
    }

    class C40682 implements Runnable {
        final /* synthetic */ SettingsFragment f19793a;

        C40682(SettingsFragment settingsFragment) {
            this.f19793a = settingsFragment;
        }

        public void run() {
            this.f19793a.startActivity(new Intent(SingApplication.f(), DiagnosticActivity.class));
        }
    }

    class C40693 implements MagicFacebook$FacebookUserInfoListener {
        final /* synthetic */ SettingsFragment f19794a;

        C40693(SettingsFragment settingsFragment) {
            this.f19794a = settingsFragment;
        }

        public void mo6410a(MagicFacebook$FacebookUserInfo magicFacebook$FacebookUserInfo) {
            if (this.f19794a.isAdded()) {
                this.f19794a.f19856z.setVisibility(0);
                this.f19794a.f19810A.setVisibility(4);
                if (magicFacebook$FacebookUserInfo == null || !magicFacebook$FacebookUserInfo.m17763a()) {
                    this.f19794a.f19856z.setChecked(false);
                    Log.a(SettingsFragment.f19809e, "User has not connected to Facebook");
                    return;
                }
                Log.a(SettingsFragment.f19809e, "Connected to Facebook as '" + magicFacebook$FacebookUserInfo.f15985g + "'");
                this.f19794a.f19855y.setText(this.f19794a.ab ? magicFacebook$FacebookUserInfo.f15985g : this.f19794a.getString(C1947R.string.facebook));
                this.f19794a.f19856z.setChecked(this.f19794a.ab);
            }
        }

        public void mo6411b(MagicFacebook$FacebookUserInfo magicFacebook$FacebookUserInfo) {
            mo6410a(magicFacebook$FacebookUserInfo);
        }
    }

    class C40714 implements OnCheckedChangeListener {
        final /* synthetic */ SettingsFragment f19796a;

        class C40701 implements UpdateListener {
            final /* synthetic */ C40714 f19795a;

            C40701(C40714 c40714) {
                this.f19795a = c40714;
            }

            public void mo6597a(NetworkResponse networkResponse, Boolean bool, int i) {
                if (bool.booleanValue()) {
                    if (this.f19795a.f19796a.f19835Z.equals(EmailOptIn.NO)) {
                        this.f19795a.f19796a.m19846b((int) C1947R.string.settings_newsletter_unsubscribe);
                    } else {
                        this.f19795a.f19796a.m19846b((int) C1947R.string.settings_newsletter_subscribe);
                    }
                }
                this.f19795a.f19796a.mo6603a(networkResponse, bool, i);
            }

            public void mo6598b(NetworkResponse networkResponse, Boolean bool, int i) {
            }
        }

        C40714(SettingsFragment settingsFragment) {
            this.f19796a = settingsFragment;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!this.f19796a.isAdded()) {
                return;
            }
            if (this.f19796a.f19825P == null || !this.f19796a.f19825P.isShowing()) {
                if (z) {
                    this.f19796a.f19835Z = EmailOptIn.YES;
                } else {
                    this.f19796a.f19835Z = EmailOptIn.NO;
                }
                this.f19796a.f19825P = new BusyDialog(this.f19796a.getActivity(), this.f19796a.getResources().getString(C1947R.string.settings_update_user_profile));
                this.f19796a.f19825P.show();
                new UserUpdateTask(UserManager.a().i(), UserManager.a().j(), UserManager.a().m(), this.f19796a.f19835Z, new C40701(this)).execute(new Void[0]);
                return;
            }
            this.f19796a.f19853w.setChecked(this.f19796a.f19835Z.equals(EmailOptIn.YES));
        }
    }

    class C40725 implements OnCheckedChangeListener {
        final /* synthetic */ SettingsFragment f19797a;

        C40725(SettingsFragment settingsFragment) {
            this.f19797a = settingsFragment;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f19797a.m21414c(!z);
        }
    }

    class C40736 implements OnCheckedChangeListener {
        final /* synthetic */ SettingsFragment f19798a;

        C40736(SettingsFragment settingsFragment) {
            this.f19798a = settingsFragment;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f19798a.m21390d(z);
        }
    }

    class C40768 implements OnCheckedChangeListener {
        final /* synthetic */ SettingsFragment f19803a;

        C40768(SettingsFragment settingsFragment) {
            this.f19803a = settingsFragment;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.f19803a.isAdded()) {
                SingApplication.f().getSharedPreferences("sing_prefs", 0).edit().putBoolean("SAMSUNG_RTM_ENABLED_IN_SETTINGS", z).apply();
            }
        }
    }

    class C40779 implements FacebookCallback<LoginResult> {
        final /* synthetic */ SettingsFragment f19804a;

        C40779(SettingsFragment settingsFragment) {
            this.f19804a = settingsFragment;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            m21367a((LoginResult) obj);
        }

        public void m21367a(LoginResult loginResult) {
            if (this.f19804a.isAdded()) {
                Log.b(SettingsFragment.f19809e, "onSuccess called; session state is open: " + loginResult);
                MagicFacebook.a().f();
                this.f19804a.m21371P();
            }
        }

        public void onCancel() {
            Log.b(SettingsFragment.f19809e, "onCancel.");
            if (this.f19804a.isAdded()) {
                this.f19804a.f19855y.setText(C1947R.string.facebook);
                this.f19804a.f19856z.setChecked(false);
            }
        }

        public void onError(FacebookException facebookException) {
            Log.b(SettingsFragment.f19809e, "onError.");
            if (this.f19804a.isAdded()) {
                this.f19804a.f19855y.setText(C1947R.string.facebook);
                this.f19804a.f19856z.setChecked(false);
            }
        }
    }

    private enum ErrorType {
        EMPTY_EMAIL,
        EMPTY_USERNAME,
        INVALID_PASSWORD_LENGTH
    }

    public static SettingsFragment m21376a() {
        return m21385b(false);
    }

    public static SettingsFragment m21385b(boolean z) {
        SettingsFragment settingsFragment_ = new SettingsFragment_();
        Bundle bundle = new Bundle();
        bundle.putBoolean("SETTINGS_GOTO_BLURB", z);
        settingsFragment_.setArguments(bundle);
        return settingsFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m21368M();
    }

    private void m21368M() {
        this.f19829T = UserManager.a().i();
        this.f19830U = UserManager.a().j();
        this.f19831V = UserManager.a().k();
        this.f19832W = UserManager.a().l();
        this.f19833X = UserManager.a().m();
        if (this.f19829T == null) {
            this.f19829T = "";
        }
        if (this.f19830U == null) {
            this.f19830U = "";
        }
        if (this.f19831V == null) {
            this.f19831V = "";
        }
        if (this.f19832W == null) {
            this.f19832W = "";
        }
        if (this.f19833X == null) {
            this.f19833X = "";
        }
    }

    @AfterViews
    protected void mo6514z() {
        Log.b(f19809e, "Begin updateFollowingViewBinding()");
        super.m20708a(this.f19817H, this.f19817H.f21825a, true, 200, 200, SingPermissionRequests.f23948b);
        this.f19817H.m23398b();
        if (UserManager.a().u() != null) {
            this.f19834Y = UserManager.a().u().trim();
            this.f19844n.setText(this.f19834Y);
        } else {
            Log.b(f19809e, "Getting blurb for account: " + UserManager.a().f());
            UserManager.a().a(UserManager.a().f(), new C40661(this));
        }
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getBoolean("SETTINGS_GOTO_BLURB", false)) {
            arguments.putBoolean("SETTINGS_GOTO_BLURB", false);
            this.f19844n.setFocusableInTouchMode(true);
            this.f19844n.requestFocus();
            Activity activity = getActivity();
            if (activity != null) {
                MiscUtils.m25890a(activity, this.f19844n);
            }
        }
        ViewGroup viewGroup = (ViewGroup) this.f19853w.getParent();
        viewGroup.removeView(this.f19853w);
        this.f19835Z = UserManager.a().v();
        this.f19853w.setChecked(this.f19835Z.equals(EmailOptIn.YES));
        viewGroup.addView(this.f19853w);
        this.f19853w.setOnCheckedChangeListener(this.f19823N);
        if (DeviceSettings.m()) {
            this.f19819J.setChecked(SingApplication.f().getSharedPreferences("sing_prefs", 0).getBoolean("SAMSUNG_RTM_ENABLED_IN_SETTINGS", SingServerValues.m21700u()));
            this.f19819J.setOnCheckedChangeListener(this.f19824O);
            this.f19818I.setVisibility(0);
        } else {
            this.f19818I.setVisibility(8);
        }
        m21369N();
        m21410L();
        if (ChatUtils.b()) {
            m21399A();
            m21400B();
            this.f19814E.setVisibility(0);
        } else {
            this.f19814E.setVisibility(8);
        }
        this.ac = new DiagnosticTapRecognizer(this.f19820K, new C40682(this));
        m21374S();
    }

    public void onStart() {
        Log.b(f19809e, "Begin onStart()");
        super.onStart();
        m19850c((int) C1947R.string.core_settings);
        this.ab = getActivity().getSharedPreferences("sing_prefs", 0).getBoolean("facebook.enabled", true);
        if (MagicFacebook.a().c()) {
            this.f19856z.setVisibility(4);
            this.f19810A.setVisibility(0);
            MagicFacebook.a().a(new C40693(this));
        } else {
            this.f19856z.setChecked(false);
            Log.a(f19809e, "User has not connected to Facebook");
        }
        m21373R();
        Analytics.m17835a();
    }

    public void onResume() {
        super.onResume();
        if (this.f19844n.hasFocus()) {
            MiscUtils.m25890a(getActivity(), this.f19844n);
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
        m21409K();
        MiscUtils.m25891a(getActivity(), false);
        m21370O();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19844n.setOnFocusChangeListener(null);
        this.f19825P = null;
    }

    private void m21390d(final boolean z) {
        SingApplication.j().b(z, new Completion<ChatStatus>(this) {
            final /* synthetic */ SettingsFragment f19802b;

            public void m21365a(final ChatStatus chatStatus) {
                if (chatStatus == ChatStatus.OK || !this.f19802b.isAdded()) {
                    this.f19802b.m19846b(z ? C1947R.string.chat_success_change_read_receipts_setting_enabled : C1947R.string.chat_success_change_read_receipts_setting_disabled);
                    ChatAnalytics.m22395b(z ? SettingToggleType.ON : SettingToggleType.OFF);
                    return;
                }
                this.f19802b.m19838a(new Runnable(this) {
                    final /* synthetic */ C40757 f19800b;

                    public void run() {
                        this.f19800b.f19802b.f19816G.setOnCheckedChangeListener(null);
                        this.f19800b.f19802b.f19816G.setChecked(z);
                        this.f19800b.f19802b.f19816G.setOnCheckedChangeListener(this.f19800b.f19802b.ao);
                        ChatUtils.a(this.f19800b.f19802b.getActivity(), C1947R.string.chat_error_change_read_receipts_setting, chatStatus);
                    }
                });
            }
        });
    }

    void m21399A() {
        this.f19815F.setOnCheckedChangeListener(null);
        this.f19815F.setChecked(!SingApplication.j().f());
        this.f19815F.setOnCheckedChangeListener(this.an);
    }

    void m21400B() {
        this.f19816G.setOnCheckedChangeListener(null);
        this.f19816G.setChecked(SingApplication.j().g());
        this.f19816G.setOnCheckedChangeListener(this.ao);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            Log.e(f19809e, "Bad result code, " + i2 + ", returned for request code: " + i);
            return;
        }
        switch (i) {
            case 2202:
                if (intent.getExtras() != null) {
                    Bitmap b = m20711b(intent);
                    if (b != null) {
                        this.f19817H.setBitmap(b);
                        m20707a(b, null);
                        return;
                    }
                    Log.e(f19809e, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
                    return;
                }
                return;
            case 32487:
                m21373R();
                return;
            default:
                return;
        }
    }

    @Click
    protected void m21401C() {
        if (!SingApplication.g.booleanValue()) {
            boolean z = !this.f19856z.isChecked();
            this.f19856z.setChecked(z);
            if (z) {
                TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.facebook_disconnect_confirm_title), getString(C1947R.string.facebook_disconnect_confirm_text), true, true);
                textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
                    final /* synthetic */ SettingsFragment f19755a;

                    {
                        this.f19755a = r1;
                    }

                    public void mo6385a(CustomAlertDialog customAlertDialog) {
                        this.f19755a.m21372Q();
                    }

                    public void mo6386b(CustomAlertDialog customAlertDialog) {
                    }
                });
                textAlertDialog.m19812c(true);
                textAlertDialog.m19810b(true);
                textAlertDialog.show();
            } else if (MagicFacebook.a().c()) {
                m21371P();
            } else {
                LoginManager.getInstance().registerCallback(m19862m().n_(), new C40779(this));
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), MagicNetwork.d().getFacebookReadPermissions());
            }
        }
    }

    @Click
    protected void m21402D() {
        if (!SingApplication.g.booleanValue()) {
            MagicTwitter a = MiscUtils.m25882a();
            if (a == null) {
                Log.e(f19809e, "Twitter instance not ready");
                this.f19813D.setChecked(false);
                return;
            }
            boolean c = a.m18845c();
            this.f19813D.setChecked(c);
            if (c) {
                TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.twitter_disconnect_confirm_title), getString(C1947R.string.twitter_disconnect_confirm_text), true, true);
                textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
                    final /* synthetic */ SettingsFragment f19756a;

                    {
                        this.f19756a = r1;
                    }

                    public void mo6385a(CustomAlertDialog customAlertDialog) {
                        MagicTwitter.m18838a().m18844b();
                        this.f19756a.f19812C.setText(C1947R.string.twitter);
                        this.f19756a.f19813D.setChecked(false);
                    }

                    public void mo6386b(CustomAlertDialog customAlertDialog) {
                    }
                });
                textAlertDialog.m19812c(true);
                textAlertDialog.m19810b(true);
                textAlertDialog.show();
                return;
            }
            startActivityForResult(new Intent(getActivity(), TwitterOAuthActivity_.class), 32487);
        }
    }

    protected void m21414c(final boolean z) {
        if (z != SingApplication.j().f()) {
            SingApplication.j().a(z, new Completion<ChatStatus>(this) {
                final /* synthetic */ SettingsFragment f19760b;

                public void m21351a(final ChatStatus chatStatus) {
                    if (chatStatus == ChatStatus.OK || !this.f19760b.isAdded()) {
                        this.f19760b.m19846b(z ? C1947R.string.chat_disabled_notifications_setting : C1947R.string.chat_enabled_notifications_setting);
                        ChatAnalytics.m22388a(z ? SettingToggleType.OFF : SettingToggleType.ON);
                        return;
                    }
                    this.f19760b.m19838a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass12 f19758b;

                        public void run() {
                            this.f19758b.f19760b.m21399A();
                            ChatUtils.a(this.f19758b.f19760b.getActivity(), C1947R.string.chat_error_change_notifications_setting, chatStatus);
                        }
                    });
                }
            });
        }
    }

    @Click
    protected void m21403E() {
        mo6487a(BlockedUsersFragment.m22319a());
    }

    @Click
    protected void m21404F() {
        Log.b(f19809e, "showPrivacyPolicy");
        mo6487a(WebViewFragment.m22012a(getResources().getString(C1947R.string.privacy_url), getResources().getString(C1947R.string.settings_privacy_policy)));
    }

    @Click
    protected void m21405G() {
        Log.b(f19809e, "showTermsOfService");
        mo6487a(WebViewFragment.m22012a(getResources().getString(C1947R.string.tos_url), getResources().getString(C1947R.string.settings_terms_of_service)));
    }

    @Click
    protected void m21406H() {
        if (this.f19825P == null || !this.f19825P.isShowing()) {
            Boolean valueOf;
            String str;
            Object obj;
            Object obj2;
            Boolean bool;
            Object obj3;
            CharSequence charSequence;
            CharSequence charSequence2;
            Object obj4;
            Boolean bool2;
            Boolean bool3;
            String str2;
            String str3;
            Boolean valueOf2;
            MiscUtils.m25891a(getActivity(), false);
            this.f19822M.requestFocus();
            ArrayList arrayList = new ArrayList();
            Boolean valueOf3 = Boolean.valueOf(false);
            Boolean valueOf4 = Boolean.valueOf(false);
            UserManager a = UserManager.a();
            CharSequence j = a.j();
            if (j == null) {
                j = "";
            }
            CharSequence i = a.i();
            if (i == null) {
                i = "";
            }
            String m = a.m();
            if (m == null) {
                m = "";
            }
            String k = a.k();
            if (k == null) {
                k = "";
            }
            String l = a.l();
            if (l == null) {
                l = "";
            }
            String obj5 = this.f19839i.getText().toString();
            if (obj5.equals(this.f19840j.getText().toString())) {
                Boolean valueOf5 = Boolean.valueOf(true);
                if (obj5.length() >= 6 && obj5.length() <= 16) {
                    valueOf = Boolean.valueOf(true);
                    valueOf3 = valueOf5;
                    str = obj5;
                    obj = null;
                } else if (obj5.length() > 0) {
                    arrayList.add(ErrorType.INVALID_PASSWORD_LENGTH);
                    obj = 1;
                    String str4 = m;
                    valueOf = valueOf3;
                    valueOf3 = valueOf5;
                    str = str4;
                } else {
                    obj = null;
                    Boolean bool4 = valueOf5;
                    str = m;
                    valueOf = valueOf3;
                    valueOf3 = bool4;
                }
            } else {
                valueOf3 = Boolean.valueOf(false);
                obj = null;
                str = m;
                valueOf = Boolean.valueOf(false);
            }
            CharSequence obj6 = this.f19837g.getText().toString();
            if (obj6.compareTo(i) == 0) {
                obj2 = obj;
                bool = valueOf;
                obj3 = null;
                charSequence = i;
            } else if (TextUtils.isEmpty(obj6) || TextUtils.getTrimmedLength(obj6) == 0) {
                arrayList.add(ErrorType.EMPTY_USERNAME);
                this.f19837g.setText(i);
                obj2 = 1;
                bool = Boolean.valueOf(false);
                obj3 = null;
                charSequence = i;
            } else {
                charSequence = obj6;
                obj2 = obj;
                bool = valueOf;
                int i2 = 1;
            }
            CharSequence obj7 = this.f19838h.getText().toString();
            if (obj7.compareTo(j) == 0) {
                charSequence2 = j;
                obj4 = obj2;
                obj2 = null;
            } else if (TextUtils.isEmpty(obj7) || TextUtils.getTrimmedLength(obj7) == 0) {
                arrayList.add(ErrorType.EMPTY_EMAIL);
                this.f19838h.setText(j);
                bool = Boolean.valueOf(false);
                charSequence2 = j;
                obj4 = 1;
                obj2 = null;
            } else {
                charSequence2 = obj7;
                obj4 = obj2;
                int i3 = 1;
            }
            if (obj4 != null || (r3 == null && r10 == null)) {
                this.f19837g.setText(i);
                this.f19838h.setText(j);
                bool2 = bool;
            } else {
                bool2 = Boolean.valueOf(true);
            }
            if (valueOf3.booleanValue()) {
                bool3 = bool2;
            } else {
                bool3 = Boolean.valueOf(false);
            }
            String trim = this.f19841k.getText().toString().trim();
            if (trim.compareTo(k) != 0) {
                str2 = trim;
                bool2 = Boolean.valueOf(true);
            } else {
                bool2 = valueOf4;
                str2 = k;
            }
            String trim2 = this.f19842l.getText().toString().trim();
            if (trim2.compareTo(l) != 0) {
                str3 = trim2;
                valueOf2 = Boolean.valueOf(true);
            } else {
                str3 = l;
                valueOf2 = bool2;
            }
            if (obj4 != null && valueOf3.booleanValue()) {
                this.f19839i.setText("");
                this.f19840j.setText("");
                StringBuilder stringBuilder = new StringBuilder();
                obj3 = null;
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    switch ((ErrorType) arrayList.get(i4)) {
                        case EMPTY_EMAIL:
                            stringBuilder.append(getString(C1947R.string.settings_empty_email));
                            break;
                        case EMPTY_USERNAME:
                            stringBuilder.append(getString(C1947R.string.settings_empty_handle));
                            break;
                        case INVALID_PASSWORD_LENGTH:
                            obj3 = 1;
                            continue;
                    }
                    if (i4 < arrayList.size() - 1) {
                        stringBuilder.append("\n\n");
                    }
                }
                if (obj3 != null) {
                    if (arrayList.size() > 1) {
                        stringBuilder.append("\n\n");
                    }
                    stringBuilder.append(getString(C1947R.string.settings_password_length_invalid));
                }
                if (valueOf2.booleanValue()) {
                    this.f19827R = stringBuilder.toString();
                    this.f19828S = Boolean.valueOf(true);
                } else {
                    TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.settings_generic_invalid), stringBuilder.toString(), true, false);
                    textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
                    textAlertDialog.m19814d(true);
                    textAlertDialog.show();
                }
            }
            if (bool3.booleanValue() || !valueOf3.booleanValue() || valueOf2.booleanValue()) {
                this.f19825P = new BusyDialog(getActivity(), getResources().getString(C1947R.string.settings_update_user_profile));
                this.f19825P.show();
                UpdateListener anonymousClass13 = new UpdateListener(this) {
                    final /* synthetic */ SettingsFragment f19764d;
                    private boolean f19765e;
                    private NetworkResponse f19766f;
                    private boolean f19767g;
                    private int f19768h;

                    public void mo6597a(NetworkResponse networkResponse, Boolean bool, int i) {
                        boolean z = true;
                        if (!valueOf2.booleanValue() || this.f19765e) {
                            SettingsFragment settingsFragment = this.f19764d;
                            boolean booleanValue = bool3.booleanValue();
                            boolean booleanValue2 = valueOf2.booleanValue();
                            NetworkResponse b = !valueOf3.booleanValue() ? NetworkResponse.b() : networkResponse;
                            if (!(bool.booleanValue() && valueOf3.booleanValue())) {
                                z = false;
                            }
                            settingsFragment.mo6604a(booleanValue, booleanValue2, b, z, !bool.booleanValue() ? i : C1947R.string.settings_password_confirmation_error, this.f19766f, this.f19767g, this.f19768h);
                            return;
                        }
                        this.f19765e = true;
                        this.f19766f = networkResponse;
                        this.f19767g = bool.booleanValue();
                        this.f19768h = i;
                    }

                    public void mo6598b(NetworkResponse networkResponse, Boolean bool, int i) {
                        boolean z = false;
                        if (!bool3.booleanValue() || this.f19765e) {
                            SettingsFragment settingsFragment = this.f19764d;
                            boolean z2 = bool3.booleanValue() || !valueOf3.booleanValue();
                            boolean booleanValue = valueOf2.booleanValue();
                            NetworkResponse b = !valueOf3.booleanValue() ? NetworkResponse.b() : this.f19766f;
                            if (this.f19767g && valueOf3.booleanValue()) {
                                z = true;
                            }
                            settingsFragment.mo6604a(z2, booleanValue, b, z, !valueOf3.booleanValue() ? C1947R.string.settings_password_confirmation_error : this.f19768h, networkResponse, bool.booleanValue(), i);
                            return;
                        }
                        this.f19765e = true;
                        this.f19766f = networkResponse;
                        this.f19767g = bool.booleanValue();
                        this.f19768h = i;
                    }
                };
                Log.b(f19809e, "Updating profile");
                UserUpdateTask userUpdateTask = new UserUpdateTask(charSequence, charSequence2, str, null, anonymousClass13);
                final UserUpdateTask userUpdateTask2 = new UserUpdateTask(str2, str3, anonymousClass13);
                final Boolean bool5 = bool3;
                final UserUpdateTask userUpdateTask3 = userUpdateTask;
                bool = valueOf2;
                this.f19825P.m23579a(new BusyDialogListener(this) {
                    final /* synthetic */ SettingsFragment f19773e;

                    public void mo6373a() {
                        if (bool5.booleanValue()) {
                            userUpdateTask3.cancel(true);
                        }
                        if (bool.booleanValue()) {
                            userUpdateTask2.cancel(true);
                        }
                    }
                });
                if (bool3.booleanValue()) {
                    userUpdateTask.execute(new Void[0]);
                }
                if (valueOf2.booleanValue()) {
                    userUpdateTask2.execute(new Void[0]);
                }
                if (!bool3.booleanValue() && !valueOf2.booleanValue() && !valueOf3.booleanValue()) {
                    mo6604a(true, false, NetworkResponse.b(), false, C1947R.string.settings_password_confirmation_error, null, true, 0);
                    return;
                }
                return;
            }
            this.f19841k.setText(this.f19841k.getText().toString().trim());
            this.f19842l.setText(this.f19842l.getText().toString().trim());
            m19846b((int) C1947R.string.settings_nothing_to_update);
        }
    }

    @Click
    protected void m21407I() {
        Log.b(f19809e, "launchAVQualitySurvey");
        SurveyContext surveyContext = new SurveyContext();
        surveyContext.f24288b = EntryPoint.SETTINGS;
        SurveyPresenter.m25508a().m25551c(getActivity(), surveyContext);
    }

    private void m21369N() {
        TextWatcher anonymousClass15 = new TextWatcher(this) {
            final /* synthetic */ SettingsFragment f19774a;

            {
                this.f19774a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (this.f19774a.isAdded()) {
                    Object obj = (this.f19774a.f19837g.getText().toString().equals(this.f19774a.f19829T) && this.f19774a.f19838h.getText().toString().equals(this.f19774a.f19830U) && this.f19774a.f19839i.getText().toString().isEmpty() && this.f19774a.f19841k.getText().toString().equals(this.f19774a.f19831V) && this.f19774a.f19842l.getText().toString().equals(this.f19774a.f19832W)) ? null : 1;
                    if (obj != null) {
                        this.f19774a.f19843m.setTextColor(this.f19774a.getResources().getColor(C1947R.color.button_text_inverse));
                    } else {
                        this.f19774a.f19843m.setTextColor(this.f19774a.getResources().getColor(C1947R.color.light_gray_text));
                    }
                    if (!this.f19774a.f19839i.getText().toString().isEmpty()) {
                        this.f19774a.f19845o.setText(this.f19774a.f19839i.getText());
                    }
                    if (!this.f19774a.f19840j.getText().toString().isEmpty()) {
                        this.f19774a.f19846p.setText(this.f19774a.f19840j.getText());
                    }
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        };
        this.f19826Q = new OnFocusChangeListener(this) {
            final /* synthetic */ SettingsFragment f19775a;

            {
                this.f19775a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    this.f19775a.m21370O();
                }
            }
        };
        this.f19844n.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ SettingsFragment f19776a;

            {
                this.f19776a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (this.f19776a.isAdded() && (i == 3 || i == 6)) {
                    this.f19776a.m21370O();
                    this.f19776a.mo6601J();
                }
                return false;
            }
        });
        WeakListener.m19083a(this.f19837g, anonymousClass15);
        WeakListener.m19083a(this.f19838h, anonymousClass15);
        WeakListener.m19083a(this.f19839i, anonymousClass15);
        WeakListener.m19083a(this.f19841k, anonymousClass15);
        WeakListener.m19083a(this.f19842l, anonymousClass15);
        InputFilter[] inputFilterArr = new InputFilter[]{new LengthFilter(this, 50) {
            final /* synthetic */ SettingsFragment f19777a;

            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                CharSequence filter = super.filter(charSequence, i, i2, spanned, i3, i4);
                if (filter != null) {
                    this.f19777a.m19849b(this.f19777a.getResources().getString(C1947R.string.settings_name_long, new Object[]{Integer.toString(50)}));
                }
                return filter;
            }
        }};
        this.f19841k.setFilters(inputFilterArr);
        this.f19842l.setFilters(inputFilterArr);
        this.f19844n.setOnFocusChangeListener(this.f19826Q);
    }

    @UiThread
    protected void mo6601J() {
        if (isAdded()) {
            MiscUtils.m25891a(getActivity(), true);
            this.f19836f.requestFocus();
        }
    }

    private void m21370O() {
        boolean z = false;
        if (!this.aa && this.f19844n != null) {
            final String obj = this.f19844n.getText().toString();
            if ((this.f19834Y == null && obj.length() > 0) || !obj.equals(this.f19834Y)) {
                z = true;
            }
            if (z) {
                this.aa = true;
                Log.b(f19809e, "Updating blurb");
                UserManager.a().a(obj, new UserManager$UpdateUserBlurbResponseCallback(this) {
                    final /* synthetic */ SettingsFragment f19779b;

                    public void handleResponse(NetworkResponse networkResponse) {
                        this.f19779b.f19834Y = obj;
                        this.f19779b.m19846b((int) C1947R.string.settings_profile_updated);
                        NotificationCenter.m19011a().m19017b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
                        this.f19779b.aa = false;
                    }
                });
            }
        }
    }

    protected void m21409K() {
        if (this.f19825P != null) {
            this.f19825P.dismiss();
            this.f19825P = null;
        }
    }

    @UiThread
    protected void mo6604a(boolean z, boolean z2, NetworkResponse networkResponse, boolean z3, int i, NetworkResponse networkResponse2, boolean z4, int i2) {
        int i3 = C1947R.string.settings_update_fail;
        if (isAdded()) {
            if (z && z2) {
                boolean z5 = z3 && z4;
                if (z3) {
                    i = i2;
                }
                if (z3) {
                    i3 = C1947R.string.settings_name_update_fail;
                }
                if (z3) {
                    networkResponse = networkResponse2;
                }
                m21383a(z5, i, i3, networkResponse);
            } else if (z) {
                m21383a(z3, i, (int) C1947R.string.settings_update_fail, networkResponse);
            } else if (z2) {
                m21383a(z4, i2, (int) C1947R.string.settings_name_update_fail, networkResponse2);
            }
            m21368M();
            m21410L();
            return;
        }
        m21409K();
    }

    private void m21383a(boolean z, int i, int i2, NetworkResponse networkResponse) {
        Object obj;
        Object obj2;
        Object obj3;
        Object i3 = UserManager.a().i();
        if (i3 == null) {
            i3 = "";
        }
        Object j = UserManager.a().j();
        if (j == null) {
            j = "";
        }
        String m = UserManager.a().m();
        if (m == null) {
            obj = "";
        } else {
            String str = m;
        }
        m = UserManager.a().k();
        if (m == null) {
            obj2 = "";
        } else {
            String str2 = m;
        }
        m = UserManager.a().l();
        if (m == null) {
            obj3 = "";
        } else {
            String str3 = m;
        }
        if (!this.f19838h.getText().toString().equals(j)) {
            this.f19838h.requestFocus();
        } else if (!this.f19837g.getText().toString().equals(i3)) {
            this.f19837g.requestFocus();
        } else if (!this.f19839i.getText().toString().equals("") && !this.f19839i.getText().toString().equals(obj)) {
            this.f19839i.requestFocus();
        } else if (!this.f19841k.getText().toString().equals(obj2)) {
            this.f19841k.requestFocus();
        } else if (!this.f19842l.getText().toString().equals(obj3)) {
            this.f19842l.requestFocus();
        }
        final boolean z2 = !this.f19829T.equals(i3);
        final boolean z3 = !this.f19830U.equals(j);
        final boolean z4 = !this.f19833X.equals(obj);
        final boolean z5 = !this.f19831V.equals(obj2);
        final boolean z6 = !this.f19832W.equals(obj3);
        final boolean z7 = z && !this.f19839i.getText().toString().isEmpty();
        if (this.f19825P != null) {
            this.f19825P.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ SettingsFragment f19787g;

                public void onDismiss(DialogInterface dialogInterface) {
                    SettingsFragment settingsFragment = this.f19787g;
                    boolean z = z2;
                    boolean z2 = z3;
                    boolean z3 = z4 || z7;
                    settingsFragment.m21384a(z, z2, z3, z5, z6);
                }
            });
        }
        if (z) {
            this.f19843m.setTextColor(getResources().getColor(C1947R.color.light_gray_text));
            if (!this.f19828S.booleanValue() || this.f19825P == null) {
                mo6602a(1, -1, (int) C1947R.string.settings_profile_updated, Boolean.valueOf(true));
            } else {
                this.f19825P.m23574a(2, getResources().getString(C1947R.string.settings_generic_invalid), this.f19827R, true, getString(C1947R.string.core_ok));
                this.f19828S = Boolean.valueOf(false);
                this.f19827R = null;
            }
        } else if (i == -1) {
            mo6602a(2, (int) C1947R.string.settings_update_fail, i2, Boolean.valueOf(true));
            MagicNetwork.a(networkResponse);
        } else {
            mo6602a(2, (int) C1947R.string.settings_update_fail, i, Boolean.valueOf(true));
        }
        if (z2 || z3 || z4 || z5 || z6) {
            NotificationCenter.m19011a().m19017b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
        }
    }

    private void m21384a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (z) {
            if (this.ad == null) {
                this.ad = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_out);
            }
            m21380a(this.f19847q, this.ad, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        if (z2) {
            if (this.ae == null) {
                this.ae = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_out);
            }
            m21380a(this.f19848r, this.ae, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        if (z3) {
            if (this.af == null) {
                this.af = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_out);
            }
            if (this.ag == null) {
                this.ag = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_out);
            }
            if (this.ah == null) {
                this.ah = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_in);
            }
            if (this.ai == null) {
                this.ai = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_in);
            }
            if (this.aj == null) {
                this.aj = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_out);
            }
            if (this.ak == null) {
                this.ak = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_out);
            }
            m21380a(this.f19849s, this.af, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            m21380a(this.f19850t, this.ag, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            m21380a(this.f19839i, this.ah, 0.0f);
            m21380a(this.f19840j, this.ai, 0.0f);
            m21380a(this.f19845o, this.aj, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            m21380a(this.f19846p, this.ak, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        if (z4) {
            if (this.al == null) {
                this.al = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_out);
            }
            m21380a(this.f19851u, this.al, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        if (z5) {
            if (this.am == null) {
                this.am = AnimatorInflater.loadAnimator(getActivity(), C1947R.animator.settings_delayed_fade_out);
            }
            m21380a(this.f19852v, this.am, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
    }

    private void m21380a(View view, Animator animator, float f) {
        animator.end();
        animator.cancel();
        view.setVisibility(0);
        view.setAlpha(f);
        animator.setTarget(view);
        animator.start();
    }

    @UiThread
    protected void mo6603a(NetworkResponse networkResponse, Boolean bool, int i) {
        if (isAdded()) {
            if (bool.booleanValue()) {
                mo6602a(1, -1, (int) C1947R.string.settings_profile_updated, Boolean.valueOf(true));
                NotificationCenter.m19011a().m19017b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
            } else {
                if (i == -1) {
                    mo6602a(2, (int) C1947R.string.settings_update_fail, (int) C1947R.string.settings_update_fail, Boolean.valueOf(true));
                    MagicNetwork.a(networkResponse);
                } else {
                    mo6602a(2, (int) C1947R.string.settings_update_fail, i, Boolean.valueOf(true));
                }
                this.f19853w.setOnCheckedChangeListener(null);
                this.f19853w.setChecked(UserManager.a().v().equals(EmailOptIn.YES));
                this.f19853w.setOnCheckedChangeListener(this.f19823N);
            }
            m21410L();
        }
    }

    public void m21410L() {
        UserManager a = UserManager.a();
        this.f19839i.setText("");
        this.f19840j.setText("");
        CharSequence j = a.j();
        if (j != null) {
            this.f19838h.setText(j);
            if (this.f19838h.hasFocus()) {
                this.f19838h.setSelection(this.f19838h.getText().length());
            }
        }
        j = a.i();
        if (j != null) {
            this.f19837g.setText(j);
            if (this.f19837g.hasFocus()) {
                this.f19837g.setSelection(this.f19837g.getText().length());
            }
        }
        j = a.k();
        if (j != null) {
            this.f19841k.setText(j);
            if (this.f19841k.hasFocus()) {
                this.f19841k.setSelection(this.f19841k.getText().length());
            }
        }
        CharSequence l = a.l();
        if (l != null) {
            this.f19842l.setText(l);
            if (this.f19842l.hasFocus()) {
                this.f19842l.setSelection(this.f19842l.getText().length());
            }
        }
    }

    @SupposeUiThread
    protected void mo6602a(int i, int i2, int i3, Boolean bool) {
        if (this.f19825P == null) {
            Log.e(f19809e, "Trying to call setBusyDialogStatus on a null BusyDialog!");
        } else if (i2 == -1) {
            this.f19825P.m23577a(i, getString(i3), bool.booleanValue(), getString(C1947R.string.core_ok));
        } else {
            this.f19825P.m23574a(i, getString(i2), getString(i3), bool.booleanValue(), getString(C1947R.string.core_ok));
        }
    }

    private void m21371P() {
        MagicFacebook.a().a(new MagicFacebook$FacebookUserInfoListener(this) {
            final /* synthetic */ SettingsFragment f19788a;

            {
                this.f19788a = r1;
            }

            public void mo6410a(MagicFacebook$FacebookUserInfo magicFacebook$FacebookUserInfo) {
                if (this.f19788a.isAdded()) {
                    this.f19788a.f19855y.setText(magicFacebook$FacebookUserInfo.f15985g);
                    this.f19788a.f19856z.setChecked(true);
                    this.f19788a.getActivity().getSharedPreferences("sing_prefs", 0).edit().putBoolean("facebook.enabled", true).apply();
                }
            }

            public void mo6411b(MagicFacebook$FacebookUserInfo magicFacebook$FacebookUserInfo) {
                if (this.f19788a.isAdded()) {
                    this.f19788a.f19855y.setText(C1947R.string.facebook);
                    this.f19788a.f19856z.setChecked(false);
                }
            }
        });
        ((MasterActivity) getActivity()).N();
    }

    private void m21372Q() {
        this.f19856z.setChecked(false);
        this.f19855y.setText(getString(C1947R.string.facebook));
        getActivity().getSharedPreferences("sing_prefs", 0).edit().putBoolean("facebook.enabled", false).apply();
    }

    private void m21373R() {
        MagicTwitter a = MiscUtils.m25882a();
        if (a == null) {
            Log.e(f19809e, "Twitter instance not ready");
        } else {
            a.m18840a(new TwitterAccountCallback(this) {
                final /* synthetic */ SettingsFragment f19791a;

                {
                    this.f19791a = r1;
                }

                public void mo6600a(final AccountSettings accountSettings) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass22 f19790b;

                        public void run() {
                            if (this.f19790b.f19791a.isAdded()) {
                                if (accountSettings == null) {
                                    this.f19790b.f19791a.f19812C.setText(C1947R.string.twitter);
                                    this.f19790b.f19791a.f19813D.setChecked(false);
                                } else {
                                    this.f19790b.f19791a.f19813D.setChecked(true);
                                    this.f19790b.f19791a.f19812C.setText(accountSettings.getScreenName());
                                }
                                this.f19790b.f19791a.f19811B.forceLayout();
                            }
                        }
                    });
                }
            });
        }
    }

    private void m21374S() {
        this.f19821L.setText(("4.4.9" + " Build 1231") + " " + "sing_android-qa-release-prod".replace("sing_android-qa-", ""));
    }
}
