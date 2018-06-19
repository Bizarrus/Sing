package com.smule.android.utils;

class ReferenceMonitor$1 implements Runnable {
    final /* synthetic */ ReferenceMonitor f17827a;

    ReferenceMonitor$1(ReferenceMonitor referenceMonitor) {
        this.f17827a = referenceMonitor;
    }

    public void run() {
        ReferenceMonitor.b(this.f17827a);
    }
}
