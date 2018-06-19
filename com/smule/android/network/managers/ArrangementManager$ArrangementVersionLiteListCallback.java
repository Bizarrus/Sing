package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionLiteListResponse;

public interface ArrangementManager$ArrangementVersionLiteListCallback extends ResponseInterface<ArrangementVersionLiteListResponse> {
    void handleResponse(ArrangementVersionLiteListResponse arrangementVersionLiteListResponse);
}
