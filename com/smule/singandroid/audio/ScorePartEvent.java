package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ScorePartEvent extends AudioPowerEvent {
    public static final Creator<ScorePartEvent> CREATOR = new C42341();
    public int mScorePart;

    static class C42341 implements Creator<ScorePartEvent> {
        C42341() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m22289a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m22290a(i);
        }

        public ScorePartEvent m22289a(Parcel parcel) {
            return new ScorePartEvent(parcel);
        }

        public ScorePartEvent[] m22290a(int i) {
            return new ScorePartEvent[i];
        }
    }

    public ScorePartEvent(float f, int i) {
        this.offset = f;
        this.mScorePart = i;
    }

    protected ScorePartEvent(Parcel parcel) {
        this.mScorePart = parcel.readInt();
    }

    public float getTime() {
        return this.offset;
    }

    public int getScorePart() {
        return this.mScorePart;
    }

    public String toString() {
        return "[" + this.offset + ", " + this.mScorePart + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mScorePart);
    }

    public int describeContents() {
        return 0;
    }
}
