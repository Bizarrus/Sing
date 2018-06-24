/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import com.smule.android.network.api.OfferAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.OfferModel;
import java.util.concurrent.Future;
import retrofit2.Call;

public class OfferManager {
    private static final String a = OfferManager.class.getName();
    private static OfferManager c;
    private com.smule.android.network.api.OfferAPI b = MagicNetwork.a().a(com.smule.android.network.api.OfferAPI.class);

    private OfferManager() {
    }

    public static OfferManager a() {
        if (c == null) {
            c = new OfferManager();
        }
        return c;
    }

    public OfferModel a(OfferAPI triggerType) {
        return OfferModel.a(NetworkUtils.a(this.b.checkOfferEligibility(new SnpRequest(){
            public String trigger;

            public OfferAPI setTrigger(OfferAPI triggerType) {
                this.trigger = triggerType.name();
                return this;
            }
        }.setTrigger(triggerType))));
    }

    public Future<?> a(final OfferAPI triggerType, final OfferEligibilityResponseCallback offerEligibilityResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                OfferModel offerModel = OfferManager.this.a(triggerType);
                CoreUtil.a(offerEligibilityResponseCallback, offerModel);
            }
        });
    }

    public static interface OfferEligibilityResponseCallback
    extends ResponseInterface<OfferModel> {
        @Override
        public void handleResponse(OfferModel var1);
    }

}

