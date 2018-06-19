package com.smule.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class ReferenceMonitor$ReferenceMonitorInfo {
    final /* synthetic */ ReferenceMonitor f17828a;
    private String f17829b;
    private Date f17830c;
    private int f17831d;
    private int f17832e;
    private final SimpleDateFormat f17833f = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);

    public ReferenceMonitor$ReferenceMonitorInfo(ReferenceMonitor referenceMonitor, Object obj, int i) {
        this.f17828a = referenceMonitor;
        this.f17829b = obj.getClass().getSimpleName();
        this.f17831d = (ReferenceMonitor.a(referenceMonitor).containsKey(this.f17829b) ? ((Integer) ReferenceMonitor.a(referenceMonitor).get(this.f17829b)).intValue() : 0) + 1;
        ReferenceMonitor.a(referenceMonitor).put(this.f17829b, Integer.valueOf(this.f17831d));
        this.f17832e = i;
        this.f17830c = new Date();
    }

    public String toString() {
        return this.f17829b + " instance: " + this.f17831d + " created: " + this.f17833f.format(this.f17830c);
    }
}
