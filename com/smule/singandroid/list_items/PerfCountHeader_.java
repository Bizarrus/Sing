package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PerfCountHeader_ extends PerfCountHeader implements HasViews, OnViewChangedListener {
    private boolean f23110c = false;
    private final OnViewChangedNotifier f23111d = new OnViewChangedNotifier();

    public PerfCountHeader_(Context context) {
        super(context);
        m24391a();
    }

    public static PerfCountHeader m24392b(Context context) {
        PerfCountHeader perfCountHeader_ = new PerfCountHeader_(context);
        perfCountHeader_.onFinishInflate();
        return perfCountHeader_;
    }

    public void onFinishInflate() {
        if (!this.f23110c) {
            this.f23110c = true;
            inflate(getContext(), C1947R.layout.perf_count_header, this);
            this.f23111d.a(this);
        }
        super.onFinishInflate();
    }

    private void m24391a() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23111d);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24393a(HasViews hasViews) {
        this.a = (TextView) hasViews.findViewById(C1947R.id.mHeaderTextView);
        this.b = hasViews.findViewById(C1947R.id.mHeaderBackground);
    }
}
