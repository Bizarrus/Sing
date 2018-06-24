/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.purchases;

import com.smule.android.purchases.Base64DecoderException;

public class Base64 {
    static final /* synthetic */ boolean a;
    private static final byte[] b;
    private static final byte[] c;
    private static final byte[] d;
    private static final byte[] e;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !Base64.class.desiredAssertionStatus();
        a = bl;
        b = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        c = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        d = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};
        e = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};
    }

    private Base64() {
    }

    private static int a(byte[] arrby, int n, byte[] arrby2, int n2, byte[] arrby3) {
        if (arrby[n + 2] == 61) {
            arrby2[n2] = (byte)((arrby3[arrby[n]] << 24 >>> 6 | arrby3[arrby[n + 1]] << 24 >>> 12) >>> 16);
            return 1;
        }
        if (arrby[n + 3] == 61) {
            n = arrby3[arrby[n]] << 24 >>> 6 | arrby3[arrby[n + 1]] << 24 >>> 12 | arrby3[arrby[n + 2]] << 24 >>> 18;
            arrby2[n2] = (byte)(n >>> 16);
            arrby2[n2 + 1] = (byte)(n >>> 8);
            return 2;
        }
        n = arrby3[arrby[n]] << 24 >>> 6 | arrby3[arrby[n + 1]] << 24 >>> 12 | arrby3[arrby[n + 2]] << 24 >>> 18 | arrby3[arrby[n + 3]] << 24 >>> 24;
        arrby2[n2] = (byte)(n >> 16);
        arrby2[n2 + 1] = (byte)(n >> 8);
        arrby2[n2 + 2] = (byte)n;
        return 3;
    }

    public static byte[] a(String arrby) throws Base64DecoderException {
        arrby = arrby.getBytes();
        return Base64.a(arrby, 0, arrby.length);
    }

    public static byte[] a(byte[] arrby, int n, int n2) throws Base64DecoderException {
        return Base64.a(arrby, n, n2, d);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static byte[] a(byte[] arrby, int n, int n2, byte[] arrby2) throws Base64DecoderException {
        byte[] arrby3 = new byte[n2 * 3 / 4 + 2];
        byte[] arrby4 = new byte[4];
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        while (n3 < n2) {
            byte by = (byte)(arrby[n3 + n] & 127);
            int n6 = arrby2[by];
            if (n6 < -5) {
                throw new Base64DecoderException("Bad Base64 input character at " + n3 + ": " + arrby[n3 + n] + "(decimal)");
            }
            if (n6 >= -1) {
                if (by == 61) {
                    n6 = n2 - n3;
                    n = (byte)(arrby[n2 - 1 + n] & 127);
                    if (n4 == 0 || n4 == 1) {
                        throw new Base64DecoderException("invalid padding byte '=' at byte offset " + n3);
                    }
                    if (n4 == 3 && n6 > 2 || n4 == 4 && n6 > 1) {
                        throw new Base64DecoderException("padding byte '=' falsely signals end of encoded value at offset " + n3);
                    }
                    if (n == 61 || n == 10) break;
                    throw new Base64DecoderException("encoded value has invalid trailing byte");
                }
                n6 = n4 + 1;
                arrby4[n4] = by;
                if (n6 == 4) {
                    n4 = Base64.a(arrby4, 0, arrby3, n5, arrby2) + n5;
                    n5 = 0;
                } else {
                    n4 = n5;
                    n5 = n6;
                }
            } else {
                n6 = n5;
                n5 = n4;
                n4 = n6;
            }
            n6 = n3 + 1;
            n3 = n4;
            n4 = n5;
            n5 = n3;
            n3 = n6;
        }
        n = n5;
        if (n4 != 0) {
            if (n4 == 1) {
                throw new Base64DecoderException("single trailing character at offset " + (n2 - 1));
            }
            arrby4[n4] = 61;
            n = n5 + Base64.a(arrby4, 0, arrby3, n5, arrby2);
        }
        arrby = new byte[n];
        System.arraycopy(arrby3, 0, arrby, 0, n);
        return arrby;
    }
}

