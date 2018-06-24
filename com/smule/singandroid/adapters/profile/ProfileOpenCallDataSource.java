package com.smule.singandroid.adapters.profile;

import android.os.Parcel;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.api.PerformancesAPI$FillStatus;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformancesByPerformerResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesByPerformerResponse;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.ProfileFragment.DroidSing10042Exception;
import com.smule.singandroid.datasource.FillLocalPerfsDataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class ProfileOpenCallDataSource extends FillLocalPerfsDataSource {
    public static final String f20549a = ProfileOpenCallDataSource.class.getName();
    protected boolean f20550b;
    private int f20551o;
    private int f20552p;

    public ProfileOpenCallDataSource(ProfileFragment profileFragment) {
        super(ProfileOpenCallDataSource.class.getSimpleName() + ":" + profileFragment.m21064A().accountId, profileFragment, true);
    }

    public int mo6242a() {
        return 25;
    }

    public void mo6689a(int i, PerformanceListItemContainer performanceListItemContainer) {
        super.mo6689a(i, performanceListItemContainer);
        if (performanceListItemContainer.f19116e) {
            this.f20552p++;
        } else {
            this.f20551o++;
        }
    }

    public boolean mo6688a(Object obj) {
        boolean a = super.mo6688a(obj);
        if (a) {
            if (((PerformanceListItemContainer) obj).f19116e) {
                this.f20552p--;
            } else {
                this.f20551o--;
            }
        }
        return a;
    }

    protected void mo6684a(Parcel parcel) {
        super.mo6684a(parcel);
        parcel.writeInt(this.f20550b ? 1 : 0);
        parcel.writeInt(this.f20552p);
        parcel.writeInt(this.f20551o);
    }

    protected void mo6685b(Parcel parcel) {
        boolean z = true;
        super.mo6685b(parcel);
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.f20550b = z;
        this.f20552p = parcel.readInt();
        this.f20551o = parcel.readInt();
    }

    private void m22068b(int i, int i2, FetchDataCallback<PerformanceListItemContainer> fetchDataCallback) {
        final int I = this.m.m21072I();
        final FetchDataCallback<PerformanceListItemContainer> fetchDataCallback2 = fetchDataCallback;
        final int i3 = i;
        final int i4 = i2;
        PerformanceManager.a().a(this.m.m21064A().accountId, null, PerformancesAPI$FillStatus.ACTIVESEED, Boolean.valueOf(true), Integer.valueOf(i), Integer.valueOf(i2), new PerformanceManager$PerformancesByPerformerResponseCallback(this) {
            final /* synthetic */ ProfileOpenCallDataSource f20540e;

            public void handleResponse(PerformancesByPerformerResponse performancesByPerformerResponse) {
                if (this.f20540e.m == null || !this.f20540e.m.m19843a(I)) {
                    fetchDataCallback2.mo6256a();
                } else if (performancesByPerformerResponse.a()) {
                    boolean z;
                    if (performancesByPerformerResponse.mStorageLimit != null) {
                        this.f20540e.l = performancesByPerformerResponse.mStorageLimit.intValue();
                    }
                    if (performancesByPerformerResponse.mTotalPerformances != null) {
                        this.f20540e.f20551o = performancesByPerformerResponse.mTotalPerformances.intValue();
                    }
                    if (performancesByPerformerResponse.mPerformances.size() > 0) {
                        this.f20540e.m.m21101a("", 1);
                    }
                    List arrayList = new ArrayList();
                    if (i3 == 0 && this.f20540e.k.size() > 0) {
                        arrayList.addAll(this.f20540e.k);
                    }
                    boolean b = SubscriptionManager.a().b();
                    int k = this.f20540e.m17669s() ? 0 : this.f20540e.m17661k();
                    Iterator it = performancesByPerformerResponse.mPerformances.iterator();
                    while (it.hasNext()) {
                        PerformanceV2 performanceV2 = (PerformanceV2) it.next();
                        if (!b && this.f20540e.l > 0 && arrayList.size() + k >= this.f20540e.l) {
                            z = true;
                            break;
                        } else if (this.f20540e.m.m21105a(performanceV2, PerformancesAPI$FillStatus.ACTIVESEED)) {
                            PerformanceListItemContainer performanceListItemContainer = new PerformanceListItemContainer(performanceV2);
                            if (this.f20540e.k.contains(performanceListItemContainer)) {
                                this.f20540e.k.remove(performanceListItemContainer);
                                arrayList.remove(performanceListItemContainer);
                            }
                            arrayList.add(performanceListItemContainer);
                        }
                    }
                    z = false;
                    if (z) {
                        performancesByPerformerResponse.mNext = Integer.valueOf(-1);
                    }
                    if (performancesByPerformerResponse.mNext.intValue() == -1 && this.f20540e.m.m21071H()) {
                        this.f20540e.f20550b = true;
                        if (arrayList.isEmpty()) {
                            this.f20540e.m22071c(0, i4, fetchDataCallback2);
                            return;
                        } else {
                            fetchDataCallback2.mo6257a(arrayList, 0);
                            return;
                        }
                    }
                    fetchDataCallback2.mo6257a(arrayList, performancesByPerformerResponse.mNext.intValue());
                } else {
                    fetchDataCallback2.mo6256a();
                    this.f20540e.m.m19846b((int) C1947R.string.profile_update_error);
                }
            }
        });
    }

    private void m22071c(final int i, int i2, final FetchDataCallback<PerformanceListItemContainer> fetchDataCallback) {
        final int I = this.m.m21072I();
        PerformanceManager.a().b(this.m.m21064A().accountId, Integer.valueOf(i), Integer.valueOf(i2), new PerformanceManager$PerformancesByPerformerResponseCallback(this) {
            final /* synthetic */ ProfileOpenCallDataSource f20544d;

            public void handleResponse(PerformancesByPerformerResponse performancesByPerformerResponse) {
                boolean z = false;
                if (this.f20544d.m == null || !this.f20544d.m.m19843a(I)) {
                    fetchDataCallback.mo6256a();
                } else if (performancesByPerformerResponse.a()) {
                    int i;
                    if (performancesByPerformerResponse.mTotalPerformances != null) {
                        this.f20544d.f20552p = performancesByPerformerResponse.mTotalPerformances.intValue();
                    }
                    List arrayList = new ArrayList();
                    boolean b = SubscriptionManager.a().b();
                    int k = this.f20544d.m17669s() ? 0 : this.f20544d.m17661k();
                    Iterator it = performancesByPerformerResponse.mPerformances.iterator();
                    while (it.hasNext()) {
                        PerformanceV2 performanceV2 = (PerformanceV2) it.next();
                        if (!b && this.f20544d.l > 0 && arrayList.size() + k >= this.f20544d.l) {
                            z = true;
                            break;
                        }
                        Object performanceListItemContainer = new PerformanceListItemContainer(performanceV2);
                        this.f20544d.mo6688a(performanceListItemContainer);
                        performanceListItemContainer.f19116e = true;
                        if (i == 0 && arrayList.isEmpty()) {
                            performanceListItemContainer.f19115d = true;
                        }
                        arrayList.add(performanceListItemContainer);
                    }
                    FetchDataCallback fetchDataCallback = fetchDataCallback;
                    if (z) {
                        i = -1;
                    } else {
                        i = performancesByPerformerResponse.mNext.intValue();
                    }
                    fetchDataCallback.mo6257a(arrayList, i);
                } else {
                    fetchDataCallback.mo6256a();
                    this.f20544d.m.m19846b((int) C1947R.string.profile_update_error);
                }
            }
        });
    }

    public void mo6690q() {
        super.mo6690q();
        this.f20550b = false;
    }

    public Future<?> mo6243a(int i, int i2, FetchDataCallback<PerformanceListItemContainer> fetchDataCallback) {
        if (this.m.m21064A() == null || this.m.m21064A().accountId == 0) {
            Log.d(f20549a, "account is null or accountId is 0", new DroidSing10042Exception("account" + (this.m.m21064A() == null ? "null" : Integer.valueOf(0)) + " mLoadBookmarks:" + this.f20550b).fillInStackTrace());
            fetchDataCallback.mo6256a();
            this.f20550b = false;
        } else if (this.f20550b) {
            m22071c(i, i2, fetchDataCallback);
        } else {
            m22068b(i, i2, fetchDataCallback);
        }
        return null;
    }

    public int mo6264d() {
        return this.f20551o;
    }

    public int m22095v() {
        return this.f20552p;
    }
}
