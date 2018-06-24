/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.AsyncTask
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.task.SongDownloadTask
 *  com.smule.singandroid.task.SongDownloadTask$DownloadListener
 *  com.smule.singandroid.task.SongDownloadTask$DownloadProgressListener
 */
package com.smule.singandroid.dialogs;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.customviews.SNPProgressImageView;
import com.smule.singandroid.task.SongDownloadTask;

public class SongDownloadDialog
extends SmuleDialog {
    private static final String a = SongDownloadDialog.class.getName();
    private Context b;
    private SNPProgressImageView c;
    private TextView d;
    private Button e;
    private SongDownloadTask f;
    private boolean g = false;
    private SongDownloadDialogListener h;

    public SongDownloadDialog(Context context, String string2, String string3, SongDownloadDialogListener songDownloadDialogListener) {
        super(context, 2131493244);
        if (songDownloadDialogListener == null) {
            throw new IllegalArgumentException("SongDownloadDialogListener must not be null");
        }
        this.b = context;
        this.setContentView(LayoutInflater.from((Context)context).inflate(2130903419, null, false));
        this.a();
        this.h = songDownloadDialogListener;
        this.d.setText((CharSequence)string2);
        this.e.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SongDownloadDialog.this.b();
            }
        });
        this.setCancelable(true);
        ImageUtils.a(string3, (ImageView)this.c, 2130837896);
        this.c.setMax(100);
    }

    private void a() {
        this.d = (TextView)this.findViewById(2131755909);
        this.c = (SNPProgressImageView)this.findViewById(2131756268);
        this.e = (Button)this.findViewById(2131755376);
    }

    private void b() {
        this.h.b();
        if (this.f != null) {
            this.g = true;
            this.f.cancel(false);
            this.f = null;
        }
        this.dismiss();
    }

    public void a(int n) {
        this.c.setProgress(n);
    }

    public void a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        this.f = new SongDownloadTask(this.b, songbookEntry, performanceV2, new SongDownloadTask.DownloadListener(){

            public void a(boolean bl, SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
                SongDownloadDialog.this.f = null;
                if (bl) {
                    SongDownloadDialog.this.a(100);
                    SongDownloadDialog.this.h.a(songbookEntry);
                    return;
                }
                if (SongDownloadDialog.this.g) {
                    SongDownloadDialog.this.h.b();
                    return;
                }
                SongDownloadDialog.this.h.a();
            }
        }, new SongDownloadTask.DownloadProgressListener(){

            public void a(int n) {
                if (n >= 0) {
                    SongDownloadDialog.this.a(n);
                }
            }
        });
        this.a(0);
        this.show();
        this.f.execute((Object[])new Void[0]);
    }

    public void dismiss() {
        try {
            if (this.f != null) {
                this.g = true;
                this.f.cancel(false);
                this.f = null;
            }
            super.dismiss();
            return;
        }
        catch (Exception exception) {
            Log.e(a, "Exception thrown when dismissing BusyDialog");
            return;
        }
    }

    public void onBackPressed() {
        this.b();
    }

    public static interface SongDownloadDialogListener {
        public void a();

        public void a(SongbookEntry var1);

        public void b();
    }

}

