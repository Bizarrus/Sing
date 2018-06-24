/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.media.AudioManager
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.audio.AudioDefs;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.AppSettingsModel;
import com.smule.android.network.managers.DeviceSettingsFetcher;
import com.smule.android.network.managers.DeviceSettingsManager;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.audio.OpenSLStreamVersion;

public class DeviceSettings {
    private static final String a = DeviceSettings.class.getName();
    @NonNull
    private DeviceSettingsFetcher b;
    @NonNull
    private SingServerValues c;
    @NonNull
    private Context d;

    public DeviceSettings() {
        this(DeviceSettingsManager.b(), AppSettingsManager.a(), SingApplication.g());
    }

    public DeviceSettings(@NonNull DeviceSettingsFetcher deviceSettingsFetcher, @NonNull AppSettingsModel appSettingsModel, @NonNull Context context) {
        this.b = deviceSettingsFetcher;
        this.c = new SingServerValues(appSettingsModel);
        this.d = context;
    }

    private int A() {
        return this.a("stream_version", -1);
    }

    private Integer B() {
        return this.b.a("opensl_rtm_latency");
    }

    private int C() {
        int n = Integer.MAX_VALUE;
        if (this.B() != null) {
            n = this.a("opensl_rtm_min_os", Integer.MAX_VALUE);
        }
        return n;
    }

    private Integer D() {
        return null;
    }

    private boolean E() {
        return this.c.p();
    }

    private boolean F() {
        boolean bl = false;
        if (this.m()) {
            bl = this.d.getApplicationContext().getSharedPreferences("sing_prefs", 0).getBoolean("SAMSUNG_RTM_ENABLED_IN_SETTINGS", this.c.z());
        }
        return bl;
    }

    private int a(String object, int n) {
        if ((object = this.b.a((String)object)) != null) {
            n = object.intValue();
        }
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Integer a(AudioDefs.HeadphonesType object) {
        Integer n = this.b.a(object.e());
        String string2 = a;
        StringBuilder stringBuilder = new StringBuilder().append("Returning superpowered latency ");
        object = n == null ? "null" : Integer.toString(n);
        Log.a(string2, stringBuilder.append((String)object).append(" (ms)").toString());
        return n;
    }

    private int w() {
        return this.a("srate", -1);
    }

    private int x() {
        return this.a("srate_v4", -1);
    }

    private int y() {
        return this.a("buffsize", -1);
    }

    private int z() {
        return this.a("buffsize_v4", -1);
    }

    public int a() {
        return this.a("buffmult", 2);
    }

    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    @Nullable
    public Integer a(Context context) {
        block4 : {
            try {
                context = (AudioManager)context.getApplicationContext().getSystemService("audio");
                if (context != null) break block4;
            }
            catch (Throwable throwable) {
                Log.e(a, "Could not get native buffer size from AudioManager");
                return null;
            }
            Log.d(a, "AudioManager is null in getNativeBufferSize(). Can't determine the audio system native buffer size");
            return null;
        }
        int n = Integer.parseInt(context.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER"));
        return n;
    }

    public Integer a(AudioDefs.HeadphonesType object, OpenSLStreamVersion object2, int n) {
        if (this.e()) {
            return this.a((AudioDefs.HeadphonesType)object);
        }
        if (this.F()) {
            return this.D();
        }
        if (this.l()) {
            return this.B();
        }
        if (object2 == OpenSLStreamVersion.c) {
            object2 = this.b.a(object.d());
            if (object2 != null) {
                Log.a(a, "Returning latency " + object2 + " (ms)");
                return object2;
            }
            object = this.b.a(object.c());
            Log.a(a, "No V3 latency. OpenSL Stream V1 latency is " + object + "ms. Offset requested: " + n + "ms.");
            if (object == null) {
                return null;
            }
            if (object.intValue() + n < 0) {
                Log.e(a, "Latency offset is too high to be handled for this device setting");
                return object;
            }
            return object.intValue() + n;
        }
        return this.b.a(object.c());
    }

    int b() {
        return this.a("buffmult_v4", 1);
    }

    int c() {
        int n = 1;
        if (this.e()) {
            n = this.a("buffdiv", 1);
        }
        return n;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    @TargetApi(value=17)
    public float d() {
        float f;
        block9 : {
            block8 : {
                try {
                    AudioManager audioManager = (AudioManager)this.d.getApplicationContext().getSystemService("audio");
                    if (audioManager == null) break block8;
                    f = Float.parseFloat(audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE"));
                    break block9;
                }
                catch (Throwable throwable) {
                    Log.d(a, "getSampleRate - Unable to obtain rate from AudioManager.", throwable);
                }
            }
            f = 44100.0f;
        }
        float f2 = f;
        if (!this.F()) {
            if (this.l()) {
                f2 = f;
            } else if (Build.MODEL != null && Build.MODEL.toUpperCase().contains("NEXUS") && Build.VERSION.SDK_INT < 23) {
                Log.c(a, "getSampleRate - Overriding device-preferred audio sample rate to " + 44100.0f + " for device of type " + Build.MODEL);
                f2 = 44100.0f;
            } else {
                int n = this.e() ? this.x() : this.w();
                f2 = f;
                if (n != -1) {
                    f2 = n;
                    Log.c(a, "getSampleRate - Returning value from device settings API: " + f2);
                }
            }
        }
        Log.c(a, "getSampleRate - returning sampling rate of " + f2 + " for " + Build.MODEL);
        return f2;
    }

    public boolean e() {
        if (this.i() == OpenSLStreamVersion.d.a()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    @TargetApi(value=17)
    public int f() {
        Integer n;
        int n2 = this.e() ? this.z() : this.y();
        Integer n3 = n = Integer.valueOf(n2);
        if (n == -1) {
            n3 = n = this.a(this.d);
            if (n == null) {
                n3 = 1024;
            }
            n2 = this.e() ? this.b() : this.a();
            n3 = Integer.valueOf(n3 * n2) / this.c();
        }
        n3 = Math.min(2048, n3);
        Log.b(a, "getBufferSize returning: " + n3);
        return n3;
    }

    public int g() {
        return this.a("read_buffers", 32);
    }

    public int h() {
        return this.a("write_buffers", 32);
    }

    /*
     * Enabled aggressive block sorting
     */
    public int i() {
        int n;
        int n2 = 3;
        this.d.getApplicationContext().getSharedPreferences("sing_prefs", 0);
        if (this.A() != -1) {
            n = this.A();
            Log.b(a, "getOpenSLStreamVersion - returning value from device settings API: " + n);
        } else if (this.c.A() != 0) {
            n = this.c.A();
            Log.b(a, "getOpenSLStreamVersion - returning value from SNP settings: " + n);
        } else {
            Log.b(a, "getOpenSLStreamVersion - no value returned from device settings API; returning default: " + 3);
            n = 3;
        }
        if (n > 4) {
            Log.b(a, "getOpenSLStreamVersion - bigger than supportved value supplied; fallback to default");
            n = n2;
        }
        Log.a(a, "getOpenSLStreamVersion - OpenSL ES Stream Version: " + n);
        return n;
    }

    public boolean j() {
        if (this.a("npt_g_enabled", 0) == 1) {
            return true;
        }
        return false;
    }

    public float k() {
        return (float)this.a("default_monitor_volume", 0) / 100.0f;
    }

    public boolean l() {
        if (this.E() || Build.VERSION.SDK_INT >= this.C()) {
            return true;
        }
        return false;
    }

    boolean m() {
        return false;
    }

    public boolean n() {
        if (this.o() != AudioDefs.MonitoringMode.a) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public AudioDefs.MonitoringMode o() {
        AudioDefs.MonitoringMode monitoringMode = AudioDefs.MonitoringMode.a;
        Integer n = this.b.a("rtm_enabled");
        if (n != null) {
            if (n != 0) return AudioDefs.MonitoringMode.b;
            return AudioDefs.MonitoringMode.a;
        }
        if (this.F()) {
            return AudioDefs.MonitoringMode.c;
        }
        if (!this.l()) return monitoringMode;
        return AudioDefs.MonitoringMode.b;
    }

    boolean p() {
        if (this.a("vplay", 1) == 1) {
            return true;
        }
        return false;
    }

    boolean q() {
        if (this.a("vrec", 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean r() {
        if (this.a("vfx_enabled", 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean s() {
        if (this.a("vfx_record", 1) == 1) {
            return true;
        }
        return false;
    }

    public  t() {
        return .a(this.a("ota_source", .a.a()));
    }

    public boolean u() {
        if (this.a("compute_ota_latency", 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean v() {
        if (this.t() != .b) {
            return true;
        }
        return false;
    }

}

