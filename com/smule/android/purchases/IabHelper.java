package com.smule.android.purchases;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationManagerCompat;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.purchases.Consts.PurchaseState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class IabHelper {
    boolean f17548a = false;
    String f17549b = "IabHelper";
    boolean f17550c = false;
    boolean f17551d = false;
    boolean f17552e = false;
    boolean f17553f = false;
    boolean f17554g = false;
    String f17555h = "";
    Context f17556i;
    IInAppBillingService f17557j;
    ServiceConnection f17558k;
    volatile boolean f17559l;
    int f17560m;
    String f17561n;
    String f17562o = null;
    OnIabPurchaseFinishedListener f17563p;

    public interface OnIabSetupFinishedListener {
        void mo6281a(IabResult iabResult);
    }

    public interface QueryInventoryFinishedListener {
        void mo6282a(IabResult iabResult, Inventory inventory);
    }

    public interface OnIabPurchaseFinishedListener {
        void mo6283a(IabResult iabResult, Purchase purchase);
    }

    public interface OnConsumeFinishedListener {
        void mo6284a(Purchase purchase, IabResult iabResult);
    }

    public interface OnConsumeMultiFinishedListener {
        void m18631a(List<Purchase> list, List<IabResult> list2);
    }

    public IabHelper(Context context, String str) {
        this.f17556i = context.getApplicationContext();
        this.f17562o = str;
        m18655c("IAB helper created.");
    }

    public void m18649a(boolean z, String str) {
        m18633d();
        this.f17548a = z;
        this.f17549b = str;
    }

    public void m18643a(final OnIabSetupFinishedListener onIabSetupFinishedListener) {
        m18633d();
        if (this.f17550c) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        m18655c("Starting in-app billing setup.");
        this.f17558k = new ServiceConnection(this) {
            final /* synthetic */ IabHelper f17526b;

            public void onServiceDisconnected(ComponentName componentName) {
                this.f17526b.m18655c("Billing service disconnected.");
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                if (!this.f17526b.f17551d) {
                    this.f17526b.m18655c("Billing service connected.");
                    this.f17526b.f17557j = Stub.a(iBinder);
                    String packageName = this.f17526b.f17556i.getPackageName();
                    try {
                        this.f17526b.m18655c("Checking for in-app billing 3 support.");
                        int a = this.f17526b.f17557j.a(3, packageName, "inapp");
                        if (a != 0) {
                            if (onIabSetupFinishedListener != null) {
                                onIabSetupFinishedListener.mo6281a(new IabResult(a, "Error checking for billing v3 support."));
                            }
                            this.f17526b.f17552e = false;
                            return;
                        }
                        this.f17526b.m18655c("In-app billing version 3 supported for " + packageName);
                        int a2 = this.f17526b.f17557j.a(3, packageName, "subs");
                        if (a2 == 0) {
                            this.f17526b.m18655c("Subscriptions AVAILABLE.");
                            this.f17526b.f17552e = true;
                        } else {
                            this.f17526b.m18655c("Subscriptions NOT AVAILABLE. Response: " + a2);
                        }
                        this.f17526b.f17550c = true;
                        if (onIabSetupFinishedListener != null) {
                            onIabSetupFinishedListener.mo6281a(new IabResult(0, "Setup successful."));
                        }
                    } catch (RemoteException e) {
                        if (onIabSetupFinishedListener != null) {
                            onIabSetupFinishedListener.mo6281a(new IabResult(-1001, "RemoteException while setting up in-app billing."));
                        }
                        e.printStackTrace();
                    }
                }
            }
        };
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        if (this.f17556i == null) {
            m18655c("mContext is NULL.");
        } else if (this.f17556i.getPackageManager() == null) {
            m18655c("mContext.getPackageManager() is NULL.");
        }
        List queryIntentServices = this.f17556i.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
            this.f17559l = this.f17556i.bindService(intent, this.f17558k, 1);
        } else if (onIabSetupFinishedListener != null) {
            onIabSetupFinishedListener.mo6281a(new IabResult(3, "Billing service unavailable on device."));
        }
    }

    public synchronized void m18640a() {
        if (this.f17553f) {
            m18655c("Async operation in progress, will delay disposing");
            this.f17554g = true;
        } else {
            m18655c("Disposing.");
            this.f17550c = false;
            if (this.f17558k != null && this.f17559l) {
                m18655c("Unbinding from service.");
                if (this.f17556i != null) {
                    this.f17556i.unbindService(this.f17558k);
                }
            }
            this.f17551d = true;
            this.f17556i = null;
            this.f17558k = null;
            this.f17559l = false;
            this.f17557j = null;
            this.f17563p = null;
        }
    }

    private void m18633d() {
        if (this.f17551d) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    public boolean m18653b() {
        m18633d();
        return this.f17552e;
    }

    public void m18641a(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) {
        m18642a(activity, str, "subs", i, onIabPurchaseFinishedListener, str2);
    }

    public void m18642a(Activity activity, String str, String str2, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str3) {
        IabResult iabResult;
        m18633d();
        m18647a("launchPurchaseFlow");
        m18652b("launchPurchaseFlow");
        if (!str2.equals("subs") || this.f17552e) {
            try {
                m18655c("Constructing buy intent for " + str + ", item type: " + str2 + " (payload: " + str3 + ")");
                Bundle a = this.f17557j.a(3, this.f17556i.getPackageName(), str, str2, str3);
                int a2 = m18635a(a);
                if (a2 != 0) {
                    m18656d("Unable to buy item, Error response: " + m18632a(a2));
                    m18654c();
                    iabResult = new IabResult(a2, "Unable to buy item");
                    if (onIabPurchaseFinishedListener != null) {
                        onIabPurchaseFinishedListener.mo6283a(iabResult, null);
                        return;
                    }
                    return;
                }
                PendingIntent pendingIntent = (PendingIntent) a.getParcelable("BUY_INTENT");
                m18655c("Launching buy intent for " + str + ". Request code: " + i);
                this.f17560m = i;
                this.f17563p = onIabPurchaseFinishedListener;
                this.f17561n = str2;
                activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), 0, 0, 0);
                return;
            } catch (SendIntentException e) {
                m18656d("SendIntentException while launching purchase flow for sku " + str);
                e.printStackTrace();
                m18654c();
                iabResult = new IabResult(-1004, "Failed to send intent.");
                if (onIabPurchaseFinishedListener != null) {
                    onIabPurchaseFinishedListener.mo6283a(iabResult, null);
                    return;
                }
                return;
            } catch (RemoteException e2) {
                m18656d("RemoteException while launching purchase flow for sku " + str);
                e2.printStackTrace();
                m18654c();
                iabResult = new IabResult(-1001, "Remote exception while starting purchase flow");
                if (onIabPurchaseFinishedListener != null) {
                    onIabPurchaseFinishedListener.mo6283a(iabResult, null);
                    return;
                }
                return;
            }
        }
        iabResult = new IabResult(-1009, "Subscriptions are not available.");
        m18654c();
        if (onIabPurchaseFinishedListener != null) {
            onIabPurchaseFinishedListener.mo6283a(iabResult, null);
        }
    }

    public boolean m18651a(int i, int i2, Intent intent) {
        if (i != this.f17560m) {
            return false;
        }
        m18633d();
        m18647a("handleActivityResult");
        m18654c();
        IabResult iabResult;
        if (intent == null) {
            m18656d("Null data in IAB activity result.");
            iabResult = new IabResult(-1002, "Null data in IAB result");
            if (this.f17563p != null) {
                this.f17563p.mo6283a(iabResult, null);
            }
            return true;
        }
        int a = m18634a(intent);
        final String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
        final String stringExtra2 = intent.getStringExtra("INAPP_DATA_SIGNATURE");
        if (i2 == -1 && a == 0) {
            m18655c("Successful resultcode from purchase activity.");
            m18655c("Purchase data: " + stringExtra);
            m18655c("Data signature: " + stringExtra2);
            m18655c("Extras: " + intent.getExtras());
            m18655c("Expected item type: " + this.f17561n);
            if (stringExtra == null || stringExtra2 == null) {
                m18656d("BUG: either purchaseData or dataSignature is null.");
                m18655c("Extras: " + intent.getExtras().toString());
                iabResult = new IabResult(-1008, "IAB returned null purchaseData or dataSignature");
                if (this.f17563p != null) {
                    this.f17563p.mo6283a(iabResult, null);
                }
                return true;
            }
            try {
                final Purchase purchase = new Purchase(this.f17561n, stringExtra, stringExtra2);
                String c = purchase.m18668c();
                if (Security.m18676a(this.f17562o, stringExtra, stringExtra2)) {
                    MagicNetwork.a(new Runnable(this) {
                        final /* synthetic */ IabHelper f17530d;

                        public void run() {
                            ServerPurchaseExecutor.m18678a(PurchaseState.m18618a(purchase.m18670e()), purchase.m18668c(), purchase.m18667b(), purchase.m18669d(), stringExtra, stringExtra2);
                        }
                    });
                    m18655c("Purchase signature successfully verified.");
                    if (this.f17563p != null) {
                        this.f17563p.mo6283a(new IabResult(0, "Success"), purchase);
                    }
                } else {
                    m18656d("Purchase signature verification FAILED for sku " + c);
                    iabResult = new IabResult(-1003, "Signature verification failed for sku " + c);
                    if (this.f17563p != null) {
                        this.f17563p.mo6283a(iabResult, purchase);
                    }
                    return true;
                }
            } catch (JSONException e) {
                m18656d("Failed to parse purchase data.");
                e.printStackTrace();
                iabResult = new IabResult(-1002, "Failed to parse purchase data.");
                if (this.f17563p != null) {
                    this.f17563p.mo6283a(iabResult, null);
                }
                return true;
            }
        } else if (i2 == -1) {
            m18655c("Result code was OK but in-app billing response was not OK: " + m18632a(a));
            if (this.f17563p != null) {
                this.f17563p.mo6283a(new IabResult(a, "Problem purchashing item."), null);
            }
        } else if (i2 == 0) {
            m18655c("Purchase canceled - Response: " + m18632a(a));
            iabResult = new IabResult(-1005, "User canceled.");
            if (this.f17563p != null) {
                this.f17563p.mo6283a(iabResult, null);
            }
        } else {
            m18656d("Purchase failed. Result code: " + Integer.toString(i2) + ". Response: " + m18632a(a));
            iabResult = new IabResult(-1006, "Unknown purchase response.");
            if (this.f17563p != null) {
                this.f17563p.mo6283a(iabResult, null);
            }
        }
        return true;
    }

    public Inventory m18638a(boolean z, List<String> list) throws IabException {
        return m18639a(z, (List) list, null);
    }

    public Inventory m18639a(boolean z, List<String> list, List<String> list2) throws IabException {
        m18633d();
        m18647a("queryInventory");
        try {
            Inventory inventory = new Inventory();
            int a = m18636a(inventory, "inapp");
            if (a != 0) {
                throw new IabException(a, "Error refreshing inventory (querying owned items).");
            }
            if (z) {
                a = m18637a("inapp", inventory, (List) list);
                if (a != 0) {
                    throw new IabException(a, "Error refreshing inventory (querying prices of items).");
                }
            }
            if (this.f17552e) {
                a = m18636a(inventory, "subs");
                if (a != 0) {
                    throw new IabException(a, "Error refreshing inventory (querying owned subscriptions).");
                } else if (z) {
                    a = m18637a("subs", inventory, (List) list);
                    if (a != 0) {
                        throw new IabException(a, "Error refreshing inventory (querying prices of subscriptions).");
                    }
                }
            }
            return inventory;
        } catch (Exception e) {
            throw new IabException(-1001, "Remote exception while refreshing inventory.", e);
        } catch (Exception e2) {
            throw new IabException(-1002, "Error parsing JSON response while refreshing inventory.", e2);
        }
    }

    public void m18650a(boolean z, List<String> list, QueryInventoryFinishedListener queryInventoryFinishedListener) {
        final Handler handler = new Handler();
        m18633d();
        m18647a("queryInventory");
        m18652b("refresh inventory");
        final boolean z2 = z;
        final List<String> list2 = list;
        final QueryInventoryFinishedListener queryInventoryFinishedListener2 = queryInventoryFinishedListener;
        new Thread(new Runnable(this) {
            final /* synthetic */ IabHelper f17538e;

            public void run() {
                IabResult iabResult = new IabResult(0, "Inventory refresh successful.");
                Inventory inventory = null;
                try {
                    inventory = this.f17538e.m18638a(z2, list2);
                } catch (IabException e) {
                    iabResult = e.m18630a();
                }
                this.f17538e.m18654c();
                if (!this.f17538e.f17551d && queryInventoryFinishedListener2 != null) {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ C36403 f17533c;

                        public void run() {
                            queryInventoryFinishedListener2.mo6282a(iabResult, inventory);
                        }
                    });
                }
            }
        }).start();
    }

    public void m18644a(QueryInventoryFinishedListener queryInventoryFinishedListener) {
        m18650a(true, null, queryInventoryFinishedListener);
    }

    void m18645a(Purchase purchase) throws IabException {
        m18633d();
        m18647a("consume");
        if (purchase.f17568a.equals("inapp")) {
            try {
                String g = purchase.m18672g();
                String c = purchase.m18668c();
                if (g == null || g.equals("")) {
                    m18656d("Can't consume " + c + ". No token.");
                    throw new IabException(-1007, "PurchaseInfo is missing token for sku: " + c + " " + purchase);
                }
                m18655c("Consuming sku: " + c + ", token: " + g);
                int b = this.f17557j.b(3, this.f17556i.getPackageName(), g);
                if (b == 0) {
                    m18655c("Successfully consumed sku: " + c);
                    return;
                } else {
                    m18655c("Error consuming consuming sku " + c + ". " + m18632a(b));
                    throw new IabException(b, "Error consuming sku " + c);
                }
            } catch (Exception e) {
                throw new IabException(-1001, "Remote exception while consuming. PurchaseInfo: " + purchase, e);
            }
        }
        throw new IabException(-1010, "Items of type '" + purchase.f17568a + "' can't be consumed.");
    }

    public void m18646a(Purchase purchase, OnConsumeFinishedListener onConsumeFinishedListener) {
        m18633d();
        m18647a("consume");
        List arrayList = new ArrayList();
        arrayList.add(purchase);
        m18648a(arrayList, onConsumeFinishedListener, null);
    }

    public static String m18632a(int i) {
        String[] split = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] split2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (i <= NotificationManagerCompat.IMPORTANCE_UNSPECIFIED) {
            int i2 = -1000 - i;
            if (i2 < 0 || i2 >= split2.length) {
                return String.valueOf(i) + ":Unknown IAB Helper Error";
            }
            return split2[i2];
        } else if (i < 0 || i >= split.length) {
            return String.valueOf(i) + ":Unknown";
        } else {
            return split[i];
        }
    }

    void m18647a(String str) {
        if (!this.f17550c) {
            m18656d("Illegal state for operation (" + str + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + str);
        }
    }

    int m18635a(Bundle bundle) {
        Object obj = bundle.get("RESPONSE_CODE");
        if (obj == null) {
            m18655c("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            m18656d("Unexpected type for bundle response code.");
            m18656d(obj.getClass().getName());
            throw new RuntimeException("Unexpected type for bundle response code: " + obj.getClass().getName());
        }
    }

    int m18634a(Intent intent) {
        Object obj = intent.getExtras().get("RESPONSE_CODE");
        if (obj == null) {
            m18656d("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            m18656d("Unexpected type for intent response code.");
            m18656d(obj.getClass().getName());
            throw new RuntimeException("Unexpected type for intent response code: " + obj.getClass().getName());
        }
    }

    synchronized void m18652b(String str) {
        if (this.f17553f) {
            throw new IllegalStateException("Can't start async operation (" + str + ") because another async operation(" + this.f17555h + ") is in progress.");
        }
        this.f17555h = str;
        this.f17553f = true;
        m18655c("Starting async operation: " + str);
    }

    synchronized void m18654c() {
        m18655c("Ending async operation: " + this.f17555h);
        this.f17555h = "";
        this.f17553f = false;
        if (this.f17554g) {
            this.f17554g = false;
            m18640a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    int m18636a(com.smule.android.purchases.Inventory r14, java.lang.String r15) throws org.json.JSONException, android.os.RemoteException {
        /*
        r13 = this;
        r6 = -1003; // 0xfffffffffffffc15 float:NaN double:NaN;
        r3 = 0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Querying owned items, item type: ";
        r0 = r0.append(r1);
        r0 = r0.append(r15);
        r0 = r0.toString();
        r13.m18655c(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Package name: ";
        r0 = r0.append(r1);
        r1 = r13.f17556i;
        r1 = r1.getPackageName();
        r0 = r0.append(r1);
        r0 = r0.toString();
        r13.m18655c(r0);
        r0 = 0;
        r1 = r13.f17557j;
        if (r1 != 0) goto L_0x0191;
    L_0x003a:
        return r6;
    L_0x003b:
        r1 = r4;
    L_0x003c:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "Calling getPurchases with continuation token: ";
        r2 = r2.append(r4);
        r2 = r2.append(r0);
        r2 = r2.toString();
        r13.m18655c(r2);
        r2 = r13.f17557j;
        r4 = 3;
        r5 = r13.f17556i;
        r5 = r5.getPackageName();
        r7 = r2.a(r4, r5, r15, r0);
        r0 = r13.m18635a(r7);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "Owned items response: ";
        r2 = r2.append(r4);
        r4 = java.lang.String.valueOf(r0);
        r2 = r2.append(r4);
        r2 = r2.toString();
        r13.m18655c(r2);
        if (r0 == 0) goto L_0x009b;
    L_0x007f:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "getPurchases() failed: ";
        r1 = r1.append(r2);
        r2 = m18632a(r0);
        r1 = r1.append(r2);
        r1 = r1.toString();
        r13.m18655c(r1);
        r6 = r0;
        goto L_0x003a;
    L_0x009b:
        r0 = "INAPP_PURCHASE_ITEM_LIST";
        r0 = r7.containsKey(r0);
        if (r0 == 0) goto L_0x00b3;
    L_0x00a3:
        r0 = "INAPP_PURCHASE_DATA_LIST";
        r0 = r7.containsKey(r0);
        if (r0 == 0) goto L_0x00b3;
    L_0x00ab:
        r0 = "INAPP_DATA_SIGNATURE_LIST";
        r0 = r7.containsKey(r0);
        if (r0 != 0) goto L_0x00bb;
    L_0x00b3:
        r0 = "Bundle returned from getPurchases() doesn't contain required fields.";
        r13.m18656d(r0);
        r6 = -1002; // 0xfffffffffffffc16 float:NaN double:NaN;
        goto L_0x003a;
    L_0x00bb:
        r0 = "INAPP_PURCHASE_ITEM_LIST";
        r8 = r7.getStringArrayList(r0);
        r0 = "INAPP_PURCHASE_DATA_LIST";
        r9 = r7.getStringArrayList(r0);
        r0 = "INAPP_DATA_SIGNATURE_LIST";
        r10 = r7.getStringArrayList(r0);
        r5 = r3;
        r4 = r1;
    L_0x00cf:
        r0 = r9.size();
        if (r5 >= r0) goto L_0x0169;
    L_0x00d5:
        r0 = r9.get(r5);
        r0 = (java.lang.String) r0;
        r1 = r10.get(r5);
        r1 = (java.lang.String) r1;
        r2 = r8.get(r5);
        r2 = (java.lang.String) r2;
        r11 = r13.f17562o;
        r11 = com.smule.android.purchases.Security.m18676a(r11, r0, r1);
        if (r11 == 0) goto L_0x0136;
    L_0x00ef:
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "Sku is owned: ";
        r11 = r11.append(r12);
        r2 = r11.append(r2);
        r2 = r2.toString();
        r13.m18655c(r2);
        r2 = new com.smule.android.purchases.Purchase;
        r2.<init>(r15, r0, r1);
        r1 = r2.m18672g();
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x012f;
    L_0x0114:
        r1 = "BUG: empty/null token!";
        r13.m18657e(r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r11 = "Purchase data: ";
        r1 = r1.append(r11);
        r0 = r1.append(r0);
        r0 = r0.toString();
        r13.m18655c(r0);
    L_0x012f:
        r14.m18663a(r2);
    L_0x0132:
        r0 = r5 + 1;
        r5 = r0;
        goto L_0x00cf;
    L_0x0136:
        r2 = "Purchase signature verification **FAILED**. Not adding item.";
        r13.m18657e(r2);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "   Purchase data: ";
        r2 = r2.append(r4);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r13.m18655c(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "   Signature: ";
        r0 = r0.append(r2);
        r0 = r0.append(r1);
        r0 = r0.toString();
        r13.m18655c(r0);
        r4 = 1;
        goto L_0x0132;
    L_0x0169:
        r0 = "INAPP_CONTINUATION_TOKEN";
        r0 = r7.getString(r0);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Continuation token: ";
        r1 = r1.append(r2);
        r1 = r1.append(r0);
        r1 = r1.toString();
        r13.m18655c(r1);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 == 0) goto L_0x003b;
    L_0x018b:
        if (r4 == 0) goto L_0x018e;
    L_0x018d:
        r3 = r6;
    L_0x018e:
        r6 = r3;
        goto L_0x003a;
    L_0x0191:
        r1 = r3;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.android.purchases.IabHelper.a(com.smule.android.purchases.Inventory, java.lang.String):int");
    }

    int m18637a(String str, Inventory inventory, List<String> list) throws RemoteException, JSONException {
        Iterator it;
        m18655c("Querying SKU details.");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(inventory.m18665b(str));
        if (list != null) {
            for (String str2 : list) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        if (arrayList.size() == 0) {
            m18655c("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size() / 20;
        int size2 = arrayList.size() % 20;
        for (int i = 0; i < size; i++) {
            ArrayList arrayList3 = new ArrayList();
            for (String str22 : arrayList.subList(i * 20, (i * 20) + 20)) {
                arrayList3.add(str22);
            }
            arrayList2.add(arrayList3);
        }
        if (size2 != 0) {
            Iterator it2;
            ArrayList arrayList4 = new ArrayList();
            for (String str222 : arrayList.subList(size * 20, (size * 20) + size2)) {
                arrayList4.add(str222);
            }
            arrayList2.add(arrayList4);
            it = arrayList2.iterator();
            while (it.hasNext()) {
                ArrayList arrayList5 = (ArrayList) it.next();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("ITEM_ID_LIST", arrayList5);
                Bundle a = this.f17557j.a(3, this.f17556i.getPackageName(), str, bundle);
                if (a.containsKey("DETAILS_LIST")) {
                    it2 = a.getStringArrayList("DETAILS_LIST").iterator();
                    while (it2.hasNext()) {
                        SkuDetails skuDetails = new SkuDetails(str, (String) it2.next());
                        m18655c("Got sku details: " + skuDetails);
                        inventory.m18664a(skuDetails);
                    }
                } else {
                    int a2 = m18635a(a);
                    if (a2 != 0) {
                        m18655c("getSkuDetails() failed: " + m18632a(a2));
                        return a2;
                    }
                    m18656d("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                    return -1002;
                }
            }
        }
        return 0;
    }

    void m18648a(List<Purchase> list, OnConsumeFinishedListener onConsumeFinishedListener, OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) {
        final Handler handler = new Handler();
        m18652b("consume");
        final List<Purchase> list2 = list;
        final OnConsumeFinishedListener onConsumeFinishedListener2 = onConsumeFinishedListener;
        final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener2 = onConsumeMultiFinishedListener;
        new Thread(new Runnable(this) {
            final /* synthetic */ IabHelper f17547e;

            public void run() {
                final List arrayList = new ArrayList();
                for (Purchase purchase : list2) {
                    try {
                        this.f17547e.m18645a(purchase);
                        arrayList.add(new IabResult(0, "Successful consume of sku " + purchase.m18668c()));
                    } catch (IabException e) {
                        arrayList.add(e.m18630a());
                    }
                }
                this.f17547e.m18654c();
                if (!(this.f17547e.f17551d || onConsumeFinishedListener2 == null)) {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ C36434 f17540b;

                        public void run() {
                            onConsumeFinishedListener2.mo6284a((Purchase) list2.get(0), (IabResult) arrayList.get(0));
                        }
                    });
                }
                if (!this.f17547e.f17551d && onConsumeMultiFinishedListener2 != null) {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ C36434 f17542b;

                        public void run() {
                            onConsumeMultiFinishedListener2.m18631a(list2, arrayList);
                        }
                    });
                }
            }
        }).start();
    }

    void m18655c(String str) {
        if (this.f17548a) {
            Log.b(this.f17549b, str);
        }
    }

    void m18656d(String str) {
        Log.e(this.f17549b, "In-app billing error: " + str);
    }

    void m18657e(String str) {
        Log.d(this.f17549b, "In-app billing warning: " + str);
    }
}
