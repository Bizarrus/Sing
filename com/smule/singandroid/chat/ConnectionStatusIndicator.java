package com.smule.singandroid.chat;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import com.smule.android.logging.Log;
import com.smule.android.utils.IndefiniteToast;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManager$ConnectionStatus;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.GroupChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity;

public class ConnectionStatusIndicator implements ChatManagerListener {
    private static String f21028b = ConnectionStatusIndicator.class.getName();
    Handler f21029a = new Handler(Looper.getMainLooper());
    private final Context f21030c;
    private final ChatManager f21031d;
    private ChatManager$ConnectionStatus f21032e;
    private IndefiniteToast f21033f;

    public ConnectionStatusIndicator(Context context, ChatManager chatManager) {
        this.f21030c = context;
        this.f21031d = chatManager;
    }

    public void m22693b() {
        this.f21031d.a(this);
        mo6320a(this.f21031d.b());
    }

    public void m22695c() {
        this.f21031d.b(this);
        m22696d();
    }

    public void mo6320a(ChatManager$ConnectionStatus chatManager$ConnectionStatus) {
        switch (chatManager$ConnectionStatus) {
            case NO_NETWORK:
                m22691a(this.f21030c.getString(C1947R.string.login_cannot_connect_to_smule), chatManager$ConnectionStatus, -1);
                return;
            case DISCONNECTED:
                if (this.f21032e == ChatManager$ConnectionStatus.CONNECTED) {
                    m22694b(this.f21030c.getString(C1947R.string.chat_status_disconnected), chatManager$ConnectionStatus, -1);
                    return;
                }
                return;
            case CONNECTING:
                m22694b(this.f21030c.getString(C1947R.string.chat_status_connecting), chatManager$ConnectionStatus, -1);
                return;
            case CONNECTED:
                if (this.f21032e != null && this.f21032e != ChatManager$ConnectionStatus.CONNECTED) {
                    m22694b(this.f21030c.getString(C1947R.string.chat_status_connected), chatManager$ConnectionStatus, 3);
                    return;
                }
                return;
            case NO_HOSTS:
                m22691a("Invalid connection status\nNo hosts", chatManager$ConnectionStatus, -1);
                return;
            default:
                m22691a("Invalid connection status\nThis is very broken :(", chatManager$ConnectionStatus, -1);
                return;
        }
    }

    public void mo6319a() {
    }

    public void mo6322e(Chat chat) {
    }

    public void mo6323f(Chat chat) {
    }

    public void mo6324g(Chat chat) {
    }

    public void mo6325h(Chat chat) {
    }

    public void mo6321a(GroupChat groupChat) {
    }

    protected void m22691a(String str, ChatManager$ConnectionStatus chatManager$ConnectionStatus, long j) {
        Log.b(f21028b, "showObviousMessage: " + str);
        if (str == null || str.isEmpty()) {
            Log.e(f21028b, "showUnobtrusiveMessage - message was empty");
            return;
        }
        m22696d();
        this.f21033f = new IndefiniteToast(this.f21030c, str);
        this.f21033f.m18979a(j);
        this.f21033f.m18978a();
        this.f21032e = chatManager$ConnectionStatus;
    }

    protected String m22692b(ChatManager$ConnectionStatus chatManager$ConnectionStatus) {
        return "tag" + chatManager$ConnectionStatus;
    }

    protected void m22694b(String str, final ChatManager$ConnectionStatus chatManager$ConnectionStatus, long j) {
        Log.b(f21028b, "showUnobtrusiveMessage: " + str);
        m22696d();
        Builder ticker = new Builder(this.f21030c).setLargeIcon(BitmapFactory.decodeResource(this.f21030c.getResources(), C1947R.drawable.ic_launcher)).setContentTitle(this.f21030c.getResources().getString(C1947R.string.app_name)).setContentText(str).setContentIntent(PendingIntent.getActivity(this.f21030c, 0, new Intent(this.f21030c, MasterActivity.class), 0)).setTicker(str);
        switch (chatManager$ConnectionStatus) {
            case NO_NETWORK:
                ticker.setSmallIcon(C1947R.drawable.connected_lost);
                break;
            case DISCONNECTED:
                ticker.setSmallIcon(C1947R.drawable.connected_lost);
                break;
            case CONNECTING:
                ticker.setSmallIcon(C1947R.drawable.notification_connecting);
                break;
            case CONNECTED:
                ticker.setSmallIcon(C1947R.drawable.connected_check);
                break;
        }
        final NotificationManagerCompat from = NotificationManagerCompat.from(this.f21030c);
        from.notify(m22692b(chatManager$ConnectionStatus), 3800, ticker.build());
        if (j > 0) {
            this.f21029a.postDelayed(new Runnable(this) {
                final /* synthetic */ ConnectionStatusIndicator f21026c;

                public void run() {
                    from.cancel(this.f21026c.m22692b(chatManager$ConnectionStatus), 3800);
                }
            }, 1000 * j);
        }
        this.f21032e = chatManager$ConnectionStatus;
    }

    protected void m22696d() {
        Log.b(f21028b, "removeStatusMessage");
        if (this.f21033f != null) {
            this.f21033f.m18981c();
            this.f21033f = null;
        }
        this.f21029a.removeCallbacksAndMessages(null);
        NotificationManagerCompat from = NotificationManagerCompat.from(this.f21030c);
        for (ChatManager$ConnectionStatus b : ChatManager$ConnectionStatus.values()) {
            from.cancel(m22692b(b), 3800);
        }
    }
}
