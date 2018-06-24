package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.singandroid.C1947R;

public class SortOptionsDialog extends SmuleDialog {
    View f22330a = findViewById(C1947R.id.background_container);
    TextView f22331b;
    TextView f22332c;

    class C45211 implements OnClickListener {
        final /* synthetic */ SortOptionsDialog f22329a;

        C45211(SortOptionsDialog sortOptionsDialog) {
            this.f22329a = sortOptionsDialog;
        }

        public void onClick(View view) {
            this.f22329a.dismiss();
        }
    }

    public SortOptionsDialog(Activity activity, int i, int i2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        super(activity, C1947R.style.MagicModal);
        setContentView(C1947R.layout.search_show_all_sort_option_dialog);
        this.f22330a.setOnClickListener(new C45211(this));
        this.f22331b = (TextView) findViewById(C1947R.id.option_first);
        this.f22332c = (TextView) findViewById(C1947R.id.option_second);
        this.f22331b.setText(i);
        this.f22332c.setText(i2);
        this.f22331b.setOnClickListener(onClickListener);
        this.f22332c.setOnClickListener(onClickListener2);
        m23723a(true);
    }

    public void m23723a(boolean z) {
        int color;
        int i = -1;
        TextView textView = this.f22331b;
        if (z) {
            color = getContext().getResources().getColor(C1947R.color.divider_line_grey);
        } else {
            color = -1;
        }
        textView.setBackgroundColor(color);
        TextView textView2 = this.f22332c;
        if (!z) {
            i = getContext().getResources().getColor(C1947R.color.divider_line_grey);
        }
        textView2.setBackgroundColor(i);
    }
}
