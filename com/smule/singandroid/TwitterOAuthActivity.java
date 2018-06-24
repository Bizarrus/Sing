package com.smule.singandroid;

import android.os.Bundle;
import com.smule.android.logging.Log;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.twitter.TwitterOAuthView;
import com.smule.android.twitter.TwitterOAuthView.Listener;
import com.smule.android.twitter.TwitterOAuthView.Result;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import twitter4j.auth.AccessToken;

@EActivity
public class TwitterOAuthActivity extends BaseActivity implements Listener {
    public static final String f20433g = TwitterOAuthActivity.class.getName();
    @ViewById
    protected TwitterOAuthView f20434h;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onStart() {
        super.onStart();
        this.f20434h.setDebugEnabled(true);
        this.f20434h.m18871a(getString(C1947R.string.twitter_consumer_key), getString(C1947R.string.twitter_consumer_secret), getString(C1947R.string.twitter_callback), true, this);
    }

    public void mo6677a(TwitterOAuthView twitterOAuthView, AccessToken accessToken) {
        MagicTwitter.m18838a().m18843a(accessToken);
        setResult(-1);
        finish();
    }

    public void mo6676a(TwitterOAuthView twitterOAuthView, Result result) {
        if (result != Result.CANCELLATION) {
            Log.d(f20433g, "TwitterOAuthView returned result code '" + (result != null ? result.name() : "NULL") + "'");
        }
        setResult(0);
        finish();
    }
}
