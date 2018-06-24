/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.AsyncTask
 *  android.text.Spannable
 *  android.text.SpannableString
 *  android.text.style.URLSpan
 *  android.text.util.Linkify
 *  com.smule.singandroid.task.RegisterTask
 *  com.smule.singandroid.task.RegisterTask$RegisterTaskListener
 *  com.smule.singandroid.utils.URLSpanNoUnderline
 *  com.smule.singandroid.utils.ValidationUtils
 */
package com.smule.singandroid.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import com.smule.android.logging.Analytics;
import com.smule.android.network.api.OfferAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.OfferManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.OfferModel;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.VerifyEmailMessageDialog;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.registration.RegistrationEntryActivity;
import com.smule.singandroid.registration.WelcomeActivity_;
import com.smule.singandroid.task.RegisterTask;
import com.smule.singandroid.utils.URLSpanNoUnderline;
import com.smule.singandroid.utils.ValidationUtils;
import java.io.Serializable;
import java.util.concurrent.Future;

public class NewAccountFlow {
    private static final String a = NewAccountFlow.class.getName();
    private Activity b;
    private BusyDialog c;
    private String d;

    public NewAccountFlow(Activity activity) {
        this.b = activity;
    }

    private Spannable a(SpannableString spannableString) {
        spannableString = new SpannableString((CharSequence)spannableString);
        for (URLSpan uRLSpan : (URLSpan[])spannableString.getSpans(0, spannableString.length(), URLSpan.class)) {
            int n = spannableString.getSpanStart((Object)uRLSpan);
            int n2 = spannableString.getSpanEnd((Object)uRLSpan);
            spannableString.removeSpan((Object)uRLSpan);
            spannableString.setSpan((Object)new URLSpanNoUnderline(uRLSpan.getURL()), n, n2, 0);
        }
        return spannableString;
    }

    private void a(final boolean bl) {
        Object object = new SpannableString((CharSequence)this.b.getResources().getString(2131296899, new Object[]{this.d}));
        Linkify.addLinks((Spannable)object, (int)2);
        object = new VerifyEmailMessageDialog(this.b, 2131297376, this.a((SpannableString)object), 2131296705, 2131296674);
        object.d(true);
        object.a(new Runnable(){

            @Override
            public void run() {
                Intent intent = new Intent((Context)NewAccountFlow.this.b, WelcomeActivity_.class);
                intent.putExtra("PARAM_LOGIN_METHOD", (Serializable)((Object)Analytics.a));
                intent.putExtra("SHOW_EMAIL_OPT", bl);
                NewAccountFlow.this.b.startActivity(intent);
                NewAccountFlow.this.b.finish();
            }
        });
        object.show();
    }

    public boolean a(String string2, String string3) {
        if (this.c != null) {
            this.c.dismiss();
            this.c = null;
        }
        this.d = string2;
        Listeners listeners = new Listeners();
        this.c = new BusyDialog(this.b, this.b.getResources().getString(2131296896));
        this.c.show();
        if (!ValidationUtils.a((String)string2)) {
            this.c.a(2, this.b.getString(2131296920), this.b.getString(2131296919), null, this.b.getString(2131296705));
            return false;
        }
        new RegisterTask(this.b, this.d, string3, (RegisterTask.RegisterTaskListener)listeners).execute((Object[])new Void[0]);
        return true;
    }

    private class Listeners
    implements UserManager,
    RegisterTask.RegisterTaskListener {
        private Listeners() {
        }

        public void a(UserManager.RegistrationResponse registrationResponse) {
            com.smule.android.network.core.NetworkResponse networkResponse = registrationResponse.a;
            final boolean bl = registrationResponse.showEmailOpt;
            if (networkResponse.a != NetworkResponse.a) {
                NewAccountFlow.this.c.a(2, NewAccountFlow.this.b.getResources().getString(2131296895), null, "OK");
                MagicNetwork.d().showConnectionError();
                return;
            }
            switch (networkResponse.b) {
                default: {
                    MagicNetwork.a(networkResponse);
                    NewAccountFlow.this.c.a(2, NewAccountFlow.this.b.getResources().getString(2131296908), networkResponse.h(), "OK");
                    return;
                }
                case 56: 
                case 67: 
                case 1006: {
                    if (networkResponse.f == 13) {
                        com.smule.android.network.managers.UserManager.a().a(NewAccountFlow.this.d, this);
                        return;
                    }
                    NewAccountFlow.this.c.a(2, NewAccountFlow.this.b.getResources().getString(2131297323), null, "OK");
                    return;
                }
                case 0: 
            }
            RegistrationContext.d();
            if (NewAccountFlow.this.c != null) {
                NewAccountFlow.this.c.dismiss();
            }
            OfferManager.a().a(OfferAPI.EMAIL_CONFIRM, new OfferManager.OfferEligibilityResponseCallback(){

                @Override
                public void handleResponse(OfferModel offerModel) {
                    if (offerModel.a() && offerModel.eligible) {
                        NewAccountFlow.this.a(bl);
                        return;
                    }
                    offerModel = new Intent((Context)NewAccountFlow.this.b, WelcomeActivity_.class);
                    offerModel.putExtra("PARAM_LOGIN_METHOD", (Serializable)((Object)Analytics.a));
                    offerModel.putExtra("SHOW_EMAIL_OPT", bl);
                    NewAccountFlow.this.b.startActivity((Intent)offerModel);
                    NewAccountFlow.this.b.finish();
                }
            });
        }

        @Override
        public void handleResponse(UserManager.AccountIconResponse accountIconResponse) {
            if (accountIconResponse.a == null || accountIconResponse.a.a != NetworkResponse.a) {
                NewAccountFlow.this.c.a(2, NewAccountFlow.this.b.getResources().getString(2131296895), null, "OK");
                return;
            }
            if (!accountIconResponse.a.c()) {
                NewAccountFlow.this.c.a(2, NewAccountFlow.this.b.getResources().getString(2131296908), accountIconResponse.a.h(), "OK");
                MagicNetwork.a(accountIconResponse.a);
                return;
            }
            if (accountIconResponse.mAccount.handle != null) {
                if (NewAccountFlow.this.c != null) {
                    NewAccountFlow.this.c.dismiss();
                    NewAccountFlow.this.c = null;
                }
                accountIconResponse = RegistrationEntryActivity.a(NewAccountFlow.this.b, accountIconResponse.mAccount.handle, NewAccountFlow.this.d, accountIconResponse.mAccount.picUrl);
                NewAccountFlow.this.b.startActivity((Intent)accountIconResponse);
                NewAccountFlow.this.b.finish();
                return;
            }
            NewAccountFlow.this.c.a(2, NewAccountFlow.this.b.getResources().getString(2131296634), null, "OK");
        }

    }

}

