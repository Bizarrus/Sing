/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 */
package com.smule.singandroid.customviews;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.lang.ref.WeakReference;

@Deprecated
public abstract class PaginatedAdapter
extends BaseAdapter {
    public static final String a = PaginatedAdapter.class.getSimpleName();
    protected int b = 1;
    protected int c = 1;
    protected boolean d = false;
    WeakReference<> e;

    private  e() {
        if (this.e != null) {
            return this.e.get();
        }
        return null;
    }

    public abstract View a(int var1, View var2, ViewGroup var3);

    public void a() {
        this.b = this.c;
    }

    public void a(int n) {
        this.c = n;
    }

    public void b() {
        ++this.b;
    }

    protected abstract void b(int var1);

    public void c() {
        this.d = false;
         hasMorePagesListener = this.e();
        if (hasMorePagesListener != null) {
            hasMorePagesListener.a();
        }
    }

    public void d() {
        this.d = true;
         hasMorePagesListener = this.e();
        if (hasMorePagesListener != null) {
            hasMorePagesListener.b();
        }
    }

    public final View getView(int n, View view, ViewGroup viewGroup) {
        view = this.a(n, view, viewGroup);
        if (n == this.getCount() - 1 && this.d) {
            this.b(this.b + 1);
        }
        return view;
    }

}

