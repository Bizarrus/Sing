/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio;

import com.smule.singandroid.audio.AudioPowerEvent;
import com.smule.singandroid.audio.GlitchCount;
import com.smule.singandroid.audio.OpenSLStreamVersion;
import com.smule.singandroid.audio.exception.InvalidInternalState;
import com.smule.singandroid.audio.exception.JNIError;
import com.smule.singandroid.audio.exception.NativeException;
import com.smule.singandroid.audio.exception.PreconditionsViolated;
import com.smule.singandroid.audio.exception.UninitializedException;
import java.util.List;

class AudioInterface {
    public static final String a = AudioInterface.class.getSimpleName();

    AudioInterface() {
    }

    public static String c() throws UninitializedException {
        return Integer.toString(AudioInterface.getOpenSLStreamV1Latency());
    }

    static native void destroyPerformance();

    private static native int getOpenSLStreamV1Latency() throws UninitializedException;

    private native int getOpenSLStreamVersionInt() throws UninitializedException;

    static native void setContext(String var0, String var1, String var2, String var3, String var4, String var5) throws NativeException;

    public OpenSLStreamVersion a() throws UninitializedException {
        if (this.getAudioSystemName().startsWith("OpenSL")) {
            return OpenSLStreamVersion.a(this.getOpenSLStreamVersionInt());
        }
        return OpenSLStreamVersion.e;
    }

    native void assertUninitialized();

    public float b() throws UninitializedException {
        return 0.0f;
    }

    native void doAnalysis() throws NativeException;

    native boolean endOfPerformanceReached() throws UninitializedException;

    native void finalizePerformanceNative() throws UninitializedException, InvalidInternalState;

    native List<AudioPowerEvent> getAudioPowerEvents();

    native int getAudioSystemLatency() throws UninitializedException;

    native String getAudioSystemName() throws JNIError, UninitializedException;

    native float getBackgroundDuration_seconds() throws UninitializedException;

    native int getBufferSize() throws JNIError, UninitializedException;

    native float getDetectedPitch_MIDI() throws NativeException;

    native float getMaxVULevel_amp() throws UninitializedException;

    native float getNoiseGateThreshold() throws NativeException;

    native float[] getNoiseProfile() throws NativeException;

    native float[] getNoiseStatistics() throws NativeException;

    native GlitchCount getOpenSLStreamV3GlitchEvents() throws UninitializedException, InvalidInternalState;

    native float getPregainDB() throws NativeException;

    native float[] getRMSStatistics() throws NativeException;

    native float[] getRobotVoiceFeatures() throws NativeException;

    native int getSampleRate() throws JNIError, UninitializedException;

    native float getSibilanceFreq_Hz() throws NativeException;

    native float getSongPosition_seconds() throws UninitializedException;

    native float[] getUnvoicedStatistics() throws NativeException;

    native int getUserDelayCalibrationFromBackgroundTrack() throws NativeException;

    native float getVULevel_amp() throws UninitializedException;

    native int getVocalMonitorVersion() throws NativeException;

    native float[] getVoicedStatistics() throws NativeException;

    public native boolean isFXEnabled(String var1) throws NativeException;

    native boolean isPlaying() throws UninitializedException;

    native void prepareForRealTime() throws NativeException;

    native void renderPerformanceToFile(String var1) throws NativeException;

    native void rewindRecording() throws NativeException;

    native void setAutoTuneEnabled(boolean var1) throws NativeException;

    native void setBackgroundLevel_amp(float var1) throws NativeException;

    native void setForegroundDelay_ms(float var1) throws NativeException;

    native int setForegroundFX(String var1) throws NativeException;

    native void setForegroundLevel_amp(float var1) throws NativeException;

    native void setForegroundPan(float var1) throws NativeException;

    native void setHeadphonesUsed(boolean var1) throws NativeException;

    native void setMetaParameters(float var1, float var2) throws NativeException;

    native void setMonitoring(boolean var1) throws NativeException;

    native void setMonitoringFX(String var1) throws NativeException;

    native void setMonitoringLevel_amp(float var1) throws NativeException;

    native void setMonitoringMetaParameters(float var1, float var2) throws NativeException;

    native void setMonitoringPan(float var1) throws NativeException;

    native void setPause(boolean var1) throws UninitializedException, InvalidInternalState, PreconditionsViolated;

    native void setSongPosition_seconds(float var1) throws NativeException;

    native void setupOpenSLES(int var1, int var2, int var3, int var4, int var5, int var6, int var7, String var8) throws NativeException;

    native boolean setupPerformance(String var1, String var2, String var3, String var4, byte[] var5, int var6, int var7) throws NativeException;

    native void setupSAPA(int var1, int var2, int var3, int var4) throws NativeException;

    native void setupSuperpowered(int var1, int var2, int var3, int var4) throws NativeException;

    native void start() throws NativeException;

    native void stopAndShutdown() throws NativeException;

    static enum InternalState {
        a,
        b,
        c,
        d;
        

        private InternalState() {
        }
    }

}

