/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapShader
 *  android.graphics.Canvas
 *  android.graphics.Matrix
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.renderscript.Allocation
 *  android.renderscript.Element
 *  android.renderscript.RSDriverException
 *  android.renderscript.RenderScript
 *  android.renderscript.ScriptIntrinsicBlur
 *  android.support.annotation.NonNull
 *  android.support.v4.util.Pair
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 *  com.nostra13.universalimageloader.cache.memory.MemoryCache
 *  com.nostra13.universalimageloader.core.DefaultConfigurationFactory
 *  com.nostra13.universalimageloader.core.DisplayImageOptions
 *  com.nostra13.universalimageloader.core.DisplayImageOptions$Builder
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.assist.ContentLengthInputStream
 *  com.nostra13.universalimageloader.core.assist.LoadedFrom
 *  com.nostra13.universalimageloader.core.display.BitmapDisplayer
 *  com.nostra13.universalimageloader.core.download.BaseImageDownloader
 *  com.nostra13.universalimageloader.core.download.ImageDownloader
 *  com.nostra13.universalimageloader.core.download.ImageDownloader$Scheme
 *  com.nostra13.universalimageloader.core.imageaware.ImageAware
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.utils.MemoryCacheUtils
 *  okhttp3.Call
 *  okhttp3.OkHttpClient
 *  okhttp3.Request
 *  okhttp3.Request$Builder
 *  okhttp3.Response
 *  okhttp3.ResponseBody
 */
package com.smule.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSDriverException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ContentLengthInputStream;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.ui.SNPImageView;
import com.smule.android.ui.roundedimageview.RoundedImageView;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.SimpleLRUCache;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ImageUtils {
    private static final String a = ImageUtils.class.getName();
    private static final SimpleLRUCache<String, Pair<Long, Integer>> b = new SimpleLRUCache(10);
    private static RenderScript c;

    public static Bitmap a(Context context, Bitmap bitmap, float f) {
        float f2 = 25.0f;
        if (Build.VERSION.SDK_INT >= 18 && context != null) {
            if (bitmap == null) {
                return null;
            }
            if (f <= 0.0f) {
                f = 0.1f;
            }
            if (f > 25.0f) {
                f = f2;
            }
            Bitmap bitmap2 = Bitmap.createBitmap((int)bitmap.getWidth(), (int)bitmap.getHeight(), (Bitmap.Config)Bitmap.Config.ARGB_8888);
            try {
                RenderScript renderScript = ImageUtils.b(context.getApplicationContext());
                context = Allocation.createFromBitmap((RenderScript)renderScript, (Bitmap)bitmap);
                Allocation allocation = Allocation.createFromBitmap((RenderScript)renderScript, (Bitmap)bitmap2);
                renderScript = ScriptIntrinsicBlur.create((RenderScript)renderScript, (Element)Element.U8_4((RenderScript)renderScript));
                renderScript.setRadius(f);
                renderScript.setInput((Allocation)context);
                renderScript.forEach(allocation);
                allocation.copyTo(bitmap2);
                return bitmap2;
            }
            catch (RSDriverException rSDriverException) {
                return ImageUtils.a(bitmap);
            }
        }
        return ImageUtils.a(bitmap);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Bitmap a(Bitmap bitmap) {
        Bitmap bitmap2 = null;
        int n = (int)((float)bitmap.getWidth() * 0.025f);
        int n2 = (int)((float)bitmap.getHeight() * 0.025f);
        if (n <= 0) {
            n = bitmap.getWidth();
        }
        if (n2 <= 0) {
            n2 = bitmap.getHeight();
        }
        Bitmap bitmap3 = bitmap2;
        if (n <= 0) return bitmap3;
        bitmap3 = bitmap2;
        if (n2 <= 0) return bitmap3;
        return Bitmap.createScaledBitmap((Bitmap)bitmap, (int)n, (int)n2, (boolean)true);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static Bitmap a(Bitmap bitmap, int n) {
        Bitmap bitmap2;
        try {
            bitmap2 = Bitmap.createBitmap((int)bitmap.getWidth(), (int)bitmap.getHeight(), (Bitmap.Config)Bitmap.Config.ARGB_8888);
        }
        catch (Exception exception) {
            void var0_2;
            bitmap2 = null;
            Log.e(a, var0_2.getMessage());
            return bitmap2;
        }
        Paint paint = new Paint(1);
        paint.setColor(n);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint2 = new Paint();
        paint2.setShader((Shader)paint);
        paint2.setAntiAlias(true);
        new Canvas(bitmap2).drawCircle((float)(bitmap.getWidth() / 2), (float)(bitmap.getHeight() / 2), (float)(bitmap.getWidth() / 2), paint2);
        return bitmap2;
        {
            catch (Exception exception) {}
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Bitmap a(Bitmap bitmap, int n, int n2) {
        int n3;
        int n4;
        block3 : {
            block2 : {
                if (n == 0 || n2 == 0) break block2;
                n3 = bitmap.getWidth();
                n4 = bitmap.getHeight();
                if (n3 != n || n4 != n2) break block3;
            }
            return bitmap;
        }
        float f = (float)n / (float)n3;
        float f2 = (float)n2 / (float)n4;
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap((Bitmap)bitmap, (int)0, (int)0, (int)n3, (int)n4, (Matrix)matrix, (boolean)false);
    }

    public static ImageDownloader a(Context context) {
        return new ImageDownloader(context){
            final ImageDownloader a;
            {
                this.a = new BaseImageDownloader(context){

                    protected InputStream b(String string2, Object object) throws IOException {
                        string2 = new Request.Builder().a(string2).b();
                        string2 = MagicNetwork.a().t().newCall((Request)string2).b().h();
                        return new ContentLengthInputStream(string2.byteStream(), (int)string2.contentLength());
                    }
                };
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Converted monitor instructions to comments
             * Lifted jumps to return sites
             */
            public InputStream a(String string2, Object object) throws IOException {
                Object object2;
                switch (.a[ImageDownloader.Scheme.a((String)string2).ordinal()]) {
                    default: {
                        return this.a.a(string2, object);
                    }
                    case 1: 
                    case 2: 
                }
                long l = System.currentTimeMillis();
                try {
                    object2 = object = this.a.a(string2, object);
                }
                catch (SocketTimeoutException socketTimeoutException) {
                    com.smule.android.logging.EventLogger2.a(string2, System.currentTimeMillis() - l, 0, 0, EventLogger2.d, 100, "ImageUtils", null, null, false);
                    throw socketTimeoutException;
                }
                catch (IOException iOException) {
                    long l2 = System.currentTimeMillis();
                    String string3 = iOException.toString();
                    int n = 0;
                    EventLogger2 errorDomain = EventLogger2.e;
                    object = errorDomain;
                    int n2 = n;
                    if (string3 != null) {
                        object = errorDomain;
                        n2 = n;
                        if (string3.startsWith("Image request failed with response code")) {
                            Object object3 = object = string3.replaceFirst("Image request failed with response code", "");
                            if (!TextUtils.isEmpty((CharSequence)object)) {
                                object3 = object.trim();
                            }
                            object = errorDomain;
                            n2 = n;
                            if (!TextUtils.isEmpty((CharSequence)object3)) {
                                try {
                                    n2 = Integer.parseInt((String)object3);
                                    object = EventLogger2.b;
                                }
                                catch (NumberFormatException numberFormatException) {
                                    n2 = 0;
                                    object = EventLogger2.e;
                                }
                            }
                        }
                    }
                    com.smule.android.logging.EventLogger2.a(string2, l2 - l, 0, 0, (Object)object, n2, "ImageUtils", string3, null, false);
                    throw iOException;
                }
                if (!(object instanceof ContentLengthInputStream)) return object2;
                object2 = b;
                // MONITORENTER : object2
                b.put(string2, new Pair((Object)l, (Object)((ContentLengthInputStream)object).available()));
                // MONITOREXIT : object2
                return object;
            }
        };
    }

    static /* synthetic */ String a() {
        return a;
    }

    public static void a(View view, Drawable drawable2) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable2);
            return;
        }
        view.setBackgroundDrawable(drawable2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(ImageView imageView, Bitmap bitmap, int n, boolean bl) {
        int n2;
        int n3 = bitmap.getWidth();
        if (n3 != (n2 = bitmap.getHeight())) {
            bitmap = n3 >= n2 ? Bitmap.createBitmap((Bitmap)bitmap, (int)(bitmap.getWidth() / 2 - bitmap.getHeight() / 2), (int)0, (int)bitmap.getHeight(), (int)bitmap.getHeight()) : Bitmap.createBitmap((Bitmap)bitmap, (int)0, (int)(bitmap.getHeight() / 2 - bitmap.getWidth() / 2), (int)bitmap.getWidth(), (int)bitmap.getWidth());
        }
        Bitmap bitmap2 = bitmap;
        if (bl) {
            bitmap2 = ImageUtils.a(bitmap, imageView.getWidth(), imageView.getHeight());
        }
        imageView.setImageBitmap(ImageUtils.a(bitmap2, n));
    }

    public static void a(String string2, ImageView imageView) {
        ImageUtils.a(string2, imageView, null);
    }

    public static void a(String string2, ImageView imageView, int n) {
        ImageUtils.a(string2, imageView, n, false);
    }

    public static void a(String string2, ImageView imageView, int n, ImageLoadingListener imageLoadingListener) {
        ImageUtils.a(string2, imageView, n, false, 0, imageLoadingListener);
    }

    public static void a(String string2, ImageView imageView, int n, boolean bl) {
        ImageUtils.a(string2, imageView, n, bl, -12303292);
    }

    public static void a(String string2, ImageView imageView, int n, boolean bl, int n2) {
        ImageUtils.a(string2, imageView, n, bl, n2, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(String object, ImageView imageView, int n, boolean bl, int n2, int n3, ImageLoadingListener imageLoadingListener, boolean bl2) {
        if (imageView == null) {
            throw new NullPointerException("loadImage - imageView was null");
        }
        if ((object == null || object.length() == 0 || ImageUtils.a((String)object)) && n != 0) {
            imageView.setImageResource(n);
            return;
        }
        Object object2 = object;
        if (bl2) {
            object2 = object;
            if (imageView instanceof SNPImageView) {
                object2 = ((SNPImageView)imageView).a((String)object);
            }
        }
        if (bl) {
            if (imageView instanceof RoundedImageView) {
                int n4 = imageView.getLayoutParams().width;
                if (n4 == -2 || n4 == -1) {
                    n4 = imageView.getMeasuredWidth();
                }
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ((RoundedImageView)imageView).setCornerRadius(n4);
                if (n4 == 0) {
                    Log.e(a, "Image Loading Corner Radius = 0. Url: " + (String)object2);
                }
            } else {
                Log.d(a, "Attempting to make circular an ImageView that's not a subclass of RoundedImageView");
            }
        }
        if (imageView instanceof RoundedImageView) {
            ((RoundedImageView)imageView).setBorderWidth((float)n3);
            if (n2 != 0) {
                ((RoundedImageView)imageView).setBorderColor(n2);
            }
        }
        object = imageLoadingListener;
        if (imageLoadingListener == null) {
            object = new ImageLoadingListener(){

                public void a(String string2, View view) {
                }

                public void a(String string2, View view, Bitmap bitmap) {
                }

                public void a(String string2, View view, com.nostra13.universalimageloader.core.assist.FailReason failReason) {
                    Log.d(ImageUtils.a(), "Image Loading Failed. Url: " + string2);
                }

                public void b(String string2, View view) {
                    Log.b(ImageUtils.a(), "Image Loading Cancelled. Url: " + string2);
                }
            };
        }
        ImageLoader.a().a((String)object2, imageView, new DisplayImageOptions.Builder().a(n).a(true).b(true).a(new BitmapDisplayer((String)object2){
            final String a;
            final BitmapDisplayer b;
            {
                this.a = string2;
                this.b = DefaultConfigurationFactory.c();
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Converted monitor instructions to comments
             * Lifted jumps to return sites
             */
            public void a(Bitmap object, ImageAware imageAware, LoadedFrom loadedFrom) {
                this.b.a((Bitmap)object, imageAware, loadedFrom);
                if (loadedFrom != LoadedFrom.a) {
                    ImageUtils.b(this.a, true);
                    return;
                }
                long l = System.currentTimeMillis();
                object = b;
                // MONITORENTER : object
                imageAware = (Pair)b.get(this.a);
                // MONITOREXIT : object
                if (imageAware != null && imageAware.first != null && imageAware.second != null) {
                    com.smule.android.logging.EventLogger2.a(this.a, l - (Long)imageAware.first, 0, ((Integer)imageAware.second).intValue(), EventLogger2.a, 0, null, null, null, false);
                }
                ImageUtils.b(this.a, false);
            }
        }).a(), (ImageLoadingListener)object);
    }

    public static void a(String string2, ImageView imageView, int n, boolean bl, int n2, ImageLoadingListener imageLoadingListener) {
        ImageUtils.a(string2, imageView, n, bl, n2, 0, imageLoadingListener, true);
    }

    public static void a(String string2, ImageView imageView, ImageLoadingListener imageLoadingListener) {
        ImageUtils.a(string2, imageView, 0, false, 0, imageLoadingListener);
    }

    public static boolean a(@NonNull String string2) {
        return string2.matches(".*account/icon/.*_defpic.*");
    }

    public static Bitmap b(String string2) {
        List list = MemoryCacheUtils.a((String)string2, (MemoryCache)ImageLoader.a().b());
        if (list == null || list.size() == 0) {
            return null;
        }
        ImageUtils.b(string2, true);
        return (Bitmap)list.get(0);
    }

    private static RenderScript b(Context context) {
        if (c == null) {
            c = RenderScript.create((Context)context);
        }
        return c;
    }

    public static void b(View view, Drawable drawable2) {
        int n = view.getPaddingLeft();
        int n2 = view.getPaddingTop();
        int n3 = view.getPaddingRight();
        int n4 = view.getPaddingBottom();
        ImageUtils.a(view, drawable2);
        view.setPadding(n, n2, n3, n4);
    }

    private static void b(@NonNull String string2, boolean bl) {
        com.smule.android.logging.Analytics.a(string2, bl, Analytics.b);
    }

}

