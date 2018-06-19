package com.smule.android.network.core;

import java.io.IOException;
import okhttp3.Response;

public class ServerException extends IOException {
    public Response f16485a;
    public String f16486b;

    public int m18119a() {
        return this.f16485a.m27932c();
    }

    public String m18120b() {
        return m18118a(this.f16485a, "server");
    }

    public ServerException(Response response, String str) {
        fillInStackTrace();
        this.f16485a = response;
        this.f16486b = str;
    }

    public String toString() {
        return "ServerException (" + m18120b() + "): " + m18119a();
    }

    private static String m18118a(Response response, String str) {
        String a = response.m27927a(str);
        return a != null ? a : null;
    }
}
