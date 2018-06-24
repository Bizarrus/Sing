/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.SNPChat;

public class SNPChat
implements Parcelable {
    public static final Parcelable.Creator<SNPChat> CREATOR = new Parcelable.Creator<SNPChat>(){

        public SNPChat a(Parcel parcel) {
            return new SNPChat(parcel);
        }

        public SNPChat[] a(int n) {
            return new SNPChat[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="name")
    public String jid;
    @JsonProperty(value="type")
    public String type;

    public SNPChat() {
    }

    public SNPChat(Parcel parcel) {
        this.jid = parcel.readString();
        this.type = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        block5 : {
            block4 : {
                if (this == object) break block4;
                if (object == null || this.getClass() != object.getClass()) {
                    return false;
                }
                object = (SNPChat)object;
                if (!this.jid.equals(object.jid) || !this.type.equals(object.type)) break block5;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.jid.hashCode() * 31 + this.type.hashCode();
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.jid);
        parcel.writeString(this.type);
    }

}

