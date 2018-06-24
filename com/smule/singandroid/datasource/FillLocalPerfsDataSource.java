/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.datasource;

import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.FileUploaderService;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.datasource.BaseProfileDataSource;
import com.smule.singandroid.profile.ProfileFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public abstract class FillLocalPerfsDataSource
extends BaseProfileDataSource {
    protected List<PerformanceListItemContainer> m = new ArrayList<PerformanceListItemContainer>();
    protected int n = new SingServerValues().ab();
    protected final ProfileFragment o;
    protected final boolean p;

    public FillLocalPerfsDataSource(String string2, ProfileFragment profileFragment, boolean bl) {
        super(string2);
        this.o = profileFragment;
        this.p = bl;
        this.e();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void e() {
        if (this.o != null && this.o.a(this.o.P()) && this.o.M()) {
            for (PerformanceV2 performanceV2 : this.o.L().c()) {
                if (performanceV2 == null || this.p != performanceV2.seed) continue;
                PerformanceListItemContainer performanceListItemContainer = new PerformanceListItemContainer(performanceV2);
                this.m.add(performanceListItemContainer);
            }
        }
    }

    @Override
    public abstract Future<?> a(MagicDataSource.OffsetPaginationTracker var1, int var2, MagicDataSource.FetchDataCallback<PerformanceListItemContainer, MagicDataSource.OffsetPaginationTracker> var3);
}

