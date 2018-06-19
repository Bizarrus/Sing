package com.smule.android.console.commands;

import android.content.Context;
import com.smule.android.console.ExtCmd;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.ListUtils;

public class AppSettingsCmd implements ExtCmd {
    private Context f6678a;

    public AppSettingsCmd(Context context) {
        this.f6678a = context;
    }

    public String mo4035a() {
        return "settings";
    }

    public String mo4036a(String[] strArr) {
        if (strArr != null && strArr.length == 1) {
            MagicNetwork.m7789a();
            return "Fetching app settings:\n" + ListUtils.a(MagicNetwork.m7804d().getAppSettingIDs(), "\n") + "\n";
        } else if (strArr == null || strArr.length != 2) {
            return "";
        } else {
            String str = strArr[1];
            return "Values for setting family " + str + ":\n" + JsonUtils.a(AppSettingsManager.m7855a().m7878a(str)) + "\n";
        }
    }
}
