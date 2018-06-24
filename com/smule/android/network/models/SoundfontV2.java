/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.managers.l10n.LocalizationObject;
import com.smule.android.network.managers.l10n.LocalizationObjectId;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.network.models.SoundfontV2;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@LocalizationObject(a="soundfont")
public class SoundfontV2
implements Parcelable {
    public static final Parcelable.Creator<SoundfontV2> CREATOR = new Parcelable.Creator<SoundfontV2>(){

        public SoundfontV2 a(Parcel parcel) {
            return new SoundfontV2(parcel);
        }

        public SoundfontV2[] a(int n) {
            return new SoundfontV2[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="enabled")
    public boolean enabled;
    @JsonProperty(value="info")
    public String info;
    @JsonProperty(value="name")
    public String name;
    @JsonProperty(value="resources")
    public List<ResourceV2> resources;
    @JsonProperty(value="soundfontId")
    @LocalizationObjectId
    public String soundfontId;
    @JsonProperty(value="ts")
    public long ts;

    public SoundfontV2() {
        this.resources = new ArrayList<ResourceV2>();
    }

    /*
     * Enabled aggressive block sorting
     */
    public SoundfontV2(Parcel parcel) {
        boolean bl = true;
        this.resources = new ArrayList<ResourceV2>();
        this.soundfontId = parcel.readString();
        this.ts = parcel.readLong();
        this.name = parcel.readString();
        this.info = parcel.readString();
        if (parcel.readByte() != 1) {
            bl = false;
        }
        this.enabled = bl;
        parcel.readTypedList(this.resources, ResourceV2.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        String string2 = this.soundfontId == null ? "" : this.soundfontId;
        parcel.writeString(string2);
        parcel.writeLong(this.ts);
        string2 = this.name == null ? "" : this.name;
        parcel.writeString(string2);
        string2 = this.info == null ? "" : this.info;
        parcel.writeString(string2);
        n = this.enabled ? 1 : 0;
        parcel.writeByte((byte)n);
        parcel.writeTypedList(this.resources);
    }

}

