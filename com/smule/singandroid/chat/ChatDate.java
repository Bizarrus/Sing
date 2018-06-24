/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 */
package com.smule.singandroid.chat;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.smule.singandroid.SingApplication;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ChatDate {
    protected final Resources a;
    protected final Date b = new Date();
    protected final Calendar c = Calendar.getInstance();
    protected final Date d;
    protected final Calendar e;
    protected final Calendar f = Calendar.getInstance();
    protected final Locale g;

    public ChatDate(Date date) {
        this(date, SingApplication.g());
    }

    public ChatDate(Date date, Context context) {
        this.d = date;
        this.e = Calendar.getInstance();
        this.e.setTime(date);
        this.a = context.getResources();
        this.g = this.a.getConfiguration().locale;
    }

    public String a() {
        return this.a(false);
    }

    public String a(boolean bl) {
        return this.a(bl, false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String a(boolean bl, boolean bl2) {
        String string2;
        if (this.c()) {
            String string3;
            if (bl2) {
                string3 = this.a.getString(2131296477);
                do {
                    return new SimpleDateFormat(string3, this.g).format(this.d);
                    break;
                } while (true);
            }
            string3 = this.a.getString(2131296598);
            return new SimpleDateFormat(string3, this.g).format(this.d);
        }
        if (this.d()) {
            String string4;
            if (bl) {
                string4 = this.a.getString(2131296480);
                do {
                    return new SimpleDateFormat(string4, this.g).format(this.d);
                    break;
                } while (true);
            }
            string4 = this.a.getString(2131296479);
            return new SimpleDateFormat(string4, this.g).format(this.d);
        }
        if (this.e()) {
            String string5;
            if (bl) {
                string5 = this.a.getString(2131296478);
                do {
                    return new SimpleDateFormat(string5, this.g).format(this.d);
                    break;
                } while (true);
            }
            string5 = "cccc";
            return new SimpleDateFormat(string5, this.g).format(this.d);
        }
        if (this.f()) {
            String string6;
            if (bl) {
                string6 = this.a.getString(2131296600);
                do {
                    return new SimpleDateFormat(string6, this.g).format(this.d);
                    break;
                } while (true);
            }
            string6 = this.a.getString(2131296599);
            return new SimpleDateFormat(string6, this.g).format(this.d);
        }
        if (bl) {
            string2 = this.a.getString(2131296602);
            do {
                return new SimpleDateFormat(string2, this.g).format(this.d);
                break;
            } while (true);
        }
        string2 = this.a.getString(2131296601);
        return new SimpleDateFormat(string2, this.g).format(this.d);
    }

    public boolean a(ChatDate chatDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(chatDate.d);
        if (this.e.get(1) == calendar.get(1) && this.e.get(6) == calendar.get(6)) {
            return true;
        }
        return false;
    }

    public boolean a(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (this.c.get(1) == calendar.get(1) && this.c.get(6) == calendar.get(6)) {
            return true;
        }
        return false;
    }

    public String b() {
        return new SimpleDateFormat(this.a.getString(2131296598), this.g).format(this.d);
    }

    public boolean b(Date date) {
        this.f.setTime(date);
        this.f.add(6, 1);
        if (this.c.get(1) == this.f.get(1) && this.f.get(6) == this.c.get(6)) {
            return true;
        }
        return false;
    }

    public boolean c() {
        return this.a(this.d);
    }

    public boolean c(Date date) {
        if (date.getTime() + TimeUnit.DAYS.toMillis(7) > this.b.getTime()) {
            return true;
        }
        return false;
    }

    public boolean d() {
        return this.b(this.d);
    }

    public boolean d(Date date) {
        this.f.setTime(date);
        if (this.c.get(1) == this.f.get(1)) {
            return true;
        }
        return false;
    }

    public boolean e() {
        return this.c(this.d);
    }

    public boolean f() {
        return this.d(this.d);
    }
}

