package com.smule.android.purchases;

public class IabException extends Exception {
    IabResult f17524a;

    public IabException(IabResult iabResult) {
        this(iabResult, null);
    }

    public IabException(int i, String str) {
        this(new IabResult(i, str));
    }

    public IabException(IabResult iabResult, Exception exception) {
        super(iabResult.m18659b(), exception);
        this.f17524a = iabResult;
    }

    public IabException(int i, String str, Exception exception) {
        this(new IabResult(i, str), exception);
    }

    public IabResult m18630a() {
        return this.f17524a;
    }
}
