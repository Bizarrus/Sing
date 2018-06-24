/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.v4.app.NotificationCompat
 *  android.support.v4.app.NotificationCompat$Builder
 *  android.support.v4.app.NotificationManagerCompat
 */
package com.smule.singandroid.chat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.smule.android.logging.Log;
import com.smule.android.utils.IndefiniteToast;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.GroupChat;
import com.smule.singandroid.MasterActivity;

public class ConnectionStatusIndicator
implements ChatManagerListener {
    private static String b = ConnectionStatusIndicator.class.getName();
    Handler a = new Handler(Looper.getMainLooper());
    private final Context c;
    private final com.smule.chat.ChatManager d;
    private ChatManager e;
    private IndefiniteToast f;

    public ConnectionStatusIndicator(Context context, com.smule.chat.ChatManager chatManager) {
        this.c = context;
        this.d = chatManager;
    }

    @Override
    public void a() {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(ChatManager connectionStatus) {
        switch (.a[connectionStatus.ordinal()]) {
            default: {
                this.a("Invalid connection status\nThis is very broken :(", connectionStatus, -1);
                return;
            }
            case 1: {
                this.a(this.c.getString(2131296895), connectionStatus, -1);
                return;
            }
            case 2: {
                if (this.e != ChatManager.e) return;
                {
                    this.b(this.c.getString(2131296589), connectionStatus, -1);
                    return;
                }
            }
            case 3: {
                this.b(this.c.getString(2131296588), connectionStatus, -1);
                return;
            }
            case 4: {
                if (this.e == null || this.e == ChatManager.e) return;
                {
                    this.b(this.c.getString(2131296587), connectionStatus, 3);
                    return;
                }
            }
            case 5: 
        }
        this.a("Invalid connection status\nNo hosts", connectionStatus, -1);
    }

    @Override
    public void a(GroupChat groupChat) {
    }

    protected void a(String string2, ChatManager connectionStatus, long l) {
        Log.b(b, "showObviousMessage: " + string2);
        if (string2 == null || string2.isEmpty()) {
            Log.e(b, "showUnobtrusiveMessage - message was empty");
            return;
        }
        this.d();
        this.f = new IndefiniteToast(this.c, string2);
        this.f.a(l);
        this.f.a();
        this.e = connectionStatus;
    }

    protected String b(ChatManager connectionStatus) {
        return "tag" + (Object)((Object)connectionStatus);
    }

    public void b() {
        this.d.a(this);
        this.a(this.d.b());
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void b(String var1_1, ChatManager var2_2, long var3_3) {
        block7 : {
            Log.b(ConnectionStatusIndicator.b, "showUnobtrusiveMessage: " + var1_1);
            this.d();
            var5_4 = new Intent(this.c, MasterActivity.class);
            var5_4 = PendingIntent.getActivity((Context)this.c, (int)0, (Intent)var5_4, (int)0);
            var1_1 = new NotificationCompat.Builder(this.c).setLargeIcon(BitmapFactory.decodeResource((Resources)this.c.getResources(), (int)2130837837)).setContentTitle((CharSequence)this.c.getResources().getString(2131296402)).setContentText((CharSequence)var1_1).setContentIntent((PendingIntent)var5_4).setTicker((CharSequence)var1_1);
            switch (.a[var2_2.ordinal()]) {
                case 1: {
                    var1_1.setSmallIcon(2130837790);
                    ** break;
                }
                case 2: {
                    var1_1.setSmallIcon(2130837790);
                    ** break;
                }
                case 3: {
                    var1_1.setSmallIcon(2130838146);
                }
lbl15: // 4 sources:
                default: {
                    break block7;
                }
                case 4: 
            }
            var1_1.setSmallIcon(2130837789);
        }
        var5_4 = NotificationManagerCompat.from((Context)this.c);
        var5_4.notify(this.b(var2_2), 3800, var1_1.build());
        if (var3_3 > 0) {
            this.a.postDelayed(new Runnable((NotificationManagerCompat)var5_4, var2_2){
                final /* synthetic */ NotificationManagerCompat a;
                final /* synthetic */ ChatManager b;
                {
                    this.a = notificationManagerCompat;
                    this.b = connectionStatus;
                }

                @Override
                public void run() {
                    this.a.cancel(ConnectionStatusIndicator.this.b(this.b), 3800);
                }
            }, 1000 * var3_3);
        }
        this.e = var2_2;
    }

    public void c() {
        this.d.b(this);
        this.d();
    }

    protected void d() {
        Log.b(b, "removeStatusMessage");
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
        this.a.removeCallbacksAndMessages((Object)null);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from((Context)this.c);
        ChatManager[] arrconnectionStatus = ChatManager.values();
        int n = arrconnectionStatus.length;
        for (int i = 0; i < n; ++i) {
            notificationManagerCompat.cancel(this.b(arrconnectionStatus[i]), 3800);
        }
    }

    @Override
    public void e(Chat chat) {
    }

    @Override
    public void f(Chat chat) {
    }

    @Override
    public void g(Chat chat) {
    }

    @Override
    public void h(Chat chat) {
    }

}

