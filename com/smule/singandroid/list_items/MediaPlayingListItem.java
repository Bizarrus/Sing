/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.AbsListView
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.utils.MiscUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class MediaPlayingListItem
extends LinearLayout
implements MediaPlayingViewInterface {
    private static final String a = MediaPlayingListItem.class.getName();
    @ViewById
    protected PlayableItemView ae;
    protected VisualState af = VisualState.a;
    private String b;

    public MediaPlayingListItem(Context context) {
        super(context);
    }

    public MediaPlayingListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MediaPlayingListItem(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(AbsListView absListView) {
        Log.b(a, "refreshAlbumArtPlayingFunctionality loadingKey: " + MediaPlayerServiceController.a().h() + " loadedKey: " + MediaPlayerServiceController.a().i());
        int n = 0;
        while (n < absListView.getChildCount()) {
            View view = absListView.getChildAt(n);
            if (view instanceof MediaPlayingViewInterface) {
                ((MediaPlayingViewInterface)view).r_();
            }
            ++n;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void b(AbsListView absListView) {
        int n = 0;
        while (n < absListView.getChildCount()) {
            View view = absListView.getChildAt(n);
            if (view instanceof MediaPlayingViewInterface) {
                ((MediaPlayingViewInterface)view).s_();
            }
            ++n;
        }
    }

    protected void a(String string2) {
        this.b = string2;
        this.af = VisualState.a;
        this.r_();
    }

    public boolean a(PerformanceV2 performanceV2) {
        return MiscUtils.a((PerformanceV2)performanceV2);
    }

    public String getMediaKey() {
        return this.b;
    }

    protected void m() {
        if (this.af != VisualState.d) {
            this.ae.c();
            this.af = VisualState.d;
        }
    }

    protected void n() {
        if (this.af != VisualState.c) {
            this.ae.d();
            this.af = VisualState.c;
        }
    }

    protected void o() {
        if (this.af != VisualState.b) {
            Log.b(a, "setIdleState: " + this.b);
            this.ae.b();
            this.af = VisualState.b;
        }
    }

    @AfterViews
    protected void p() {
        this.ae.c.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MediaPlayingListItem.this.u();
            }
        });
        this.ae.b.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MediaPlayingListItem.this.u();
            }
        });
    }

    public boolean q() {
        boolean bl = false;
        Object object = MediaPlayerServiceController.a();
        boolean bl2 = bl;
        if (object.j()) {
            object = object.i();
            bl2 = bl;
            if (object != null) {
                bl2 = bl;
                if (object.equals(this.b)) {
                    bl2 = true;
                }
            }
        }
        return bl2;
    }

    public boolean r() {
        boolean bl = false;
        Object object = MediaPlayerServiceController.a();
        boolean bl2 = bl;
        if (object.k()) {
            object = object.i();
            bl2 = bl;
            if (object != null) {
                bl2 = bl;
                if (object.equals(this.b)) {
                    bl2 = true;
                }
            }
        }
        return bl2;
    }

    @Override
    public void r_() {
        if (this.t() || this.s()) {
            this.n();
            return;
        }
        if (this.q()) {
            this.m();
            return;
        }
        this.o();
    }

    public boolean s() {
        boolean bl = false;
        Object object = MediaPlayerServiceController.a();
        boolean bl2 = bl;
        if (object.l()) {
            object = object.i();
            bl2 = bl;
            if (object != null) {
                bl2 = bl;
                if (object.equals(this.b)) {
                    bl2 = true;
                }
            }
        }
        return bl2;
    }

    @Override
    public void s_() {
    }

    public void setAlbumArtClickedState(boolean bl) {
        if (bl) {
            this.n();
            return;
        }
        this.o();
    }

    public boolean t() {
        String string2 = MediaPlayerServiceController.a().h();
        if (string2 != null && string2.equals(this.b)) {
            return true;
        }
        return false;
    }

    public void u() {
        Log.b(a, "configureAlbumArtPlayingFunctionality - toggleAudio with key: " + this.b);
        MediaPlayerServiceController.a().d();
    }

    protected static enum VisualState {
        a,
        b,
        c,
        d;
        

        private VisualState() {
        }
    }

}

