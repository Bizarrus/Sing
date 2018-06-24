/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 */
package com.smule.android.utils;

import android.os.Build;
import com.smule.android.logging.Log;
import com.smule.android.utils.ThreadUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class NotificationCenter {
    public static final String a = NotificationCenter.class.getName();
    private static NotificationCenter c = null;
    HashMap<String, Observable> b = new HashMap();

    private NotificationCenter() {
    }

    public static NotificationCenter a() {
        synchronized (NotificationCenter.class) {
            if (c == null) {
                c = new NotificationCenter();
            }
            NotificationCenter notificationCenter = c;
            return notificationCenter;
        }
    }

    public void a(String string2, Object object) {
        this.a(string2, object, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void a(String object, Object object2, boolean bl) {
        if (bl) {
            Log.b(a, "Posting notification " + (String)object);
        }
        // MONITORENTER : this
        object = this.b.get(object);
        // MONITOREXIT : this
        if (object == null) return;
        object.notifyObservers(object2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2, Observer observer) {
        synchronized (this) {
            Observable observable;
            void var2_2;
            Observable observable2 = observable = this.b.get(string2);
            if (observable == null) {
                observable2 = Build.VERSION.CODENAME == null || Build.VERSION.CODENAME.charAt(0) == 'N' ? new NCompatNotificationObservable(string2) : new NotificationObservable(string2);
                this.b.put(string2, observable2);
            }
            observable2.addObserver((Observer)var2_2);
            return;
        }
    }

    public /* varargs */ void a(final String string2, final Object ... arrobject) {
        ThreadUtils.a(new Runnable(){

            @Override
            public void run() {
                NotificationCenter.this.b(string2, arrobject);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(String object, Observer observer) {
        synchronized (this) {
            object = this.b.get(object);
            if (object != null) {
                void var2_2;
                object.deleteObserver((Observer)var2_2);
            }
            return;
        }
    }

    public /* varargs */ void b(String string2, Object ... arrobject) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (arrobject != null) {
            if (arrobject.length % 2 == 1) {
                throw new Error("Bad number of parameters " + arrobject.length);
            }
            for (int i = 0; i < arrobject.length; i += 2) {
                hashMap.put(arrobject[i].toString(), arrobject[i + 1]);
            }
        }
        try {
            this.a(string2, (Object)hashMap);
            return;
        }
        catch (Exception exception) {
            Log.b(a, "Exception while posting a notification", exception);
            throw new RuntimeException(exception);
        }
    }

    private static class NCompatNotificationObservable
    extends NotificationObservable {
        public static final String a = NotificationCenter.a;
        private final List<Observer> c = new LinkedList<Observer>();

        public NCompatNotificationObservable(String string2) {
            super(string2);
        }

        @Override
        public void addObserver(Observer observer) {
            this.c.add(observer);
        }

        @Override
        protected void clearChanged() {
        }

        @Override
        public int countObservers() {
            return this.c.size();
        }

        @Override
        public void deleteObserver(Observer observer) {
            synchronized (this) {
                this.c.remove(observer);
                return;
            }
        }

        @Override
        public void deleteObservers() {
            synchronized (this) {
                this.c.clear();
                return;
            }
        }

        @Override
        public void notifyObservers() {
            this.notifyObservers(null);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void notifyObservers(Object object) {
            int n;
            int n2 = 0;
            Observer[] arrobserver = null;
            synchronized (this) {
                if (this.hasChanged()) {
                    this.clearChanged();
                    n = this.c.size();
                    arrobserver = new Observer[n];
                    this.c.toArray(arrobserver);
                } else {
                    n = 0;
                }
            }
            if (arrobserver == null) return;
            while (n2 < n) {
                arrobserver[n2].update(this, object);
                ++n2;
            }
            return;
        }

        @Override
        protected void setChanged() {
            super.setChanged();
        }
    }

    public static class NotificationObservable
    extends Observable {
        protected final String b;

        public NotificationObservable(String string2) {
            this.b = string2;
        }

        @Override
        public boolean hasChanged() {
            return true;
        }
    }

}

