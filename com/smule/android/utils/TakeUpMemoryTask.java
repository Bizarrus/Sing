/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 */
package com.smule.android.utils;

import android.os.AsyncTask;
import com.smule.android.logging.Log;
import java.io.PrintStream;
import java.util.ArrayList;

class TakeUpMemoryTask
extends AsyncTask<Void, Void, Void> {
    private static String b = TakeUpMemoryTask.class.getName();
    boolean a;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected /* varargs */ Void a(Void ... arrvoid) {
        if (this.a) {
            this.b();
            do {
                return null;
                break;
            } while (true);
        }
        this.a();
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a() {
        System.out.println("\n=================> OOM test started..\n");
        int n = 1;
        int n2 = 20;
        while (n < 20) {
            int n3;
            System.out.println("Iteration " + n + " Free Mem: " + Runtime.getRuntime().freeMemory());
            int n4 = 2;
            int[] arrn = new int[n2];
            do {
                arrn[n4] = 0;
                n4 = n3 = n4 - 1;
            } while (n3 > 0);
            System.out.println("\nRequired Memory for next loop: " + (n2 *= 5));
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            ++n;
        }
    }

    public void b() {
        Log.b(b, "generateOOMSlow");
        ArrayList<int[]> arrayList = new ArrayList<int[]>();
        do {
            int[] arrn = new int[10000];
            arrn[0] = 0;
            arrayList.add(arrn);
            try {
                Thread.sleep(100);
                continue;
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
                continue;
            }
            break;
        } while (true);
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a((Void[])arrobject);
    }
}

