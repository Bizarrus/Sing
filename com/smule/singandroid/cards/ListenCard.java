/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.widget.FrameLayout
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.cards;

import android.content.Context;
import android.widget.FrameLayout;
import com.smule.singandroid.cards.ListenCard_;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ListenCard
extends FrameLayout
implements MediaPlayingViewInterface {
    private static final String b = ListenCard.class.getName();
    @ViewById
    protected SquarePerformanceItem a;

    public ListenCard(Context context) {
        super(context);
    }

    public static ListenCard a(Context context) {
        return ListenCard_.b(context);
    }

    public String getMediaKey() {
        return this.a.getMediaKey();
    }

    public SquarePerformanceItem getSquarePerformanceItem() {
        return this.a;
    }

    @Override
    public void r_() {
        this.a.r_();
    }

    @Override
    public void s_() {
    }
}

