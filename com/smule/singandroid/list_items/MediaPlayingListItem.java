package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.utils.MiscUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class MediaPlayingListItem extends LinearLayout implements MediaPlayingViewInterface {
    private static final String f21376a = MediaPlayingListItem.class.getName();
    @ViewById
    protected PlayableItemView ae;
    protected VisualState af = VisualState.UNKNOWN;
    private String f21377b;

    class C46661 implements OnClickListener {
        final /* synthetic */ MediaPlayingListItem f23092a;

        C46661(MediaPlayingListItem mediaPlayingListItem) {
            this.f23092a = mediaPlayingListItem;
        }

        public void onClick(View view) {
            this.f23092a.mo6881u();
        }
    }

    class C46672 implements OnClickListener {
        final /* synthetic */ MediaPlayingListItem f23093a;

        C46672(MediaPlayingListItem mediaPlayingListItem) {
            this.f23093a = mediaPlayingListItem;
        }

        public void onClick(View view) {
            this.f23093a.mo6881u();
        }
    }

    protected enum VisualState {
        UNKNOWN,
        IDLE,
        LOADING,
        PLAYING
    }

    public MediaPlayingListItem(Context context) {
        super(context);
    }

    public MediaPlayingListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MediaPlayingListItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public String getMediaKey() {
        return this.f21377b;
    }

    @AfterViews
    protected void m23042p() {
        this.ae.f21798c.setOnClickListener(new C46661(this));
        this.ae.f21797b.setOnClickListener(new C46672(this));
    }

    protected void m23036a(String str) {
        this.f21377b = str;
        this.af = VisualState.UNKNOWN;
        u_();
    }

    public boolean m23043q() {
        MediaPlayerServiceController a = MediaPlayerServiceController.m24641a();
        if (!a.m24676j()) {
            return false;
        }
        String i = a.m24673i();
        if (i == null || !i.equals(this.f21377b)) {
            return false;
        }
        return true;
    }

    public boolean m23044r() {
        MediaPlayerServiceController a = MediaPlayerServiceController.m24641a();
        if (!a.m24678k()) {
            return false;
        }
        String i = a.m24673i();
        if (i == null || !i.equals(this.f21377b)) {
            return false;
        }
        return true;
    }

    public boolean m23045s() {
        MediaPlayerServiceController a = MediaPlayerServiceController.m24641a();
        if (!a.m24680l()) {
            return false;
        }
        String i = a.m24673i();
        if (i == null || !i.equals(this.f21377b)) {
            return false;
        }
        return true;
    }

    public boolean m23046t() {
        String h = MediaPlayerServiceController.m24641a().m24671h();
        return h != null && h.equals(this.f21377b);
    }

    public void mo6881u() {
        Log.b(f21376a, "configureAlbumArtPlayingFunctionality - toggleAudio with key: " + this.f21377b);
        MediaPlayerServiceController.m24641a().m24663d();
    }

    public void u_() {
        if (m23046t() || m23045s()) {
            mo6873n();
        } else if (m23043q()) {
            mo6872m();
        } else {
            mo6874o();
        }
    }

    public void mo6724b() {
    }

    public void setAlbumArtClickedState(boolean z) {
        if (z) {
            mo6873n();
        } else {
            mo6874o();
        }
    }

    protected void mo6872m() {
        if (this.af != VisualState.PLAYING) {
            this.ae.m23380c();
            this.af = VisualState.PLAYING;
        }
    }

    protected void mo6873n() {
        if (this.af != VisualState.LOADING) {
            this.ae.m23381d();
            this.af = VisualState.LOADING;
        }
    }

    protected void mo6874o() {
        if (this.af != VisualState.IDLE) {
            Log.b(f21376a, "setIdleState: " + this.f21377b);
            this.ae.m23379b();
            this.af = VisualState.IDLE;
        }
    }

    public static void m23034a(AbsListView absListView) {
        Log.b(f21376a, "refreshAlbumArtPlayingFunctionality loadingKey: " + MediaPlayerServiceController.m24641a().m24671h() + " loadedKey: " + MediaPlayerServiceController.m24641a().m24673i());
        for (int i = 0; i < absListView.getChildCount(); i++) {
            View childAt = absListView.getChildAt(i);
            if (childAt instanceof MediaPlayingViewInterface) {
                ((MediaPlayingViewInterface) childAt).u_();
            }
        }
    }

    public static void m23035b(AbsListView absListView) {
        for (int i = 0; i < absListView.getChildCount(); i++) {
            View childAt = absListView.getChildAt(i);
            if (childAt instanceof MediaPlayingViewInterface) {
                ((MediaPlayingViewInterface) childAt).mo6724b();
            }
        }
    }

    public boolean m23037a(PerformanceV2 performanceV2) {
        return MiscUtils.m25895a(performanceV2);
    }
}
