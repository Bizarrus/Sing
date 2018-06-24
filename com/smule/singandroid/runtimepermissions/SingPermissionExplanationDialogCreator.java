package com.smule.singandroid.runtimepermissions;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.ImageView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest.ExplanationDialogCreator;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest.ExplanationDialogListener;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;

public class SingPermissionExplanationDialogCreator implements ExplanationDialogCreator {
    private final int f23942a;
    private final int f23943b;
    private final int f23944c;
    private final int f23945d;
    private final int f23946e;

    public SingPermissionExplanationDialogCreator(@StringRes int i, @StringRes int i2, @StringRes int i3, @StringRes int i4, @DrawableRes int i5) {
        this.f23943b = i;
        this.f23944c = i2;
        this.f23945d = i3;
        this.f23946e = i4;
        this.f23942a = i5;
    }

    public SingPermissionExplanationDialogCreator(@StringRes int i, @StringRes int i2, @StringRes int i3, @StringRes int i4) {
        this(i, i2, i3, i4, 0);
    }

    @NonNull
    public Dialog mo6921a(Context context, final ExplanationDialogListener explanationDialogListener) {
        Dialog textAlertDialog = new TextAlertDialog(context, this.f23943b, this.f23944c, true, true);
        textAlertDialog.setCanceledOnTouchOutside(false);
        textAlertDialog.m19807a(false);
        if (this.f23942a != 0) {
            textAlertDialog.m19799a((int) C1947R.layout.soft_permission_request_header);
            ((ImageView) textAlertDialog.findViewById(C1947R.id.header_image)).setImageResource(this.f23942a);
        }
        textAlertDialog.m19800a(this.f23946e, this.f23945d);
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ SingPermissionExplanationDialogCreator f23941b;

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                explanationDialogListener.mo6286b(customAlertDialog);
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                explanationDialogListener.mo6285a(customAlertDialog);
            }
        });
        return textAlertDialog;
    }
}
