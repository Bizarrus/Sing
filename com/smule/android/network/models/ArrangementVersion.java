/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.ArrangementVersion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ArrangementVersion
implements Parcelable {
    public static final Parcelable.Creator<ArrangementVersion> CREATOR = new Parcelable.Creator<ArrangementVersion>(){

        public ArrangementVersion a(Parcel parcel) {
            return new ArrangementVersion(parcel);
        }

        public ArrangementVersion[] a(int n) {
            return new ArrangementVersion[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="arr")
    public Arrangement arrangement;
    @JsonProperty(value="groupParts")
    public boolean groupParts;
    @JsonProperty(value="length")
    public int length;
    @JsonProperty(value="lyrics")
    public boolean lyrics;
    @JsonProperty(value="multipart")
    public boolean multipart;
    @JsonProperty(value="published")
    public boolean published;
    @JsonIgnore
    public ConcurrentHashMap<String, String> resourceFilePaths;
    @JsonProperty(value="normResources")
    public List<Resource> resources;
    @JsonProperty(value="ver")
    public int version;

    public ArrangementVersion() {
        this.length = -1;
        this.resources = new ArrayList<Resource>();
        this.resourceFilePaths = new ConcurrentHashMap();
    }

    /*
     * Enabled aggressive block sorting
     */
    public ArrangementVersion(Parcel parcel) {
        boolean bl = true;
        this.length = -1;
        this.resources = new ArrayList<Resource>();
        this.resourceFilePaths = new ConcurrentHashMap();
        this.arrangement = (Arrangement)parcel.readParcelable(Arrangement.class.getClassLoader());
        this.version = parcel.readInt();
        boolean bl2 = parcel.readByte() == 1;
        this.published = bl2;
        bl2 = parcel.readByte() == 1;
        this.lyrics = bl2;
        bl2 = parcel.readByte() == 1;
        this.multipart = bl2;
        bl2 = parcel.readByte() == 1 ? bl : false;
        this.groupParts = bl2;
        this.length = parcel.readInt();
        parcel.readTypedList(this.resources, Resource.CREATOR);
        this.resourceFilePaths = (ConcurrentHashMap)parcel.readSerializable();
    }

    private Resource a(String string2) {
        for (Resource resource : this.resources) {
            if (!string2.equalsIgnoreCase(resource.role)) continue;
            return resource;
        }
        return null;
    }

    public Resource a() {
        Resource resource;
        Resource resource2 = resource = this.a("cover_art_google");
        if (resource == null) {
            resource2 = this.a("cover_art");
        }
        return resource2;
    }

    public Resource b() {
        return this.a("background");
    }

    public Resource c() {
        Resource resource;
        Resource resource2 = resource = this.a("preview");
        if (resource == null) {
            resource2 = this.b();
        }
        return resource2;
    }

    public boolean d() {
        if (this.arrangement != null && this.arrangement.a()) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ArrangementVersion)) {
            return false;
        }
        object = (ArrangementVersion)object;
        if (this.lyrics != object.lyrics) {
            return false;
        }
        if (this.multipart != object.multipart) {
            return false;
        }
        if (this.groupParts != object.groupParts) {
            return false;
        }
        if (this.published != object.published) {
            return false;
        }
        if (this.version != object.version) {
            return false;
        }
        if (this.length != object.length) {
            return false;
        }
        if (this.arrangement != null) {
            if (!this.arrangement.equals(object.arrangement)) {
                return false;
            }
        } else if (object.arrangement != null) return false;
        if (this.resources != null) {
            if (this.resources.equals(object.resources)) return true;
            return false;
        }
        if (object.resources == null) return true;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 1;
        int n2 = 0;
        int n3 = this.arrangement != null ? this.arrangement.hashCode() : 0;
        int n4 = this.version;
        int n5 = this.published ? 1 : 0;
        int n6 = this.lyrics ? 1 : 0;
        int n7 = this.multipart ? 1 : 0;
        if (!this.groupParts) {
            n = 0;
        }
        int n8 = this.length;
        if (this.resources != null) {
            n2 = this.resources.hashCode();
        }
        return (((n7 + (n6 + (n5 + (n3 * 31 + n4) * 31) * 31) * 31) * 31 + n) * 31 + n8) * 31 + n2;
    }

    public String toString() {
        return "ArrangementVersion{arrangement=" + this.arrangement + ", version=" + this.version + ", published=" + this.published + ", lyrics=" + this.lyrics + ", multipart=" + this.multipart + ", groupParts=" + this.groupParts + ", length=" + this.length + ", resources=" + this.resources + '}';
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        int n2 = 1;
        parcel.writeParcelable((Parcelable)this.arrangement, 0);
        parcel.writeInt(this.version);
        n = this.published ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.lyrics ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.multipart ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.groupParts ? n2 : 0;
        parcel.writeByte((byte)n);
        parcel.writeInt(this.length);
        parcel.writeTypedList(this.resources);
        parcel.writeSerializable(this.resourceFilePaths);
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class Resource
    implements Parcelable {
        public static final Parcelable.Creator<Resource> CREATOR = new Parcelable.Creator<Resource>(){

            public Resource a(Parcel parcel) {
                return new Resource(parcel);
            }

            public Resource[] a(int n) {
                return new Resource[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
        @JsonProperty(value="contentType")
        public String contentType;
        @JsonProperty(value="role")
        public String role;
        @JsonProperty(value="size")
        public long size;
        @JsonProperty(value="uid")
        public String uid;
        @JsonProperty(value="url")
        public String url;

        public Resource() {
        }

        public Resource(Parcel parcel) {
            this.role = parcel.readString();
            this.url = parcel.readString();
            this.uid = parcel.readString();
            this.contentType = parcel.readString();
            this.size = parcel.readLong();
        }

        public int describeContents() {
            return 0;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof Resource)) {
                return false;
            }
            object = (Resource)object;
            if (this.size != object.size) {
                return false;
            }
            if (this.contentType != null) {
                if (!this.contentType.equals(object.contentType)) {
                    return false;
                }
            } else if (object.contentType != null) return false;
            if (this.role != null) {
                if (!this.role.equals(object.role)) {
                    return false;
                }
            } else if (object.role != null) return false;
            if (this.uid != null) {
                if (!this.uid.equals(object.uid)) {
                    return false;
                }
            } else if (object.uid != null) return false;
            if (this.url != null) {
                if (this.url.equals(object.url)) return true;
                return false;
            }
            if (object.url == null) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n = 0;
            int n2 = this.role != null ? this.role.hashCode() : 0;
            int n3 = this.url != null ? this.url.hashCode() : 0;
            int n4 = this.uid != null ? this.uid.hashCode() : 0;
            if (this.contentType != null) {
                n = this.contentType.hashCode();
            }
            return ((n4 + (n3 + n2 * 31) * 31) * 31 + n) * 31 + (int)(this.size ^ this.size >>> 32);
        }

        public String toString() {
            return "Resource{role='" + this.role + '\'' + ", url='" + this.url + '\'' + ", uid='" + this.uid + '\'' + ", contentType='" + this.contentType + '\'' + ", size=" + this.size + '}';
        }

        public void writeToParcel(Parcel parcel, int n) {
            parcel.writeString(this.role);
            parcel.writeString(this.url);
            parcel.writeString(this.uid);
            parcel.writeString(this.contentType);
            parcel.writeLong(this.size);
        }
    }

}

