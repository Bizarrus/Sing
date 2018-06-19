package com.smule.singandroid;

import android.media.AudioManager.OnAudioFocusChangeListener;
import com.smule.android.logging.Log;

class BaseActivity$AudioManagerFocusListener implements OnAudioFocusChangeListener {
    final /* synthetic */ BaseActivity f18464a;

    private BaseActivity$AudioManagerFocusListener(BaseActivity baseActivity) {
        this.f18464a = baseActivity;
    }

    public void onAudioFocusChange(int i) {
        switch (i) {
            case -3:
                Log.b(BaseActivity.a, "onAudioFocusChange - AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                break;
            case -2:
                Log.b(BaseActivity.a, "onAudioFocusChange - AUDIOFOCUS_LOSS_TRANSIENT");
                this.f18464a.k();
                break;
            case -1:
                Log.b(BaseActivity.a, "onAudioFocusChange - AUDIOFOCUS_LOSS");
                this.f18464a.k();
                break;
            case 1:
                Log.b(BaseActivity.a, "onAudioFocusChange - AUDIOFOCUS_GAIN");
                this.f18464a.a(BaseActivity.a(this.f18464a) == -3);
                break;
            default:
                Log.b(BaseActivity.a, "onAudioFocusChange - " + i);
                break;
        }
        BaseActivity.a(this.f18464a, i);
    }
}
