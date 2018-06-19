package com.smule.android.datasources;

import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.FacebookFriend.ComparatorByName;
import com.smule.android.facebook.FriendFinder;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.facebook.MagicFacebook$FindFacebookFriendsListener;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import twitter4j.HttpResponseCode;

public class FacebookFriendsDataSource extends MagicDataSource<FacebookFriend> {
    private static final String f15878k = FacebookFriendsDataSource.class.getName();
    FriendFinder f15879a = new FriendFinder(this.f15881c, false, HttpResponseCode.INTERNAL_SERVER_ERROR);
    FetchDataCallback<FacebookFriend> f15880b;
    MagicFacebook$FindFacebookFriendsListener f15881c = new C35161(this);

    class C35161 implements MagicFacebook$FindFacebookFriendsListener {
        final /* synthetic */ FacebookFriendsDataSource f15864a;

        C35161(FacebookFriendsDataSource facebookFriendsDataSource) {
            this.f15864a = facebookFriendsDataSource;
        }

        public void mo6240a() {
            Log.b(FacebookFriendsDataSource.f15878k, "errorFetchingFriendsFromFacebook");
            this.f15864a.f15880b.mo6256a();
        }

        public void mo6241a(List<FacebookFriend> list, List<FacebookFriend> list2, boolean z) {
            Log.b(FacebookFriendsDataSource.f15878k, "friendsFound");
            list.addAll(list2);
            List arrayList = new ArrayList();
            for (FacebookFriend facebookFriend : list) {
                if (facebookFriend.f15947e != 0) {
                    arrayList.add(facebookFriend);
                }
            }
            Collections.sort(arrayList, new ComparatorByName());
            this.f15864a.f15880b.mo6257a(arrayList, z ? 1 : -1);
        }
    }

    public FacebookFriendsDataSource() {
        super(FacebookFriendsDataSource.class.getSimpleName());
    }

    public int mo6242a() {
        return 0;
    }

    public Future<?> mo6243a(int i, int i2, FetchDataCallback<FacebookFriend> fetchDataCallback) {
        Log.b(f15878k, "fetchData called");
        if (MagicFacebook.a().c()) {
            this.f15880b = fetchDataCallback;
            this.f15879a.m17759a();
        } else {
            Log.b(f15878k, "fetchData session was not valid");
            fetchDataCallback.mo6256a();
        }
        return null;
    }
}
