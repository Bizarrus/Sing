package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.uploader.FileUploaderService.VideoUploadStatus;
import com.smule.singandroid.C1947R;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class VideoUploadStatusView extends RelativeLayout {
    private static final String f22051f = VideoUploadStatusView.class.getName();
    @ViewById
    protected View f22052a;
    @ViewById
    protected TextView f22053b;
    @ViewById
    protected ImageView f22054c;
    @ViewById
    protected ProgressBar f22055d;
    protected boolean f22056e;
    private Runnable f22057g;
    private VideoUploadStatus f22058h = VideoUploadStatus.UNKNOWN;

    public VideoUploadStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setRecordingType(boolean z) {
        this.f22056e = z;
    }

    public void setVideoStatus(VideoUploadStatus videoUploadStatus) {
        int i = 0;
        this.f22058h = videoUploadStatus;
        switch (videoUploadStatus) {
            case UNKNOWN:
                this.f22052a.setVisibility(8);
                return;
            case UPLOADING:
                this.f22052a.setVisibility(0);
                this.f22054c.setVisibility(0);
                ProgressBar progressBar = this.f22055d;
                if (!this.f22056e) {
                    i = 8;
                }
                progressBar.setVisibility(i);
                this.f22054c.setImageResource(C1947R.drawable.btn_video_upload_cancel);
                this.f22053b.setText(getResources().getString(C1947R.string.uploading_recording));
                return;
            case RENDERING:
                this.f22052a.setVisibility(0);
                this.f22054c.setVisibility(8);
                this.f22055d.setVisibility(8);
                this.f22053b.setText(getResources().getString(C1947R.string.rendering_recording));
                return;
            case DONE:
                if (this.f22056e) {
                    this.f22052a.setVisibility(0);
                    this.f22054c.setVisibility(0);
                    this.f22055d.setVisibility(8);
                    this.f22054c.setImageResource(C1947R.drawable.icn_video_processed);
                    this.f22053b.setText(getResources().getString(C1947R.string.video_ready));
                    return;
                }
                this.f22052a.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public VideoUploadStatus getUploadStatus() {
        return this.f22058h;
    }

    public void setCancelVideoUploadHandler(Runnable runnable) {
        this.f22057g = runnable;
    }

    @Click
    public void m23532a() {
        Log.b(f22051f, "Cancel upload " + this.f22057g + " " + this.f22058h);
        if (this.f22057g != null && this.f22058h == VideoUploadStatus.UPLOADING) {
            this.f22057g.run();
        }
    }

    public void m23533a(long j) {
        this.f22055d.setProgress((int) j);
    }
}
