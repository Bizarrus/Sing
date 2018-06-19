package com.smule.chat;

import android.os.Handler;
import android.os.Looper;
import com.smule.chat.PriorityExecutor.Task;
import java.util.concurrent.Executor;

public abstract class PriorityExecutor$PhasedTask<Params, Result> implements Task {
    private static final Handler f6987a = new Handler(Looper.getMainLooper());
    private Params[] f6988b;

    protected abstract Result m8444a(Params... paramsArr);

    @SafeVarargs
    PriorityExecutor$PhasedTask(Params... paramsArr) {
        this.f6988b = paramsArr;
    }

    protected void m8445a(Result result) {
    }

    public void run() {
        f6987a.post(new 1(this, m8444a(this.f6988b)));
    }

    public void m8446a(Executor executor) {
        executor.execute(this);
    }
}
