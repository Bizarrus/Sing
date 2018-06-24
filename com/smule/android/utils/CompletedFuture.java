/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  io.fabric.sdk.android.services.concurrency.internal.AbstractFuture
 */
package com.smule.android.utils;

import io.fabric.sdk.android.services.concurrency.internal.AbstractFuture;

public class CompletedFuture<T>
extends AbstractFuture<T> {
    public CompletedFuture(T t) {
        this.a(t);
    }
}

