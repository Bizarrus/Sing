package com.smule.singandroid;

import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.MagicFacebook$FindFacebookFriendsListener;
import java.util.List;

class MasterActivity$13 implements MagicFacebook$FindFacebookFriendsListener {
    final /* synthetic */ MasterActivity f18850a;

    MasterActivity$13(MasterActivity masterActivity) {
        this.f18850a = masterActivity;
    }

    public void mo6241a(List<FacebookFriend> list, List<FacebookFriend> list2, boolean z) {
        if (list != null && !list.isEmpty()) {
            MagicPreferences.m20305a(this.f18850a, false);
            this.f18850a.O();
        }
    }

    public void mo6240a() {
    }
}
