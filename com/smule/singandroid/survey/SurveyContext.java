package com.smule.singandroid.survey;

import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.PostSingBundle;
import java.util.Arrays;
import java.util.List;

public class SurveyContext {
    public PostSingBundle f24287a;
    public EntryPoint f24288b;
    public boolean f24289c;
    public boolean f24290d;
    public SongbookEntry f24291e;
    public PerformanceV2 f24292f;
    public String f24293g;

    public enum EntryPoint {
        VIDEO_REC_CANCEL("rec_cancel", Boolean.valueOf(true), Arrays.asList(new AVQualityReason[]{AVQualityReason.MUSIC_PLAYBACK_OFF, AVQualityReason.VIDEO_QUALITY_BAD, AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.OTHER})),
        VIDEO_REVIEW_CANCEL("review_cancel", Boolean.valueOf(true), Arrays.asList(new AVQualityReason[]{AVQualityReason.MUSIC_PLAYBACK_OFF, AVQualityReason.VIDEO_QUALITY_BAD, AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.SINGING_OUT_OF_SYNC, AVQualityReason.AUDIO_VIDEO_OUT_OF_SYNC, AVQualityReason.UNABLE_TO_ADJUST_MIC_VOLUME, AVQualityReason.OTHER})),
        VIDEO_UPLOAD("upload", Boolean.valueOf(true), Arrays.asList(new AVQualityReason[]{AVQualityReason.VIDEO_QUALITY_BAD, AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.SINGING_OUT_OF_SYNC, AVQualityReason.AUDIO_VIDEO_OUT_OF_SYNC, AVQualityReason.UNABLE_TO_ADJUST_MIC_VOLUME, AVQualityReason.OTHER})),
        AUDIO_REC_CANCEL("rec_cancel", Boolean.valueOf(false), Arrays.asList(new AVQualityReason[]{AVQualityReason.MUSIC_PLAYBACK_OFF, AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.OTHER})),
        AUDIO_REVIEW_CANCEL("review_cancel", Boolean.valueOf(false), Arrays.asList(new AVQualityReason[]{AVQualityReason.MUSIC_PLAYBACK_OFF, AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.SINGING_OUT_OF_SYNC, AVQualityReason.UNABLE_TO_ADJUST_MIC_VOLUME, AVQualityReason.OTHER})),
        AUDIO_UPLOAD("upload", Boolean.valueOf(false), Arrays.asList(new AVQualityReason[]{AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.SINGING_OUT_OF_SYNC, AVQualityReason.UNABLE_TO_ADJUST_MIC_VOLUME, AVQualityReason.OTHER})),
        VIDEO_POST_LISTEN("post_listen", Boolean.valueOf(true), Arrays.asList(new AVQualityReason[]{AVQualityReason.VIDEO_QUALITY_BAD, AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.SINGING_OUT_OF_SYNC, AVQualityReason.AUDIO_VIDEO_OUT_OF_SYNC, AVQualityReason.UPLOAD_DOES_NOT_MATCH, AVQualityReason.OTHER})),
        AUDIO_POST_LISTEN("post_listen", Boolean.valueOf(false), Arrays.asList(new AVQualityReason[]{AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.SINGING_OUT_OF_SYNC, AVQualityReason.UPLOAD_DOES_NOT_MATCH, AVQualityReason.OTHER})),
        SETTINGS("settings", null, Arrays.asList(new AVQualityReason[]{AVQualityReason.VIDEO_QUALITY_BAD, AVQualityReason.RECORDING_SOUNDED_BROKEN, AVQualityReason.SINGING_TOO_LOUD_OR_SOFT, AVQualityReason.SINGING_OUT_OF_SYNC, AVQualityReason.AUDIO_VIDEO_OUT_OF_SYNC, AVQualityReason.OTHER}));
        
        private final String f24284j;
        private final Boolean f24285k;
        private final List<AVQualityReason> f24286l;

        private EntryPoint(String str, Boolean bool, List<AVQualityReason> list) {
            this.f24284j = str;
            this.f24285k = bool;
            this.f24286l = list;
        }

        public String m25500a() {
            return this.f24284j;
        }

        public Boolean m25501b() {
            return this.f24285k;
        }

        public List<AVQualityReason> m25502c() {
            return this.f24286l;
        }

        public boolean m25503d() {
            return Arrays.asList(new EntryPoint[]{VIDEO_REC_CANCEL, VIDEO_REVIEW_CANCEL, VIDEO_UPLOAD, AUDIO_REC_CANCEL, AUDIO_REVIEW_CANCEL, AUDIO_UPLOAD}).contains(this);
        }

        public boolean m25504e() {
            return Arrays.asList(new EntryPoint[]{AUDIO_POST_LISTEN, VIDEO_POST_LISTEN}).contains(this);
        }

        public boolean m25505f() {
            return SETTINGS.equals(this);
        }

        public boolean m25506g() {
            return Arrays.asList(new EntryPoint[]{VIDEO_REC_CANCEL, VIDEO_REVIEW_CANCEL, AUDIO_REC_CANCEL, AUDIO_REVIEW_CANCEL}).contains(this);
        }
    }

    public ArrangementVersionLite m25507a() {
        if (this.f24291e instanceof ArrangementVersionLiteEntry) {
            return ((ArrangementVersionLiteEntry) this.f24291e).m18774a();
        }
        return null;
    }
}
