package com.smule.android.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import com.smule.android.C3482R;

public class SmuleDialog extends Dialog {
    private Handler f17707a;

    public SmuleDialog(Context context) {
        this(context, C3482R.style.MagicModal);
    }

    public SmuleDialog(Context context, int i) {
        super(context, i);
        this.f17707a = new Handler();
        m18881a();
    }

    private void m18881a() {
        m18882a(0.5f);
    }

    public void m18882a(float f) {
        getWindow().getAttributes().dimAmount = f;
        getWindow().addFlags(2);
    }
}
