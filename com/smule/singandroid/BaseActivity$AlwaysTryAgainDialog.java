package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.smule.singandroid.dialogs.TextAlertDialog;

class BaseActivity$AlwaysTryAgainDialog extends TextAlertDialog {

    class C37761 implements OnClickListener {
        final /* synthetic */ BaseActivity$AlwaysTryAgainDialog f18445a;

        C37761(BaseActivity$AlwaysTryAgainDialog baseActivity$AlwaysTryAgainDialog) {
            this.f18445a = baseActivity$AlwaysTryAgainDialog;
        }

        public void onClick(View view) {
            this.f18445a.mo6374a();
        }
    }

    BaseActivity$AlwaysTryAgainDialog(Activity activity, String str, String str2, boolean z, boolean z2) {
        super((Context) activity, str, (CharSequence) str2, z, z2);
    }

    public void onBackPressed() {
        mo6374a();
    }

    public void setCanceledOnTouchOutside(boolean z) {
        this.a.setOnClickListener(new C37761(this));
    }
}
