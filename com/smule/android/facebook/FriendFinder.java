package com.smule.android.facebook;

import android.os.Bundle;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.GraphResponse.PagingDirection;
import com.facebook.share.internal.ShareConstants;
import com.smule.android.facebook.MagicFacebook.FacebookFindUserResponse;
import com.smule.android.facebook.MagicFacebook.FacebookUserItem;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.SocialManager;
import com.smule.android.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FriendFinder {
    private static final String f15958f = FriendFinder.class.getName();
    MagicFacebook$FindFacebookFriendsListener f15959a;
    boolean f15960b;
    GraphRequest f15961c;
    int f15962d;
    boolean f15963e = true;

    class C35261 implements Callback {
        final /* synthetic */ FriendFinder f15950a;

        class C35251 implements Runnable {
            final /* synthetic */ C35261 f15949a;

            C35251(C35261 c35261) {
                this.f15949a = c35261;
            }

            public void run() {
                this.f15949a.f15950a.f15959a.mo6240a();
            }
        }

        C35261(FriendFinder friendFinder) {
            this.f15950a = friendFinder;
        }

        public void onCompleted(GraphResponse graphResponse) {
            Log.b(FriendFinder.f15958f, "GraphRequest.Callback onCompleted");
            JSONObject jSONObject = graphResponse.getJSONObject();
            JSONArray optJSONArray = jSONObject != null ? jSONObject.optJSONArray(ShareConstants.WEB_DIALOG_PARAM_DATA) : null;
            if (optJSONArray == null) {
                ThreadUtils.m19058a(new C35251(this));
                return;
            }
            this.f15950a.m17757a(optJSONArray, graphResponse);
            this.f15950a.f15961c = graphResponse.getRequestForPagedResults(PagingDirection.NEXT);
            this.f15950a.f15963e = this.f15950a.f15961c != null;
        }
    }

    class C35283 implements Runnable {
        final /* synthetic */ FriendFinder f15954a;

        C35283(FriendFinder friendFinder) {
            this.f15954a = friendFinder;
        }

        public void run() {
            this.f15954a.f15959a.mo6240a();
        }
    }

    public FriendFinder(MagicFacebook$FindFacebookFriendsListener magicFacebook$FindFacebookFriendsListener, boolean z, int i) {
        this.f15959a = magicFacebook$FindFacebookFriendsListener;
        this.f15960b = z;
        this.f15962d = i;
    }

    public void m17759a() {
        Bundle bundle;
        Log.b(f15958f, "findNext");
        if (!this.f15963e) {
            Log.e(f15958f, "findNext called after there was no more data");
            this.f15959a.mo6241a(new ArrayList(), new ArrayList(), false);
        }
        final Callback c35261 = new C35261(this);
        if (this.f15962d > 0) {
            bundle = new Bundle();
            bundle.putInt("limit", this.f15962d);
        } else {
            bundle = null;
        }
        final GraphRequest graphRequest = this.f15961c != null ? this.f15961c : new GraphRequest(MagicFacebook.a().b(), "me/friends", bundle, null);
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ FriendFinder f15953c;

            public void run() {
                graphRequest.setCallback(c35261);
                graphRequest.executeAndWait();
            }
        });
    }

    private void m17757a(JSONArray jSONArray, GraphResponse graphResponse) {
        Log.b(f15958f, "findFacebookFriends - newMyFriendsRequest callback called");
        if (jSONArray == null) {
            Log.e(f15958f, "findFacebookFriends - users returned null");
            return;
        }
        List<FacebookFriend> a = FacebookFriend.m17754a(jSONArray);
        Log.b(f15958f, "Facebook friends call returned: " + a.size() + " friends.");
        FacebookFindUserResponse a2 = FacebookFindUserResponse.a(SocialManager.m18345a().m18346a(FacebookFriend.m17753a((List) a), this.f15960b));
        if (a2.a()) {
            Map a3 = m17755a(a2.mFollowing);
            Map a4 = m17755a(a2.mNotFollowing);
            for (FacebookFriend facebookFriend : a) {
                FacebookUserItem facebookUserItem;
                if (a3.containsKey(facebookFriend.f15943a)) {
                    facebookFriend.f15946d = true;
                    facebookUserItem = (FacebookUserItem) a3.get(facebookFriend.f15943a);
                    facebookFriend.f15947e = facebookUserItem.mAccountId;
                    facebookFriend.f15948f = facebookUserItem.mAccountIcon;
                } else if (a4.containsKey(facebookFriend.f15943a)) {
                    facebookFriend.f15946d = false;
                    facebookUserItem = (FacebookUserItem) a4.get(facebookFriend.f15943a);
                    facebookFriend.f15947e = facebookUserItem.mAccountId;
                    facebookFriend.f15948f = facebookUserItem.mAccountIcon;
                }
            }
            final List arrayList = new ArrayList();
            final List arrayList2 = new ArrayList();
            for (FacebookFriend facebookFriend2 : a) {
                if (facebookFriend2.f15947e == 0) {
                    arrayList.add(facebookFriend2);
                } else {
                    arrayList2.add(facebookFriend2);
                }
            }
            ThreadUtils.m19058a(new Runnable(this) {
                final /* synthetic */ FriendFinder f15957c;

                public void run() {
                    this.f15957c.f15959a.mo6241a(arrayList2, arrayList, this.f15957c.f15963e);
                }
            });
            return;
        }
        Log.e(f15958f, "findFacebookFriends - users returned null");
        ThreadUtils.m19058a(new C35283(this));
    }

    private HashMap<String, FacebookUserItem> m17755a(List<FacebookUserItem> list) {
        HashMap<String, FacebookUserItem> hashMap = new HashMap();
        for (FacebookUserItem facebookUserItem : list) {
            hashMap.put(facebookUserItem.mFbId, facebookUserItem);
        }
        return hashMap;
    }
}
