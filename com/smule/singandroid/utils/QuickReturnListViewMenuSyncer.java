package com.smule.singandroid.utils;

import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.foound.widget.AmazingListView;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.BaseFragment$BaseFragmentResponder$MenuToggleAction;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.customviews.MasterToolbar;

public class QuickReturnListViewMenuSyncer {
    private static final String f24911a = QuickReturnListViewMenuSyncer.class.getSimpleName();
    private int f24912b;
    private int f24913c = 0;
    private int f24914d;
    private int f24915e = -1;
    private boolean f24916f = false;
    private int f24917g = -10;
    private int f24918h = 0;
    private View f24919i;
    private int f24920j = 0;
    private int f24921k = 0;
    private int f24922l = -1;
    private final MediaPlayingActivity f24923m;
    private final AbsListView f24924n;
    private final View f24925o;
    private final View f24926p;
    private final MasterToolbar f24927q;
    private ActionBarFadeCallback f24928r;
    private boolean f24929s = true;
    private int f24930t;
    private ActionBarHighlightMode f24931u;
    private DataSetObserver f24932v;
    private OnGlobalLayoutListener f24933w;
    private Runnable f24934x = new C50261(this);
    private Handler f24935y = new Handler();
    private boolean f24936z;

    public interface ActionBarFadeCallback {
        void mo6661A();

        void mo6662d(int i);

        void mo6663z();
    }

    class C50261 implements Runnable {
        final /* synthetic */ QuickReturnListViewMenuSyncer f24898a;

        C50261(QuickReturnListViewMenuSyncer quickReturnListViewMenuSyncer) {
            this.f24898a = quickReturnListViewMenuSyncer;
        }

        public void run() {
            if (this.f24898a.m25973g()) {
                int i;
                if (((int) this.f24898a.f24925o.getTranslationY()) < this.f24898a.f24925o.getHeight() / 2) {
                    i = 0;
                } else {
                    i = this.f24898a.f24925o.getHeight();
                }
                this.f24898a.f24923m.a(i - this.f24898a.f24912b);
                this.f24898a.f24925o.animate().setDuration(200).translationY((float) i);
                this.f24898a.f24925o.clearAnimation();
            }
        }
    }

    public enum ActionBarHighlightMode {
        NEVER,
        ALWAYS,
        AFTER_SCROLL
    }

    public boolean m25991a() {
        return this.f24936z;
    }

    public QuickReturnListViewMenuSyncer(MediaPlayingActivity mediaPlayingActivity, final AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, final OnScrollListener onScrollListener, View view, View view2, MasterToolbar masterToolbar) {
        this.f24923m = mediaPlayingActivity;
        this.f24924n = absListView;
        this.f24925o = view;
        this.f24926p = view2;
        this.f24927q = masterToolbar;
        this.f24931u = actionBarHighlightMode;
        if (this.f24925o != null) {
            this.f24923m.a(BaseFragment$BaseFragmentResponder$MenuToggleAction.SHOW_IF_ALLOWED);
            this.f24912b = this.f24925o.getHeight();
            this.f24932v = new DataSetObserver(this) {
                final /* synthetic */ QuickReturnListViewMenuSyncer f24900b;

                public void onInvalidated() {
                    super.onInvalidated();
                }

                public void onChanged() {
                    super.onChanged();
                    if (this.f24900b.f24912b != 0) {
                        this.f24900b.m25954a(absListView);
                    }
                }
            };
            ((ListAdapter) absListView.getAdapter()).registerDataSetObserver(this.f24932v);
            this.f24933w = new OnGlobalLayoutListener(this, new ViewTreeObserver.OnGlobalLayoutListener(this) {
                final /* synthetic */ QuickReturnListViewMenuSyncer f24902b;

                public void onGlobalLayout() {
                    this.f24902b.f24912b = this.f24902b.f24925o.getHeight();
                    this.f24902b.m25954a(absListView);
                    LayoutUtils.m25859b(absListView, this.f24902b.f24933w);
                }
            });
            LayoutUtils.m25854a((View) absListView, this.f24933w);
        }
        m25994d();
        absListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ QuickReturnListViewMenuSyncer f24904b;

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                int i4;
                this.f24904b.f24919i = null;
                if (onScrollListener != null) {
                    onScrollListener.onScroll(absListView, i, i2, i3);
                }
                if (absListView instanceof AmazingListView) {
                    ((AmazingListView) absListView).a(i);
                }
                if (absListView instanceof MagicListView) {
                    ((MagicListView) absListView).m18010a(i);
                }
                if (this.f24904b.f24931u == ActionBarHighlightMode.AFTER_SCROLL) {
                    if (i > 0) {
                        i4 = 255;
                    } else {
                        if (i2 > 1) {
                            i4 = absListView.getChildAt(0).getTop();
                            int af = this.f24904b.f24923m.af();
                            if (i4 < 0) {
                                i4 = Math.min((i4 * -255) / af, 255);
                            }
                        }
                        i4 = 0;
                    }
                    if (i4 < this.f24904b.f24930t && this.f24904b.f24936z) {
                        this.f24904b.f24936z = false;
                        if (this.f24904b.f24928r != null) {
                            this.f24904b.f24928r.mo6663z();
                        }
                    }
                    this.f24904b.f24930t = i4;
                    this.f24904b.f24927q.m23227a(i4, false);
                    if (this.f24904b.f24928r != null) {
                        this.f24904b.f24928r.mo6662d(this.f24904b.f24927q.m23225a(i4));
                    }
                    if (i4 == 255 && !this.f24904b.f24936z) {
                        this.f24904b.f24936z = true;
                        if (this.f24904b.f24928r != null) {
                            this.f24904b.f24928r.mo6661A();
                        }
                    } else if (i4 != 255) {
                        if (this.f24904b.f24936z && this.f24904b.f24928r != null) {
                            this.f24904b.f24928r.mo6663z();
                        }
                        this.f24904b.f24936z = false;
                    }
                }
                if (this.f24904b.m25973g()) {
                    i4 = (int) this.f24904b.f24925o.getTranslationY();
                    this.f24904b.f24914d = (int) (1.2d * ((double) this.f24904b.m25988a(absListView, i, i2, i3)));
                    if (this.f24904b.f24929s) {
                        if (this.f24904b.f24914d != 0) {
                            this.f24904b.f24913c = this.f24904b.f24914d;
                        } else {
                            return;
                        }
                    }
                    this.f24904b.f24929s = false;
                    if (this.f24904b.f24917g == i) {
                        if (this.f24904b.f24919i != null) {
                            i4 += this.f24904b.f24918h - this.f24904b.f24919i.getTop();
                        }
                    } else if (Math.abs(this.f24904b.f24917g - i) != 1) {
                        i4 += this.f24904b.f24914d - this.f24904b.f24913c;
                    } else if (this.f24904b.f24917g < i) {
                        i4 += this.f24904b.f24918h - (this.f24904b.f24919i.getTop() - this.f24904b.f24920j);
                    } else {
                        i4 += (this.f24904b.f24918h - this.f24904b.f24921k) - this.f24904b.f24919i.getTop();
                    }
                    this.f24904b.f24913c = this.f24904b.f24914d;
                    i4 = Math.min(this.f24904b.f24912b, Math.max(0, i4));
                    this.f24904b.f24925o.clearAnimation();
                    int i5 = i + i2;
                    if (i5 == i3 && this.f24904b.f24922l != i5) {
                        Log.b(QuickReturnListViewMenuSyncer.f24911a, "last item");
                        this.f24904b.f24922l = i5;
                        i4 = this.f24904b.f24912b;
                    }
                    this.f24904b.f24925o.setTranslationY((float) i4);
                    i4 = Math.min(0, i4 - this.f24904b.f24912b);
                    this.f24904b.f24923m.b(i4);
                    this.f24904b.f24926p.setTranslationY((float) i4);
                    if (this.f24904b.f24919i != null) {
                        this.f24904b.f24917g = i;
                        this.f24904b.f24918h = this.f24904b.f24919i.getTop();
                        this.f24904b.f24920j = this.f24904b.f24921k;
                    }
                    this.f24904b.f24919i = null;
                }
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (onScrollListener != null) {
                    onScrollListener.onScrollStateChanged(absListView, i);
                }
                if (this.f24904b.m25973g() && i == 0) {
                    int i2;
                    if (((int) this.f24904b.f24925o.getTranslationY()) < this.f24904b.f24925o.getHeight() / 2) {
                        i2 = 0;
                    } else {
                        i2 = this.f24904b.f24925o.getHeight();
                    }
                    this.f24904b.f24923m.a(i2 - this.f24904b.f24912b);
                    this.f24904b.f24925o.animate().setDuration(200).translationY((float) i2);
                    this.f24904b.f24925o.clearAnimation();
                }
            }
        });
    }

    public void m25992b() {
        if (this.f24933w != null) {
            LayoutUtils.m25859b(this.f24924n, this.f24933w);
            this.f24933w = null;
        }
        if (this.f24932v != null) {
            if (!(this.f24924n == null || this.f24924n.getAdapter() == null)) {
                ((ListAdapter) this.f24924n.getAdapter()).unregisterDataSetObserver(this.f24932v);
            }
            this.f24932v = null;
        }
        if (this.f24924n != null) {
            this.f24924n.setOnScrollListener(null);
        }
    }

    private void m25954a(AbsListView absListView) {
        if (absListView.getPaddingBottom() != this.f24912b) {
            if (m25960b(absListView)) {
                absListView.setPadding(absListView.getPaddingLeft(), absListView.getPaddingTop(), absListView.getPaddingRight(), this.f24912b);
            } else if (absListView.getLastVisiblePosition() != -1) {
                absListView.setPadding(absListView.getPaddingLeft(), absListView.getPaddingTop(), absListView.getPaddingRight(), 0);
            }
        }
    }

    private boolean m25973g() {
        return this.f24925o != null && this.f24925o.getVisibility() == 0;
    }

    private boolean m25960b(AbsListView absListView) {
        if (!m25973g()) {
            return false;
        }
        int lastVisiblePosition = absListView.getLastVisiblePosition();
        int firstVisiblePosition = absListView.getFirstVisiblePosition();
        if (lastVisiblePosition < absListView.getCount() - 1 || lastVisiblePosition < 0 || firstVisiblePosition != 0 || absListView.getCount() == 0) {
            return false;
        }
        int i;
        View childAt = absListView.getChildAt(0);
        if (childAt == null || childAt.getTop() >= 0) {
            i = 0;
        } else {
            i = -childAt.getTop();
        }
        View childAt2 = absListView.getChildAt(lastVisiblePosition - firstVisiblePosition);
        if (((double) ((childAt2.getBottom() + i) - this.f24925o.getBottom())) > 1.3d * ((double) this.f24912b) || i + childAt2.getBottom() <= this.f24925o.getTop() || lastVisiblePosition < absListView.getCount() - 1) {
            return false;
        }
        return true;
    }

    private int m25951a(View view) {
        int height = view.getHeight();
        if (height > 0) {
            return height;
        }
        view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredHeight();
    }

    public int m25988a(AbsListView absListView, int i, int i2, int i3) {
        if (i3 <= 0 || i2 <= 0) {
            return 0;
        }
        int count;
        int i4;
        if (absListView instanceof GridView) {
            GridView gridView = (GridView) absListView;
            if (gridView.getNumColumns() > 0) {
                i /= gridView.getNumColumns();
            }
        }
        if (absListView.getAdapter() != null) {
            count = ((ListAdapter) absListView.getAdapter()).getCount();
        } else {
            count = 0;
        }
        if (count > 0 && !this.f24916f) {
            i4 = 0;
            for (count = 0; count < i2; count++) {
                i4 += m25951a(absListView.getChildAt(count));
            }
            this.f24915e = i4 / i2;
            this.f24916f = i2 > 2;
        }
        View childAt = absListView.getChildAt(0);
        if (childAt == null) {
            return 0;
        }
        this.f24919i = childAt;
        i4 = m25951a(childAt);
        if (i4 <= 0) {
            return 0;
        }
        this.f24921k = i4;
        return (this.f24915e * i) - ((childAt.getTop() * this.f24915e) / i4);
    }

    public void m25993c() {
        m25994d();
    }

    public void m25994d() {
        final int i = this.f24931u == ActionBarHighlightMode.ALWAYS ? 255 : this.f24930t;
        this.f24927q.m23227a(i, true);
        if (this.f24928r != null) {
            this.f24928r.mo6662d(this.f24927q.m23225a(i));
        }
        this.f24923m.runOnUiThread(new Runnable(this) {
            final /* synthetic */ QuickReturnListViewMenuSyncer f24906b;

            public void run() {
                if (this.f24906b.f24923m.f()) {
                    this.f24906b.f24927q.m23227a(i, true);
                    if (this.f24906b.f24928r != null) {
                        this.f24906b.f24928r.mo6662d(this.f24906b.f24927q.m23225a(i));
                    }
                }
            }
        });
    }

    public void m25989a(int i) {
        this.f24931u = ActionBarHighlightMode.NEVER;
        this.f24927q.m23227a(i, true);
    }

    public void m25990a(ActionBarFadeCallback actionBarFadeCallback) {
        this.f24928r = actionBarFadeCallback;
    }

    public void m25995e() {
        this.f24929s = true;
    }
}
