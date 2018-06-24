/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Looper
 */
package com.smule.android.utils;

import android.os.Looper;
import java.util.Collection;
import java.util.HashSet;

public class UncaughtExceptionHelper
implements Thread.UncaughtExceptionHandler {
    private final Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();
    private final Collection<ListenerInfo> b = new HashSet<ListenerInfo>();

    public void a(Class<? extends Throwable> class_, Listener listener) {
        this.b.add(new ListenerInfo(class_, listener));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public final void uncaughtException(Thread thread, Throwable throwable) {
        boolean bl = Looper.getMainLooper().getThread() == thread;
        for (ListenerInfo listenerInfo : this.b) {
            if (!listenerInfo.b.isAssignableFrom(throwable.getClass())) continue;
            listenerInfo.c.a(throwable, bl);
        }
        if (this.a != null) {
            this.a.uncaughtException(thread, throwable);
        }
    }

    public static interface Listener {
        public void a(Throwable var1, boolean var2);
    }

    private class ListenerInfo {
        private final Class<? extends Throwable> b;
        private final Listener c;

        private ListenerInfo(Class<? extends Throwable> class_, Listener listener) {
            this.b = class_;
            this.c = listener;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean equals(Object object) {
            block5 : {
                block4 : {
                    if (this == object) break block4;
                    if (object == null || this.getClass() != object.getClass()) {
                        return false;
                    }
                    object = (ListenerInfo)object;
                    if (!this.b.equals(object.b) || !this.c.equals(object.c)) break block5;
                }
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.b.hashCode() * 31 + this.c.hashCode();
        }
    }

}

