package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import java.text.MessageFormat;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PerformanceListEmptyListItem extends LinearLayout {
    @ViewById
    ImageView f23112a;
    @ViewById
    TextView f23113b;
    @ViewById
    TextView f23114c;

    public static PerformanceListEmptyListItem m24394a(Context context) {
        return PerformanceListEmptyListItem_.m24397b(context);
    }

    public PerformanceListEmptyListItem(Context context) {
        super(context);
    }

    public PerformanceListEmptyListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void m24396a(int i, boolean z, String str, OnClickListener onClickListener) {
        if (z) {
            switch (i) {
                case 0:
                    this.f23113b.setText(C1947R.string.perf_list_item_no_recordings_text);
                    this.f23114c.setText(C1947R.string.perf_list_item_no_performances_button_text);
                    break;
                case 1:
                    this.f23113b.setText(C1947R.string.perf_list_item_no_open_calls_text);
                    this.f23114c.setText(C1947R.string.perf_list_item_no_performances_button_text);
                    break;
                case 3:
                    this.f23113b.setText(C1947R.string.profile_empty_favorite_mine);
                    this.f23114c.setText(C1947R.string.perf_list_item_no_favorite_button_text);
                    break;
                default:
                    this.f23113b.setText(C1947R.string.perf_list_item_no_arrangements_text);
                    this.f23114c.setText(C1947R.string.perf_list_item_no_arrangements_button_text);
                    break;
            }
            this.f23114c.setVisibility(0);
            this.f23114c.setOnClickListener(onClickListener);
        } else {
            int i2;
            switch (i) {
                case 0:
                    i2 = C1947R.string.perf_list_item_no_recordings_other_user_text;
                    break;
                case 1:
                    i2 = C1947R.string.perf_list_item_no_open_calls_other_user_text;
                    break;
                case 3:
                    i2 = C1947R.string.profile_empty_favorite_other;
                    break;
                default:
                    i2 = C1947R.string.perf_list_item_no_arrangements_other_user_text;
                    break;
            }
            this.f23113b.setText(MessageFormat.format(this.f23113b.getContext().getString(i2), new Object[]{str}));
            this.f23114c.setVisibility(8);
        }
        switch (i) {
            case 0:
                this.f23112a.setImageResource(C1947R.drawable.icn_sing_empty_screen);
                return;
            case 1:
                this.f23112a.setImageResource(C1947R.drawable.icn_opencall_empty_screen);
                return;
            case 3:
                this.f23112a.setImageResource(C1947R.drawable.icn_empty_favorites);
                return;
            default:
                this.f23112a.setImageResource(C1947R.drawable.icn_cccp_empty_screen);
                return;
        }
    }

    public void m24395a() {
        this.f23113b.setText(C1947R.string.perf_list_item_no_open_calls_text);
        this.f23112a.setImageResource(C1947R.drawable.icn_opencall_empty_screen);
        this.f23114c.setVisibility(8);
    }

    public void setModeEmptyNotifications(OnClickListener onClickListener) {
        this.f23113b.setText(C1947R.string.news_empty_screen_text);
        this.f23112a.setImageResource(C1947R.drawable.icn_sing_empty_screen);
        this.f23114c.setVisibility(0);
        this.f23114c.setOnClickListener(onClickListener);
    }
}
