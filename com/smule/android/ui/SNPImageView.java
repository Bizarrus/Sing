package com.smule.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smule.android.C3482R;
import com.smule.android.logging.Log;
import io.fabric.sdk.android.services.events.EventsFilesManager;

public class SNPImageView extends RoundedImageView {
    private static final String f17702c = SNPImageView.class.getName();
    protected int f17703a;
    protected int f17704b;

    public SNPImageView(Context context) {
        this(context, null);
    }

    public SNPImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SNPImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f17703a = -3;
        this.f17704b = -3;
        m18879a(context, attributeSet, i);
    }

    protected void m18879a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3482R.styleable.SNPImageView, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            if (obtainStyledAttributes.getIndex(i2) == C3482R.styleable.SNPImageView_SNPImageView_size) {
                try {
                    int i3 = obtainStyledAttributes.getInt(i2, -1);
                    if (i3 > 0) {
                        this.f17703a = i3;
                        this.f17704b = i3;
                    }
                } catch (UnsupportedOperationException e) {
                    e.getMessage();
                }
            }
        }
        obtainStyledAttributes.recycle();
        if (this.f17704b == -3 || this.f17703a == -3) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, new int[]{16842996, 16842997});
            if (obtainStyledAttributes2.getLayoutDimension(0, "layout_width") > 0 && obtainStyledAttributes2.getLayoutDimension(1, "layout_width") > 0) {
                try {
                    this.f17703a = obtainStyledAttributes2.getDimensionPixelSize(0, -3);
                    this.f17704b = obtainStyledAttributes2.getDimensionPixelSize(1, -3);
                } catch (UnsupportedOperationException e2) {
                    Log.e(f17702c, e2.getMessage());
                }
            }
            obtainStyledAttributes2.recycle();
        }
    }

    public int getXMLLayoutWidth() {
        return this.f17703a;
    }

    public int getXMLLayoutHeight() {
        return this.f17704b;
    }

    public String m18878a(String str) {
        if (str == null) {
            return null;
        }
        if (getXMLLayoutWidth() < 0 || getXMLLayoutHeight() < 0) {
            return str;
        }
        String str2;
        float max = (float) Math.max(getXMLLayoutWidth(), getXMLLayoutHeight());
        if (max <= 128.0f) {
            str2 = "128";
        } else if (max <= 256.0f) {
            str2 = "256";
        } else if (max <= 512.0f) {
            str2 = "512";
        } else {
            str2 = "1024";
        }
        String c = m18876c(str);
        if (c.isEmpty()) {
            return str;
        }
        return str.substring(0, str.length() - c.length()) + m18877d(c) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + str2 + '.' + m18880b(c);
    }

    private String m18876c(String str) {
        if (str == null) {
            return null;
        }
        String str2 = "";
        int lastIndexOf = str.lastIndexOf(System.getProperty("file.separator"));
        if (lastIndexOf != -1) {
            return lastIndexOf < str.length() + -1 ? str.substring(lastIndexOf + 1) : str2;
        } else {
            return str;
        }
    }

    private String m18877d(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    public String m18880b(String str) {
        String str2 = "";
        if (str == null) {
            return str2;
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1 || lastIndexOf >= str.length() - 1) {
            return str2;
        }
        return str.substring(lastIndexOf + 1);
    }
}
