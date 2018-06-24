/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Environment
 *  android.os.StatFs
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 *  okhttp3.Call
 *  okhttp3.MultipartBody
 *  okhttp3.MultipartBody$Part
 *  okhttp3.OkHttpClient
 *  okhttp3.OkHttpClient$Builder
 *  okhttp3.Protocol
 *  okhttp3.Request
 *  okhttp3.Request$Builder
 *  okhttp3.RequestBody
 *  okhttp3.Response
 *  okhttp3.ResponseBody
 *  retrofit2.BitmapRequestBodyConverter
 *  retrofit2.Call
 *  retrofit2.FileRequestBody
 */
package com.smule.android.network.managers;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.api.TracksAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.uploader.UploadJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.BitmapRequestBodyConverter;
import retrofit2.FileRequestBody;

public class TracksManager {
    private static final String a = TracksManager.class.getName();
    private static TracksManager b;
    private com.smule.android.network.api.TracksAPI c = MagicNetwork.a().a(com.smule.android.network.api.TracksAPI.class);

    private TracksManager() {
    }

    public static TracksManager a() {
        synchronized (TracksManager.class) {
            if (b == null) {
                b = new TracksManager();
            }
            TracksManager tracksManager = b;
            return tracksManager;
        }
    }

    private static SortedSet<UploadJob.Chunk> a(String arrstring) {
        TreeSet<UploadJob.Chunk> treeSet = new TreeSet<UploadJob.Chunk>();
        arrstring = TextUtils.split((String)arrstring, (String)",");
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            String[] arrstring2 = TextUtils.split((String)TextUtils.split((String)arrstring[i], (String)"/")[0], (String)"-");
            treeSet.add(new UploadJob.Chunk(Long.parseLong(arrstring2[0]), Long.parseLong(arrstring2[1])));
        }
        return treeSet;
    }

    private MultipartBody.Part a(UploadJob.UploadResourceInfo object, int n) {
        if (object.mResourceFilename != null) {
            File file = new File(object.mResourceFilename);
            FileRequestBody fileRequestBody = new FileRequestBody(file);
            object = this.b(file.getName()) + object.mPerformanceResourceInfo.mResourceType.b();
            return MultipartBody.Part.createFormData((String)("file" + n), (String)object, (RequestBody)fileRequestBody);
        }
        return null;
    }

    private String b(String string2) {
        int n = string2.lastIndexOf(46);
        String string3 = string2;
        if (n != -1) {
            string3 = string2.substring(0, n);
        }
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public NetworkResponse a(UploadJob uploadJob, UploadJob.UploadResourceInfo object, SnpRequest postProgressListener, UploadAnalyticsCallback uploadAnalyticsCallback, PerformancesAPI uploadType, NetworkPreconditionsCallback networkPreconditionsCallback) {
        Object object2;
        block8 : {
            PerformanceManager.PerformanceResourceInfo performanceResourceInfo = object.mPerformanceResourceInfo;
            File file = new File(object.mResourceFilename);
            long l = file.length();
            long l2 = object.mSliceSize;
            SortedSet<UploadJob.Chunk> sortedSet = uploadJob.uploadedChunks;
            String string2 = uploadJob.performanceKey + "-" + uploadJob.trackKey;
            String string3 = "attachment; filename=\"" + string2 + "\"";
            int n = 0;
            object = null;
            do {
                if (sortedSet != null && !sortedSet.isEmpty()) {
                    object2 = object;
                    if (sortedSet.first().end >= l - 1) break block8;
                }
                long l3 = sortedSet == null || sortedSet.isEmpty() ? 0 : sortedSet.first().end + 1;
                long l4 = sortedSet != null && sortedSet.size() > 1 ? sortedSet.tailSet((UploadJob.Chunk)new UploadJob.Chunk((long)l3, (long)(1 + l3))).first().start : l;
                l4 = l3 + l2 < l4 ? l2 : (l4 -= l3);
                object = "bytes " + l3 + "-" + (l3 + l4 - 1) + "/" + l;
                Log.b(a, "uploadMedia:" + (String)object);
                object2 = new FileRequestBody(file, l3, (int)l4);
                if (!networkPreconditionsCallback.a()) {
                    Log.b(a, "Missing network connectivity");
                    return null;
                }
                object = NetworkUtils.a(this.c.uploadVideo(performanceResourceInfo.mHostname, string3, string2, performanceResourceInfo.mPOP, uploadJob.performanceKey, performanceResourceInfo.mResourceId, performanceResourceInfo.mResourceType.toString(), uploadJob.trackKey, uploadType, (String)object, (FileRequestBody)object2));
                if (object.c()) {
                    postProgressListener.onChunkTransfered(TracksManager.a("0-" + (l - 1) + "/" + l));
                    return object;
                }
                if (object.k != null && object.k.c() == 201) {
                    try {
                        postProgressListener.onChunkTransfered(TracksManager.a(object.j));
                    }
                    catch (NumberFormatException numberFormatException) {
                        Log.e(a, "Failed to parse " + object.j);
                        ++n;
                        if (!networkPreconditionsCallback.a()) {
                            return object;
                        }
                        uploadAnalyticsCallback.a(uploadJob, n);
                    }
                    continue;
                }
                object2 = object;
                if (n >= 2) break block8;
                ++n;
                object2 = object;
                if (!networkPreconditionsCallback.a()) break block8;
                uploadAnalyticsCallback.a(uploadJob, n);
            } while (!uploadJob.a());
            return null;
        }
        return object2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public NetworkResponse a(UploadJob object, ArrayList<UploadJob.UploadResourceInfo> object2, PerformancesAPI object3, NetworkPreconditionsCallback networkPreconditionsCallback) {
        Object object4 = object.performanceKey;
        object = object.trackKey;
        object3 = new com.smule.android.network.core.SnpRequest(){
            public PerformanceManager.PerformanceResourceInfo file1ResourceInfo;
            public PerformanceManager.PerformanceResourceInfo file2ResourceInfo;
            public PerformanceManager.PerformanceResourceInfo file3ResourceInfo;
            public String performanceKey;
            public String trackKey;
            public PerformancesAPI uploadType;

            public TracksAPI setFile1ResourceInfo(PerformanceManager.PerformanceResourceInfo performanceResourceInfo) {
                this.file1ResourceInfo = performanceResourceInfo;
                return this;
            }

            public TracksAPI setFile2ResourceInfo(PerformanceManager.PerformanceResourceInfo performanceResourceInfo) {
                this.file2ResourceInfo = performanceResourceInfo;
                return this;
            }

            public TracksAPI setFile3ResourceInfo(PerformanceManager.PerformanceResourceInfo performanceResourceInfo) {
                this.file3ResourceInfo = performanceResourceInfo;
                return this;
            }

            public TracksAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public TracksAPI setTrackKey(String string2) {
                this.trackKey = string2;
                return this;
            }

            public TracksAPI setUploadType(PerformancesAPI uploadType) {
                this.uploadType = uploadType;
                return this;
            }
        }.setPerformanceKey((String)object4).setTrackKey((String)object).setUploadType((Object)object3);
        object4 = (UploadJob.UploadResourceInfo)object2.get(0);
        MultipartBody.Part part = this.a((UploadJob.UploadResourceInfo)object4, 1);
        object3.setFile1ResourceInfo(object4.mPerformanceResourceInfo);
        if (object2.size() > 1) {
            object = (UploadJob.UploadResourceInfo)object2.get(1);
            object3.setFile2ResourceInfo(object.mPerformanceResourceInfo);
            object = this.a((UploadJob.UploadResourceInfo)object, 2);
        } else {
            object = null;
        }
        if (object2.size() > 2) {
            object2 = (UploadJob.UploadResourceInfo)object2.get(2);
            object3.setFile3ResourceInfo(object2.mPerformanceResourceInfo);
            object2 = this.a((UploadJob.UploadResourceInfo)object2, 3);
        } else {
            object2 = null;
        }
        if (!networkPreconditionsCallback.a()) {
            Log.b(a, "Missing network connectivity");
            return null;
        }
        return NetworkUtils.a(this.c.uploadResources(object4.mPerformanceResourceInfo.mHostname, object4.mPerformanceResourceInfo.mPOP, (Object)object3, part, (MultipartBody.Part)object, (MultipartBody.Part)object2));
    }

    public NetworkResponse a(File file) {
        return NetworkUtils.a(this.c.uploadAudio(MultipartBody.Part.createFormData((String)"audio", (String)file.getName(), (RequestBody)new FileRequestBody(file))));
    }

    public NetworkResponse a(@NonNull File file, @NonNull UploadJob object) {
        String string2 = object.performanceKey;
        object = object.uploadKey;
        return NetworkUtils.a(this.c.uploadTrackAudio(new com.smule.android.network.core.SnpRequest(){
            public String performanceKey;
            public String trackKey;

            public TracksAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public TracksAPI setTrackKey(String string2) {
                this.trackKey = string2;
                return this;
            }
        }.setPerformanceKey(string2).setTrackKey((String)object), MultipartBody.Part.createFormData((String)"audio", (String)file.getName(), (RequestBody)new FileRequestBody(file))));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public NetworkResponse a(@NonNull File file, @NonNull UploadJob uploadJob, @NonNull SnpRequest postProgressListener, @NonNull UploadAnalyticsCallback uploadAnalyticsCallback, @NonNull NetworkPreconditionsCallback networkPreconditionsCallback) {
        long l = file.length();
        long l2 = uploadJob.resourceInfo.mSliceSize;
        String string2 = uploadJob.performanceKey;
        String string3 = uploadJob.uploadKey;
        SortedSet<UploadJob.Chunk> sortedSet = uploadJob.uploadedChunks;
        String string4 = string2 + "-" + string3;
        String string5 = "attachment; filename=\"" + file.getName() + "\"";
        int n = 0;
        Object object = null;
        do {
            Object object2;
            if (sortedSet != null && !sortedSet.isEmpty() && sortedSet.first().end >= l - 1) {
                return object;
            }
            long l3 = sortedSet == null || sortedSet.isEmpty() ? 0 : sortedSet.first().end + 1;
            long l4 = sortedSet != null && sortedSet.size() > 1 ? sortedSet.tailSet((UploadJob.Chunk)new UploadJob.Chunk((long)l3, (long)(1 + l3))).first().start : l;
            l4 = l3 + l2 < l4 ? l2 : (l4 -= l3);
            object = "bytes " + l3 + "-" + (l3 + l4 - 1) + "/" + l;
            Log.b(a, "uploadMedia:" + (String)object);
            if (!networkPreconditionsCallback.a()) {
                Log.b(a, "Missing network connectivity");
                return null;
            }
            object = uploadJob.resourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager.PerformanceResourceInfo.b ? NetworkUtils.a(this.c.uploadVideo(MagicNetwork.d().getVideoServerHost(), string4, (String)object, string5, string2, string3, new FileRequestBody(file, l3, (int)l4))) : NetworkUtils.a(this.c.uploadAudioResource(string4, (String)object, string5, string2, string3, new FileRequestBody(file, l3, (int)l4)));
            if (object.c()) {
                postProgressListener.onChunkTransfered(TracksManager.a("0-" + (l - 1) + "/" + l));
                return object;
            }
            if (object.k != null && object.k.c() == 201) {
                try {
                    postProgressListener.onChunkTransfered(TracksManager.a(object.j));
                }
                catch (NumberFormatException numberFormatException) {
                    Log.e(a, "Failed to parse " + object.j);
                    object2 = object;
                    if (!networkPreconditionsCallback.a()) return object2;
                    uploadAnalyticsCallback.a(uploadJob, ++n);
                }
                continue;
            }
            object2 = object;
            if (n >= 2) return object2;
            object2 = object;
            if (!networkPreconditionsCallback.a()) return object2;
            uploadAnalyticsCallback.a(uploadJob, ++n);
        } while (!uploadJob.a());
        return null;
    }

    public NetworkResponse a(String object, PerformanceManager.PerformanceResourceInfo performanceResourceInfo, Bitmap bitmap) {
        object = new .setPerformanceKey((String)object).setUploadType(PerformancesAPI.UPDATE);
        bitmap = MultipartBody.Part.createFormData((String)"file1", (String)"coverArt.jpg", (RequestBody)BitmapRequestBodyConverter.convertTo((Bitmap)bitmap));
        object.setFile1ResourceInfo(performanceResourceInfo);
        return NetworkUtils.a(this.c.uploadResources(performanceResourceInfo.mHostname, performanceResourceInfo.mPOP, (Object)object, (MultipartBody.Part)bitmap, null, null));
    }

    public VideoDownloader a(Context object, String string2, VideoDownloadCallback videoDownloadCallback) {
        object = new VideoDownloader((Context)object, string2, videoDownloadCallback);
        object.execute((Object[])new Void[0]);
        return object;
    }

    public static interface NetworkPreconditionsCallback {
        public boolean a();
    }

    public static interface UploadAnalyticsCallback {
        public void a(UploadJob var1, int var2);
    }

    public static interface VideoDownloadCallback {
        public void a(int var1);

        public void a(Long var1, Long var2);
    }

    public class VideoDownloader
    extends AsyncTask<Void, Long, Integer> {
        private String b;
        private VideoDownloadCallback c;
        private InputStream d;
        private FileOutputStream e;
        private Context f;

        VideoDownloader(Context context, String string2, VideoDownloadCallback videoDownloadCallback) {
            this.d = null;
            this.c = videoDownloadCallback;
            this.b = string2;
            this.f = context;
        }

        private void a() {
            try {
                if (this.d != null) {
                    this.d.close();
                }
                if (this.e != null) {
                    this.e.close();
                }
                return;
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return;
            }
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        @TargetApi(value=18)
        protected /* varargs */ Integer a(Void ... var1_1) {
            var6_5 = 0;
            var2_6 = 0;
            var1_1 = new OkHttpClient.Builder().a(MagicNetwork.c).b().newCall(new Request.Builder().a(this.b).a().b());
            var10_7 = new byte[](this.f.getExternalFilesDir(null).toString(), "sing_video");
            if (!var10_7.exists()) {
                var10_7.mkdir();
            }
            if ((var10_7 = new File((File)var10_7, "video")).exists()) {
                var10_7.delete();
            }
            var10_7.createNewFile();
            var1_1 = var1_1.b();
            if (var1_1.c() != 200) return 2;
            this.e = new FileOutputStream((File)var10_7);
            this.d = var1_1.h().byteStream();
            var10_7 = new byte[4096];
            var8_8 = var1_1.h().contentLength();
            var1_1 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (Build.VERSION.SDK_INT >= 18) {
                var4_9 = var1_1.getAvailableBlocksLong();
                var4_9 = var1_1.getBlockSizeLong() * var4_9;
            } else {
                var4_9 = var1_1.getAvailableBlocks() * var1_1.getBlockSize();
            }
            if (var4_9 < var8_8) {
                this.a();
                return 1;
            }
            this.publishProgress((Object[])new Long[]{0, var8_8});
            var4_9 = var6_5;
            while ((var3_10 = this.d.read(var10_7)) > 0 && var3_10 != -1) {
                this.publishProgress((Object[])new Long[]{var4_9 += (long)var3_10, var8_8});
                if (!this.isCancelled()) break block19;
            }
            ** GOTO lbl48
            {
                block19 : {
                    this.a();
                    return 2;
                }
                try {
                    this.e.write(var10_7, 0, var3_10);
                    continue;
                }
                catch (IOException var1_3) {
                    this.a();
                    return 2;
                    catch (Throwable var1_4) {
                        this.a();
                        throw var1_4;
                    }
lbl48: // 1 sources:
                    if (var4_9 != var8_8) {
                        var2_6 = 2;
                    }
                    try {
                        this.a();
                    }
                    catch (IOException var1_2) {
                        var1_2.printStackTrace();
                        return 2;
                        break;
                    }
                    return var2_6;
                }
            }
        }

        protected void a(Integer n) {
            this.c.a(n);
        }

        protected /* varargs */ void a(Long ... arrlong) {
            this.c.a(arrlong[0], arrlong[1]);
        }

        @TargetApi(value=18)
        protected /* synthetic */ Object doInBackground(Object[] arrobject) {
            return this.a((Void[])arrobject);
        }

        protected void onCancelled() {
            super.onCancelled();
            this.a();
        }

        protected /* synthetic */ void onPostExecute(Object object) {
            this.a((Integer)object);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] arrobject) {
            this.a((Long[])arrobject);
        }
    }

}

