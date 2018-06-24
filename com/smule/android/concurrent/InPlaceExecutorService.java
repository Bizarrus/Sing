/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.android.concurrent;

import android.support.annotation.NonNull;
import com.smule.android.concurrent.SynchronousFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

public class InPlaceExecutorService
implements ExecutorService {
    private boolean a = false;
    private List<SynchronousFuture<?>> b = new ArrayList();

    private <T> SynchronousFuture<T> a(@NonNull Callable<T> object) throws RejectedExecutionException {
        if (this.a) {
            throw new RejectedExecutionException("Cannot accept new tasks because the ExecutorService is already shutdown");
        }
        object = new SynchronousFuture<T>((Callable<T>)object);
        this.b.add((Object)object);
        return object;
    }

    private void a() {
        for (SynchronousFuture synchronousFuture : this.b) {
            if (!synchronousFuture.isDone() && !synchronousFuture.isCancelled()) continue;
            this.b.remove(synchronousFuture);
        }
    }

    @Override
    public boolean awaitTermination(long l, @NonNull TimeUnit timeUnit) {
        return this.isTerminated();
    }

    @Override
    public void execute(@NonNull Runnable runnable) throws NullPointerException {
        runnable.run();
    }

    @NonNull
    @Override
    public <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> iterator) {
        this.a();
        ArrayList<SynchronousFuture<T>> arrayList = new ArrayList<SynchronousFuture<T>>();
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            arrayList.add(this.a((Callable)iterator.next()));
        }
        iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            ((RunnableFuture)iterator.next()).run();
        }
        return new ArrayList<Future<T>>(arrayList);
    }

    @NonNull
    @Override
    public <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> collection, long l, @NonNull TimeUnit timeUnit) throws InterruptedException {
        throw new InterruptedException("This class does not support timeout invocation");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @NonNull
    @Override
    public <T> T invokeAny(@NonNull Collection<? extends Callable<T>> object) throws ExecutionException {
        if (object.isEmpty()) {
            throw new IllegalArgumentException("Tasks collection is empty");
        }
        Object var2_3 = null;
        Iterator iterator = object.iterator();
        object = var2_3;
        while (iterator.hasNext()) {
            object = (Callable)iterator.next();
            try {
                object = object.call();
            }
            catch (Exception exception) {
                continue;
            }
            return (T)object;
        }
        throw new ExecutionException((Throwable)object);
    }

    @Override
    public <T> T invokeAny(@NonNull Collection<? extends Callable<T>> collection, long l, @NonNull TimeUnit timeUnit) throws InterruptedException {
        throw new InterruptedException("This class does not support timeout invocation");
    }

    @Override
    public boolean isShutdown() {
        return this.a;
    }

    @Override
    public boolean isTerminated() {
        this.a();
        if (this.a && this.b.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void shutdown() {
        this.a = true;
    }

    @NonNull
    @Override
    public List<Runnable> shutdownNow() {
        this.a = true;
        return new ArrayList<Runnable>(this.b);
    }

    @NonNull
    @Override
    public Future<?> submit(@NonNull Runnable runnable) {
        this.a();
        return this.a(Executors.callable(runnable));
    }

    @NonNull
    @Override
    public <T> Future<T> submit(@NonNull Runnable runnable, T t) {
        this.a();
        return this.a(Executors.callable(runnable, t));
    }

    @NonNull
    @Override
    public <T> Future<T> submit(@NonNull Callable<T> callable) {
        this.a();
        return this.a(callable);
    }
}

