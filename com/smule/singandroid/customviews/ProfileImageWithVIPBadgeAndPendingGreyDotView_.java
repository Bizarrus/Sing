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

public final class ProfileImageWithVIPBadgeAndPendingGreyDotView_ extends ProfileImageWithVIPBadgeAndPendingGreyDotView implements HasViews, OnViewChangedListener {
    private boolean f21847t = false;
    private final OnViewChangedNotifier f21848u = new OnViewChangedNotifier();

    public ProfileImageWithVIPBadgeAndPendingGreyDotView_(Context context) {
        super(context);
        m23404e();
    }

    public ProfileImageWithVIPBadgeAndPendingGreyDotView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23404e();
    }

    public ProfileImageWithVIPBadgeAndPendingGreyDotView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23404e();
    }

    public void onFinishInflate() {
        if (!this.f21847t) {
            this.f21847t = true;
            inflate(getContext(), C1947R.layout.profile_with_pending_dot_view, this);
            this.f21848u.a(this);
        }
        super.onFinishInflate();
    }

    private void m23404e() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21848u);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23405a(HasViews hasViews) {
        this.a = (SNPImageView) hasViews.findViewById(C1947R.id.mInnerProfileImage);
        this.b = (ImageView) hasViews.findViewById(C1947R.id.mVIPBadge);
        this.c = (TextView) hasViews.findViewById(C1947R.id.mPerfCount);
        this.s = (SNPImageView) hasViews.findViewById(C1947R.id.pending_layout);
    }
}
