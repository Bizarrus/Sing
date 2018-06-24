package com.smule.singandroid.pre_sing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Analytics.PageType;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.customviews.CheckmarkAnimation;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class PreSingHeadsetReminderFragment extends PreSingBaseFragment {
    private static final String f23672B = PreSingHeadsetReminderFragment.class.getName();
    public boolean f23673A = false;
    @ViewById
    ImageView f23674t;
    @ViewById
    TextView f23675u;
    @ViewById
    TextView f23676v;
    @ViewById
    CheckmarkAnimation f23677w;
    @ViewById
    Button f23678x;
    HeadSetBroadCastReceiver f23679y;
    public boolean f23680z = false;

    public class HeadSetBroadCastReceiver extends BroadcastReceiver {
        final /* synthetic */ PreSingHeadsetReminderFragment f23671a;

        public HeadSetBroadCastReceiver(PreSingHeadsetReminderFragment preSingHeadsetReminderFragment) {
            this.f23671a = preSingHeadsetReminderFragment;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.compareTo("android.intent.action.HEADSET_PLUG") == 0) {
                Log.b(PreSingHeadsetReminderFragment.f23672B, "onReceive - ACTION_HEADSET_PLUG");
                final int intExtra = intent.getIntExtra("state", 0);
                final int intExtra2 = intent.getIntExtra("microphone", -1);
                this.f23671a.m19838a(new Runnable(this) {
                    final /* synthetic */ HeadSetBroadCastReceiver f23670c;

                    public void run() {
                        boolean z = true;
                        if (this.f23670c.f23671a.isAdded()) {
                            PreSingHeadsetReminderFragment preSingHeadsetReminderFragment = this.f23670c.f23671a;
                            boolean z2 = intExtra == 1;
                            if (intExtra2 != 1) {
                                z = false;
                            }
                            preSingHeadsetReminderFragment.m24924a(z2, z);
                        }
                    }
                });
            }
        }
    }

    public String mo6383s() {
        return f23672B;
    }

    static boolean m24922a() {
        return !SingApplication.g().e();
    }

    public boolean mo6446i() {
        return false;
    }

    public boolean mo6400c() {
        return true;
    }

    public void onResume() {
        super.onResume();
        m24924a(SingApplication.g().e(), false);
        this.f23679y = new HeadSetBroadCastReceiver(this);
        getActivity().registerReceiver(this.f23679y, new IntentFilter("android.intent.action.HEADSET_PLUG"));
    }

    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(this.f23679y);
    }

    protected void m24924a(boolean z, boolean z2) {
        this.f23680z = z;
        boolean z3 = z && z2;
        this.f23673A = z3;
        if (z) {
            this.f23677w.m23151a();
        } else {
            this.f23677w.m23153b();
        }
        this.f23678x.setTextColor(getResources().getColor(z ? C1947R.color.button_text_inverse : C1947R.color.body_text_grey));
        this.f23675u.setText(z ? C1947R.string.headphone_reminder_good_title : C1947R.string.headphone_reminder_bad_title);
        this.f23676v.setText(z ? C1947R.string.headphone_reminder_good_description : C1947R.string.headphone_reminder_bad_description);
        this.f23678x.setText(z ? C1947R.string.core_next : C1947R.string.core_skip);
    }

    @Click
    protected void m24923R() {
        SingAnalytics.m26067a(HeadphonesType.m17502a(this.f23680z, this.f23673A), this.i.f20149n, PageType.SCREEN);
        mo6900K();
    }
}
