/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.purchases;

import com.smule.android.purchases.IabResult;

public class IabException
extends Exception {
    IabResult a;

    public IabException(int n, String string2) {
        this(new IabResult(n, string2));
    }

    public IabException(int n, String string2, Exception exception) {
        this(new IabResult(n, string2), exception);
    }

    public IabException(IabResult iabResult) {
        this(iabResult, null);
    }

    public IabException(IabResult iabResult, Exception exception) {
        super(iabResult.b(), exception);
        this.a = iabResult;
    }

    public IabResult a() {
        return this.a;
    }
}

