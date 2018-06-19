package com.smule.singandroid;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.facebook.MagicFacebook$FacebookOnPostCallback;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Analytics.PerformanceStatus;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Analytics.Share;
import com.smule.android.logging.Analytics.SocialChannel;
import com.smule.android.logging.Analytics.VideoStatusType;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.StoreManager.SongResponseCallback;
import com.smule.android.network.managers.TracksManager;
import com.smule.android.network.managers.TracksManager.VideoDownloadCallback;
import com.smule.android.network.managers.TracksManager.VideoDownloader;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.twitter.MagicTwitter.TwitterOnPostCallback;
import com.smule.android.utils.ImageUtils$ImageViewLoadOptimizer;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.Toaster;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatManager;
import com.smule.chat.GroupChat;
import com.smule.chat.PeerChat;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.customviews.SquareSNPImageView;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.ShareUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.ShareModuleType;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.StatusUpdate;

@EActivity
public class ShareActivity extends BaseActivity {
    public static final String f19895g = ShareActivity.class.getName();
    @ViewById
    protected TextView f19896A;
    @InstanceState
    protected boolean f19897B;
    @InstanceState
    protected Intent f19898C;
    @InstanceState
    protected Intent f19899D;
    @InstanceState
    protected Intent f19900E;
    @InstanceState
    protected Intent f19901F;
    @InstanceState
    protected Intent f19902G;
    @InstanceState
    protected Intent f19903H;
    protected TwitterOnPostCallback f19904I;
    protected MagicFacebook$FacebookOnPostCallback f19905J;
    @Extra
    @InstanceState
    protected PerformanceV2 f19906K;
    @Extra
    @InstanceState
    protected String f19907L;
    @Extra
    @InstanceState
    protected ArrangementVersionLite f19908M;
    @Extra
    @InstanceState
    protected SongV2 f19909N;
    @Extra
    @InstanceState
    protected String f19910O;
    @Extra
    @InstanceState
    protected SearchClkContext f19911P;
    @Extra
    @InstanceState
    protected PostSingBundle f19912Q;
    @InstanceState
    protected boolean f19913R;
    @InstanceState
    protected SocialChannel f19914S;
    @InstanceState
    protected boolean f19915T = false;
    private CallbackManager f19916U;
    private boolean f19917V;
    private VideoDownloader f19918W;
    private List<ButtonRank> f19919X = new ArrayList();
    private Set<String> f19920Y = new HashSet();
    @ViewById
    protected TextView f19921h;
    @ViewById
    protected SquareSNPImageView f19922i;
    @ViewById
    protected ImageView f19923j;
    @ViewById
    protected TextView f19924k;
    @ViewById
    protected TextView f19925l;
    @ViewById
    protected ViewGroup f19926m;
    @ViewById
    protected View f19927n;
    @ViewById
    protected View f19928o;
    @ViewById
    protected View f19929p;
    @ViewById
    protected View f19930q;
    @ViewById
    protected View f19931r;
    @ViewById
    protected View f19932s;
    @ViewById
    protected View f19933t;
    @ViewById
    protected View f19934u;
    @ViewById
    protected ToggleButton f19935v;
    @ViewById
    protected ToggleButton f19936w;
    @ViewById
    protected RelativeLayout f19937x;
    @ViewById
    protected TextView f19938y;
    @ViewById
    protected ProgressBar f19939z;

    class C40871 implements FacebookCallback<LoginResult> {
        final /* synthetic */ ShareActivity f19882a;

        C40871(ShareActivity shareActivity) {
            this.f19882a = shareActivity;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            m21430a((LoginResult) obj);
        }

        public void m21430a(LoginResult loginResult) {
            this.f19882a.m21446c(true);
        }

        public void onCancel() {
            this.f19882a.m21446c(true);
        }

        public void onError(FacebookException facebookException) {
            Log.b(ShareActivity.f19895g, "Facebook Error", facebookException);
            this.f19882a.m21446c(true);
        }
    }

    class C40882 implements SongResponseCallback {
        final /* synthetic */ ShareActivity f19883a;

        C40882(ShareActivity shareActivity) {
            this.f19883a = shareActivity;
        }

        public void mo6605a(SongV2 songV2) {
            if (songV2 != null) {
                this.f19883a.f19909N = songV2;
                this.f19883a.mo6611u();
            }
        }
    }

    class C40955 implements TwitterOnPostCallback {
        final /* synthetic */ ShareActivity f19892a;

        C40955(ShareActivity shareActivity) {
            this.f19892a = shareActivity;
        }

        public void mo6468a() {
            Toaster.a(this.f19892a, this.f19892a.getString(this.f19892a.m21448v() ? C1947R.string.share_success_arrangement : C1947R.string.share_success));
        }

        public void mo6469a(Exception exception) {
            Toaster.a(this.f19892a, this.f19892a.getString(C1947R.string.share_fail));
        }
    }

    class C40966 implements MagicFacebook$FacebookOnPostCallback {
        final /* synthetic */ ShareActivity f19893a;

        C40966(ShareActivity shareActivity) {
            this.f19893a = shareActivity;
        }

        public void mo6608a() {
            Toaster.a(this.f19893a, this.f19893a.getString(this.f19893a.m21448v() ? C1947R.string.share_success_arrangement : C1947R.string.share_success));
        }

        public void mo6609b() {
            Toaster.a(this.f19893a, this.f19893a.getString(C1947R.string.share_fail));
        }
    }

    class C40977 extends TypeReference<List<ButtonRank>> {
        final /* synthetic */ ShareActivity f19894a;

        C40977(ShareActivity shareActivity) {
            this.f19894a = shareActivity;
        }
    }

    public static Intent m21438a(Context context, PerformanceV2 performanceV2, String str, ArrangementVersionLite arrangementVersionLite, SongV2 songV2, Long l, SearchClkContext searchClkContext) {
        Log.b(f19895g, "generateIntent -- is context null ? " + (context == null));
        Intent intent = new Intent(context, ShareActivity_.class);
        intent.putExtra("PERFORMANCE_KEY", performanceV2);
        intent.putExtra("ARRANGEMENT_KEY", arrangementVersionLite);
        intent.putExtra("OPENCALL_KEY", str);
        intent.putExtra("SONG_KEY", songV2);
        intent.putExtra("PROMO_ID_KEY", l.toString());
        intent.putExtra("SEARCHCLK_CONTEXT_KEY", searchClkContext);
        return intent;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f19916U = Factory.create();
        LoginManager.getInstance().registerCallback(this.f19916U, new C40871(this));
    }

    protected void m21459d() {
        super.d();
        if (this.f19906K == null && this.f19908M == null) {
            finish();
            return;
        }
        this.f19917V = this.f19922i == null;
        if (this.f19908M != null) {
            this.f19921h.setText(getString(C1947R.string.share_this_song));
        }
        if (!this.f19917V) {
            Bitmap a = ImageToDiskUtils.m25830a((Context) this, "duetjoincompositebitmap");
            if (a != null) {
                this.f19922i.setImageBitmap(a);
            } else {
                new ImageUtils$ImageViewLoadOptimizer().m18975a(this.f19906K != null ? this.f19906K.coverUrl : this.f19908M.coverUrl, this.f19922i, C1947R.drawable.icn_default_album_xmedium);
            }
            ImageToDiskUtils.m25838b(this, "duetjoincompositebitmap");
            this.f19924k.setText(this.f19906K != null ? this.f19906K.title : this.f19908M.name);
            this.f19925l.setText(getString(C1947R.string.share_created_by, new Object[]{UserManager.a().i()}));
        }
        if (this.f19906K != null) {
            TextView textView;
            int i;
            if (this.f19917V) {
                textView = this.f19921h;
                i = (this.f19906K.d() || this.f19906K.e()) ? C1947R.string.share_dialog_cta_collab : C1947R.string.share_dialog_cta_solo;
                textView.setText(getString(i));
            } else {
                textView = this.f19921h;
                i = (this.f19906K.d() || this.f19906K.e()) ? C1947R.string.share_title_collab : C1947R.string.share_title_solo;
                textView.setText(getString(i));
            }
        }
        mo6611u();
        if (this.f19906K != null) {
            SingAnalytics.m26113a(PerformanceV2Util.m25947e(this.f19906K), this.f19906K.performanceKey, SingAnalytics.m26146e(this.f19906K), Analytics.m17828a(this.f19906K), SingAnalytics.m26140d(this.f19906K), this.f19906K.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO, this.f19917V ? ShareModuleType.DIALOG : ShareModuleType.PAGE);
        }
        if (this.f19909N == null && this.f19906K != null && this.f19906K.songUid != null) {
            StoreManager.m18378a().m18417a(this.f19906K.songUid, new C40882(this));
        }
    }

    protected void onStart() {
        boolean z = true;
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("sing_prefs", 0);
        boolean z2 = sharedPreferences.getBoolean("facebook.enabled", true);
        boolean z3 = sharedPreferences.getBoolean("twitter.enabled", true);
        Log.c(f19895g, "onStart : " + MagicFacebook.a().c() + " / " + this.f19897B);
        m21447d(z2);
        ToggleButton toggleButton = this.f19936w;
        if (!(MiscUtils.m25899b() && z3)) {
            z = false;
        }
        toggleButton.setChecked(z);
    }

    public void onResume() {
        super.onResume();
        MiscUtils.m25891a((Activity) this, false);
        if (!(this.f19917V || this.f19913R)) {
            Animator loadAnimator = AnimatorInflater.loadAnimator(this, C1947R.animator.share_visualizer);
            loadAnimator.setStartDelay(250);
            loadAnimator.setTarget(this.f19923j);
            ObjectAnimator.ofFloat(this.f19923j, "alpha", new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f}).setDuration(0).start();
            loadAnimator.start();
            ObjectAnimator.ofFloat(this.f19922i, "alpha", new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f}).setDuration(0).start();
            loadAnimator = AnimatorInflater.loadAnimator(this, C1947R.animator.pulse);
            loadAnimator.setTarget(this.f19922i);
            loadAnimator.start();
            this.f19913R = true;
        }
        if (this.f19914S != null) {
            SingAnalytics.m26099a(this.f19906K.performanceKey, this.f19914S, SingAnalytics.m26146e(this.f19906K), Analytics.m17828a(this.f19906K), SingAnalytics.m26140d(this.f19906K), this.f19906K.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO);
            this.f19914S = null;
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        Editor edit = getSharedPreferences("sing_prefs", 0).edit();
        if (MagicFacebook.a().c() && MagicFacebook.a().g()) {
            edit.putBoolean("facebook.enabled", this.f19935v.isChecked());
        }
        if (MiscUtils.m25899b()) {
            edit.putBoolean("twitter.enabled", this.f19936w.isChecked());
        }
        edit.apply();
    }

    protected void onDestroy() {
        super.onDestroy();
        m21452z();
        this.f19926m = null;
        this.f19928o = null;
        this.f19930q = null;
        this.f19935v = null;
        this.f19924k = null;
        this.f19931r = null;
        this.f19925l = null;
        this.f19923j = null;
        this.f19922i = null;
        this.f19936w = null;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (ChatUtils.a() && i == 42405 && i2 == -1) {
            List linkedList = new LinkedList();
            if (intent.hasExtra("RESULT.SELECTED_ACCOUNTS")) {
                Iterator it = intent.getParcelableArrayListExtra("RESULT.SELECTED_ACCOUNTS").iterator();
                while (it.hasNext()) {
                    linkedList.add(((AccountIcon) it.next()).handle);
                }
            }
            ChatManager j = SingApplication.j();
            if (intent.hasExtra("RESULT.SELECTED_CHATS")) {
                for (String a : intent.getStringArrayListExtra("RESULT.SELECTED_CHATS")) {
                    Chat a2 = j.a(a);
                    if (a2 != null) {
                        if (a2.mo6335a() == Type.PEER) {
                            AccountIcon R = ((PeerChat) a2).mo6333R();
                            if (R != null) {
                                linkedList.add(R.handle);
                            }
                        } else {
                            linkedList.add(((GroupChat) a2).mo6334S());
                        }
                    }
                }
            }
            if (!linkedList.isEmpty()) {
                m21455a(linkedList);
                return;
            }
            return;
        }
        this.f19916U.onActivityResult(i, i2, intent);
    }

    private void m21446c(final boolean z) {
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ ShareActivity f19885b;

            public void run() {
                this.f19885b.m21447d(z);
            }
        });
    }

    private void m21447d(boolean z) {
        Log.b(f19895g, "refreshFacebookToggle. isLoggedIn=" + MagicFacebook.a().c() + " hasPublishPermission=" + MagicFacebook.a().g());
        ToggleButton toggleButton = this.f19935v;
        boolean z2 = MagicFacebook.a().c() && MagicFacebook.a().g() && z;
        toggleButton.setChecked(z2);
        if (MagicFacebook.a().c() && this.f19897B) {
            MagicFacebook.a().f();
            MagicFacebook.a().a(this);
            this.f19897B = false;
        }
    }

    @Click
    protected void m21453a() {
        if (!SingApplication.g.booleanValue()) {
            if (this.f19935v.isChecked()) {
                m21443b(SocialChannel.FACEBOOK);
            }
            if (!MagicFacebook.a().c()) {
                this.f19935v.setChecked(false);
                this.f19897B = true;
                LoginManager.getInstance().logInWithReadPermissions(this, MagicNetwork.d().getFacebookReadPermissions());
            } else if (!MagicFacebook.a().g()) {
                this.f19935v.setChecked(false);
                MagicFacebook.a().a(this);
            }
        }
    }

    @Click
    protected void m21456b() {
        this.f19935v.performClick();
    }

    @Click
    protected void m21465q() {
        if (!SingApplication.g.booleanValue()) {
            if (this.f19936w.isChecked()) {
                m21443b(SocialChannel.TWITTER);
            }
            if (!MiscUtils.m25899b()) {
                this.f19936w.setChecked(false);
                startActivity(new Intent(this, TwitterOAuthActivity_.class));
            }
        }
    }

    @Click
    protected void m21466r() {
        this.f19936w.performClick();
    }

    @Click
    protected void m21454a(View view) {
        if (this.f19898C != null) {
            m21443b(SocialChannel.CHAT);
            startActivityForResult(this.f19898C, 42405);
        }
    }

    @Click
    protected void m21457b(View view) {
        if (this.f19903H != null) {
            m21445c((String) view.getTag());
            m21443b(SocialChannel.YOUTUBE);
            this.f19896A.setVisibility(8);
            m21440a(SocialChannel.YOUTUBE);
        }
    }

    private void m21440a(final SocialChannel socialChannel) {
        if (this.f19915T) {
            this.f19914S = socialChannel;
            startActivityForResult(this.f19903H, 42405);
            return;
        }
        this.f19938y.setText("0%");
        this.f19939z.setProgress(0);
        this.f19937x.setVisibility(0);
        this.f19918W = TracksManager.m18471a().m18481a(getApplicationContext(), this.f19906K.videoRenderedMp4Url, new VideoDownloadCallback(this) {
            final /* synthetic */ ShareActivity f19891b;

            class C40901 implements Runnable {
                final /* synthetic */ C40944 f19886a;

                C40901(C40944 c40944) {
                    this.f19886a = c40944;
                }

                public void run() {
                    this.f19886a.f19891b.f19937x.setVisibility(4);
                }
            }

            class C40912 implements Runnable {
                final /* synthetic */ C40944 f19887a;

                C40912(C40944 c40944) {
                    this.f19887a = c40944;
                }

                public void run() {
                    this.f19887a.f19891b.f19937x.setVisibility(4);
                }
            }

            class C40923 implements Runnable {
                final /* synthetic */ C40944 f19888a;

                C40923(C40944 c40944) {
                    this.f19888a = c40944;
                }

                public void run() {
                    this.f19888a.f19891b.f19937x.setVisibility(4);
                }
            }

            class C40934 implements Runnable {
                final /* synthetic */ C40944 f19889a;

                C40934(C40944 c40944) {
                    this.f19889a = c40944;
                }

                public void run() {
                    this.f19889a.f19891b.f19937x.setVisibility(4);
                }
            }

            public void mo6607a(Long l, Long l2) {
                if (!this.f19891b.isFinishing()) {
                    int longValue = (int) ((((float) l.longValue()) / ((float) l2.longValue())) * 100.0f);
                    this.f19891b.f19938y.setText(longValue + "%");
                    this.f19891b.f19939z.setProgress(longValue);
                }
            }

            public void mo6606a(int i) {
                this.f19891b.f19918W = null;
                if (!this.f19891b.isFinishing()) {
                    TextAlertDialog textAlertDialog;
                    switch (i) {
                        case 0:
                            this.f19891b.f19937x.setVisibility(4);
                            this.f19891b.f19915T = true;
                            this.f19891b.f19914S = socialChannel;
                            this.f19891b.startActivityForResult(this.f19891b.f19903H, 42405);
                            return;
                        case 1:
                            textAlertDialog = new TextAlertDialog(this.f19891b, (int) C1947R.string.share_not_enough_space_title, (int) C1947R.string.share_not_enough_space_desc);
                            textAlertDialog.m19806a(null, this.f19891b.getString(C1947R.string.core_ok));
                            textAlertDialog.m19804a(new C40901(this));
                            textAlertDialog.m19809b(new C40912(this));
                            textAlertDialog.show();
                            this.f19891b.f19937x.setVisibility(4);
                            return;
                        default:
                            textAlertDialog = new TextAlertDialog(this.f19891b, null, this.f19891b.getResources().getString(C1947R.string.songbook_error_connecting_to_network));
                            textAlertDialog.m19806a(null, this.f19891b.getString(C1947R.string.core_ok));
                            textAlertDialog.m19804a(new C40923(this));
                            textAlertDialog.m19809b(new C40934(this));
                            textAlertDialog.show();
                            this.f19891b.f19937x.setVisibility(4);
                            return;
                    }
                }
            }
        });
    }

    @Click
    protected void m21458c(View view) {
        if (this.f19900E != null) {
            m21445c((String) view.getTag());
            m21443b(SocialChannel.LINE);
            startActivity(this.f19900E);
        }
    }

    @Click
    protected void m21460d(View view) {
        if (this.f19899D != null) {
            m21445c((String) view.getTag());
            m21443b(SocialChannel.MESSENGER);
            try {
                startActivity(this.f19899D);
            } catch (Throwable e) {
                Log.c(f19895g, "Messenger activity not found", e);
            }
        }
    }

    @Click
    protected void m21461e(View view) {
        if (this.f19901F != null) {
            m21445c((String) view.getTag());
            m21443b(SocialChannel.SMS);
            startActivity(this.f19901F);
        }
    }

    @Click
    protected void m21462f(View view) {
        if (this.f19902G != null) {
            m21445c((String) view.getTag());
            m21443b(SocialChannel.EMAIL);
            startActivity(this.f19902G);
        }
    }

    @Click
    protected void m21463g(View view) {
        m21445c((String) view.getTag());
        m21443b(SocialChannel.COPY_LINK);
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(this.f19906K != null ? this.f19906K.title : this.f19908M.name, this.f19906K != null ? this.f19906K.u() : this.f19908M.b()));
        Toaster.a(this, getString(C1947R.string.share_copy_toast));
    }

    @Click
    protected void m21464h(View view) {
        m21445c((String) view.getTag());
        m21443b(SocialChannel.GENERIC);
        if (this.f19906K != null) {
            ShareUtils.m26024e(this, this.f19906K, this.f19909N);
        } else {
            ShareUtils.m26023e(this, this.f19908M);
        }
    }

    @Click
    protected void m21467s() {
        String u;
        Log.b(f19895g, "Facebook toggle checked: " + this.f19935v.isChecked());
        if (!SingApplication.g.booleanValue() && this.f19935v.isChecked()) {
            if (this.f19906K != null) {
                SingAnalytics.m26099a(this.f19906K.performanceKey, SocialChannel.FACEBOOK, SingAnalytics.m26146e(this.f19906K), Analytics.m17828a(this.f19906K), SingAnalytics.m26140d(this.f19906K), this.f19906K.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO);
            } else {
                SingAnalytics.m26070a(SocialChannel.FACEBOOK, this.f19908M.key);
            }
            MagicFacebook a = MagicFacebook.a();
            if (this.f19906K != null) {
                u = this.f19906K.u();
            } else {
                u = this.f19908M.b();
            }
            a.a(u, this.f19905J);
        }
        Log.b(f19895g, "Twitter toggle checked: " + this.f19936w.isChecked());
        if (!SingApplication.g.booleanValue() && this.f19936w.isChecked()) {
            StatusUpdate statusUpdate;
            if (this.f19906K != null) {
                SingAnalytics.m26099a(this.f19906K.performanceKey, SocialChannel.TWITTER, SingAnalytics.m26146e(this.f19906K), Analytics.m17828a(this.f19906K), SingAnalytics.m26140d(this.f19906K), this.f19906K.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO);
            } else {
                SingAnalytics.m26070a(SocialChannel.TWITTER, this.f19908M.key);
            }
            u = this.f19906K != null ? ShareUtils.m26011b(this, this.f19906K, this.f19909N) : ShareUtils.m26000a((Context) this, this.f19908M);
            if (this.f19906K != null) {
                statusUpdate = new StatusUpdate(MessageFormat.format(u, new Object[]{this.f19906K.title, this.f19906K.u()}));
            } else {
                statusUpdate = new StatusUpdate(MessageFormat.format(u, new Object[]{this.f19908M.name, this.f19908M.b()}));
            }
            MagicTwitter a2 = MiscUtils.m25882a();
            if (a2 != null) {
                a2.m18841a(statusUpdate, this.f19904I);
            }
        }
        m21468t();
    }

    public void onBackPressed() {
        if (this.f19918W == null || this.f19918W.isCancelled()) {
            m21468t();
            return;
        }
        this.f19918W.cancel(true);
        this.f19937x.setVisibility(4);
    }

    protected void m21468t() {
        finish();
    }

    protected void m21455a(List<String> list) {
    }

    private boolean m21448v() {
        return this.f19906K == null && this.f19908M != null;
    }

    @UiThread
    protected void mo6611u() {
        if (!isFinishing()) {
            this.f19904I = new C40955(this);
            this.f19905J = new C40966(this);
            PackageManager packageManager = getPackageManager();
            if (ChatUtils.a() && this.f19906K != null) {
                this.f19898C = ChatShareInviteActivity.m22648a((Context) this, this.f19906K, this.f19911P);
            }
            if (this.f19898C == null) {
                this.f19927n.setVisibility(8);
            }
            if (packageManager != null) {
                String a;
                String c;
                if (this.f19906K != null) {
                    a = ShareUtils.m26003a((Context) this, this.f19906K, this.f19909N);
                } else {
                    a = ShareUtils.m26010b(this, this.f19908M);
                }
                this.f19899D = ShareUtils.m25997a(a);
                if (this.f19899D == null) {
                    this.f19926m.removeView(this.f19929p);
                }
                this.f19900E = ShareUtils.m26008b(a);
                if (this.f19900E == null) {
                    this.f19926m.removeView(this.f19928o);
                }
                this.f19901F = ShareUtils.m26013c(a);
                if (this.f19901F == null) {
                    this.f19926m.removeView(this.f19930q);
                }
                if (this.f19906K != null) {
                    c = ShareUtils.m26016c(this, this.f19906K, this.f19909N);
                    a = ShareUtils.m26021d(this, this.f19906K, this.f19909N);
                } else {
                    c = ShareUtils.m26015c(this, this.f19908M);
                    a = ShareUtils.m26020d(this, this.f19908M);
                }
                this.f19902G = ShareUtils.m25998a(c, a);
                if (this.f19902G == null) {
                    this.f19926m.removeView(this.f19931r);
                }
                if (this.f19906K == null || this.f19906K.videoRenderedMp4Url == null) {
                    this.f19926m.removeView(this.f19932s);
                } else {
                    this.f19903H = ShareUtils.m26025f(this);
                    if (this.f19903H == null) {
                        this.f19926m.removeView(this.f19932s);
                    }
                }
            }
            this.f19919X = m21449w();
            m21451y();
        }
    }

    private void m21443b(SocialChannel socialChannel) {
        VideoStatusType videoStatusType = null;
        Share share = socialChannel == SocialChannel.GENERIC ? Share.MORE : Share.BASIC;
        String d = this.f19908M == null ? SingAnalytics.m26140d(this.f19906K) : this.f19908M.key;
        String str = this.f19906K == null ? null : this.f19906K.performanceKey;
        PerformanceStatus e = this.f19906K == null ? null : SingAnalytics.m26146e(this.f19906K);
        Ensemble a = this.f19906K == null ? null : Analytics.m17828a(this.f19906K);
        if (this.f19906K != null) {
            videoStatusType = this.f19906K.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO;
        }
        if (socialChannel == SocialChannel.CHAT) {
            SingAnalytics.m26096a(str, e, a, d, videoStatusType, socialChannel);
        } else if (this.f19906K != null) {
            SingAnalytics.m26114a(str, d, socialChannel, share, e, a, videoStatusType);
        } else {
            SingAnalytics.m26100a(d, socialChannel, share);
        }
    }

    private List<ButtonRank> m21449w() {
        try {
            List<ButtonRank> a = JsonUtils.m18990a(getSharedPreferences("sing_prefs", 0).getString("share_buttons_rank", ""), new C40977(this));
        } catch (Exception e) {
            Object arrayList = new ArrayList();
        }
        if (a.size() != this.f19926m.getChildCount()) {
            return m21450x();
        }
        Collections.sort(a);
        for (ButtonRank buttonRank : a) {
            if (buttonRank.tag.equals("chat")) {
                buttonRank.nice = Integer.MIN_VALUE;
                return a;
            }
        }
        return a;
    }

    private List<ButtonRank> m21450x() {
        List<ButtonRank> arrayList = new ArrayList();
        int childCount = this.f19926m.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ButtonRank buttonRank = new ButtonRank((String) this.f19926m.getChildAt(i).getTag(), i * 10);
            if (buttonRank.tag.equals("chat")) {
                buttonRank.nice = Integer.MIN_VALUE;
            }
            arrayList.add(buttonRank);
        }
        return arrayList;
    }

    private void m21451y() {
        Map hashMap = new HashMap();
        int childCount = this.f19926m.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.f19926m.getChildAt(i);
            hashMap.put((String) childAt.getTag(), childAt);
        }
        this.f19926m.removeAllViews();
        for (ButtonRank buttonRank : this.f19919X) {
            View view = (View) hashMap.get(buttonRank.tag);
            if (view != null) {
                this.f19926m.addView(view);
            }
        }
    }

    private void m21445c(String str) {
        this.f19920Y.add(str);
    }

    private void m21452z() {
        for (ButtonRank buttonRank : this.f19919X) {
            if (this.f19920Y.contains(buttonRank.tag)) {
                buttonRank.nice -= 11;
            }
        }
        Collections.sort(this.f19919X);
        try {
            getSharedPreferences("sing_prefs", 0).edit().putString("share_buttons_rank", JsonUtils.m18984a().writeValueAsString(this.f19919X)).apply();
        } catch (Throwable e) {
            Log.d(f19895g, "Failed to save rankings", e);
        }
    }
}
