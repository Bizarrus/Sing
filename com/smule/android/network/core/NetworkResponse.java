package com.smule.android.network.core;

import android.support.annotation.NonNull;
import android.support.v4.view.PointerIconCompat;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.millennialmedia.InterstitialAd.InterstitialErrorStatus;
import com.smule.android.logging.Log;
import com.smule.android.utils.JsonUtils;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;

public class NetworkResponse extends ResponseBody {
    private static final String f6753p = NetworkResponse.class.getName();
    private static boolean f6754s = false;
    public Status f6755a = Status.e;
    public int f6756b = -1;
    public String f6757c;
    public int f6758d;
    public String f6759e;
    public int f6760f;
    public String f6761g;
    public long f6762h;
    public long f6763i;
    public String f6764j;
    public Response f6765k;
    public JsonNode f6766l;
    public String f6767m;
    @JsonIgnore
    private MediaType mMediaType = MediaType.a("application/json; charset=UTF-8");
    protected JsonNode f6768n;
    public String f6769o;
    private String f6770q;
    private boolean f6771r;

    @JsonInclude(Include.NON_NULL)
    public static class StatusNode {
        public StatusObject status = new StatusObject();
    }

    @JsonInclude(Include.NON_NULL)
    public static class StatusObject {
        public int code;
        public String info;
        public String internalErrorMessage;
        public String message;
        public int status;
        public int version = 1;
    }

    public NetworkResponse(Response response, String str, boolean z) {
        this.f6770q = str;
        this.f6771r = z;
        if (response != null) {
            String str2 = "";
            try {
                str2 = NetworkUtils.a(response);
            } catch (RuntimeException e) {
                this.f6755a = Status.f;
            }
            this.f6764j = str2;
            if (!z) {
                m7848b(str2);
            }
            m7847a(response);
            this.f6765k = response;
        }
    }

    public NetworkResponse(String str) {
        if (str != null) {
            this.f6764j = str;
            m7848b(str);
        }
    }

    private NetworkResponse() {
    }

    public static NetworkResponse m7834a() {
        NetworkResponse networkResponse = new NetworkResponse();
        networkResponse.f6755a = Status.a;
        networkResponse.f6756b = 0;
        return networkResponse;
    }

    public static NetworkResponse m7835a(int i) {
        NetworkResponse networkResponse = new NetworkResponse();
        networkResponse.f6755a = Status.a;
        networkResponse.f6756b = i;
        return networkResponse;
    }

    public static NetworkResponse m7837a(@NonNull String str, @NonNull NetworkResponse networkResponse, JsonNode jsonNode) {
        StatusNode statusNode = new StatusNode();
        statusNode.status.status = networkResponse.f6755a.ordinal();
        statusNode.status.code = networkResponse.f6756b;
        statusNode.status.message = networkResponse.f6757c;
        statusNode.status.info = networkResponse.f6759e;
        statusNode.status.internalErrorMessage = networkResponse.f6767m;
        JsonNode valueToTree = JsonUtils.a().valueToTree(statusNode);
        if (jsonNode != null) {
            ((ObjectNode) valueToTree).set(ShareConstants.WEB_DIALOG_PARAM_DATA, jsonNode);
        }
        NetworkResponse networkResponse2 = new NetworkResponse();
        networkResponse2.f6770q = str;
        try {
            String writeValueAsString = JsonUtils.a().writeValueAsString(valueToTree);
            if (writeValueAsString == null) {
                return networkResponse2;
            }
            networkResponse2.f6764j = writeValueAsString;
            networkResponse2.m7848b(writeValueAsString);
            return networkResponse2;
        } catch (JsonProcessingException e) {
            networkResponse2 = new NetworkResponse();
            networkResponse2.f6770q = networkResponse.f6770q;
            networkResponse2.f6755a = networkResponse.f6755a;
            networkResponse2.f6756b = networkResponse.f6756b;
            networkResponse2.f6757c = networkResponse.f6757c;
            networkResponse2.f6759e = networkResponse.f6759e;
            networkResponse2.f6767m = networkResponse.f6767m;
            networkResponse2.m7843h();
            return networkResponse2;
        }
    }

    public static NetworkResponse m7840b() {
        return m7836a(Status.d);
    }

    public static NetworkResponse m7836a(Status status) {
        NetworkResponse networkResponse = new NetworkResponse();
        networkResponse.f6755a = status;
        return networkResponse;
    }

    public MediaType contentType() {
        return this.mMediaType;
    }

    public long contentLength() {
        m7843h();
        return (long) this.f6764j.length();
    }

    private void m7843h() {
        if (this.f6764j == null) {
            StatusNode statusNode = new StatusNode();
            statusNode.status.status = this.f6755a.ordinal();
            statusNode.status.code = this.f6756b;
            statusNode.status.message = this.f6757c;
            statusNode.status.info = this.f6759e;
            statusNode.status.internalErrorMessage = this.f6767m;
            try {
                this.f6764j = JsonUtils.a().writeValueAsString(statusNode);
            } catch (Throwable e) {
                Log.m7775d(f6753p, "could not generate JSON body:info:" + this.f6759e + " message:" + this.f6757c + " internalErrorMessage:" + this.f6767m, e);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{\"status\":{\"status\":").append(this.f6755a.ordinal()).append(", \"code\":").append(this.f6756b);
                if (this.f6757c != null) {
                    stringBuilder.append(",\"message\":\"").append(this.f6757c).append("\"");
                }
                if (this.f6759e != null) {
                    stringBuilder.append(",\"info\":\"").append(this.f6759e).append("\"");
                }
                stringBuilder.append(",\"version\":1}}");
                this.f6764j = stringBuilder.toString();
            }
        }
    }

    public BufferedSource source() {
        m7843h();
        return Okio.a(Okio.a(new ByteArrayInputStream(this.f6764j.getBytes(this.mMediaType.c()))));
    }

    public String toString() {
        return this.f6764j;
    }

    public boolean m7850c() {
        if (this.f6771r) {
            int i = -1;
            if (this.f6765k != null) {
                i = this.f6765k.c();
            }
            if (200 == i) {
                return true;
            }
            return false;
        } else if (this.f6755a == Status.a && this.f6756b == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean m7851d() {
        return this.f6765k != null && this.f6765k.c() == InterstitialErrorStatus.EXPIRED;
    }

    public boolean m7852e() {
        return this.f6756b == PointerIconCompat.TYPE_NO_DROP;
    }

    public String m7846a(String str) {
        return m7841b(this.f6766l, str);
    }

    public int m7844a(String str, int i) {
        return m7831a(this.f6766l, str, i);
    }

    public long m7845a(String str, long j) {
        return m7832a(this.f6766l, str, j);
    }

    protected void m7848b(String str) {
        if (str == null || str.equals("")) {
            Log.m7774d("NetworkResponse", "Empty response from server");
            return;
        }
        try {
            JsonNode jsonNode;
            this.f6768n = (JsonNode) JsonUtils.a().readValue(str, JsonNode.class);
            if (this.f6768n.has("status")) {
                jsonNode = this.f6768n.get("status");
                int a = m7831a(jsonNode, "status", -1);
                if (a != -1) {
                    this.f6755a = Status.values()[a];
                }
                this.f6756b = m7831a(jsonNode, "code", 1);
                this.f6757c = m7841b(jsonNode, "message");
                this.f6759e = m7841b(jsonNode, "info");
                this.f6758d = m7831a(jsonNode, "version", -1);
                this.f6767m = m7841b(jsonNode, "internalErrorMessage");
            }
            if (this.f6768n.has(ShareConstants.WEB_DIALOG_PARAM_DATA)) {
                this.f6766l = this.f6768n.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                jsonNode = this.f6766l.has("loginResult") ? this.f6766l.get("loginResult") : this.f6766l;
                this.f6760f = m7831a(jsonNode, "reason", 0);
                this.f6761g = m7841b(jsonNode, "sessionToken");
                this.f6762h = m7832a(jsonNode, "sessionTtl", 0);
                this.f6763i = m7832a(jsonNode, "serverTime", 0);
            }
            if (this.f6756b != 0) {
                if (this.f6756b == 51) {
                    Log.m7774d(f6753p, "Session expired");
                } else {
                    Log.m7776e("NetworkResponse", "Error code returned from server: " + this.f6756b + ", for API " + this.f6770q);
                }
            }
            m7849c(String.format("response (%s) : %s", new Object[]{this.f6770q, "[Cleansed]"}));
        } catch (Throwable e) {
            Log.m7773c("NetworkResponse", "Error parsing json response: " + str, e);
        }
    }

    protected void m7849c(String str) {
        if (f6754s) {
            while (str.length() > 1024) {
                Log.m7767a("NetworkResponse", str.substring(0, 1024));
                str = str.substring(1024);
            }
        }
        Log.m7767a("NetworkResponse", str);
    }

    protected void m7847a(Response response) {
        String a = response.a("ETag");
        if (a != null) {
            this.f6769o = a;
        }
    }

    protected static JsonNode m7833a(JsonNode jsonNode, String str) {
        if (jsonNode != null) {
            JsonNode jsonNode2 = jsonNode.get(str);
            if (jsonNode2 != null) {
                return jsonNode2;
            }
        }
        return null;
    }

    public static String m7841b(JsonNode jsonNode, String str) {
        return (String) m7838a(jsonNode, str, null, new 2());
    }

    public static int m7831a(JsonNode jsonNode, String str, int i) {
        return ((Integer) m7838a(jsonNode, str, Integer.valueOf(i), new 3())).intValue();
    }

    public static long m7832a(JsonNode jsonNode, String str, long j) {
        return ((Long) m7838a(jsonNode, str, Long.valueOf(j), new 4())).longValue();
    }

    private static <T> T m7838a(JsonNode jsonNode, String str, T t, ValueGetter<T> valueGetter) {
        ArrayList arrayList = new ArrayList(Arrays.asList(str.split("\\.")));
        String str2 = (String) arrayList.remove(arrayList.size() - 1);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jsonNode = m7833a(jsonNode, (String) it.next());
        }
        if (jsonNode == null) {
            return t;
        }
        JsonNode jsonNode2 = jsonNode.get(str2);
        if (jsonNode2 != null) {
            return valueGetter.a(jsonNode2, t);
        }
        return t;
    }

    public static boolean m7839a(JsonNode jsonNode, String str, boolean z) {
        if (jsonNode == null) {
            return z;
        }
        JsonNode jsonNode2 = jsonNode.get(str);
        if (jsonNode2 != null) {
            return jsonNode2.asBoolean(z);
        }
        return z;
    }

    public JsonNode m7853f() {
        return this.f6766l;
    }

    public String m7854g() {
        return this.f6770q;
    }

    public static String m7842b(Status status) {
        switch (5.a[status.ordinal()]) {
            case 1:
                return "OK";
            case 2:
                return "Connection timeout";
            case 3:
                return "Unknown host";
            case 4:
                return "General failure";
            case 5:
                return "Uninitialized";
            case 6:
                return "Call canceled";
            case 7:
                return "Server maintenance";
            default:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }
}
