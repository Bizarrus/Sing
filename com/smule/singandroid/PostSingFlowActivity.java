package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.PostSongUpsellActivity_.IntentBuilder_;
import com.smule.singandroid.ads.FullScreenAd;
import com.smule.singandroid.ads.PerformanceCancelAd;
import com.smule.singandroid.ads.PostPerformanceReviewAd;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.survey.SurveyContext;
import com.smule.singandroid.survey.SurveyContext.EntryPoint;
import com.smule.singandroid.survey.SurveyPresenter;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;

@EActivity
public class PostSingFlowActivity extends BaseActivity {
    private static final String f19339k = PostSingFlowActivity.class.getName();
    FullScreenAd f19340g;
    @Extra
    protected PostSingBundle f19341h;
    @InstanceState
    protected ArrayList<String> f19342i = new ArrayList();
    final Observer f19343j = new C39721(this);
    private List<Task> f19344l = new ArrayList();
    private SingBundle f19345m;
    private PerformanceV2 f19346n;

    class C39721 implements Observer {
        final /* synthetic */ PostSingFlowActivity f19325a;

        C39721(PostSingFlowActivity postSingFlowActivity) {
            this.f19325a = postSingFlowActivity;
        }

        public void update(Observable observable, Object obj) {
            if (!this.f19325a.h()) {
                this.f19325a.m20883b();
            }
        }
    }

    class C39754 implements Runnable {
        final /* synthetic */ PostSingFlowActivity f19330a;

        C39754(PostSingFlowActivity postSingFlowActivity) {
            this.f19330a = postSingFlowActivity;
        }

        public void run() {
            this.f19330a.startActivity(ChatShareInviteActivity.m22649a(this.f19330a).m22676a(this.f19330a.f19346n).m22678a(this.f19330a.f19346n.o()).m22679a(this.f19330a.f19341h.f19322h).m22677a(this.f19330a.f19345m.f20153r).m22675a(SearchClkContext.POSTSING).m22681b(this.f19330a.m20893u()).b());
        }
    }

    class C39765 implements Runnable {
        final /* synthetic */ PostSingFlowActivity f19331a;

        C39765(PostSingFlowActivity postSingFlowActivity) {
            this.f19331a = postSingFlowActivity;
        }

        public void run() {
            this.f19331a.startActivity(ShareActivity.m21438a(this.f19331a.getApplicationContext(), this.f19331a.f19346n, this.f19331a.f19345m.f20145j, null, this.f19331a.f19345m.m21659h(), this.f19331a.f19345m.f20153r, SearchClkContext.POSTSING));
        }
    }

    class C39776 implements Runnable {
        final /* synthetic */ PostSingFlowActivity f19332a;

        C39776(PostSingFlowActivity postSingFlowActivity) {
            this.f19332a = postSingFlowActivity;
        }

        public void run() {
            this.f19332a.startActivity(new IntentBuilder_(this.f19332a).m20923a(this.f19332a.f19341h).b());
        }
    }

    class C39787 implements Runnable {
        final /* synthetic */ PostSingFlowActivity f19333a;

        C39787(PostSingFlowActivity postSingFlowActivity) {
            this.f19333a = postSingFlowActivity;
        }

        public void run() {
            this.f19333a.startActivity(new Intent(this.f19333a, TrialSubscriptionActivity_.class));
        }
    }

    class C39798 implements Runnable {
        final /* synthetic */ PostSingFlowActivity f19334a;

        C39798(PostSingFlowActivity postSingFlowActivity) {
            this.f19334a = postSingFlowActivity;
        }

        public void run() {
            FullScreenAd performanceCancelAd = new PerformanceCancelAd();
            performanceCancelAd.m22259a(this.f19334a, this.f19334a.f19345m);
            this.f19334a.f19340g = performanceCancelAd;
        }
    }

    class C39809 implements Runnable {
        final /* synthetic */ PostSingFlowActivity f19335a;

        C39809(PostSingFlowActivity postSingFlowActivity) {
            this.f19335a = postSingFlowActivity;
        }

        public void run() {
            if (!this.f19335a.f19340g.m22254a(this.f19335a)) {
                this.f19335a.m20883b();
            }
        }
    }

    class Task {
        final /* synthetic */ PostSingFlowActivity f19336a;
        private String f19337b;
        private Runnable f19338c;

        public Task(PostSingFlowActivity postSingFlowActivity, String str, Runnable runnable) {
            this.f19336a = postSingFlowActivity;
            Log.b(PostSingFlowActivity.f19339k, "Created task: " + str);
            this.f19337b = str;
            this.f19338c = runnable;
        }

        public void m20873a() {
            Log.b(PostSingFlowActivity.f19339k, "Running task: " + this.f19337b);
            this.f19338c.run();
        }
    }

    public static void m20878a(Activity activity, SingBundle singBundle, String str) {
        PostSingBundle postSingBundle = new PostSingBundle(singBundle);
        postSingBundle.f19318d = true;
        postSingBundle.f19320f = str;
        postSingBundle.f19321g = singBundle.m21654d() ? EntryPoint.VIDEO_REC_CANCEL : EntryPoint.AUDIO_REC_CANCEL;
        m20884b(activity, postSingBundle);
    }

    public static void m20876a(Activity activity, PostSingBundle postSingBundle) {
        postSingBundle.f19319e = true;
        postSingBundle.f19321g = postSingBundle.f19316b.m21654d() ? EntryPoint.VIDEO_REVIEW_CANCEL : EntryPoint.AUDIO_REVIEW_CANCEL;
        m20884b(activity, postSingBundle);
    }

    public static void m20877a(Activity activity, PostSingBundle postSingBundle, PerformanceV2 performanceV2) {
        postSingBundle.f19317c = performanceV2;
        postSingBundle.f19321g = postSingBundle.f19316b.m21654d() ? EntryPoint.VIDEO_UPLOAD : EntryPoint.AUDIO_UPLOAD;
        SurveyPresenter.m25508a().m25543a((Context) activity, performanceV2);
        m20884b(activity, postSingBundle);
    }

    private static void m20884b(Activity activity, PostSingBundle postSingBundle) {
        activity.startActivity(new PostSingFlowActivity_.IntentBuilder_(activity).m20899a(postSingBundle).b());
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.b(f19339k, "starting");
        this.f19345m = this.f19341h.f19316b;
        this.f19346n = this.f19341h.f19317c;
        if (!this.f19345m.f20149n) {
            Object obj = (this.f19345m.f20146k || this.f19346n == null || !(this.f19346n.d() || this.f19346n.e())) ? null : 1;
            if (obj != null) {
                m20894v();
            } else if (this.f19346n != null) {
                m20895w();
            }
            if (!this.f19341h.f19318d && SingServerValues.m21664B()) {
                m20896x();
            }
            if (obj == null || !m20893u()) {
                SurveyContext q = m20889q();
                if (SurveyPresenter.m25508a().m25549b(this, q)) {
                    m20885b(q);
                } else if (SurveyPresenter.m25508a().m25547a(this.f19345m.f20139d)) {
                    m20880a(q);
                }
            } else {
                m20891s();
                m20892t();
            }
            if (this.f19345m.f20149n && TrialSubscriptionActivity.m21947a((Context) this)) {
                m20897y();
            }
            if (this.f19341h.f19318d) {
                m20898z();
            } else {
                m20874A();
            }
        }
    }

    protected void onResume() {
        super.onResume();
        NotificationCenter.m19011a().m19014a(SurveyPresenter.f24309b, this.f19343j);
        m20883b();
    }

    protected void onPause() {
        super.onPause();
        NotificationCenter.m19011a().m19016b(SurveyPresenter.f24309b, this.f19343j);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f19340g != null) {
            this.f19340g.m22258c();
            this.f19340g = null;
        }
    }

    private void m20881a(String str, Runnable runnable) {
        m20882a(str, runnable, null);
    }

    private void m20882a(String str, Runnable runnable, Runnable runnable2) {
        if (!this.f19342i.contains(str)) {
            if (runnable2 != null) {
                runnable2.run();
            }
            this.f19344l.add(new Task(this, str, runnable));
        }
    }

    private void m20883b() {
        if (this.f19344l.size() > 0) {
            Task task = (Task) this.f19344l.remove(0);
            task.m20873a();
            this.f19342i.add(task.f19337b);
            return;
        }
        startActivity(MasterActivity.a(this));
        finish();
    }

    private SurveyContext m20889q() {
        boolean z = true;
        SurveyContext surveyContext = new SurveyContext();
        surveyContext.f24287a = this.f19341h;
        surveyContext.f24288b = this.f19341h.f19321g;
        surveyContext.f24291e = this.f19345m.f20139d;
        surveyContext.f24293g = this.f19341h.f19320f;
        surveyContext.f24292f = this.f19346n;
        if (this.f19345m.f20139d.m18772r()) {
            boolean z2;
            if (m20890r().multipart && this.f19345m.m21643a()) {
                z2 = true;
            } else {
                z2 = false;
            }
            surveyContext.f24290d = z2;
            if (!(m20890r().groupParts && this.f19345m.m21648b())) {
                z = false;
            }
            surveyContext.f24289c = z;
        }
        return surveyContext;
    }

    private ArrangementVersion m20890r() {
        if (this.f19346n != null) {
            return this.f19346n.arrangementVersion;
        }
        return this.f19345m.f20141f != null ? this.f19345m.f20141f.arrangementVersion : ((ArrangementVersionLiteEntry) this.f19345m.f20139d).f17623a.arrangementVersion;
    }

    private void m20880a(final SurveyContext surveyContext) {
        m20881a("ARRANGEMENT_SURVEY_TASK", new Runnable(this) {
            final /* synthetic */ PostSingFlowActivity f19327b;

            public void run() {
                SurveyPresenter.m25508a().m25542a(this.f19327b, surveyContext);
            }
        });
    }

    private void m20885b(final SurveyContext surveyContext) {
        m20881a("AV_QUALITY_SURVEY_TASK", new Runnable(this) {
            final /* synthetic */ PostSingFlowActivity f19329b;

            public void run() {
                SurveyPresenter.m25508a().m25551c(this.f19329b, surveyContext);
            }
        });
    }

    private void m20891s() {
        this.f19342i.add("ARRANGEMENT_SURVEY_TASK");
    }

    private void m20892t() {
        this.f19342i.add("AV_QUALITY_SURVEY_TASK");
    }

    private boolean m20893u() {
        boolean z = !this.f19345m.m21652c() && ((this.f19345m.m21643a() || this.f19345m.m21648b()) && MiscUtils.m25896a(UserManager.a().h()));
        if (!z) {
            return false;
        }
        long j = SingApplication.f().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).getLong("CUSTOMIZE_PROFILE_PIC_TIMESTAMP", 0);
        if (j == 0) {
            return true;
        }
        boolean z2 = SingApplication.f().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).getBoolean("CUSTOMIZE_PROFILE_PIC_NO_MORE", false);
        if (System.currentTimeMillis() - j <= TimeUnit.DAYS.toMillis(7) || z2) {
            return false;
        }
        return true;
    }

    private void m20894v() {
        m20881a("Invite", new C39754(this));
    }

    private void m20895w() {
        m20881a("Share", new C39765(this));
    }

    private void m20896x() {
        m20881a("UpsellDialog", new C39776(this));
    }

    private void m20897y() {
        m20881a("ShowTrialSubscription", new C39787(this));
    }

    private void m20898z() {
        m20882a("ShowCancelAd", new C39809(this), new C39798(this));
    }

    private void m20874A() {
        FullScreenAd postPerformanceReviewAd = new PostPerformanceReviewAd();
        this.f19340g = postPerformanceReviewAd;
        postPerformanceReviewAd.m22260a(this, this.f19346n != null ? this.f19346n.songUid : null, this.f19345m.f20153r.toString(), this.f19345m.f20145j, Boolean.valueOf(this.f19345m.f20146k));
        m20881a("ShowPostPerformanceAd", new Runnable(this) {
            final /* synthetic */ PostSingFlowActivity f19324a;

            {
                this.f19324a = r1;
            }

            public void run() {
                if (!this.f19324a.f19340g.m22254a(this.f19324a)) {
                    this.f19324a.m20883b();
                }
            }
        });
    }
}
