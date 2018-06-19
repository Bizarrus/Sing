package com.smule.android.network.api;

import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import com.smule.android.logging.EventLogger2$Event;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.UserManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventLogger2API$PostEventsRequest extends SnpRequest {
    public String app = MagicNetwork.b();
    public List<Object> events;

    public EventLogger2API$PostEventsRequest setEvents(List<EventLogger2$Event> list) {
        List arrayList = new ArrayList();
        for (EventLogger2$Event eventLogger2$Event : list) {
            if (TextUtils.isEmpty(eventLogger2$Event.f16359b)) {
                String str = "eventType empty:" + eventLogger2$Event;
                Log.d(EventLogger2API.TAG, str, new Exception(str));
            } else {
                Map hashMap = new HashMap();
                hashMap.put("ts", Long.valueOf((long) ((double) eventLogger2$Event.f16358a)));
                hashMap.put("app", this.app);
                hashMap.put("plyrId", Long.valueOf(UserManager.a().g()));
                hashMap.put("event", eventLogger2$Event.f16359b);
                if (eventLogger2$Event.f16360c != null) {
                    hashMap.put("target", eventLogger2$Event.f16360c);
                }
                if (eventLogger2$Event.f16361d != null) {
                    hashMap.put(PlaceFields.CONTEXT, eventLogger2$Event.f16361d);
                }
                if (eventLogger2$Event.f16362e != null) {
                    hashMap.put("value", eventLogger2$Event.f16362e);
                }
                if (eventLogger2$Event.f16363f != null) {
                    hashMap.put("k1", eventLogger2$Event.f16363f);
                }
                if (eventLogger2$Event.f16364g != null) {
                    hashMap.put("k2", eventLogger2$Event.f16364g);
                }
                if (eventLogger2$Event.f16365h != null) {
                    hashMap.put("k3", eventLogger2$Event.f16365h);
                }
                if (eventLogger2$Event.f16366i != null) {
                    hashMap.put("k4", eventLogger2$Event.f16366i);
                }
                if (eventLogger2$Event.f16367j != null) {
                    hashMap.put("k5", eventLogger2$Event.f16367j);
                }
                if (eventLogger2$Event.f16368k != null) {
                    hashMap.put("k6", eventLogger2$Event.f16368k);
                }
                if (eventLogger2$Event.f16369l != null) {
                    hashMap.put("k7", eventLogger2$Event.f16369l);
                }
                if (eventLogger2$Event.f16370m != null) {
                    hashMap.put("k8", eventLogger2$Event.f16370m);
                }
                if (eventLogger2$Event.f16371n != null) {
                    hashMap.put("k9", eventLogger2$Event.f16371n);
                }
                arrayList.add(hashMap);
            }
        }
        this.events = arrayList;
        return this;
    }
}
