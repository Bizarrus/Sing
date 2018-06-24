package com.smule.singandroid.list_items;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformancePost;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.utils.MiscUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class CommentItem extends RelativeLayout {
    private static final String f22811g = CommentItem.class.getSimpleName();
    @ViewById
    protected ProfileImageWithVIPBadge f22812a;
    @ViewById
    protected TextView f22813b;
    @ViewById
    protected TextView f22814c;
    @ViewById
    protected TextView f22815d;
    @ViewById
    protected View f22816e;
    @ViewById
    protected View f22817f;
    private PerformancePost f22818h;
    private CommentItemListener f22819i;

    public interface CommentItemListener {
        void mo6814a(CommentItem commentItem, PerformancePost performancePost);

        void mo6815b(CommentItem commentItem, PerformancePost performancePost);

        void mo6816c(CommentItem commentItem, PerformancePost performancePost);

        void mo6817d(CommentItem commentItem, PerformancePost performancePost);
    }

    class C46241 implements OnClickListener {
        final /* synthetic */ CommentItem f22802a;

        C46241(CommentItem commentItem) {
            this.f22802a = commentItem;
        }

        public void onClick(View view) {
            this.f22802a.f22819i.mo6817d(this.f22802a, this.f22802a.f22818h);
        }
    }

    class C46252 implements OnClickListener {
        final /* synthetic */ CommentItem f22803a;

        C46252(CommentItem commentItem) {
            this.f22803a = commentItem;
        }

        public void onClick(View view) {
            this.f22803a.mo6867a();
        }
    }

    class C46263 implements OnClickListener {
        final /* synthetic */ CommentItem f22804a;

        C46263(CommentItem commentItem) {
            this.f22804a = commentItem;
        }

        public void onClick(View view) {
            this.f22804a.mo6867a();
        }
    }

    public static CommentItem m24244a(Context context) {
        return CommentItem_.m24252b(context);
    }

    public CommentItem(Context context) {
        super(context);
    }

    public CommentItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setListener(CommentItemListener commentItemListener) {
        this.f22819i = commentItemListener;
    }

    private void setupHashTagSpannable(String str) {
        SpannableString spannableString = new SpannableString(str);
        Hashtag.m24194a((MediaPlayingActivity) getContext(), spannableString);
        this.f22814c.setMovementMethod(new LinkMovementMethod());
        this.f22814c.setText(spannableString);
        this.f22814c.setHighlightColor(0);
    }

    @UiThread
    public void mo6868a(BaseFragment baseFragment, PerformancePost performancePost, final boolean z, boolean z2, final boolean z3) {
        int i = false;
        if (this.f22818h != performancePost) {
            this.f22818h = performancePost;
            this.f22812a.m23395a(this.f22818h.accountIcon, new C46241(this));
            this.f22813b.setText(this.f22818h.accountIcon.handle);
            this.f22813b.setCompoundDrawablesWithIntrinsicBounds(this.f22818h.accountIcon.c() ? C1947R.drawable.icn_verified : 0, 0, 0, 0);
            setupHashTagSpannable(performancePost.message);
            this.f22815d.setText(MiscUtils.m25887a(this.f22818h.time / 1000, true, false, true));
            this.f22813b.setOnClickListener(new C46252(this));
            this.f22817f.setOnClickListener(new C46263(this));
            setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ CommentItem f22807c;

                public boolean onLongClick(View view) {
                    CommentItem commentItem = this.f22807c;
                    boolean z = z || z3;
                    commentItem.mo6869a(z);
                    return true;
                }
            });
            this.f22817f.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ CommentItem f22810c;

                public boolean onLongClick(View view) {
                    CommentItem commentItem = this.f22810c;
                    boolean z = z || z3;
                    commentItem.mo6869a(z);
                    return true;
                }
            });
            View view = this.f22816e;
            if (!z2) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    @UiThread
    protected void mo6869a(boolean z) {
        if (this.f22819i == null) {
            return;
        }
        if (z) {
            this.f22819i.mo6815b(this, this.f22818h);
        } else {
            this.f22819i.mo6814a(this, this.f22818h);
        }
    }

    @UiThread
    protected void mo6867a() {
        if (this.f22819i != null) {
            this.f22819i.mo6816c(this, this.f22818h);
        }
    }
}
