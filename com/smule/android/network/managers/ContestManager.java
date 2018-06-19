package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.format.Time;
import com.bluelinelabs.logansquare.LoganSquare;
import com.smule.android.logging.Log;
import com.smule.android.network.api.ContestAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.models.ContestData.ContestInfo;
import com.smule.android.network.models.ContestData.ContestUserState;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class ContestManager {
    private static final String f16567a = ContestManager.class.getName();
    private ContestInfo f16568b;
    private Map<Long, ContestInfo> f16569c = new HashMap();
    private Map<Long, ContestUserState> f16570d = new HashMap();
    private long f16571e = 0;
    private Timer f16572f;
    private Context f16573g;
    private ContestAPI f16574h = ((ContestAPI) MagicNetwork.a().a(ContestAPI.class));
    private Observer f16575i = new C35461(this);

    class C35461 implements Observer {
        final /* synthetic */ ContestManager f16562a;

        C35461(ContestManager contestManager) {
            this.f16562a = contestManager;
        }

        public void update(Observable observable, Object obj) {
            if ("USER_EXISTENCE_TYPE_EXISTING".equals((String) obj)) {
                this.f16562a.f16568b = null;
                this.f16562a.f16569c = new HashMap();
            }
            this.f16562a.m18141a();
        }
    }

    class C35472 implements Runnable {
        final /* synthetic */ ContestManager f16563a;

        C35472(ContestManager contestManager) {
            this.f16563a = contestManager;
        }

        public void run() {
            ContestInfoResponse b = this.f16563a.m18142b();
            if (b != null && b.f16565a.c()) {
                this.f16563a.m18136a(b.f16566b.contestInfos);
            }
        }
    }

    class C35483 extends TimerTask {
        final /* synthetic */ ContestManager f16564a;

        C35483(ContestManager contestManager) {
            this.f16564a = contestManager;
        }

        public void run() {
            NotificationCenter.m19011a().m19017b("CONTEST_STATE_MIGHT_HAVE_CHANGED", new Object[0]);
            this.f16564a.m18138e();
        }
    }

    class C35494 implements Comparator<ContestInfo> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m18130a((ContestInfo) obj, (ContestInfo) obj2);
        }

        public int m18130a(ContestInfo contestInfo, ContestInfo contestInfo2) {
            return contestInfo.contest.id.compareTo(contestInfo2.contest.id);
        }
    }

    public static class ContestInfoResponse {
        public NetworkResponse f16565a;
        public com.smule.android.network.models.ContestData.ContestInfoResponse f16566b;

        ContestInfoResponse(NetworkResponse networkResponse) {
            this.f16565a = networkResponse;
            if (this.f16565a != null && this.f16565a.l != null) {
                this.f16566b = (com.smule.android.network.models.ContestData.ContestInfoResponse) JsonUtils.m18985a(this.f16565a.l, com.smule.android.network.models.ContestData.ContestInfoResponse.class);
            }
        }
    }

    public static class SubmitScoreResponse {
    }

    private ContestManager() {
    }

    public void m18141a() {
        if (this.f16568b == null) {
            MagicNetwork.a(new C35472(this));
        }
    }

    private void m18137d() {
        Editor edit = this.f16573g.getSharedPreferences("CONTEST_SETTINGS", 0).edit();
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        for (Entry entry : this.f16569c.entrySet()) {
            hashMap.put(((Long) entry.getKey()).toString(), entry.getValue());
        }
        for (Entry entry2 : this.f16570d.entrySet()) {
            hashMap2.put(((Long) entry2.getKey()).toString(), entry2.getValue());
        }
        try {
            edit.putString("version", "1.0").putString("contests", LoganSquare.a(hashMap)).putString("states", LoganSquare.a(hashMap2));
        } catch (Throwable e) {
            Log.d(f16567a, "Error serializing object to JSON", e);
        } finally {
            edit.apply();
        }
    }

    private void m18138e() {
        m18139f();
        Date c = m18143c();
        this.f16572f = new Timer();
        this.f16572f.schedule(new C35483(this), c);
    }

    private void m18139f() {
        this.f16572f.cancel();
        this.f16572f.purge();
    }

    public ContestInfoResponse m18142b() {
        return new ContestInfoResponse(NetworkUtils.m18104a(this.f16574h.getContestInfo(ContestAPI.CURRENT_CONTEST_INFO_REQUEST)));
    }

    private void m18136a(List<ContestInfo> list) {
        for (ContestInfo contestInfo : list) {
            ContestUserState a = m18140a(contestInfo);
            if (contestInfo.rank != null) {
                a.rank = contestInfo.rank;
            }
            if (contestInfo.score != null) {
                a.score = contestInfo.score;
            }
            if (!(contestInfo.rank == null || contestInfo.score == null)) {
                a.started = Boolean.valueOf(true);
            }
            if (!contestInfo.isEnded()) {
                this.f16568b = contestInfo;
            }
            this.f16569c.put(contestInfo.contest.id, contestInfo);
        }
        Long valueOf = Long.valueOf(Long.valueOf(System.currentTimeMillis() / 1000).longValue() - 345600);
        Iterator it = this.f16569c.entrySet().iterator();
        while (it.hasNext()) {
            if (((ContestInfo) ((Entry) it.next()).getValue()).contest.end.longValue() < valueOf.longValue()) {
                it.remove();
            }
        }
        Iterator it2 = this.f16570d.entrySet().iterator();
        while (it2.hasNext()) {
            if (!this.f16569c.containsKey(((Entry) it2.next()).getKey())) {
                it2.remove();
            }
        }
        m18137d();
        this.f16571e = System.currentTimeMillis();
        NotificationCenter.m19011a().m19017b("CONTEST_STATE_MIGHT_HAVE_CHANGED", new Object[0]);
    }

    public ContestUserState m18140a(ContestInfo contestInfo) {
        ContestUserState contestUserState = (ContestUserState) this.f16570d.get(contestInfo.contest.id);
        if (contestUserState != null) {
            return contestUserState;
        }
        contestUserState = new ContestUserState();
        contestUserState.reported = Boolean.valueOf(false);
        contestUserState.started = Boolean.valueOf(false);
        this.f16570d.put(contestInfo.contest.id, contestUserState);
        return contestUserState;
    }

    public Date m18143c() {
        if (this.f16568b != null && !this.f16568b.isEnded()) {
            return new Date(this.f16568b.contest.end.longValue() * 1000);
        }
        Time time = new Time();
        time.setToNow();
        Time a = m18131a(time);
        if (time.before(a)) {
            return new Date(a.toMillis(false));
        }
        a = new Time();
        a.set(time.toMillis(false) + 86400000);
        return new Date(m18131a(a).toMillis(false));
    }

    private Time m18131a(Time time) {
        Time time2 = new Time();
        time2.switchTimezone("America/Los_Angeles");
        time2.set(0, 0, 12, time.monthDay, time.month, time.year);
        return time2;
    }
}
