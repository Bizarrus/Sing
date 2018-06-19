package com.smule.singandroid;

class MediaPlayingActivity$2 implements Runnable {
    final /* synthetic */ Runnable f18885a;
    final /* synthetic */ MediaPlayingActivity f18886b;

    MediaPlayingActivity$2(MediaPlayingActivity mediaPlayingActivity, Runnable runnable) {
        this.f18886b = mediaPlayingActivity;
        this.f18885a = runnable;
    }

    public void run() {
        if (this.f18886b.f() && MediaPlayingActivity.a(this.f18886b) != null) {
            MediaPlayingActivity.a(this.f18886b).m23935d(this.f18885a);
        }
    }
}
