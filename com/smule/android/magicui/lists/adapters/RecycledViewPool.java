package com.smule.android.magicui.lists.adapters;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import java.util.ArrayList;

public class RecycledViewPool {
    private SparseArray<ArrayList<View>> f16443a = new SparseArray();
    private SparseIntArray f16444b = new SparseIntArray();
    private int f16445c = 0;

    public interface RecyclableView {
        void m18074a();
    }

    public View m18076a(int i) {
        ArrayList arrayList = (ArrayList) this.f16443a.get(i);
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        int size = arrayList.size() - 1;
        View view = (View) arrayList.get(size);
        arrayList.remove(size);
        return view;
    }

    public void m18077a(View view, int i) {
        ArrayList b = m18075b(i);
        if (this.f16444b.get(i) > b.size()) {
            if (view instanceof RecyclableView) {
                ((RecyclableView) view).m18074a();
            }
            b.add(view);
        }
    }

    private ArrayList<View> m18075b(int i) {
        ArrayList<View> arrayList = (ArrayList) this.f16443a.get(i);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.f16443a.put(i, arrayList);
            if (this.f16444b.indexOfKey(i) < 0) {
                this.f16444b.put(i, 5);
            }
        }
        return arrayList;
    }
}
