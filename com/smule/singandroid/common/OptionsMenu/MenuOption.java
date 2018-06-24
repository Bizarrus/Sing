/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 */
package com.smule.singandroid.common.OptionsMenu;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.parameter.KeyedParameter;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.common.OptionsMenu.OptionsMenuError;

public class MenuOption {
    private final String a;
    private final OnClickListener b;

    public MenuOption(@NonNull Context context, int n, @NonNull OnClickListener onClickListener) {
        this.a = context.getResources().getString(n);
        this.b = onClickListener;
    }

    public MenuOption(@NonNull String string2, @NonNull OnClickListener onClickListener) {
        this.a = string2;
        this.b = onClickListener;
    }

    String a() throws SmuleException {
        if (TextUtils.isEmpty((CharSequence)this.a)) {
            throw new SmuleException((IError)OptionsMenuError.a, new KeyedParameter[0]);
        }
        return this.a;
    }

    public void a(OptionsMenu optionsMenu) throws SmuleException {
        if (this.b == null) {
            throw new SmuleException((IError)OptionsMenuError.b, new KeyedParameter[0]);
        }
        this.b.a(optionsMenu);
    }

    public static interface OnClickListener {
        public void a(OptionsMenu var1);
    }

}

