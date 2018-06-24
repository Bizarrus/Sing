/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.SquareAlbumGrid;
import com.smule.singandroid.list_items.TopicItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class TopicItem_
extends TopicItem
implements HasViews,
OnViewChangedListener {
    private boolean g = false;
    private final OnViewChangedNotifier h = new OnViewChangedNotifier();

    public TopicItem_(Context context) {
        super(context);
        this.c();
    }

    public static TopicItem b(Context object) {
        object = new TopicItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.h);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (SquareAlbumGrid)hasViews.findViewById(2131755273);
        this.b = (ImageView)hasViews.findViewById(2131755274);
        this.c = (TextView)hasViews.findViewById(2131755277);
        this.d = hasViews.findViewById(2131755275);
        this.e = hasViews.findViewById(2131755276);
        this.b();
    }

    public void onFinishInflate() {
        if (!this.g) {
            this.g = true;
            TopicItem_.inflate((Context)this.getContext(), (int)2130903078, (ViewGroup)this);
            this.h.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

