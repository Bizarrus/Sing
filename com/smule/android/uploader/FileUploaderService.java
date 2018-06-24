/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.Binder
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.IBinder
 *  android.os.Looper
 *  android.os.Message
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.v4.content.LocalBroadcastManager
 *  android.text.TextUtils
 *  android.util.Pair
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.JsonMappingException
 *  com.fasterxml.jackson.databind.JsonNode
 *  okhttp3.Response
 */
package com.smule.android.uploader;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.audio.AudioDefs;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.TracksManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.UploadJob;
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
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import okhttp3.Response;

public class FileUploaderService
extends Service {
    private static final String a = FileUploaderService.class.getName();
    private static final long w = TimeUnit.DAYS.toMillis(7);
    private static final byte[] x = "OggS".getBytes();
    private final Map<String, Pair<VideoUploadStatus, PerformanceV2>> b = new HashMap<String, Pair<VideoUploadStatus, PerformanceV2>>();
    private final ConcurrentHashMap<String, UploadJob> c = new ConcurrentHashMap();
    private ChunkyQueue<UploadJob> d;
    private final IBinder e;
    private final Set<String> f;
    private final int g;
    private ExecutorService h;
    private volatile Looper i;
    private volatile ServiceHandler j;
    private boolean k;
    private JobStatusChecker l;
    private ExecutorService m;
    private Future n;
    private Set<String> o;
    private boolean p;
    private String q;
    private String r;
    private Boolean s;
    private Object t;
    private boolean u;
    private TracksManager.NetworkPreconditionsCallback v;

    public FileUploaderService() {
        this.e = new FileUploaderBinder();
        this.m = Executors.newSingleThreadExecutor();
        this.v = new TracksManager.NetworkPreconditionsCallback(){

            @Override
            public boolean a() {
                return NetworkUtils.a((Context)FileUploaderService.this, FileUploaderService.this.a());
            }
        };
        this.g = MagicNetwork.d().getFileUploaderServiceThreadCount();
        this.f = new HashSet<String>(this.g);
        this.t = new Object();
    }

    private long a(SortedSet<UploadJob.Chunk> object, long l) {
        if (l == 0) {
            return 0;
        }
        object = object.iterator();
        long l2 = 0;
        while (object.hasNext()) {
            UploadJob.Chunk chunk = (UploadJob.Chunk)object.next();
            l2 = chunk.end - chunk.start + 1 + l2;
        }
        return 100 * l2 / l;
    }

    public static Intent a(Context context) {
        return new Intent(context, FileUploaderService.class);
    }

    public static Intent a(Context context, ArrayList<UploadJob.UploadResourceInfo> arrayList, String string2, PerformanceV2 performanceV2, String string3, String string4, boolean bl, boolean bl2, boolean bl3, String string5, boolean bl4, String string6, String string7, String string8, boolean bl5, boolean bl6, boolean bl7, boolean bl8, boolean bl9, boolean bl10) {
        context = new Intent(context, FileUploaderService.class);
        context.putExtra("RESOURCE_INFO", arrayList);
        context.putExtra("TRACK_KEY", string2);
        context.putExtra("PERFORMANCE", (Parcelable)performanceV2);
        context.putExtra("SONG_UID", string3);
        context.putExtra("ARRANGEMENT_KEY", string4);
        context.putExtra("ONBOARDING_KEY", bl);
        context.putExtra("JOIN_KEY", bl2);
        context.putExtra("AUDIO_EFFECT_VIP_ONLY_KEY", bl3);
        context.putExtra("AUDIO_EFFECT_NAME_KEY", string5);
        context.putExtra("VIDEO_EFFECT_VIP_ONLY_KEY", bl4);
        context.putExtra("VIDEO_STYLE_ID_KEY", string6);
        context.putExtra("COLOR_FILTER_ID_KEY", string7);
        context.putExtra("INTENSITY_ID_KEY", string8);
        context.putExtra("AIRBRUSH_EFFECT_KEY", bl5);
        context.putExtra("AUTO_TUNE_KEY", bl6);
        context.putExtra("USED_HEADPHONE_KEY", bl7);
        context.putExtra("HEADPHONES_HAD_MIC_KEY", bl8);
        context.putExtra("BOOSTED_KEY", bl9);
        context.putExtra("ADD_VIDEO_KEY", bl10);
        return context;
    }

    /*
     * Enabled aggressive block sorting
     */
    private NetworkResponse a(final UploadJob uploadJob) {
        Object object = uploadJob.resourceInfo;
        final File file = new File(object.mResourceFilename);
        switch (.a[object.mPerformanceResourceInfo.mResourceType.ordinal()]) {
            default: {
                return TracksManager.a().a(file, uploadJob, new SnpRequest(){

                    @Override
                    public void onChunkTransfered(SortedSet<UploadJob.Chunk> sortedSet) {
                        uploadJob.a(sortedSet);
                        uploadJob.a(FileUploaderService.this.j());
                        FileUploaderService.this.a(uploadJob.performanceKey, FileUploaderService.this.a(sortedSet, file.length()), VideoUploadStatus.a);
                    }
                }, new TracksManager.UploadAnalyticsCallback(){

                    @Override
                    public void a(UploadJob uploadJob, int n) {
                        com.smule.android.logging.Analytics.c(uploadJob.performanceKey, uploadJob.songUid, uploadJob.arrangementKey);
                    }
                }, this.v);
            }
            case 2: 
        }
        if (uploadJob.isNew) {
            String string2 = uploadJob.performanceKey;
            object = uploadJob.isOnboarding ? Analytics.c : Analytics.d;
            com.smule.android.logging.Analytics.a(string2, object, Analytics.a, com.smule.android.logging.Analytics.b(uploadJob.performance), uploadJob.isJoin, com.smule.android.logging.Analytics.a(uploadJob.performance), com.smule.android.logging.Analytics.c(uploadJob.performance));
            uploadJob.isNew = false;
            uploadJob.a(this.j());
        }
        return TracksManager.a().a(file, uploadJob);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(@NonNull File object) {
        boolean bl;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;
        try {
            object = new FileInputStream((File)object);
            bufferedReader = new BufferedReader(new InputStreamReader((InputStream)object));
            stringBuilder = new StringBuilder();
            bl = true;
        }
        catch (IOException iOException) {
            return "";
        }
        do {
            String string2;
            if ((string2 = bufferedReader.readLine()) == null) {
                bufferedReader.close();
                object.close();
                return stringBuilder.toString();
            }
            if (bl) {
                stringBuilder.append(string2);
                bl = false;
                continue;
            }
            stringBuilder.append("\n").append(string2);
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Intent intent) {
        Object object = intent.getParcelableArrayListExtra("RESOURCE_INFO");
        if (object == null) {
            Log.c(a, "Starting service");
            return;
        }
        ArrayList<UploadJob> arrayList = new ArrayList<UploadJob>();
        object = object.iterator();
        do {
            if (!object.hasNext()) {
                this.d.a(arrayList);
                return;
            }
            ArrayList<String> arrayList2 = (UploadJob.UploadResourceInfo)object.next();
            if (arrayList2.mResourceFilename == null || !new File(arrayList2.mResourceFilename).exists()) {
                Log.d(a, "missing file");
                continue;
            }
            UploadJob uploadJob = new UploadJob();
            uploadJob.resourceInfo = arrayList2;
            uploadJob.trackKey = intent.getStringExtra("TRACK_KEY");
            uploadJob.performance = (PerformanceV2)intent.getParcelableExtra("PERFORMANCE");
            uploadJob.performanceKey = uploadJob.performance.performanceKey;
            uploadJob.songUid = intent.getStringExtra("SONG_UID");
            uploadJob.arrangementKey = intent.getStringExtra("ARRANGEMENT_KEY");
            uploadJob.isOnboarding = intent.getBooleanExtra("ONBOARDING_KEY", false);
            uploadJob.isJoin = intent.getBooleanExtra("JOIN_KEY", false);
            uploadJob.audioEffectVIPOnly = intent.getBooleanExtra("AUDIO_EFFECT_VIP_ONLY_KEY", false);
            uploadJob.audioEffectName = intent.getStringExtra("AUDIO_EFFECT_NAME_KEY");
            uploadJob.videoEffectVIPOnly = intent.getBooleanExtra("VIDEO_EFFECT_VIP_ONLY_KEY", false);
            uploadJob.videoStyleId = intent.getStringExtra("VIDEO_STYLE_ID_KEY");
            uploadJob.colorFilterId = intent.getStringExtra("COLOR_FILTER_ID_KEY");
            uploadJob.intensityId = intent.getStringExtra("INTENSITY_ID_KEY");
            uploadJob.isAirbrushOn = intent.getBooleanExtra("AIRBRUSH_EFFECT_KEY", false);
            uploadJob.isAutoTuneOn = intent.getBooleanExtra("AUTO_TUNE_KEY", false);
            uploadJob.isAddVideoUsed = intent.getBooleanExtra("ADD_VIDEO_KEY", false);
            arrayList2 = new ArrayList();
            if (uploadJob.isAirbrushOn) {
                arrayList2.add("airbrush");
            }
            this.q = uploadJob.colorFilterId;
            boolean bl = uploadJob.colorFilterId != null;
            if (bl) {
                bl = !uploadJob.colorFilterId.equals("unsupported");
                if (bl) {
                    arrayList2.add(uploadJob.colorFilterId);
                    arrayList2.add(uploadJob.videoStyleId);
                    arrayList2.add(uploadJob.intensityId);
                } else {
                    uploadJob.colorFilterId = "normal";
                    this.q = "unsupported";
                    arrayList2.add(this.q);
                }
            }
            if (!arrayList2.isEmpty()) {
                this.q = TextUtils.join((CharSequence)":", (Iterable)arrayList2);
            }
            arrayList2 = new ArrayList<String>();
            if (uploadJob.isAutoTuneOn) {
                arrayList2.add("pitch_corrected");
            } else {
                arrayList2.add("not_pitch_corrected");
            }
            arrayList2.add(uploadJob.audioEffectName);
            if (!arrayList2.isEmpty()) {
                this.r = TextUtils.join((CharSequence)":", arrayList2);
            }
            uploadJob.usedHeadphone = intent.getBooleanExtra("USED_HEADPHONE_KEY", false);
            uploadJob.headphonesHadMic = intent.getBooleanExtra("HEADPHONES_HAD_MIC_KEY", false);
            uploadJob.boosted = intent.getBooleanExtra("BOOSTED_KEY", false);
            uploadJob.isNew = true;
            uploadJob.a(this.j());
            this.a(uploadJob.performanceKey, VideoUploadStatus.a, uploadJob.performance);
            arrayList.add(uploadJob);
            this.c.put(uploadJob.id, uploadJob);
            Log.b(a, "Pushed job " + uploadJob.id);
        } while (true);
    }

    private void a(String string2, long l, VideoUploadStatus videoUploadStatus) {
        Intent intent = new Intent();
        intent.setAction("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS");
        intent.addCategory(this.getPackageName());
        intent.putExtra("PERFORMANCE_KEY", string2);
        intent.putExtra("FILE_UPLOAD_PROGRESS", l);
        intent.putExtra("FILE_UPLOAD_STATUS", (Serializable)((Object)videoUploadStatus));
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
        NotificationCenter.a().a("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", (Object)intent.getExtras());
    }

    /*
     * Exception decompiling
     */
    private void a(String var1_1, HashSet<String> var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl33 : TryStatement: try { 4[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:517)
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

    private void a(List<UploadJob> list) {
        NetworkResponse networkResponse = this.b(list);
        Log.b(a, "uploadResponse:" + (Object)((Object)networkResponse));
        if (networkResponse != null && networkResponse.c()) {
            this.c(list);
            return;
        }
        this.a(list, networkResponse);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void a(List<UploadJob> object, NetworkResponse networkResponse) {
        block14 : {
            block13 : {
                block12 : {
                    this.h((List<UploadJob>)object);
                    if (this.e((List<UploadJob>)object) || this.d((List<UploadJob>)object)) {
                        Log.b(a, "deleting jobs - recording not found or cancelled");
                        this.f((List<UploadJob>)object);
                        return;
                    }
                    if (networkResponse != null && (networkResponse.b == 1012 || networkResponse.b == 1028)) break block12;
                    if (networkResponse != null && networkResponse.b == 1030) break block13;
                    break block14;
                }
                Log.b(a, "deleting jobs - associated performance not found or already assigned");
                this.f((List<UploadJob>)object);
                Iterator iterator = object.iterator();
                while (iterator.hasNext()) {
                    UploadJob uploadJob = (UploadJob)iterator.next();
                    if (uploadJob.resourceInfo.mPerformanceResourceInfo.mResourceType != PerformanceManager.PerformanceResourceInfo.a) continue;
                    String string2 = uploadJob.performanceKey;
                    object = uploadJob.isOnboarding ? Analytics.c : Analytics.d;
                    com.smule.android.logging.Analytics.a(string2, object, Analytics.b, com.smule.android.logging.Analytics.b(uploadJob.performance), uploadJob.isJoin, com.smule.android.logging.Analytics.a(uploadJob.performance), "snp", null, (Integer)networkResponse.b, com.smule.android.logging.Analytics.c(uploadJob.performance));
                }
                return;
            }
            Iterator iterator = object.iterator();
            while (iterator.hasNext()) {
                UploadJob uploadJob = (UploadJob)iterator.next();
                Log.b(a, "invalid media jobID " + uploadJob.id + ": " + uploadJob.performanceKey);
                ++uploadJob.consecutiveFailures;
                uploadJob.invalidMedia = true;
                uploadJob.a(this.j());
                if (uploadJob.resourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager.PerformanceResourceInfo.a) {
                    String string3 = uploadJob.performanceKey;
                    object = uploadJob.isOnboarding ? Analytics.c : Analytics.d;
                    com.smule.android.logging.Analytics.a(string3, object, Analytics.b, com.smule.android.logging.Analytics.b(uploadJob.performance), uploadJob.isJoin, com.smule.android.logging.Analytics.a(uploadJob.performance), "snp", null, (Integer)networkResponse.b, com.smule.android.logging.Analytics.c(uploadJob.performance));
                }
                this.a(uploadJob.performanceKey, VideoUploadStatus.d, uploadJob.performance);
                this.a(uploadJob.performanceKey, 100, VideoUploadStatus.d);
            }
            return;
        }
        final ArrayList<UploadJob> arrayList = new ArrayList<UploadJob>();
        Iterator iterator = object.iterator();
        do {
            String string4;
            if (!iterator.hasNext()) {
                if (arrayList.isEmpty()) return;
                if (this.j == null) return;
                this.j.postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        if (FileUploaderService.this.d != null) {
                            FileUploaderService.this.d.a(arrayList);
                        }
                    }
                }, (long)((2 << ((UploadJob)arrayList.get((int)0)).consecutiveFailures) * 1000));
                return;
            }
            UploadJob uploadJob = (UploadJob)iterator.next();
            ++uploadJob.consecutiveFailures;
            uploadJob.a(this.j());
            if (uploadJob.consecutiveFailures < 4) {
                Log.b(a, "jobId:" + uploadJob.id + " fail:" + uploadJob.consecutiveFailures);
                arrayList.add(uploadJob);
                continue;
            }
            Log.b(a, "too many failures:" + uploadJob.id);
            String string5 = "os";
            String string6 = null;
            Object object2 = null;
            object = string5;
            Object object3 = object2;
            if (networkResponse != null) {
                string4 = networkResponse.m;
                object = string5;
                string6 = string4;
                object3 = object2;
                if (networkResponse.k != null) {
                    if (networkResponse.k.d()) {
                        object = "snp";
                        object3 = networkResponse.b;
                        string6 = string4;
                    } else {
                        object = "http";
                        string6 = Integer.toString(networkResponse.k.c());
                        object3 = object2;
                    }
                }
            }
            object2 = uploadJob.resourceInfo;
            if (object2.mPerformanceResourceInfo.mResourceType == PerformanceManager.PerformanceResourceInfo.b) {
                com.smule.android.logging.Analytics.a(uploadJob.performanceKey, (String)object, string6, (Integer)object3, uploadJob.songUid, uploadJob.arrangementKey);
                continue;
            }
            if (object2.mPerformanceResourceInfo.mResourceType != PerformanceManager.PerformanceResourceInfo.a) continue;
            string4 = uploadJob.performanceKey;
            object2 = uploadJob.isOnboarding ? Analytics.c : Analytics.d;
            com.smule.android.logging.Analytics.a(string4, object2, Analytics.b, com.smule.android.logging.Analytics.b(uploadJob.performance), uploadJob.isJoin, com.smule.android.logging.Analytics.a(uploadJob.performance), (String)object, string6, (Integer)object3, com.smule.android.logging.Analytics.c(uploadJob.performance));
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean a(String string2, VideoUploadStatus videoUploadStatus, PerformanceV2 performanceV2) {
        Map<String, Pair<VideoUploadStatus, PerformanceV2>> map = this.b;
        synchronized (map) {
            if (performanceV2 == null) {
                Log.b(a, "updateJobStatus:" + string2 + " removed");
                this.b.remove(string2);
                this.l();
                return true;
            }
            Pair<VideoUploadStatus, PerformanceV2> pair = this.b.get(string2);
            if (pair != null && pair.first == VideoUploadStatus.d) {
                Log.b(a, "updateJobStatus:" + string2 + " marked as invalid already");
                return false;
            }
            if (videoUploadStatus == VideoUploadStatus.d || videoUploadStatus != VideoUploadStatus.b) {
                Log.b(a, "updateJobStatus:" + string2 + " to " + (Object)((Object)videoUploadStatus));
                this.b.put(string2, (Pair)new Pair((Object)videoUploadStatus, (Object)performanceV2));
                this.l();
                return true;
            }
            pair = this.c.values().iterator();
            do {
                if (pair.hasNext()) continue;
                this.b.put(string2, (Pair)new Pair((Object)videoUploadStatus, (Object)performanceV2));
                this.l();
                Log.b(a, "updateJobStatus:" + string2 + " to " + (Object)((Object)videoUploadStatus));
                return true;
            } while (!((UploadJob)pair.next()).performanceKey.equals(string2));
            Log.b(a, "updateJobStatus:" + string2 + " upload still pending");
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private NetworkResponse b(List<UploadJob> object) {
        Object object2;
        final UploadJob uploadJob = (UploadJob)object.get(0);
        PerformancesAPI uploadType = uploadJob.isJoin ? PerformancesAPI.JOIN : PerformancesAPI.CREATE;
        if (object.size() == 1) {
            object2 = uploadJob.resourceInfo;
            this.c(uploadJob.performanceKey);
            if (uploadJob.d()) {
                return this.a(uploadJob);
            }
            if (object2.mPerformanceResourceInfo.mResourceType == PerformanceManager.PerformanceResourceInfo.b) {
                object = new File(object2.mResourceFilename);
                return TracksManager.a().a(uploadJob, (UploadJob.UploadResourceInfo)object2, new SnpRequest((File)object){
                    final /* synthetic */ File b;
                    {
                        this.b = file;
                    }

                    @Override
                    public void onChunkTransfered(SortedSet<UploadJob.Chunk> sortedSet) {
                        uploadJob.a(sortedSet);
                        uploadJob.a(FileUploaderService.this.j());
                        FileUploaderService.this.a(uploadJob.performanceKey, FileUploaderService.this.a(sortedSet, this.b.length()), VideoUploadStatus.a);
                    }
                }, new TracksManager.UploadAnalyticsCallback(){

                    @Override
                    public void a(UploadJob uploadJob, int n) {
                        com.smule.android.logging.Analytics.c(uploadJob.performanceKey, uploadJob.songUid, uploadJob.arrangementKey);
                    }
                }, uploadType, this.v);
            }
        }
        object2 = new ArrayList();
        Iterator iterator = object.iterator();
        while (iterator.hasNext()) {
            UploadJob uploadJob2 = (UploadJob)iterator.next();
            object = uploadJob2.resourceInfo;
            object2.add(object);
            if (uploadJob2.isNew) {
                if (object.mPerformanceResourceInfo.mResourceType == PerformanceManager.PerformanceResourceInfo.a) {
                    String string2 = uploadJob2.performanceKey;
                    object = uploadJob2.isOnboarding ? Analytics.c : Analytics.d;
                    com.smule.android.logging.Analytics.a(string2, object, Analytics.a, com.smule.android.logging.Analytics.b(uploadJob2.performance), uploadJob2.isJoin, com.smule.android.logging.Analytics.a(uploadJob2.performance), com.smule.android.logging.Analytics.c(uploadJob2.performance));
                }
                uploadJob2.isNew = false;
                uploadJob2.a(this.j());
            }
            this.c(uploadJob2.performanceKey);
        }
        return TracksManager.a().a(uploadJob, (ArrayList<UploadJob.UploadResourceInfo>)object2, uploadType, this.v);
    }

    private void c(String string2) {
        synchronized (this) {
            if (MagicNetwork.d().getFileUploaderServiceWifiOnly() && this.o.size() < MagicNetwork.d().getFileUploaderServiceUploadsDialogThreshold() * 3 && this.o.add(string2)) {
                this.getSharedPreferences("FILE_UPLOADER_SERVICE_SETTINGS", 0).edit().putStringSet("PROCESSED_UPLOADS_SETTINGS", this.o).apply();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void c(List<UploadJob> list) {
        Iterator<UploadJob> iterator = list.iterator();
        while (iterator.hasNext()) {
            UploadJob uploadJob = iterator.next();
            Object object = uploadJob.resourceInfo;
            if (object.mPerformanceResourceInfo.mResourceType == PerformanceManager.PerformanceResourceInfo.b) {
                com.smule.android.logging.Analytics.d(uploadJob.performanceKey, uploadJob.songUid, uploadJob.arrangementKey);
            } else if (object.mPerformanceResourceInfo.mResourceType == PerformanceManager.PerformanceResourceInfo.a && !uploadJob.audioAnalyticsFired) {
                Object object2 = uploadJob.performanceKey;
                object = uploadJob.isOnboarding ? Analytics.c : Analytics.d;
                com.smule.android.logging.Analytics.a((String)object2, object, Analytics.a, com.smule.android.logging.Analytics.b(uploadJob.performance), uploadJob.isJoin, com.smule.android.logging.Analytics.a(uploadJob.performance), null, null, null, com.smule.android.logging.Analytics.c(uploadJob.performance));
                if (uploadJob.isJoin) {
                    com.smule.android.logging.Analytics.a(uploadJob.performanceKey, uploadJob.boosted, this.r, this.q, AudioDefs.HeadphonesType.a(uploadJob.usedHeadphone, uploadJob.headphonesHadMic), com.smule.android.logging.Analytics.b(uploadJob.performance), com.smule.android.logging.Analytics.a(uploadJob.performance), com.smule.android.logging.Analytics.c(uploadJob.performance), uploadJob.performance.video, uploadJob.isAddVideoUsed);
                    object = "perf_join_create";
                } else {
                    object2 = uploadJob.performanceKey;
                    object = uploadJob.isOnboarding ? Analytics.c : Analytics.d;
                    com.smule.android.logging.Analytics.a((String)object2, object, this.r, this.q, AudioDefs.HeadphonesType.a(uploadJob.usedHeadphone, uploadJob.headphonesHadMic), com.smule.android.logging.Analytics.b(uploadJob.performance), com.smule.android.logging.Analytics.a(uploadJob.performance), com.smule.android.logging.Analytics.c(uploadJob.performance), uploadJob.performance.video, uploadJob.isAddVideoUsed);
                    object = "perf_start_create";
                }
                object2 = new ArrayList();
                object2.add(new Pair((Object)"ensemble_type", (Object)com.smule.android.logging.Analytics.a(uploadJob.performance).a()));
                if (uploadJob.isJoin) {
                    object2.add(new Pair((Object)"perfjoin_key", (Object)uploadJob.performanceKey));
                }
                MagicNetwork.d().logAppboyEvent((String)object, (List<Pair<String, String>>)object2);
                uploadJob.audioAnalyticsFired = true;
                uploadJob.a(this.j());
            }
            uploadJob.b(this.j());
            this.c.remove(uploadJob.id);
            object = this.f;
            synchronized (object) {
                Log.b(a, "mActiveTransfers:remove:" + uploadJob.id);
                this.f.remove(uploadJob.id);
                if (!this.a(uploadJob.performanceKey, VideoUploadStatus.b, uploadJob.performance)) continue;
            }
            this.a(list.get((int)0).performanceKey, 100, VideoUploadStatus.b);
        }
        return;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void d(String string2) {
        File[] arrfile = new File(this.j()).listFiles(new FileFilter(){

            @Override
            public boolean accept(File file) {
                if (file.toString().endsWith(".json") && !file.toString().endsWith("job_status.json")) {
                    return true;
                }
                return false;
            }
        });
        if (arrfile == null) {
            return;
        }
        int n = arrfile.length;
        int n2 = 0;
        while (n2 < n) {
            File file = arrfile[n2];
            try {
                UploadJob uploadJob = JsonUtils.a(JsonUtils.a().readTree(file), UploadJob.class);
                if (uploadJob.performanceKey.equals(string2)) {
                    uploadJob.b(this.j());
                    return;
                }
            }
            catch (Exception exception) {
                Log.d(a, "Failed to parse " + file.getAbsolutePath(), exception);
            }
            ++n2;
        }
    }

    private boolean d(List<UploadJob> object) {
        object = object.iterator();
        while (object.hasNext()) {
            UploadJob.UploadResourceInfo uploadResourceInfo = ((UploadJob)object.next()).resourceInfo;
            if (uploadResourceInfo.mResourceFilename == null || uploadResourceInfo.mResourceFilename.isEmpty() || new File(uploadResourceInfo.mResourceFilename).exists()) continue;
            return true;
        }
        return false;
    }

    private boolean e(List<UploadJob> object) {
        object = object.iterator();
        while (object.hasNext()) {
            if (!((UploadJob)object.next()).b()) continue;
            return true;
        }
        return false;
    }

    private void f(List<UploadJob> object) {
        object = object.iterator();
        while (object.hasNext()) {
            UploadJob uploadJob = (UploadJob)object.next();
            Log.b(a, "canceling jobID " + uploadJob.id);
            uploadJob.b(this.j());
            this.c.remove(uploadJob.id);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void g() {
        if (NetworkUtils.a((Context)this, this.a())) {
            Log.b(a, "Connected mWifi=" + this.s);
            Object object = this.t;
            synchronized (object) {
                this.u = true;
                this.t.notifyAll();
                return;
            }
        }
        Log.b(a, "Not Connected mWifi=" + this.s);
        Object object = this.t;
        synchronized (object) {
            this.u = false;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void g(List<UploadJob> object) {
        Set<String> set = this.f;
        synchronized (set) {
            object = object.iterator();
            while (object.hasNext()) {
                UploadJob uploadJob = (UploadJob)object.next();
                Log.b(a, "mActiveTransfers:add:" + uploadJob.id);
                this.f.add(uploadJob.id);
            }
            return;
        }
    }

    private void h() {
        this.getSharedPreferences("FILE_UPLOADER_SERVICE_SETTINGS", 0).edit().putBoolean("WIFI_ONLY", this.s.booleanValue()).apply();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void h(List<UploadJob> object) {
        Set<String> set = this.f;
        synchronized (set) {
            object = object.iterator();
            while (object.hasNext()) {
                UploadJob uploadJob = (UploadJob)object.next();
                Log.b(a, "mActiveTransfers:remove:" + uploadJob.id);
                this.f.remove(uploadJob.id);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void i() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("FILE_UPLOADER_SERVICE_SETTINGS", 0);
        Boolean bl = sharedPreferences.contains("WIFI_ONLY") ? Boolean.valueOf(sharedPreferences.getBoolean("WIFI_ONLY", false)) : null;
        this.s = bl;
        this.o = sharedPreferences.getStringSet("PROCESSED_UPLOADS_SETTINGS", new HashSet());
        this.p = sharedPreferences.getBoolean("DISPLAYED_SETTINGS_DIALOG_SETTINGS", false);
    }

    private String j() {
        String string2 = ResourceUtils.a((Context)this) + File.separator + "upload_queue";
        File file = new File(string2);
        if (!file.exists() && !file.mkdirs()) {
            Log.d(a, "Failed to create queue directory");
        }
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void k() {
        Map<String, Pair<VideoUploadStatus, PerformanceV2>> map = this.b;
        synchronized (map) {
            try {
                File file = new File(this.j() + File.separator + "job_status.json");
                if (!file.exists()) {
                    Log.c(a, "Creating new job_status.json");
                    if (!file.createNewFile()) {
                        Log.d(a, "Failed to create queue file");
                    }
                } else if (file.length() > 0) {
                    for (Map.Entry entry : JsonUtils.b(JsonUtils.a().readTree(file), new TypeReference<Map<String, String>>(){}).entrySet()) {
                        VideoUploadStatus videoUploadStatus = VideoUploadStatus.valueOf((String)entry.getValue());
                        if (videoUploadStatus == VideoUploadStatus.a || videoUploadStatus == VideoUploadStatus.d) continue;
                        Log.b(a, "Loading job status for " + (String)entry.getKey() + " " + (Object)((Object)videoUploadStatus));
                        this.b.put((String)entry.getKey(), (Pair<VideoUploadStatus, PerformanceV2>)new Pair((Object)videoUploadStatus, (Object)null));
                    }
                }
            }
            catch (Exception exception) {
                Log.d(a, "Failed to read job status map", exception);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void l() {
        try {
            FileWriter fileWriter = new FileWriter(this.j() + File.separator + "job_status.json");
            Object object = new HashMap(this.b.size());
            for (Map.Entry<String, Pair<VideoUploadStatus, PerformanceV2>> entry : this.b.entrySet()) {
                if (entry.getValue().first == VideoUploadStatus.a || entry.getValue().first == VideoUploadStatus.d) continue;
                object.put(entry.getKey(), entry.getValue().first);
            }
            if ((object = JsonUtils.a(object)) != null) {
                fileWriter.write((String)object);
            }
            fileWriter.close();
            return;
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to save job status map", iOException);
            return;
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void m() {
        Object object = new File(this.j());
        HashSet<String> hashSet = new HashSet<String>();
        File[] arrfile = object.listFiles(new FileFilter(){

            @Override
            public boolean accept(File file) {
                if (file.toString().endsWith(".json") && !file.toString().endsWith("job_status.json")) {
                    return true;
                }
                return false;
            }
        });
        if (arrfile == null) {
            this.a(ResourceUtils.a(), hashSet);
            this.a(ResourceUtils.b((Context)this), hashSet);
            return;
        }
        int n = arrfile.length;
        int n2 = 0;
        do {
            block17 : {
                String string2;
                if (n2 >= n) {
                    Log.b(a, "Queue filled, size=" + this.d.a());
                    this.a(ResourceUtils.a(), hashSet);
                    this.a(ResourceUtils.b((Context)this), hashSet);
                    return;
                }
                File file = arrfile[n2];
                if (file.length() == 0) {
                    try {
                        file.delete();
                    }
                    catch (Exception exception) {
                        MagicCrittercism.a("exception while deleting: " + file.getAbsolutePath());
                        Log.d(a, "exception while deleting", new DroidSing9737Exception("delete").initCause(exception));
                    }
                } else {
                    Object object2;
                    block16 : {
                        object = JsonUtils.a().readTree(file);
                        object2 = JsonUtils.a((JsonNode)object, UploadJob.class);
                        if (object2.performance != null) break block16;
                        if (!file.delete()) {
                            Log.b(a, "could not delete:" + file.getAbsolutePath());
                        }
                        break block17;
                        catch (JsonMappingException jsonMappingException) {
                            object2 = FileUploaderService.a(file);
                            MagicCrittercism.a("Mapping ex: " + file.getAbsolutePath() + "length:" + file.length() + " contents:" + (String)object2);
                            Log.d(a, "JsonMappingException", new DroidSing9737Exception("JsonMappingException").initCause((Throwable)jsonMappingException));
                            break block17;
                        }
                        catch (Exception exception) {
                            Log.d(a, "Failed to parse " + file.getAbsolutePath(), exception);
                            break block17;
                        }
                    }
                    try {
                        this.c.put(object2.id, (UploadJob)object2);
                        if (!TextUtils.isEmpty((CharSequence)object2.resourceInfo.mResourceFilename)) {
                            hashSet.add(object2.resourceInfo.mResourceFilename);
                        }
                        if (!object2.invalidMedia) {
                            this.d.a((UploadJob)object2);
                        }
                        string2 = object2.performance.performanceKey;
                        object = object2.invalidMedia ? VideoUploadStatus.d : VideoUploadStatus.a;
                    }
                    catch (Exception exception) {
                        Log.d(a, "Failed to parse " + file.getAbsolutePath(), exception);
                    }
                }
                break block17;
                this.a(string2, (VideoUploadStatus)((Object)object), object2.performance);
            }
            ++n2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void n() {
        Set<String> set = this.f;
        synchronized (set) {
            Iterator<String> iterator = this.f.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                object = this.c.get(object);
                object.c();
                this.d.a((UploadJob)object);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public VideoUploadStatus a(String string2) {
        Map<String, Pair<VideoUploadStatus, PerformanceV2>> map = this.b;
        synchronized (map) {
            Object object = this.b.get(string2);
            object = object != null ? (VideoUploadStatus)((Object)object.first) : VideoUploadStatus.e;
            if (object == VideoUploadStatus.c || object == VideoUploadStatus.d) {
                this.a(string2, (VideoUploadStatus)null, (PerformanceV2)null);
            }
            Log.b(a, "Job status for " + string2 + " is " + object);
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2, String string3, String string4) {
        Iterator<UploadJob> iterator = this.c.values().iterator();
        do {
            if (!iterator.hasNext()) {
                this.a(string2, (VideoUploadStatus)null, (PerformanceV2)null);
                return;
            }
            UploadJob uploadJob = iterator.next();
            if (!uploadJob.performanceKey.equals(string2)) continue;
            Log.b(a, "cancelling key:" + string2 + " id:" + uploadJob.id);
            this.d.b(uploadJob);
            Object object = this.f;
            synchronized (object) {
                if (this.f.contains(uploadJob.id)) {
                    uploadJob.c();
                } else {
                    this.d(string2);
                }
            }
            uploadJob.b(this.j());
            this.c.remove(uploadJob.id);
            if (uploadJob.resourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager.PerformanceResourceInfo.b) {
                com.smule.android.logging.Analytics.b(string2, string3, string4);
                continue;
            }
            if (uploadJob.resourceInfo.mPerformanceResourceInfo.mResourceType != PerformanceManager.PerformanceResourceInfo.a) continue;
            String string5 = uploadJob.performanceKey;
            object = uploadJob.isOnboarding ? Analytics.c : Analytics.d;
            com.smule.android.logging.Analytics.a(string5, object, Analytics.c, com.smule.android.logging.Analytics.b(uploadJob.performance), uploadJob.isJoin, com.smule.android.logging.Analytics.a(uploadJob.performance), null, null, null, com.smule.android.logging.Analytics.c(uploadJob.performance));
        } while (true);
    }

    public void a(boolean bl) {
        this.k = bl;
    }

    public boolean a() {
        if (this.s != null) {
            return this.s;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public FileUploaderService b(boolean bl) {
        Log.b(a, "Setting wifiOnly=" + bl);
        this.s = bl;
        this.h();
        if (bl) {
            com.smule.android.logging.Analytics.r();
        } else {
            com.smule.android.logging.Analytics.s();
        }
        this.n();
        return this;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(String string2) {
        Map<String, Pair<VideoUploadStatus, PerformanceV2>> map = this.b;
        synchronized (map) {
            this.a(string2, 100, VideoUploadStatus.c);
            this.a(string2, (VideoUploadStatus)null, (PerformanceV2)null);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean b() {
        boolean bl = false;
        boolean bl2 = false;
        synchronized (this) {
            boolean bl3 = MagicNetwork.d().getFileUploaderServiceWifiOnly();
            if (!bl3) {
                return bl2;
            }
            bl2 = bl;
            if (this.s == null) {
                bl2 = bl;
                if (!this.p) {
                    bl2 = bl;
                    if (this.o.size() >= MagicNetwork.d().getFileUploaderServiceUploadsDialogThreshold()) {
                        bl2 = true;
                    }
                }
            }
            if (!bl2) {
                bl = bl2;
                if (this.s == null) return bl;
            }
            this.p = true;
            this.getSharedPreferences("FILE_UPLOADER_SERVICE_SETTINGS", 0).edit().putBoolean("DISPLAYED_SETTINGS_DIALOG_SETTINGS", this.p).apply();
            return bl2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public List<PerformanceV2> c() {
        Map<String, Pair<VideoUploadStatus, PerformanceV2>> map = this.b;
        synchronized (map) {
            ArrayList<PerformanceV2> arrayList = new ArrayList<PerformanceV2>();
            Iterator<Pair<VideoUploadStatus, PerformanceV2>> iterator = this.b.values().iterator();
            while (iterator.hasNext()) {
                Pair<VideoUploadStatus, PerformanceV2> pair = iterator.next();
                if (pair.first != VideoUploadStatus.a && (pair.first != VideoUploadStatus.d || pair.second == null)) continue;
                arrayList.add((PerformanceV2)pair.second);
            }
            return arrayList;
        }
    }

    public void d() {
        synchronized (this) {
            this.n = this.m.submit(this.l);
            return;
        }
    }

    public void e() {
        synchronized (this) {
            if (this.n != null) {
                this.n.cancel(true);
                this.n = null;
            }
            return;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.e;
    }

    public void onCreate() {
        super.onCreate();
        Log.b(a, "onCreate " + (Object)((Object)this));
        HandlerThread handlerThread = new HandlerThread("IntentService[FileUploaderService]");
        handlerThread.start();
        this.i = handlerThread.getLooper();
        this.j = new ServiceHandler(this.i);
        this.i();
        NetworkUtils.b((Context)this, new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void run() {
                FileUploaderService.this.g();
                if (NetworkUtils.a((Context)FileUploaderService.this)) {
                    Set set = FileUploaderService.this.f;
                    synchronized (set) {
                        for (String string2 : FileUploaderService.this.f) {
                            UploadJob uploadJob = (UploadJob)FileUploaderService.this.c.get(string2);
                            if (uploadJob != null) {
                                FileUploaderService.this.c(uploadJob.performanceKey);
                                continue;
                            }
                            Log.d(a, "Missing job with id=" + string2);
                        }
                    }
                }
            }
        });
        this.a(false);
        this.d = new ChunkyQueue();
        this.l = new JobStatusChecker(this.b);
        this.m();
        this.k();
        this.h = Executors.newFixedThreadPool(this.g);
        for (int i = 0; i < this.g; ++i) {
            this.h.submit(new Consumer(this.d));
        }
    }

    public void onDestroy() {
        Log.b(a, "Destroying");
        this.i.quit();
        this.h.shutdown();
        super.onDestroy();
    }

    public void onStart(Intent intent, int n) {
        Message message = this.j.obtainMessage();
        message.arg1 = n;
        message.obj = intent;
        this.j.sendMessage(message);
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        if (intent != null) {
            this.onStart(intent, n2);
        }
        if (this.k) {
            return 3;
        }
        return 2;
    }

    private static class ChunkyQueue<T> {
        private final List<T> a = new ArrayList<T>();

        private ChunkyQueue() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        int a() {
            List<T> list = this.a;
            synchronized (list) {
                return this.a.size();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        List<T> a(TakePredicate<T> takePredicate) {
            ArrayList<T> arrayList = new ArrayList<T>();
            List<T> list = this.a;
            synchronized (list) {
                do {
                    if (!this.a.isEmpty()) {
                        T t = this.a.remove(0);
                        arrayList.add(t);
                        Iterator<T> iterator = this.a.iterator();
                        do {
                            if (!iterator.hasNext()) {
                                this.a.notify();
                                return arrayList;
                            }
                            T t2 = iterator.next();
                            if (!takePredicate.a(t, t2)) continue;
                            arrayList.add(t2);
                            iterator.remove();
                        } while (true);
                    }
                    try {
                        this.a.wait();
                    }
                    catch (InterruptedException interruptedException) {
                    }
                } while (true);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        void a(T t) {
            List<T> list = this.a;
            synchronized (list) {
                this.a.add(t);
                this.a.notify();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        void a(List<T> list) {
            List<T> list2 = this.a;
            synchronized (list2) {
                this.a.addAll(list);
                this.a.notify();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        void b(T t) {
            List<T> list = this.a;
            synchronized (list) {
                this.a.remove(t);
                this.a.notify();
                return;
            }
        }

        static interface TakePredicate<T> {
            public boolean a(T var1, T var2);
        }

    }

    private class Consumer
    implements Runnable {
        private final ChunkyQueue<UploadJob> b;

        public Consumer(ChunkyQueue<UploadJob> chunkyQueue) {
            this.b = chunkyQueue;
        }

        private List<UploadJob> a() throws InterruptedException {
            return this.b.a(new ChunkyQueue.TakePredicate<UploadJob>(){

                @Override
                public boolean a(UploadJob uploadJob, UploadJob uploadJob2) {
                    return Consumer.this.a(uploadJob, uploadJob2);
                }
            });
        }

        private void a(List<UploadJob> object) {
            object = object.iterator();
            while (object.hasNext()) {
                UploadJob uploadJob = (UploadJob)object.next();
                if (uploadJob.c(FileUploaderService.this.j())) continue;
                Log.d(a, "job:" + uploadJob.id + " doesn't exist");
                object.remove();
                FileUploaderService.this.c.remove(uploadJob.id);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private boolean a(UploadJob object, UploadJob object2) {
            block3 : {
                block2 : {
                    if (!object.performanceKey.equals(object2.performanceKey)) break block2;
                    object = object.resourceInfo.mPerformanceResourceInfo;
                    object2 = object2.resourceInfo.mPerformanceResourceInfo;
                    if (!object.mResourceType.equals((Object)PerformanceManager.PerformanceResourceInfo.b) && !object2.mResourceType.equals((Object)PerformanceManager.PerformanceResourceInfo.b) && object.mHostname.equals(object2.mHostname) && object.mPOP.equals(object2.mPOP)) break block3;
                }
                return false;
            }
            return true;
        }

        /*
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        @Override
        public void run() {
            do {
                boolean bl;
                List<UploadJob> list = FileUploaderService.this.t;
                // MONITORENTER : list
                while (!(bl = FileUploaderService.this.u)) {
                    try {
                        FileUploaderService.this.t.wait();
                    }
                    catch (InterruptedException interruptedException) {}
                }
                // MONITOREXIT : list
                Log.b(a, "Connected, checking for jobs");
                list = this.a();
                this.a(list);
                if (list.size() <= 0) continue;
                FileUploaderService.this.g(list);
                FileUploaderService.this.a(list);
                continue;
                break;
            } while (true);
            catch (InterruptedException interruptedException) {
                Log.a(a, "Consumer interrupted, exiting", interruptedException);
                return;
            }
        }

    }

    public static class DroidSing9737Exception
    extends Throwable {
        public DroidSing9737Exception(String string2) {
            super(string2);
        }
    }

    public class FileUploaderBinder
    extends Binder {
        public FileUploaderService a() {
            return FileUploaderService.this;
        }
    }

    private class JobStatusChecker
    implements Runnable {
        private final Map<String, Pair<VideoUploadStatus, PerformanceV2>> b;

        public JobStatusChecker(Map<String, Pair<VideoUploadStatus, PerformanceV2>> map) {
            this.b = map;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void run() {
            Object object = new HashSet();
            Object object2 = this.b;
            synchronized (object2) {
                for (Object object3 : this.b.keySet()) {
                    if (this.b.get((Object)object3).first != VideoUploadStatus.b) continue;
                    object.add(object3);
                }
            }
            object2 = new ArrayList();
            object = object.iterator();
            do {
                block15 : {
                    block14 : {
                        if (!object.hasNext()) break block14;
                        if (!Thread.interrupted()) break block15;
                        Log.b(a, "Status check stopped");
                    }
                    return;
                }
                object2.add(object.next());
                object.remove();
                if (object2.size() != 25 && object.hasNext()) continue;
                Object object4 = PerformanceManager.a().a((Collection<String>)object2);
                if (object4 == null || !object4.a() || object4.mPerformances == null || object4.mPerformances.size() != object2.size()) {
                    object4 = object2.iterator();
                    while (object4.hasNext()) {
                        Object object3;
                        object3 = (String)object4.next();
                        FileUploaderService.this.a((String)object3, 100, VideoUploadStatus.b);
                    }
                } else {
                    for (Object object3 : object4.mPerformances) {
                        if (object3 != null && object3.a()) {
                            FileUploaderService.this.a(object3.performanceKey, VideoUploadStatus.c, (PerformanceV2)object3);
                            FileUploaderService.this.a(object3.performanceKey, 100, VideoUploadStatus.c);
                            continue;
                        }
                        if (object3 == null) continue;
                        FileUploaderService.this.a(object3.performanceKey, 100, VideoUploadStatus.b);
                    }
                }
                object2.clear();
            } while (true);
        }
    }

    private final class ServiceHandler
    extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            FileUploaderService.this.a((Intent)message.obj);
        }
    }

    public static enum VideoUploadStatus {
        a,
        b,
        c,
        d,
        e;
        

        private VideoUploadStatus() {
        }
    }

}

