package com.smule.android.console;

import android.os.Build;
import android.os.Handler;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.C3482R;
import com.smule.android.console.CmdSettings.Setting;
import com.smule.android.console.ConstantData.ConsoleFontSize;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

public class CommandDispatcher implements StdOut, Runnable {
    private Thread f15723a = new Thread(this);
    private Semaphore f15724b = new Semaphore(0, true);
    private UiSideCommand f15725c;
    private CmdInfo f15726d;

    public CommandDispatcher(Handler handler) {
        this.f15725c = new UiSideCommand(handler);
        this.f15723a.start();
    }

    public void m17540a(CmdInfo cmdInfo) {
        this.f15726d = cmdInfo;
        this.f15724b.release();
    }

    public boolean m17542a(int i) {
        m17540a(new CmdInfo(i));
        return true;
    }

    public void run() {
        CmdSettings cmdSettings = new CmdSettings();
        m17537a(cmdSettings);
        m17538b(cmdSettings);
    }

    private void m17536a() {
        Object obj = 1;
        while (obj != null) {
            try {
                this.f15724b.acquire();
                obj = null;
            } catch (InterruptedException e) {
            }
        }
    }

    public void mo6236a(String str) {
        this.f15725c.m17574a(str);
    }

    public void mo6237b(String str) {
        this.f15725c.m17576b(str);
    }

    public void m17544c(String str) {
        this.f15725c.m17578c(str);
    }

    private void m17537a(CmdSettings cmdSettings) {
        cmdSettings.m17529a(Setting.ls_full_mode);
        this.f15725c.m17572a(cmdSettings.m17530b(Setting.console_font_size));
    }

    private void m17538b(CmdSettings cmdSettings) {
        HelpCmd.m17562a(this);
        int i;
        for (int i2 = 1; i2 != 0; i2 = i) {
            mo6236a("#:-)> ");
            m17536a();
            mo6237b(this.f15726d.c());
            CmdInfo$BuiltInCmd b = this.f15726d.b();
            ExtCmd a = this.f15726d.a();
            String[] d = this.f15726d.d();
            if (a != null) {
                mo6237b(a.a(d));
                i = i2;
            } else if (b == CmdInfo$BuiltInCmd.env) {
                mo6237b("host: " + MagicNetwork.c());
                UserManager a2 = UserManager.a();
                mo6237b("playerId: " + a2.g());
                mo6237b("handle: " + a2.i());
                mo6237b("guest: " + a2.z());
                mo6237b("sessionId: " + MagicNetwork.a().k());
                mo6237b("deviceId: " + MagicNetwork.d().getDeviceId());
                mo6237b("androidId: " + MagicNetwork.d().getAndroidId());
                mo6237b("installDays: " + (a2.q().longValue() / 1000));
                mo6237b("model: " + Build.MODEL);
                i = i2;
            } else if (b == CmdInfo$BuiltInCmd.days) {
                if (d == null || d.length != 2) {
                    HelpCmd.m17563a((StdOut) this, b);
                    i = i2;
                } else {
                    Long valueOf;
                    try {
                        valueOf = Long.valueOf(Long.parseLong(d[1]));
                    } catch (NumberFormatException e) {
                        valueOf = null;
                    }
                    if (valueOf != null) {
                        UserManager.a().a(Long.valueOf(valueOf.longValue() * 1000));
                    } else {
                        HelpCmd.m17563a((StdOut) this, b);
                    }
                    i = i2;
                }
            } else if (b == CmdInfo$BuiltInCmd.crash) {
                i = i2;
            } else if (b == CmdInfo$BuiltInCmd.cls) {
                this.f15725c.m17575b();
                i = i2;
            } else if (b == CmdInfo$BuiltInCmd.history) {
                LinkedHashMap b2 = cmdSettings.m17531b(0);
                if (b2 != null && b2.size() > 0) {
                    for (Entry entry : b2.entrySet()) {
                        mo6237b(entry.getKey() + ": " + ((String) entry.getValue()));
                    }
                }
                i = i2;
            } else if (b == CmdInfo$BuiltInCmd.req) {
                if (d == null || d.length != 2) {
                    HelpCmd.m17563a((StdOut) this, b);
                    i = i2;
                } else {
                    i = i2;
                }
            } else if (b == CmdInfo$BuiltInCmd.expire) {
                MagicNetwork.a().m();
                i = i2;
            } else if (b != CmdInfo$BuiltInCmd.settings) {
                if (b == CmdInfo$BuiltInCmd.help) {
                    if (d != null) {
                        if (d.length == 1) {
                            HelpCmd.m17565b(this);
                            i = i2;
                        } else {
                            HelpCmd.m17564a((StdOut) this, d[1]);
                            i = i2;
                        }
                    }
                } else if (b == CmdInfo$BuiltInCmd._history_cmd_) {
                    r0 = cmdSettings.m17524a(this.f15726d.e());
                    if (r0 != null) {
                        m17540a(CmdInfo.b(r0));
                    } else {
                        m17544c(CFunc.m17512a(C3482R.string.error_history_id_not_found));
                    }
                    i = i2;
                } else if (b == CmdInfo$BuiltInCmd.ver) {
                    mo6237b("");
                    mo6237b(CFunc.m17512a(C3482R.string.app_name) + " [" + CFunc.m17515b() + "]");
                    mo6237b("");
                    mo6237b(CFunc.m17518c());
                    mo6237b("");
                    i = i2;
                } else if (b == CmdInfo$BuiltInCmd.sres) {
                    this.f15725c.m17571a();
                    i = i2;
                } else if (b == CmdInfo$BuiltInCmd.netinfo) {
                    SystemCmd.m17568a(this);
                    i = i2;
                } else if (b == CmdInfo$BuiltInCmd.fontsize) {
                    if (d != null) {
                        if (d.length == 1) {
                            this.f15725c.m17579d();
                            i = i2;
                        } else {
                            try {
                                ConsoleFontSize consoleFontSize = (ConsoleFontSize) Enum.valueOf(ConsoleFontSize.class, d[1]);
                                this.f15725c.m17572a(consoleFontSize.m17550a());
                                cmdSettings.m17527a(Setting.console_font_size, Integer.valueOf(consoleFontSize.m17550a()));
                                i = i2;
                            } catch (Exception e2) {
                                m17544c(CFunc.m17512a(C3482R.string.error_unknown_fontsize));
                                HelpCmd.m17563a((StdOut) this, b);
                                i = i2;
                            }
                        }
                    }
                } else if (b == CmdInfo$BuiltInCmd.exit) {
                    i = 0;
                } else if (b == CmdInfo$BuiltInCmd._ui_exit_) {
                    i = 0;
                } else if (b == CmdInfo$BuiltInCmd._unknown_ && !CFunc.m17517b(this.f15726d.c())) {
                    m17544c(CFunc.m17512a(C3482R.string.error_unknown_cmd) + this.f15726d.c());
                }
                i = i2;
            } else if (d == null) {
                HelpCmd.m17563a((StdOut) this, b);
                i = i2;
            } else if (d.length == 1) {
                for (String d2 : MagicNetwork.d().getAppSettingIDs()) {
                    m17539d(d2);
                }
                i = i2;
            } else if (d.length == 2) {
                m17539d(d[1]);
                i = i2;
            } else if (d.length == 3) {
                JsonNode a3 = AppSettingsManager.a().a(d[1], d[2]);
                if (a3 != null) {
                    mo6237b("<" + d[1] + ", " + d[2] + "> = " + a3.toString());
                } else {
                    mo6237b("<" + d[1] + ", " + d[2] + "> = null");
                }
                i = i2;
            } else {
                HelpCmd.m17563a((StdOut) this, b);
                i = i2;
            }
            if (b != CmdInfo$BuiltInCmd.history && (b == null || !CmdInfo.a(b))) {
                cmdSettings.m17532b(this.f15726d.c());
            }
        }
        if (this.f15726d.b() == CmdInfo$BuiltInCmd.exit) {
            this.f15725c.m17577c();
        }
        cmdSettings.m17526a();
        Log.b("CommandDispatcher", "***** CommandDispatcher ends ****");
    }

    private void m17539d(String str) {
        Map a = AppSettingsManager.a().a(str);
        if (a != null) {
            for (Entry entry : a.entrySet()) {
                mo6237b("<" + str + ", " + ((String) entry.getKey()) + "> = " + (entry.getValue() != null ? ((JsonNode) entry.getValue()).toString() : "null"));
            }
            return;
        }
        mo6237b("<" + str + "> = null");
    }
}
