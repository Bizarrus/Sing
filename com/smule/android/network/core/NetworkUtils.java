/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.util.Pair
 *  io.fabric.sdk.android.services.concurrency.internal.AbstractFuture
 *  okhttp3.Headers
 *  okhttp3.HttpUrl
 *  okhttp3.Request
 *  okhttp3.RequestBody
 *  okhttp3.Response
 *  okhttp3.ResponseBody
 *  retrofit2.Call
 *  retrofit2.Response
 */
package com.smule.android.network.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Pair;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import io.fabric.sdk.android.services.concurrency.internal.AbstractFuture;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.SyncFailedException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class NetworkUtils {
    private static final String a = NetworkUtils.class.getName();

    public static long a(Headers headers) {
        long l = 0;
        for (int i = 0; i < headers.a(); ++i) {
            l += (long)(headers.a(i).length() + ": ".length() + headers.b(i).length() + 2);
        }
        return l;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static long a(Request request) {
        long l;
        long l2;
        block3 : {
            l2 = request.b().length() + 1 + request.a().toString().length() + " HTTP/1.1".length() + 2;
            l = NetworkUtils.a(request.c()) + l2;
            try {
                if (request.d() == null) break block3;
                l2 = request.d().contentLength();
            }
            catch (IOException iOException) {
                Log.d(a, "error getting content length:" + iOException);
                return l;
            }
            do {
                return l2 + l;
                break;
            } while (true);
        }
        l2 = 0;
        return l2 + l;
    }

    private static BroadcastReceiver a(Context context, Runnable object, final boolean bl) {
        object = new BroadcastReceiver((Runnable)object){
            final /* synthetic */ Runnable b;
            {
                this.b = runnable;
            }

            public void onReceive(Context context, Intent intent) {
                boolean bl2 = NetworkUtils.a(context);
                Log.c(a, "Network connectivity changed. Connected=" + bl2);
                if (!bl || bl2) {
                    this.b.run();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver((BroadcastReceiver)object, intentFilter);
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static NetworkResponse a(Call<NetworkResponse> response) {
        try {
            response = response.execute();
            if (response.body() != null) {
                return (NetworkResponse)((Object)response.body());
            }
            NetworkResponse networkResponse = new NetworkResponse(response.errorBody().string());
            response = response.raw() != null && response.raw().c() > 0 ? response.raw() : null;
            networkResponse.k = response;
            return networkResponse;
        }
        catch (Exception exception) {
            Log.d(a, "Exception when calling API", exception);
            NetworkResponse networkResponse = new NetworkResponse(null);
            networkResponse.a = NetworkResponse.d;
            return networkResponse;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2, long l, String charSequence, String string3) {
        String string4;
        StringBuilder stringBuilder = new StringBuilder().append("").append(l).append((String)charSequence);
        if (string4 != null) {
            do {
                return NetworkUtils.makeDigest(string2, stringBuilder.append(string4).toString().getBytes());
                break;
            } while (true);
        }
        string4 = "";
        return NetworkUtils.makeDigest(string2, stringBuilder.append(string4).toString().getBytes());
    }

    public static String a(List<Pair<String, String>> object, String string2, String string3) {
        Collections.sort(object, new Comparator<Pair<String, String>>(){

            public int a(Pair<String, String> pair, Pair<String, String> pair2) {
                return ((String)pair.first).compareTo((String)pair2.first);
            }

            @Override
            public /* synthetic */ int compare(Object object, Object object2) {
                return this.a((Pair)object, (Pair)object2);
            }
        });
        StringBuilder stringBuilder = new StringBuilder();
        object = object.iterator();
        while (object.hasNext()) {
            Pair pair = (Pair)object.next();
            stringBuilder.append((String)pair.first).append((String)pair.second);
        }
        if (string3 != null) {
            stringBuilder.append(string3);
        }
        return NetworkUtils.makeDigest(string2, stringBuilder.toString().getBytes());
    }

    public static String a(Response response) {
        return NetworkUtils.a(response, null);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(Response var0, ProgressListener var1_2) {
        var5_3 = "";
        if (var0.h() == null) {
            return var5_3;
        }
        var0 = new BufferedReader(new InputStreamReader(var0.h().byteStream()));
        var6_4 = new StringBuffer("");
        var7_5 = new char[4096];
        while ((var2_6 = var0.read(var7_5, 0, var7_5.length)) > 0) {
            if (Thread.currentThread().isInterrupted()) {
                var0.close();
                throw new RuntimeException("API call canceled during read.");
            }
            if (var1_2 == null) ** GOTO lbl23
        }
        ** GOTO lbl25
        {
            var3_7 = var2_6;
            try {
                var1_2.b(var3_7);
                var1_2.a();
                ** GOTO lbl23
            }
            catch (IOException var0_1) {
                block6 : {
                    Log.c(NetworkUtils.a, "Error reading body", var0_1);
                    var0 = var5_3;
                    break block6;
lbl23: // 2 sources:
                    var6_4.append(var7_5, 0, var2_6);
                    continue;
lbl25: // 1 sources:
                    var0.close();
                    var0 = var6_4.toString();
                }
                if (Thread.interrupted() == false) return var0;
                throw new RuntimeException("API call canceled after read.");
                break;
            }
        }
    }

    public static <T extends ParsedResponse> Future<T> a(final Call<NetworkResponse> call, final Class<T> class_) {
        return new AbstractFuture<T>(){
            {
                MagicNetwork.a(new Runnable(){

                    @Override
                    public void run() {
                        Object t = ParsedResponse.a(NetworkUtils.a(call), class_);
                        1.this.a(t);
                    }
                });
            }

            protected void a() {
                call.cancel();
            }

        };
    }

    public static void a(Context context, Runnable runnable) {
        NetworkUtils.b(context, runnable, true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void a(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    private static void a(OutputStream outputStream) throws IOException {
        outputStream.flush();
        try {
            if (outputStream instanceof FileOutputStream) {
                ((FileOutputStream)outputStream).getFD().sync();
            }
            return;
        }
        catch (SyncFailedException syncFailedException) {
            return;
        }
    }

    public static boolean a(Context context) {
        if ((context = ((ConnectivityManager)context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo()) != null && context.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean a(Context context, boolean bl) {
        NetworkInfo networkInfo = ((ConnectivityManager)context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        boolean bl2 = NetworkUtils.b(context);
        if (networkInfo != null && networkInfo.isConnected() && (!bl || bl2)) {
            return true;
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    public static boolean a(MagicNetwork var0, OutputStream var1_1, ProgressListener var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[DOLOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:419)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:471)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2880)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:837)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:217)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:162)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:95)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:357)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:769)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:701)
        // org.benf.cfr.reader.Main.doJar(Main.java:134)
        // org.benf.cfr.reader.Main.main(Main.java:189)
        throw new IllegalStateException("Decompilation failed");
    }

    public static long b(Headers object) {
        if ((object = object.a("Content-Length")) != null) {
            return Integer.parseInt((String)object) + 2;
        }
        return 0;
    }

    public static long b(Response response) {
        Headers headers = response.g();
        long l = "HTTP/1.1 XXX ".length() + response.e().length() + 2;
        long l2 = NetworkUtils.a(headers);
        return NetworkUtils.b(headers) + (l + l2);
    }

    public static void b(Context context, Runnable runnable) {
        NetworkUtils.b(context, runnable, false);
    }

    private static void b(Context context, Runnable runnable, boolean bl) {
        if (!bl || NetworkUtils.a(context)) {
            runnable.run();
        }
        NetworkUtils.a(context, runnable, bl);
    }

    public static boolean b(Context context) {
        if ((context = ((ConnectivityManager)context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo()) != null && context.getType() == 1) {
            return true;
        }
        return false;
    }

    private static native String makeDigest(String var0, byte[] var1);

    public static interface ProgressListener {
        public void a();

        public void a(long var1);

        public void b(long var1);
    }

}

