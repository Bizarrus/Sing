/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.purchases;

public class Consts {
    public static long a = -1;
    public static long b = -2;

    public static enum PurchaseState {
        a,
        b,
        c;
        

        private PurchaseState() {
        }

        public static PurchaseState a(int n) {
            PurchaseState[] arrpurchaseState = PurchaseState.values();
            if (n < 0 || n >= arrpurchaseState.length) {
                return b;
            }
            return arrpurchaseState[n];
        }
    }

    public static enum ResponseCode {
        a,
        b,
        c,
        d,
        e,
        f,
        g;
        

        private ResponseCode() {
        }

        public static ResponseCode a(int n) {
            ResponseCode[] arrresponseCode = ResponseCode.values();
            if (-1005 == n) {
                return b;
            }
            if (n < 0 || n >= arrresponseCode.length) {
                return g;
            }
            return arrresponseCode[n];
        }
    }

}

