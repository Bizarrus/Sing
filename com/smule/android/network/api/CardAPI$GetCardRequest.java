package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class CardAPI$GetCardRequest extends SnpRequest {
    public String cardKey;

    public CardAPI$GetCardRequest setCardKey(String str) {
        this.cardKey = str;
        return this;
    }
}
