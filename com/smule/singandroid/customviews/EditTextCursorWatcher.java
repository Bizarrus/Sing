package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextCursorWatcher extends EditText {
    private OnSelectionChangedListener f21543a = null;

    public interface OnSelectionChangedListener {
        void mo6812a(int i, int i2);
    }

    public EditTextCursorWatcher(Context context) {
        super(context);
    }

    public EditTextCursorWatcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EditTextCursorWatcher(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        this.f21543a = onSelectionChangedListener;
    }

    protected void onSelectionChanged(int i, int i2) {
        if (this.f21543a != null) {
            this.f21543a.mo6812a(i, i2);
        }
    }
}
