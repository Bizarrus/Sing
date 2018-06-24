/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.StringCacheManager;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.utils.PerformanceV2Util;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SquarePerformanceItem
extends MediaPlayingListItem
implements PerformanceItemInterface {
    private static final String f = SquarePerformanceItem.class.getName();
    @ViewById
    protected TextView a;
    @ViewById
    protected TextView b;
    @ViewById
    protected TextView c;
    @ViewById
    protected TextView d;
    ImageUtils e = new Object(){
        public String a;

        public void a() {
            this.a = null;
        }

        public void a(ImageView imageView) {
            com.nostra13.universalimageloader.core.ImageLoader.a().a(imageView);
        }

        public boolean a(String string2, ImageView imageView, int n) {
            return this.a(string2, imageView, n, false);
        }

        public boolean a(String string2, ImageView imageView, int n, boolean bl) {
            if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl);
                this.a = string2;
                return true;
            }
            return false;
        }

        public boolean a(String string2, ImageView imageView, int n, boolean bl, int n2) {
            if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl, -1, n2, null, true);
                this.a = string2;
                return true;
            }
            return false;
        }
    };
    private PerformanceV2 g;
    private String h;
    private PerformanceItemInterface.PerformanceItemListener i;
    private SquarePerformanceItemListener j;
    private LocalizedShortNumberFormatter k;

    public SquarePerformanceItem(Context context) {
        super(context);
    }

    public SquarePerformanceItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquarePerformanceItem(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c() {
        TextView textView = this.a;
        String string2 = this.g.title != null ? this.g.title : "";
        textView.setText((CharSequence)string2);
        this.b.setText((CharSequence)PerformanceV2Util.a((Resources)this.getResources(), (PerformanceV2)this.g, (boolean)true));
        this.d();
        if (this.g.coverUrl != null && !this.g.coverUrl.isEmpty()) {
            this.e.a(this.g.coverUrl, this.ae.a, 2130837896);
            return;
        }
        this.ae.a.setImageResource(2130837896);
    }

    private void d() {
        this.a(this.getPlaySongKey());
        this.ae.i.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (SquarePerformanceItem.this.j != null) {
                    SquarePerformanceItem.this.j.a();
                }
            }
        });
        if (!this.g.hasBeenLoved) {
            this.g.hasBeenLoved = StringCacheManager.a().b(this.g.performanceKey);
        }
        if (this.g.hasBeenLoved) {
            this.ae.j.setImageDrawable(this.getResources().getDrawable(2130837945));
            return;
        }
        this.ae.j.setImageDrawable(this.getResources().getDrawable(2130837946));
        this.ae.j.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (SquarePerformanceItem.this.j != null) {
                    SquarePerformanceItem.this.j.b();
                }
            }
        });
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.k == null) {
            this.k = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.k;
    }

    public String getPlaySongKey() {
        if (this.g != null) {
            return this.g.performanceKey;
        }
        return null;
    }

    public PlayableItemView getPlayableItemView() {
        return this.ae;
    }

    public String getRecId() {
        return this.h;
    }

    @Override
    public void setListener(PerformanceItemInterface.PerformanceItemListener performanceItemListener) {
        this.i = performanceItemListener;
    }

    @Override
    public void setPerformance(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            Log.e(f, "setPerformance passed a null performance!");
            return;
        }
        this.g = performanceV2;
        this.c();
        this.c.setText((CharSequence)this.getLocalizedFormatter().a(this.g.totalListens, this.getResources().getInteger(2131623962)));
        this.d.setText((CharSequence)this.getLocalizedFormatter().a(this.g.totalLoves, this.getResources().getInteger(2131623962)));
        this.setOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                switch (.a[SquarePerformanceItem.this.af.ordinal()]) {
                    case 1: 
                    case 2: {
                        if (SquarePerformanceItem.this.i != null) {
                            SquarePerformanceItem.this.i.a((MediaPlayingViewInterface)SquarePerformanceItem.this, SquarePerformanceItem.this.g);
                            return;
                        }
                    }
                    default: {
                        return;
                    }
                    case 3: 
                }
                SquarePerformanceItem.this.u();
            }
        });
        this.ae.a(this.a(this.g), 2130837814);
    }

    public void setRecId(String string2) {
        this.h = string2;
    }

    public void setSquarePerformanceItemListener(SquarePerformanceItemListener squarePerformanceItemListener) {
        this.j = squarePerformanceItemListener;
    }

    public static interface SquarePerformanceItemListener {
        public void a();

        public void b();
    }

}

