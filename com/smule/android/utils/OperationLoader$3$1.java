package com.smule.android.utils;

import com.smule.android.utils.OperationLoader.3;
import java.util.Observable;
import java.util.Observer;

class OperationLoader$3$1 implements Observer {
    final /* synthetic */ 3 f17815a;

    OperationLoader$3$1(3 3) {
        this.f17815a = 3;
    }

    public void update(Observable observable, Object obj) {
        if (this.f17815a.a) {
            NotificationCenter.m19011a().m19016b(this.f17815a.b, (Observer) this);
        }
        this.f17815a.a(true);
    }
}
