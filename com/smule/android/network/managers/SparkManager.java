package com.smule.android.network.managers;

import com.smule.android.network.api.SparkAPI;
import com.smule.android.network.api.SparkAPI$GetActiveChatsRequest;
import com.smule.android.network.api.SparkAPI$GetMuteStateRequest;
import com.smule.android.network.api.SparkAPI$SetActiveChatsRequest;
import com.smule.android.network.api.SparkAPI$SetMuteStateRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.SNPChat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

public class SparkManager {
    private static SparkManager f17102a;
    private SparkAPI f17103b = ((SparkAPI) MagicNetwork.a().a(SparkAPI.class));

    class C36141 implements Runnable {
        final /* synthetic */ Collection f17085a;
        final /* synthetic */ Collection f17086b;
        final /* synthetic */ ActiveChatsUpdateCallback f17087c;
        final /* synthetic */ SparkManager f17088d;

        public void run() {
            NetworkResponse a = this.f17088d.m18360a(this.f17085a, this.f17086b);
            if (this.f17087c != null) {
                CoreUtil.m18079a(this.f17087c, a);
            }
        }
    }

    class C36163 implements Runnable {
        final /* synthetic */ Collection f17093a;
        final /* synthetic */ MuteStateResponseCallback f17094b;
        final /* synthetic */ SparkManager f17095c;

        public void run() {
            MuteStateResponse a = this.f17095c.m18361a(this.f17093a);
            if (this.f17094b != null) {
                CoreUtil.m18079a(this.f17094b, a);
            }
        }
    }

    public interface ActiveChatsCallback extends ResponseInterface<ActiveChatsResponse> {
    }

    public interface ActiveChatsUpdateCallback extends ResponseInterface<NetworkResponse> {
    }

    public interface MuteStateResponseCallback extends ResponseInterface<MuteStateResponse> {
        void handleResponse(MuteStateResponse muteStateResponse);
    }

    public interface MuteStateUpdateCallback extends ResponseInterface<MuteStateUpdateResponse> {
        void handleResponse(MuteStateUpdateResponse muteStateUpdateResponse);
    }

    public static class MuteStateUpdateResponse extends ParsedResponse {
    }

    public interface OfflineMessageCallback extends ResponseInterface<OfflineMessageResponse> {
        void handleResponse(OfflineMessageResponse offlineMessageResponse);
    }

    public static SparkManager m18359a() {
        if (f17102a == null) {
            f17102a = new SparkManager();
        }
        return f17102a;
    }

    private SparkManager() {
    }

    public NetworkResponse m18360a(Collection<SNPChat> collection, Collection<SNPChat> collection2) {
        return NetworkUtils.m18104a(this.f17103b.setActiveChats(new SparkAPI$SetActiveChatsRequest().setAdd(collection).setRemove(collection2)));
    }

    public Future<?> m18364a(final String str, final int i, final ActiveChatsCallback activeChatsCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SparkManager f17092d;

            public void run() {
                ActiveChatsResponse a = ActiveChatsResponse.a(NetworkUtils.m18104a(this.f17092d.f17103b.getActiveChats(new SparkAPI$GetActiveChatsRequest().setType(str).setLimit(Integer.valueOf(i)))));
                if (activeChatsCallback != null) {
                    CoreUtil.m18079a(activeChatsCallback, a);
                }
            }
        });
    }

    public MuteStateResponse m18361a(Collection<SNPChat> collection) {
        return MuteStateResponse.a(NetworkUtils.m18104a(this.f17103b.getMuteState(new SparkAPI$GetMuteStateRequest().setChats(collection))));
    }

    public Future<?> m18363a(SNPChat sNPChat, MuteStateUpdateCallback muteStateUpdateCallback) {
        return m18365a(Collections.singletonList(sNPChat), muteStateUpdateCallback);
    }

    public Future<?> m18365a(List<SNPChat> list, MuteStateUpdateCallback muteStateUpdateCallback) {
        return m18366a((List) list, Collections.emptyList(), muteStateUpdateCallback);
    }

    public Future<?> m18367b(SNPChat sNPChat, MuteStateUpdateCallback muteStateUpdateCallback) {
        return m18368b(Collections.singletonList(sNPChat), muteStateUpdateCallback);
    }

    public Future<?> m18368b(List<SNPChat> list, MuteStateUpdateCallback muteStateUpdateCallback) {
        return m18366a(Collections.emptyList(), (List) list, muteStateUpdateCallback);
    }

    public Future<?> m18366a(final List<SNPChat> list, final List<SNPChat> list2, final MuteStateUpdateCallback muteStateUpdateCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SparkManager f17099d;

            public void run() {
                NetworkResponse a = NetworkUtils.m18104a(this.f17099d.f17103b.setMuteState(new SparkAPI$SetMuteStateRequest().setAdd(list).setRemove(list2)));
                if (muteStateUpdateCallback != null) {
                    CoreUtil.m18079a(muteStateUpdateCallback, MuteStateUpdateResponse.a(a, MuteStateUpdateResponse.class));
                }
            }
        });
    }

    public Future<?> m18362a(final OfflineMessageCallback offlineMessageCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SparkManager f17101b;

            public void run() {
                NetworkResponse a = NetworkUtils.m18104a(this.f17101b.f17103b.getOfflineMessageCount(new SnpRequest()));
                if (offlineMessageCallback != null) {
                    CoreUtil.m18079a(offlineMessageCallback, OfflineMessageResponse.a(a, OfflineMessageResponse.class));
                }
            }
        });
    }
}
