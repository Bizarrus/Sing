package com.smule.singandroid.dialogs;

import android.content.Context;
import android.widget.TextView;
import com.smule.singandroid.C1947R;

public class TextAlertDialog extends CustomAlertDialog {
    private static final String f18463c = TextAlertDialog.class.getName();

    public TextAlertDialog(Context context, String str) {
        this(context, null, str);
    }

    public TextAlertDialog(Context context, int i, int i2) {
        this(context, context.getResources().getString(i), context.getResources().getString(i2));
    }

    public TextAlertDialog(Context context, String str, String str2) {
        this(context, str, (CharSequence) str2, true, true);
    }

    public TextAlertDialog(Context context, int i, int i2, boolean z, boolean z2) {
        this(context, context.getResources().getString(i), context.getResources().getString(i2), z, z2);
    }

    public TextAlertDialog(Context context, String str, CharSequence charSequence, boolean z, boolean z2) {
        super(context, C1947R.layout.standard_dialog_contents, z, z2, true);
        TextView textView = (TextView) findViewById(C1947R.id.customTextView);
        m19805a(str);
        if (charSequence != null) {
            textView.setText(charSequence);
            textView.setVisibility(0);
            return;
        }
        textView.setVisibility(8);
    }
}
