/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$LanguageSelectType
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.utils.RestartUtil;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.adapters.LanguagesListAdapter;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Locale;
import java.util.concurrent.Future;

public class LanguagesDialog
extends CustomAlertDialog {
    private LanguagesListAdapter c;

    public LanguagesDialog(final Activity activity, final Locale locale) {
        super((Context)activity, 2130903259, true, true, true);
        this.c = new LanguagesListAdapter((Context)activity, locale);
        this.a(activity.getString(2131296934));
        this.b();
        this.a(2131296705, 2131296672);
        this.a(new Runnable(){

            @Override
            public void run() {
                if (!locale.equals(LanguagesDialog.this.c.a())) {
                    TextAlertDialog textAlertDialog = new TextAlertDialog((Context)activity, activity.getString(2131296667), activity.getString(2131297341));
                    textAlertDialog.a(2131296705, 2131296672);
                    textAlertDialog.a(new Runnable(){

                        @Override
                        public void run() {
                            final Locale locale = LanguagesDialog.this.c.a();
                            com.smule.android.network.managers.LocalizationManager.a().a(LocaleSettings.b(locale), new LocalizationManager(){

                                @Override
                                public void handleResponse(LocalizationManager.SetPreferredLanguageResponse setPreferredLanguageResponse) {
                                    ((BaseActivity)activity).a(locale, false);
                                    SingAnalytics.a((SingAnalytics.LanguageSelectType)SingAnalytics.LanguageSelectType.b, (String)LocaleSettings.b(locale), (String)LocaleSettings.b(locale));
                                    RestartUtil.a((Context)activity);
                                }
                            });
                        }

                    });
                    textAlertDialog.b(new Runnable(){

                        @Override
                        public void run() {
                            LanguagesDialog.this.d();
                        }
                    });
                    textAlertDialog.show();
                    return;
                }
                LanguagesDialog.this.d();
            }

        });
        ((ListView)this.findViewById(2131755885)).setAdapter((ListAdapter)this.c);
    }

}

