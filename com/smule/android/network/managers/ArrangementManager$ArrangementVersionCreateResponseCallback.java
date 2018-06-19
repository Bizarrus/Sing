package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionCreateResponse;

public interface ArrangementManager$ArrangementVersionCreateResponseCallback extends ResponseInterface<ArrangementVersionCreateResponse> {
    void handleResponse(ArrangementVersionCreateResponse arrangementVersionCreateResponse);
}
