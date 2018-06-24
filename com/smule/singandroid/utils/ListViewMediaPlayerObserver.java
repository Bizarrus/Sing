package com.smule.singandroid.utils;

import android.widget.AbsListView;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.android.utils.ThreadUtils;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController.MediaPlayerObserver;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController.MediaPlayerObserverInterface;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ListViewMediaPlayerObserver extends MediaPlayerObserver {
    private static final String f24769b = ListViewMediaPlayerObserver.class.getSimpleName();
    private static WeakHashMap<AbsListView, ListViewMediaPlayerObserver> f24770c = new WeakHashMap();
    WeakReference<AbsListView> f24771a;

    public static void m25861a(AbsListView absListView) {
        MediaPlayerObserverInterface listViewMediaPlayerObserver = new ListViewMediaPlayerObserver(absListView);
        f24770c.put(absListView, listViewMediaPlayerObserver);
        MediaPlayerServiceController.m24641a().m24652a(listViewMediaPlayerObserver);
        ReferenceMonitor.a().a(listViewMediaPlayerObserver);
        MediaPlayingListItem.m23034a(absListView);
    }

    public static void m25862b(AbsListView absListView) {
        MediaPlayerObserverInterface mediaPlayerObserverInterface = (ListViewMediaPlayerObserver) f24770c.get(absListView);
        if (mediaPlayerObserverInterface != null) {
            f24770c.remove(absListView);
            MediaPlayerServiceController.m24641a().m24659b(mediaPlayerObserverInterface);
        }
    }

    public ListViewMediaPlayerObserver(AbsListView absListView) {
        this.f24771a = new WeakReference(absListView);
    }

    private void m25860a() {
        final AbsListView absListView = (AbsListView) this.f24771a.get();
        if (absListView != null) {
            ThreadUtils.m19058a(new Runnable(this) {
                final /* synthetic */ ListViewMediaPlayerObserver f24768b;

                public void run() {
                    MediaPlayingListItem.m23034a(absListView);
                }
            });
        }
    }

    public void mo6478c(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        m25860a();
    }

    public void mo6477b(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        m25860a();
    }

    public void mo6476a(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        m25860a();
    }

    public void mo6479d(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        m25860a();
    }

    public void mo6480e(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        m25860a();
    }

    public void mo6481f(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        m25860a();
    }

    public void mo6483h(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        m25860a();
    }
}
