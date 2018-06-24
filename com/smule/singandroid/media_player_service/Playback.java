package com.smule.singandroid.media_player_service;

import android.content.Context;
import android.text.TextUtils;
import android.view.TextureView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.singandroid.utils.MediaPlaybackUtils;

public abstract class Playback {
    public final String f23447a = Playback.class.getName();
    float f23448b = 0.2f;
    float f23449c = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    int f23450d = 0;
    int f23451e = 1;
    int f23452f = 2;
    protected final Context f23453g;
    protected Callback f23454h;
    protected boolean f23455i;
    private volatile int f23456j;
    private QueueItem f23457k;
    private volatile long f23458l;
    private int f23459m = this.f23450d;
    private boolean f23460n;

    public interface Callback {
        void mo6889a(int i);

        void mo6890b(int i);
    }

    public abstract void mo6971a();

    public abstract void mo6972a(float f);

    public abstract void mo6973a(long j);

    public abstract void mo6975a(boolean z);

    public abstract void mo6976b();

    public abstract boolean mo6977d();

    public abstract boolean mo6978e();

    public abstract long mo6979f();

    public abstract long mo6980g();

    protected abstract void mo6981h();

    public abstract void mo6982i();

    public Playback(Context context, Callback callback) {
        this.f23453g = context;
        m24692a(0);
        m24696a(callback);
    }

    public void m24693a(int i, boolean z) {
        Log.b(this.f23447a, "setState: " + MediaPlaybackUtils.m25872a(i));
        this.f23456j = i;
        if (this.f23454h != null) {
            this.f23454h.mo6889a(m24703c());
        }
    }

    public void m24692a(int i) {
        m24693a(i, true);
    }

    public int m24703c() {
        return this.f23456j;
    }

    public final void m24697a(QueueItem queueItem, boolean z) {
        Object obj = !TextUtils.equals(queueItem.m24731b(), m24713k()) ? 1 : null;
        if (obj != null) {
            m24701b(0);
        }
        this.f23457k = queueItem;
        if (m24703c() != 2 || obj != null) {
            this.f23455i = z;
            mo6981h();
        } else if (z) {
            mo6971a();
        }
    }

    public void m24701b(long j) {
        this.f23458l = j;
    }

    public long m24712j() {
        return this.f23458l;
    }

    public String m24713k() {
        return this.f23457k != null ? this.f23457k.m24731b() : null;
    }

    public QueueItem m24714l() {
        return this.f23457k;
    }

    public void m24696a(Callback callback) {
        this.f23454h = callback;
    }

    public void m24700b(int i) {
        Log.b(this.f23447a, "onAudioFocusChange. focusChange=" + i);
        if (i == 1) {
            this.f23459m = this.f23452f;
        } else if (i == -1 || i == -2 || i == -3) {
            boolean z = i == -3;
            this.f23459m = z ? this.f23451e : this.f23450d;
            if (this.f23456j == 3 && !z) {
                m24705d(true);
            }
        } else {
            Log.e(this.f23447a, "onAudioFocusChange: Ignoring unsupported focusChange: " + i);
        }
        m24704c(false);
    }

    public void m24702b(boolean z) {
        this.f23459m = z ? this.f23452f : this.f23450d;
    }

    protected void m24704c(boolean z) {
        Log.b(this.f23447a, "configMediaPlayerState. mAudioFocus=" + this.f23459m);
        if (this.f23459m != this.f23450d) {
            if (this.f23459m == this.f23451e) {
                mo6972a(this.f23448b);
            } else {
                mo6972a(this.f23449c);
            }
            if (m24715m() || z) {
                Log.b(this.f23447a, "configMediaPlayerState startMediaPlayer. seeking to " + this.f23458l);
                if (this.f23458l == mo6980g()) {
                    mo6971a();
                } else {
                    mo6973a(this.f23458l);
                }
                if (m24715m()) {
                    m24705d(false);
                }
            }
        } else if (m24703c() == 3) {
            mo6982i();
        }
    }

    public void mo6974a(TextureView textureView) {
    }

    protected void m24705d(boolean z) {
        this.f23460n = z;
    }

    protected boolean m24715m() {
        return false;
    }
}
