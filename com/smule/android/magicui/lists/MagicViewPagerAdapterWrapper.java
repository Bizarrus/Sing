/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcelable
 *  android.support.v4.view.PagerAdapter
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewParent
 */
package com.smule.android.magicui.lists;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.RecycledViewPool;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

class MagicViewPagerAdapterWrapper
extends PagerAdapter {
    MagicAdapter a;
    protected WeakHashMap<View, Integer> b = new WeakHashMap();
    protected RecycledViewPool c;
    private Parcelable d;

    public MagicViewPagerAdapterWrapper(MagicAdapter magicAdapter) {
        this.a = magicAdapter;
        this.a.a(new MagicDataSource.DataSourceObserverObject(){

            @Override
            public void c(MagicDataSource magicDataSource) {
                MagicViewPagerAdapterWrapper.this.notifyDataSetChanged();
            }
        });
    }

    private void a(View view, int n, int n2) {
        this.a.a(view, n, n2);
        view.setTag((Object)this.a(n));
    }

    private void b() {
        ArrayList<Map.Entry<View, Integer>> arrayList = new ArrayList<Map.Entry<View, Integer>>();
        for (Map.Entry<View, Integer> entry : this.b.entrySet()) {
            int n = entry.getValue();
            if (n < this.a.d()) {
                n = this.a.c(n);
                this.a(entry.getKey(), entry.getValue(), n);
                continue;
            }
            if (this.a.c()) continue;
            arrayList.add(entry);
        }
        for (Map.Entry entry : arrayList) {
            this.destroyItem((ViewGroup)((View)entry.getKey()).getParent(), (Integer)entry.getValue(), entry.getKey());
        }
        if (arrayList.size() != 0) {
            this.notifyDataSetChanged();
        }
    }

    public Parcelable a() {
        return this.d;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final View a(ViewGroup viewGroup, int n) {
        MagicDataSource.DataState dataState = this.a.a().i();
        switch (.a[dataState.ordinal()]) {
            case 1: {
                throw new RuntimeException("DataState was NONE");
            }
            case 2: {
                if (n >= this.a.d()) {
                    viewGroup = this.a.a(viewGroup);
                    viewGroup.setTag((Object)"loading");
                    return viewGroup;
                }
            }
            default: {
                int n2 = this.a.c(n);
                dataState = null;
                if (this.c != null) {
                    dataState = this.c.a(n2);
                }
                if ((viewGroup = this.a.a(viewGroup, (View)dataState, n)) != null) {
                    this.a((View)viewGroup, n, n2);
                }
                return viewGroup;
            }
            case 3: {
                return this.a.b(viewGroup);
            }
            case 4: {
                return this.a.c(viewGroup);
            }
            case 5: 
        }
        return this.a.d(viewGroup);
    }

    public String a(int n) {
        return "page" + n;
    }

    public void a(Parcelable parcelable) {
        this.d = parcelable;
    }

    public void a(RecycledViewPool recycledViewPool) {
        this.c = recycledViewPool;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void destroyItem(ViewGroup viewGroup, int n, Object object) {
        block3 : {
            block2 : {
                if (object == null) break block2;
                object = (View)object;
                viewGroup.removeView((View)object);
                this.b.remove(object);
                if (n >= this.a.d()) break block2;
                n = this.a.c(n);
                if (this.c != null) break block3;
            }
            return;
        }
        this.c.a((View)object, n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getCount() {
        int n;
        int n2 = this.a.d();
        if (this.a.c()) {
            n = 1;
            do {
                return n + n2;
                break;
            } while (true);
        }
        n = 0;
        return n + n2;
    }

    public Object instantiateItem(ViewGroup viewGroup, int n) {
        View view = this.a(viewGroup, n);
        if (view != null) {
            viewGroup.addView(view);
            this.b.put(view, n);
        }
        return view;
    }

    public boolean isViewFromObject(View view, Object object) {
        if (view == object) {
            return true;
        }
        return false;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.b();
    }

}

