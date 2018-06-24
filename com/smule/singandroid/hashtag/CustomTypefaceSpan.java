/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.graphics.Typeface
 *  android.text.TextPaint
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.view.View
 *  android.widget.TextView
 */
package com.smule.singandroid.hashtag;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.smule.singandroid.hashtag.CustomLinkMovementMethod;

public class CustomTypefaceSpan
extends ClickableSpan {
    private Typeface a;

    public CustomTypefaceSpan(Context context) {
        this.a(context);
    }

    private void a(Context context) {
        this.a = Typeface.createFromAsset((AssetManager)context.getAssets(), (String)"fonts/ProximaNova-Semibold.ttf");
    }

    public void onClick(View view) {
        if (view instanceof TextView) {
            ((CustomLinkMovementMethod)((TextView)view).getMovementMethod()).a.a();
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        if (this.a != null) {
            textPaint.setTypeface(this.a);
        }
    }
}

