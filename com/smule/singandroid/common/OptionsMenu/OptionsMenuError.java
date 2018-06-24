/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.common.OptionsMenu;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;

enum OptionsMenuError implements IError
{
    a(1, "Display Name is empty"),
    b(2, "The runnable for click behavior is null");
    
    private int c;
    private int d;
    private String e;

    private OptionsMenuError(int n2, String string3) {
        this.d = n2;
        this.e = string3;
        this.c = ErrorHelper.a("OPTIONS_MENU_ERROR_CODE_OFFSET");
    }

    @Override
    public String a() {
        return this.e;
    }
}

