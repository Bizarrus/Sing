package com.smule.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.smule.android.logging.Log;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class MagicDevice {
    public static final String f17796a = MagicDevice.class.getName();
    public static String f17797b = "ed6153fa-df85-401b-aa48-2855714d6987";
    public static final Set<String> f17798c = new HashSet();
    private static String f17799d = null;
    private static String f17800e = null;
    private static String f17801f = null;
    private static Boolean f17802g = null;
    private static boolean f17803h = false;

    static {
        f17798c.add("9774d56d682e549c");
        f17798c.add("e78ffe4c50967b89");
    }

    public static synchronized String m19003a(Context context) {
        String str;
        synchronized (MagicDevice.class) {
            if (f17799d == null) {
                m19007d(context);
            }
            str = f17799d;
        }
        return str;
    }

    public static synchronized String m19005b(Context context) {
        String str;
        synchronized (MagicDevice.class) {
            if (f17800e == null) {
                m19007d(context);
            }
            str = f17800e;
        }
        return str;
    }

    public static Boolean m19006c(Context context) {
        if (f17802g != null) {
            return f17802g;
        }
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            f17802g = advertisingIdInfo != null ? Boolean.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled()) : null;
            return f17802g;
        } catch (Exception e) {
            return Boolean.valueOf(true);
        }
    }

    public static String m19004a(Context context, boolean z) {
        try {
            if (f17801f == null) {
                Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                if (advertisingIdInfo == null) {
                    Log.e(f17796a, "Error obtaining advertising id: client info returned was NULL");
                    return null;
                }
                f17801f = advertisingIdInfo.getId();
                Log.b(f17796a, "Got advertising id: " + f17801f);
            }
            if (z) {
                return "a:" + f17801f;
            }
            return f17801f;
        } catch (Throwable e) {
            if (f17803h) {
                return null;
            }
            Log.c(f17796a, "Error obtaining advertising id ", e);
            f17803h = true;
            return null;
        }
    }

    private static void m19007d(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MAGIC_DEVICE", 0);
        f17799d = sharedPreferences.getString("DEVICE_ID_KEY", null);
        if (f17799d == null) {
            f17799d = m19008e(context);
            Editor edit = sharedPreferences.edit();
            edit.putString("DEVICE_ID_KEY", f17799d);
            edit.putString("ANDROID_ID", f17800e);
            edit.apply();
            return;
        }
        f17800e = sharedPreferences.getString("ANDROID_ID", null);
    }

    private static String m19008e(Context context) {
        f17800e = Secure.getString(context.getContentResolver(), "android_id");
        try {
            Log.a(f17796a, "Read ANDROID_ID value \"" + f17800e + "\"");
            if (f17800e == null || "".equals(f17800e) || f17798c.contains(f17800e)) {
                f17800e = MagicDigest.m19009a(m19002a());
                f17800e = f17800e != null ? "s:" + f17800e : UUID.randomUUID().toString();
                Log.a(f17796a, "Generated unique ID..." + f17800e);
            }
            return UUID.nameUUIDFromBytes((f17800e + f17797b).getBytes("utf8")).toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String m19002a() {
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        int i2;
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            i = 0;
            while (it.hasNext()) {
                try {
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    stringBuilder.append(networkInterface.getDisplayName()).append(":");
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress != null) {
                        int length = hardwareAddress.length;
                        for (i2 = 0; i2 < length; i2++) {
                            stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i2])}));
                        }
                        if (stringBuilder.length() > 0) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }
                        i++;
                    }
                } catch (Exception e) {
                    i2 = i;
                }
            }
        } catch (Exception e2) {
            i2 = 0;
            i = i2;
            return i <= 0 ? null : stringBuilder.toString();
        }
        if (i <= 0) {
        }
    }
}
