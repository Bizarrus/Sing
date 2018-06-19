package com.smule.android.utils;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import com.smule.android.logging.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OperationLoader {
    private static final String f6950a = OperationLoader.class.getSimpleName();
    private static final List<OperationStatus> f6951g = new ArrayList(0);
    private final Map<String, Operation> f6952b = new LinkedHashMap();
    private volatile boolean f6953c;
    private Handler f6954d;
    private final List<Operation> f6955e = new ArrayList(10);
    private final Object f6956f = new Object();
    private final Callback f6957h = new C19454(this);

    public static class Operation {
        private static final Collection<String> f6930a = new LinkedList();
        public String f6931e;
        public Collection<String> f6932f;
        @Deprecated
        public final Operation f6933g;
        boolean f6934h;
        int f6935i;
        volatile boolean f6936j;
        volatile boolean f6937k;
        volatile long f6938l;
        OperationLoader f6939m;

        public static class OperationStatus {
            public String f6948a;
            public boolean f6949b;

            public OperationStatus(String str, boolean z) {
                this.f6948a = str;
                this.f6949b = z;
            }
        }

        @Deprecated
        public Operation() {
            this.f6936j = false;
            this.f6937k = false;
            this.f6938l = 0;
            this.f6933g = this;
        }

        public Operation(String str, Collection<String> collection) {
            this(str, collection, 0);
        }

        public Operation(String str, Collection<String> collection, int i) {
            this.f6936j = false;
            this.f6937k = false;
            this.f6938l = 0;
            this.f6931e = str;
            if (collection == null) {
                collection = f6930a;
            }
            this.f6932f = collection;
            this.f6935i = i;
            this.f6933g = this;
        }

        @Deprecated
        public void mo4042a(OperationLoader operationLoader) {
            m8377a(true);
        }

        protected void mo4041a(@NonNull List<OperationStatus> list) {
            mo4042a(this.f6939m);
        }

        protected void m8377a(boolean z) {
            this.f6934h = z;
            this.f6937k = true;
            this.f6936j = false;
            this.f6938l = System.currentTimeMillis();
            this.f6939m.m8402d(this);
        }

        void m8374a() {
            this.f6936j = false;
            this.f6937k = false;
            this.f6938l = 0;
        }
    }

    class C19443 extends Operation {
        final /* synthetic */ boolean f6945a;
        final /* synthetic */ String f6946b;

        protected void mo4041a(@NonNull List<OperationStatus> list) {
            NotificationCenter.a().a(this.f6946b, new 1(this));
        }
    }

    class C19454 implements Callback {
        final /* synthetic */ OperationLoader f6947a;

        C19454(OperationLoader operationLoader) {
            this.f6947a = operationLoader;
        }

        public boolean handleMessage(Message message) {
            Operation a;
            Handler target;
            switch (message.what) {
                case -1096149566:
                    a = this.f6947a.m8386b();
                    break;
                case 14593280:
                    target = message.getTarget();
                    if (target == null || !this.f6947a.f6953c) {
                        return true;
                    }
                    target.sendEmptyMessage(410464667);
                    return true;
                case 410464667:
                    a = this.f6947a.m8386b();
                    break;
                case 584554521:
                    a = this.f6947a.m8386b();
                    break;
                case 685310705:
                    this.f6947a.f6953c = true;
                    a = this.f6947a.m8386b();
                    break;
                default:
                    return false;
            }
            if (a == null) {
                return true;
            }
            a.f6936j = true;
            a.f6937k = false;
            a.mo4041a(this.f6947a.m8388e(a));
            target = message.getTarget();
            if (target == null) {
                return true;
            }
            target.removeMessages(14593280);
            if (this.f6947a.f6953c) {
                target.sendEmptyMessageDelayed(14593280, 500);
            }
            target.sendEmptyMessageDelayed(410464667, 100);
            return true;
        }
    }

    public OperationLoader m8394a(String str, Collection<String> collection, Runnable runnable) {
        final Runnable runnable2 = runnable;
        return m8392a(new Operation(this, str, collection, 0) {
            final /* synthetic */ OperationLoader f6941b;

            protected void mo4041a(@NonNull List<OperationStatus> list) {
                runnable2.run();
                m8377a(true);
            }
        });
    }

    @Deprecated
    public OperationLoader m8393a(String str, Collection<String> collection, Operation operation) {
        operation.f6931e = str;
        operation.f6932f = collection;
        return m8392a(operation);
    }

    public OperationLoader m8392a(Operation operation) {
        operation.f6939m = this;
        operation.m8374a();
        m8384a(operation.f6931e, operation);
        return this;
    }

    public Operation m8391a(String str) {
        Operation operation;
        synchronized (this.f6956f) {
            operation = (Operation) this.f6952b.remove(str);
        }
        m8390g(operation);
        return operation;
    }

    public boolean m8399b(String str) {
        synchronized (this.f6956f) {
            Operation operation = (Operation) this.f6952b.get(str);
            if (operation == null) {
                return false;
            } else if (operation.f6936j) {
                return false;
            } else {
                operation.f6937k = false;
                operation.f6938l = 0;
                m8395a();
                return true;
            }
        }
    }

    public boolean m8401c(String str) {
        boolean z;
        synchronized (this.f6956f) {
            Operation operation = (Operation) this.f6952b.get(str);
            z = operation != null && operation.f6937k;
        }
        return z;
    }

    public boolean m8397a(Collection<String> collection) {
        for (String c : collection) {
            if (!m8401c(c)) {
                return false;
            }
        }
        return true;
    }

    public void m8396a(Collection<String> collection, final Runnable runnable) {
        if (m8397a((Collection) collection)) {
            runnable.run();
            return;
        }
        final String str = runnable.getClass().toString() + "@" + System.identityHashCode(runnable);
        m8393a(str, (Collection) collection, new Operation(this) {
            final /* synthetic */ OperationLoader f6944c;

            public void mo4042a(OperationLoader operationLoader) {
                this.f6944c.m8391a(str);
                operationLoader.m8400c((Operation) this);
                runnable.run();
            }
        }).m8395a();
    }

    private void m8384a(String str, Operation operation) {
        synchronized (this.f6956f) {
            this.f6952b.put(str, operation);
        }
        m8389f(operation);
    }

    public synchronized void m8395a() {
        if (this.f6954d == null) {
            HandlerThread handlerThread = new HandlerThread(f6950a);
            handlerThread.start();
            this.f6954d = new Handler(handlerThread.getLooper(), this.f6957h);
        }
        this.f6954d.sendEmptyMessage(410464667);
    }

    private Operation m8386b() {
        boolean z;
        Operation operation;
        synchronized (this.f6956f) {
            this.f6955e.clear();
            Object obj = null;
            Object obj2 = null;
            z = false;
            for (Operation operation2 : this.f6952b.values()) {
                Operation operation22;
                if (operation22.f6936j) {
                    obj2 = 1;
                } else {
                    Object obj3;
                    Object obj4;
                    if (!operation22.f6937k) {
                        z = true;
                    }
                    if (operation22.f6932f != null) {
                        obj3 = 1;
                        for (String str : operation22.f6932f) {
                            operation = (Operation) this.f6952b.get(str);
                            if (operation == null) {
                                obj = 1;
                            }
                            if (operation == null || !operation.f6937k) {
                                obj4 = null;
                            } else {
                                obj4 = obj3;
                            }
                            obj3 = obj4;
                        }
                        obj4 = obj;
                    } else {
                        int i = 1;
                        obj4 = obj;
                    }
                    if (obj3 == null) {
                        obj = obj4;
                    } else {
                        if (!operation22.f6937k || m8398b(operation22)) {
                            this.f6955e.add(operation22);
                        }
                        obj = obj4;
                    }
                }
            }
            if (this.f6955e.isEmpty()) {
                operation = null;
            } else {
                i = 1;
                operation = (Operation) this.f6955e.get(0);
                while (i < this.f6955e.size()) {
                    operation22 = (Operation) this.f6955e.get(i);
                    if (operation22.f6935i <= operation.f6935i) {
                        operation22 = operation;
                    }
                    i++;
                    operation = operation22;
                }
            }
        }
        if (operation == null && z && r8 == null && obj == null) {
            Log.m7776e(f6950a, "Problem choosing next operation to execute. Is there a dependency cycle?");
        }
        this.f6953c = z;
        return operation;
    }

    public boolean m8398b(Operation operation) {
        if (operation.f6932f != null) {
            for (String str : operation.f6932f) {
                Operation operation2 = (Operation) this.f6952b.get(str);
                if (m8398b(operation2)) {
                    return false;
                }
                if (operation2.f6938l > operation.f6938l) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<OperationStatus> m8388e(Operation operation) {
        if (operation.f6932f == null || operation.f6932f.isEmpty()) {
            return f6951g;
        }
        List<OperationStatus> arrayList = new ArrayList();
        synchronized (this.f6956f) {
            for (String str : operation.f6932f) {
                arrayList.add(new OperationStatus(str, ((Operation) this.f6952b.get(str)).f6934h));
            }
        }
        return arrayList;
    }

    @Deprecated
    public void m8400c(Operation operation) {
        operation.m8377a(true);
    }

    private void m8389f(Operation operation) {
        if (this.f6954d != null) {
            this.f6954d.sendMessage(m8381a(685310705, operation));
        }
    }

    private void m8390g(Operation operation) {
        if (this.f6954d != null) {
            this.f6954d.sendMessage(m8381a(-1096149566, operation));
        }
    }

    void m8402d(Operation operation) {
        this.f6954d.sendMessage(m8381a(584554521, operation));
    }

    private Message m8381a(int i, Operation operation) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = operation;
        return obtain;
    }
}
