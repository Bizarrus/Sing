/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.FrameLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.smule.singandroid.customviews.SingTip_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SingTip
extends FrameLayout {
    @ViewById
    protected TextView a;
    protected String b = null;
    protected View.OnClickListener c;

    public SingTip(Context context) {
        super(context);
    }

    public static SingTip a(Context object, String string2) {
        object = SingTip_.a((Context)object);
        object.setTipText(string2);
        return object;
    }

    @AfterViews
    protected void a() {
        this.setTipText(this.b);
    }

    @Click
    protected void a(View view) {
        if (this.c != null) {
            this.c.onClick(view);
        }
    }

    public void setListener(View.OnClickListener onClickListener) {
        this.c = onClickListener;
    }

    public void setTipText(String string2) {
        if (string2 != null) {
            this.a.setText((CharSequence)string2);
            this.b = string2;
        }
    }
}

