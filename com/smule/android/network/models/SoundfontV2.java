package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.managers.l10n.LocalizationObject;
import com.smule.android.network.managers.l10n.LocalizationObjectId;
import java.util.ArrayList;
import java.util.List;

@LocalizationObject(a = "soundfont")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoundfontV2 implements Parcelable {
    public static final Creator<SoundfontV2> CREATOR = new 1();
    @JsonProperty("enabled")
    public boolean enabled;
    @JsonProperty("info")
    public String info;
    @JsonProperty("name")
    public String name;
    @JsonProperty("resources")
    public List<ResourceV2> resources = new ArrayList();
    @JsonProperty("soundfontId")
    @LocalizationObjectId
    public String soundfontId;
    @JsonProperty("ts")
    public long ts;

    public SoundfontV2(Parcel parcel) {
        boolean z = true;
        this.soundfontId = parcel.readString();
        this.ts = parcel.readLong();
        this.name = parcel.readString();
        this.info = parcel.readString();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.enabled = z;
        parcel.readTypedList(this.resources, ResourceV2.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.soundfontId == null ? "" : this.soundfontId);
        parcel.writeLong(this.ts);
        parcel.writeString(this.name == null ? "" : this.name);
        parcel.writeString(this.info == null ? "" : this.info);
        parcel.writeByte((byte) (this.enabled ? 1 : 0));
        parcel.writeTypedList(this.resources);
    }

    public int describeContents() {
        return 0;
    }
}
