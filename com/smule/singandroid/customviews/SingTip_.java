package com.smule.singandroid.customviews;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SingTip_ extends SingTip implements HasViews, OnViewChangedListener {
    private boolean f21962d = false;
    private final OnViewChangedNotifier f21963e = new OnViewChangedNotifier();

    class C44411 implements OnClickListener {
        final /* synthetic */ SingTip_ f21961a;

        C44411(SingTip_ singTip_) {
            this.f21961a = singTip_;
        }

        public void onClick(View view) {
            this.f21961a.m23483a(view);
        }
    }

    public SingTip_(Context context) {
        super(context);
        m23485b();
    }

    public static SingTip m23484a(Context context) {
        SingTip singTip_ = new SingTip_(context);
        singTip_.onFinishInflate();
        return singTip_;
    }

    public void onFinishInflate() {
        if (!this.f21962d) {
            this.f21962d = true;
            inflate(getContext(), C1947R.layout.sing_tip, this);
            this.f21963e.a(this);
        }
        super.onFinishInflate();
    }

    private void m23485b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21963e);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23486a(HasViews hasViews) {
        this.a = (TextView) hasViews.findViewById(C1947R.id.sing_tip_body);
        View findViewById = hasViews.findViewById(C1947R.id.root_view);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C44411(this));
        }
        m23482a();
    }
}
