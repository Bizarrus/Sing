/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewParent
 */
package com.smule.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import com.smule.android.R;
import com.smule.android.logging.Log;
import com.smule.android.ui.roundedimageview.RoundedImageView;

public class SNPImageView
extends RoundedImageView {
    private static final String e = SNPImageView.class.getName();
    protected int a = -3;
    protected int b = -3;
    private boolean f;

    public SNPImageView(Context context) {
        this(context, null);
    }

    public SNPImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SNPImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a(context, attributeSet, n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String c(String string2) {
        if (string2 == null) {
            return null;
        }
        int n = string2.lastIndexOf(System.getProperty("file.separator"));
        String string3 = string2;
        if (n == -1) return string3;
        if (n >= string2.length() - 1) return "";
        return string2.substring(n + 1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String d(String string2) {
        if (string2 == null) {
            return null;
        }
        int n = string2.lastIndexOf(46);
        String string3 = string2;
        if (n == -1) return string3;
        return string2.substring(0, n);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public String a(String var1_1) {
        if (var1_1 == null) {
            return null;
        }
        if (this.getXMLLayoutWidth() >= 0 && this.getXMLLayoutHeight() >= 0) ** GOTO lbl11
        if (this.f && this.getParent() instanceof View && ((View)this.getParent()).getWidth() > 0 && ((View)this.getParent()).getHeight() > 0) {
            var2_3 = Math.max(((View)this.getParent()).getWidth(), ((View)this.getParent()).getHeight());
        } else {
            var4_2 = var1_1;
            if (this.f == false) return var4_2;
            Log.d(SNPImageView.e, "Parent View is not ready to reference", new IllegalStateException());
            return var1_1;
lbl11: // 1 sources:
            var2_3 = Math.max(this.getXMLLayoutWidth(), this.getXMLLayoutHeight());
        }
        var3_4 = var2_3 <= 128.0f ? "128" : (var2_3 <= 256.0f ? "256" : (var2_3 <= 512.0f ? "512" : "1024"));
        var5_5 = this.c(var1_1);
        var4_2 = var1_1;
        if (var5_5.isEmpty() != false) return var4_2;
        return var1_1.substring(0, var1_1.length() - var5_5.length()) + this.d(var5_5) + "_" + var3_4 + '.' + this.b(var5_5);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void a(Context context, AttributeSet attributeSet, int n) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SNPImageView, n, 0);
        int n2 = typedArray.getIndexCount();
        for (n = 0; n < n2; ++n) {
            if (typedArray.getIndex(n) != R.styleable.SNPImageView_SNPImageView_size) continue;
            try {
                int n3 = typedArray.getInt(n, -1);
                if (n3 <= 0) continue;
                this.a = n3;
                this.b = n3;
                continue;
            }
            catch (UnsupportedOperationException unsupportedOperationException) {
                unsupportedOperationException.getMessage();
            }
        }
        typedArray.recycle();
        if (this.b == -3 || this.a == -3) {
            if ((context = context.obtainStyledAttributes(attributeSet, new int[]{16842996, 16842997})).getLayoutDimension(0, "layout_width") > 0 && context.getLayoutDimension(1, "layout_width") > 0) {
                try {
                    this.a = context.getDimensionPixelSize(0, -3);
                    this.b = context.getDimensionPixelSize(1, -3);
                }
                catch (UnsupportedOperationException unsupportedOperationException) {
                    Log.e(e, unsupportedOperationException.getMessage());
                }
            }
            context.recycle();
        }
    }

    public String b(String string2) {
        String string3;
        String string4 = string3 = "";
        if (string2 != null) {
            int n = string2.lastIndexOf(46);
            string4 = string3;
            if (n != -1) {
                string4 = string3;
                if (n < string2.length() - 1) {
                    string4 = string2.substring(n + 1);
                }
            }
        }
        return string4;
    }

    public int getXMLLayoutHeight() {
        return this.b;
    }

    public int getXMLLayoutWidth() {
        return this.a;
    }

    public void setAllowMatchParent(boolean bl) {
        this.f = bl;
    }
}

