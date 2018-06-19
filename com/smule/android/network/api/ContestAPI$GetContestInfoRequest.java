package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class ContestAPI$GetContestInfoRequest extends SnpRequest {
    public ArrayList<HashMap<String, Object>> contestScores;
    public Boolean previous;

    public ContestAPI$GetContestInfoRequest setPrevious(Boolean bool) {
        this.previous = bool;
        return this;
    }

    public ContestAPI$GetContestInfoRequest setContestScores(ArrayList<HashMap<String, Object>> arrayList) {
        this.contestScores = arrayList;
        return this;
    }
}
