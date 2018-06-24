package com.smule.singandroid.textviews;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import com.smule.singandroid.hashtag.CustomTypefaceSpan;

public class EllipsizingEndMarginTextView extends EllipsizingTextView {
    public static final CharSequence f24520a = " View More…";

    static /* synthetic */ class C49721 {
        static final /* synthetic */ int[] f24504a = new int[TruncateAt.values().length];

        static {
            try {
                f24504a[TruncateAt.END.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f24504a[TruncateAt.START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f24504a[TruncateAt.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f24504a[TruncateAt.MARQUEE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private class EllipsizeEndStrategy extends EllipsizeStrategy {
        final /* synthetic */ EllipsizingEndMarginTextView f24506a;

        private EllipsizeEndStrategy(EllipsizingEndMarginTextView ellipsizingEndMarginTextView) {
            this.f24506a = ellipsizingEndMarginTextView;
            super(ellipsizingEndMarginTextView);
        }

        protected CharSequence mo6956a(CharSequence charSequence) {
            int lineEnd = m25707e(charSequence).getLineEnd(this.f24506a.e - 1);
            int length = charSequence.length();
            lineEnd = (length - lineEnd) - this.f24506a.f;
            if (lineEnd < EllipsizingEndMarginTextView.f24520a.length()) {
                lineEnd = EllipsizingEndMarginTextView.f24520a.length();
            }
            String trim = TextUtils.substring(charSequence, 0, length - lineEnd).trim();
            String b = m25709b(trim);
            while (!m25706d(b + EllipsizingEndMarginTextView.f24520a)) {
                int lastIndexOf = trim.lastIndexOf(32);
                if (lastIndexOf == -1) {
                    break;
                }
                trim = trim.substring(0, lastIndexOf).trim();
                b = m25709b(trim);
            }
            Object obj = b + EllipsizingEndMarginTextView.f24520a;
            CharSequence spannableString = new SpannableString(new SpannableStringBuilder(obj));
            spannableString.setSpan(new CustomTypefaceSpan(this.f24506a.getContext()), spannableString.length() - EllipsizingEndMarginTextView.f24520a.length(), spannableString.length(), 34);
            if (charSequence instanceof Spanned) {
                TextUtils.copySpansFrom((Spanned) charSequence, 0, obj.length(), null, spannableString, 0);
            }
            return spannableString;
        }

        public String m25709b(CharSequence charSequence) {
            return this.f24506a.g.matcher(charSequence).replaceFirst("");
        }
    }

    public EllipsizingEndMarginTextView(Context context) {
        super(context);
        this.f = 12;
    }

    public EllipsizingEndMarginTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 12;
    }

    public EllipsizingEndMarginTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = 12;
    }

    public void setEllipsize(TruncateAt truncateAt) {
        if (truncateAt == null) {
            this.c = new EllipsizeNoneStrategy(this);
            return;
        }
        switch (C49721.f24504a[truncateAt.ordinal()]) {
            case 1:
                this.c = new EllipsizeEndStrategy();
                return;
            case 2:
                this.c = new EllipsizeStartStrategy(this);
                return;
            case 3:
                this.c = new EllipsizeMiddleStrategy(this);
                return;
            case 4:
                super.setEllipsize(truncateAt);
                this.d = false;
                break;
        }
        this.c = new EllipsizeNoneStrategy(this);
    }
}
