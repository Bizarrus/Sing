package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class AudioPowerEvent implements Parcelable {
    public static final Creator<AudioPowerEvent> CREATOR = new C42311();
    public boolean isOn;
    public float offset;

    static class C42311 implements Creator<AudioPowerEvent> {
        C42311() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m22278a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m22279a(i);
        }

        public AudioPowerEvent m22278a(Parcel parcel) {
            return new AudioPowerEvent(parcel);
        }

        public AudioPowerEvent[] m22279a(int i) {
            return new AudioPowerEvent[i];
        }
    }

    public AudioPowerEvent() {
        this.offset = 0.0f;
        this.isOn = false;
        this.offset = 0.0f;
        this.isOn = false;
    }

    public AudioPowerEvent(float f, boolean z) {
        this.offset = 0.0f;
        this.isOn = false;
        this.offset = f;
        this.isOn = z;
    }

    protected AudioPowerEvent(Parcel parcel) {
        boolean z = false;
        this.offset = 0.0f;
        this.isOn = false;
        this.offset = parcel.readFloat();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.isOn = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.offset);
        parcel.writeByte((byte) (this.isOn ? 1 : 0));
    }

    public int describeContents() {
        return 0;
    }
}
