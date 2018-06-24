/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.POST
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.Contact;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface ContactsAPI {
    @POST(value="/v2/contact/clear")
    @SNP
    public Call<NetworkResponse> clearContacts(@Body SnpRequest var1);

    @POST(value="/v2/contact/find")
    @SNP(secure=true)
    public Call<NetworkResponse> findContacts(@Body  var1);

    @POST(value="/v2/consent/update")
    @SNP
    public Call<NetworkResponse> updateConsent(@Body  var1);

}

