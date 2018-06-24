package com.smule.singandroid.survey;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.smule.android.core.state_machine.StateMachineError;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.StringCacheManager;
import com.smule.singandroid.MagicPreferences;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.survey.SurveyStateMachine.State;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SurveyPresenter {
    public static final String f24308a = SurveyPresenter.class.getName();
    public static final String f24309b = (f24308a + "SURVEY_COMPLETE_EVENT");
    private static SurveyPresenter f24310d;
    private static Random f24311h = new Random();
    protected SurveyStateCommander f24312c;
    private SurveyConfig f24313e;
    private SurveyRatingDialog f24314f;
    private SurveyReasonDialog f24315g;

    class C49221 implements Observer {
        final /* synthetic */ SurveyPresenter f24299a;

        C49221(SurveyPresenter surveyPresenter) {
            this.f24299a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            this.f24299a.m25529j();
        }
    }

    class C49232 implements Observer {
        final /* synthetic */ SurveyPresenter f24300a;

        C49232(SurveyPresenter surveyPresenter) {
            this.f24300a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            this.f24300a.m25530k();
        }
    }

    class C49243 implements Observer {
        final /* synthetic */ SurveyPresenter f24301a;

        C49243(SurveyPresenter surveyPresenter) {
            this.f24301a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            if (obj instanceof RatingInterface) {
                this.f24301a.m25518b((RatingInterface) obj);
            } else {
                this.f24301a.m25521b("SurveyStateCommander.RATING_COMPLETE should only broadcast RatingInterface");
            }
        }
    }

    class C49254 implements Observer {
        final /* synthetic */ SurveyPresenter f24302a;

        C49254(SurveyPresenter surveyPresenter) {
            this.f24302a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            this.f24302a.m25531l();
        }
    }

    class C49265 implements Observer {
        final /* synthetic */ SurveyPresenter f24303a;

        C49265(SurveyPresenter surveyPresenter) {
            this.f24303a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            if (obj instanceof ReasonInterface) {
                this.f24303a.m25519b((ReasonInterface) obj);
            } else {
                this.f24303a.m25521b("SurveyStateCommander.REASON_SELECTED should only broadcast ReasonInterface");
            }
        }
    }

    class C49276 implements Observer {
        final /* synthetic */ SurveyPresenter f24304a;

        C49276(SurveyPresenter surveyPresenter) {
            this.f24304a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            this.f24304a.m25532m();
        }
    }

    class C49287 implements Observer {
        final /* synthetic */ SurveyPresenter f24305a;

        C49287(SurveyPresenter surveyPresenter) {
            this.f24305a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            this.f24305a.m25533n();
        }
    }

    class C49298 implements Observer {
        final /* synthetic */ SurveyPresenter f24306a;

        C49298(SurveyPresenter surveyPresenter) {
            this.f24306a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            this.f24306a.m25534o();
        }
    }

    class C49309 implements Observer {
        final /* synthetic */ SurveyPresenter f24307a;

        C49309(SurveyPresenter surveyPresenter) {
            this.f24307a = surveyPresenter;
        }

        public void update(Observable observable, Object obj) {
            this.f24307a.m25535p();
        }
    }

    public static synchronized SurveyPresenter m25508a() {
        SurveyPresenter surveyPresenter;
        synchronized (SurveyPresenter.class) {
            if (f24310d == null) {
                f24310d = new SurveyPresenter();
            }
            surveyPresenter = f24310d;
        }
        return surveyPresenter;
    }

    public boolean m25548b() {
        return this.f24312c.m25577b();
    }

    public void m25543a(Context context, PerformanceV2 performanceV2) {
        MagicPreferences.m20300a(context, performanceV2);
    }

    public PerformanceV2 m25541a(Context context) {
        return MagicPreferences.m20311b(context, null);
    }

    public boolean m25547a(@NonNull SongbookEntry songbookEntry) {
        return (m25508a().m25548b() || !songbookEntry.m18772r() || StringCacheManager.a().d(songbookEntry.mo6289c())) ? false : true;
    }

    public void m25542a(@NonNull Activity activity, @NonNull SurveyContext surveyContext) {
        this.f24313e = new ArrangementConfig(activity, surveyContext);
        m25540u();
    }

    public void m25546a(String str) {
        StringCacheManager.a().c(str);
    }

    public boolean m25549b(@NonNull Activity activity, @NonNull SurveyContext surveyContext) {
        if (m25508a().m25548b()) {
            return false;
        }
        if (surveyContext.f24288b.m25505f()) {
            return true;
        }
        boolean z = (surveyContext.f24287a == null || surveyContext.f24287a.f19316b == null || !surveyContext.f24287a.f19316b.f20149n) ? false : true;
        if (z) {
            return false;
        }
        double M = SingServerValues.m21675M();
        if (Math.abs(M - 1.0d) < 1.0E-5d) {
            return true;
        }
        if (System.currentTimeMillis() - MagicPreferences.m20318c(activity, 0) < TimeUnit.DAYS.toMillis(1) || f24311h.nextDouble() > M) {
            return false;
        }
        return true;
    }

    public void m25551c(Activity activity, SurveyContext surveyContext) {
        MagicPreferences.m20314b((Context) activity, System.currentTimeMillis());
        this.f24313e = new AVQualityConfig(activity, surveyContext);
        m25540u();
    }

    public void m25544a(RatingInterface ratingInterface) {
        try {
            this.f24312c.m25575a(ratingInterface);
        } catch (Exception e) {
            if (e.m17588a() == StateMachineError.COMMAND_NOT_ALLOWED_IN_CURRENT_STATE && this.f24312c.m25574a() == State.RATING_COMPLETE) {
                Log.b(f24308a, "Disregarding selectRating burst");
            } else {
                m25513a(e);
            }
        }
    }

    public void m25550c() {
        try {
            this.f24312c.m25581f();
        } catch (Exception e) {
            if (e.m17588a() == StateMachineError.COMMAND_NOT_ALLOWED_IN_CURRENT_STATE && this.f24312c.m25574a() == State.FINISHED) {
                Log.b(f24308a, "Disregarding cancelRating burst");
            } else {
                m25513a(e);
            }
        }
    }

    public void m25545a(ReasonInterface reasonInterface) {
        try {
            this.f24312c.m25576a(reasonInterface);
        } catch (Exception e) {
            m25513a(e);
        }
    }

    public void m25552d() {
        try {
            this.f24312c.m25583h();
        } catch (Exception e) {
            if (e.m17588a() == StateMachineError.COMMAND_NOT_ALLOWED_IN_CURRENT_STATE && this.f24312c.m25574a() == State.REASON_COMPLETE) {
                Log.b(f24308a, "Disregarding commitReason burst");
            } else {
                m25513a(e);
            }
        }
    }

    public void m25553e() {
        try {
            this.f24312c.m25584i();
        } catch (Exception e) {
            if (e.m17588a() == StateMachineError.COMMAND_NOT_ALLOWED_IN_CURRENT_STATE && this.f24312c.m25574a() == State.REPORT_STARTED) {
                Log.b(f24308a, "Disregarding cancelReason burst");
            } else {
                m25513a(e);
            }
        }
    }

    public void m25554f() {
        try {
            this.f24312c.m25586k();
        } catch (Exception e) {
            m25513a(e);
        }
    }

    public void m25555g() {
        try {
            this.f24312c.m25587l();
        } catch (Exception e) {
            if (e.m17588a() == StateMachineError.COMMAND_NOT_ALLOWED_IN_CURRENT_STATE && this.f24312c.m25574a() == State.FINISHED) {
                Log.b(f24308a, "Disregarding cancelExtras burst");
            } else {
                m25513a(e);
            }
        }
    }

    public static void m25515a(String str, Object obj) {
        m25516a(str, obj, true);
    }

    public static void m25516a(String str, Object obj, boolean z) {
        if (!SurveyStateCommander.m25570r().contains(str)) {
            Log.e(f24308a, "Notification name not declared in SurveyStateCommander.getNotificationNames(): " + str);
        }
        NotificationCenter.m19011a().m19013a(str, obj, z);
    }

    public static void m25517a(String str, Object... objArr) {
        if (!SurveyStateCommander.m25570r().contains(str)) {
            Log.e(f24308a, "Notification name not declared in SurveyStateCommander.getNotificationNames(): " + str);
        }
        NotificationCenter.m19011a().m19017b(str, objArr);
    }

    private void m25529j() {
        try {
            if (this.f24313e.mo6942d()) {
                this.f24312c.m25580e();
            } else {
                this.f24312c.m25579d();
            }
        } catch (Exception e) {
            m25513a(e);
        }
    }

    private void m25530k() {
        this.f24314f = this.f24313e.mo6944f();
        this.f24314f.show();
    }

    private void m25518b(RatingInterface ratingInterface) {
        try {
            if (ratingInterface == this.f24313e.mo6939b()) {
                this.f24312c.m25588m();
            } else {
                this.f24312c.m25582g();
            }
        } catch (Exception e) {
            m25513a(e);
        }
    }

    private void m25531l() {
        if (this.f24313e.mo6942d()) {
            this.f24314f.m25493i();
        }
        this.f24315g = this.f24313e.mo6936a(this.f24314f);
        this.f24315g.show();
    }

    private void m25519b(ReasonInterface reasonInterface) {
        this.f24315g.m19810b(true);
    }

    private void m25532m() {
        try {
            this.f24312c.m25588m();
        } catch (Exception e) {
            m25513a(e);
        }
    }

    private void m25533n() {
        try {
            this.f24313e.mo6938a(this.f24312c.m25591p(), this.f24312c.m25592q());
            this.f24312c.m25589n();
        } catch (Exception e) {
            m25513a(e);
        }
    }

    private void m25534o() {
        try {
            if (m25557i().m25591p() == this.f24313e.mo6939b()) {
                this.f24312c.m25585j();
            } else {
                this.f24312c.m25590o();
            }
        } catch (Exception e) {
            m25513a(e);
        }
    }

    private void m25535p() {
        try {
            this.f24313e.mo6940b(this.f24314f).run();
        } catch (Exception e) {
            m25513a(e);
        }
    }

    private void m25536q() {
        try {
            this.f24312c.m25590o();
        } catch (Exception e) {
            m25513a(e);
        }
    }

    private void m25537r() {
        m25539t();
        NotificationCenter.m19011a().m19017b(f24309b, new Object[0]);
    }

    private SurveyPresenter() {
        m25538s();
        m25556h();
    }

    private void m25538s() {
        try {
            this.f24312c = new SurveyStateCommander();
        } catch (Throwable e) {
            Log.d(f24308a, "This should only ever be caused when our SurveyStateMachine is configured incorrectly.", e);
        }
    }

    public void m25556h() {
        NotificationCenter.m19011a().m19014a(State.SURVEY_STARTED.m25595a(), new C49221(this));
        NotificationCenter.m19011a().m19014a(State.RATING_STARTED.m25595a(), new C49232(this));
        NotificationCenter.m19011a().m19014a(State.RATING_COMPLETE.m25595a(), new C49243(this));
        NotificationCenter.m19011a().m19014a(State.REASON_STARTED.m25595a(), new C49254(this));
        NotificationCenter.m19011a().m19014a(State.HAVE_REASON.m25595a(), new C49265(this));
        NotificationCenter.m19011a().m19014a(State.REASON_COMPLETE.m25595a(), new C49276(this));
        NotificationCenter.m19011a().m19014a(State.REPORT_STARTED.m25595a(), new C49287(this));
        NotificationCenter.m19011a().m19014a(State.REPORT_COMPLETE.m25595a(), new C49298(this));
        NotificationCenter.m19011a().m19014a(State.EXTRAS_STARTED.m25595a(), new C49309(this));
        NotificationCenter.m19011a().m19014a(State.EXTRAS_COMPLETE.m25595a(), new Observer(this) {
            final /* synthetic */ SurveyPresenter f24297a;

            {
                this.f24297a = r1;
            }

            public void update(Observable observable, Object obj) {
                this.f24297a.m25536q();
            }
        });
        NotificationCenter.m19011a().m19014a(State.FINISHED.m25595a(), new Observer(this) {
            final /* synthetic */ SurveyPresenter f24298a;

            {
                this.f24298a = r1;
            }

            public void update(Observable observable, Object obj) {
                this.f24298a.m25537r();
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m25539t() {
        /*
        r4 = this;
        r3 = 0;
        r0 = r4.f24314f;	 Catch:{ Exception -> 0x002a }
        if (r0 == 0) goto L_0x0012;
    L_0x0005:
        r0 = r4.f24314f;	 Catch:{ Exception -> 0x002a }
        r0 = r0.isShowing();	 Catch:{ Exception -> 0x002a }
        if (r0 == 0) goto L_0x0012;
    L_0x000d:
        r0 = r4.f24314f;	 Catch:{ Exception -> 0x002a }
        r0.dismiss();	 Catch:{ Exception -> 0x002a }
    L_0x0012:
        r0 = r4.f24315g;	 Catch:{ Exception -> 0x002a }
        if (r0 == 0) goto L_0x0023;
    L_0x0016:
        r0 = r4.f24315g;	 Catch:{ Exception -> 0x002a }
        r0 = r0.isShowing();	 Catch:{ Exception -> 0x002a }
        if (r0 == 0) goto L_0x0023;
    L_0x001e:
        r0 = r4.f24315g;	 Catch:{ Exception -> 0x002a }
        r0.dismiss();	 Catch:{ Exception -> 0x002a }
    L_0x0023:
        r4.f24314f = r3;
        r4.f24315g = r3;
        r4.f24313e = r3;
    L_0x0029:
        return;
    L_0x002a:
        r0 = move-exception;
        r1 = f24308a;	 Catch:{ all -> 0x003c }
        r2 = "Exception occurred when trying to dismiss old dialogs";
        com.smule.android.logging.Log.d(r1, r2, r0);	 Catch:{ all -> 0x003c }
        r4.m25538s();	 Catch:{ all -> 0x003c }
        r4.f24314f = r3;
        r4.f24315g = r3;
        r4.f24313e = r3;
        goto L_0x0029;
    L_0x003c:
        r0 = move-exception;
        r4.f24314f = r3;
        r4.f24315g = r3;
        r4.f24313e = r3;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.singandroid.survey.SurveyPresenter.t():void");
    }

    private void m25540u() {
        try {
            this.f24312c.m25578c();
        } catch (Exception e) {
            if (e.m17588a() == StateMachineError.COMMAND_NOT_ALLOWED_IN_CURRENT_STATE && (this.f24312c.m25574a() == State.SURVEY_STARTED || this.f24312c.m25574a() == State.REASON_STARTED)) {
                Log.b(f24308a, "Disregarding startSurvey burst");
                return;
            }
            m25513a(e);
            NotificationCenter.m19011a().m19017b(f24309b, new Object[0]);
        }
    }

    private void m25513a(Exception exception) {
        m25514a(null, exception);
    }

    private void m25521b(String str) {
        m25514a(str, null);
    }

    private void m25514a(String str, Exception exception) {
        String str2 = "Abandoning survey ";
        if (str != null) {
            str2 = str2 + "because: " + str;
        }
        if (exception == null) {
            Log.e(f24308a, str2);
        } else {
            Log.d(f24308a, str2, exception);
        }
        m25539t();
        m25538s();
    }

    protected SurveyStateCommander m25557i() {
        return this.f24312c;
    }
}
