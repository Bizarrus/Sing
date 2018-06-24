/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.ColorInt
 *  android.text.TextPaint
 *  android.text.style.ClickableSpan
 */
package com.smule.singandroid.common;

import android.support.annotation.ColorInt;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

public abstract class MagicClickableSpan
extends ClickableSpan {
    @ColorInt
    private int a;
    @ColorInt
    private int b;
    private boolean c;
    private boolean d = false;
    private boolean e = false;

    public MagicClickableSpan(@ColorInt int n, @ColorInt int n2, boolean bl) {
        this.a = n;
        this.b = n2;
        this.c = bl;
        this.e = true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        if (this.e) {
            int n = this.d ? this.b : this.a;
            textPaint.setColor(n);
        }
        textPaint.setUnderlineText(this.c);
    }
}

