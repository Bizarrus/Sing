/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.adapters.songbook;

import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.adapters.songbook.SongbookSongsAdapter;
import java.util.List;

public abstract class SongbookPaginatedAdapter
extends SongbookSongsAdapter {
    public SongbookPaginatedAdapter(SongbookAmazingAdapter.AdapterInterface adapterInterface) {
        super(adapterInterface);
    }

    @Override
    public void b(String string2) {
        if (!this.j && this.m != null && this.m.size() > 0) {
            this.h();
        }
        this.c(false);
    }

    @Override
    public void c(int n) {
        if (!this.j && this.m != null && this.m.size() > 0) {
            this.g();
        }
        this.c(false);
    }

    protected abstract void c(boolean var1);

    @Override
    public void m() {
        this.c(true);
    }

    @Override
    public boolean v() {
        return true;
    }

    protected void w() {
        SongbookAmazingAdapter.DataRefreshListener dataRefreshListener = this.E();
        if (dataRefreshListener != null) {
            dataRefreshListener.a();
        }
    }

    protected void x() {
        SongbookAmazingAdapter.DataRefreshListener dataRefreshListener = this.E();
        if (dataRefreshListener != null) {
            dataRefreshListener.c();
        }
    }

    protected void y() {
        SongbookAmazingAdapter.DataRefreshListener dataRefreshListener = this.E();
        if (dataRefreshListener != null) {
            dataRefreshListener.b();
        }
    }
}

