/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.utils.PerformanceV2Util;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PlayableItemDetailsView
extends MediaPlayingListItem
implements MediaPlayingViewInterface {
    private ImageUtils a = new Object(){
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
    private LocalizedShortNumberFormatter b;
    @ViewById
    protected View q;
    @ViewById
    protected TextView r;
    @ViewById
    protected TextView s;
    @ViewById
    protected TextView t;
    @ViewById
    protected TextView u;
    protected PerformanceV2 v;

    public PlayableItemDetailsView(Context context) {
        super(context);
    }

    public PlayableItemDetailsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PlayableItemDetailsView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.b == null) {
            this.b = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(PerformanceV2 performanceV2, boolean bl) {
        this.v = performanceV2;
        this.t.setText((CharSequence)this.v.title);
        this.u.setText((CharSequence)PerformanceV2Util.a((Resources)this.getResources(), (PerformanceV2)this.v, (boolean)bl));
        this.r.setText((CharSequence)this.getLocalizedFormatter().a(this.v.totalListens, this.getResources().getInteger(2131623962)));
        this.s.setText((CharSequence)this.getLocalizedFormatter().a(this.v.totalLoves, this.getResources().getInteger(2131623962)));
        if (this.v.coverUrl != null && !this.v.coverUrl.isEmpty()) {
            this.a.a(this.v.coverUrl, this.ae.a, 2130837860);
        } else {
            this.ae.a.setImageResource(2130837860);
        }
        this.a(this.v.performanceKey);
        this.ae.a(this.a(this.v), 2130838138);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl) {
        View view = this.q;
        int n = bl ? 0 : 8;
        view.setVisibility(n);
    }

    public void setOnAlbumArtClickListener(View.OnClickListener onClickListener) {
        this.ae.setOnClickListener(onClickListener);
    }
}

