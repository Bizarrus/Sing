package com.smule.singandroid.chat;

import android.content.Context;
import android.content.res.Resources;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ChatDate {
    protected final Resources f20777a;
    protected final Date f20778b;
    protected final Calendar f20779c;
    protected final Date f20780d;
    protected final Calendar f20781e;
    protected final Calendar f20782f;
    protected final Locale f20783g;

    public ChatDate(Date date, Context context) {
        this.f20782f = Calendar.getInstance();
        this.f20778b = new Date();
        this.f20779c = Calendar.getInstance();
        this.f20780d = date;
        this.f20781e = Calendar.getInstance();
        this.f20781e.setTime(date);
        this.f20777a = context.getResources();
        this.f20783g = this.f20777a.getConfiguration().locale;
    }

    public ChatDate(Date date) {
        this(date, SingApplication.f());
    }

    public String m22429a() {
        return m22430a(false);
    }

    public String m22430a(boolean z) {
        return m22431a(z, false);
    }

    public String m22431a(boolean z, boolean z2) {
        if (m22436c()) {
            return new SimpleDateFormat(z2 ? this.f20777a.getString(C1947R.string.chat_day_stamp_today_with_time) : this.f20777a.getString(C1947R.string.chat_timestamp_hmm), this.f20783g).format(this.f20780d);
        } else if (m22438d()) {
            if (z) {
                r0 = this.f20777a.getString(C1947R.string.chat_day_stamp_yesterday_with_time);
            } else {
                r0 = this.f20777a.getString(C1947R.string.chat_day_stamp_yesterday);
            }
            return new SimpleDateFormat(r0, this.f20783g).format(this.f20780d);
        } else if (m22440e()) {
            return new SimpleDateFormat(z ? this.f20777a.getString(C1947R.string.chat_day_stamp_weekday_with_time) : "cccc", this.f20783g).format(this.f20780d);
        } else if (m22441f()) {
            if (z) {
                r0 = this.f20777a.getString(C1947R.string.chat_timestamp_md_with_time);
            } else {
                r0 = this.f20777a.getString(C1947R.string.chat_timestamp_md);
            }
            return new SimpleDateFormat(r0, this.f20783g).format(this.f20780d);
        } else {
            if (z) {
                r0 = this.f20777a.getString(C1947R.string.chat_timestamp_mdy_with_time);
            } else {
                r0 = this.f20777a.getString(C1947R.string.chat_timestamp_mdy);
            }
            return new SimpleDateFormat(r0, this.f20783g).format(this.f20780d);
        }
    }

    public String m22434b() {
        return new SimpleDateFormat(this.f20777a.getString(C1947R.string.chat_timestamp_hmm), this.f20783g).format(this.f20780d);
    }

    public boolean m22433a(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (this.f20779c.get(1) == instance.get(1) && this.f20779c.get(6) == instance.get(6)) {
            return true;
        }
        return false;
    }

    public boolean m22435b(Date date) {
        this.f20782f.setTime(date);
        this.f20782f.add(6, 1);
        if (this.f20779c.get(1) == this.f20782f.get(1) && this.f20782f.get(6) == this.f20779c.get(6)) {
            return true;
        }
        return false;
    }

    public boolean m22437c(Date date) {
        return date.getTime() + TimeUnit.DAYS.toMillis(7) > this.f20778b.getTime();
    }

    public boolean m22439d(Date date) {
        this.f20782f.setTime(date);
        if (this.f20779c.get(1) == this.f20782f.get(1)) {
            return true;
        }
        return false;
    }

    public boolean m22432a(ChatDate chatDate) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(chatDate.f20780d);
        if (this.f20781e.get(1) == instance.get(1) && this.f20781e.get(6) == instance.get(6)) {
            return true;
        }
        return false;
    }

    public boolean m22436c() {
        return m22433a(this.f20780d);
    }

    public boolean m22438d() {
        return m22435b(this.f20780d);
    }

    public boolean m22440e() {
        return m22437c(this.f20780d);
    }

    public boolean m22441f() {
        return m22439d(this.f20780d);
    }
}
