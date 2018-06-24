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
import com.smule.android.network.models.CursorModel;
import com.smule.android.utils.ParcelUtils;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CursorModel
implements Parcelable {
    public static final Parcelable.Creator<CursorModel> CREATOR;
    public static String a;
    @JsonProperty
    public boolean hasNext;
    @JsonProperty
    public String next;

    static {
        a = "start";
        CREATOR = new Parcelable.Creator<CursorModel>(){

            public CursorModel a(Parcel parcel) {
                return new CursorModel(parcel);
            }

            public CursorModel[] a(int n) {
                return new CursorModel[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public CursorModel() {
    }

    public CursorModel(Parcel parcel) {
        this.next = parcel.readString();
        this.hasNext = ParcelUtils.a(parcel);
    }

    public static CursorModel a() {
        CursorModel cursorModel = new CursorModel();
        cursorModel.b();
        return cursorModel;
    }

    public void b() {
        this.next = a;
        this.hasNext = true;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "CursorModel: {next=" + this.next + ", hasNext=" + this.hasNext + "}";
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        String string2 = this.next == null ? "" : this.next;
        parcel.writeString(string2);
        ParcelUtils.a(parcel, this.hasNext);
    }
}

