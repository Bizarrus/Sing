/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 */
package com.smule.singandroid.boost;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.singandroid.dialogs.CustomAlertDialog;

public class BoostCountdownDialog
extends CustomAlertDialog {
    public BoostCountdownDialog(Context context, int n, boolean bl, boolean bl2, boolean bl3) {
        super(context, n, bl, bl2, bl3);
        this.j();
    }

    private void j() {
        LinearLayout linearLayout = (LinearLayout)this.findViewById(2131755635);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.leftMargin = 0;
        layoutParams.rightMargin = 0;
        linearLayout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }
}

