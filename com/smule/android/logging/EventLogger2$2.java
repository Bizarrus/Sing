package com.smule.android.logging;

class EventLogger2$2 implements Runnable {
    final /* synthetic */ EventLogger2 f16330a;

    EventLogger2$2(EventLogger2 eventLogger2) {
        this.f16330a = eventLogger2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r5 = this;
        r0 = r5.f16330a;
        r0 = com.smule.android.logging.EventLogger2.c(r0);
        r0 = com.smule.android.network.core.NetworkUtils.m18113a(r0);
        if (r0 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r0 = com.smule.android.network.managers.UserManager.a();
        r0 = r0.g();
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x0023;
    L_0x001b:
        r0 = com.smule.android.logging.EventLogger2.a;
        r1 = "flushEvents: playerId was 0; not flushing";
        com.smule.android.logging.Log.a(r0, r1);
        goto L_0x000c;
    L_0x0023:
        r2 = 0;
        r3 = r5.f16330a;
        monitor-enter(r3);
        r0 = r5.f16330a;	 Catch:{ all -> 0x0035 }
        r0 = com.smule.android.logging.EventLogger2.d(r0);	 Catch:{ all -> 0x0035 }
        r0 = r0.size();	 Catch:{ all -> 0x0035 }
        if (r0 > 0) goto L_0x0038;
    L_0x0033:
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        goto L_0x000c;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        throw r0;
    L_0x0038:
        r1 = 0;
        r0 = r5.f16330a;	 Catch:{ all -> 0x0035 }
        r0 = com.smule.android.logging.EventLogger2.d(r0);	 Catch:{ all -> 0x0035 }
        r4 = r0.iterator();	 Catch:{ all -> 0x0035 }
    L_0x0043:
        r0 = r4.hasNext();	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0056;
    L_0x0049:
        r0 = r4.next();	 Catch:{ all -> 0x0035 }
        r0 = (com.smule.android.logging.EventLogger2$Event) r0;	 Catch:{ all -> 0x0035 }
        r0 = r0.f16373p;	 Catch:{ all -> 0x0035 }
        if (r0 != 0) goto L_0x0073;
    L_0x0053:
        r0 = 1;
    L_0x0054:
        r1 = r0;
        goto L_0x0043;
    L_0x0056:
        if (r1 == 0) goto L_0x0071;
    L_0x0058:
        r0 = r5.f16330a;	 Catch:{ all -> 0x0035 }
        r0 = com.smule.android.logging.EventLogger2.d(r0);	 Catch:{ all -> 0x0035 }
        r1 = r5.f16330a;	 Catch:{ all -> 0x0035 }
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x0035 }
        r2.<init>();	 Catch:{ all -> 0x0035 }
        com.smule.android.logging.EventLogger2.a(r1, r2);	 Catch:{ all -> 0x0035 }
    L_0x0068:
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x000c;
    L_0x006b:
        r1 = r5.f16330a;
        com.smule.android.logging.EventLogger2.b(r1, r0);
        goto L_0x000c;
    L_0x0071:
        r0 = r2;
        goto L_0x0068;
    L_0x0073:
        r0 = r1;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.android.logging.EventLogger2$2.run():void");
    }
}
