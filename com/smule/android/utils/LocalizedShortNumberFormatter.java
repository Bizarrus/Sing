package com.smule.android.utils;

import android.content.Context;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.C3482R;
import com.smule.android.logging.Log;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class LocalizedShortNumberFormatter {
    public static final String f17789a = LocalizedShortNumberFormatter.class.getName();
    private static final NumberFormat f17790c = NumberFormat.getInstance(Locale.getDefault());
    private static final NumberFormat f17791d = new DecimalFormat("@@@");
    private static final String f17792e = Character.toString(new DecimalFormat().getDecimalFormatSymbols().getDecimalSeparator());
    private static final String f17793f = new DecimalFormat("#").format(0);
    private final List<UnitFormat> f17794b = new LinkedList();
    private Context f17795g;

    private class UnitFormat {
        public final long f17785a;
        public final float f17786b;
        public final String f17787c;
        final /* synthetic */ LocalizedShortNumberFormatter f17788d;

        private UnitFormat(LocalizedShortNumberFormatter localizedShortNumberFormatter, long j, float f, String str) {
            this.f17788d = localizedShortNumberFormatter;
            this.f17785a = j;
            this.f17786b = f;
            this.f17787c = str;
        }
    }

    public LocalizedShortNumberFormatter(Context context) {
        Object obj = null;
        this.f17795g = context;
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        for (String str : context.getResources().getStringArray(C3482R.array.cm_number_short_forms)) {
            String str2;
            int indexOf = str2.indexOf(58);
            if (indexOf > 0) {
                String substring = str2.substring(0, indexOf);
                str2 = str2.substring(indexOf + 1);
                try {
                    long parseLong = Long.parseLong(substring);
                    if (!str2.equals(obj)) {
                        f = (float) parseLong;
                        obj = str2;
                    }
                    this.f17794b.add(new UnitFormat(parseLong, f, obj));
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    public String m18999a(long j, long j2) {
        if (j > j2) {
            return m18998a(j);
        }
        try {
            return f17790c.format(j);
        } catch (Throwable e) {
            Log.c(f17789a, "Failed to format value: " + j, e);
            try {
                return NumberFormat.getInstance(Locale.US).format(j);
            } catch (IllegalArgumentException e2) {
                return Long.toString(j);
            }
        }
    }

    public String m18998a(long j) {
        if (j < ((UnitFormat) this.f17794b.get(0)).f17785a) {
            return f17790c.format(j);
        }
        int i = 0;
        while (((UnitFormat) this.f17794b.get(i + 1)).f17785a <= j) {
            i++;
        }
        String format = f17791d.format((double) (((float) j) / ((UnitFormat) this.f17794b.get(i)).f17786b));
        while (format.endsWith(f17793f)) {
            format = format.substring(0, format.length() - 1);
        }
        if (format.endsWith(f17792e)) {
            format = format.substring(0, format.length() - 1);
        }
        return this.f17795g.getString(C3482R.string.cm_number_short_forms_string, new Object[]{format, ((UnitFormat) this.f17794b.get(i)).f17787c});
    }
}
