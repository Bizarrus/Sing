package com.smule.singandroid.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.graphics.PointF;
import com.mopub.volley.DefaultRetryPolicy;
import com.samsung.android.sdk.professionalaudio.SapaService.Parameters;
import com.smule.singandroid.C1947R;
import jp.co.cyberagent.android.gpuimage.GPUImageBoxBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLookupFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageMaskBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSobelEdgeDetection;
import jp.co.cyberagent.android.gpuimage.GPUImageStepFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSwitchableFilterChain;

public class VideoFilterDatabase {
    public static Point f25443a = null;
    private static int f25444b = 360;
    private static int f25445c = Parameters.BUFFER_SIZE_480;
    private static float f25446d = 0.3f;
    private static float f25447e = 0.6f;
    private static float f25448f = 2.0f;
    private static float f25449g = 0.1f;
    private static float f25450h = 0.73f;
    private static float f25451i = 2.0f;
    private static float f25452j = 2.0f;
    private static float f25453k = 0.175f;
    private static float f25454l = 0.5f;
    private static float f25455m = 2.0f;

    public enum FilterType {
        NORMAL,
        SEPIA,
        BLACK_AND_WHITE,
        VINTAGE,
        SELFIE,
        FIGHTCLUB
    }

    public static FilterType[] m26569a() {
        return FilterType.values();
    }

    private static Bitmap m26566a(Context context, int i) {
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        return BitmapFactory.decodeResource(context.getResources(), i, options);
    }

    private static GPUImageFilterGroup m26571c() {
        int i = f25444b;
        int i2 = f25445c;
        GPUImageFilterGroup gPUImageFilterGroup = new GPUImageFilterGroup();
        GPUImageFilter gPUImageFilter = new GPUImageFilter();
        gPUImageFilterGroup.m27281b(gPUImageFilter);
        GPUImageFilter gPUImageBoxBlurFilter = new GPUImageBoxBlurFilter();
        gPUImageBoxBlurFilter.m26451c(i, i2);
        gPUImageFilterGroup.m27281b(gPUImageBoxBlurFilter);
        gPUImageBoxBlurFilter.m27293a(2.0f);
        gPUImageFilter.mo6993a(gPUImageBoxBlurFilter);
        GPUImageFilter gPUImageSobelEdgeDetection = new GPUImageSobelEdgeDetection();
        gPUImageFilterGroup.m27281b(gPUImageSobelEdgeDetection);
        gPUImageSobelEdgeDetection.m26451c(i, i2);
        gPUImageSobelEdgeDetection.m27434a(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / (((float) i) * 1.9f));
        gPUImageSobelEdgeDetection.m27435b(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / (((float) i2) * 1.9f));
        gPUImageBoxBlurFilter.mo6993a(gPUImageSobelEdgeDetection);
        GPUImageFilter gPUImageMaskBlendFilter = new GPUImageMaskBlendFilter();
        gPUImageFilterGroup.m27281b(gPUImageMaskBlendFilter);
        gPUImageFilter.mo6994a(gPUImageMaskBlendFilter, 0);
        gPUImageBoxBlurFilter.mo6994a(gPUImageMaskBlendFilter, 1);
        gPUImageSobelEdgeDetection.mo6994a(gPUImageMaskBlendFilter, 2);
        gPUImageFilterGroup.m27283c(gPUImageFilter);
        gPUImageFilterGroup.m27285d(gPUImageMaskBlendFilter);
        return gPUImageFilterGroup;
    }

    private static PointF m26572d() {
        if (f25443a == null) {
            return new PointF((float) f25444b, (float) f25445c);
        }
        float f = ((float) f25444b) / 3.0f;
        return new PointF(f, f / (((float) f25443a.x) / ((float) f25443a.y)));
    }

    public static GPUImageFilterGroup m26570b() {
        GPUImageFilterGroup gPUImageFilterGroup = new GPUImageFilterGroup();
        GPUImageFilter gPUImageFilter = new GPUImageFilter();
        gPUImageFilterGroup.m27281b(gPUImageFilter);
        GPUImageFilter gPUImageSobelEdgeDetection = new GPUImageSobelEdgeDetection();
        gPUImageFilterGroup.m27281b(gPUImageSobelEdgeDetection);
        gPUImageFilter.mo6993a(gPUImageSobelEdgeDetection);
        GPUImageFilter gPUImageStepFilter = new GPUImageStepFilter(f25446d, f25447e);
        gPUImageFilterGroup.m27281b(gPUImageStepFilter);
        gPUImageSobelEdgeDetection.mo6993a(gPUImageStepFilter);
        gPUImageSobelEdgeDetection = new GPUImageGaussianBlurFilter();
        gPUImageFilterGroup.m27281b(gPUImageSobelEdgeDetection);
        gPUImageSobelEdgeDetection.m27348a(f25448f);
        gPUImageStepFilter.mo6993a(gPUImageSobelEdgeDetection);
        gPUImageStepFilter = new GPUImageStepFilter(f25449g, f25450h);
        gPUImageFilterGroup.m27281b(gPUImageStepFilter);
        gPUImageSobelEdgeDetection.mo6993a(gPUImageStepFilter);
        gPUImageSobelEdgeDetection = new GPUImageBoxBlurFilter();
        gPUImageFilterGroup.m27281b(gPUImageSobelEdgeDetection);
        gPUImageSobelEdgeDetection.m27293a(f25451i);
        gPUImageFilter.mo6993a(gPUImageSobelEdgeDetection);
        GPUImageFilter gPUImageMaskBlendFilter = new GPUImageMaskBlendFilter();
        gPUImageFilterGroup.m27281b(gPUImageMaskBlendFilter);
        gPUImageFilter.mo6994a(gPUImageMaskBlendFilter, 0);
        gPUImageSobelEdgeDetection.mo6994a(gPUImageMaskBlendFilter, 1);
        gPUImageStepFilter.mo6994a(gPUImageMaskBlendFilter, 2);
        gPUImageSobelEdgeDetection = new GPUImageSobelEdgeDetection();
        gPUImageFilterGroup.m27281b(gPUImageSobelEdgeDetection);
        gPUImageFilter.mo6993a(gPUImageSobelEdgeDetection);
        gPUImageStepFilter = new GPUImageBoxBlurFilter();
        gPUImageStepFilter.m27293a(f25452j);
        gPUImageFilterGroup.m27281b(gPUImageStepFilter);
        gPUImageSobelEdgeDetection.mo6993a(gPUImageStepFilter);
        GPUImageFilter gPUImageStepFilter2 = new GPUImageStepFilter(f25453k, f25454l);
        gPUImageFilterGroup.m27281b(gPUImageStepFilter2);
        gPUImageStepFilter.mo6993a(gPUImageStepFilter2);
        PointF d = m26572d();
        gPUImageSobelEdgeDetection.m26451c(Math.round(d.x), Math.round(d.y));
        gPUImageSobelEdgeDetection = new GPUImageBoxBlurFilter();
        gPUImageSobelEdgeDetection.m27293a(f25455m);
        gPUImageFilterGroup.m27281b(gPUImageSobelEdgeDetection);
        gPUImageFilter.mo6993a(gPUImageSobelEdgeDetection);
        gPUImageSobelEdgeDetection.m26451c(Math.round(d.x), Math.round(d.y));
        gPUImageStepFilter = new GPUImageMaskBlendFilter();
        gPUImageFilterGroup.m27281b(gPUImageStepFilter);
        gPUImageMaskBlendFilter.mo6994a(gPUImageStepFilter, 0);
        gPUImageSobelEdgeDetection.mo6994a(gPUImageStepFilter, 1);
        gPUImageStepFilter2.mo6994a(gPUImageStepFilter, 2);
        gPUImageFilterGroup.m27283c(gPUImageFilter);
        gPUImageFilterGroup.m27285d(gPUImageStepFilter);
        return gPUImageFilterGroup;
    }

    public static GPUImageSwitchableFilterChain m26568a(Context context, FilterType filterType, boolean z) {
        return new GPUImageSwitchableFilterChain(m26571c(), m26567a(context, filterType), z);
    }

    public static GPUImageFilter m26567a(Context context, FilterType filterType) {
        GPUImageFilter gPUImageLookupFilter;
        switch (filterType) {
            case NORMAL:
                return new GPUImageFilter();
            case BLACK_AND_WHITE:
                return new GPUImageGrayscaleFilter();
            case VINTAGE:
                gPUImageLookupFilter = new GPUImageLookupFilter();
                gPUImageLookupFilter.m27263a(m26566a(context, (int) C1947R.drawable.lookup_vintage));
                return gPUImageLookupFilter;
            case FIGHTCLUB:
                gPUImageLookupFilter = new GPUImageLookupFilter();
                gPUImageLookupFilter.m27263a(m26566a(context, (int) C1947R.drawable.lookup_fightclub));
                return gPUImageLookupFilter;
            case SEPIA:
                gPUImageLookupFilter = new GPUImageLookupFilter();
                gPUImageLookupFilter.m27263a(m26566a(context, (int) C1947R.drawable.lookup_sepia));
                return gPUImageLookupFilter;
            case SELFIE:
                gPUImageLookupFilter = new GPUImageLookupFilter();
                gPUImageLookupFilter.m27263a(m26566a(context, (int) C1947R.drawable.lookup_selfie));
                return gPUImageLookupFilter;
            default:
                throw new RuntimeException("Video filter not found");
        }
    }
}
