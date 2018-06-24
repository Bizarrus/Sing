/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.console;

public class ConstantData {
    private ConstantData() {
    }

    public static enum ConsoleFontSize {
        a(-1),
        b(20),
        c(16),
        d(12);
        
        private final int e;

        private ConsoleFontSize(int n2) {
            this.e = n2;
        }

        public static ConsoleFontSize a(int n) {
            for (ConsoleFontSize consoleFontSize : ConsoleFontSize.values()) {
                if (consoleFontSize.a() != n) continue;
                return consoleFontSize;
            }
            return a;
        }

        public int a() {
            return this.e;
        }
    }

    public static enum MsgType {
        a,
        b,
        c,
        d,
        e,
        f;
        

        private MsgType() {
        }
    }

}

