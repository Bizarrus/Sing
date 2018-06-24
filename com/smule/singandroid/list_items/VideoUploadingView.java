/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.FileUploaderService;
import com.smule.singandroid.customviews.VideoUploadStatusView;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class VideoUploadingView
extends MediaPlayingListItem {
    @ViewById
    protected VideoUploadStatusView C;
    protected PerformanceV2 D;

    public VideoUploadingView(Context context) {
        super(context);
    }

    public void a(long l) {
        this.C.a(l);
    }

    public PerformanceV2 getPerformance() {
        return this.D;
    }

    public FileUploaderService.VideoUploadStatus getUploadStatus() {
        return this.C.getUploadStatus();
    }

    public void setCancelVideoUploadHandler(Runnable runnable) {
        this.C.setCancelVideoUploadHandler(runnable);
    }

    public void setRecordingType(boolean bl) {
        this.C.setRecordingType(bl);
    }

    public void setVideoStatus(FileUploaderService.VideoUploadStatus videoUploadStatus) {
        this.C.setVideoStatus(videoUploadStatus);
    }
}

