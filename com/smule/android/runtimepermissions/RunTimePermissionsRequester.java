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
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.PermissionAskType;
import com.smule.android.logging.Analytics.PermissionErrorReason;
import com.smule.android.logging.Analytics.PermissionNeverAskAgain;
import com.smule.android.logging.Analytics.PermissionResult;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest.ExplanationDialogCreator;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest.ExplanationDialogListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RunTimePermissionsRequester {
    public static final String f17614a = RunTimePermissionsRequester.class.getSimpleName();
    private static final Map<String, String> f17615b = new ArrayMap();
    private static final Map<String, String> f17616c = new ArrayMap();
    private final RunTimePermissionsRequest f17617d;
    private final ResultCallback f17618e;

    private class HardRequestReport {
        final /* synthetic */ RunTimePermissionsRequester f17608a;
        private final Set<String> f17609b;
        private final boolean f17610c;
        private final boolean f17611d;

        private HardRequestReport(RunTimePermissionsRequester runTimePermissionsRequester, PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, String[] strArr, int[] iArr) {
            boolean z = false;
            this.f17608a = runTimePermissionsRequester;
            this.f17609b = new LinkedHashSet();
            int i = 0;
            boolean z2 = false;
            while (i < strArr.length) {
                boolean z3;
                PermissionNeverAskAgain a;
                if (iArr[i] == -1) {
                    z3 = true;
                } else {
                    this.f17609b.add(strArr[i]);
                    z3 = z2;
                }
                boolean a2 = runTimePermissionsRequester.m18728a(permissionHandlingActivityOrFragmentWrapper, strArr[i]);
                if (a2) {
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (runTimePermissionsRequester.m18734b(permissionHandlingActivityOrFragmentWrapper, strArr[i])) {
                    a = PermissionNeverAskAgain.m17800a(Boolean.valueOf(a2));
                } else {
                    a = null;
                }
                Analytics.m17861a((String) RunTimePermissionsRequester.f17615b.get(strArr[i]), a, PermissionAskType.HARD_ASK, PermissionResult.m17802a(iArr[i]), null);
                i++;
                z = z2;
                z2 = z3;
            }
            runTimePermissionsRequester.m18731b(permissionHandlingActivityOrFragmentWrapper.m18700a(), strArr);
            this.f17610c = z2;
            this.f17611d = z;
        }
    }

    private static class PermissionHandlingActivityOrFragmentWrapper {
        private final Activity f17612a;
        private final Fragment f17613b;

        private <A extends Activity & OnRequestPermissionsResultCallback> PermissionHandlingActivityOrFragmentWrapper(A a) {
            this.f17612a = a;
            this.f17613b = null;
        }

        private <F extends Fragment & FragmentCompat.OnRequestPermissionsResultCallback> PermissionHandlingActivityOrFragmentWrapper(F f) {
            this.f17613b = f;
            this.f17612a = null;
        }

        private Context m18700a() {
            if (this.f17612a != null) {
                return this.f17612a;
            }
            return this.f17613b.getActivity();
        }

        private void m18702a(Intent intent) {
            if (this.f17612a != null) {
                this.f17612a.startActivity(intent);
            } else {
                this.f17613b.startActivity(intent);
            }
        }

        private void m18705a(String[] strArr, int i) {
            if (this.f17612a != null) {
                ActivityCompat.requestPermissions(this.f17612a, strArr, i);
            } else {
                FragmentCompat.requestPermissions(this.f17613b, strArr, i);
            }
        }

        private boolean m18707a(String str) {
            if (this.f17612a != null) {
                return ActivityCompat.shouldShowRequestPermissionRationale(this.f17612a, str);
            }
            return FragmentCompat.shouldShowRequestPermissionRationale(this.f17613b, str);
        }
    }

    public interface ResultCallback {
        void mo6372a(boolean z, @NonNull Set<String> set);
    }

    static {
        f17615b.put("android.permission.CAMERA", "cam_permission_clk");
        f17615b.put("android.permission.ACCESS_COARSE_LOCATION", "loc_permission_clk");
        f17615b.put("android.permission.RECORD_AUDIO", "mic_permission_clk");
        f17616c.put("android.permission.CAMERA", "android.hardware.camera.front");
        f17616c.put("android.permission.ACCESS_COARSE_LOCATION", "android.hardware.location");
        f17616c.put("android.permission.RECORD_AUDIO", "android.hardware.microphone");
    }

    private static void m18732b(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + permissionHandlingActivityOrFragmentWrapper.m18700a().getPackageName()));
        permissionHandlingActivityOrFragmentWrapper.m18702a(intent);
    }

    public RunTimePermissionsRequester(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        this.f17617d = runTimePermissionsRequest;
        this.f17618e = resultCallback;
    }

    public <A extends Activity & OnRequestPermissionsResultCallback> void m18737a(@NonNull A a) {
        m18736c(new PermissionHandlingActivityOrFragmentWrapper((Activity) a));
    }

    public <F extends Fragment & FragmentCompat.OnRequestPermissionsResultCallback> void m18739a(@NonNull F f) {
        m18736c(new PermissionHandlingActivityOrFragmentWrapper((Fragment) f));
    }

    private void m18736c(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper) {
        boolean z = true;
        Context a = permissionHandlingActivityOrFragmentWrapper.m18700a();
        Collection a2 = m18710a(a, this.f17617d.f17592b);
        if (a2.isEmpty()) {
            m18727a(this.f17617d.f17592b);
            return;
        }
        Set linkedHashSet = new LinkedHashSet(Arrays.asList(this.f17617d.f17592b));
        linkedHashSet.removeAll(a2);
        if (m18729a(permissionHandlingActivityOrFragmentWrapper, this.f17617d.f17592b)) {
            if (this.f17617d.f17595e == null) {
                z = false;
            }
            m18718a(permissionHandlingActivityOrFragmentWrapper, z, linkedHashSet);
        } else if (this.f17617d.f17593c == null || m18733b(a, this.f17617d.f17591a)) {
            permissionHandlingActivityOrFragmentWrapper.m18705a(this.f17617d.f17592b, 1);
        } else {
            m18716a(permissionHandlingActivityOrFragmentWrapper, linkedHashSet);
        }
    }

    private void m18716a(@NonNull final PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, final Set<String> set) {
        final Context a = permissionHandlingActivityOrFragmentWrapper.m18700a();
        RunTimePermissionsRequest.m18687a(a, this.f17617d.f17593c, new ExplanationDialogListener(this) {
            final /* synthetic */ RunTimePermissionsRequester f17600d;

            public void mo6285a(Dialog dialog) {
                this.f17600d.m18725a(null, PermissionAskType.SOFT_ASK, PermissionResult.DENY);
                this.f17600d.m18713a(a, this.f17600d.f17617d.f17591a);
                this.f17600d.m18717a(permissionHandlingActivityOrFragmentWrapper, this.f17600d.f17617d.f17594d != null, 1, set);
            }

            public void mo6286b(Dialog dialog) {
                this.f17600d.m18725a(null, PermissionAskType.SOFT_ASK, PermissionResult.OKAY);
                this.f17600d.m18713a(a, this.f17600d.f17617d.f17591a);
                permissionHandlingActivityOrFragmentWrapper.m18705a(this.f17600d.f17617d.f17592b, 1);
            }
        });
    }

    public <A extends Activity & OnRequestPermissionsResultCallback> void m18738a(@NonNull A a, int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        m18715a(new PermissionHandlingActivityOrFragmentWrapper((Activity) a), i, strArr, iArr);
    }

    public <F extends Fragment & FragmentCompat.OnRequestPermissionsResultCallback> void m18740a(@NonNull F f, int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        m18715a(new PermissionHandlingActivityOrFragmentWrapper((Fragment) f), i, strArr, iArr);
    }

    private void m18715a(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        boolean z = true;
        if (!(strArr.length == 0)) {
            HardRequestReport hardRequestReport = new HardRequestReport(permissionHandlingActivityOrFragmentWrapper, strArr, iArr);
            boolean z2;
            if (hardRequestReport.f17611d) {
                if (this.f17617d.f17595e == null || !this.f17617d.f17596f) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                m18718a(permissionHandlingActivityOrFragmentWrapper, z2, hardRequestReport.f17609b);
                return;
            }
            if (i == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z3;
            if (this.f17617d.f17594d != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!(hardRequestReport.f17610c && r2 && !r1)) {
                z = false;
            }
            m18717a(permissionHandlingActivityOrFragmentWrapper, z, 2, hardRequestReport.f17609b);
        }
    }

    private List<String> m18710a(@NonNull Context context, @NonNull String[] strArr) {
        List<String> arrayList = new ArrayList();
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private void m18713a(@NonNull Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0);
        Set arraySet = new ArraySet(sharedPreferences.getStringSet("SoftPermissionRequestsSeen", null));
        arraySet.add(str);
        sharedPreferences.edit().putStringSet("SoftPermissionRequestsSeen", arraySet).apply();
    }

    private boolean m18733b(@NonNull Context context, String str) {
        return context.getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0).getStringSet("SoftPermissionRequestsSeen", new ArraySet()).contains(str);
    }

    private void m18731b(@NonNull Context context, @NonNull String[] strArr) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0);
        Set arraySet = new ArraySet(sharedPreferences.getStringSet("HardPermissionRequestsSeen", null));
        arraySet.addAll(Arrays.asList(strArr));
        sharedPreferences.edit().putStringSet("HardPermissionRequestsSeen", arraySet).apply();
    }

    private boolean m18729a(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, @NonNull String[] strArr) {
        boolean z = false;
        for (String a : strArr) {
            if (m18728a(permissionHandlingActivityOrFragmentWrapper, a)) {
                z = true;
            }
        }
        return z;
    }

    private boolean m18728a(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, @NonNull String str) {
        if (ContextCompat.checkSelfPermission(permissionHandlingActivityOrFragmentWrapper.m18700a(), str) == 0) {
            return false;
        }
        SharedPreferences sharedPreferences = permissionHandlingActivityOrFragmentWrapper.m18700a().getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0);
        Set arraySet = new ArraySet(sharedPreferences.getStringSet("RationalizedPermissions", null));
        boolean contains = arraySet.contains(str);
        boolean a = permissionHandlingActivityOrFragmentWrapper.m18707a(str);
        if (a) {
            arraySet.add(str);
            sharedPreferences.edit().putStringSet("RationalizedPermissions", arraySet).apply();
        }
        if (!a && m18734b(permissionHandlingActivityOrFragmentWrapper, str) && contains) {
            return true;
        }
        return false;
    }

    private boolean m18734b(@NonNull PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, @NonNull String str) {
        return permissionHandlingActivityOrFragmentWrapper.m18700a().getSharedPreferences(RunTimePermissionsRequester.class.getName(), 0).getStringSet("HardPermissionRequestsSeen", new ArraySet()).contains(str);
    }

    private void m18718a(@NonNull final PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, boolean z, @NonNull final Set<String> set) {
        ExplanationDialogCreator explanationDialogCreator = this.f17617d.f17595e;
        if (z) {
            RunTimePermissionsRequest.m18687a(permissionHandlingActivityOrFragmentWrapper.m18700a(), explanationDialogCreator, new ExplanationDialogListener(this) {
                final /* synthetic */ RunTimePermissionsRequester f17603c;

                public void mo6285a(Dialog dialog) {
                    this.f17603c.m18712a(permissionHandlingActivityOrFragmentWrapper.m18700a(), PermissionResult.DENY);
                    this.f17603c.m18726a(set);
                }

                public void mo6286b(Dialog dialog) {
                    this.f17603c.m18712a(permissionHandlingActivityOrFragmentWrapper.m18700a(), PermissionResult.OPEN_SETTINGS);
                    this.f17603c.m18726a(set);
                    RunTimePermissionsRequester.m18732b(permissionHandlingActivityOrFragmentWrapper);
                }
            });
        } else {
            m18726a((Set) set);
        }
    }

    private void m18717a(@NonNull final PermissionHandlingActivityOrFragmentWrapper permissionHandlingActivityOrFragmentWrapper, boolean z, final int i, @NonNull final Set<String> set) {
        if (z) {
            RunTimePermissionsRequest.m18687a(permissionHandlingActivityOrFragmentWrapper.m18700a(), this.f17617d.f17594d, new ExplanationDialogListener(this) {
                final /* synthetic */ RunTimePermissionsRequester f17607d;

                public void mo6285a(Dialog dialog) {
                    this.f17607d.m18725a(null, PermissionAskType.SOFT_ASK_AGAIN, PermissionResult.DENY);
                    this.f17607d.m18726a(set);
                }

                public void mo6286b(Dialog dialog) {
                    this.f17607d.m18725a(null, PermissionAskType.SOFT_ASK_AGAIN, PermissionResult.OKAY);
                    permissionHandlingActivityOrFragmentWrapper.m18705a(this.f17607d.f17617d.f17592b, i);
                }
            });
        } else {
            m18726a((Set) set);
        }
    }

    private void m18727a(@NonNull String[] strArr) {
        m18726a(new LinkedHashSet(Arrays.asList(strArr)));
    }

    private void m18726a(@NonNull Set<String> set) {
        boolean z = false;
        if (this.f17618e != null) {
            for (Object contains : this.f17617d.f17592b) {
                if (!set.contains(contains)) {
                    break;
                }
            }
            z = true;
            this.f17618e.mo6372a(z, set);
        }
    }

    private void m18725a(@Nullable Boolean bool, @NonNull PermissionAskType permissionAskType, @NonNull PermissionResult permissionResult) {
        for (Object obj : this.f17617d.f17592b) {
            String str = (String) f17615b.get(obj);
            if (str != null) {
                Analytics.m17861a(str, PermissionNeverAskAgain.m17800a(bool), permissionAskType, permissionResult, null);
            }
        }
    }

    private void m18712a(@NonNull Context context, @NonNull PermissionResult permissionResult) {
        PackageManager packageManager = context.getPackageManager();
        for (Object obj : this.f17617d.f17592b) {
            String str = (String) f17615b.get(obj);
            if (str != null) {
                PermissionErrorReason permissionErrorReason = packageManager == null ? null : packageManager.hasSystemFeature((String) f17616c.get(obj)) ? PermissionErrorReason.PERMISSION_DENIED : PermissionErrorReason.DEVICE_ERROR;
                Analytics.m17861a(str, null, PermissionAskType.ERROR_ASK, permissionResult, permissionErrorReason);
            }
        }
    }
}
