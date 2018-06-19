package com.smule.singandroid;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.smule.android.logging.Log;
import org.androidannotations.annotations.EActivity;

@EActivity
public class ForcedUpgradeActivity extends BlockingActivity {
    public static String f18758g = "UPGRADE_URL_EXTRAS_KEY";

    protected AlertDialog mo6371a() {
        final String stringExtra = getIntent().getStringExtra(f18758g);
        Log.c(a, "Showing upgrade dialog!");
        Builder builder = new Builder(this);
        builder.setMessage(SingServerValues.m21681b());
        builder.setCancelable(false);
        builder.setPositiveButton(SingServerValues.m21678a(), new OnClickListener(this) {
            final /* synthetic */ ForcedUpgradeActivity f18757b;

            public void onClick(DialogInterface dialogInterface, int i) {
                String str = stringExtra;
                if (!(str.startsWith("http://") || str.startsWith("https://"))) {
                    str = "http://" + str;
                }
                this.f18757b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                this.f18757b.finish();
                System.exit(0);
            }
        });
        return builder.create();
    }
}
