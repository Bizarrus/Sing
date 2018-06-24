package com.smule.singandroid.runtimepermissions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;

public abstract class SingPermissionRequests {
    public static final RunTimePermissionsRequest f23947a = new RunTimePermissionsRequest("location for curating singing experience", "android.permission.ACCESS_COARSE_LOCATION").m18688a(new SingPermissionExplanationDialogCreator(C1947R.string.permission_location_soft_request_title, C1947R.string.permission_location_soft_request_body, C1947R.string.core_no_thanks, C1947R.string.core_ok, C1947R.drawable.icn_location_permission));
    public static final RunTimePermissionsRequest f23948b = new RunTimePermissionsRequest("camera for profile", "android.permission.CAMERA").m18688a(new SingPermissionExplanationDialogCreator(C1947R.string.permission_camera_soft_request_title, C1947R.string.permission_camera_profile_ftux_soft_request_body, C1947R.string.core_no_thanks, C1947R.string.core_ok, C1947R.drawable.icn_camera_permission)).m18689a(false, new SingPermissionExplanationDialogCreator(C1947R.string.permission_camera_error_dialog_title, C1947R.string.permission_camera_error_dialog_body, C1947R.string.core_no_thanks, C1947R.string.permission_open_settings));
    public static final RunTimePermissionsRequest f23949c = new RunTimePermissionsRequest("camera for song", "android.permission.CAMERA").m18688a(new SingPermissionExplanationDialogCreator(C1947R.string.permission_camera_soft_request_title, C1947R.string.permission_camera_sing_soft_request_body, C1947R.string.core_no_thanks, C1947R.string.core_ok, C1947R.drawable.icn_camera_permission)).m18689a(false, new SingPermissionExplanationDialogCreator(C1947R.string.permission_camera_error_dialog_title, C1947R.string.permission_camera_error_dialog_body, C1947R.string.core_no_thanks, C1947R.string.permission_open_settings));
    public static final RunTimePermissionsRequest f23950d = new RunTimePermissionsRequest("mic", "android.permission.RECORD_AUDIO").m18688a(new SingPermissionExplanationDialogCreator(C1947R.string.permission_mic_soft_request_title, C1947R.string.permission_mic_soft_request_body, C1947R.string.core_no_thanks, C1947R.string.core_ok, C1947R.drawable.icn_mic_permission)).m18690b(new SingPermissionExplanationDialogCreator(C1947R.string.permission_are_you_sure, C1947R.string.permission_mic_appeal_dialog_body, C1947R.string.core_no_thanks, C1947R.string.core_ok)).m18689a(true, new SingPermissionExplanationDialogCreator(C1947R.string.permission_mic_error_dialog_title, C1947R.string.permission_mic_error_dialog_body, C1947R.string.core_no_thanks, C1947R.string.permission_open_settings));
    private static boolean f23951e = false;

    public static void m25195a(@NonNull BaseActivity baseActivity, @Nullable ResultCallback resultCallback) {
        if (!f23951e) {
            baseActivity.a(f23947a, resultCallback);
            f23951e = true;
        }
    }

    public static void m25196a(@NonNull BaseFragment baseFragment, @Nullable ResultCallback resultCallback) {
        if (!f23951e) {
            baseFragment.mo6406a(f23947a, resultCallback);
            f23951e = true;
        }
    }

    private SingPermissionRequests() {
    }
}
