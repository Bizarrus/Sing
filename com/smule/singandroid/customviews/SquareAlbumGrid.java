/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.GridView
 *  android.widget.ImageView
 *  android.widget.ListAdapter
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.network.models.Topic;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.SimpleBarrier;
import java.util.ArrayList;

public class SquareAlbumGrid
extends GridView {
    final SimpleBarrier a;
    private Topic b = new Topic();
    private AlbumImageListener c;

    public SquareAlbumGrid(Context context) {
        super(context);
        this.a = new SimpleBarrier(4, new Runnable(){

            @Override
            public void run() {
                SquareAlbumGrid.this.c.a();
            }
        });
    }

    public SquareAlbumGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new SimpleBarrier(4, new );
    }

    public SquareAlbumGrid(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a = new SimpleBarrier(4, new );
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.setAdapter((ListAdapter)new AlbumImagesAdapter(this.getContext()));
    }

    public void onMeasure(int n, int n2) {
        super.onMeasure(n, n);
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n, n3, n4);
    }

    public void setAlbumImageListener(AlbumImageListener albumImageListener) {
        this.c = albumImageListener;
    }

    public void setTopic(Topic topic) {
        this.b = topic;
    }

    public static interface AlbumImageListener {
        public void a();
    }

    private class AlbumImagesAdapter
    extends BaseAdapter {
        private LayoutInflater b;

        public AlbumImagesAdapter(Context context) {
            this.b = LayoutInflater.from((Context)context);
        }

        public int getCount() {
            if (SquareAlbumGrid.this.b != null && SquareAlbumGrid.b((SquareAlbumGrid)SquareAlbumGrid.this).coverUrls != null) {
                return 4;
            }
            return 0;
        }

        public Object getItem(int n) {
            if (SquareAlbumGrid.this.b != null && SquareAlbumGrid.b((SquareAlbumGrid)SquareAlbumGrid.this).coverUrls != null && SquareAlbumGrid.b((SquareAlbumGrid)SquareAlbumGrid.this).coverUrls.get(n) != null) {
                return SquareAlbumGrid.b((SquareAlbumGrid)SquareAlbumGrid.this).coverUrls.get(n);
            }
            return null;
        }

        public long getItemId(int n) {
            return n;
        }

        public View getView(int n, View view, ViewGroup viewGroup) {
            View view2 = view;
            if (view == null) {
                view2 = this.b.inflate(2130903436, viewGroup, false);
                view2.setTag(2131756731, (Object)view2.findViewById(2131756731));
            }
            view = (ImageView)view2.getTag(2131756731);
            if (n >= SquareAlbumGrid.b((SquareAlbumGrid)SquareAlbumGrid.this).coverUrls.size()) {
                view.setImageDrawable(SquareAlbumGrid.this.getResources().getDrawable(2130837600));
                SquareAlbumGrid.this.a.a();
                return view2;
            }
            ImageUtils.a(SquareAlbumGrid.b((SquareAlbumGrid)SquareAlbumGrid.this).coverUrls.get(n), (ImageView)view, 2130837600, (ImageLoadingListener)new SimpleImageLoadingListener(){

                public void a(String string2, View view, Bitmap bitmap) {
                    SquareAlbumGrid.this.a.a();
                }
            });
            return view2;
        }

    }

}

