package com.smule.singandroid.hashtag;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class CustomTypefaceSpan extends ClickableSpan {
    private Typeface f22685a;

    public CustomTypefaceSpan(Context context) {
        m24187a(context);
    }

    private void m24187a(Context context) {
        this.f22685a = Typeface.createFromAsset(context.getAssets(), "fonts/ProximaNova-Semibold.ttf");
    }

    public void onClick(View view) {
        if (view instanceof TextView) {
            ((CustomLinkMovementMethod) ((TextView) view).getMovementMethod()).f22684a.mo6871a();
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        if (this.f22685a != null) {
            textPaint.setTypeface(this.f22685a);
        }
    }
}
