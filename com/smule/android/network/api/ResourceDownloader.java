package com.smule.android.network.api;

import android.content.Context;
import android.support.annotation.NonNull;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.CacheType;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.MagicNetwork$StreamResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.NetworkUtils.ProgressListener;
import com.smule.android.network.models.SongV2;
import com.smule.android.utils.ResourceUtils;
import java.io.File;
import java.io.FileOutputStream;

public class ResourceDownloader {
    static final String TAG = ResourceDownloader.class.getName();

    public static class DownloadResult {
        public final File mFile;
        public final Status mStatus;

        public enum Status {
            INVALID_URL,
            CACHED,
            DOWNLOAD_SUCCESS,
            DOWNLOAD_FAIL,
            DOWNLOAD_FAIL_DURING_READ
        }

        private DownloadResult(Status status, File file) {
            this.mStatus = status;
            this.mFile = file;
        }

        public boolean isSuccess() {
            return this.mStatus == Status.DOWNLOAD_SUCCESS || this.mStatus == Status.CACHED;
        }

        public boolean isCached() {
            return this.mStatus == Status.CACHED;
        }
    }

    @NonNull
    public static DownloadResult downloadProductResourcesFromURL(String str, String str2, Context context, SongV2 songV2, ProgressListener progressListener) {
        File file = new File(ResourceUtils.m19032b(context) + "/" + str2);
        if (songV2.a()) {
            return downloadFile(str, file, progressListener);
        }
        if (!file.exists()) {
            return downloadFile(str, file, progressListener);
        }
        logNptC(file, true);
        return new DownloadResult(Status.CACHED, file);
    }

    @NonNull
    public static DownloadResult downloadFileFromURL(String str, String str2, Context context) {
        return downloadFileFromURL(str, str2, context, null);
    }

    @NonNull
    public static DownloadResult downloadFileFromURL(String str, String str2, Context context, ProgressListener progressListener) {
        File file = new File(ResourceUtils.m19032b(context) + "/" + str2);
        if (!file.exists()) {
            return downloadFile(str, file, progressListener);
        }
        logNptC(file, true);
        return new DownloadResult(Status.CACHED, file);
    }

    @NonNull
    private static DownloadResult downloadFile(String str, File file) {
        return downloadFile(str, file, null);
    }

    @NonNull
    private static DownloadResult downloadFile(String str, File file, ProgressListener progressListener) {
        if (str.equals("")) {
            Log.e(TAG, "Empty URL passed to downloadFile(String url, File file); aborting download!");
            return new DownloadResult(Status.INVALID_URL, null);
        }
        try {
            MagicNetwork$StreamResponse a = MagicNetwork.a().a(str, progressListener);
            if (a != null) {
                try {
                    if (!NetworkUtils.m18114a(a, new FileOutputStream(file), progressListener)) {
                        return new DownloadResult(Status.DOWNLOAD_FAIL_DURING_READ, file);
                    }
                    logNptC(file, false);
                    return new DownloadResult(Status.DOWNLOAD_SUCCESS, file);
                } catch (Throwable e) {
                    Log.d(TAG, "Couldn't find file for downloaded resource", e);
                }
            }
        } catch (Throwable e2) {
            Log.c(TAG, "Server exception: ", e2);
        } catch (Throwable e22) {
            Log.c(TAG, "Server IO exception: ", e22);
        } catch (RuntimeException e3) {
            if (e3.getMessage() == null || !e3.getMessage().contains("canceled")) {
                throw e3;
            }
            Log.c(TAG, "File download was canceled from " + str);
        }
        if (file.exists() && !file.delete()) {
            Log.d(TAG, "Could not delete file: " + file.getAbsolutePath());
        }
        return new DownloadResult(Status.DOWNLOAD_FAIL, null);
    }

    private static void logNptC(@NonNull File file, boolean z) {
        Analytics.m17879a(file.getAbsolutePath(), z, CacheType.RESOURCE);
    }
}
