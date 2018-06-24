/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.mediaplaying;

import com.smule.singandroid.mediaplaying.MediaPlayingFragment;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import java.util.List;

public interface PlaybackPresenter {
    public void a(int var1, ClickSource var2);

    public void a(int var1, String var2);

    public void a(int var1, boolean var2);

    public void a(MediaPlayingFragment var1, int var2);

    public void a(MediaPlayingFragment var1, int var2, boolean var3);

    public void a(List<MediaPlayingPlayable> var1, int var2);

    public void a(boolean var1);

    public void b(int var1, ClickSource var2);

    public void b(int var1, String var2);

    public void c(int var1, String var2);

    public static enum ClickSource {
        a,
        b,
        c,
        d;
        

        private ClickSource() {
        }
    }

}

