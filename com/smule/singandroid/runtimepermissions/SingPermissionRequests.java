/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid.runtimepermissions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.runtimepermissions.SingPermissionExplanationDialogCreator;
import com.smule.singandroid.runtimepermissions.SingPermissionExplanationFullscreenDialogCreator;

public abstract class SingPermissionRequests {
    public static final RunTimePermissionsRequest a = new RunTimePermissionsRequest("location for curating singing experience", "android.permission.ACCESS_COARSE_LOCATION").a(new SingPermissionExplanationDialogCreator(2131297127, 2131297126, 2131296702, 2131296705, 2130837943));
    public static final RunTimePermissionsRequest b = new RunTimePermissionsRequest("camera for profile", "android.permission.CAMERA").a(new SingPermissionExplanationDialogCreator(2131297125, 2131297123, 2131296702, 2131296705, 2130837879)).a(false, new SingPermissionExplanationDialogCreator(2131297122, 2131297121, 2131296702, 2131297133));
    public static final RunTimePermissionsRequest c = new RunTimePermissionsRequest("camera for song", "android.permission.CAMERA").a(new SingPermissionExplanationDialogCreator(2131297125, 2131297124, 2131296702, 2131296705, 2130837879)).a(false, new SingPermissionExplanationDialogCreator(2131297122, 2131297121, 2131296702, 2131297133));
    public static final RunTimePermissionsRequest d = new RunTimePermissionsRequest("mic", "android.permission.RECORD_AUDIO").a(new SingPermissionExplanationDialogCreator(2131297132, 2131297131, 2131296702, 2131296705, 2130837949)).b(new SingPermissionExplanationDialogCreator(2131297120, 2131297128, 2131296702, 2131296705)).a(true, new SingPermissionExplanationDialogCreator(2131297130, 2131297129, 2131296702, 2131297133));
    public static final SingPermissionExplanationFullscreenDialogCreator e = new SingPermissionExplanationFullscreenDialogCreator(2131296821, 2131297723, 2131297497, 2131296808, 2130838083, false, 2130903192);
    public static final RunTimePermissionsRequest f = new RunTimePermissionsRequest("read contacts", "android.permission.READ_CONTACTS").a(e);
    public static final RunTimePermissionsRequest g = new RunTimePermissionsRequest("read contacts", "android.permission.READ_CONTACTS");
    public static final RunTimePermissionsRequest h = new RunTimePermissionsRequest("read contacts", "android.permission.READ_CONTACTS").a(true, new SingPermissionExplanationDialogCreator(2131296825, 2131296824, 2131297497, 2131296823, 2130838083, false, 2130903191));
    public static final RunTimePermissionsRequest i = new RunTimePermissionsRequest("mic", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE").a(new SingPermissionExplanationDialogCreator(2131297132, 2131297131, 2131296702, 2131296705, 2130837949)).b(new SingPermissionExplanationDialogCreator(2131297120, 2131297128, 2131296702, 2131296705)).a(true, new SingPermissionExplanationDialogCreator(2131297130, 2131297129, 2131296702, 2131297133));
    private static boolean j = false;

    private SingPermissionRequests() {
    }

    public static void a(@NonNull BaseActivity baseActivity, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        if (!j) {
            baseActivity.a(a, resultCallback);
            j = true;
        }
    }

    public static void a(@NonNull BaseFragment baseFragment, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        if (!j) {
            baseFragment.a(a, resultCallback);
            j = true;
        }
    }
}

