package com.smule.singandroid.chat.message_views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.ChatStatus;
import com.smule.singandroid.C1947R;

public class MessageTimestampStatus extends TextView {
    private ObjectAnimator f21422a;
    private Drawable f21423b;

    public MessageTimestampStatus(Context context) {
        super(context);
    }

    public MessageTimestampStatus(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (isInEditMode()) {
            m23092a(State.READY, false, false, true, ChatStatus.OK);
        }
    }

    public MessageTimestampStatus(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m23092a(State state, boolean z, boolean z2, boolean z3, ChatStatus chatStatus) {
        if ((z3 || state != State.READY) && state != State.RAW) {
            setPadding(getPaddingLeft(), getPaddingTop(), 0, getPaddingBottom());
            switch (state) {
                case SENDING:
                    m23093b();
                    return;
                case READY:
                    if (z) {
                        m23091a(C1947R.drawable.icn_read_chat, z2);
                        return;
                    } else {
                        m23089a();
                        return;
                    }
                case ERROR:
                    if (chatStatus == ChatStatus.DELIVERY_FAILED) {
                        m23091a(C1947R.drawable.icn_error_chat, z2);
                        return;
                    } else {
                        m23089a();
                        return;
                    }
                default:
                    return;
            }
        }
        m23089a();
    }

    public void m23089a() {
        m23090a(0);
        setPadding(getPaddingLeft(), getPaddingTop(), getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium), getPaddingBottom());
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    protected void m23091a(int i, boolean z) {
        Drawable drawable = getResources().getDrawable(i);
        if (this.f21423b == null || !z) {
            m23090a(0);
            setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            this.f21423b = drawable;
            return;
        }
        Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{this.f21423b, drawable});
        transitionDrawable.setCrossFadeEnabled(true);
        setCompoundDrawablesWithIntrinsicBounds(null, null, transitionDrawable, null);
        transitionDrawable.startTransition(200);
        m23090a(200);
        this.f21423b = drawable;
    }

    protected void m23090a(int i) {
        if (this.f21422a != null) {
            if (i == 0) {
                this.f21422a.cancel();
            } else {
                final ObjectAnimator objectAnimator = this.f21422a;
                postDelayed(new Runnable(this) {
                    final /* synthetic */ MessageTimestampStatus f21420b;

                    public void run() {
                        objectAnimator.cancel();
                    }
                }, (long) i);
            }
            this.f21422a = null;
        }
    }

    protected void m23093b() {
        RotateDrawable rotateDrawable = (RotateDrawable) getResources().getDrawable(C1947R.drawable.chat_message_status_sending_icon);
        setCompoundDrawablesWithIntrinsicBounds(null, null, rotateDrawable, null);
        this.f21422a = ObjectAnimator.ofInt(rotateDrawable, "level", new int[]{0, 10000});
        this.f21422a.setInterpolator(new LinearInterpolator());
        this.f21422a.setDuration(1000);
        this.f21422a.setRepeatCount(-1);
        this.f21422a.start();
        this.f21423b = rotateDrawable;
    }
}
