package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import java.util.Set;

public class PhotoTakingDialog extends SmuleDialog {

    public interface PhotoTakingListener {
        void mo6532a(int i);
    }

    public static void m23674a(final BaseActivity baseActivity, RunTimePermissionsRequest runTimePermissionsRequest, final boolean z, final PhotoTakingListener photoTakingListener) {
        baseActivity.a(runTimePermissionsRequest, new ResultCallback() {
            public void mo6372a(boolean z, @NonNull Set<String> set) {
                if (z) {
                    new PhotoTakingDialog(baseActivity, z, photoTakingListener).show();
                } else {
                    photoTakingListener.mo6532a(0);
                }
            }
        });
    }

    private PhotoTakingDialog(Activity activity, boolean z, final PhotoTakingListener photoTakingListener) {
        super(activity, C1947R.style.Theme.Transparent);
        View inflate = activity.getLayoutInflater().inflate(C1947R.layout.photo_options_dialog, null, false);
        setContentView(inflate);
        setCanceledOnTouchOutside(true);
        View findViewById = inflate.findViewById(C1947R.id.photoTakePhoto);
        View findViewById2 = inflate.findViewById(C1947R.id.photoExistingPhoto);
        View findViewById3 = inflate.findViewById(C1947R.id.photoFacebook);
        View findViewById4 = inflate.findViewById(C1947R.id.photoCancel);
        findViewById3.setVisibility(z ? 0 : 8);
        findViewById.setTag(Integer.valueOf(1));
        findViewById2.setTag(Integer.valueOf(2));
        findViewById3.setTag(Integer.valueOf(3));
        findViewById4.setTag(Integer.valueOf(0));
        OnClickListener c45022 = new OnClickListener(this) {
            final /* synthetic */ PhotoTakingDialog f22277b;

            public void onClick(View view) {
                this.f22277b.dismiss();
                if (view.getTag() != null) {
                    photoTakingListener.mo6532a(((Integer) view.getTag()).intValue());
                }
            }
        };
        findViewById.setOnClickListener(c45022);
        findViewById2.setOnClickListener(c45022);
        findViewById3.setOnClickListener(c45022);
        findViewById4.setOnClickListener(c45022);
        setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ PhotoTakingDialog f22279b;

            public void onCancel(DialogInterface dialogInterface) {
                photoTakingListener.mo6532a(0);
            }
        });
    }
}
