package com.smule.singandroid.survey;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.DebugManager;
import com.smule.android.network.managers.DebugManager.PreuploadResponse;
import com.smule.android.network.managers.DebugManager.PreuploadResponseCallback;
import com.smule.android.network.managers.DebugManager.UploadResponseCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MagicPreferences;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.VocalEffect;
import com.smule.singandroid.survey.SurveyContext.EntryPoint;
import com.smule.singandroid.utils.PerformanceCreateUtil;
import com.smule.singandroid.utils.PerformanceCreateUtil.ResourceCompressionListener;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.video.VideoFilterManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AVQualityConfig implements SurveyConfig {
    private static final String f24219d = AVQualityConfig.class.getName();
    protected Activity f24220a;
    protected SurveyContext f24221b;
    protected List<ReasonInterface> f24222c = new ArrayList(this.f24221b.f24288b.m25502c());

    public AVQualityConfig(@NonNull Activity activity, @NonNull SurveyContext surveyContext) {
        this.f24220a = activity;
        this.f24221b = surveyContext;
        boolean remove = this.f24222c.remove(AVQualityReason.OTHER);
        Collections.shuffle(this.f24222c);
        if (remove) {
            this.f24222c.add(AVQualityReason.OTHER);
        }
    }

    public String mo6937a() {
        return this.f24220a.getResources().getString(C1947R.string.av_survey_how_was_quality);
    }

    public RatingInterface mo6939b() {
        return AVQualityRating.GOOD;
    }

    public RatingInterface mo6941c() {
        return AVQualityRating.BAD;
    }

    public boolean mo6942d() {
        return this.f24221b.f24288b.m25503d() || this.f24221b.f24288b.m25504e();
    }

    public int mo6943e() {
        return C1947R.layout.av_quality_ratings_dialog_contents;
    }

    public SurveyRatingDialog mo6944f() {
        return new SurveyRatingDialog(this.f24220a, this.f24221b, this);
    }

    public String mo6945g() {
        return this.f24220a.getResources().getString(C1947R.string.av_survey_what_happened);
    }

    public String mo6946h() {
        return this.f24220a.getResources().getString(this.f24221b.f24288b.m25505f() ? C1947R.string.core_cancel : C1947R.string.core_skip);
    }

    public List<ReasonInterface> mo6947i() {
        return this.f24222c;
    }

    public SurveyReasonDialog mo6936a(SurveyRatingDialog surveyRatingDialog) {
        return new SurveyReasonDialog(this.f24220a, this, surveyRatingDialog);
    }

    public Runnable mo6940b(@NonNull final SurveyRatingDialog surveyRatingDialog) {
        return new Runnable(this) {
            final /* synthetic */ AVQualityConfig f24208b;

            public void run() {
                surveyRatingDialog.m25492h();
            }
        };
    }

    public String mo6948j() {
        return this.f24220a.getResources().getString(C1947R.string.av_survey_thanks);
    }

    public void mo6938a(@NonNull RatingInterface ratingInterface, @Nullable ReasonInterface reasonInterface) {
        PostSingBundle postSingBundle = this.f24221b.f24287a;
        final SingBundle singBundle = postSingBundle == null ? null : postSingBundle.f19316b;
        final String e = singBundle == null ? null : singBundle.m21655e();
        if (this.f24221b.f24288b.m25506g() && AVQualityReason.RECORDING_SOUNDED_BROKEN == reasonInterface && e != null) {
            final RatingInterface ratingInterface2 = ratingInterface;
            final ReasonInterface reasonInterface2 = reasonInterface;
            DebugManager.m18144a().m18148a("DEBUG_AUDIO", new PreuploadResponseCallback(this) {
                final /* synthetic */ AVQualityConfig f24213e;

                public void handleResponse(PreuploadResponse preuploadResponse) {
                    if (preuploadResponse.a()) {
                        this.f24213e.m25434a(singBundle, ratingInterface2, reasonInterface2, Long.valueOf(preuploadResponse.resourceId));
                        this.f24213e.m25437a(e, preuploadResponse.resourceId);
                        return;
                    }
                    Log.e(AVQualityConfig.f24219d, "Error calling DebugAPI.preupload(); response code" + preuploadResponse.a.b);
                    this.f24213e.m25434a(singBundle, ratingInterface2, reasonInterface2, null);
                }
            });
            return;
        }
        m25434a(singBundle, ratingInterface, reasonInterface, null);
    }

    private void m25437a(final String str, final long j) {
        ResourceCompressionListener c49143 = new ResourceCompressionListener(this) {
            final /* synthetic */ AVQualityConfig f24216c;

            public void mo6387a() {
                Log.b(AVQualityConfig.f24219d, "Starting compression of audio file at " + str + " for resourceID = " + j);
            }

            public void mo6388a(String str) {
                Log.b(AVQualityConfig.f24219d, "Compression done for resourceID = " + j + "; compressedFilename = " + str);
                this.f24216c.m25441b(str, j);
            }

            public void mo6389b() {
                Log.e(AVQualityConfig.f24219d, "Compression of audio file failed for resourceID = " + j);
            }
        };
        Bundle bundle = new Bundle();
        bundle.putString("RECORDING_FILE_EXTRA_KEY", str);
        new PerformanceCreateUtil(Long.valueOf(j), null).m25930a(this.f24220a, str, bundle, c49143);
    }

    private void m25441b(String str, final long j) {
        DebugManager.m18144a().m18147a(new File(str), "DEBUG_AUDIO", j, new UploadResponseCallback(this) {
            final /* synthetic */ AVQualityConfig f24218b;

            public void handleResponse(NetworkResponse networkResponse) {
                if (networkResponse.c()) {
                    Log.b(AVQualityConfig.f24219d, "Successfully uploaded audio debug file with resourceId = " + j);
                } else {
                    Log.e(AVQualityConfig.f24219d, "Error uploading audio debug file for resourceId = " + j);
                }
            }
        });
    }

    private void m25434a(SingBundle singBundle, RatingInterface ratingInterface, ReasonInterface reasonInterface, Long l) {
        SingAnalytics.m26117a(m25432a(l), m25446l(), m25439b(ratingInterface, reasonInterface), m25431a(singBundle), m25438b(singBundle), m25442c(singBundle), m25443d(singBundle), m25444e(singBundle), m25447m());
    }

    private String m25432a(Long l) {
        if (l != null) {
            return String.valueOf(l);
        }
        if (this.f24221b.f24292f != null) {
            return this.f24221b.f24292f.performanceKey;
        }
        return null;
    }

    private String m25446l() {
        return this.f24221b.f24288b.m25500a();
    }

    private Integer m25439b(RatingInterface ratingInterface, ReasonInterface reasonInterface) {
        if (mo6942d() && ratingInterface != mo6941c()) {
            return null;
        }
        return Integer.valueOf(reasonInterface == null ? -1 : reasonInterface.mo6950a());
    }

    private HeadphonesType m25431a(SingBundle singBundle) {
        if (this.f24221b.f24288b == EntryPoint.SETTINGS || this.f24221b.f24288b == EntryPoint.AUDIO_POST_LISTEN || this.f24221b.f24288b == EntryPoint.VIDEO_POST_LISTEN || singBundle == null) {
            return null;
        }
        return HeadphonesType.m17502a(singBundle.m21650b("USED_HEADPHONE", false), singBundle.m21650b("HEADPHONE_HAD_MIC", false));
    }

    private Boolean m25438b(SingBundle singBundle) {
        if (singBundle != null) {
            return Boolean.valueOf(singBundle.f20146k);
        }
        return null;
    }

    private Ensemble m25442c(SingBundle singBundle) {
        if (singBundle == null || singBundle.f20137b == null) {
            return null;
        }
        return singBundle.f20137b.m21631a();
    }

    private String m25443d(SingBundle singBundle) {
        String d = this.f24221b.f24292f != null ? SingAnalytics.m26140d(this.f24221b.f24292f) : (singBundle == null || singBundle.f20139d == null) ? null : SingAnalytics.m26141d(singBundle.f20139d);
        if (d == "-") {
            return null;
        }
        return d;
    }

    private String m25444e(SingBundle singBundle) {
        boolean z = true;
        String str = null;
        if (!this.f24221b.f24288b.m25503d()) {
            return null;
        }
        String b = MagicPreferences.m20312b(this.f24220a, "PREFS_LAST_SELECTED_FX", SingServerValues.m21689j());
        VocalEffect b2 = VocalEffect.m21979b(b);
        boolean z2 = b2 != null && b2.m21987e();
        if (this.f24221b.f24288b.m25501b().booleanValue()) {
            Boolean valueOf;
            String f = singBundle == null ? null : singBundle.m21657f();
            if (singBundle != null) {
                valueOf = Boolean.valueOf(singBundle.m21658g());
            }
            str = m25433a(f, valueOf);
            if (f == null || !VideoFilterManager.m26577b(f)) {
                z = false;
            }
        } else {
            z = false;
        }
        return Analytics.m17834a(z2, b, z, str);
    }

    private String m25433a(String str, Boolean bool) {
        String str2 = "unsupported";
        str2 = "airbrush";
        Object arrayList = new ArrayList();
        if (str != null && str.equals("unsupported")) {
            arrayList.add("unsupported");
        } else if (!(str == null || str.equals("unsupported"))) {
            arrayList.add(str);
        }
        if (bool != null && bool.booleanValue()) {
            arrayList.add("airbrush");
        }
        if (arrayList.isEmpty()) {
            return str;
        }
        Collections.sort(arrayList);
        return TextUtils.join(":", arrayList);
    }

    private Boolean m25447m() {
        return this.f24221b.f24288b.m25501b();
    }
}
