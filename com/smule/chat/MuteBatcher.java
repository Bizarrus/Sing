/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.models.SNPChat;
import com.smule.chat.CollectionBatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MuteBatcher {
    private static final MuteBatcher b = new MuteBatcher();
    private CollectionBatcher<SNPChat, SparkManager> a;

    private MuteBatcher() {
        this.a = new CollectionBatcher<SNPChat, SparkManager>(300, 0){

            SparkManager a(Set<SNPChat> set) {
                return com.smule.android.network.managers.SparkManager.a().a(set);
            }

            @Override
            /* synthetic */ Object b(Set set) {
                return this.a(set);
            }
        };
    }

    public static MuteBatcher a() {
        return b;
    }

    public void a(SNPChat sNPChat, SparkManager.MuteStateResponseCallback muteStateResponseCallback) {
        this.a.a(new MuteAggregator(sNPChat, muteStateResponseCallback));
    }

    private static class MuteAggregator
    extends CollectionBatcher.CallbackAggregator<SNPChat, SparkManager> {
        private SparkManager.MuteStateResponseCallback b;
        private SparkManager c;

        public MuteAggregator(SNPChat sNPChat, SparkManager.MuteStateResponseCallback muteStateResponseCallback) {
            super(new HashSet<SNPChat>(Collections.singleton(sNPChat)));
            this.b = muteStateResponseCallback;
        }

        @Override
        public void a() {
            this.b.handleResponse(this.c);
        }

        @Override
        public void a(SparkManager object) {
            if (this.c == null) {
                this.c = new ParsedResponse(){
                    @com.fasterxml.jackson.annotation.JsonProperty(value="mute")
                    public List<SNPChat> muted;
                    @com.fasterxml.jackson.annotation.JsonProperty(value="notMute")
                    public List<SNPChat> notMuted;

                    static SparkManager a(NetworkResponse networkResponse) {
                        return SparkManager.a(networkResponse, SparkManager.class);
                    }
                };
                this.c.muted = new ArrayList<SNPChat>();
                this.c.notMuted = new ArrayList<SNPChat>();
            }
            if (object != null && object.a()) {
                this.c.a = object.a;
                for (SNPChat sNPChat : object.muted) {
                    if (!this.a.contains(sNPChat)) continue;
                    this.c.muted.add(sNPChat);
                }
                for (SNPChat sNPChat : object.notMuted) {
                    if (!this.a.contains(sNPChat)) continue;
                    this.c.notMuted.add(sNPChat);
                }
            }
        }
    }

}

