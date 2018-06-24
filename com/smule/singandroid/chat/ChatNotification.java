package com.smule.singandroid.chat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.view.View;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.smule.android.logging.Log;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.JsonUtils;
import com.smule.singandroid.C1947R;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatNotification {
    public static final String f20951a = ChatNotification.class.getName();
    protected final Context f20952b;
    protected final String f20953c;
    protected final String f20954d;
    protected final String f20955e;
    protected final int f20956f;
    protected final String f20957g;
    protected final Intent f20958h;
    protected final Map<String, Info> f20959i = new HashMap();

    public interface NotificationReadyCallback {
        void mo6622a(Notification notification);
    }

    public static class Builder {
        protected Context f20944a;
        protected String f20945b;
        protected String f20946c;
        protected String f20947d;
        protected int f20948e;
        protected String f20949f;
        protected Intent f20950g;

        public Builder(Context context) {
            this.f20944a = context;
        }

        public Builder m22627a(String str) {
            this.f20945b = str;
            return this;
        }

        public Builder m22629b(String str) {
            this.f20946c = str;
            return this;
        }

        public Builder m22630c(String str) {
            this.f20947d = str;
            return this;
        }

        public Builder m22625a(int i) {
            this.f20948e = i;
            return this;
        }

        public Builder m22631d(String str) {
            this.f20949f = str;
            return this;
        }

        public Builder m22626a(Intent intent) {
            this.f20950g = intent;
            return this;
        }

        public ChatNotification m22628a() {
            return new ChatNotification(this.f20944a, this.f20945b, this.f20946c, this.f20947d, this.f20948e, this.f20949f, this.f20950g);
        }
    }

    public static void m22633a(Context context, String str) {
        if (str != null) {
            ((NotificationManager) context.getSystemService("notification")).cancel(str, str.hashCode());
            Map a = m22632a(context);
            Info info = (Info) a.get(str);
            if (info != null) {
                Log.b(f20951a, "Removing notification: " + info.messages);
            }
            a.remove(str);
            m22634a(context, a);
        }
    }

    public static void m22636b(Context context, String str) {
        if (str != null) {
            for (Info info : m22632a(context).values()) {
                if (info.jid == null) {
                    m22633a(context, info.tag);
                } else if (info.jid.equals(str)) {
                    m22633a(context, info.tag);
                    return;
                }
            }
        }
    }

    public ChatNotification(Context context, String str, String str2, String str3, int i, String str4, Intent intent) {
        this.f20952b = context;
        this.f20953c = str;
        this.f20954d = str2;
        this.f20955e = str3;
        this.f20956f = i;
        this.f20957g = str4;
        this.f20958h = intent;
        this.f20959i.putAll(m22632a(this.f20952b));
    }

    public void m22638a(String str, final NotificationReadyCallback notificationReadyCallback) {
        Info info;
        Style inboxStyle = new InboxStyle();
        inboxStyle.setBigContentTitle(this.f20954d);
        final android.support.v4.app.NotificationCompat.Builder autoCancel = new android.support.v4.app.NotificationCompat.Builder(this.f20952b).setTicker(this.f20953c).setContentTitle(this.f20954d).setContentText(this.f20955e).setSmallIcon(this.f20956f).setGroup(str).setGroupSummary(true).setAutoCancel(true);
        Info info2;
        if (this.f20959i.containsKey(str)) {
            info2 = (Info) this.f20959i.get(str);
            if (info2.messages.size() > 0) {
                autoCancel.setNumber(info2.messages.size() + 1);
                info = info2;
            } else {
                info = info2;
            }
        } else {
            info2 = new Info();
            info2.tag = str;
            info2.jid = this.f20958h.getData().getLastPathSegment();
            this.f20959i.put(str, info2);
            info = info2;
        }
        info.messages.add(this.f20955e);
        int size = info.messages.size();
        int integer = this.f20952b.getResources().getInteger(C1947R.integer.chat_max_notification_messages);
        int i = 0;
        while (i < integer && i < size) {
            Log.b(f20951a, "Adding pending message: " + ((String) info.messages.get(i)));
            inboxStyle.addLine((CharSequence) info.messages.get(i));
            i++;
        }
        if (size > integer) {
            inboxStyle.addLine(this.f20952b.getString(C1947R.string.chat_notification_more_messages, new Object[]{Integer.valueOf(size - integer)}));
        }
        this.f20958h.putExtra("com.smule.singandroid.AGGREGATED_MESSAGE_COUNT_KEY", size);
        autoCancel.setContentIntent(PendingIntent.getActivity(this.f20952b, 0, this.f20958h, 134217728));
        inboxStyle.setSummaryText(this.f20952b.getString(C1947R.string.chat_notification_cta));
        autoCancel.setStyle(inboxStyle);
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ ChatNotification f20943c;

            class C42841 implements ImageLoadingListener {
                final /* synthetic */ C42851 f20940a;

                C42841(C42851 c42851) {
                    this.f20940a = c42851;
                }

                public void mo6154a(String str, View view) {
                    Log.b(ChatNotification.f20951a, "onLoadingStarted");
                }

                public void mo6156a(String str, View view, FailReason failReason) {
                    Log.b(ChatNotification.f20951a, "Posting default notification");
                    this.f20940a.f20943c.m22637a(autoCancel.build(), notificationReadyCallback);
                }

                public void mo6155a(String str, View view, Bitmap bitmap) {
                    try {
                        int dimensionPixelSize = this.f20940a.f20943c.f20952b.getResources().getDimensionPixelSize(C1947R.dimen.profile_middle);
                        Bitmap a = ImageUtils.a(ImageUtils.a(bitmap, dimensionPixelSize, dimensionPixelSize), this.f20940a.f20943c.f20952b.getResources().getColor(17170445));
                        Log.b(ChatNotification.f20951a, "Posting custom notification");
                        this.f20940a.f20943c.m22637a(autoCancel.setLargeIcon(a).build(), notificationReadyCallback);
                    } catch (Exception e) {
                        Log.b(ChatNotification.f20951a, "Posting default notification");
                        this.f20940a.f20943c.m22637a(autoCancel.build(), notificationReadyCallback);
                    }
                }

                public void mo6157b(String str, View view) {
                    Log.b(ChatNotification.f20951a, "Posting default notification");
                    this.f20940a.f20943c.m22637a(autoCancel.build(), notificationReadyCallback);
                }
            }

            public void run() {
                ImageLoader.a().a(this.f20943c.f20957g, new C42841(this));
            }
        });
        m22634a(this.f20952b, this.f20959i);
    }

    protected void m22637a(Notification notification, NotificationReadyCallback notificationReadyCallback) {
        if (notificationReadyCallback != null) {
            notificationReadyCallback.mo6622a(notification);
        }
    }

    protected static Map<String, Info> m22632a(Context context) {
        String string = context.getSharedPreferences(f20951a, 0).getString("sing.SAVED_PUSHES_KEY", "{}");
        Map<String, Info> hashMap = new HashMap();
        try {
            Notifications notifications = (Notifications) JsonUtils.m18984a().readValue(string, Notifications.class);
            if (!(notifications == null || notifications.notifications == null)) {
                for (Info info : notifications.notifications) {
                    hashMap.put(info.tag, info);
                }
            }
        } catch (IOException e) {
            Log.d(f20951a, "Failed to deserialize JSON: " + string);
        }
        return hashMap;
    }

    protected static void m22634a(Context context, Map<String, Info> map) {
        Editor edit = context.getSharedPreferences(f20951a, 0).edit();
        try {
            Notifications notifications = new Notifications();
            for (Info add : map.values()) {
                notifications.notifications.add(add);
            }
            String writeValueAsString = JsonUtils.m18984a().writeValueAsString(notifications);
            Log.b(f20951a, "JSON: " + writeValueAsString);
            edit.putString("sing.SAVED_PUSHES_KEY", writeValueAsString).apply();
        } catch (IOException e) {
            Log.d(f20951a, "Failed to serialize to JSON");
        }
    }

    public static boolean m22635a(String str, String str2, Uri uri, Intent intent) {
        if (str == null || !str.equals("ch")) {
            return false;
        }
        int intExtra = intent.getIntExtra("com.smule.singandroid.AGGREGATED_MESSAGE_COUNT_KEY", 0);
        ChatAnalytics.m22390a(str, str2, uri.toString(), intExtra == 0 ? null : Integer.valueOf(intExtra));
        return true;
    }
}
