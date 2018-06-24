/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.text.SpannableString
 *  android.text.TextPaint
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.view.View
 *  android.widget.TextView
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.fragments;

import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.smule.android.network.managers.SongbookManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.fragments.AboutAdsFragment_;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class AboutAdsFragment
extends BaseFragment {
    public static final String g = AboutAdsFragment.class.getName();
    @ViewById
    protected TextView h;

    public static AboutAdsFragment a() {
        return new AboutAdsFragment_();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SubscriptionManager.a().b()) {
            this.a(g);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.u();
        this.c(2131296961);
    }

    @AfterViews
    protected void t() {
        String string2 = this.getResources().getString(2131296964);
        SpannableString spannableString = new SpannableString((CharSequence)this.getResources().getString(2131296960, new Object[]{string2}));
        ClickableSpan clickableSpan = new ClickableSpan(){

            public void onClick(View object) {
                object = SongbookManager.b().c();
                AboutAdsFragment.this.a(UpsellManager.a((boolean)false, (SongbookEntry)null, (String)object, (String)null, (UpsellType)UpsellType.e));
            }

            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(AboutAdsFragment.this.getResources().getColor(2131689479));
                textPaint.setUnderlineText(false);
            }
        };
        int n = spannableString.toString().indexOf(string2);
        if (n > -1) {
            spannableString.setSpan((Object)clickableSpan, n, string2.length() + n, 33);
        }
        this.h.setText((CharSequence)spannableString);
        this.h.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public String x() {
        return g;
    }

}

