/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.util.AttributeSet
 *  android.util.Pair
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.utils.MiscUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class CustomToolBarBase
extends LinearLayout {
    @ViewById
    protected View g;
    @ViewById
    protected ImageView h;
    @ViewById
    protected TextView i;
    @ViewById
    protected TextView j;
    @ViewById
    protected TextView k;
    @ViewById
    protected ImageView l;
    @ViewById
    protected TextView m;
    protected TitleType n = TitleType.a;

    public CustomToolBarBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomToolBarBase(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public void a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        this.a(songbookEntry, performanceV2, false);
    }

    public void a(SongbookEntry songbookEntry, PerformanceV2 performanceV2, boolean bl) {
        if (songbookEntry == null && performanceV2 == null) {
            throw new IllegalArgumentException("Entry and/or performance required");
        }
        songbookEntry = MiscUtils.a((SongbookEntry)songbookEntry, (PerformanceV2)performanceV2, (boolean)bl);
        this.a((CharSequence)songbookEntry.first, (CharSequence)songbookEntry.second);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(TitleType titleType) {
        int n = 4;
        int n2 = 0;
        int n3 = titleType == TitleType.a ? 1 : 0;
        titleType = this.i;
        int n4 = n3 != 0 ? 0 : 4;
        titleType.setVisibility(n4);
        titleType = this.j;
        n4 = n3 != 0 ? 0 : 4;
        titleType.setVisibility(n4);
        titleType = this.k;
        n4 = n3 != 0 ? n : 0;
        titleType.setVisibility(n4);
        titleType = this.h;
        n3 = n3 != 0 ? n2 : 8;
        titleType.setVisibility(n3);
    }

    public void a(CharSequence charSequence, CharSequence charSequence2) {
        if (this.n == TitleType.a) {
            this.i.setText(charSequence);
            this.setSubTitle(charSequence2);
            return;
        }
        this.k.setText(charSequence);
    }

    public ImageView getLeftButton() {
        return this.h;
    }

    public ImageView getPreSearchLeftButton() {
        return this.l;
    }

    public TextView getPreSearchTitleView() {
        return this.m;
    }

    public CharSequence getSubTitle() {
        return this.j.getText();
    }

    public CharSequence getTitle() {
        return this.i.getText();
    }

    public TextView getTitleView() {
        return this.i;
    }

    protected void setSubTitle(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            this.j.setVisibility(8);
            return;
        }
        this.j.setVisibility(0);
        this.j.setTextColor(this.getResources().getColor(2131689547));
        this.j.setText(charSequence);
    }

    public void setTitle(CharSequence charSequence) {
        this.a(charSequence, null);
    }

    public void setTitleCompoundDrawable(int n) {
        this.i.setCompoundDrawablesWithIntrinsicBounds(0, 0, n, 0);
    }

    public void setTitleType(TitleType titleType) {
        this.n = titleType;
        this.a(titleType);
    }

    public static enum TitleType {
        a,
        b;
        

        private TitleType() {
        }
    }

}

