/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream
 *  com.google.flatbuffers.FlatBufferBuilder
 *  com.google.flatbuffers.Table
 */
package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import com.smule.AV.RobotVoice.Features;
import com.smule.AV.RobotVoice.FlattenedFeatures;
import com.smule.SmuleApplication;
import com.smule.android.logging.Log;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.PackageInfoUtils;
import com.smule.android.utils.ResourceUtils;
import com.smule.singandroid.audio.ALYCEMetadata;
import com.smule.singandroid.audio.AVComposition;
import com.smule.singandroid.audio.AudioPower;
import com.smule.singandroid.audio.AudioPowerEvent;
import com.smule.singandroid.audio.DeviceData;
import com.smule.singandroid.audio.Metadata;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

@JsonIgnoreProperties(ignoreUnknown=true, value={"robotVoiceFeatures", "robotVoiceClassification"})
public class Metadata
implements Parcelable {
    public static final Parcelable.Creator<Metadata> CREATOR;
    static final String a;
    @JsonProperty(value="alyce")
    public ALYCEMetadata alyceMetadata;
    @JsonProperty
    public AudioPower audioPower;
    @JsonProperty
    public List<AudioPowerEvent> audioPowerEvents;
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    public Boolean autoTuneEnabled = null;
    @JsonProperty
    public List<AVComposition> avComposition;
    @JsonProperty
    public Float avgNoiseRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float avgRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float avgUnvoicedRMS = Float.valueOf(0.0f);
    public float[] b = null;
    public Byte c = null;
    @JsonProperty
    public String clientVersion = PackageInfoUtils.b(SmuleApplication.a());
    @JsonProperty
    public DeviceData deviceData = null;
    @JsonProperty
    public Float maxNoiseRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float maxRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float maxUnvoicedRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float maxVoicedRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float medianNoiseRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float medianRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float medianUnvoicedRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float medianVoicedRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float minNoiseRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float minRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float minUnvoicedRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float minVoicedRMS = Float.valueOf(0.0f);
    @JsonProperty
    public Float noiseGateThreshold = Float.valueOf(0.0f);
    @JsonProperty
    public String noiseProfile = "";
    @JsonProperty
    public Float normalizationScaleFactor = Float.valueOf(0.0f);
    @JsonProperty
    public Float pregainDB = Float.valueOf(0.0f);
    @JsonProperty
    public String renderedSeedVersion = "0";
    @JsonProperty
    public Float sibilanceFrequencyHz = Float.valueOf(0.0f);
    @JsonProperty
    public Boolean usePreGain = false;
    @JsonProperty
    public int userDelayCalibrationMs;
    @JsonProperty
    public Float visualGainDb = Float.valueOf(0.0f);
    @JsonProperty
    public Integer vocalMonitorVersion = 0;
    @JsonProperty
    public Float voicedRMS = Float.valueOf(0.0f);

    static {
        a = Metadata.class.getName();
        CREATOR = new Parcelable.Creator<Metadata>(){

            public Metadata a(Parcel parcel) {
                return new Metadata(parcel);
            }

            public Metadata[] a(int n) {
                return new Metadata[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public Metadata() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public Metadata(Parcel parcel) {
        this.audioPower = (AudioPower)parcel.readParcelable(AudioPower.class.getClassLoader());
        this.userDelayCalibrationMs = parcel.readInt();
        this.audioPowerEvents = new ArrayList<AudioPowerEvent>();
        parcel.readTypedList(this.audioPowerEvents, AudioPowerEvent.CREATOR);
        this.voicedRMS = Float.valueOf(parcel.readFloat());
        this.minRMS = Float.valueOf(parcel.readFloat());
        this.maxRMS = Float.valueOf(parcel.readFloat());
        this.sibilanceFrequencyHz = Float.valueOf(parcel.readFloat());
        this.noiseProfile = parcel.readString();
        this.visualGainDb = Float.valueOf(parcel.readFloat());
        this.normalizationScaleFactor = Float.valueOf(parcel.readFloat());
        this.maxNoiseRMS = Float.valueOf(parcel.readFloat());
        boolean bl = parcel.readInt() == 1;
        this.usePreGain = bl;
        this.clientVersion = parcel.readString();
        this.alyceMetadata = (ALYCEMetadata)parcel.readParcelable(ALYCEMetadata.class.getClassLoader());
        this.deviceData = (DeviceData)parcel.readParcelable(DeviceData.class.getClassLoader());
        this.noiseGateThreshold = Float.valueOf(parcel.readFloat());
        this.vocalMonitorVersion = parcel.readInt();
        this.pregainDB = Float.valueOf(parcel.readFloat());
        this.avgRMS = Float.valueOf(parcel.readFloat());
        this.medianRMS = Float.valueOf(parcel.readFloat());
        this.minNoiseRMS = Float.valueOf(parcel.readFloat());
        this.avgNoiseRMS = Float.valueOf(parcel.readFloat());
        this.medianNoiseRMS = Float.valueOf(parcel.readFloat());
        this.minVoicedRMS = Float.valueOf(parcel.readFloat());
        this.maxVoicedRMS = Float.valueOf(parcel.readFloat());
        this.medianVoicedRMS = Float.valueOf(parcel.readFloat());
        this.minUnvoicedRMS = Float.valueOf(parcel.readFloat());
        this.maxUnvoicedRMS = Float.valueOf(parcel.readFloat());
        this.avgUnvoicedRMS = Float.valueOf(parcel.readFloat());
        this.medianUnvoicedRMS = Float.valueOf(parcel.readFloat());
        this.renderedSeedVersion = parcel.readString();
        this.avComposition = new ArrayList<AVComposition>();
        parcel.readTypedList(this.avComposition, AVComposition.CREATOR);
        this.autoTuneEnabled = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        this.b = parcel.createFloatArray();
        this.c = (Byte)parcel.readValue(Integer.class.getClassLoader());
    }

    /*
     * Enabled aggressive block sorting
     */
    private static int a(@NonNull FlatBufferBuilder flatBufferBuilder, @Nullable String object, @Nullable float[] arrf, @Nullable Byte by) {
        Integer n = null;
        object = object != null ? Integer.valueOf(flatBufferBuilder.a((CharSequence)object)) : null;
        if (arrf != null || by != null) {
            n = Metadata.a(flatBufferBuilder, arrf, by);
        }
        com.smule.AV.Metadata.a(flatBufferBuilder);
        if (object != null) {
            com.smule.AV.Metadata.a(flatBufferBuilder, object.intValue());
        }
        if (n != null) {
            com.smule.AV.Metadata.b(flatBufferBuilder, n);
        }
        return com.smule.AV.Metadata.b(flatBufferBuilder);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static int a(FlatBufferBuilder flatBufferBuilder, @Nullable float[] object, @Nullable Byte by) {
        object = object == null ? null : Integer.valueOf(FlattenedFeatures.a(flatBufferBuilder, (float[])object));
        Features.a(flatBufferBuilder);
        if (object != null) {
            Features.a(flatBufferBuilder, object.intValue());
            Features.a(flatBufferBuilder, 1);
        }
        if (by != null) {
            Features.b(flatBufferBuilder, by.byteValue());
        }
        return Features.b(flatBufferBuilder);
    }

    @NonNull
    private static com.smule.AV.Metadata a(ByteBuffer byteBuffer) throws  {
        byte[] arrby = new byte[byteBuffer.capacity() - 4];
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.get(arrby);
        int n = byteBuffer.getInt();
        CRC32 cRC32 = new CRC32();
        cRC32.update(arrby);
        long l = cRC32.getValue();
        if (l != (long)n) {
            throw new IOException("Data fails checksum verification. Provided " + n + ". Calculated " + l){};
        }
        byteBuffer.rewind();
        return com.smule.AV.Metadata.a(byteBuffer);
    }

    @NonNull
    public static Metadata a(@Nullable File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException("json file seems to not exist");
        }
        return (Metadata)JsonUtils.a().readValue(file, Metadata.class);
    }

    @NonNull
    private static ByteBuffer a(@Nullable String arrby, @Nullable float[] object, @Nullable Byte comparable) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        flatBufferBuilder.g(Metadata.a(flatBufferBuilder, (String)arrby, (float[])object, comparable));
        arrby = flatBufferBuilder.f();
        object = new CRC32();
        object.update(arrby, 0, arrby.length);
        comparable = ByteBuffer.allocate(arrby.length + 4);
        comparable.order(ByteOrder.LITTLE_ENDIAN);
        comparable.put(arrby, 0, arrby.length);
        comparable.putInt((int)object.getValue());
        return comparable;
    }

    /*
     * Enabled aggressive block sorting
     */
    @NonNull
    public static Metadata b(@Nullable File object) throws IOException {
        if (object == null || !object.exists()) {
            throw new FileNotFoundException("metadata file seems to not exist");
        }
        Table table = Metadata.a(Metadata.d((File)object));
        object = table.a();
        table = table.b();
        object = object == null ? new Metadata() : (Metadata)JsonUtils.a().readValue((String)object, Metadata.class);
        if (table != null) {
            object.c = Byte.valueOf(table.b());
            if (table.a() != 1) {
                Log.d(a, "Ignoring structured features because Metadata.java doesn't know how to flatten them");
                return object;
            }
            if ((table = (FlattenedFeatures)table.a(new FlattenedFeatures())) != null) {
                object.b = table.a().asFloatBuffer().array();
            }
        }
        return object;
    }

    @NonNull
    public static Metadata c(@Nullable File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException("json file seems to not exist");
        }
        if (file.getName().endsWith(".bin")) {
            return Metadata.b(file);
        }
        return Metadata.a(file);
    }

    @NonNull
    private ByteBuffer c() {
        return Metadata.a(this.toString(), this.b, this.c);
    }

    @NonNull
    private static ByteBuffer d(@NonNull File file) throws IOException {
        return (ByteBuffer)ResourceUtils.a(new FileInputStream(file), new ResourceUtils.Functional<ByteBuffer, FileInputStream, IOException>(){

            public ByteBuffer a(FileInputStream fileInputStream) throws IOException {
                return (ByteBuffer)ResourceUtils.a(fileInputStream.getChannel(), new ResourceUtils.Functional<ByteBuffer, java.nio.channels.FileChannel, IOException>(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public ByteBuffer a(java.nio.channels.FileChannel fileChannel) throws IOException {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[(int)fileChannel.size()]);
                        fileChannel.read(byteBuffer);
                        byteBuffer.flip();
                        return byteBuffer;
                    }
                });
            }
        });
    }

    @NonNull
    public InputStream a() {
        ByteBuffer byteBuffer = this.c();
        byteBuffer.flip();
        return new ByteBufferBackedInputStream(byteBuffer.asReadOnlyBuffer());
    }

    @NonNull
    public byte[] b() {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        flatBufferBuilder.g(Metadata.a(flatBufferBuilder, this.toString(), this.b, this.c));
        return flatBufferBuilder.f();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return JsonUtils.a(this);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.audioPower, 0);
        parcel.writeInt(this.userDelayCalibrationMs);
        parcel.writeTypedList(this.audioPowerEvents);
        parcel.writeFloat(this.voicedRMS.floatValue());
        parcel.writeFloat(this.minRMS.floatValue());
        parcel.writeFloat(this.maxRMS.floatValue());
        parcel.writeFloat(this.sibilanceFrequencyHz.floatValue());
        parcel.writeString(this.noiseProfile);
        parcel.writeFloat(this.visualGainDb.floatValue());
        parcel.writeFloat(this.normalizationScaleFactor.floatValue());
        parcel.writeFloat(this.maxNoiseRMS.floatValue());
        n = this.usePreGain != false ? 1 : 0;
        parcel.writeInt(n);
        parcel.writeString(this.clientVersion);
        parcel.writeParcelable((Parcelable)this.alyceMetadata, 0);
        parcel.writeParcelable((Parcelable)this.deviceData, 0);
        parcel.writeFloat(this.noiseGateThreshold.floatValue());
        parcel.writeInt(this.vocalMonitorVersion.intValue());
        parcel.writeFloat(this.pregainDB.floatValue());
        parcel.writeFloat(this.avgRMS.floatValue());
        parcel.writeFloat(this.medianRMS.floatValue());
        parcel.writeFloat(this.minNoiseRMS.floatValue());
        parcel.writeFloat(this.avgNoiseRMS.floatValue());
        parcel.writeFloat(this.medianNoiseRMS.floatValue());
        parcel.writeFloat(this.minVoicedRMS.floatValue());
        parcel.writeFloat(this.maxVoicedRMS.floatValue());
        parcel.writeFloat(this.medianVoicedRMS.floatValue());
        parcel.writeFloat(this.minUnvoicedRMS.floatValue());
        parcel.writeFloat(this.maxUnvoicedRMS.floatValue());
        parcel.writeFloat(this.avgUnvoicedRMS.floatValue());
        parcel.writeFloat(this.medianUnvoicedRMS.floatValue());
        parcel.writeString(this.renderedSeedVersion);
        parcel.writeTypedList(this.avComposition);
        parcel.writeValue((Object)this.autoTuneEnabled);
        parcel.writeFloatArray(this.b);
        parcel.writeValue((Object)this.c);
    }

}

