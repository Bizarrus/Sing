/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.TextUtils
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.core.JsonLocation
 *  com.fasterxml.jackson.core.JsonParseException
 *  com.fasterxml.jackson.core.JsonParser
 *  com.fasterxml.jackson.core.JsonProcessingException
 *  com.fasterxml.jackson.core.ObjectCodec
 *  com.fasterxml.jackson.core.TreeNode
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.DeserializationContext
 *  com.fasterxml.jackson.databind.JsonDeserializer
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.fasterxml.jackson.databind.ObjectReader
 *  com.fasterxml.jackson.databind.annotation.JsonDeserialize
 */
package com.smule.android.uploader;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.UploadJob;
import com.smule.android.utils.JsonUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonDeserialize(using=.class)
public class UploadJob {
    private static final String a = UploadJob.class.getName();
    @JsonProperty
    public String arrangementKey;
    @JsonProperty
    public boolean audioAnalyticsFired = false;
    @JsonProperty
    public String audioEffectName;
    @JsonProperty
    public boolean audioEffectVIPOnly;
    private transient boolean b = false;
    @JsonProperty
    public boolean boosted;
    @JsonProperty
    public String colorFilterId;
    @JsonIgnore
    @JsonIgnoreProperties(ignoreUnknown=true)
    public int consecutiveFailures;
    @JsonProperty
    public boolean headphonesHadMic;
    @JsonProperty
    public String id = UUID.randomUUID().toString();
    @JsonProperty
    public String intensityId;
    @JsonProperty
    public boolean invalidMedia = false;
    @JsonIgnore
    public boolean isAddVideoUsed;
    @JsonProperty
    public boolean isAirbrushOn;
    @JsonProperty
    public boolean isAutoTuneOn;
    @JsonProperty
    public boolean isJoin = false;
    @JsonProperty
    public boolean isNew;
    @JsonProperty
    public boolean isOnboarding = false;
    @JsonProperty
    public PerformanceV2 performance;
    @JsonProperty
    public String performanceKey;
    @JsonProperty
    public UploadResourceInfo resourceInfo;
    @JsonProperty
    public String songUid;
    @JsonProperty
    public String trackKey;
    @JsonProperty
    public String uploadKey;
    @JsonProperty
    public SortedSet<Chunk> uploadedChunks = new TreeSet<Chunk>();
    @JsonProperty
    public boolean usedHeadphone;
    @JsonProperty
    public int version = 2;
    @JsonProperty
    public boolean videoEffectVIPOnly;
    @JsonProperty
    public String videoStyleId;

    private void e() {
        if (!TextUtils.isEmpty((CharSequence)this.resourceInfo.mResourceFilename)) {
            new File(this.resourceInfo.mResourceFilename).delete();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2) {
        try {
            FileWriter fileWriter = new FileWriter(string2 + File.separator + this.id + ".json");
            String string3 = JsonUtils.a(this);
            if (string3 == null) {
                throw new IOException("unable to convert to JSON");
            }
            fileWriter.write(string3);
            fileWriter.close();
            return;
        }
        catch (IOException iOException) {
            MagicCrittercism.a("unable to save JSON: " + string2 + " id:" + this.id + " key:" + this.performanceKey);
            Log.d(a, "unable to save JSON", new Throwable("save"){}.initCause(iOException));
            return;
        }
    }

    public void a(SortedSet<Chunk> sortedSet) {
        this.uploadedChunks.clear();
        this.uploadedChunks.addAll(sortedSet);
    }

    public boolean a() {
        return this.b;
    }

    public void b(String string2) {
        new File(string2 + File.separator + this.id + ".json").delete();
        this.e();
    }

    public boolean b() {
        boolean bl = this.b;
        this.b = false;
        return bl;
    }

    public void c() {
        this.b = true;
    }

    public boolean c(String string2) {
        return new File(string2 + File.separator + this.id + ".json").exists();
    }

    public boolean d() {
        if (!TextUtils.isEmpty((CharSequence)this.uploadKey)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        return false;
    }

    public static class Chunk
    implements Comparable<Chunk> {
        @JsonProperty
        public long end;
        @JsonProperty
        public long start;

        public Chunk() {
        }

        public Chunk(long l, long l2) {
            this.start = l;
            this.end = l2;
        }

        public int a(Chunk chunk) {
            if (chunk == null) {
                return 1;
            }
            return new Long(this.start).compareTo(chunk.start);
        }

        @Override
        public /* synthetic */ int compareTo(Object object) {
            return this.a((Chunk)object);
        }
    }

    public static class UploadResourceInfo
    implements Parcelable {
        public static final Parcelable.Creator<UploadResourceInfo> CREATOR = new Parcelable.Creator<UploadResourceInfo>(){

            public UploadResourceInfo a(Parcel parcel) {
                return new UploadResourceInfo(parcel);
            }

            public UploadResourceInfo[] a(int n) {
                return new UploadResourceInfo[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
        @JsonProperty(value="info")
        public PerformanceManager.PerformanceResourceInfo mPerformanceResourceInfo;
        @JsonProperty(value="filename")
        public String mResourceFilename;
        @JsonProperty(value="sliceSize")
        public long mSliceSize = -1;
        @JsonProperty(value="timeoutSec")
        public int mTimeoutSec = -1;

        public UploadResourceInfo() {
        }

        UploadResourceInfo(Parcel parcel) {
            this.mPerformanceResourceInfo = new PerformanceManager.PerformanceResourceInfo();
            this.mPerformanceResourceInfo.mPOP = parcel.readString();
            this.mPerformanceResourceInfo.mResourceType = PerformanceManager.PerformanceResourceInfo.valueOf(parcel.readString());
            this.mPerformanceResourceInfo.mResourceId = parcel.readLong();
            this.mPerformanceResourceInfo.mHostname = parcel.readString();
            this.mSliceSize = parcel.readLong();
            this.mTimeoutSec = parcel.readInt();
            this.mResourceFilename = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int n) {
            parcel.writeString(this.mPerformanceResourceInfo.mPOP);
            parcel.writeString(this.mPerformanceResourceInfo.mResourceType.toString());
            parcel.writeLong(this.mPerformanceResourceInfo.mResourceId.longValue());
            parcel.writeString(this.mPerformanceResourceInfo.mHostname);
            parcel.writeLong(this.mSliceSize);
            parcel.writeInt(this.mTimeoutSec);
            parcel.writeString(this.mResourceFilename);
        }
    }

}

