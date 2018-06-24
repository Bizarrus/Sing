package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.models.Lyric.Version;
import com.smule.singandroid.models.LyricLine;
import com.smule.singandroid.models.SongLyrics;

public class LyricsView extends ListView {
    private static final String f21575a = LyricsView.class.getSimpleName();
    private int f21576b = 0;
    private int f21577c = 0;
    private boolean f21578d = false;
    private SongLyrics f21579e;
    private LyricsAdapter f21580f = new LyricsAdapter();
    private int f21581g = C1947R.layout.lyric_line;

    private class LyricsAdapter extends BaseAdapter {
        final /* synthetic */ LyricsView f21574a;

        private LyricsAdapter(LyricsView lyricsView) {
            this.f21574a = lyricsView;
        }

        public int getCount() {
            if (this.f21574a.f21579e == null) {
                return 0;
            }
            return this.f21574a.f21579e.m24749b();
        }

        public Object getItem(int i) {
            Object obj = null;
            if (this.f21574a.f21579e != null) {
                try {
                    obj = this.f21574a.f21579e.m24744a(i);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            return obj;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            LyricLine lyricLine = (LyricLine) getItem(i);
            if (view == null) {
                view2 = (LyricTextView) View.inflate(this.f21574a.getContext(), this.f21574a.f21581g, null);
            } else {
                view2 = (LyricTextView) view;
            }
            view2.setSingPart(this.f21574a.f21577c);
            view2.setLyricVersion(this.f21574a.f21579e.m24752d());
            view2.setLyrics(lyricLine);
            return view2;
        }
    }

    public LyricsView(Context context) {
        super(context);
        setAdapter(this.f21580f);
    }

    public LyricsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAdapter(this.f21580f);
    }

    public LyricsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setAdapter(this.f21580f);
    }

    public void setTextViewLayoutId(int i) {
        this.f21581g = i;
    }

    public void setSingPart(int i) {
        this.f21577c = i;
    }

    public void setLyrics(SongLyrics songLyrics) {
        if (!(songLyrics == null || songLyrics.m24751c() || this.f21578d)) {
            if (songLyrics.m24752d() == Version.COMMUNITY_V1) {
                LyricLine a = songLyrics.m24744a(0);
                SongLyrics songLyrics2 = songLyrics;
                songLyrics2.m24748a("...", 0.0d, a.f23492b, a.f23496f);
                songLyrics.m24748a(" ", 0.0d, 0.0d, a.f23496f);
            }
            LyricLine a2 = songLyrics.m24744a(songLyrics.m24749b() - 1);
            for (int i = 0; i < 10; i++) {
                double d = (((double) i) * 1.0d) + (a2.f23493c + 3600.0d);
                songLyrics.m24750b("", d, d + 1.0d, a2.f23496f);
            }
            this.f21578d = true;
        }
        this.f21579e = songLyrics;
        this.f21576b = 0;
        this.f21580f.notifyDataSetChanged();
    }

    public int m23218a(int i) {
        double d = 0.5d;
        if (this.f21579e.m24752d() == Version.COMMUNITY_V1) {
            LyricTextView b = m23214b(i);
            if (b != null) {
                d = b.getLastScrollToPastDuration();
            }
        }
        return (int) (d * 1000.0d);
    }

    public void m23219a() {
        smoothScrollToPositionFromTop(0, 0, 0);
    }

    private LyricTextView m23214b(int i) {
        int firstVisiblePosition = getFirstVisiblePosition();
        int childCount = (getChildCount() + firstVisiblePosition) - 1;
        if (i < firstVisiblePosition || i > childCount) {
            return null;
        }
        return (LyricTextView) getChildAt(i - firstVisiblePosition);
    }

    public void m23220a(double d) {
        if (this.f21579e.m24752d() == Version.COMMUNITY_V1) {
            m23217c(d);
        } else {
            m23215b(d);
        }
    }

    private void m23215b(double d) {
        while (this.f21576b < this.f21580f.getCount()) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int childCount = (getChildCount() + firstVisiblePosition) - 1;
            while (firstVisiblePosition <= childCount) {
                LyricTextView b = m23214b(firstVisiblePosition);
                if (b != null) {
                    b.m23211b(d);
                }
                firstVisiblePosition++;
            }
            LyricTextView b2 = m23214b(this.f21576b + 1);
            if (b2 != null && b2.m23210a(d)) {
                smoothScrollToPositionFromTop(this.f21576b + 1, 0, m23218a(this.f21576b));
                this.f21576b++;
            } else {
                return;
            }
        }
    }

    private void m23217c(double d) {
        while (this.f21576b < this.f21580f.getCount()) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int childCount = (getChildCount() + firstVisiblePosition) - 1;
            while (firstVisiblePosition <= childCount) {
                LyricTextView b = m23214b(firstVisiblePosition);
                if (b != null) {
                    b.m23211b(d);
                }
                firstVisiblePosition++;
            }
            LyricTextView b2 = m23214b(this.f21576b);
            LyricTextView b3 = m23214b(this.f21576b + 1);
            if (b2 != null) {
                boolean a;
                double lastScrollToPastDuration = b2.getLastScrollToPastDuration();
                if (b3 != null) {
                    a = b3.m23210a(d + lastScrollToPastDuration);
                } else {
                    LyricLine lyricLine = (LyricLine) this.f21580f.getItem(this.f21576b + 1);
                    a = lyricLine != null ? d + lastScrollToPastDuration >= lyricLine.f23492b : false;
                }
                if (a) {
                    if (this.f21576b > 0) {
                        smoothScrollToPositionFromTop(this.f21576b, 0, (int) (1000.0d * lastScrollToPastDuration));
                    }
                    this.f21576b++;
                } else {
                    return;
                }
            }
            return;
        }
    }
}
