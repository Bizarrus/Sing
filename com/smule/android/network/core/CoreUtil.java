/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.core;

import com.smule.android.annotations.NetworkThread;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.ResponseInterface;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CoreUtil {
    private static final String a = CoreUtil.class.getName();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static <T> void a(final ResponseInterface<T> responseInterface, final T t) {
        if (responseInterface == null) {
            return;
        }
        try {
            if (responseInterface.getClass().getMethod("handleResponse", t.getClass()).getAnnotation(NetworkThread.class) == null) {
                Log.b(a, "Running on UI thread");
                MagicNetwork.g().post(new Runnable(){

                    @Override
                    public void run() {
                        responseInterface.handleResponse(t);
                    }
                });
                return;
            }
            Log.b(a, "Running off UI thread");
            responseInterface.handleResponse(t);
            return;
        }
        catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
            throw new RuntimeException(noSuchMethodException);
        }
    }

}

