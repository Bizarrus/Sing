/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.ObjectAnimator
 *  android.animation.TimeInterpolator
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.RotateDrawable
 *  android.graphics.drawable.TransitionDrawable
 *  android.util.AttributeSet
 *  android.view.animation.LinearInterpolator
 *  android.widget.TextView
 */
package com.smule.singandroid.chat.message_views;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;

public class MessageTimestampStatus
extends TextView {
    private ObjectAnimator a;
    private Drawable b;

    public MessageTimestampStatus(Context context) {
        super(context);
    }

    public MessageTimestampStatus(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (this.isInEditMode()) {
            this.a(ChatMessage.State.b, false, false, true, ChatStatus.a);
        }
    }

    public MessageTimestampStatus(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public void a() {
        this.a(0);
        int n = this.getResources().getDimensionPixelOffset(2131428167);
        this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), n, this.getPaddingBottom());
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(int n) {
        if (this.a == null) {
            return;
        }
        if (n == 0) {
            this.a.cancel();
        } else {
            this.postDelayed(new Runnable(this.a){
                final /* synthetic */ ObjectAnimator a;
                {
                    this.a = objectAnimator;
                }

                @Override
                public void run() {
                    this.a.cancel();
                }
            }, (long)n);
        }
        this.a = null;
    }

    protected void a(int n, boolean bl) {
        Drawable drawable2 = this.getResources().getDrawable(n);
        if (this.b == null || !bl) {
            this.a(0);
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable2, null);
            this.b = drawable2;
            return;
        }
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{this.b, drawable2});
        transitionDrawable.setCrossFadeEnabled(true);
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, (Drawable)transitionDrawable, null);
        transitionDrawable.startTransition(200);
        this.a(200);
        this.b = drawable2;
    }

    public void a(ChatMessage.State state, boolean bl, boolean bl2, boolean bl3, ChatStatus chatStatus) {
        if (!bl3 && state == ChatMessage.State.b || state == ChatMessage.State.a) {
            this.a();
            return;
        }
        this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), 0, this.getPaddingBottom());
        switch (.a[state.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.b();
                return;
            }
            case 2: {
                if (bl) {
                    this.a(2130837978, bl2);
                    return;
                }
                this.a();
                return;
            }
            case 3: 
        }
        if (chatStatus == ChatStatus.h) {
            this.a(2130837912, bl2);
            return;
        }
        this.a();
    }

    protected void b() {
        RotateDrawable rotateDrawable = (RotateDrawable)this.getResources().getDrawable(2130837688);
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, (Drawable)rotateDrawable, null);
        this.a = ObjectAnimator.ofInt((Object)rotateDrawable, (String)"level", (int[])new int[]{0, 10000});
        this.a.setInterpolator((TimeInterpolator)new LinearInterpolator());
        this.a.setDuration(1000);
        this.a.setRepeatCount(-1);
        this.a.start();
        this.b = rotateDrawable;
    }

}

