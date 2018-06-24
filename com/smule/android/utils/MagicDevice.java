/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  com.google.android.gms.ads.identifier.AdvertisingIdClient
 */
package com.smule.android.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.smule.android.logging.Log;
import com.smule.android.utils.MagicDigest;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class MagicDevice {
    public static final String a = MagicDevice.class.getName();
    public static String b = "ed6153fa-df85-401b-aa48-2855714d6987";
    public static final Set<String> c = new HashSet<String>();
    private static String d;
    private static String e;
    private static String f;
    private static Boolean g;
    private static boolean h;

    static {
        c.add("9774d56d682e549c");
        c.add("e78ffe4c50967b89");
        d = null;
        e = null;
        f = null;
        g = null;
        h = false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static String a() {
        var3 = new StringBuilder();
        try {
            var4_1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            var0_4 = 0;
            ** GOTO lbl9
        }
        catch (Exception var4_2) {
            block9 : {
                var0_4 = 0;
                break block9;
lbl9: // 1 sources:
                do {
                    var1_5 = var0_4;
                    if (var4_1.hasNext()) {
                        var5_7 = var4_1.next();
                        var3.append(var5_7.getDisplayName()).append(":");
                        if ((var5_7 = var5_7.getHardwareAddress()) == null) continue;
                        var2_6 = var5_7.length;
                        for (var1_5 = 0; var1_5 < var2_6; ++var1_5) {
                            var3.append(String.format("%02x:", new Object[]{Byte.valueOf(var5_7[var1_5])}));
                        }
                        if (var3.length() > 0) {
                            var3.deleteCharAt(var3.length() - 1);
                        }
                        ++var0_4;
                        continue;
                    }
                    break;
                } while (true);
                catch (Exception var4_3) {}
            }
            var1_5 = var0_4;
        }
        if (var1_5 <= 0) return null;
        return var3.toString();
    }

    public static String a(Context object) {
        synchronized (MagicDevice.class) {
            if (d == null) {
                MagicDevice.d((Context)object);
            }
            object = d;
            return object;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Context object, boolean bl) {
        block5 : {
            block6 : {
                try {
                    if (f != null) break block5;
                    if ((object = AdvertisingIdClient.getAdvertisingIdInfo((Context)object)) != null) break block6;
                }
                catch (Exception exception) {
                    if (h) return null;
                    Log.c(a, "Error obtaining advertising id ", exception);
                    h = true;
                    return null;
                }
                Log.e(a, "Error obtaining advertising id: client info returned was NULL");
                return null;
            }
            f = object.getId();
            Log.b(a, "Got advertising id: " + f);
        }
        if (!bl) return f;
        return "a:" + f;
    }

    public static String b(Context object) {
        synchronized (MagicDevice.class) {
            if (e == null) {
                MagicDevice.d((Context)object);
            }
            object = e;
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Boolean c(Context object) {
        if (g != null) {
            return g;
        }
        try {
            object = AdvertisingIdClient.getAdvertisingIdInfo((Context)object);
            object = object != null ? Boolean.valueOf(object.isLimitAdTrackingEnabled()) : null;
            g = object;
            return g;
        }
        catch (Exception exception) {
            return true;
        }
    }

    private static void d(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MAGIC_DEVICE", 0);
        d = sharedPreferences.getString("DEVICE_ID_KEY", null);
        if (d == null) {
            d = MagicDevice.e(context);
            context = sharedPreferences.edit();
            context.putString("DEVICE_ID_KEY", d);
            context.putString("ANDROID_ID", e);
            context.apply();
            return;
        }
        e = sharedPreferences.getString("ANDROID_ID", null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String e(Context object) {
        e = Settings.Secure.getString((ContentResolver)object.getContentResolver(), (String)"android_id");
        try {
            Log.a(a, "Read ANDROID_ID value \"" + e + "\"");
            if (e == null || "".equals(e) || c.contains(e)) {
                e = MagicDigest.a(MagicDevice.a());
                object = e != null ? "s:" + e : UUID.randomUUID().toString();
                e = object;
                Log.a(a, "Generated unique ID..." + e);
            }
            object = UUID.nameUUIDFromBytes((e + b).getBytes("utf8"));
            return object.toString();
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException(unsupportedEncodingException);
        }
    }
}

