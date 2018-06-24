/*
 * Decompiled with CFR 0_123.
 */
package com.smule.samples.player;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;

public enum SimPlayerError implements IError
{
    a(1, "Could not initialize"),
    b(2, "Could not start player"),
    c(3, "Could not stop player"),
    d(4, "Could not close player");
    
    private int e;
    private int f;
    private String g;

    private SimPlayerError(int n2, String string3) {
        this.f = n2;
        this.g = string3;
        this.e = ErrorHelper.a("SIM_PLAYER_ERROR_CODE_OFFSET");
    }

    @Override
    public String a() {
        return this.g;
    }
}

