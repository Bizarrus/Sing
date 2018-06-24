/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class UserNameListItem
extends LinearLayout {
    @ViewById
    protected TextView a;
    protected String b;

    public UserNameListItem(Context context) {
        super(context);
    }

    public UserNameListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String getUserName() {
        return this.b;
    }

    public void setUserName(String string2) {
        this.a.setText((CharSequence)string2);
        this.b = string2;
    }
}

