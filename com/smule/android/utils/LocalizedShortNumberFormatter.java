/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 */
package com.smule.android.utils;

import android.content.Context;
import android.content.res.Resources;
import com.smule.android.R;
import com.smule.android.logging.Log;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class LocalizedShortNumberFormatter {
    public static final String a = LocalizedShortNumberFormatter.class.getName();
    private static final NumberFormat c;
    private static final NumberFormat d;
    private static final String e;
    private static final String f;
    private final List<UnitFormat> b;
    private Context g;

    static {
        f = new DecimalFormat("#").format(0);
        c = NumberFormat.getInstance(Locale.getDefault());
        d = new DecimalFormat("@@@");
        e = Character.toString(new DecimalFormat().getDecimalFormatSymbols().getDecimalSeparator());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public LocalizedShortNumberFormatter(Context object) {
        Object object2 = null;
        this.b = new LinkedList<UnitFormat>();
        this.g = object;
        float f = 1.0f;
        String[] arrstring = object.getResources().getStringArray(R.array.cm_number_short_forms);
        int n = arrstring.length;
        int n2 = 0;
        object = object2;
        while (n2 < n) {
            Object object3 = arrstring[n2];
            int n3 = object3.indexOf(58);
            if (n3 > 0) {
                object2 = object3.substring(0, n3);
                String string2 = object3.substring(n3 + 1);
                float f2 = f;
                object3 = object;
                try {
                    long l = Long.parseLong((String)object2);
                    float f3 = f;
                    object2 = object;
                    f2 = f;
                    object3 = object;
                    if (!string2.equals(object)) {
                        f3 = l;
                        object2 = string2;
                    }
                    f2 = f3;
                    object3 = object2;
                    this.b.add(new UnitFormat(l, f3, (String)object2));
                    f = f3;
                    object = object2;
                }
                catch (NumberFormatException numberFormatException) {
                    f = f2;
                    object = object3;
                }
            }
            ++n2;
        }
        return;
    }

    public String a(long l) {
        if (l < this.b.get((int)0).a) {
            return c.format(l);
        }
        int n = 0;
        while (this.b.get((int)(n + 1)).a <= l) {
            ++n;
        }
        String string2 = d.format((float)l / this.b.get((int)n).b);
        while (string2.endsWith(f)) {
            string2 = string2.substring(0, string2.length() - 1);
        }
        String string3 = string2;
        if (string2.endsWith(e)) {
            string3 = string2.substring(0, string2.length() - 1);
        }
        return this.g.getString(R.string.cm_number_short_forms_string, new Object[]{string3, this.b.get((int)n).c});
    }

    public String a(long l, long l2) {
        if (l > l2) {
            return this.a(l);
        }
        try {
            String string2 = c.format(l);
            return string2;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            Log.c(a, "Failed to format value: " + l, illegalArgumentException);
            try {
                String string3 = NumberFormat.getInstance(Locale.US).format(l);
                return string3;
            }
            catch (IllegalArgumentException illegalArgumentException2) {
                return Long.toString(l);
            }
        }
    }

    private class UnitFormat {
        public final long a;
        public final float b;
        public final String c;

        private UnitFormat(long l, float f, String string2) {
            this.a = l;
            this.b = f;
            this.c = string2;
        }
    }

}

