/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.bookmark;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.common.OptionsMenu.MenuOption;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.concurrent.Future;

public class SongBookmarkRemoveMenuBuilder {
    public static OptionsMenu a(@NonNull Context context, @NonNull ArrangementVersionLiteEntry arrangementVersionLiteEntry, @Nullable Runnable runnable) {
        return SongBookmarkRemoveMenuBuilder.a(context, arrangementVersionLiteEntry.c(), arrangementVersionLiteEntry.w(), arrangementVersionLiteEntry.v(), runnable);
    }

    private static OptionsMenu a(@NonNull Context context, @NonNull String object, @NonNull String object2, @Nullable String string2, @Nullable Runnable runnable) {
        object = new MenuOption(context.getString(2131296670), new MenuOption.OnClickListener((String)object, (String)object2, string2, context, runnable){
            final /* synthetic */ String a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;
            final /* synthetic */ Context d;
            final /* synthetic */ Runnable e;
            {
                this.a = string2;
                this.b = string3;
                this.c = string4;
                this.d = context;
                this.e = runnable;
            }

            @Override
            public void a(OptionsMenu optionsMenu) {
                ArrangementManager.a().b(this.a, new ResponseInterface<ArrangementManager.RemoveSongBookmarkResponse>(){

                    @Override
                    public void handleResponse(ArrangementManager.RemoveSongBookmarkResponse removeSongBookmarkResponse) {
                        if (removeSongBookmarkResponse.a()) {
                            SingAnalytics.h((String)1.this.b, (String)1.this.c);
                            ArrangementManager.a().a(true);
                            Toaster.a(1.this.d, 1.this.d.getString(2131296433));
                            if (1.this.e != null) {
                                1.this.e.run();
                            }
                            return;
                        }
                        Toaster.a(1.this.d, 2131296895);
                    }
                });
            }

        });
        object2 = new MenuOption(context.getString(2131296672), null);
        return new OptionsMenu.Builder().a(new MenuOption[]{object, object2}).a(context);
    }

}

