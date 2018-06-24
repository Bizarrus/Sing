/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.graphics.Typeface
 *  android.text.SpannableString
 *  android.text.Spanned
 *  android.text.TextPaint
 *  android.text.style.ClickableSpan
 *  android.view.View
 *  android.widget.TextView
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.twitter.Extractor
 *  com.twitter.Extractor$Entity
 *  com.twitter.Extractor$Entity$Type
 */
package com.smule.singandroid.hashtag;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.fragments.SearchByTagFragment;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.SingAnalytics;
import com.twitter.Extractor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class Hashtag
extends ClickableSpan {
    private MediaPlayingActivity a;
    private boolean b;
    private boolean c = false;
    private Typeface d;

    public Hashtag(MediaPlayingActivity mediaPlayingActivity, boolean bl, String string2) {
        this.a = mediaPlayingActivity;
        this.b = bl;
        this.a((Context)this.a, string2);
    }

    private void a(Context context, String string2) {
        this.d = Typeface.createFromAsset((AssetManager)context.getAssets(), (String)string2);
    }

    public static void a(MediaPlayingActivity mediaPlayingActivity, SpannableString spannableString) {
        Hashtag.a(mediaPlayingActivity, spannableString, "fonts/ProximaNova-Semibold.ttf");
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(MediaPlayingActivity mediaPlayingActivity, SpannableString spannableString, String string2) {
        Object object = new Extractor();
        Extractor.Entity entity = new Extractor.Entity();
        String string3 = spannableString.toString();
        entity.addAll(object.c(string3));
        entity.addAll(object.a(string3));
        object = entity.iterator();
        while (object.hasNext()) {
            entity = (Extractor.Entity)object.next();
            boolean bl = entity.c() == Extractor.Entity.Type.b;
            spannableString.setSpan((Object)new Hashtag(mediaPlayingActivity, bl, string2), entity.a().intValue(), entity.b().intValue(), 0);
        }
        return;
    }

    private void a(String object) {
        this.c = false;
        object = SearchByTagFragment.a((String)object, false);
        if (this.a != null) {
            this.a.a(false, true, new HashtagCallback((SearchByTagFragment)((Object)object)){
                final /* synthetic */ SearchByTagFragment a;
                {
                    this.a = searchByTagFragment;
                }

                @Override
                public void a() {
                    if (Hashtag.this.a != null) {
                        Hashtag.this.a.a((BaseFragment)((Object)this.a), this.a.a());
                    }
                }
            });
        }
    }

    private void a(String string2, long l, long l2) {
        com.smule.android.logging.Analytics.a(Analytics.k, Analytics.h, 1, "@" + string2, "@" + string2, l, null);
        com.smule.android.logging.Analytics.a(Analytics.k, Analytics.b, Analytics.d, null, null, 0, l2, null, null, 1, 0);
    }

    private void b(final String string2) {
        final long l = System.currentTimeMillis();
        com.smule.android.network.managers.UserManager.a().b(string2, new UserManager(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(UserManager.AccountIconResponse object) {
                Hashtag.this.c = false;
                final long l2 = System.currentTimeMillis() - l;
                if (object.a != null && object.a.c()) {
                    if (Hashtag.this.a == null || object.mAccount == null) return;
                    {
                        Hashtag.this.a.a(false, true, new HashtagCallback((UserManager.AccountIconResponse)object){
                            final /* synthetic */ UserManager.AccountIconResponse b;
                            {
                                this.b = accountIconResponse;
                            }

                            @Override
                            public void a() {
                                if (Hashtag.this.a != null) {
                                    Hashtag.this.a(string2, l2, this.b.mAccount.accountId);
                                    ProfileFragment profileFragment = ProfileFragment.a(this.b.mAccount, true);
                                    Hashtag.this.a.a(profileFragment, profileFragment.t());
                                }
                            }
                        });
                        return;
                    }
                } else {
                    SingAnalytics.a(Analytics.k, Analytics.h, (int)0, (String)("@" + string2), (String)("@" + string2), (long)l2, (Integer)null);
                    object = SearchByTagFragment.a(string2, true);
                    if (Hashtag.this.a == null) return;
                    {
                        Hashtag.this.a.a(false, true, new HashtagCallback((SearchByTagFragment)((Object)object)){
                            final /* synthetic */ SearchByTagFragment a;
                            {
                                this.a = searchByTagFragment;
                            }

                            @Override
                            public void a() {
                                if (Hashtag.this.a != null) {
                                    Hashtag.this.a.a((BaseFragment)((Object)this.a), this.a.a());
                                }
                            }
                        });
                        return;
                    }
                }
            }

        });
    }

    public void onClick(View object) {
        if (this.c) {
            return;
        }
        this.c = true;
        object = (Spanned)((TextView)object).getText();
        object = object.subSequence(object.getSpanStart((Object)this) + 1, object.getSpanEnd((Object)this)).toString();
        if (this.b) {
            this.a((String)object);
            return;
        }
        this.b((String)object);
    }

    public void updateDrawState(TextPaint textPaint) {
        if (this.d != null) {
            textPaint.setTypeface(this.d);
        }
    }

    public static interface HashtagCallback {
        public void a();
    }

}

