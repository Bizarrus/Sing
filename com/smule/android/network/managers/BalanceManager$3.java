package com.smule.android.network.managers;

class BalanceManager$3 implements Runnable {
    final /* synthetic */ boolean f16559a;
    final /* synthetic */ Runnable f16560b;
    final /* synthetic */ BalanceManager f16561c;

    BalanceManager$3(BalanceManager balanceManager, boolean z, Runnable runnable) {
        this.f16561c = balanceManager;
        this.f16559a = z;
        this.f16560b = runnable;
    }

    public void run() {
        BalanceManager.a(this.f16561c, this.f16559a);
        if (this.f16560b != null) {
            this.f16560b.run();
        }
    }
}
