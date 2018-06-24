/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.models;

public class SongbookEntryId {
    public Type a;
    public String b;
    public String c;

    public SongbookEntryId(String string2, String string3, String string4) throws IllegalArgumentException {
        if (string2 == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (string3 == null && string4 == null) {
            throw new IllegalArgumentException("songId and arrKey cannot both be null");
        }
        this.a = Type.valueOf(string2);
        this.b = string3;
        this.c = string4;
    }

    public static enum Type {
        a,
        b;
        

        private Type() {
        }
    }

}

