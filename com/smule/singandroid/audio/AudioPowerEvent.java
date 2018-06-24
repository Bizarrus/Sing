/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable;

public class AudioPowerEvent
implements Parcelable {
    public static final Parcelable.Creator<AudioPowerEvent> CREATOR = new Parcelable.Creator<AudioPowerEvent>(){

        public AudioPowerEvent a(Parcel parcel) {
            return new AudioPowerEvent(parcel);
        }

        public AudioPowerEvent[] a(int n) {
            return new AudioPowerEvent[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    public boolean isOn;
    public float offset;

    public AudioPowerEvent() {
        this.offset = 0.0f;
        this.isOn = false;
        this.offset = 0.0f;
        this.isOn = false;
    }

    public AudioPowerEvent(float f, boolean bl) {
        this.offset = 0.0f;
        this.isOn = false;
        this.offset = f;
        this.isOn = bl;
    }

    protected AudioPowerEvent(Parcel parcel) {
        boolean bl = false;
        this.offset = 0.0f;
        this.isOn = false;
        this.offset = parcel.readFloat();
        if (parcel.readByte() != 0) {
            bl = true;
        }
        this.isOn = bl;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeFloat(this.offset);
        n = this.isOn ? 1 : 0;
        parcel.writeByte((byte)n);
    }

}

