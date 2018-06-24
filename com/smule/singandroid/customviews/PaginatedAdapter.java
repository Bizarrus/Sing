package com.smule.singandroid.customviews;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.lang.ref.WeakReference;

@Deprecated
public abstract class PaginatedAdapter extends BaseAdapter {
    public static final String f7122c = PaginatedAdapter.class.getSimpleName();
    protected int f7123d = 1;
    protected int f7124e = 1;
    protected boolean f7125f = false;
    WeakReference<HasMorePagesListener> f7126g;

    public abstract View m8841a(int i, View view, ViewGroup viewGroup);

    protected abstract void m8842a(int i);

    public void m8843d(int i) {
        this.f7124e = i;
    }

    public void m8844f() {
        this.f7123d = this.f7124e;
    }

    public void m8845g() {
        this.f7123d++;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View a = m8841a(i, view, viewGroup);
        if (i == getCount() - 1 && this.f7125f) {
            m8842a(this.f7123d + 1);
        }
        return a;
    }

    public void m8846h() {
        this.f7125f = false;
        HasMorePagesListener a = m8840a();
        if (a != null) {
            a.a();
        }
    }

    public void m8847i() {
        this.f7125f = true;
        HasMorePagesListener a = m8840a();
        if (a != null) {
            a.b();
        }
    }

    private HasMorePagesListener m8840a() {
        if (this.f7126g != null) {
            return (HasMorePagesListener) this.f7126g.get();
        }
        return null;
    }
}
