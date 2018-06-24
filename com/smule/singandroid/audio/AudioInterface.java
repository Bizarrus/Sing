package com.smule.singandroid.audio;

import android.content.Context;
import android.os.AsyncTask;
import com.smule.android.logging.Log;
import com.smule.android.utils.ResourceUtils;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.SingApplication;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.zip.ZipFile;

public class AudioInterface {
    public static final String f20656a = AudioInterface.class.getSimpleName();
    private String f20657b;

    public static abstract class FailRunnable implements Runnable {
        private Throwable f19603a;

        public void m21147a(Throwable th) {
            this.f19603a = th;
        }

        public Throwable m21146a() {
            return this.f19603a;
        }
    }

    private class FinalizePerformanceTask extends AsyncTask<Void, Void, Boolean> {
        final /* synthetic */ AudioInterface f20640a;
        private boolean f20641b;
        private Runnable f20642c = null;
        private Runnable f20643d = null;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m22261a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m22262a((Boolean) obj);
        }

        public FinalizePerformanceTask(AudioInterface audioInterface, boolean z, Runnable runnable, Runnable runnable2) {
            this.f20640a = audioInterface;
            this.f20641b = z;
            this.f20642c = runnable;
            this.f20643d = runnable2;
        }

        protected Boolean m22261a(Void... voidArr) {
            try {
                this.f20640a.finalizePerformanceNative();
                if (this.f20641b || this.f20640a.f20657b == null || new File(this.f20640a.f20657b).delete()) {
                    return Boolean.valueOf(true);
                }
                Log.e(AudioInterface.f20656a, "Couldn't delete recording file: " + this.f20640a.f20657b);
                return Boolean.valueOf(false);
            } catch (Throwable th) {
                Log.d(AudioInterface.f20656a, "Failed to finalize performance because of an exception in native code", th);
                return Boolean.valueOf(false);
            }
        }

        protected void m22262a(Boolean bool) {
            if (this.f20642c != null && bool == Boolean.TRUE) {
                this.f20642c.run();
            }
            if (this.f20643d != null && bool == Boolean.FALSE) {
                this.f20643d.run();
            }
        }
    }

    private class SetupPerformanceTask extends AsyncTask<Void, Void, Boolean> {
        final /* synthetic */ AudioInterface f20644a;
        private String f20645b;
        private String f20646c;
        private String f20647d;
        private String f20648e;
        private String f20649f;
        private Runnable f20650g = null;
        private FailRunnable f20651h = null;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m22263a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m22264a((Boolean) obj);
        }

        public SetupPerformanceTask(AudioInterface audioInterface, String str, String str2, String str3, String str4, String str5, Runnable runnable, FailRunnable failRunnable) {
            this.f20644a = audioInterface;
            this.f20645b = str;
            this.f20646c = str3;
            this.f20647d = str4;
            this.f20648e = str2;
            this.f20649f = str5;
            this.f20650g = runnable;
            this.f20651h = failRunnable;
        }

        protected Boolean m22263a(Void... voidArr) {
            try {
                Log.a(AudioInterface.f20656a, "calling setupPerformanceNative from SetupPerformanceTask");
                return Boolean.valueOf(this.f20644a.setupPerformanceNative(this.f20645b, this.f20648e, this.f20647d, this.f20646c, this.f20649f));
            } catch (Throwable th) {
                Log.d(AudioInterface.f20656a, "Failed to setup performance because of an exception thrown by native code", th);
                this.f20651h.m21147a(th);
                return Boolean.valueOf(false);
            }
        }

        protected void m22264a(Boolean bool) {
            if (bool.booleanValue()) {
                try {
                    this.f20644a.prepareForRealTime();
                    if (this.f20650g != null) {
                        this.f20650g.run();
                    }
                } catch (Throwable th) {
                    Log.d(AudioInterface.f20656a, "Failed to prepare for realtime audio rendering because of an exception in native code", th);
                    this.f20651h.m21147a(th);
                    if (this.f20651h != null) {
                        this.f20651h.run();
                    }
                }
            } else if (this.f20651h != null) {
                this.f20651h.run();
            }
        }
    }

    public enum SystemType {
        OpenSL,
        SAPA,
        Superpowered
    }

    public static native void destroyPerformance();

    private native int getAudioSystemLatency();

    private native int getOpenSLStreamV1Latency();

    private native int getSampleRate();

    private native void setContext(String str) throws Exception;

    private native void setupOpenSLES(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws Exception;

    private native boolean setupPerformanceNative(String str, String str2, String str3, String str4, String str5);

    private native void setupSAPA(int i, int i2, int i3, int i4);

    private native void setupSuperpowered(int i, int i2, int i3, int i4);

    public native boolean endOfPerformanceReached();

    public native void finalizePerformanceNative();

    public native ArrayList<AudioPowerEvent> getAudioPowerEvents();

    public native String getAudioSystemName();

    public native float getBackgroundDuration_seconds();

    public native float getDetectedPitch_MIDI();

    public native String getEncodedNoiseProfile();

    public native float getMaxNoiseRMS();

    public native float getMaxRMS();

    public native float getMaxVULevel_amp();

    public native float getMinRMS();

    public native int getOpenSLStreamVersionInt();

    public native float getSibilanceFreq_Hz();

    public native float getSongPosition_seconds();

    public native int getUserDelayCalibrationFromBackgroundTrack();

    public native float getVULevel_amp();

    public native float getVoicedRMS();

    public native boolean isPlaying();

    public native void prepareForRealTime();

    public native void renderPerformanceToFile(String str);

    public native void rewindRecording();

    public native void setBackgroundLevel_amp(float f);

    public native void setForegroundDelay_ms(float f);

    public native void setForegroundFX(String str);

    public native void setForegroundLevel_amp(float f);

    public native void setForegroundPan(float f);

    public native void setMetaParameters(float f, float f2);

    public native void setMonitoring(boolean z);

    public native void setMonitoringLevel_amp(float f);

    public native void setMonitoringPan(float f);

    public native void setPause(boolean z);

    public native void setSongPosition_seconds(float f);

    public native void start();

    public native void stopAndShutdown();

    public AudioInterface(Context context, String str) throws Exception {
        int i = 0;
        String[] strArr = new String[]{"re201_5.wav", "sf_opera_IR.wav", "blue_plate.wav"};
        int length = strArr.length;
        while (i < length) {
            setContext(ResourceUtils.m19026a(context, "audio/" + strArr[i]).getParent());
            i++;
        }
        m22269a(str);
    }

    public void m22269a(String str) throws Exception {
        SystemType systemType = SystemType.OpenSL;
        int h = DeviceSettings.h();
        float e = DeviceSettings.e();
        int g = DeviceSettings.g();
        int round = Math.round(e);
        if (((float) round) != e) {
            throw new IllegalArgumentException("samplerate must be an integer number of samples per second");
        }
        if (DeviceSettings.l()) {
            Log.a(f20656a, "Setting up audio system");
        } else {
            Log.a(f20656a, "Setting up audio system");
        }
        if (systemType == SystemType.OpenSL) {
            setupOpenSLES(h, round, g, 1, 2, 2, 2, str);
        } else if (systemType == SystemType.SAPA) {
            setupSAPA(round, g, 1, 2);
        } else if (systemType == SystemType.Superpowered) {
            setupSuperpowered(round, g, 1, 2);
        } else {
            throw new InvalidPropertiesFormatException("Unknown system type");
        }
    }

    public OpenSLStreamVersion m22267a() {
        if (getAudioSystemName().startsWith("OpenSL")) {
            return OpenSLStreamVersion.m22283a(getOpenSLStreamVersionInt());
        }
        return OpenSLStreamVersion.UNSPECIFIED;
    }

    public float m22272b() {
        return 0.0f;
    }

    public String m22273c() {
        return Integer.toString(getOpenSLStreamV1Latency());
    }

    public String m22274d() {
        return Integer.toString(getAudioSystemLatency());
    }

    public void m22270a(String str, String str2, String str3, String str4, String str5, Runnable runnable, FailRunnable failRunnable) {
        new SetupPerformanceTask(this, str, str2, str4, str3, str5, runnable, failRunnable).execute(new Void[0]);
    }

    public void m22271a(boolean z, Runnable runnable, Runnable runnable2) {
        new FinalizePerformanceTask(this, z, runnable, runnable2).execute(new Void[0]);
    }

    public int mo6718e() {
        return getSampleRate();
    }

    public void m22268a(float f) {
        Log.b(f20656a, "set master volume");
    }

    public static byte[] readFile(String str) {
        try {
            ZipFile zipFile = new ZipFile(new File(SingApplication.f().getApplicationInfo().sourceDir));
            try {
                InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(str));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[16384];
                while (true) {
                    try {
                        int read = inputStream.read(bArr, 0, bArr.length);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            byteArrayOutputStream.flush();
                            return byteArrayOutputStream.toByteArray();
                        }
                    } catch (IOException e) {
                        return null;
                    }
                }
            } catch (IOException e2) {
                return null;
            }
        } catch (IOException e3) {
            return null;
        }
    }
}
