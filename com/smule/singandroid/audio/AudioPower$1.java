package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class AudioPower$1 implements Creator<AudioPower> {
    AudioPower$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m22276a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m22277a(i);
    }

    public AudioPower m22276a(Parcel parcel) {
        return new AudioPower(parcel);
    }

    public AudioPower[] m22277a(int i) {
        return new AudioPower[i];
    }
}
