package com.smule.singandroid.survey;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;

public class ArrangementRatingDialog extends SurveyRatingDialog {
    private static final String f24258e = ArrangementRatingDialog.class.getName();

    public ArrangementRatingDialog(Context context, SurveyContext surveyContext, SurveyConfig surveyConfig) {
        super(context, surveyContext, surveyConfig);
        ArrangementVersionLite a = this.c.m25507a();
        m25494a(a);
        m25495b(a);
    }

    private void m25494a(ArrangementVersionLite arrangementVersionLite) {
        ImageView imageView = (ImageView) findViewById(C1947R.id.album_art);
        String str = arrangementVersionLite.songId;
        ImageUtils.a(SongbookEntryUtils.m26164a(this.c.f24291e, str != null ? StoreManager.m18378a().m18416a(str) : null), imageView, C1947R.drawable.icn_default_album_small);
    }

    private void m25495b(ArrangementVersionLite arrangementVersionLite) {
        AccountIcon accountIcon = arrangementVersionLite.accountIcon;
        if (accountIcon == null || accountIcon.handle == null || accountIcon.handle.length() <= 0) {
            MagicCrittercism.a(new Exception("Rating dialog accountIcon issue: " + arrangementVersionLite));
            return;
        }
        StyleReplacer styleReplacer = new StyleReplacer(getContext().getString(C1947R.string.performance_rating_arranger), (TextView) findViewById(C1947R.id.arranger_text), null);
        styleReplacer.m26181a("{0}", accountIcon.handle, (float) getContext().getResources().getDimensionPixelSize(C1947R.dimen.font_body_text), (int) C1947R.color.sub_heading_dark, TypefaceUtils.m26188a());
        styleReplacer.m26174a();
    }
}
