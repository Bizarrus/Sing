package com.smule.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSDriverException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.CacheType;
import com.smule.android.logging.Log;
import com.smule.android.ui.SNPImageView;
import java.util.List;

public class ImageUtils {
    private static final String f6926a = ImageUtils.class.getName();
    private static final SimpleLRUCache<String, Pair<Long, Integer>> f6927b = new SimpleLRUCache(10);
    private static ImageUtilsImageDownloader f6928c;
    private static RenderScript f6929d;

    public static void m8357a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void m8372b(View view, Drawable drawable) {
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        m8357a(view, drawable);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public static void m8359a(String str, ImageView imageView) {
        if (imageView instanceof SNPImageView) {
            str = ((SNPImageView) imageView).a(str);
        }
        m8366a(str, imageView, null);
    }

    public static void m8366a(String str, ImageView imageView, ImageLoadingListener imageLoadingListener) {
        m8365a(str, imageView, 0, false, 0, imageLoadingListener);
    }

    public static void m8360a(String str, ImageView imageView, int i) {
        m8362a(str, imageView, i, false);
    }

    public static void m8361a(String str, ImageView imageView, int i, ImageLoadingListener imageLoadingListener) {
        m8365a(str, imageView, i, false, 0, imageLoadingListener);
    }

    public static void m8363a(String str, ImageView imageView, int i, boolean z, int i2) {
        m8365a(str, imageView, i, z, i2, null);
    }

    public static void m8365a(String str, ImageView imageView, int i, boolean z, int i2, ImageLoadingListener imageLoadingListener) {
        m8364a(str, imageView, i, z, i2, 0, imageLoadingListener, true);
    }

    public static void m8364a(String str, ImageView imageView, int i, boolean z, int i2, int i3, ImageLoadingListener imageLoadingListener, boolean z2) {
        if (imageView == null) {
            throw new NullPointerException("loadImage - imageView was null");
        } else if ((str == null || str.length() == 0 || m8368a(str)) && i != 0) {
            imageView.setImageResource(i);
        } else {
            if (z2 && (imageView instanceof SNPImageView)) {
                str = ((SNPImageView) imageView).a(str);
            }
            if (z) {
                if (imageView instanceof RoundedImageView) {
                    int measuredWidth;
                    int i4 = imageView.getLayoutParams().width;
                    if (i4 == -2 || i4 == -1) {
                        measuredWidth = imageView.getMeasuredWidth();
                    } else {
                        measuredWidth = i4;
                    }
                    imageView.setScaleType(ScaleType.CENTER_CROP);
                    ((RoundedImageView) imageView).setCornerRadius((float) measuredWidth);
                    if (measuredWidth == 0) {
                        Log.m7776e(f6926a, "Image Loading Corner Radius = 0. Url: " + str);
                    }
                } else {
                    Log.m7774d(f6926a, "Attempting to make circular an ImageView that's not a subclass of RoundedImageView");
                }
            }
            if (imageView instanceof RoundedImageView) {
                ((RoundedImageView) imageView).setBorderWidth((float) i3);
                if (i2 != 0) {
                    ((RoundedImageView) imageView).setBorderColor(i2);
                }
            }
            if (imageLoadingListener == null) {
                imageLoadingListener = new 1();
            }
            ImageLoader.m7591a().m7595a(str, imageView, new Builder().a(i).a(true).b(true).a(new 2(str)).a(), imageLoadingListener);
        }
    }

    private static void m8373b(@NonNull String str, boolean z) {
        Analytics.a(str, z, CacheType.b);
    }

    public static ImageDownloader m8355a(Context context) {
        f6928c = new ImageUtilsImageDownloader(context, null);
        return f6928c;
    }

    public static boolean m8368a(String str) {
        return str.matches(".*account/icon/.*_defpic.*");
    }

    public static void m8362a(String str, ImageView imageView, int i, boolean z) {
        m8363a(str, imageView, i, z, -12303292);
    }

    public static void m8358a(ImageView imageView, Bitmap bitmap, int i, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = width != height ? width >= height ? Bitmap.createBitmap(bitmap, (bitmap.getWidth() / 2) - (bitmap.getHeight() / 2), 0, bitmap.getHeight(), bitmap.getHeight()) : Bitmap.createBitmap(bitmap, 0, (bitmap.getHeight() / 2) - (bitmap.getWidth() / 2), bitmap.getWidth(), bitmap.getWidth()) : bitmap;
        if (z) {
            createBitmap = m8354a(createBitmap, imageView.getWidth(), imageView.getHeight());
        }
        imageView.setImageBitmap(m8353a(createBitmap, i));
    }

    public static Bitmap m8354a(Bitmap bitmap, int i, int i2) {
        if (i == 0 || i2 == 0) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i && height == i2) {
            return bitmap;
        }
        float f = ((float) i) / ((float) width);
        float f2 = ((float) i2) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public static Bitmap m8353a(Bitmap bitmap, int i) {
        Bitmap createBitmap;
        Exception e;
        try {
            createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            try {
                Paint paint = new Paint(1);
                paint.setColor(i);
                paint.setStyle(Style.FILL_AND_STROKE);
                Shader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
                Paint paint2 = new Paint();
                paint2.setShader(bitmapShader);
                paint2.setAntiAlias(true);
                new Canvas(createBitmap).drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) (bitmap.getWidth() / 2), paint2);
            } catch (Exception e2) {
                e = e2;
                Log.m7776e(f6926a, e.getMessage());
                return createBitmap;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            createBitmap = null;
            e = exception;
            Log.m7776e(f6926a, e.getMessage());
            return createBitmap;
        }
        return createBitmap;
    }

    public static Bitmap m8369b(String str) {
        List a = MemoryCacheUtils.a(str, ImageLoader.m7591a().m7604b());
        if (a == null || a.size() == 0) {
            return null;
        }
        m8373b(str, true);
        return (Bitmap) a.get(0);
    }

    public static Bitmap m8351a(Context context, Bitmap bitmap, float f) {
        float f2 = 25.0f;
        if (VERSION.SDK_INT < 18 || context == null) {
            return m8352a(bitmap);
        }
        if (bitmap == null) {
            return null;
        }
        float f3;
        if (f <= 0.0f) {
            f3 = 0.1f;
        } else {
            f3 = f;
        }
        if (f3 <= 25.0f) {
            f2 = f3;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        try {
            RenderScript b = m8370b(context.getApplicationContext());
            Allocation createFromBitmap = Allocation.createFromBitmap(b, bitmap);
            Allocation createFromBitmap2 = Allocation.createFromBitmap(b, createBitmap);
            ScriptIntrinsicBlur create = ScriptIntrinsicBlur.create(b, Element.U8_4(b));
            create.setRadius(f2);
            create.setInput(createFromBitmap);
            create.forEach(createFromBitmap2);
            createFromBitmap2.copyTo(createBitmap);
            return createBitmap;
        } catch (RSDriverException e) {
            return m8352a(bitmap);
        }
    }

    public static Bitmap m8352a(Bitmap bitmap) {
        int width = (int) (((float) bitmap.getWidth()) * 0.025f);
        int height = (int) (((float) bitmap.getHeight()) * 0.025f);
        if (width <= 0) {
            width = bitmap.getWidth();
        }
        if (height <= 0) {
            height = bitmap.getHeight();
        }
        if (width <= 0 || height <= 0) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    private static RenderScript m8370b(Context context) {
        if (f6929d == null) {
            f6929d = RenderScript.create(context);
        }
        return f6929d;
    }
}
