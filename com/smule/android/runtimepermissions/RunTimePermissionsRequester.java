/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageManager
 *  android.net.Uri
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v13.app.FragmentCompat
 *  android.support.v13.app.FragmentCompat$OnRequestPermissionsResultCallback
 *  android.support.v4.app.ActivityCompat
 *  android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback
 *  android.support.v4.content.ContextCompat
 *  android.support.v4.util.ArrayMap
 *  android.support.v4.util.ArraySet
 *  android.text.TextUtils
 */
package com.smule.android.runtimepermissions;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RunTimePermissionsRequester {
    public static final String a = RunTimePermissionsRequester.class.getSimpleName();
    private static final Map<String, String> b = new ArrayMap();
    private static final Map<String, String> c = new ArrayMap();
    private final RunTimePermissionsRequest d;
    private final ResultCallback e;
    private Dialog f = null;

    static {
        b.put("android.permission.CAMERA", "cam_permission_clk");
        b.put("android.permission.ACCESS_COARSE_LOCATION", "loc_permission_clk");
        b.put("android.permission.RECORD_AUDIO", "mic_permission_clk");
        b.put("android.permission.READ_CONTACTS", "contacts_permission_clk");
        c.put("android.permission.CAMERA", "android.hardware.camera.front");
        c.put("android.permission.ACCESS_COARSE_LOCATION", "android.hardware.location");
        c.put("android.permission.RECORD_AUDIO", "android.hardware.microphone");
    }

    public RunTimePermissionsRequester(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        this.d = runTimePermissionsRequest;
        this.e = resultCallback;
    }

    static /* synthetic */ RunTimePermissionsRequest a(RunTimePermissionsRequester runTimePermissionsRequester) {
        return runTimePermissionsRequester.d;
    }

    private List<String> a(@NonNull Context context, @NonNull String[] arrstring) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : arrstring) {
            if (ContextCompat.checkSelfPermission((Context)context, (String)string2) == 0) continue;
            arrayList.add(string2);
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(@NonNull Context object, @NonNull Analytics permissionResult) {
        PackageManager packageManager = object.getPackageManager();
        String[] arrstring = this.d.b;
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String string2 = arrstring[n2];
            String string3 = b.get(string2);
            if (string3 != null) {
                void var1_7;
                void var2_10;
                String string4 = c.get(string2);
                if (packageManager == null) {
                    Object var1_6 = null;
                } else if (TextUtils.isEmpty((CharSequence)string4) || packageManager.hasSystemFeature(string4)) {
                    Analytics permissionErrorReason = Analytics.a;
                } else {
                    Analytics permissionErrorReason = Analytics.b;
                }
                com.smule.android.logging.Analytics.a(string3, null, Analytics.d, var2_10, var1_7);
            }
            ++n2;
        }
    }

    private void a(@NonNull Context context, String string2) {
        context = context.getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0);
        ArraySet arraySet = new ArraySet((Collection)context.getStringSet("SoftPermissionRequestsSeen", null));
        arraySet.add(string2);
        context.edit().putStringSet("SoftPermissionRequestsSeen", (Set)arraySet).apply();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, int n, @NonNull String[] object, @NonNull int[] arrn) {
        boolean bl = true;
        if (object.length == 0) {
            return;
        }
        boolean bl2 = false;
        if (bl2) {
            return;
        }
        if (((HardRequestReport)(object = new HardRequestReport(this, permissionHandlingActivityOrFragmentWrapper, (String[])object, arrn))).d) {
            bl = this.d.e != null && this.d.f;
            this.a(permissionHandlingActivityOrFragmentWrapper, bl, ((HardRequestReport)object).b);
            return;
        }
        n = n == 2 ? 1 : 0;
        bl2 = this.d.d != null;
        if (!((HardRequestReport)object).c || !bl2 || n != 0) {
            bl = false;
        }
        this.a(permissionHandlingActivityOrFragmentWrapper, bl, 2, ((HardRequestReport)object).b);
    }

    private void a(final @NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, final Set<String> set) {
        final Context context = permissionHandlingActivityOrFragmentWrapper.a();
        this.f = RunTimePermissionsRequest.a(context, this.d.c, new RunTimePermissionsRequest.ExplanationDialogListener(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(Dialog dialog) {
                RunTimePermissionsRequester.this.a(null, Analytics.b, Analytics.b);
                if (!"mic".equals(RunTimePermissionsRequester.a((RunTimePermissionsRequester)RunTimePermissionsRequester.this).a)) {
                    RunTimePermissionsRequester.this.a(context, RunTimePermissionsRequester.a((RunTimePermissionsRequester)RunTimePermissionsRequester.this).a);
                }
                boolean bl = RunTimePermissionsRequester.a((RunTimePermissionsRequester)RunTimePermissionsRequester.this).d != null;
                RunTimePermissionsRequester.this.a(permissionHandlingActivityOrFragmentWrapper, bl, 1, set);
            }

            @Override
            public void b(Dialog dialog) {
                RunTimePermissionsRequester.this.a(null, Analytics.b, Analytics.a);
                if (!"mic".equals(RunTimePermissionsRequester.a((RunTimePermissionsRequester)RunTimePermissionsRequester.this).a)) {
                    RunTimePermissionsRequester.this.a(context, RunTimePermissionsRequester.a((RunTimePermissionsRequester)RunTimePermissionsRequester.this).a);
                }
                permissionHandlingActivityOrFragmentWrapper.a(RunTimePermissionsRequester.a((RunTimePermissionsRequester)RunTimePermissionsRequester.this).b, 1);
            }
        });
    }

    private void a(final @NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, boolean bl, final int n, final @NonNull Set<String> set) {
        if (bl) {
            this.f = RunTimePermissionsRequest.a(permissionHandlingActivityOrFragmentWrapper.a(), this.d.d, new RunTimePermissionsRequest.ExplanationDialogListener(){

                @Override
                public void a(Dialog dialog) {
                    RunTimePermissionsRequester.this.a(null, Analytics.c, Analytics.b);
                    RunTimePermissionsRequester.this.a((F)set);
                }

                @Override
                public void b(Dialog dialog) {
                    RunTimePermissionsRequester.this.a(null, Analytics.c, Analytics.a);
                    permissionHandlingActivityOrFragmentWrapper.a(RunTimePermissionsRequester.a((RunTimePermissionsRequester)RunTimePermissionsRequester.this).b, n);
                }
            });
            return;
        }
        this.a((F)set);
    }

    private void a(final @NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, boolean bl, final @NonNull Set<String> set) {
        RunTimePermissionsRequest.ExplanationDialogCreator explanationDialogCreator = this.d.e;
        if (!bl) {
            this.a((F)set);
            return;
        }
        this.f = RunTimePermissionsRequest.a(permissionHandlingActivityOrFragmentWrapper.a(), explanationDialogCreator, new RunTimePermissionsRequest.ExplanationDialogListener(){

            @Override
            public void a(Dialog dialog) {
                RunTimePermissionsRequester.this.a(permissionHandlingActivityOrFragmentWrapper.a(), Analytics.b);
                RunTimePermissionsRequester.this.a((F)set);
            }

            @Override
            public void b(Dialog dialog) {
                RunTimePermissionsRequester.this.a(permissionHandlingActivityOrFragmentWrapper.a(), Analytics.c);
                RunTimePermissionsRequester.this.a((F)set);
                RunTimePermissionsRequester.b(permissionHandlingActivityOrFragmentWrapper);
            }
        });
    }

    private void a(@Nullable Boolean bl, @NonNull Analytics permissionAskType, @NonNull Analytics permissionResult) {
        for (String string2 : this.d.b) {
            if ((string2 = b.get(string2)) == null) continue;
            com.smule.android.logging.Analytics.a(string2, Analytics.a(bl), permissionAskType, permissionResult, null);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(@NonNull Set<String> set) {
        boolean bl;
        block2 : {
            bl = false;
            if (this.e == null) return;
            String[] arrstring = this.d.b;
            int n = arrstring.length;
            for (int i = 0; i < n; ++i) {
                if (set.contains(arrstring[i])) {
                    continue;
                }
                break block2;
            }
            bl = true;
        }
        this.e.a(bl, set);
    }

    private void a(@NonNull String[] arrstring) {
        this.a((F)new LinkedHashSet<String>(Arrays.asList(arrstring)));
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, @NonNull String string2) {
        block5 : {
            block4 : {
                if (ContextCompat.checkSelfPermission((Context)permissionHandlingActivityOrFragmentWrapper.a(), (String)string2) == 0) break block4;
                SharedPreferences sharedPreferences = permissionHandlingActivityOrFragmentWrapper.a().getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0);
                ArraySet arraySet = new ArraySet((Collection)sharedPreferences.getStringSet("RationalizedPermissions", null));
                boolean bl = arraySet.contains(string2);
                boolean bl2 = permissionHandlingActivityOrFragmentWrapper.a(string2);
                if (bl2) {
                    arraySet.add(string2);
                    sharedPreferences.edit().putStringSet("RationalizedPermissions", (Set)arraySet).apply();
                }
                if (!bl2 && this.b(permissionHandlingActivityOrFragmentWrapper, string2) && bl) break block5;
            }
            return false;
        }
        return true;
    }

    private boolean a(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, @NonNull String[] arrstring) {
        boolean bl = false;
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            if (!this.a(permissionHandlingActivityOrFragmentWrapper, arrstring[i])) continue;
            bl = true;
        }
        return bl;
    }

    private void b(@NonNull Context context, @NonNull String[] arrstring) {
        context = context.getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0);
        ArraySet arraySet = new ArraySet((Collection)context.getStringSet("HardPermissionRequestsSeen", null));
        arraySet.addAll(Arrays.asList(arrstring));
        context.edit().putStringSet("HardPermissionRequestsSeen", (Set)arraySet).apply();
    }

    private static void b(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse((String)("package:" + permissionHandlingActivityOrFragmentWrapper.a().getPackageName())));
        permissionHandlingActivityOrFragmentWrapper.a(intent);
    }

    private boolean b(@NonNull Context context, String string2) {
        return context.getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0).getStringSet("SoftPermissionRequestsSeen", (Set)new ArraySet()).contains(string2);
    }

    private boolean b(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, @NonNull String string2) {
        return permissionHandlingActivityOrFragmentWrapper.a().getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0).getStringSet("HardPermissionRequestsSeen", (Set)new ArraySet()).contains(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper) {
        boolean bl = true;
        Context context = permissionHandlingActivityOrFragmentWrapper.a();
        List<String> list = this.a(context, this.d.b);
        if (list.isEmpty()) {
            this.a(this.d.b);
            return;
        }
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>(Arrays.asList(this.d.b));
        linkedHashSet.removeAll(list);
        if (this.a(permissionHandlingActivityOrFragmentWrapper, this.d.b)) {
            if (this.d.e == null) {
                bl = false;
            }
            this.a(permissionHandlingActivityOrFragmentWrapper, bl, linkedHashSet);
            return;
        }
        if (this.d.c != null && !this.b(context, this.d.a)) {
            this.a(permissionHandlingActivityOrFragmentWrapper, linkedHashSet);
            return;
        }
        permissionHandlingActivityOrFragmentWrapper.a(this.d.b, 1);
    }

    public void a() {
        if (this.f != null) {
            this.f.dismiss();
            this.f = null;
        }
    }

    public <A extends Activity> void a(@NonNull A a) {
        this.c(new PermissionHandlingActivityOrFragmentWrapper((Activity)a, null));
    }

    public <A extends Activity> void a(@NonNull A a, int n, @NonNull String[] arrstring, @NonNull int[] arrn) {
        this.a(new PermissionHandlingActivityOrFragmentWrapper((Activity)a, null), n, arrstring, arrn);
    }

    public <F extends Fragment> void a(@NonNull F f) {
        this.c(new PermissionHandlingActivityOrFragmentWrapper((Fragment)f, null));
    }

    public <F extends Fragment> void a(@NonNull F f, int n, @NonNull String[] arrstring, @NonNull int[] arrn) {
        this.a(new PermissionHandlingActivityOrFragmentWrapper((Fragment)f, null), n, arrstring, arrn);
    }

    private class HardRequestReport {
        final /* synthetic */ RunTimePermissionsRequester a;
        private final Set<String> b;
        private final boolean c;
        private final boolean d;

        /*
         * Enabled aggressive block sorting
         */
        private HardRequestReport(RunTimePermissionsRequester runTimePermissionsRequester, PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, String[] arrstring, int[] arrn) {
            boolean bl = false;
            this.a = runTimePermissionsRequester;
            this.b = new LinkedHashSet<String>();
            int n = 0;
            boolean bl2 = false;
            do {
                boolean bl3;
                if (n >= arrstring.length) {
                    runTimePermissionsRequester.b(permissionHandlingActivityOrFragmentWrapper.a(), arrstring);
                    this.c = bl2;
                    this.d = bl;
                    return;
                }
                if (arrn[n] == -1) {
                    bl2 = true;
                } else {
                    this.b.add(arrstring[n]);
                }
                if (bl3 = runTimePermissionsRequester.a(permissionHandlingActivityOrFragmentWrapper, arrstring[n])) {
                    bl = true;
                }
                Analytics permissionNeverAskAgain = runTimePermissionsRequester.b(permissionHandlingActivityOrFragmentWrapper, arrstring[n]) ? Analytics.a(bl3) : null;
                com.smule.android.logging.Analytics.a((String)b.get(arrstring[n]), permissionNeverAskAgain, Analytics.a, Analytics.a(arrn[n]), null);
                ++n;
            } while (true);
        }
    }

    private static class PermissionHandlingActivityOrFragmentWrapper {
        private final Activity a;
        private final Fragment b;

        private <A extends Activity> PermissionHandlingActivityOrFragmentWrapper(A a) {
            this.a = a;
            this.b = null;
        }

        /* synthetic */ PermissionHandlingActivityOrFragmentWrapper(Activity activity,  var2_2) {
            this((F)activity);
        }

        private <F extends Fragment> PermissionHandlingActivityOrFragmentWrapper(F f) {
            this.b = f;
            this.a = null;
        }

        /* synthetic */ PermissionHandlingActivityOrFragmentWrapper(Fragment fragment,  var2_2) {
            this((F)fragment);
        }

        private Context a() {
            if (this.a != null) {
                return this.a;
            }
            return this.b.getActivity();
        }

        private void a(Intent intent) {
            if (this.a != null) {
                this.a.startActivity(intent);
                return;
            }
            this.b.startActivity(intent);
        }

        private void a(String[] arrstring, int n) {
            if (this.a != null) {
                ActivityCompat.requestPermissions((Activity)this.a, (String[])arrstring, (int)n);
                return;
            }
            FragmentCompat.requestPermissions((Fragment)this.b, (String[])arrstring, (int)n);
        }

        private boolean a(String string2) {
            if (this.a != null) {
                return ActivityCompat.shouldShowRequestPermissionRationale((Activity)this.a, (String)string2);
            }
            return FragmentCompat.shouldShowRequestPermissionRationale((Fragment)this.b, (String)string2);
        }
    }

    public static interface ResultCallback {
        public void a(boolean var1, @NonNull Set<String> var2);
    }

}

