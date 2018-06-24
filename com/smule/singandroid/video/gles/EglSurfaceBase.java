package com.smule.singandroid.video.gles;

import android.annotation.TargetApi;
import android.opengl.EGL14;
import android.opengl.EGLSurface;
import com.smule.android.logging.Log;

@TargetApi(19)
public class EglSurfaceBase {
    protected static final String f25485a = GlUtil.f25490a;
    protected EglCore f25486b;
    private EGLSurface f25487c = EGL14.EGL_NO_SURFACE;
    private int f25488d = -1;
    private int f25489e = -1;

    protected EglSurfaceBase(EglCore eglCore) {
        this.f25486b = eglCore;
    }

    public void m26607a(Object obj) {
        if (this.f25487c != EGL14.EGL_NO_SURFACE) {
            throw new IllegalStateException("surface already created");
        }
        this.f25487c = this.f25486b.m26598a(obj);
    }

    public void m26605a() {
        this.f25486b.m26600a(this.f25487c);
        this.f25487c = EGL14.EGL_NO_SURFACE;
        this.f25489e = -1;
        this.f25488d = -1;
    }

    public void m26608b() {
        this.f25486b.m26603b(this.f25487c);
    }

    public boolean m26609c() {
        boolean c = this.f25486b.m26604c(this.f25487c);
        if (!c) {
            Log.b(f25485a, "WARNING: swapBuffers() failed");
        }
        return c;
    }

    public void m26606a(long j) {
        this.f25486b.m26601a(this.f25487c, j);
    }
}
