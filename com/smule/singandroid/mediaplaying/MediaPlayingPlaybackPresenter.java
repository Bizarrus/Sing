/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  android.support.annotation.UiThread
 *  android.text.TextUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.mediaplaying;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingFragment;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.mediaplaying.PlaybackPresenter;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.List;

@UiThread
public class MediaPlayingPlaybackPresenter
implements PlaybackPresenter {
    private static String a = MediaPlayingPlaybackPresenter.class.getSimpleName();
    private final MediaPlayingActivity b;
    private List<MediaPlayingPlayable> c = new ArrayList<MediaPlayingPlayable>(0);
    private int d = -1;
    private boolean e;
    private String f = "";
    private boolean g = false;
    private boolean h = false;

    public MediaPlayingPlaybackPresenter(@NonNull MediaPlayingActivity mediaPlayingActivity) {
        this.b = mediaPlayingActivity;
    }

    private void a() {
        this.b(this.d - 1, false);
    }

    private boolean a(int n) {
        if (n >= 0 && n == this.d) {
            if (this.d < this.c.size()) {
                return true;
            }
            Log.d(a, "somehow the currentItem is not on the list", new IllegalStateException());
        }
        return false;
    }

    private void b() {
        this.b(this.d + 1, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(int n, boolean bl) {
        if (n < 0 || n >= this.c.size()) {
            Log.e(a, "UI is proly making verboten call. i=" + n + ", " + this.c.size());
            return;
        }
        if (this.g) {
            Log.d(a, "Fragment busy state was not cleared - playing i=" + n + ", " + this.c.size());
            this.g = false;
        }
        if (this.h) {
            Log.d(a, "Pending Next was not cleared - playing i=" + n + ", " + this.c.size());
            this.h = false;
        }
        if (n == this.c.size() - 1) {
            SingAnalytics.N();
        }
        this.d = n;
        Object object = this.c.get(n);
        if (object.b() != null) {
            Log.d(a, "Continuous play with Songbook entries is not supported yet : " + object.getClass().getSimpleName(), new IllegalStateException());
            return;
        }
        if (object.a() == null) {
            Log.d(a, "Could not continuous play this item, wrong type: " + object.getClass().getSimpleName(), new IllegalStateException());
            return;
        }
        object = object.a();
        this.f = object.performanceKey;
        if (bl) {
            this.e = MiscUtils.a((PerformanceV2)object);
        }
        MediaPlayingActivity mediaPlayingActivity = this.b;
        boolean bl2 = this.e;
        n = this.d;
        bl = !bl;
        mediaPlayingActivity.a((PerformanceV2)object, bl2, true, null, -1, null, false, 0, null, n, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(int n, PlaybackPresenter.ClickSource clickSource) {
        if (!this.a(n)) {
            return;
        }
        MediaPlayingPlayable mediaPlayingPlayable = this.c.get(this.d);
        if (clickSource == PlaybackPresenter.ClickSource.d) {
            SingAnalytics.b((MediaPlayingPlayable)mediaPlayingPlayable);
        } else {
            SingAnalytics.a((PlaybackPresenter.ClickSource)clickSource, (MediaPlayingPlayable)mediaPlayingPlayable);
        }
        this.b();
    }

    @Override
    public void a(int n, String string2) {
        if (!this.a(n)) {
            return;
        }
        if (!this.c.get(this.d).c().equals(string2)) {
            Log.e(a, "cannot reset current item. Invalid performance key");
            return;
        }
        this.b(this.d, false);
    }

    @Override
    public void a(int n, boolean bl) {
        if (!this.a(n)) {
            return;
        }
        this.e = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(MediaPlayingFragment mediaPlayingFragment, int n) {
        boolean bl = true;
        if (!this.a(n)) {
            return;
        }
        n = this.d == 0 ? 1 : 0;
        boolean bl2 = this.d == this.c.size() - 1;
        MediaPlayingPlayable mediaPlayingPlayable = bl2 ? null : this.c.get(this.d + 1);
        boolean bl3 = n == 0;
        if (bl2) {
            bl = false;
        }
        mediaPlayingFragment.a(bl3, bl, mediaPlayingPlayable);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(MediaPlayingFragment mediaPlayingFragment, int n, boolean bl) {
        block3 : {
            block2 : {
                if (!this.a(n)) break block2;
                this.g = bl;
                if (this.g || !this.h) break block2;
                this.h = false;
                if (MagicPreferences.h(this.b.getApplicationContext())) break block3;
            }
            return;
        }
        SingAnalytics.a((MediaPlayingPlayable)this.c.get(this.d));
        this.b();
    }

    @Override
    public void a(@NonNull List<MediaPlayingPlayable> list, int n) {
        this.c = list;
        this.b(n, true);
    }

    @Override
    public void a(boolean bl) {
        if (bl) {
            SingAnalytics.L();
            return;
        }
        SingAnalytics.M();
    }

    @Override
    public void b(int n, PlaybackPresenter.ClickSource clickSource) {
        if (!this.a(n)) {
            return;
        }
        SingAnalytics.b((PlaybackPresenter.ClickSource)clickSource, (MediaPlayingPlayable)this.c.get(this.d));
        this.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void b(int n, String string2) {
        block6 : {
            block5 : {
                if (!this.a(n)) break block5;
                if (TextUtils.isEmpty((CharSequence)string2)) {
                    Log.d(a, "onPlaybackStart - playable ID should not be null or empty");
                    return;
                }
                if (!string2.equals(this.c.get(this.d).c())) {
                    Log.d(a, "onPlaybackStart - FIXME - index match but not the playable ID");
                    return;
                }
                if ("".equals(this.f)) break block6;
            }
            return;
        }
        this.f = string2;
        Log.a(a, "onPlaybackStart - continuous play restarted for item" + this.d + "/" + (this.c.size() - 1));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void c(int n, String string2) {
        block6 : {
            block5 : {
                if (!this.a(n)) break block5;
                if (TextUtils.isEmpty((CharSequence)string2)) {
                    Log.d(a, "onPlaybackStart - playable ID cannot be null or empty", new IllegalStateException());
                    return;
                }
                if (!string2.equals(this.f)) break block5;
                this.f = "";
                if (this.g) {
                    this.h = true;
                    return;
                }
                if (MagicPreferences.h(this.b.getApplicationContext())) break block6;
            }
            return;
        }
        SingAnalytics.a((MediaPlayingPlayable)this.c.get(this.d));
        this.b();
    }
}

