package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class TopicsAPI$SubmitChosenTopicsRequest extends SnpRequest {
    public Boolean compositionChoices;
    public List<SongBookEntryId> inject;
    public List<Integer> topicIds;

    public static class SongBookEntryId {
        public String arrKey;
        public String songId;
        public Type type;

        public enum Type {
            SONG,
            ARR
        }

        public SongBookEntryId(String str, String str2, String str3) throws IllegalArgumentException {
            if (str == null) {
                throw new IllegalArgumentException("type cannot be null");
            } else if (str2 == null && str3 == null) {
                throw new IllegalArgumentException("songId and arrKey cannot both be null");
            } else if (str2 == null || str3 == null) {
                this.type = Type.valueOf(str);
                this.songId = str2;
                this.arrKey = str3;
            } else {
                throw new IllegalArgumentException("songId and arrKey cannot both be set");
            }
        }
    }

    public TopicsAPI$SubmitChosenTopicsRequest setTopicIds(List<Integer> list) {
        this.topicIds = list;
        return this;
    }

    public TopicsAPI$SubmitChosenTopicsRequest setCompositionChoices(Boolean bool) {
        this.compositionChoices = bool;
        return this;
    }

    public TopicsAPI$SubmitChosenTopicsRequest setInject(List<SongBookEntryId> list) {
        this.inject = list;
        return this;
    }
}
