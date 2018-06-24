/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.console;

import com.smule.android.R;
import com.smule.android.console.CFunc;
import com.smule.android.console.CmdInfo;
import com.smule.android.console.ExtCmd;
import com.smule.android.console.StdOut;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HelpCmd {
    private HelpCmd() {
    }

    public static void a(StdOut stdOut) {
        stdOut.b(CFunc.a(R.string.help_prompt));
    }

    public static void a(StdOut stdOut, CmdInfo builtInCmd) {
        try {
            stdOut.b(CFunc.a(R.string.class.getField("usage_" + builtInCmd.name()).getInt(null)));
            return;
        }
        catch (Exception exception) {
            stdOut.b(CFunc.a(R.string.cmd_unknown));
            return;
        }
    }

    public static void a(StdOut stdOut, String string2) {
        try {
            stdOut.b(CFunc.a(R.string.class.getField("cmd_" + string2).getInt(null)));
            return;
        }
        catch (Exception exception) {
            stdOut.b(CFunc.a(R.string.cmd_unknown));
            return;
        }
    }

    public static void b(StdOut stdOut) {
        stdOut.b(CFunc.a(R.string.help_commands));
        stdOut.b("");
        for (CmdInfo builtInCmd : CmdInfo.values()) {
            if (CmdInfo.a(builtInCmd)) continue;
            stdOut.b(builtInCmd.name());
        }
        Iterator<String> iterator = CmdInfo.a.keySet().iterator();
        while (iterator.hasNext()) {
            stdOut.b((String)iterator.next());
        }
        stdOut.b("");
        stdOut.b(CFunc.a(R.string.cmd_help));
    }
}

