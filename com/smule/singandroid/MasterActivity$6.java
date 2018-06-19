package com.smule.singandroid;

import com.smule.android.network.managers.PromotionManager.PromotionCallback;
import com.smule.android.network.managers.PromotionManager.PromotionResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.utils.DeepLink;

class MasterActivity$6 implements PromotionCallback {
    final /* synthetic */ DeepLink f18859a;
    final /* synthetic */ MasterActivity f18860b;

    MasterActivity$6(MasterActivity masterActivity, DeepLink deepLink) {
        this.f18860b = masterActivity;
        this.f18859a = deepLink;
    }

    public void handleResponse(PromotionResponse promotionResponse) {
        if (promotionResponse.a()) {
            String string = this.f18860b.getResources().getString(C1947R.string.promo_promotion);
            if (!(promotionResponse.hashtag == null || promotionResponse.hashtag.isEmpty())) {
                string = "#" + promotionResponse.hashtag;
            }
            this.f18860b.c(WebViewFragment.m22013a(promotionResponse.promoUrl + "?account_id=" + UserManager.a().f() + "&app_id=sing_google&locale=" + this.f18860b.getResources().getConfiguration().locale.toString(), string, false));
            this.f18860b.W();
            MagicPreferences.m20303a(this.f18860b, "LAST_PROMOTION_HASHTAG_PAIR", this.f18859a.f24742d + "," + promotionResponse.hashtag);
        } else if (promotionResponse.a.b == 1027) {
            Toaster.a(this.f18860b, C1947R.string.promo_inactive);
        } else {
            Toaster.a(this.f18860b, C1947R.string.promo_error);
        }
    }
}
