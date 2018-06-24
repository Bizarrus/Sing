/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.database.Cursor
 *  android.net.Uri
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$Contacts
 *  android.provider.ContactsContract$Data
 *  android.support.v4.content.ContextCompat
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.ContactsAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.Contact;
import com.smule.android.network.models.MatchedAccount;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import retrofit2.Call;

public class ContactsManager {
    private static final String a = ContactsManager.class.getName();
    private static ContactsManager b;
    private com.smule.android.network.api.ContactsAPI c = MagicNetwork.a().a(com.smule.android.network.api.ContactsAPI.class);
    private boolean d = false;

    private ContactsManager() {
        this.d = this.g();
    }

    public static ContactsManager a() {
        synchronized (ContactsManager.class) {
            if (b == null) {
                b = new ContactsManager();
            }
            ContactsManager contactsManager = b;
            return contactsManager;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a(Cursor cursor, Contact contact) {
        if (!cursor.getString(cursor.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/phone_v2")) return;
        switch (cursor.getInt(cursor.getColumnIndex("data2"))) {
            default: {
                return;
            }
            case 1: 
            case 2: 
            case 3: 
        }
        contact.b(cursor.getString(cursor.getColumnIndex("data1")));
    }

    private void a(boolean bl) {
        MagicNetwork.e().edit().putBoolean("CONTACTS_CONSENT_AGREEMENT", bl).apply();
        this.d = bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void b(Cursor cursor, Contact contact) {
        if (!cursor.getString(cursor.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/email_v2")) return;
        switch (cursor.getInt(cursor.getColumnIndex("data2"))) {
            default: {
                return;
            }
            case 1: 
            case 2: 
        }
        contact.c(cursor.getString(cursor.getColumnIndex("data1")));
    }

    public static void e() {
        ContactsManager.a().a(UserManager.a().V(), new UpdateConsentCallback(){

            @Override
            public void handleResponse(NetworkResponse networkResponse) {
                if (networkResponse.c()) {
                    UserManager.a().d(true);
                }
            }
        });
    }

    private List<Contact> f() {
        ArrayList<Contact> arrayList = new ArrayList<Contact>();
        Context context = MagicNetwork.d().getApplicationContext();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        uri = context.getContentResolver().query(uri, null, null, null, "display_name ASC ");
        if (uri.moveToFirst()) {
            do {
                Object object;
                long l = uri.getLong(uri.getColumnIndex("_ID"));
                Uri uri2 = ContactsContract.Data.CONTENT_URI;
                uri2 = context.getContentResolver().query(uri2, null, "contact_id = " + l, null, null);
                if (uri2.moveToFirst() && !TextUtils.isEmpty((CharSequence)(object = uri2.getString(uri2.getColumnIndex("display_name"))))) {
                    object = new Contact().a((String)object);
                    do {
                        this.a((Cursor)uri2, (Contact)object);
                        this.b((Cursor)uri2, (Contact)object);
                    } while (uri2.moveToNext());
                    if (!object.emails.isEmpty() || !object.phoneNumbers.isEmpty()) {
                        arrayList.add((Contact)object);
                    }
                }
                uri2.close();
            } while (uri.moveToNext());
        }
        uri.close();
        return arrayList;
    }

    private boolean g() {
        return MagicNetwork.e().getBoolean("CONTACTS_CONSENT_AGREEMENT", false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public NetworkResponse a(ContactsAPI object, ContactsAPI consentState) {
        if ((object = NetworkUtils.a(this.c.updateConsent(new SnpRequest(){
            public ContactsAPI.Consent consent;

            public ContactsAPI setConsent(ContactsAPI.Consent consent) {
                this.consent = consent;
                return this;
            }

            public static class ContactsAPI.Consent {
                public String type;
                public String value;

                public ContactsAPI.Consent setType(ContactsAPI consentType) {
                    this.type = consentType.name();
                    return this;
                }

                public ContactsAPI.Consent setType(String string2) {
                    this.type = string2;
                    return this;
                }

                public ContactsAPI.Consent setValue(ContactsAPI consentState) {
                    this.value = consentState.name();
                    return this;
                }
            }

        }.setConsent(new ContactsAPI.Consent().setType((Object)object).setValue(consentState))))).c()) {
            boolean bl = consentState == ContactsAPI.AGREE;
            this.a(bl);
        }
        return object;
    }

    public NetworkResponse a(String string2) {
        return NetworkUtils.a(this.c.updateConsent(new .setConsent(new ContactsAPI.Consent().setType(string2).setValue(ContactsAPI.AGREE))));
    }

    public Future<?> a(final ContactsAPI consentType, final ContactsAPI consentState, final UpdateConsentCallback updateConsentCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(updateConsentCallback, ContactsManager.this.a(consentType, consentState));
            }
        });
    }

    public Future<?> a(final FindContactsCallback findContactsCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(findContactsCallback, ContactsManager.this.b());
            }
        });
    }

    public Future<?> a(final String string2, final UpdateConsentCallback updateConsentCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(updateConsentCallback, ContactsManager.this.a(string2));
            }
        });
    }

    public boolean a(Context context) {
        if (this.c() && ContextCompat.checkSelfPermission((Context)context, (String)"android.permission.READ_CONTACTS") == 0) {
            return true;
        }
        return false;
    }

    public  b() {
        String string2 = ((TelephonyManager)MagicNetwork.d().getApplicationContext().getSystemService("phone")).getSimCountryIso();
        return .a(NetworkUtils.a(this.c.findContacts(new SnpRequest(){
            public List<Contact> contacts;
            public String simCountryCode;

            public ContactsAPI setContacts(List<Contact> list) {
                this.contacts = list;
                return this;
            }

            public ContactsAPI setSimCountryCode(String string2) {
                this.simCountryCode = string2;
                return this;
            }
        }.setSimCountryCode(string2).setContacts(this.f()))));
    }

    public boolean c() {
        return this.d;
    }

    public NetworkResponse d() {
        return NetworkUtils.a(this.c.clearContacts(new SnpRequest()));
    }

    public static interface ClearContactsCallback
    extends ResponseInterface<NetworkResponse> {
        @Override
        public void handleResponse(NetworkResponse var1);
    }

    public static interface FindContactsCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface UpdateConsentCallback
    extends ResponseInterface<NetworkResponse> {
        @Override
        public void handleResponse(NetworkResponse var1);
    }

}

