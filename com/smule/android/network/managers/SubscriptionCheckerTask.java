/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 */
package com.smule.android.network.managers;

import android.os.AsyncTask;
import com.smule.android.network.core.ServerException;
import com.smule.android.network.managers.SubscriptionManager;
import java.io.IOException;

public class SubscriptionCheckerTask
extends AsyncTask<Void, Void, SubscriptionManager> {
    private  a;

    public SubscriptionCheckerTask( subscriptionCheckerListener) {
        this.a = subscriptionCheckerListener;
    }

    protected /* varargs */ SubscriptionManager a(Void ... object) {
        try {
            object = SubscriptionManager.a().g();
            return object;
        }
        catch (IOException iOException) {
            return null;
        }
        catch (ServerException serverException) {
            return null;
        }
    }

    protected void a(SubscriptionManager subscriptionStatusResponse) {
        if (this.a != null) {
            this.a.a(subscriptionStatusResponse);
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a((Void[])arrobject);
    }

    protected /* synthetic */ void onPostExecute(Object object) {
        this.a(object);
    }

}

