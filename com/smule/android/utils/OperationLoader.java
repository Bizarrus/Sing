/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Handler$Callback
 *  android.os.HandlerThread
 *  android.os.Looper
 *  android.os.Message
 *  android.support.annotation.NonNull
 */
package com.smule.android.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.smule.android.logging.Log;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

public class OperationLoader {
    private static final String a = OperationLoader.class.getSimpleName();
    private static final List<Operation.OperationStatus> g = new ArrayList<Operation.OperationStatus>(0);
    private final Map<String, Operation> b = new LinkedHashMap<String, Operation>();
    private volatile boolean c;
    private Handler d;
    private final List<Operation> e = new ArrayList<Operation>(10);
    private final Object f = new Object();
    private final Handler.Callback h;

    public OperationLoader() {
        this.h = new Handler.Callback(){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public boolean handleMessage(Message var1_1) {
                var3_2 = true;
                switch (var1_1.what) {
                    default: {
                        return false;
                    }
                    case 685310705: {
                        OperationLoader.a(OperationLoader.this, true);
                        var4_4 = OperationLoader.a(OperationLoader.this);
                        ** GOTO lbl17
                    }
                    case 584554521: {
                        var4_4 = OperationLoader.a(OperationLoader.this);
                        ** GOTO lbl17
                    }
                    case -1096149566: {
                        var4_4 = OperationLoader.a(OperationLoader.this);
                        ** GOTO lbl17
                    }
                    case 410464667: {
                        var4_4 = OperationLoader.a(OperationLoader.this);
lbl17: // 4 sources:
                        var2_3 = var3_2;
                        if (var4_4 == null) return var2_3;
                        var4_4.i = true;
                        var4_4.j = false;
                        var4_4.a(OperationLoader.a(OperationLoader.this, var4_4));
                        var1_1 = var1_1.getTarget();
                        var2_3 = var3_2;
                        if (var1_1 == null) return var2_3;
                        var1_1.removeMessages(14593280);
                        if (OperationLoader.b(OperationLoader.this)) {
                            var1_1.sendEmptyMessageDelayed(14593280, 500);
                        }
                        var1_1.sendEmptyMessageDelayed(410464667, 100);
                        return true;
                    }
                    case 14593280: 
                }
                var1_1 = var1_1.getTarget();
                var2_3 = var3_2;
                if (var1_1 == null) return var2_3;
                var2_3 = var3_2;
                if (OperationLoader.b(OperationLoader.this) == false) return var2_3;
                var1_1.sendEmptyMessage(410464667);
                return true;
            }
        };
    }

    private Message a(int n, Operation operation) {
        Message message = Message.obtain();
        message.what = n;
        message.obj = operation;
        return message;
    }

    static /* synthetic */ Operation a(OperationLoader operationLoader) {
        return operationLoader.b();
    }

    static /* synthetic */ List a(OperationLoader operationLoader, Operation operation) {
        return operationLoader.e(operation);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(String string2, Operation operation) {
        Object object = this.f;
        synchronized (object) {
            this.b.put(string2, operation);
        }
        this.f(operation);
    }

    static /* synthetic */ boolean a(OperationLoader operationLoader, boolean bl) {
        operationLoader.c = bl;
        return bl;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private Operation b() {
        var8_1 = this.f;
        // MONITORENTER : var8_1
        this.e.clear();
        var6_2 = this.b.values().iterator();
        var1_3 = 0;
        var3_4 = false;
        var5_5 = false;
        do {
            block18 : {
                block16 : {
                    block17 : {
                        block15 : {
                            block14 : {
                                if (!var6_2.hasNext()) break block14;
                                var7_8 = var6_2.next();
                                if (var7_8.i) {
                                    var3_4 = true;
                                    continue;
                                }
                                if (!var7_8.j) {
                                    var5_5 = true;
                                }
                                if (var7_8.e == null) break block15;
                                var9_9 = var7_8.e.iterator();
                                var4_7 = 1;
                                var2_6 = var1_3;
                                var1_3 = var4_7;
                                break block16;
                            }
                            if (!this.e.isEmpty()) {
                                break;
                            }
                            break block17;
                        }
                        var4_7 = 1;
                        break block18;
                    }
                    var7_8 = null;
                    ** GOTO lbl-1000
                }
                while (var9_9.hasNext()) {
                    var10_10 = var9_9.next();
                    if ((var10_10 = this.b.get(var10_10)) == null) {
                        var2_6 = 1;
                    }
                    if (var10_10 != null && var10_10.j) continue;
                    var1_3 = 0;
                }
                var4_7 = var1_3;
                var1_3 = var2_6;
            }
            if (var4_7 == 0 || var7_8.j && !this.b((Operation)var7_8)) continue;
            this.e.add((Operation)var7_8);
        } while (true);
        var6_2 = this.e.get(0);
        var2_6 = 1;
        do {
            var7_8 = var6_2;
            if (var2_6 < this.e.size()) {
                var7_8 = this.e.get(var2_6);
                if (var7_8.h > var6_2.h) {
                    var6_2 = var7_8;
                }
            } else lbl-1000: // 2 sources:
            {
                // MONITOREXIT : var8_1
                if (var7_8 == null && var5_5 && !var3_4 && var1_3 == 0) {
                    Log.e(OperationLoader.a, "Problem choosing next operation to execute. Is there a dependency cycle?");
                }
                this.c = var5_5;
                return var7_8;
            }
            ++var2_6;
        } while (true);
    }

    static /* synthetic */ boolean b(OperationLoader operationLoader) {
        return operationLoader.c;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private List<Operation.OperationStatus> e(Operation object) {
        if (object.e == null || object.e.isEmpty()) {
            return g;
        }
        ArrayList<Operation.OperationStatus> arrayList = new ArrayList<Operation.OperationStatus>();
        Object object2 = this.f;
        synchronized (object2) {
            object = object.e.iterator();
            while (object.hasNext()) {
                String string2 = (String)object.next();
                arrayList.add(new Operation.OperationStatus(string2, this.b.get((Object)string2).g));
            }
            return arrayList;
        }
    }

    private void f(Operation operation) {
        if (this.d != null) {
            this.d.sendMessage(this.a(685310705, operation));
        }
    }

    private void g(Operation operation) {
        if (this.d != null) {
            this.d.sendMessage(this.a(-1096149566, operation));
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Operation a(String object) {
        Object object2 = this.f;
        synchronized (object2) {
            object = this.b.remove(object);
        }
        this.g((Operation)object);
        return object;
    }

    public OperationLoader a(Operation operation) {
        operation.l = this;
        operation.a();
        this.a(operation.d, operation);
        return this;
    }

    @Deprecated
    public OperationLoader a(String string2, Collection<String> collection, Operation operation) {
        operation.d = string2;
        operation.e = collection;
        return this.a(operation);
    }

    public OperationLoader a(String string2, Collection<String> collection, final Runnable runnable) {
        return this.a(new Operation(string2, collection, 0){

            @Override
            protected void a(@NonNull List<Operation.OperationStatus> list) {
                runnable.run();
                this.a(true);
            }
        });
    }

    public void a() {
        synchronized (this) {
            if (this.d == null) {
                HandlerThread handlerThread = new HandlerThread(a);
                handlerThread.start();
                this.d = new Handler(handlerThread.getLooper(), this.h);
            }
            this.d.sendEmptyMessage(410464667);
            return;
        }
    }

    public void a(Collection<String> collection, final Runnable runnable) {
        if (this.a(collection)) {
            runnable.run();
            return;
        }
        final String string2 = runnable.getClass().toString() + "@" + System.identityHashCode(runnable);
        this.a(string2, collection, new Operation(){

            @Override
            public void a(OperationLoader operationLoader) {
                OperationLoader.this.a(string2);
                operationLoader.c(this);
                runnable.run();
            }
        }).a();
    }

    public boolean a(Collection<String> object) {
        object = object.iterator();
        while (object.hasNext()) {
            if (this.c((String)object.next())) continue;
            return false;
        }
        return true;
    }

    public boolean b(Operation operation) {
        if (operation.e != null) {
            for (String string2 : operation.e) {
                Operation object = this.b.get(string2);
                if (this.b(object)) {
                    return false;
                }
                if (object.k <= operation.k) continue;
                return true;
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean b(String object) {
        Object object2 = this.f;
        synchronized (object2) {
            object = this.b.get(object);
            if (object == null) {
                return false;
            }
            if (object.i) {
                return false;
            }
            object.j = false;
            object.k = 0;
        }
        this.a();
        return true;
    }

    @Deprecated
    public void c(Operation operation) {
        operation.a(true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean c(String object) {
        Object object2 = this.f;
        synchronized (object2) {
            object = this.b.get(object);
            if (object == null) return false;
            if (!object.j) return false;
            return true;
        }
    }

    void d(Operation operation) {
        this.d.sendMessage(this.a(584554521, operation));
    }

    public static class Operation {
        private static final Collection<String> a = new LinkedList<String>();
        public String d;
        public Collection<String> e;
        @Deprecated
        public final Operation f;
        boolean g;
        int h;
        volatile boolean i = false;
        volatile boolean j = false;
        volatile long k = 0;
        OperationLoader l;

        @Deprecated
        public Operation() {
            this.f = this;
        }

        public Operation(String string2, Collection<String> collection) {
            this(string2, collection, 0);
        }

        /*
         * Enabled aggressive block sorting
         */
        public Operation(String string2, Collection<String> collection, int n) {
            this.d = string2;
            if (collection == null) {
                collection = a;
            }
            this.e = collection;
            this.h = n;
            this.f = this;
        }

        void a() {
            this.i = false;
            this.j = false;
            this.k = 0;
        }

        @Deprecated
        public void a(OperationLoader operationLoader) {
            this.a(true);
        }

        protected void a(@NonNull List<OperationStatus> list) {
            this.a(this.l);
        }

        protected void a(boolean bl) {
            this.g = bl;
            this.j = true;
            this.i = false;
            this.k = System.currentTimeMillis();
            this.l.d(this);
        }

        public static class OperationStatus {
            public String a;
            public boolean b;

            public OperationStatus(String string2, boolean bl) {
                this.a = string2;
                this.b = bl;
            }
        }

    }

}

