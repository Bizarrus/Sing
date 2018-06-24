package com.smule.singandroid.pre_sing;

import android.content.Intent;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment.ActionBarHighlightMode;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.TrialSubscriptionActivity;
import com.smule.singandroid.TrialSubscriptionActivity_;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.SingTip;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyDialog.BusyDialogListener;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.task.SongDownloadTask;
import com.smule.singandroid.task.SongDownloadTask.DownloadListener;
import com.smule.singandroid.task.SongDownloadTask.DownloadProgressListener;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EFragment
public class PreSingDownloadFragment extends PreSingBaseFragment {
    private static final String f23630H = PreSingDownloadFragment.class.getName();
    @ViewById
    LinearLayout f23631A;
    @ViewById
    TextView f23632B;
    protected SongDownloadTask f23633C;
    protected TextAlertDialog f23634D;
    final DownloadProgressListener f23635E = new C47481(this);
    final DownloadListener f23636F = new C47502(this);
    protected int f23637G;
    private AtomicBoolean f23638I = new AtomicBoolean(false);
    private long f23639J;
    private Handler f23640K = new Handler(Looper.getMainLooper());
    private int f23641L;
    private int f23642M;
    private int f23643N;
    protected ProgressBar f23644t;
    @ViewById
    protected ProfileImageWithVIPBadge f23645u;
    @ViewById
    protected ProfileImageWithVIPBadge f23646v;
    @ViewById
    protected TextView f23647w;
    @ViewById
    protected TextView f23648x;
    @ViewById
    protected ViewPager f23649y;
    @ViewById
    protected View f23650z;

    class C47481 implements DownloadProgressListener {
        final /* synthetic */ PreSingDownloadFragment f23615a;

        C47481(PreSingDownloadFragment preSingDownloadFragment) {
            this.f23615a = preSingDownloadFragment;
        }

        public void mo6818a(int i) {
            if (this.f23615a.isAdded() && this.f23615a.f23644t != null) {
                this.f23615a.f23644t.setProgress(i);
            }
        }
    }

    class C47502 implements DownloadListener {
        final /* synthetic */ PreSingDownloadFragment f23617a;

        class C47491 implements BusyDialogListener {
            final /* synthetic */ C47502 f23616a;

            C47491(C47502 c47502) {
                this.f23616a = c47502;
            }

            public void mo6373a() {
                PreSingActivity preSingActivity = (PreSingActivity) this.f23616a.f23617a.getActivity();
                if (preSingActivity != null && this.f23616a.f23617a.i.f20149n) {
                    preSingActivity.finish();
                }
            }
        }

        C47502(PreSingDownloadFragment preSingDownloadFragment) {
            this.f23617a = preSingDownloadFragment;
        }

        public void mo6541a(boolean z, SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
            if (this.f23617a.f23633C != null && !this.f23617a.f23633C.isCancelled()) {
                this.f23617a.f23638I.set(true);
                this.f23617a.f23633C = null;
                if (!this.f23617a.isAdded()) {
                    return;
                }
                if (z) {
                    this.f23617a.k = songbookEntry;
                    this.f23617a.j = performanceV2;
                    if (this.f23617a.f23644t != null) {
                        this.f23617a.f23644t.setProgress(100);
                    }
                    this.f23617a.mo6897a();
                } else if (this.f23617a.m != null) {
                    this.f23617a.m.m23577a(2, this.f23617a.getString(C1947R.string.songbook_download_failed_message), true, this.f23617a.getString(C1947R.string.core_ok));
                    this.f23617a.m.m23579a(new C47491(this));
                    if (!this.f23617a.m.isShowing()) {
                        this.f23617a.m.m23580a(true);
                    }
                }
            }
        }
    }

    class C47513 implements Runnable {
        final /* synthetic */ PreSingDownloadFragment f23618a;

        C47513(PreSingDownloadFragment preSingDownloadFragment) {
            this.f23618a = preSingDownloadFragment;
        }

        public void run() {
            if (!this.f23618a.isAdded()) {
                return;
            }
            if (this.f23618a.f23634D == null || !this.f23618a.f23634D.isShowing()) {
                this.f23618a.m24838N();
            }
        }
    }

    class C47524 implements BusyDialogListener {
        final /* synthetic */ PreSingDownloadFragment f23619a;

        C47524(PreSingDownloadFragment preSingDownloadFragment) {
            this.f23619a = preSingDownloadFragment;
        }

        public void mo6373a() {
            if (this.f23619a.f23633C != null) {
                this.f23619a.f23633C.m25690d();
            }
            this.f23619a.mo6898H();
            this.f23619a.getActivity().onBackPressed();
        }
    }

    class C47555 implements CustomAlertDialogListener {
        final /* synthetic */ PreSingDownloadFragment f23622a;

        class C47531 implements Runnable {
            final /* synthetic */ C47555 f23620a;

            C47531(C47555 c47555) {
                this.f23620a = c47555;
            }

            public void run() {
                if (TrialSubscriptionActivity.m21947a(this.f23620a.f23622a.getActivity())) {
                    this.f23620a.f23622a.startActivity(new Intent(this.f23620a.f23622a.getActivity(), TrialSubscriptionActivity_.class));
                    this.f23620a.f23622a.getActivity().finish();
                    return;
                }
                this.f23620a.f23622a.getActivity().finish();
            }
        }

        class C47542 implements Runnable {
            final /* synthetic */ C47555 f23621a;

            C47542(C47555 c47555) {
                this.f23621a = c47555;
            }

            public void run() {
                this.f23621a.f23622a.mo6913Y();
            }
        }

        C47555(PreSingDownloadFragment preSingDownloadFragment) {
            this.f23622a = preSingDownloadFragment;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            this.f23622a.m19848b(new C47531(this));
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            if (this.f23622a.f23638I.get()) {
                this.f23622a.m19848b(new C47542(this));
            }
        }
    }

    public class SingTipsAdapter extends PagerAdapter {
        final /* synthetic */ PreSingDownloadFragment f23625a;
        private final List<Integer> f23626b = Arrays.asList(new Integer[]{Integer.valueOf(C1947R.string.sing_tip1), Integer.valueOf(C1947R.string.sing_tip3), Integer.valueOf(C1947R.string.sing_tip6), Integer.valueOf(C1947R.string.sing_tip7), Integer.valueOf(C1947R.string.sing_tip8), Integer.valueOf(C1947R.string.sing_tip11), Integer.valueOf(C1947R.string.sing_tip12), Integer.valueOf(C1947R.string.sing_tip13), Integer.valueOf(C1947R.string.sing_tip14)});
        private final List<Integer> f23627c = Arrays.asList(new Integer[]{Integer.valueOf(C1947R.string.sing_tip2), Integer.valueOf(C1947R.string.sing_tip4), Integer.valueOf(C1947R.string.sing_tip5), Integer.valueOf(C1947R.string.sing_tip9), Integer.valueOf(C1947R.string.sing_tip10), Integer.valueOf(C1947R.string.sing_tip15), Integer.valueOf(C1947R.string.sing_tip16), Integer.valueOf(C1947R.string.sing_tip17), Integer.valueOf(C1947R.string.sing_tip18), Integer.valueOf(C1947R.string.sing_tip19), Integer.valueOf(C1947R.string.sing_tip20), Integer.valueOf(C1947R.string.sing_tip21), Integer.valueOf(C1947R.string.sing_tip22)});
        private List<Integer> f23628d = new ArrayList();

        public SingTipsAdapter(PreSingDownloadFragment preSingDownloadFragment) {
            int i = 0;
            this.f23625a = preSingDownloadFragment;
            Collections.shuffle(this.f23626b);
            Collections.shuffle(this.f23627c);
            while (i < 3) {
                if (new Random().nextInt(3) < 2) {
                    this.f23628d.add(this.f23626b.get(i));
                } else {
                    this.f23628d.add(this.f23627c.get(i));
                }
                i++;
            }
        }

        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            View a = SingTip.m23481a(this.f23625a.getActivity(), this.f23625a.getString(((Integer) this.f23628d.get(i % this.f23628d.size())).intValue()));
            a.setListener(new OnClickListener(this) {
                final /* synthetic */ SingTipsAdapter f23624b;

                public void onClick(View view) {
                    this.f23624b.f23625a.f23649y.setCurrentItem(i, true);
                }
            });
            viewGroup.addView(a);
            return a;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public int getCount() {
            return HttpResponseCode.MULTIPLE_CHOICES;
        }
    }

    public class SingTipsTransformer implements PageTransformer {
        final /* synthetic */ PreSingDownloadFragment f23629a;

        public SingTipsTransformer(PreSingDownloadFragment preSingDownloadFragment) {
            this.f23629a = preSingDownloadFragment;
        }

        public void transformPage(View view, float f) {
            int width = view.getWidth();
            int height = view.getHeight();
            float c = ((float) (this.f23629a.f23642M + this.f23629a.f23643N)) / ((float) this.f23629a.f23641L);
            float max = Math.max(0.85f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - Math.abs(f - c));
            float f2 = (((float) height) * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - max)) / 2.0f;
            float f3 = (((float) width) * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - max)) / 2.0f;
            if (f - c < 0.0f) {
                view.setTranslationX(f3 - (f2 / 2.0f));
            } else {
                view.setTranslationX((-f3) + (f2 / 2.0f));
            }
            view.setScaleX(max);
            view.setScaleY(max);
            view.setAlpha((((max - 0.85f) / 0.14999998f) * 0.5f) + 0.5f);
        }
    }

    protected void mo6897a() {
        if (!this.i.f20149n || this.f23634D == null || !this.f23634D.isShowing()) {
            mo6913Y();
        }
    }

    private void mo6913Y() {
        long currentTimeMillis = System.currentTimeMillis() - this.f23639J;
        currentTimeMillis = currentTimeMillis < 4000 ? 4000 - currentTimeMillis : 0;
        this.f23640K.removeCallbacksAndMessages(null);
        this.f23640K.postDelayed(new C47513(this), currentTimeMillis);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m19831a(ActionBarHighlightMode.WHITE);
    }

    protected void mo6896E() {
        super.mo6896E();
        this.f23644t = m19861l();
        if (this.f23644t != null) {
            this.f23644t.setProgress(0);
            this.f23644t.setMax(100);
            this.f23644t.setVisibility(0);
        }
        mo6912T();
        this.m = new BusyDialog(getActivity(), getString(C1947R.string.core_loading));
        this.m.m23579a(new C47524(this));
        if (this.f23633C == null) {
            m24874W();
        }
    }

    @AfterViews
    protected void m24869R() {
        if (this.f23631A != null) {
            this.f23631A.setVisibility(0);
        }
        if (this.f23632B != null) {
            this.f23632B.setText(this.k.mo6291e());
        }
    }

    public void onStart() {
        super.onStart();
        this.f23637G = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        getActivity().getWindow().getDecorView().setSystemUiVisibility(1284);
        getActivity().getWindow().setFlags(1024, 1024);
        LayoutParams layoutParams = (LayoutParams) ((PreSingActivity) getActivity()).m24772B().getLayoutParams();
        layoutParams.height = 0;
        ((PreSingActivity) getActivity()).m24772B().setLayoutParams(layoutParams);
    }

    public void onStop() {
        super.onStop();
        getActivity().getWindow().clearFlags(1040);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(this.f23637G);
        m19866q();
    }

    @Click
    protected void m24870S() {
        ((PreSingActivity) getActivity()).onBackPressed();
    }

    protected void mo6912T() {
        m24872U();
        m24840P();
        m24873V();
    }

    protected void m24872U() {
        boolean z;
        String str = null;
        String str2 = this.j != null ? this.j.accountIcon.handle : null;
        if (this.j != null) {
            str = this.j.accountIcon.picUrl;
        }
        if (this.j == null || !this.j.accountIcon.a()) {
            z = false;
        } else {
            z = true;
        }
        if (this.i.m21643a()) {
            if (this.i.f20142g == 2) {
                this.f23645u.setProfilePicUrl(str);
                this.f23645u.setVIP(z);
                this.f23646v.setProfilePicUrl(UserManager.a().h());
                this.f23646v.setVIP(SubscriptionManager.a().b());
            } else {
                this.f23645u.setProfilePicUrl(UserManager.a().h());
                this.f23645u.setVIP(SubscriptionManager.a().b());
                this.f23646v.setProfilePicUrl(str);
                this.f23646v.setVIP(z);
            }
            if (str2 != null) {
                str2 = getString(C1947R.string.pre_singing_prepare_studio_collab, new Object[]{str2});
            } else {
                str2 = getString(C1947R.string.pre_singing_prepare_studio_recording);
            }
        } else {
            this.f23645u.setProfilePicUrl(UserManager.a().h());
            this.f23645u.setVIP(SubscriptionManager.a().b());
            this.f23646v.setProfilePicUrl(str);
            this.f23646v.setVIP(false);
            if (!this.i.m21648b()) {
                this.f23646v.setVisibility(8);
                str2 = getString(C1947R.string.pre_singing_prepare_studio_recording);
            } else if (str2 != null) {
                str2 = getString(C1947R.string.pre_singing_prepare_studio_collab, new Object[]{str2});
            } else {
                str2 = getString(C1947R.string.pre_singing_prepare_studio_recording);
            }
        }
        int indexOf = str2.indexOf(10);
        CharSequence substring = indexOf > 0 ? str2.substring(0, indexOf) : "";
        CharSequence substring2 = indexOf > 0 ? str2.substring(indexOf + 1) : "";
        this.f23647w.setText(substring);
        this.f23648x.setText(substring2);
    }

    public String mo6383s() {
        return f23630H;
    }

    private String mo6914Z() {
        return this.j != null ? this.j.performanceKey : null;
    }

    private String aa() {
        if (this.k == null || !this.k.m18772r()) {
            return "-";
        }
        return this.k.mo6289c();
    }

    public void onResume() {
        super.onResume();
        if (this.f23644t != null) {
            this.f23644t.setVisibility(0);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.f23644t != null) {
            this.f23644t.setVisibility(8);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        m24875X();
    }

    protected void mo6420v() {
        SingAnalytics.m26120a(mo6914Z(), SongbookEntryUtils.m26167b(this.k), aa(), SingApplication.n());
    }

    protected void m24873V() {
        WindowManager windowManager = (WindowManager) getActivity().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.f23641L = displayMetrics.widthPixels;
        this.f23643N = (this.f23641L - ((int) getResources().getDimension(C1947R.dimen.presing_download_sing_tips_width))) / 2;
        this.f23649y.setPadding(this.f23643N, this.f23649y.getPaddingTop(), this.f23643N, this.f23649y.getPaddingBottom());
        this.f23650z.setBackgroundResource(this.s);
        this.f23642M = this.f23643N / 2;
        this.f23649y.setPageMargin(this.f23642M);
        this.f23649y.setAdapter(new SingTipsAdapter(this));
        this.f23649y.setCurrentItem(CtaButton.WIDTH_DIPS);
        this.f23649y.setPageTransformer(false, new SingTipsTransformer(this));
    }

    protected void m24874W() {
        SongDownloadTask t = ((PreSingActivity) getActivity()).m24789t();
        this.f23639J = System.currentTimeMillis();
        if (this.i.f20149n && t != null && t.m25687a(this.i.f20139d)) {
            t.m25684a(this.f23635E);
            t.m25683a(this.f23636F);
            this.f23633C = t;
            return;
        }
        if (this.j == null) {
            this.f23633C = new SongDownloadTask(getActivity(), this.k, this.f23636F, this.f23635E);
        } else {
            this.f23633C = new SongDownloadTask(getActivity(), this.k, this.j, this.f23636F, this.f23635E);
        }
        if (t != null && (t.m25691e() || t.getStatus() != Status.FINISHED)) {
            this.f23633C.m25689c();
        }
        this.f23633C.execute(new Void[0]);
    }

    protected void m24875X() {
        if (this.f23633C != null) {
            Log.c(f23630H, "cancelDownloadIfRunning : Cancelling song download.");
            this.f23633C.m25690d();
            this.f23633C = null;
        }
        Log.c(f23630H, "cancelDownloadIfRunning : Removing callbacks.");
        this.f23640K.removeCallbacksAndMessages(null);
    }

    public boolean mo6400c() {
        if (this.i.f20149n) {
            mo6903a(new C47555(this));
            return true;
        }
        Log.b(f23630H, "Going passing back button up to Activity");
        return false;
    }

    @UiThread
    protected void mo6903a(CustomAlertDialogListener customAlertDialogListener) {
        if (isAdded()) {
            this.f23634D = new TextAlertDialog(getActivity(), getString(C1947R.string.core_are_you_sure), getString(C1947R.string.pre_singing_onboarding_cancel_detail));
            this.f23634D.m19806a(getString(C1947R.string.core_yes), getString(C1947R.string.core_no));
            this.f23634D.m19803a(customAlertDialogListener);
            this.f23634D.show();
        }
    }
}
