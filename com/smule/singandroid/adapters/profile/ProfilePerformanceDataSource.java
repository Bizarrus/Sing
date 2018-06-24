package com.smule.singandroid.adapters.profile;

import android.os.Parcel;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.api.PerformancesAPI$FillStatus;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformancesByPerformerResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesByPerformerResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.datasource.FillLocalPerfsDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class ProfilePerformanceDataSource extends FillLocalPerfsDataSource {
    private boolean f20557a;
    private List<PerformanceListItemContainer> f20558b;

    public ProfilePerformanceDataSource(ProfileFragment profileFragment) {
        super(ProfilePerformanceDataSource.class.getSimpleName() + ":" + profileFragment.m21064A().accountId, profileFragment, false);
    }

    protected void mo6684a(Parcel parcel) {
        super.mo6684a(parcel);
        parcel.writeInt(this.c);
        parcel.writeByte(this.f20557a ? (byte) 1 : (byte) 0);
        parcel.writeList(this.f20558b);
    }

    protected void mo6685b(Parcel parcel) {
        boolean z = true;
        super.mo6685b(parcel);
        this.c = parcel.readInt();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.f20557a = z;
        this.f20558b = parcel.readArrayList(PerformanceListItemContainer.class.getClassLoader());
    }

    public int mo6242a() {
        return 25;
    }

    public Future<?> mo6243a(final int i, int i2, final FetchDataCallback<PerformanceListItemContainer> fetchDataCallback) {
        final int I = this.m.m21072I();
        return PerformanceManager.a().a(this.m.m21064A().accountId, null, PerformancesAPI$FillStatus.FILLED, Boolean.valueOf(true), Integer.valueOf(i), Integer.valueOf(i2), new PerformanceManager$PerformancesByPerformerResponseCallback(this) {
            final /* synthetic */ ProfilePerformanceDataSource f20556d;

            public void handleResponse(PerformancesByPerformerResponse performancesByPerformerResponse) {
                if (this.f20556d.m == null || !this.f20556d.m.m19843a(I)) {
                    fetchDataCallback.mo6256a();
                } else if (performancesByPerformerResponse.a()) {
                    if (performancesByPerformerResponse.mTotalPerformances != null) {
                        this.f20556d.c = performancesByPerformerResponse.mTotalPerformances.intValue();
                    }
                    List arrayList = new ArrayList();
                    if (i == 0 && this.f20556d.k.size() > 0) {
                        arrayList.addAll(this.f20556d.k);
                    }
                    boolean P = this.f20556d.m.m21079P();
                    int i;
                    if (this.f20556d.m17669s()) {
                        i = 0;
                    } else {
                        i = this.f20556d.m17661k();
                    }
                    this.f20556d.f20557a = false;
                    this.f20556d.f20558b = new ArrayList();
                    int i2 = 0;
                    while (i2 < performancesByPerformerResponse.mPerformances.size()) {
                        PerformanceV2 performanceV2 = (PerformanceV2) performancesByPerformerResponse.mPerformances.get(i2);
                        if (this.f20556d.m.m21105a(performanceV2, PerformancesAPI$FillStatus.FILLED)) {
                            PerformanceListItemContainer performanceListItemContainer;
                            if (!P && this.f20556d.l > 0 && arrayList.size() + r1 >= this.f20556d.l) {
                                this.f20556d.f20557a = true;
                                if (i2 < this.f20556d.l || i2 >= this.f20556d.l + 15) {
                                    break;
                                }
                                performanceListItemContainer = new PerformanceListItemContainer(performanceV2);
                                if (this.f20556d.f20558b.contains(performanceListItemContainer)) {
                                    this.f20556d.f20558b.remove(performanceListItemContainer);
                                }
                                this.f20556d.f20558b.add(performanceListItemContainer);
                            } else {
                                performanceListItemContainer = new PerformanceListItemContainer(performanceV2);
                                if (this.f20556d.k.contains(performanceListItemContainer)) {
                                    this.f20556d.k.remove(performanceListItemContainer);
                                    arrayList.remove(performanceListItemContainer);
                                }
                                arrayList.add(performanceListItemContainer);
                            }
                        }
                        i2++;
                    }
                    fetchDataCallback.mo6257a(arrayList, this.f20556d.f20557a ? -1 : performancesByPerformerResponse.mNext.intValue());
                    if (arrayList.isEmpty()) {
                        this.f20556d.m.ab();
                    } else {
                        this.f20556d.m.aa();
                    }
                } else {
                    fetchDataCallback.mo6256a();
                    this.f20556d.m.m19846b((int) C1947R.string.profile_update_error);
                }
            }
        });
    }

    public List<PerformanceListItemContainer> mo6264d() {
        return this.f20558b == null ? new ArrayList() : this.f20558b;
    }

    public void m22121v() {
        if (this.f20558b != null && !this.f20558b.isEmpty()) {
            this.f20558b.remove(0);
        }
    }
}
