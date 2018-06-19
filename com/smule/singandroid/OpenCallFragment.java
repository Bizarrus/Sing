package com.smule.singandroid;

import android.os.Bundle;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment.ActionBarHighlightMode;
import com.smule.singandroid.OpenCallFragment_.FragmentBuilder_;
import com.smule.singandroid.SimpleTypeTabs.OnTabClickListener;
import com.smule.singandroid.fragments.OpenCallListFragment;
import com.smule.singandroid.fragments.OpenCallListFragment.OpenCallListListener;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FilterType;
import com.smule.singandroid.utils.SingAnalytics.SectionType;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OpenCallFragment extends OpenCallListFragment implements OpenCallListListener {
    private static final String f19105v = OpenCallFragment.class.getName();
    @ViewById
    SimpleTypeTabs f19106e;
    @FragmentArg
    @InstanceState
    protected SingBundle f19107f;
    @InstanceState
    protected SongbookEntry f19108g;

    class C39191 implements OnTabClickListener {
        final /* synthetic */ OpenCallFragment f19086a;

        C39191(OpenCallFragment openCallFragment) {
            this.f19086a = openCallFragment;
        }

        public void b_(int i) {
            if (i == 0) {
                this.f19086a.h = this.f19086a.i;
            } else {
                this.f19086a.h = this.f19086a.j;
            }
            this.f19086a.h.m22040a(this.f19086a.f19108g);
            if (this.f19086a.h.getCount() == 0) {
                this.f19086a.h.m22044c();
            } else {
                this.f19086a.m20616e(1);
            }
            this.f19086a.o.setAdapter(this.f19086a.h);
        }
    }

    public String mo6383s() {
        return f19105v;
    }

    public static OpenCallFragment m20621a(SingBundle singBundle) {
        return new FragmentBuilder_().m20640a(singBundle).m20639a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f19108g = this.f19107f.f20139d;
        Log.b(f19105v, this.f19107f.toString());
    }

    protected void mo6420v() {
        SingAnalytics.m26116a(SongbookEntry.m18752b(this.f19108g), SongbookEntry.m18749a(this.f19108g), null);
    }

    @AfterViews
    protected void m20632a() {
        m20615b(this.f19108g.mo6293g());
        if (SingApplication.o()) {
            m19831a(ActionBarHighlightMode.ALWAYS);
            this.f19106e.setVisibility(0);
            this.f19106e.m21484a(0, (int) C1947R.string.core_all);
            this.f19106e.m21484a(1, (int) C1947R.string.core_video_only);
            this.f19106e.m21485a(2, false);
            this.f19106e.m21485a(3, false);
            this.f19106e.setOnTabClickListener(new C39191(this));
        }
        this.h = this.i;
        this.h.m22040a(this.f19108g);
        this.h.m22044c();
        m20614a((OpenCallListListener) this);
        m19830a(this.f19108g, null);
    }

    public void mo6502a(PerformanceV2 performanceV2) {
        PreSingActivity.m24763a(getActivity()).m24796a(StartupMode.OPENCALL).m24794a(this.f19108g).m24793a(performanceV2).m24792a(this.f19107f.f20153r.longValue()).a();
        SingAnalytics.m26081a(performanceV2.video ? FilterType.f24970b : FilterType.NONE, SectionType.ALL);
    }

    public void mo6503d(int i) {
    }

    public boolean mo6445g() {
        return true;
    }

    protected boolean mo6504z() {
        return this.f19108g.m18769o();
    }
}
