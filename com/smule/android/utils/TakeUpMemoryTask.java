package com.smule.android.utils;

import android.os.AsyncTask;
import com.smule.android.logging.Log;
import java.util.ArrayList;

class TakeUpMemoryTask extends AsyncTask<Void, Void, Void> {
    private static String f17856b = TakeUpMemoryTask.class.getName();
    boolean f17857a;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m19054a((Void[]) objArr);
    }

    protected Void m19054a(Void... voidArr) {
        if (this.f17857a) {
            m19056b();
        } else {
            m19055a();
        }
        return null;
    }

    public void m19055a() {
        System.out.println("\n=================> OOM test started..\n");
        int i = 20;
        for (int i2 = 1; i2 < 20; i2++) {
            System.out.println("Iteration " + i2 + " Free Mem: " + Runtime.getRuntime().freeMemory());
            int i3 = 2;
            int[] iArr = new int[i];
            do {
                iArr[i3] = 0;
                i3--;
            } while (i3 > 0);
            i *= 5;
            System.out.println("\nRequired Memory for next loop: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void m19056b() {
        Log.b(f17856b, "generateOOMSlow");
        ArrayList arrayList = new ArrayList();
        while (true) {
            Object obj = new int[10000];
            obj[0] = null;
            arrayList.add(obj);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
