/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  com.facebook.AccessToken
 *  com.facebook.GraphRequest
 *  com.facebook.GraphRequest$Callback
 *  com.facebook.GraphResponse
 *  com.facebook.GraphResponse$PagingDirection
 *  com.facebook.HttpMethod
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package com.smule.android.facebook;

import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.SocialManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FriendFinder {
    private static final String a = FriendFinder.class.getName();
    private MagicFacebook b;
    private boolean c;
    private GraphRequest d;
    private int e;
    private boolean f = true;

    public FriendFinder(MagicFacebook findFacebookFriendsListener, boolean bl, int n) {
        this.b = findFacebookFriendsListener;
        this.c = bl;
        this.e = n;
    }

    public FriendFinder(boolean bl, int n) {
        this.c = bl;
        this.e = n;
    }

    private HashMap<String, MagicFacebook.FacebookUserItem> a(List<MagicFacebook.FacebookUserItem> object) {
        HashMap<String, MagicFacebook.FacebookUserItem> hashMap = new HashMap<String, MagicFacebook.FacebookUserItem>();
        object = object.iterator();
        while (object.hasNext()) {
            MagicFacebook.FacebookUserItem facebookUserItem = (MagicFacebook.FacebookUserItem)object.next();
            hashMap.put(facebookUserItem.mFbId, facebookUserItem);
        }
        return hashMap;
    }

    private void a(JSONArray iterator, GraphResponse map, final MagicFacebook findFacebookFriendsListener) {
        Log.b(a, "findFacebookFriends - newMyFriendsRequest callback called");
        if (iterator == null) {
            Log.e(a, "findFacebookFriends - users returned null");
            return;
        }
        iterator = FacebookFriend.a((JSONArray)iterator);
        Log.b(a, "Facebook friends call returned: " + iterator.size() + " friends.");
        map = FacebookFriend.a(iterator);
        final ArrayList<Object> arrayList = MagicFacebook.FacebookFindUserResponse.a(SocialManager.a().a((List<String>)((Object)map), this.c));
        if (!arrayList.a()) {
            Log.e(a, "findFacebookFriends - users returned null");
            ThreadUtils.a(new Runnable(){

                @Override
                public void run() {
                    findFacebookFriendsListener.a();
                }
            });
            return;
        }
        map = this.a(arrayList.mFollowing);
        arrayList = this.a(arrayList.mNotFollowing);
        Object object = iterator.iterator();
        while (object.hasNext()) {
            MagicFacebook.FacebookUserItem facebookUserItem;
            FacebookFriend facebookFriend = (FacebookFriend)object.next();
            if (map.containsKey(facebookFriend.a)) {
                facebookFriend.d = true;
                facebookUserItem = (MagicFacebook.FacebookUserItem)map.get(facebookFriend.a);
                facebookFriend.e = facebookUserItem.mAccountId;
                facebookFriend.f = facebookUserItem.mAccountIcon;
                continue;
            }
            if (!arrayList.containsKey(facebookFriend.a)) continue;
            facebookFriend.d = false;
            facebookUserItem = (MagicFacebook.FacebookUserItem)arrayList.get(facebookFriend.a);
            facebookFriend.e = facebookUserItem.mAccountId;
            facebookFriend.f = facebookUserItem.mAccountIcon;
        }
        map = new ArrayList();
        arrayList = new ArrayList<Object>();
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            object = (FacebookFriend)iterator.next();
            if (object.e == 0) {
                map.add(object);
                continue;
            }
            arrayList.add(object);
        }
        ThreadUtils.a(new Runnable((List)((Object)map)){
            final /* synthetic */ List c;
            {
                this.c = list2;
            }

            @Override
            public void run() {
                findFacebookFriendsListener.a(arrayList, this.c, FriendFinder.this.f);
            }
        });
    }

    public void a() {
        if (this.b == null) {
            Log.e(a, "findNext called, but no listener passed in at instantiation");
            return;
        }
        this.a(this.b);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(final MagicFacebook findFacebookFriendsListener) {
        if (!this.f) {
            Log.e(a, "findNext called after there was no more data");
            findFacebookFriendsListener.a(new ArrayList<FacebookFriend>(), new ArrayList<FacebookFriend>(), false);
        }
        GraphRequest.Callback callback = new GraphRequest.Callback(){

            /*
             * Enabled aggressive block sorting
             */
            public void onCompleted(GraphResponse object) {
                Log.b(a, "GraphRequest.Callback onCompleted");
                JSONObject jSONObject = object.getJSONObject();
                jSONObject = jSONObject != null ? jSONObject.optJSONArray("data") : null;
                if (jSONObject == null) {
                    ThreadUtils.a(new Runnable(){

                        @Override
                        public void run() {
                            findFacebookFriendsListener.a();
                        }
                    });
                    return;
                }
                FriendFinder.this.a((JSONArray)jSONObject, (GraphResponse)object, findFacebookFriendsListener);
                FriendFinder.this.d = object.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT);
                object = FriendFinder.this;
                boolean bl = FriendFinder.this.d != null;
                ((FriendFinder)object).f = bl;
            }

        };
        if (this.e > 0) {
            findFacebookFriendsListener = new Bundle();
            findFacebookFriendsListener.putInt("limit", this.e);
        } else {
            findFacebookFriendsListener = null;
        }
        findFacebookFriendsListener = this.d != null ? this.d : new GraphRequest(MagicFacebook.a().b(), "me/friends", (Bundle)findFacebookFriendsListener, null);
        MagicNetwork.a(new Runnable((GraphRequest)findFacebookFriendsListener, callback){
            final /* synthetic */ GraphRequest a;
            final /* synthetic */ GraphRequest.Callback b;
            {
                this.a = graphRequest;
                this.b = callback;
            }

            @Override
            public void run() {
                this.a.setCallback(this.b);
                this.a.executeAndWait();
            }
        });
    }

}

