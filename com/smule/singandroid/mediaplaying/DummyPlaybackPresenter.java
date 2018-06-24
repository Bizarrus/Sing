/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.mediaplaying;

import com.smule.android.logging.Log;
import com.smule.singandroid.mediaplaying.MediaPlayingFragment;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.mediaplaying.PlaybackPresenter;
import java.util.List;

public class DummyPlaybackPresenter
implements PlaybackPresenter {
    private static final String a = DummyPlaybackPresenter.class.getSimpleName();

    private static final void a() {
        Log.e(a, "FIXME!!! calling presenter methods on a DummyPlaybackPresenter");
    }

    @Override
    public void a(int n, PlaybackPresenter.ClickSource clickSource) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void a(int n, String string2) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void a(int n, boolean bl) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void a(MediaPlayingFragment mediaPlayingFragment, int n) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void a(MediaPlayingFragment mediaPlayingFragment, int n, boolean bl) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void a(List<MediaPlayingPlayable> list, int n) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void a(boolean bl) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void b(int n, PlaybackPresenter.ClickSource clickSource) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void b(int n, String string2) {
        DummyPlaybackPresenter.a();
    }

    @Override
    public void c(int n, String string2) {
        DummyPlaybackPresenter.a();
    }
}

