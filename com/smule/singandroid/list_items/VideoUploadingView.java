package com.smule.singandroid.list_items;

import android.content.Context;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.FileUploaderService.VideoUploadStatus;
import com.smule.singandroid.customviews.VideoUploadStatusView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class VideoUploadingView extends MediaPlayingListItem {
    @ViewById
    protected VideoUploadStatusView f22999C;
    protected PerformanceV2 f23000D;

    public VideoUploadingView(Context context) {
        super(context);
    }

    public void setRecordingType(boolean z) {
        this.f22999C.setRecordingType(z);
    }

    public void setVideoStatus(VideoUploadStatus videoUploadStatus) {
        this.f22999C.setVideoStatus(videoUploadStatus);
    }

    public VideoUploadStatus getUploadStatus() {
        return this.f22999C.getUploadStatus();
    }

    public void setCancelVideoUploadHandler(Runnable runnable) {
        this.f22999C.setCancelVideoUploadHandler(runnable);
    }

    public void m24331a(long j) {
        this.f22999C.m23533a(j);
    }

    public PerformanceV2 getPerformance() {
        return this.f23000D;
    }
}
