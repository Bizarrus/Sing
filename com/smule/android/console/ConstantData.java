package com.smule.android.console;

public class ConstantData {

    public enum ConsoleFontSize {
        UNKNOWN(-1),
        BIG(20),
        MEDIUM(16),
        SMALL(12);
        
        private final int f15741e;

        private ConsoleFontSize(int i) {
            this.f15741e = i;
        }

        public int m17550a() {
            return this.f15741e;
        }

        public static ConsoleFontSize m17549a(int i) {
            for (ConsoleFontSize consoleFontSize : values()) {
                if (consoleFontSize.m17550a() == i) {
                    return consoleFontSize;
                }
            }
            return UNKNOWN;
        }
    }

    public enum MsgType {
        STRING,
        CLEAR,
        SHOWSRES,
        SETFONTSIZE,
        SHOWFONTSIZE,
        EXIT
    }

    private ConstantData() {
    }
}
