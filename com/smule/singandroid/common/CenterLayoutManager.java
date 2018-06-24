/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Rect
 *  android.support.annotation.NonNull
 *  android.support.v7.widget.LinearLayoutManager
 *  android.support.v7.widget.LinearSmoothScroller
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$SmoothScroller
 *  android.support.v7.widget.RecyclerView$State
 *  android.support.v7.widget.RecyclerView$ViewHolder
 *  android.util.AttributeSet
 *  android.view.View
 */
package com.smule.singandroid.common;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class CenterLayoutManager
extends LinearLayoutManager {
    private ScrollType a = ScrollType.b;

    public CenterLayoutManager(Context context) {
        super(context);
    }

    public CenterLayoutManager(@NonNull Context context, int n, boolean bl, @NonNull ScrollType scrollType) {
        super(context, n, bl);
        this.a = scrollType;
    }

    public CenterLayoutManager(Context context, AttributeSet attributeSet, int n, int n2) {
        super(context, attributeSet, n, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(RecyclerView recyclerView, int n) {
        block3 : {
            block2 : {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(n);
                if (viewHolder == null) break block2;
                Rect rect = new Rect();
                recyclerView.getHitRect(rect);
                if (!viewHolder.itemView.getLocalVisibleRect(rect) || rect.width() < viewHolder.itemView.getWidth()) break block3;
            }
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void smoothScrollToPosition(RecyclerView object, RecyclerView.State state, int n) {
        if (this.a == ScrollType.c || n == -1 || this.a != ScrollType.a && this.a((RecyclerView)object, n)) {
            return;
        }
        object = new CenterSmoothScroller(object.getContext());
        object.setTargetPosition(n);
        this.startSmoothScroll((RecyclerView.SmoothScroller)object);
    }

    private static class CenterSmoothScroller
    extends LinearSmoothScroller {
        CenterSmoothScroller(Context context) {
            super(context);
        }

        public int calculateDtToFit(int n, int n2, int n3, int n4, int n5) {
            return (n4 - n3) / 2 + n3 - ((n2 - n) / 2 + n);
        }
    }

    public static enum ScrollType {
        a,
        b,
        c;
        

        private ScrollType() {
        }
    }

}

