/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.customviews.VideoUploadStatusView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class VideoUploadStatusView_
extends VideoUploadStatusView
implements HasViews,
OnViewChangedListener {
    private boolean f = false;
    private final OnViewChangedNotifier g = new OnViewChangedNotifier();

    public VideoUploadStatusView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.g);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = hasViews.findViewById(2131756778);
        this.b = (TextView)hasViews.findViewById(2131756780);
        this.c = (ImageView)hasViews.findViewById(2131756781);
        this.d = (ProgressBar)hasViews.findViewById(2131756779);
        if (this.c != null) {
            this.c.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    VideoUploadStatusView_.this.a();
                }
            });
        }
    }

    public void onFinishInflate() {
        if (!this.f) {
            this.f = true;
            VideoUploadStatusView_.inflate((Context)this.getContext(), (int)2130903460, (ViewGroup)this);
            this.g.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

