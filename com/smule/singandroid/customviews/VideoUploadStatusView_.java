package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class VideoUploadStatusView_ extends VideoUploadStatusView implements HasViews, OnViewChangedListener {
    private boolean f22060f = false;
    private final OnViewChangedNotifier f22061g = new OnViewChangedNotifier();

    class C44501 implements OnClickListener {
        final /* synthetic */ VideoUploadStatusView_ f22059a;

        C44501(VideoUploadStatusView_ videoUploadStatusView_) {
            this.f22059a = videoUploadStatusView_;
        }

        public void onClick(View view) {
            this.f22059a.m23532a();
        }
    }

    public VideoUploadStatusView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23534b();
    }

    public void onFinishInflate() {
        if (!this.f22060f) {
            this.f22060f = true;
            inflate(getContext(), C1947R.layout.video_upload_status_container, this);
            this.f22061g.a(this);
        }
        super.onFinishInflate();
    }

    private void m23534b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22061g);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23535a(HasViews hasViews) {
        this.a = hasViews.findViewById(C1947R.id.video_status_container);
        this.b = (TextView) hasViews.findViewById(C1947R.id.video_status);
        this.c = (ImageView) hasViews.findViewById(C1947R.id.video_upload_cancel);
        this.d = (ProgressBar) hasViews.findViewById(C1947R.id.video_upload_progress);
        if (this.c != null) {
            this.c.setOnClickListener(new C44501(this));
        }
    }
}
