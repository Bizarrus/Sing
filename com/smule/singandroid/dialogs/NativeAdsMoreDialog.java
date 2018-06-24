package com.smule.singandroid.dialogs;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.fragments.AboutAdsFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;

public class NativeAdsMoreDialog extends SmuleDialog {

    class C44981 implements OnClickListener {
        final /* synthetic */ NativeAdsMoreDialog f22268a;

        C44981(NativeAdsMoreDialog nativeAdsMoreDialog) {
            this.f22268a = nativeAdsMoreDialog;
        }

        public void onClick(View view) {
            this.f22268a.dismiss();
        }
    }

    public NativeAdsMoreDialog(final BaseFragment baseFragment) {
        super(baseFragment.getActivity(), C1947R.style.MagicModal);
        setContentView(C1947R.layout.native_ad_about_ads_dialog);
        TextView textView = (TextView) findViewById(C1947R.id.subscribe);
        TextView textView2 = (TextView) findViewById(C1947R.id.about_ads);
        findViewById(C1947R.id.container).setOnClickListener(new C44981(this));
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAdsMoreDialog f22270b;

            public void onClick(View view) {
                baseFragment.mo6487a(UpsellManager.m25791a(false, null, SingServerValues.m21690k(), null, UpsellType.NATIVE_ADS_OVERFLOW));
                this.f22270b.dismiss();
            }
        });
        textView2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAdsMoreDialog f22272b;

            public void onClick(View view) {
                baseFragment.mo6487a(AboutAdsFragment.m23726a());
                this.f22272b.dismiss();
            }
        });
    }
}
