/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.view.MotionEvent
 *  android.view.View
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.network.models.Topic;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.customviews.SquareAlbumGrid;
import com.smule.singandroid.list_items.TopicItem_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class TopicItem
extends FrameLayout
implements SquareAlbumGrid.AlbumImageListener {
    public static boolean f = false;
    @ViewById
    SquareAlbumGrid a;
    @ViewById
    ImageView b;
    @ViewById
    TextView c;
    @ViewById
    View d;
    @ViewById
    View e;
    private Topic g = new Topic();

    public TopicItem(Context context) {
        super(context);
    }

    public static TopicItem a(Context context) {
        return TopicItem_.b(context);
    }

    @Override
    public void a() {
        this.b.setVisibility(4);
        this.a.setVisibility(0);
    }

    public void a(Topic topic, boolean bl) {
        if (!this.g.equals(topic)) {
            this.g = topic;
            this.a.setTopic(topic);
            this.c.setText((CharSequence)this.g.displayName);
            this.a(bl);
            this.a.invalidateViews();
        }
        this.b(bl);
    }

    public void a(boolean bl) {
        if (bl) {
            this.d.setBackgroundColor(this.getResources().getColor(2131689535));
            this.e.setVisibility(0);
            ImageUtils.b((View)this.c, null);
            return;
        }
        this.d.setBackgroundColor(this.getResources().getColor(2131689524));
        this.e.setVisibility(4);
        ImageUtils.b((View)this.c, this.getResources().getDrawable(2130837662));
    }

    @AfterViews
    public void b() {
        this.a.setAlbumImageListener(this);
    }

    public void b(boolean bl) {
        float f = 1.0f;
        if (bl) {
            this.setAlpha(1.0f);
            return;
        }
        if (TopicItem.f) {
            f = 0.5f;
        }
        this.setAlpha(f);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}

