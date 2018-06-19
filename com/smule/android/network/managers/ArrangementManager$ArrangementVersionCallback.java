package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionResponse;

public interface ArrangementManager$ArrangementVersionCallback extends ResponseInterface<ArrangementVersionResponse> {
    void handleResponse(ArrangementVersionResponse arrangementVersionResponse);
}
