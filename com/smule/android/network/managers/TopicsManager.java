/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.TopicsAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.SongbookEntryId;
import com.smule.android.network.models.Topic;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import retrofit2.Call;

public class TopicsManager {
    private static final String a = TopicsManager.class.getName();
    private static TopicsManager b;
    private com.smule.android.network.api.TopicsAPI c = MagicNetwork.a().a(com.smule.android.network.api.TopicsAPI.class);

    private TopicsManager() {
    }

    public static TopicsManager a() {
        synchronized (TopicsManager.class) {
            if (b == null) {
                b = new TopicsManager();
            }
            TopicsManager topicsManager = b;
            return topicsManager;
        }
    }

    public  a(Integer n, Integer n2) {
        return .a(NetworkUtils.a(this.c.getTopicOptions(new SnpRequest(){
            public Integer limit = 6;
            public Integer offset = 0;

            public TopicsAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public TopicsAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }
        }.setOffset(n).setLimit(n2))));
    }

    public  a(ArrayList<Integer> arrayList, boolean bl) {
        return .a(NetworkUtils.a(this.c.selectTopics(new SnpRequest(){
            public Boolean compositionChoices;
            public List<Integer> topicIds;

            public TopicsAPI setCompositionChoices(Boolean bl) {
                this.compositionChoices = bl;
                return this;
            }

            public TopicsAPI setTopicIds(List<Integer> list) {
                this.topicIds = list;
                return this;
            }
        }.setTopicIds(arrayList).setCompositionChoices(bl))));
    }

    public  a(ArrayList<Integer> arrayList, boolean bl, ArrayList<SongbookEntryId> arrayList2) {
        return .a(NetworkUtils.a(this.c.submitChosenTopics(new SnpRequest(){
            public Boolean compositionChoices;
            public List<SongbookEntryId> inject;
            public List<Integer> topicIds;

            public TopicsAPI setCompositionChoices(Boolean bl) {
                this.compositionChoices = bl;
                return this;
            }

            public TopicsAPI setInject(List<SongbookEntryId> list) {
                this.inject = list;
                return this;
            }

            public TopicsAPI setTopicIds(List<Integer> list) {
                this.topicIds = list;
                return this;
            }
        }.setTopicIds(arrayList).setCompositionChoices(bl).setInject(arrayList2))));
    }

    public Future<?> a(final Integer n, final Integer n2, final GetTopicOptionsResponseListener getTopicOptionsResponseListener) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(getTopicOptionsResponseListener, TopicsManager.this.a(n, n2));
            }
        });
    }

    public Future<?> a(final ArrayList<Integer> arrayList, final boolean bl, final ArrayList<SongbookEntryId> arrayList2, final SubmitChosenTopicsResponseListener submitChosenTopicsResponseListener) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(submitChosenTopicsResponseListener, TopicsManager.this.a(arrayList, bl, arrayList2));
            }
        });
    }

    public static interface GetTopicOptionsResponseListener
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface SelectTopicsResponseListener
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface SubmitChosenTopicsResponseListener
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

}

