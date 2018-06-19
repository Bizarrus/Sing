package com.smule.android.purchases;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.purchases.Consts.PurchaseState;
import com.smule.android.purchases.Consts.ResponseCode;
import com.smule.android.purchases.IabHelper.QueryInventoryFinishedListener;
import com.smule.android.utils.SimpleBarrier;
import java.util.List;

class GoogleV3Billing$5 implements QueryInventoryFinishedListener {
    final /* synthetic */ GoogleV3Billing f17523a;

    class C36351 implements Runnable {
        final /* synthetic */ GoogleV3Billing$5 f17518a;

        C36351(GoogleV3Billing$5 googleV3Billing$5) {
            this.f17518a = googleV3Billing$5;
        }

        public void run() {
            GoogleV3Billing.a(this.f17518a.f17523a, Boolean.valueOf(true));
        }
    }

    GoogleV3Billing$5(GoogleV3Billing googleV3Billing) {
        this.f17523a = googleV3Billing;
    }

    public void mo6282a(final IabResult iabResult, Inventory inventory) {
        if (iabResult.m18660c()) {
            List<String> b = inventory.m18665b("subs");
            if (b.size() == 0) {
                GoogleV3Billing.a(this.f17523a, Boolean.valueOf(false));
            } else {
                final SimpleBarrier simpleBarrier = new SimpleBarrier(b.size(), new C36351(this));
                for (String a : b) {
                    final Purchase a2 = inventory.m18662a(a);
                    MagicNetwork.a(new Runnable(this) {
                        final /* synthetic */ GoogleV3Billing$5 f17522d;

                        public void run() {
                            try {
                                if (ServerPurchaseExecutor.m18678a(PurchaseState.m18618a(a2.m18670e()), a2.m18668c(), a2.m18667b(), a2.m18669d(), a2.m18673h(), a2.m18674i())) {
                                    GoogleV3Billing.a(this.f17522d.f17523a, iabResult, a2, true);
                                }
                                simpleBarrier.m19034a();
                            } catch (Throwable th) {
                                simpleBarrier.m19034a();
                            }
                        }
                    });
                }
            }
        }
        if (GoogleV3Billing.b(this.f17523a) != null) {
            GoogleV3Billing.b(this.f17523a).mo6275b(ResponseCode.m18619a(iabResult.m18658a()));
        }
    }
}
