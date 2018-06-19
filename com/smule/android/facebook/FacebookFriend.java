package com.smule.android.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.smule.android.network.models.AccountIcon;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class FacebookFriend implements Parcelable {
    public static final Creator<FacebookFriend> CREATOR = new C35241();
    private static final String f15942g = FacebookFriend.class.getName();
    public String f15943a;
    public String f15944b;
    public String f15945c;
    public boolean f15946d;
    public long f15947e;
    public AccountIcon f15948f;

    static class C35241 implements Creator<FacebookFriend> {
        C35241() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m17750a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m17751a(i);
        }

        public FacebookFriend m17750a(Parcel parcel) {
            return new FacebookFriend(parcel);
        }

        public FacebookFriend[] m17751a(int i) {
            return new FacebookFriend[i];
        }
    }

    public static class ComparatorByName implements Comparator<FacebookFriend> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m17752a((FacebookFriend) obj, (FacebookFriend) obj2);
        }

        public int m17752a(FacebookFriend facebookFriend, FacebookFriend facebookFriend2) {
            return facebookFriend.f15944b.toLowerCase().compareTo(facebookFriend2.f15944b.toLowerCase());
        }
    }

    public FacebookFriend(String str, String str2) {
        this.f15944b = str;
        this.f15943a = str2;
        this.f15945c = "http://graph.facebook.com/" + str2 + "/picture?type=large";
    }

    public static List<String> m17753a(List<FacebookFriend> list) {
        List<String> arrayList = new ArrayList();
        for (FacebookFriend facebookFriend : list) {
            arrayList.add(facebookFriend.f15943a);
        }
        return arrayList;
    }

    public static List<FacebookFriend> m17754a(JSONArray jSONArray) {
        List<FacebookFriend> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(new FacebookFriend(jSONArray.getJSONObject(i).getString("name"), jSONArray.getJSONObject(i).getString("id")));
            } catch (JSONException e) {
            }
        }
        return arrayList;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f15947e);
        parcel.writeString(this.f15943a == null ? "" : this.f15943a);
        parcel.writeString(this.f15945c == null ? "" : this.f15945c);
        parcel.writeString(this.f15944b == null ? "" : this.f15944b);
        parcel.writeByte((byte) (this.f15946d ? 1 : 0));
        parcel.writeParcelable(this.f15948f, i);
    }

    public FacebookFriend(Parcel parcel) {
        boolean z = true;
        this.f15947e = parcel.readLong();
        this.f15943a = parcel.readString();
        this.f15945c = parcel.readString();
        this.f15944b = parcel.readString();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.f15946d = z;
        this.f15948f = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
    }
}
