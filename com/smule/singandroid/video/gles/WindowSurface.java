package com.smule.singandroid.video.gles;

import android.graphics.SurfaceTexture;
import android.view.Surface;

public class WindowSurface extends EglSurfaceBase {
    private Surface f25497c;
    private boolean f25498d;

    public WindowSurface(EglCore eglCore, Surface surface, boolean z) {
        super(eglCore);
        m26607a((Object) surface);
        this.f25497c = surface;
        this.f25498d = z;
    }

    public WindowSurface(EglCore eglCore, SurfaceTexture surfaceTexture) {
        super(eglCore);
        m26607a((Object) surfaceTexture);
    }

    public void m26616d() {
        m26605a();
        if (this.f25497c != null) {
            if (this.f25498d) {
                this.f25497c.release();
            }
            this.f25497c = null;
        }
    }

    public void m26615a(EglCore eglCore) {
        if (this.f25497c == null) {
            throw new RuntimeException("not yet implemented for SurfaceTexture");
        }
        this.b = eglCore;
        m26607a((Object) this.f25497c);
    }
}
