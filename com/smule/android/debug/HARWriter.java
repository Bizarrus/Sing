package com.smule.android.debug;

import android.content.Context;
import android.util.Base64;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mopub.common.AdType;
import com.smule.android.AppDelegate;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

public class HARWriter {
    private static final String f15915h = HARWriter.class.getName();
    private Context f15916a;
    private File f15917b;
    private Date f15918c = new Date();
    private DateFormat f15919d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.getDefault());
    private int f15920e;
    private Writer f15921f;
    private ObjectMapper f15922g = new ObjectMapper();

    private static class CountingSource implements Source {
        private Source f15903a;
        private long f15904b;

        CountingSource(Source source) {
            this.f15903a = source;
        }

        long m17706a() {
            return this.f15904b;
        }

        public long read(Buffer buffer, long j) throws IOException {
            long read = this.f15903a.read(buffer, j);
            this.f15904b += read;
            return read;
        }

        public Timeout timeout() {
            return this.f15903a.timeout();
        }

        public void close() throws IOException {
            this.f15903a.close();
        }
    }

    public static class EntryData {
        public Date f15905a;
        public long f15906b;
        public long f15907c;
        public Request f15908d;
        public long f15909e;
        public long f15910f;
        public Response f15911g;
        public String f15912h;

        public int m17707a() {
            return 1048576;
        }
    }

    private static class LimitedBufferedSink extends BufferedSinkWrapper {
        private int f15913a;
        private int f15914b;

        LimitedBufferedSink(BufferedSink bufferedSink, int i) {
            super(bufferedSink);
            this.f15914b = i;
        }

        public BufferedSink mo6246a(byte[] bArr, int i, int i2) throws IOException {
            int min = Math.min(this.f15913a + i2, this.f15914b);
            super.mo6246a(bArr, i, min);
            this.f15913a = min + this.f15913a;
            return this;
        }
    }

    public HARWriter(Context context, File file) {
        this.f15916a = context;
        this.f15917b = file;
        this.f15922g.enable(SerializationFeature.INDENT_OUTPUT);
        this.f15922g.configure(Feature.AUTO_CLOSE_TARGET, false);
        if (file.isDirectory()) {
            this.f15917b = new File(file, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US).format(new Date()) + ".har-tmp");
            return;
        }
        this.f15917b = file;
        m17719d();
    }

    public File m17723a() {
        return this.f15917b;
    }

    public void m17725b() {
        if (this.f15921f == null) {
            m17719d();
            m17721f();
            m17720e();
        }
    }

    public synchronized void m17727c() {
        if (this.f15921f != null) {
            m17722g();
            m17720e();
            try {
                this.f15921f.close();
            } catch (IOException e) {
            }
            this.f15921f = null;
            String absolutePath = this.f15917b.getAbsolutePath();
            if (!this.f15917b.renameTo(new File(absolutePath.substring(0, absolutePath.length() - 4)))) {
                this.f15917b.delete();
            }
        }
    }

    public synchronized void m17724a(EntryData entryData) {
        try {
            m17726b(entryData);
        } catch (Throwable e) {
            Log.d(f15915h, "write error", e);
        }
    }

    public void m17726b(EntryData entryData) {
        if (this.f15920e > 0) {
            m17716a(",\n");
        }
        this.f15920e++;
        long j = entryData.f15910f - entryData.f15906b;
        long j2 = entryData.f15907c - entryData.f15906b;
        long j3 = entryData.f15909e - entryData.f15907c;
        long j4 = entryData.f15910f - entryData.f15909e;
        Object hashMap = new HashMap();
        hashMap.put("comment", entryData.f15912h);
        hashMap.put("pagref", "log");
        hashMap.put("startedDateTime", this.f15919d.format(entryData.f15905a));
        hashMap.put("time", Long.valueOf(j));
        hashMap.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, m17713a(entryData.f15908d, entryData.m17707a()));
        hashMap.put("response", m17714a(entryData.f15911g, (long) entryData.m17707a()));
        hashMap.put("cache", new HashMap());
        HashMap hashMap2 = new HashMap();
        hashMap2.put("blocked", Integer.valueOf(-1));
        hashMap2.put("dns", Integer.valueOf(-1));
        hashMap2.put("connect", Integer.valueOf(-1));
        hashMap2.put("send", Long.valueOf(j2));
        hashMap2.put("wait", Long.valueOf(j3));
        hashMap2.put("receive", Long.valueOf(j4));
        hashMap.put("timings", hashMap2);
        m17715a(hashMap);
        m17720e();
    }

    private Map<String, Object> m17713a(Request request, int i) {
        long j = 0;
        Map hashMap = new HashMap();
        Headers c = request.m27887c();
        hashMap.put("method", request.m27885b());
        hashMap.put("url", request.m27884a().toString());
        hashMap.put("httpVersion", "HTTP/1.1");
        hashMap.put("cookies", m17711a(request));
        hashMap.put("headers", m17709a(c));
        hashMap.put("queryString", m17710a(request.m27884a()));
        hashMap.put("headersSize", Long.valueOf(m17717b(c)));
        RequestBody d = request.m27888d();
        if (d != null) {
            try {
                j = d.contentLength();
            } catch (IOException e) {
            }
        }
        hashMap.put("bodySize", Long.valueOf(j));
        if (request.m27885b().equals("POST") && d != null) {
            MediaType contentType = d.contentType();
            HashMap hashMap2 = new HashMap();
            hashMap2.put("mimeType", contentType.toString());
            if (d instanceof FormBody) {
                List arrayList = new ArrayList();
                FormBody formBody = (FormBody) d;
                for (int i2 = 0; i2 < formBody.m27665a(); i2++) {
                    Map hashMap3 = new HashMap();
                    hashMap3.put("name", formBody.m27666a(i2));
                    hashMap3.put("value", formBody.m27667b(i2));
                    arrayList.add(hashMap3);
                }
                hashMap2.put(NativeProtocol.WEB_DIALOG_PARAMS, arrayList);
            } else {
                try {
                    Object buffer = new Buffer();
                    d.writeTo(new LimitedBufferedSink(buffer, i));
                    hashMap2.put("text", buffer.a(Charset.forName("ISO-8859-1")));
                } catch (Throwable e2) {
                    Log.d(f15915h, "couldn't get body text", e2);
                }
            }
            hashMap.put("postData", hashMap2);
        }
        return hashMap;
    }

    private List<Map> m17711a(Request request) {
        List<Map> arrayList = new ArrayList();
        Headers c = request.m27887c();
        for (int i = 0; i < c.m27687a(); i++) {
            if (c.m27688a(i).equals("Cookie")) {
                Cookie a = Cookie.m27636a(request.m27884a(), c.m27690b(i));
                if (a != null) {
                    Map hashMap = new HashMap();
                    hashMap.put("name", a.m27639a());
                    hashMap.put("value", a.m27640b());
                    arrayList.add(hashMap);
                }
            }
        }
        return arrayList;
    }

    private List<Map> m17709a(Headers headers) {
        List<Map> arrayList = new ArrayList();
        for (int i = 0; i < headers.m27687a(); i++) {
            String a = headers.m27688a(i);
            if (!a.equalsIgnoreCase("cookie")) {
                Map hashMap = new HashMap();
                hashMap.put("name", a);
                hashMap.put("value", headers.m27690b(i));
                hashMap.put("comment", "");
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    private long m17717b(Headers headers) {
        long j = 0;
        for (int i = 0; i < headers.m27687a(); i++) {
            j = ((j + ((long) headers.m27688a(i).length())) + ((long) headers.m27690b(i).length())) + 4;
        }
        return 2 + j;
    }

    private List<Map> m17710a(HttpUrl httpUrl) {
        List<Map> arrayList = new ArrayList();
        for (int i = 0; i < httpUrl.m27758n(); i++) {
            Map hashMap = new HashMap();
            hashMap.put("name", httpUrl.m27739a(i));
            hashMap.put("value", httpUrl.m27741b(i));
            hashMap.put("comment", "");
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private Map<String, Object> m17714a(Response response, long j) {
        Headers g = response.m27936g();
        Object hashMap = new HashMap();
        hashMap.put("status", Integer.valueOf(response.m27932c()));
        hashMap.put("statusText", response.m27934e());
        hashMap.put("httpVersion", response.m27931b().toString().toUpperCase());
        hashMap.put("cookies", m17712a(response));
        hashMap.put("headers", m17709a(g));
        hashMap.put("content", m17718b(response, j));
        hashMap.put("headersSize", Long.valueOf(m17717b(g)));
        hashMap.put("bodySize", Integer.valueOf(-1));
        Response m = response.m27942m();
        if (m == null || !m.m27939j()) {
            hashMap.put("redirectURL", "");
        } else {
            hashMap.put("redirectURL", response.m27929a().m27884a());
        }
        return hashMap;
    }

    private List<Map> m17712a(Response response) {
        List<Map> arrayList = new ArrayList();
        Headers g = response.m27936g();
        for (int i = 0; i < g.m27687a(); i++) {
            if (g.m27688a(i).equals("Set-Cookie")) {
                Cookie a = Cookie.m27636a(response.m27929a().m27884a(), g.m27690b(i));
                if (a != null) {
                    Map hashMap = new HashMap();
                    hashMap.put("name", a.m27639a());
                    hashMap.put("value", a.m27640b());
                    hashMap.put("path", a.m27644f());
                    hashMap.put("domain", a.m27643e());
                    hashMap.put("httpOnly", Boolean.valueOf(a.m27645g()));
                    hashMap.put("secure", Boolean.valueOf(a.m27646h()));
                    if (a.m27641c()) {
                        hashMap.put("expires", this.f15919d.format(new Date(a.m27642d())));
                    }
                    arrayList.add(hashMap);
                }
            }
        }
        return arrayList;
    }

    private Map<String, Object> m17718b(Response response, long j) {
        Map<String, Object> hashMap = new HashMap();
        try {
            ResponseBody realResponseBody;
            CountingSource countingSource;
            Object str;
            ResponseBody a = response.m27930a(j);
            if ("gzip".equals(response.m27927a("Content-Encoding"))) {
                Source countingSource2 = new CountingSource(a.source());
                Source source = countingSource2;
                realResponseBody = new RealResponseBody(response.m27936g(), Okio.a(new GzipSource(countingSource2)));
                countingSource = source;
            } else {
                countingSource = null;
                realResponseBody = a;
            }
            MediaType contentType = a.contentType();
            byte[] bytes = realResponseBody.bytes();
            if (contentType.m27771a().equals("text") || (contentType.m27771a().equals("application") && contentType.m27773b().equals(AdType.STATIC_NATIVE))) {
                str = new String(bytes, contentType.m27772a(Charset.forName("UTF-8")));
            } else {
                str = Base64.encodeToString(bytes, 0);
            }
            hashMap.put("text", str);
            hashMap.put("compression", Long.valueOf(((long) bytes.length) - (countingSource != null ? countingSource.m17706a() : (long) bytes.length)));
            hashMap.put("size", Integer.valueOf(bytes.length));
            hashMap.put("mimeType", contentType.toString());
        } catch (IOException e) {
            hashMap.put("size", Integer.valueOf(0));
            hashMap.put("mimeType", "application/octet-stream");
            hashMap.put("text", "error decoding response body: " + e);
        }
        return hashMap;
    }

    private void m17719d() {
        try {
            this.f15921f = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f15917b, true), Charset.forName("UTF-8")));
        } catch (Throwable e) {
            Log.d(f15915h, "open error", e);
        }
    }

    private void m17716a(String str) {
        try {
            this.f15921f.write(str);
        } catch (Throwable e) {
            Log.d(f15915h, "write error", e);
        }
    }

    private void m17715a(Object obj) {
        try {
            this.f15922g.writeValue(this.f15921f, obj);
        } catch (Throwable e) {
            Log.d(f15915h, "write error", e);
        }
    }

    private void m17720e() {
        try {
            this.f15921f.flush();
        } catch (IOException e) {
        }
    }

    private void m17721f() {
        String writeValueAsString;
        Map hashMap = new HashMap();
        AppDelegate d = MagicNetwork.d();
        String appVersion = d.getAppVersion();
        hashMap.put("version", appVersion);
        int i = this.f15916a.getApplicationInfo().labelRes;
        Object string = i != 0 ? this.f15916a.getString(i) : "app";
        Map hashMap2 = new HashMap();
        hashMap2.put("name", d.getAppUID());
        hashMap2.put("version", appVersion);
        hashMap2.put("comment", string);
        hashMap.put("creator", hashMap2);
        hashMap2 = new HashMap();
        hashMap2.put("name", string);
        hashMap2.put("version", appVersion);
        hashMap.put("browser", hashMap2);
        Map hashMap3 = new HashMap();
        hashMap3.put("startedDateTime", this.f15919d.format(this.f15918c));
        hashMap3.put("id", "log");
        hashMap3.put("title", "Request Log - " + d.getServerHost());
        Map hashMap4 = new HashMap();
        hashMap4.put("onContentLoad", Integer.valueOf(-1));
        hashMap4.put("onLoad", Integer.valueOf(-1));
        hashMap3.put("pageTimings", hashMap4);
        hashMap.put("pages", new Map[]{hashMap3});
        hashMap3 = new HashMap();
        hashMap3.put("log", hashMap);
        try {
            writeValueAsString = this.f15922g.writeValueAsString(hashMap3);
        } catch (Throwable e) {
            Log.d(f15915h, "write error", e);
            writeValueAsString = "";
        }
        int i2 = 2;
        int length = writeValueAsString.length() - 1;
        while (i2 > 0 && length > 0) {
            if (writeValueAsString.charAt(length) == '}') {
                i2--;
            }
            length--;
        }
        m17716a(writeValueAsString.substring(0, length));
        m17716a(",\n\t\"entries\": [\n");
    }

    private void m17722g() {
        m17716a("\n\t\t]\n\t}\n}\n");
    }
}
