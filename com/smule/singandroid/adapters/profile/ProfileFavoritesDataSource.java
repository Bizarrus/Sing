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
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.datasource.BaseProfileDataSource;
import com.smule.singandroid.profile.ProfileFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class ProfileFavoritesDataSource
extends BaseProfileDataSource {
    private static final String b = ProfileFavoritesDataSource.class.getName();
    ProfileFragment a;

    public ProfileFavoritesDataSource(ProfileFragment profileFragment) {
        super(ProfileFavoritesDataSource.class.getSimpleName() + ":" + profileFragment.F().accountId);
        this.a = profileFragment;
    }

    @Override
    public int a() {
        return 25;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker object, int n, final MagicDataSource.FetchDataCallback<PerformanceListItemContainer, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        final int n2 = this.a.P();
        if (this.a == null) {
            return null;
        }
        if (this.a.F() != null && this.a.F().accountId != 0) {
            return com.smule.android.network.managers.PerformanceManager.a().a(this.a.F().accountId, object.a(), (Integer)n, new PerformanceManager(){

                @Override
                public void handleResponse(PerformanceManager.PerformancesByPerformerResponse performancesByPerformerResponse) {
                    if (ProfileFavoritesDataSource.this.a == null || !ProfileFavoritesDataSource.this.a.a(n2)) {
                        fetchDataCallback.a();
                        return;
                    }
                    if (!performancesByPerformerResponse.a()) {
                        fetchDataCallback.a();
                        ProfileFavoritesDataSource.this.a.b(2131297210);
                        return;
                    }
                    ArrayList<PerformanceListItemContainer> arrayList = new ArrayList<PerformanceListItemContainer>();
                    for (PerformanceV2 performanceV2 : performancesByPerformerResponse.mPerformances) {
                        arrayList.add(new PerformanceListItemContainer(performanceV2));
                        if (!ProfileFavoritesDataSource.this.a.O()) continue;
                        UserManager.a().g(performanceV2.performanceKey);
                    }
                    if (ProfileFavoritesDataSource.this.a.O()) {
                        UserManager.a().c(performancesByPerformerResponse.mPerformances.isEmpty());
                    }
                    if (performancesByPerformerResponse.mTotalPerformances != null) {
                        ProfileFavoritesDataSource.this.l = performancesByPerformerResponse.mTotalPerformances;
                    }
                    fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(performancesByPerformerResponse.mNext));
                }
            });
        }
        String string2 = b;
        StringBuilder stringBuilder = new StringBuilder().append("account is ");
        object = this.a.F() == null ? "null" : Integer.valueOf(0);
        Log.d(string2, "account is null or accountId is 0", new ProfileFragment.DroidSing10042Exception(stringBuilder.append(object).toString()).fillInStackTrace());
        fetchDataCallback.a();
        return null;
    }

    @Override
    protected void a(Parcel parcel) {
        super.a(parcel);
        parcel.writeInt(this.l);
    }

    @Override
    protected void b(Parcel parcel) {
        super.b(parcel);
        this.l = parcel.readInt();
    }

}

