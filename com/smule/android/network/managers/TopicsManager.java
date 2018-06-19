package com.smule.android.network.managers;

import com.smule.android.network.api.TopicsAPI;
import com.smule.android.network.api.TopicsAPI$GetTopicOptionsRequest;
import com.smule.android.network.api.TopicsAPI$SelectTopicsRequest;
import com.smule.android.network.api.TopicsAPI$SubmitChosenTopicsRequest;
import com.smule.android.network.api.TopicsAPI$SubmitChosenTopicsRequest.SongBookEntryId;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ResponseInterface;
import java.util.ArrayList;
import java.util.concurrent.Future;

public class TopicsManager {
    private static final String f17191a = TopicsManager.class.getName();
    private static TopicsManager f17192b;
    private TopicsAPI f17193c = ((TopicsAPI) MagicNetwork.a().a(TopicsAPI.class));

    class C36312 implements Runnable {
        final /* synthetic */ SelectTopicsResponseListener f17182a;
        final /* synthetic */ ArrayList f17183b;
        final /* synthetic */ boolean f17184c;
        final /* synthetic */ TopicsManager f17185d;

        public void run() {
            CoreUtil.m18079a(this.f17182a, this.f17185d.m18460a(this.f17183b, this.f17184c));
        }
    }

    public interface GetTopicOptionsResponseListener extends ResponseInterface<GetTopicOptionsResponse> {
        void handleResponse(GetTopicOptionsResponse getTopicOptionsResponse);
    }

    public interface SelectTopicsResponseListener extends ResponseInterface<SelectTopicsResponse> {
        void handleResponse(SelectTopicsResponse selectTopicsResponse);
    }

    public interface SubmitChosenTopicsResponseListener extends ResponseInterface<SubmitChosenTopicsResponse> {
        void handleResponse(SubmitChosenTopicsResponse submitChosenTopicsResponse);
    }

    public static synchronized TopicsManager m18458a() {
        TopicsManager topicsManager;
        synchronized (TopicsManager.class) {
            if (f17192b == null) {
                f17192b = new TopicsManager();
            }
            topicsManager = f17192b;
        }
        return topicsManager;
    }

    private TopicsManager() {
    }

    public GetTopicOptionsResponse m18459a(Integer num, Integer num2) {
        return GetTopicOptionsResponse.a(NetworkUtils.m18104a(this.f17193c.getTopicOptions(new TopicsAPI$GetTopicOptionsRequest().setOffset(num).setLimit(num2))));
    }

    public Future<?> m18462a(final Integer num, final Integer num2, final GetTopicOptionsResponseListener getTopicOptionsResponseListener) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ TopicsManager f17181d;

            public void run() {
                CoreUtil.m18079a(getTopicOptionsResponseListener, this.f17181d.m18459a(num, num2));
            }
        });
    }

    public SelectTopicsResponse m18460a(ArrayList<Integer> arrayList, boolean z) {
        return SelectTopicsResponse.a(NetworkUtils.m18104a(this.f17193c.selectTopics(new TopicsAPI$SelectTopicsRequest().setTopicIds(arrayList).setCompositionChoices(Boolean.valueOf(z)))));
    }

    public SubmitChosenTopicsResponse m18461a(ArrayList<Integer> arrayList, boolean z, ArrayList<SongBookEntryId> arrayList2) {
        return SubmitChosenTopicsResponse.a(NetworkUtils.m18104a(this.f17193c.submitChosenTopics(new TopicsAPI$SubmitChosenTopicsRequest().setTopicIds(arrayList).setCompositionChoices(Boolean.valueOf(z)).setInject(arrayList2))));
    }

    public Future<?> m18463a(ArrayList<Integer> arrayList, boolean z, ArrayList<SongBookEntryId> arrayList2, SubmitChosenTopicsResponseListener submitChosenTopicsResponseListener) {
        final SubmitChosenTopicsResponseListener submitChosenTopicsResponseListener2 = submitChosenTopicsResponseListener;
        final ArrayList<Integer> arrayList3 = arrayList;
        final boolean z2 = z;
        final ArrayList<SongBookEntryId> arrayList4 = arrayList2;
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ TopicsManager f17190e;

            public void run() {
                CoreUtil.m18079a(submitChosenTopicsResponseListener2, this.f17190e.m18461a(arrayList3, z2, arrayList4));
            }
        });
    }
}
