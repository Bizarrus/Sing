package com.smule.android.magicui.lists.adapters;

import android.database.Observable;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserver;
import java.util.Iterator;
import java.util.List;

public class MagicDataSourceObservable extends Observable<DataSourceObserver> implements DataSourceObserver {
    public void mo6254c(MagicDataSource magicDataSource) {
        synchronized (this.mObservers) {
            Iterator it = this.mObservers.iterator();
            while (it.hasNext()) {
                ((DataSourceObserver) it.next()).mo6254c(magicDataSource);
            }
        }
    }

    public void mo6255d(MagicDataSource magicDataSource) {
        synchronized (this.mObservers) {
            Iterator it = this.mObservers.iterator();
            while (it.hasNext()) {
                ((DataSourceObserver) it.next()).mo6255d(magicDataSource);
            }
        }
    }

    public void mo6252a(MagicDataSource magicDataSource, List<Object> list) {
        synchronized (this.mObservers) {
            Iterator it = this.mObservers.iterator();
            while (it.hasNext()) {
                ((DataSourceObserver) it.next()).mo6252a(magicDataSource, list);
            }
        }
    }

    public void mo6251a(MagicDataSource magicDataSource) {
        synchronized (this.mObservers) {
            Iterator it = this.mObservers.iterator();
            while (it.hasNext()) {
                ((DataSourceObserver) it.next()).mo6251a(magicDataSource);
            }
        }
    }

    public void mo6253b(MagicDataSource magicDataSource) {
        synchronized (this.mObservers) {
            Iterator it = this.mObservers.iterator();
            while (it.hasNext()) {
                ((DataSourceObserver) it.next()).mo6253b(magicDataSource);
            }
        }
    }
}
