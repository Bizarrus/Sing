/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.util.SparseArray
 *  android.util.SparseIntArray
 *  android.view.View
 */
package com.smule.android.magicui.lists.adapters;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import java.util.ArrayList;

public class RecycledViewPool {
    private SparseArray<ArrayList<View>> a = new SparseArray();
    private SparseIntArray b = new SparseIntArray();
    private int c = 0;

    private ArrayList<View> b(int n) {
        ArrayList arrayList;
        ArrayList arrayList2 = arrayList = (ArrayList)this.a.get(n);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.a.put(n, arrayList);
            arrayList2 = arrayList;
            if (this.b.indexOfKey(n) < 0) {
                this.b.put(n, 5);
                arrayList2 = arrayList;
            }
        }
        return arrayList2;
    }

    public View a(int n) {
        ArrayList arrayList = (ArrayList)this.a.get(n);
        if (arrayList != null && !arrayList.isEmpty()) {
            n = arrayList.size() - 1;
            View view = (View)arrayList.get(n);
            arrayList.remove(n);
            return view;
        }
        return null;
    }

    public void a(View view, int n) {
        ArrayList<View> arrayList = this.b(n);
        if (this.b.get(n) <= arrayList.size()) {
            return;
        }
        if (view instanceof RecyclableView) {
            ((RecyclableView)view).a();
        }
        arrayList.add(view);
    }

    public static interface RecyclableView {
        public void a();
    }

}

