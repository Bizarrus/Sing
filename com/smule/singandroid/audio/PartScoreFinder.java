package com.smule.singandroid.audio;

import com.smule.android.logging.Log;
import com.smule.singandroid.models.Lyric;
import java.util.ArrayList;

public class PartScoreFinder {
    static final String f20668a = PartScoreFinder.class.getSimpleName();

    public static class PartScore {
        public int f20664a = 0;
        public int f20665b = 0;
        public int f20666c = 0;
        public int f20667d = 0;
    }

    public static PartScore m22285a(ArrayList<Lyric> arrayList, ArrayList<AudioPowerEvent> arrayList2, int i) {
        PartScore partScore = new PartScore();
        if (arrayList.isEmpty()) {
            partScore.f20664a = -1;
            return partScore;
        }
        int i2 = 0;
        Lyric lyric = (Lyric) arrayList.get(0);
        Object obj = null;
        while (i2 < arrayList.size()) {
            Object obj2;
            Lyric lyric2;
            if (i2 == arrayList.size() - 1) {
                obj2 = 1;
            } else {
                obj2 = obj;
            }
            Lyric lyric3 = (Lyric) arrayList.get(i2);
            if (lyric3.f23490i == lyric.f23490i && obj2 == null) {
                lyric2 = lyric;
            } else {
                Object obj3 = (i == lyric.f23490i || lyric.f23490i == 3) ? 1 : null;
                if (obj3 != null) {
                    partScore.f20664a++;
                } else {
                    partScore.f20666c++;
                }
                for (int i3 = 0; i3 < arrayList2.size() - 1; i3++) {
                    AudioPowerEvent audioPowerEvent = (AudioPowerEvent) arrayList2.get(i3);
                    if (audioPowerEvent.isOn) {
                        AudioPowerEvent audioPowerEvent2 = (AudioPowerEvent) arrayList2.get(i3 + 1);
                        if (audioPowerEvent2.isOn) {
                            Log.e(f20668a, "unexpected event sequence!");
                        }
                        if (((double) audioPowerEvent.offset) > lyric3.f23483b) {
                            break;
                        } else if (((double) audioPowerEvent2.offset) >= lyric.f23483b) {
                            if (obj3 != null) {
                                partScore.f20665b++;
                            } else {
                                partScore.f20667d++;
                            }
                        }
                    }
                }
                lyric2 = lyric3;
            }
            i2++;
            lyric = lyric2;
            obj = obj2;
        }
        return partScore;
    }
}
