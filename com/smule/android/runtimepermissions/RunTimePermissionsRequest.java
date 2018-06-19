package com.smule.android.runtimepermissions;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

public class RunTimePermissionsRequest {
    final String f17591a;
    final String[] f17592b;
    ExplanationDialogCreator f17593c = null;
    ExplanationDialogCreator f17594d = null;
    ExplanationDialogCreator f17595e = null;
    boolean f17596f;

    public interface ExplanationDialogListener {
        void mo6285a(Dialog dialog);

        void mo6286b(Dialog dialog);
    }

    public interface ExplanationDialogCreator {
        @NonNull
        Dialog mo6921a(Context context, ExplanationDialogListener explanationDialogListener);
    }

    static void m18687a(Context context, ExplanationDialogCreator explanationDialogCreator, final ExplanationDialogListener explanationDialogListener) {
        explanationDialogCreator.mo6921a(context, new ExplanationDialogListener() {
            public void mo6285a(Dialog dialog) {
                dialog.dismiss();
                explanationDialogListener.mo6285a(dialog);
            }

            public void mo6286b(Dialog dialog) {
                dialog.dismiss();
                explanationDialogListener.mo6286b(dialog);
            }
        }).show();
    }

    public RunTimePermissionsRequest(String str, String... strArr) {
        this.f17591a = str;
        this.f17592b = strArr;
    }

    public RunTimePermissionsRequest m18688a(ExplanationDialogCreator explanationDialogCreator) {
        this.f17593c = explanationDialogCreator;
        return this;
    }

    public RunTimePermissionsRequest m18690b(ExplanationDialogCreator explanationDialogCreator) {
        this.f17594d = explanationDialogCreator;
        return this;
    }

    public RunTimePermissionsRequest m18689a(boolean z, ExplanationDialogCreator explanationDialogCreator) {
        this.f17595e = explanationDialogCreator;
        this.f17596f = z;
        return this;
    }
}
