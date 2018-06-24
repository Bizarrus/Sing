/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.content.Context
 *  android.support.annotation.NonNull
 */
package com.smule.android.runtimepermissions;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

public class RunTimePermissionsRequest {
    final String a;
    final String[] b;
    ExplanationDialogCreator c = null;
    ExplanationDialogCreator d = null;
    ExplanationDialogCreator e = null;
    boolean f;

    public /* varargs */ RunTimePermissionsRequest(String string2, String ... arrstring) {
        this.a = string2;
        this.b = arrstring;
    }

    static Dialog a(Context context, ExplanationDialogCreator explanationDialogCreator, final ExplanationDialogListener explanationDialogListener) {
        context = explanationDialogCreator.a(context, new ExplanationDialogListener(){

            @Override
            public void a(Dialog dialog) {
                dialog.dismiss();
                explanationDialogListener.a(dialog);
            }

            @Override
            public void b(Dialog dialog) {
                dialog.dismiss();
                explanationDialogListener.b(dialog);
            }
        });
        context.show();
        return context;
    }

    public RunTimePermissionsRequest a(ExplanationDialogCreator explanationDialogCreator) {
        this.c = explanationDialogCreator;
        return this;
    }

    public RunTimePermissionsRequest a(boolean bl, ExplanationDialogCreator explanationDialogCreator) {
        this.e = explanationDialogCreator;
        this.f = bl;
        return this;
    }

    public RunTimePermissionsRequest b(ExplanationDialogCreator explanationDialogCreator) {
        this.d = explanationDialogCreator;
        return this;
    }

    public static interface ExplanationDialogCreator {
        @NonNull
        public Dialog a(Context var1, ExplanationDialogListener var2);
    }

    public static interface ExplanationDialogListener {
        public void a(Dialog var1);

        public void b(Dialog var1);
    }

}

