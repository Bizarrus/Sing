/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.annotation.LayoutRes
 *  android.support.annotation.StringRes
 *  android.support.annotation.StyleRes
 *  android.view.View
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;
import com.smule.singandroid.dialogs.CustomAlertDialog;

public class TextAlertDialog
extends CustomAlertDialog {
    public TextAlertDialog(Context context, @StringRes int n, @StringRes int n2) {
        this(context, context.getResources().getString(n), context.getResources().getString(n2));
    }

    public TextAlertDialog(Context context, @LayoutRes int n, @StyleRes int n2, @StringRes int n3, @StringRes int n4, boolean bl, boolean bl2) {
        this(context, n, n2, context.getResources().getString(n3), context.getResources().getString(n4), bl, bl2);
    }

    public TextAlertDialog(Context context, @LayoutRes int n, @StringRes int n2, @StringRes int n3, boolean bl, boolean bl2) {
        this(context, n, context.getResources().getString(n2), context.getResources().getString(n3), bl, bl2);
    }

    public TextAlertDialog(Context context, @LayoutRes int n, @StyleRes int n2, String string2, CharSequence charSequence, boolean bl, boolean bl2) {
        super(context, n, n2, 2130903438, bl, bl2, true, null);
        context = (TextView)this.findViewById(2131756735);
        this.a(string2);
        if (charSequence != null) {
            context.setText(charSequence);
            context.setVisibility(0);
            return;
        }
        context.setVisibility(8);
    }

    public TextAlertDialog(Context context, @StringRes int n, @StringRes int n2, boolean bl, boolean bl2) {
        this(context, context.getResources().getString(n), context.getResources().getString(n2), bl, bl2);
    }

    public TextAlertDialog(Context context, @LayoutRes int n, String string2, CharSequence charSequence, boolean bl, boolean bl2) {
        this(context, n, 0, string2, charSequence, bl, bl2);
    }

    public TextAlertDialog(Context context, String string2) {
        this(context, null, string2);
    }

    public TextAlertDialog(Context context, String string2, CharSequence charSequence, boolean bl, boolean bl2) {
        this(context, 0, string2, charSequence, bl, bl2);
    }

    public TextAlertDialog(Context context, String string2, String string3) {
        this(context, string2, string3, true, true);
    }

    public void j() {
        ((TextView)this.findViewById(2131756735)).setGravity(1);
    }
}

