package com.smule.singandroid.hashtag;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchExecuteContext;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Analytics.SearchResultClkValue;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$AccountIconResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconResponse;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.fragments.SearchByTagFragment;
import com.twitter.Extractor;
import com.twitter.Extractor.Entity;
import com.twitter.Extractor.Entity.Type;
import java.util.ArrayList;
import java.util.List;

public class Hashtag extends ClickableSpan {
    private MediaPlayingActivity f22696a;
    private boolean f22697b;
    private boolean f22698c = false;
    private Typeface f22699d;

    public interface HashtagCallback {
        void mo6863a();
    }

    public Hashtag(MediaPlayingActivity mediaPlayingActivity, boolean z, String str) {
        this.f22696a = mediaPlayingActivity;
        this.f22697b = z;
        m24193a(this.f22696a, str);
    }

    private void m24193a(Context context, String str) {
        this.f22699d = Typeface.createFromAsset(context.getAssets(), str);
    }

    public void updateDrawState(TextPaint textPaint) {
        if (this.f22699d != null) {
            textPaint.setTypeface(this.f22699d);
        }
    }

    public void onClick(View view) {
        if (!this.f22698c) {
            this.f22698c = true;
            Spanned spanned = (Spanned) ((TextView) view).getText();
            String charSequence = spanned.subSequence(spanned.getSpanStart(this) + 1, spanned.getSpanEnd(this)).toString();
            if (this.f22697b) {
                m24197a(charSequence);
            } else {
                m24200b(charSequence);
            }
        }
    }

    public static void m24194a(MediaPlayingActivity mediaPlayingActivity, SpannableString spannableString) {
        m24195a(mediaPlayingActivity, spannableString, "fonts/ProximaNova-Semibold.ttf");
    }

    public static void m24195a(MediaPlayingActivity mediaPlayingActivity, SpannableString spannableString, String str) {
        Extractor extractor = new Extractor();
        List<Entity> arrayList = new ArrayList();
        String spannableString2 = spannableString.toString();
        arrayList.addAll(extractor.m26626c(spannableString2));
        arrayList.addAll(extractor.m26623a(spannableString2));
        for (Entity entity : arrayList) {
            spannableString.setSpan(new Hashtag(mediaPlayingActivity, entity.m26620c() == Type.f25520b, str), entity.m26618a().intValue(), entity.m26619b().intValue(), 0);
        }
    }

    private void m24197a(String str) {
        this.f22698c = false;
        final SearchByTagFragment a = SearchByTagFragment.m24145a(str, false);
        if (this.f22696a != null) {
            this.f22696a.a(false, true, new HashtagCallback(this) {
                final /* synthetic */ Hashtag f22687b;

                public void mo6863a() {
                    if (this.f22687b.f22696a != null) {
                        this.f22687b.f22696a.a(a, a.m24169a());
                    }
                }
            });
        }
    }

    private void m24198a(String str, long j, long j2) {
        Analytics.m17853a(SearchTarget.DIRECT_USER, SearchExecuteContext.MENTION, 1, "@" + str, "@" + str, j, null);
        Analytics.m17854a(SearchTarget.DIRECT_USER, SearchResultClkContext.REGULAR, SearchResultClkValue.DIRECT, null, null, Integer.valueOf(0), Long.valueOf(j2), null, null, 1, 0);
    }

    private void m24200b(final String str) {
        final long currentTimeMillis = System.currentTimeMillis();
        UserManager.a().b(str, new UserManager$AccountIconResponseCallback(this) {
            final /* synthetic */ Hashtag f22695c;

            public void handleResponse(final AccountIconResponse accountIconResponse) {
                this.f22695c.f22698c = false;
                final long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                if (accountIconResponse.a == null || !accountIconResponse.a.c()) {
                    Analytics.m17853a(SearchTarget.DIRECT_USER, SearchExecuteContext.MENTION, 0, "@" + str, "@" + str, currentTimeMillis, null);
                    final SearchByTagFragment a = SearchByTagFragment.m24145a(str, true);
                    if (this.f22695c.f22696a != null) {
                        this.f22695c.f22696a.a(false, true, new HashtagCallback(this) {
                            final /* synthetic */ C46042 f22692b;

                            public void mo6863a() {
                                if (this.f22692b.f22695c.f22696a != null) {
                                    this.f22692b.f22695c.f22696a.a(a, a.m24169a());
                                }
                            }
                        });
                    }
                } else if (this.f22695c.f22696a != null && accountIconResponse.mAccount != null) {
                    this.f22695c.f22696a.a(false, true, new HashtagCallback(this) {
                        final /* synthetic */ C46042 f22690c;

                        public void mo6863a() {
                            if (this.f22690c.f22695c.f22696a != null) {
                                this.f22690c.f22695c.m24198a(str, currentTimeMillis, accountIconResponse.mAccount.accountId);
                                BaseFragment a = ProfileFragment.m21037a(accountIconResponse.mAccount, true);
                                this.f22690c.f22695c.f22696a.a(a, a.mo6514z());
                            }
                        }
                    });
                }
            }
        });
    }
}
