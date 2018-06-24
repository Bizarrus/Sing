/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.smule.android.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.magicui.ExternalFriendContainer;
import com.smule.android.network.models.AccountIcon;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookFriend
implements Parcelable,
ExternalFriendContainer {
    public static final Parcelable.Creator<FacebookFriend> CREATOR;
    private static final String g;
    public String a;
    public String b;
    public String c;
    public boolean d;
    public long e;
    public AccountIcon f;

    static {
        g = FacebookFriend.class.getName();
        CREATOR = new Parcelable.Creator<FacebookFriend>(){

            public FacebookFriend a(Parcel parcel) {
                return new FacebookFriend(parcel);
            }

            public FacebookFriend[] a(int n) {
                return new FacebookFriend[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    public FacebookFriend(Parcel parcel) {
        boolean bl = true;
        this.e = parcel.readLong();
        this.a = parcel.readString();
        this.c = parcel.readString();
        this.b = parcel.readString();
        if (parcel.readByte() != 1) {
            bl = false;
        }
        this.d = bl;
        this.f = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
    }

    public FacebookFriend(String string2, String string3) {
        this.b = string2;
        this.a = string3;
        this.c = MagicFacebook.a(string3);
    }

    public static List<String> a(List<FacebookFriend> object) {
        ArrayList<String> arrayList = new ArrayList<String>();
        object = object.iterator();
        while (object.hasNext()) {
            arrayList.add(((FacebookFriend)object.next()).a);
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<FacebookFriend> a(JSONArray jSONArray) {
        ArrayList<FacebookFriend> arrayList = new ArrayList<FacebookFriend>();
        int n = 0;
        while (n < jSONArray.length()) {
            try {
                arrayList.add(new FacebookFriend(jSONArray.getJSONObject(n).getString("name"), jSONArray.getJSONObject(n).getString("id")));
            }
            catch (JSONException jSONException) {}
            ++n;
        }
        return arrayList;
    }

    @Override
    public String a() {
        return this.b;
    }

    @Override
    public String b() {
        return this.c;
    }

    @Override
    public long c() {
        return this.e;
    }

    @Override
    public AccountIcon d() {
        return this.f;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeLong(this.e);
        String string2 = this.a == null ? "" : this.a;
        parcel.writeString(string2);
        string2 = this.c == null ? "" : this.c;
        parcel.writeString(string2);
        string2 = this.b == null ? "" : this.b;
        parcel.writeString(string2);
        byte by = this.d ? 1 : 0;
        parcel.writeByte(by);
        parcel.writeParcelable((Parcelable)this.f, n);
    }

    public static class ComparatorByName
    implements Comparator<FacebookFriend> {
        public int a(FacebookFriend facebookFriend, FacebookFriend facebookFriend2) {
            return facebookFriend.b.toLowerCase().compareTo(facebookFriend2.b.toLowerCase());
        }

        @Override
        public /* synthetic */ int compare(Object object, Object object2) {
            return this.a((FacebookFriend)object, (FacebookFriend)object2);
        }
    }

}

