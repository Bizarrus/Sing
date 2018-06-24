package com.smule.singandroid.video.gles;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import com.smule.android.logging.Log;

public class ExternalTexture extends Texture2dProgram {
    private static final String f25475a = ExternalTexture.class.getSimpleName();
    private int f25476d = GlUtil.m26611a(m26592b(), mo7018a());
    private int f25477e;
    private int f25478f = 36197;

    protected String m26592b() {
        return "uniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n";
    }

    protected String mo7018a() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    }

    public ExternalTexture() {
        if (this.f25476d == 0) {
            throw new RuntimeException("Unable to create program");
        }
        Log.b(f25475a, "Created program " + this.f25476d);
        GLES20.glUseProgram(this.f25476d);
        GlUtil.m26613a("glUseProgram");
        GLES20.glActiveTexture(33984);
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.f25476d, "aPosition");
        GlUtil.m26614b(glGetAttribLocation, "aPosition");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        GlUtil.m26613a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(glGetAttribLocation, 2, 5126, false, 0, b);
        GlUtil.m26613a("glVertexAttribPointer");
        glGetAttribLocation = GLES20.glGetAttribLocation(this.f25476d, "aTextureCoord");
        GlUtil.m26614b(glGetAttribLocation, "aTextureCoord");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        GlUtil.m26613a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(glGetAttribLocation, 2, 5126, false, 0, c);
        GlUtil.m26613a("glVertexAttribPointer");
        this.f25477e = GLES20.glGetUniformLocation(this.f25476d, "uTexMatrix");
        GlUtil.m26614b(this.f25477e, "uTexMatrix");
    }

    public void mo7016c() {
        Log.b(f25475a, "deleting program " + this.f25476d);
        GLES20.glDeleteProgram(this.f25476d);
        this.f25476d = -1;
    }

    public int mo7017d() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GlUtil.m26613a("glGenTextures");
        int i = iArr[0];
        GLES20.glBindTexture(this.f25478f, i);
        GlUtil.m26613a("glBindTexture " + i);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        GlUtil.m26613a("glTexParameter");
        GLES20.glBindTexture(3553, 0);
        GlUtil.m26613a("glBindTexture");
        return i;
    }

    public void mo7015a(float[] fArr, int i) {
        GlUtil.m26613a("draw start");
        GLES20.glUseProgram(this.f25476d);
        GlUtil.m26613a("glUseProgram");
        GLES20.glActiveTexture(33984);
        GlUtil.m26613a("glActiveTexture");
        GLES20.glBindTexture(this.f25478f, i);
        GlUtil.m26613a("glBindTexture");
        GLES20.glUniformMatrix4fv(this.f25477e, 1, false, fArr, 0);
        GlUtil.m26613a("glUniformMatrix4fv");
        GLES20.glDrawArrays(5, 0, 4);
        GlUtil.m26613a("glDrawArrays");
        GLES20.glBindTexture(this.f25478f, 0);
        GlUtil.m26613a("glBindTexture");
    }
}
