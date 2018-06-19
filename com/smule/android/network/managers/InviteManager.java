package com.smule.android.network.managers;

import com.smule.android.network.api.InviteAPI;
import com.smule.android.network.api.InviteAPI$ListInvitesRequest;
import com.smule.android.network.api.InviteAPI$SendInvitesRequest;
import com.smule.android.network.api.InviteAPI$SendInvitesToAllFollowersRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ResponseInterface;
import java.util.Collection;
import java.util.concurrent.Future;

public class InviteManager {
    private static final String f16688a = InviteManager.class.getName();
    private static InviteManager f16689b;
    private InviteAPI f16690c = ((InviteAPI) MagicNetwork.a().a(InviteAPI.class));

    class C35735 implements Runnable {
        final /* synthetic */ Collection f16678a;
        final /* synthetic */ boolean f16679b;
        final /* synthetic */ String f16680c;
        final /* synthetic */ int f16681d;
        final /* synthetic */ SendingInvitesResponseCallback f16682e;
        private boolean f16683f;
        private boolean f16684g;
        private boolean f16685h;
        private boolean f16686i;
        private int f16687j;

        class C35711 implements NetworkResponseCallback {
            final /* synthetic */ C35735 f16676a;

            C35711(C35735 c35735) {
                this.f16676a = c35735;
            }

            public void handleResponse(NetworkResponse networkResponse) {
                if (networkResponse.c()) {
                    this.f16676a.f16685h = true;
                    this.f16676a.f16687j = this.f16676a.f16687j + this.f16676a.f16681d;
                } else if (networkResponse.b == 1026) {
                    this.f16676a.f16685h = false;
                } else {
                    this.f16676a.f16682e.m18237a();
                    return;
                }
                if (this.f16676a.f16684g) {
                    this.f16676a.f16682e.m18238a(this.f16676a.f16679b, this.f16676a.f16685h, this.f16676a.f16683f, this.f16676a.f16686i, this.f16676a.f16687j);
                } else {
                    this.f16676a.f16684g = true;
                }
            }
        }

        class C35722 implements NetworkResponseCallback {
            final /* synthetic */ C35735 f16677a;

            C35722(C35735 c35735) {
                this.f16677a = c35735;
            }

            public void handleResponse(NetworkResponse networkResponse) {
                if (networkResponse.c()) {
                    this.f16677a.f16686i = true;
                    this.f16677a.f16687j = this.f16677a.f16687j + this.f16677a.f16678a.size();
                    if (this.f16677a.f16684g) {
                        this.f16677a.f16682e.m18238a(this.f16677a.f16679b, this.f16677a.f16685h, this.f16677a.f16683f, this.f16677a.f16686i, this.f16677a.f16687j);
                        return;
                    } else {
                        this.f16677a.f16684g = true;
                        return;
                    }
                }
                this.f16677a.f16682e.m18237a();
            }
        }

        public void run() {
            if (this.f16679b) {
                InviteManager.m18240a().m18243a(this.f16680c, new C35711(this));
            }
            if (!this.f16678a.isEmpty()) {
                InviteManager.m18240a().m18245a(this.f16680c, this.f16678a, new C35722(this));
            }
        }
    }

    public interface ListInvitesResponseCallback extends ResponseInterface<ListInvitesResponse> {
        void handleResponse(ListInvitesResponse listInvitesResponse);
    }

    public interface SendingInvitesResponseCallback {
        void m18237a();

        void m18238a(boolean z, boolean z2, boolean z3, boolean z4, int i);
    }

    private InviteManager() {
    }

    public static synchronized InviteManager m18240a() {
        InviteManager inviteManager;
        synchronized (InviteManager.class) {
            if (f16689b == null) {
                f16689b = new InviteManager();
            }
            inviteManager = f16689b;
        }
        return inviteManager;
    }

    public ListInvitesResponse m18241a(String str, Integer num, Integer num2, String str2) {
        if ("FOLLOWING".equals(str2)) {
            return ListInvitesResponse.a(NetworkUtils.m18104a(this.f16690c.listInvitesFollower(new InviteAPI$ListInvitesRequest().setApp(str).setOffset(num).setLimit(num2))));
        }
        return ListInvitesResponse.a(NetworkUtils.m18104a(this.f16690c.listInvites(new InviteAPI$ListInvitesRequest().setApp(str).setOffset(num).setLimit(num2))));
    }

    public Future<?> m18244a(String str, Integer num, Integer num2, String str2, ListInvitesResponseCallback listInvitesResponseCallback) {
        final ListInvitesResponseCallback listInvitesResponseCallback2 = listInvitesResponseCallback;
        final String str3 = str;
        final Integer num3 = num;
        final Integer num4 = num2;
        final String str4 = str2;
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ InviteManager f16664f;

            public void run() {
                CoreUtil.m18079a(listInvitesResponseCallback2, this.f16664f.m18241a(str3, num3, num4, str4));
            }
        });
    }

    public Future<?> m18242a(Integer num, Integer num2, String str, ListInvitesResponseCallback listInvitesResponseCallback) {
        return m18244a(null, num, num2, str, listInvitesResponseCallback);
    }

    public Future<?> m18245a(final String str, final Collection<Long> collection, final NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ InviteManager f16668d;

            public void run() {
                CoreUtil.m18079a(networkResponseCallback, NetworkUtils.m18104a(this.f16668d.f16690c.sendInvites(new InviteAPI$SendInvitesRequest().setPerformanceKey(str).setAccountIds(collection))));
            }
        });
    }

    public Future<?> m18246b(final String str, final Collection<Long> collection, final NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ InviteManager f16672d;

            public void run() {
                CoreUtil.m18079a(networkResponseCallback, NetworkUtils.m18104a(this.f16672d.f16690c.sendChatInvites(new InviteAPI$SendInvitesRequest().setPerformanceKey(str).setAccountIds(collection))));
            }
        });
    }

    public Future<?> m18243a(final String str, final NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ InviteManager f16675c;

            public void run() {
                CoreUtil.m18079a(networkResponseCallback, NetworkUtils.m18104a(this.f16675c.f16690c.sendInvitesToAllFollowers(new InviteAPI$SendInvitesToAllFollowersRequest().setPerformanceKey(str))));
            }
        });
    }
}
