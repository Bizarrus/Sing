/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Contact {
    @JsonProperty(value="emails")
    public ArrayList<String> emails = new ArrayList();
    @JsonProperty(value="phoneNumbers")
    public ArrayList<String> phoneNumbers = new ArrayList();
    @JsonProperty(value="realName")
    public String realName;

    public Contact a(String string2) {
        this.realName = string2;
        return this;
    }

    public Contact b(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.phoneNumbers.add(string2);
        }
        return this;
    }

    public Contact c(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.emails.add(string2);
        }
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        boolean bl = true;
        boolean bl2 = false;
        if (this == object) {
            return true;
        }
        boolean bl3 = bl2;
        if (object == null) return bl3;
        bl3 = bl2;
        if (this.getClass() != object.getClass()) return bl3;
        object = (Contact)object;
        if (this.realName != null) {
            bl3 = bl2;
            if (!this.realName.equals(object.realName)) return bl3;
        } else if (object.realName != null) {
            return false;
        }
        if (this.phoneNumbers != null) {
            bl3 = bl2;
            if (!this.phoneNumbers.equals(object.phoneNumbers)) return bl3;
        } else if (object.phoneNumbers != null) {
            return false;
        }
        if (this.emails != null) {
            return this.emails.equals(object.emails);
        }
        bl3 = bl;
        if (object.emails == null) return bl3;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 0;
        int n2 = this.realName != null ? this.realName.hashCode() : 0;
        int n3 = this.phoneNumbers != null ? this.phoneNumbers.hashCode() : 0;
        if (this.emails != null) {
            n = this.emails.hashCode();
        }
        return (n3 + n2 * 31) * 31 + n;
    }

    public String toString() {
        return "Contact{realName='" + this.realName + '\'' + ", phoneNumbers=" + this.phoneNumbers + ", emails=" + this.emails + '}';
    }
}

