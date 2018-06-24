/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  com.fasterxml.jackson.databind.JsonNode
 */
package com.smule.android.console.commands;

import android.content.Context;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.console.ExtCmd;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.ListUtils;
import java.util.List;
import java.util.Map;

public class AppSettingsCmd
implements ExtCmd {
    private Context a;

    public AppSettingsCmd(Context context) {
        this.a = context;
    }

    @Override
    public String a() {
        return "settings";
    }

    @Override
    public String a(String[] object) {
        if (object != null && object.length == 1) {
            MagicNetwork.a();
            object = MagicNetwork.d().getAppSettingIDs();
            return "Fetching app settings:\n" + ListUtils.a(object, "\n") + "\n";
        }
        if (object != null && object.length == 2) {
            object = object[1];
            Map<String, JsonNode> map = AppSettingsManager.a().a((String)object);
            return "Values for setting family " + (String)object + ":\n" + JsonUtils.a(map) + "\n";
        }
        return "";
    }
}

