/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.uploader.FileUploaderService;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class VideoUploadStatusView
extends RelativeLayout {
    private static final String f = VideoUploadStatusView.class.getName();
    @ViewById
    protected View a;
    @ViewById
    protected TextView b;
    @ViewById
    protected ImageView c;
    @ViewById
    protected ProgressBar d;
    protected boolean e;
    private Runnable g;
    private FileUploaderService.VideoUploadStatus h = FileUploaderService.VideoUploadStatus.e;

    public VideoUploadStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Click
    public void a() {
        Log.b(f, "Cancel upload " + this.g + " " + (Object)((Object)this.h));
        if (this.g != null && this.h == FileUploaderService.VideoUploadStatus.a) {
            this.g.run();
        }
    }

    public void a(long l) {
        this.d.setProgress((int)l);
    }

    public FileUploaderService.VideoUploadStatus getUploadStatus() {
        return this.h;
    }

    public void setCancelVideoUploadHandler(Runnable runnable) {
        this.g = runnable;
    }

    public void setRecordingType(boolean bl) {
        this.e = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setVideoStatus(FileUploaderService.VideoUploadStatus videoUploadStatus) {
        int n = 0;
        this.h = videoUploadStatus;
        switch (.a[videoUploadStatus.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.a.setVisibility(8);
                return;
            }
            case 2: {
                this.a.setVisibility(0);
                this.c.setVisibility(0);
                videoUploadStatus = this.d;
                if (!this.e) {
                    n = 8;
                }
                videoUploadStatus.setVisibility(n);
                this.c.setImageResource(2130837663);
                this.b.setText((CharSequence)this.getResources().getString(2131297579));
                return;
            }
            case 3: {
                this.a.setVisibility(0);
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                this.b.setText((CharSequence)this.getResources().getString(2131297277));
                return;
            }
            case 4: 
        }
        if (this.e) {
            this.a.setVisibility(0);
            this.c.setVisibility(0);
            this.d.setVisibility(8);
            this.c.setImageResource(2130838059);
            this.b.setText((CharSequence)this.getResources().getString(2131297588));
            return;
        }
        this.a.setVisibility(8);
    }

}

