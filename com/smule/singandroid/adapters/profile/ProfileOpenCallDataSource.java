/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 */
package com.smule.singandroid.adapters.profile;

import android.os.Parcel;
import com.smule.android.logging.Log;
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
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class ProfileOpenCallDataSource
extends FillLocalPerfsDataSource {
    public static final String a = ProfileOpenCallDataSource.class.getName();
    protected boolean b;
    private int q;
    private int r;

    public ProfileOpenCallDataSource(ProfileFragment profileFragment) {
        super(ProfileOpenCallDataSource.class.getSimpleName() + ":" + profileFragment.F().accountId, profileFragment, true);
    }

    private void a(final int n, int n2, final MagicDataSource.FetchDataCallback<PerformanceListItemContainer, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        final int n3 = this.o.P();
        com.smule.android.network.managers.PerformanceManager.a().a(this.o.F().accountId, null, PerformancesAPI.ACTIVESEED, true, n, n2, new PerformanceManager(){

            @Override
            public void handleResponse(PerformanceManager.PerformancesByPerformerResponse performancesByPerformerResponse) {
                if (ProfileOpenCallDataSource.this.o == null || !ProfileOpenCallDataSource.this.o.a(n3)) {
                    fetchDataCallback.a();
                    return;
                }
                if (!performancesByPerformerResponse.a()) {
                    fetchDataCallback.a();
                    ProfileOpenCallDataSource.this.o.b(2131297210);
                    return;
                }
                if (performancesByPerformerResponse.mTotalPerformances != null) {
                    ProfileOpenCallDataSource.this.q = performancesByPerformerResponse.mTotalPerformances;
                }
                if (performancesByPerformerResponse.mPerformances.size() > 0) {
                    ProfileOpenCallDataSource.this.o.a("", 1);
                }
                ArrayList<PerformanceV2> arrayList = new ArrayList<PerformanceV2>();
                if (n == 0 && ProfileOpenCallDataSource.this.m.size() > 0) {
                    arrayList.addAll(ProfileOpenCallDataSource.this.m);
                }
                Iterator<PerformanceV2> iterator = performancesByPerformerResponse.mPerformances.iterator();
                while (iterator.hasNext()) {
                    Object object = iterator.next();
                    if (!ProfileOpenCallDataSource.this.o.a((PerformanceV2)object, PerformancesAPI.ACTIVESEED)) continue;
                    object = new PerformanceListItemContainer((PerformanceV2)object);
                    if (ProfileOpenCallDataSource.this.m.contains(object)) {
                        ProfileOpenCallDataSource.this.m.remove(object);
                        arrayList.remove(object);
                    }
                    arrayList.add((PerformanceV2)object);
                }
                fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(performancesByPerformerResponse.mNext));
            }
        });
    }

    private void b(int n, final int n2, final MagicDataSource.FetchDataCallback<PerformanceListItemContainer, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        final int n3 = this.o.P();
        com.smule.android.network.managers.PerformanceManager.a().a(n, (Integer)n2, new PerformanceManager(){

            @Override
            public void handleResponse(PerformanceManager.PerformancesByPerformerResponse performancesByPerformerResponse) {
                if (ProfileOpenCallDataSource.this.o == null || !ProfileOpenCallDataSource.this.o.a(n3)) {
                    fetchDataCallback.a();
                    return;
                }
                if (!performancesByPerformerResponse.a()) {
                    fetchDataCallback.a();
                    ProfileOpenCallDataSource.this.o.b(2131297210);
                    return;
                }
                if (performancesByPerformerResponse.mTotalPerformances != null) {
                    ProfileOpenCallDataSource.this.r = performancesByPerformerResponse.mTotalPerformances;
                }
                ArrayList<PerformanceListItemContainer> arrayList = new ArrayList<PerformanceListItemContainer>();
                Iterator<PerformanceV2> iterator = performancesByPerformerResponse.mPerformances.iterator();
                while (iterator.hasNext()) {
                    PerformanceListItemContainer performanceListItemContainer = new PerformanceListItemContainer(iterator.next());
                    ProfileOpenCallDataSource.this.a(performanceListItemContainer);
                    performanceListItemContainer.d = true;
                    arrayList.add(performanceListItemContainer);
                }
                if (performancesByPerformerResponse.mNext == -1) {
                    ProfileOpenCallDataSource.this.b = true;
                    if (arrayList.isEmpty()) {
                        ProfileOpenCallDataSource.this.a(0, n2, fetchDataCallback);
                        return;
                    }
                    fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker());
                    return;
                }
                fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(performancesByPerformerResponse.mNext));
            }
        });
    }

    @Override
    public int a() {
        return 25;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker object, int n, MagicDataSource.FetchDataCallback<PerformanceListItemContainer, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        if (this.o.F() == null || this.o.F().accountId == 0) {
            String string2 = a;
            StringBuilder stringBuilder = new StringBuilder().append("account");
            object = this.o.F() == null ? "null" : Integer.valueOf(0);
            Log.d(string2, "account is null or accountId is 0", new ProfileFragment.DroidSing10042Exception(stringBuilder.append(object).append(" mLoadInvites:").append(this.b).toString()).fillInStackTrace());
            fetchDataCallback.a();
            this.b = false;
            return null;
        }
        if (!this.o.O()) {
            this.b = true;
            this.a(object.a(), n, fetchDataCallback);
            return null;
        }
        if (this.b) {
            this.a(object.a(), n, fetchDataCallback);
            return null;
        }
        this.b(object.a(), n, fetchDataCallback);
        return null;
    }

    @Override
    public void a(int n, PerformanceListItemContainer performanceListItemContainer) {
        super.a(n, performanceListItemContainer);
        if (performanceListItemContainer.d) {
            ++this.r;
            return;
        }
        ++this.q;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a(Parcel parcel) {
        super.a(parcel);
        int n = this.b ? 1 : 0;
        parcel.writeInt(n);
        parcel.writeInt(this.r);
        parcel.writeInt(this.q);
    }

    @Override
    public boolean a(Object object) {
        boolean bl;
        block3 : {
            block2 : {
                bl = super.a(object);
                if (!bl) break block2;
                if (!((PerformanceListItemContainer)object).d) break block3;
                --this.r;
            }
            return bl;
        }
        --this.q;
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void b(Parcel parcel) {
        boolean bl = true;
        super.b(parcel);
        if (parcel.readInt() != 1) {
            bl = false;
        }
        this.b = bl;
        this.r = parcel.readInt();
        this.q = parcel.readInt();
    }

    public int e() {
        return this.q;
    }

    @Override
    public void r() {
        super.r();
        this.b = false;
    }

    public int w() {
        return this.r;
    }

}

