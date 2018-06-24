/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.app.Service
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.net.Uri
 *  android.net.wifi.WifiManager
 *  android.net.wifi.WifiManager$WifiLock
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Message
 *  android.os.Parcelable
 *  android.os.SystemClock
 *  android.support.annotation.NonNull
 *  android.support.v4.media.MediaDescriptionCompat
 *  android.support.v4.media.MediaMetadataCompat
 *  android.support.v4.media.MediaMetadataCompat$Builder
 *  android.support.v4.media.session.MediaButtonReceiver
 *  android.support.v4.media.session.MediaSessionCompat
 *  android.support.v4.media.session.MediaSessionCompat$Callback
 *  android.support.v4.media.session.MediaSessionCompat$Token
 *  android.support.v4.media.session.PlaybackStateCompat
 *  android.support.v4.media.session.PlaybackStateCompat$Builder
 *  android.view.KeyEvent
 *  android.view.TextureView
 *  android.view.View
 *  com.nostra13.universalimageloader.core.DisplayImageOptions
 *  com.nostra13.universalimageloader.core.DisplayImageOptions$Builder
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.assist.FailReason
 *  com.nostra13.universalimageloader.core.assist.ImageSize
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.smule.singandroid.utils.MediaPlaybackUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.video.ExoPlayerPlaybackWrapper
 */
package com.smule.singandroid.media_player_service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.media_player_service.MediaNotificationController;
import com.smule.singandroid.media_player_service.Playback;
import com.smule.singandroid.media_player_service.QueueItem;
import com.smule.singandroid.utils.MediaPlaybackUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.video.ExoPlayerPlaybackWrapper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MediaPlayerService
extends Service
implements AudioManager.OnAudioFocusChangeListener,
Playback.Callback {
    static MediaPlayerService b;
    private static final String d;
    final String a = "MediaPlayerService_Session";
    WeakReference<TextureView> c;
    private MediaSessionCompat e;
    private List<QueueItem> f = new ArrayList<QueueItem>();
    private int g;
    private MediaNotificationController h;
    private boolean i;
    private final DelayedStopHandler j;
    private Playback k;
    private boolean l;
    private AudioManager m;
    private int n;
    private WifiManager.WifiLock o;
    private boolean p;
    private boolean q;
    private volatile boolean r;
    private final IntentFilter s;
    private final BroadcastReceiver t;

    static {
        d = MediaPlayerService.class.getName();
    }

    public MediaPlayerService() {
        this.j = new DelayedStopHandler(this);
        this.l = false;
        this.c = null;
        this.p = true;
        this.q = false;
        this.s = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
        this.t = new BroadcastReceiver(){

            public void onReceive(Context context, Intent intent) {
                if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                    Log.b(d, "Headphones disconnected.");
                    if (MediaPlayerService.this.k != null && MediaPlayerService.this.k.d()) {
                        context = new Intent(context, MediaPlayerService.class);
                        context.setAction("com.smule.ACTION_CMD");
                        context.putExtra("CMD_NAME", "CMD_PAUSE");
                        MediaPlayerService.this.startService((Intent)context);
                    }
                }
            }
        };
    }

    public static MediaPlayerService a() {
        return b;
    }

    private void a(PlaybackStateCompat.Builder object) {
        object = this.s();
        if (object != null) {
            object.b();
        }
    }

    private void a(final QueueItem queueItem, final String string2, String string3, int n, int n2) {
        ImageLoader.a().a(string3, new ImageSize(n, n2), new DisplayImageOptions.Builder().a(true).b(true).a(), new ImageLoadingListener(){

            public void a(String string22, View view) {
            }

            public void a(String string22, View object, Bitmap bitmap) {
                string22 = new MediaMetadataCompat.Builder(queueItem.a((Context)MediaPlayerService.this)).putBitmap(string2, bitmap).build();
                queueItem.a((MediaMetadataCompat)string22);
                object = ((QueueItem)MediaPlayerService.this.f.get(MediaPlayerService.this.g)).b();
                if (queueItem.b().equals(object)) {
                    MediaPlayerService.this.e.setMetadata((MediaMetadataCompat)string22);
                }
            }

            public void a(String string22, View view, FailReason failReason) {
            }

            public void b(String string22, View view) {
            }
        });
    }

    private void a(String string2) {
        Log.b(d, "handleStopRequest: mState=" + this.l() + " error=" + string2);
        if (this.k != null) {
            this.k.a(false);
            this.k.b();
            this.k = null;
        }
        if (this.e.isActive()) {
            this.e.setActive(false);
        }
        this.j.b();
        this.u();
        this.w();
        this.b(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(String string2) {
        int n = this.q();
        Log.b(d, "updatePlaybackState, playback state=" + MediaPlaybackUtils.a((int)n));
        long l = -1;
        QueueItem queueItem = this.s();
        boolean bl = queueItem != null && queueItem.h();
        if (this.k != null) {
            long l2;
            l = l2 = this.k.j();
            if (queueItem != null) {
                l = l2;
                if (queueItem.e() == 0) {
                    long l3 = this.d();
                    l = l2;
                    if (l3 != 0) {
                        queueItem.a(l3);
                        l = l2;
                    }
                }
            }
        }
        queueItem = new PlaybackStateCompat.Builder().setActions(this.r());
        this.a((PlaybackStateCompat.Builder)queueItem);
        if (string2 != null) {
            queueItem.setErrorMessage((CharSequence)string2);
            n = 7;
        }
        queueItem.setState(n, l, 1.0f, SystemClock.elapsedRealtime());
        this.e.setPlaybackState(queueItem.build());
        if (bl && (n == 3 || n == 2)) {
            this.h.a();
        }
    }

    private void b(final boolean bl) {
        Log.b(d, "handlePlayRequest: mState=" + this.l());
        if (this.g == -1 || this.g >= this.f.size()) {
            Log.e(d, "play queue is empty size: " + this.f.size() + ". Or index is off: " + this.g);
            return;
        }
        this.j.a();
        if (!this.o.isHeld()) {
            this.o.acquire();
        }
        this.v();
        this.t();
        if (!this.i) {
            Log.a(d, "Starting service");
            this.startService(new Intent(this.getApplicationContext(), MediaPlayerService.class));
            this.i = true;
        }
        if (!this.e.isActive()) {
            this.e.setActive(true);
        }
        final QueueItem queueItem = this.f.get(this.g);
        if (this.k != null && queueItem.b().equals(this.k.k()) && this.q() == 2) {
            this.k.a(queueItem, bl);
            return;
        }
        this.p();
        this.a(MiscUtils.a((PerformanceV2)queueItem.a()), queueItem.g());
        queueItem.a(new QueueItem.DownloadResourcesListener(){

            @Override
            public void a() {
                if (MediaPlayerService.this.f.get(MediaPlayerService.this.g) == queueItem && MediaPlayerService.this.k != null) {
                    MediaPlayerService.this.k.a(queueItem, bl);
                }
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void b() {
                int n = queueItem.a() != null ? 2131297024 : 2131297522;
                MediaPlayerService.this.b(MediaPlayerService.this.getApplicationContext().getResources().getString(n));
            }
        });
    }

    static /* synthetic */ int e(MediaPlayerService mediaPlayerService) {
        int n = mediaPlayerService.g;
        mediaPlayerService.g = n + 1;
        return n;
    }

    static /* synthetic */ int h(MediaPlayerService mediaPlayerService) {
        int n = mediaPlayerService.g;
        mediaPlayerService.g = n - 1;
        return n;
    }

    private String l() {
        return MediaPlaybackUtils.a((int)this.q());
    }

    private void m() {
        this.b(true);
    }

    private void n() {
        Log.b(d, "handlePauseRequest: mState=" + this.l());
        if (this.k != null) {
            this.k.i();
        }
        this.j.b();
        this.u();
        this.w();
    }

    private void o() {
        Log.b(d, "handleStopServiceRequest: mState=" + this.l());
        if (this.k != null) {
            this.k.b();
            this.k = null;
        }
        if (this.o.isHeld()) {
            this.o.release();
        }
        this.u();
        this.w();
        if (this.e.isActive()) {
            this.e.setActive(false);
        }
        this.stopSelf();
        this.i = false;
    }

    private void p() {
        QueueItem queueItem = this.s();
        Object object = queueItem.a((Context)this);
        Log.b(d, "Updating metadata for AudioID: " + queueItem.b());
        this.e.setMetadata((MediaMetadataCompat)object);
        if (object.getDescription().getIconBitmap() == null && object.getDescription().getIconUri() != null) {
            object = object.getDescription().getIconUri().toString();
            this.a(queueItem, "android.media.metadata.ALBUM_ART", (String)object, 800, 480);
            this.a(queueItem, "android.media.metadata.DISPLAY_ICON", (String)object, 128, 128);
        }
    }

    private int q() {
        if (this.k != null) {
            return this.k.c();
        }
        return 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private long r() {
        long l;
        long l2 = l = 4;
        if (this.k == null) return l2;
        l2 = l;
        if (this.f == null) return l2;
        if (this.f.isEmpty()) {
            return l;
        }
        l2 = l;
        if (this.k.d()) {
            l2 = 4 | 2;
        }
        l = l2;
        if (this.g > 0) {
            l = l2 | 16;
        }
        l2 = l;
        if (this.g >= this.f.size() - 1) return l2;
        return l | 32;
    }

    private QueueItem s() {
        if (this.g != -1 && this.g < this.f.size()) {
            return this.f.get(this.g);
        }
        return null;
    }

    private void t() {
        Log.b(d, "tryToGetAudioFocus");
        if (!this.q) {
            this.m.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener)this);
            if (this.m.requestAudioFocus((AudioManager.OnAudioFocusChangeListener)this, 3, 1) == 1) {
                this.q = true;
            }
        }
        if (this.k != null) {
            this.k.b(this.q);
        }
    }

    private void u() {
        Log.b(d, "giveUpAudioFocus");
        if (this.q && this.m.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener)this) == 1) {
            this.q = false;
            this.onAudioFocusChange(-1);
            if (this.k != null) {
                this.k.b(this.q);
            }
        }
    }

    private void v() {
        if (!this.r) {
            this.registerReceiver(this.t, this.s);
            this.r = true;
        }
    }

    private void w() {
        if (this.r) {
            this.unregisterReceiver(this.t);
            this.r = false;
        }
    }

    @Override
    public void a(int n) {
        this.b(null);
    }

    public void a(TextureView textureView) {
        Log.b(d, "setVideoPlaybackView");
        this.c = new WeakReference<TextureView>(textureView);
        this.a(true);
    }

    protected void a(@NonNull List<QueueItem> list, boolean bl) {
        this.f = list;
        this.b(bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(boolean bl) {
        TextureView textureView = this.c != null ? this.c.get() : null;
        if (!bl) return;
        if (this.k != null) {
            this.k.a(textureView);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl, boolean bl2) {
        String string2 = d;
        StringBuilder stringBuilder = new StringBuilder().append("resetPlaybackIfNeeded; video? ").append(bl).append("; needed? ");
        boolean bl3 = this.l != bl;
        Log.b(string2, stringBuilder.append(bl3).toString());
        if (this.k != null) {
            this.k.a(true);
            this.k.b();
        }
        string2 = new ExoPlayerPlaybackWrapper((Context)this, (Playback.Callback)this, bl);
        string2.e(bl2);
        this.k = string2;
        if (bl) {
            this.a(true);
        } else {
            this.g();
        }
        this.t();
        this.l = bl;
    }

    protected void b() {
        Log.b(d, "broadcastSessionToken");
        Intent intent = new Intent("com.smule.BROADCAST_ACTION_SESSION_TOKEN_UPDATED");
        intent.putExtra("com.smule.EXRTA_SESSION_TOKEN", (Parcelable)this.c());
        this.sendBroadcast(intent);
    }

    @Override
    public void b(int n) {
        this.n = n;
    }

    public MediaSessionCompat.Token c() {
        if (this.e != null) {
            return this.e.getSessionToken();
        }
        return null;
    }

    public long d() {
        if (this.k == null) {
            return 0;
        }
        return this.k.f();
    }

    public boolean e() {
        if (this.k == null) {
            return false;
        }
        return this.k.e();
    }

    public long f() {
        if (this.k == null) {
            return 0;
        }
        return this.k.g();
    }

    protected void g() {
        this.a((TextureView)null);
    }

    public void h() {
        this.p = true;
        this.j.a();
    }

    public void i() {
        this.p = false;
        this.j.b();
    }

    public boolean j() {
        return this.p;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onAudioFocusChange(int n) {
        boolean bl = true;
        Log.b(d, "onAudioFocusChange. focusChange = " + n);
        if (n != 1) {
            bl = false;
        }
        this.q = bl;
        if (this.k != null) {
            this.k.b(n);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.b(d, "onCreate");
        b = this;
        this.m = (AudioManager)this.getSystemService("audio");
        this.o = ((WifiManager)this.getSystemService("wifi")).createWifiLock(1, "sing_lock");
        this.e = new MediaSessionCompat((Context)this, "MediaPlayerService_Session", new ComponentName((Context)this, MediaButtonReceiver.class), null);
        this.e.setCallback((MediaSessionCompat.Callback)new MediaSessionCallback());
        this.e.setFlags(3);
        Context context = this.getApplicationContext();
        context = PendingIntent.getActivity((Context)context, (int)99, (Intent)new Intent(context, MasterActivity_.class), (int)134217728);
        this.e.setSessionActivity((PendingIntent)context);
        context = new Bundle();
        this.e.setExtras((Bundle)context);
        this.b(null);
        this.h = new MediaNotificationController(this);
        this.b();
    }

    public void onDestroy() {
        Log.b(d, "onDestroy");
        this.a((String)null);
        this.j.a();
        this.e.release();
        b = null;
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        Log.b(d, "onStartCommand");
        MediaButtonReceiver.handleIntent((MediaSessionCompat)this.e, (Intent)intent);
        if (intent != null) {
            Object object = intent.getAction();
            String string2 = intent.getStringExtra("CMD_NAME");
            if ("com.smule.ACTION_CMD".equals(object)) {
                if ("CMD_UPDATE_SESSION_TOKEN".equals(string2)) {
                    this.b();
                    return 2;
                }
                if ("CMD_PLAY_QUEUE".equals(string2)) {
                    object = intent.getParcelableArrayListExtra("com.smule.INTENT_EXTRA_MEDIA_QUEUE");
                    boolean bl = intent.getBooleanExtra("com.smule.INTENT_EXTRA_PLAY_WHEN_READY", true);
                    this.i = true;
                    this.a((List<QueueItem>)object, bl);
                    return 1;
                }
                if ("CMD_STOP_SERVICE".equals(string2)) {
                    this.o();
                }
            }
        }
        this.j.b();
        return 1;
    }

    private static class DelayedStopHandler
    extends Handler {
        private final WeakReference<MediaPlayerService> a;

        private DelayedStopHandler(MediaPlayerService mediaPlayerService) {
            this.a = new WeakReference<MediaPlayerService>(mediaPlayerService);
        }

        public void a() {
            this.removeCallbacksAndMessages((Object)null);
        }

        public void b() {
            this.removeCallbacksAndMessages((Object)null);
            this.sendEmptyMessageDelayed(0, 300000);
        }

        public void handleMessage(Message object) {
            block3 : {
                block2 : {
                    object = this.a.get();
                    if (object == null || ((MediaPlayerService)object).k == null) break block2;
                    if (!object.j() && !((MediaPlayerService)object).k.d()) break block3;
                    Log.b(d, "Ignoring delayed stop since the media player is in use.");
                }
                return;
            }
            Log.b(d, "Stopping service with delay handler.");
            object.stopSelf();
            ((MediaPlayerService)object).i = false;
        }
    }

    private final class MediaSessionCallback
    extends MediaSessionCompat.Callback {
        private MediaSessionCallback() {
        }

        public void onCustomAction(@NonNull String string2, Bundle bundle) {
            Log.b(d, "onCustomAction: " + string2);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean onMediaButtonEvent(Intent intent) {
            Log.b(d, "onMediaButtonEvent: " + (Object)intent);
            KeyEvent keyEvent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent == null || keyEvent.getAction() != 0 || keyEvent.getRepeatCount() != 0) return super.onMediaButtonEvent(intent);
            switch (keyEvent.getKeyCode()) {
                default: {
                    return super.onMediaButtonEvent(intent);
                }
                case 79: 
                case 85: {
                    if (MediaPlayerService.this.k != null && MediaPlayerService.this.k.d()) {
                        MediaPlayerService.this.n();
                        do {
                            return true;
                            break;
                        } while (true);
                    }
                    MediaPlayerService.this.m();
                    return true;
                }
                case 126: {
                    MediaPlayerService.this.m();
                    return true;
                }
                case 127: {
                    MediaPlayerService.this.n();
                    return true;
                }
                case 86: 
            }
            MediaPlayerService.this.a(null);
            return true;
        }

        public void onPause() {
            Log.b(d, "pause. current state=" + MediaPlayerService.this.l());
            MediaPlayerService.this.n();
        }

        public void onPlay() {
            Log.b(d, "play");
            MediaPlayerService.this.m();
        }

        public void onSeekTo(long l) {
            Log.b(d, "onSeekTo:" + l);
            if (MediaPlayerService.this.k != null) {
                MediaPlayerService.this.k.a((long)((int)l));
            }
        }

        public void onSkipToNext() {
            Log.b(d, "skipToNext");
            MediaPlayerService.e(MediaPlayerService.this);
            if (MediaPlayerService.this.f != null && MediaPlayerService.this.g >= MediaPlayerService.this.f.size()) {
                MediaPlayerService.this.g = 0;
            }
            MediaPlayerService.this.m();
        }

        public void onSkipToPrevious() {
            Log.b(d, "skipToPrevious");
            MediaPlayerService.h(MediaPlayerService.this);
            if (MediaPlayerService.this.f != null && MediaPlayerService.this.g < 0) {
                MediaPlayerService.this.g = 0;
            }
            MediaPlayerService.this.m();
        }

        public void onStop() {
            Log.b(d, "stop. current state=" + MediaPlayerService.this.l());
            MediaPlayerService.this.a(null);
        }
    }

}

