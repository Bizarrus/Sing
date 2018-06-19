package com.smule.chat;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$BlockUsersResponseCallback;
import com.smule.android.network.managers.UserManager$BlockedUsersResponseCallback;
import com.smule.android.network.managers.UserManager.BlockedUsersResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockManager {
    private Set<Long> f17921a = new HashSet();

    public void m19127a(final Runnable runnable) {
        UserManager.a().a(new UserManager$BlockedUsersResponseCallback(this) {
            final /* synthetic */ BlockManager f17916b;

            public void handleResponse(BlockedUsersResponse blockedUsersResponse) {
                if (blockedUsersResponse.a()) {
                    this.f17916b.f17921a.clear();
                    this.f17916b.f17921a.addAll(blockedUsersResponse.accountIds);
                }
                runnable.run();
            }
        });
    }

    public List<Long> m19125a() {
        return new ArrayList(this.f17921a);
    }

    public boolean m19128a(long j) {
        return this.f17921a.contains(Long.valueOf(j));
    }

    public void m19126a(long j, boolean z, Completion<ChatStatus> completion) {
        final boolean z2 = z;
        final long j2 = j;
        final Completion<ChatStatus> completion2 = completion;
        UserManager$BlockUsersResponseCallback c36782 = new UserManager$BlockUsersResponseCallback(this) {
            final /* synthetic */ BlockManager f17920d;

            public void handleResponse(NetworkResponse networkResponse) {
                Object obj;
                if (networkResponse.c()) {
                    if (z2) {
                        this.f17920d.f17921a.add(Long.valueOf(j2));
                    } else {
                        this.f17920d.f17921a.remove(Long.valueOf(j2));
                    }
                    obj = ChatStatus.OK;
                } else {
                    obj = ChatStatus.NETWORK_ERROR;
                }
                completion2.mo6329a(obj);
            }
        };
        if (z) {
            UserManager.a().a(j, c36782);
        } else {
            UserManager.a().b(j, c36782);
        }
    }
}
