/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.content.Context
 *  android.support.annotation.DrawableRes
 *  android.support.annotation.LayoutRes
 *  android.support.annotation.NonNull
 *  android.support.annotation.StringRes
 *  android.text.TextUtils
 *  android.view.View
 *  android.widget.ImageView
 */
package com.smule.singandroid.runtimepermissions;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;

public class SingPermissionExplanationDialogCreator
implements RunTimePermissionsRequest.ExplanationDialogCreator {
    protected final int a;
    protected final int b;
    protected final int c;
    protected final int d;
    protected final int e;
    protected final int f;
    protected boolean g;

    public SingPermissionExplanationDialogCreator(@StringRes int n, @StringRes int n2, @StringRes int n3, @StringRes int n4) {
        this(n, n2, n3, n4, 0);
    }

    public SingPermissionExplanationDialogCreator(@StringRes int n, @StringRes int n2, @StringRes int n3, @StringRes int n4, @DrawableRes int n5) {
        this(n, n2, n3, n4, n5, true, 0);
    }

    public SingPermissionExplanationDialogCreator(@StringRes int n, @StringRes int n2, @StringRes int n3, @StringRes int n4, @DrawableRes int n5, boolean bl, @LayoutRes int n6) {
        this.b = n;
        this.c = n2;
        this.d = n3;
        this.e = n4;
        this.a = n5;
        this.g = bl;
        this.f = n6;
    }

    /*
     * Enabled aggressive block sorting
     */
    @NonNull
    @Override
    public Dialog a(Context context, final RunTimePermissionsRequest.ExplanationDialogListener explanationDialogListener) {
        TextAlertDialog textAlertDialog = this.a(context);
        if (TextUtils.isEmpty((CharSequence)context.getString(this.c))) {
            textAlertDialog.a();
        }
        textAlertDialog.setCanceledOnTouchOutside(false);
        textAlertDialog.a(false);
        if (this.a != 0) {
            if (!this.g) {
                textAlertDialog.a(this.a, true);
                textAlertDialog.a(0);
                textAlertDialog.j();
            } else {
                textAlertDialog.b(2130903418);
                ((ImageView)textAlertDialog.findViewById(2131755217)).setImageResource(this.a);
            }
        }
        textAlertDialog.a(this.e, this.d);
        textAlertDialog.a(new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                explanationDialogListener.b(customAlertDialog);
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                explanationDialogListener.a(customAlertDialog);
            }
        });
        return textAlertDialog;
    }

    protected TextAlertDialog a(Context context) {
        return new TextAlertDialog(context, this.f, this.b, this.c, true, true);
    }

}

