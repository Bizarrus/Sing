package com.smule.singandroid.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.location.Location;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.PerformancesAPI$EnsembleType;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResourceInfo$ResourceType;
import com.smule.android.network.managers.PerformanceManager.CreateOrJoinResponse;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.managers.PerformanceManager.PreuploadResponse;
import com.smule.android.network.managers.TracksManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.uploader.UploadJob.UploadResourceInfo;
import com.smule.android.utils.LocationUtils;
import com.smule.android.utils.ResourceUtils;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.SingCoreBridge;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.adapters.profile.ProfilePerformanceDataSource;
import com.smule.singandroid.audio.AudioInterface;
import com.smule.singandroid.video.VideoFilterManager;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.Future;
import org.json.JSONObject;

public class PerformanceCreateUtil {
    private static final String f24881a = PerformanceCreateUtil.class.getName();
    private Long f24882b;
    private String f24883c;
    private long f24884d;

    public interface ResourceCompressionListener {
        void mo6387a();

        void mo6388a(String str);

        void mo6389b();
    }

    public interface ResourceUploadListener {
        void mo6390a();

        void mo6391a(long j);

        void mo6392b();
    }

    public interface PerformanceCreateListener {
        void mo6393a(NetworkResponse networkResponse);

        void mo6394a(PerformanceV2 performanceV2, String str, String str2);

        void mo6395a(ArrayList<PerformanceResourceInfo> arrayList);

        void mo6396b(NetworkResponse networkResponse);
    }

    class C50142 implements Runnable {
        final /* synthetic */ Activity f24822a;
        final /* synthetic */ String f24823b;
        final /* synthetic */ Bundle f24824c;
        final /* synthetic */ ResourceCompressionListener f24825d;
        final /* synthetic */ ResourceUploadListener f24826e;
        final /* synthetic */ int f24827f;
        final /* synthetic */ PerformanceCreateUtil f24828g;

        class C50111 implements Runnable {
            final /* synthetic */ C50142 f24819a;

            C50111(C50142 c50142) {
                this.f24819a = c50142;
            }

            public void run() {
                this.f24819a.f24826e.mo6390a();
            }
        }

        class C50122 implements Runnable {
            final /* synthetic */ C50142 f24820a;

            C50122(C50142 c50142) {
                this.f24820a = c50142;
            }

            public void run() {
                this.f24820a.f24826e.mo6391a(this.f24820a.f24828g.f24882b.longValue());
            }
        }

        class C50133 implements Runnable {
            final /* synthetic */ C50142 f24821a;

            C50133(C50142 c50142) {
                this.f24821a = c50142;
            }

            public void run() {
                this.f24821a.f24826e.mo6392b();
            }
        }

        public void run() {
            File a = this.f24828g.m25924b(this.f24822a, this.f24823b, this.f24824c, this.f24825d);
            if (a == null) {
                Log.c(PerformanceCreateUtil.f24881a, "compression failed");
                return;
            }
            Log.c(PerformanceCreateUtil.f24881a, "Uploading track resource");
            this.f24822a.runOnUiThread(new C50111(this));
            this.f24828g.f24884d = a.length();
            NetworkResponse networkResponse = null;
            for (int i = 0; i <= Math.min(this.f24827f, 0); i++) {
                networkResponse = TracksManager.m18471a().m18476a(a);
                if (networkResponse.c()) {
                    break;
                }
                Log.d(PerformanceCreateUtil.f24881a, "Performance upload failed");
            }
            if (networkResponse == null || !networkResponse.c()) {
                this.f24822a.runOnUiThread(new C50133(this));
                return;
            }
            this.f24828g.f24882b = Long.valueOf(networkResponse.a("resourceId", -1));
            Log.c(PerformanceCreateUtil.f24881a, "Track Resource ID: " + this.f24828g.f24882b);
            this.f24822a.runOnUiThread(new C50122(this));
        }
    }

    public static PerformanceResourceInfo m25914a(ArrayList<PerformanceResourceInfo> arrayList, PerformanceManager$PerformanceResourceInfo$ResourceType performanceManager$PerformanceResourceInfo$ResourceType) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            PerformanceResourceInfo performanceResourceInfo = (PerformanceResourceInfo) it.next();
            if (performanceResourceInfo.mResourceType == performanceManager$PerformanceResourceInfo$ResourceType) {
                return performanceResourceInfo;
            }
        }
        return null;
    }

    private static String m25927b(String str, Bitmap bitmap) throws IOException {
        File file = new File(ResourceUtils.m19032b(MagicNetwork.d().getApplicationContext()), str + PerformanceManager$PerformanceResourceInfo$ResourceType.f16865d.m18254a());
        OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        bitmap.compress(CompressFormat.JPEG, 90, bufferedOutputStream);
        bufferedOutputStream.close();
        return file.getAbsolutePath();
    }

    public PerformanceCreateUtil(Long l, String str) {
        this.f24882b = l;
        this.f24883c = str;
    }

    public long m25929a() {
        return this.f24884d;
    }

    public void m25930a(Activity activity, String str, Bundle bundle, ResourceCompressionListener resourceCompressionListener) {
        final Activity activity2 = activity;
        final String str2 = str;
        final Bundle bundle2 = bundle;
        final ResourceCompressionListener resourceCompressionListener2 = resourceCompressionListener;
        new Thread(new Runnable(this) {
            final /* synthetic */ PerformanceCreateUtil f24818e;

            public void run() {
                this.f24818e.m25924b(activity2, str2, bundle2, resourceCompressionListener2);
            }
        }).start();
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    protected boolean m25932a(String str, String str2) {
        FileInputStream fileInputStream;
        boolean z;
        Object e;
        Throwable th;
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            fileInputStream = new FileInputStream(str2);
            try {
                mediaPlayer.setDataSource(fileInputStream.getFD());
                mediaPlayer.prepare();
                z = true;
                try {
                    long duration = (long) mediaPlayer.getDuration();
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(str);
                    long parseLong = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
                    if (mediaPlayer.getDuration() < 5000 || Math.abs(duration - parseLong) > 5000) {
                        MagicCrittercism.a("WAV file duration: " + parseLong);
                        MagicCrittercism.a("OGG file duration: " + duration);
                        MagicCrittercism.a(new IllegalStateException("Potentially broken recording"));
                    }
                } catch (Exception e2) {
                }
                mediaPlayer.release();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e3) {
                        Log.d(f24881a, "Can't close " + str2, e3);
                    }
                }
            } catch (IOException e4) {
                e = e4;
                try {
                    Log.e(f24881a, "MediaPlayer:" + e);
                    z = false;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e32) {
                            Log.d(f24881a, "Can't close " + str2, e32);
                        }
                    }
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e322) {
                            Log.d(f24881a, "Can't close " + str2, e322);
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e = e5;
            fileInputStream = null;
            Log.e(f24881a, "MediaPlayer:" + e);
            z = false;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return z;
    }

    public void m25931a(Future<PreuploadResponse> future, Activity activity, boolean z, boolean z2, boolean z3, int i, String str, String str2, int i2, String str3, String str4, int i3, String str5, Float f, Float f2, float f3, boolean z4, boolean z5, String str6, Bitmap bitmap, String str7, String str8, boolean z6, String str9, boolean z7, boolean z8, boolean z9, PerformanceCreateListener performanceCreateListener) {
        final Context applicationContext = MagicNetwork.d().getApplicationContext();
        final Activity activity2 = activity;
        final Future<PreuploadResponse> future2 = future;
        final PerformanceCreateListener performanceCreateListener2 = performanceCreateListener;
        final String str10 = str7;
        final String str11 = str9;
        final int i4 = i3;
        final String str12 = str5;
        final float f4 = f3;
        final Float f5 = f;
        final Float f6 = f2;
        final boolean z10 = z7;
        final Bitmap bitmap2 = bitmap;
        final String str13 = str8;
        final boolean z11 = z2;
        final boolean z12 = z3;
        final int i5 = i;
        final boolean z13 = z;
        final String str14 = str3;
        final boolean z14 = z5;
        final String str15 = str2;
        final int i6 = i2;
        final String str16 = str;
        final String str17 = str4;
        final String str18 = str6;
        final boolean z15 = z4;
        final boolean z16 = z6;
        final boolean z17 = z8;
        final boolean z18 = z9;
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ PerformanceCreateUtil f24843D;

            public void run() {
                if (activity2 == null) {
                    MagicCrittercism.a("Activity was terminated before finish uploading, but proceeding normally.");
                }
                try {
                    final PreuploadResponse preuploadResponse = (PreuploadResponse) future2.get();
                    if (preuploadResponse.a()) {
                        boolean b;
                        String str;
                        String str2;
                        String a;
                        ArrayList arrayList;
                        Long l;
                        Long l2;
                        Long l3;
                        Long l4;
                        PerformanceResourceInfo a2;
                        UploadResourceInfo uploadResourceInfo;
                        String uuid;
                        PerformanceResourceInfo a3;
                        UploadResourceInfo uploadResourceInfo2;
                        UploadResourceInfo uploadResourceInfo3;
                        Integer valueOf;
                        PerformanceManager a4;
                        String str3;
                        Integer valueOf2;
                        String str4;
                        String str5;
                        String str6;
                        Long valueOf3;
                        Boolean valueOf4;
                        Float valueOf5;
                        Float valueOf6;
                        Boolean valueOf7;
                        Boolean valueOf8;
                        PerformancesAPI$EnsembleType performancesAPI$EnsembleType;
                        CreateOrJoinResponse a5;
                        final PerformanceV2 performanceV2;
                        final ArrayList arrayList2 = preuploadResponse.mResources;
                        Location a6 = LocationUtils.m19000a();
                        float latitude = a6 != null ? (float) a6.getLatitude() : Float.NaN;
                        float longitude = a6 != null ? (float) a6.getLongitude() : Float.NaN;
                        boolean z = (str10 == null || str10.isEmpty()) ? false : true;
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ C50203 f24834b;

                            public void run() {
                                performanceCreateListener2.mo6395a(arrayList2);
                            }
                        });
                        if (z && DeviceSettings.r()) {
                            String str7 = str11;
                            b = VideoFilterManager.m26577b(str7);
                            str = str7;
                        } else if (!z || DeviceSettings.r()) {
                            b = false;
                            str = null;
                        } else {
                            b = false;
                            str = "unsupported";
                        }
                        PerformanceCreateUtil performanceCreateUtil = this.f24843D;
                        int i = i4;
                        String str8 = str12;
                        float f = f4;
                        Float f2 = f5;
                        Float f3 = f6;
                        if (str != null) {
                            if (!str.equals("unsupported")) {
                                str2 = str;
                                a = performanceCreateUtil.m25921a(z, i, str8, f, f2, f3, str2, z10);
                                arrayList = new ArrayList();
                                l = null;
                                l2 = null;
                                l3 = null;
                                l4 = null;
                                a2 = PerformanceCreateUtil.m25914a(arrayList2, PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO);
                                if (a2 != null) {
                                    l = a2.mResourceId;
                                    uploadResourceInfo = new UploadResourceInfo();
                                    uploadResourceInfo.mPerformanceResourceInfo = a2;
                                    uploadResourceInfo.mResourceFilename = this.f24843D.f24883c;
                                    uploadResourceInfo.mSliceSize = SingServerValues.m21703x() * 1000;
                                    uploadResourceInfo.mTimeoutSec = SingServerValues.m21704y();
                                    arrayList.add(uploadResourceInfo);
                                }
                                a2 = PerformanceCreateUtil.m25914a(arrayList2, PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b);
                                if (a2 != null) {
                                    l4 = a2.mResourceId;
                                    uploadResourceInfo = new UploadResourceInfo();
                                    uploadResourceInfo.mPerformanceResourceInfo = a2;
                                    uploadResourceInfo.mResourceFilename = str10;
                                    uploadResourceInfo.mSliceSize = SingServerValues.m21697r() * 1000;
                                    uploadResourceInfo.mTimeoutSec = SingServerValues.m21698s();
                                    arrayList.add(uploadResourceInfo);
                                }
                                uuid = UUID.randomUUID().toString();
                                a3 = PerformanceCreateUtil.m25914a(arrayList2, PerformanceManager$PerformanceResourceInfo$ResourceType.f16865d);
                                if (!(a3 == null || bitmap2 == null)) {
                                    l2 = a3.mResourceId;
                                    str8 = PerformanceCreateUtil.m25927b(uuid, bitmap2);
                                    uploadResourceInfo2 = new UploadResourceInfo();
                                    uploadResourceInfo2.mPerformanceResourceInfo = a3;
                                    uploadResourceInfo2.mResourceFilename = str8;
                                    arrayList.add(uploadResourceInfo2);
                                }
                                a3 = PerformanceCreateUtil.m25914a(arrayList2, PerformanceManager$PerformanceResourceInfo$ResourceType.META);
                                if (a3 != null) {
                                    l3 = a3.mResourceId;
                                    try {
                                        uuid = this.f24843D.m25928b(uuid, str13);
                                        uploadResourceInfo3 = new UploadResourceInfo();
                                        uploadResourceInfo3.mPerformanceResourceInfo = a3;
                                        uploadResourceInfo3.mResourceFilename = uuid;
                                        arrayList.add(uploadResourceInfo3);
                                    } catch (Exception e) {
                                        this.f24843D.m25922a(performanceCreateListener2, e);
                                        return;
                                    }
                                }
                                valueOf = (z11 || z12) ? Integer.valueOf(i5) : null;
                                if (z13) {
                                    a4 = PerformanceManager.a();
                                    str3 = str15;
                                    valueOf2 = Integer.valueOf(i6);
                                    str4 = str15 != null ? str16 : null;
                                    str5 = str17;
                                    if (str18 != null) {
                                        str6 = "";
                                    } else {
                                        str6 = str18;
                                    }
                                    valueOf3 = Long.valueOf(0);
                                    valueOf4 = Boolean.valueOf(z15);
                                    valueOf5 = Float.valueOf(latitude);
                                    valueOf6 = Float.valueOf(longitude);
                                    valueOf7 = Boolean.valueOf(z);
                                    valueOf8 = Boolean.valueOf(z14);
                                    performancesAPI$EnsembleType = z11 ? PerformancesAPI$EnsembleType.DUET : z12 ? PerformancesAPI$EnsembleType.GROUP : null;
                                    a5 = a4.a(str3, valueOf2, str4, str5, str6, valueOf3, valueOf4, valueOf5, valueOf6, a, l, l2, l3, l4, valueOf, valueOf7, valueOf8, null, null, performancesAPI$EnsembleType);
                                } else {
                                    a5 = PerformanceManager.a().a(str14, Float.valueOf(latitude), Float.valueOf(longitude), a, l, l2, l3, l4, valueOf, Boolean.valueOf(z), Boolean.valueOf(z14), null, null);
                                }
                                performanceV2 = a5.mPerformance;
                                if (a5.a() || performanceV2 == null) {
                                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                        final /* synthetic */ C50203 f24839b;

                                        public void run() {
                                            performanceCreateListener2.mo6396b(a5.a);
                                        }
                                    });
                                    return;
                                }
                                str6 = performanceV2.webUrl;
                                Log.b(PerformanceCreateUtil.f24881a, "uploading performanceKey:" + performanceV2.performanceKey);
                                try {
                                    applicationContext.startService(FileUploaderService.m18903a(applicationContext, arrayList, a5.mTrackKey, performanceV2, str16, str15, z16, z13, z17, str12, b, str, z10, z14, z18));
                                } catch (Exception e2) {
                                }
                                if (!(str14 == null || z12)) {
                                    PerformanceManager.a().b(performanceV2.performanceKey, null);
                                }
                                final String str9 = str6;
                                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                    final /* synthetic */ C50203 f24837c;

                                    public void run() {
                                        MagicDataSource.m17632a(ProfileOpenCallDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                                        MagicDataSource.m17632a(ProfilePerformanceDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                                        performanceCreateListener2.mo6394a(performanceV2, str9, performanceV2.performanceKey);
                                    }
                                });
                                return;
                            }
                        }
                        str2 = "normal";
                        a = performanceCreateUtil.m25921a(z, i, str8, f, f2, f3, str2, z10);
                        arrayList = new ArrayList();
                        l = null;
                        l2 = null;
                        l3 = null;
                        l4 = null;
                        a2 = PerformanceCreateUtil.m25914a(arrayList2, PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO);
                        if (a2 != null) {
                            l = a2.mResourceId;
                            uploadResourceInfo = new UploadResourceInfo();
                            uploadResourceInfo.mPerformanceResourceInfo = a2;
                            uploadResourceInfo.mResourceFilename = this.f24843D.f24883c;
                            uploadResourceInfo.mSliceSize = SingServerValues.m21703x() * 1000;
                            uploadResourceInfo.mTimeoutSec = SingServerValues.m21704y();
                            arrayList.add(uploadResourceInfo);
                        }
                        a2 = PerformanceCreateUtil.m25914a(arrayList2, PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b);
                        if (a2 != null) {
                            l4 = a2.mResourceId;
                            uploadResourceInfo = new UploadResourceInfo();
                            uploadResourceInfo.mPerformanceResourceInfo = a2;
                            uploadResourceInfo.mResourceFilename = str10;
                            uploadResourceInfo.mSliceSize = SingServerValues.m21697r() * 1000;
                            uploadResourceInfo.mTimeoutSec = SingServerValues.m21698s();
                            arrayList.add(uploadResourceInfo);
                        }
                        uuid = UUID.randomUUID().toString();
                        a3 = PerformanceCreateUtil.m25914a(arrayList2, PerformanceManager$PerformanceResourceInfo$ResourceType.f16865d);
                        l2 = a3.mResourceId;
                        try {
                            str8 = PerformanceCreateUtil.m25927b(uuid, bitmap2);
                            uploadResourceInfo2 = new UploadResourceInfo();
                            uploadResourceInfo2.mPerformanceResourceInfo = a3;
                            uploadResourceInfo2.mResourceFilename = str8;
                            arrayList.add(uploadResourceInfo2);
                            a3 = PerformanceCreateUtil.m25914a(arrayList2, PerformanceManager$PerformanceResourceInfo$ResourceType.META);
                            if (a3 != null) {
                                l3 = a3.mResourceId;
                                uuid = this.f24843D.m25928b(uuid, str13);
                                uploadResourceInfo3 = new UploadResourceInfo();
                                uploadResourceInfo3.mPerformanceResourceInfo = a3;
                                uploadResourceInfo3.mResourceFilename = uuid;
                                arrayList.add(uploadResourceInfo3);
                            }
                            if (!z11) {
                            }
                            if (z13) {
                                a4 = PerformanceManager.a();
                                str3 = str15;
                                valueOf2 = Integer.valueOf(i6);
                                if (str15 != null) {
                                }
                                str5 = str17;
                                if (str18 != null) {
                                    str6 = str18;
                                } else {
                                    str6 = "";
                                }
                                valueOf3 = Long.valueOf(0);
                                valueOf4 = Boolean.valueOf(z15);
                                valueOf5 = Float.valueOf(latitude);
                                valueOf6 = Float.valueOf(longitude);
                                valueOf7 = Boolean.valueOf(z);
                                valueOf8 = Boolean.valueOf(z14);
                                if (z11) {
                                }
                                a5 = a4.a(str3, valueOf2, str4, str5, str6, valueOf3, valueOf4, valueOf5, valueOf6, a, l, l2, l3, l4, valueOf, valueOf7, valueOf8, null, null, performancesAPI$EnsembleType);
                            } else {
                                a5 = PerformanceManager.a().a(str14, Float.valueOf(latitude), Float.valueOf(longitude), a, l, l2, l3, l4, valueOf, Boolean.valueOf(z), Boolean.valueOf(z14), null, null);
                            }
                            performanceV2 = a5.mPerformance;
                            if (a5.a()) {
                            }
                            new Handler(Looper.getMainLooper()).post(/* anonymous class already generated */);
                            return;
                        } catch (Exception e3) {
                            this.f24843D.m25922a(performanceCreateListener2, e3);
                            return;
                        }
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ C50203 f24830b;

                        public void run() {
                            performanceCreateListener2.mo6393a(preuploadResponse.a);
                        }
                    });
                } catch (Exception e4) {
                    final NetworkResponse a7 = NetworkResponse.a(10);
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ C50203 f24832b;

                        public void run() {
                            performanceCreateListener2.mo6396b(a7);
                        }
                    });
                }
            }
        });
    }

    private String m25928b(String str, String str2) throws IOException {
        File file = new File(ResourceUtils.m19032b(MagicNetwork.d().getApplicationContext()), str + PerformanceManager$PerformanceResourceInfo$ResourceType.META.m18254a());
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        bufferedWriter.write(str2);
        bufferedWriter.close();
        return file.getAbsolutePath();
    }

    private void m25922a(final PerformanceCreateListener performanceCreateListener, final Exception exception) {
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ PerformanceCreateUtil f24872c;

            public void run() {
                NetworkResponse networkResponse = new NetworkResponse(null);
                networkResponse.a = NetworkResponse$Status.FAILURE;
                networkResponse.b = 10;
                networkResponse.c = exception.getMessage();
                performanceCreateListener.mo6396b(networkResponse);
            }
        });
    }

    private String m25921a(boolean z, int i, String str, float f, Float f2, Float f3, String str2, boolean z2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("score", i);
            jSONObject.put("post_effect", str);
            jSONObject.put("gain", (double) f);
            if (f2 != null) {
                jSONObject.put("p1", f2);
            }
            if (f3 != null) {
                jSONObject.put("p2", f3);
            }
            if (z) {
                jSONObject.put("vfx", str2);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("color", str2);
                jSONObject2.put("airbrush", z2);
                jSONObject.put("video_filter_map", jSONObject2);
            }
            Log.b(f24881a, "trackOptions: " + jSONObject.toString(2));
        } catch (Throwable e) {
            Log.d(f24881a, "could not create track options", e);
        }
        return jSONObject.toString();
    }

    private File m25924b(Activity activity, String str, Bundle bundle, final ResourceCompressionListener resourceCompressionListener) {
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ PerformanceCreateUtil f24874b;

            public void run() {
                resourceCompressionListener.mo6387a();
            }
        });
        String str2 = str.substring(0, str.lastIndexOf(".wav")) + ".m4a";
        Log.c(f24881a, "Compressing recording to " + str2);
        String[] strArr = new String[(bundle.size() * 2)];
        int i = 0;
        for (String str3 : bundle.keySet()) {
            int i2 = i + 1;
            strArr[i] = str3;
            i = i2 + 1;
            strArr[i2] = bundle.get(str3) != null ? bundle.get(str3).toString() : "";
        }
        try {
            Log.a(AudioInterface.f20656a, "Encoded " + SingCoreBridge.oggEncodePCM(str, str2, DeviceSettings.e(), strArr) + " frames to ogg");
            if (m25932a(str, str2)) {
                File file = new File(str2);
                Log.c(f24881a, "Done with compression! File size: " + file.length() + "bytes");
                this.f24883c = str2;
                activity.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ PerformanceCreateUtil f24880b;

                    public void run() {
                        resourceCompressionListener.mo6388a(this.f24880b.f24883c);
                    }
                });
                return file;
            }
            Log.e(f24881a, "Invalid ogg file!");
            MagicCrittercism.a(new Exception("OGG file corrupt."));
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ PerformanceCreateUtil f24878b;

                public void run() {
                    resourceCompressionListener.mo6389b();
                }
            });
            return null;
        } catch (Throwable e) {
            Log.d(f24881a, "Ogg file encoding failed: " + e.getMessage(), e);
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ PerformanceCreateUtil f24876b;

                public void run() {
                    resourceCompressionListener.mo6389b();
                }
            });
            return null;
        }
    }
}
