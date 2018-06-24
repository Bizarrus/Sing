/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.android.concurrent;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SynchronousFuture<T>
implements RunnableFuture<T> {
    @Nullable
    private T a;
    @Nullable
    private Throwable b;
    @NonNull
    private final Callable<T> c;
    private boolean d = false;
    private boolean e = false;

    public SynchronousFuture(@NonNull Callable<T> callable) {
        this.c = callable;
    }

    @Override
    public boolean cancel(boolean bl) {
        this.e = true;
        if (!this.d) {
            return true;
        }
        return false;
    }

    @Override
    public T get() throws ExecutionException, InterruptedException {
        if (!this.d) {
            if (this.e) {
                throw new InterruptedException("Operation cancelled");
            }
            this.run();
        }
        if (this.b != null) {
            throw new ExecutionException(this.b);
        }
        return this.a;
    }

    @Override
    public T get(long l, @NonNull TimeUnit timeUnit) throws TimeoutException {
        throw new TimeoutException("This class does not support the timeout invocation");
    }

    @Override
    public boolean isCancelled() {
        if (this.e && !this.d) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isDone() {
        return this.d;
    }

    @Override
    public void run() {
        try {
            this.a = this.c.call();
            return;
        }
        catch (Exception exception) {
            this.b = exception;
            return;
        }
        finally {
            this.d = true;
        }
    }
}

