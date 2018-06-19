package com.smule.android.ads.fullScreenAds;

import android.database.Observable;
import com.mopub.mobileads.MoPubErrorCode;
import java.util.Iterator;

protected class MagicFullScreenAdMediatorAdapter$MagicFullScreenAdObservable extends Observable<MagicFullScreenAdMediatorAdapter$EventListener> {
    final /* synthetic */ MagicFullScreenAdMediatorAdapter f15621a;

    protected MagicFullScreenAdMediatorAdapter$MagicFullScreenAdObservable(MagicFullScreenAdMediatorAdapter magicFullScreenAdMediatorAdapter) {
        this.f15621a = magicFullScreenAdMediatorAdapter;
    }

    public void m17449a() {
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            ((MagicFullScreenAdMediatorAdapter$EventListener) it.next()).mo6897a();
        }
    }

    public void m17450a(MagicFullScreenAdMediatorAdapter$AdImpressionResult magicFullScreenAdMediatorAdapter$AdImpressionResult, MoPubErrorCode moPubErrorCode) {
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            ((MagicFullScreenAdMediatorAdapter$EventListener) it.next()).mo6906a(magicFullScreenAdMediatorAdapter$AdImpressionResult, moPubErrorCode);
        }
    }

    public void m17451a(MagicFullScreenAdMediatorAdapter$RewardResult magicFullScreenAdMediatorAdapter$RewardResult) {
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            ((MagicFullScreenAdMediatorAdapter$EventListener) it.next()).mo6907a(magicFullScreenAdMediatorAdapter$RewardResult);
        }
    }
}
