/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  okhttp3.Response
 */
package com.smule.android.network.core;

import java.io.IOException;
import okhttp3.Response;

public class ServerException
extends IOException {
    public Response a;
    public String b;

    public ServerException(Response response, String string2) {
        this.fillInStackTrace();
        this.a = response;
        this.b = string2;
    }

    private static String a(Response object, String string2) {
        if ((object = object.a(string2)) != null) {
            return object;
        }
        return null;
    }

    public int a() {
        return this.a.c();
    }

    public String b() {
        return ServerException.a(this.a, "server");
    }

    @Override
    public String toString() {
        return "ServerException (" + this.b() + "): " + this.a();
    }
}

