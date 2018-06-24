/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.ListView
 */
package com.smule.singandroid.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.SingServerValues;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Future;

public class OpenCallListAdapter
extends BaseAdapter {
    private static final String k = OpenCallListAdapter.class.getName();
    int a = 1;
    int b = 1;
    int c = 10;
    boolean d = false;
    HasMorePagesListener e;
    SongbookEntry f;
    boolean g = false;
    String h;
    boolean i = false;
    boolean j = false;
    private ArrayList<PerformanceV2> l;
    private OpenCallListAdapterUIDelegate m;
    private PerformanceManager n;

    public OpenCallListAdapter(OpenCallListAdapterUIDelegate arrayList, ArrayList<PerformanceV2> arrayList2) {
        this.n = new PerformanceManager(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(PerformanceManager.PerformancesResponse iterator) {
                if (iterator.a()) {
                    int n;
                    if (OpenCallListAdapter.this.f.t()) {
                        iterator = iterator.mPerformances.iterator();
                        int n2 = 0;
                        do {
                            n = n2++;
                            if (iterator.hasNext()) {
                                PerformanceV2 performanceV2 = iterator.next();
                                if (!performanceV2.p()) continue;
                                OpenCallListAdapter.this.l.add(performanceV2);
                                continue;
                            }
                            break;
                        } while (true);
                    } else {
                        OpenCallListAdapter.this.l.addAll(iterator.mPerformances);
                        n = iterator.mPerformances.size();
                    }
                    OpenCallListAdapter.this.notifyDataSetChanged();
                    if (n <= 0) {
                        OpenCallListAdapter.this.d();
                    } else {
                        OpenCallListAdapter.this.b();
                    }
                } else {
                    OpenCallListAdapter.this.d();
                }
                OpenCallListAdapter.this.i = false;
            }
        };
        if (arrayList == null) {
            Log.e(k, "No UI delegate set on PerformanceListAdapter");
        }
        this.m = arrayList;
        arrayList = arrayList2;
        if (arrayList2 == null) {
            arrayList = new ArrayList();
        }
        this.l = arrayList;
        this.a(this.l.size() / this.a + 1);
        this.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(int n) {
        PerformancesAPI sortOrder = null;
        if (this.i) {
            return;
        }
        this.e();
        this.i = true;
        if (!this.f.t()) {
            com.smule.android.network.managers.PerformanceManager.a().a(this.h, null, (n - 1) * this.c, this.c, (Boolean)this.g, this.n);
            return;
        }
        if (this.j) {
            sortOrder = PerformancesAPI.HOT;
        }
        PerformancesAPI fillStatus = this.j ? PerformancesAPI.ACTIVESEED : PerformancesAPI.SEED;
        com.smule.android.network.managers.PerformanceManager.a().a(this.h, sortOrder, (n - 1) * this.c, this.c, fillStatus, this.g, this.n);
    }

    static /* synthetic */ String f() {
        return k;
    }

    public void a() {
        this.a = this.b;
    }

    public void a(int n) {
        this.b = n;
    }

    public void a(SongbookEntry songbookEntry) {
        this.f = songbookEntry;
        this.h = songbookEntry.c();
    }

    public void a(HasMorePagesListener hasMorePagesListener) {
        this.e = hasMorePagesListener;
    }

    public void a(boolean bl) {
        this.g = bl;
    }

    public void b() {
        ++this.a;
    }

    public void b(boolean bl) {
        this.j = bl;
    }

    public void c() {
        this.a();
        this.b(this.a);
    }

    public void d() {
        this.d = false;
        if (this.e != null) {
            this.e.a();
        }
    }

    public void e() {
        this.d = true;
        if (this.e != null) {
            this.e.b();
        }
    }

    public int getCount() {
        if (this.l != null) {
            return this.l.size();
        }
        return 0;
    }

    public Object getItem(int n) {
        if (n < this.l.size()) {
            return this.l.get(n);
        }
        return null;
    }

    public long getItemId(int n) {
        return n;
    }

    public int getItemViewType(int n) {
        PerformanceV2 performanceV2 = (PerformanceV2)this.getItem(n);
        boolean bl = new SingServerValues().ac();
        if (performanceV2 == null || !performanceV2.boost || !bl) {
            return ViewType.a.ordinal();
        }
        return ViewType.b.ordinal();
    }

    public View getView(int n, View view, ViewGroup viewGroup) {
        if (n == this.getCount() - 1 && this.d) {
            this.b(this.a);
        }
        PerformanceV2 performanceV2 = (PerformanceV2)this.getItem(n);
        return this.m.a(view, viewGroup, performanceV2, ViewType.values()[this.getItemViewType(n)], n);
    }

    public int getViewTypeCount() {
        return 2;
    }

    public static interface HasMorePagesListener {
        public void a();

        public void b();
    }

    public static interface OpenCallListAdapterUIDelegate {
        public View a(View var1, ViewGroup var2, PerformanceV2 var3, ViewType var4, int var5);
    }

    public static enum ViewType {
        a,
        b;
        

        private ViewType() {
        }
    }

}

