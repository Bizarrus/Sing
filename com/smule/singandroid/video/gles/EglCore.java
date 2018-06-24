package com.smule.singandroid.video.gles;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;
import com.smule.android.logging.Log;

@TargetApi(19)
public final class EglCore {
    private static final String f25480a = GlUtil.f25490a;
    private EGLDisplay f25481b;
    private EGLContext f25482c;
    private EGLConfig f25483d;
    private int f25484e;

    public EglCore() {
        this(null, 0);
    }

    public EglCore(EGLContext eGLContext, int i) {
        this.f25481b = EGL14.EGL_NO_DISPLAY;
        this.f25482c = EGL14.EGL_NO_CONTEXT;
        this.f25483d = null;
        this.f25484e = -1;
        if (this.f25481b != EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("EGL already set up");
        }
        if (eGLContext == null) {
            eGLContext = EGL14.EGL_NO_CONTEXT;
        }
        this.f25481b = EGL14.eglGetDisplay(0);
        if (this.f25481b == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        int[] iArr = new int[2];
        if (EGL14.eglInitialize(this.f25481b, iArr, 0, iArr, 1)) {
            EGLConfig a;
            EGLContext eglCreateContext;
            if ((i & 2) != 0) {
                a = m26596a(i, 3);
                if (a != null) {
                    eglCreateContext = EGL14.eglCreateContext(this.f25481b, a, eGLContext, new int[]{12440, 3, 12344}, 0);
                    if (EGL14.eglGetError() == 12288) {
                        this.f25483d = a;
                        this.f25482c = eglCreateContext;
                        this.f25484e = 3;
                    }
                }
            }
            if (this.f25482c == EGL14.EGL_NO_CONTEXT) {
                a = m26596a(i, 2);
                if (a == null) {
                    throw new RuntimeException("Unable to find a suitable EGLConfig");
                }
                eglCreateContext = EGL14.eglCreateContext(this.f25481b, a, eGLContext, new int[]{12440, 2, 12344}, 0);
                m26597a("eglCreateContext");
                this.f25483d = a;
                this.f25482c = eglCreateContext;
                this.f25484e = 2;
            }
            iArr = new int[1];
            EGL14.eglQueryContext(this.f25481b, this.f25482c, 12440, iArr, 0);
            Log.b(f25480a, "EGLContext created, client version " + iArr[0]);
            return;
        }
        this.f25481b = null;
        throw new RuntimeException("unable to initialize EGL14");
    }

    private EGLConfig m26596a(int i, int i2) {
        int i3;
        if (i2 >= 3) {
            i3 = 68;
        } else {
            i3 = 4;
        }
        int[] iArr = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, i3, 12344, 0, 12344};
        if ((i & 1) != 0) {
            iArr[iArr.length - 3] = 12610;
            iArr[iArr.length - 2] = 1;
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglChooseConfig(this.f25481b, iArr, 0, eGLConfigArr, 0, eGLConfigArr.length, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        Log.d(f25480a, "unable to find RGB8888 / " + i2 + " EGLConfig");
        return null;
    }

    public void m26599a() {
        if (this.f25481b != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglMakeCurrent(this.f25481b, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            EGL14.eglDestroyContext(this.f25481b, this.f25482c);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.f25481b);
        }
        this.f25481b = EGL14.EGL_NO_DISPLAY;
        this.f25482c = EGL14.EGL_NO_CONTEXT;
        this.f25483d = null;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.f25481b != EGL14.EGL_NO_DISPLAY) {
                Log.d(f25480a, "WARNING: EglCore was not explicitly released -- state may be leaked");
                m26599a();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public void m26600a(EGLSurface eGLSurface) {
        EGL14.eglDestroySurface(this.f25481b, eGLSurface);
    }

    public EGLSurface m26598a(Object obj) {
        if ((obj instanceof Surface) || (obj instanceof SurfaceTexture)) {
            EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(this.f25481b, this.f25483d, obj, new int[]{12344}, 0);
            m26597a("eglCreateWindowSurface");
            if (eglCreateWindowSurface != null) {
                return eglCreateWindowSurface;
            }
            throw new RuntimeException("surface was null");
        }
        throw new RuntimeException("invalid surface: " + obj);
    }

    public void m26603b(EGLSurface eGLSurface) {
        if (this.f25481b == EGL14.EGL_NO_DISPLAY) {
            Log.b(f25480a, "NOTE: makeCurrent w/o display");
        }
        if (!EGL14.eglMakeCurrent(this.f25481b, eGLSurface, eGLSurface, this.f25482c)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public void m26602b() {
        if (!EGL14.eglMakeCurrent(this.f25481b, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public boolean m26604c(EGLSurface eGLSurface) {
        return EGL14.eglSwapBuffers(this.f25481b, eGLSurface);
    }

    public void m26601a(EGLSurface eGLSurface, long j) {
        EGLExt.eglPresentationTimeANDROID(this.f25481b, eGLSurface, j);
    }

    private void m26597a(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError != 12288) {
            throw new RuntimeException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
        }
    }
}
