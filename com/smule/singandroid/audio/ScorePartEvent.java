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
import com.smule.singandroid.audio.AudioPowerEvent;

public class ScorePartEvent
extends AudioPowerEvent {
    public static final Parcelable.Creator<ScorePartEvent> CREATOR = new Parcelable.Creator<ScorePartEvent>(){

        public ScorePartEvent a(Parcel parcel) {
            return new ScorePartEvent(parcel);
        }

        public ScorePartEvent[] a(int n) {
            return new ScorePartEvent[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    public int mScorePart;

    public ScorePartEvent(float f, int n) {
        this.offset = f;
        this.mScorePart = n;
    }

    protected ScorePartEvent(Parcel parcel) {
        this.mScorePart = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getScorePart() {
        return this.mScorePart;
    }

    public float getTime() {
        return this.offset;
    }

    public String toString() {
        return "[" + this.offset + ", " + this.mScorePart + "]";
    }

    @Override
    public void writeToParcel(Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.mScorePart);
    }

}

