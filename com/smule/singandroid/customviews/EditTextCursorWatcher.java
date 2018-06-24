/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.EditText
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextCursorWatcher
extends EditText {
    private OnSelectionChangedListener a = null;

    public EditTextCursorWatcher(Context context) {
        super(context);
    }

    public EditTextCursorWatcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EditTextCursorWatcher(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onSelectionChanged(int n, int n2) {
        if (this.a != null) {
            this.a.a(n, n2);
        }
    }

    public void setOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        this.a = onSelectionChangedListener;
    }

    public static interface OnSelectionChangedListener {
        public void a(int var1, int var2);
    }

}

