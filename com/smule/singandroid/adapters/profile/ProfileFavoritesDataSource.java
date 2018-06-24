package com.smule.singandroid.adapters.profile;

import android.os.Parcel;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformancesByPerformerResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesByPerformerResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.ProfileFragment.DroidSing10042Exception;
import com.smule.singandroid.datasource.BaseProfileDataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class ProfileFavoritesDataSource extends BaseProfileDataSource {
    private static final String f20534b = ProfileFavoritesDataSource.class.getName();
    ProfileFragment f20535a;

    public ProfileFavoritesDataSource(ProfileFragment profileFragment) {
        super(ProfileFavoritesDataSource.class.getSimpleName() + ":" + profileFragment.m21064A().accountId);
        this.f20535a = profileFragment;
    }

    protected void mo6684a(Parcel parcel) {
        super.mo6684a(parcel);
        parcel.writeInt(this.c);
    }

    protected void mo6685b(Parcel parcel) {
        super.mo6685b(parcel);
        this.c = parcel.readInt();
    }

    public int mo6242a() {
        return 25;
    }

    public Future<?> mo6243a(int i, int i2, final FetchDataCallback<PerformanceListItemContainer> fetchDataCallback) {
        final int I = this.f20535a.m21072I();
        if (this.f20535a == null) {
            return null;
        }
        if (this.f20535a.m21064A() != null && this.f20535a.m21064A().accountId != 0) {
            return PerformanceManager.a().a(this.f20535a.m21064A().accountId, Integer.valueOf(i), Integer.valueOf(i2), new PerformanceManager$PerformancesByPerformerResponseCallback(this) {
                final /* synthetic */ ProfileFavoritesDataSource f20532c;

                public void handleResponse(PerformancesByPerformerResponse performancesByPerformerResponse) {
                    if (this.f20532c.f20535a == null || !this.f20532c.f20535a.m19843a(I)) {
                        fetchDataCallback.mo6256a();
                    } else if (performancesByPerformerResponse.a()) {
                        List arrayList = new ArrayList();
                        Iterator it = performancesByPerformerResponse.mPerformances.iterator();
                        while (it.hasNext()) {
                            PerformanceV2 performanceV2 = (PerformanceV2) it.next();
                            arrayList.add(new PerformanceListItemContainer(performanceV2));
                            if (this.f20532c.f20535a.m21071H()) {
                                UserManager.a().g(performanceV2.performanceKey);
                            }
                        }
                        if (this.f20532c.f20535a.m21071H()) {
                            UserManager.a().c(performancesByPerformerResponse.mPerformances.isEmpty());
                        }
                        if (performancesByPerformerResponse.mTotalPerformances != null) {
                            this.f20532c.c = performancesByPerformerResponse.mTotalPerformances.intValue();
                        }
                        fetchDataCallback.mo6257a(arrayList, performancesByPerformerResponse.mNext.intValue());
                    } else {
                        fetchDataCallback.mo6256a();
                        this.f20532c.f20535a.m19846b((int) C1947R.string.profile_update_error);
                    }
                }
            });
        }
        Log.d(f20534b, "account is null or accountId is 0", new DroidSing10042Exception("account is " + (this.f20535a.m21064A() == null ? "null" : Integer.valueOf(0))).fillInStackTrace());
        fetchDataCallback.mo6256a();
        return null;
    }
}
