package com.smule.singandroid.adapters.songbook;

import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.AdapterInterface;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.DataRefreshListener;

public abstract class SongbookPaginatedAdapter extends SongbookSongsAdapter {
    protected abstract void mo6708c(boolean z);

    public SongbookPaginatedAdapter(AdapterInterface adapterInterface) {
        super(adapterInterface);
    }

    public boolean mo6707s() {
        return true;
    }

    public void mo6698j() {
        mo6708c(true);
    }

    public void mo6706c(int i) {
        if (!(this.i || this.l == null || this.l.size() <= 0)) {
            f();
        }
        mo6708c(false);
    }

    protected void m22198t() {
        DataRefreshListener z = m22193z();
        if (z != null) {
            z.mo6882a();
        }
    }

    protected void m22199u() {
        DataRefreshListener z = m22193z();
        if (z != null) {
            z.mo6884c();
        }
    }

    protected void m22200v() {
        DataRefreshListener z = m22193z();
        if (z != null) {
            z.mo6883b();
        }
    }
}
