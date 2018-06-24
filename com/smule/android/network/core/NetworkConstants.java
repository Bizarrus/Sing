/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.core;

public class NetworkConstants {

    public static enum Timeout {
        DEFAULT(15000),
        MEDIUM(30000),
        MEDIUM_LONG(45000),
        LONG(60000),
        VERY_LONG(600000),
        INFINITE(0);
        
        private int mTimeout;

        private Timeout(int n2) {
            this.mTimeout = n2;
        }

        public int getTimeout() {
            return this.mTimeout;
        }
    }

}

