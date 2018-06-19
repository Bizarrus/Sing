package com.smule.android.network.managers;

import com.smule.android.logging.Log;
import java.util.Observable;
import java.util.Observer;

class BalanceManager$1 implements Observer {
    final /* synthetic */ BalanceManager f16557a;

    BalanceManager$1(BalanceManager balanceManager) {
        this.f16557a = balanceManager;
    }

    public void update(Observable observable, Object obj) {
        if ("USER_EXISTENCE_TYPE_EXISTING".equals((String) obj)) {
            Log.c(BalanceManager.c(), "user logged into existing account. Updating balance.");
            this.f16557a.b();
        }
    }
}
