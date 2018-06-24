/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.BlockerUserItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class BlockerUserItem_
extends BlockerUserItem
implements HasViews,
OnViewChangedListener {
    private boolean h = false;
    private final OnViewChangedNotifier i = new OnViewChangedNotifier();

    public BlockerUserItem_(Context context) {
        super(context);
        this.c();
    }

    public static BlockerUserItem a(Context object) {
        object = new BlockerUserItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.i);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131755298);
        this.b = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755297);
        this.c = (Button)hasViews.findViewById(2131755299);
        this.d = hasViews.findViewById(2131755300);
        if (this.a != null) {
            this.a.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    BlockerUserItem_.this.b();
                }
            });
        }
        this.a();
    }

    public void onFinishInflate() {
        if (!this.h) {
            this.h = true;
            BlockerUserItem_.inflate((Context)this.getContext(), (int)2130903086, (ViewGroup)this);
            this.i.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

