/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.os.RemoteException
 *  android.support.annotation.NonNull
 *  android.support.v4.media.MediaMetadataCompat
 *  android.support.v4.media.session.MediaControllerCompat
 *  android.support.v4.media.session.MediaControllerCompat$Callback
 *  android.support.v4.media.session.MediaControllerCompat$TransportControls
 *  android.support.v4.media.session.MediaSessionCompat
 *  android.support.v4.media.session.MediaSessionCompat$Token
 *  android.support.v4.media.session.PlaybackStateCompat
 *  android.view.TextureView
 *  android.view.View
 *  android.view.Window
 *  com.smule.singandroid.utils.MediaPlaybackUtils
 */
package com.smule.singandroid.media_player_service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import com.smule.android.logging.Log;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ThreadUtils;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.media_player_service.MediaPlayerService;
import com.smule.singandroid.media_player_service.QueueItem;
import com.smule.singandroid.utils.MediaPlaybackUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaPlayerServiceController {
    public static final String a;
    public static final String b;
    private static String j;
    private static MediaPlayerServiceController q;
    WeakReference<Window> c = null;
    WeakReference<View> d = null;
    WeakReference<View> e = null;
    BroadcastReceiver f;
    MediaControllerCompat.Callback g;
    MediaPlayerObserver h;
    MediaPlayerObserver i;
    private Context k;
    private MediaControllerCompat l;
    private MediaControllerCompat.TransportControls m;
    private List<MediaPlayerObserverInterface> n = new ArrayList<MediaPlayerObserverInterface>();
    private String o;
    private boolean p;

    static {
        j = MediaPlayerServiceController.class.getName();
        a = j + ".TOGGLED_TO_PAUSE";
        b = j + ".TOGGLED_TO_PLAY";
    }

    private MediaPlayerServiceController(Context context) {
        this.f = new BroadcastReceiver(){

            public void onReceive(Context context, Intent intent) {
                context = (MediaSessionCompat.Token)intent.getParcelableExtra("com.smule.EXRTA_SESSION_TOKEN");
                MediaPlayerServiceController.this.a((MediaSessionCompat.Token)context);
            }
        };
        this.g = new MediaControllerCompat.Callback(){
            boolean a;
            {
                this.a = false;
            }

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
                Log.b(j, "Received new metadata: " + (Object)mediaMetadataCompat);
                MediaPlayerServiceController.this.l(MediaPlayerServiceController.this.i());
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onPlaybackStateChanged(@NonNull PlaybackStateCompat object) {
                Log.b(j, "Received new playback state: " + MediaPlaybackUtils.a((int)object.getState()));
                String string2 = MediaPlayerServiceController.this.i();
                switch (object.getState()) {
                    case 3: {
                        if (!this.a) {
                            this.a = true;
                            MediaPlayerServiceController.this.h(string2);
                            Log.b(j, "Cleared in STATE_PLAYING");
                            MediaPlayerServiceController.this.o = null;
                            MediaPlayerServiceController.this.p = false;
                        }
                        MediaPlayerServiceController.this.e(string2);
                        return;
                    }
                    case 2: {
                        if (!this.a && !MediaPlayerServiceController.this.p) {
                            this.a = true;
                            MediaPlayerServiceController.this.h(string2);
                            Log.b(j, "Cleared in STATE_PAUSED");
                            MediaPlayerServiceController.this.o = null;
                            MediaPlayerServiceController.this.p = false;
                        }
                        MediaPlayerServiceController.this.f(string2);
                        return;
                    }
                    case 0: {
                        MediaPlayerServiceController.this.f(string2);
                        return;
                    }
                    case 8: {
                        MediaPlayerServiceController.this.g(MediaPlayerServiceController.this.o);
                        return;
                    }
                    case 6: {
                        MediaPlayerServiceController.this.j(string2);
                        return;
                    }
                    case 1: {
                        this.a = false;
                        if (MediaPlayerServiceController.this.i() != null) {
                            MediaPlayerServiceController.this.f(string2);
                            MediaPlayerServiceController.this.k(string2);
                            return;
                        }
                    }
                    default: {
                        return;
                    }
                    case 7: 
                }
                this.a = false;
                object = MediaPlayerServiceController.this.o;
                Log.b(j, "Cleared in STATE_ERROR");
                MediaPlayerServiceController.this.o = null;
                MediaPlayerServiceController.this.p = false;
                MediaPlayerServiceController.this.i((String)object);
            }

            public void onSessionEvent(String string2, Bundle bundle) {
                super.onSessionEvent(string2, bundle);
            }
        };
        this.h = new MediaPlayerObserver(){

            @Override
            public void a(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.s();
            }

            @Override
            public void b(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.s();
            }

            @Override
            public void c(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.s();
            }

            @Override
            public void d(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.s();
            }

            @Override
            public void e(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.t();
            }
        };
        this.i = new MediaPlayerObserver(){

            @Override
            public void c(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.a(true);
            }

            @Override
            public void d(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.a(false);
            }

            @Override
            public void e(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.a(true);
            }

            @Override
            public void f(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.a(true);
            }

            @Override
            public void g(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayerServiceController.this.a(false);
            }
        };
        this.k = context.getApplicationContext();
        this.k.registerReceiver(this.f, new IntentFilter("com.smule.BROADCAST_ACTION_SESSION_TOKEN_UPDATED"));
        this.a(this.h);
        this.a(this.i);
    }

    public static MediaPlayerServiceController a() {
        if (q == null) {
            q = new MediaPlayerServiceController(SingApplication.g());
        }
        return q;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int v() {
        PlaybackStateCompat playbackStateCompat;
        if (this.l == null || (playbackStateCompat = this.l.getPlaybackState()) == null) {
            return 0;
        }
        return playbackStateCompat.getState();
    }

    public void a(int n) {
        if (this.m == null) {
            Log.e(j, "skipTo: mMediaControls was null");
            return;
        }
        this.m.seekTo((long)n);
    }

    public void a(Activity activity) {
        if (!this.j()) {
            this.q();
        }
        if (MediaPlayerService.a() != null) {
            MediaPlayerService.a().h();
        }
    }

    public void a(Activity activity, TextureView textureView, View view, View view2) {
        Log.b(j, "setVideoView");
        MediaPlayerService mediaPlayerService = MediaPlayerService.a();
        if (this.c != null && this.c.get() != activity.getWindow()) {
            this.a(false);
        }
        this.c = new WeakReference<Window>(activity.getWindow());
        this.d = new WeakReference<View>(view);
        this.e = new WeakReference<View>(view2);
        if (mediaPlayerService != null) {
            mediaPlayerService.a(textureView);
        }
        if (view2 != null && this.j() && !this.n()) {
            this.s();
        }
        this.a(true);
    }

    protected void a(MediaSessionCompat.Token token) {
        Log.b(j, "updateAudioSessionToken");
        if (token == null) {
            this.l = null;
            this.m = null;
            return;
        }
        try {
            this.l = new MediaControllerCompat(this.k, token);
            this.l.registerCallback(this.g);
            this.m = this.l.getTransportControls();
            return;
        }
        catch (RemoteException remoteException) {
            remoteException.printStackTrace();
            return;
        }
    }

    public void a(MediaPlayerObserverInterface mediaPlayerObserverInterface) {
        this.n.add(mediaPlayerObserverInterface);
    }

    public void a(@NonNull QueueItem queueItem, boolean bl) {
        ArrayList<QueueItem> arrayList = new ArrayList<QueueItem>();
        arrayList.add(queueItem);
        this.o = queueItem.b();
        this.p = bl;
        Log.b(j, "Set in playMediaQueue: " + this.o);
        this.a(arrayList, bl);
    }

    public void a(String string2) {
        if (this.o == null || !this.o.equals(string2)) {
            this.e();
            this.o = string2;
            Log.b(j, "Set in setLoadingKey: " + string2);
            this.g(string2);
        }
    }

    public void a(@NonNull ArrayList<QueueItem> arrayList, boolean bl) {
        Intent intent = this.m("CMD_PLAY_QUEUE");
        intent.putParcelableArrayListExtra("com.smule.INTENT_EXTRA_MEDIA_QUEUE", arrayList);
        intent.putExtra("com.smule.INTENT_EXTRA_PLAY_WHEN_READY", bl);
        this.k.startService(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(final boolean bl) {
        final Window window = this.c != null ? this.c.get() : null;
        String string2 = j;
        StringBuilder stringBuilder = new StringBuilder().append("stayAwake: ").append(bl).append(" hasVideo: ");
        boolean bl2 = window != null;
        Log.b(string2, stringBuilder.append(bl2).toString());
        if (window != null) {
            ThreadUtils.a(new Runnable(){

                @Override
                public void run() {
                    if (bl && MediaPlayerServiceController.this.j()) {
                        Log.b(j, "stayAwake: addFlags(FLAG_KEEP_SCREEN_ON)");
                        window.addFlags(128);
                        return;
                    }
                    Log.b(j, "stayAwake: clearFlags(FLAG_KEEP_SCREEN_ON)");
                    window.clearFlags(128);
                }
            });
        }
    }

    public void b() {
        if (this.m == null) {
            Log.e(j, "play: mMediaControls was null");
            return;
        }
        this.m.play();
    }

    public void b(Activity activity) {
        if (MediaPlayerService.a() != null) {
            MediaPlayerService.a().i();
        }
    }

    public void b(MediaPlayerObserverInterface mediaPlayerObserverInterface) {
        this.n.remove(mediaPlayerObserverInterface);
    }

    public void b(String string2) {
        if (this.o != null && this.o.equals(string2)) {
            this.o = null;
            this.p = false;
            Log.b(j, "Cleared in notifyLoadFailed");
            this.i(string2);
        }
    }

    public void c() {
        if (this.m == null) {
            Log.e(j, "pause: mMediaControls was null");
            return;
        }
        this.m.pause();
    }

    public boolean c(String string2) {
        if (string2 != null && (this.l() && (string2.equals(this.i()) || this.o != null && this.o.equals(string2)) || !this.l() && string2.equals(this.i()))) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void d() {
        block5 : {
            block4 : {
                if (this.o != null) break block4;
                if (this.j()) {
                    Log.b(j, "togglePlayState - is playing, so pausing");
                    this.c();
                    NotificationCenter.a().b(a, new Object[0]);
                    return;
                }
                if (this.k()) break block5;
            }
            return;
        }
        Log.b(j, "togglePlayState - is not playing, so playing");
        this.b();
        NotificationCenter.a().b(b, new Object[0]);
    }

    public boolean d(String string2) {
        if (string2 != null && this.v() == 6 && string2.equals(this.i())) {
            return true;
        }
        return false;
    }

    public void e() {
        if (this.m == null) {
            Log.e(j, "stop: mMediaControls was null");
            return;
        }
        this.m.stop();
        this.r();
        this.o = null;
        this.p = false;
    }

    protected void e(String string2) {
        Log.b(j, "sendMediaPlayingNotification - called with id: " + string2);
        Iterator<MediaPlayerObserverInterface> iterator = this.n.iterator();
        while (iterator.hasNext()) {
            iterator.next().c(this, string2);
        }
    }

    public long f() {
        long l;
        long l2 = l = MediaPlayerService.a().d();
        if (l == -1) {
            l2 = 0;
        }
        return l2;
    }

    protected void f(String string2) {
        Log.b(j, "sendMediaPausedNotification - called with id: " + string2);
        Iterator<MediaPlayerObserverInterface> iterator = this.n.iterator();
        while (iterator.hasNext()) {
            iterator.next().d(this, string2);
        }
    }

    public long g() {
        if (MediaPlayerService.a() != null) {
            return MediaPlayerService.a().f();
        }
        Log.e(j, "getCurrentPosition called with invalid service instance");
        return 0;
    }

    protected void g(String string2) {
        Log.b(j, "sendMediaLoadingNotification - called with id: " + string2);
        Iterator<MediaPlayerObserverInterface> iterator = this.n.iterator();
        while (iterator.hasNext()) {
            iterator.next().f(this, string2);
        }
    }

    public String h() {
        return this.o;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void h(String string2) {
        Log.b(j, "sendMediaLoadedNotification - called with id: " + string2);
        if (string2 != null) {
            Iterator<MediaPlayerObserverInterface> iterator = this.n.iterator();
            while (iterator.hasNext()) {
                iterator.next().b(this, string2);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String i() {
        if (this.l == null || this.l.getMetadata() == null) return null;
        switch (this.l.getPlaybackState().getState()) {
            default: {
                return null;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 8: 
        }
        return this.l.getMetadata().getString("android.media.metadata.MEDIA_ID");
    }

    protected void i(String string2) {
        Log.b(j, "sendMediaLoadFailedNotification - called with id: " + string2);
        Iterator<MediaPlayerObserverInterface> iterator = this.n.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(this, string2);
        }
    }

    protected void j(String string2) {
        Log.b(j, "sendMediaBufferingNotification - called with id: " + string2);
        Iterator<MediaPlayerObserverInterface> iterator = this.n.iterator();
        while (iterator.hasNext()) {
            iterator.next().e(this, string2);
        }
    }

    public boolean j() {
        if (this.v() == 3) {
            return true;
        }
        return false;
    }

    protected void k(String string2) {
        Log.b(j, "sendMediaCompletedNotification - called with id: " + string2);
        Iterator<MediaPlayerObserverInterface> iterator = this.n.iterator();
        while (iterator.hasNext()) {
            iterator.next().g(this, string2);
        }
    }

    public boolean k() {
        if (this.v() == 2) {
            return true;
        }
        return false;
    }

    protected void l(String string2) {
        Log.b(j, "sendMediaMetadataChangedNotification - called with id: " + string2);
        Iterator<MediaPlayerObserverInterface> iterator = this.n.iterator();
        while (iterator.hasNext()) {
            iterator.next().h(this, string2);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean l() {
        int n = this.v();
        if (n != 6 && n != 8) return false;
        n = 1;
        if (n == 0) return false;
        if (!this.m()) return false;
        return true;
    }

    protected Intent m(String string2) {
        Intent intent = new Intent(this.k.getApplicationContext(), MediaPlayerService.class);
        intent.setAction("com.smule.ACTION_CMD");
        intent.putExtra("CMD_NAME", string2);
        return intent;
    }

    public boolean m() {
        if (this.i() != null || this.o != null) {
            return true;
        }
        return false;
    }

    public boolean n() {
        if (this.v() == 6) {
            return true;
        }
        return false;
    }

    public void o() {
        if (MediaPlayerService.a() == null) {
            Log.b(j, "starting service since current instance null");
            this.q();
        }
    }

    public void p() {
        Intent intent = this.m("CMD_STOP_SERVICE");
        this.k.startService(intent);
    }

    protected void q() {
        Intent intent = this.m("CMD_UPDATE_SESSION_TOKEN");
        this.k.startService(intent);
    }

    public void r() {
        MediaPlayerService mediaPlayerService = MediaPlayerService.a();
        if (mediaPlayerService != null) {
            mediaPlayerService.a((TextureView)null);
        }
        this.a(false);
        this.c = null;
        this.d = null;
        this.e = null;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void s() {
        View view = this.d != null ? this.d.get() : null;
        if (this.e == null) return;
        View view2 = this.e.get();
        if (view == null) return;
        view.setVisibility(8);
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void t() {
        if (this.e == null) return;
        View view = this.e.get();
        if (view == null) return;
        view.setVisibility(0);
    }

    public static class MediaPlayerObserver
    implements MediaPlayerObserverInterface {
        @Override
        public void a(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        }

        @Override
        public void b(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        }

        @Override
        public void c(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        }

        @Override
        public void d(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        }

        @Override
        public void e(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        }

        @Override
        public void f(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        }

        @Override
        public void g(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        }

        @Override
        public void h(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        }
    }

    public static interface MediaPlayerObserverInterface {
        public void a(MediaPlayerServiceController var1, String var2);

        public void b(MediaPlayerServiceController var1, String var2);

        public void c(MediaPlayerServiceController var1, String var2);

        public void d(MediaPlayerServiceController var1, String var2);

        public void e(MediaPlayerServiceController var1, String var2);

        public void f(MediaPlayerServiceController var1, String var2);

        public void g(MediaPlayerServiceController var1, String var2);

        public void h(MediaPlayerServiceController var1, String var2);
    }

}

