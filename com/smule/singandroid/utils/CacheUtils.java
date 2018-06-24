package com.smule.singandroid.utils;

import com.smule.android.logging.Log;
import com.smule.singandroid.SingCoreBridge;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CacheUtils {
    private static final String f24660a = CacheUtils.class.getName();

    public static void m25808a(String str, int i, int i2) {
        Log.b(f24660a, "trimCache - start");
        List<String> arrayList = new ArrayList();
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    String path = file.getPath();
                    hashMap.put(path, Integer.valueOf(SingCoreBridge.getFileLastAccessedTime(path)));
                }
            }
        }
        for (Entry entry : hashMap.entrySet()) {
            String str2 = (String) entry.getKey();
            if (m25810b(str2)) {
                arrayList.add(str2);
            } else if (m25809a(str2)) {
                hashMap2.put(str2, entry.getValue());
            } else {
                SingCoreBridge.setFileLastAccessedTime(str2, ((Integer) entry.getValue()).intValue());
            }
        }
        if (i2 != 0) {
            int currentTimeMillis = (int) (((float) System.currentTimeMillis()) / 1000.0f);
            for (Entry entry2 : hashMap2.entrySet()) {
                if (((Integer) entry2.getValue()).intValue() + i2 < currentTimeMillis) {
                    arrayList.add(entry2.getKey());
                    hashMap2.remove(entry2.getKey());
                }
            }
        }
        if (i != 0) {
            List a = MiscUtils.m25889a(hashMap2);
            for (int i3 = 0; i3 < a.size() - i; i3++) {
                arrayList.add(((Entry) a.get(i3)).getKey());
            }
        }
        for (String str3 : arrayList) {
            if (!new File(str3).delete()) {
                Log.e(f24660a, "trimCache - Could not delete cache file: " + str3);
            }
            hashMap2.remove(str3);
            Log.b(f24660a, "trimCache - deleting: " + str3);
        }
        for (Entry entry22 : hashMap2.entrySet()) {
            SingCoreBridge.setFileLastAccessedTime((String) entry22.getKey(), ((Integer) entry22.getValue()).intValue());
        }
        Log.b(f24660a, "trimCache - end");
    }

    private static boolean m25809a(String str) {
        boolean z = false;
        byte[] bArr = new byte[12];
        try {
            byte[] bytes;
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
            randomAccessFile.readFully(bArr);
            long length = randomAccessFile.length();
            randomAccessFile.close();
            if (length >= 10) {
                bytes = "MThd".getBytes();
                z = Arrays.equals(bytes, Arrays.copyOfRange(bArr, 0, bytes.length));
            }
            if (!z && length >= 14) {
                bytes = "ftypM4A".getBytes();
                z = Arrays.equals(bytes, Arrays.copyOfRange(bArr, 4, bytes.length + 4));
            }
        } catch (IOException e) {
            Log.e(f24660a, "cacheFileFormatCanBeRemoved - Could not read file format: " + str);
        }
        return z;
    }

    private static boolean m25810b(String str) {
        return str.endsWith(".wav.json") || str.endsWith(".wav");
    }
}
