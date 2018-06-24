/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.database.Observable
 */
package com.smule.android.magicui.lists.adapters;

import android.database.Observable;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MagicDataSourceObservable
extends Observable<MagicDataSource.DataSourceObserver>
implements MagicDataSource.DataSourceObserver {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(MagicDataSource magicDataSource) {
        ArrayList arrayList = this.mObservers;
        synchronized (arrayList) {
            Iterator iterator = this.mObservers.iterator();
            while (iterator.hasNext()) {
                ((MagicDataSource.DataSourceObserver)iterator.next()).a(magicDataSource);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(MagicDataSource magicDataSource, List<Object> list) {
        ArrayList arrayList = this.mObservers;
        synchronized (arrayList) {
            Iterator iterator = this.mObservers.iterator();
            while (iterator.hasNext()) {
                ((MagicDataSource.DataSourceObserver)iterator.next()).a(magicDataSource, list);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void b(MagicDataSource magicDataSource) {
        ArrayList arrayList = this.mObservers;
        synchronized (arrayList) {
            Iterator iterator = this.mObservers.iterator();
            while (iterator.hasNext()) {
                ((MagicDataSource.DataSourceObserver)iterator.next()).b(magicDataSource);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void c(MagicDataSource magicDataSource) {
        ArrayList arrayList = this.mObservers;
        synchronized (arrayList) {
            Iterator iterator = this.mObservers.iterator();
            while (iterator.hasNext()) {
                ((MagicDataSource.DataSourceObserver)iterator.next()).c(magicDataSource);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void d(MagicDataSource magicDataSource) {
        ArrayList arrayList = this.mObservers;
        synchronized (arrayList) {
            Iterator iterator = this.mObservers.iterator();
            while (iterator.hasNext()) {
                ((MagicDataSource.DataSourceObserver)iterator.next()).d(magicDataSource);
            }
            return;
        }
    }
}

