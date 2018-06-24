/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.annotation.Nullable
 *  android.text.Html
 *  android.text.Spannable
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.text.style.URLSpan
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.Window
 *  android.widget.TextView
 *  com.facebook.accountkit.AccountKitError
 *  com.facebook.accountkit.AccountKitError$Type
 *  com.facebook.accountkit.ui.AdvancedUIManager
 *  com.facebook.accountkit.ui.AdvancedUIManager$AdvancedUIManagerListener
 *  com.facebook.accountkit.ui.ButtonType
 *  com.facebook.accountkit.ui.LoginFlowState
 *  com.facebook.accountkit.ui.TextPosition
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$AccountKitFlow
 */
package com.smule.singandroid.registration;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.TextView;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.ui.AdvancedUIManager;
import com.facebook.accountkit.ui.ButtonType;
import com.facebook.accountkit.ui.LoginFlowState;
import com.facebook.accountkit.ui.TextPosition;
import com.smule.android.logging.Analytics;
import com.smule.singandroid.WebViewActivity;
import com.smule.singandroid.utils.SingAnalytics;

public class SmuleAdvancedUIManager
implements AdvancedUIManager {
    public static final Parcelable.Creator<SmuleAdvancedUIManager> CREATOR;
    public static final String a;
    private AdvancedUIManager.AdvancedUIManagerListener b;
    private AccountKitToolbar c;

    static {
        a = SmuleAdvancedUIManager.class.getSimpleName();
        CREATOR = new Parcelable.Creator<SmuleAdvancedUIManager>(){

            public SmuleAdvancedUIManager a(Parcel parcel) {
                return new SmuleAdvancedUIManager();
            }

            public SmuleAdvancedUIManager[] a(int n) {
                return new SmuleAdvancedUIManager[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public int describeContents() {
        return 0;
    }

    @Nullable
    public Fragment getActionBarFragment(LoginFlowState loginFlowState) {
        Log.v((String)a, (String)("getActionBarFragment: " + loginFlowState.name().toLowerCase()));
        if (this.c == null) {
            this.c = new AccountKitToolbar();
            this.c.a(new View.OnClickListener(){

                public void onClick(View view) {
                    if (SmuleAdvancedUIManager.this.b != null) {
                        SmuleAdvancedUIManager.this.b.onBack();
                    }
                }
            });
        }
        this.c.a(loginFlowState);
        return this.c;
    }

    @Nullable
    public Fragment getBodyFragment(LoginFlowState loginFlowState) {
        if (this.c != null) {
            this.c.a(loginFlowState);
        }
        return null;
    }

    @Nullable
    public ButtonType getButtonType(LoginFlowState loginFlowState) {
        return null;
    }

    @Nullable
    public Fragment getFooterFragment(LoginFlowState loginFlowState) {
        if (loginFlowState == LoginFlowState.CODE_INPUT) {
            return new PrivacyPolicyFragment();
        }
        return null;
    }

    @Nullable
    public Fragment getHeaderFragment(LoginFlowState loginFlowState) {
        return null;
    }

    @Nullable
    public TextPosition getTextPosition(LoginFlowState loginFlowState) {
        return null;
    }

    public void onError(AccountKitError accountKitError) {
        if (accountKitError != null) {
            com.smule.android.logging.Analytics.b(Analytics.f.a(), "acctkit_error", accountKitError.getErrorType().name().toLowerCase(), "" + accountKitError.getDetailErrorCode());
        }
    }

    public void setAdvancedUIManagerListener(AdvancedUIManager.AdvancedUIManagerListener advancedUIManagerListener) {
        this.b = advancedUIManagerListener;
    }

    public void writeToParcel(Parcel parcel, int n) {
    }

    public static class AccountKitToolbar
    extends Fragment
    implements ViewTreeObserver.OnGlobalLayoutListener {
        private View.OnClickListener a;
        private LoginFlowState b;
        private LoginFlowState c;

        private void a() {
            if (this.c != this.b) {
                SingAnalytics.a((SingAnalytics.AccountKitFlow)SingAnalytics.AccountKitFlow.a, (LoginFlowState)this.b);
            }
            this.c = this.b;
        }

        public void a(View.OnClickListener onClickListener) {
            this.a = onClickListener;
        }

        public void a(LoginFlowState loginFlowState) {
            this.b = loginFlowState;
            this.a();
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            layoutInflater = (ViewGroup)layoutInflater.inflate(2130903068, viewGroup, false);
            layoutInflater.findViewById(2131755222).setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    if (AccountKitToolbar.this.a != null) {
                        AccountKitToolbar.this.a.onClick(view);
                    }
                }
            });
            return layoutInflater;
        }

        public void onGlobalLayout() {
            Activity activity = this.getActivity();
            if (activity != null && this.b == LoginFlowState.RESEND && (activity = activity.getWindow().findViewById(2131755502)) instanceof TextView) {
                ((TextView)activity).setTextColor(this.getResources().getColor(2131689546));
                activity.setBackgroundColor(this.getResources().getColor(2131689503));
            }
        }

        public void onPause() {
            super.onPause();
            Activity activity = this.getActivity();
            if (activity != null) {
                activity.getWindow().findViewById(16908290).getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
            }
            this.c = null;
        }

        public void onResume() {
            super.onResume();
            Activity activity = this.getActivity();
            if (activity != null) {
                activity.getWindow().findViewById(16908290).getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
            }
            this.a();
        }

    }

    public static class PrivacyPolicyFragment
    extends Fragment {
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            layoutInflater = (ViewGroup)layoutInflater.inflate(2130903371, viewGroup, false);
            viewGroup = (TextView)layoutInflater.findViewById(2131756375);
            bundle = (Spannable)Html.fromHtml((String)this.getString(2131296936));
            for (URLSpan uRLSpan : (URLSpan[])bundle.getSpans(0, bundle.length(), URLSpan.class)) {
                bundle.setSpan((Object)new ClickableSpan(uRLSpan.getURL()){
                    final /* synthetic */ String a;
                    {
                        this.a = string2;
                    }

                    public void onClick(View view) {
                        PrivacyPolicyFragment.this.startActivity(WebViewActivity.a((Context)PrivacyPolicyFragment.this.getActivity(), this.a, true, true));
                    }
                }, bundle.getSpanStart((Object)uRLSpan), bundle.getSpanEnd((Object)uRLSpan), 0);
                bundle.removeSpan((Object)uRLSpan);
            }
            viewGroup.setText((CharSequence)bundle);
            viewGroup.setLinkTextColor(this.getResources().getColor(2131689482));
            viewGroup.setMovementMethod(LinkMovementMethod.getInstance());
            viewGroup.setHighlightColor(0);
            return layoutInflater;
        }

    }

}

