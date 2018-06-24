/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.os.SystemClock
 *  okhttp3.Interceptor
 *  okhttp3.Interceptor$Chain
 *  okhttp3.MediaType
 *  okhttp3.Request
 *  okhttp3.Request$Builder
 *  okhttp3.RequestBody
 *  okhttp3.Response
 *  okio.BufferedSink
 */
package com.smule.android.debug;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.smule.android.debug.HARWriter;
import com.smule.android.logging.BaseEventLog2Listener;
import com.smule.android.logging.EventLogger2;
import com.smule.android.network.core.MagicNetwork;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class RequestRecorder {
    private static RequestRecorder a;
    private State b;
    private HARWriter c;
    private Interceptor d;
    private EventLogger2 e;
    private String f;

    private RequestRecorder() {
        this.d = new RecordingInterceptor();
        this.b = State.a;
    }

    public static RequestRecorder a() {
        synchronized (RequestRecorder.class) {
            if (a == null) {
                a = new RequestRecorder();
            }
            RequestRecorder requestRecorder = a;
            return requestRecorder;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void h() {
        String string2 = this.c != null ? this.c.a().getAbsolutePath() : null;
        File[] arrfile = this.e().listFiles();
        if (arrfile == null) return;
        int n = arrfile.length;
        int n2 = 0;
        while (n2 < n) {
            File file = arrfile[n2];
            String string3 = file.getAbsolutePath();
            if (string3.endsWith(".har-tmp") && !string3.equals(string2)) {
                new HARWriter(this.l(), file).c();
            }
            ++n2;
        }
    }

    private SharedPreferences i() {
        return this.l().getSharedPreferences("har-recorder", 0);
    }

    private void j() {
        this.k();
        File file = this.e();
        file.mkdirs();
        this.c = new HARWriter(this.l(), file);
        this.c.b();
    }

    private void k() {
        if (this.c != null) {
            this.c.c();
            this.c = null;
        }
    }

    private Context l() {
        return MagicNetwork.d().getApplicationContext();
    }

    private String m() {
        synchronized (this) {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.e != null) {
                stringBuilder.append("Last event: ");
                stringBuilder.append(this.e.b);
                if (this.e.d != null) {
                    stringBuilder.append(" context=");
                    stringBuilder.append(this.e.d);
                }
                if (this.e.c != null) {
                    stringBuilder.append(" target=");
                    stringBuilder.append(this.e.c);
                }
            }
            if (this.f != null) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append('\n');
                }
                stringBuilder.append("Last activity: ");
                stringBuilder.append(this.f);
            }
            stringBuilder = stringBuilder.toString();
            return stringBuilder;
        }
    }

    /*
     * Exception decompiling
     */
    public void a(State var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: First case is not immediately after switch.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:366)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:423)
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

    public State b() {
        synchronized (this) {
            State state = this.b;
            return state;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public HARWriter c() {
        synchronized (this) {
            if (this.b != State.b) return null;
            return this.c;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void d() {
        synchronized (this) {
            State state = this.i().getBoolean("start-on-launch", false) ? State.b : State.a;
            this.a(state);
            com.smule.android.logging.EventLogger2.a(new BaseEventLog2Listener(){

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                @Override
                public void a(Activity activity) {
                    RequestRecorder requestRecorder = RequestRecorder.this;
                    synchronized (requestRecorder) {
                        RequestRecorder.this.f = this.c(activity);
                        return;
                    }
                }

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                @Override
                public void a(EventLogger2 event) {
                    RequestRecorder requestRecorder = RequestRecorder.this;
                    synchronized (requestRecorder) {
                        RequestRecorder.this.e = event;
                        return;
                    }
                }

                @Override
                public void b(Activity activity) {
                }
            });
            return;
        }
    }

    public File e() {
        return new File(this.l().getExternalCacheDir(), "har");
    }

    public Interceptor f() {
        return this.d;
    }

    public File[] g() {
        this.h();
        File[] arrfile = this.e().listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File file, String string2) {
                return string2.endsWith(".har");
            }
        });
        if (arrfile != null) {
            return arrfile;
        }
        return new File[0];
    }

    private class RecordingInterceptor
    implements Interceptor {
        private RecordingInterceptor() {
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            HARWriter hARWriter = RequestRecorder.this.c();
            if (hARWriter == null) {
                return chain.a(chain.a());
            }
            HARWriter.EntryData entryData = new HARWriter.EntryData();
            entryData.a = new Date();
            entryData.b = SystemClock.elapsedRealtime();
            Request request = chain.a();
            RequestBodyWrapper requestBodyWrapper = new RequestBodyWrapper(request.d());
            entryData.d = request = request.e().a(request.b(), (RequestBody)requestBodyWrapper).b();
            chain = chain.a(request);
            entryData.e = entryData.c = requestBodyWrapper.b;
            entryData.g = chain;
            entryData.f = SystemClock.elapsedRealtime();
            entryData.h = RequestRecorder.this.m();
            hARWriter.a(entryData);
            return chain;
        }
    }

    private static class RequestBodyWrapper
    extends RequestBody {
        RequestBody a;
        long b;

        RequestBodyWrapper(RequestBody requestBody) {
            this.a = requestBody;
        }

        public long contentLength() throws IOException {
            return this.a.contentLength();
        }

        public MediaType contentType() {
            return this.a.contentType();
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.a.writeTo(bufferedSink);
            if (this.b == 0) {
                this.b = SystemClock.elapsedRealtime();
            }
        }
    }

    public static enum State {
        a,
        b,
        c;
        

        private State() {
        }
    }

}

