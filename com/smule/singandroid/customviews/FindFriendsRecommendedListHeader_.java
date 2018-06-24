/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
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
import com.smule.singandroid.customviews.FindFriendsRecommendedListHeader;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsRecommendedListHeader_
extends FindFriendsRecommendedListHeader
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public FindFriendsRecommendedListHeader_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public static FindFriendsRecommendedListHeader a(Context object, AttributeSet attributeSet) {
        object = new FindFriendsRecommendedListHeader_((Context)object, attributeSet);
        object.onFinishInflate();
        return object;
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.b = hasViews.findViewById(2131755814);
        if ((hasViews = hasViews.findViewById(2131755828)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FindFriendsRecommendedListHeader_.this.a();
                }
            });
        }
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            FindFriendsRecommendedListHeader_.inflate((Context)this.getContext(), (int)2130903245, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

