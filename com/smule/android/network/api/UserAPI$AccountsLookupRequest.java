package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.Collection;

public class UserAPI$AccountsLookupRequest extends SnpRequest {
    public Collection<Long> accountIds;

    public UserAPI$AccountsLookupRequest setAccountIds(Collection<Long> collection) {
        this.accountIds = collection;
        return this;
    }
}
