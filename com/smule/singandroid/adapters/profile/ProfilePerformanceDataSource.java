/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 */
package com.smule.singandroid.adapters.profile;

import android.os.Parcel;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.datasource.FillLocalPerfsDataSource;
import com.smule.singandroid.profile.ProfileFragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public class ProfilePerformanceDataSource
extends FillLocalPerfsDataSource {
    private boolean a;
    private boolean b = true;
    private List<PerformanceListItemContainer> q;

    public ProfilePerformanceDataSource(ProfileFragment profileFragment) {
        super(ProfilePerformanceDataSource.class.getSimpleName() + ":" + profileFragment.F().accountId, profileFragment, false);
    }

    @Override
    public int a() {
        return 25;
    }

    @Override
    public Future<?> a(final MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<PerformanceListItemContainer, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        final int n2 = this.o.P();
        return com.smule.android.network.managers.PerformanceManager.a().a(this.o.F().accountId, null, PerformancesAPI.FILLED, true, offsetPaginationTracker.a(), n, new PerformanceManager(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(PerformanceManager.PerformancesByPerformerResponse performancesByPerformerResponse) {
                Object object;
                if (ProfilePerformanceDataSource.this.o == null || !ProfilePerformanceDataSource.this.o.a(n2)) {
                    fetchDataCallback.a();
                    return;
                }
                if (!performancesByPerformerResponse.a()) {
                    fetchDataCallback.a();
                    ProfilePerformanceDataSource.this.o.b(2131297210);
                    return;
                }
                if (ProfilePerformanceDataSource.this.b) {
                    ProfilePerformanceDataSource.this.u();
                    ProfilePerformanceDataSource.this.b = false;
                }
                if (performancesByPerformerResponse.mTotalPerformances != null) {
                    ProfilePerformanceDataSource.this.l = performancesByPerformerResponse.mTotalPerformances;
                }
                ArrayList<Object> arrayList = new ArrayList<Object>();
                if (offsetPaginationTracker.a() == 0 && ProfilePerformanceDataSource.this.m.size() > 0) {
                    arrayList.addAll(ProfilePerformanceDataSource.this.m);
                }
                boolean bl = ProfilePerformanceDataSource.this.o.Y();
                int n = ProfilePerformanceDataSource.this.t() ? 0 : ProfilePerformanceDataSource.this.k();
                ProfilePerformanceDataSource.this.a = false;
                ProfilePerformanceDataSource.this.q = new ArrayList();
                for (int i = 0; i < performancesByPerformerResponse.mPerformances.size(); ++i) {
                    object = performancesByPerformerResponse.mPerformances.get(i);
                    if (!ProfilePerformanceDataSource.this.o.a((PerformanceV2)object, PerformancesAPI.FILLED)) continue;
                    if (!bl && ProfilePerformanceDataSource.this.n > 0 && arrayList.size() + n >= ProfilePerformanceDataSource.this.n) {
                        ProfilePerformanceDataSource.this.a = true;
                    }
                    object = new PerformanceListItemContainer((PerformanceV2)object);
                    if (!ProfilePerformanceDataSource.this.a) {
                        if (ProfilePerformanceDataSource.this.m.contains(object)) {
                            ProfilePerformanceDataSource.this.m.remove(object);
                            arrayList.remove(object);
                        }
                        arrayList.add(object);
                        continue;
                    }
                    if (ProfilePerformanceDataSource.this.q.contains(object)) {
                        ProfilePerformanceDataSource.this.q.remove(object);
                    }
                    if (ProfilePerformanceDataSource.this.q.size() >= 15) continue;
                    ProfilePerformanceDataSource.this.q.add(object);
                }
                object = fetchDataCallback;
                n = ProfilePerformanceDataSource.this.a ? -1 : performancesByPerformerResponse.mNext;
                object.a(arrayList, new MagicDataSource.OffsetPaginationTracker(n));
                if (!arrayList.isEmpty()) {
                    ProfilePerformanceDataSource.this.o.ap();
                    return;
                }
                ProfilePerformanceDataSource.this.o.aq();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a(Parcel parcel) {
        super.a(parcel);
        parcel.writeInt(this.l);
        byte by = this.a ? 1 : 0;
        parcel.writeByte(by);
        parcel.writeList(this.q);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void b(Parcel parcel) {
        boolean bl = true;
        super.b(parcel);
        this.l = parcel.readInt();
        if (parcel.readByte() != 1) {
            bl = false;
        }
        this.a = bl;
        this.q = parcel.readArrayList(PerformanceListItemContainer.class.getClassLoader());
    }

    public List<PerformanceListItemContainer> e() {
        if (this.q == null) {
            return new ArrayList<PerformanceListItemContainer>();
        }
        return this.q;
    }

    public void w() {
        if (this.q != null && !this.q.isEmpty()) {
            this.q.remove(0);
        }
    }

}

