package com.smule.android.debug;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import com.smule.android.debug.HARWriter.EntryData;
import com.smule.android.logging.BaseEventLog2Listener;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$Event;
import com.smule.android.network.core.MagicNetwork;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class RequestRecorder {
    private static RequestRecorder f15933a;
    private State f15934b = State.INACTIVE;
    private HARWriter f15935c;
    private Interceptor f15936d = new RecordingInterceptor();
    private EventLogger2$Event f15937e;
    private String f15938f;

    class C35191 extends BaseEventLog2Listener {
        final /* synthetic */ RequestRecorder f15923a;

        C35191(RequestRecorder requestRecorder) {
            this.f15923a = requestRecorder;
        }

        public void m17729a(EventLogger2$Event eventLogger2$Event) {
            synchronized (this.f15923a) {
                this.f15923a.f15937e = eventLogger2$Event;
            }
        }

        public void m17728a(Activity activity) {
            synchronized (this.f15923a) {
                this.f15923a.f15938f = c(activity);
            }
        }

        public void m17730b(Activity activity) {
        }
    }

    class C35202 implements FilenameFilter {
        final /* synthetic */ RequestRecorder f15924a;

        C35202(RequestRecorder requestRecorder) {
            this.f15924a = requestRecorder;
        }

        public boolean accept(File file, String str) {
            return str.endsWith(".har");
        }
    }

    private class RecordingInterceptor implements Interceptor {
        final /* synthetic */ RequestRecorder f15926a;

        private RecordingInterceptor(RequestRecorder requestRecorder) {
            this.f15926a = requestRecorder;
        }

        public Response intercept(Chain chain) throws IOException {
            HARWriter c = this.f15926a.m17743c();
            if (c == null) {
                return chain.mo7152a(chain.mo7151a());
            }
            EntryData entryData = new EntryData();
            entryData.f15905a = new Date();
            entryData.f15906b = SystemClock.elapsedRealtime();
            Request a = chain.mo7151a();
            RequestBody requestBodyWrapper = new RequestBodyWrapper(a.m27888d());
            a = a.m27889e().m27870a(a.m27885b(), requestBodyWrapper).m27877b();
            entryData.f15908d = a;
            Response a2 = chain.mo7152a(a);
            entryData.f15907c = requestBodyWrapper.f15928b;
            entryData.f15909e = entryData.f15907c;
            entryData.f15911g = a2;
            entryData.f15910f = SystemClock.elapsedRealtime();
            entryData.f15912h = this.f15926a.m17740m();
            c.m17724a(entryData);
            return a2;
        }
    }

    private static class RequestBodyWrapper extends RequestBody {
        RequestBody f15927a;
        long f15928b;

        RequestBodyWrapper(RequestBody requestBody) {
            this.f15927a = requestBody;
        }

        public long contentLength() throws IOException {
            return this.f15927a.contentLength();
        }

        public MediaType contentType() {
            return this.f15927a.contentType();
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.f15927a.writeTo(bufferedSink);
            if (this.f15928b == 0) {
                this.f15928b = SystemClock.elapsedRealtime();
            }
        }
    }

    public enum State {
        INACTIVE,
        RECORDING,
        START_ON_NEXT_LAUNCH
    }

    public static synchronized RequestRecorder m17731a() {
        RequestRecorder requestRecorder;
        synchronized (RequestRecorder.class) {
            if (f15933a == null) {
                f15933a = new RequestRecorder();
            }
            requestRecorder = f15933a;
        }
        return requestRecorder;
    }

    public synchronized State m17742b() {
        return this.f15934b;
    }

    public synchronized HARWriter m17743c() {
        return this.f15934b == State.RECORDING ? this.f15935c : null;
    }

    public synchronized void m17744d() {
        m17741a(m17736i().getBoolean("start-on-launch", false) ? State.RECORDING : State.INACTIVE);
        EventLogger2.a(new C35191(this));
    }

    private RequestRecorder() {
    }

    public File m17745e() {
        return new File(m17739l().getExternalCacheDir(), "har");
    }

    public Interceptor m17746f() {
        return this.f15936d;
    }

    public File[] m17747g() {
        m17735h();
        File[] listFiles = m17745e().listFiles(new C35202(this));
        return listFiles != null ? listFiles : new File[0];
    }

    private void m17735h() {
        Object absolutePath = this.f15935c != null ? this.f15935c.m17723a().getAbsolutePath() : null;
        File[] listFiles = m17745e().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                String absolutePath2 = file.getAbsolutePath();
                if (absolutePath2.endsWith(".har-tmp") && !absolutePath2.equals(absolutePath)) {
                    new HARWriter(m17739l(), file).m17727c();
                }
            }
        }
    }

    private SharedPreferences m17736i() {
        return m17739l().getSharedPreferences("har-recorder", 0);
    }

    public synchronized void m17741a(State state) {
        if (state != this.f15934b) {
            Editor edit = m17736i().edit();
            this.f15934b = state;
            switch (this.f15934b) {
                case INACTIVE:
                    edit.remove("start-on-launch");
                    m17738k();
                    break;
                case RECORDING:
                    edit.remove("start-on-launch");
                    m17737j();
                    break;
                case START_ON_NEXT_LAUNCH:
                    edit.putBoolean("start-on-launch", true);
                    m17738k();
                    break;
            }
            edit.apply();
        }
    }

    private void m17737j() {
        m17738k();
        File e = m17745e();
        e.mkdirs();
        this.f15935c = new HARWriter(m17739l(), e);
        this.f15935c.m17725b();
    }

    private void m17738k() {
        if (this.f15935c != null) {
            this.f15935c.m17727c();
            this.f15935c = null;
        }
    }

    private Context m17739l() {
        return MagicNetwork.d().getApplicationContext();
    }

    private synchronized String m17740m() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        if (this.f15937e != null) {
            stringBuilder.append("Last event: ");
            stringBuilder.append(this.f15937e.f16359b);
            if (this.f15937e.f16361d != null) {
                stringBuilder.append(" context=");
                stringBuilder.append(this.f15937e.f16361d);
            }
            if (this.f15937e.f16360c != null) {
                stringBuilder.append(" target=");
                stringBuilder.append(this.f15937e.f16360c);
            }
        }
        if (this.f15938f != null) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append("Last activity: ");
            stringBuilder.append(this.f15938f);
        }
        return stringBuilder.toString();
    }
}
