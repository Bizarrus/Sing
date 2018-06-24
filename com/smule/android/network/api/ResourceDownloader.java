/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.NonNull
 */
package com.smule.android.network.api;

import android.content.Context;
import android.support.annotation.NonNull;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.api.ResourceDownloader;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ServerException;
import com.smule.android.network.models.SongV2;
import com.smule.android.utils.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResourceDownloader {
    static final String TAG = ResourceDownloader.class.getName();

    @NonNull
    private static  downloadFile(String string2, File file) {
        return ResourceDownloader.downloadFile(string2, file, null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @NonNull
    private static  downloadFile(String string2, File file, NetworkUtils.ProgressListener object) {
        block8 : {
            if (string2.equals("")) {
                Log.e(TAG, "Empty URL passed to downloadFile(String url, File file); aborting download!");
                return new Object(Status.INVALID_URL, null){
                    public final File mFile;
                    public final Status mStatus;
                    {
                        this.mStatus = status;
                        this.mFile = file;
                    }

                    public boolean isCached() {
                        if (this.mStatus == Status.CACHED) {
                            return true;
                        }
                        return false;
                    }

                    public boolean isSuccess() {
                        if (this.mStatus == Status.DOWNLOAD_SUCCESS || this.mStatus == Status.CACHED) {
                            return true;
                        }
                        return false;
                    }

                    public static enum Status {
                        INVALID_URL,
                        CACHED,
                        DOWNLOAD_SUCCESS,
                        DOWNLOAD_FAIL,
                        DOWNLOAD_FAIL_DURING_READ;
                        

                        private Status() {
                        }
                    }

                };
            }
            try {
                block9 : {
                    MagicNetwork streamResponse = com.smule.android.network.core.MagicNetwork.a().a(string2, (NetworkUtils.ProgressListener)object);
                    if (streamResponse == null) break block8;
                    try {
                        if (NetworkUtils.a(streamResponse, new FileOutputStream(file), (NetworkUtils.ProgressListener)object)) break block9;
                        return new ;
                    }
                    catch (FileNotFoundException fileNotFoundException) {
                        Log.d(TAG, "Couldn't find file for downloaded resource", fileNotFoundException);
                    }
                }
                ResourceDownloader.logNptC(file, false);
                return new ;
            }
            catch (ServerException serverException) {
                Log.c(TAG, "Server exception: ", serverException);
            }
            catch (IOException iOException) {
                Log.c(TAG, "Server IO exception: ", iOException);
            }
            catch (RuntimeException runtimeException) {
                if (runtimeException.getMessage() == null) throw runtimeException;
                if (!runtimeException.getMessage().contains("canceled")) throw runtimeException;
                Log.c(TAG, "File download was canceled from " + string2);
            }
        }
        if (!file.exists()) return new ;
        if (file.delete()) return new ;
        Log.d(TAG, "Could not delete file: " + file.getAbsolutePath());
        return new ;
    }

    @NonNull
    public static  downloadFileFromURL(String string2, String string3, Context context) {
        return ResourceDownloader.downloadFileFromURL(string2, string3, context, null);
    }

    @NonNull
    public static  downloadFileFromURL(String string2, String object, Context object2, NetworkUtils.ProgressListener progressListener) {
        object = new File((String)(object2 = ResourceUtils.b((Context)object2)) + "/" + (String)object);
        if (object.exists()) {
            ResourceDownloader.logNptC((File)object, true);
            return new ;
        }
        return ResourceDownloader.downloadFile(string2, (File)object, progressListener);
    }

    @Deprecated
    @NonNull
    public static  downloadProductResourcesFromURL(String string2, String object, Context object2, SongV2 songV2, NetworkUtils.ProgressListener progressListener) {
        object2 = ResourceUtils.b((Context)object2);
        object = new File((String)object2 + "/" + (String)object);
        if (songV2.a()) {
            return ResourceDownloader.downloadFile(string2, (File)object, progressListener);
        }
        if (object.exists()) {
            ResourceDownloader.logNptC((File)object, true);
            return new ;
        }
        return ResourceDownloader.downloadFile(string2, (File)object, progressListener);
    }

    private static void logNptC(@NonNull File file, boolean bl) {
        com.smule.android.logging.Analytics.a(file.getAbsolutePath(), bl, Analytics.a);
    }

}

