/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Handler
 *  com.fasterxml.jackson.databind.JsonNode
 */
package com.smule.android.console;

import android.os.Build;
import android.os.Handler;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.R;
import com.smule.android.console.CFunc;
import com.smule.android.console.CmdInfo;
import com.smule.android.console.CmdSettings;
import com.smule.android.console.ConstantData;
import com.smule.android.console.ExtCmd;
import com.smule.android.console.HelpCmd;
import com.smule.android.console.StdOut;
import com.smule.android.console.SystemCmd;
import com.smule.android.console.UiSideCommand;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class CommandDispatcher
implements StdOut,
Runnable {
    private Thread a;
    private Semaphore b;
    private UiSideCommand c;
    private CmdInfo d;

    public CommandDispatcher(Handler handler) {
        this.c = new UiSideCommand(handler);
        this.b = new Semaphore(0, true);
        this.a = new Thread(this);
        this.a.start();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a() {
        boolean bl = true;
        while (bl) {
            try {
                this.b.acquire();
                return;
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    private void a(CmdSettings cmdSettings) {
        cmdSettings.a(CmdSettings.Setting.a);
        int n = cmdSettings.b(CmdSettings.Setting.b);
        this.c.a(n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(CmdSettings cmdSettings) {
        HelpCmd.a(this);
        boolean bl = true;
        while (bl) {
            CmdInfo builtInCmd;
            block59 : {
                Object object;
                block61 : {
                    block62 : {
                        long l;
                        block60 : {
                            block58 : {
                                this.a("#:-)> ");
                                this.a();
                                this.b(this.d.c());
                                builtInCmd = this.d.b();
                                ExtCmd extCmd = this.d.a();
                                object = this.d.d();
                                if (extCmd == null) break block58;
                                this.b(extCmd.a((String[])object));
                                break block59;
                            }
                            if (builtInCmd != CmdInfo.f) break block60;
                            this.b("host: " + MagicNetwork.c());
                            object = UserManager.a();
                            this.b("playerId: " + object.g());
                            this.b("handle: " + object.i());
                            this.b("guest: " + object.y());
                            this.b("sessionId: " + MagicNetwork.a().k());
                            this.b("deviceId: " + MagicNetwork.d().getDeviceId());
                            this.b("androidId: " + MagicNetwork.d().getAndroidId());
                            this.b("installDays: " + object.q() / 1000);
                            this.b("model: " + Build.MODEL);
                            break block59;
                        }
                        if (builtInCmd != CmdInfo.m) break block61;
                        if (object == null || object.length != 2) break block62;
                        try {
                            l = Long.parseLong(object[1]);
                        }
                        catch (NumberFormatException numberFormatException) {
                            object = null;
                        }
                        object = l;
                        if (object != null) {
                            UserManager.a().a((Long)(object.longValue() * 1000));
                        } else {
                            HelpCmd.a((StdOut)this, builtInCmd);
                        }
                        break block59;
                    }
                    HelpCmd.a((StdOut)this, builtInCmd);
                    break block59;
                }
                if (builtInCmd != CmdInfo.h) {
                    if (builtInCmd == CmdInfo.i) {
                        this.c.b();
                    } else if (builtInCmd == CmdInfo.d) {
                        object = cmdSettings.b(0);
                        if (object != null && object.size() > 0) {
                            for (Map.Entry entry : object.entrySet()) {
                                this.b(entry.getKey() + ": " + (String)entry.getValue());
                            }
                        }
                    } else if (builtInCmd == CmdInfo.j) {
                        if (object == null || object.length != 2) {
                            HelpCmd.a((StdOut)this, builtInCmd);
                        }
                    } else if (builtInCmd == CmdInfo.k) {
                        MagicNetwork.a().m();
                    } else if (builtInCmd == CmdInfo.l) {
                        if (object != null) {
                            if (object.length == 1) {
                                object = MagicNetwork.d().getAppSettingIDs().iterator();
                                while (object.hasNext()) {
                                    this.d((String)object.next());
                                }
                            } else if (object.length == 2) {
                                this.d((String)((Object)object[1]));
                            } else if (object.length == 3) {
                                JsonNode jsonNode = AppSettingsManager.a().a((String)object[1], (String)object[2]);
                                if (jsonNode != null) {
                                    this.b("<" + (String)object[1] + ", " + (String)object[2] + "> = " + jsonNode.toString());
                                } else {
                                    this.b("<" + (String)object[1] + ", " + (String)object[2] + "> = null");
                                }
                            } else {
                                HelpCmd.a((StdOut)this, builtInCmd);
                            }
                        } else {
                            HelpCmd.a((StdOut)this, builtInCmd);
                        }
                    } else if (builtInCmd == CmdInfo.e) {
                        if (object != null) {
                            if (object.length == 1) {
                                HelpCmd.b(this);
                            } else {
                                HelpCmd.a((StdOut)this, object[1]);
                            }
                        }
                    } else if (builtInCmd == CmdInfo.c) {
                        object = cmdSettings.a(this.d.e());
                        if (object != null) {
                            this.a(CmdInfo.b((String)object));
                        } else {
                            this.c(CFunc.a(R.string.error_history_id_not_found));
                        }
                    } else if (builtInCmd == CmdInfo.n) {
                        this.b("");
                        this.b(CFunc.a(R.string.app_name) + " [" + CFunc.b() + "]");
                        this.b("");
                        this.b(CFunc.c());
                        this.b("");
                    } else if (builtInCmd == CmdInfo.o) {
                        this.c.a();
                    } else if (builtInCmd == CmdInfo.p) {
                        SystemCmd.a(this);
                    } else if (builtInCmd == CmdInfo.q) {
                        if (object != null) {
                            if (object.length == 1) {
                                this.c.d();
                            } else {
                                try {
                                    object = Enum.valueOf(ConstantData.ConsoleFontSize.class, (String)object[1]);
                                    this.c.a(object.a());
                                    cmdSettings.a(CmdSettings.Setting.b, object.a());
                                }
                                catch (Exception exception) {
                                    this.c(CFunc.a(R.string.error_unknown_fontsize));
                                    HelpCmd.a((StdOut)this, builtInCmd);
                                }
                            }
                        }
                    } else if (builtInCmd == CmdInfo.r) {
                        bl = false;
                    } else if (builtInCmd == CmdInfo.b) {
                        bl = false;
                    } else if (builtInCmd == CmdInfo.a && !CFunc.b(this.d.c())) {
                        this.c(CFunc.a(R.string.error_unknown_cmd) + this.d.c());
                    }
                }
            }
            if (builtInCmd == CmdInfo.d || builtInCmd != null && CmdInfo.a(builtInCmd)) continue;
            cmdSettings.b(this.d.c());
        }
        if (this.d.b() == CmdInfo.r) {
            this.c.c();
        }
        cmdSettings.a();
        Log.b("CommandDispatcher", "***** CommandDispatcher ends ****");
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(String string2) {
        Map<String, JsonNode> map = AppSettingsManager.a().a(string2);
        if (map == null) {
            this.b("<" + string2 + "> = null");
            return;
        } else {
            for (Map.Entry<String, JsonNode> entry : map.entrySet()) {
                map = entry.getValue() != null ? entry.getValue().toString() : "null";
                this.b("<" + string2 + ", " + entry.getKey() + "> = " + (String)((Object)map));
            }
        }
    }

    public void a(CmdInfo cmdInfo) {
        this.d = cmdInfo;
        this.b.release();
    }

    @Override
    public void a(String string2) {
        this.c.a(string2);
    }

    public boolean a(int n) {
        this.a(new CmdInfo(n));
        return true;
    }

    @Override
    public void b(String string2) {
        this.c.b(string2);
    }

    public void c(String string2) {
        this.c.c(string2);
    }

    @Override
    public void run() {
        CmdSettings cmdSettings = new CmdSettings();
        this.a(cmdSettings);
        this.b(cmdSettings);
    }
}

