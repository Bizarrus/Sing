/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.PendingIntent
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentSender
 *  android.content.IntentSender$SendIntentException
 *  android.content.ServiceConnection
 *  android.content.pm.PackageManager
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.os.RemoteException
 *  android.text.TextUtils
 *  com.android.vending.billing.IInAppBillingService
 *  com.android.vending.billing.IInAppBillingService$Stub
 *  org.json.JSONException
 */
package com.smule.android.purchases;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.vending.billing.IInAppBillingService;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.purchases.Consts;
import com.smule.android.purchases.IabException;
import com.smule.android.purchases.IabResult;
import com.smule.android.purchases.Inventory;
import com.smule.android.purchases.Purchase;
import com.smule.android.purchases.Security;
import com.smule.android.purchases.ServerPurchaseExecutor;
import com.smule.android.purchases.SkuDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class IabHelper {
    boolean a = false;
    String b = "IabHelper";
    boolean c = false;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    boolean g = false;
    String h = "";
    Context i;
    IInAppBillingService j;
    ServiceConnection k;
    volatile boolean l;
    int m;
    String n;
    String o = null;
    OnIabPurchaseFinishedListener p;

    public IabHelper(Context context, String string2) {
        this.i = context.getApplicationContext();
        this.o = string2;
        this.c("IAB helper created.");
    }

    public static String a(int n) {
        String[] arrstring = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] arrstring2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (n <= -1000) {
            int n2 = -1000 - n;
            if (n2 >= 0 && n2 < arrstring2.length) {
                return arrstring2[n2];
            }
            return String.valueOf(n) + ":Unknown IAB Helper Error";
        }
        if (n < 0 || n >= arrstring.length) {
            return String.valueOf(n) + ":Unknown";
        }
        return arrstring[n];
    }

    private void d() {
        if (this.d) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    int a(Intent object) {
        if ((object = object.getExtras().get("RESPONSE_CODE")) == null) {
            this.d("Intent with no response code, assuming OK (known issue)");
            return 0;
        }
        if (object instanceof Integer) {
            return (Integer)object;
        }
        if (object instanceof Long) {
            return (int)((Long)object).longValue();
        }
        this.d("Unexpected type for intent response code.");
        this.d(object.getClass().getName());
        throw new RuntimeException("Unexpected type for intent response code: " + object.getClass().getName());
    }

    int a(Bundle object) {
        if ((object = object.get("RESPONSE_CODE")) == null) {
            this.c("Bundle with null response code, assuming OK (known issue)");
            return 0;
        }
        if (object instanceof Integer) {
            return (Integer)object;
        }
        if (object instanceof Long) {
            return (int)((Long)object).longValue();
        }
        this.d("Unexpected type for bundle response code.");
        this.d(object.getClass().getName());
        throw new RuntimeException("Unexpected type for bundle response code: " + object.getClass().getName());
    }

    /*
     * Enabled aggressive block sorting
     */
    int a(Inventory inventory, String string2) throws JSONException, RemoteException {
        int n;
        int n2 = 0;
        this.c("Querying owned items, item type: " + string2);
        this.c("Package name: " + this.i.getPackageName());
        Object object = null;
        if (this.j == null) {
            return -1003;
        }
        boolean bl = false;
        do {
            this.c("Calling getPurchases with continuation token: " + (String)object);
            object = this.j.a(3, this.i.getPackageName(), string2, (String)object);
            n = this.a((Bundle)object);
            this.c("Owned items response: " + String.valueOf(n));
            if (n != 0) {
                this.c("getPurchases() failed: " + IabHelper.a(n));
                return n;
            }
            if (!(object.containsKey("INAPP_PURCHASE_ITEM_LIST") && object.containsKey("INAPP_PURCHASE_DATA_LIST") && object.containsKey("INAPP_DATA_SIGNATURE_LIST"))) {
                this.d("Bundle returned from getPurchases() doesn't contain required fields.");
                return -1002;
            }
            ArrayList arrayList = object.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            ArrayList arrayList2 = object.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
            ArrayList arrayList3 = object.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
            for (n = 0; n < arrayList2.size(); ++n) {
                String string3 = (String)arrayList2.get(n);
                Object object2 = (String)arrayList3.get(n);
                String string4 = (String)arrayList.get(n);
                if (Security.a(this.o, string3, (String)object2)) {
                    this.c("Sku is owned: " + string4);
                    object2 = new Purchase(string2, string3, (String)object2);
                    if (TextUtils.isEmpty((CharSequence)object2.g())) {
                        this.e("BUG: empty/null token!");
                        this.c("Purchase data: " + string3);
                    }
                    inventory.a((Purchase)object2);
                    continue;
                }
                this.e("Purchase signature verification **FAILED**. Not adding item.");
                this.c("   Purchase data: " + string3);
                this.c("   Signature: " + (String)object2);
                bl = true;
            }
            object = object.getString("INAPP_CONTINUATION_TOKEN");
            this.c("Continuation token: " + (String)object);
        } while (!TextUtils.isEmpty((CharSequence)object));
        n = n2;
        if (!bl) return n;
        return -1003;
    }

    int a(String string2, Inventory inventory, List<String> object) throws RemoteException, JSONException {
        int n;
        Object object2;
        this.c("Querying SKU details.");
        Iterator iterator = new ArrayList<String>();
        iterator.addAll(inventory.b(string2));
        if (object != null) {
            object = object.iterator();
            while (object.hasNext()) {
                object2 = (String)object.next();
                if (iterator.contains(object2)) continue;
                iterator.add((String)object2);
            }
        }
        if (iterator.size() == 0) {
            this.c("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        object = new ArrayList<Bundle>();
        int n2 = iterator.size() / 20;
        int n3 = iterator.size() % 20;
        for (n = 0; n < n2; ++n) {
            object2 = new ArrayList();
            Iterator iterator2 = iterator.subList(n * 20, n * 20 + 20).iterator();
            while (iterator2.hasNext()) {
                object2.add((String)((String)iterator2.next()));
            }
            object.add(object2);
        }
        if (n3 != 0) {
            object2 = new Bundle();
            iterator = iterator.subList(n2 * 20, n2 * 20 + n3).iterator();
            while (iterator.hasNext()) {
                object2.add((String)((String)iterator.next()));
            }
            object.add(object2);
            object = object.iterator();
            while (object.hasNext()) {
                iterator = (ArrayList)object.next();
                object2 = new Bundle();
                object2.putStringArrayList("ITEM_ID_LIST", (ArrayList)((Object)iterator));
                iterator = this.j.a(3, this.i.getPackageName(), string2, (Bundle)object2);
                if (!iterator.containsKey("DETAILS_LIST")) {
                    n = this.a((Bundle)iterator);
                    if (n != 0) {
                        this.c("getSkuDetails() failed: " + IabHelper.a(n));
                        return n;
                    }
                    this.d("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                    return -1002;
                }
                iterator = iterator.getStringArrayList("DETAILS_LIST").iterator();
                while (iterator.hasNext()) {
                    object2 = new SkuDetails(string2, (String)iterator.next());
                    this.c("Got sku details: " + object2);
                    inventory.a((SkuDetails)object2);
                }
            }
        }
        return 0;
    }

    public Inventory a(boolean bl, List<String> list) throws IabException {
        return this.a(bl, list, (List<String>)null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Inventory a(boolean bl, List<String> list, List<String> object) throws IabException {
        block9 : {
            int n;
            block8 : {
                this.d();
                this.a("queryInventory");
                object = new Inventory();
                n = this.a((Inventory)object, "inapp");
                if (n != 0) {
                    throw new IabException(n, "Error refreshing inventory (querying owned items).");
                }
                if (!bl) break block8;
                n = this.a("inapp", (Inventory)object, list);
                if (n == 0) break block8;
                throw new IabException(n, "Error refreshing inventory (querying prices of items).");
            }
            try {
                if (!this.e) break block9;
                n = this.a((Inventory)object, "subs");
                if (n != 0) {
                    throw new IabException(n, "Error refreshing inventory (querying owned subscriptions).");
                }
            }
            catch (RemoteException remoteException) {
                throw new IabException(-1001, "Remote exception while refreshing inventory.", (Exception)remoteException);
            }
            catch (JSONException jSONException) {
                throw new IabException(-1002, "Error parsing JSON response while refreshing inventory.", (Exception)jSONException);
            }
            if (bl && (n = this.a("subs", (Inventory)object, list)) != 0) {
                throw new IabException(n, "Error refreshing inventory (querying prices of subscriptions).");
            }
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a() {
        synchronized (this) {
            if (this.f) {
                this.c("Async operation in progress, will delay disposing");
                this.g = true;
            } else {
                this.c("Disposing.");
                this.c = false;
                if (this.k != null && this.l) {
                    this.c("Unbinding from service.");
                    if (this.i != null) {
                        this.i.unbindService(this.k);
                    }
                }
                this.d = true;
                this.i = null;
                this.k = null;
                this.l = false;
                this.j = null;
                this.p = null;
            }
            return;
        }
    }

    public void a(Activity activity, String string2, int n, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String string3) {
        this.a(activity, string2, "subs", n, onIabPurchaseFinishedListener, string3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Activity object, String string2, String string3, int n, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String string4) {
        this.d();
        this.a("launchPurchaseFlow");
        this.b("launchPurchaseFlow");
        if (string3.equals("subs") && !this.e) {
            object = new IabResult(-1009, "Subscriptions are not available.");
            this.c();
            if (onIabPurchaseFinishedListener == null) return;
            {
                onIabPurchaseFinishedListener.a((IabResult)object, null);
                return;
            }
        }
        try {
            this.c("Constructing buy intent for " + string2 + ", item type: " + string3 + " (payload: " + string4 + ")");
            string4 = this.j.a(3, this.i.getPackageName(), string2, string3, string4);
            int n2 = this.a((Bundle)string4);
            if (n2 != 0) {
                this.d("Unable to buy item, Error response: " + IabHelper.a(n2));
                this.c();
                object = new IabResult(n2, "Unable to buy item");
                if (onIabPurchaseFinishedListener == null) return;
                {
                    onIabPurchaseFinishedListener.a((IabResult)object, null);
                    return;
                }
            }
            string4 = (PendingIntent)string4.getParcelable("BUY_INTENT");
            this.c("Launching buy intent for " + string2 + ". Request code: " + n);
            this.m = n;
            this.p = onIabPurchaseFinishedListener;
            this.n = string3;
            object.startIntentSenderForResult(string4.getIntentSender(), n, new Intent(), 0, 0, 0);
            return;
        }
        catch (IntentSender.SendIntentException sendIntentException) {
            this.d("SendIntentException while launching purchase flow for sku " + string2);
            sendIntentException.printStackTrace();
            this.c();
            IabResult iabResult = new IabResult(-1004, "Failed to send intent.");
            if (onIabPurchaseFinishedListener == null) return;
            {
                onIabPurchaseFinishedListener.a(iabResult, null);
                return;
            }
        }
        catch (RemoteException remoteException) {
            this.d("RemoteException while launching purchase flow for sku " + string2);
            remoteException.printStackTrace();
            this.c();
            IabResult iabResult = new IabResult(-1001, "Remote exception while starting purchase flow");
            if (onIabPurchaseFinishedListener == null) return;
            onIabPurchaseFinishedListener.a(iabResult, null);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(final OnIabSetupFinishedListener onIabSetupFinishedListener) {
        List list;
        this.d();
        if (this.c) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        this.c("Starting in-app billing setup.");
        this.k = new ServiceConnection(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onServiceConnected(ComponentName object, IBinder iBinder) {
                block13 : {
                    block12 : {
                        if (IabHelper.this.d) break block12;
                        IabHelper.this.c("Billing service connected.");
                        IabHelper.this.j = IInAppBillingService.Stub.a((IBinder)iBinder);
                        object = IabHelper.this.i.getPackageName();
                        try {
                            IabHelper.this.c("Checking for in-app billing 3 support.");
                            int n = IabHelper.this.j.a(3, (String)object, "inapp");
                            if (n != 0) {
                                if (onIabSetupFinishedListener != null) {
                                    onIabSetupFinishedListener.a(new IabResult(n, "Error checking for billing v3 support."));
                                }
                                IabHelper.this.e = false;
                                return;
                            }
                            IabHelper.this.c("In-app billing version 3 supported for " + (String)object);
                            n = IabHelper.this.j.a(3, (String)object, "subs");
                            if (n == 0) {
                                IabHelper.this.c("Subscriptions AVAILABLE.");
                                IabHelper.this.e = true;
                            } else {
                                IabHelper.this.c("Subscriptions NOT AVAILABLE. Response: " + n);
                            }
                        }
                        catch (RemoteException remoteException) {
                            if (onIabSetupFinishedListener != null) {
                                onIabSetupFinishedListener.a(new IabResult(-1001, "RemoteException while setting up in-app billing."));
                            }
                            remoteException.printStackTrace();
                            return;
                        }
                        IabHelper.this.c = true;
                        if (onIabSetupFinishedListener != null) break block13;
                    }
                    return;
                }
                onIabSetupFinishedListener.a(new IabResult(0, "Setup successful."));
            }

            public void onServiceDisconnected(ComponentName componentName) {
                IabHelper.this.c("Billing service disconnected.");
            }
        };
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        if (this.i == null) {
            this.c("mContext is NULL.");
        } else if (this.i.getPackageManager() == null) {
            this.c("mContext.getPackageManager() is NULL.");
        }
        if ((list = this.i.getPackageManager().queryIntentServices(intent, 0)) != null && !list.isEmpty()) {
            this.l = this.i.bindService(intent, this.k, 1);
            return;
        } else {
            if (onIabSetupFinishedListener == null) return;
            {
                onIabSetupFinishedListener.a(new IabResult(3, "Billing service unavailable on device."));
                return;
            }
        }
    }

    public void a(QueryInventoryFinishedListener queryInventoryFinishedListener) {
        this.a(true, null, queryInventoryFinishedListener);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void a(Purchase purchase) throws IabException {
        String string2;
        int n;
        this.d();
        this.a("consume");
        if (!purchase.a.equals("inapp")) {
            throw new IabException(-1010, "Items of type '" + purchase.a + "' can't be consumed.");
        }
        try {
            String string3 = purchase.g();
            string2 = purchase.c();
            if (string3 == null || string3.equals("")) {
                this.d("Can't consume " + string2 + ". No token.");
                throw new IabException(-1007, "PurchaseInfo is missing token for sku: " + string2 + " " + purchase);
            }
            this.c("Consuming sku: " + string2 + ", token: " + string3);
            n = this.j.b(3, this.i.getPackageName(), string3);
            if (n == 0) {
                this.c("Successfully consumed sku: " + string2);
                return;
            }
        }
        catch (RemoteException remoteException) {
            throw new IabException(-1001, "Remote exception while consuming. PurchaseInfo: " + purchase, (Exception)remoteException);
        }
        this.c("Error consuming consuming sku " + string2 + ". " + IabHelper.a(n));
        throw new IabException(n, "Error consuming sku " + string2);
    }

    public void a(Purchase purchase, OnConsumeFinishedListener onConsumeFinishedListener) {
        this.d();
        this.a("consume");
        ArrayList<Purchase> arrayList = new ArrayList<Purchase>();
        arrayList.add(purchase);
        this.a(arrayList, onConsumeFinishedListener, null);
    }

    void a(String string2) {
        if (!this.c) {
            this.d("Illegal state for operation (" + string2 + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + string2);
        }
    }

    void a(final List<Purchase> list, final OnConsumeFinishedListener onConsumeFinishedListener, final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) {
        final Handler handler = new Handler();
        this.b("consume");
        new Thread(new Runnable(){

            @Override
            public void run() {
                final ArrayList<IabResult> arrayList = new ArrayList<IabResult>();
                for (Purchase purchase : list) {
                    try {
                        IabHelper.this.a(purchase);
                        arrayList.add(new IabResult(0, "Successful consume of sku " + purchase.c()));
                    }
                    catch (IabException iabException) {
                        arrayList.add(iabException.a());
                    }
                }
                IabHelper.this.c();
                if (!IabHelper.this.d && onConsumeFinishedListener != null) {
                    handler.post(new Runnable(){

                        @Override
                        public void run() {
                            onConsumeFinishedListener.a((Purchase)list.get(0), (IabResult)arrayList.get(0));
                        }
                    });
                }
                if (!IabHelper.this.d && onConsumeMultiFinishedListener != null) {
                    handler.post(new Runnable(){

                        @Override
                        public void run() {
                            onConsumeMultiFinishedListener.a(list, arrayList);
                        }
                    });
                }
            }

        }).start();
    }

    public void a(boolean bl, String string2) {
        this.d();
        this.a = bl;
        this.b = string2;
    }

    public void a(final boolean bl, final List<String> list, final QueryInventoryFinishedListener queryInventoryFinishedListener) {
        final Handler handler = new Handler();
        this.d();
        this.a("queryInventory");
        this.b("refresh inventory");
        new Thread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void run() {
                final IabResult iabResult = new IabResult(0, "Inventory refresh successful.");
                final Inventory inventory = null;
                try {
                    Inventory inventory2;
                    inventory = inventory2 = IabHelper.this.a(bl, list);
                }
                catch (IabException iabException) {
                    iabResult = iabException.a();
                }
                IabHelper.this.c();
                if (!IabHelper.this.d && queryInventoryFinishedListener != null) {
                    handler.post(new Runnable(){

                        @Override
                        public void run() {
                            queryInventoryFinishedListener.a(iabResult, inventory);
                        }
                    });
                }
            }

        }).start();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a(int n, int n2, Intent object) {
        if (n != this.m) {
            return false;
        }
        this.d();
        this.a("handleActivityResult");
        this.c();
        if (object == null) {
            this.d("Null data in IAB activity result.");
            object = new IabResult(-1002, "Null data in IAB result");
            if (this.p == null) return true;
            this.p.a((IabResult)object, null);
            return true;
        }
        n = this.a((Intent)object);
        Object object2 = object.getStringExtra("INAPP_PURCHASE_DATA");
        String string2 = object.getStringExtra("INAPP_DATA_SIGNATURE");
        if (n2 == -1 && n == 0) {
            this.c("Successful resultcode from purchase activity.");
            this.c("Purchase data: " + (String)object2);
            this.c("Data signature: " + string2);
            this.c("Extras: " + (Object)object.getExtras());
            this.c("Expected item type: " + this.n);
            if (object2 == null || string2 == null) {
                this.d("BUG: either purchaseData or dataSignature is null.");
                this.c("Extras: " + object.getExtras().toString());
                object = new IabResult(-1008, "IAB returned null purchaseData or dataSignature");
                if (this.p == null) return true;
                this.p.a((IabResult)object, null);
                return true;
            }
            try {
                object = new Purchase(this.n, (String)object2, string2);
                String string3 = object.c();
                if (!Security.a(this.o, (String)object2, string2)) {
                    this.d("Purchase signature verification FAILED for sku " + string3);
                    object2 = new IabResult(-1003, "Signature verification failed for sku " + string3);
                    if (this.p == null) return true;
                    this.p.a((IabResult)object2, (Purchase)object);
                    return true;
                }
                MagicNetwork.a(new Runnable((Purchase)object, (String)object2, string2){
                    final /* synthetic */ Purchase a;
                    final /* synthetic */ String b;
                    final /* synthetic */ String c;
                    {
                        this.a = purchase;
                        this.b = string2;
                        this.c = string3;
                    }

                    @Override
                    public void run() {
                        ServerPurchaseExecutor.a(Consts.PurchaseState.a(this.a.e()), this.a.c(), this.a.b(), this.a.d(), this.b, this.c);
                    }
                });
                this.c("Purchase signature successfully verified.");
                if (this.p == null) return true;
            }
            catch (JSONException jSONException) {
                this.d("Failed to parse purchase data.");
                jSONException.printStackTrace();
                IabResult iabResult = new IabResult(-1002, "Failed to parse purchase data.");
                if (this.p == null) return true;
                this.p.a(iabResult, null);
                return true;
            }
            this.p.a(new IabResult(0, "Success"), (Purchase)object);
            return true;
        }
        if (n2 == -1) {
            this.c("Result code was OK but in-app billing response was not OK: " + IabHelper.a(n));
            if (this.p == null) return true;
            object = new IabResult(n, "Problem purchashing item.");
            this.p.a((IabResult)object, null);
            return true;
        }
        if (n2 == 0) {
            this.c("Purchase canceled - Response: " + IabHelper.a(n));
            object = new IabResult(-1005, "User canceled.");
            if (this.p == null) return true;
            this.p.a((IabResult)object, null);
            return true;
        }
        this.d("Purchase failed. Result code: " + Integer.toString(n2) + ". Response: " + IabHelper.a(n));
        object = new IabResult(-1006, "Unknown purchase response.");
        if (this.p == null) return true;
        this.p.a((IabResult)object, null);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void b(String string2) {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Can't start async operation (" + string2 + ") because another async operation(" + this.h + ") is in progress.");
            }
            this.h = string2;
            this.f = true;
            this.c("Starting async operation: " + string2);
            return;
        }
    }

    public boolean b() {
        this.d();
        return this.e;
    }

    void c() {
        synchronized (this) {
            this.c("Ending async operation: " + this.h);
            this.h = "";
            this.f = false;
            if (this.g) {
                this.g = false;
                this.a();
            }
            return;
        }
    }

    void c(String string2) {
        if (this.a) {
            Log.b(this.b, string2);
        }
    }

    void d(String string2) {
        Log.e(this.b, "In-app billing error: " + string2);
    }

    void e(String string2) {
        Log.d(this.b, "In-app billing warning: " + string2);
    }

    public static interface OnConsumeFinishedListener {
        public void a(Purchase var1, IabResult var2);
    }

    public static interface OnConsumeMultiFinishedListener {
        public void a(List<Purchase> var1, List<IabResult> var2);
    }

    public static interface OnIabPurchaseFinishedListener {
        public void a(IabResult var1, Purchase var2);
    }

    public static interface OnIabSetupFinishedListener {
        public void a(IabResult var1);
    }

    public static interface QueryInventoryFinishedListener {
        public void a(IabResult var1, Inventory var2);
    }

}

