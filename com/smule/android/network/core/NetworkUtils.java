package com.smule.android.network.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Pair;
import com.smule.android.logging.Log;
import io.fabric.sdk.android.services.concurrency.internal.AbstractFuture;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.SyncFailedException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class NetworkUtils {
    private static final String f16484a = NetworkUtils.class.getName();

    static class C35442 implements Comparator<Pair<String, String>> {
        C35442() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m18097a((Pair) obj, (Pair) obj2);
        }

        public int m18097a(Pair<String, String> pair, Pair<String, String> pair2) {
            return ((String) pair.first).compareTo((String) pair2.first);
        }
    }

    public interface ProgressListener {
        void mo6953a();

        void mo6954a(long j);

        void mo6955b(long j);
    }

    private static native String makeDigest(String str, byte[] bArr);

    public static String m18108a(Response response) {
        return m18109a(response, null);
    }

    public static NetworkResponse m18104a(Call<NetworkResponse> call) {
        NetworkResponse networkResponse;
        try {
            retrofit2.Response execute = call.execute();
            if (execute.body() != null) {
                return (NetworkResponse) execute.body();
            }
            networkResponse = new NetworkResponse(execute.errorBody().string());
            Response raw = (execute.raw() == null || execute.raw().m27932c() <= 0) ? null : execute.raw();
            networkResponse.k = raw;
            return networkResponse;
        } catch (Throwable e) {
            Log.d(f16484a, "Exception when calling API", e);
            networkResponse = new NetworkResponse(null);
            networkResponse.a = NetworkResponse$Status.FAILURE;
            return networkResponse;
        }
    }

    public static <T extends ParsedResponse> Future<T> m18110a(final Call<NetworkResponse> call, final Class<T> cls) {
        return new AbstractFuture<T>() {

            class C35421 implements Runnable {
                final /* synthetic */ C35431 f16479a;

                C35421(C35431 c35431) {
                    this.f16479a = c35431;
                }

                public void run() {
                    this.f16479a.m18093a((Object) ParsedResponse.a(NetworkUtils.m18104a(call), cls));
                }
            }

            protected void mo6259a() {
                call.cancel();
            }
        };
    }

    public static String m18109a(Response response, ProgressListener progressListener) {
        String str = "";
        if (response.m27937h() != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.m27937h().byteStream()));
                StringBuffer stringBuffer = new StringBuffer("");
                char[] cArr = new char[4096];
                while (true) {
                    int read = bufferedReader.read(cArr, 0, cArr.length);
                    if (read <= 0) {
                        break;
                    } else if (Thread.currentThread().isInterrupted()) {
                        bufferedReader.close();
                        throw new RuntimeException("API call canceled during read.");
                    } else {
                        if (progressListener != null) {
                            progressListener.mo6955b((long) read);
                            progressListener.mo6953a();
                        }
                        stringBuffer.append(cArr, 0, read);
                    }
                }
                bufferedReader.close();
                str = stringBuffer.toString();
            } catch (Throwable e) {
                Log.c(f16484a, "Error reading body", e);
            }
            if (Thread.interrupted()) {
                throw new RuntimeException("API call canceled after read.");
            }
        }
        return str;
    }

    public static long m18102a(Request request) {
        long a = m18101a(request.m27887c()) + ((long) ((((request.m27885b().length() + 1) + request.m27884a().toString().length()) + " HTTP/1.1".length()) + 2));
        try {
            return (request.m27888d() != null ? request.m27888d().contentLength() : 0) + a;
        } catch (IOException e) {
            Log.d(f16484a, "error getting content length:" + e);
            return a;
        }
    }

    public static long m18116b(Response response) {
        Headers g = response.m27936g();
        return m18115b(g) + (((long) (("HTTP/1.1 XXX ".length() + response.m27934e().length()) + 2)) + m18101a(g));
    }

    public static long m18101a(Headers headers) {
        long j = 0;
        for (int i = 0; i < headers.m27687a(); i++) {
            j += (long) (((headers.m27688a(i).length() + ": ".length()) + headers.m27690b(i).length()) + 2);
        }
        return j;
    }

    public static long m18115b(Headers headers) {
        String a = headers.m27689a("Content-Length");
        return a != null ? (long) (Integer.parseInt(a) + 2) : 0;
    }

    public static String m18107a(List<Pair<String, String>> list, String str, String str2) {
        Collections.sort(list, new C35442());
        StringBuilder stringBuilder = new StringBuilder();
        for (Pair pair : list) {
            stringBuilder.append((String) pair.first).append((String) pair.second);
        }
        if (str2 != null) {
            stringBuilder.append(str2);
        }
        return makeDigest(str, stringBuilder.toString().getBytes());
    }

    public static String m18106a(String str, long j, String str2, String str3) {
        StringBuilder append = new StringBuilder().append("").append(j).append(str2);
        if (str3 == null) {
            str3 = "";
        }
        return makeDigest(str, append.append(str3).toString().getBytes());
    }

    public static boolean m18114a(MagicNetwork$StreamResponse magicNetwork$StreamResponse, OutputStream outputStream, ProgressListener progressListener) {
        IOException e;
        Throwable th;
        if (progressListener != null) {
            progressListener.mo6954a(magicNetwork$StreamResponse.f16468e);
        }
        Closeable bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            try {
                byte[] bArr = new byte[4096];
                do {
                    int read = magicNetwork$StreamResponse.f16467d.read(bArr);
                    if (read < 0) {
                        bufferedOutputStream.flush();
                        m18112a(outputStream);
                        m18111a(bufferedOutputStream);
                        m18111a((Closeable) outputStream);
                        m18111a(magicNetwork$StreamResponse.f16467d);
                        return true;
                    }
                    if (progressListener != null) {
                        progressListener.mo6955b((long) read);
                        progressListener.mo6953a();
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                } while (!Thread.currentThread().isInterrupted());
                magicNetwork$StreamResponse.f16466c.mo7158c();
                throw new RuntimeException("Server call canceled during read.");
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            bufferedOutputStream = null;
            try {
                String message = e.getMessage();
                if (message == null || !message.startsWith("No space")) {
                    m18111a(bufferedOutputStream);
                    m18111a((Closeable) outputStream);
                    m18111a(magicNetwork$StreamResponse.f16467d);
                    return false;
                }
                m18111a(bufferedOutputStream);
                m18111a((Closeable) outputStream);
                m18111a(magicNetwork$StreamResponse.f16467d);
                return false;
            } catch (Throwable th2) {
                th = th2;
                m18111a(bufferedOutputStream);
                m18111a((Closeable) outputStream);
                m18111a(magicNetwork$StreamResponse.f16467d);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            m18111a(bufferedOutputStream);
            m18111a((Closeable) outputStream);
            m18111a(magicNetwork$StreamResponse.f16467d);
            throw th;
        }
    }

    private static void m18111a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private static void m18112a(OutputStream outputStream) throws IOException {
        outputStream.flush();
        try {
            if (outputStream instanceof FileOutputStream) {
                ((FileOutputStream) outputStream).getFD().sync();
            }
        } catch (SyncFailedException e) {
        }
    }

    public static BroadcastReceiver m18103a(Context context, final Runnable runnable) {
        BroadcastReceiver c35453 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Log.c(NetworkUtils.f16484a, "Network connectivity changed.");
                if (NetworkUtils.m18113a(context)) {
                    Log.c(NetworkUtils.f16484a, "Network just connected");
                    runnable.run();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(c35453, intentFilter);
        return c35453;
    }

    public static void m18117b(Context context, Runnable runnable) {
        if (m18113a(context)) {
            runnable.run();
        }
        m18103a(context, runnable);
    }

    public static boolean m18113a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
