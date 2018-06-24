package com.smule.singandroid.media_player_service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaControllerCompat.Callback;
import android.support.v4.media.session.MediaControllerCompat.TransportControls;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.NotificationCompat.Builder;
import android.support.v7.app.NotificationCompat.MediaStyle;
import android.view.View;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.samsung.android.sdk.professionalaudio.SapaService.Parameters;
import com.smule.android.logging.Log;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.utils.MediaPlaybackUtils;

public class MediaNotificationController extends BroadcastReceiver {
    private static final String f23378a = MediaNotificationController.class.getName();
    private final MediaPlayerService f23379b;
    private Token f23380c;
    private MediaControllerCompat f23381d;
    private TransportControls f23382e;
    private PlaybackStateCompat f23383f;
    private MediaMetadataCompat f23384g;
    private final NotificationManager f23385h;
    private final PendingIntent f23386i;
    private final PendingIntent f23387j;
    private final PendingIntent f23388k;
    private final PendingIntent f23389l;
    private final int f23390m;
    private boolean f23391n = false;
    private final Callback f23392o = new C47171(this);

    class C47171 extends Callback {
        final /* synthetic */ MediaNotificationController f23376a;

        C47171(MediaNotificationController mediaNotificationController) {
            this.f23376a = mediaNotificationController;
        }

        public void onPlaybackStateChanged(@NonNull PlaybackStateCompat playbackStateCompat) {
            this.f23376a.f23383f = playbackStateCompat;
            Log.b(MediaNotificationController.f23378a, "Received new playback state" + playbackStateCompat);
            if (playbackStateCompat.getState() == 1 || playbackStateCompat.getState() == 0) {
                this.f23376a.m24571b();
            } else if (playbackStateCompat.getState() != 6) {
                this.f23376a.m24568f();
            }
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            this.f23376a.f23384g = mediaMetadataCompat;
            Log.b(MediaNotificationController.f23378a, "Received new metadata " + mediaMetadataCompat);
            this.f23376a.m24568f();
        }

        public void onSessionDestroyed() {
            super.onSessionDestroyed();
            Log.b(MediaNotificationController.f23378a, "Session was destroyed, resetting to the new session token");
            this.f23376a.m24565d();
        }
    }

    class C47182 implements ImageLoadingListener {
        final /* synthetic */ MediaNotificationController f23377a;

        C47182(MediaNotificationController mediaNotificationController) {
            this.f23377a = mediaNotificationController;
        }

        public void mo6154a(String str, View view) {
        }

        public void mo6156a(String str, View view, FailReason failReason) {
            Notification a = this.f23377a.m24552a(BitmapFactory.decodeResource(this.f23377a.f23379b.getResources(), C1947R.drawable.icn_android_notification_default_art), false);
            if (a != null) {
                this.f23377a.f23385h.notify(412, a);
            }
        }

        public void mo6155a(String str, View view, Bitmap bitmap) {
            if (this.f23377a.f23384g != null && this.f23377a.f23384g.getDescription().getIconUri() != null && this.f23377a.f23384g.getDescription().getIconUri().toString().equals(str)) {
                Log.b(MediaNotificationController.f23378a, "fetchBitmapFromURLAsync: set bitmap to " + str);
                Notification a = this.f23377a.m24552a(bitmap, false);
                if (a != null) {
                    this.f23377a.f23385h.notify(412, a);
                }
            }
        }

        public void mo6157b(String str, View view) {
        }
    }

    public MediaNotificationController(MediaPlayerService mediaPlayerService) {
        this.f23379b = mediaPlayerService;
        m24565d();
        this.f23390m = this.f23379b.getResources().getColor(C1947R.color.media_player_service_notification_background);
        this.f23385h = (NotificationManager) this.f23379b.getSystemService("notification");
        String packageName = this.f23379b.getPackageName();
        this.f23386i = PendingIntent.getBroadcast(this.f23379b, 100, new Intent("com.smule.singandroid.pause").setPackage(packageName), 268435456);
        this.f23387j = PendingIntent.getBroadcast(this.f23379b, 100, new Intent("com.smule.singandroid.play").setPackage(packageName), 268435456);
        this.f23388k = PendingIntent.getBroadcast(this.f23379b, 100, new Intent("com.smule.singandroid.prev").setPackage(packageName), 268435456);
        this.f23389l = PendingIntent.getBroadcast(this.f23379b, 100, new Intent("com.smule.singandroid.next").setPackage(packageName), 268435456);
        this.f23385h.cancelAll();
    }

    public void m24570a() {
        if (!this.f23391n) {
            this.f23384g = this.f23381d.getMetadata();
            this.f23383f = this.f23381d.getPlaybackState();
            Notification g = m24569g();
            if (g != null) {
                this.f23381d.registerCallback(this.f23392o);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.smule.singandroid.next");
                intentFilter.addAction("com.smule.singandroid.pause");
                intentFilter.addAction("com.smule.singandroid.play");
                intentFilter.addAction("com.smule.singandroid.prev");
                intentFilter.addAction("com.smule.singandroid.stop");
                this.f23379b.registerReceiver(this, intentFilter);
                this.f23379b.startForeground(412, g);
                this.f23391n = true;
            }
        }
    }

    public void m24571b() {
        if (this.f23391n) {
            this.f23391n = false;
            this.f23381d.unregisterCallback(this.f23392o);
            try {
                this.f23385h.cancel(412);
                this.f23379b.unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
            }
            this.f23379b.stopForeground(true);
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.b(f23378a, "Received intent with action " + action);
        Object obj = -1;
        switch (action.hashCode()) {
            case -709092671:
                if (action.equals("com.smule.singandroid.pause")) {
                    obj = null;
                    break;
                }
                break;
            case 392712392:
                if (action.equals("com.smule.singandroid.next")) {
                    obj = 2;
                    break;
                }
                break;
            case 392777993:
                if (action.equals("com.smule.singandroid.play")) {
                    obj = 1;
                    break;
                }
                break;
            case 392783880:
                if (action.equals("com.smule.singandroid.prev")) {
                    obj = 3;
                    break;
                }
                break;
            case 392875479:
                if (action.equals("com.smule.singandroid.stop")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.f23382e.pause();
                return;
            case 1:
                this.f23382e.play();
                return;
            case 2:
                this.f23382e.skipToNext();
                return;
            case 3:
                this.f23382e.skipToPrevious();
                return;
            case 4:
                this.f23382e.stop();
                return;
            default:
                Log.d(f23378a, "Unknown intent ignored. Action=" + action);
                return;
        }
    }

    private void m24565d() {
        Token c = this.f23379b.m24623c();
        if (this.f23380c == null || !this.f23380c.equals(c)) {
            if (this.f23381d != null) {
                this.f23381d.unregisterCallback(this.f23392o);
            }
            this.f23380c = c;
            try {
                this.f23381d = new MediaControllerCompat(this.f23379b, this.f23380c);
                this.f23382e = this.f23381d.getTransportControls();
                if (this.f23391n) {
                    this.f23381d.registerCallback(this.f23392o);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private PendingIntent m24566e() {
        Intent intent = new Intent(this.f23379b, MasterActivity_.class);
        intent.putExtra("com.smule.INTENT_EXTRA_FROM_MEDIA_NOTIFICATION", true);
        return PendingIntent.getActivity(this.f23379b, 100, intent, 268435456);
    }

    private PendingIntent m24554a(boolean z) {
        return PendingIntent.getBroadcast(this.f23379b, z ? 0 : 100, new Intent("com.smule.singandroid.stop").setPackage(this.f23379b.getPackageName()), 268435456);
    }

    private void m24568f() {
        Notification g = m24569g();
        if (g != null) {
            this.f23385h.notify(412, g);
            if (this.f23383f == null || this.f23383f.getState() != 3) {
                this.f23379b.stopForeground(false);
            } else {
                this.f23379b.startForeground(412, g);
            }
        }
    }

    private Notification m24569g() {
        return m24552a(null, true);
    }

    private Notification m24552a(Bitmap bitmap, boolean z) {
        Bitmap bitmap2 = null;
        Log.b(f23378a, "createNotification. mMetadata=" + this.f23384g);
        if (this.f23384g == null || this.f23383f == null) {
            return null;
        }
        int i;
        String str;
        Builder builder = new Builder(this.f23379b);
        if ((this.f23383f.getActions() & 16) != 0) {
            builder.addAction(C1947R.drawable.ic_media_previous, this.f23379b.getString(C1947R.string.media_player_previous), this.f23388k);
            i = true;
        } else {
            boolean z2 = false;
        }
        m24557a(builder);
        if ((this.f23383f.getActions() & 32) != 0) {
            builder.addAction(C1947R.drawable.ic_media_next, this.f23379b.getString(C1947R.string.media_player_next), this.f23389l);
        }
        MediaDescriptionCompat description = this.f23384g.getDescription();
        if (bitmap != null) {
            str = null;
            bitmap2 = bitmap;
        } else if (description.getIconUri() != null) {
            str = description.getIconUri().toString();
            Log.b(f23378a, "Loading album art: " + str);
            bitmap = ImageUtils.b(str);
            if (bitmap == null) {
                Log.b(f23378a, "Art not found in cache");
            } else {
                Log.b(f23378a, "Art found in cache");
                str = null;
            }
            bitmap2 = bitmap;
        } else {
            str = null;
        }
        builder.setStyle(new MediaStyle().setShowActionsInCompactView(new int[]{i}).setMediaSession(this.f23380c).setShowCancelButton(true).setCancelButtonIntent(m24554a(true))).setColor(this.f23390m).setSmallIcon(C1947R.drawable.icn_android_notification).setVisibility(1).setUsesChronometer(true).setContentIntent(m24566e()).setContentTitle(description.getTitle()).setContentText(description.getSubtitle()).setDeleteIntent(m24554a(false)).setLargeIcon(bitmap2);
        m24560b(builder);
        if (str != null && z) {
            m24559a(str, builder);
        }
        return builder.build();
    }

    private void m24557a(Builder builder) {
        CharSequence string;
        int i;
        PendingIntent pendingIntent;
        Log.b(f23378a, "updatePlayPauseAction state: " + MediaPlaybackUtils.m25872a(this.f23383f.getState()));
        if (this.f23383f.getState() == 3 || this.f23383f.getState() == 6 || this.f23383f.getState() == 8) {
            string = this.f23379b.getString(C1947R.string.media_player_pause);
            i = C1947R.drawable.ic_media_pause;
            pendingIntent = this.f23386i;
        } else {
            string = this.f23379b.getString(C1947R.string.media_player_play);
            i = C1947R.drawable.ic_media_play;
            pendingIntent = this.f23387j;
        }
        builder.addAction(new Action(i, string, pendingIntent));
    }

    private void m24560b(Builder builder) {
        boolean z = true;
        Log.b(f23378a, "updateNotificationPlaybackState. mPlaybackState=" + this.f23383f);
        if (this.f23383f == null || !this.f23391n) {
            Log.b(f23378a, "updateNotificationPlaybackState. cancelling notification!");
            this.f23379b.stopForeground(true);
            return;
        }
        if (this.f23383f.getState() != 3 || this.f23383f.getPosition() < 0) {
            Log.b(f23378a, "updateNotificationPlaybackState. hiding playback position");
            builder.setWhen(0).setShowWhen(false).setUsesChronometer(false);
        } else {
            Log.b(f23378a, "updateNotificationPlaybackState. updating playback position to " + ((System.currentTimeMillis() - this.f23383f.getPosition()) / 1000) + " seconds");
            builder.setWhen(System.currentTimeMillis() - this.f23383f.getPosition()).setShowWhen(true).setUsesChronometer(true);
        }
        if (this.f23383f.getState() != 3) {
            z = false;
        }
        builder.setOngoing(z);
    }

    private void m24559a(String str, Builder builder) {
        Log.b(f23378a, "Attempting async: " + str);
        ImageLoader.a().a(str, new ImageSize(800, Parameters.BUFFER_SIZE_480), new DisplayImageOptions.Builder().m17114a(true).m17116b(true).m17115a(), new C47182(this));
    }
}
