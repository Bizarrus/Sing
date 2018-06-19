package com.smule.android.utils;

import android.os.Build.VERSION;
import com.smule.android.logging.Log;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class NotificationCenter {
    public static final String f17811a = NotificationCenter.class.getName();
    private static NotificationCenter f17812c = null;
    HashMap<String, Observable> f17813b = new HashMap();

    public static class NotificationObservable extends Observable {
        protected final String f17808b;

        public NotificationObservable(String str) {
            this.f17808b = str;
        }

        public boolean hasChanged() {
            return true;
        }
    }

    private static class NCompatNotificationObservable extends NotificationObservable {
        public static final String f17809a = NotificationCenter.f17811a;
        private final List<Observer> f17810c = new LinkedList();

        public NCompatNotificationObservable(String str) {
            super(str);
        }

        public void addObserver(Observer observer) {
            this.f17810c.add(observer);
        }

        protected void clearChanged() {
        }

        public int countObservers() {
            return this.f17810c.size();
        }

        public synchronized void deleteObserver(Observer observer) {
            this.f17810c.remove(observer);
        }

        public synchronized void deleteObservers() {
            this.f17810c.clear();
        }

        public void notifyObservers() {
            notifyObservers(null);
        }

        public void notifyObservers(Object obj) {
            int i = 0;
            Observer[] observerArr = null;
            synchronized (this) {
                int size;
                if (hasChanged()) {
                    clearChanged();
                    size = this.f17810c.size();
                    observerArr = new Observer[size];
                    this.f17810c.toArray(observerArr);
                } else {
                    size = 0;
                }
            }
            if (observerArr != null) {
                while (i < size) {
                    observerArr[i].update(this, obj);
                    i++;
                }
            }
        }

        protected void setChanged() {
            super.setChanged();
        }
    }

    public static synchronized NotificationCenter m19011a() {
        NotificationCenter notificationCenter;
        synchronized (NotificationCenter.class) {
            if (f17812c == null) {
                f17812c = new NotificationCenter();
            }
            notificationCenter = f17812c;
        }
        return notificationCenter;
    }

    private NotificationCenter() {
    }

    public synchronized void m19014a(String str, Observer observer) {
        Observable observable = (Observable) this.f17813b.get(str);
        if (observable == null) {
            observable = (VERSION.CODENAME == null || VERSION.CODENAME.charAt(0) == 'N') ? new NCompatNotificationObservable(str) : new NotificationObservable(str);
            this.f17813b.put(str, observable);
        }
        observable.addObserver(observer);
    }

    public synchronized void m19016b(String str, Observer observer) {
        Observable observable = (Observable) this.f17813b.get(str);
        if (observable != null) {
            observable.deleteObserver(observer);
        }
    }

    public void m19015a(final String str, final Object... objArr) {
        ThreadUtils.m19058a(new Runnable(this) {
            final /* synthetic */ NotificationCenter f17807c;

            public void run() {
                this.f17807c.m19017b(str, objArr);
            }
        });
    }

    public void m19012a(String str, Object obj) {
        m19013a(str, obj, true);
    }

    public void m19013a(String str, Object obj, boolean z) {
        if (z) {
            Log.b(f17811a, "Posting notification " + str);
        }
        synchronized (this) {
            Observable observable = (Observable) this.f17813b.get(str);
        }
        if (observable != null) {
            observable.notifyObservers(obj);
        }
    }

    public void m19017b(String str, Object... objArr) {
        Object hashMap = new HashMap();
        if (objArr != null) {
            if (objArr.length % 2 == 1) {
                throw new Error("Bad number of parameters " + objArr.length);
            }
            for (int i = 0; i < objArr.length; i += 2) {
                hashMap.put(objArr[i].toString(), objArr[i + 1]);
            }
        }
        try {
            m19012a(str, hashMap);
        } catch (Throwable e) {
            Log.b(f17811a, "Exception while posting a notification", e);
            throw new RuntimeException(e);
        }
    }
}
