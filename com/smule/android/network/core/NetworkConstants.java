package com.smule.android.network.core;

public class NetworkConstants {

    public enum Timeout {
        DEFAULT(15000),
        MEDIUM(30000),
        LONG(60000),
        VERY_LONG(600000),
        INFINITE(0);
        
        private int mTimeout;

        private Timeout(int i) {
            this.mTimeout = i;
        }

        public int getTimeout() {
            return this.mTimeout;
        }
    }
}
