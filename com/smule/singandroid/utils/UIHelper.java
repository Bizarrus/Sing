package com.smule.singandroid.utils;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.singandroid.C1947R;

public class UIHelper {
    public static void m26198a(Context context, long j, TextView textView) {
        if (j == UserManager.a().f()) {
            textView.setVisibility(8);
        } else if (FollowManager.m18204a().m18222a(j)) {
            textView.setText(context.getString(C1947R.string.core_following));
            textView.setActivated(true);
            textView.setVisibility(0);
        } else {
            textView.setText(context.getString(C1947R.string.core_follow));
            textView.setActivated(false);
            textView.setVisibility(0);
        }
    }

    public static void m26197a(Context context, long j, ImageButton imageButton) {
        if (j == UserManager.a().f()) {
            imageButton.setVisibility(4);
        } else if (FollowManager.m18204a().m18222a(j)) {
            imageButton.setImageResource(C1947R.drawable.icn_following);
            imageButton.setVisibility(0);
        } else {
            imageButton.setImageResource(C1947R.drawable.icn_follow);
            imageButton.setVisibility(0);
        }
    }

    public static void m26199a(ProgressBar progressBar, int i) {
        ((LayerDrawable) progressBar.getProgressDrawable()).findDrawableByLayerId(16908301).setColorFilter(i, Mode.SRC_IN);
    }
}
