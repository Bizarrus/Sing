package com.smule.singandroid.utils;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.smule.singandroid.utils.CustomTypefaceSpan.TextViewStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class StyleReplacer {
    static final /* synthetic */ boolean f25081a = (!StyleReplacer.class.desiredAssertionStatus());
    private String f25082b;
    private ArrayList<StyleReplacement> f25083c = new ArrayList();
    private Object f25084d;
    private TextView f25085e;
    private Resources f25086f;
    private OnClickListener f25087g;

    private static class NoDrawClickableSpan extends ClickableSpan {
        private OnClickListener f25073a;

        NoDrawClickableSpan(OnClickListener onClickListener) {
            this.f25073a = onClickListener;
        }

        public void onClick(View view) {
            if (this.f25073a != null) {
                this.f25073a.onClick(view);
            }
        }

        public void updateDrawState(TextPaint textPaint) {
        }
    }

    private class StyleReplacement implements Comparable {
        public String f25074a;
        public String f25075b;
        public Object f25076c;
        public OnClickListener f25077d;
        public boolean f25078e;
        int f25079f;
        final /* synthetic */ StyleReplacer f25080g;

        public StyleReplacement(StyleReplacer styleReplacer, String str, String str2, Object obj) {
            this(styleReplacer, str, str2, obj, null);
        }

        public StyleReplacement(StyleReplacer styleReplacer, String str, String str2, Object obj, OnClickListener onClickListener) {
            this(styleReplacer, str, str2, obj, onClickListener, false);
        }

        public StyleReplacement(StyleReplacer styleReplacer, String str, String str2, Object obj, OnClickListener onClickListener, boolean z) {
            this.f25080g = styleReplacer;
            this.f25074a = str;
            if (z) {
                this.f25075b = "*" + str2;
            } else {
                this.f25075b = str2;
            }
            this.f25076c = obj;
            this.f25077d = onClickListener;
            this.f25078e = z;
        }

        public int compareTo(Object obj) {
            return ((StyleReplacement) obj).f25079f;
        }
    }

    public StyleReplacer(String str, TextView textView, Object obj) {
        this.f25082b = str;
        this.f25085e = textView;
        m26179a(obj);
    }

    public StyleReplacer(String str, TextView textView, float f, int i, Typeface typeface) {
        this.f25082b = str;
        this.f25085e = textView;
        m26175a(f, i, typeface);
    }

    public StyleReplacer(String str, TextView textView, Object obj, Resources resources) {
        this.f25082b = str;
        this.f25085e = textView;
        this.f25086f = resources;
        m26179a(obj);
    }

    public void m26179a(Object obj) {
        if (obj instanceof TextViewStyle) {
            this.f25084d = new CustomTypefaceSpan(this.f25085e.getContext(), (TextViewStyle) obj);
        } else {
            this.f25084d = obj;
        }
    }

    public void m26175a(float f, int i, Typeface typeface) {
        if (f25081a || this.f25085e != null) {
            this.f25084d = new CustomTypefaceSpan(this.f25085e.getContext(), f, i, typeface);
            return;
        }
        throw new AssertionError();
    }

    public void m26174a() {
        if (f25081a || this.f25085e != null) {
            this.f25085e.setText(m26185b(), BufferType.SPANNABLE);
            return;
        }
        throw new AssertionError();
    }

    public void m26182a(String str, String str2, Object obj) {
        m26183a(str, str2, obj, null);
    }

    public void m26183a(String str, String str2, Object obj, OnClickListener onClickListener) {
        m26184a(str, str2, obj, onClickListener, false);
    }

    public void m26184a(String str, String str2, Object obj, OnClickListener onClickListener, boolean z) {
        this.f25083c.add(new StyleReplacement(this, str, str2, obj, onClickListener, z));
    }

    public void m26181a(String str, String str2, float f, int i, Typeface typeface) {
        if (f25081a || this.f25085e != null) {
            this.f25083c.add(new StyleReplacement(this, str, str2, new CustomTypefaceSpan(this.f25085e.getContext(), f, i, typeface)));
            return;
        }
        throw new AssertionError();
    }

    public void m26180a(String str, Object obj) {
        int indexOf = this.f25082b.indexOf(str);
        if (indexOf != -1) {
            this.f25083c.add(new StyleReplacement(this, this.f25082b.substring(indexOf + str.length(), this.f25082b.length()), null, obj));
        }
    }

    public void m26178a(OnClickListener onClickListener) {
        this.f25087g = onClickListener;
    }

    public Spannable m26185b() {
        String str = this.f25082b;
        Iterator it = this.f25083c.iterator();
        while (it.hasNext()) {
            StyleReplacement styleReplacement = (StyleReplacement) it.next();
            styleReplacement.f25079f = this.f25082b.indexOf(styleReplacement.f25074a);
        }
        Iterator it2 = this.f25083c.iterator();
        String str2 = str;
        while (it2.hasNext()) {
            String str3;
            styleReplacement = (StyleReplacement) it2.next();
            int i = styleReplacement.f25079f;
            if (i == -1 || styleReplacement.f25075b == null) {
                str3 = str2;
            } else {
                int length = styleReplacement.f25075b.length() - styleReplacement.f25074a.length();
                Iterator it3 = this.f25083c.iterator();
                while (it3.hasNext()) {
                    StyleReplacement styleReplacement2 = (StyleReplacement) it3.next();
                    int i2 = styleReplacement2.f25079f;
                    if (i2 > i) {
                        styleReplacement2.f25079f = i2 + length;
                    }
                }
                str3 = str2.substring(0, i) + styleReplacement.f25075b + str2.substring(styleReplacement.f25074a.length() + i, str2.length());
            }
            str2 = str3;
        }
        Spannable spannableString = new SpannableString(str2);
        if (this.f25084d != null) {
            spannableString.setSpan(this.f25084d, 0, str2.length(), 0);
        }
        it = this.f25083c.iterator();
        while (it.hasNext()) {
            styleReplacement = (StyleReplacement) it.next();
            i = styleReplacement.f25079f;
            if (i != -1) {
                int length2;
                if (styleReplacement.f25075b != null) {
                    length2 = styleReplacement.f25075b.length();
                } else {
                    length2 = styleReplacement.f25074a.length();
                }
                spannableString.setSpan(styleReplacement.f25076c, i, i + length2, 0);
                if (this.f25086f != null && styleReplacement.f25078e) {
                    spannableString.setSpan(PerformanceV2Util.m25937a(this.f25086f), i, i + 1, 17);
                }
                if (styleReplacement.f25077d != null) {
                    spannableString.setSpan(new NoDrawClickableSpan(styleReplacement.f25077d), i, i + length2, 33);
                }
            }
        }
        m26176a(spannableString);
        return spannableString;
    }

    protected void m26176a(Spannable spannable) {
        if (this.f25087g != null) {
            StyleReplacement styleReplacement;
            Iterator it = this.f25083c.iterator();
            while (it.hasNext()) {
                styleReplacement = (StyleReplacement) it.next();
                if (styleReplacement.f25077d == null || styleReplacement.f25079f == -1) {
                    it.remove();
                }
            }
            Collections.sort(this.f25083c);
            for (int i = 0; i < this.f25083c.size(); i++) {
                int i2;
                styleReplacement = (StyleReplacement) this.f25083c.get(i);
                if (i == 0 && styleReplacement.f25079f > 0) {
                    m26177a(spannable, 0, styleReplacement.f25079f, this.f25087g);
                }
                int length = styleReplacement.f25079f + styleReplacement.f25075b.length();
                if (i < this.f25083c.size() - 1) {
                    i2 = ((StyleReplacement) this.f25083c.get(i + 1)).f25079f;
                } else {
                    i2 = spannable.length();
                }
                m26177a(spannable, length, i2, this.f25087g);
            }
        }
    }

    protected void m26177a(Spannable spannable, int i, int i2, OnClickListener onClickListener) {
        if (i != i2) {
            spannable.setSpan(new NoDrawClickableSpan(onClickListener), i, i2, 33);
        }
    }
}
