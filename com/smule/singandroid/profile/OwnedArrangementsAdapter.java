/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.ViewGroup
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.WebViewActivity;
import com.smule.singandroid.bookmark.SongBookmarkMenuBuilder;
import com.smule.singandroid.bookmark.SongBookmarkRemoveMenuBuilder;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.profile.BasePerformancesAdapter;
import com.smule.singandroid.profile.ProfileArrangementContainer;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.profile.ProfileListView;
import com.smule.singandroid.profile.ProfileUserInfo;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.concurrent.ConcurrentHashMap;
import org.androidannotations.api.builder.PostActivityStarter;

public class OwnedArrangementsAdapter
extends BasePerformancesAdapter {
    private final String c = OwnedArrangementsAdapter.class.getSimpleName();
    private ProfileListView d;

    public OwnedArrangementsAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
        super(profileListView, magicDataSource);
        this.d = profileListView;
        this.e(2130903273);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String k() {
        int n;
        int n2 = this.d.e.U();
        Resources resources = this.d.getResources();
        if (this.d.e.O()) {
            n = 2131361808;
            do {
                return resources.getQuantityString(n, n2, new Object[]{this.d.e.ak().a(n2, this.d.getResources().getInteger(2131623962))});
                break;
            } while (true);
        }
        n = 2131361833;
        return resources.getQuantityString(n, n2, new Object[]{this.d.e.ak().a(n2, this.d.getResources().getInteger(2131623962))});
    }

    private String l() {
        int n = this.d.e.V();
        return this.d.getResources().getQuantityString(2131361793, n, new Object[]{this.d.e.ak().a(n, this.d.getResources().getInteger(2131623962))});
    }

    @Override
    public View a(ViewGroup viewGroup, int n) {
        return ListingListItem.a((Context)this.d.e.getActivity());
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(View object, int n, int n2) {
        final ProfileArrangementContainer profileArrangementContainer = (ProfileArrangementContainer)this.a(n);
        final ArrangementVersionLiteEntry arrangementVersionLiteEntry = profileArrangementContainer.c();
        if (arrangementVersionLiteEntry == null) {
            Log.e(this.c, "bindView: Unable to bind, entry is null");
            return;
        }
        object = (ListingListItem)object;
        boolean bl = n == 0;
        object.a(arrangementVersionLiteEntry, bl);
        object.a(this.d.e.u.e());
        object.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SingAnalytics.c((SongbookEntry)arrangementVersionLiteEntry);
                if (profileArrangementContainer.a()) {
                    SingAnalytics.a((String)arrangementVersionLiteEntry.w(), (String)arrangementVersionLiteEntry.v(), Analytics.b);
                }
                PreSingActivity.a((Context)OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.getActivity()).a(PreSingActivity.StartupMode.a).a((SongbookEntry)arrangementVersionLiteEntry).a();
            }
        });
        object.setAlbumArtClickListener(new View.OnClickListener((ListingListItem)object, profileArrangementContainer, arrangementVersionLiteEntry){
            final /* synthetic */ ListingListItem a;
            final /* synthetic */ ProfileArrangementContainer b;
            final /* synthetic */ ArrangementVersionLiteEntry c;
            {
                this.a = listingListItem;
                this.b = profileArrangementContainer;
                this.c = arrangementVersionLiteEntry;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                boolean bl = true;
                if (this.a.t()) return;
                if (!OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.isAdded()) {
                    return;
                }
                this.a.setAlbumArtClickedState(true);
                Log.b(OwnedArrangementsAdapter.this.c, "mPerformanceItemListener - onAlbumArtClicked called");
                if (OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.W() == null) {
                    OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.a(new ConcurrentHashMap<String, Boolean>());
                }
                boolean bl2 = this.b.a();
                if (OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.W().containsKey(this.c.c())) {
                    OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.a(OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.W().get(this.c.c()), this.c, this.a, bl2);
                    return;
                }
                int n = this.c.a().removalCode;
                if (n < 40 || n > 49) {
                    bl = false;
                }
                OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.W().put(this.c.c(), bl);
                OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.a(bl, this.c, this.a, bl2);
            }
        });
        if (profileArrangementContainer.a()) {
            object.setOnLongClickListener(new View.OnLongClickListener(){

                public boolean onLongClick(View view) {
                    SongBookmarkRemoveMenuBuilder.a((Context)OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.getActivity(), arrangementVersionLiteEntry, new Runnable(){

                        @Override
                        public void run() {
                            OwnedArrangementsAdapter.this.f();
                        }
                    }).a();
                    return true;
                }

            });
        } else {
            object.setOnLongClickListener(new View.OnLongClickListener(){

                public boolean onLongClick(View view) {
                    new SongBookmarkMenuBuilder().a(arrangementVersionLiteEntry).a(false).a(new SongBookmarkMenuBuilder.SongBookmarkCallback(){

                        @Override
                        public void a() {
                            OwnedArrangementsAdapter.this.f();
                        }
                    }).a((Context)OwnedArrangementsAdapter.a((OwnedArrangementsAdapter)OwnedArrangementsAdapter.this).e.getActivity()).a();
                    return true;
                }

            });
        }
        n2 = !this.d.e.O() || this.d.e.U() == 0 || this.d.e.V() == 0 ? 2131689991 : 2131689862;
        if (n == 0) {
            if (profileArrangementContainer.a()) {
                object.a(this.l(), this.d.getResources().getColor(n2), 2130837973);
                return;
            }
            if (!profileArrangementContainer.b()) return;
            {
                object.a(this.k(), this.d.getResources().getColor(n2), 0);
                return;
            }
        }
        if (((ProfileArrangementContainer)this.a(n)).b() && ((ProfileArrangementContainer)this.a(n - 1)).a()) {
            object.a(this.k(), this.d.getResources().getColor(n2), 0);
            return;
        }
        object.c();
    }

    @Override
    public View d(ViewGroup viewGroup) {
        return this.a(null, new View.OnClickListener(){

            public void onClick(View view) {
                OwnedArrangementsAdapter.this.d.getContext().startActivity(WebViewActivity.a(OwnedArrangementsAdapter.this.d.getContext(), OwnedArrangementsAdapter.this.d.getContext().getString(2131297745), true, true));
            }
        });
    }

    @Override
    protected int h() {
        return 2;
    }

    @Override
    protected String j() {
        Log.e(this.c, "getHeaderText called in OwnedArrangementsAdapter");
        return null;
    }

}

