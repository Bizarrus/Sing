/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.android.console;

import android.support.annotation.NonNull;
import com.smule.android.console.CFunc;
import com.smule.android.console.ExtCmd;
import java.util.HashMap;

public class CmdInfo {
    static HashMap<String, ExtCmd> a = new HashMap();
    private ExtCmd b;
    private  c;
    private String d;
    private String[] e;
    private boolean f;

    public CmdInfo(int n) {
        this.c = .c;
        this.d = "!" + String.valueOf(n);
        this.f = true;
    }

    public CmdInfo( builtInCmd, String string2, String[] arrstring) {
        this.c = builtInCmd;
        this.d = string2;
        this.e = arrstring;
    }

    public CmdInfo(ExtCmd extCmd, String string2, String[] arrstring) {
        this.b = extCmd;
        this.d = string2;
        this.e = arrstring;
    }

    public static ExtCmd a(String string2) {
        return a.get(string2);
    }

    public static void a(ExtCmd extCmd) {
        a.put(extCmd.a(), extCmd);
    }

    public static boolean a( object) {
        if ((object = object.name()).startsWith("_") || object.endsWith("_")) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static CmdInfo b(@NonNull String object) {
        String[] arrstring = object.split(" ");
        if (arrstring.length <= 0) return null;
        ExtCmd extCmd = CmdInfo.a(arrstring[0]);
        if (extCmd == null) return new CmdInfo(Enum.valueOf(.class, arrstring[0]), (String)object, arrstring);
        try {
            return new CmdInfo(extCmd, (String)object, arrstring);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    public static void f() {
        a.clear();
    }

    public ExtCmd a() {
        return this.b;
    }

    public  b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String[] d() {
        return this.e;
    }

    public int e() {
        return CFunc.a(this.d.substring(1));
    }

}

