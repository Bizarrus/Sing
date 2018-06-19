package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.ArrangementManager.ArrangementCreateResponse;

public interface ArrangementManager$ArrangementCreateCallback extends ResponseInterface<ArrangementCreateResponse> {
    void handleResponse(ArrangementCreateResponse arrangementCreateResponse);
}
