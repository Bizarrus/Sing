package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.smule.singandroid.C1947R;

public class DeleteRecordingConfirmationDialog extends TextAlertDialog {
    private static final String f22267c = DeleteRecordingConfirmationDialog.class.getName();

    class C44971 implements OnClickListener {
        final /* synthetic */ DeleteRecordingConfirmationDialog f22266a;

        C44971(DeleteRecordingConfirmationDialog deleteRecordingConfirmationDialog) {
            this.f22266a = deleteRecordingConfirmationDialog;
        }

        public void onClick(View view) {
            this.f22266a.mo6374a();
        }
    }

    public DeleteRecordingConfirmationDialog(Activity activity) {
        super((Context) activity, activity.getString(C1947R.string.core_are_you_sure), activity.getString(C1947R.string.post_performance_delete));
        m19806a(activity.getString(C1947R.string.core_cancel), activity.getString(C1947R.string.delete_performance));
    }

    public void setCanceledOnTouchOutside(boolean z) {
        this.a.setOnClickListener(new C44971(this));
    }

    public void onBackPressed() {
        mo6374a();
    }
}
