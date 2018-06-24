/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.os.Environment
 *  android.support.annotation.NonNull
 *  android.support.v4.content.ContextCompat
 */
package com.smule.android.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.smule.android.logging.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResourceUtils {
    static final String a = ResourceUtils.class.getName();
    private static String b = null;

    @NonNull
    public static File a(Context context, String string2) {
        return new File(context.getFilesDir(), string2);
    }

    public static File a(@NonNull Context object, @NonNull String object2, @NonNull String string2, @NonNull String string3) {
        if (ContextCompat.checkSelfPermission((Context)object, (String)"android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            object = object2 = new File(Environment.getExternalStoragePublicDirectory((String)object2), "smule");
            if (!object2.exists()) {
                object = object2;
                if (!object2.mkdirs()) {
                    Log.b(a, "Failed to create directory that would contain file!");
                    return null;
                }
            }
        } else {
            object = object.getCacheDir();
        }
        object2 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(object.getPath() + File.separator + string2 + (String)object2 + string3);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static File a(Context context, String string2, boolean bl) throws IOException {
        AssetManager assetManager = context.getAssets();
        if (!bl) {
            bl = true;
            do {
                return ResourceUtils.a(string2, assetManager, context, bl);
                break;
            } while (true);
        }
        bl = false;
        return ResourceUtils.a(string2, assetManager, context, bl);
    }

    public static File a(AssetManager object, String object2, File file, boolean bl) throws IOException {
        object = ResourceUtils.a((AssetManager)object, (String)object2);
        object2 = ResourceUtils.a(file);
        if (bl || !file.exists()) {
            ResourceUtils.a((InputStream)object, (OutputStream)object2);
        }
        return file;
    }

    @NonNull
    public static File a(String string2, AssetManager assetManager, Context object, boolean bl) throws IOException {
        object = ResourceUtils.a((Context)object, string2);
        if (bl && object.exists()) {
            return object;
        }
        return ResourceUtils.a(assetManager, string2, (File)object, true);
    }

    @NonNull
    private static FileOutputStream a(@NonNull File file) throws IOException {
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("Could not create parent directory for " + file.getAbsolutePath());
        }
        return new FileOutputStream(file);
    }

    private static InputStream a(AssetManager assetManager, String string2) throws IOException {
        return assetManager.open(string2);
    }

    public static <Return, Resource extends Closeable, CallException extends Exception> Return a(Resource Resource2, Functional<Return, Resource, CallException> functional) throws Exception, IOException {
        try {
            functional = functional.a(Resource2);
            return (Return)functional;
        }
        finally {
            Resource2.close();
        }
    }

    public static String a() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String a(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        ResourceUtils.a(inputStream, outputStream, 2048);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(InputStream inputStream, OutputStream outputStream, int n) throws IOException {
        byte[] arrby = new byte[n];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, n);
        outputStream = new BufferedOutputStream(outputStream, n);
        try {
            int n2;
            while ((n2 = bufferedInputStream.read(arrby, 0, n)) > 0) {
                outputStream.write(arrby, 0, n2);
            }
            outputStream.flush();
            return;
        }
        finally {
            outputStream.close();
            inputStream.close();
        }
    }

    public static String b(Context context) {
        if (b != null) {
            Log.b(a, "Returning external directory for cache: " + b);
            return b;
        }
        return context.getFilesDir().getAbsolutePath();
    }

    public static File c(@NonNull Context context) {
        return ResourceUtils.a(context, Environment.DIRECTORY_PICTURES, "IMG_", ".jpg");
    }

    public static interface Functional<Return, Parameters, CallException extends Exception> {
        public Return a(Parameters var1) throws Exception;
    }

}

