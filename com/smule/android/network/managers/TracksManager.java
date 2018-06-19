package com.smule.android.network.managers;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.millennialmedia.InterstitialAd.InterstitialErrorStatus;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PerformancesAPI$UploadType;
import com.smule.android.network.api.TracksAPI;
import com.smule.android.network.api.TracksAPI$UploadResourceRequest;
import com.smule.android.network.api.TracksAPI$UploadTrackAudioRequest;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest$PostProgressListener;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.uploader.UploadJob;
import com.smule.android.uploader.UploadJob.Chunk;
import com.smule.android.uploader.UploadJob.UploadResourceInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import okhttp3.Call;
import okhttp3.MultipartBody.Part;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.BitmapRequestBodyConverter;
import retrofit2.FileRequestBody;

public class TracksManager {
    private static final String f17200a = TracksManager.class.getName();
    private static TracksManager f17201b;
    private TracksAPI f17202c = ((TracksAPI) MagicNetwork.a().a(TracksAPI.class));

    public interface UploadAnalyticsCallback {
        void mo6303a(UploadJob uploadJob, int i);
    }

    public interface VideoDownloadCallback {
        void mo6606a(int i);

        void mo6607a(Long l, Long l2);
    }

    public class VideoDownloader extends AsyncTask<Void, Long, Integer> {
        final /* synthetic */ TracksManager f17194a;
        private String f17195b;
        private VideoDownloadCallback f17196c;
        private InputStream f17197d = null;
        private FileOutputStream f17198e;
        private Context f17199f;

        @TargetApi(18)
        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m18468a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m18469a((Integer) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            m18470a((Long[]) objArr);
        }

        VideoDownloader(TracksManager tracksManager, Context context, String str, VideoDownloadCallback videoDownloadCallback) {
            this.f17194a = tracksManager;
            this.f17196c = videoDownloadCallback;
            this.f17195b = str;
            this.f17199f = context;
        }

        @TargetApi(18)
        protected Integer m18468a(Void... voidArr) {
            long j = 0;
            int i = 0;
            Call newCall = new Builder().m27801a(MagicNetwork.c).m27812b().newCall(new Request.Builder().m27868a(this.f17195b).m27866a().m27877b());
            try {
                File file = new File(this.f17199f.getExternalFilesDir(null).toString(), "sing_video");
                if (!file.exists()) {
                    file.mkdir();
                }
                File file2 = new File(file, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
                if (file2.exists()) {
                    file2.delete();
                }
                file2.createNewFile();
                Response b = newCall.mo7157b();
                if (b.m27932c() != 200) {
                    return Integer.valueOf(2);
                }
                long blockSizeLong;
                this.f17198e = new FileOutputStream(file2);
                this.f17197d = b.m27937h().byteStream();
                byte[] bArr = new byte[4096];
                long contentLength = b.m27937h().contentLength();
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
                if (VERSION.SDK_INT >= 18) {
                    blockSizeLong = statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
                } else {
                    blockSizeLong = (long) (statFs.getAvailableBlocks() * statFs.getBlockSize());
                }
                Integer valueOf;
                if (blockSizeLong < contentLength) {
                    valueOf = Integer.valueOf(1);
                    return valueOf;
                }
                publishProgress(new Long[]{Long.valueOf(0), Long.valueOf(contentLength)});
                while (true) {
                    int read = this.f17197d.read(bArr);
                    if (read > 0 && read != -1) {
                        j += (long) read;
                        publishProgress(new Long[]{Long.valueOf(j), Long.valueOf(contentLength)});
                        if (isCancelled()) {
                            valueOf = Integer.valueOf(2);
                            m18467a();
                            return valueOf;
                        }
                        try {
                            this.f17198e.write(bArr, 0, read);
                        } catch (IOException e) {
                            valueOf = Integer.valueOf(2);
                        } finally {
                            m18467a();
                        }
                    }
                }
                if (j != contentLength) {
                    i = 2;
                }
                valueOf = Integer.valueOf(i);
                m18467a();
                return valueOf;
            } catch (IOException e2) {
                e2.printStackTrace();
                return Integer.valueOf(2);
            }
        }

        private void m18467a() {
            try {
                if (this.f17197d != null) {
                    this.f17197d.close();
                }
                if (this.f17198e != null) {
                    this.f17198e.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        protected void onCancelled() {
            super.onCancelled();
            m18467a();
        }

        protected void m18470a(Long... lArr) {
            this.f17196c.mo6607a(lArr[0], lArr[1]);
        }

        protected void m18469a(Integer num) {
            this.f17196c.mo6606a(num.intValue());
        }
    }

    public static synchronized TracksManager m18471a() {
        TracksManager tracksManager;
        synchronized (TracksManager.class) {
            if (f17201b == null) {
                f17201b = new TracksManager();
            }
            tracksManager = f17201b;
        }
        return tracksManager;
    }

    private TracksManager() {
    }

    public NetworkResponse m18476a(File file) {
        return NetworkUtils.m18104a(this.f17202c.uploadAudio(Part.createFormData("audio", file.getName(), new FileRequestBody(file))));
    }

    public NetworkResponse m18477a(@NonNull File file, @NonNull UploadJob uploadJob) {
        String str = uploadJob.performanceKey;
        return NetworkUtils.m18104a(this.f17202c.uploadTrackAudio(new TracksAPI$UploadTrackAudioRequest().setPerformanceKey(str).setTrackKey(uploadJob.uploadKey), Part.createFormData("audio", file.getName(), new FileRequestBody(file))));
    }

    public NetworkResponse m18478a(@NonNull File file, @NonNull UploadJob uploadJob, @NonNull SnpRequest$PostProgressListener snpRequest$PostProgressListener, @NonNull UploadAnalyticsCallback uploadAnalyticsCallback) {
        int i;
        long length = file.length();
        long j = uploadJob.uploadResourceInfo.mSliceSize;
        String str = uploadJob.performanceKey;
        String str2 = uploadJob.uploadKey;
        SortedSet sortedSet = uploadJob.uploadedChunks;
        String str3 = str + "-" + str2;
        String str4 = "attachment; filename=\"" + file.getName() + "\"";
        int i2 = 0;
        NetworkResponse networkResponse = null;
        while (true) {
            if (sortedSet != null && !sortedSet.isEmpty() && ((Chunk) sortedSet.first()).end >= length - 1) {
                return networkResponse;
            }
            long j2;
            long j3;
            long j4;
            NetworkResponse a;
            if (sortedSet == null || sortedSet.isEmpty()) {
                j2 = 0;
            } else {
                j2 = ((Chunk) sortedSet.first()).end + 1;
            }
            if (sortedSet == null || sortedSet.size() <= 1) {
                j3 = length;
            } else {
                j3 = ((Chunk) sortedSet.tailSet(new Chunk(j2, 1 + j2)).first()).start;
            }
            if (j2 + j < j3) {
                j4 = j;
            } else {
                j4 = j3 - j2;
            }
            String str5 = "bytes " + j2 + "-" + ((j2 + j4) - 1) + "/" + length;
            Log.b(f17200a, "uploadMedia:" + str5);
            if (uploadJob.uploadResourceInfo.mPerformanceResourceInfo.mResourceType == PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b) {
                a = NetworkUtils.m18104a(this.f17202c.uploadVideo(MagicNetwork.d().getVideoServerHost(), str3, str5, str4, str, str2, new FileRequestBody(file, j2, (int) j4)));
            } else {
                a = NetworkUtils.m18104a(this.f17202c.uploadAudioResource(str3, str5, str4, str, str2, new FileRequestBody(file, j2, (int) j4)));
            }
            if (a.c()) {
                snpRequest$PostProgressListener.onChunkTransfered(m18472a("0-" + (length - 1) + "/" + length));
                return a;
            }
            if (a.k != null && a.k.m27932c() == InterstitialErrorStatus.EXPIRED) {
                try {
                    snpRequest$PostProgressListener.onChunkTransfered(m18472a(a.j));
                    i = i2;
                } catch (NumberFormatException e) {
                    Log.e(f17200a, "Failed to parse " + a.j);
                    i = i2 + 1;
                    uploadAnalyticsCallback.mo6303a(uploadJob, i);
                }
            } else if (i2 >= 2) {
                return a;
            } else {
                i = i2 + 1;
                uploadAnalyticsCallback.mo6303a(uploadJob, i);
            }
            if (Thread.currentThread().isInterrupted()) {
                return null;
            }
            i2 = i;
            networkResponse = a;
        }
    }

    private static SortedSet<Chunk> m18472a(String str) {
        SortedSet<Chunk> treeSet = new TreeSet();
        for (String split : TextUtils.split(str, ",")) {
            String[] split2 = TextUtils.split(TextUtils.split(split, "/")[0], "-");
            treeSet.add(new Chunk(Long.parseLong(split2[0]), Long.parseLong(split2[1])));
        }
        return treeSet;
    }

    public VideoDownloader m18481a(Context context, String str, VideoDownloadCallback videoDownloadCallback) {
        VideoDownloader videoDownloader = new VideoDownloader(this, context, str, videoDownloadCallback);
        videoDownloader.execute(new Void[0]);
        return videoDownloader;
    }

    public NetworkResponse m18480a(String str, @Nullable String str2, ArrayList<UploadResourceInfo> arrayList, PerformancesAPI$UploadType performancesAPI$UploadType) {
        Part a;
        Part a2;
        TracksAPI$UploadResourceRequest uploadType = new TracksAPI$UploadResourceRequest().setPerformanceKey(str).setTrackKey(str2).setUploadType(performancesAPI$UploadType);
        UploadResourceInfo uploadResourceInfo = (UploadResourceInfo) arrayList.get(0);
        Part a3 = m18473a(uploadResourceInfo, 1);
        uploadType.setFile1ResourceInfo(uploadResourceInfo.mPerformanceResourceInfo);
        if (arrayList.size() > 1) {
            UploadResourceInfo uploadResourceInfo2 = (UploadResourceInfo) arrayList.get(1);
            uploadType.setFile2ResourceInfo(uploadResourceInfo2.mPerformanceResourceInfo);
            a = m18473a(uploadResourceInfo2, 2);
        } else {
            a = null;
        }
        if (arrayList.size() > 2) {
            uploadResourceInfo2 = (UploadResourceInfo) arrayList.get(2);
            uploadType.setFile3ResourceInfo(uploadResourceInfo2.mPerformanceResourceInfo);
            a2 = m18473a(uploadResourceInfo2, 3);
        } else {
            a2 = null;
        }
        return NetworkUtils.m18104a(this.f17202c.uploadResources(uploadResourceInfo.mPerformanceResourceInfo.mHostname, uploadResourceInfo.mPerformanceResourceInfo.mPOP, uploadType, a3, a, a2));
    }

    public NetworkResponse m18479a(String str, PerformanceResourceInfo performanceResourceInfo, Bitmap bitmap) {
        TracksAPI$UploadResourceRequest uploadType = new TracksAPI$UploadResourceRequest().setPerformanceKey(str).setUploadType(PerformancesAPI$UploadType.UPDATE);
        Part createFormData = Part.createFormData("file1", "coverArt.jpg", BitmapRequestBodyConverter.convertTo(bitmap));
        uploadType.setFile1ResourceInfo(performanceResourceInfo);
        return NetworkUtils.m18104a(this.f17202c.uploadResources(performanceResourceInfo.mHostname, performanceResourceInfo.mPOP, uploadType, createFormData, null, null));
    }

    private Part m18473a(UploadResourceInfo uploadResourceInfo, int i) {
        if (uploadResourceInfo.mResourceFilename == null) {
            return null;
        }
        File file = new File(uploadResourceInfo.mResourceFilename);
        RequestBody fileRequestBody = new FileRequestBody(file);
        return Part.createFormData("file" + i, m18474b(file.getName()) + uploadResourceInfo.mPerformanceResourceInfo.mResourceType.m18255b(), fileRequestBody);
    }

    private String m18474b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    public NetworkResponse m18475a(UploadJob uploadJob, UploadResourceInfo uploadResourceInfo, SnpRequest$PostProgressListener snpRequest$PostProgressListener, UploadAnalyticsCallback uploadAnalyticsCallback, PerformancesAPI$UploadType performancesAPI$UploadType) {
        PerformanceResourceInfo performanceResourceInfo = uploadResourceInfo.mPerformanceResourceInfo;
        File file = new File(uploadResourceInfo.mResourceFilename);
        long length = file.length();
        long j = uploadResourceInfo.mSliceSize;
        SortedSet sortedSet = uploadJob.uploadedChunks;
        String str = uploadJob.performanceKey + "-" + uploadJob.trackKey;
        String str2 = "attachment; filename=\"" + str + "\"";
        int i = 0;
        NetworkResponse networkResponse = null;
        while (true) {
            if (sortedSet != null && !sortedSet.isEmpty() && ((Chunk) sortedSet.first()).end >= length - 1) {
                break;
            }
            long j2;
            long j3;
            if (sortedSet == null || sortedSet.isEmpty()) {
                j2 = 0;
            } else {
                j2 = ((Chunk) sortedSet.first()).end + 1;
            }
            if (sortedSet == null || sortedSet.size() <= 1) {
                j3 = length;
            } else {
                j3 = ((Chunk) sortedSet.tailSet(new Chunk(j2, 1 + j2)).first()).start;
            }
            if (j2 + j < j3) {
                j3 = j;
            } else {
                j3 -= j2;
            }
            String str3 = "bytes " + j2 + "-" + ((j2 + j3) - 1) + "/" + length;
            Log.b(f17200a, "uploadMedia:" + str3);
            networkResponse = NetworkUtils.m18104a(this.f17202c.uploadVideo(performanceResourceInfo.mHostname, str2, str, performanceResourceInfo.mPOP, uploadJob.performanceKey, performanceResourceInfo.mResourceId, performanceResourceInfo.mResourceType.toString(), uploadJob.trackKey, performancesAPI$UploadType, str3, new FileRequestBody(file, j2, (int) j3)));
            if (networkResponse.c()) {
                snpRequest$PostProgressListener.onChunkTransfered(m18472a("0-" + (length - 1) + "/" + length));
                return networkResponse;
            }
            int i2;
            if (networkResponse.k == null || networkResponse.k.m27932c() != InterstitialErrorStatus.EXPIRED) {
                if (i >= 2) {
                    break;
                }
                i2 = i + 1;
                uploadAnalyticsCallback.mo6303a(uploadJob, i2);
            } else {
                try {
                    snpRequest$PostProgressListener.onChunkTransfered(m18472a(networkResponse.j));
                    i2 = i;
                } catch (NumberFormatException e) {
                    Log.e(f17200a, "Failed to parse " + networkResponse.j);
                    i2 = i + 1;
                    uploadAnalyticsCallback.mo6303a(uploadJob, i2);
                }
            }
            if (Thread.currentThread().isInterrupted()) {
                return null;
            }
            i = i2;
        }
        return networkResponse;
    }
}
