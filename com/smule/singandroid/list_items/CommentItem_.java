package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.TextView;
import com.smule.android.network.models.PerformancePost;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CommentItem_ extends CommentItem implements HasViews, OnViewChangedListener {
    private boolean f22829g = false;
    private final OnViewChangedNotifier f22830h = new OnViewChangedNotifier();

    class C46313 implements Runnable {
        final /* synthetic */ CommentItem_ f22828a;

        C46313(CommentItem_ commentItem_) {
            this.f22828a = commentItem_;
        }

        public void run() {
            super.mo6867a();
        }
    }

    public CommentItem_(Context context) {
        super(context);
        m24253b();
    }

    public static CommentItem m24252b(Context context) {
        CommentItem commentItem_ = new CommentItem_(context);
        commentItem_.onFinishInflate();
        return commentItem_;
    }

    public void onFinishInflate() {
        if (!this.f22829g) {
            this.f22829g = true;
            inflate(getContext(), C1947R.layout.comment_item, this);
            this.f22830h.a(this);
        }
        super.onFinishInflate();
    }

    private void m24253b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22830h);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24256a(HasViews hasViews) {
        this.a = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mCommenterProfileImage);
        this.b = (TextView) hasViews.findViewById(C1947R.id.mCommenterHandle);
        this.c = (TextView) hasViews.findViewById(C1947R.id.mComment);
        this.d = (TextView) hasViews.findViewById(C1947R.id.mTimeIcon);
        this.e = hasViews.findViewById(C1947R.id.mOwnCommentHighlight);
        this.f = hasViews.findViewById(C1947R.id.not_owner_comment_view);
    }

    public void mo6868a(BaseFragment baseFragment, PerformancePost performancePost, boolean z, boolean z2, boolean z3) {
        final BaseFragment baseFragment2 = baseFragment;
        final PerformancePost performancePost2 = performancePost;
        final boolean z4 = z;
        final boolean z5 = z2;
        final boolean z6 = z3;
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ CommentItem_ f22825f;

            public void run() {
                super.mo6868a(baseFragment2, performancePost2, z4, z5, z6);
            }
        }, 0);
    }

    protected void mo6869a(final boolean z) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ CommentItem_ f22827b;

            public void run() {
                super.mo6869a(z);
            }
        }, 0);
    }

    protected void mo6867a() {
        UiThreadExecutor.a("", new C46313(this), 0);
    }
}
