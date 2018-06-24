/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.smule.android.ui.dialogs.SmuleDialog;

public class AdLoadingDialog
extends SmuleDialog {
    protected boolean a = false;

    /*
     * Enabled aggressive block sorting
     */
    public AdLoadingDialog(Context context, boolean bl, View.OnClickListener onClickListener) {
        super(context, 2131493339);
        this.setContentView(2130903075);
        this.a = bl;
        if (this.a) {
            this.findViewById(2131755255).setVisibility(0);
            this.findViewById(2131755254).setVisibility(8);
        } else {
            this.findViewById(2131755255).setVisibility(8);
            this.findViewById(2131755254).setVisibility(0);
        }
        this.findViewById(2131755257).setOnClickListener(onClickListener);
    }

    public void a() {
        TextView textView;
        if (this.a && (textView = (TextView)this.findViewById(2131755256)) != null) {
            textView.setText(2131297040);
        }
    }
}

