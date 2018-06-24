package com.smule.singandroid.dialogs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.singandroid.ShareActivity;
import org.androidannotations.annotations.EActivity;

@EActivity
public class ShareActivityDialog extends ShareActivity {
    public static Intent m23688a(Context context, PerformanceV2 performanceV2) {
        return m23689a(context, performanceV2, null, null);
    }

    public static Intent m23687a(Context context, ArrangementVersionLite arrangementVersionLite) {
        return m23689a(context, null, arrangementVersionLite, null);
    }

    public static Intent m23689a(Context context, PerformanceV2 performanceV2, ArrangementVersionLite arrangementVersionLite, SongV2 songV2) {
        Intent a = ShareActivity.m21438a(context, performanceV2, null, arrangementVersionLite, songV2, Long.valueOf(-1), SearchClkContext.SHAREMESSAGE);
        a.setComponent(new ComponentName(context, ShareActivityDialog_.class));
        return a;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setFinishOnTouchOutside(true);
    }
}
