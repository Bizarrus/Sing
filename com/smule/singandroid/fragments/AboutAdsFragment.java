package com.smule.singandroid.fragments;

import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class AboutAdsFragment extends BaseFragment {
    public static final String f22341e = AboutAdsFragment.class.getName();
    @ViewById
    protected TextView f22342f;

    class C45231 extends ClickableSpan {
        final /* synthetic */ AboutAdsFragment f22340a;

        C45231(AboutAdsFragment aboutAdsFragment) {
            this.f22340a = aboutAdsFragment;
        }

        public void onClick(View view) {
            this.f22340a.mo6487a(UpsellManager.m25791a(false, null, SingServerValues.m21690k(), null, UpsellType.NATIVE_ADS_ABOUT));
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(this.f22340a.getResources().getColor(C1947R.color.about_ads_upgrade_link_text_color));
            textPaint.setUnderlineText(false);
        }
    }

    public String mo6383s() {
        return f22341e;
    }

    public static AboutAdsFragment m23726a() {
        return new AboutAdsFragment_();
    }

    public void onResume() {
        super.onResume();
        if (SubscriptionManager.a().b()) {
            m19840a(f22341e);
        }
    }

    public void onStart() {
        super.onStart();
        m19866q();
        m19850c((int) C1947R.string.native_ads_about_ads);
    }

    @AfterViews
    protected void m23728z() {
        String string = getResources().getString(C1947R.string.native_ads_upgrade_to_vip_here);
        CharSequence spannableString = new SpannableString(getResources().getString(C1947R.string.native_ads_about_ad_second, new Object[]{string}));
        C45231 c45231 = new C45231(this);
        int indexOf = spannableString.toString().indexOf(string);
        if (indexOf > -1) {
            spannableString.setSpan(c45231, indexOf, string.length() + indexOf, 33);
        }
        this.f22342f.setText(spannableString);
        this.f22342f.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
