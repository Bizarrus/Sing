/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  android.util.Pair
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.FrameLayout
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.customviews.QuickSelectControlItemViewModel;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class QuickSelectControlItemView
extends FrameLayout
implements View.OnClickListener {
    @ViewById
    protected IconFontView a;
    @ViewById
    protected IconFontView b;
    private QuickSelectControlItemViewModel c;

    public QuickSelectControlItemView(@NonNull Context context) {
        super(context);
    }

    public void onClick(View view) {
        view = new Pair((Object)((String)this.getTag()), (Object)this.c.c());
        NotificationCenter.a().a("NOTIFICATION_QUICK_SELECT", (Object)view);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSelected(boolean bl) {
        IconFontView iconFontView = this.a;
        int n = bl ? 0 : 8;
        iconFontView.setVisibility(n);
    }

    public void setupView(QuickSelectControlItemViewModel quickSelectControlItemViewModel) {
        this.c = quickSelectControlItemViewModel;
        this.b.setText(this.c.a());
        this.b.setTextColor(this.c.b());
        this.setSelected(this.c.d());
        this.setOnClickListener((View.OnClickListener)this);
    }
}

