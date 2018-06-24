/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 */
package com.smule.chat;

import android.os.Handler;
import android.os.Looper;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.chat.PriorityExecutor;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class CollectionBatcher<ElementType, ResponseType> {
    private Deque<CollectionBatcher<ElementType, ResponseType>> a;
    private boolean b;
    private boolean c;
    private Handler d;
    private int e;
    private int f;
    private String g;
    private int h;
    private int i;

    public CollectionBatcher(int n, int n2) {
        this.e = n;
        this.d = new Handler(Looper.getMainLooper());
        this.a = new ArrayDeque<CollectionBatcher<ElementType, ResponseType>>();
        this.f = n2;
        this.g = this.getClass().getName();
    }

    private void a() {
        if (!this.c) {
            this.c = true;
            PriorityExecutor.a.a(new PriorityExecutor.PriorityTask(this.f){

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                @Override
                public void run() {
                    synchronized (this) {
                        CollectionBatcher.this.b();
                        CollectionBatcher.this.c = false;
                        return;
                    }
                }
            });
        }
    }

    private void a(CollectionBatcher<ElementType, ResponseType> collectionBatcher, CallbackAggregator<ElementType, ResponseType> callbackAggregator, Set<ElementType> set) {
        HashSet<ElementType> hashSet = new HashSet<ElementType>(set);
        hashSet.retainAll(collectionBatcher.a);
        if (!hashSet.isEmpty()) {
            collectionBatcher.b.add(callbackAggregator);
            set.removeAll(hashSet);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b() {
        synchronized (this) {
            if (!this.b) {
                this.b = true;
                this.d.post(new Runnable(){

                    @Override
                    public void run() {
                        MagicNetwork.a(new Runnable(){

                            /*
                             * Enabled aggressive block sorting
                             * Enabled unnecessary exception pruning
                             * Enabled aggressive exception aggregation
                             */
                            @Override
                            public void run() {
                                boolean bl = CollectionBatcher.this.c();
                                synchronized (this) {
                                    CollectionBatcher.this.b = false;
                                    if (bl) {
                                        CollectionBatcher.this.b();
                                    }
                                    return;
                                }
                            }
                        });
                    }

                });
            }
            return;
        }
    }

    private void b(CollectionBatcher<ElementType, ResponseType> collectionBatcher, CallbackAggregator<ElementType, ResponseType> callbackAggregator, Set<ElementType> object) {
        int n = this.e - collectionBatcher.a.size();
        object = object.iterator();
        while (n > 0 && object.hasNext()) {
            Object e = object.next();
            if (n <= 0) continue;
            object.remove();
            collectionBatcher.a.add(e);
            --n;
        }
        collectionBatcher.b.add(callbackAggregator);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(CallbackAggregator<ElementType, ResponseType> callbackAggregator) {
        synchronized (this) {
            HashSet hashSet = new HashSet(callbackAggregator.a);
            Object object = this.a.iterator();
            while (object.hasNext()) {
                this.a((Batch)((Object)object.next()), callbackAggregator, hashSet);
                if (!hashSet.isEmpty()) continue;
                return;
            }
            do {
                block10 : {
                    block9 : {
                        if (hashSet.isEmpty()) {
                            return;
                        }
                        Batch batch = (Batch)((Object)this.a.peekLast());
                        if (batch == null) break block9;
                        object = batch;
                        if (!batch.a()) break block10;
                    }
                    object = new Batch();
                    this.a.add((CollectionBatcher<ElementType, ResponseType>)object);
                }
                this.b((Batch)object, callbackAggregator, hashSet);
                if (!((Batch)object).a() || object != this.a.peekFirst()) continue;
                this.b();
            } while (true);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean c() {
        Batch batch;
        synchronized (this) {
            batch = (Batch)((Object)this.a.peekFirst());
            if (batch == null) {
                return false;
            }
        }
        this.h += batch.a.size();
        ++this.i;
        Log.a(this.g, this + " batchsize=" + batch.a.size() + " items=" + this.h + " count=" + this.i);
        ResponseType ResponseType = this.b(batch.a);
        synchronized (this) {
            for (final CallbackAggregator callbackAggregator : batch.b) {
                callbackAggregator.a(ResponseType);
                callbackAggregator.a.removeAll(batch.a);
                if (!callbackAggregator.a.isEmpty()) continue;
                this.d.post(new Runnable(){

                    @Override
                    public void run() {
                        callbackAggregator.a();
                    }
                });
            }
            this.a.removeFirst();
            batch = (Batch)((Object)this.a.peekFirst());
            if (batch == null) return false;
            if (!batch.a()) return false;
            return true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(final CallbackAggregator<ElementType, ResponseType> callbackAggregator) {
        if (callbackAggregator.a.isEmpty()) {
            this.d.post(new Runnable(){

                @Override
                public void run() {
                    callbackAggregator.a(null);
                    callbackAggregator.a();
                }
            });
            return;
        }
        synchronized (this) {
            this.b(callbackAggregator);
            this.a();
            return;
        }
    }

    abstract ResponseType b(Set<ElementType> var1);

    private class Batch {
        public LinkedHashSet<ElementType> a;
        public HashSet<CallbackAggregator<ElementType, ResponseType>> b;

        public Batch() {
            this.a = new LinkedHashSet();
            this.b = new HashSet();
        }

        private boolean a() {
            if (this.a.size() >= CollectionBatcher.this.e) {
                return true;
            }
            return false;
        }
    }

    public static abstract class CallbackAggregator<ElementType, ResponseType> {
        protected HashSet<ElementType> a;

        public CallbackAggregator(HashSet<ElementType> hashSet) {
            this.a = hashSet;
        }

        public abstract void a();

        public abstract void a(ResponseType var1);
    }

}

