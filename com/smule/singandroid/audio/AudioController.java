/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v4.util.Pair
 *  com.smule.singandroid.utils.ArrayConverter
 *  com.smule.singandroid.utils.Base64
 */
package com.smule.singandroid.audio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import com.smule.android.audio.AudioDefs;
import com.smule.android.audio.GlitchContext;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.ResourceUtils;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.audio.AudioInterface;
import com.smule.singandroid.audio.AudioPowerEvent;
import com.smule.singandroid.audio.AudioSystemStateMachine;
import com.smule.singandroid.audio.AudioUtils;
import com.smule.singandroid.audio.GlitchCount;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.audio.OpenSLStreamVersion;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.exception.StateMachineConfigurationError;
import com.smule.singandroid.audio.core.exception.StateMachineTransitionException;
import com.smule.singandroid.audio.core.state_machine.CommandDispatcher;
import com.smule.singandroid.audio.core.state_machine.CommandListener;
import com.smule.singandroid.audio.core.state_machine.ICommand;
import com.smule.singandroid.audio.core.state_machine.IState;
import com.smule.singandroid.audio.core.state_machine.StateChangeDispatcher;
import com.smule.singandroid.audio.core.state_machine.StateChangeListener;
import com.smule.singandroid.audio.exception.InvalidInternalState;
import com.smule.singandroid.audio.exception.NativeException;
import com.smule.singandroid.audio.exception.UninitializedException;
import com.smule.singandroid.utils.ArrayConverter;
import com.smule.singandroid.utils.Base64;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AudioController
extends AudioSystemStateMachine
implements CommandListener,
StateChangeListener {
    public static final String a = AudioController.class.getSimpleName();
    public static final List<String> b = Arrays.asList("re201_5.wav", "sf_opera_IR.wav", "blue_plate.wav", "richard_quad.wav");
    public static final List<String> c = Arrays.asList("Presets.json", "OTAPresets.json", "VocalMonitor.json");
    private final AudioInterface f;
    private AudioObserver g;

    public AudioController(AudioObserver audioObserver, Context context) throws IOException, NativeException {
        this(audioObserver, AudioController.a(context), AudioController.b(context), AudioController.c(context), new AudioInterface());
    }

    public AudioController(AudioObserver object, ResourceFetcher object2, ResourceFetcher object3, ResourceFetcher object4, AudioInterface audioInterface) throws NativeException, IOException, StateMachineConfigurationError, IllegalArgumentException {
        this.g = object;
        this.a(new CommandDispatcher(Arrays.asList(new AudioSystemStateMachine.NonRealTimeCommandLogger(), this)));
        this.a(new StateChangeDispatcher(Arrays.asList(new AudioSystemStateMachine.TransitionLogger(), this)));
        if (audioInterface == null) {
            throw new IllegalArgumentException("audioInterface cannot be null");
        }
        this.f = audioInterface;
        object = AudioController.a((ResourceFetcher)object2, b);
        object3 = AudioController.a((ResourceFetcher)object3, c);
        object2 = object4.a("RobotVoice.bin").getAbsolutePath();
        object3 = AudioUtils.a((String)object3);
        object4 = new SingServerValues();
        AudioInterface.setContext((String)object, (String)object3, (String)object2, object4.m(), object4.l(), object4.k());
    }

    public static ResourceFetcher a(Context context) {
        return new ResourceUtilAssetFetcher(context, "audio/", false);
    }

    @NonNull
    private static String a(@NonNull ResourceFetcher object, @NonNull List<String> object2) throws IOException, NullPointerException, IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException("You must give me a real ResourceFetcher");
        }
        if (object2 == null || object2.size() <= 0) {
            Log.d(a, "There were no files to unpack. I'm returning the base search directory from the fetcher");
            return object.a();
        }
        ArrayList<File> arrayList = new ArrayList<File>(object2.size());
        object2 = object2.iterator();
        while (object2.hasNext()) {
            arrayList.add(object.a((String)object2.next()));
        }
        String object32 = ((File)arrayList.get(0)).getParent();
        object2 = object32;
        if (object32 == null) {
            object2 = object.a();
        }
        if (object2 == null) {
            throw new FileNotFoundException("I can't explain it, but the files directory seems to not exist");
        }
        for (File file : arrayList) {
            if (file == null) {
                throw new FileNotFoundException("Resource File doesn't exist");
            }
            if (object2.equals(file.getParent())) continue;
            throw new FileNotFoundException("Invalid precondition: not all resources are in the same location");
        }
        return object2;
    }

    private void a(SystemType systemType, int n, int n2, int n3, int n4, int n5, int n6, int n7) throws NativeException, InvalidPropertiesFormatException {
        Log.a(AudioInterface.a, "Setting up audio system");
        if (systemType == SystemType.a) {
            if (n != 4) {
                Log.c(a, "Setting up OpenSLES.");
                this.f.setupOpenSLES(n, n3, n4, n5, n6, n7, n2, null);
                return;
            }
            Log.c(a, "Setting up SuperPowered.");
            this.f.setupSuperpowered(n3, n4, n5, n6);
            return;
        }
        if (systemType == SystemType.b) {
            Log.c(a, "Setting up SAPA.");
            this.f.setupSAPA(n3, n4, n5, n6);
            return;
        }
        throw new InvalidPropertiesFormatException("Unknown system type");
    }

    public static ResourceFetcher b(Context context) {
        return new ResourceUtilAssetFetcher(context, "effects/", true);
    }

    private void b(@NonNull DeviceSettings deviceSettings) throws NativeException, InvalidPropertiesFormatException {
        SystemType systemType = SystemType.a;
        int n = deviceSettings.i();
        float f = deviceSettings.d();
        int n2 = deviceSettings.f();
        int n3 = Math.round(f);
        if ((float)n3 != f) {
            throw new IllegalArgumentException("samplerate must be an integer number of samples per second");
        }
        if (deviceSettings.l()) {
            // empty if block
        }
        this.a(systemType, n, 2, n3, n2, 1, 2, 2);
    }

    public static ResourceFetcher c(Context context) {
        return new ResourceUtilAssetFetcher(context, "flatbuffers/", false);
    }

    public static String l() throws StateMachineTransitionException, UninitializedException {
        return AudioInterface.c();
    }

    public Future<Void> a(@NonNull String string2, ExecutorService executorService, AudioSystemStateMachine.CommandDelegate commandDelegate) throws NativeException, StateMachineTransitionException {
        AudioSystemStateMachine.CommandWorker<Void, String, NativeException> commandWorker = new AudioSystemStateMachine.CommandWorker<Void, String, NativeException>(){

            @Nullable
            @Override
            Void a(@Nullable String string2) throws NativeException {
                AudioController.this.f.renderPerformanceToFile(string2);
                return null;
            }
        };
        return this.a(AudioSystemStateMachine.Command.m, commandWorker, string2, AudioSystemStateMachine.Command.n, executorService, commandDelegate);
    }

    public void a() throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.a, new AudioSystemStateMachine.BasicWorker<UninitializedException>(){

            @Override
            void a() throws UninitializedException {
                AudioController.this.f.assertUninitialized();
            }
        });
    }

    public void a(final float f) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.r, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setSongPosition_seconds(f);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(int n, GlitchContext glitchContext, AudioDefs.HeadphonesType headphonesType, Byte by) throws StateMachineTransitionException, NativeException {
        boolean bl = true;
        if (this.j() == OpenSLStreamVersion.c || this.j() == OpenSLStreamVersion.d) {
            int n2;
            int n3;
            void var4_6;
            SingServerValues singServerValues = new SingServerValues();
            Object object = new DeviceSettings();
            try {
                n2 = UserManager.a().a(3809728, 99);
                n3 = singServerValues.D();
                n2 = n2 + 1 <= n3 ? 1 : 0;
            }
            catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                Log.e(a, "Unable to generate a hash.");
                n2 = 0;
            }
            if (n2 == 0) {
                if (!object.j()) return;
            }
            object = this.x();
            n2 = object.getInputGlitches();
            n3 = object.getOutputGlitches();
            int n4 = object.getFileGlitches();
            int n5 = object.getInputCalls();
            int n6 = object.getOutputCalls();
            int n7 = object.getIoWarmup();
            int n8 = object.getPlaybackGlitches();
            if (by != null) {
                if (by.byteValue() != 1) {
                    bl = false;
                }
                Boolean bl2 = bl;
            } else {
                Object var4_7 = null;
            }
            EventLogger2.a(n, glitchContext, headphonesType, n2, n3, n4, n5, n6, n7, n8, (Boolean)var4_6);
        }
    }

    public void a(final @NonNull Pair<Float, Float> pair) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setMetaParameters(((Float)pair.first).floatValue(), ((Float)pair.second).floatValue());
            }
        });
    }

    public void a(final DeviceSettings deviceSettings) throws Exception {
        this.a(AudioSystemStateMachine.Command.b, new AudioSystemStateMachine.BasicWorker<Exception>(){

            @Override
            void a() throws InvalidPropertiesFormatException, NativeException {
                AudioController.this.b(deviceSettings);
            }
        });
    }

    public void a(DeviceSettings deviceSettings, final String string2, final String string3, final String string4, final String string5, final byte[] arrby) throws StateMachineTransitionException, NativeException {
        final int n = deviceSettings.g();
        final int n2 = deviceSettings.h();
        this.a(AudioSystemStateMachine.Command.c, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setupPerformance(string2, string3, string4, string5, arrby, n, n2);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(@NonNull ICommand iCommand, @NonNull IError iError) {
        block5 : {
            block4 : {
                if (this.g == null) break block4;
                if (iCommand == AudioSystemStateMachine.Command.b) {
                    this.g.a(iError);
                    return;
                }
                if (iCommand == AudioSystemStateMachine.Command.c) break block5;
            }
            return;
        }
        this.g.b(iError);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(@NonNull IState iState, @NonNull IState iState2, @Nullable IError iError) {
        block5 : {
            block4 : {
                if (this.g == null) break block4;
                if (iState2 == AudioSystemStateMachine.State.f && iState != AudioSystemStateMachine.State.f) {
                    this.g.t();
                    return;
                }
                if (iState2 == AudioSystemStateMachine.State.k && iState != AudioSystemStateMachine.State.k) break block5;
            }
            return;
        }
        this.g.a((AudioSystemStateMachine.State)iState, (AudioSystemStateMachine.Result)iError);
    }

    public void a(final boolean bl) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setHeadphonesUsed(bl);
            }
        });
    }

    public boolean a(@NonNull String string2) throws NativeException {
        return this.f.isFXEnabled(string2);
    }

    public void b() throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.f, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            void a() throws NativeException {
                AudioController.this.f.start();
            }
        });
    }

    public void b(float f) {
        Log.d(a, "I'm not actually doing anything with this setMasterVolume method " + Float.toString(f));
    }

    public void b(final @NonNull Pair<Float, Float> pair) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setMonitoringMetaParameters(((Float)pair.first).floatValue(), ((Float)pair.second).floatValue());
            }
        });
    }

    public void b(final @NonNull String string2) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            void a() throws NativeException {
                AudioController.this.f.setMonitoringFX(string2);
            }
        });
    }

    public void b(final boolean bl) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.r, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setAutoTuneEnabled(bl);
            }
        });
    }

    public int c(final @NonNull String string2) throws StateMachineTransitionException, NativeException {
        return (Integer)this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.CommandWorker<Integer, Void, NativeException>(){

            @Nullable
            @Override
            Integer a(@Nullable Void void_) throws NativeException {
                return AudioController.this.f.setForegroundFX(string2);
            }
        });
    }

    public void c() throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.j, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            void a() throws NativeException {
                AudioController.this.f.stopAndShutdown();
            }
        });
    }

    public void c(final float f) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setMonitoringLevel_amp(f);
            }
        });
    }

    public void c(final boolean bl) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setMonitoring(bl);
            }
        });
    }

    public void d() throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.e, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            void a() throws NativeException {
                AudioController.this.f.prepareForRealTime();
            }
        });
    }

    public void d(final float f) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setMonitoringPan(f);
            }
        });
    }

    public void e() throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.g, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            void a() throws NativeException {
                if (AudioController.this.B() != AudioSystemStateMachine.State.l) {
                    AudioController.this.f.setPause(false);
                }
            }
        });
    }

    public void e(final float f) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.r, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setForegroundDelay_ms(f);
            }
        });
    }

    public void f() throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.h, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            void a() throws NativeException {
                if (AudioController.this.B() == AudioSystemStateMachine.State.l) {
                    AudioController.this.f.setPause(true);
                }
            }
        });
    }

    public void f(final float f) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setForegroundLevel_amp(f);
            }
        });
    }

    public void g(final float f) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.r, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setForegroundPan(f);
            }
        });
    }

    public boolean g() throws StateMachineTransitionException, UninitializedException {
        return (Boolean)this.a(AudioSystemStateMachine.Command.p, new AudioSystemStateMachine.CommandWorker<Boolean, Void, UninitializedException>(){

            @Nullable
            @Override
            Boolean a(Void void_) throws UninitializedException {
                return AudioController.this.f.isPlaying();
            }
        });
    }

    public int h() throws StateMachineTransitionException, UninitializedException {
        return (Integer)this.a(AudioSystemStateMachine.Command.o, new AudioSystemStateMachine.CommandWorker<Integer, Void, UninitializedException>(){

            @NonNull
            @Override
            Integer a(Void void_) throws UninitializedException {
                return AudioController.this.f.getSampleRate();
            }
        });
    }

    public void h(final float f) throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.t, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.setBackgroundLevel_amp(f);
            }
        });
    }

    public String i() throws StateMachineTransitionException, UninitializedException {
        return (String)this.a(AudioSystemStateMachine.Command.o, new AudioSystemStateMachine.CommandWorker<String, Void, UninitializedException>(){

            @NonNull
            @Override
            String a(Void void_) throws UninitializedException {
                return AudioController.this.f.getAudioSystemName();
            }
        });
    }

    public OpenSLStreamVersion j() throws StateMachineTransitionException, UninitializedException {
        return (OpenSLStreamVersion)((Object)this.a(AudioSystemStateMachine.Command.o, new AudioSystemStateMachine.CommandWorker<OpenSLStreamVersion, Void, UninitializedException>(){

            @NonNull
            @Override
            OpenSLStreamVersion a(Void void_) throws UninitializedException {
                return AudioController.this.f.a();
            }
        }));
    }

    public String k() throws StateMachineTransitionException, UninitializedException {
        return (String)this.a(AudioSystemStateMachine.Command.o, new AudioSystemStateMachine.CommandWorker<String, Void, UninitializedException>(){

            @NonNull
            @Override
            String a(Void void_) throws UninitializedException {
                return Integer.toString(AudioController.this.f.getAudioSystemLatency());
            }
        });
    }

    public float m() throws StateMachineTransitionException, UninitializedException {
        return ((Float)this.a(AudioSystemStateMachine.Command.o, new AudioSystemStateMachine.CommandWorker<Float, Void, UninitializedException>(){

            @NonNull
            @Override
            Float a(Void void_) throws UninitializedException {
                return Float.valueOf(AudioController.this.f.b());
            }
        })).floatValue();
    }

    public void n() throws StateMachineTransitionException {
        synchronized (this) {
            AudioSystemStateMachine.Command command = AudioSystemStateMachine.Command.k;
            this.a(command);
            AudioInterface.destroyPerformance();
            this.b(command, AudioSystemStateMachine.Result.a);
            return;
        }
    }

    public void o() throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.l, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws UninitializedException, InvalidInternalState {
                AudioController.this.f.finalizePerformanceNative();
            }
        });
    }

    public void p() throws StateMachineTransitionException, NativeException {
        this.a(AudioSystemStateMachine.Command.r, new AudioSystemStateMachine.BasicWorker<NativeException>(){

            @Override
            public void a() throws NativeException {
                AudioController.this.f.rewindRecording();
            }
        });
    }

    public float q() throws StateMachineTransitionException, UninitializedException {
        return ((Float)this.a(AudioSystemStateMachine.Command.s, new AudioSystemStateMachine.CommandWorker<Float, Void, UninitializedException>(){

            @NonNull
            @Override
            Float a(Void void_) throws UninitializedException {
                return Float.valueOf(AudioController.this.f.getSongPosition_seconds());
            }
        })).floatValue();
    }

    public boolean r() throws StateMachineTransitionException, UninitializedException {
        return (Boolean)this.a(AudioSystemStateMachine.Command.s, new AudioSystemStateMachine.CommandWorker<Boolean, Void, UninitializedException>(){

            @NonNull
            @Override
            Boolean a(Void void_) throws UninitializedException {
                return AudioController.this.f.endOfPerformanceReached();
            }
        });
    }

    public float s() throws StateMachineTransitionException, UninitializedException {
        return ((Float)this.a(AudioSystemStateMachine.Command.p, new AudioSystemStateMachine.GetWorker<Float, UninitializedException>(){

            @NonNull
            Float a() throws UninitializedException {
                return Float.valueOf(AudioController.this.f.getBackgroundDuration_seconds());
            }

            @NonNull
            @Override
            /* synthetic */ Object b() throws Exception {
                return this.a();
            }
        })).floatValue();
    }

    public float t() throws StateMachineTransitionException, NativeException {
        return ((Float)this.a(AudioSystemStateMachine.Command.s, new AudioSystemStateMachine.GetWorker<Float, NativeException>(){

            @NonNull
            Float a() throws NativeException {
                return Float.valueOf(AudioController.this.f.getDetectedPitch_MIDI());
            }

            @NonNull
            @Override
            /* synthetic */ Object b() throws Exception {
                return this.a();
            }
        })).floatValue();
    }

    public float u() throws StateMachineTransitionException, UninitializedException {
        return ((Float)this.a(AudioSystemStateMachine.Command.s, new AudioSystemStateMachine.GetWorker<Float, UninitializedException>(){

            @NonNull
            Float a() throws UninitializedException {
                return Float.valueOf(AudioController.this.f.getMaxVULevel_amp());
            }

            @NonNull
            @Override
            /* synthetic */ Object b() throws Exception {
                return this.a();
            }
        })).floatValue();
    }

    public float v() throws StateMachineTransitionException, UninitializedException {
        return ((Float)this.a(AudioSystemStateMachine.Command.s, new AudioSystemStateMachine.GetWorker<Float, UninitializedException>(){

            @NonNull
            Float a() throws UninitializedException {
                return Float.valueOf(AudioController.this.f.getVULevel_amp());
            }

            @NonNull
            @Override
            /* synthetic */ Object b() throws Exception {
                return this.a();
            }
        })).floatValue();
    }

    public Metadata w() throws StateMachineTransitionException, NativeException {
        return (Metadata)this.a(AudioSystemStateMachine.Command.q, new AudioSystemStateMachine.GetWorker<Metadata, NativeException>(){

            /*
             * Enabled aggressive block sorting
             */
            @NonNull
            Metadata a() throws NativeException {
                Metadata metadata = new Metadata();
                Log.c(AudioController.a, "Getting performance statistics from the audio engine");
                AudioController.this.f.doAnalysis();
                String string2 = Base64.a((byte[])ArrayConverter.a((float[])AudioController.this.f.getNoiseProfile()));
                float[] arrf = AudioController.this.f.getNoiseStatistics();
                if (arrf.length == 4) {
                    metadata.minNoiseRMS = Float.valueOf(arrf[0]);
                    metadata.maxNoiseRMS = Float.valueOf(arrf[1]);
                    metadata.avgNoiseRMS = Float.valueOf(arrf[2]);
                    metadata.medianNoiseRMS = Float.valueOf(arrf[3]);
                } else {
                    Log.e(AudioController.a, "noise metadata incorrect size.");
                }
                if ((arrf = AudioController.this.f.getVoicedStatistics()).length == 4) {
                    metadata.minVoicedRMS = Float.valueOf(arrf[0]);
                    metadata.maxVoicedRMS = Float.valueOf(arrf[1]);
                    metadata.voicedRMS = Float.valueOf(arrf[2]);
                    metadata.medianVoicedRMS = Float.valueOf(arrf[3]);
                } else {
                    Log.e(AudioController.a, "voiced metadata incorrect size.");
                }
                if ((arrf = AudioController.this.f.getRMSStatistics()).length == 4) {
                    metadata.minRMS = Float.valueOf(arrf[0]);
                    metadata.maxRMS = Float.valueOf(arrf[1]);
                    metadata.avgRMS = Float.valueOf(arrf[2]);
                    metadata.medianRMS = Float.valueOf(arrf[3]);
                } else {
                    Log.e(AudioController.a, "rms metadata incorrect size.");
                }
                if ((arrf = AudioController.this.f.getUnvoicedStatistics()).length == 4) {
                    metadata.minUnvoicedRMS = Float.valueOf(arrf[0]);
                    metadata.maxUnvoicedRMS = Float.valueOf(arrf[1]);
                    metadata.avgUnvoicedRMS = Float.valueOf(arrf[2]);
                    metadata.medianUnvoicedRMS = Float.valueOf(arrf[3]);
                } else {
                    Log.e(AudioController.a, "unvoiced metadata incorrect size.");
                }
                metadata.sibilanceFrequencyHz = Float.valueOf(AudioController.this.f.getSibilanceFreq_Hz());
                metadata.noiseProfile = string2;
                metadata.audioPowerEvents = AudioController.this.f.getAudioPowerEvents();
                metadata.noiseGateThreshold = Float.valueOf(AudioController.this.f.getNoiseGateThreshold());
                metadata.vocalMonitorVersion = AudioController.this.f.getVocalMonitorVersion();
                metadata.pregainDB = Float.valueOf(AudioController.this.f.getPregainDB());
                metadata.b = AudioController.this.f.getRobotVoiceFeatures();
                return metadata;
            }

            @NonNull
            @Override
            /* synthetic */ Object b() throws Exception {
                return this.a();
            }
        });
    }

    public GlitchCount x() throws StateMachineTransitionException, NativeException {
        return (GlitchCount)this.a(AudioSystemStateMachine.Command.o, new AudioSystemStateMachine.GetWorker<GlitchCount, NativeException>(){

            @NonNull
            GlitchCount a() throws NativeException {
                return AudioController.this.f.getOpenSLStreamV3GlitchEvents();
            }

            @NonNull
            @Override
            /* synthetic */ Object b() throws Exception {
                return this.a();
            }
        });
    }

    public static interface AudioObserver {
        public void a(AudioSystemStateMachine.State var1, AudioSystemStateMachine.Result var2);

        public void a(IError var1);

        public void b(IError var1);

        public void t();
    }

    public static abstract class FailRunnable
    implements Runnable {
    }

    public static interface ResourceFetcher {
        @NonNull
        public File a(@NonNull String var1) throws IOException;

        @NonNull
        public String a();
    }

    private static class ResourceUtilAssetFetcher
    implements ResourceFetcher {
        @NonNull
        final Context a;
        @NonNull
        final String b;
        final boolean c;

        ResourceUtilAssetFetcher(@NonNull Context context, @NonNull String string2, boolean bl) {
            this.a = context;
            this.b = string2;
            this.c = bl;
        }

        @NonNull
        @Override
        public File a(@NonNull String string2) throws NullPointerException, IOException {
            return ResourceUtils.a(this.a, this.b + string2, this.c);
        }

        @NonNull
        @Override
        public String a() {
            return this.a.getFilesDir().getAbsolutePath();
        }
    }

    public static enum SystemType {
        a,
        b;
        

        private SystemType() {
        }
    }

}

