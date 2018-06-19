package com.smule.chat;

import android.os.Handler;
import android.os.Looper;
import com.smule.android.network.core.MagicNetwork;
import com.smule.chat.PriorityExecutor.PriorityTask;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class CollectionBatcher<ElementType, ResponseType> {
    private Deque<Batch> f17882a = new ArrayDeque();
    private boolean f17883b;
    private boolean f17884c;
    private Handler f17885d = new Handler(Looper.getMainLooper());
    private int f17886e;
    private int f17887f;
    private String f17888g;
    private int f17889h;
    private int f17890i;

    public static abstract class CallbackAggregator<ElementType, ResponseType> {
        protected HashSet<ElementType> f17898a;

        public abstract void mo6308a();

        public abstract void mo6309a(ResponseType responseType);

        public CallbackAggregator(HashSet<ElementType> hashSet) {
            this.f17898a = hashSet;
        }
    }

    class C37173 implements Runnable {
        final /* synthetic */ CollectionBatcher f18151a;

        class C37161 implements Runnable {
            final /* synthetic */ C37173 f18150a;

            C37161(C37173 c37173) {
                this.f18150a = c37173;
            }

            public void run() {
                boolean c = this.f18150a.f18151a.m19093c();
                synchronized (this) {
                    this.f18150a.f18151a.f17883b = false;
                    if (c) {
                        this.f18150a.f18151a.m19088b();
                    }
                }
            }
        }

        C37173(CollectionBatcher collectionBatcher) {
            this.f18151a = collectionBatcher;
        }

        public void run() {
            MagicNetwork.a(new C37161(this));
        }
    }

    private class Batch {
        public LinkedHashSet<ElementType> f18154a = new LinkedHashSet();
        public HashSet<CallbackAggregator<ElementType, ResponseType>> f18155b = new HashSet();
        final /* synthetic */ CollectionBatcher f18156c;

        public Batch(CollectionBatcher collectionBatcher) {
            this.f18156c = collectionBatcher;
        }

        private boolean m19419a() {
            return this.f18154a.size() >= this.f18156c.f17886e;
        }
    }

    abstract ResponseType mo6306b(Set<ElementType> set);

    public CollectionBatcher(int i, int i2) {
        this.f17886e = i;
        this.f17887f = i2;
        this.f17888g = getClass().getName();
    }

    public void m19095a(final CallbackAggregator<ElementType, ResponseType> callbackAggregator) {
        if (callbackAggregator.f17898a.isEmpty()) {
            this.f17885d.post(new Runnable(this) {
                final /* synthetic */ CollectionBatcher f18147b;

                public void run() {
                    callbackAggregator.mo6309a(null);
                    callbackAggregator.mo6308a();
                }
            });
            return;
        }
        synchronized (this) {
            m19090b((CallbackAggregator) callbackAggregator);
            m19085a();
        }
    }

    private void m19085a() {
        if (!this.f17884c) {
            this.f17884c = true;
            PriorityExecutor.f18318a.m19668a(new PriorityTask(this, this.f17887f) {
                final /* synthetic */ CollectionBatcher f18149a;

                public void run() {
                    synchronized (this) {
                        this.f18149a.m19088b();
                        this.f18149a.f17884c = false;
                    }
                }
            });
        }
    }

    private void m19090b(CallbackAggregator<ElementType, ResponseType> callbackAggregator) {
        synchronized (this) {
            Batch batch;
            Set hashSet = new HashSet(callbackAggregator.f17898a);
            for (Batch batch2 : this.f17882a) {
                m19086a(batch2, callbackAggregator, hashSet);
                if (hashSet.isEmpty()) {
                    return;
                }
            }
            while (!hashSet.isEmpty()) {
                batch2 = (Batch) this.f17882a.peekLast();
                if (batch2 == null || batch2.m19419a()) {
                    batch2 = new Batch(this);
                    this.f17882a.add(batch2);
                }
                m19089b(batch2, callbackAggregator, hashSet);
                if (batch2.m19419a() && batch2 == this.f17882a.peekFirst()) {
                    m19088b();
                }
            }
        }
    }

    private void m19086a(Batch batch, CallbackAggregator<ElementType, ResponseType> callbackAggregator, Set<ElementType> set) {
        Collection hashSet = new HashSet(set);
        hashSet.retainAll(batch.f18154a);
        if (!hashSet.isEmpty()) {
            batch.f18155b.add(callbackAggregator);
            set.removeAll(hashSet);
        }
    }

    private void m19089b(Batch batch, CallbackAggregator<ElementType, ResponseType> callbackAggregator, Set<ElementType> set) {
        int size = this.f17886e - batch.f18154a.size();
        Iterator it = set.iterator();
        while (size > 0 && it.hasNext()) {
            Object next = it.next();
            if (size > 0) {
                it.remove();
                batch.f18154a.add(next);
                size--;
            }
        }
        batch.f18155b.add(callbackAggregator);
    }

    private void m19088b() {
        synchronized (this) {
            if (!this.f17883b) {
                this.f17883b = true;
                this.f17885d.post(new C37173(this));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m19093c() {
        /*
        r7 = this;
        r2 = 0;
        monitor-enter(r7);
        r0 = r7.f17882a;	 Catch:{ all -> 0x0094 }
        r0 = r0.peekFirst();	 Catch:{ all -> 0x0094 }
        r0 = (com.smule.chat.CollectionBatcher.Batch) r0;	 Catch:{ all -> 0x0094 }
        if (r0 != 0) goto L_0x000f;
    L_0x000c:
        monitor-exit(r7);	 Catch:{ all -> 0x0094 }
        r0 = r2;
    L_0x000e:
        return r0;
    L_0x000f:
        monitor-exit(r7);	 Catch:{ all -> 0x0094 }
        r1 = r7.f17889h;
        r3 = r0.f18154a;
        r3 = r3.size();
        r1 = r1 + r3;
        r7.f17889h = r1;
        r1 = r7.f17890i;
        r1 = r1 + 1;
        r7.f17890i = r1;
        r1 = r7.f17888g;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3 = r3.append(r7);
        r4 = " batchsize=";
        r3 = r3.append(r4);
        r4 = r0.f18154a;
        r4 = r4.size();
        r3 = r3.append(r4);
        r4 = " items=";
        r3 = r3.append(r4);
        r4 = r7.f17889h;
        r3 = r3.append(r4);
        r4 = " count=";
        r3 = r3.append(r4);
        r4 = r7.f17890i;
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.smule.android.logging.Log.a(r1, r3);
        r1 = r0.f18154a;
        r3 = r7.mo6306b(r1);
        monitor-enter(r7);
        r1 = r0.f18155b;	 Catch:{ all -> 0x0091 }
        r4 = r1.iterator();	 Catch:{ all -> 0x0091 }
    L_0x0068:
        r1 = r4.hasNext();	 Catch:{ all -> 0x0091 }
        if (r1 == 0) goto L_0x0097;
    L_0x006e:
        r1 = r4.next();	 Catch:{ all -> 0x0091 }
        r1 = (com.smule.chat.CollectionBatcher.CallbackAggregator) r1;	 Catch:{ all -> 0x0091 }
        r1.mo6309a(r3);	 Catch:{ all -> 0x0091 }
        r5 = r1.f17898a;	 Catch:{ all -> 0x0091 }
        r6 = r0.f18154a;	 Catch:{ all -> 0x0091 }
        r5.removeAll(r6);	 Catch:{ all -> 0x0091 }
        r5 = r1.f17898a;	 Catch:{ all -> 0x0091 }
        r5 = r5.isEmpty();	 Catch:{ all -> 0x0091 }
        if (r5 == 0) goto L_0x0068;
    L_0x0086:
        r5 = r7.f17885d;	 Catch:{ all -> 0x0091 }
        r6 = new com.smule.chat.CollectionBatcher$4;	 Catch:{ all -> 0x0091 }
        r6.<init>(r7, r1);	 Catch:{ all -> 0x0091 }
        r5.post(r6);	 Catch:{ all -> 0x0091 }
        goto L_0x0068;
    L_0x0091:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0091 }
        throw r0;
    L_0x0094:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0094 }
        throw r0;
    L_0x0097:
        r0 = r7.f17882a;	 Catch:{ all -> 0x0091 }
        r0.removeFirst();	 Catch:{ all -> 0x0091 }
        r0 = r7.f17882a;	 Catch:{ all -> 0x0091 }
        r0 = r0.peekFirst();	 Catch:{ all -> 0x0091 }
        r0 = (com.smule.chat.CollectionBatcher.Batch) r0;	 Catch:{ all -> 0x0091 }
        if (r0 == 0) goto L_0x00b0;
    L_0x00a6:
        r0 = r0.m19419a();	 Catch:{ all -> 0x0091 }
        if (r0 == 0) goto L_0x00b0;
    L_0x00ac:
        r0 = 1;
    L_0x00ad:
        monitor-exit(r7);	 Catch:{ all -> 0x0091 }
        goto L_0x000e;
    L_0x00b0:
        r0 = r2;
        goto L_0x00ad;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.chat.CollectionBatcher.c():boolean");
    }
}
