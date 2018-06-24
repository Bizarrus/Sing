package com.smule.singandroid.media_player_service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaControllerCompat.Callback;
import android.support.v4.media.session.MediaControllerCompat.TransportControls;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import com.smule.android.logging.Log;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ThreadUtils;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.utils.MediaPlaybackUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MediaPlayerServiceController {
    public static final String f23430a = (f23432j + ".TOGGLED_TO_PAUSE");
    public static final String f23431b = (f23432j + ".TOGGLED_TO_PLAY");
    private static String f23432j = MediaPlayerServiceController.class.getName();
    private static MediaPlayerServiceController f23433q;
    WeakReference<Window> f23434c = null;
    WeakReference<View> f23435d = null;
    WeakReference<View> f23436e = null;
    BroadcastReceiver f23437f = new C47221(this);
    Callback f23438g = new C47232(this);
    MediaPlayerObserver f23439h = new C47254(this);
    MediaPlayerObserver f23440i = new C47265(this);
    private Context f23441k;
    private MediaControllerCompat f23442l;
    private TransportControls f23443m;
    private List<MediaPlayerObserverInterface> f23444n = new ArrayList();
    private String f23445o;
    private boolean f23446p;

    public interface MediaPlayerObserverInterface {
        void mo6476a(MediaPlayerServiceController mediaPlayerServiceController, String str);

        void mo6477b(MediaPlayerServiceController mediaPlayerServiceController, String str);

        void mo6478c(MediaPlayerServiceController mediaPlayerServiceController, String str);

        void mo6479d(MediaPlayerServiceController mediaPlayerServiceController, String str);

        void mo6480e(MediaPlayerServiceController mediaPlayerServiceController, String str);

        void mo6481f(MediaPlayerServiceController mediaPlayerServiceController, String str);

        void mo6482g(MediaPlayerServiceController mediaPlayerServiceController, String str);

        void mo6483h(MediaPlayerServiceController mediaPlayerServiceController, String str);
    }

    public static class MediaPlayerObserver implements MediaPlayerObserverInterface {
        public void mo6478c(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6477b(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6476a(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6479d(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6480e(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6483h(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6481f(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6482g(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }
    }

    class C47221 extends BroadcastReceiver {
        final /* synthetic */ MediaPlayerServiceController f23422a;

        C47221(MediaPlayerServiceController mediaPlayerServiceController) {
            this.f23422a = mediaPlayerServiceController;
        }

        public void onReceive(Context context, Intent intent) {
            this.f23422a.m24651a((Token) intent.getParcelableExtra("com.smule.EXRTA_SESSION_TOKEN"));
        }
    }

    class C47232 extends Callback {
        boolean f23423a = false;
        final /* synthetic */ MediaPlayerServiceController f23424b;

        C47232(MediaPlayerServiceController mediaPlayerServiceController) {
            this.f23424b = mediaPlayerServiceController;
        }

        public void onSessionEvent(String str, Bundle bundle) {
            super.onSessionEvent(str, bundle);
        }

        public void onPlaybackStateChanged(@NonNull PlaybackStateCompat playbackStateCompat) {
            Log.b(MediaPlayerServiceController.f23432j, "Received new playback state: " + MediaPlaybackUtils.m25872a(playbackStateCompat.getState()));
            String i = this.f23424b.m24673i();
            switch (playbackStateCompat.getState()) {
                case 0:
                    this.f23424b.m24668f(i);
                    return;
                case 1:
                    this.f23423a = false;
                    if (this.f23424b.m24673i() != null) {
                        this.f23424b.m24668f(i);
                        this.f23424b.m24677k(i);
                        return;
                    }
                    return;
                case 2:
                    if (!(this.f23423a || this.f23424b.f23446p)) {
                        this.f23423a = true;
                        this.f23424b.m24672h(i);
                        Log.b(MediaPlayerServiceController.f23432j, "Cleared in STATE_PAUSED");
                        this.f23424b.f23445o = null;
                        this.f23424b.f23446p = false;
                    }
                    this.f23424b.m24668f(i);
                    return;
                case 3:
                    if (!this.f23423a) {
                        this.f23423a = true;
                        this.f23424b.m24672h(i);
                        Log.b(MediaPlayerServiceController.f23432j, "Cleared in STATE_PLAYING");
                        this.f23424b.f23445o = null;
                        this.f23424b.f23446p = false;
                    }
                    this.f23424b.m24666e(i);
                    return;
                case 6:
                    this.f23424b.m24675j(i);
                    return;
                case 7:
                    this.f23423a = false;
                    i = this.f23424b.f23445o;
                    Log.b(MediaPlayerServiceController.f23432j, "Cleared in STATE_ERROR");
                    this.f23424b.f23445o = null;
                    this.f23424b.f23446p = false;
                    this.f23424b.m24674i(i);
                    return;
                case 8:
                    this.f23424b.m24670g(this.f23424b.f23445o);
                    return;
                default:
                    return;
            }
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            Log.b(MediaPlayerServiceController.f23432j, "Received new metadata: " + mediaMetadataCompat);
            this.f23424b.m24679l(this.f23424b.m24673i());
        }
    }

    class C47254 extends MediaPlayerObserver {
        final /* synthetic */ MediaPlayerServiceController f23428a;

        C47254(MediaPlayerServiceController mediaPlayerServiceController) {
            this.f23428a = mediaPlayerServiceController;
        }

        public void mo6480e(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23428a.m24688s();
        }

        public void mo6479d(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23428a.m24688s();
        }

        public void mo6476a(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23428a.m24688s();
        }

        public void mo6477b(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23428a.m24688s();
        }

        public void mo6483h(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23428a.m24689t();
        }
    }

    class C47265 extends MediaPlayerObserver {
        final /* synthetic */ MediaPlayerServiceController f23429a;

        C47265(MediaPlayerServiceController mediaPlayerServiceController) {
            this.f23429a = mediaPlayerServiceController;
        }

        public void mo6478c(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23429a.m24656a(true);
        }

        public void mo6477b(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23429a.m24656a(false);
        }

        public void mo6476a(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23429a.m24656a(true);
        }

        public void mo6483h(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23429a.m24656a(true);
        }

        public void mo6481f(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f23429a.m24656a(false);
        }
    }

    public static MediaPlayerServiceController m24641a() {
        if (f23433q == null) {
            f23433q = new MediaPlayerServiceController(SingApplication.f());
        }
        return f23433q;
    }

    private MediaPlayerServiceController(Context context) {
        this.f23441k = context.getApplicationContext();
        this.f23441k.registerReceiver(this.f23437f, new IntentFilter("com.smule.BROADCAST_ACTION_SESSION_TOKEN_UPDATED"));
        m24652a(this.f23439h);
        m24652a(this.f23440i);
    }

    public void m24653a(@NonNull QueueItem queueItem, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(queueItem);
        this.f23445o = queueItem.m24731b();
        this.f23446p = z;
        Log.b(f23432j, "Set in playMediaQueue: " + this.f23445o);
        m24655a(arrayList, z);
    }

    public void m24655a(@NonNull ArrayList<QueueItem> arrayList, boolean z) {
        Intent m = m24681m("CMD_PLAY_QUEUE");
        m.putParcelableArrayListExtra("com.smule.INTENT_EXTRA_MEDIA_QUEUE", arrayList);
        m.putExtra("com.smule.INTENT_EXTRA_PLAY_WHEN_READY", z);
        this.f23441k.startService(m);
    }

    public void m24657b() {
        if (this.f23443m == null) {
            Log.e(f23432j, "play: mMediaControls was null");
        } else {
            this.f23443m.play();
        }
    }

    public void m24661c() {
        if (this.f23443m == null) {
            Log.e(f23432j, "pause: mMediaControls was null");
        } else {
            this.f23443m.pause();
        }
    }

    public void m24663d() {
        if (this.f23445o == null) {
            if (m24676j()) {
                Log.b(f23432j, "togglePlayState - is playing, so pausing");
                m24661c();
                NotificationCenter.m19011a().m19017b(f23430a, new Object[0]);
            } else if (m24678k()) {
                Log.b(f23432j, "togglePlayState - is not playing, so playing");
                m24657b();
                NotificationCenter.m19011a().m19017b(f23431b, new Object[0]);
            }
        }
    }

    public void m24665e() {
        if (this.f23443m == null) {
            Log.e(f23432j, "stop: mMediaControls was null");
            return;
        }
        this.f23443m.stop();
        m24687r();
        this.f23445o = null;
        this.f23446p = false;
    }

    public void m24648a(int i) {
        if (this.f23443m == null) {
            Log.e(f23432j, "skipTo: mMediaControls was null");
        } else {
            this.f23443m.seekTo((long) i);
        }
    }

    public long m24667f() {
        long d = MediaPlayerService.m24585a().m24624d();
        if (d == -1) {
            return 0;
        }
        return d;
    }

    public long m24669g() {
        if (MediaPlayerService.m24585a() != null) {
            return MediaPlayerService.m24585a().m24626f();
        }
        Log.e(f23432j, "getCurrentPosition called with invalid service instance");
        return 0;
    }

    public void m24654a(String str) {
        if (this.f23445o == null || !this.f23445o.equals(str)) {
            m24665e();
            this.f23445o = str;
            Log.b(f23432j, "Set in setLoadingKey: " + str);
            m24670g(str);
        }
    }

    public String m24671h() {
        return this.f23445o;
    }

    public void m24660b(String str) {
        if (this.f23445o != null && this.f23445o.equals(str)) {
            this.f23445o = null;
            this.f23446p = false;
            Log.b(f23432j, "Cleared in notifyLoadFailed");
            m24674i(str);
        }
    }

    public String m24673i() {
        if (this.f23442l == null || this.f23442l.getMetadata() == null) {
            return null;
        }
        switch (this.f23442l.getPlaybackState().getState()) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return this.f23442l.getMetadata().getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
            default:
                return null;
        }
    }

    public boolean m24662c(String str) {
        return str != null && ((m24680l() && (str.equals(m24673i()) || (this.f23445o != null && this.f23445o.equals(str)))) || (!m24680l() && str.equals(m24673i())));
    }

    public boolean m24664d(String str) {
        return str != null && m24647v() == 6 && str.equals(m24673i());
    }

    public boolean m24676j() {
        return m24647v() == 3;
    }

    public boolean m24678k() {
        return m24647v() == 2;
    }

    public boolean m24680l() {
        boolean z;
        int v = m24647v();
        if (v == 6 || v == 8) {
            z = true;
        } else {
            z = false;
        }
        if (z && m24682m()) {
            return true;
        }
        return false;
    }

    public boolean m24682m() {
        return (m24673i() == null && this.f23445o == null) ? false : true;
    }

    public boolean m24683n() {
        return m24647v() == 6;
    }

    private int m24647v() {
        if (this.f23442l == null) {
            return 0;
        }
        PlaybackStateCompat playbackState = this.f23442l.getPlaybackState();
        if (playbackState != null) {
            return playbackState.getState();
        }
        return 0;
    }

    public void m24684o() {
        if (MediaPlayerService.m24585a() == null) {
            Log.b(f23432j, "starting service since current instance null");
            m24686q();
        }
    }

    public void m24685p() {
        this.f23441k.startService(m24681m("CMD_STOP_SERVICE"));
    }

    public void m24649a(Activity activity) {
        if (!m24676j()) {
            m24686q();
        }
        if (MediaPlayerService.m24585a() != null) {
            MediaPlayerService.m24585a().m24628h();
        }
    }

    public void m24658b(Activity activity) {
        if (MediaPlayerService.m24585a() != null) {
            MediaPlayerService.m24585a().m24629i();
        }
    }

    public void m24652a(MediaPlayerObserverInterface mediaPlayerObserverInterface) {
        this.f23444n.add(mediaPlayerObserverInterface);
    }

    public void m24659b(MediaPlayerObserverInterface mediaPlayerObserverInterface) {
        this.f23444n.remove(mediaPlayerObserverInterface);
    }

    protected void m24651a(Token token) {
        Log.b(f23432j, "updateAudioSessionToken");
        if (token == null) {
            this.f23442l = null;
            this.f23443m = null;
            return;
        }
        try {
            this.f23442l = new MediaControllerCompat(this.f23441k, token);
            this.f23442l.registerCallback(this.f23438g);
            this.f23443m = this.f23442l.getTransportControls();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void m24666e(String str) {
        Log.b(f23432j, "sendMediaPlayingNotification - called with id: " + str);
        for (MediaPlayerObserverInterface a : this.f23444n) {
            a.mo6476a(this, str);
        }
    }

    protected void m24668f(String str) {
        Log.b(f23432j, "sendMediaPausedNotification - called with id: " + str);
        for (MediaPlayerObserverInterface b : this.f23444n) {
            b.mo6477b(this, str);
        }
    }

    protected void m24670g(String str) {
        Log.b(f23432j, "sendMediaLoadingNotification - called with id: " + str);
        for (MediaPlayerObserverInterface c : this.f23444n) {
            c.mo6478c(this, str);
        }
    }

    protected void m24672h(String str) {
        Log.b(f23432j, "sendMediaLoadedNotification - called with id: " + str);
        if (str != null) {
            for (MediaPlayerObserverInterface d : this.f23444n) {
                d.mo6479d(this, str);
            }
        }
    }

    protected void m24674i(String str) {
        Log.b(f23432j, "sendMediaLoadFailedNotification - called with id: " + str);
        for (MediaPlayerObserverInterface e : this.f23444n) {
            e.mo6480e(this, str);
        }
    }

    protected void m24675j(String str) {
        Log.b(f23432j, "sendMediaBufferingNotification - called with id: " + str);
        for (MediaPlayerObserverInterface h : this.f23444n) {
            h.mo6483h(this, str);
        }
    }

    protected void m24677k(String str) {
        Log.b(f23432j, "sendMediaCompletedNotification - called with id: " + str);
        for (MediaPlayerObserverInterface f : this.f23444n) {
            f.mo6481f(this, str);
        }
    }

    protected void m24679l(String str) {
        Log.b(f23432j, "sendMediaMetadataChangedNotification - called with id: " + str);
        for (MediaPlayerObserverInterface g : this.f23444n) {
            g.mo6482g(this, str);
        }
    }

    protected void m24686q() {
        this.f23441k.startService(m24681m("CMD_UPDATE_SESSION_TOKEN"));
    }

    protected Intent m24681m(String str) {
        Intent intent = new Intent(this.f23441k.getApplicationContext(), MediaPlayerService.class);
        intent.setAction("com.smule.ACTION_CMD");
        intent.putExtra("CMD_NAME", str);
        return intent;
    }

    public void m24650a(Activity activity, TextureView textureView, View view, View view2) {
        Log.b(f23432j, "setVideoView");
        MediaPlayerService a = MediaPlayerService.m24585a();
        if (!(this.f23434c == null || this.f23434c.get() == activity.getWindow())) {
            m24656a(false);
        }
        this.f23434c = new WeakReference(activity.getWindow());
        this.f23435d = new WeakReference(view);
        this.f23436e = new WeakReference(view2);
        if (a != null) {
            a.m24617a(textureView);
        }
        if (!(view2 == null || !m24676j() || m24683n())) {
            m24688s();
        }
        m24656a(true);
    }

    public void m24687r() {
        MediaPlayerService a = MediaPlayerService.m24585a();
        if (a != null) {
            a.m24617a(null);
        }
        m24656a(false);
        this.f23434c = null;
        this.f23435d = null;
        this.f23436e = null;
    }

    protected void m24656a(final boolean z) {
        final Window window = this.f23434c != null ? (Window) this.f23434c.get() : null;
        Log.b(f23432j, "stayAwake: " + z + " hasVideo: " + (window != null));
        if (window != null) {
            ThreadUtils.m19058a(new Runnable(this) {
                final /* synthetic */ MediaPlayerServiceController f23427c;

                public void run() {
                    if (z && this.f23427c.m24676j()) {
                        Log.b(MediaPlayerServiceController.f23432j, "stayAwake: addFlags(FLAG_KEEP_SCREEN_ON)");
                        window.addFlags(128);
                        return;
                    }
                    Log.b(MediaPlayerServiceController.f23432j, "stayAwake: clearFlags(FLAG_KEEP_SCREEN_ON)");
                    window.clearFlags(128);
                }
            });
        }
    }

    protected void m24688s() {
        View view;
        View view2 = this.f23435d != null ? (View) this.f23435d.get() : null;
        if (this.f23436e != null) {
            view = (View) this.f23436e.get();
        } else {
            view = null;
        }
        if (view2 != null) {
            view2.setVisibility(8);
        }
        if (view != null) {
            view.setVisibility(8);
        }
    }

    protected void m24689t() {
        View view = this.f23436e != null ? (View) this.f23436e.get() : null;
        if (view != null) {
            view.setVisibility(0);
        }
    }
}
