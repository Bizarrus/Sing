/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.SongbookEntryUtils
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.list_items.ListingListItem_;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.profile.ProfileUserInfo;
import com.smule.singandroid.utils.SongbookEntryUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ListingListItem
extends MediaPlayingListItem {
    public static Integer p;
    private static final String q;
    @ViewById
    protected LinearLayout a;
    @ViewById
    protected TextView b;
    @ViewById
    protected ViewGroup c;
    @ViewById
    protected View d;
    @ViewById
    protected TextView e;
    @ViewById
    protected TextView f;
    @ViewById
    protected TextView g;
    @ViewById
    protected ImageView h;
    @ViewById
    protected TextView i;
    @ViewById
    protected TextView j;
    @ViewById
    protected TextView k;
    @ViewById
    protected View l;
    @ViewById
    protected Button m;
    @ViewById
    protected View n;
    @ViewById
    protected TextView o;
    private SongbookEntry r;
    private LocalizedShortNumberFormatter s;
    private ImageUtils t = new Object(){
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
    private boolean u;

    static {
        q = ListingListItem.class.getName();
        p = 1234;
    }

    public ListingListItem(Context context) {
        super(context);
        this.s = new LocalizedShortNumberFormatter(context);
        this.setTag(2131755015, (Object)p);
    }

    public static ListingListItem a(Context context) {
        return ListingListItem_.b(context);
    }

    private void setDividerVisibility(boolean bl) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(ArrangementVersionLiteEntry object, boolean bl) {
        int n = 0;
        if (object == null) {
            Log.e(q, "setArrangementVersionLite - arrEntry is null!");
            return;
        } else {
            int n2;
            this.r = object;
            ArrangementVersionLite arrangementVersionLite = object.a;
            boolean bl2 = arrangementVersionLite.a();
            String string2 = object.e();
            if (bl2) {
                this.e.setText((CharSequence)string2);
                if (arrangementVersionLite.artist == null) {
                    this.f.setVisibility(8);
                } else {
                    this.f.setVisibility(0);
                    this.f.setText((CharSequence)arrangementVersionLite.artist);
                }
                string2 = this.i;
                n2 = object.k() ? 8 : 0;
                string2.setVisibility(n2);
            } else {
                this.e.setText((CharSequence)string2);
                this.f.setVisibility(0);
                this.f.setText((CharSequence)arrangementVersionLite.artist);
                if (object.k()) {
                    this.i.setVisibility(8);
                } else {
                    this.i.setVisibility(0);
                }
            }
            this.f.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            object = SongbookEntryUtils.a((SongbookEntry)object);
            if (object != null) {
                this.t.a((String)object, this.ae.a, 2130837897);
            } else {
                this.ae.a.setImageResource(2130837897);
            }
            this.a(arrangementVersionLite.key);
            this.h.setVisibility(4);
            if (arrangementVersionLite.rating != null && arrangementVersionLite.totalVotes >= 3) {
                object = "" + (int)(arrangementVersionLite.rating.floatValue() * 100.0f) + "% (" + this.s.a(arrangementVersionLite.totalVotes) + ")";
                this.k.setText((CharSequence)object);
                if (arrangementVersionLite.highlyRated) {
                    object = this.getResources().getDrawable(2130838032);
                    n2 = this.getContext().getResources().getColor(2131689589);
                } else {
                    object = this.getResources().getDrawable(2130838031);
                    n2 = this.getContext().getResources().getColor(2131689548);
                }
            } else {
                this.k.setText(2131297524);
                object = this.getResources().getDrawable(2130838031);
                n2 = this.getContext().getResources().getColor(2131689548);
            }
            this.k.setCompoundDrawablesWithIntrinsicBounds((Drawable)object, null, null, null);
            this.k.setTextColor(n2);
            this.k.setVisibility(0);
            if (arrangementVersionLite.smuleOwned) {
                this.j.setCompoundDrawablesWithIntrinsicBounds(2130837988, 0, 0, 0);
            } else if (arrangementVersionLite.accountIcon.c()) {
                this.j.setCompoundDrawablesWithIntrinsicBounds(2130838055, 0, 0, 0);
            } else {
                this.j.setCompoundDrawablesWithIntrinsicBounds(2130838054, 0, 0, 0);
            }
            object = this.j;
            n2 = arrangementVersionLite.accountIcon.c() ? 0 : this.getResources().getDimensionPixelOffset(2131427591);
            object.setCompoundDrawablePadding(n2);
            this.j.setText((CharSequence)arrangementVersionLite.accountIcon.handle);
            this.j.setVisibility(0);
            this.g.setVisibility(8);
            object = this.d;
            n2 = n;
            if (bl) {
                n2 = 8;
            }
            object.setVisibility(n2);
            if (!this.r.o()) return;
            {
                this.k.setVisibility(8);
                return;
            }
        }
    }

    public void a(SongbookEntry songbookEntry, boolean bl) {
        if (songbookEntry.t()) {
            this.a((ArrangementVersionLiteEntry)songbookEntry, bl);
        }
    }

    public void a(ProfileUserInfo.ColorSet colorSet) {
        Drawable drawable2 = this.getResources().getDrawable(2130838177);
        drawable2.setColorFilter(colorSet.e, PorterDuff.Mode.SRC_ATOP);
        this.m.setBackground(drawable2);
        this.m.setTextColor(-1);
    }

    public void a(String string2, int n, int n2) {
        this.o.setText((CharSequence)string2);
        this.o.setCompoundDrawablesWithIntrinsicBounds(0, 0, n2, 0);
        this.n.setBackgroundColor(n);
        this.l.setVisibility(0);
    }

    protected boolean a(MotionEvent motionEvent) {
        if (this.u && motionEvent.getActionMasked() == 0) {
            int n = this.getResources().getDimensionPixelSize(2131427526);
            if (motionEvent.getX() > (float)(this.getMeasuredWidth() - n)) {
                return true;
            }
        }
        return false;
    }

    public void c() {
        this.l.setVisibility(8);
    }

    public void d() {
        this.j.setVisibility(8);
        this.k.setVisibility(8);
    }

    public void e() {
        this.ae.a.setImageDrawable(null);
        this.t.a();
        this.setOnTouchListener(null);
        this.setOnClickListener(null);
        this.setOnLongClickListener(null);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a(motionEvent) || super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (this.a(motionEvent) || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public void setAlbumArtClickListener(View.OnClickListener onClickListener) {
        if (this.ae.a != null) {
            this.ae.a.setOnClickListener(onClickListener);
            return;
        }
        Log.e(q, "setAlbumArtClickListener - mAlbumArtImageView is null");
    }

    public void setFastScrollEnabled(boolean bl) {
        this.u = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setLastItemBottomPaddingVisibility(boolean bl) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.a.getLayoutParams();
        int n = bl ? this.getResources().getDimensionPixelOffset(2131427817) : 0;
        marginLayoutParams.bottomMargin = n;
        this.a.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
    }

    public void setListHeaderText(String string2) {
        this.b.setText((CharSequence)string2);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void setOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.c.setOnLongClickListener(onLongClickListener);
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.c.setOnTouchListener(onTouchListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setShowListHeader(boolean bl) {
        TextView textView = this.b;
        int n = bl ? 0 : 8;
        textView.setVisibility(n);
    }
}

