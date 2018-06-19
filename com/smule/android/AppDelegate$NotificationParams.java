package com.smule.android;

import android.net.Uri;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.smule.android.network.core.MagicNetwork;
import org.json.JSONObject;

public class AppDelegate$NotificationParams {
    public final Uri f15539a;
    public final String f15540b;
    public final String f15541c;
    public final String f15542d;
    public final String f15543e;
    public final String f15544f;
    public final String f15545g;

    public AppDelegate$NotificationParams(Bundle bundle) {
        String str = (String) bundle.get("DESTINATION_URI");
        if (str != null) {
            this.f15539a = Uri.parse(str);
        } else {
            this.f15539a = MagicNetwork.d().getDefaultDeepLink();
        }
        this.f15540b = (String) bundle.get("MESSAGE");
        this.f15541c = (String) bundle.get("HEADER");
        this.f15542d = (String) bundle.get("MESSAGE");
        this.f15543e = (String) bundle.get("PARAMS");
        this.f15544f = (String) bundle.get("ICON");
        this.f15545g = (String) bundle.get("TAG");
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ShareConstants.MEDIA_URI, this.f15539a == null ? "null" : this.f15539a.toString());
            jSONObject.put("tickerText", this.f15540b);
            jSONObject.put("header", this.f15541c);
            jSONObject.put("msg", this.f15542d);
            jSONObject.put(NativeProtocol.WEB_DIALOG_PARAMS, this.f15543e);
            jSONObject.put("icon", this.f15544f);
            jSONObject.put("tag", this.f15545g);
            return jSONObject.toString();
        } catch (Exception e) {
            return super.toString();
        }
    }
}
