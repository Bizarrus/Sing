/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.net.Uri
 *  android.os.RemoteException
 *  android.support.annotation.NonNull
 *  android.support.v4.app.NotificationCompat
 *  android.support.v4.app.NotificationCompat$Action
 *  android.support.v4.app.NotificationCompat$Builder
 *  android.support.v4.app.NotificationCompat$Style
 *  android.support.v4.media.MediaDescriptionCompat
 *  android.support.v4.media.MediaMetadataCompat
 *  android.support.v4.media.session.MediaControllerCompat
 *  android.support.v4.media.session.MediaControllerCompat$Callback
 *  android.support.v4.media.session.MediaControllerCompat$TransportControls
 *  android.support.v4.media.session.MediaSessionCompat
 *  android.support.v4.media.session.MediaSessionCompat$Token
 *  android.support.v4.media.session.PlaybackStateCompat
 *  android.support.v7.app.NotificationCompat
 *  android.support.v7.app.NotificationCompat$Builder
 *  android.support.v7.app.NotificationCompat$MediaStyle
 *  android.view.View
 *  com.nostra13.universalimageloader.core.DisplayImageOptions
 *  com.nostra13.universalimageloader.core.DisplayImageOptions$Builder
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.assist.FailReason
 *  com.nostra13.universalimageloader.core.assist.ImageSize
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.smule.singandroid.utils.MediaPlaybackUtils
 */
package com.smule.singandroid.media_player_service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.smule.android.logging.Log;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.media_player_service.MediaPlayerService;
import com.smule.singandroid.utils.MediaPlaybackUtils;

public class MediaNotificationController
extends BroadcastReceiver {
    private static final String a = MediaNotificationController.class.getName();
    private final MediaPlayerService b;
    private MediaSessionCompat.Token c;
    private MediaControllerCompat d;
    private MediaControllerCompat.TransportControls e;
    private PlaybackStateCompat f;
    private MediaMetadataCompat g;
    private final NotificationManager h;
    private final PendingIntent i;
    private final PendingIntent j;
    private final PendingIntent k;
    private final PendingIntent l;
    private final int m;
    private boolean n = false;
    private final MediaControllerCompat.Callback o;

    public MediaNotificationController(MediaPlayerService object) {
        this.o = new MediaControllerCompat.Callback(){

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
                MediaNotificationController.this.g = mediaMetadataCompat;
                Log.b(a, "Received new metadata " + (Object)mediaMetadataCompat);
                MediaNotificationController.this.f();
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onPlaybackStateChanged(@NonNull PlaybackStateCompat playbackStateCompat) {
                MediaNotificationController.this.f = playbackStateCompat;
                Log.b(a, "Received new playback state" + (Object)playbackStateCompat);
                if (playbackStateCompat.getState() == 1 || playbackStateCompat.getState() == 0) {
                    MediaNotificationController.this.b();
                    return;
                } else {
                    if (playbackStateCompat.getState() == 6) return;
                    {
                        MediaNotificationController.this.f();
                        return;
                    }
                }
            }

            public void onSessionDestroyed() {
                super.onSessionDestroyed();
                Log.b(a, "Session was destroyed, resetting to the new session token");
                MediaNotificationController.this.d();
            }
        };
        this.b = object;
        this.d();
        this.m = this.b.getResources().getColor(2131689805);
        this.h = (NotificationManager)this.b.getSystemService("notification");
        object = this.b.getPackageName();
        this.i = PendingIntent.getBroadcast((Context)this.b, (int)100, (Intent)new Intent("com.smule.singandroid.pause").setPackage((String)object), (int)268435456);
        this.j = PendingIntent.getBroadcast((Context)this.b, (int)100, (Intent)new Intent("com.smule.singandroid.play").setPackage((String)object), (int)268435456);
        this.k = PendingIntent.getBroadcast((Context)this.b, (int)100, (Intent)new Intent("com.smule.singandroid.prev").setPackage((String)object), (int)268435456);
        this.l = PendingIntent.getBroadcast((Context)this.b, (int)100, (Intent)new Intent("com.smule.singandroid.next").setPackage((String)object), (int)268435456);
        this.h.cancelAll();
    }

    /*
     * Enabled aggressive block sorting
     */
    private Notification a(Bitmap object, boolean bl) {
        int n;
        Object object2;
        NotificationCompat.Builder builder;
        MediaDescriptionCompat mediaDescriptionCompat;
        block9 : {
            block8 : {
                block7 : {
                    object2 = null;
                    Log.b(a, "createNotification. mMetadata=" + (Object)this.g);
                    if (this.g == null || this.f == null) {
                        return null;
                    }
                    builder = new NotificationCompat.Builder((Context)this.b);
                    if ((this.f.getActions() & 16) != 0) {
                        builder.addAction(2130837841, (CharSequence)this.b.getString(2131296949), this.k);
                        n = 1;
                    } else {
                        n = 0;
                    }
                    this.a(builder);
                    if ((this.f.getActions() & 32) != 0) {
                        builder.addAction(2130837838, (CharSequence)this.b.getString(2131296946), this.l);
                    }
                    mediaDescriptionCompat = this.g.getDescription();
                    if (object != null) break block7;
                    if (mediaDescriptionCompat.getIconUri() == null) break block8;
                    object = mediaDescriptionCompat.getIconUri().toString();
                    Log.b(a, "Loading album art: " + (String)object);
                    object2 = ImageUtils.b((String)object);
                    if (object2 == null) {
                        Log.b(a, "Art not found in cache");
                    } else {
                        Log.b(a, "Art found in cache");
                        object = null;
                    }
                    break block9;
                }
                Object var5_7 = null;
                object2 = object;
                object = var5_7;
                break block9;
            }
            object = null;
        }
        builder.setStyle((NotificationCompat.Style)new NotificationCompat.MediaStyle().setShowActionsInCompactView(new int[]{n}).setMediaSession(this.c).setShowCancelButton(true).setCancelButtonIntent(this.a(true))).setColor(this.m).setSmallIcon(2130837864).setVisibility(1).setUsesChronometer(true).setContentIntent(this.e()).setContentTitle(mediaDescriptionCompat.getTitle()).setContentText(mediaDescriptionCompat.getSubtitle()).setDeleteIntent(this.a(false)).setLargeIcon((Bitmap)object2);
        this.b(builder);
        if (object != null && bl) {
            this.a((String)object, builder);
        }
        return builder.build();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private PendingIntent a(boolean bl) {
        int n;
        String string2 = this.b.getPackageName();
        MediaPlayerService mediaPlayerService = this.b;
        if (bl) {
            n = 0;
            do {
                return PendingIntent.getBroadcast((Context)mediaPlayerService, (int)n, (Intent)new Intent("com.smule.singandroid.stop").setPackage(string2), (int)268435456);
                break;
            } while (true);
        }
        n = 100;
        return PendingIntent.getBroadcast((Context)mediaPlayerService, (int)n, (Intent)new Intent("com.smule.singandroid.stop").setPackage(string2), (int)268435456);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(NotificationCompat.Builder builder) {
        PendingIntent pendingIntent;
        int n;
        String string2;
        Log.b(a, "updatePlayPauseAction state: " + MediaPlaybackUtils.a((int)this.f.getState()));
        if (this.f.getState() == 3 || this.f.getState() == 6 || this.f.getState() == 8) {
            string2 = this.b.getString(2131296947);
            n = 2130837839;
            pendingIntent = this.i;
        } else {
            string2 = this.b.getString(2131296948);
            n = 2130837840;
            pendingIntent = this.j;
        }
        builder.addAction(new NotificationCompat.Action(n, (CharSequence)string2, pendingIntent));
    }

    private void a(String string2, NotificationCompat.Builder builder) {
        Log.b(a, "Attempting async: " + string2);
        ImageLoader.a().a(string2, new ImageSize(800, 480), new DisplayImageOptions.Builder().a(true).b(true).a(), new ImageLoadingListener(){

            public void a(String string2, View view) {
            }

            public void a(String string2, View view, Bitmap bitmap) {
                if (MediaNotificationController.this.g != null && MediaNotificationController.this.g.getDescription().getIconUri() != null && MediaNotificationController.this.g.getDescription().getIconUri().toString().equals(string2)) {
                    Log.b(a, "fetchBitmapFromURLAsync: set bitmap to " + string2);
                    string2 = MediaNotificationController.this.a(bitmap, false);
                    if (string2 != null) {
                        MediaNotificationController.this.h.notify(412, (Notification)string2);
                    }
                }
            }

            public void a(String string2, View view, FailReason failReason) {
                string2 = BitmapFactory.decodeResource((Resources)MediaNotificationController.this.b.getResources(), (int)2130837865);
                if ((string2 = MediaNotificationController.this.a((Bitmap)string2, false)) != null) {
                    MediaNotificationController.this.h.notify(412, (Notification)string2);
                }
            }

            public void b(String string2, View view) {
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(NotificationCompat.Builder builder) {
        boolean bl = true;
        Log.b(a, "updateNotificationPlaybackState. mPlaybackState=" + (Object)this.f);
        if (this.f == null || !this.n) {
            Log.b(a, "updateNotificationPlaybackState. cancelling notification!");
            this.b.stopForeground(true);
            return;
        }
        if (this.f.getState() == 3 && this.f.getPosition() >= 0) {
            Log.b(a, "updateNotificationPlaybackState. updating playback position to " + (System.currentTimeMillis() - this.f.getPosition()) / 1000 + " seconds");
            builder.setWhen(System.currentTimeMillis() - this.f.getPosition()).setShowWhen(true).setUsesChronometer(true);
        } else {
            Log.b(a, "updateNotificationPlaybackState. hiding playback position");
            builder.setWhen(0).setShowWhen(false).setUsesChronometer(false);
        }
        if (this.f.getState() != 3) {
            bl = false;
        }
        builder.setOngoing(bl);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void d() {
        MediaSessionCompat.Token token = this.b.c();
        if (this.c != null && this.c.equals((Object)token)) return;
        if (this.d != null) {
            this.d.unregisterCallback(this.o);
        }
        this.c = token;
        try {
            this.d = new MediaControllerCompat((Context)this.b, this.c);
            this.e = this.d.getTransportControls();
            if (!this.n) return;
            this.d.registerCallback(this.o);
            return;
        }
        catch (RemoteException remoteException) {
            remoteException.printStackTrace();
            return;
        }
    }

    private PendingIntent e() {
        Intent intent = new Intent((Context)this.b, MasterActivity_.class);
        intent.putExtra("com.smule.INTENT_EXTRA_FROM_MEDIA_NOTIFICATION", true);
        return PendingIntent.getActivity((Context)this.b, (int)100, (Intent)intent, (int)268435456);
    }

    private void f() {
        block3 : {
            block2 : {
                Notification notification = this.g();
                if (notification == null) break block2;
                this.h.notify(412, notification);
                if (this.f == null || this.f.getState() != 3) break block3;
                this.b.startForeground(412, notification);
            }
            return;
        }
        this.b.stopForeground(false);
    }

    private Notification g() {
        return this.a(null, true);
    }

    public void a() {
        if (!this.n) {
            this.g = this.d.getMetadata();
            this.f = this.d.getPlaybackState();
            Notification notification = this.g();
            if (notification != null) {
                this.d.registerCallback(this.o);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.smule.singandroid.next");
                intentFilter.addAction("com.smule.singandroid.pause");
                intentFilter.addAction("com.smule.singandroid.play");
                intentFilter.addAction("com.smule.singandroid.prev");
                intentFilter.addAction("com.smule.singandroid.stop");
                this.b.registerReceiver((BroadcastReceiver)this, intentFilter);
                this.b.startForeground(412, notification);
                this.n = true;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b() {
        if (this.n) {
            this.n = false;
            this.d.unregisterCallback(this.o);
            try {
                this.h.cancel(412);
                this.b.unregisterReceiver((BroadcastReceiver)this);
            }
            catch (IllegalArgumentException illegalArgumentException) {}
            this.b.stopForeground(true);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onReceive(Context var1_1, Intent var2_2) {
        block19 : {
            var1_1 = var2_2.getAction();
            Log.b(MediaNotificationController.a, "Received intent with action " + (String)var1_1);
            var3_3 = -1;
            switch (var1_1.hashCode()) {
                case -709092671: {
                    if (var1_1.equals("com.smule.singandroid.pause")) {
                        var3_3 = 0;
                        ** break;
                    }
                    ** GOTO lbl23
                }
                case 392777993: {
                    if (var1_1.equals("com.smule.singandroid.play")) {
                        var3_3 = 1;
                        ** break;
                    }
                    ** GOTO lbl23
                }
                case 392712392: {
                    if (var1_1.equals("com.smule.singandroid.next")) {
                        var3_3 = 2;
                        ** break;
                    }
                    ** GOTO lbl23
                }
                case 392783880: {
                    if (var1_1.equals("com.smule.singandroid.prev")) {
                        var3_3 = 3;
                    }
                }
lbl23: // 10 sources:
                default: {
                    break block19;
                }
                case 392875479: 
            }
            if (var1_1.equals("com.smule.singandroid.stop")) {
                var3_3 = 4;
            }
        }
        switch (var3_3) {
            default: {
                Log.d(MediaNotificationController.a, "Unknown intent ignored. Action=" + (String)var1_1);
                return;
            }
            case 0: {
                this.e.pause();
                return;
            }
            case 1: {
                this.e.play();
                return;
            }
            case 2: {
                this.e.skipToNext();
                return;
            }
            case 3: {
                this.e.skipToPrevious();
                return;
            }
            case 4: 
        }
        this.e.stop();
    }

}

