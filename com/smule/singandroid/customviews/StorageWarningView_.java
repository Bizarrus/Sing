package com.smule.singandroid.customviews;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class StorageWarningView_ extends StorageWarningView implements HasViews, OnViewChangedListener {
    private boolean f22013h = false;
    private final OnViewChangedNotifier f22014i = new OnViewChangedNotifier();

    public StorageWarningView_(Context context, boolean z, OnClickListener onClickListener) {
        super(context, z, onClickListener);
        m23517f();
    }

    public void onFinishInflate() {
        if (!this.f22013h) {
            this.f22013h = true;
            inflate(getContext(), C1947R.layout.storage_warning_view, this);
            this.f22014i.a(this);
        }
        super.onFinishInflate();
    }

    private void m23517f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22014i);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public static StorageWarningView m23516a(Context context, boolean z, OnClickListener onClickListener) {
        StorageWarningView storageWarningView_ = new StorageWarningView_(context, z, onClickListener);
        storageWarningView_.onFinishInflate();
        return storageWarningView_;
    }

    public void m23518a(HasViews hasViews) {
        this.a = hasViews.findViewById(C1947R.id.storage_limit_top_divider);
        this.b = (LinearLayout) hasViews.findViewById(C1947R.id.storage_limit_warning_layout);
        this.c = (TextView) hasViews.findViewById(C1947R.id.storage_limit_textview);
        this.d = (Button) hasViews.findViewById(C1947R.id.storage_limit_cta);
        this.e = (LinearLayout) hasViews.findViewById(C1947R.id.storage_limit_limited_rows);
        this.f = (FrameLayout) hasViews.findViewById(C1947R.id.storage_limit_gradient);
        this.g = (FrameLayout) hasViews.findViewById(C1947R.id.storage_limit_bottom_padding);
        m23512b();
    }
}
