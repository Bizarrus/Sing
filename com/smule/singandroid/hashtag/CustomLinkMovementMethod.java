/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.Layout
 *  android.text.NoCopySpan
 *  android.text.NoCopySpan$Concrete
 *  android.text.Selection
 *  android.text.Spannable
 *  android.text.method.ScrollingMovementMethod
 *  android.text.style.ClickableSpan
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.ViewConfiguration
 *  android.widget.TextView
 */
package com.smule.singandroid.hashtag;

import android.content.Context;
import android.text.Layout;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

public class CustomLinkMovementMethod
extends ScrollingMovementMethod {
    private static Object e = new NoCopySpan.Concrete();
    TextClickedListener a = null;
    private final int b;
    private int c;
    private int d;

    public CustomLinkMovementMethod(Context context) {
        this.b = ViewConfiguration.get((Context)context).getScaledTouchSlop();
    }

    private boolean a(int n, int n2) {
        if (Math.sqrt((n = this.c - n) * n + (n2 = this.d - n2) * n2) < (double)this.b) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean a(int var1_1, TextView var2_2, Spannable var3_3) {
        var11_4 = var2_2.getLayout();
        var5_5 = var2_2.getTotalPaddingTop();
        var6_6 = var2_2.getTotalPaddingBottom();
        var7_7 = var2_2.getScrollY();
        var8_8 = var2_2.getHeight();
        var4_9 = var11_4.getLineForVertical(var7_7);
        var5_5 = var11_4.getLineForVertical(var8_8 + var7_7 - (var5_5 + var6_6));
        var8_8 = var11_4.getLineStart(var4_9);
        var9_10 = var11_4.getLineEnd(var5_5);
        var11_4 = (ClickableSpan[])var3_3.getSpans(var8_8, var9_10, ClickableSpan.class);
        var4_9 = Selection.getSelectionStart((CharSequence)var3_3);
        var5_5 = Selection.getSelectionEnd((CharSequence)var3_3);
        var6_6 = Math.min(var4_9, var5_5);
        var5_5 = var7_7 = Math.max(var4_9, var5_5);
        var4_9 = var6_6;
        if (var6_6 < 0) {
            var5_5 = var7_7;
            var4_9 = var6_6;
            if (var3_3.getSpanStart(CustomLinkMovementMethod.e) >= 0) {
                var4_9 = var5_5 = var3_3.length();
            }
        }
        var6_6 = var5_5;
        var5_5 = var4_9;
        if (var4_9 > var9_10) {
            var6_6 = Integer.MAX_VALUE;
            var5_5 = Integer.MAX_VALUE;
        }
        if (var6_6 < var8_8) {
            var4_9 = -1;
            var5_5 = -1;
        } else {
            var4_9 = var6_6;
        }
        switch (var1_1) {
            default: {
                return false;
            }
            case 1: {
                if (var5_5 == var4_9) return false;
                if ((var3_3 = (ClickableSpan[])var3_3.getSpans(var5_5, var4_9, ClickableSpan.class)).length != 1) return false;
                var3_3[0].onClick((View)var2_2);
                return false;
            }
            case 2: {
                var8_8 = -1;
                var6_6 = -1;
                var1_1 = 0;
                do {
                    if (var1_1 >= var11_4.length) {
                        if (var6_6 < 0) return false;
                        Selection.setSelection((Spannable)var3_3, (int)var8_8, (int)var6_6);
                        return true;
                    }
                    var10_11 = var3_3.getSpanEnd((Object)var11_4[var1_1]);
                    if (var10_11 < var4_9) ** GOTO lbl53
                    var9_10 = var8_8;
                    var7_7 = var6_6;
                    if (var5_5 != var4_9) ** GOTO lbl58
lbl53: // 2 sources:
                    var9_10 = var8_8;
                    var7_7 = var6_6;
                    if (var10_11 > var8_8) {
                        var7_7 = var3_3.getSpanStart((Object)var11_4[var1_1]);
                        var9_10 = var10_11;
                    }
lbl58: // 4 sources:
                    ++var1_1;
                    var8_8 = var9_10;
                    var6_6 = var7_7;
                } while (true);
            }
            case 3: 
        }
        var1_1 = 0;
        var8_8 = Integer.MAX_VALUE;
        var6_6 = Integer.MAX_VALUE;
        do {
            block23 : {
                block22 : {
                    if (var1_1 >= var11_4.length) {
                        if (var8_8 >= Integer.MAX_VALUE) return false;
                        Selection.setSelection((Spannable)var3_3, (int)var6_6, (int)var8_8);
                        return true;
                    }
                    var10_12 = var3_3.getSpanStart((Object)var11_4[var1_1]);
                    if (var10_12 > var5_5) break block22;
                    var9_10 = var8_8;
                    var7_7 = var6_6;
                    if (var5_5 != var4_9) break block23;
                }
                var9_10 = var8_8;
                var7_7 = var6_6;
                if (var10_12 < var6_6) {
                    var9_10 = var3_3.getSpanEnd((Object)var11_4[var1_1]);
                    var7_7 = var10_12;
                }
            }
            ++var1_1;
            var8_8 = var9_10;
            var6_6 = var7_7;
        } while (true);
    }

    public void a(TextClickedListener textClickedListener) {
        this.a = textClickedListener;
    }

    protected boolean down(TextView textView, Spannable spannable) {
        if (this.a(3, textView, spannable)) {
            return true;
        }
        return super.down(textView, spannable);
    }

    public void initialize(TextView textView, Spannable spannable) {
        Selection.removeSelection((Spannable)spannable);
        spannable.removeSpan(e);
    }

    protected boolean left(TextView textView, Spannable spannable) {
        if (this.a(2, textView, spannable)) {
            return true;
        }
        return super.left(textView, spannable);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKeyDown(TextView textView, Spannable spannable, int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return super.onKeyDown(textView, spannable, n, keyEvent);
            }
            case 23: 
            case 66: {
                if (keyEvent.getRepeatCount() != 0 || !this.a(1, textView, spannable)) return super.onKeyDown(textView, spannable, n, keyEvent);
                return true;
            }
        }
    }

    public boolean onKeyUp(TextView textView, Spannable spannable, int n, KeyEvent keyEvent) {
        return false;
    }

    public void onTakeFocus(TextView textView, Spannable spannable, int n) {
        Selection.removeSelection((Spannable)spannable);
        if ((n & 1) != 0) {
            spannable.setSpan(e, 0, 0, 34);
            return;
        }
        spannable.removeSpan(e);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int n = motionEvent.getAction();
        if (n != 1) {
            if (n != 0) return super.onTouchEvent(textView, spannable, motionEvent);
        }
        int n2 = (int)motionEvent.getRawX();
        int n3 = (int)motionEvent.getRawY();
        if (n == 0) {
            this.c = n2;
            this.d = n3;
        }
        int n4 = (int)motionEvent.getX();
        int n5 = (int)motionEvent.getY();
        int n6 = textView.getTotalPaddingLeft();
        int n7 = textView.getTotalPaddingTop();
        int n8 = textView.getScrollX();
        int n9 = textView.getScrollY();
        ClickableSpan[] arrclickableSpan = textView.getLayout();
        n4 = arrclickableSpan.getOffsetForHorizontal(arrclickableSpan.getLineForVertical(n5 - n7 + n9), (float)(n4 - n6 + n8));
        arrclickableSpan = (ClickableSpan[])spannable.getSpans(n4, n4, ClickableSpan.class);
        if (arrclickableSpan.length == 0) {
            Selection.removeSelection((Spannable)spannable);
            if (n != 1) return super.onTouchEvent(textView, spannable, motionEvent);
            if (!this.a(n2, n3)) return super.onTouchEvent(textView, spannable, motionEvent);
            if (this.a == null) return super.onTouchEvent(textView, spannable, motionEvent);
            this.a.a();
            return super.onTouchEvent(textView, spannable, motionEvent);
        }
        if (n == 1 && this.a(n2, n3)) {
            arrclickableSpan[0].onClick((View)textView);
            return true;
        }
        if (n != 0) return true;
        Selection.setSelection((Spannable)spannable, (int)spannable.getSpanStart((Object)arrclickableSpan[0]), (int)spannable.getSpanEnd((Object)arrclickableSpan[0]));
        return true;
    }

    protected boolean right(TextView textView, Spannable spannable) {
        if (this.a(3, textView, spannable)) {
            return true;
        }
        return super.right(textView, spannable);
    }

    protected boolean up(TextView textView, Spannable spannable) {
        if (this.a(2, textView, spannable)) {
            return true;
        }
        return super.up(textView, spannable);
    }

    public static interface TextClickedListener {
        public void a();
    }

}

