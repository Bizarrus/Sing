/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.os.Bundle
 *  com.smule.singandroid.survey.SurveyContext
 *  com.smule.singandroid.survey.SurveyContext$EntryPoint
 *  com.smule.singandroid.survey.SurveyPresenter
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.Extra
 *  org.androidannotations.annotations.InstanceState
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.smule.android.ads.MagicAd;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.PostSingFlowActivity_;
import com.smule.singandroid.PostSongUpsellActivity_;
import com.smule.singandroid.ShareActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.TrialSubscriptionActivity;
import com.smule.singandroid.TrialSubscriptionActivity_;
import com.smule.singandroid.ads.FullScreenAd;
import com.smule.singandroid.ads.PerformanceCancelAd;
import com.smule.singandroid.ads.PostPerformanceReviewAd;
import com.smule.singandroid.ads.SingAdSettings;
import com.smule.singandroid.ads.SingFullScreenAd;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.chat.ChatShareInviteActivity_;
import com.smule.singandroid.survey.AVQualityPerformanceInfo;
import com.smule.singandroid.survey.SurveyContext;
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

@SuppressLint(value={"Registered"})
@EActivity
public class PostSingFlowActivity
extends BaseActivity {
    private static final String k = PostSingFlowActivity.class.getName();
    FullScreenAd g;
    @Extra
    protected PostSingBundle h;
    @InstanceState
    protected ArrayList<String> i = new ArrayList();
    final Observer j;
    private List<Task> l = new ArrayList<Task>();
    private SingBundle m;
    private PerformanceV2 n;

    public PostSingFlowActivity() {
        this.j = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                if (!PostSingFlowActivity.this.j()) {
                    PostSingFlowActivity.this.b();
                }
            }
        };
    }

    private void A() {
        this.a("UpsellDialog", new Runnable(){

            @Override
            public void run() {
                Intent intent = new PostSongUpsellActivity_.IntentBuilder_((Context)PostSingFlowActivity.this).a(PostSingFlowActivity.this.h).b();
                PostSingFlowActivity.this.startActivity(intent);
            }
        });
    }

    private void B() {
        this.a("ShowTrialSubscription", new Runnable(){

            @Override
            public void run() {
                Intent intent = new Intent((Context)PostSingFlowActivity.this, TrialSubscriptionActivity_.class);
                PostSingFlowActivity.this.startActivity(intent);
            }
        });
    }

    private void C() {
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                PerformanceCancelAd performanceCancelAd = new PerformanceCancelAd();
                performanceCancelAd.a((Activity)PostSingFlowActivity.this, PostSingFlowActivity.this.m);
                PostSingFlowActivity.this.g = performanceCancelAd;
            }
        };
        this.a("ShowCancelAd", new Runnable(){

            @Override
            public void run() {
                if (!PostSingFlowActivity.this.g.a((Activity)PostSingFlowActivity.this)) {
                    PostSingFlowActivity.this.b();
                }
            }
        }, runnable);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void D() {
        PostPerformanceReviewAd postPerformanceReviewAd = new PostPerformanceReviewAd();
        this.g = postPerformanceReviewAd;
        String string2 = this.m.d != null ? this.m.d.c() : null;
        postPerformanceReviewAd.a((Activity)this, string2, this.m.s.toString(), this.m.j, this.m.k);
        this.a("ShowPostPerformanceAd", new Runnable(){

            @Override
            public void run() {
                if (!PostSingFlowActivity.this.g.a((Activity)PostSingFlowActivity.this)) {
                    PostSingFlowActivity.this.b();
                }
            }
        });
    }

    private void a(final Activity activity) {
        this.a("PreloadPreSingFullScreenAd", new Runnable(){

            @Override
            public void run() {
                MagicFullScreenAdMediatorAdapter magicFullScreenAdMediatorAdapter = MagicAdAdapterFactory.a().a(activity);
                if (magicFullScreenAdMediatorAdapter != null) {
                    SingFullScreenAd singFullScreenAd = new SingFullScreenAd(SingAdSettings.FullScreenAdPlacement.b, null);
                    singFullScreenAd.a(false);
                    Log.c(k, "FULLSCREEN_AD: PostSing preload of PreSing fullscreen ad - task fired");
                    magicFullScreenAdMediatorAdapter.preloadAd(singFullScreenAd);
                }
                PostSingFlowActivity.this.b();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Activity activity, PostSingBundle postSingBundle) {
        postSingBundle.e = true;
        SurveyContext.EntryPoint entryPoint = postSingBundle.b.f() ? SurveyContext.EntryPoint.b : SurveyContext.EntryPoint.e;
        postSingBundle.g = entryPoint;
        PostSingFlowActivity.b(activity, postSingBundle);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Activity activity, PostSingBundle postSingBundle, PerformanceV2 object) {
        postSingBundle.c = object;
        object = postSingBundle.b.f() ? SurveyContext.EntryPoint.c : SurveyContext.EntryPoint.f;
        postSingBundle.g = object;
        object = new AVQualityPerformanceInfo(postSingBundle);
        SurveyPresenter.a().a((Context)activity, (AVQualityPerformanceInfo)object);
        PostSingFlowActivity.b(activity, postSingBundle);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Activity activity, SingBundle singBundle, String string2) {
        PostSingBundle postSingBundle = new PostSingBundle(singBundle);
        postSingBundle.d = true;
        postSingBundle.f = string2;
        singBundle = singBundle.f() ? SurveyContext.EntryPoint.a : SurveyContext.EntryPoint.d;
        postSingBundle.g = singBundle;
        PostSingFlowActivity.b(activity, postSingBundle);
    }

    private void a(final SurveyContext surveyContext) {
        this.a("ARRANGEMENT_SURVEY_TASK", new Runnable(){

            @Override
            public void run() {
                SurveyPresenter.a().a((Activity)PostSingFlowActivity.this, surveyContext);
            }
        });
    }

    private void a(String string2, Runnable runnable) {
        this.a(string2, runnable, null);
    }

    private void a(String string2, Runnable runnable, Runnable runnable2) {
        if (this.i.contains(string2)) {
            return;
        }
        if (runnable2 != null) {
            runnable2.run();
        }
        this.l.add(new Task(string2, runnable));
    }

    private void b() {
        if (this.l.size() > 0) {
            Task task = this.l.remove(0);
            task.a();
            this.i.add(task.b);
            return;
        }
        this.startActivity(MasterActivity.a((Context)this));
        this.finish();
    }

    private static void b(Activity activity, PostSingBundle postSingBundle) {
        activity.startActivity(new PostSingFlowActivity_.IntentBuilder_((Context)activity).a(postSingBundle).b());
    }

    private void b(final SurveyContext surveyContext) {
        this.a("AV_QUALITY_SURVEY_TASK", new Runnable(){

            @Override
            public void run() {
                SurveyPresenter.a().c((Activity)PostSingFlowActivity.this, surveyContext);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private SurveyContext t() {
        boolean bl = true;
        SurveyContext surveyContext = new SurveyContext();
        surveyContext.a = this.h;
        surveyContext.b = this.h.g;
        surveyContext.e = this.m.d;
        surveyContext.g = this.h.f;
        surveyContext.f = new AVQualityPerformanceInfo(this.h);
        if (this.m.d.t()) {
            boolean bl2 = this.u().multipart && this.m.c();
            surveyContext.d = bl2;
            bl2 = this.u().groupParts && this.m.d() ? bl : false;
            surveyContext.c = bl2;
        }
        return surveyContext;
    }

    private ArrangementVersion u() {
        if (this.n != null) {
            return this.n.arrangementVersion;
        }
        if (this.m.f != null) {
            return this.m.f.arrangementVersion;
        }
        return ((ArrangementVersionLiteEntry)this.m.d).a.arrangementVersion;
    }

    private void v() {
        this.i.add("ARRANGEMENT_SURVEY_TASK");
    }

    private void w() {
        this.i.add("AV_QUALITY_SURVEY_TASK");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean x() {
        boolean bl = true;
        if (this.m.e()) return false;
        if (!this.m.c()) {
            if (!this.m.d()) return false;
        }
        if (!MiscUtils.a((String)UserManager.a().h())) return false;
        boolean bl2 = true;
        if (!bl2) {
            return false;
        }
        long l = SingApplication.g().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).getLong("CUSTOMIZE_PROFILE_PIC_TIMESTAMP", 0);
        boolean bl3 = bl;
        if (l == 0) return bl3;
        boolean bl4 = SingApplication.g().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).getBoolean("CUSTOMIZE_PROFILE_PIC_NO_MORE", false);
        if (System.currentTimeMillis() - l <= TimeUnit.DAYS.toMillis(7)) return false;
        bl3 = bl;
        if (!bl4) return bl3;
        return false;
    }

    private void y() {
        this.a("Invite", new Runnable(){

            @Override
            public void run() {
                Intent intent = ChatShareInviteActivity.a((Context)PostSingFlowActivity.this).a(PostSingFlowActivity.this.n).a(PostSingFlowActivity.this.n.s()).a(PostSingFlowActivity.this.h.h).a(PostSingFlowActivity.c((PostSingFlowActivity)PostSingFlowActivity.this).s).a(Analytics.h).b(PostSingFlowActivity.this.x()).b();
                PostSingFlowActivity.this.startActivity(intent);
            }
        });
    }

    private void z() {
        this.a("Share", new Runnable(){

            @Override
            public void run() {
                Intent intent = ShareActivity.a(PostSingFlowActivity.this.getApplicationContext(), PostSingFlowActivity.this.n, PostSingFlowActivity.c((PostSingFlowActivity)PostSingFlowActivity.this).j, null, PostSingFlowActivity.c((PostSingFlowActivity)PostSingFlowActivity.this).s, Analytics.h);
                PostSingFlowActivity.this.startActivity(intent);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onCreate(Bundle bundle) {
        block16 : {
            block15 : {
                super.onCreate(bundle);
                Log.b(k, "starting");
                this.m = this.h.b;
                this.n = this.h.c;
                if (this.m.o) break block15;
                boolean bl = !this.m.k && this.n != null && (this.n.e() || this.n.f());
                if (bl) {
                    this.y();
                } else if (this.n != null) {
                    this.z();
                }
                boolean bl2 = new SingServerValues().H();
                if (!this.h.d && bl2) {
                    this.A();
                }
                if (bl && this.x()) {
                    this.v();
                    this.w();
                } else {
                    bundle = this.t();
                    if (SurveyPresenter.a().b((Activity)this, (SurveyContext)bundle)) {
                        this.b((SurveyContext)bundle);
                    } else if (SurveyPresenter.a().a(this.m.d)) {
                        this.a((SurveyContext)bundle);
                    }
                }
                if (this.m.o && TrialSubscriptionActivity.a((Context)this)) {
                    this.B();
                }
                if (this.h.d) {
                    this.C();
                } else {
                    this.D();
                }
                if (MagicAdSettings.c()) break block16;
            }
            return;
        }
        this.a((Activity)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            this.g.c();
            this.g = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        NotificationCenter.a().b(SurveyPresenter.b, this.j);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationCenter.a().a(SurveyPresenter.b, this.j);
        this.b();
    }

    class Task {
        private String b;
        private Runnable c;

        public Task(String string2, Runnable runnable) {
            Log.b(k, "Created task: " + string2);
            this.b = string2;
            this.c = runnable;
        }

        public void a() {
            Log.b(k, "Running task: " + this.b);
            this.c.run();
        }
    }

}

