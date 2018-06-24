package com.smule.singandroid.utils;

import com.smule.android.logging.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: ImageToDiskUtils */
class FileStorage {
    private static final String f24744a = FileStorage.class.getName();
    private static String f24745b;

    FileStorage() {
    }

    static FileOutputStream m25825a(String str) throws IOException {
        File file = new File(f24745b + "/" + str);
        Log.b(f24744a, "writing file " + file.getAbsolutePath() + " - " + str);
        if (file.createNewFile() && file.canWrite()) {
            return new FileOutputStream(file);
        }
        return null;
    }

    static FileInputStream m25826b(String str) throws FileNotFoundException {
        File file = new File(f24745b + "/" + str);
        if (file.canRead()) {
            Log.b(f24744a, "reading file " + file.getAbsolutePath() + " - " + str);
            return new FileInputStream(file);
        }
        Log.b(f24744a, "Can't read file " + str);
        return null;
    }

    static void m25827c(String str) {
        File file = new File(f24745b + "/" + str);
        if (file.canRead()) {
            file.delete();
        }
    }

    static void m25828d(String str) {
        f24745b = str;
    }
}
