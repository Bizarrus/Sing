package com.smule.singandroid.datasource;

import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.singandroid.PerformanceListItemContainer;

public abstract class BaseProfileDataSource extends MagicDataSource<PerformanceListItemContainer> {
    protected int f20533c;

    public BaseProfileDataSource(String str) {
        super(str);
    }

    public void mo6689a(int i, PerformanceListItemContainer performanceListItemContainer) {
        super.mo6687a(i, (Object) performanceListItemContainer);
        this.f20533c++;
    }

    public boolean mo6688a(Object obj) {
        boolean a = super.mo6688a(obj);
        if (a) {
            this.f20533c--;
        }
        return a;
    }

    public int m22056w() {
        return this.f20533c;
    }
}
