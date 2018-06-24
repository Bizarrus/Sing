package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.network.models.Topic;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.SimpleBarrier;
import com.smule.singandroid.C1947R;

public class SquareAlbumGrid extends GridView {
    final SimpleBarrier f21996a = new SimpleBarrier(4, new C44441(this));
    private Topic f21997b = new Topic();
    private AlbumImageListener f21998c;

    class C44441 implements Runnable {
        final /* synthetic */ SquareAlbumGrid f21992a;

        C44441(SquareAlbumGrid squareAlbumGrid) {
            this.f21992a = squareAlbumGrid;
        }

        public void run() {
            this.f21992a.f21998c.mo6885a();
        }
    }

    public interface AlbumImageListener {
        void mo6885a();
    }

    private class AlbumImagesAdapter extends BaseAdapter {
        final /* synthetic */ SquareAlbumGrid f21994a;
        private LayoutInflater f21995b;

        class C44451 extends SimpleImageLoadingListener {
            final /* synthetic */ AlbumImagesAdapter f21993a;

            C44451(AlbumImagesAdapter albumImagesAdapter) {
                this.f21993a = albumImagesAdapter;
            }

            public void mo6155a(String str, View view, Bitmap bitmap) {
                this.f21993a.f21994a.f21996a.m19034a();
            }
        }

        public AlbumImagesAdapter(SquareAlbumGrid squareAlbumGrid, Context context) {
            this.f21994a = squareAlbumGrid;
            this.f21995b = LayoutInflater.from(context);
        }

        public int getCount() {
            return (this.f21994a.f21997b == null || this.f21994a.f21997b.coverUrls == null) ? 0 : 4;
        }

        public Object getItem(int i) {
            return (this.f21994a.f21997b == null || this.f21994a.f21997b.coverUrls == null || this.f21994a.f21997b.coverUrls.get(i) == null) ? null : (String) this.f21994a.f21997b.coverUrls.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f21995b.inflate(C1947R.layout.square_album_item, viewGroup, false);
                view.setTag(C1947R.id.album_image, view.findViewById(C1947R.id.album_image));
            }
            ImageView imageView = (ImageView) view.getTag(C1947R.id.album_image);
            if (i >= this.f21994a.f21997b.coverUrls.size()) {
                imageView.setImageDrawable(this.f21994a.getResources().getDrawable(C1947R.drawable.album_blank));
                this.f21994a.f21996a.m19034a();
            } else {
                ImageUtils.a((String) this.f21994a.f21997b.coverUrls.get(i), imageView, C1947R.drawable.album_blank, new C44451(this));
            }
            return view;
        }
    }

    public SquareAlbumGrid(Context context) {
        super(context);
    }

    public SquareAlbumGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareAlbumGrid(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i, i3, i4);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setAdapter(new AlbumImagesAdapter(this, getContext()));
    }

    public void setTopic(Topic topic) {
        this.f21997b = topic;
    }

    public void setAlbumImageListener(AlbumImageListener albumImageListener) {
        this.f21998c = albumImageListener;
    }
}
