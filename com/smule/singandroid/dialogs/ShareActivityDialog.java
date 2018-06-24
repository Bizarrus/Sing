/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  org.androidannotations.annotations.EActivity
 */
package com.smule.singandroid.dialogs;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.ShareActivity;
import com.smule.singandroid.dialogs.ShareActivityDialog_;
import org.androidannotations.annotations.EActivity;

@SuppressLint(value={"Registered"})
@EActivity
public class ShareActivityDialog
extends ShareActivity {
    public static Intent a(Context context, ArrangementVersionLite arrangementVersionLite) {
        return ShareActivityDialog.a(context, null, arrangementVersionLite);
    }

    public static Intent a(Context context, PerformanceV2 performanceV2) {
        return ShareActivityDialog.a(context, performanceV2, null);
    }

    public static Intent a(Context context, PerformanceV2 performanceV2, ArrangementVersionLite arrangementVersionLite) {
        performanceV2 = ShareActivity.a(context, performanceV2, null, arrangementVersionLite, -1, Analytics.i);
        performanceV2.setComponent(new ComponentName(context, ShareActivityDialog_.class));
        return performanceV2;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setFinishOnTouchOutside(true);
    }
}

