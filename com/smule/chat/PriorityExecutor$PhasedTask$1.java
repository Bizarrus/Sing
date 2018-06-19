package com.smule.chat;

import com.smule.chat.PriorityExecutor.PhasedTask;

class PriorityExecutor$PhasedTask$1 implements Runnable {
    final /* synthetic */ Object f18315a;
    final /* synthetic */ PhasedTask f18316b;

    PriorityExecutor$PhasedTask$1(PhasedTask phasedTask, Object obj) {
        this.f18316b = phasedTask;
        this.f18315a = obj;
    }

    public void run() {
        this.f18316b.a(this.f18315a);
    }
}
