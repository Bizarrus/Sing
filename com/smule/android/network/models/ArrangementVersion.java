package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrangementVersion implements Parcelable {
    public static final Creator<ArrangementVersion> CREATOR = new 1();
    @JsonProperty("arr")
    public Arrangement arrangement;
    @JsonProperty("groupParts")
    public boolean groupParts;
    @JsonProperty("length")
    public int length = -1;
    @JsonProperty("lyrics")
    public boolean lyrics;
    @JsonProperty("multipart")
    public boolean multipart;
    @JsonProperty("published")
    public boolean published;
    @JsonIgnore
    public ConcurrentHashMap<String, String> resourceFilePaths = new ConcurrentHashMap();
    @JsonProperty("normResources")
    public List<Resource> resources = new ArrayList();
    @JsonProperty("ver")
    public int version;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Resource implements Parcelable {
        public static final Creator<Resource> CREATOR = new 1();
        @JsonProperty("contentType")
        public String contentType;
        @JsonProperty("role")
        public String role;
        @JsonProperty("size")
        public long size;
        @JsonProperty("uid")
        public String uid;
        @JsonProperty("url")
        public String url;

        public Resource(Parcel parcel) {
            this.role = parcel.readString();
            this.url = parcel.readString();
            this.uid = parcel.readString();
            this.contentType = parcel.readString();
            this.size = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.role);
            parcel.writeString(this.url);
            parcel.writeString(this.uid);
            parcel.writeString(this.contentType);
            parcel.writeLong(this.size);
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Resource)) {
                return false;
            }
            Resource resource = (Resource) obj;
            if (this.size != resource.size) {
                return false;
            }
            if (this.contentType == null ? resource.contentType != null : !this.contentType.equals(resource.contentType)) {
                return false;
            }
            if (this.role == null ? resource.role != null : !this.role.equals(resource.role)) {
                return false;
            }
            if (this.uid == null ? resource.uid != null : !this.uid.equals(resource.uid)) {
                return false;
            }
            if (this.url != null) {
                if (this.url.equals(resource.url)) {
                    return true;
                }
            } else if (resource.url == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode;
            int i = 0;
            int hashCode2 = (this.role != null ? this.role.hashCode() : 0) * 31;
            if (this.url != null) {
                hashCode = this.url.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode2 = (hashCode + hashCode2) * 31;
            if (this.uid != null) {
                hashCode = this.uid.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode = (hashCode + hashCode2) * 31;
            if (this.contentType != null) {
                i = this.contentType.hashCode();
            }
            return ((hashCode + i) * 31) + ((int) (this.size ^ (this.size >>> 32)));
        }

        public String toString() {
            return "Resource{role='" + this.role + '\'' + ", url='" + this.url + '\'' + ", uid='" + this.uid + '\'' + ", contentType='" + this.contentType + '\'' + ", size=" + this.size + '}';
        }
    }

    public ArrangementVersion(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.arrangement = (Arrangement) parcel.readParcelable(Arrangement.class.getClassLoader());
        this.version = parcel.readInt();
        this.published = parcel.readByte() == (byte) 1;
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.lyrics = z;
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.multipart = z;
        if (parcel.readByte() != (byte) 1) {
            z2 = false;
        }
        this.groupParts = z2;
        this.length = parcel.readInt();
        parcel.readTypedList(this.resources, Resource.CREATOR);
        this.resourceFilePaths = (ConcurrentHashMap) parcel.readSerializable();
    }

    private Resource m8242a(String str) {
        for (Resource resource : this.resources) {
            if (str.equalsIgnoreCase(resource.role)) {
                return resource;
            }
        }
        return null;
    }

    public Resource m8243a() {
        Resource a = m8242a("cover_art_google");
        if (a == null) {
            return m8242a("cover_art");
        }
        return a;
    }

    public Resource m8244b() {
        return m8242a("background");
    }

    public boolean m8245c() {
        return this.arrangement != null && this.arrangement.m8241a();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArrangementVersion)) {
            return false;
        }
        ArrangementVersion arrangementVersion = (ArrangementVersion) obj;
        if (this.lyrics != arrangementVersion.lyrics) {
            return false;
        }
        if (this.multipart != arrangementVersion.multipart) {
            return false;
        }
        if (this.groupParts != arrangementVersion.groupParts) {
            return false;
        }
        if (this.published != arrangementVersion.published) {
            return false;
        }
        if (this.version != arrangementVersion.version) {
            return false;
        }
        if (this.length != arrangementVersion.length) {
            return false;
        }
        if (this.arrangement == null ? arrangementVersion.arrangement != null : !this.arrangement.equals(arrangementVersion.arrangement)) {
            return false;
        }
        if (this.resources != null) {
            if (this.resources.equals(arrangementVersion.resources)) {
                return true;
            }
        } else if (arrangementVersion.resources == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int i3 = 0;
        int hashCode = (((this.arrangement != null ? this.arrangement.hashCode() : 0) * 31) + this.version) * 31;
        if (this.published) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.lyrics) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.multipart) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + hashCode) * 31;
        if (!this.groupParts) {
            i2 = 0;
        }
        i = (((i + i2) * 31) + this.length) * 31;
        if (this.resources != null) {
            i3 = this.resources.hashCode();
        }
        return i + i3;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeParcelable(this.arrangement, 0);
        parcel.writeInt(this.version);
        parcel.writeByte((byte) (this.published ? 1 : 0));
        if (this.lyrics) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.multipart) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.groupParts) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeInt(this.length);
        parcel.writeTypedList(this.resources);
        parcel.writeSerializable(this.resourceFilePaths);
    }

    public String toString() {
        return "ArrangementVersion{arrangement=" + this.arrangement + ", version=" + this.version + ", published=" + this.published + ", lyrics=" + this.lyrics + ", multipart=" + this.multipart + ", groupParts=" + this.groupParts + ", length=" + this.length + ", resources=" + this.resources + '}';
    }
}
