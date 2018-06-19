package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.audio.AudioDefs.MonitoringMode;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.DeviceSettingsManager;
import com.smule.singandroid.audio.OpenSLStreamVersion;

public class DeviceSettings {
    private static final String f7041a = DeviceSettings.class.getName();

    public static int m8655a(HeadphonesType headphonesType, OpenSLStreamVersion openSLStreamVersion, int i) {
        if (m8669o()) {
            return m8665k();
        }
        if (m8666l()) {
            return m8663i();
        }
        if (openSLStreamVersion != OpenSLStreamVersion.c) {
            return DeviceSettingsManager.a().a(headphonesType.c(), -1);
        }
        int a = DeviceSettingsManager.a().a(headphonesType.d(), -1);
        if (a != -1) {
            Log.m7767a(f7041a, "Returning latency " + a + " (ms)");
            return a;
        }
        a = DeviceSettingsManager.a().a(headphonesType.c(), -1);
        Log.m7767a(f7041a, "No V3 latency. OpenSL Stream V1 latency is " + a + "ms. Offset requested: " + i + "ms.");
        if (a == -1) {
            return a;
        }
        if (a + i >= 0) {
            return a + i;
        }
        Log.m7776e(f7041a, "Latency offset is too high to be handled for this device setting");
        return a;
    }

    public static int m8654a() {
        return DeviceSettingsManager.a().a("srate", -1);
    }

    public static int m8656b() {
        return DeviceSettingsManager.a().a("buffsize", -1);
    }

    public static int m8657c() {
        return DeviceSettingsManager.a().a("streamver", -1);
    }

    public static int m8658d() {
        return DeviceSettingsManager.a().a("buffmult", 2);
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    @TargetApi(17)
    public static float m8659e() {
        float parseFloat;
        int a;
        if (VERSION.SDK_INT >= 17) {
            try {
                parseFloat = Float.parseFloat(((AudioManager) SingApplication.m8798f().getSystemService("audio")).getProperty("android.media.property.OUTPUT_SAMPLE_RATE"));
            } catch (Throwable th) {
                Log.m7772c(f7041a, "getSampleRate - Unable to obtain rate from AudioManager.");
            }
            if (!(m8669o() || m8666l())) {
                if (Build.MODEL == null && Build.MODEL.toUpperCase().contains("NEXUS") && VERSION.SDK_INT < 23) {
                    Log.m7772c(f7041a, "getSampleRate - Overriding device-preferred audio sample rate to " + 44100.0f + " for device of type " + Build.MODEL);
                    parseFloat = 44100.0f;
                } else {
                    a = m8654a();
                    if (a != -1) {
                        parseFloat = (float) a;
                        Log.m7772c(f7041a, "getSampleRate - Returning value from device settings API: " + parseFloat);
                    }
                }
            }
            Log.m7772c(f7041a, "getSampleRate - returning sampling rate of " + parseFloat + " for " + Build.MODEL);
            return parseFloat;
        }
        parseFloat = 44100.0f;
        if (Build.MODEL == null) {
        }
        a = m8654a();
        if (a != -1) {
            parseFloat = (float) a;
            Log.m7772c(f7041a, "getSampleRate - Returning value from device settings API: " + parseFloat);
        }
        Log.m7772c(f7041a, "getSampleRate - returning sampling rate of " + parseFloat + " for " + Build.MODEL);
        return parseFloat;
    }

    @Nullable
    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public static Integer m8660f() {
        if (VERSION.SDK_INT < 17) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(((AudioManager) SingApplication.m8798f().getSystemService("audio")).getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")));
        } catch (Throwable th) {
            Log.m7776e(f7041a, "Could not get native buffer size from AudioManager");
            return null;
        }
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    @TargetApi(17)
    public static int m8661g() {
        Integer valueOf = Integer.valueOf(m8656b());
        if (valueOf.intValue() == -1) {
            valueOf = m8660f();
            if (valueOf == null) {
                valueOf = Integer.valueOf(1024);
            }
            if (!m8666l()) {
                valueOf = Integer.valueOf(valueOf.intValue() * m8658d());
            }
        }
        valueOf = Integer.valueOf(Math.min(2048, valueOf.intValue()));
        Log.m7770b(f7041a, "getBufferSize returning: " + valueOf);
        return valueOf.intValue();
    }

    public static int m8662h() {
        int c;
        SingApplication.m8798f().getSharedPreferences("sing_prefs", 0);
        if (m8657c() != -1) {
            c = m8657c();
            Log.m7770b(f7041a, "getOpenSLStreamVersion - returning value from device settings API: " + c);
        } else if (SingServerValues.v() != 0) {
            c = SingServerValues.v();
            Log.m7770b(f7041a, "getOpenSLStreamVersion - returning value from SNP settings: " + c);
        } else {
            c = 1;
            Log.m7770b(f7041a, "getOpenSLStreamVersion - no value returned from device settings API; returning default: " + 1);
        }
        Log.m7767a(f7041a, "getOpenSLStreamVersion - OpenSL ES Stream Version: " + c);
        return c;
    }

    public static int m8663i() {
        return DeviceSettingsManager.a().a("opensl_rtm_latency", -1);
    }

    public static int m8664j() {
        if (m8663i() > 0) {
            return DeviceSettingsManager.a().a("opensl_rtm_min_os", Integer.MAX_VALUE);
        }
        return Integer.MAX_VALUE;
    }

    public static int m8665k() {
        return -1;
    }

    public static boolean m8666l() {
        return VERSION.SDK_INT >= m8664j();
    }

    public static boolean m8667m() {
        return false;
    }

    public static MonitoringMode m8668n() {
        MonitoringMode monitoringMode = MonitoringMode.a;
        if (m8669o()) {
            return MonitoringMode.c;
        }
        if (m8666l()) {
            return MonitoringMode.b;
        }
        return monitoringMode;
    }

    public static boolean m8669o() {
        if (m8667m()) {
            return SingApplication.m8798f().getSharedPreferences("sing_prefs", 0).getBoolean("SAMSUNG_RTM_ENABLED_IN_SETTINGS", SingServerValues.u());
        }
        return false;
    }

    public static boolean m8670p() {
        return DeviceSettingsManager.a().a("vplay", 1) == 1;
    }

    public static boolean m8671q() {
        return DeviceSettingsManager.a().a("vrec", 1) == 1;
    }

    public static boolean m8672r() {
        return DeviceSettingsManager.a().a("vfx_enabled", 1) == 1;
    }

    public static boolean m8673s() {
        return DeviceSettingsManager.a().a("vfx_record", 1) == 1;
    }
}
