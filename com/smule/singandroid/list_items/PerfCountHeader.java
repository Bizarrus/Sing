package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PerfCountHeader extends FrameLayout {
    @ViewById
    TextView f23108a;
    @ViewById
    View f23109b;

    public static PerfCountHeader m24390a(Context context) {
        return PerfCountHeader_.m24392b(context);
    }

    public PerfCountHeader(Context context) {
        super(context);
    }

    public void setText(String str) {
        this.f23108a.setText(str);
    }

    public TextView getTextView() {
        return this.f23108a;
    }

    public void setBackgroundColor(int i) {
        this.f23109b.setBackgroundColor(i);
    }
}
