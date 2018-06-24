/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.purchases;

import com.smule.android.purchases.IabHelper;

public class IabResult {
    int a;
    String b;

    public IabResult(int n, String string2) {
        this.a = n;
        if (string2 == null || string2.trim().length() == 0) {
            this.b = IabHelper.a(n);
            return;
        }
        this.b = string2 + " (response: " + IabHelper.a(n) + ")";
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        if (this.a == 0) {
            return true;
        }
        return false;
    }

    public boolean d() {
        if (!this.c()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "IabResult: " + this.b();
    }
}

