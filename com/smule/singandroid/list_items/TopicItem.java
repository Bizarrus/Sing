package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.network.models.Topic;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.SquareAlbumGrid;
import com.smule.singandroid.customviews.SquareAlbumGrid.AlbumImageListener;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class TopicItem extends FrameLayout implements AlbumImageListener {
    public static boolean f23283f = false;
    @ViewById
    SquareAlbumGrid f23284a;
    @ViewById
    ImageView f23285b;
    @ViewById
    TextView f23286c;
    @ViewById
    View f23287d;
    @ViewById
    View f23288e;
    private Topic f23289g = new Topic();

    public static TopicItem m24502a(Context context) {
        return TopicItem_.m24508b(context);
    }

    public TopicItem(Context context) {
        super(context);
    }

    @AfterViews
    public void m24506b() {
        this.f23284a.setAlbumImageListener(this);
    }

    public void m24504a(Topic topic, boolean z) {
        if (!this.f23289g.equals(topic)) {
            this.f23289g = topic;
            this.f23284a.setTopic(topic);
            this.f23286c.setText(this.f23289g.displayName);
            m24505a(z);
            this.f23284a.invalidateViews();
        }
        m24507b(z);
    }

    public void mo6885a() {
        this.f23285b.setVisibility(4);
        this.f23284a.setVisibility(0);
    }

    public void m24505a(boolean z) {
        if (z) {
            this.f23287d.setBackgroundColor(getResources().getColor(C1947R.color.black_75_percent));
            this.f23288e.setVisibility(0);
            ImageUtils.b(this.f23286c, null);
            return;
        }
        this.f23287d.setBackgroundColor(getResources().getColor(C1947R.color.black_25_percent));
        this.f23288e.setVisibility(4);
        ImageUtils.b(this.f23286c, getResources().getDrawable(C1947R.drawable.btn_topics));
    }

    public void m24507b(boolean z) {
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        if (z) {
            setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            return;
        }
        if (f23283f) {
            f = 0.5f;
        }
        setAlpha(f);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
