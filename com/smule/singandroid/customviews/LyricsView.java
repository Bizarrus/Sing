/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.smule.singandroid.customviews.LyricTextView;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.models.LyricLine;
import com.smule.singandroid.models.SongLyrics;

public class LyricsView
extends ListView {
    private static final String a = LyricsView.class.getSimpleName();
    private int b = 0;
    private int c = 0;
    private boolean d = false;
    private SongLyrics e;
    private LyricsAdapter f;
    private int g = 2130903278;

    public LyricsView(Context context) {
        super(context);
        this.f = new LyricsAdapter();
        this.setAdapter((ListAdapter)this.f);
    }

    public LyricsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new LyricsAdapter();
        this.setAdapter((ListAdapter)this.f);
    }

    public LyricsView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.f = new LyricsAdapter();
        this.setAdapter((ListAdapter)this.f);
    }

    private LyricTextView b(int n) {
        int n2 = this.getFirstVisiblePosition();
        int n3 = this.getChildCount();
        if (n < n2 || n > n3 + n2 - 1) {
            return null;
        }
        return (LyricTextView)this.getChildAt(n - n2);
    }

    private void b(double d) {
        while (this.b < this.f.getCount()) {
            LyricTextView lyricTextView;
            int n = this.getFirstVisiblePosition();
            int n2 = this.getChildCount();
            for (int i = n; i <= n2 + n - 1; ++i) {
                lyricTextView = this.b(i);
                if (lyricTextView == null) continue;
                lyricTextView.b(d);
            }
            lyricTextView = this.b(this.b + 1);
            if (lyricTextView == null || !lyricTextView.a(d)) break;
            this.smoothScrollToPositionFromTop(this.b + 1, 0, this.a(this.b));
            ++this.b;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(double d) {
        while (this.b < this.f.getCount()) {
            int n;
            boolean bl;
            Object object;
            int n2 = this.getFirstVisiblePosition();
            int n3 = this.getChildCount();
            for (n = n2; n <= n3 + n2 - 1; ++n) {
                object = this.b(n);
                if (object == null) continue;
                object.b(d);
            }
            object = this.b(this.b);
            LyricTextView lyricTextView = this.b(this.b + 1);
            if (object == null) break;
            double d2 = object.getLastScrollToPastDuration();
            if (lyricTextView != null) {
                bl = lyricTextView.a(d + d2);
            } else {
                object = (LyricLine)this.f.getItem(this.b + 1);
                if (object == null) {
                    return;
                }
                if (d + d2 < object.b) {
                    return;
                }
                bl = true;
            }
            if (!bl) break;
            if (this.b > 0) {
                n = (int)(1000.0 * d2);
                this.smoothScrollToPositionFromTop(this.b, 0, n);
            }
            ++this.b;
        }
    }

    public int a(int n) {
        double d;
        double d2 = d = 0.5;
        if (this.e.d() == Lyric.Version.b) {
            LyricTextView lyricTextView = this.b(n);
            d2 = d;
            if (lyricTextView != null) {
                d2 = lyricTextView.getLastScrollToPastDuration();
            }
        }
        return (int)(d2 * 1000.0);
    }

    public void a() {
        this.smoothScrollToPositionFromTop(0, 0, 0);
    }

    public void a(double d) {
        if (this.e.d() == Lyric.Version.b) {
            this.c(d);
            return;
        }
        this.b(d);
    }

    public void setLyrics(SongLyrics songLyrics) {
        if (songLyrics != null && !songLyrics.c() && !this.d) {
            LyricLine lyricLine;
            if (songLyrics.d() == Lyric.Version.b) {
                lyricLine = songLyrics.a(0);
                songLyrics.a("...", 0.0, lyricLine.b, lyricLine.f);
                songLyrics.a(" ", 0.0, 0.0, lyricLine.f);
            }
            lyricLine = songLyrics.a(songLyrics.b() - 1);
            for (int i = 0; i < 10; ++i) {
                double d = lyricLine.c;
                d = (double)i * 1.0 + (d + 3600.0);
                songLyrics.b("", d, d + 1.0, lyricLine.f);
            }
            this.d = true;
        }
        this.e = songLyrics;
        this.b = 0;
        this.f.notifyDataSetChanged();
    }

    public void setSingPart(int n) {
        this.c = n;
    }

    public void setTextViewLayoutId(int n) {
        this.g = n;
    }

    private class LyricsAdapter
    extends BaseAdapter {
        private LyricsAdapter() {
        }

        public int getCount() {
            if (LyricsView.this.e == null) {
                return 0;
            }
            return LyricsView.this.e.b();
        }

        public Object getItem(int n) {
            if (LyricsView.this.e == null) {
                return null;
            }
            try {
                LyricLine lyricLine = LyricsView.this.e.a(n);
                return lyricLine;
            }
            catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                return null;
            }
        }

        public long getItemId(int n) {
            return n;
        }

        /*
         * Enabled aggressive block sorting
         */
        public View getView(int n, View object, ViewGroup object2) {
            object2 = (LyricLine)this.getItem(n);
            object = object == null ? (LyricTextView)View.inflate((Context)LyricsView.this.getContext(), (int)LyricsView.this.g, (ViewGroup)null) : (LyricTextView)((Object)object);
            object.setSingPart(LyricsView.this.c);
            object.setLyricVersion(LyricsView.this.e.d());
            object.setLyrics((LyricLine)object2);
            return object;
        }
    }

}

