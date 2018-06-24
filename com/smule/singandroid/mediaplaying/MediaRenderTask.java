/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 *  android.os.AsyncTask$Status
 *  android.os.Handler
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid.mediaplaying;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformanceV2;
import java.security.InvalidParameterException;
import java.util.concurrent.atomic.AtomicLong;

public class MediaRenderTask
extends AsyncTask<String, Void, PerformanceV2> {
    private static final String a = MediaRenderTask.class.getName();
    private static long b = 90000;
    private static long c = 5000;
    private MediaRenderTaskListener d;
    private final AtomicLong e = new AtomicLong(0);
    private Runnable f;
    private Handler g;

    public MediaRenderTask(@NonNull MediaRenderTaskListener mediaRenderTaskListener) {
        if (mediaRenderTaskListener == null) {
            throw new InvalidParameterException("MediaRenderTask requires valid listener");
        }
        this.d = mediaRenderTaskListener;
    }

    private PerformanceV2 a(String object) {
        object = PerformanceManager.a().a((String)object, false);
        if (!object.a()) {
            return null;
        }
        return object.mPerformance;
    }

    private boolean c(@Nullable PerformanceV2 performanceV2) {
        if (performanceV2 != null && performanceV2.a()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected /* varargs */ PerformanceV2 a(String ... object) {
        Object var7_3 = null;
        Object object2 = null;
        if (object == null || object.length != 1) {
            throw new InvalidParameterException("MediaRenderTask only renders single performance");
        }
        Object object3 = object[0];
        object = var7_3;
        if (!PerformanceManager.a().a((String)object3, PerformancesAPI.MAIN).c()) return object2;
        do {
            object2 = object;
            if (this.c((PerformanceV2)object)) {
                return object2;
            }
            long l = System.currentTimeMillis();
            object = object2 = this.a((String)object3);
            if (this.c((PerformanceV2)object2)) continue;
            l = System.currentTimeMillis() - l;
            object = object2;
            if (l >= c) continue;
            long l2 = c;
            try {
                Thread.sleep(l2 - l);
                object = object2;
                continue;
            }
            catch (Exception exception) {}
            return object2;
        } while (true);
    }

    public void a() {
        this.d = null;
        this.cancel(true);
    }

    protected void a(@Nullable PerformanceV2 performanceV2) {
        block3 : {
            block2 : {
                this.g.removeCallbacks(this.f);
                if (this.d == null) break block2;
                if (performanceV2 != null && performanceV2.a()) break block3;
                this.d.a();
            }
            return;
        }
        this.d.a(performanceV2);
    }

    protected void b(@Nullable PerformanceV2 performanceV2) {
        this.g.removeCallbacks(this.f);
        if (this.d != null) {
            this.d.a();
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a((String[])arrobject);
    }

    protected /* synthetic */ void onCancelled(@Nullable Object object) {
        this.b((PerformanceV2)object);
    }

    protected /* synthetic */ void onPostExecute(@Nullable Object object) {
        this.a((PerformanceV2)object);
    }

    protected void onPreExecute() {
        this.e.set(System.currentTimeMillis());
        this.f = new Runnable(){

            @Override
            public void run() {
                if (MediaRenderTask.this.getStatus() != AsyncTask.Status.FINISHED) {
                    MediaRenderTask.this.cancel(true);
                }
            }
        };
        this.g = new Handler();
        this.g.postDelayed(this.f, b);
    }

    public static interface MediaRenderTaskListener {
        public void a();

        public void a(PerformanceV2 var1);
    }

}

