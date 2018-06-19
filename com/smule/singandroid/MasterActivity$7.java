package com.smule.singandroid;

import com.smule.android.logging.Log;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.SingBundle.PerformanceType;

class MasterActivity$7 extends Operation {
    final /* synthetic */ UiHandler f18862a;
    final /* synthetic */ String f18863b;
    final /* synthetic */ boolean f18864c;
    final /* synthetic */ PerformanceType f18865d;
    final /* synthetic */ int f18866n;
    final /* synthetic */ Long f18867o;
    final /* synthetic */ boolean f18868p;
    final /* synthetic */ MasterActivity f18869q;

    class C38671 implements Runnable {
        final /* synthetic */ MasterActivity$7 f18861a;

        C38671(MasterActivity$7 masterActivity$7) {
            this.f18861a = masterActivity$7;
        }

        public void run() {
            MasterActivity.a(this.f18861a.f18869q, this.f18861a.f18863b, this.f18861a.f18864c, this.f18861a.f18865d, this.f18861a.f18866n, this.f18861a.f18867o, this.f18861a.f18868p);
        }
    }

    MasterActivity$7(MasterActivity masterActivity, UiHandler uiHandler, String str, boolean z, PerformanceType performanceType, int i, Long l, boolean z2) {
        this.f18869q = masterActivity;
        this.f18862a = uiHandler;
        this.f18863b = str;
        this.f18864c = z;
        this.f18865d = performanceType;
        this.f18866n = i;
        this.f18867o = l;
        this.f18868p = z2;
    }

    public void m20326a(OperationLoader operationLoader) {
        Log.b(MasterActivity.g, "Store deep init complete.");
        operationLoader.a("OP_DEEP_LINK_WAIT_FOR_STORE_INIT");
        operationLoader.c(this.g);
        this.f18862a.post(new C38671(this));
    }
}
