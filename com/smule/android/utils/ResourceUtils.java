package com.smule.android.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.smule.android.logging.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResourceUtils {
    static final String f17834a = ResourceUtils.class.getName();
    private static String f17835b = null;

    public static String m19029a(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    public static String m19032b(Context context) {
        if (f17835b == null) {
            return context.getFilesDir().getAbsolutePath();
        }
        Log.b(f17834a, "Returning external directory for cache: " + f17835b);
        return f17835b;
    }

    public static File m19026a(Context context, String str) {
        File file = new File(context.getFilesDir(), str);
        if (file.exists() || m19031a(context, str, file)) {
            return file;
        }
        Log.e(f17834a, "Couldn't extract asset: " + str);
        return null;
    }

    public static boolean m19031a(Context context, String str, File file) {
        try {
            m19030a(context.getAssets().open(str), file, true);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void m19030a(InputStream inputStream, File file, boolean z) throws IOException {
        byte[] bArr = new byte[2048];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 2048);
        if (z || !file.exists()) {
            file.getParentFile().mkdirs();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 2048);
            while (true) {
                int read = bufferedInputStream.read(bArr, 0, 2048);
                if (read <= 0) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        inputStream.close();
    }

    public static File m19033c(@NonNull Context context) {
        return m19027a(context, Environment.DIRECTORY_PICTURES, "IMG_", ".jpg");
    }

    public static File m19027a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        File file;
        if (ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            file = new File(Environment.getExternalStoragePublicDirectory(str), "smule");
            if (!(file.exists() || file.mkdirs())) {
                Log.b(f17834a, "Failed to create directory that would contain file!");
                return null;
            }
        }
        file = context.getCacheDir();
        return new File(file.getPath() + File.separator + str2 + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + str3);
    }

    public static String m19028a() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
