package com.smule.singandroid;

import android.view.View;
import android.view.View.OnClickListener;

class MasterActivity$5 implements OnClickListener {
    final /* synthetic */ MasterActivity f18858a;

    MasterActivity$5(MasterActivity masterActivity) {
        this.f18858a = masterActivity;
    }

    public void onClick(View view) {
        if (!(this.f18858a.F() instanceof SongbookFragment)) {
            this.f18858a.onBackPressed();
        }
    }
}
