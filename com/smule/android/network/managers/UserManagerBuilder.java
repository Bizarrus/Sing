/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.managers;

import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.utils.EmailOptIn;
import java.util.List;

public class UserManagerBuilder {
    public String A;
    public EmailOptIn B;
    public String C;
    public List<String> D;
    public ProfileCustomizations E;
    public long a = 0;
    public long b = 0;
    public String c = null;
    public String d = null;
    public String e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public UserManager m = null;
    public Long n = 0;
    public int o = 0;
    public String p;
    public String q;
    public String r;
    public int s;
    public String t;
    public String u;
    public String v;
    public String w;
    public boolean x;
    public String y;
    public String z;

    public UserManagerBuilder a(int n) {
        this.o = n;
        return this;
    }

    public UserManagerBuilder a(long l) {
        this.a = l;
        return this;
    }

    public UserManagerBuilder a(UserManager loginType) {
        this.m = loginType;
        return this;
    }

    public UserManagerBuilder a(ProfileCustomizations profileCustomizations) {
        this.E = profileCustomizations;
        return this;
    }

    public UserManagerBuilder a(EmailOptIn emailOptIn) {
        this.B = emailOptIn;
        return this;
    }

    public UserManagerBuilder a(Long l) {
        this.n = l;
        return this;
    }

    public UserManagerBuilder a(String string2) {
        this.c = string2;
        return this;
    }

    public UserManagerBuilder a(List<String> list) {
        this.D = list;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public UserManagerBuilder a(boolean bl) {
        bl = !bl;
        this.x = bl;
        return this;
    }

    public UserManagerBuilder b(int n) {
        this.s = n;
        return this;
    }

    public UserManagerBuilder b(long l) {
        this.b = l;
        return this;
    }

    public UserManagerBuilder b(String string2) {
        this.d = string2;
        return this;
    }

    public UserManagerBuilder c(String string2) {
        this.f = string2;
        return this;
    }

    public UserManagerBuilder d(String string2) {
        this.e = string2;
        return this;
    }

    public UserManagerBuilder e(String string2) {
        this.g = string2;
        return this;
    }

    public UserManagerBuilder f(String string2) {
        this.h = string2;
        return this;
    }

    public UserManagerBuilder g(String string2) {
        this.i = string2;
        return this;
    }

    public UserManagerBuilder h(String string2) {
        this.j = string2;
        return this;
    }

    public UserManagerBuilder i(String string2) {
        this.k = string2;
        return this;
    }

    public UserManagerBuilder j(String string2) {
        this.l = string2;
        return this;
    }

    public UserManagerBuilder k(String string2) {
        this.q = string2;
        return this;
    }

    public UserManagerBuilder l(String string2) {
        this.u = string2;
        return this;
    }

    public UserManagerBuilder m(String string2) {
        this.p = string2;
        return this;
    }

    public UserManagerBuilder n(String string2) {
        this.C = string2;
        return this;
    }

    public UserManagerBuilder o(String string2) {
        this.r = string2;
        return this;
    }

    public UserManagerBuilder p(String string2) {
        this.t = string2;
        return this;
    }

    public UserManagerBuilder q(String string2) {
        this.v = string2;
        return this;
    }

    public UserManagerBuilder r(String string2) {
        this.w = string2;
        return this;
    }

    public UserManagerBuilder s(String string2) {
        this.y = string2;
        return this;
    }

    public UserManagerBuilder t(String string2) {
        this.z = string2;
        return this;
    }

    public UserManagerBuilder u(String string2) {
        this.A = string2;
        return this;
    }
}

