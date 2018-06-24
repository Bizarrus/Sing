/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.inputmethod.EditorInfo
 *  android.view.inputmethod.InputConnection
 *  android.widget.EditText
 */
package com.smule.singandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class MultiLineDoneEditText
extends EditText {
    public MultiLineDoneEditText(Context context) {
        super(context);
    }

    public MultiLineDoneEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MultiLineDoneEditText(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnection = super.onCreateInputConnection(editorInfo);
        int n = editorInfo.imeOptions & 255;
        if ((n & 6) != 0) {
            editorInfo.imeOptions = n ^ editorInfo.imeOptions;
            editorInfo.imeOptions |= 6;
        }
        if ((editorInfo.imeOptions & 1073741824) != 0) {
            editorInfo.imeOptions &= -1073741825;
        }
        return inputConnection;
    }
}

