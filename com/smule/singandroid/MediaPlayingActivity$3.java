package com.smule.singandroid;

import android.app.FragmentManager;
import android.content.Intent;
import com.smule.singandroid.fragments.NowPlayingFragment;

class MediaPlayingActivity$3 implements Runnable {
    final /* synthetic */ Intent f18887a;
    final /* synthetic */ MediaPlayingActivity f18888b;

    MediaPlayingActivity$3(MediaPlayingActivity mediaPlayingActivity, Intent intent) {
        this.f18888b = mediaPlayingActivity;
        this.f18887a = intent;
    }

    public void run() {
        FragmentManager fragmentManager = this.f18888b.getFragmentManager();
        fragmentManager.popBackStack();
        NowPlayingFragment nowPlayingFragment = (NowPlayingFragment) fragmentManager.findFragmentByTag("NOW_PLAYING_FRAGMENT");
        if (nowPlayingFragment != null) {
            nowPlayingFragment.onActivityResult(6800, -1, this.f18887a);
        }
    }
}
