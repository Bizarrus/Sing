package com.smule.singandroid.chat;

import android.os.Bundle;
import com.smule.singandroid.FindFriendsFragment;
import org.androidannotations.annotations.EFragment;

@EFragment
public class FindFriendsWithoutHeaderFragment extends FindFriendsFragment {
    public static final String f21056r = FindFriendsFragment.class.getName();

    public static FindFriendsWithoutHeaderFragment m22730B() {
        return m22731c(false);
    }

    public static FindFriendsWithoutHeaderFragment m22731c(boolean z) {
        return FindFriendsWithoutHeaderFragment_.m22734C().m22733a(z).m22732a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(false);
        this.k.m23180d();
    }
}
