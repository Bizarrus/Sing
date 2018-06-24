package com.smule.singandroid.dialogs;

import android.content.Context;
import android.view.View.OnClickListener;
import com.smule.singandroid.C1947R;

public class AdLoadingDialog extends SmuleDialog {
    public AdLoadingDialog(Context context, OnClickListener onClickListener) {
        super(context, C1947R.style.Sing.Dialog.BusyScreen);
        setContentView(C1947R.layout.ad_loading_dialog);
        findViewById(C1947R.id.cancel_loading_button).setOnClickListener(onClickListener);
    }
}
