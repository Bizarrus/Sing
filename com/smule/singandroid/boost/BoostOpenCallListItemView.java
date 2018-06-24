/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.boost;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.boost.BoostOpenCallListItemView_;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.OpenCallListItem;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class BoostOpenCallListItemView
extends OpenCallListItem {
    @ViewById
    protected ImageView a;

    public BoostOpenCallListItemView(Context context) {
        super(context);
        this.c = context;
    }

    public static BoostOpenCallListItemView a(Context context) {
        BoostOpenCallListItemView boostOpenCallListItemView = BoostOpenCallListItemView_.b(context);
        boostOpenCallListItemView.c = context;
        return boostOpenCallListItemView;
    }

    @Override
    public void a() {
    }

    @Override
    public void a(boolean bl) {
    }

    @Override
    protected void b() {
        this.ae.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                BoostOpenCallListItemView.this.e();
            }
        });
        this.k.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                BoostOpenCallListItemView.this.f();
            }
        });
        this.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                BoostOpenCallListItemView.this.g();
            }
        });
    }

    @Override
    protected void c() {
        String string2 = this.getAlbumArtUrl();
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.d.a(string2, this.a, 2130837860);
            return;
        }
        this.ae.a.setImageResource(2130837860);
    }

    @Override
    protected void setExpireTime(boolean bl) {
    }

}

