/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.util.Base64
 *  com.fasterxml.jackson.core.JsonGenerator
 *  com.fasterxml.jackson.core.JsonGenerator$Feature
 *  com.fasterxml.jackson.core.JsonProcessingException
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  com.fasterxml.jackson.databind.SerializationFeature
 *  okhttp3.Cookie
 *  okhttp3.FormBody
 *  okhttp3.Headers
 *  okhttp3.HttpUrl
 *  okhttp3.MediaType
 *  okhttp3.Protocol
 *  okhttp3.Request
 *  okhttp3.RequestBody
 *  okhttp3.Response
 *  okhttp3.ResponseBody
 *  okhttp3.internal.http.RealResponseBody
 *  okio.Buffer
 *  okio.BufferedSink
 *  okio.BufferedSource
 *  okio.GzipSource
 *  okio.Okio
 *  okio.Source
 *  okio.Timeout
 */
package com.smule.android.debug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Base64;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smule.android.debug.BufferedSinkWrapper;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

public class HARWriter {
    private static final String h = HARWriter.class.getName();
    private Context a;
    private File b;
    private Date c;
    private DateFormat d;
    private int e;
    private Writer f;
    private ObjectMapper g;

    public HARWriter(Context object, File file) {
        this.a = object;
        this.b = file;
        this.c = new Date();
        this.d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.getDefault());
        this.g = new ObjectMapper();
        this.g.enable(SerializationFeature.INDENT_OUTPUT);
        this.g.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        if (file.isDirectory()) {
            object = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US);
            this.b = new File(file, object.format(new Date()) + ".har-tmp");
            return;
        }
        this.b = file;
        this.d();
    }

    private List<Map> a(Headers headers) {
        ArrayList<Map> arrayList = new ArrayList<Map>();
        for (int i = 0; i < headers.a(); ++i) {
            String string2 = headers.a(i);
            if (string2.equalsIgnoreCase("cookie")) continue;
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("name", string2);
            hashMap.put("value", headers.b(i));
            hashMap.put("comment", "");
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private List<Map> a(HttpUrl httpUrl) {
        ArrayList<Map> arrayList = new ArrayList<Map>();
        for (int i = 0; i < httpUrl.n(); ++i) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("name", httpUrl.a(i));
            hashMap.put("value", httpUrl.b(i));
            hashMap.put("comment", "");
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private List<Map> a(Request request) {
        ArrayList<Map> arrayList = new ArrayList<Map>();
        Headers headers = request.c();
        for (int i = 0; i < headers.a(); ++i) {
            Cookie cookie;
            if (!headers.a(i).equals("Cookie") || (cookie = Cookie.a((HttpUrl)request.a(), (String)headers.b(i))) == null) continue;
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("name", cookie.a());
            hashMap.put("value", cookie.b());
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private List<Map> a(Response response) {
        ArrayList<Map> arrayList = new ArrayList<Map>();
        Headers headers = response.g();
        for (int i = 0; i < headers.a(); ++i) {
            Object object;
            if (!headers.a(i).equals("Set-Cookie") || (object = Cookie.a((HttpUrl)response.a().a(), (String)headers.b(i))) == null) continue;
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("name", object.a());
            hashMap.put("value", object.b());
            hashMap.put("path", object.f());
            hashMap.put("domain", object.e());
            hashMap.put("httpOnly", object.g());
            hashMap.put("secure", object.h());
            if (object.c()) {
                object = new Date(object.d());
                hashMap.put("expires", this.d.format((Date)object));
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Map<String, Object> a(Request hashMap, int n) {
        long l = 0;
        HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
        Headers headers = hashMap.c();
        hashMap2.put("method", hashMap.b());
        hashMap2.put("url", hashMap.a().toString());
        hashMap2.put("httpVersion", "HTTP/1.1");
        hashMap2.put("cookies", this.a((Request)hashMap));
        hashMap2.put("headers", this.a(headers));
        hashMap2.put("queryString", this.a(hashMap.a()));
        hashMap2.put("headersSize", this.b(headers));
        headers = hashMap.d();
        long l2 = l;
        if (headers != null) {
            try {
                l2 = headers.contentLength();
            }
            catch (IOException iOException) {
                l2 = l;
            }
        }
        hashMap2.put("bodySize", l2);
        if (hashMap.b().equals("POST") && headers != null) {
            Object object = headers.contentType();
            hashMap = new HashMap();
            hashMap.put("mimeType", object.toString());
            if (headers instanceof FormBody) {
                object = new ArrayList();
                headers = (FormBody)headers;
                for (n = 0; n < headers.a(); ++n) {
                    HashMap<String, String> hashMap3 = new HashMap<String, String>();
                    hashMap3.put("name", headers.a(n));
                    hashMap3.put("value", headers.b(n));
                    object.add(hashMap3);
                }
                hashMap.put("params", object);
            } else {
                try {
                    object = new Buffer();
                    headers.writeTo((BufferedSink)new LimitedBufferedSink((BufferedSink)object, n));
                    hashMap.put("text", object.a(Charset.forName("ISO-8859-1")));
                }
                catch (IOException iOException) {
                    Log.d(h, "couldn't get body text", iOException);
                }
            }
            hashMap2.put("postData", hashMap);
        }
        return hashMap2;
    }

    private Map<String, Object> a(Response response, long l) {
        Headers headers = response.g();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("status", response.c());
        hashMap.put("statusText", response.e());
        hashMap.put("httpVersion", response.b().toString().toUpperCase());
        hashMap.put("cookies", this.a(response));
        hashMap.put("headers", this.a(headers));
        hashMap.put("content", this.b(response, l));
        hashMap.put("headersSize", this.b(headers));
        hashMap.put("bodySize", -1);
        headers = response.m();
        if (headers != null && headers.j()) {
            hashMap.put("redirectURL", (Object)response.a().a());
            return hashMap;
        }
        hashMap.put("redirectURL", "");
        return hashMap;
    }

    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    private ResponseBody a(Response response, CountingSource countingSource) {
        countingSource = new GzipSource((Source)countingSource);
        return new RealResponseBody(response.g(), Okio.a((Source)countingSource));
    }

    private void a(Object object) {
        try {
            this.g.writeValue(this.f, object);
            return;
        }
        catch (IOException iOException) {
            Log.d(h, "write error", iOException);
            return;
        }
    }

    private void a(String string2) {
        try {
            this.f.write(string2);
            return;
        }
        catch (IOException iOException) {
            Log.d(h, "write error", iOException);
            return;
        }
    }

    private long b(Headers headers) {
        long l = 0;
        for (int i = 0; i < headers.a(); ++i) {
            l = l + (long)headers.a(i).length() + (long)headers.b(i).length() + 4;
        }
        return 2 + l;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Map<String, Object> b(Response arrby, long l) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        try {
            Object object;
            byte[] arrby2;
            ResponseBody responseBody = arrby.a(l);
            if ("gzip".equals(arrby.a("Content-Encoding"))) {
                arrby2 = new byte[]((Source)responseBody.source());
                object = this.a((Response)arrby, (CountingSource)arrby2);
                arrby = arrby2;
            } else {
                arrby = null;
                object = responseBody;
            }
            responseBody = responseBody.contentType();
            arrby2 = object.bytes();
            object = responseBody.a().equals("text") || responseBody.a().equals("application") && responseBody.b().equals("json") ? new String(arrby2, responseBody.a(Charset.forName("UTF-8"))) : Base64.encodeToString((byte[])arrby2, (int)0);
            hashMap.put("text", object);
            if (arrby != null) {
                l = arrby.a();
            } else {
                int n = arrby2.length;
                l = n;
            }
            hashMap.put("compression", (long)arrby2.length - l);
            hashMap.put("size", arrby2.length);
            hashMap.put("mimeType", responseBody.toString());
            return hashMap;
        }
        catch (IOException iOException) {
            hashMap.put("size", 0);
            hashMap.put("mimeType", "application/octet-stream");
            hashMap.put("text", "error decoding response body: " + iOException);
            return hashMap;
        }
    }

    private void d() {
        try {
            this.f = new BufferedWriter(new OutputStreamWriter((OutputStream)new FileOutputStream(this.b, true), Charset.forName("UTF-8")));
            return;
        }
        catch (FileNotFoundException fileNotFoundException) {
            Log.d(h, "open error", fileNotFoundException);
            return;
        }
    }

    private void e() {
        try {
            this.f.flush();
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void f() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Object object = MagicNetwork.d();
        String string2 = object.getAppVersion();
        hashMap.put("version", string2);
        int n = this.a.getApplicationInfo().labelRes;
        Object object2 = n != 0 ? this.a.getString(n) : "app";
        HashMap<String, String> hashMap2 = new HashMap<String, String>();
        hashMap2.put("name", object.getAppUID());
        hashMap2.put("version", string2);
        hashMap2.put("comment", (String)object2);
        hashMap.put("creator", hashMap2);
        hashMap2 = new HashMap();
        hashMap2.put("name", (String)object2);
        hashMap2.put("version", string2);
        hashMap.put("browser", hashMap2);
        object2 = new HashMap();
        object2.put("startedDateTime", this.d.format(this.c));
        object2.put("id", "log");
        object2.put("title", "Request Log - " + object.getServerHost());
        object = new HashMap();
        object.put("onContentLoad", -1);
        object.put("onLoad", -1);
        object2.put("pageTimings", object);
        hashMap.put("pages", new Map[]{object2});
        object2 = new HashMap<String, String>();
        object2.put((String)"log", hashMap);
        try {
            object2 = this.g.writeValueAsString(object2);
        }
        catch (JsonProcessingException jsonProcessingException) {
            Log.d(h, "write error", (Throwable)jsonProcessingException);
            object2 = "";
        }
        n = object2.length();
        int n2 = 2;
        --n;
        while (n2 > 0 && n > 0) {
            int n3 = n2;
            if (object2.charAt(n) == '}') {
                n3 = n2 - 1;
            }
            --n;
            n2 = n3;
        }
        this.a(object2.substring(0, n));
        this.a(",\n\t\"entries\": [\n");
    }

    private void g() {
        this.a("\n\t\t]\n\t}\n}\n");
    }

    public File a() {
        return this.b;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void a(EntryData entryData) {
        synchronized (this) {
            try {
                this.b(entryData);
                do {
                    return;
                    break;
                } while (true);
            }
            catch (Exception exception) {
                Log.d(h, "write error", exception);
                return;
            }
            finally {
            }
        }
    }

    public void b() {
        if (this.f == null) {
            this.d();
            this.f();
            this.e();
        }
    }

    public void b(EntryData object) {
        if (this.e > 0) {
            this.a(",\n");
        }
        ++this.e;
        long l = object.f;
        long l2 = object.b;
        long l3 = object.c;
        long l4 = object.b;
        long l5 = object.e;
        long l6 = object.c;
        long l7 = object.f;
        long l8 = object.e;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("comment", object.h);
        hashMap.put("pagref", "log");
        hashMap.put("startedDateTime", this.d.format(object.a));
        hashMap.put("time", l - l2);
        hashMap.put("request", this.a(object.d, object.a()));
        hashMap.put("response", this.a(object.g, (long)object.a()));
        hashMap.put("cache", new HashMap());
        object = new HashMap();
        object.put("blocked", -1);
        object.put("dns", -1);
        object.put("connect", -1);
        object.put("send", l3 - l4);
        object.put("wait", l5 - l6);
        object.put("receive", l7 - l8);
        hashMap.put("timings", object);
        this.a(hashMap);
        this.e();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void c() {
        synchronized (this) {
            if (this.f != null) {
                this.g();
                this.e();
                try {
                    this.f.close();
                }
                catch (IOException iOException) {}
                this.f = null;
                String string2 = this.b.getAbsolutePath();
                if (!this.b.renameTo(new File(string2 = string2.substring(0, string2.length() - 4)))) {
                    this.b.delete();
                }
            }
            return;
        }
    }

    private static class CountingSource
    implements Source {
        private Source a;
        private long b;

        CountingSource(Source source) {
            this.a = source;
        }

        long a() {
            return this.b;
        }

        public void close() throws IOException {
            this.a.close();
        }

        public long read(Buffer buffer, long l) throws IOException {
            l = this.a.read(buffer, l);
            this.b += l;
            return l;
        }

        public Timeout timeout() {
            return this.a.timeout();
        }
    }

    public static class EntryData {
        public Date a;
        public long b;
        public long c;
        public Request d;
        public long e;
        public long f;
        public Response g;
        public String h;

        public int a() {
            return 1048576;
        }
    }

    private static class LimitedBufferedSink
    extends BufferedSinkWrapper {
        private int a;
        private int b;

        LimitedBufferedSink(BufferedSink bufferedSink, int n) {
            super(bufferedSink);
            this.b = n;
        }

        @Override
        public BufferedSink a(byte[] arrby, int n, int n2) throws IOException {
            n2 = Math.min(this.a + n2, this.b);
            super.a(arrby, n, n2);
            this.a = n2 + this.a;
            return this;
        }
    }

}

