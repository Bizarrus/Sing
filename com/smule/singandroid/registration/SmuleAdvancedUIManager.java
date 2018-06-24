package com.smule.singandroid.registration;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.ui.AdvancedUIManager;
import com.facebook.accountkit.ui.AdvancedUIManager.AdvancedUIManagerListener;
import com.facebook.accountkit.ui.ButtonType;
import com.facebook.accountkit.ui.LoginFlowState;
import com.facebook.accountkit.ui.TextPosition;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.WebViewActivity;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.AccountKitFlow;

public class SmuleAdvancedUIManager implements AdvancedUIManager {
    public static final Creator<SmuleAdvancedUIManager> CREATOR = new C48512();
    public static final String f23937a = SmuleAdvancedUIManager.class.getSimpleName();
    private AdvancedUIManagerListener f23938b;
    private AccountKitToolbar f23939c;

    class C48501 implements OnClickListener {
        final /* synthetic */ SmuleAdvancedUIManager f23930a;

        C48501(SmuleAdvancedUIManager smuleAdvancedUIManager) {
            this.f23930a = smuleAdvancedUIManager;
        }

        public void onClick(View view) {
            if (this.f23930a.f23938b != null) {
                this.f23930a.f23938b.onBack();
            }
        }
    }

    static class C48512 implements Creator<SmuleAdvancedUIManager> {
        C48512() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m25185a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m25186a(i);
        }

        public SmuleAdvancedUIManager m25185a(Parcel parcel) {
            return new SmuleAdvancedUIManager();
        }

        public SmuleAdvancedUIManager[] m25186a(int i) {
            return new SmuleAdvancedUIManager[i];
        }
    }

    public static class AccountKitToolbar extends Fragment implements OnGlobalLayoutListener {
        private OnClickListener f23932a;
        private LoginFlowState f23933b;
        private LoginFlowState f23934c;

        class C48521 implements OnClickListener {
            final /* synthetic */ AccountKitToolbar f23931a;

            C48521(AccountKitToolbar accountKitToolbar) {
                this.f23931a = accountKitToolbar;
            }

            public void onClick(View view) {
                if (this.f23931a.f23932a != null) {
                    this.f23931a.f23932a.onClick(view);
                }
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(C1947R.layout.account_kit_toolbar, viewGroup, false);
            viewGroup2.findViewById(C1947R.id.backbutton).setOnClickListener(new C48521(this));
            return viewGroup2;
        }

        public void onResume() {
            super.onResume();
            Activity activity = getActivity();
            if (activity != null) {
                activity.getWindow().findViewById(16908290).getViewTreeObserver().addOnGlobalLayoutListener(this);
            }
            m25188a();
        }

        public void onPause() {
            super.onPause();
            Activity activity = getActivity();
            if (activity != null) {
                activity.getWindow().findViewById(16908290).getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            this.f23934c = null;
        }

        public void onGlobalLayout() {
            Activity activity = getActivity();
            if (activity != null && this.f23933b == LoginFlowState.RESEND) {
                View findViewById = activity.getWindow().findViewById(C1947R.id.com_accountkit_send_in_fb_button);
                if (findViewById instanceof TextView) {
                    ((TextView) findViewById).setTextColor(getResources().getColor(C1947R.color.body_text_darker_grey));
                    findViewById.setBackgroundColor(getResources().getColor(C1947R.color.background_button_grey));
                }
            }
        }

        public void m25189a(OnClickListener onClickListener) {
            this.f23932a = onClickListener;
        }

        public void m25190a(LoginFlowState loginFlowState) {
            this.f23933b = loginFlowState;
            m25188a();
        }

        private void m25188a() {
            if (this.f23934c != this.f23933b) {
                SingAnalytics.m26077a(AccountKitFlow.PHONE, this.f23933b);
            }
            this.f23934c = this.f23933b;
        }
    }

    public static class PrivacyPolicyFragment extends Fragment {
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(C1947R.layout.privacy_policy_links, viewGroup, false);
            TextView textView = (TextView) viewGroup2.findViewById(C1947R.id.terms_text);
            Spannable spannable = (Spannable) Html.fromHtml(getString(C1947R.string.login_sign_up_terms));
            for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
                final String url = uRLSpan.getURL();
                spannable.setSpan(new ClickableSpan(this) {
                    final /* synthetic */ PrivacyPolicyFragment f23936b;

                    public void onClick(View view) {
                        this.f23936b.startActivity(WebViewActivity.m22001a(this.f23936b.getActivity(), url, true, true));
                    }
                }, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 0);
                spannable.removeSpan(uRLSpan);
            }
            textView.setText(spannable);
            textView.setLinkTextColor(getResources().getColor(C1947R.color.account_kit_tos_text));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setHighlightColor(0);
            return viewGroup2;
        }
    }

    @Nullable
    public Fragment getActionBarFragment(LoginFlowState loginFlowState) {
        Log.v(f23937a, "getActionBarFragment: " + loginFlowState.name().toLowerCase());
        if (this.f23939c == null) {
            this.f23939c = new AccountKitToolbar();
            this.f23939c.m25189a(new C48501(this));
        }
        this.f23939c.m25190a(loginFlowState);
        return this.f23939c;
    }

    @Nullable
    public Fragment getBodyFragment(LoginFlowState loginFlowState) {
        if (this.f23939c != null) {
            this.f23939c.m25190a(loginFlowState);
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

    public void setAdvancedUIManagerListener(AdvancedUIManagerListener advancedUIManagerListener) {
        this.f23938b = advancedUIManagerListener;
    }

    public void onError(AccountKitError accountKitError) {
        if (accountKitError != null) {
            Analytics.m17890b(RegistrationFlow.PHONE.mo6235a(), "acctkit_error", accountKitError.getErrorType().name().toLowerCase(), "" + accountKitError.getDetailErrorCode());
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }
}
