package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.SmuleApplication;
import com.smule.android.logging.Log;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.PackageInfoUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata implements Parcelable {
    public static final Creator<Metadata> CREATOR = new 1();
    static final String f7111a = Metadata.class.getName();
    @JsonProperty("audioPower")
    public AudioPower audioPower;
    @JsonProperty("audioPowerEvents")
    public ArrayList<AudioPowerEvent> audioPowerEvents;
    @JsonProperty("clientVersion")
    public String clientVersion = PackageInfoUtils.b(SmuleApplication.m7644a());
    @JsonProperty("maxNoiseRMS")
    public Float maxNoiseRMS = Float.valueOf(0.0f);
    @JsonProperty("maxRMS")
    public Float maxRMS = Float.valueOf(0.0f);
    @JsonProperty("minRMS")
    public Float minRMS = Float.valueOf(0.0f);
    @JsonProperty("myParts")
    public Integer myParts = Integer.valueOf(-1);
    @JsonProperty("myPartsSung")
    public Integer myPartsSung = Integer.valueOf(-1);
    @JsonProperty("noiseProfile")
    public String noiseProfile = "";
    @JsonProperty("normalizationScaleFactor")
    public Float normalizationScaleFactor = Float.valueOf(0.0f);
    @JsonProperty("otherParts")
    public Integer otherParts = Integer.valueOf(-1);
    @JsonProperty("otherPartsSung")
    public Integer otherPartsSung = Integer.valueOf(-1);
    @JsonProperty("sibilanceFrequencyHz")
    public Float sibilanceFrequencyHz = Float.valueOf(0.0f);
    @JsonProperty("usePreGain")
    public Boolean usePreGain = Boolean.valueOf(false);
    @JsonProperty("userDelayCalibrationMs")
    public int userDelayCalibrationMs;
    @JsonProperty("visualGainDb")
    public Float visualGainDb = Float.valueOf(0.0f);
    @JsonProperty("voicedRMS")
    public Float voicedRMS = Float.valueOf(0.0f);

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeParcelable(this.audioPower, 0);
        parcel.writeInt(this.userDelayCalibrationMs);
        parcel.writeTypedList(this.audioPowerEvents);
        parcel.writeInt(this.myParts.intValue());
        parcel.writeInt(this.myPartsSung.intValue());
        parcel.writeInt(this.otherParts.intValue());
        parcel.writeInt(this.otherPartsSung.intValue());
        parcel.writeFloat(this.voicedRMS.floatValue());
        parcel.writeFloat(this.minRMS.floatValue());
        parcel.writeFloat(this.maxRMS.floatValue());
        parcel.writeFloat(this.sibilanceFrequencyHz.floatValue());
        parcel.writeString(this.noiseProfile);
        parcel.writeFloat(this.visualGainDb.floatValue());
        parcel.writeFloat(this.normalizationScaleFactor.floatValue());
        parcel.writeFloat(this.maxNoiseRMS.floatValue());
        if (this.usePreGain.booleanValue()) {
            i2 = 1;
        }
        parcel.writeInt(i2);
        parcel.writeString(this.clientVersion);
    }

    public Metadata(Parcel parcel) {
        boolean z;
        this.audioPower = (AudioPower) parcel.readParcelable(AudioPower.class.getClassLoader());
        this.userDelayCalibrationMs = parcel.readInt();
        this.audioPowerEvents = new ArrayList();
        parcel.readTypedList(this.audioPowerEvents, AudioPowerEvent.CREATOR);
        this.myParts = Integer.valueOf(parcel.readInt());
        this.myPartsSung = Integer.valueOf(parcel.readInt());
        this.otherParts = Integer.valueOf(parcel.readInt());
        this.otherPartsSung = Integer.valueOf(parcel.readInt());
        this.voicedRMS = Float.valueOf(parcel.readFloat());
        this.minRMS = Float.valueOf(parcel.readFloat());
        this.maxRMS = Float.valueOf(parcel.readFloat());
        this.sibilanceFrequencyHz = Float.valueOf(parcel.readFloat());
        this.noiseProfile = parcel.readString();
        this.visualGainDb = Float.valueOf(parcel.readFloat());
        this.normalizationScaleFactor = Float.valueOf(parcel.readFloat());
        this.maxNoiseRMS = Float.valueOf(parcel.readFloat());
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.usePreGain = Boolean.valueOf(z);
        this.clientVersion = parcel.readString();
    }

    public static Metadata m8824a(File file) {
        if (file != null && file.exists()) {
            try {
                return (Metadata) JsonUtils.a().readValue(file, Metadata.class);
            } catch (IOException e) {
                Log.m7776e(f7111a, "Error parsing JSON file at " + file.getAbsolutePath());
            }
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return JsonUtils.a(this);
    }
}
