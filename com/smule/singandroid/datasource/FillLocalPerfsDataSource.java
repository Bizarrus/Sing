package com.smule.singandroid.datasource;

import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.SingServerValues;
import java.util.ArrayList;
import java.util.List;

public abstract class FillLocalPerfsDataSource extends BaseProfileDataSource {
    protected List<PerformanceListItemContainer> f20545k = new ArrayList();
    protected int f20546l = SingServerValues.m21677O();
    protected final ProfileFragment f20547m;
    protected final boolean f20548n;

    public FillLocalPerfsDataSource(String str, ProfileFragment profileFragment, boolean z) {
        super(str);
        this.f20547m = profileFragment;
        this.f20548n = z;
        mo6264d();
    }

    private void mo6264d() {
        if (this.f20547m != null && this.f20547m.m19843a(this.f20547m.m21072I()) && this.f20547m.m21069F()) {
            for (PerformanceV2 performanceV2 : this.f20547m.m21068E().m18937a()) {
                if (performanceV2 != null && this.f20548n == performanceV2.seed) {
                    this.f20545k.add(new PerformanceListItemContainer(performanceV2));
                }
            }
        }
    }
}
