/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.text.format.Time
 *  com.bluelinelabs.logansquare.LoganSquare
 *  com.fasterxml.jackson.databind.JsonNode
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.Time;
import com.bluelinelabs.logansquare.LoganSquare;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.api.ContestAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.models.ContestData;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;

public class ContestManager {
    private static final String a = ContestManager.class.getName();
    private ContestData.ContestInfo b;
    private Map<Long, ContestData.ContestInfo> c = new HashMap<Long, ContestData.ContestInfo>();
    private Map<Long, ContestData.ContestUserState> d = new HashMap<Long, ContestData.ContestUserState>();
    private long e = 0;
    private Timer f;
    private Context g;
    private com.smule.android.network.api.ContestAPI h;
    private Observer i;

    private ContestManager() {
        this.i = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                if ("USER_EXISTENCE_TYPE_EXISTING".equals((String)object)) {
                    ContestManager.this.b = null;
                    ContestManager.this.c = new HashMap();
                }
                ContestManager.this.a();
            }
        };
        this.h = MagicNetwork.a().a(com.smule.android.network.api.ContestAPI.class);
    }

    private Time a(Time time) {
        Time time2 = new Time();
        time2.switchTimezone("America/Los_Angeles");
        time2.set(0, 0, 12, time.monthDay, time.month, time.year);
        return time2;
    }

    private void a(List<ContestData.ContestInfo> iterator) {
        Object object;
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            object = iterator.next();
            ContestData.ContestUserState contestUserState = this.a(object);
            if (object.rank != null) {
                contestUserState.rank = object.rank;
            }
            if (object.score != null) {
                contestUserState.score = object.score;
            }
            if (object.rank != null && object.score != null) {
                contestUserState.started = true;
            }
            if (!object.isEnded()) {
                this.b = object;
            }
            this.c.put(object.contest.id, object);
        }
        long l = System.currentTimeMillis() / 1000;
        iterator = this.c.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().contest.end >= Long.valueOf(l - 345600)) continue;
            iterator.remove();
        }
        iterator = this.d.entrySet().iterator();
        while (iterator.hasNext()) {
            object = (Map.Entry)iterator.next();
            if (this.c.containsKey(object.getKey())) continue;
            iterator.remove();
        }
        this.d();
        this.e = System.currentTimeMillis();
        NotificationCenter.a().b("CONTEST_STATE_MIGHT_HAVE_CHANGED", new Object[0]);
    }

    private void d() {
        SharedPreferences.Editor editor = this.g.getSharedPreferences("CONTEST_SETTINGS", 0).edit();
        HashMap<String, ContestData.ContestInfo> hashMap = new HashMap<String, ContestData.ContestInfo>();
        HashMap hashMap2 = new HashMap();
        for (Map.Entry<Long, ContestData.ContestInfo> entry : this.c.entrySet()) {
            hashMap.put(entry.getKey().toString(), entry.getValue());
        }
        for (Map.Entry entry : this.d.entrySet()) {
            hashMap2.put(((Long)entry.getKey()).toString(), entry.getValue());
        }
        try {
            editor.putString("version", "1.0").putString("contests", LoganSquare.a(hashMap)).putString("states", LoganSquare.a(hashMap2));
            return;
        }
        catch (IOException iOException) {
            Log.d(a, "Error serializing object to JSON", iOException);
            return;
        }
        finally {
            editor.apply();
        }
    }

    private void e() {
        this.f();
        Date date = this.c();
        this.f = new Timer();
        this.f.schedule(new TimerTask(){

            @Override
            public void run() {
                NotificationCenter.a().b("CONTEST_STATE_MIGHT_HAVE_CHANGED", new Object[0]);
                ContestManager.this.e();
            }
        }, date);
    }

    private void f() {
        this.f.cancel();
        this.f.purge();
    }

    public ContestData.ContestUserState a(ContestData.ContestInfo contestInfo) {
        ContestData.ContestUserState contestUserState;
        ContestData.ContestUserState contestUserState2 = contestUserState = this.d.get(contestInfo.contest.id);
        if (contestUserState == null) {
            contestUserState2 = new ContestData.ContestUserState();
            contestUserState2.reported = false;
            contestUserState2.started = false;
            this.d.put(contestInfo.contest.id, contestUserState2);
        }
        return contestUserState2;
    }

    public void a() {
        if (this.b == null) {
            MagicNetwork.a(new Runnable(){

                @Override
                public void run() {
                    Object object = ContestManager.this.b();
                    if (object != null && object.a.c()) {
                        object = object.b.contestInfos;
                        ContestManager.this.a((List)object);
                    }
                }
            });
        }
    }

    public ContestInfoResponse b() {
        return new ContestInfoResponse(NetworkUtils.a(this.h.getContestInfo(com.smule.android.network.api.ContestAPI.CURRENT_CONTEST_INFO_REQUEST)));
    }

    public Date c() {
        if (this.b != null && !this.b.isEnded()) {
            return new Date(this.b.contest.end * 1000);
        }
        Time time = new Time();
        time.setToNow();
        Time time2 = this.a(time);
        if (time.before(time2)) {
            return new Date(time2.toMillis(false));
        }
        time2 = new Time();
        time2.set(time.toMillis(false) + 86400000);
        return new Date(this.a(time2).toMillis(false));
    }

    public static class ContestInfoResponse {
        public NetworkResponse a;
        public ContestData.ContestInfoResponse b;

        ContestInfoResponse(NetworkResponse networkResponse) {
            this.a = networkResponse;
            if (this.a != null && this.a.l != null) {
                this.b = JsonUtils.a(this.a.l, ContestData.ContestInfoResponse.class);
            }
        }
    }

    public static class SubmitScoreResponse {
    }

}

