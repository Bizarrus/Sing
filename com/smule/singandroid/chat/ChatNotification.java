/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.v4.app.NotificationCompat
 *  android.support.v4.app.NotificationCompat$Builder
 *  android.support.v4.app.NotificationCompat$InboxStyle
 *  android.support.v4.app.NotificationCompat$Style
 *  android.view.View
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.assist.FailReason
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 */
package com.smule.singandroid.chat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.smule.android.logging.Log;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.JsonUtils;
import com.smule.singandroid.chat.ChatAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChatNotification {
    public static final String a = ChatNotification.class.getName();
    protected final Context b;
    protected final String c;
    protected final String d;
    protected final String e;
    protected final int f;
    protected final String g;
    protected final Intent h;
    protected final Map<String, > i = new HashMap<String, >();

    public ChatNotification(Context context, String string2, String string3, String string4, int n, String string5, Intent intent) {
        this.b = context;
        this.c = string2;
        this.d = string3;
        this.e = string4;
        this.f = n;
        this.g = string5;
        this.h = intent;
        this.i.putAll(ChatNotification.a(this.b));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static Map<String, > a(Context object) {
        object = object.getSharedPreferences(a, 0).getString("sing.SAVED_PUSHES_KEY", "{}");
        HashMap<String, > hashMap = new HashMap<String, >();
         notifications = JsonUtils.a().readValue((String)object, .class);
        if (notifications == null) return hashMap;
        try {
            if (notifications.notifications == null) return hashMap;
            for ( info : notifications.notifications) {
                hashMap.put(info.tag, info);
            }
            return hashMap;
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to deserialize JSON: " + (String)object);
        }
        return hashMap;
    }

    public static void a(Context context, String string2) {
        if (string2 == null) {
            return;
        }
        ((NotificationManager)context.getSystemService("notification")).cancel(string2, string2.hashCode());
        Map<String, > map = ChatNotification.a(context);
         info = map.get(string2);
        if (info != null) {
            Log.b(a, "Removing notification: " + info.messages);
        }
        map.remove(string2);
        ChatNotification.a(context, map);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static void a(Context context, Map<String, > object) {
        context = context.getSharedPreferences(a, 0).edit();
        try {
            Object object2 = new Object(){
                @JsonProperty(value="notificationInfo")
                public List<> notifications = new LinkedList<>();
            };
            for ( info : object.values()) {
                object2.notifications.add(info);
            }
            object = JsonUtils.a().writeValueAsString(object2);
            Log.b(a, "JSON: " + (String)object);
            context.putString("sing.SAVED_PUSHES_KEY", (String)object).apply();
            return;
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to serialize to JSON");
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(String string2, String string3, Uri object, Intent object2) {
        if (string2 == null || !string2.equals("ch")) {
            return false;
        }
        int n = object2.getIntExtra("com.smule.singandroid.AGGREGATED_MESSAGE_COUNT_KEY", 0);
        object2 = object.toString();
        object = n == 0 ? null : Integer.valueOf(n);
        ChatAnalytics.a(string2, string3, (String)object2, (Number)object);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void b(Context context, String string2) {
        if (string2 != null) {
            for ( info : ChatNotification.a(context).values()) {
                if (info.jid == null) {
                    ChatNotification.a(context, info.tag);
                    continue;
                }
                if (!info.jid.equals(string2)) continue;
                ChatNotification.a(context, info.tag);
                return;
            }
        }
    }

    protected void a(Notification notification, NotificationReadyCallback notificationReadyCallback) {
        if (notificationReadyCallback != null) {
            notificationReadyCallback.a(notification);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(String object, final NotificationReadyCallback notificationReadyCallback) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle((CharSequence)this.d);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this.b).setTicker((CharSequence)this.c).setContentTitle((CharSequence)this.d).setContentText((CharSequence)this.e).setSmallIcon(this.f).setGroup((String)object).setGroupSummary(true).setAutoCancel(true);
        if (this.i.containsKey(object)) {
            object = this.i.get(object);
            if (object.messages.size() > 0) {
                builder.setNumber(object.messages.size() + 1);
            }
        } else {
            Object object2 = new Object(){
                @JsonProperty(value="jid")
                public String jid;
                @JsonProperty(value="messages")
                public List<String> messages = new ArrayList<String>();
                @JsonProperty(value="tag")
                public String tag;
            };
            object2.tag = object;
            object2.jid = this.h.getData().getLastPathSegment();
            this.i.put((String)object, object2);
            object = object2;
        }
        object.messages.add(this.e);
        int n = object.messages.size();
        int n2 = this.b.getResources().getInteger(2131623947);
        for (int i = 0; i < n2 && i < n; ++i) {
            Log.b(a, "Adding pending message: " + object.messages.get(i));
            inboxStyle.addLine((CharSequence)object.messages.get(i));
        }
        if (n > n2) {
            inboxStyle.addLine((CharSequence)this.b.getString(2131296575, new Object[]{n - n2}));
        }
        this.h.putExtra("com.smule.singandroid.AGGREGATED_MESSAGE_COUNT_KEY", n);
        builder.setContentIntent(PendingIntent.getActivity((Context)this.b, (int)0, (Intent)this.h, (int)134217728));
        inboxStyle.setSummaryText((CharSequence)this.b.getString(2131296574));
        builder.setStyle((NotificationCompat.Style)inboxStyle);
        new Handler(Looper.getMainLooper()).post(new Runnable(){

            @Override
            public void run() {
                ImageLoader.a().a(ChatNotification.this.g, new ImageLoadingListener(){

                    public void a(String string2, View view) {
                        Log.b(ChatNotification.a, "onLoadingStarted");
                    }

                    public void a(String string2, View view, Bitmap bitmap) {
                        try {
                            int n = ChatNotification.this.b.getResources().getDimensionPixelSize(2131427735);
                            string2 = ImageUtils.a(ImageUtils.a(bitmap, n, n), ChatNotification.this.b.getResources().getColor(17170445));
                            Log.b(ChatNotification.a, "Posting custom notification");
                            ChatNotification.this.a(builder.setLargeIcon((Bitmap)string2).build(), notificationReadyCallback);
                            return;
                        }
                        catch (Exception exception) {
                            Log.b(ChatNotification.a, "Posting default notification");
                            ChatNotification.this.a(builder.build(), notificationReadyCallback);
                            return;
                        }
                    }

                    public void a(String string2, View view, FailReason failReason) {
                        Log.b(ChatNotification.a, "Posting default notification");
                        ChatNotification.this.a(builder.build(), notificationReadyCallback);
                    }

                    public void b(String string2, View view) {
                        Log.b(ChatNotification.a, "Posting default notification");
                        ChatNotification.this.a(builder.build(), notificationReadyCallback);
                    }
                });
            }

        });
        ChatNotification.a(this.b, this.i);
    }

    public static class Builder {
        protected Context a;
        protected String b;
        protected String c;
        protected String d;
        protected int e;
        protected String f;
        protected Intent g;

        public Builder(Context context) {
            this.a = context;
        }

        public Builder a(int n) {
            this.e = n;
            return this;
        }

        public Builder a(Intent intent) {
            this.g = intent;
            return this;
        }

        public Builder a(String string2) {
            this.b = string2;
            return this;
        }

        public ChatNotification a() {
            return new ChatNotification(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        public Builder b(String string2) {
            this.c = string2;
            return this;
        }

        public Builder c(String string2) {
            this.d = string2;
            return this;
        }

        public Builder d(String string2) {
            this.f = string2;
            return this;
        }
    }

    public static interface NotificationReadyCallback {
        public void a(Notification var1);
    }

}

