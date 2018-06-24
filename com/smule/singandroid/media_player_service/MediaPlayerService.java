package com.smule.singandroid.media_player_service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.TransportMediator;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import com.mopub.volley.DefaultRetryPolicy;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.samsung.android.sdk.professionalaudio.SapaService.Parameters;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.media_player_service.Playback.Callback;
import com.smule.singandroid.media_player_service.QueueItem.DownloadResourcesListener;
import com.smule.singandroid.utils.MediaPlaybackUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.video.ExoPlayerPlaybackWrapper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MediaPlayerService extends Service implements OnAudioFocusChangeListener, Callback {
    static MediaPlayerService f23402b;
    private static final String f23403d = MediaPlayerService.class.getName();
    final String f23404a = "MediaPlayerService_Session";
    WeakReference<TextureView> f23405c = null;
    private MediaSessionCompat f23406e;
    private List<QueueItem> f23407f = new ArrayList();
    private int f23408g;
    private MediaNotificationController f23409h;
    private boolean f23410i;
    private final DelayedStopHandler f23411j = new DelayedStopHandler();
    private Playback f23412k;
    private boolean f23413l = false;
    private AudioManager f23414m;
    private int f23415n;
    private WifiLock f23416o;
    private boolean f23417p = true;
    private boolean f23418q = false;
    private volatile boolean f23419r;
    private final IntentFilter f23420s = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
    private final BroadcastReceiver f23421t = new C47213(this);

    class C47213 extends BroadcastReceiver {
        final /* synthetic */ MediaPlayerService f23399a;

        C47213(MediaPlayerService mediaPlayerService) {
            this.f23399a = mediaPlayerService;
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                Log.b(MediaPlayerService.f23403d, "Headphones disconnected.");
                if (this.f23399a.f23412k != null && this.f23399a.f23412k.mo6977d()) {
                    Intent intent2 = new Intent(context, MediaPlayerService.class);
                    intent2.setAction("com.smule.ACTION_CMD");
                    intent2.putExtra("CMD_NAME", "CMD_PAUSE");
                    this.f23399a.startService(intent2);
                }
            }
        }
    }

    private static class DelayedStopHandler extends Handler {
        private final WeakReference<MediaPlayerService> f23400a;

        private DelayedStopHandler(MediaPlayerService mediaPlayerService) {
            this.f23400a = new WeakReference(mediaPlayerService);
        }

        public void handleMessage(Message message) {
            MediaPlayerService mediaPlayerService = (MediaPlayerService) this.f23400a.get();
            if (mediaPlayerService != null && mediaPlayerService.f23412k != null) {
                if (mediaPlayerService.m24630j() || mediaPlayerService.f23412k.mo6977d()) {
                    Log.b(MediaPlayerService.f23403d, "Ignoring delayed stop since the media player is in use.");
                    return;
                }
                Log.b(MediaPlayerService.f23403d, "Stopping service with delay handler.");
                mediaPlayerService.stopSelf();
                mediaPlayerService.f23410i = false;
            }
        }

        public void m24580a() {
            removeCallbacksAndMessages(null);
        }

        public void m24581b() {
            removeCallbacksAndMessages(null);
            sendEmptyMessageDelayed(0, 300000);
        }
    }

    private final class MediaSessionCallback extends MediaSessionCompat.Callback {
        final /* synthetic */ MediaPlayerService f23401a;

        private MediaSessionCallback(MediaPlayerService mediaPlayerService) {
            this.f23401a = mediaPlayerService;
        }

        public void onPlay() {
            Log.b(MediaPlayerService.f23403d, "play");
            this.f23401a.m24605m();
        }

        public void onSeekTo(long j) {
            Log.b(MediaPlayerService.f23403d, "onSeekTo:" + j);
            if (this.f23401a.f23412k != null) {
                this.f23401a.f23412k.mo6973a((long) ((int) j));
            }
        }

        public void onPause() {
            Log.b(MediaPlayerService.f23403d, "pause. current state=" + this.f23401a.m24604l());
            this.f23401a.m24606n();
        }

        public void onStop() {
            Log.b(MediaPlayerService.f23403d, "stop. current state=" + this.f23401a.m24604l());
            this.f23401a.m24590a(null);
        }

        public void onSkipToNext() {
            Log.b(MediaPlayerService.f23403d, "skipToNext");
            this.f23401a.f23408g = this.f23401a.f23408g + 1;
            if (this.f23401a.f23407f != null && this.f23401a.f23408g >= this.f23401a.f23407f.size()) {
                this.f23401a.f23408g = 0;
            }
            this.f23401a.m24605m();
        }

        public void onSkipToPrevious() {
            Log.b(MediaPlayerService.f23403d, "skipToPrevious");
            this.f23401a.f23408g = this.f23401a.f23408g - 1;
            if (this.f23401a.f23407f != null && this.f23401a.f23408g < 0) {
                this.f23401a.f23408g = 0;
            }
            this.f23401a.m24605m();
        }

        public void onCustomAction(@NonNull String str, Bundle bundle) {
            Log.b(MediaPlayerService.f23403d, "onCustomAction: " + str);
        }

        public boolean onMediaButtonEvent(Intent intent) {
            Log.b(MediaPlayerService.f23403d, "onMediaButtonEvent: " + intent);
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent != null && keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                switch (keyEvent.getKeyCode()) {
                    case 79:
                    case 85:
                        if (this.f23401a.f23412k == null || !this.f23401a.f23412k.mo6977d()) {
                            this.f23401a.m24605m();
                        } else {
                            this.f23401a.m24606n();
                        }
                        return true;
                    case 86:
                        this.f23401a.m24590a(null);
                        return true;
                    case TransportMediator.KEYCODE_MEDIA_PLAY /*126*/:
                        this.f23401a.m24605m();
                        return true;
                    case TransportMediator.KEYCODE_MEDIA_PAUSE /*127*/:
                        this.f23401a.m24606n();
                        return true;
                }
            }
            return super.onMediaButtonEvent(intent);
        }
    }

    public static MediaPlayerService m24585a() {
        return f23402b;
    }

    public void onCreate() {
        super.onCreate();
        Log.b(f23403d, "onCreate");
        f23402b = this;
        this.f23414m = (AudioManager) getSystemService("audio");
        this.f23416o = ((WifiManager) getSystemService("wifi")).createWifiLock(1, "sing_lock");
        this.f23406e = new MediaSessionCompat(this, "MediaPlayerService_Session", new ComponentName(this, MediaButtonReceiver.class), null);
        this.f23406e.setCallback(new MediaSessionCallback());
        this.f23406e.setFlags(3);
        Context applicationContext = getApplicationContext();
        this.f23406e.setSessionActivity(PendingIntent.getActivity(applicationContext, 99, new Intent(applicationContext, MasterActivity_.class), 134217728));
        this.f23406e.setExtras(new Bundle());
        m24594b(null);
        this.f23409h = new MediaNotificationController(this);
        m24621b();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.b(f23403d, "onStartCommand");
        MediaButtonReceiver.handleIntent(this.f23406e, intent);
        if (intent != null) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("CMD_NAME");
            if ("com.smule.ACTION_CMD".equals(action)) {
                if ("CMD_UPDATE_SESSION_TOKEN".equals(stringExtra)) {
                    m24621b();
                    return 2;
                } else if ("CMD_PLAY_QUEUE".equals(stringExtra)) {
                    List parcelableArrayListExtra = intent.getParcelableArrayListExtra("com.smule.INTENT_EXTRA_MEDIA_QUEUE");
                    boolean booleanExtra = intent.getBooleanExtra("com.smule.INTENT_EXTRA_PLAY_WHEN_READY", true);
                    this.f23410i = true;
                    m24618a(parcelableArrayListExtra, booleanExtra);
                    return 1;
                } else if ("CMD_STOP_SERVICE".equals(stringExtra)) {
                    m24607o();
                }
            }
        }
        this.f23411j.m24581b();
        return 1;
    }

    public void onDestroy() {
        Log.b(f23403d, "onDestroy");
        m24590a(null);
        this.f23411j.m24580a();
        this.f23406e.release();
        f23402b = null;
    }

    protected void m24621b() {
        Log.b(f23403d, "broadcastSessionToken");
        Intent intent = new Intent("com.smule.BROADCAST_ACTION_SESSION_TOKEN_UPDATED");
        intent.putExtra("com.smule.EXRTA_SESSION_TOKEN", m24623c());
        sendBroadcast(intent);
    }

    public Token m24623c() {
        if (this.f23406e != null) {
            return this.f23406e.getSessionToken();
        }
        return null;
    }

    protected void m24618a(@NonNull List<QueueItem> list, boolean z) {
        this.f23407f = list;
        m24595b(z);
    }

    private String m24604l() {
        return MediaPlaybackUtils.m25872a(m24609q());
    }

    private void m24605m() {
        m24595b(true);
    }

    private void m24595b(final boolean z) {
        Log.b(f23403d, "handlePlayRequest: mState=" + m24604l());
        if (this.f23408g == -1 || this.f23408g >= this.f23407f.size()) {
            Log.e(f23403d, "play queue is empty size: " + this.f23407f.size() + ". Or index is off: " + this.f23408g);
            return;
        }
        this.f23411j.m24580a();
        if (!this.f23416o.isHeld()) {
            this.f23416o.acquire();
        }
        m24614v();
        m24612t();
        if (!this.f23410i) {
            Log.a(f23403d, "Starting service");
            startService(new Intent(getApplicationContext(), MediaPlayerService.class));
            this.f23410i = true;
        }
        if (!this.f23406e.isActive()) {
            this.f23406e.setActive(true);
        }
        final QueueItem queueItem = (QueueItem) this.f23407f.get(this.f23408g);
        if (this.f23412k != null && queueItem.m24731b().equals(this.f23412k.m24713k()) && m24609q() == 2) {
            this.f23412k.m24697a(queueItem, z);
            return;
        }
        m24608p();
        m24620a(MiscUtils.m25895a(queueItem.m24725a()), queueItem.m24737g());
        queueItem.m24729a(new DownloadResourcesListener(this) {
            final /* synthetic */ MediaPlayerService f23395c;

            public void mo6887a() {
                if (this.f23395c.f23407f.get(this.f23395c.f23408g) == queueItem && this.f23395c.f23412k != null) {
                    this.f23395c.f23412k.m24697a(queueItem, z);
                }
            }

            public void mo6888b() {
                int i;
                if (queueItem.m24725a() != null) {
                    i = C1947R.string.now_playing_load_error;
                } else {
                    i = C1947R.string.songbook_download_failed_message;
                }
                this.f23395c.m24594b(this.f23395c.getApplicationContext().getResources().getString(i));
            }
        });
    }

    private void m24606n() {
        Log.b(f23403d, "handlePauseRequest: mState=" + m24604l());
        if (this.f23412k != null) {
            this.f23412k.mo6982i();
        }
        this.f23411j.m24581b();
        m24613u();
        m24615w();
    }

    private void m24590a(String str) {
        Log.b(f23403d, "handleStopRequest: mState=" + m24604l() + " error=" + str);
        if (this.f23412k != null) {
            this.f23412k.mo6975a(false);
            this.f23412k.mo6976b();
            this.f23412k = null;
        }
        if (this.f23406e.isActive()) {
            this.f23406e.setActive(false);
        }
        this.f23411j.m24581b();
        m24613u();
        m24615w();
        m24594b(str);
    }

    private void m24607o() {
        Log.b(f23403d, "handleStopServiceRequest: mState=" + m24604l());
        if (this.f23412k != null) {
            this.f23412k.mo6976b();
            this.f23412k = null;
        }
        if (this.f23416o.isHeld()) {
            this.f23416o.release();
        }
        m24613u();
        m24615w();
        if (this.f23406e.isActive()) {
            this.f23406e.setActive(false);
        }
        stopSelf();
        this.f23410i = false;
    }

    private void m24608p() {
        QueueItem s = m24611s();
        MediaMetadataCompat a = s.m24724a((Context) this);
        Log.b(f23403d, "Updating metadata for AudioID: " + s.m24731b());
        this.f23406e.setMetadata(a);
        if (a.getDescription().getIconBitmap() == null && a.getDescription().getIconUri() != null) {
            String uri = a.getDescription().getIconUri().toString();
            m24589a(s, MediaMetadataCompat.METADATA_KEY_ALBUM_ART, uri, 800, Parameters.BUFFER_SIZE_480);
            m24589a(s, MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, uri, 128, 128);
        }
    }

    private void m24589a(final QueueItem queueItem, final String str, String str2, int i, int i2) {
        ImageLoader.a().a(str2, new ImageSize(i, i2), new Builder().m17114a(true).m17116b(true).m17115a(), new ImageLoadingListener(this) {
            final /* synthetic */ MediaPlayerService f23398c;

            public void mo6154a(String str, View view) {
            }

            public void mo6156a(String str, View view, FailReason failReason) {
            }

            public void mo6155a(String str, View view, Bitmap bitmap) {
                MediaMetadataCompat build = new MediaMetadataCompat.Builder(queueItem.m24724a(this.f23398c)).putBitmap(str, bitmap).build();
                queueItem.m24728a(build);
                if (queueItem.m24731b().equals(((QueueItem) this.f23398c.f23407f.get(this.f23398c.f23408g)).m24731b())) {
                    this.f23398c.f23406e.setMetadata(build);
                }
            }

            public void mo6157b(String str, View view) {
            }
        });
    }

    private int m24609q() {
        return this.f23412k != null ? this.f23412k.m24703c() : 0;
    }

    private void m24594b(String str) {
        int q = m24609q();
        Log.b(f23403d, "updatePlaybackState, playback state=" + MediaPlaybackUtils.m25872a(q));
        long j = -1;
        QueueItem s = m24611s();
        Object obj = (s == null || !s.m24738h()) ? null : 1;
        if (this.f23412k != null) {
            j = this.f23412k.m24712j();
            if (s != null && s.m24735e() == 0) {
                long d = m24624d();
                if (d != 0) {
                    s.m24727a(d);
                }
            }
        }
        PlaybackStateCompat.Builder actions = new PlaybackStateCompat.Builder().setActions(m24610r());
        m24586a(actions);
        if (str != null) {
            actions.setErrorMessage(str);
            q = 7;
        }
        actions.setState(q, j, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, SystemClock.elapsedRealtime());
        this.f23406e.setPlaybackState(actions.build());
        if (obj == null) {
            return;
        }
        if (q == 3 || q == 2) {
            this.f23409h.m24570a();
        }
    }

    private void m24586a(PlaybackStateCompat.Builder builder) {
        QueueItem s = m24611s();
        if (s != null) {
            s.m24731b();
        }
    }

    private long m24610r() {
        long j = 4;
        if (this.f23412k == null || this.f23407f == null || this.f23407f.isEmpty()) {
            return 4;
        }
        if (this.f23412k.mo6977d()) {
            j = 4 | 2;
        }
        if (this.f23408g > 0) {
            j |= 16;
        }
        if (this.f23408g < this.f23407f.size() - 1) {
            return j | 32;
        }
        return j;
    }

    private QueueItem m24611s() {
        if (this.f23408g == -1 || this.f23408g >= this.f23407f.size()) {
            return null;
        }
        return (QueueItem) this.f23407f.get(this.f23408g);
    }

    public void mo6889a(int i) {
        m24594b(null);
    }

    public void mo6890b(int i) {
        this.f23415n = i;
    }

    public long m24624d() {
        if (this.f23412k == null) {
            return 0;
        }
        return this.f23412k.mo6979f();
    }

    public boolean m24625e() {
        if (this.f23412k == null) {
            return false;
        }
        return this.f23412k.mo6978e();
    }

    public long m24626f() {
        if (this.f23412k == null) {
            return 0;
        }
        return this.f23412k.mo6980g();
    }

    public void m24620a(boolean z, boolean z2) {
        Log.b(f23403d, "resetPlaybackIfNeeded; video? " + z + "; needed? " + (this.f23413l != z));
        if (this.f23412k != null) {
            this.f23412k.mo6975a(true);
            this.f23412k.mo6976b();
        }
        Playback exoPlayerPlaybackWrapper = new ExoPlayerPlaybackWrapper(this, this, z);
        exoPlayerPlaybackWrapper.m26280e(z2);
        this.f23412k = exoPlayerPlaybackWrapper;
        if (z) {
            m24619a(true);
        } else {
            m24627g();
        }
        m24612t();
        this.f23413l = z;
    }

    public void m24617a(TextureView textureView) {
        Log.b(f23403d, "setVideoPlaybackView");
        this.f23405c = new WeakReference(textureView);
        m24619a(true);
    }

    protected void m24627g() {
        m24617a(null);
    }

    protected void m24619a(boolean z) {
        TextureView textureView = this.f23405c != null ? (TextureView) this.f23405c.get() : null;
        if (z && this.f23412k != null) {
            this.f23412k.mo6974a(textureView);
        }
    }

    public void m24628h() {
        this.f23417p = true;
        this.f23411j.m24580a();
    }

    public void m24629i() {
        this.f23417p = false;
        this.f23411j.m24581b();
    }

    public boolean m24630j() {
        return this.f23417p;
    }

    public void onAudioFocusChange(int i) {
        boolean z = true;
        Log.b(f23403d, "onAudioFocusChange. focusChange = " + i);
        if (i != 1) {
            z = false;
        }
        this.f23418q = z;
        if (this.f23412k != null) {
            this.f23412k.m24700b(i);
        }
    }

    private void m24612t() {
        Log.b(f23403d, "tryToGetAudioFocus");
        if (!this.f23418q) {
            this.f23414m.abandonAudioFocus(this);
            if (this.f23414m.requestAudioFocus(this, 3, 1) == 1) {
                this.f23418q = true;
            }
        }
        if (this.f23412k != null) {
            this.f23412k.m24702b(this.f23418q);
        }
    }

    private void m24613u() {
        Log.b(f23403d, "giveUpAudioFocus");
        if (this.f23418q && this.f23414m.abandonAudioFocus(this) == 1) {
            this.f23418q = false;
            onAudioFocusChange(-1);
            if (this.f23412k != null) {
                this.f23412k.m24702b(this.f23418q);
            }
        }
    }

    private void m24614v() {
        if (!this.f23419r) {
            registerReceiver(this.f23421t, this.f23420s);
            this.f23419r = true;
        }
    }

    private void m24615w() {
        if (this.f23419r) {
            unregisterReceiver(this.f23421t);
            this.f23419r = false;
        }
    }
}
