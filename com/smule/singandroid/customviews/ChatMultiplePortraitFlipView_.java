/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingGreyDotView;
import java.util.ArrayList;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatMultiplePortraitFlipView_
extends ChatMultiplePortraitFlipView
implements HasViews,
OnViewChangedListener {
    private boolean j = false;
    private final OnViewChangedNotifier k = new OnViewChangedNotifier();

    public ChatMultiplePortraitFlipView_(Context context) {
        super(context);
        this.b();
    }

    public ChatMultiplePortraitFlipView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public ChatMultiplePortraitFlipView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b();
    }

    public ChatMultiplePortraitFlipView_(Context context, AttributeSet attributeSet, int n, int n2) {
        super(context, attributeSet, n, n2);
        this.b();
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.k);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews object) {
        this.b = (ViewGroup)object.findViewById(2131755434);
        this.c = (ViewGroup)object.findViewById(2131755435);
        this.d = (ViewGroup)object.findViewById(2131755443);
        this.e = (ProfileImageWithVIPBadge)object.findViewById(2131755444);
        this.f = (ProfileImageWithVIPBadge)object.findViewById(2131755436);
        this.g = (ViewGroup)object.findViewById(2131755437);
        this.h = (ViewGroup)object.findViewById(2131755440);
        ArrayList<Object> arrayList = new ArrayList<Object>();
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755438);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView2 = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755439);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView3 = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755441);
        object = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755442);
        if (profileImageWithVIPBadgeAndPendingGreyDotView != null) {
            arrayList.add((Object)profileImageWithVIPBadgeAndPendingGreyDotView);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView2 != null) {
            arrayList.add((Object)profileImageWithVIPBadgeAndPendingGreyDotView2);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView3 != null) {
            arrayList.add((Object)profileImageWithVIPBadgeAndPendingGreyDotView3);
        }
        if (object != null) {
            arrayList.add(object);
        }
        this.i = arrayList;
    }

    public void onFinishInflate() {
        if (!this.j) {
            this.j = true;
            ChatMultiplePortraitFlipView_.inflate((Context)this.getContext(), (int)2130903117, (ViewGroup)this);
            this.k.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

