package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.utils.JsonUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SongSuggestionManager {
    public static final String f20227a = SongSuggestionManager.class.getName();
    private static SongSuggestionManager f20228i = null;
    private Context f20229b;
    private int f20230c = 4;
    private int f20231d = 1;
    private List<String> f20232e = new ArrayList();
    private float f20233f = 1.1f;
    private float f20234g = 0.9f;
    private List<SuggestionsForDay> f20235h = new ArrayList();
    private ArrayDeque<String> f20236j = new ArrayDeque();

    class C41611 implements Comparator<WeightedSuggestion> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m21777a((WeightedSuggestion) obj, (WeightedSuggestion) obj2);
        }

        public int m21777a(WeightedSuggestion weightedSuggestion, WeightedSuggestion weightedSuggestion2) {
            if (weightedSuggestion.f20226a > weightedSuggestion2.f20226a) {
                return 1;
            }
            return weightedSuggestion.f20226a == weightedSuggestion2.f20226a ? 0 : -1;
        }
    }

    private class SuggestionsForDay {
        public int f20223a;
        public List<String> f20224b;
        final /* synthetic */ SongSuggestionManager f20225c;

        private SuggestionsForDay(SongSuggestionManager songSuggestionManager) {
            this.f20225c = songSuggestionManager;
        }
    }

    private class WeightedSuggestion {
        public float f20226a;
    }

    public static SongSuggestionManager m21778a(Context context) {
        if (f20228i == null) {
            f20228i = new SongSuggestionManager(context);
        }
        return f20228i;
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    private SongSuggestionManager(Context context) {
        this.f20229b = context.getApplicationContext();
        for (LinkedHashMap linkedHashMap : AppSettingsManager.a(this.f20229b).a("sing.suggestions", "songsForDay_A", new ArrayList())) {
            if (linkedHashMap.containsKey("day") && linkedHashMap.containsKey("songs")) {
                SuggestionsForDay suggestionsForDay = new SuggestionsForDay();
                suggestionsForDay.f20223a = Integer.parseInt((String) linkedHashMap.get("day"));
                suggestionsForDay.f20224b = (ArrayList) linkedHashMap.get("songs");
                this.f20235h.add(suggestionsForDay);
            }
        }
        JsonNode a = AppSettingsManager.a(this.f20229b).a("sing.suggestions", "suggestionConfig", null);
        if (a != null) {
            if (a.has("suggestedSectionLength")) {
                this.f20230c = a.get("suggestedSectionLength").asInt(4);
            }
            if (a.has("songHistoryLength")) {
                this.f20231d = a.get("songHistoryLength").asInt(1);
            }
            if (a.has("artistSuggestionWeight")) {
                this.f20233f = (float) a.get("artistSuggestionWeight").asDouble(1.100000023841858d);
            }
            if (a.has("genreSuggestionWeight")) {
                this.f20234g = (float) a.get("genreSuggestionWeight").asDouble(0.8999999761581421d);
            }
            if (a.has("overrideSongs")) {
                try {
                    this.f20232e = (List) JsonUtils.m18984a().treeToValue(a.get("overrideSongs"), List.class);
                } catch (Throwable e) {
                    Log.d(f20227a, "Could not parse overrideSongs", e);
                }
            }
        }
        Set<String> stringSet = this.f20229b.getSharedPreferences("sing_prefs", 0).getStringSet("SONG_HISTORY", null);
        if (stringSet != null) {
            for (String add : stringSet) {
                this.f20236j.add(add);
            }
        }
    }

    public void m21779a(String str) {
        this.f20236j.removeLastOccurrence(str);
        this.f20236j.addFirst(str);
        this.f20229b.getSharedPreferences("sing_prefs", 0).edit().putStringSet("SONG_HISTORY", new LinkedHashSet(this.f20236j)).apply();
    }
}
