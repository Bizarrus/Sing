package com.smule.android.console;

import com.smule.android.C3482R;
import com.smule.android.C3482R.string;

public class HelpCmd {
    private HelpCmd() {
    }

    public static void m17562a(StdOut stdOut) {
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.help_prompt));
    }

    public static void m17565b(StdOut stdOut) {
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.help_commands));
        stdOut.mo6237b("");
        for (CmdInfo$BuiltInCmd cmdInfo$BuiltInCmd : CmdInfo$BuiltInCmd.values()) {
            if (!CmdInfo.a(cmdInfo$BuiltInCmd)) {
                stdOut.mo6237b(cmdInfo$BuiltInCmd.name());
            }
        }
        for (String b : CmdInfo.a.keySet()) {
            stdOut.mo6237b(b);
        }
        stdOut.mo6237b("");
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.cmd_help));
    }

    public static void m17564a(StdOut stdOut, String str) {
        try {
            stdOut.mo6237b(CFunc.m17512a(string.class.getField("cmd_" + str).getInt(null)));
        } catch (Exception e) {
            stdOut.mo6237b(CFunc.m17512a(C3482R.string.cmd_unknown));
        }
    }

    public static void m17563a(StdOut stdOut, CmdInfo$BuiltInCmd cmdInfo$BuiltInCmd) {
        try {
            stdOut.mo6237b(CFunc.m17512a(string.class.getField("usage_" + cmdInfo$BuiltInCmd.name()).getInt(null)));
        } catch (Exception e) {
            stdOut.mo6237b(CFunc.m17512a(C3482R.string.cmd_unknown));
        }
    }
}
