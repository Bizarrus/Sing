/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.view.TextureView
 *  com.smule.singandroid.utils.MediaPlaybackUtils
 */
package com.smule.singandroid.media_player_service;

import android.content.Context;
import android.text.TextUtils;
import android.view.TextureView;
import com.smule.android.logging.Log;
import com.smule.singandroid.media_player_service.QueueItem;
import com.smule.singandroid.utils.MediaPlaybackUtils;

public abstract class Playback {
    public final String a = Playback.class.getName();
    float b = 0.2f;
    float c = 1.0f;
    int d = 0;
    int e = 1;
    int f = 2;
    protected final Context g;
    protected Callback h;
    protected boolean i;
    private volatile int j;
    private QueueItem k;
    private volatile long l;
    private int m = this.d;
    private boolean n;

    public Playback(Context context, Callback callback) {
        this.g = context;
        this.a(0);
        this.a(callback);
    }

    public abstract void a();

    public abstract void a(float var1);

    public void a(int n) {
        this.a(n, true);
    }

    public void a(int n, boolean bl) {
        Log.b(this.a, "setState: " + MediaPlaybackUtils.a((int)n));
        this.j = n;
        if (this.h != null) {
            this.h.a(this.c());
        }
    }

    public abstract void a(long var1);

    public abstract void a(TextureView var1);

    public void a(Callback callback) {
        this.h = callback;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void a(QueueItem queueItem, boolean bl) {
        boolean bl2 = !TextUtils.equals((CharSequence)queueItem.b(), (CharSequence)this.k());
        if (bl2) {
            this.b(0);
        }
        this.k = queueItem;
        if (this.c() == 2 && !bl2) {
            if (bl) {
                this.a();
            }
            return;
        }
        this.i = bl;
        this.h();
    }

    public abstract void a(boolean var1);

    public abstract void b();

    /*
     * Enabled aggressive block sorting
     */
    public void b(int n) {
        Log.b(this.a, "onAudioFocusChange. focusChange=" + n);
        if (n == 1) {
            this.m = this.f;
        } else if (n == -1 || n == -2 || n == -3) {
            n = n == -3 ? 1 : 0;
            int n2 = n != 0 ? this.e : this.d;
            this.m = n2;
            if (this.j == 3 && n == 0) {
                this.d(true);
            }
        } else {
            Log.e(this.a, "onAudioFocusChange: Ignoring unsupported focusChange: " + n);
        }
        this.c(false);
    }

    public void b(long l) {
        this.l = l;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b(boolean bl) {
        int n = bl ? this.f : this.d;
        this.m = n;
    }

    public int c() {
        return this.j;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c(boolean bl) {
        Log.b(this.a, "configMediaPlayerState. mAudioFocus=" + this.m);
        if (this.m == this.d) {
            if (this.c() != 3) return;
            {
                this.i();
                return;
            }
        } else {
            if (this.m == this.e) {
                this.a(this.b);
            } else {
                this.a(this.c);
            }
            if (!this.m() && !bl) return;
            {
                Log.b(this.a, "configMediaPlayerState startMediaPlayer. seeking to " + this.l);
                if (this.l == this.g()) {
                    this.a();
                } else {
                    this.a(this.l);
                }
                if (!this.m()) return;
                {
                    this.d(false);
                    return;
                }
            }
        }
    }

    protected void d(boolean bl) {
        this.n = bl;
    }

    public abstract boolean d();

    public abstract boolean e();

    public abstract long f();

    public abstract long g();

    protected abstract void h();

    public abstract void i();

    public long j() {
        return this.l;
    }

    public String k() {
        if (this.k != null) {
            return this.k.b();
        }
        return null;
    }

    public QueueItem l() {
        return this.k;
    }

    protected boolean m() {
        return false;
    }

    public static interface Callback {
        public void a(int var1);

        public void b(int var1);
    }

}

