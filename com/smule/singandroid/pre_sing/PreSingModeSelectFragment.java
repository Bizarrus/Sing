package com.smule.singandroid.pre_sing;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.datasources.OpenSeedsDataSource;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataState;
import com.smule.android.network.managers.PerformanceManager$PerformancesResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.UiAwareRunnable;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.RecClkType;
import com.smule.singandroid.utils.SingAnalytics.RecEnsembleType;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.util.Iterator;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class PreSingModeSelectFragment extends PreSingBaseFragment {
    private static final String f23691E = PreSingModeSelectFragment.class.getName();
    @ViewById
    protected View f23692A;
    @ViewById
    protected View f23693B;
    @ViewById
    protected View f23694C;
    @ViewById
    protected TextView f23695D;
    @ViewById
    protected RelativeLayout f23696t;
    @ViewById
    protected TextView f23697u;
    @ViewById
    protected ImageView f23698v;
    @ViewById
    protected TextView f23699w;
    @ViewById
    protected TextView f23700x;
    @ViewById
    protected TextView f23701y;
    @ViewById
    protected TextView f23702z;

    class C47701 implements PerformanceManager$PerformancesResponseCallback {
        final /* synthetic */ PreSingModeSelectFragment f23686a;

        C47701(PreSingModeSelectFragment preSingModeSelectFragment) {
            this.f23686a = preSingModeSelectFragment;
        }

        public void handleResponse(PerformancesResponse performancesResponse) {
            if (performancesResponse.a()) {
                int i;
                if (this.f23686a.k.m18772r()) {
                    Iterator it = performancesResponse.mPerformances.iterator();
                    i = 0;
                    while (it.hasNext()) {
                        int i2;
                        if (((PerformanceV2) it.next()).n()) {
                            i2 = i + 1;
                        } else {
                            i2 = i;
                        }
                        i = i2;
                    }
                } else {
                    i = performancesResponse.mPerformances.size();
                }
                this.f23686a.m24946d(i);
            }
        }
    }

    class C47712 implements Runnable {
        final /* synthetic */ PreSingModeSelectFragment f23687a;

        C47712(PreSingModeSelectFragment preSingModeSelectFragment) {
            this.f23687a = preSingModeSelectFragment;
        }

        public void run() {
            if (this.f23687a.isAdded() && !this.f23687a.p.get()) {
                this.f23687a.m24843a(this.f23687a.f23701y, true, false);
                this.f23687a.m19839a(this.f23687a.m24842a(this.f23687a.f23701y), 500);
            }
        }
    }

    class C47734 implements Runnable {
        final /* synthetic */ PreSingModeSelectFragment f23690a;

        C47734(PreSingModeSelectFragment preSingModeSelectFragment) {
            this.f23690a = preSingModeSelectFragment;
        }

        public void run() {
            this.f23690a.mo6905b(this.f23690a.n.multipart);
        }
    }

    public String mo6383s() {
        return f23691E;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.i.f20139d.m18772r()) {
            m24846a(false, null);
        }
        m24848b(this.k, this.j);
    }

    protected void mo6420v() {
        SingAnalytics.m26084a(m24828D(), this.i.f20139d.mo6290d(), SongbookEntryUtils.m26168c(this.i.f20139d));
    }

    protected void mo6896E() {
        super.mo6896E();
        PerformanceManager$PerformancesResponseCallback c47701 = new C47701(this);
        this.p.set(false);
        this.o = new OpenSeedsDataSource(this.k, false, SingApplication.o(), c47701);
        if (this.o.m17659i() == DataState.NONE) {
            this.o.m17665o();
        } else {
            m24946d(this.o.m17661k());
        }
        this.f23701y.setVisibility(4);
        new Handler().postDelayed(new C47712(this), 500);
        if (PerformanceV2Util.m25941a(this.k.mo6289c())) {
            this.f23702z.setText(getResources().getString(C1947R.string.pre_singing_title_freestyle));
        }
        m24841Q();
    }

    private void m24946d(final int i) {
        new UiHandler((Fragment) this).m19080a(new UiAwareRunnable(this) {
            final /* synthetic */ PreSingModeSelectFragment f23689b;

            public void mo6509a(boolean z) {
                if (z) {
                    this.f23689b.p.set(true);
                    this.f23689b.f23701y.setVisibility(8);
                    if (i < 1) {
                        this.f23689b.f23697u.setVisibility(0);
                        this.f23689b.f23697u.setText(C1947R.string.pre_singing_no_singers);
                        this.f23689b.f23697u.setTextColor(this.f23689b.getResources().getColor(C1947R.color.empty_screen_text));
                        this.f23689b.f23698v.setVisibility(0);
                        this.f23689b.l = false;
                        this.f23689b.f23698v.setImageDrawable(this.f23689b.getResources().getDrawable(C1947R.drawable.icn_no_open_calls));
                        this.f23689b.f23700x.setVisibility(0);
                        this.f23689b.f23700x.setText(C1947R.string.pre_singing_vip_no_open_cta);
                        this.f23689b.f23700x.setTextColor(this.f23689b.getResources().getColor(C1947R.color.empty_screen_text));
                        this.f23689b.f23699w.setVisibility(8);
                        this.f23689b.f23695D.setVisibility(4);
                        this.f23689b.m24843a(this.f23689b.f23696t, true, true);
                    } else if (this.f23689b.f23700x.getVisibility() == 0) {
                        this.f23689b.m24843a(this.f23689b.f23700x, false, true);
                    }
                }
            }
        });
    }

    @Click
    protected void mo6897a() {
        m24952c(false);
        SingAnalytics.m26083a(m24828D(), this.i.f20139d.mo6290d(), RecClkType.START, RecEnsembleType.SOLO, SongbookEntryUtils.m26168c(this.i.f20139d));
        m24847a(false, false, 0);
    }

    @Click
    protected void m24948R() {
        boolean z = false;
        m24952c(false);
        SingAnalytics.m26083a(m24828D(), this.i.f20139d.mo6290d(), RecClkType.START, RecEnsembleType.DUET, SongbookEntryUtils.m26168c(this.i.f20139d));
        if (!PerformanceV2Util.m25941a(this.k.mo6289c())) {
            z = true;
        }
        if (z && this.k.m18772r()) {
            if (this.n == null) {
                m24846a(true, new C47734(this));
                return;
            }
            z = this.n.multipart;
        }
        mo6905b(z);
    }

    @Click
    protected void m24949S() {
        m24952c(false);
        SingAnalytics.m26083a(m24828D(), this.i.f20139d.mo6290d(), RecClkType.START, RecEnsembleType.GROUP, SongbookEntryUtils.m26168c(this.i.f20139d));
        m24847a(false, true, 0);
    }

    @SupposeUiThread
    protected void mo6905b(boolean z) {
        Log.b(f23691E, "showPartSelectionView - called");
        if (z) {
            m24845a(ViewPhase.DUET_PART_SELECT);
        } else {
            m24847a(true, false, 0);
        }
    }

    protected void m24952c(boolean z) {
        this.f23692A.setEnabled(z);
        this.f23692A.setClickable(z);
        this.f23693B.setEnabled(z);
        this.f23693B.setClickable(z);
        this.f23694C.setEnabled(z);
        this.f23694C.setClickable(z);
    }

    public void mo6904w() {
        m24952c(true);
    }
}
