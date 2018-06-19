package com.smule.android.uploader;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo.ResourceType;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.JsonUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

@JsonDeserialize(using = Deserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadJob {
    private static final String f6925a = UploadJob.class.getName();
    @JsonProperty("arrangementKey")
    public String arrangementKey;
    @JsonProperty("audioAnalyticsFired")
    public boolean audioAnalyticsFired = false;
    @JsonProperty("audioEffectName")
    public String audioEffectName;
    @JsonProperty("audioEffectVIPOnly")
    public boolean audioEffectVIPOnly;
    @JsonIgnore
    @JsonIgnoreProperties(ignoreUnknown = true)
    public int consecutiveFailures;
    @JsonProperty("headphonesHadMic")
    public boolean headphonesHadMic;
    @JsonProperty("id")
    public String id = UUID.randomUUID().toString();
    @JsonProperty("invalidMedia")
    public boolean invalidMedia = false;
    @JsonProperty("isAirbrushOn")
    public boolean isAirbrushOn;
    @JsonProperty("isJoin")
    public boolean isJoin = false;
    @JsonProperty("isNew")
    public boolean isNew;
    @JsonProperty("isOnboarding")
    public boolean isOnboarding = false;
    @JsonProperty("performance")
    public PerformanceV2 performance;
    @JsonProperty("performanceKey")
    public String performanceKey;
    @JsonProperty("songUid")
    public String songUid;
    @JsonProperty("trackKey")
    public String trackKey;
    @JsonProperty("uploadKey")
    public String uploadKey;
    @JsonProperty("resourceInfo")
    public UploadResourceInfo uploadResourceInfo;
    @JsonProperty("uploadedChunks")
    public SortedSet<Chunk> uploadedChunks = new TreeSet();
    @JsonProperty("usedHeadphone")
    public boolean usedHeadphone;
    @JsonProperty("version")
    public int version = 2;
    @JsonProperty("videoEffectName")
    public String videoEffectName;
    @JsonProperty("videoEffectVIPOnly")
    public boolean videoEffectVIPOnly;

    public static class Chunk implements Comparable<Chunk> {
        @JsonProperty("end")
        public long end;
        @JsonProperty("start")
        public long start;

        public /* synthetic */ int compareTo(Object obj) {
            return m8343a((Chunk) obj);
        }

        public Chunk(long j, long j2) {
            this.start = j;
            this.end = j2;
        }

        public int m8343a(Chunk chunk) {
            if (chunk == null) {
                return 1;
            }
            return new Long(this.start).compareTo(Long.valueOf(chunk.start));
        }
    }

    public static class UploadResourceInfo implements Parcelable {
        public static final Creator<UploadResourceInfo> CREATOR = new 1();
        @JsonProperty("info")
        public PerformanceResourceInfo mPerformanceResourceInfo;
        @JsonProperty("filename")
        public String mResourceFilename;
        @JsonProperty("sliceSize")
        public long mSliceSize;
        @JsonProperty("timeoutSec")
        public int mTimeoutSec;

        public UploadResourceInfo() {
            this.mSliceSize = -1;
            this.mTimeoutSec = -1;
        }

        UploadResourceInfo(Parcel parcel) {
            this.mSliceSize = -1;
            this.mTimeoutSec = -1;
            this.mPerformanceResourceInfo = new PerformanceResourceInfo();
            this.mPerformanceResourceInfo.mPOP = parcel.readString();
            this.mPerformanceResourceInfo.mResourceType = ResourceType.valueOf(parcel.readString());
            this.mPerformanceResourceInfo.mResourceId = Long.valueOf(parcel.readLong());
            this.mPerformanceResourceInfo.mHostname = parcel.readString();
            this.mSliceSize = parcel.readLong();
            this.mTimeoutSec = parcel.readInt();
            this.mResourceFilename = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mPerformanceResourceInfo.mPOP);
            parcel.writeString(this.mPerformanceResourceInfo.mResourceType.toString());
            parcel.writeLong(this.mPerformanceResourceInfo.mResourceId.longValue());
            parcel.writeString(this.mPerformanceResourceInfo.mHostname);
            parcel.writeLong(this.mSliceSize);
            parcel.writeInt(this.mTimeoutSec);
            parcel.writeString(this.mResourceFilename);
        }
    }

    public void m8345a(String str) {
        try {
            FileWriter fileWriter = new FileWriter(str + File.separator + this.id + ".json");
            String a = JsonUtils.a(this);
            if (a == null) {
                throw new IOException("unable to convert to JSON");
            }
            fileWriter.write(a);
            fileWriter.close();
        } catch (Throwable e) {
            MagicCrittercism.m7779a("unable to save JSON: " + str + " id:" + this.id + " key:" + this.performanceKey);
            Log.m7775d(f6925a, "unable to save JSON", new DroidSing9737SaveException("save").initCause(e));
        }
    }

    public void m8348b(String str) {
        new File(str + File.separator + this.id + ".json").delete();
        m8344b();
    }

    public void m8346a(SortedSet<Chunk> sortedSet) {
        this.uploadedChunks.clear();
        this.uploadedChunks.addAll(sortedSet);
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    private void m8344b() {
        if (!TextUtils.isEmpty(this.uploadResourceInfo.mResourceFilename)) {
            new File(this.uploadResourceInfo.mResourceFilename).delete();
        }
    }

    public boolean m8349c(String str) {
        return new File(str + File.separator + this.id + ".json").exists();
    }

    public boolean m8347a() {
        return !TextUtils.isEmpty(this.uploadKey);
    }
}
