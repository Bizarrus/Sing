package com.smule.singandroid.utils;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.smule.android.logging.Log;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseActivity;

public class AppIndexer {
    public static final String f24652a = AppIndexer.class.getSimpleName();
    public static final Uri f24653b = Uri.parse("http://www.smule.com/song");
    private GoogleApiClient f24654c;

    public void m25803a(Context context) {
        this.f24654c = new Builder(context).addApi(AppIndex.APP_INDEX_API).build();
    }

    public static String m25799a(SongbookEntry songbookEntry) {
        if (!songbookEntry.m18772r()) {
            return songbookEntry.mo6291e() + " by " + songbookEntry.mo6292f();
        }
        if (((ArrangementVersionLiteEntry) songbookEntry).m18777b()) {
            return songbookEntry.mo6291e();
        }
        return songbookEntry.mo6291e() + " by " + songbookEntry.mo6292f() + " Version";
    }

    public static Uri m25800b(SongbookEntry songbookEntry) {
        return BaseActivity.b.buildUpon().appendPath("sing").appendPath(songbookEntry.m18772r() ? "arrangement" : "song").appendPath(songbookEntry.mo6289c()).build();
    }

    public static Uri m25801c(SongbookEntry songbookEntry) {
        if (songbookEntry.m18772r()) {
            return f24653b.buildUpon().appendPath(songbookEntry.mo6289c()).appendPath("arrangement").build();
        }
        return f24653b.buildUpon().appendPath(songbookEntry.mo6289c()).build();
    }

    public void m25806d(SongbookEntry songbookEntry) {
        Log.b(f24652a, "startAction: " + songbookEntry + " / " + m25799a(songbookEntry) + " / " + m25801c(songbookEntry) + " / " + m25800b(songbookEntry));
        m25804a(Action.newAction(Action.TYPE_LISTEN, m25799a(songbookEntry), m25801c(songbookEntry), m25800b(songbookEntry)));
    }

    public void m25804a(final Action action) {
        this.f24654c.connect();
        AppIndex.AppIndexApi.start(this.f24654c, action).setResultCallback(new ResultCallback<Status>(this) {
            final /* synthetic */ AppIndexer f24649b;

            public /* synthetic */ void onResult(Result result) {
                m25797a((Status) result);
            }

            public void m25797a(Status status) {
                if (status.isSuccess()) {
                    Log.b(AppIndexer.f24652a, "App Indexing API: Recorded indexing action " + action + " successfully.");
                } else {
                    Log.e(AppIndexer.f24652a, "App Indexing API: There was an error recording the indexing action " + status.toString());
                }
            }
        });
    }

    public void m25807e(SongbookEntry songbookEntry) {
        Log.b(f24652a, "endAction: " + songbookEntry + " / " + m25799a(songbookEntry) + " / " + m25801c(songbookEntry) + " / " + m25800b(songbookEntry));
        m25805b(Action.newAction(Action.TYPE_LISTEN, m25799a(songbookEntry), m25801c(songbookEntry), m25800b(songbookEntry)));
    }

    public void m25805b(final Action action) {
        AppIndex.AppIndexApi.end(this.f24654c, action).setResultCallback(new ResultCallback<Status>(this) {
            final /* synthetic */ AppIndexer f24651b;

            public /* synthetic */ void onResult(Result result) {
                m25798a((Status) result);
            }

            public void m25798a(Status status) {
                if (status.isSuccess()) {
                    Log.b(AppIndexer.f24652a, "App Indexing API: Recorded indexing action " + action.toString() + " successfully.");
                } else {
                    Log.e(AppIndexer.f24652a, "App Indexing API: There was an error recording the indexing action " + status.toString());
                }
            }
        });
        this.f24654c.disconnect();
    }

    public void m25802a() {
        this.f24654c.disconnect();
    }
}
