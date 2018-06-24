/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.datasource;

import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.singandroid.PerformanceListItemContainer;

public abstract class BaseProfileDataSource
extends MagicDataSource<PerformanceListItemContainer, MagicDataSource.OffsetPaginationTracker> {
    protected int l;

    public BaseProfileDataSource(String string2) {
        super(string2, new MagicDataSource.OffsetPaginationTracker());
    }

    @Override
    public void a(int n, PerformanceListItemContainer performanceListItemContainer) {
        super.a(n, performanceListItemContainer);
        ++this.l;
    }

    @Override
    public boolean a(Object object) {
        boolean bl = super.a(object);
        if (bl) {
            --this.l;
        }
        return bl;
    }

    public int x() {
        return this.l;
    }
}

