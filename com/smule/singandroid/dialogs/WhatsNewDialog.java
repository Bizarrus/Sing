package com.smule.singandroid.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.core.MagicNetwork;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ScrollViewWithMaxHeight;

public class WhatsNewDialog extends SmuleDialog {
    protected Button f22334a = ((Button) this.f22338e.findViewById(C1947R.id.yesButton));
    protected TextView f22335b = ((TextView) this.f22338e.findViewById(C1947R.id.whats_new_header_text));
    protected LinearLayout f22336c = ((LinearLayout) this.f22338e.findViewById(C1947R.id.whats_new_content));
    protected ScrollViewWithMaxHeight f22337d = ((ScrollViewWithMaxHeight) this.f22338e.findViewById(C1947R.id.whats_new_scroll_view));
    private ViewGroup f22338e;
    private Runnable f22339f;

    class C45221 implements OnClickListener {
        final /* synthetic */ WhatsNewDialog f22333a;

        C45221(WhatsNewDialog whatsNewDialog) {
            this.f22333a = whatsNewDialog;
        }

        public void onClick(View view) {
            this.f22333a.mo6374a();
        }
    }

    public WhatsNewDialog(BaseActivity baseActivity) {
        super(baseActivity, C1947R.style.WhatsNewDialog, true);
        this.f22338e = (ViewGroup) LayoutInflater.from(baseActivity).inflate(C1947R.layout.whats_new_dialog_content, null, false);
        setContentView(this.f22338e);
        this.f22337d.setMaxHeight(getContext().getResources().getDimensionPixelSize(C1947R.dimen.whats_new_content_max_height));
        this.f22334a.setOnClickListener(new C45221(this));
        this.f22335b.setText(baseActivity.getString(C1947R.string.whats_new_header, new Object[]{MagicNetwork.d().getAppVersion()}));
        String[] stringArray = baseActivity.getResources().getStringArray(C1947R.array.whats_new_titles);
        String[] stringArray2 = baseActivity.getResources().getStringArray(C1947R.array.whats_new_descs);
        if (stringArray.length != stringArray2.length) {
            throw new RuntimeException("What's New titles and descriptions numbers are not equal!");
        }
        for (int i = 0; i < stringArray.length; i++) {
            LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(C1947R.layout.whats_new_content_template, null);
            TextView textView = (TextView) linearLayout.findViewById(C1947R.id.whats_new_content_desc);
            ((TextView) linearLayout.findViewById(C1947R.id.whats_new_content_title)).setText(stringArray[i]);
            textView.setText(stringArray2[i]);
            this.f22336c.addView(linearLayout);
        }
    }

    public void m23725a(Runnable runnable) {
        this.f22339f = runnable;
    }

    protected void mo6374a() {
        if (this.f22339f != null) {
            this.f22339f.run();
        }
        dismiss();
    }
}
