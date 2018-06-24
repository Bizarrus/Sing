/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.bookmark;

import android.content.Context;
import android.support.annotation.NonNull;
import com.smule.android.logging.Analytics;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.common.OptionsMenu.MenuOption;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.concurrent.Future;

public class SongBookmarkMenuBuilder {
    private ArrangementVersionLiteEntry a = null;
    private ArrangementVersionLite b = null;
    private boolean c = true;
    private String d = null;
    private String e = null;
    private String f = null;
    private SongBookmarkCallback g = null;

    public SongBookmarkMenuBuilder a(ArrangementVersionLite arrangementVersionLite) {
        this.b = arrangementVersionLite;
        this.d = arrangementVersionLite.key;
        this.e = Analytics.b(arrangementVersionLite);
        this.f = Analytics.a(arrangementVersionLite);
        return this;
    }

    public SongBookmarkMenuBuilder a(ArrangementVersionLiteEntry arrangementVersionLiteEntry) {
        this.a = arrangementVersionLiteEntry;
        this.d = arrangementVersionLiteEntry.c();
        this.e = arrangementVersionLiteEntry.w();
        this.f = arrangementVersionLiteEntry.v();
        return this;
    }

    public SongBookmarkMenuBuilder a(SongBookmarkCallback songBookmarkCallback) {
        this.g = songBookmarkCallback;
        return this;
    }

    public SongBookmarkMenuBuilder a(boolean bl) {
        this.c = bl;
        return this;
    }

    public OptionsMenu a(final @NonNull Context context) {
        MenuOption menuOption = new MenuOption(context.getString(2131296671), new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                ArrangementManager.a().a(SongBookmarkMenuBuilder.this.d, new ResponseInterface<ArrangementManager.AddSongBookmarkResponse>(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void handleResponse(ArrangementManager.AddSongBookmarkResponse object) {
                        if (object.a()) {
                            SingAnalytics.g((String)SongBookmarkMenuBuilder.this.e, (String)SongBookmarkMenuBuilder.this.f);
                            ArrangementManager.a().a(true);
                            if (SongBookmarkMenuBuilder.this.g != null) {
                                SongBookmarkMenuBuilder.this.g.a();
                            }
                            Toaster.a(context, context.getString(2131296427));
                            if (MagicPreferences.e(context)) return;
                            {
                                if (SongBookmarkMenuBuilder.this.c) {
                                    NotificationCenter.a().a("SNACKBAR_MESSAGE_KEY", (Object)context.getString(2131297515));
                                } else {
                                    Toaster.a(context, context.getString(2131297515));
                                }
                                MagicPreferences.c(context, true);
                                return;
                            }
                        } else {
                            if (object.a.b != 1015) return;
                            {
                                object = new TextAlertDialog(context, context.getString(2131297514), context.getString(2131297513), true, false);
                                object.a(context.getString(2131296705), null);
                                object.d(true);
                                object.show();
                                return;
                            }
                        }
                    }
                });
            }

        });
        MenuOption menuOption2 = new MenuOption(context.getString(2131296672), null);
        return new OptionsMenu.Builder().a(menuOption, menuOption2).a(context);
    }

    public static interface SongBookmarkCallback {
        public void a();
    }

}

