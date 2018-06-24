/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.SpannableString
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformancePost;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.list_items.CommentItem_;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.utils.MiscUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class CommentItem
extends RelativeLayout {
    private static final String g = CommentItem.class.getSimpleName();
    @ViewById
    protected ProfileImageWithVIPBadge a;
    @ViewById
    protected TextView b;
    @ViewById
    protected TextView c;
    @ViewById
    protected TextView d;
    @ViewById
    protected View e;
    @ViewById
    protected View f;
    private PerformancePost h;
    private CommentItemListener i;

    public CommentItem(Context context) {
        super(context);
    }

    public CommentItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static CommentItem a(Context context) {
        return CommentItem_.b(context);
    }

    private void setupHashTagSpannable(String string2) {
        string2 = new SpannableString((CharSequence)string2);
        Hashtag.a((MediaPlayingActivity)this.getContext(), (SpannableString)string2);
        this.c.setMovementMethod((MovementMethod)new LinkMovementMethod());
        this.c.setText((CharSequence)string2);
        this.c.setHighlightColor(0);
    }

    @UiThread
    protected void a() {
        if (this.i != null) {
            this.i.c(this, this.h);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    public void a(BaseFragment baseFragment, PerformancePost performancePost, final boolean bl, boolean bl2, final boolean bl3) {
        int n = 0;
        if (this.h == performancePost) {
            return;
        }
        this.h = performancePost;
        this.a.a(this.h.accountIcon, new View.OnClickListener(){

            public void onClick(View view) {
                CommentItem.this.i.d(CommentItem.this, CommentItem.this.h);
            }
        });
        this.b.setText((CharSequence)this.h.accountIcon.handle);
        baseFragment = this.b;
        int n2 = this.h.accountIcon.c() ? 2130838055 : 0;
        baseFragment.setCompoundDrawablesWithIntrinsicBounds(n2, 0, 0, 0);
        this.setupHashTagSpannable(performancePost.message);
        this.d.setText((CharSequence)MiscUtils.a((long)(this.h.time / 1000), (boolean)true, (boolean)false, (boolean)true));
        this.b.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                CommentItem.this.a();
            }
        });
        this.f.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                CommentItem.this.a();
            }
        });
        this.setOnLongClickListener(new View.OnLongClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onLongClick(View object) {
                object = CommentItem.this;
                boolean bl2 = bl || bl3;
                object.a(bl2);
                return true;
            }
        });
        this.f.setOnLongClickListener(new View.OnLongClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onLongClick(View object) {
                object = CommentItem.this;
                boolean bl2 = bl || bl3;
                object.a(bl2);
                return true;
            }
        });
        baseFragment = this.e;
        n2 = bl2 ? n : 8;
        baseFragment.setVisibility(n2);
    }

    @UiThread
    protected void a(boolean bl) {
        block3 : {
            block2 : {
                if (this.i == null) break block2;
                if (!bl) break block3;
                this.i.b(this, this.h);
            }
            return;
        }
        this.i.a(this, this.h);
    }

    public void setListener(CommentItemListener commentItemListener) {
        this.i = commentItemListener;
    }

    public static interface CommentItemListener {
        public void a(CommentItem var1, PerformancePost var2);

        public void b(CommentItem var1, PerformancePost var2);

        public void c(CommentItem var1, PerformancePost var2);

        public void d(CommentItem var1, PerformancePost var2);
    }

}

