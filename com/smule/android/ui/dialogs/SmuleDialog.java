/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.content.Context
 *  android.os.Handler
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 */
package com.smule.android.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.smule.android.R;
import com.smule.android.utils.Toaster;

public class SmuleDialog
extends Dialog {
    private Handler a = new Handler();

    public SmuleDialog(Context context) {
        this(context, R.style.MagicModal);
    }

    public SmuleDialog(Context context, int n) {
        super(context, n);
        this.a();
    }

    public SmuleDialog(Context context, int n, boolean bl) {
        super(context, n);
        if (bl) {
            this.a();
        }
    }

    private void a() {
        this.a(0.5f);
    }

    public void a(float f) {
        this.getWindow().getAttributes().dimAmount = f;
        this.getWindow().addFlags(2);
    }

    protected void a(Context context, String string2) {
        com.smule.android.utils.Toaster.a(context, string2, Toaster.b);
    }
}

