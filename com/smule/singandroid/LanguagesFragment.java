/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$LanguageSelectType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.utils.RestartUtil;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.LanguagesFragment_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.adapters.LanguagesListAdapter;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Locale;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class LanguagesFragment
extends BaseFragment {
    public static final String g = LanguagesFragment.class.getName();
    @InstanceState
    protected Locale h;
    @ViewById
    protected ListView i;
    private LanguagesListAdapter j;

    public static LanguagesFragment a(Locale locale) {
        LanguagesFragment_ languagesFragment_ = new LanguagesFragment_();
        languagesFragment_.h = locale;
        return languagesFragment_;
    }

    private void t() {
        this.j = new LanguagesListAdapter((Context)this.getActivity(), this.h, new LanguagesListAdapter.LanguageSelectionCallback(){

            @Override
            public void a(final Locale locale) {
                if (!locale.equals(LanguagesFragment.this.h)) {
                    TextAlertDialog textAlertDialog = new TextAlertDialog((Context)LanguagesFragment.this.getActivity(), LanguagesFragment.this.getString(2131296667), LanguagesFragment.this.getString(2131297341));
                    textAlertDialog.a(2131296705, 2131296672);
                    textAlertDialog.a(new Runnable(){

                        @Override
                        public void run() {
                            com.smule.android.network.managers.LocalizationManager.a().a(LocaleSettings.b(locale), new LocalizationManager(){

                                @Override
                                public void handleResponse(LocalizationManager.SetPreferredLanguageResponse setPreferredLanguageResponse) {
                                    ((BaseActivity)LanguagesFragment.this.getActivity()).a(locale, false);
                                    SingAnalytics.a((SingAnalytics.LanguageSelectType)SingAnalytics.LanguageSelectType.a, (String)LocaleSettings.b(LanguagesFragment.this.h), (String)LocaleSettings.b(locale));
                                    LanguagesFragment.this.a(new Runnable(){

                                        @Override
                                        public void run() {
                                            RestartUtil.a(SingApplication.g());
                                        }
                                    }, 500);
                                }

                            });
                        }

                    });
                    textAlertDialog.show();
                }
            }

        });
        this.i.setAdapter((ListAdapter)this.j);
    }

    @AfterViews
    protected void a() {
        this.t();
    }

    @Override
    public void onStart() {
        this.c(2131297340);
        super.onStart();
    }

    @Override
    public String x() {
        return g;
    }

}

