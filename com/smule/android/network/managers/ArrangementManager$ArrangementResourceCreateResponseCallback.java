package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.ArrangementManager.ArrangementResourceCreateResponse;

public interface ArrangementManager$ArrangementResourceCreateResponseCallback extends ResponseInterface<ArrangementResourceCreateResponse> {
    void handleResponse(ArrangementResourceCreateResponse arrangementResourceCreateResponse);
}
