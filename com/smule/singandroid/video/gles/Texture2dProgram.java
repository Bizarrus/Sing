package com.smule.singandroid.video.gles;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import java.nio.FloatBuffer;

public class Texture2dProgram {
    private static final String f25460a = Texture2dProgram.class.getSimpleName();
    public static final FloatBuffer f25461b = GlUtil.m26612a(new float[]{-1.0f, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, -1.0f, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT});
    public static final FloatBuffer f25462c = GlUtil.m26612a(new float[]{0.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT});
    private ProgramType f25463d;
    private int f25464e;
    private int f25465f;
    private int f25466g;
    private int f25467h;
    private int f25468i;
    private int f25469j;
    private int f25470k;
    private int f25471l;
    private float[] f25472m;
    private float[] f25473n;
    private float f25474o;

    public enum ProgramType {
        TEXTURE_EXT,
        TEXTURE_EXT_BW,
        TEXTURE_EXT_FILT
    }

    public Texture2dProgram() {
        this(ProgramType.TEXTURE_EXT, null, 0.0f, 0, 0);
    }

    public Texture2dProgram(ProgramType programType, float[] fArr, float f, int i, int i2) {
        this.f25472m = new float[9];
        this.f25463d = programType;
        switch (programType) {
            case TEXTURE_EXT:
                this.f25471l = 36197;
                this.f25464e = GlUtil.m26611a("uniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
                break;
            case TEXTURE_EXT_BW:
                this.f25471l = 36197;
                this.f25464e = GlUtil.m26611a("uniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec4 tc = texture2D(sTexture, vTextureCoord);\n    float color = tc.r * 0.3 + tc.g * 0.59 + tc.b * 0.11;\n    gl_FragColor = vec4(color, color, color, 1.0);\n}\n");
                break;
            case TEXTURE_EXT_FILT:
                this.f25471l = 36197;
                this.f25464e = GlUtil.m26611a("uniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\n#define KERNEL_SIZE 9\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float uKernel[KERNEL_SIZE];\nuniform vec2 uTexOffset[KERNEL_SIZE];\nuniform float uColorAdjust;\nvoid main() {\n    int i = 0;\n    vec4 sum = vec4(0.0);\n        for (i = 0; i < KERNEL_SIZE; i++) {\n            vec4 texc = texture2D(sTexture, vTextureCoord + uTexOffset[i]);\n            sum += texc * uKernel[i];\n        }\n        sum += uColorAdjust;\n    gl_FragColor = sum;\n}\n");
                break;
            default:
                throw new RuntimeException("Unhandled type " + programType);
        }
        if (this.f25464e == 0) {
            throw new RuntimeException("Unable to create program");
        }
        Log.b(f25460a, "Created program " + this.f25464e + " (" + programType + ")");
        GLES20.glUseProgram(this.f25464e);
        GlUtil.m26613a("glUseProgram");
        GLES20.glActiveTexture(33984);
        this.f25469j = GLES20.glGetAttribLocation(this.f25464e, "aPosition");
        GlUtil.m26614b(this.f25469j, "aPosition");
        GLES20.glEnableVertexAttribArray(this.f25469j);
        GlUtil.m26613a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.f25469j, 2, 5126, false, 0, f25461b);
        GlUtil.m26613a("glVertexAttribPointer");
        this.f25470k = GLES20.glGetAttribLocation(this.f25464e, "aTextureCoord");
        GlUtil.m26614b(this.f25470k, "aTextureCoord");
        GLES20.glEnableVertexAttribArray(this.f25470k);
        GlUtil.m26613a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.f25470k, 2, 5126, false, 0, f25462c);
        GlUtil.m26613a("glVertexAttribPointer");
        this.f25465f = GLES20.glGetUniformLocation(this.f25464e, "uTexMatrix");
        GlUtil.m26614b(this.f25465f, "uTexMatrix");
        this.f25466g = GLES20.glGetUniformLocation(this.f25464e, "uKernel");
        if (this.f25466g < 0) {
            this.f25466g = -1;
            this.f25467h = -1;
            this.f25468i = -1;
            return;
        }
        this.f25467h = GLES20.glGetUniformLocation(this.f25464e, "uTexOffset");
        GlUtil.m26614b(this.f25467h, "uTexOffset");
        this.f25468i = GLES20.glGetUniformLocation(this.f25464e, "uColorAdjust");
        GlUtil.m26614b(this.f25468i, "uColorAdjust");
        m26585a(i, i2);
        m26586a(fArr, f);
    }

    public void mo7016c() {
        Log.b(f25460a, "deleting program " + this.f25464e);
        GLES20.glDeleteProgram(this.f25464e);
        this.f25464e = -1;
    }

    public int mo7017d() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GlUtil.m26613a("glGenTextures");
        int i = iArr[0];
        GLES20.glBindTexture(this.f25471l, i);
        GlUtil.m26613a("glBindTexture " + i);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        GlUtil.m26613a("glTexParameter");
        return i;
    }

    public void m26586a(float[] fArr, float f) {
        if (fArr.length != 9) {
            throw new IllegalArgumentException("Kernel size is " + fArr.length + " vs. " + 9);
        }
        System.arraycopy(fArr, 0, this.f25472m, 0, 9);
        this.f25474o = f;
        GLES20.glUniform1fv(this.f25466g, 9, this.f25472m, 0);
        GLES20.glUniform1f(this.f25468i, this.f25474o);
    }

    private void m26585a(int i, int i2) {
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / ((float) i);
        float f2 = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / ((float) i2);
        this.f25473n = new float[]{-f, -f2, 0.0f, -f2, f, -f2, -f, 0.0f, 0.0f, 0.0f, f, 0.0f, -f, f2, 0.0f, f2, f, f2};
        GLES20.glUniform2fv(this.f25467h, 9, this.f25473n, 0);
    }

    public void mo7015a(float[] fArr, int i) {
        GlUtil.m26613a("draw start");
        GLES20.glBindTexture(this.f25471l, i);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        GLES20.glUniformMatrix4fv(this.f25465f, 1, false, fArr, 0);
        GlUtil.m26613a("glUniformMatrix4fv");
        GLES20.glDrawArrays(5, 0, 4);
        GlUtil.m26613a("glDrawArrays");
        GLES20.glBindTexture(this.f25471l, 0);
    }
}
