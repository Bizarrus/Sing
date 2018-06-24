/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.annotation.NonNull
 */
package com.smule.chat;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.smule.chat.PriorityExecutor;
import java.util.ArrayList;
import java.util.concurrent.Executor;

class PriorityExecutor
implements Executor {
    public static final PriorityExecutor a = new PriorityExecutor();
    private final Object b = new Object();
    private PriorityDeque c = new PriorityDeque();
    private Runnable d;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a() {
        Object object = this.b;
        synchronized (object) {
            Task task = this.c.a();
            this.d = task;
            if (task != null) {
                AsyncTask.THREAD_POOL_EXECUTOR.execute(this.d);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(final Task task) {
        Object object = this.b;
        synchronized (object) {
            this.c.a(new Task(){

                @Override
                public int a() {
                    return task.a();
                }

                @Override
                public void run() {
                    try {
                        task.run();
                        return;
                    }
                    finally {
                        PriorityExecutor.this.a();
                    }
                }
            });
            if (this.d == null) {
                this.a();
            }
            return;
        }
    }

    @Override
    public void execute(final @NonNull Runnable runnable) {
        this.a(new PriorityTask(0){

            @Override
            public void run() {
                runnable.run();
            }
        });
    }

    private static class PriorityDeque {
        private final ArrayList<Task> a = new ArrayList();

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public Task a() {
            ArrayList<Task> arrayList = this.a;
            synchronized (arrayList) {
                Task task = null;
                int n = Integer.MIN_VALUE;
                int n2 = -1;
                int n3 = 0;
                do {
                    int n4;
                    block6 : {
                        block5 : {
                            block4 : {
                                if (n3 >= this.a.size()) break block4;
                                Task task2 = this.a.get(n3);
                                n4 = task2.a();
                                if (n4 <= n) break block5;
                                n2 = n4;
                                task = task2;
                                n = n3;
                                break block6;
                            }
                            if (task != null) {
                                this.a.remove(n2);
                            }
                            return task;
                        }
                        n4 = n2;
                        n2 = n;
                        n = n4;
                    }
                    ++n3;
                    n4 = n2;
                    n2 = n;
                    n = n4;
                } while (true);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void a(Task task) {
            ArrayList<Task> arrayList = this.a;
            synchronized (arrayList) {
                this.a.add(task);
                return;
            }
        }
    }

    public static abstract class PriorityTask
    implements Task {
        private final int a;

        public PriorityTask(int n) {
            this.a = n;
        }

        @Override
        public int a() {
            return this.a;
        }
    }

    public static interface Task
    extends Runnable {
        public int a();
    }

}

