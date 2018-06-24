/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.common;

public enum JoinSectionType {
    a("invites"),
    b("feed"),
    c("profile"),
    d("search"),
    e("all"),
    f("hot"),
    g("all_video"),
    h("");
    
    private String i;

    private JoinSectionType(String string3) {
        this.i = string3;
    }

    public String a() {
        return this.i;
    }
}

