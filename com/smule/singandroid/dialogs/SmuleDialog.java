package com.smule.singandroid.dialogs;

import android.app.Dialog;
import android.content.Context;
import com.smule.android.utils.Toaster;

public class SmuleDialog extends Dialog {
    public SmuleDialog(Context context, int i) {
        super(context, i);
        mo6374a();
    }

    public SmuleDialog(Context context, int i, boolean z) {
        super(context, i);
        if (z) {
            mo6374a();
        }
    }

    private void mo6374a() {
        m19789a(0.65f);
    }

    public void m19789a(float f) {
        getWindow().getAttributes().dimAmount = f;
        getWindow().addFlags(2);
    }

    protected void m19790a(Context context, String str) {
        Toaster.a(context, str);
    }
}
