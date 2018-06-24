package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.smule.singandroid.C1947R;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatMultiplePortraitFlipView_ extends ChatMultiplePortraitFlipView implements HasViews, OnViewChangedListener {
    private boolean f21505j = false;
    private final OnViewChangedNotifier f21506k = new OnViewChangedNotifier();

    public ChatMultiplePortraitFlipView_(Context context) {
        super(context);
        m23149b();
    }

    public ChatMultiplePortraitFlipView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23149b();
    }

    public ChatMultiplePortraitFlipView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23149b();
    }

    public ChatMultiplePortraitFlipView_(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        m23149b();
    }

    public void onFinishInflate() {
        if (!this.f21505j) {
            this.f21505j = true;
            inflate(getContext(), C1947R.layout.chat_multiple_portrait_flip_layout, this);
            this.f21506k.a(this);
        }
        super.onFinishInflate();
    }

    private void m23149b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21506k);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23150a(HasViews hasViews) {
        this.b = (ViewGroup) hasViews.findViewById(C1947R.id.multiple_protraits_root);
        this.c = (ViewGroup) hasViews.findViewById(C1947R.id.front_layout);
        this.d = (ViewGroup) hasViews.findViewById(C1947R.id.back_layout);
        this.e = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.peer_profile_image_selected);
        this.f = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.peer_profile_image);
        this.g = (ViewGroup) hasViews.findViewById(C1947R.id.group_profile_images);
        this.h = (ViewGroup) hasViews.findViewById(C1947R.id.group_profile_row_2);
        List arrayList = new ArrayList();
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.group_profile_image_1);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView2 = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.group_profile_image_2);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView3 = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.group_profile_image_3);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView4 = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.group_profile_image_4);
        if (profileImageWithVIPBadgeAndPendingGreyDotView != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView2 != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView2);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView3 != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView3);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView4 != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView4);
        }
        this.i = arrayList;
    }
}
