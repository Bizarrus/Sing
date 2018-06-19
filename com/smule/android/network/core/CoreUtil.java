package com.smule.android.network.core;

import com.smule.android.annotations.NetworkThread;
import com.smule.android.logging.Log;

public class CoreUtil {
    private static final String f16449a = CoreUtil.class.getName();

    public static <T> void m18079a(final ResponseInterface<T> responseInterface, final T t) {
        if (responseInterface != null) {
            try {
                if (((NetworkThread) responseInterface.getClass().getMethod("handleResponse", new Class[]{t.getClass()}).getAnnotation(NetworkThread.class)) == null) {
                    Log.b(f16449a, "Running on UI thread");
                    MagicNetwork.g().post(new Runnable() {
                        public void run() {
                            responseInterface.handleResponse(t);
                        }
                    });
                    return;
                }
                Log.b(f16449a, "Running off UI thread");
                responseInterface.handleResponse(t);
            } catch (Throwable e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
