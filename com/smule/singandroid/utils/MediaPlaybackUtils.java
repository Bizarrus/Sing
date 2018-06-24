package com.smule.singandroid.utils;

public class MediaPlaybackUtils {
    public static String m25872a(int i) {
        String str = null;
        switch (i) {
            case 0:
                str = "PlaybackStateCompat.STATE_NONE";
                break;
            case 1:
                str = "PlaybackStateCompat.STATE_STOPPED";
                break;
            case 2:
                str = "PlaybackStateCompat.STATE_PAUSED";
                break;
            case 3:
                str = "PlaybackStateCompat.STATE_PLAYING";
                break;
            case 4:
                str = "PlaybackStateCompat.STATE_FAST_FORWARDING";
                break;
            case 5:
                str = "PlaybackStateCompat.STATE_REWINDING";
                break;
            case 6:
                str = "PlaybackStateCompat.STATE_BUFFERING";
                break;
            case 7:
                str = "PlaybackStateCompat.STATE_ERROR";
                break;
            case 8:
                str = "PlaybackStateCompat.STATE_CONNECTING";
                break;
            case 9:
                str = "PlaybackStateCompat.STATE_SKIPPING_TO_PREVIOUS";
                break;
            case 10:
                str = "PlaybackStateCompat.STATE_SKIPPING_TO_NEXT";
                break;
            case 11:
                str = "PlaybackStateCompat.STATE_SKIPPING_TO_QUEUE_ITEM";
                break;
        }
        if (str == null) {
            return "Invalid PlaybackStateCompat: " + i;
        }
        return str + " - " + i;
    }

    public static String m25873b(int i) {
        String str = null;
        switch (i) {
            case 1:
                str = "ExoPlayer.STATE_IDLE";
                break;
            case 2:
                str = "ExoPlayer.STATE_PREPARING";
                break;
            case 3:
                str = "ExoPlayer.STATE_BUFFERING";
                break;
            case 4:
                str = "ExoPlayer.STATE_READY";
                break;
            case 5:
                str = "ExoPlayer.STATE_ENDED";
                break;
        }
        if (str == null) {
            return "Invalid ExoPlayer state: " + i;
        }
        return str + " - " + i;
    }
}
