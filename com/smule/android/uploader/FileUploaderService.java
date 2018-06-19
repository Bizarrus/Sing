package com.smule.android.uploader;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.util.Pair;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mopub.common.Constants;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.AttemptType;
import com.smule.android.logging.Analytics.UploadCompletionType;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.api.PerformancesAPI$UploadType;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest$PostProgressListener;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResourceInfo$ResourceType;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.managers.TracksManager;
import com.smule.android.network.managers.TracksManager.UploadAnalyticsCallback;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.UploadJob.Chunk;
import com.smule.android.uploader.UploadJob.UploadResourceInfo;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ResourceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class FileUploaderService extends Service {
    private static final String f17738a = FileUploaderService.class.getName();
    private static final long f17739n = TimeUnit.DAYS.toMillis(7);
    private static final byte[] f17740o = "OggS".getBytes();
    private final Map<String, Pair<VideoUploadStatus, PerformanceV2>> f17741b = new HashMap();
    private final ConcurrentHashMap<String, UploadJob> f17742c = new ConcurrentHashMap();
    private ChunkyQueue<UploadJob> f17743d;
    private final IBinder f17744e = new FileUploaderBinder(this);
    private final Map<String, Thread> f17745f = new HashMap(this.f17746g);
    private final int f17746g = MagicNetwork.d().getFileUploaderServiceThreadCount();
    private volatile Looper f17747h;
    private volatile ServiceHandler f17748i;
    private boolean f17749j;
    private JobStatusChecker f17750k;
    private Thread f17751l = null;
    private String f17752m;

    class C36541 extends TypeReference<Map<String, String>> {
        final /* synthetic */ FileUploaderService f17710a;

        C36541(FileUploaderService fileUploaderService) {
            this.f17710a = fileUploaderService;
        }
    }

    class C36552 implements FileFilter {
        final /* synthetic */ FileUploaderService f17711a;

        C36552(FileUploaderService fileUploaderService) {
            this.f17711a = fileUploaderService;
        }

        public boolean accept(File file) {
            return file.toString().endsWith(".json") && !file.toString().endsWith("job_status.json");
        }
    }

    class C36563 implements FileFilter {
        final /* synthetic */ FileUploaderService f17712a;

        C36563(FileUploaderService fileUploaderService) {
            this.f17712a = fileUploaderService;
        }

        public boolean accept(File file) {
            return file.toString().endsWith(".json") && !file.toString().endsWith("job_status.json");
        }
    }

    class C36574 implements FileFilter {
        final /* synthetic */ FileUploaderService f17713a;

        C36574(FileUploaderService fileUploaderService) {
            this.f17713a = fileUploaderService;
        }

        public boolean accept(File file) {
            String name = file.getName();
            return name.endsWith(PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO.m18254a()) || name.endsWith(PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b.m18254a()) || name.endsWith(PerformanceManager$PerformanceResourceInfo$ResourceType.META.m18254a()) || name.endsWith(PerformanceManager$PerformanceResourceInfo$ResourceType.f16865d.m18254a());
        }
    }

    class C36596 implements UploadAnalyticsCallback {
        final /* synthetic */ FileUploaderService f17717a;

        C36596(FileUploaderService fileUploaderService) {
            this.f17717a = fileUploaderService;
        }

        public void mo6303a(UploadJob uploadJob, int i) {
            Analytics.m17894c(uploadJob.performanceKey, uploadJob.songUid, uploadJob.arrangementKey);
        }
    }

    class C36618 implements UploadAnalyticsCallback {
        final /* synthetic */ FileUploaderService f17721a;

        C36618(FileUploaderService fileUploaderService) {
            this.f17721a = fileUploaderService;
        }

        public void mo6303a(UploadJob uploadJob, int i) {
            Analytics.m17894c(uploadJob.performanceKey, uploadJob.songUid, uploadJob.arrangementKey);
        }
    }

    private static class ChunkyQueue<T> {
        private final List<T> f17724a;

        interface TakePredicate<T> {
            boolean mo6304a(T t, T t2);
        }

        private ChunkyQueue() {
            this.f17724a = new ArrayList();
        }

        void m18890a(T t) {
            synchronized (this.f17724a) {
                this.f17724a.add(t);
                this.f17724a.notify();
            }
        }

        void m18891a(List<T> list) {
            synchronized (this.f17724a) {
                this.f17724a.addAll(list);
                this.f17724a.notify();
            }
        }

        int m18888a() {
            int size;
            synchronized (this.f17724a) {
                size = this.f17724a.size();
            }
            return size;
        }

        void m18892b(T t) {
            synchronized (this.f17724a) {
                this.f17724a.remove(t);
                this.f17724a.notify();
            }
        }

        List<T> m18889a(TakePredicate<T> takePredicate) {
            List<T> arrayList = new ArrayList();
            synchronized (this.f17724a) {
                while (this.f17724a.isEmpty()) {
                    try {
                        this.f17724a.wait();
                    } catch (InterruptedException e) {
                    }
                }
                Object remove = this.f17724a.remove(0);
                arrayList.add(remove);
                Iterator it = this.f17724a.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (takePredicate.mo6304a(remove, next)) {
                        arrayList.add(next);
                        it.remove();
                    }
                }
                this.f17724a.notify();
            }
            return arrayList;
        }
    }

    private class Consumer implements Runnable {
        final /* synthetic */ FileUploaderService f17726a;
        private final ChunkyQueue<UploadJob> f17727b;

        class C36631 implements TakePredicate<UploadJob> {
            final /* synthetic */ Consumer f17725a;

            C36631(Consumer consumer) {
                this.f17725a = consumer;
            }

            public boolean m18893a(UploadJob uploadJob, UploadJob uploadJob2) {
                return this.f17725a.m18898a(uploadJob, uploadJob2);
            }
        }

        public Consumer(FileUploaderService fileUploaderService, ChunkyQueue<UploadJob> chunkyQueue) {
            this.f17726a = fileUploaderService;
            this.f17727b = chunkyQueue;
        }

        public void run() {
            while (true) {
                try {
                    if (NetworkUtils.m18113a(this.f17726a)) {
                        List a = m18895a();
                        m18896a(a);
                        if (a.size() > 0) {
                            this.f17726a.m18932f(a);
                            this.f17726a.m18914a(a);
                        }
                    } else {
                        Log.b(FileUploaderService.f17738a, "Not connected");
                        Thread.sleep(60000);
                    }
                } catch (Throwable e) {
                    Log.a(FileUploaderService.f17738a, "Consumer interrupted", e);
                }
            }
        }

        private List<UploadJob> m18895a() throws InterruptedException {
            return this.f17727b.m18889a(new C36631(this));
        }

        private boolean m18898a(UploadJob uploadJob, UploadJob uploadJob2) {
            if (!uploadJob.performanceKey.equals(uploadJob2.performanceKey)) {
                return false;
            }
            PerformanceResourceInfo performanceResourceInfo = uploadJob.uploadResourceInfo.mPerformanceResourceInfo;
            PerformanceResourceInfo performanceResourceInfo2 = uploadJob2.uploadResourceInfo.mPerformanceResourceInfo;
            if (performanceResourceInfo.mResourceType.equals(PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b) || performanceResourceInfo2.mResourceType.equals(PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b) || !performanceResourceInfo.mHostname.equals(performanceResourceInfo2.mHostname) || !performanceResourceInfo.mPOP.equals(performanceResourceInfo2.mPOP)) {
                return false;
            }
            return true;
        }

        private void m18896a(List<UploadJob> list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                UploadJob uploadJob = (UploadJob) it.next();
                if (!uploadJob.c(this.f17726a.m18927e())) {
                    Log.d(FileUploaderService.f17738a, "job:" + uploadJob.id + " doesn't exist");
                    it.remove();
                    this.f17726a.f17742c.remove(uploadJob.id);
                }
            }
        }
    }

    public static class DroidSing9737Exception extends Throwable {
        public DroidSing9737Exception(String str) {
            super(str);
        }
    }

    public class FileUploaderBinder extends Binder {
        final /* synthetic */ FileUploaderService f17728a;

        public FileUploaderBinder(FileUploaderService fileUploaderService) {
            this.f17728a = fileUploaderService;
        }

        public FileUploaderService m18899a() {
            return this.f17728a;
        }
    }

    private class JobStatusChecker implements Runnable {
        final /* synthetic */ FileUploaderService f17729a;
        private final Map<String, Pair<VideoUploadStatus, PerformanceV2>> f17730b;

        public JobStatusChecker(FileUploaderService fileUploaderService, Map<String, Pair<VideoUploadStatus, PerformanceV2>> map) {
            this.f17729a = fileUploaderService;
            this.f17730b = map;
            fileUploaderService.m18935h();
            fileUploaderService.m18931f();
            for (int i = 0; i < fileUploaderService.f17746g; i++) {
                new Thread(new Consumer(fileUploaderService, fileUploaderService.f17743d), "upload-consumer-" + i).start();
            }
        }

        public void run() {
            HashSet hashSet = new HashSet();
            synchronized (this.f17730b) {
                for (String str : this.f17730b.keySet()) {
                    if (((Pair) this.f17730b.get(str)).first == VideoUploadStatus.RENDERING) {
                        hashSet.add(str);
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                if (Thread.interrupted()) {
                    Log.b(FileUploaderService.f17738a, "Status check stopped");
                    return;
                }
                arrayList.add(it.next());
                it.remove();
                if (arrayList.size() == 25 || !it.hasNext()) {
                    PerformancesResponse a = PerformanceManager.a().a(arrayList);
                    Iterator it2;
                    if (a == null || !a.a() || a.mPerformances == null || a.mPerformances.size() != arrayList.size()) {
                        it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            this.f17729a.m18912a((String) it2.next(), 100, VideoUploadStatus.RENDERING);
                        }
                    } else {
                        it2 = a.mPerformances.iterator();
                        while (it2.hasNext()) {
                            PerformanceV2 performanceV2 = (PerformanceV2) it2.next();
                            if (performanceV2 != null && performanceV2.a()) {
                                this.f17729a.m18917a(performanceV2.performanceKey, VideoUploadStatus.DONE, performanceV2);
                                this.f17729a.m18912a(performanceV2.performanceKey, 100, VideoUploadStatus.DONE);
                            } else if (performanceV2 != null) {
                                this.f17729a.m18912a(performanceV2.performanceKey, 100, VideoUploadStatus.RENDERING);
                            }
                        }
                    }
                    arrayList.clear();
                }
            }
            this.f17729a.f17751l = null;
        }
    }

    private final class ServiceHandler extends Handler {
        final /* synthetic */ FileUploaderService f17731a;

        public ServiceHandler(FileUploaderService fileUploaderService, Looper looper) {
            this.f17731a = fileUploaderService;
            super(looper);
        }

        public void handleMessage(Message message) {
            this.f17731a.m18908a((Intent) message.obj);
        }
    }

    public enum VideoUploadStatus {
        UPLOADING,
        RENDERING,
        DONE,
        ERROR_INVALID_MEDIA,
        UNKNOWN
    }

    public void m18939a(boolean z) {
        this.f17749j = z;
    }

    public static Intent m18902a(Context context) {
        return new Intent(context, FileUploaderService.class);
    }

    public static Intent m18903a(Context context, ArrayList<UploadResourceInfo> arrayList, String str, PerformanceV2 performanceV2, String str2, String str3, boolean z, boolean z2, boolean z3, String str4, boolean z4, String str5, boolean z5, boolean z6, boolean z7) {
        Intent intent = new Intent(context, FileUploaderService.class);
        intent.putExtra("RESOURCE_INFO", arrayList);
        intent.putExtra("TRACK_KEY", str);
        intent.putExtra("PERFORMANCE", performanceV2);
        intent.putExtra("SONG_UID", str2);
        intent.putExtra("ARRANGEMENT_KEY", str3);
        intent.putExtra("ONBOARDING_KEY", z);
        intent.putExtra("JOIN_KEY", z2);
        intent.putExtra("AUDIO_EFFECT_VIP_ONLY_KEY", z3);
        intent.putExtra("AUDIO_EFFECT_NAME_KEY", str4);
        intent.putExtra("VIDEO_EFFECT_VIP_ONLY_KEY", z4);
        intent.putExtra("VIDEO_EFFECT_NAME_KEY", str5);
        intent.putExtra("AIRBRUSH_EFFECT_KEY", z5);
        intent.putExtra("USED_HEADPHONE_KEY", z6);
        intent.putExtra("HEADPHONES_HAD_MIC_KEY", z7);
        return intent;
    }

    public void onDestroy() {
        Log.b(f17738a, "Destroying");
        this.f17747h.quit();
        super.onDestroy();
    }

    public void onCreate() {
        super.onCreate();
        Log.b(f17738a, "onCreate " + this);
        HandlerThread handlerThread = new HandlerThread("IntentService[FileUploaderService]");
        handlerThread.start();
        this.f17747h = handlerThread.getLooper();
        this.f17748i = new ServiceHandler(this, this.f17747h);
        m18939a(false);
        this.f17743d = new ChunkyQueue();
        this.f17750k = new JobStatusChecker(this, this.f17741b);
    }

    public void onStart(Intent intent, int i) {
        Message obtainMessage = this.f17748i.obtainMessage();
        obtainMessage.arg1 = i;
        obtainMessage.obj = intent;
        this.f17748i.sendMessage(obtainMessage);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            onStart(intent, i2);
        }
        return this.f17749j ? 3 : 2;
    }

    private String m18927e() {
        String str = ResourceUtils.m19029a(this) + File.separator + "upload_queue";
        File file = new File(str);
        if (!(file.exists() || file.mkdirs())) {
            Log.d(f17738a, "Failed to create queue directory");
        }
        return str;
    }

    private void m18931f() {
        synchronized (this.f17741b) {
            try {
                File file = new File(m18927e() + File.separator + "job_status.json");
                if (!file.exists()) {
                    Log.c(f17738a, "Creating new job_status.json");
                    if (!file.createNewFile()) {
                        Log.d(f17738a, "Failed to create queue file");
                    }
                } else if (file.length() > 0) {
                    for (Entry entry : JsonUtils.m18993b(JsonUtils.m18984a().readTree(file), new C36541(this)).entrySet()) {
                        VideoUploadStatus valueOf = VideoUploadStatus.valueOf((String) entry.getValue());
                        if (!(valueOf == VideoUploadStatus.UPLOADING || valueOf == VideoUploadStatus.ERROR_INVALID_MEDIA)) {
                            Log.b(f17738a, "Loading job status for " + ((String) entry.getKey()) + " " + valueOf);
                            this.f17741b.put(entry.getKey(), new Pair(valueOf, (PerformanceV2) null));
                        }
                    }
                }
            } catch (Throwable e) {
                Log.d(f17738a, "Failed to read job status map", e);
            }
        }
    }

    private void m18933g() {
        try {
            FileWriter fileWriter = new FileWriter(m18927e() + File.separator + "job_status.json");
            Object hashMap = new HashMap(this.f17741b.size());
            for (Entry entry : this.f17741b.entrySet()) {
                if (!(((Pair) entry.getValue()).first == VideoUploadStatus.UPLOADING || ((Pair) entry.getValue()).first == VideoUploadStatus.ERROR_INVALID_MEDIA)) {
                    hashMap.put(entry.getKey(), ((Pair) entry.getValue()).first);
                }
            }
            String a = JsonUtils.m18987a(hashMap);
            if (a != null) {
                fileWriter.write(a);
            }
            fileWriter.close();
        } catch (Throwable e) {
            Log.d(f17738a, "Failed to save job status map", e);
        }
    }

    private boolean m18917a(String str, VideoUploadStatus videoUploadStatus, PerformanceV2 performanceV2) {
        synchronized (this.f17741b) {
            if (performanceV2 == null) {
                Log.b(f17738a, "updateJobStatus:" + str + " removed");
                this.f17741b.remove(str);
                m18933g();
                return true;
            }
            Pair pair = (Pair) this.f17741b.get(str);
            if (pair != null && pair.first == VideoUploadStatus.ERROR_INVALID_MEDIA) {
                Log.b(f17738a, "updateJobStatus:" + str + " marked as invalid already");
                return false;
            } else if (videoUploadStatus == VideoUploadStatus.ERROR_INVALID_MEDIA || videoUploadStatus != VideoUploadStatus.RENDERING) {
                Log.b(f17738a, "updateJobStatus:" + str + " to " + videoUploadStatus);
                this.f17741b.put(str, new Pair(videoUploadStatus, performanceV2));
                m18933g();
                return true;
            } else {
                for (UploadJob uploadJob : this.f17742c.values()) {
                    if (uploadJob.performanceKey.equals(str)) {
                        Log.b(f17738a, "updateJobStatus:" + str + " upload still pending");
                        return false;
                    }
                }
                this.f17741b.put(str, new Pair(videoUploadStatus, performanceV2));
                m18933g();
                Log.b(f17738a, "updateJobStatus:" + str + " to " + videoUploadStatus);
                return true;
            }
        }
    }

    private void m18935h() {
        File file = new File(m18927e());
        HashSet hashSet = new HashSet();
        File[] listFiles = file.listFiles(new C36552(this));
        if (listFiles == null) {
            m18913a(ResourceUtils.m19028a(), hashSet);
            m18913a(ResourceUtils.m19032b(this), hashSet);
            return;
        }
        for (File file2 : listFiles) {
            if (file2.length() == 0) {
                try {
                    file2.delete();
                } catch (Throwable e) {
                    MagicCrittercism.a("exception while deleting: " + file2.getAbsolutePath());
                    Log.d(f17738a, "exception while deleting", new DroidSing9737Exception("delete").initCause(e));
                }
            } else {
                try {
                    try {
                        Object obj = (UploadJob) JsonUtils.m18985a(JsonUtils.m18984a().readTree(file2), UploadJob.class);
                        if (obj.performance != null) {
                            this.f17742c.put(obj.id, obj);
                            if (!TextUtils.isEmpty(obj.uploadResourceInfo.mResourceFilename)) {
                                hashSet.add(obj.uploadResourceInfo.mResourceFilename);
                            }
                            if (!obj.invalidMedia) {
                                this.f17743d.m18890a(obj);
                            }
                            m18917a(obj.performance.performanceKey, obj.invalidMedia ? VideoUploadStatus.ERROR_INVALID_MEDIA : VideoUploadStatus.UPLOADING, obj.performance);
                        } else if (!file2.delete()) {
                            Log.b(f17738a, "could not delete:" + file2.getAbsolutePath());
                        }
                    } catch (Throwable e2) {
                        Log.d(f17738a, "Failed to parse " + file2.getAbsolutePath(), e2);
                    }
                } catch (Throwable e22) {
                    MagicCrittercism.a("Mapping ex: " + file2.getAbsolutePath() + "length:" + file2.length() + " contents:" + m18906a(file2));
                    Log.d(f17738a, "JsonMappingException", new DroidSing9737Exception("JsonMappingException").initCause(e22));
                } catch (Throwable e222) {
                    Log.d(f17738a, "Failed to parse " + file2.getAbsolutePath(), e222);
                }
            }
        }
        Log.b(f17738a, "Queue filled, size=" + this.f17743d.m18888a());
        m18913a(ResourceUtils.m19028a(), hashSet);
        m18913a(ResourceUtils.m19032b(this), hashSet);
    }

    private void m18922c(String str) {
        File[] listFiles = new File(m18927e()).listFiles(new C36563(this));
        if (listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file = listFiles[i];
                try {
                    UploadJob uploadJob = (UploadJob) JsonUtils.m18985a(JsonUtils.m18984a().readTree(file), UploadJob.class);
                    if (uploadJob.performanceKey.equals(str)) {
                        uploadJob.b(m18927e());
                        return;
                    }
                    i++;
                } catch (Throwable e) {
                    Log.d(f17738a, "Failed to parse " + file.getAbsolutePath(), e);
                }
            }
        }
    }

    private void m18913a(String str, HashSet<String> hashSet) {
        long currentTimeMillis = System.currentTimeMillis() - f17739n;
        File[] listFiles = new File(str).listFiles(new C36574(this));
        if (listFiles == null) {
            Log.d(f17738a, "list of files null");
            return;
        }
        for (File file : listFiles) {
            if (file.isFile() && !hashSet.contains(file.getAbsolutePath()) && file.lastModified() <= currentTimeMillis) {
                if (file.getName().endsWith(PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO.m18254a())) {
                    FileInputStream fileInputStream;
                    try {
                        fileInputStream = new FileInputStream(file);
                        if (file.length() < 4) {
                            fileInputStream.close();
                        } else {
                            byte[] bArr = new byte[4];
                            if (fileInputStream.read(bArr) != 4) {
                                fileInputStream.close();
                            } else {
                                if (Arrays.equals(bArr, f17740o) && !file.delete()) {
                                    Log.b(f17738a, "could not delete:" + file.getAbsolutePath());
                                }
                                fileInputStream.close();
                            }
                        }
                    } catch (IOException e) {
                        Log.b(f17738a, "exception while deleting:" + e + " " + file.getAbsolutePath());
                    } catch (Throwable th) {
                        fileInputStream.close();
                    }
                } else if (!file.delete()) {
                    Log.b(f17738a, "could not delete:" + file.getAbsolutePath());
                }
            }
        }
    }

    public VideoUploadStatus m18936a(String str) {
        VideoUploadStatus videoUploadStatus;
        synchronized (this.f17741b) {
            Pair pair = (Pair) this.f17741b.get(str);
            videoUploadStatus = pair != null ? (VideoUploadStatus) pair.first : VideoUploadStatus.UNKNOWN;
            if (videoUploadStatus == VideoUploadStatus.DONE || videoUploadStatus == VideoUploadStatus.ERROR_INVALID_MEDIA) {
                m18917a(str, null, null);
            }
            Log.b(f17738a, "Job status for " + str + " is " + videoUploadStatus);
        }
        return videoUploadStatus;
    }

    public List<PerformanceV2> m18937a() {
        List<PerformanceV2> arrayList;
        synchronized (this.f17741b) {
            arrayList = new ArrayList();
            for (Pair pair : this.f17741b.values()) {
                if (pair.first == VideoUploadStatus.UPLOADING || (pair.first == VideoUploadStatus.ERROR_INVALID_MEDIA && pair.second != null)) {
                    arrayList.add(pair.second);
                }
            }
        }
        return arrayList;
    }

    public void m18941b(String str) {
        synchronized (this.f17741b) {
            m18912a(str, 100, VideoUploadStatus.DONE);
            m18917a(str, null, null);
        }
    }

    public void m18938a(String str, String str2, String str3) {
        for (UploadJob uploadJob : this.f17742c.values()) {
            if (uploadJob.performanceKey.equals(str)) {
                Log.b(f17738a, "cancelling key:" + str + " id:" + uploadJob.id);
                this.f17743d.m18892b(uploadJob);
                synchronized (this.f17745f) {
                    Thread thread = (Thread) this.f17745f.get(uploadJob.id);
                    if (thread != null) {
                        thread.interrupt();
                    } else {
                        m18922c(str);
                    }
                }
                uploadJob.b(m18927e());
                this.f17742c.remove(uploadJob.id);
                if (uploadJob.uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b) {
                    Analytics.m17889b(str, str2, str3);
                } else if (uploadJob.uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO) {
                    UserPath userPath;
                    String str4 = uploadJob.performanceKey;
                    if (uploadJob.isOnboarding) {
                        userPath = UserPath.ONBOARDING;
                    } else {
                        userPath = UserPath.OTHER;
                    }
                    Analytics.m17863a(str4, userPath, UploadCompletionType.CANCEL, Analytics.m17881b(uploadJob.performance), uploadJob.isJoin, Analytics.m17828a(uploadJob.performance), null, null, null, Analytics.m17891c(uploadJob.performance));
                }
            }
        }
        m18917a(str, null, null);
    }

    public IBinder onBind(Intent intent) {
        return this.f17744e;
    }

    private void m18908a(Intent intent) {
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("RESOURCE_INFO");
        if (parcelableArrayListExtra != null) {
            List arrayList = new ArrayList();
            Iterator it = parcelableArrayListExtra.iterator();
            while (it.hasNext()) {
                UploadResourceInfo uploadResourceInfo = (UploadResourceInfo) it.next();
                if (uploadResourceInfo.mResourceFilename == null || !new File(uploadResourceInfo.mResourceFilename).exists()) {
                    Log.d(f17738a, "missing file");
                } else {
                    UploadJob uploadJob = new UploadJob();
                    uploadJob.uploadResourceInfo = uploadResourceInfo;
                    uploadJob.trackKey = intent.getStringExtra("TRACK_KEY");
                    uploadJob.performance = (PerformanceV2) intent.getParcelableExtra("PERFORMANCE");
                    uploadJob.performanceKey = uploadJob.performance.performanceKey;
                    uploadJob.songUid = intent.getStringExtra("SONG_UID");
                    uploadJob.arrangementKey = intent.getStringExtra("ARRANGEMENT_KEY");
                    uploadJob.isOnboarding = intent.getBooleanExtra("ONBOARDING_KEY", false);
                    uploadJob.isJoin = intent.getBooleanExtra("JOIN_KEY", false);
                    uploadJob.audioEffectVIPOnly = intent.getBooleanExtra("AUDIO_EFFECT_VIP_ONLY_KEY", false);
                    uploadJob.audioEffectName = intent.getStringExtra("AUDIO_EFFECT_NAME_KEY");
                    uploadJob.videoEffectVIPOnly = intent.getBooleanExtra("VIDEO_EFFECT_VIP_ONLY_KEY", false);
                    uploadJob.videoEffectName = intent.getStringExtra("VIDEO_EFFECT_NAME_KEY");
                    uploadJob.isAirbrushOn = intent.getBooleanExtra("AIRBRUSH_EFFECT_KEY", false);
                    Object arrayList2 = new ArrayList();
                    this.f17752m = uploadJob.videoEffectName;
                    if (uploadJob.videoEffectName != null && uploadJob.videoEffectName.equals("unsupported")) {
                        uploadJob.videoEffectName = "normal";
                        this.f17752m = "unsupported";
                        arrayList2.add(this.f17752m);
                    } else if (!(uploadJob.videoEffectName == null || uploadJob.videoEffectName.equals("unsupported"))) {
                        arrayList2.add(uploadJob.videoEffectName);
                    }
                    if (uploadJob.isAirbrushOn) {
                        arrayList2.add("airbrush");
                    }
                    if (!arrayList2.isEmpty()) {
                        Collections.sort(arrayList2);
                        this.f17752m = TextUtils.join(":", arrayList2);
                    }
                    uploadJob.usedHeadphone = intent.getBooleanExtra("USED_HEADPHONE_KEY", false);
                    uploadJob.headphonesHadMic = intent.getBooleanExtra("HEADPHONES_HAD_MIC_KEY", false);
                    uploadJob.isNew = true;
                    uploadJob.a(m18927e());
                    m18917a(uploadJob.performanceKey, VideoUploadStatus.UPLOADING, uploadJob.performance);
                    arrayList.add(uploadJob);
                    this.f17742c.put(uploadJob.id, uploadJob);
                    Log.b(f17738a, "Pushed job " + uploadJob.id);
                }
            }
            this.f17743d.m18891a(arrayList);
            return;
        }
        Log.c(f17738a, "Starting service");
    }

    private void m18914a(List<UploadJob> list) {
        NetworkResponse b = m18918b((List) list);
        Log.b(f17738a, "uploadResponse:" + b);
        if (b == null || !b.c()) {
            m18915a((List) list, b);
        } else {
            m18923c((List) list);
        }
    }

    private NetworkResponse m18918b(List<UploadJob> list) {
        final UploadJob uploadJob = (UploadJob) list.get(0);
        PerformancesAPI$UploadType performancesAPI$UploadType = uploadJob.isJoin ? PerformancesAPI$UploadType.JOIN : PerformancesAPI$UploadType.CREATE;
        if (list.size() == 1) {
            UploadResourceInfo uploadResourceInfo = uploadJob.uploadResourceInfo;
            if (uploadJob.a()) {
                return m18904a(uploadJob);
            }
            if (uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b) {
                final File file = new File(uploadResourceInfo.mResourceFilename);
                return TracksManager.m18471a().m18475a(uploadJob, uploadResourceInfo, new SnpRequest$PostProgressListener(this) {
                    final /* synthetic */ FileUploaderService f17716c;

                    public void onChunkTransfered(SortedSet<Chunk> sortedSet) {
                        uploadJob.a(sortedSet);
                        uploadJob.a(this.f17716c.m18927e());
                        this.f17716c.m18912a(uploadJob.performanceKey, this.f17716c.m18901a((SortedSet) sortedSet, file.length()), VideoUploadStatus.UPLOADING);
                    }
                }, new C36596(this), performancesAPI$UploadType);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (UploadJob uploadJob2 : list) {
            UploadResourceInfo uploadResourceInfo2 = uploadJob2.uploadResourceInfo;
            arrayList.add(uploadResourceInfo2);
            if (uploadJob2.isNew) {
                if (uploadResourceInfo2.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO) {
                    Analytics.m17862a(uploadJob2.performanceKey, uploadJob2.isOnboarding ? UserPath.ONBOARDING : UserPath.OTHER, AttemptType.NEW, Analytics.m17881b(uploadJob2.performance), uploadJob2.isJoin, Analytics.m17828a(uploadJob2.performance), Analytics.m17891c(uploadJob2.performance));
                }
                uploadJob2.isNew = false;
                uploadJob2.a(m18927e());
            }
        }
        return TracksManager.m18471a().m18480a(uploadJob.performanceKey, uploadJob.trackKey, arrayList, performancesAPI$UploadType);
    }

    private NetworkResponse m18904a(final UploadJob uploadJob) {
        UploadResourceInfo uploadResourceInfo = uploadJob.uploadResourceInfo;
        final File file = new File(uploadResourceInfo.mResourceFilename);
        switch (uploadResourceInfo.mPerformanceResourceInfo.mResourceType) {
            case AUDIO:
                if (uploadJob.isNew) {
                    Analytics.m17862a(uploadJob.performanceKey, uploadJob.isOnboarding ? UserPath.ONBOARDING : UserPath.OTHER, AttemptType.NEW, Analytics.m17881b(uploadJob.performance), uploadJob.isJoin, Analytics.m17828a(uploadJob.performance), Analytics.m17891c(uploadJob.performance));
                    uploadJob.isNew = false;
                    uploadJob.a(m18927e());
                }
                return TracksManager.m18471a().m18477a(file, uploadJob);
            default:
                return TracksManager.m18471a().m18478a(file, uploadJob, new SnpRequest$PostProgressListener(this) {
                    final /* synthetic */ FileUploaderService f17720c;

                    public void onChunkTransfered(SortedSet<Chunk> sortedSet) {
                        uploadJob.a(sortedSet);
                        uploadJob.a(this.f17720c.m18927e());
                        this.f17720c.m18912a(uploadJob.performanceKey, this.f17720c.m18901a((SortedSet) sortedSet, file.length()), VideoUploadStatus.UPLOADING);
                    }
                }, new C36618(this));
        }
    }

    private void m18923c(List<UploadJob> list) {
        for (UploadJob uploadJob : list) {
            UploadResourceInfo uploadResourceInfo = uploadJob.uploadResourceInfo;
            if (uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b) {
                Analytics.m17897d(uploadJob.performanceKey, uploadJob.songUid, uploadJob.arrangementKey);
            } else if (uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO && !uploadJob.audioAnalyticsFired) {
                Analytics.m17863a(uploadJob.performanceKey, uploadJob.isOnboarding ? UserPath.ONBOARDING : UserPath.OTHER, UploadCompletionType.SUCCESS, Analytics.m17881b(uploadJob.performance), uploadJob.isJoin, Analytics.m17828a(uploadJob.performance), null, null, null, Analytics.m17891c(uploadJob.performance));
                if (uploadJob.isJoin) {
                    Analytics.m17887b(uploadJob.performanceKey, uploadJob.isOnboarding ? UserPath.ONBOARDING : UserPath.OTHER, uploadJob.audioEffectVIPOnly, uploadJob.audioEffectName, uploadJob.videoEffectVIPOnly, this.f17752m, HeadphonesType.m17502a(uploadJob.usedHeadphone, uploadJob.headphonesHadMic), Analytics.m17881b(uploadJob.performance), Analytics.m17828a(uploadJob.performance), Analytics.m17891c(uploadJob.performance), uploadJob.performance.video);
                } else {
                    UserPath userPath;
                    String str = uploadJob.performanceKey;
                    if (uploadJob.isOnboarding) {
                        userPath = UserPath.ONBOARDING;
                    } else {
                        userPath = UserPath.OTHER;
                    }
                    Analytics.m17865a(str, userPath, uploadJob.audioEffectVIPOnly, uploadJob.audioEffectName, uploadJob.videoEffectVIPOnly, this.f17752m, HeadphonesType.m17502a(uploadJob.usedHeadphone, uploadJob.headphonesHadMic), Analytics.m17881b(uploadJob.performance), Analytics.m17828a(uploadJob.performance), Analytics.m17891c(uploadJob.performance), uploadJob.performance.video);
                }
                uploadJob.audioAnalyticsFired = true;
                uploadJob.a(m18927e());
            }
            uploadJob.b(m18927e());
            this.f17742c.remove(uploadJob.id);
            synchronized (this.f17745f) {
                Log.b(f17738a, "mThreads:remove:" + uploadJob.id);
                this.f17745f.remove(uploadJob.id);
            }
            if (m18917a(uploadJob.performanceKey, VideoUploadStatus.RENDERING, uploadJob.performance)) {
                m18912a(((UploadJob) list.get(0)).performanceKey, 100, VideoUploadStatus.RENDERING);
            }
        }
    }

    private boolean m18926d(List<UploadJob> list) {
        for (UploadJob uploadJob : list) {
            UploadResourceInfo uploadResourceInfo = uploadJob.uploadResourceInfo;
            if (uploadResourceInfo.mResourceFilename != null && !uploadResourceInfo.mResourceFilename.isEmpty() && !new File(uploadResourceInfo.mResourceFilename).exists()) {
                return true;
            }
        }
        return false;
    }

    private void m18929e(List<UploadJob> list) {
        for (UploadJob uploadJob : list) {
            Log.b(f17738a, "canceling jobID " + uploadJob.id);
            uploadJob.b(m18927e());
            this.f17742c.remove(uploadJob.id);
        }
    }

    private void m18915a(List<UploadJob> list, NetworkResponse networkResponse) {
        m18934g(list);
        if (Thread.interrupted() || m18926d((List) list)) {
            Log.b(f17738a, "deleting jobs - recording not found or cancelled");
            m18929e((List) list);
        } else if (networkResponse != null && (networkResponse.b == PointerIconCompat.TYPE_NO_DROP || networkResponse.b == 1028)) {
            Log.b(f17738a, "deleting jobs - associated performance not found or already assigned");
            m18929e((List) list);
            for (UploadJob uploadJob : list) {
                if (uploadJob.uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO) {
                    UserPath userPath;
                    String str = uploadJob.performanceKey;
                    if (uploadJob.isOnboarding) {
                        userPath = UserPath.ONBOARDING;
                    } else {
                        userPath = UserPath.OTHER;
                    }
                    Analytics.m17863a(str, userPath, UploadCompletionType.FAIL, Analytics.m17881b(uploadJob.performance), uploadJob.isJoin, Analytics.m17828a(uploadJob.performance), "snp", null, Integer.valueOf(networkResponse.b), Analytics.m17891c(uploadJob.performance));
                }
            }
        } else if (networkResponse == null || networkResponse.b != 1030) {
            List arrayList = new ArrayList();
            for (UploadJob uploadJob2 : list) {
                uploadJob2.consecutiveFailures++;
                uploadJob2.a(m18927e());
                if (uploadJob2.consecutiveFailures < 4) {
                    Log.b(f17738a, "jobId:" + uploadJob2.id + " fail:" + uploadJob2.consecutiveFailures);
                    arrayList.add(uploadJob2);
                } else {
                    Log.b(f17738a, "too many failures:" + uploadJob2.id);
                    String str2 = "os";
                    String str3 = null;
                    Integer num = null;
                    if (networkResponse != null) {
                        str3 = networkResponse.m;
                        if (networkResponse.k != null) {
                            if (networkResponse.k.m27933d()) {
                                str2 = "snp";
                                num = Integer.valueOf(networkResponse.b);
                            } else {
                                str2 = Constants.HTTP;
                                str3 = Integer.toString(networkResponse.k.m27932c());
                            }
                        }
                    }
                    UploadResourceInfo uploadResourceInfo = uploadJob2.uploadResourceInfo;
                    if (uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b) {
                        Analytics.m17876a(uploadJob2.performanceKey, str2, str3, num, uploadJob2.songUid, uploadJob2.arrangementKey);
                    } else if (uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO) {
                        UserPath userPath2;
                        String str4 = uploadJob2.performanceKey;
                        if (uploadJob2.isOnboarding) {
                            userPath2 = UserPath.ONBOARDING;
                        } else {
                            userPath2 = UserPath.OTHER;
                        }
                        Analytics.m17863a(str4, userPath2, UploadCompletionType.FAIL, Analytics.m17881b(uploadJob2.performance), uploadJob2.isJoin, Analytics.m17828a(uploadJob2.performance), str2, str3, num, Analytics.m17891c(uploadJob2.performance));
                    }
                }
            }
            if (!arrayList.isEmpty() && this.f17748i != null) {
                final List list2 = arrayList;
                this.f17748i.postDelayed(new Runnable(this) {
                    final /* synthetic */ FileUploaderService f17723b;

                    public void run() {
                        if (this.f17723b.f17743d != null) {
                            this.f17723b.f17743d.m18891a(list2);
                        }
                    }
                }, (long) ((2 << ((UploadJob) arrayList.get(0)).consecutiveFailures) * 1000));
            }
        } else {
            for (UploadJob uploadJob22 : list) {
                Log.b(f17738a, "invalid media jobID " + uploadJob22.id + ": " + uploadJob22.performanceKey);
                uploadJob22.consecutiveFailures++;
                uploadJob22.invalidMedia = true;
                uploadJob22.a(m18927e());
                if (uploadJob22.uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO) {
                    Analytics.m17863a(uploadJob22.performanceKey, uploadJob22.isOnboarding ? UserPath.ONBOARDING : UserPath.OTHER, UploadCompletionType.FAIL, Analytics.m17881b(uploadJob22.performance), uploadJob22.isJoin, Analytics.m17828a(uploadJob22.performance), "snp", null, Integer.valueOf(networkResponse.b), Analytics.m17891c(uploadJob22.performance));
                }
                m18917a(uploadJob22.performanceKey, VideoUploadStatus.ERROR_INVALID_MEDIA, uploadJob22.performance);
                m18912a(uploadJob22.performanceKey, 100, VideoUploadStatus.ERROR_INVALID_MEDIA);
            }
        }
    }

    private long m18901a(SortedSet<Chunk> sortedSet, long j) {
        if (j == 0) {
            return 0;
        }
        long j2 = 0;
        for (Chunk chunk : sortedSet) {
            j2 = ((chunk.end - chunk.start) + 1) + j2;
        }
        return (100 * j2) / j;
    }

    private void m18912a(String str, long j, VideoUploadStatus videoUploadStatus) {
        Intent intent = new Intent();
        intent.setAction("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS");
        intent.addCategory(getPackageName());
        intent.putExtra("PERFORMANCE_KEY", str);
        intent.putExtra("FILE_UPLOAD_PROGRESS", j);
        intent.putExtra("FILE_UPLOAD_STATUS", videoUploadStatus);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        NotificationCenter.m19011a().m19012a("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", intent.getExtras());
    }

    private void m18932f(List<UploadJob> list) {
        synchronized (this.f17745f) {
            for (UploadJob uploadJob : list) {
                Log.b(f17738a, "mThreads:put:" + uploadJob.id);
                this.f17745f.put(uploadJob.id, Thread.currentThread());
            }
        }
    }

    private void m18934g(List<UploadJob> list) {
        synchronized (this.f17745f) {
            for (UploadJob uploadJob : list) {
                Log.b(f17738a, "mThreads:remove:" + uploadJob.id);
                this.f17745f.remove(uploadJob.id);
            }
        }
    }

    public synchronized void m18940b() {
        if (this.f17751l == null) {
            this.f17751l = new Thread(this.f17750k);
            this.f17751l.start();
        }
    }

    public synchronized void m18942c() {
        if (this.f17751l != null && this.f17751l.isAlive()) {
            this.f17751l.interrupt();
        }
        this.f17751l = null;
    }

    public static String m18906a(@NonNull File file) {
        try {
            InputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder stringBuilder = new StringBuilder();
            Object obj = 1;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    fileInputStream.close();
                    return stringBuilder.toString();
                } else if (obj != null) {
                    stringBuilder.append(readLine);
                    obj = null;
                } else {
                    stringBuilder.append("\n").append(readLine);
                }
            }
        } catch (IOException e) {
            return "";
        }
    }
}
