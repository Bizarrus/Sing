package com.smule.singandroid;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.androidannotations.annotations.EActivity;

@EActivity
public class AccountFrozenActivity extends BlockingActivity {

    class C37671 implements OnClickListener {
        final /* synthetic */ AccountFrozenActivity f18408a;

        C37671(AccountFrozenActivity accountFrozenActivity) {
            this.f18408a = accountFrozenActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f18408a.finishAffinity();
            System.exit(0);
        }
    }

    protected AlertDialog mo6371a() {
        Builder builder = new Builder(this);
        builder.setTitle(C1947R.string.login_failed);
        builder.setMessage(C1947R.string.login_account_frozen);
        builder.setCancelable(false);
        builder.setPositiveButton(C1947R.string.core_ok, new C37671(this));
        return builder.create();
    }
}
