package com.smule.android.network.managers;

import android.os.AsyncTask;
import com.smule.android.network.core.ServerException;
import java.io.IOException;

public class SubscriptionCheckerTask extends AsyncTask<Void, Void, SubscriptionManager$SubscriptionStatusResponse> {
    private SubscriptionCheckerListener f17160a;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m18438a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m18439a((SubscriptionManager$SubscriptionStatusResponse) obj);
    }

    public SubscriptionCheckerTask(SubscriptionCheckerListener subscriptionCheckerListener) {
        this.f17160a = subscriptionCheckerListener;
    }

    protected SubscriptionManager$SubscriptionStatusResponse m18438a(Void... voidArr) {
        SubscriptionManager$SubscriptionStatusResponse subscriptionManager$SubscriptionStatusResponse = null;
        try {
            subscriptionManager$SubscriptionStatusResponse = SubscriptionManager.a().g();
        } catch (ServerException e) {
        } catch (IOException e2) {
        }
        return subscriptionManager$SubscriptionStatusResponse;
    }

    protected void m18439a(SubscriptionManager$SubscriptionStatusResponse subscriptionManager$SubscriptionStatusResponse) {
        if (this.f17160a != null) {
            this.f17160a.a(subscriptionManager$SubscriptionStatusResponse);
        }
    }
}
