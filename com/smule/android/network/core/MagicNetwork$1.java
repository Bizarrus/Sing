package com.smule.android.network.core;

import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;

class MagicNetwork$1 implements Runnable {
    final /* synthetic */ Runnable f16456a;

    MagicNetwork$1(Runnable runnable) {
        this.f16456a = runnable;
    }

    public void run() {
        try {
            this.f16456a.run();
        } catch (Throwable e) {
            Log.d(MagicNetwork.t(), "Uncaught exception in a NETWORK thread!", e);
            MagicCrittercism.a("MagicNetwork.wrapRunnable()");
            MagicCrittercism.a(e);
        }
    }
}
