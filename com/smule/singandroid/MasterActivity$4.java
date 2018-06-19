package com.smule.singandroid;

import com.smule.singandroid.customviews.BottomNavView.Tab;

class MasterActivity$4 implements Runnable {
    final /* synthetic */ boolean f18856a;
    final /* synthetic */ MasterActivity f18857b;

    MasterActivity$4(MasterActivity masterActivity, boolean z) {
        this.f18857b = masterActivity;
        this.f18856a = z;
    }

    public void run() {
        if (this.f18856a && this.f18857b.k.getSelectedTab() == Tab.SONGBOOK) {
            this.f18857b.a(Tab.SONGBOOK, true);
        } else {
            this.f18857b.k.m23128a(Tab.SONGBOOK, true);
        }
    }
}
