package com.smule.chat;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.concurrent.Executor;

class PriorityExecutor implements Executor {
    public static final PriorityExecutor f18318a = new PriorityExecutor();
    private final Object f18319b = new Object();
    private PriorityDeque f18320c = new PriorityDeque();
    private Runnable f18321d;

    public interface Task extends Runnable {
        int mo6313a();
    }

    public static abstract class PriorityTask implements Task {
        private final int f18148a;

        public PriorityTask(int i) {
            this.f18148a = i;
        }

        public int mo6313a() {
            return this.f18148a;
        }
    }

    private static class PriorityDeque {
        private final ArrayList<Task> f18317a = new ArrayList();

        public void m19665a(Task task) {
            synchronized (this.f18317a) {
                this.f18317a.add(task);
            }
        }

        public Task m19664a() {
            Task task;
            synchronized (this.f18317a) {
                task = null;
                int i = Integer.MIN_VALUE;
                int i2 = -1;
                int i3 = 0;
                while (i3 < this.f18317a.size()) {
                    Task task2;
                    int i4;
                    Task task3 = (Task) this.f18317a.get(i3);
                    int a = task3.mo6313a();
                    if (a > i) {
                        i2 = a;
                        task2 = task3;
                        i4 = i3;
                    } else {
                        i4 = i2;
                        task2 = task;
                        i2 = i;
                    }
                    i3++;
                    i = i2;
                    task = task2;
                    i2 = i4;
                }
                if (task != null) {
                    this.f18317a.remove(i2);
                }
            }
            return task;
        }
    }

    public void execute(@NonNull final Runnable runnable) {
        m19668a(new PriorityTask(this, 0) {
            final /* synthetic */ PriorityExecutor f18312b;

            public void run() {
                runnable.run();
            }
        });
    }

    public void m19668a(final Task task) {
        synchronized (this.f18319b) {
            this.f18320c.m19665a(new Task(this) {
                final /* synthetic */ PriorityExecutor f18314b;

                public int mo6313a() {
                    return task.mo6313a();
                }

                public void run() {
                    try {
                        task.run();
                    } finally {
                        this.f18314b.m19666a();
                    }
                }
            });
            if (this.f18321d == null) {
                m19666a();
            }
        }
    }

    private void m19666a() {
        synchronized (this.f18319b) {
            Runnable a = this.f18320c.m19664a();
            this.f18321d = a;
            if (a != null) {
                AsyncTask.THREAD_POOL_EXECUTOR.execute(this.f18321d);
            }
        }
    }
}
