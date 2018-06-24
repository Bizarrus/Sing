/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Bundle
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$EntryPoint
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$VisualizerType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.OpenCallFragment_;
import com.smule.singandroid.SimpleTypeTabs;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.adapters.OpenCallListAdapter;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.fragments.OpenCallListFragment;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EFragment
public class OpenCallFragment
extends OpenCallListFragment
implements OpenCallListFragment.OpenCallListListener {
    private static final String A = OpenCallFragment.class.getName();
    private static final int[][] B = new int[][]{{2131297502, 2131297501, 2131296731}, {2131296665, 2131296731}};
    private static final JoinSectionType[][] C;
    private JoinSectionType D;
    @ViewById
    SimpleTypeTabs g;
    @FragmentArg
    @InstanceState
    protected SingBundle h;
    @InstanceState
    protected SongbookEntry i;
    boolean j = false;

    static {
        JoinSectionType joinSectionType = JoinSectionType.f;
        JoinSectionType joinSectionType2 = JoinSectionType.e;
        JoinSectionType joinSectionType3 = JoinSectionType.g;
        JoinSectionType joinSectionType4 = JoinSectionType.e;
        JoinSectionType joinSectionType5 = JoinSectionType.g;
        C = new JoinSectionType[][]{{joinSectionType, joinSectionType2, joinSectionType3}, {joinSectionType4, joinSectionType5}};
    }

    public static OpenCallFragment a(SingBundle singBundle) {
        return new OpenCallFragment_.FragmentBuilder_().a(singBundle).a();
    }

    private void a(int n, OpenCallListAdapter[] arropenCallListAdapter, JoinSectionType[] arrjoinSectionType) {
        switch (n) {
            default: {
                this.k = arropenCallListAdapter[0];
                this.D = arrjoinSectionType[0];
                return;
            }
            case 0: {
                this.k = arropenCallListAdapter[0];
                this.D = arrjoinSectionType[0];
                return;
            }
            case 1: {
                this.k = arropenCallListAdapter[1];
                this.D = arrjoinSectionType[1];
                return;
            }
            case 2: 
        }
        this.k = arropenCallListAdapter[2];
        this.D = arrjoinSectionType[2];
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(int[] arrn) {
        int n = 0;
        while (n < 4) {
            if (n < arrn.length) {
                this.g.a(n, arrn[n]);
            } else {
                this.g.a(n, false);
            }
            ++n;
        }
    }

    @Override
    protected void A() {
        SingAnalytics.a((String)SongbookEntry.b(this.i), (String)SongbookEntry.a(this.i), (SingAnalytics.VisualizerType)null);
        if (this.D == null) {
            this.D = JoinSectionType.e;
        }
        SingAnalytics.a((JoinSectionType)this.D, (String)SongbookEntry.b(this.i), (String)SingAnalytics.b((SongbookEntry)this.i));
    }

    @Override
    protected JoinSectionType F() {
        return this.D;
    }

    @Override
    protected boolean G() {
        return this.i.q();
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void a() {
        block6 : {
            Object object;
            int n;
            block5 : {
                n = 1;
                OpenCallListAdapter openCallListAdapter = this.l;
                OpenCallListAdapter openCallListAdapter2 = this.m;
                OpenCallListAdapter openCallListAdapter3 = this.n;
                OpenCallListAdapter openCallListAdapter4 = this.m;
                OpenCallListAdapter openCallListAdapter5 = this.n;
                this.c(this.i.g());
                SingAppboy.a().e();
                if (SingApplication.n()) {
                    this.a(BaseFragment.ActionBarHighlightMode.b);
                    this.g.setVisibility(0);
                    object = this.j ? B[0] : B[1];
                    this.a((int[])object);
                    this.g.setOnTabClickListener(new SimpleTypeTabs.OnTabClickListener(new OpenCallListAdapter[][]{{openCallListAdapter, openCallListAdapter2, openCallListAdapter3}, {openCallListAdapter4, openCallListAdapter5}}){
                        final /* synthetic */ OpenCallListAdapter[][] a;
                        {
                            this.a = arropenCallListAdapter;
                        }

                        /*
                         * Enabled aggressive block sorting
                         */
                        @Override
                        public void b_(int n) {
                            OpenCallListAdapter[] arropenCallListAdapter = OpenCallFragment.this.j ? this.a[0] : this.a[1];
                            JoinSectionType[] arrjoinSectionType = OpenCallFragment.this.j ? C[0] : C[1];
                            OpenCallFragment.this.a(n, arropenCallListAdapter, arrjoinSectionType);
                            OpenCallFragment.this.k.a(OpenCallFragment.this.i);
                            if (OpenCallFragment.this.k.getCount() == 0) {
                                OpenCallFragment.this.k.c();
                            } else {
                                OpenCallFragment.this.e(1);
                            }
                            OpenCallFragment.this.s.setAdapter((ListAdapter)OpenCallFragment.this.k);
                            SingAnalytics.a((JoinSectionType)OpenCallFragment.this.D, (String)SongbookEntry.b(OpenCallFragment.this.i), (String)SingAnalytics.b((SongbookEntry)OpenCallFragment.this.i));
                        }
                    });
                }
                switch (.a[this.t().ordinal()]) {
                    default: {
                        object = this.g;
                        if (!this.j) break;
                        break block5;
                    }
                    case 1: {
                        this.g.a(0);
                        object = this.j ? this.l : this.m;
                        this.k = object;
                        this.D = JoinSectionType.f;
                        break block6;
                    }
                    case 2: {
                        object = this.g;
                        n = this.j ? 2 : 1;
                        object.a(n);
                        this.k = this.n;
                        this.D = JoinSectionType.g;
                        break block6;
                    }
                }
                n = 0;
            }
            object.a(n);
            this.k = this.m;
            this.D = JoinSectionType.e;
        }
        this.k.a(this.i);
        this.k.c();
        this.a(this);
        this.a(this.i, (PerformanceV2)null);
    }

    @Override
    public void a(PerformanceV2 performanceV2) {
        PreSingActivity.a((Context)this.getActivity()).a(PreSingActivity.StartupMode.b).a(this.i).a(performanceV2).a(this.h.s.longValue()).a(PreSingActivity.EntryPoint.j).a();
        SingAnalytics.a((String)PerformanceV2Util.i((PerformanceV2)performanceV2), (JoinSectionType)this.D, (String)PerformanceV2Util.h((PerformanceV2)performanceV2));
    }

    @Override
    public void d(int n) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = this.h.d;
        this.j = new SingServerValues().ap();
        Log.b(A, this.h.toString());
    }

    @Override
    protected JoinSectionType t() {
        if (this.h != null && this.h.w != null) {
            return this.h.w;
        }
        return JoinSectionType.e;
    }

    @Override
    public String x() {
        return A;
    }

}

