package com.smule.android.utils;

import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Collection;
import java.util.HashSet;

public class UncaughtExceptionHelper implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler f6981a = Thread.getDefaultUncaughtExceptionHandler();
    private final Collection<ListenerInfo> f6982b = new HashSet();

    public interface Listener {
        void mo4106a(Throwable th, boolean z);
    }

    private class ListenerInfo {
        final /* synthetic */ UncaughtExceptionHelper f6978a;
        private final Class<? extends Throwable> f6979b;
        private final Listener f6980c;

        private ListenerInfo(UncaughtExceptionHelper uncaughtExceptionHelper, Class<? extends Throwable> cls, Listener listener) {
            this.f6978a = uncaughtExceptionHelper;
            this.f6979b = cls;
            this.f6980c = listener;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ListenerInfo listenerInfo = (ListenerInfo) obj;
            if (this.f6979b.equals(listenerInfo.f6979b) && this.f6980c.equals(listenerInfo.f6980c)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.f6979b.hashCode() * 31) + this.f6980c.hashCode();
        }
    }

    public void m8438a(Class<? extends Throwable> cls, Listener listener) {
        this.f6982b.add(new ListenerInfo(cls, listener));
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        boolean z = Looper.getMainLooper().getThread() == thread;
        for (ListenerInfo listenerInfo : this.f6982b) {
            if (listenerInfo.f6979b.isAssignableFrom(th.getClass())) {
                listenerInfo.f6980c.mo4106a(th, z);
            }
        }
        if (this.f6981a != null) {
            this.f6981a.uncaughtException(thread, th);
        }
    }
}
