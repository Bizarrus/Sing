/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.DrawableRes
 *  android.support.annotation.LayoutRes
 *  android.support.annotation.StringRes
 */
package com.smule.singandroid.runtimepermissions;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import com.smule.singandroid.dialogs.FullscreenTextAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.runtimepermissions.SingPermissionExplanationDialogCreator;

public class SingPermissionExplanationFullscreenDialogCreator
extends SingPermissionExplanationDialogCreator {
    public SingPermissionExplanationFullscreenDialogCreator(@StringRes int n, @StringRes int n2, @StringRes int n3, @StringRes int n4, @DrawableRes int n5, boolean bl, @LayoutRes int n6) {
        super(n, n2, n3, n4, n5, bl, n6);
    }

    @Override
    protected TextAlertDialog a(Context context) {
        return new FullscreenTextAlertDialog(context, this.f, this.b, this.c, true, true);
    }
}

