package com.smule.singandroid.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.SNPProgressImageView;
import com.smule.singandroid.task.SongDownloadTask;
import com.smule.singandroid.task.SongDownloadTask.DownloadListener;
import com.smule.singandroid.task.SongDownloadTask.DownloadProgressListener;

public class SongDownloadDialog extends SmuleDialog {
    private static final String f22321a = SongDownloadDialog.class.getName();
    private Context f22322b;
    private SNPProgressImageView f22323c;
    private TextView f22324d;
    private Button f22325e;
    private SongDownloadTask f22326f;
    private boolean f22327g = false;
    private SongDownloadDialogListener f22328h;

    public interface SongDownloadDialogListener {
        void mo6583a();

        void mo6584a(SongbookEntry songbookEntry);

        void mo6585b();
    }

    class C45181 implements OnClickListener {
        final /* synthetic */ SongDownloadDialog f22318a;

        C45181(SongDownloadDialog songDownloadDialog) {
            this.f22318a = songDownloadDialog;
        }

        public void onClick(View view) {
            this.f22318a.m23719b();
        }
    }

    class C45192 implements DownloadListener {
        final /* synthetic */ SongDownloadDialog f22319a;

        C45192(SongDownloadDialog songDownloadDialog) {
            this.f22319a = songDownloadDialog;
        }

        public void mo6541a(boolean z, SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
            this.f22319a.f22326f = null;
            if (z) {
                this.f22319a.m23721a(100);
                this.f22319a.f22328h.mo6584a(songbookEntry);
            } else if (this.f22319a.f22327g) {
                this.f22319a.f22328h.mo6585b();
            } else {
                this.f22319a.f22328h.mo6583a();
            }
        }
    }

    class C45203 implements DownloadProgressListener {
        final /* synthetic */ SongDownloadDialog f22320a;

        C45203(SongDownloadDialog songDownloadDialog) {
            this.f22320a = songDownloadDialog;
        }

        public void mo6818a(int i) {
            if (i >= 0) {
                this.f22320a.m23721a(i);
            }
        }
    }

    private void m23716a() {
        this.f22324d = (TextView) findViewById(C1947R.id.message);
        this.f22323c = (SNPProgressImageView) findViewById(C1947R.id.album_art_image_view);
        this.f22325e = (Button) findViewById(C1947R.id.cancel_button);
    }

    public SongDownloadDialog(Context context, String str, String str2, SongDownloadDialogListener songDownloadDialogListener) {
        super(context, C1947R.style.MagicModal);
        if (songDownloadDialogListener == null) {
            throw new IllegalArgumentException("SongDownloadDialogListener must not be null");
        }
        this.f22322b = context;
        setContentView(LayoutInflater.from(context).inflate(C1947R.layout.song_download_dialog, null, false));
        m23716a();
        this.f22328h = songDownloadDialogListener;
        this.f22324d.setText(str);
        this.f22325e.setOnClickListener(new C45181(this));
        setCancelable(true);
        ImageUtils.a(str2, this.f22323c, C1947R.drawable.icn_default_album_medium);
        this.f22323c.setMax(100);
    }

    public void onBackPressed() {
        m23719b();
    }

    public void dismiss() {
        try {
            if (this.f22326f != null) {
                this.f22327g = true;
                this.f22326f.cancel(false);
                this.f22326f = null;
            }
            super.dismiss();
        } catch (Exception e) {
            Log.e(f22321a, "Exception thrown when dismissing BusyDialog");
        }
    }

    private void m23719b() {
        this.f22328h.mo6585b();
        if (this.f22326f != null) {
            this.f22327g = true;
            this.f22326f.cancel(false);
            this.f22326f = null;
        }
        dismiss();
    }

    public void m23721a(int i) {
        this.f22323c.setProgress(i);
    }

    public void m23722a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        this.f22326f = new SongDownloadTask(this.f22322b, songbookEntry, performanceV2, new C45192(this), new C45203(this));
        m23721a(0);
        show();
        this.f22326f.execute(new Void[0]);
    }
}
