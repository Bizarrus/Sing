/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

public class BlockManager {
    private Set<Long> a = new HashSet<Long>();

    public List<Long> a() {
        return new ArrayList<Long>(this.a);
    }

    public void a(final long l, final boolean bl, Completion<ChatStatus> object) {
        object = new UserManager((Completion)object){
            final /* synthetic */ Completion c;
            {
                this.c = completion;
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(NetworkResponse object) {
                if (object.c()) {
                    if (bl) {
                        BlockManager.this.a.add(l);
                    } else {
                        BlockManager.this.a.remove(l);
                    }
                    object = ChatStatus.a;
                } else {
                    object = ChatStatus.b;
                }
                this.c.a(object);
            }
        };
        if (bl) {
            com.smule.android.network.managers.UserManager.a().a(l, object);
            return;
        }
        com.smule.android.network.managers.UserManager.a().b(l, object);
    }

    public void a(final Runnable runnable) {
        com.smule.android.network.managers.UserManager.a().a(new UserManager(){

            @Override
            public void handleResponse(UserManager.BlockedUsersResponse blockedUsersResponse) {
                if (blockedUsersResponse.a()) {
                    BlockManager.this.a.clear();
                    BlockManager.this.a.addAll(blockedUsersResponse.accountIds);
                }
                runnable.run();
            }
        });
    }

    public boolean a(long l) {
        return this.a.contains(l);
    }

}

