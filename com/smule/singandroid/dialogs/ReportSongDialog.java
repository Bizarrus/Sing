package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.smule.singandroid.C1947R;

public class ReportSongDialog extends SmuleDialog {
    View f22298a = findViewById(C1947R.id.mReportSongInappropriateContentButton);
    View f22299b = findViewById(C1947R.id.mReportSongCopyrightInfringementButton);
    View f22300c = findViewById(C1947R.id.mReportSongMislabeledContentButton);

    public ReportSongDialog(final Activity activity) {
        super(activity, C1947R.style.MagicModal);
        setContentView(C1947R.layout.report_song_dialog);
        this.f22298a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReportSongDialog f22293b;

            public void onClick(View view) {
                this.f22293b.dismiss();
                TextAlertDialog textAlertDialog = new TextAlertDialog(activity, this.f22293b.getContext().getString(C1947R.string.sing_report_inappropriate_content_short), this.f22293b.getContext().getString(C1947R.string.songbook_report_song_inapropriate_content), true, false);
                textAlertDialog.m19806a(this.f22293b.getContext().getString(C1947R.string.core_ok), null);
                textAlertDialog.show();
            }
        });
        this.f22299b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReportSongDialog f22295b;

            public void onClick(View view) {
                this.f22295b.dismiss();
                TextAlertDialog textAlertDialog = new TextAlertDialog(activity, this.f22295b.getContext().getString(C1947R.string.sing_report_copyright_infringement), this.f22295b.getContext().getString(C1947R.string.songbook_report_song_copyright), true, false);
                textAlertDialog.m19806a(this.f22295b.getContext().getString(C1947R.string.core_ok), null);
                textAlertDialog.show();
            }
        });
        this.f22300c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReportSongDialog f22297b;

            public void onClick(View view) {
                this.f22297b.dismiss();
                TextAlertDialog textAlertDialog = new TextAlertDialog(activity, this.f22297b.getContext().getString(C1947R.string.sing_report_mislabeled_content_title), this.f22297b.getContext().getString(C1947R.string.sing_report_mislabeled_content_msg), true, false);
                textAlertDialog.m19806a(this.f22297b.getContext().getString(C1947R.string.core_ok), null);
                textAlertDialog.show();
            }
        });
    }
}
