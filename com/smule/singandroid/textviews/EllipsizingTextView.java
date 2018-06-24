package com.smule.singandroid.textviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.mopub.volley.DefaultRetryPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EllipsizingTextView extends TextView {
    private static final Pattern f24507a = Pattern.compile("[\\.!?,;:…]*$", 32);
    public static final CharSequence f24508b = "…";
    protected EllipsizeStrategy f24509c;
    protected boolean f24510d;
    protected int f24511e;
    protected int f24512f;
    protected Pattern f24513g;
    private final List<EllipsizeListener> f24514h;
    private boolean f24515i;
    private boolean f24516j;
    private CharSequence f24517k;
    private float f24518l;
    private float f24519m;

    protected abstract class EllipsizeStrategy {
        final /* synthetic */ EllipsizingTextView f24505b;

        protected abstract CharSequence mo6956a(CharSequence charSequence);

        protected EllipsizeStrategy(EllipsizingTextView ellipsizingTextView) {
            this.f24505b = ellipsizingTextView;
        }

        public CharSequence m25705c(CharSequence charSequence) {
            return !m25706d(charSequence) ? mo6956a(charSequence) : charSequence;
        }

        public boolean m25706d(CharSequence charSequence) {
            return m25707e(charSequence).getLineCount() <= m25702a();
        }

        protected Layout m25707e(CharSequence charSequence) {
            return new StaticLayout(charSequence, this.f24505b.getPaint(), (this.f24505b.getMeasuredWidth() - this.f24505b.getPaddingLeft()) - this.f24505b.getPaddingRight(), Alignment.ALIGN_NORMAL, this.f24505b.f24518l, this.f24505b.f24519m, false);
        }

        protected int m25702a() {
            if (!this.f24505b.m25713a()) {
                return this.f24505b.f24511e;
            }
            int b = m25704b();
            if (b == -1) {
                return 1;
            }
            return b;
        }

        protected int m25704b() {
            return ((this.f24505b.getHeight() - this.f24505b.getCompoundPaddingTop()) - this.f24505b.getCompoundPaddingBottom()) / m25707e("").getLineBottom(0);
        }
    }

    static /* synthetic */ class C49731 {
        static final /* synthetic */ int[] f24521a = new int[TruncateAt.values().length];

        static {
            try {
                f24521a[TruncateAt.END.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f24521a[TruncateAt.START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f24521a[TruncateAt.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f24521a[TruncateAt.MARQUEE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private class EllipsizeEndStrategy extends EllipsizeStrategy {
        final /* synthetic */ EllipsizingTextView f24522a;

        private EllipsizeEndStrategy(EllipsizingTextView ellipsizingTextView) {
            this.f24522a = ellipsizingTextView;
            super(ellipsizingTextView);
        }

        protected CharSequence mo6956a(CharSequence charSequence) {
            int lineEnd = m25707e(charSequence).getLineEnd(this.f24522a.f24511e - 1);
            int length = charSequence.length();
            lineEnd = (length - lineEnd) - this.f24522a.f24512f;
            if (lineEnd < EllipsizingTextView.f24508b.length()) {
                lineEnd = EllipsizingTextView.f24508b.length();
            }
            String trim = TextUtils.substring(charSequence, 0, length - lineEnd).trim();
            String b = m25715b(trim);
            while (!m25706d(b + EllipsizingTextView.f24508b)) {
                int lastIndexOf = trim.lastIndexOf(32);
                if (lastIndexOf == -1) {
                    break;
                }
                trim = trim.substring(0, lastIndexOf).trim();
                b = m25715b(trim);
            }
            Object obj = b + EllipsizingTextView.f24508b;
            CharSequence spannableStringBuilder = new SpannableStringBuilder(obj);
            if (charSequence instanceof Spanned) {
                TextUtils.copySpansFrom((Spanned) charSequence, 0, obj.length(), null, spannableStringBuilder, 0);
            }
            return spannableStringBuilder;
        }

        public String m25715b(CharSequence charSequence) {
            return this.f24522a.f24513g.matcher(charSequence).replaceFirst("");
        }
    }

    public interface EllipsizeListener {
        void m25716a(boolean z);
    }

    protected class EllipsizeMiddleStrategy extends EllipsizeStrategy {
        final /* synthetic */ EllipsizingTextView f24523a;

        protected EllipsizeMiddleStrategy(EllipsizingTextView ellipsizingTextView) {
            this.f24523a = ellipsizingTextView;
            super(ellipsizingTextView);
        }

        protected CharSequence mo6956a(CharSequence charSequence) {
            int lineEnd = m25707e(charSequence).getLineEnd(this.f24523a.f24511e - 1);
            int length = charSequence.length();
            int i = length - lineEnd;
            if (i < EllipsizingTextView.f24508b.length()) {
                i = EllipsizingTextView.f24508b.length();
            }
            i += lineEnd % 2;
            Object trim = TextUtils.substring(charSequence, 0, (length / 2) - (i / 2)).trim();
            Object trim2 = TextUtils.substring(charSequence, (i / 2) + (length / 2), length).trim();
            while (!m25706d(trim + EllipsizingTextView.f24508b + trim2)) {
                i = trim.lastIndexOf(32);
                int indexOf = trim2.indexOf(32);
                if (i == -1 || indexOf == -1) {
                    break;
                }
                trim = trim.substring(0, i).trim();
                String trim3 = trim2.substring(indexOf, trim2.length()).trim();
            }
            Spannable spannableStringBuilder = new SpannableStringBuilder(trim);
            Spannable spannableStringBuilder2 = new SpannableStringBuilder(trim2);
            if (charSequence instanceof Spanned) {
                TextUtils.copySpansFrom((Spanned) charSequence, 0, trim.length(), null, spannableStringBuilder, 0);
                TextUtils.copySpansFrom((Spanned) charSequence, length - trim2.length(), length, null, spannableStringBuilder2, 0);
            }
            return TextUtils.concat(new CharSequence[]{spannableStringBuilder, EllipsizingTextView.f24508b, spannableStringBuilder2});
        }
    }

    protected class EllipsizeNoneStrategy extends EllipsizeStrategy {
        final /* synthetic */ EllipsizingTextView f24524a;

        protected EllipsizeNoneStrategy(EllipsizingTextView ellipsizingTextView) {
            this.f24524a = ellipsizingTextView;
            super(ellipsizingTextView);
        }

        protected CharSequence mo6956a(CharSequence charSequence) {
            return charSequence;
        }
    }

    protected class EllipsizeStartStrategy extends EllipsizeStrategy {
        final /* synthetic */ EllipsizingTextView f24525a;

        protected EllipsizeStartStrategy(EllipsizingTextView ellipsizingTextView) {
            this.f24525a = ellipsizingTextView;
            super(ellipsizingTextView);
        }

        protected CharSequence mo6956a(CharSequence charSequence) {
            int lineEnd = m25707e(charSequence).getLineEnd(this.f24525a.f24511e - 1);
            int length = charSequence.length();
            lineEnd = length - lineEnd;
            if (lineEnd < EllipsizingTextView.f24508b.length()) {
                lineEnd = EllipsizingTextView.f24508b.length();
            }
            String trim = TextUtils.substring(charSequence, lineEnd, length).trim();
            while (!m25706d(EllipsizingTextView.f24508b + trim)) {
                int indexOf = trim.indexOf(32);
                if (indexOf == -1) {
                    break;
                }
                trim = trim.substring(indexOf, trim.length()).trim();
            }
            Object obj = EllipsizingTextView.f24508b + trim;
            CharSequence spannableStringBuilder = new SpannableStringBuilder(obj);
            if (charSequence instanceof Spanned) {
                TextUtils.copySpansFrom((Spanned) charSequence, length - obj.length(), length, null, spannableStringBuilder, 0);
            }
            return spannableStringBuilder;
        }
    }

    public EllipsizingTextView(Context context) {
        this(context, null);
    }

    public EllipsizingTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public EllipsizingTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f24514h = new ArrayList();
        this.f24517k = "";
        this.f24518l = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f24519m = 0.0f;
        this.f24512f = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16843091}, i, 0);
        setMaxLines(obtainStyledAttributes.getInt(0, Integer.MAX_VALUE));
        obtainStyledAttributes.recycle();
        setEndPunctuationPattern(f24507a);
    }

    public void setEndPunctuationPattern(Pattern pattern) {
        this.f24513g = pattern;
    }

    @SuppressLint({"Override"})
    public int getMaxLines() {
        return this.f24511e;
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        this.f24511e = i;
        this.f24510d = true;
    }

    public boolean m25713a() {
        return this.f24511e == Integer.MAX_VALUE;
    }

    public void setLineSpacing(float f, float f2) {
        this.f24519m = f;
        this.f24518l = f2;
        super.setLineSpacing(f, f2);
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        if (!this.f24516j) {
            this.f24517k = charSequence;
            this.f24510d = true;
        }
        super.setText(charSequence, bufferType);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (m25713a()) {
            this.f24510d = true;
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        if (m25713a()) {
            this.f24510d = true;
        }
    }

    protected void onDraw(@NonNull Canvas canvas) {
        if (this.f24510d) {
            m25712b();
        }
        super.onDraw(canvas);
    }

    private void m25712b() {
        boolean z;
        int maxLines = getMaxLines();
        if (this.f24517k == null) {
            this.f24517k = "";
        }
        CharSequence charSequence = this.f24517k;
        if (maxLines != -1) {
            if (this.f24509c == null) {
                setEllipsize(null);
            }
            boolean z2 = !this.f24509c.m25706d(this.f24517k);
            charSequence = this.f24509c.m25705c(this.f24517k);
            z = z2;
        } else {
            z = false;
        }
        if (!charSequence.equals(getText())) {
            this.f24516j = true;
            try {
                setText(charSequence);
            } finally {
                this.f24516j = false;
            }
        }
        this.f24510d = false;
        if (z != this.f24515i) {
            this.f24515i = z;
            for (EllipsizeListener a : this.f24514h) {
                a.m25716a(z);
            }
        }
    }

    public void setEllipsize(TruncateAt truncateAt) {
        if (truncateAt == null) {
            this.f24509c = new EllipsizeNoneStrategy(this);
            return;
        }
        switch (C49731.f24521a[truncateAt.ordinal()]) {
            case 1:
                this.f24509c = new EllipsizeEndStrategy();
                return;
            case 2:
                this.f24509c = new EllipsizeStartStrategy(this);
                return;
            case 3:
                this.f24509c = new EllipsizeMiddleStrategy(this);
                return;
            case 4:
                super.setEllipsize(truncateAt);
                this.f24510d = false;
                break;
        }
        this.f24509c = new EllipsizeNoneStrategy(this);
    }
}
