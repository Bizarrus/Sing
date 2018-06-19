package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Analytics.SocialChannel;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.InviteManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResourceInfo$ResourceType;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.managers.PerformanceManager.PreuploadResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.TracksManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.StringCacheManager;
import com.smule.android.utils.UiAwareRunnable;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.audio.GlitchType;
import com.smule.singandroid.customviews.BubbleTooltipViewWithDropShadow;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.DeleteRecordingConfirmationDialog;
import com.smule.singandroid.dialogs.ProgressBarDialog;
import com.smule.singandroid.dialogs.ProgressBarDialog.ProgressBarDialogInterface;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.PerformanceCreateUtil;
import com.smule.singandroid.utils.PerformanceCreateUtil.PerformanceCreateListener;
import com.smule.singandroid.utils.PerformanceCreateUtil.ResourceCompressionListener;
import com.smule.singandroid.utils.PerformanceCreateUtil.ResourceUploadListener;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.AudioCompletionContext;
import com.smule.singandroid.utils.SingAnalytics.InviteType;
import com.smule.singandroid.utils.SingAnalytics.ReviewStepsType;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EFragment
public class PerformanceSaveFragment extends PhotoTakingFragment {
    public static final String f19187e = PerformanceSaveFragment.class.getName();
    @InstanceState
    protected PostSingBundle f19188A;
    @InstanceState
    protected String f19189B = null;
    @InstanceState
    protected boolean f19190C = false;
    @InstanceState
    protected boolean f19191D = false;
    @InstanceState
    protected String f19192E = null;
    @InstanceState
    protected boolean f19193F = false;
    @InstanceState
    protected Long f19194G = null;
    @InstanceState
    protected String f19195H = null;
    @InstanceState
    protected boolean f19196I = false;
    @InstanceState
    protected boolean f19197J = false;
    @InstanceState
    protected boolean f19198K = false;
    @InstanceState
    protected PerformanceV2 f19199L;
    @InstanceState
    protected boolean f19200M = false;
    @InstanceState
    protected String f19201N;
    @InstanceState
    protected String f19202O;
    @InstanceState
    protected String f19203P;
    @InstanceState
    protected String f19204Q;
    @InstanceState
    protected String f19205R;
    @InstanceState
    protected String f19206S;
    @InstanceState
    protected Float f19207T;
    @InstanceState
    protected Float f19208U;
    @InstanceState
    protected boolean f19209V;
    @InstanceState
    protected String f19210W;
    @InstanceState
    protected boolean f19211X;
    @InstanceState
    protected int f19212Y;
    @InstanceState
    protected float f19213Z;
    @InstanceState
    protected Mode aa;
    @InstanceState
    protected SongbookEntry ab;
    @InstanceState
    protected boolean ac = false;
    @InstanceState
    protected boolean ad;
    @InstanceState
    protected Bundle ae;
    @InstanceState
    protected boolean af;
    @InstanceState
    protected boolean ag;
    @InstanceState
    protected boolean ah = false;
    @InstanceState
    protected String ai = null;
    protected SingBundle aj;
    protected Future<PreuploadResponse> ak;
    protected boolean al;
    protected Bitmap am;
    protected PerformanceCreateUtil an;
    protected OnGlobalLayoutListener ao = new OnGlobalLayoutListener(this, new C39231(this));
    protected final Runnable ap = new Runnable(this) {
        final /* synthetic */ PerformanceSaveFragment f19144a;

        {
            this.f19144a = r1;
        }

        public void run() {
            SingAnalytics.m26060a(this.f19144a.aj.f20154s, AudioCompletionContext.REVIEW_EXIT, Float.valueOf(this.f19144a.aj.f20155t), null, this.f19144a.ad(), null, this.f19144a.f19204Q, this.f19144a.f19205R, this.f19144a.f19206S, DeviceSettings.n(), DeviceSettings.h());
            if (this.f19144a.aj.f20158w != GlitchType.NONE) {
                SingAnalytics.m26109a(null, this.f19144a.aj.f20158w, HeadphonesType.m17502a(this.f19144a.f19197J, this.f19144a.f19198K));
            }
            this.f19144a.f19188A.f19319e = true;
            this.f19144a.m20773b(false);
        }
    };
    protected final AreYouSureDialogListener aq = new AreYouSureDialogListener(this);
    protected final CustomAlertDialogListener ar = new CustomAlertDialogListener(this) {
        final /* synthetic */ PerformanceSaveFragment f19145a;

        {
            this.f19145a = r1;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            this.f19145a.m20737d("showProgressBarDialog - onBackOrCancelButton");
        }
    };
    private BusyDialog as;
    private TextAlertDialog at;
    private ProgressBarDialog au;
    private TextAlertDialog av;
    private boolean aw = false;
    private final ResourceCompressionListener ax = new ResourceCompressionListener(this) {
        final /* synthetic */ PerformanceSaveFragment f19147a;

        {
            this.f19147a = r1;
        }

        public void mo6387a() {
            if (this.f19147a.au != null) {
                this.f19147a.au.setTitle(this.f19147a.getString(C1947R.string.core_saving));
            }
        }

        public void mo6388a(String str) {
            this.f19147a.ai = str;
            this.f19147a.ai();
        }

        public void mo6389b() {
            this.f19147a.ah();
        }
    };
    private final ResourceUploadListener ay = new ResourceUploadListener(this) {
        final /* synthetic */ PerformanceSaveFragment f19148a;

        {
            this.f19148a = r1;
        }

        public void mo6390a() {
            if (this.f19148a.au != null) {
                this.f19148a.au.setTitle(this.f19148a.getString(C1947R.string.core_saving));
                this.f19148a.au.m23681a(10);
            }
        }

        public void mo6391a(long j) {
            this.f19148a.f19194G = Long.valueOf(j);
            this.f19148a.ai();
        }

        public void mo6392b() {
            this.f19148a.ah();
        }
    };
    private boolean az;
    @ViewById
    protected Button f19214f;
    @ViewById
    protected ImageView f19215g;
    @ViewById
    protected TextView f19216h;
    @ViewById
    protected ViewGroup f19217i;
    @ViewById
    protected EditText f19218j;
    @ViewById
    protected ImageView f19219k;
    @ViewById
    protected ImageView f19220l;
    @ViewById
    protected EditText f19221m;
    @ViewById
    protected ViewGroup f19222n;
    @ViewById
    protected TextView f19223o;
    @ViewById
    protected TextView f19224p;
    @ViewById
    protected ToggleButton f19225q;
    @ViewById
    protected ViewGroup f19226r;
    @ViewById
    protected ViewGroup f19227s;
    @ViewById
    protected ToggleButton f19228t;
    @ViewById
    protected TextView f19229u;
    @ViewById
    protected View f19230v;
    @ViewById
    protected TextView f19231w;
    @ViewById
    protected TextView f19232x;
    @ViewById
    protected BubbleTooltipViewWithDropShadow f19233y;
    @ViewById
    protected BubbleTooltipViewWithDropShadow f19234z;

    private interface InviteCompleteCallback {
        void mo6507a();

        void mo6508b();
    }

    class C39231 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ PerformanceSaveFragment f19150a;

        C39231(PerformanceSaveFragment performanceSaveFragment) {
            this.f19150a = performanceSaveFragment;
        }

        public void onGlobalLayout() {
            if (this.f19150a.isAdded() && this.f19150a.aa == Mode.Create) {
                this.f19150a.f19233y.setAnchoringView(this.f19150a.f19225q);
                this.f19150a.f19234z.setAnchoringView(this.f19150a.f19228t);
            }
        }
    }

    class C39262 implements TextWatcher {
        final /* synthetic */ PerformanceSaveFragment f19163a;

        C39262(PerformanceSaveFragment performanceSaveFragment) {
            this.f19163a = performanceSaveFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.f19163a.f19214f.setAlpha(this.f19163a.f19218j.getText().toString().length() == 0 ? 0.5f : DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
    }

    class C39273 implements UiAwareRunnable {
        final /* synthetic */ PerformanceSaveFragment f19164a;

        C39273(PerformanceSaveFragment performanceSaveFragment) {
            this.f19164a = performanceSaveFragment;
        }

        public void mo6509a(boolean z) {
            if (z && this.f19164a.mo6529U()) {
                this.f19164a.f19233y.m23143a();
                this.f19164a.f19234z.m23144b();
            }
        }
    }

    class C39284 implements UiAwareRunnable {
        final /* synthetic */ PerformanceSaveFragment f19165a;

        C39284(PerformanceSaveFragment performanceSaveFragment) {
            this.f19165a = performanceSaveFragment;
        }

        public void mo6509a(boolean z) {
            if (z && this.f19165a.mo6529U()) {
                this.f19165a.f19233y.m23144b();
                if (!this.f19165a.f19228t.isChecked()) {
                    this.f19165a.f19234z.m23143a();
                }
            }
        }
    }

    class C39295 implements UiAwareRunnable {
        final /* synthetic */ PerformanceSaveFragment f19166a;

        C39295(PerformanceSaveFragment performanceSaveFragment) {
            this.f19166a = performanceSaveFragment;
        }

        public void mo6509a(boolean z) {
            if (z && this.f19166a.mo6529U()) {
                this.f19166a.f19234z.m23143a();
            }
        }
    }

    class C39306 implements UiAwareRunnable {
        final /* synthetic */ PerformanceSaveFragment f19167a;

        C39306(PerformanceSaveFragment performanceSaveFragment) {
            this.f19167a = performanceSaveFragment;
        }

        public void mo6509a(boolean z) {
            if (z && this.f19167a.mo6529U()) {
                this.f19167a.f19234z.m23144b();
            }
        }
    }

    class C39327 implements OnClickListener {
        final /* synthetic */ PerformanceSaveFragment f19169a;

        class C39311 implements Runnable {
            final /* synthetic */ C39327 f19168a;

            C39311(C39327 c39327) {
                this.f19168a = c39327;
            }

            public void run() {
                this.f19168a.f19169a.mo6521a(6802, this.f19168a.f19169a.f19199L);
            }
        }

        C39327(PerformanceSaveFragment performanceSaveFragment) {
            this.f19169a = performanceSaveFragment;
        }

        public void onClick(View view) {
            NavigationUtils.m25907a(this.f19169a.getActivity(), this.f19169a.f19199L, null, new C39311(this), null, true);
        }
    }

    class C39338 implements OnClickListener {
        final /* synthetic */ PerformanceSaveFragment f19170a;

        C39338(PerformanceSaveFragment performanceSaveFragment) {
            this.f19170a = performanceSaveFragment;
        }

        public void onClick(View view) {
            this.f19170a.mo6521a(6804, this.f19170a.f19199L);
        }
    }

    class C39369 implements OnClickListener {
        final /* synthetic */ PerformanceSaveFragment f19173a;

        class C39351 implements Runnable {
            final /* synthetic */ C39369 f19172a;

            class C39341 implements PerformanceManager$PerformanceResponseCallback {
                final /* synthetic */ C39351 f19171a;

                C39341(C39351 c39351) {
                    this.f19171a = c39351;
                }

                public void handleResponse(PerformanceResponse performanceResponse) {
                    this.f19171a.f19172a.f19173a.mo6515H();
                    boolean a = performanceResponse.a();
                    if (performanceResponse.a.e()) {
                        ((BaseActivity) this.f19171a.f19172a.f19173a.getActivity()).a(performanceResponse.a.f, true, null);
                        return;
                    }
                    String string;
                    PerformanceSaveFragment performanceSaveFragment = this.f19171a.f19172a.f19173a;
                    if (a) {
                        string = this.f19171a.f19172a.f19173a.getString(C1947R.string.performance_open_call_close);
                    } else {
                        string = this.f19171a.f19172a.f19173a.getString(C1947R.string.performance_open_call_error);
                    }
                    performanceSaveFragment.m19849b(string);
                    if (a) {
                        this.f19171a.f19172a.f19173a.mo6521a(6801, this.f19171a.f19172a.f19173a.f19199L);
                    }
                }
            }

            C39351(C39369 c39369) {
                this.f19172a = c39369;
            }

            public void run() {
                this.f19172a.f19173a.mo6523c(this.f19172a.f19173a.getResources().getString(C1947R.string.closing_open_call));
                PerformanceManager.a().a(this.f19172a.f19173a.f19199L.performanceKey, null, null, null, Boolean.valueOf(true), new C39341(this));
            }
        }

        C39369(PerformanceSaveFragment performanceSaveFragment) {
            this.f19173a = performanceSaveFragment;
        }

        public void onClick(View view) {
            this.f19173a.at = new TextAlertDialog(this.f19173a.getActivity(), this.f19173a.getString(C1947R.string.core_are_you_sure), this.f19173a.getString(C1947R.string.performance_delete_open));
            this.f19173a.at.m19806a(this.f19173a.getString(C1947R.string.performance_save_close_now), this.f19173a.getString(C1947R.string.core_cancel));
            this.f19173a.at.m19804a(new C39351(this));
            this.f19173a.at.show();
        }
    }

    class AreYouSureDialogListener implements CustomAlertDialogListener {
        final /* synthetic */ PerformanceSaveFragment f19174a;
        private boolean f19175b;

        AreYouSureDialogListener(PerformanceSaveFragment performanceSaveFragment) {
            this.f19174a = performanceSaveFragment;
        }

        public void m20702a(boolean z) {
            this.f19175b = z;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            if (this.f19175b) {
                this.f19174a.m20737d("mAreYouSureDialogListener: onOkButton");
            }
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            this.f19174a.ap.run();
        }
    }

    public enum Mode {
        Create,
        Edit
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        super.onCreate(bundle);
        if (this.f19188A != null) {
            this.aj = this.f19188A.f19316b;
        }
        this.an = new PerformanceCreateUtil(this.f19194G, this.ai);
        if (bundle == null) {
            Log.b(f19187e, "onCreate - no saved instance state");
            Bundle arguments = getArguments();
            this.f19199L = (PerformanceV2) arguments.getParcelable("PERFORMANCE_SAVE_PERFORMANCE_KEY");
            this.f19189B = this.f19199L != null ? this.f19199L.performanceKey : null;
            this.aa = this.f19199L == null ? Mode.Create : Mode.Edit;
            this.f19210W = arguments.getString("RECORDING_FILE_EXTRA_KEY");
            this.f19211X = arguments.getBoolean("PITCH_CORRECT_EXTRA_KEY", false);
            this.f19212Y = arguments.getInt("SCORE_EXTRA_KEY", 0);
            this.f19213Z = arguments.getFloat("USER_GAIN_DB", DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f19197J = arguments.getBoolean("USED_HEADPHONE", false);
            this.f19198K = arguments.getBoolean("HEADPHONE_HAD_MIC", false);
            this.ae = arguments;
        } else {
            Log.b(f19187e, "onCreate - restoring from saved instance state");
        }
        if (this.aa == Mode.Create) {
            boolean z2;
            Log.b(f19187e, this.aj.toString());
            this.ab = this.aj.f20139d;
            this.al = PerformanceV2Util.m25941a(m20747E());
            this.ad = this.aj.f20146k;
            if (this.aj.m21643a() || this.aj.m21648b()) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.ah = z2;
            Log.b(f19187e, "Performance was a join: " + this.ad);
            this.f19201N = getArguments().getString("EFFECT_PRESET");
            this.f19202O = getArguments().getString("FX_INITIAL");
            this.f19203P = getArguments().getString("FX_SELECTED");
            this.f19204Q = getArguments().getString("FXS_UNIQUE_REVIEW");
            this.f19205R = getArguments().getString("ADJUSTED_SLIDER");
            this.f19206S = getArguments().getString("PLAY_PAUSE_COUNT");
            if (this.f19201N == null || this.f19201N.isEmpty()) {
                this.f19201N = "****";
            }
            this.f19207T = Float.valueOf(getArguments().getFloat("META_PARAM_1", -1.0f));
            this.f19208U = Float.valueOf(getArguments().getFloat("META_PARAM_2", -1.0f));
            if (this.f19207T.floatValue() == -1.0f) {
                this.f19207T = null;
            }
            if (this.f19208U.floatValue() == -1.0f) {
                this.f19208U = null;
            }
            this.f19209V = getArguments().getBoolean("PRESET_VIP_EXTRA_KEY");
            if (this.f19192E == null || this.f19192E.isEmpty()) {
                this.f19192E = SongbookEntryUtils.m26164a(this.ab, this.aj.m21659h());
            }
        } else {
            this.f19193F = true;
            this.f19196I = this.f19199L.isPrivate;
            if (this.f19199L.d() || this.f19199L.e()) {
                z = true;
            }
            this.ah = z;
            this.f19192E = this.f19199L.coverUrl;
            this.al = PerformanceV2Util.m25941a(this.f19199L.songUid);
        }
        if (this.ab != null && this.ab.m18773s() && ((ListingEntry) this.ab).f17626a.song == null) {
            Log.d(f19187e, "onCreate - at the end, song was null!");
            if (!(this.f19199L == null || this.f19199L.songUid == null)) {
                ((ListingEntry) this.ab).f17626a.song = StoreManager.m18378a().m18416a(this.f19199L.songUid);
            }
            if (((ListingEntry) this.ab).f17626a.song == null) {
                Log.e(f19187e, "onCreate - mSong was not able to be set to non-null!");
            }
        }
    }

    @AfterViews
    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    protected void mo6510a() {
        CharSequence string;
        getActivity().getWindow().setSoftInputMode(48);
        aa();
        this.f19230v.setVisibility(8);
        mo6526G();
        if (!this.f19193F) {
            ag();
        }
        if (this.al) {
            WeakListener.m19083a(this.f19218j, new C39262(this));
            this.f19214f.setAlpha(this.f19218j.getText().toString().length() == 0 ? 0.5f : DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        } else {
            this.f19217i.setVisibility(8);
        }
        m20720Z();
        if (this.ah) {
            string = getString(C1947R.string.performance_save_video_hint_say_something_collab);
        } else {
            string = getString(C1947R.string.performance_save_video_hint_say_something_solo);
        }
        this.f19221m.setHint(string);
        if (this.aa == Mode.Create) {
            if (this.al) {
                this.f19218j.requestFocus();
                MiscUtils.m25890a(getActivity(), this.f19218j);
            } else {
                this.f19221m.requestFocus();
                MiscUtils.m25890a(getActivity(), this.f19221m);
            }
            this.f19216h.setText(C1947R.string.performance_customize);
            this.f19233y.setAnchoringView(this.f19225q);
            this.f19233y.setColor(getResources().getColor(C1947R.color.chat_no_follow_banner_bg));
            this.f19233y.setText(C1947R.string.performance_save_tooltip_private);
            this.f19233y.setSharedPreferenceKey("TOOLTIP_PRIVACY");
            this.f19234z.setAnchoringView(this.f19228t);
            this.f19234z.setColor(getResources().getColor(C1947R.color.chat_no_follow_banner_bg));
            this.f19234z.setText(C1947R.string.performance_save_tooltip_invite);
            this.f19234z.setSharedPreferenceKey("TOOLTIP_INVITE");
            LayoutUtils.m25854a(this.f19221m, this.ao);
            if (mo6529U()) {
                this.f19221m.setHint(C1947R.string.performance_save_hint_message);
            }
            if (this.ad) {
                Log.b(f19187e, "updateFollowingViewBinding: Create mode, is a join");
                this.f19220l.setVisibility(4);
                this.f19225q.setClickable(false);
                this.f19225q.setOnTouchListener(null);
                this.f19225q.setAlpha(0.5f);
                this.f19221m.setEnabled(false);
                this.f19221m.setHint("");
                this.f19218j.setEnabled(false);
                PerformanceV2 performanceV2 = this.aj.f20141f;
                if (performanceV2 != null) {
                    this.f19218j.setText(performanceV2.title);
                } else if (this.ab != null) {
                    this.f19218j.setText(m20746D());
                }
                m20737d("updateFollowingViewBinding: auto-create for joins");
            } else {
                Log.b(f19187e, "updateFollowingViewBinding: Create mode, is not a join");
                this.f19220l.setVisibility(0);
                this.f19221m.setEnabled(true);
                if (this.aj.f20153r.longValue() >= 0) {
                    String b = MagicPreferences.m20312b(getActivity(), "LAST_PROMOTION_HASHTAG_PAIR", null);
                    if (b == null) {
                        Log.d(f19187e, "Hashtag was lost somehow.  Shouldn't happen, but can proceed without it.");
                    } else {
                        String[] split = b.split(",");
                        if (this.aj.f20153r.equals(Long.valueOf(Long.parseLong(split[0])))) {
                            this.f19221m.setText("#" + split[1] + " ");
                            this.f19221m.setSelection(this.f19221m.getText().length());
                        }
                    }
                }
                if (this.al) {
                    this.f19218j.setEnabled(true);
                    this.f19218j.setHint(getString(C1947R.string.performance_save_hint_song_title));
                } else {
                    this.f19218j.setText(m20746D());
                    this.f19218j.setEnabled(false);
                }
            }
            if (this.f19191D) {
                mo6516J();
            }
        }
        if (this.f19199L != null) {
            this.f19230v.setVisibility(0);
            string = getString(C1947R.string.performance_save_recorded, new Object[]{MiscUtils.m25886a((long) this.f19199L.createdAt, false, false)});
            if (this.f19199L.seed) {
                Log.b(f19187e, "updateFollowingViewBinding - for seed, performance is closed: " + this.f19199L.closed);
                if (this.f19199L.closed) {
                    this.f19231w.setText(string);
                    if (PerformanceV2Util.m25940a(this.f19199L)) {
                        this.f19232x.setText(getString(C1947R.string.delete_performance));
                    } else {
                        this.f19232x.setVisibility(4);
                    }
                    ab();
                } else {
                    this.f19231w.setText(getString(C1947R.string.open_call_closes_in_x_time_left, new Object[]{MiscUtils.m25886a(this.f19199L.expireAt, false, false)}));
                    this.f19232x.setText(getString(C1947R.string.performance_save_close_now));
                    ac();
                }
                this.f19218j.setText(this.f19199L.title);
                this.f19221m.setText(this.f19199L.message);
                return;
            }
            ab();
            this.f19218j.setText(this.f19199L.title);
            this.f19221m.setText(this.f19199L.message);
            this.f19231w.setText(string);
            this.f19232x.setText(getString(C1947R.string.delete_performance));
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        LayoutUtils.m25859b(this.f19221m, this.ao);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            Log.e(f19187e, "Bad result code, " + i2 + ", returned for request code: " + i);
            return;
        }
        switch (i) {
            case 2202:
                if (intent.getExtras() != null) {
                    Bitmap b = m20711b(intent);
                    if (b != null) {
                        this.f19219k.setImageBitmap(b);
                        String str = "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
                        ImageToDiskUtils.m25836a(getActivity(), str, b);
                        this.am = b;
                        this.f19195H = str;
                        this.f19200M = true;
                        mo6528T();
                        return;
                    }
                    Log.e(f19187e, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean mo6400c() {
        if (this.aa != Mode.Create || this.f19191D) {
            mo6521a(6804, this.f19199L);
        } else {
            ae();
        }
        return true;
    }

    @Click
    protected void mo6514z() {
        switch (this.aa) {
            case Create:
                m20737d("mSavePerformanceButton - View.OnClickListener");
                return;
            case Edit:
                af();
                return;
            default:
                throw new RuntimeException("Unhandled mode: " + this.aa);
        }
    }

    @Click
    protected void m20743A() {
        this.f19225q.toggle();
    }

    @CheckedChange
    protected void m20766a(CompoundButton compoundButton, boolean z) {
        int i;
        MiscUtils.m25891a(getActivity(), true);
        if (z) {
            this.f19223o.setText(getResources().getString(C1947R.string.performance_save_public));
            if (this.ah) {
                i = C1947R.string.duet_group_save_public_description;
            } else {
                i = C1947R.string.performance_save_public_description;
            }
            this.f19224p.setText(getResources().getString(i));
            this.f19196I = false;
            m20717W();
        } else {
            this.f19223o.setText(getResources().getString(C1947R.string.performance_save_private));
            if (this.ah) {
                i = C1947R.string.duet_group_save_private_description;
            } else {
                i = C1947R.string.solo_save_private_description;
            }
            this.f19224p.setText(getResources().getString(i));
            this.f19196I = true;
            mo6530V();
        }
        ViewGroup viewGroup = this.f19226r;
        i = (this.ah && z) ? 0 : 8;
        viewGroup.setVisibility(i);
    }

    private boolean mo6529U() {
        return this.aj != null && ((this.aj.m21643a() || this.aj.m21648b()) && !this.aj.m21652c());
    }

    private void mo6530V() {
        new UiHandler(this.f19225q).m19082b(new C39273(this));
    }

    private void m20717W() {
        new UiHandler(this.f19225q).m19082b(new C39284(this));
    }

    private void m20718X() {
        new UiHandler(this.f19225q).m19082b(new C39295(this));
    }

    private void m20719Y() {
        new UiHandler(this.f19225q).m19082b(new C39306(this));
    }

    @Click
    protected void m20744B() {
        if (!this.af) {
            this.f19228t.toggle();
        }
    }

    @CheckedChange
    protected void m20771b(CompoundButton compoundButton, boolean z) {
        MiscUtils.m25891a(getActivity(), true);
        if (z) {
            m20719Y();
        } else {
            m20718X();
        }
    }

    @Click
    protected void m20745C() {
        MiscUtils.m25891a(getActivity(), true);
        ae();
    }

    protected String m20746D() {
        if (this.ab != null) {
            return this.ab.mo6291e();
        }
        return "";
    }

    protected String m20747E() {
        return SongbookEntry.m18752b(this.ab);
    }

    protected boolean mo6525F() {
        return false;
    }

    protected void mo6526G() {
        if (this.f19195H != null) {
            this.am = ImageToDiskUtils.m25830a(getActivity(), this.f19195H);
            if (this.am != null) {
                this.f19219k.setImageBitmap(this.am);
                return;
            } else {
                ImageUtils.a(this.f19192E, this.f19219k, C1947R.drawable.icn_default_album_small);
                return;
            }
        }
        ImageUtils.a(this.f19192E, this.f19219k, C1947R.drawable.icn_default_album_small);
    }

    private void m20720Z() {
        boolean z = true;
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        if (this.f19199L != null) {
            this.f19225q.setChecked(!this.f19196I);
            boolean l = this.f19199L.l();
            this.f19225q.setEnabled(l);
            this.f19222n.setAlpha(l ? DefaultRetryPolicy.DEFAULT_BACKOFF_MULT : 0.5f);
            if (this.f19199L.k()) {
                this.af = StringCacheManager.a().f(this.f19199L.performanceKey);
                this.f19228t.setChecked(this.af);
                ToggleButton toggleButton = this.f19228t;
                if (this.af) {
                    z = false;
                }
                toggleButton.setEnabled(z);
                this.f19229u.setText(getString(this.af ? C1947R.string.invite_all_followers_already_invited : C1947R.string.invite_all_followers_text));
                ViewGroup viewGroup = this.f19227s;
                if (!this.f19228t.isEnabled()) {
                    f = 0.5f;
                }
                viewGroup.setAlpha(f);
            } else {
                this.f19226r.setVisibility(8);
            }
        }
        m20766a(null, this.f19225q.isChecked());
        m20771b(null, this.f19228t.isChecked());
    }

    private void aa() {
        super.m20708a(this.f19219k, this.f19219k, false, HttpResponseCode.BAD_REQUEST, HttpResponseCode.BAD_REQUEST, SingPermissionRequests.f23949c);
    }

    private void ab() {
        this.f19232x.setOnClickListener(new C39327(this));
        this.f19215g.setOnClickListener(new C39338(this));
    }

    private void ac() {
        this.f19232x.setOnClickListener(new C39369(this));
        this.f19215g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PerformanceSaveFragment f19134a;

            {
                this.f19134a = r1;
            }

            public void onClick(View view) {
                this.f19134a.mo6521a(6804, this.f19134a.f19199L);
            }
        });
    }

    private String ad() {
        return null + "," + this.f19202O + "," + this.f19201N;
    }

    private void ae() {
        mo6518L();
    }

    private void m20737d(String str) {
        Log.b(f19187e, "savePerformance - called from source: " + str);
        if (!this.ag) {
            SingAnalytics.m26129b(m20747E(), this.aj.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.f19197J, this.f19198K), this.f19201N, this.ad, this.aj.f20137b.m21631a(), m20758P(), this.aj.f20141f != null ? Boolean.valueOf(this.aj.f20141f.video) : null, mo6525F());
            this.ag = true;
        }
        CharSequence obj = this.f19218j.getText().toString();
        if (this.al && !this.ad && TextUtils.getTrimmedLength(obj) == 0) {
            m19849b(getResources().getString(C1947R.string.performance_save_title_required));
        } else if (this.f19193F) {
            mo6527Q();
        } else if (this.aw) {
            m20751I();
        } else {
            ag();
            m20751I();
        }
    }

    private void af() {
        String obj;
        boolean z = false;
        String obj2 = this.f19221m.getText().toString();
        if (this.al) {
            obj = this.f19218j.getText().toString();
            if (TextUtils.getTrimmedLength(obj) == 0) {
                m19849b(getResources().getString(C1947R.string.performance_save_title_required));
                return;
            }
        }
        obj = this.f19199L.title;
        boolean z2 = this.am != null;
        if (!(obj.equals(this.f19199L.title) && obj2.equals(this.f19199L.message) && this.f19196I == this.f19199L.isPrivate && (!this.f19199L.k() || this.af == this.f19228t.isChecked()))) {
            z = true;
        }
        if (z2 || z) {
            mo6523c(getString(C1947R.string.core_saving));
            m20728a(z2, z, obj, obj2);
            return;
        }
        mo6521a(6804, this.f19199L);
    }

    private void m20728a(boolean z, boolean z2, String str, String str2) {
        final boolean z3 = z;
        final boolean z4 = z2;
        final String str3 = str;
        final String str4 = str2;
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment f19139e;

            public void run() {
                NetworkResponse a;
                if (z3) {
                    PreuploadResponse a2 = PerformanceManager.a().a(this.f19139e.f19189B, this.f19139e instanceof PerformanceSaveVideoFragment);
                    if (a2.a()) {
                        ArrayList arrayList = a2.mResources;
                        if (arrayList == null) {
                            this.f19139e.m19846b((int) C1947R.string.performance_update_error);
                            ((PerformanceSaveActivity) this.f19139e.getActivity()).m20666q();
                            this.f19139e.mo6515H();
                            return;
                        }
                        a = this.f19139e.m20722a(arrayList);
                        if (a != null) {
                            this.f19139e.m20736d(a);
                            this.f19139e.mo6515H();
                            return;
                        }
                    }
                    this.f19139e.m20736d(a2.a);
                    this.f19139e.mo6515H();
                    return;
                }
                if (z4) {
                    a = this.f19139e.m20763a(str3, str4);
                    if (a != null) {
                        this.f19139e.m20736d(a);
                        this.f19139e.mo6515H();
                        return;
                    }
                    return;
                }
                this.f19139e.mo6515H();
                this.f19139e.mo6521a(6803, null);
            }
        });
    }

    protected NetworkResponse m20763a(String str, String str2) {
        PerformanceResponse a = PerformanceManager.a().a(this.f19199L.performanceKey, str, str2, Boolean.valueOf(this.f19196I), null);
        if (!a.a()) {
            return a.a;
        }
        final PerformanceV2 performanceV2 = a.mPerformance;
        this.f19192E = performanceV2.coverUrl;
        m19849b(getString(C1947R.string.performance_updated));
        PerformanceManager.a().a(performanceV2);
        mo6515H();
        if (performanceV2.k() && this.f19225q.isChecked() && this.f19228t.isChecked() && !this.af) {
            m20769a(new InviteCompleteCallback(this) {
                final /* synthetic */ PerformanceSaveFragment f19141b;

                public void mo6507a() {
                    this.f19141b.mo6521a(6803, performanceV2);
                }

                public void mo6508b() {
                    this.f19141b.mo6521a(6803, performanceV2);
                }
            });
            return null;
        }
        mo6521a(6803, performanceV2);
        return null;
    }

    private NetworkResponse m20722a(ArrayList<PerformanceResourceInfo> arrayList) {
        NetworkResponse a = TracksManager.m18471a().m18479a(this.f19189B, PerformanceCreateUtil.m25914a((ArrayList) arrayList, PerformanceManager$PerformanceResourceInfo$ResourceType.f16865d), this.am);
        return a.c() ? null : a;
    }

    private void m20736d(@NonNull NetworkResponse networkResponse) {
        if (networkResponse.e()) {
            ((BaseActivity) getActivity()).a(networkResponse.f, true, null);
        } else {
            m19849b(getString(C1947R.string.performance_update_error));
        }
    }

    @UiThread
    protected void mo6521a(int i, PerformanceV2 performanceV2) {
        MiscUtils.m25891a(getActivity(), true);
        Intent intent = new Intent();
        intent.putExtra("CHANGE_MADE_CODE", i);
        intent.putExtra("CHANGE_MADE_PERFORMANCE", performanceV2);
        super.m19824a(intent);
    }

    @UiThread
    protected void mo6523c(String str) {
        this.as = new BusyDialog(getActivity(), str);
        this.as.show();
    }

    @UiThread
    protected void mo6515H() {
        if (this.as != null) {
            this.as.dismiss();
        }
    }

    protected void m20751I() {
        final long currentTimeMillis = System.currentTimeMillis();
        this.au = new ProgressBarDialog(getActivity(), getString(C1947R.string.core_saving), new ProgressBarDialogInterface(this) {
            final /* synthetic */ PerformanceSaveFragment f19143b;

            public void mo6384a() {
                Boolean bool = null;
                if (this.f19143b.au != null) {
                    this.f19143b.au.dismiss();
                    this.f19143b.au = null;
                    int round = Math.round(((float) (System.currentTimeMillis() - currentTimeMillis)) / 1000.0f);
                    String E = this.f19143b.m20747E();
                    UserPath userPath = this.f19143b.aj.f20149n ? UserPath.ONBOARDING : UserPath.OTHER;
                    long a = this.f19143b.an.m25929a();
                    ReviewStepsType reviewStepsType = ReviewStepsType.UPLOAD;
                    String a2 = this.f19143b.m20733b(this.f19143b.f19199L);
                    if (!(!this.f19143b.ad || this.f19143b.aj == null || this.f19143b.aj.f20141f == null)) {
                        bool = Boolean.valueOf(this.f19143b.aj.f20141f.video);
                    }
                    SingAnalytics.m26102a(E, userPath, round, a, reviewStepsType, a2, bool, this.f19143b.mo6525F());
                    if (this.f19143b.ad) {
                        this.f19143b.mo6517K();
                    }
                }
            }
        });
        this.au.m23685b(5);
        this.au.show();
    }

    @SupposeUiThread
    protected void mo6516J() {
        if (isAdded()) {
            this.f19220l.setVisibility(8);
            this.f19225q.setVisibility(8);
            this.f19221m.setEnabled(false);
            this.f19218j.setEnabled(false);
            mo6524d(null);
        }
    }

    @UiThread
    protected void mo6517K() {
        if (isAdded()) {
            this.at = new DeleteRecordingConfirmationDialog(getActivity());
            this.aq.m20702a(true);
            this.at.m19803a(this.aq);
            this.at.show();
        }
    }

    @UiThread
    protected void mo6518L() {
        if (isAdded()) {
            String E = m20747E();
            UserPath userPath = this.aj.f20149n ? UserPath.ONBOARDING : UserPath.OTHER;
            HeadphonesType a = HeadphonesType.m17502a(this.f19197J, this.f19198K);
            String str = this.f19201N;
            boolean z = this.ad;
            Ensemble a2 = this.aj.f20137b.m21631a();
            ReviewStepsType reviewStepsType = ReviewStepsType.CUSTOMIZE;
            String b = m20733b(this.f19199L);
            Boolean valueOf = (!this.ad || this.aj.f20141f == null) ? null : Boolean.valueOf(this.aj.f20141f.video);
            SingAnalytics.m26106a(E, userPath, a, str, z, a2, reviewStepsType, b, valueOf, mo6525F());
            this.at = new DeleteRecordingConfirmationDialog(getActivity());
            this.aq.m20702a(false);
            this.at.m19803a(this.aq);
            this.at.show();
        }
    }

    @UiThread
    protected void mo6519M() {
        if (isAdded()) {
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.performance_upload_error));
            textAlertDialog.m19804a(new Runnable(this) {
                final /* synthetic */ PerformanceSaveFragment f19146a;

                {
                    this.f19146a = r1;
                }

                public void run() {
                    this.f19146a.ag();
                }
            });
            textAlertDialog.show();
            return;
        }
        Log.d(f19187e, "showFailUploadDialog - not added to fragment; aborting");
    }

    private void ag() {
        if (!this.f19193F) {
            if (this.aa != Mode.Create) {
                Log.e(f19187e, "Call to prepareResourceFile but the mode of the activity is not Mode.Create!");
            }
            this.aw = true;
            this.an.m25930a(getActivity(), this.f19210W, this.ae, this.ax);
        }
    }

    private void ah() {
        Log.d(f19187e, "prepareResourceFail");
        mo6519M();
        mo6524d(null);
        this.aw = false;
    }

    private void ai() {
        Log.b(f19187e, "prepareResourceDone");
        this.f19193F = true;
        this.aw = true;
        mo6524d(new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment f19149a;

            {
                this.f19149a = r1;
            }

            public void run() {
                this.f19149a.mo6527Q();
            }
        });
    }

    @UiThread
    protected void mo6520N() {
        if (isAdded()) {
            this.f19191D = true;
            mo6516J();
        }
    }

    protected void m20769a(final InviteCompleteCallback inviteCompleteCallback) {
        if (!StringCacheManager.a().f(this.f19199L.performanceKey)) {
            mo6523c(getString(C1947R.string.invite_progress));
            InviteManager.m18240a().m18243a(this.f19189B, new NetworkResponseCallback(this) {
                final /* synthetic */ PerformanceSaveFragment f19154b;

                class C39241 implements Runnable {
                    final /* synthetic */ AnonymousClass20 f19151a;

                    C39241(AnonymousClass20 anonymousClass20) {
                        this.f19151a = anonymousClass20;
                    }

                    public void run() {
                        inviteCompleteCallback.mo6507a();
                    }
                }

                class C39252 implements CustomAlertDialogListener {
                    final /* synthetic */ AnonymousClass20 f19152a;

                    C39252(AnonymousClass20 anonymousClass20) {
                        this.f19152a = anonymousClass20;
                    }

                    public void mo6385a(CustomAlertDialog customAlertDialog) {
                        this.f19152a.f19154b.m20769a(inviteCompleteCallback);
                    }

                    public void mo6386b(CustomAlertDialog customAlertDialog) {
                        inviteCompleteCallback.mo6508b();
                    }
                }

                public void handleResponse(NetworkResponse networkResponse) {
                    this.f19154b.mo6515H();
                    if (this.f19154b.az) {
                        MagicCrittercism.a(new RuntimeException("Duplicated invites to followers").fillInStackTrace());
                    }
                    if (networkResponse.c() || networkResponse.b == 1026) {
                        this.f19154b.az = true;
                        StringCacheManager.a().e(this.f19154b.f19199L.performanceKey);
                        SingAnalytics.m26101a(this.f19154b.f19189B, SocialChannel.SNP, this.f19154b.f19199L.songUid, Analytics.m17828a(this.f19154b.f19199L), InviteType.FOLLOWER, this.f19154b.f19199L.arrKey, this.f19154b.f19199L.video);
                        if (inviteCompleteCallback != null) {
                            new Handler(Looper.getMainLooper()).post(new C39241(this));
                            return;
                        }
                        return;
                    }
                    Context activity = this.f19154b.getActivity();
                    if (activity == null) {
                        return;
                    }
                    if (networkResponse.e()) {
                        ((BaseActivity) activity).a(networkResponse.f, true, null);
                        return;
                    }
                    CustomAlertDialogListener c39252 = new C39252(this);
                    if (this.f19154b.av == null) {
                        this.f19154b.av = new TextAlertDialog(activity, (int) C1947R.string.invite_fail_title, (int) C1947R.string.invite_fail_message);
                        this.f19154b.av.m19800a((int) C1947R.string.invite_connect_fail_retry, (int) C1947R.string.invite_connect_fail_cancel);
                        this.f19154b.av.m19812c(true);
                        this.f19154b.av.m19803a(c39252);
                    }
                    this.f19154b.av.show();
                }
            });
        }
    }

    protected void m20757O() {
        if (this.au != null) {
            this.au.m23683a(getString(C1947R.string.performance_finalizing));
            this.au.m23682a(null);
            this.au.m23684b();
        } else {
            this.au = new ProgressBarDialog(getActivity(), getString(C1947R.string.core_saving), null);
            this.au.m23685b(5);
            this.au.show();
        }
        this.au.mo6374a();
        this.au.m23681a(5);
    }

    protected String m20758P() {
        if (this.f19199L != null) {
            return SingAnalytics.m26140d(this.f19199L);
        }
        return SingAnalytics.m26141d(this.ab);
    }

    protected void mo6527Q() {
        if (!this.f19191D) {
            String obj = this.f19218j.getText().toString();
            if (this.al) {
                if (!this.ad && TextUtils.getTrimmedLength(obj) == 0) {
                    m19849b(getResources().getString(C1947R.string.performance_save_title_required));
                    return;
                }
            } else if (TextUtils.getTrimmedLength(obj) == 0) {
                obj = m20746D();
            }
            SingAnalytics.m26107a(m20747E(), this.aj.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.f19197J, this.f19198K), this.f19201N, this.ad, this.aj.f20137b.m21631a(), m20758P(), this.aj.f20141f != null ? Boolean.valueOf(this.aj.f20141f.video) : null, mo6525F());
            m20757O();
            Bitmap bitmap = null;
            if (this.am == null && this.f19195H != null) {
                bitmap = ImageToDiskUtils.m25830a(getActivity(), this.f19195H);
            } else if (this.am != null) {
                bitmap = this.am;
            }
            Log.b(f19187e, "createPerformance - performance title is: " + obj);
            String str = null;
            if (this.aj.f20157v != null) {
                this.aj.f20157v.audioPower = null;
                str = JsonUtils.m18987a(this.aj.f20157v);
            } else {
                MagicCrittercism.a(new Exception("noMetaDataFoundException3"));
            }
            this.an.m25931a(this.ak, getActivity(), this.aj.m21652c(), this.aj.m21643a(), this.aj.m21648b(), this.aj.f20142g, this.ab.m18773s() ? this.ab.mo6289c() : this.ab.mo6290d(), this.ab.m18772r() ? this.ab.mo6289c() : null, this.ab.m18772r() ? ((ArrangementVersionLiteEntry) this.ab).f17623a.version : 0, this.aj.f20145j, obj, this.f19212Y, this.f19201N, this.f19207T, this.f19208U, this.f19213Z, this.f19196I, this.f19197J, this.f19221m.getText().toString(), bitmap, null, str, this.aj.f20149n, null, false, this.f19209V, this.f19198K, new PerformanceCreateListener(this) {
                final /* synthetic */ PerformanceSaveFragment f19155a;

                {
                    this.f19155a = r1;
                }

                public void mo6395a(ArrayList<PerformanceResourceInfo> arrayList) {
                    this.f19155a.f19188A.f19323i = arrayList;
                    this.f19155a.m20760R();
                }

                public void mo6394a(PerformanceV2 performanceV2, String str, String str2) {
                    this.f19155a.mo6531a(performanceV2);
                }

                public void mo6393a(NetworkResponse networkResponse) {
                    this.f19155a.m20767a(networkResponse);
                }

                public void mo6396b(NetworkResponse networkResponse) {
                    this.f19155a.m20772b(networkResponse);
                }
            });
        }
    }

    protected void m20760R() {
        this.aw = true;
    }

    protected void mo6531a(PerformanceV2 performanceV2) {
        if (isAdded()) {
            if (this.au != null) {
                this.au.m23683a(getString(C1947R.string.core_done));
                this.au.m23686c();
            }
            this.aw = false;
            this.f19199L = performanceV2;
            this.f19189B = performanceV2.performanceKey;
            Log.b(f19187e, "Performance creation completed!");
            SingAnalytics.m26060a(this.aj.f20154s, AudioCompletionContext.UPLOAD, Float.valueOf(this.aj.f20155t), this.f19189B, ad(), null, this.f19204Q, this.f19205R, this.f19206S, DeviceSettings.n(), DeviceSettings.h());
            if (this.aj.f20158w != GlitchType.NONE) {
                SingAnalytics.m26109a(this.f19189B, this.aj.f20158w, HeadphonesType.m17502a(this.f19197J, this.f19198K));
            }
            mo6520N();
            m20761S();
        }
    }

    protected void m20761S() {
        boolean z = !this.ad && (this.f19199L.d() || this.f19199L.e());
        if (z && this.f19225q.isChecked() && this.f19228t.isChecked() && !this.af) {
            m20769a(new InviteCompleteCallback(this) {
                final /* synthetic */ PerformanceSaveFragment f19156a;

                {
                    this.f19156a = r1;
                }

                public void mo6507a() {
                    m20695a(true);
                }

                public void mo6508b() {
                    m20695a(false);
                }

                public void m20695a(boolean z) {
                    this.f19156a.f19188A.f19322h = z;
                    this.f19156a.m20773b(true);
                }
            });
        } else {
            m20773b(true);
        }
    }

    protected void m20773b(boolean z) {
        ((PerformanceSaveActivity) getActivity()).m20662a(this.f19188A, this.f19199L, z);
    }

    protected void m20767a(NetworkResponse networkResponse) {
        if (isAdded()) {
            mo6524d(null);
            final PerformanceSaveActivity performanceSaveActivity = (PerformanceSaveActivity) getActivity();
            performanceSaveActivity.a(new Runnable(this) {
                final /* synthetic */ PerformanceSaveFragment f19158b;

                public void run() {
                    performanceSaveActivity.m20666q();
                    this.f19158b.mo6527Q();
                }
            }, new Runnable(this) {
                final /* synthetic */ PerformanceSaveFragment f19159a;

                {
                    this.f19159a = r1;
                }

                public void run() {
                    this.f19159a.mo6400c();
                }
            });
            this.aw = false;
        }
    }

    protected void m20772b(NetworkResponse networkResponse) {
        if (isAdded()) {
            mo6524d(null);
            if (networkResponse.e()) {
                ((BaseActivity) getActivity()).a(networkResponse.f, true, null);
            } else if (networkResponse.a == NetworkResponse$Status.CONNECTION_TIMEOUT) {
                ((BaseActivity) getActivity()).a(new Runnable(this) {
                    final /* synthetic */ PerformanceSaveFragment f19160a;

                    {
                        this.f19160a = r1;
                    }

                    public void run() {
                        this.f19160a.m20737d("onPerformanceCreationFailed");
                    }
                }, new Runnable(this) {
                    final /* synthetic */ PerformanceSaveFragment f19161a;

                    {
                        this.f19161a = r1;
                    }

                    public void run() {
                        this.f19161a.mo6400c();
                    }
                });
            } else {
                m19849b(getString(C1947R.string.performance_create_error));
            }
            this.aw = false;
        }
    }

    @UiThread
    protected void mo6524d(Runnable runnable) {
        if (this.au != null) {
            this.au.dismiss();
            this.au = null;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    protected void mo6528T() {
    }

    public void m20770a(Future<PreuploadResponse> future) {
        this.ak = future;
    }

    private String m20733b(PerformanceV2 performanceV2) {
        return SingAnalytics.m26140d(performanceV2);
    }

    public boolean mo6511h() {
        return false;
    }
}
