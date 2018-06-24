/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.annotation.RequiresApi
 *  android.util.Pair
 *  com.facebook.device.yearclass.DeviceInfo
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.millennialmedia.internal.utils.IOUtils
 */
package com.smule.singandroid.audio;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Pair;
import com.facebook.device.yearclass.DeviceInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.millennialmedia.internal.utils.IOUtils;
import com.smule.android.logging.Log;
import com.smule.android.utils.JsonUtils;
import com.smule.singandroid.audio.DeviceData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DeviceData
implements Parcelable {
    public static final Parcelable.Creator<DeviceData> CREATOR;
    static final String a;
    @JsonProperty
    public final String CPU_Architecture;
    @JsonProperty
    @NonNull
    public final List<String> CPU_Features;
    @JsonProperty
    @Nullable
    public final String CPU_Name;
    @JsonProperty
    @Nullable
    public final String audioChipset;
    @JsonProperty
    public final int clockSpeed;
    @JsonProperty
    public final long maxMemory;
    @JsonProperty
    public final int numberOfCores;

    static {
        a = DeviceData.class.getName();
        CREATOR = new Parcelable.Creator<DeviceData>(){

            public DeviceData a(Parcel parcel) {
                return new DeviceData(parcel);
            }

            public DeviceData[] a(int n) {
                return new DeviceData[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public DeviceData() {
        this.CPU_Architecture = null;
        this.numberOfCores = -1;
        this.clockSpeed = -1;
        this.maxMemory = -1;
        this.audioChipset = null;
        this.CPU_Name = null;
        this.CPU_Features = new ArrayList<String>();
    }

    @RequiresApi(api=9)
    public DeviceData(Context context) {
        this.CPU_Architecture = System.getProperty("os.arch");
        this.numberOfCores = DeviceInfo.getNumberOfCPUCores();
        this.clockSpeed = DeviceInfo.getCPUMaxFreqKHz();
        this.maxMemory = DeviceInfo.getTotalMemory((Context)context);
        this.audioChipset = DeviceData.a();
        this.CPU_Name = this.b();
        this.CPU_Features = this.c();
    }

    public DeviceData(Parcel parcel) {
        this.CPU_Architecture = parcel.readString();
        this.numberOfCores = parcel.readInt();
        this.clockSpeed = parcel.readInt();
        this.maxMemory = parcel.readLong();
        this.audioChipset = parcel.readString();
        this.CPU_Name = parcel.readString();
        this.CPU_Features = new ArrayList<String>();
        parcel.readList(this.CPU_Features, ArrayList.class.getClassLoader());
    }

    @Nullable
    private static String a() {
        try {
            String string2 = DeviceData.a(new File("/proc/asound/version")).trim();
            return string2;
        }
        catch (IOException iOException) {
            Log.e(a, "Failed to get audio chipset information from /proc/asound/version");
            return null;
        }
    }

    @NonNull
    private static String a(File object) throws IOException {
        object = new FileInputStream((File)object);
        String string2 = IOUtils.convertStreamToString((InputStream)object);
        object.close();
        return string2;
    }

    @Nullable
    @RequiresApi(api=9)
    private static String a(File object, String string2, String object2) throws IOException {
        object = DeviceData.a((File)object, (String)object2);
        object2 = new Comparator<Pair<String, String>>(){

            public int a(Pair<String, String> pair, Pair<String, String> pair2) {
                return ((String)pair.first).compareTo((String)pair2.first);
            }

            public /* synthetic */ int compare(Object object, Object object2) {
                return this.a((Pair)object, (Pair)object2);
            }
        };
        Collections.sort(object, object2);
        int n = Collections.binarySearch(object, new Pair((Object)string2, (Object)null), object2);
        if (n < 0) {
            return null;
        }
        return (String)((Pair)object.get((int)n)).second;
    }

    @NonNull
    @RequiresApi(api=9)
    private static List<Pair<String, String>> a(File arrstring, String string2) throws IOException {
        arrstring = DeviceData.a((File)arrstring).split("\n");
        ArrayList<Pair<String, String>> arrayList = new ArrayList<Pair<String, String>>(arrstring.length);
        for (String string3 : arrstring) {
            String[] arrstring2;
            if (string3.trim().isEmpty() || (arrstring2 = string3.split(string2, 2)).length != 2) continue;
            arrayList.add((Pair)new Pair((Object)arrstring2[0].trim(), (Object)arrstring2[1].trim()));
        }
        return arrayList;
    }

    @Nullable
    @RequiresApi(api=9)
    private String b() {
        try {
            String string2 = DeviceData.a(new File("/proc/cpuinfo"), "Hardware", ":");
            return string2;
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to get CPU name from /proc/cpuinfo", iOException);
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @NonNull
    @RequiresApi(api=9)
    private List<String> c() {
        try {
            String string2 = DeviceData.a(new File("/proc/cpuinfo"), "Features", ":");
            if (string2 != null) return Arrays.asList(string2.split("\\s+"));
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to get \"features\" from /proc/cpuinfo", iOException);
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return JsonUtils.a(this);
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.CPU_Architecture);
        parcel.writeInt(this.numberOfCores);
        parcel.writeInt(this.clockSpeed);
        parcel.writeLong(this.maxMemory);
        parcel.writeString(this.audioChipset);
        parcel.writeString(this.CPU_Name);
        parcel.writeList(this.CPU_Features);
    }
}

