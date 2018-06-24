package com.smule.singandroid.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.ui.OverlayWithHoleImageView;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.utils.LayoutUtils;

public class ChatTooltipDialog extends SmuleDialog {
    protected RelativeLayout f22148a = ((RelativeLayout) this.f22153f.findViewById(C1947R.id.mChatTooltipOverlay));
    protected TextView f22149b = ((TextView) this.f22153f.findViewById(C1947R.id.chat_tooltip_text));
    protected RelativeLayout f22150c = ((RelativeLayout) this.f22153f.findViewById(C1947R.id.mChatTooltipBubble));
    protected View f22151d = this.f22153f.findViewById(C1947R.id.message_center_tooltip_triangle);
    protected OverlayWithHoleImageView f22152e = ((OverlayWithHoleImageView) this.f22153f.findViewById(C1947R.id.mChatTooltipBackground));
    private ViewGroup f22153f;
    private int f22154g;
    private int f22155h;
    private OnClickListener f22156i;
    private OnClickListener f22157j;

    public ChatTooltipDialog(BaseActivity baseActivity, int i, int i2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        super(baseActivity, 16973840, false);
        this.f22153f = (ViewGroup) LayoutInflater.from(baseActivity).inflate(C1947R.layout.chat_tooltip_layout, null, false);
        setContentView(this.f22153f);
        m23583a(i, i2, onClickListener, onClickListener2);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void mo6374a() {
        int a = LayoutUtils.m25843a(24, getContext());
        this.f22152e.m18875a(this.f22156i, this.f22157j);
        this.f22152e.m18874a(this.f22154g, this.f22155h, a);
        this.f22151d.setX((float) (this.f22154g - (this.f22151d.getWidth() / 2)));
    }

    public void m23582a(int i) {
        this.f22149b.setText(i);
    }

    public void m23583a(int i, int i2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.f22154g = i;
        this.f22155h = i2;
        this.f22156i = onClickListener;
        this.f22157j = onClickListener2;
        mo6374a();
    }
}
