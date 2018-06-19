package com.smule.android.console;

import android.support.annotation.NonNull;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;

public class CmdInfo {
    static HashMap<String, ExtCmd> f6672a = new HashMap();
    private ExtCmd f6673b;
    private BuiltInCmd f6674c;
    private String f6675d;
    private String[] f6676e;
    private boolean f6677f;

    public CmdInfo(ExtCmd extCmd, String str, String[] strArr) {
        this.f6673b = extCmd;
        this.f6675d = str;
        this.f6676e = strArr;
    }

    public CmdInfo(BuiltInCmd builtInCmd, String str, String[] strArr) {
        this.f6674c = builtInCmd;
        this.f6675d = str;
        this.f6676e = strArr;
    }

    public CmdInfo(int i) {
        this.f6674c = BuiltInCmd.c;
        this.f6675d = "!" + String.valueOf(i);
        this.f6677f = true;
    }

    public ExtCmd m7667a() {
        return this.f6673b;
    }

    public BuiltInCmd m7668b() {
        return this.f6674c;
    }

    public String m7669c() {
        return this.f6675d;
    }

    public String[] m7670d() {
        return this.f6676e;
    }

    public int m7671e() {
        return CFunc.a(this.f6675d.substring(1));
    }

    public static void m7663a(ExtCmd extCmd) {
        f6672a.put(extCmd.mo4035a(), extCmd);
    }

    public static ExtCmd m7662a(String str) {
        return (ExtCmd) f6672a.get(str);
    }

    public static void m7666f() {
        f6672a.clear();
    }

    public static CmdInfo m7665b(@NonNull String str) {
        String[] split = str.split(" ");
        if (split.length > 0) {
            try {
                ExtCmd a = m7662a(split[0]);
                if (a != null) {
                    return new CmdInfo(a, str, split);
                }
                return new CmdInfo((BuiltInCmd) Enum.valueOf(BuiltInCmd.class, split[0]), str, split);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static boolean m7664a(BuiltInCmd builtInCmd) {
        String name = builtInCmd.name();
        return name.startsWith(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR) || name.endsWith(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
    }
}
