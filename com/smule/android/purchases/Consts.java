package com.smule.android.purchases;

public class Consts {
    public static long f17510a = -1;
    public static long f17511b = -2;

    public enum PurchaseState {
        PURCHASED,
        CANCELED,
        REFUNDED;

        public static PurchaseState m18618a(int i) {
            PurchaseState[] values = values();
            if (i < 0 || i >= values.length) {
                return CANCELED;
            }
            return values[i];
        }
    }

    public enum ResponseCode {
        RESULT_OK,
        RESULT_USER_CANCELED,
        RESULT_SERVICE_UNAVAILABLE,
        RESULT_BILLING_UNAVAILABLE,
        RESULT_ITEM_UNAVAILABLE,
        RESULT_DEVELOPER_ERROR,
        RESULT_ERROR;

        public static ResponseCode m18619a(int i) {
            ResponseCode[] values = values();
            if (-1005 == i) {
                return RESULT_USER_CANCELED;
            }
            if (i < 0 || i >= values.length) {
                return RESULT_ERROR;
            }
            return values[i];
        }
    }
}
