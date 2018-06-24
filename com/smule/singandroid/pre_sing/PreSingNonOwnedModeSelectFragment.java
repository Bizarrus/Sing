package com.smule.singandroid.pre_sing;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.android.datasources.OpenSeedsDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataState;
import com.smule.android.network.managers.PerformanceManager$PerformancesResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.UiAwareRunnable;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.RecClkType;
import com.smule.singandroid.utils.SingAnalytics.RecEnsembleType;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.util.Iterator;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class PreSingNonOwnedModeSelectFragment extends PreSingBaseFragment {
    private static final String f23717z = PreSingNonOwnedModeSelectFragment.class.getName();
    @ViewById
    protected View f23718t;
    @ViewById
    protected View f23719u;
    @ViewById
    protected View f23720v;
    @ViewById
    protected TextView f23721w;
    @ViewById
    protected TextView f23722x;
    @ViewById
    protected TextView f23723y;

    class C47791 implements PerformanceManager$PerformancesResponseCallback {
        final /* synthetic */ PreSingNonOwnedModeSelectFragment f23710a;

        C47791(PreSingNonOwnedModeSelectFragment preSingNonOwnedModeSelectFragment) {
            this.f23710a = preSingNonOwnedModeSelectFragment;
        }

        public void handleResponse(PerformancesResponse performancesResponse) {
            if (performancesResponse.a()) {
                int i;
                if (this.f23710a.k.m18772r()) {
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
                this.f23710a.m24976d(i);
            }
        }
    }

    class C47802 implements Runnable {
        final /* synthetic */ PreSingNonOwnedModeSelectFragment f23711a;

        C47802(PreSingNonOwnedModeSelectFragment preSingNonOwnedModeSelectFragment) {
            this.f23711a = preSingNonOwnedModeSelectFragment;
        }

        public void run() {
            if (this.f23711a.isAdded() && !this.f23711a.p.get()) {
                this.f23711a.m24843a(this.f23711a.f23722x, true, false);
                this.f23711a.m19839a(this.f23711a.m24842a(this.f23711a.f23722x), 500);
            }
        }
    }

    class C47813 implements OnClickListener {
        final /* synthetic */ PreSingNonOwnedModeSelectFragment f23712a;

        C47813(PreSingNonOwnedModeSelectFragment preSingNonOwnedModeSelectFragment) {
            this.f23712a = preSingNonOwnedModeSelectFragment;
        }

        public void onClick(View view) {
            this.f23712a.m24978b(false);
            SingAnalytics.m26083a(this.f23712a.m24828D(), this.f23712a.i.f20139d.mo6290d(), RecClkType.START, RecEnsembleType.SOLO, SongbookEntryUtils.m26168c(this.f23712a.i.f20139d));
            this.f23712a.m24849b(PerformanceType.SOLO);
            this.f23712a.m24844a(PerformanceType.SOLO);
            this.f23712a.m24833I();
        }
    }

    class C47824 implements OnClickListener {
        final /* synthetic */ PreSingNonOwnedModeSelectFragment f23713a;

        C47824(PreSingNonOwnedModeSelectFragment preSingNonOwnedModeSelectFragment) {
            this.f23713a = preSingNonOwnedModeSelectFragment;
        }

        public void onClick(View view) {
            this.f23713a.m24978b(false);
            SingAnalytics.m26083a(this.f23713a.m24828D(), this.f23713a.i.f20139d.mo6290d(), RecClkType.START, RecEnsembleType.DUET, SongbookEntryUtils.m26168c(this.f23713a.i.f20139d));
            this.f23713a.m24849b(PerformanceType.DUET);
            this.f23713a.m24844a(PerformanceType.DUET);
            this.f23713a.m24833I();
        }
    }

    class C47835 implements OnClickListener {
        final /* synthetic */ PreSingNonOwnedModeSelectFragment f23714a;

        C47835(PreSingNonOwnedModeSelectFragment preSingNonOwnedModeSelectFragment) {
            this.f23714a = preSingNonOwnedModeSelectFragment;
        }

        public void onClick(View view) {
            this.f23714a.m24978b(false);
            SingAnalytics.m26083a(this.f23714a.m24828D(), this.f23714a.i.f20139d.mo6290d(), RecClkType.START, RecEnsembleType.GROUP, SongbookEntryUtils.m26168c(this.f23714a.i.f20139d));
            this.f23714a.m24849b(PerformanceType.GROUP);
            this.f23714a.m24844a(PerformanceType.GROUP);
            this.f23714a.m24833I();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.i.f20139d.m18772r()) {
            m24846a(false, null);
        }
        m24848b(this.k, this.j);
    }

    public String mo6383s() {
        return f23717z;
    }

    protected void mo6420v() {
        SingAnalytics.m26084a(m24828D(), this.i.f20139d.mo6290d(), this.i.f20139d.mo6289c());
    }

    protected void mo6896E() {
        super.mo6896E();
        PerformanceManager$PerformancesResponseCallback c47791 = new C47791(this);
        this.p.set(false);
        this.o = new OpenSeedsDataSource(this.k, false, SingApplication.o(), c47791);
        if (this.o.m17659i() == DataState.NONE) {
            this.o.m17665o();
        } else {
            m24976d(this.o.m17661k());
        }
        this.f23722x.setVisibility(4);
        new Handler().postDelayed(new C47802(this), 500);
        if (PerformanceV2Util.m25941a(this.k.mo6290d())) {
            this.f23723y.setText(getResources().getString(C1947R.string.pre_singing_create_freestyle));
        }
        this.f23718t.setOnClickListener(new C47813(this));
        this.f23719u.setOnClickListener(new C47824(this));
        this.f23720v.setOnClickListener(new C47835(this));
    }

    private void m24976d(final int i) {
        new UiHandler((Fragment) this).m19080a(new UiAwareRunnable(this) {
            final /* synthetic */ PreSingNonOwnedModeSelectFragment f23716b;

            public void mo6509a(boolean z) {
                if (z) {
                    this.f23716b.p.set(true);
                    this.f23716b.f23722x.clearAnimation();
                    this.f23716b.f23722x.setVisibility(8);
                    if (i < 1) {
                        this.f23716b.f23721w.setText(C1947R.string.pre_singing_no_open_cta);
                        this.f23716b.f23721w.setTextColor(this.f23716b.getResources().getColor(C1947R.color.empty_screen_text));
                        this.f23716b.f23721w.setVisibility(0);
                        this.f23716b.e.setVisibility(0);
                        ImageUtils.a(this.f23716b.e, this.f23716b.getResources().getDrawable(C1947R.drawable.button_grey));
                        this.f23716b.e.setEnabled(false);
                        this.f23716b.f.setOnClickListener(null);
                        this.f23716b.f.setBackgroundColor(this.f23716b.getResources().getColor(C1947R.color.white));
                        this.f23716b.g.setVisibility(8);
                        this.f23716b.m24843a(this.f23716b.f, true, true);
                    } else if (this.f23716b.f23721w.getVisibility() == 0) {
                        this.f23716b.m24843a(this.f23716b.f23721w, false, true);
                    }
                }
            }
        });
    }

    protected void m24978b(boolean z) {
        this.f23718t.setEnabled(z);
        this.f23718t.setClickable(z);
        this.f23719u.setEnabled(z);
        this.f23719u.setClickable(z);
        this.f23720v.setEnabled(z);
        this.f23720v.setClickable(z);
    }
}
