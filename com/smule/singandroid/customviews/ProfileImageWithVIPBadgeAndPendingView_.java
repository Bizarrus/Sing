package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileImageWithVIPBadgeAndPendingView_ extends ProfileImageWithVIPBadgeAndPendingView implements HasViews, OnViewChangedListener {
    private boolean f21852u = false;
    private final OnViewChangedNotifier f21853v = new OnViewChangedNotifier();

    public ProfileImageWithVIPBadgeAndPendingView_(Context context) {
        super(context);
        m23407d();
    }

    public ProfileImageWithVIPBadgeAndPendingView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23407d();
    }

    public ProfileImageWithVIPBadgeAndPendingView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23407d();
    }

    public void onFinishInflate() {
        if (!this.f21852u) {
            this.f21852u = true;
            inflate(getContext(), C1947R.layout.profile_with_pending_view, this);
            this.f21853v.a(this);
        }
        super.onFinishInflate();
    }

    private void m23407d() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21853v);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23408a(HasViews hasViews) {
        this.a = (SNPImageView) hasViews.findViewById(C1947R.id.mInnerProfileImage);
        this.b = (ImageView) hasViews.findViewById(C1947R.id.mVIPBadge);
        this.c = (TextView) hasViews.findViewById(C1947R.id.mPerfCount);
        this.s = (RelativeLayout) hasViews.findViewById(C1947R.id.pending_layout);
        this.t = (ImageView) hasViews.findViewById(C1947R.id.mPendingOverlay);
    }
}
