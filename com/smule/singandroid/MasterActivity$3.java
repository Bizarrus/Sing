package com.smule.singandroid;

import android.app.FragmentManager.OnBackStackChangedListener;

class MasterActivity$3 implements OnBackStackChangedListener {
    final /* synthetic */ MasterActivity f18855a;

    MasterActivity$3(MasterActivity masterActivity) {
        this.f18855a = masterActivity;
    }

    public void onBackStackChanged() {
        if (this.f18855a.F() != null) {
            MasterActivity.a(this.f18855a);
        }
    }
}
