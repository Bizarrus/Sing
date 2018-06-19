package com.smule.android.utils;

import io.fabric.sdk.android.services.concurrency.internal.AbstractFuture;

public class CompletedFuture<T> extends AbstractFuture<T> {
    public CompletedFuture(T t) {
        m18093a((Object) t);
    }
}
