/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.datasources;

import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.ContactsManager;
import com.smule.android.network.models.MatchedAccount;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;

public class ContactsDataSource
extends MagicDataSource<MatchedAccount, MagicDataSource.OffsetPaginationTracker> {
    private static boolean a;

    public ContactsDataSource() {
        super(ContactsDataSource.class.getSimpleName(), new MagicDataSource.OffsetPaginationTracker());
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<MatchedAccount, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        com.smule.android.network.managers.ContactsManager.a().a(new ContactsManager.FindContactsCallback(){

            @Override
            public void handleResponse(ContactsManager findContactsResponse) {
                if (findContactsResponse.a()) {
                    Collections.sort(findContactsResponse.notFollowing, new Comparator<MatchedAccount>(){

                        public int a(MatchedAccount matchedAccount, MatchedAccount matchedAccount2) {
                            return matchedAccount.realName.toLowerCase().compareTo(matchedAccount2.realName.toLowerCase());
                        }

                        @Override
                        public /* synthetic */ int compare(Object object, Object object2) {
                            return this.a((MatchedAccount)object, (MatchedAccount)object2);
                        }
                    });
                    a = findContactsResponse.hasMatches;
                    fetchDataCallback.a(findContactsResponse.notFollowing, new MagicDataSource.OffsetPaginationTracker(-1));
                    return;
                }
                fetchDataCallback.a();
            }

        });
        return null;
    }

    @Override
    public void c() {
        super.c();
    }

    public boolean l_() {
        return a;
    }

}

