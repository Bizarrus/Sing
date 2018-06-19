package com.smule.singandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class MultiLineDoneEditText extends EditText {
    public MultiLineDoneEditText(Context context) {
        super(context);
    }

    public MultiLineDoneEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MultiLineDoneEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        int i = editorInfo.imeOptions & 255;
        if ((i & 6) != 0) {
            editorInfo.imeOptions = i ^ editorInfo.imeOptions;
            editorInfo.imeOptions |= 6;
        }
        if ((editorInfo.imeOptions & 1073741824) != 0) {
            editorInfo.imeOptions &= -1073741825;
        }
        return onCreateInputConnection;
    }
}
