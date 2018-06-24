package com.smule.singandroid.video;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.opengl.Matrix;
import android.os.Build;
import com.mopub.volley.DefaultRetryPolicy;
import com.samsung.android.sdk.professionalaudio.SapaService;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import java.io.IOException;
import java.util.List;

public class CameraUtils {
    private static final String f25113a = CameraUtils.class.getSimpleName();
    private static CameraUtils f25114b;
    private Camera f25115c;
    private int f25116d;
    private CameraInfo f25117e;
    private Parameters f25118f;
    private Config f25119g;
    private boolean f25120h;

    public class Config {
        public int f25108a;
        public int f25109b;
        public int f25110c;
        public boolean f25111d;
        final /* synthetic */ CameraUtils f25112e;

        public Config(CameraUtils cameraUtils) {
            this.f25112e = cameraUtils;
        }

        public String toString() {
            return "mOrientation:" + this.f25108a + " mWidth:" + this.f25109b + " mHeight:" + this.f25110c + " mFront:" + this.f25111d;
        }
    }

    public static CameraUtils m26202a() {
        if (f25114b == null) {
            f25114b = new CameraUtils();
        }
        return f25114b;
    }

    private static boolean m26210d() {
        for (Object equals : SingApplication.g().getResources().getStringArray(C1947R.array.samsung_s4_models)) {
            if (Build.MODEL.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public static void m26206a(Parameters parameters, int i, Point point) {
        int i2;
        int i3;
        Size preferredPreviewSizeForVideo = parameters.getPreferredPreviewSizeForVideo();
        if (preferredPreviewSizeForVideo != null || point.x <= 0 || point.y <= 0) {
            i2 = preferredPreviewSizeForVideo.width;
            i3 = preferredPreviewSizeForVideo.height;
            Log.b(f25113a, "Camera preferred preview size for video is " + preferredPreviewSizeForVideo.width + "x" + preferredPreviewSizeForVideo.height);
        } else {
            if (i == 90 || i == 270) {
                i2 = point.y;
                i3 = point.x;
            } else {
                i2 = point.x;
                i3 = point.y;
            }
            Log.d(f25113a, "No preferred preview size. Matching screen resolution " + i2 + "x" + i3);
        }
        if (m26210d()) {
            i2 = 1920;
            i3 = 1080;
        }
        m26205a(parameters, i2, i3, (int) SapaService.Parameters.BUFFER_SIZE_480);
    }

    private static void m26205a(Parameters parameters, int i, int i2, int i3) {
        int i4 = Integer.MAX_VALUE;
        int i5 = -1;
        int i6 = -1;
        for (Size size : parameters.getSupportedPreviewSizes()) {
            if (size.height >= i3 && size.width >= i3) {
                int i7;
                if (size.height * i == size.width * i2) {
                    int min = Math.min(size.width, size.height);
                    if (min - i3 < i4) {
                        i6 = size.width;
                        i5 = size.height;
                        i7 = min - i3;
                        i4 = i5;
                        i5 = i6;
                        i6 = i5;
                        i5 = i4;
                        i4 = i7;
                    }
                }
                i7 = i4;
                i4 = i5;
                i5 = i6;
                i6 = i5;
                i5 = i4;
                i4 = i7;
            }
        }
        if (i6 <= -1 || i5 <= -1) {
            Log.d(f25113a, "Unable to find suitable preview size. Using default.");
        } else {
            parameters.setPreviewSize(i6, i5);
        }
    }

    public static void m26204a(Parameters parameters) {
        int[] iArr = new int[2];
        parameters.getPreviewFpsRange(iArr);
        Log.b(f25113a, "choosePreviewFps:cur:" + iArr[0] + "-" + iArr[1]);
        List supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.isEmpty()) {
            Log.b(f25113a, "choosePreviewFps: ranges not available");
            return;
        }
        int[] iArr2 = (int[]) supportedPreviewFpsRange.get(0);
        int i = -1;
        for (int i2 = 0; i2 < supportedPreviewFpsRange.size(); i2++) {
            iArr = (int[]) supportedPreviewFpsRange.get(i2);
            int i3 = iArr[1] == 30000 ? 200 : iArr[1] > 15000 ? 100 : 0;
            int min = Math.min((iArr[1] - iArr[0]) / 1000, 99);
            if (min > 0) {
                i3 += min;
            }
            if (i3 > i) {
                iArr2 = iArr;
                i = i3;
            }
        }
        Log.b(f25113a, "choosePreviewFps:score:" + i + " " + iArr2[0] + "-" + iArr2[1]);
        parameters.setPreviewFpsRange(iArr2[0], iArr2[1]);
    }

    public static void m26208b(Parameters parameters) {
        for (Size size : parameters.getSupportedPreviewSizes()) {
            Log.b(f25113a, "supported: " + size.width + "x" + size.height);
        }
    }

    public static void m26209c(Parameters parameters) {
        List supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        for (int i = 0; i < supportedPreviewFpsRange.size(); i++) {
            Log.b(f25113a, "fps[" + i + "]:" + ((int[]) supportedPreviewFpsRange.get(i))[0] + "," + ((int[]) supportedPreviewFpsRange.get(i))[1]);
        }
    }

    public static String m26203a(String str) {
        return str + ".mp4";
    }

    public Config m26211a(boolean z, boolean z2, int i, Point point) {
        Log.b(f25113a, "start+ front:" + z);
        if (this.f25115c != null) {
            throw new RuntimeException("camera already initialized");
        }
        this.f25117e = new CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        this.f25116d = 0;
        while (this.f25116d < numberOfCameras) {
            Camera.getCameraInfo(this.f25116d, this.f25117e);
            if ((this.f25117e.facing == 1) == z) {
                this.f25115c = Camera.open(this.f25116d);
                break;
            }
            this.f25116d++;
        }
        if (this.f25115c == null) {
            Log.b(f25113a, "No matching camera found for front-facing:" + z + ". Opening default.");
            this.f25115c = Camera.open();
            this.f25116d = 0;
        }
        if (this.f25115c == null) {
            throw new RuntimeException("Unable to open camera");
        }
        String str;
        this.f25119g = new Config(this);
        this.f25118f = this.f25115c.getParameters();
        m26208b(this.f25118f);
        m26209c(this.f25118f);
        m26206a(this.f25118f, i, point);
        m26204a(this.f25118f);
        Size previewSize = this.f25118f.getPreviewSize();
        this.f25118f.setRecordingHint(true);
        if (z2) {
            Log.b(f25113a, "forceRotate:displayRotation:" + i);
            m26212a(i);
        }
        Log.b(f25113a, "orientation:" + this.f25117e.orientation);
        this.f25119g.f25108a = this.f25117e.orientation;
        this.f25115c.setParameters(this.f25118f);
        int[] iArr = new int[2];
        this.f25118f.getPreviewFpsRange(iArr);
        String str2 = previewSize.width + "x" + previewSize.height;
        if (iArr[0] == iArr[1]) {
            str = str2 + " @" + (((double) iArr[0]) / 1000.0d) + "fps";
        } else {
            str = str2 + " @[" + (((double) iArr[0]) / 1000.0d) + " - " + (((double) iArr[1]) / 1000.0d) + "] fps";
        }
        Log.c(f25113a, "Preview: " + str);
        this.f25120h = z;
        this.f25119g.f25109b = previewSize.width;
        this.f25119g.f25110c = previewSize.height;
        this.f25119g.f25111d = this.f25120h;
        Log.b(f25113a, "config:" + this.f25119g);
        Log.b(f25113a, "start-");
        return this.f25119g;
    }

    public void m26212a(int i) {
        if (this.f25115c == null) {
            Log.e(f25113a, "setCameraDisplayOrientation:camera uninitialized, ignoring");
            return;
        }
        int i2;
        if (this.f25117e.facing == 1) {
            i2 = (360 - ((this.f25117e.orientation + i) % 360)) % 360;
        } else {
            i2 = ((this.f25117e.orientation - i) + 360) % 360;
        }
        Log.b(f25113a, "setCameraDisplayOrientation:mInfo.orientation:" + this.f25117e.orientation + " displayRotation:" + i + " result:" + i2);
        this.f25115c.setDisplayOrientation(i2);
    }

    public void m26214b() {
        Log.b(f25113a, "shutdown+");
        if (this.f25115c != null) {
            this.f25115c.stopPreview();
            this.f25115c.release();
            this.f25115c = null;
            Log.b(f25113a, "shutdown: released");
        }
        Log.b(f25113a, "shutdown-");
    }

    public void m26213a(SurfaceTexture surfaceTexture) throws IOException {
        Log.b(f25113a, "startPreview+");
        if (this.f25115c != null) {
            this.f25115c.setPreviewTexture(surfaceTexture);
            this.f25115c.startPreview();
        }
        Log.b(f25113a, "startPreview-");
    }

    public final Config m26215c() {
        return this.f25119g;
    }

    public static int m26201a(Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    public static void m26207a(float[] fArr, boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        float f;
        Log.b(f25113a, "setTransformMatrix:front:" + z + " orientation:" + i + " displayRotation:" + i2 + " incoming=" + i3 + "x" + i4 + " output=" + i5 + "x" + i6);
        if (z) {
            i7 = i + i2;
        } else {
            i7 = ((i - i2) + 360) % 360;
        }
        float f2 = ((float) i5) / ((float) i6);
        if (i7 % 180 == 0) {
            f = ((float) i3) / ((float) i4);
        } else {
            f = ((float) i4) / ((float) i3);
        }
        Matrix.setIdentityM(fArr, 0);
        Matrix.translateM(fArr, 0, 0.5f, 0.5f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        if (!z) {
            Matrix.scaleM(fArr, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        Matrix.rotateM(fArr, 0, (float) i7, 0.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        if (f2 > f) {
            Log.b(f25113a, "scale:Y:" + (f / f2));
            Matrix.scaleM(fArr, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, f / f2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        } else {
            Log.b(f25113a, "scale:X:" + (f2 / f));
            Matrix.scaleM(fArr, 0, f2 / f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        Matrix.translateM(fArr, 0, -0.5f, -0.5f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }
}
