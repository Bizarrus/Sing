package com.smule.android.network.core;

/* synthetic */ class NetworkResponse$5 {
    static final /* synthetic */ int[] f16469a = new int[NetworkResponse$Status.values().length];

    static {
        try {
            f16469a[NetworkResponse$Status.OK.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f16469a[NetworkResponse$Status.CONNECTION_TIMEOUT.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f16469a[NetworkResponse$Status.UNKNOWN_HOST.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f16469a[NetworkResponse$Status.FAILURE.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f16469a[NetworkResponse$Status.UNINITIALIZED.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f16469a[NetworkResponse$Status.CALL_CANCELED.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f16469a[NetworkResponse$Status.SERVER_MAINTENANCE.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
