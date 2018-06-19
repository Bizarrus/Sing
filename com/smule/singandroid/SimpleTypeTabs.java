package com.smule.singandroid;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class SimpleTypeTabs extends RelativeLayout {
    private static final String f19961e = SimpleTypeTabs.class.getName();
    @ViewsById
    protected List<View> f19962a;
    @ViewsById
    protected List<TextView> f19963b;
    @ViewsById
    protected List<TextView> f19964c;
    @ViewsById
    protected List<ImageView> f19965d;
    private Context f19966f;
    private int f19967g = 0;

    public interface OnTabClickListener {
        void b_(int i);
    }

    public SimpleTypeTabs(Context context) {
        super(context);
        this.f19966f = context;
    }

    public SimpleTypeTabs(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f19966f = context;
    }

    @AfterViews
    protected void m21482a() {
        for (int i = 0; i < this.f19962a.size(); i++) {
            ((View) this.f19962a.get(i)).setTag(Integer.valueOf(i));
        }
    }

    public void m21484a(int i, int i2) {
        ((TextView) this.f19963b.get(i)).setText(i2);
    }

    public void setOnTabClickListener(final OnTabClickListener onTabClickListener) {
        OnClickListener c41071 = new OnClickListener(this) {
            final /* synthetic */ SimpleTypeTabs f19958b;

            public void onClick(View view) {
                int intValue = ((Integer) view.getTag()).intValue();
                if (this.f19958b.f19967g != intValue) {
                    this.f19958b.m21483a(intValue);
                    onTabClickListener.b_(intValue);
                }
            }
        };
        for (View onClickListener : this.f19962a) {
            onClickListener.setOnClickListener(c41071);
        }
    }

    public void m21483a(final int i) {
        this.f19967g = i;
        new Handler(this.f19966f.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ SimpleTypeTabs f19960b;

            public void run() {
                Log.b(SimpleTypeTabs.f19961e, "Setting performance type to " + i);
                for (View view : this.f19960b.f19962a) {
                    if (i == ((Integer) view.getTag()).intValue()) {
                        view.setBackgroundDrawable(this.f19960b.getResources().getDrawable(C1947R.drawable.simple_tab_selected_indicator));
                    } else {
                        view.setBackgroundColor(this.f19960b.getResources().getColor(C1947R.color.singing_song_title_background));
                    }
                }
                int color = this.f19960b.getResources().getColor(C1947R.color.button_text_inverse);
                int color2 = this.f19960b.getResources().getColor(C1947R.color.menu_title_grey);
                for (int i = 0; i < this.f19960b.f19962a.size(); i++) {
                    int i2;
                    TextView textView = (TextView) this.f19960b.f19963b.get(i);
                    if (i == i) {
                        i2 = color;
                    } else {
                        i2 = color2;
                    }
                    textView.setTextColor(i2);
                }
            }
        });
    }

    @AfterViews
    protected void m21486b() {
        m21481d();
    }

    private void m21481d() {
        DisplayMetrics displayMetrics = this.f19966f.getResources().getDisplayMetrics();
        int numberOfVisibleTabs = displayMetrics.widthPixels / getNumberOfVisibleTabs();
        for (View minimumWidth : this.f19962a) {
            minimumWidth.setMinimumWidth(numberOfVisibleTabs);
        }
    }

    public void m21485a(int i, boolean z) {
        for (View view : this.f19962a) {
            if (((Integer) view.getTag()).intValue() == i) {
                view.setVisibility(z ? 0 : 8);
                m21481d();
            }
        }
        m21481d();
    }

    private int getNumberOfVisibleTabs() {
        int i = 0;
        for (View visibility : this.f19962a) {
            int i2;
            if (visibility.getVisibility() == 0) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }
}
