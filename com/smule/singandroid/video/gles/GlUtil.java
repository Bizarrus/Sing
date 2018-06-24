package com.smule.singandroid.video.gles;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.smule.android.logging.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GlUtil {
    public static final String f25490a = (GlUtil.class.getSimpleName() + ":VIDEO");
    public static final float[] f25491b = new float[16];

    static {
        Matrix.setIdentityM(f25491b, 0);
    }

    private GlUtil() {
    }

    public static int m26611a(String str, String str2) {
        int a = m26610a(35633, str);
        if (a == 0) {
            return 0;
        }
        int a2 = m26610a(35632, str2);
        if (a2 == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        m26613a("glCreateProgram");
        if (glCreateProgram == 0) {
            Log.e(f25490a, "Could not create program");
        }
        GLES20.glAttachShader(glCreateProgram, a);
        m26613a("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, a2);
        m26613a("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] == 1) {
            return glCreateProgram;
        }
        Log.e(f25490a, "Could not link program: ");
        Log.e(f25490a, GLES20.glGetProgramInfoLog(glCreateProgram));
        GLES20.glDeleteProgram(glCreateProgram);
        return 0;
    }

    public static int m26610a(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        m26613a("glCreateShader type=" + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        Log.e(f25490a, "Could not compile shader " + i + ":");
        Log.e(f25490a, " " + GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    public static void m26613a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            String str2 = str + ": glError 0x" + Integer.toHexString(glGetError);
            Log.e(f25490a, str2);
            throw new RuntimeException(str2);
        }
    }

    public static void m26614b(int i, String str) {
        if (i < 0) {
            throw new RuntimeException("Unable to locate '" + str + "' in program");
        }
    }

    public static FloatBuffer m26612a(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }
}
