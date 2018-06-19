package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.LocalizationManager.GetLocalizationPackageResponse;

public interface LocalizationManager$GetLocalizationPackageListener extends ResponseInterface<GetLocalizationPackageResponse> {
    void handleResponse(GetLocalizationPackageResponse getLocalizationPackageResponse);
}
