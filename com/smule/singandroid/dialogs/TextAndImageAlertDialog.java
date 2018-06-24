/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.support.annotation.DrawableRes
 *  android.support.annotation.StringRes
 *  android.view.View
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.singandroid.dialogs.TextAlertDialog;

public class TextAndImageAlertDialog
extends TextAlertDialog {
    private static final String c = TextAndImageAlertDialog.class.getSimpleName();

    public TextAndImageAlertDialog(Context context, @StringRes int n, @StringRes int n2, @StringRes int n3, @StringRes int n4, @DrawableRes int n5, int n6) {
        this(context, context.getResources().getString(n), context.getResources().getString(n2), true, true, null, n6);
        this.a(n5, true);
        this.a(n6);
        this.a(n3, n4);
    }

    public TextAndImageAlertDialog(Context context, @StringRes int n, @StringRes int n2, boolean bl, boolean bl2, Drawable drawable2, int n3) {
        this(context, context.getResources().getString(n), context.getResources().getString(n2), bl, bl2, drawable2, n3);
    }

    public TextAndImageAlertDialog(Context context, String string2, CharSequence charSequence, boolean bl, boolean bl2, Drawable drawable2, int n) {
        int n2;
        super(context, string2, charSequence, bl, bl2);
        if (drawable2 != null && (n = (n2 = drawable2.getIntrinsicWidth()) - n * 2) > 0) {
            Log.b(c, "forcing text width:" + n2);
            ((TextView)this.findViewById(2131755176)).setWidth(n);
            ((TextView)this.findViewById(2131756735)).setWidth(n);
        }
        this.a(2130837613, true);
    }
}

