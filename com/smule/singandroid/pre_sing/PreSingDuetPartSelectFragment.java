package com.smule.singandroid.pre_sing;

import com.smule.android.network.managers.UserManager;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.RecEnsembleType;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class PreSingDuetPartSelectFragment extends PreSingBaseFragment {
    public static final String f23658t = PreSingDuetPartSelectFragment.class.getName();
    protected boolean f23659u;
    @ViewById
    protected ProfileImageWithVIPBadge f23660v;

    public String mo6383s() {
        return f23658t;
    }

    protected void mo6420v() {
        SingAnalytics.m26110a(this.i.f20139d.mo6290d(), RecEnsembleType.DUET, this.i.f20139d.mo6289c());
    }

    @Click
    protected void mo6897a() {
        if (!this.f23659u) {
            this.f23659u = true;
            SingAnalytics.m26062a(1, this.i.f20139d.mo6290d(), RecEnsembleType.DUET, this.i.f20139d.mo6289c());
            m24847a(true, false, 1);
        }
    }

    @Click
    protected void m24899R() {
        if (!this.f23659u) {
            this.f23659u = true;
            SingAnalytics.m26062a(2, this.i.f20139d.mo6290d(), RecEnsembleType.DUET, this.i.f20139d.mo6289c());
            m24847a(true, false, 2);
        }
    }

    @Click
    protected void m24900S() {
        if (!this.f23659u) {
            this.f23659u = true;
            SingAnalytics.m26062a(0, this.i.f20139d.mo6290d(), RecEnsembleType.DUET, this.i.f20139d.mo6289c());
            m24847a(true, false, 0);
        }
    }

    protected void mo6896E() {
        super.mo6896E();
        this.f23660v.setProfilePicUrl(UserManager.a().h());
        this.f23660v.setVIP(this.i.f20147l);
    }

    public void mo6904w() {
        super.mo6904w();
        this.f23659u = false;
    }
}
