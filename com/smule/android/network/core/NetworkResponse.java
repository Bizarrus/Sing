/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 *  com.fasterxml.jackson.core.JsonProcessingException
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  com.fasterxml.jackson.databind.node.ObjectNode
 *  okhttp3.MediaType
 *  okhttp3.Response
 *  okhttp3.ResponseBody
 *  okio.BufferedSource
 *  okio.Okio
 *  okio.Source
 */
package com.smule.android.network.core;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.utils.JsonUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public class NetworkResponse
extends ResponseBody {
    private static final String p = NetworkResponse.class.getName();
    private static boolean s = false;
    public  a = .e;
    public int b = -1;
    public String c;
    public int d;
    public String e;
    public int f;
    public String g;
    public long h;
    public long i;
    public String j;
    public Response k;
    public JsonNode l;
    public String m;
    @JsonIgnore
    private MediaType mMediaType = MediaType.a((String)"application/json; charset=UTF-8");
    protected JsonNode n;
    public String o;
    private String q;
    private boolean r;

    private NetworkResponse() {
    }

    public NetworkResponse(String string2) {
        if (string2 != null) {
            this.j = string2;
            this.b(string2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public NetworkResponse(Response response, String string2, boolean bl) {
        this.q = string2;
        this.r = bl;
        if (response != null) {
            string2 = "";
            try {
                String string3;
                string2 = string3 = NetworkUtils.a(response);
            }
            catch (RuntimeException runtimeException) {
                this.a = .f;
            }
            this.j = string2;
            if (!bl) {
                this.b(string2);
            }
            this.a(response);
            this.k = response;
        }
    }

    public static int a(JsonNode jsonNode, String string2, int n) {
        return NetworkResponse.a(jsonNode, string2, n, new <Integer>(){

            public Integer a(JsonNode jsonNode, Integer n) {
                return jsonNode.asInt(n.intValue());
            }
        });
    }

    public static long a(JsonNode jsonNode, String string2, long l) {
        return NetworkResponse.a(jsonNode, string2, l, new <Long>(){

            public Long a(JsonNode jsonNode, Long l) {
                return jsonNode.asLong(l.longValue());
            }
        });
    }

    protected static JsonNode a(JsonNode jsonNode, String string2) {
        if (jsonNode != null && (jsonNode = jsonNode.get(string2)) != null) {
            return jsonNode;
        }
        return null;
    }

    public static NetworkResponse a() {
        NetworkResponse networkResponse = new NetworkResponse();
        networkResponse.a = .a;
        networkResponse.b = 0;
        return networkResponse;
    }

    public static NetworkResponse a(int n) {
        NetworkResponse networkResponse = new NetworkResponse();
        networkResponse.a = .a;
        networkResponse.b = n;
        return networkResponse;
    }

    public static NetworkResponse a( status) {
        NetworkResponse networkResponse = new NetworkResponse();
        networkResponse.a = status;
        return networkResponse;
    }

    public static NetworkResponse a(@NonNull String string2, @NonNull NetworkResponse networkResponse, JsonNode object) {
        block4 : {
            StatusNode statusNode = new StatusNode();
            statusNode.status.status = networkResponse.a.ordinal();
            statusNode.status.code = networkResponse.b;
            statusNode.status.message = networkResponse.c;
            statusNode.status.info = networkResponse.e;
            statusNode.status.internalErrorMessage = networkResponse.m;
            statusNode = JsonUtils.a().valueToTree((Object)statusNode);
            if (object != null) {
                ((ObjectNode)statusNode).set("data", (JsonNode)object);
            }
            object = new NetworkResponse();
            object.q = string2;
            try {
                string2 = JsonUtils.a().writeValueAsString((Object)statusNode);
                if (string2 == null) break block4;
            }
            catch (JsonProcessingException jsonProcessingException) {
                NetworkResponse networkResponse2 = new NetworkResponse();
                networkResponse2.q = networkResponse.q;
                networkResponse2.a = networkResponse.a;
                networkResponse2.b = networkResponse.b;
                networkResponse2.c = networkResponse.c;
                networkResponse2.e = networkResponse.e;
                networkResponse2.m = networkResponse.m;
                networkResponse2.i();
                return networkResponse2;
            }
            object.j = string2;
            object.b(string2);
        }
        return object;
    }

    private static <T> T a(JsonNode jsonNode, String iterator, T t, <T> valueGetter) {
        iterator = new ArrayList<String>(Arrays.asList(iterator.split("\\.")));
        String string2 = (String)iterator.remove(iterator.size() - 1);
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            jsonNode = NetworkResponse.a(jsonNode, (String)iterator.next());
        }
        iterator = t;
        if (jsonNode != null) {
            jsonNode = jsonNode.get(string2);
            iterator = t;
            if (jsonNode != null) {
                iterator = valueGetter.a(jsonNode, t);
            }
        }
        return (T)iterator;
    }

    public static boolean a(JsonNode jsonNode, String string2, boolean bl) {
        boolean bl2 = bl;
        if (jsonNode != null) {
            jsonNode = jsonNode.get(string2);
            bl2 = bl;
            if (jsonNode != null) {
                bl2 = jsonNode.asBoolean(bl);
            }
        }
        return bl2;
    }

    public static NetworkResponse b() {
        return NetworkResponse.a(.d);
    }

    public static String b(JsonNode jsonNode, String string2) {
        return NetworkResponse.a(jsonNode, string2, null, new <String>(){

            public String a(JsonNode jsonNode, String string2) {
                return jsonNode.asText(string2);
            }
        });
    }

    public static String b( status) {
        switch (.a[status.ordinal()]) {
            default: {
                return "Unknown";
            }
            case 1: {
                return "OK";
            }
            case 2: {
                return "Connection timeout";
            }
            case 3: {
                return "Unknown host";
            }
            case 4: {
                return "General failure";
            }
            case 5: {
                return "Uninitialized";
            }
            case 6: {
                return "Call canceled";
            }
            case 7: 
        }
        return "Server maintenance";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void i() {
        if (this.j != null) return;
        StatusNode statusNode = new StatusNode();
        statusNode.status.status = this.a.ordinal();
        statusNode.status.code = this.b;
        statusNode.status.message = this.c;
        statusNode.status.info = this.e;
        statusNode.status.internalErrorMessage = this.m;
        try {
            this.j = JsonUtils.a().writeValueAsString((Object)statusNode);
            return;
        }
        catch (JsonProcessingException jsonProcessingException) {
            Log.d(p, "could not generate JSON body:info:" + this.e + " message:" + this.c + " internalErrorMessage:" + this.m, (Throwable)jsonProcessingException);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{\"status\":{\"status\":").append(this.a.ordinal()).append(", \"code\":").append(this.b);
            if (this.c != null) {
                stringBuilder.append(",\"message\":\"").append(this.c).append("\"");
            }
            if (this.e != null) {
                stringBuilder.append(",\"info\":\"").append(this.e).append("\"");
            }
            stringBuilder.append(",\"version\":1}}");
            this.j = stringBuilder.toString();
            return;
        }
    }

    public int a(String string2, int n) {
        return NetworkResponse.a(this.l, string2, n);
    }

    public long a(String string2, long l) {
        return NetworkResponse.a(this.l, string2, l);
    }

    public String a(String string2) {
        return NetworkResponse.b(this.l, string2);
    }

    protected void a(Response object) {
        if ((object = object.a("ETag")) != null) {
            this.o = object;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void b(String string2) {
        block7 : {
            block8 : {
                if (string2 == null || string2.equals("")) {
                    Log.d("NetworkResponse", "Empty response from server");
                    return;
                }
                ObjectMapper objectMapper = JsonUtils.a();
                try {
                    this.n = (JsonNode)objectMapper.readValue(string2, JsonNode.class);
                    if (this.n.has("status")) {
                        objectMapper = this.n.get("status");
                        int n = NetworkResponse.a((JsonNode)objectMapper, "status", -1);
                        if (n != -1) {
                            this.a = .values()[n];
                        }
                        this.b = NetworkResponse.a((JsonNode)objectMapper, "code", 1);
                        this.c = NetworkResponse.b((JsonNode)objectMapper, "message");
                        this.e = NetworkResponse.b((JsonNode)objectMapper, "info");
                        this.d = NetworkResponse.a((JsonNode)objectMapper, "version", -1);
                        this.m = NetworkResponse.b((JsonNode)objectMapper, "internalErrorMessage");
                    }
                    if (this.n.has("data")) {
                        this.l = this.n.get("data");
                        objectMapper = this.l.has("loginResult") ? this.l.get("loginResult") : this.l;
                        this.f = NetworkResponse.a((JsonNode)objectMapper, "reason", 0);
                        this.g = NetworkResponse.b((JsonNode)objectMapper, "sessionToken");
                        this.h = NetworkResponse.a((JsonNode)objectMapper, "sessionTtl", 0);
                        this.i = NetworkResponse.a((JsonNode)objectMapper, "serverTime", 0);
                    }
                    if (this.b == 0) break block7;
                    if (this.b != 51) break block8;
                    Log.d(p, "Session expired");
                    break block7;
                }
                catch (IOException iOException) {
                    Log.c("NetworkResponse", "Error parsing json response: " + string2, iOException);
                    return;
                }
            }
            Log.e("NetworkResponse", "Error code returned from server: " + this.b + ", for API " + this.q);
        }
        this.c(String.format("response (%s) : %s", this.q, "[Cleansed]"));
    }

    protected void c(String string2) {
        String string3 = string2;
        if (s) {
            do {
                string3 = string2;
                if (string2.length() <= 1024) break;
                Log.a("NetworkResponse", string2.substring(0, 1024));
                string2 = string2.substring(1024);
            } while (true);
        }
        Log.a("NetworkResponse", string3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean c() {
        if (this.r) {
            int n = -1;
            if (this.k == null) return false;
            n = this.k.c();
            if (200 == n) return true;
            return false;
        }
        if (this.a != .a || this.b != 0) return false;
        return true;
    }

    public long contentLength() {
        this.i();
        return this.j.length();
    }

    public MediaType contentType() {
        return this.mMediaType;
    }

    public boolean d() {
        if (this.k != null && this.k.c() == 201) {
            return true;
        }
        return false;
    }

    public boolean e() {
        if (this.b == 1012) {
            return true;
        }
        return false;
    }

    public JsonNode f() {
        return this.l;
    }

    public String g() {
        return this.q;
    }

    public Integer h() {
        if (this.a == .a) {
            return this.b;
        }
        return null;
    }

    public BufferedSource source() {
        this.i();
        return Okio.a((Source)Okio.a((InputStream)new ByteArrayInputStream(this.j.getBytes(this.mMediaType.c()))));
    }

    public String toString() {
        return this.j;
    }

    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    public static class StatusNode {
        public StatusObject status = new StatusObject();
    }

    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    public static class StatusObject {
        public int code;
        public String info;
        public String internalErrorMessage;
        public String message;
        public int status;
        public int version = 1;
    }

}

