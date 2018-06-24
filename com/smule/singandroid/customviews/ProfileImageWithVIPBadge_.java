package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileImageWithVIPBadge_ extends ProfileImageWithVIPBadge implements HasViews, OnViewChangedListener {
    private boolean f21854s = false;
    private final OnViewChangedNotifier f21855t = new OnViewChangedNotifier();

    public ProfileImageWithVIPBadge_(Context context) {
        super(context);
        m23409d();
    }

    public ProfileImageWithVIPBadge_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23409d();
    }

    public ProfileImageWithVIPBadge_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23409d();
    }

    public void onFinishInflate() {
        if (!this.f21854s) {
            this.f21854s = true;
            inflate(getContext(), C1947R.layout.profile_with_badge, this);
            this.f21855t.a(this);
        }
        super.onFinishInflate();
    }

    private void m23409d() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21855t);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23410a(HasViews hasViews) {
        this.a = (SNPImageView) hasViews.findViewById(C1947R.id.mInnerProfileImage);
        this.b = (ImageView) hasViews.findViewById(C1947R.id.mVIPBadge);
        this.c = (TextView) hasViews.findViewById(C1947R.id.mPerfCount);
    }
}
