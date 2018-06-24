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
import java.util.ArrayList;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface ContestAPI {
    public static final  CURRENT_CONTEST_INFO_REQUEST = new SnpRequest(){
        public ArrayList<HashMap<String, Object>> contestScores;
        public Boolean previous;

        public  setContestScores(ArrayList<HashMap<String, Object>> arrayList) {
            this.contestScores = arrayList;
            return this;
        }

        public  setPrevious(Boolean bl) {
            this.previous = bl;
            return this;
        }
    }.setPrevious(false);
    public static final  PAST_CONTEST_INFO_REQUEST = new .setPrevious(true);

    @POST(value="/v2/contest/get")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getContestInfo(@Body  var1);

    @POST(value="/v2/contest/score")
    @SNP
    public Call<NetworkResponse> submitScore(@Body  var1);

}

