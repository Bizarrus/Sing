/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.net.DhcpInfo
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 */
package com.smule.android.console;

import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.smule.android.R;
import com.smule.android.console.CFunc;
import com.smule.android.console.StdOut;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class SystemCmd {
    private SystemCmd() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(StdOut stdOut) {
        WifiManager wifiManager = (WifiManager)CFunc.a().getSystemService("wifi");
        if (wifiManager != null && wifiManager.isWifiEnabled() && wifiManager.getWifiState() == 3) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            wifiManager = wifiManager.getDhcpInfo();
            stdOut.b(CFunc.a(R.string.netinfo_wifiinfo));
            stdOut.a(CFunc.a(R.string.netinfo_ssid) + ": ");
            stdOut.b(wifiInfo.getSSID());
            stdOut.a(CFunc.a(R.string.netinfo_ipaddr) + ": ");
            stdOut.b(CFunc.b(wifiManager.ipAddress));
            stdOut.a(CFunc.a(R.string.netinfo_netmask) + ": ");
            stdOut.b(CFunc.b(wifiManager.netmask));
            stdOut.a(CFunc.a(R.string.netinfo_gateway) + ": ");
            stdOut.b(CFunc.b(wifiManager.gateway));
            stdOut.a(CFunc.a(R.string.netinfo_dns1) + ": ");
            stdOut.b(CFunc.b(wifiManager.dns1));
            stdOut.a(CFunc.a(R.string.netinfo_dns2) + ": ");
            stdOut.b(CFunc.b(wifiManager.dns2));
            return;
        }
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            block2 : do {
                if (!enumeration.hasMoreElements()) return;
                wifiManager = enumeration.nextElement();
                stdOut.a(CFunc.a(R.string.netinfo_netintf_name) + ": ");
                stdOut.b(wifiManager.getDisplayName());
                wifiManager = wifiManager.getInetAddresses();
                do {
                    if (!wifiManager.hasMoreElements()) continue block2;
                    InetAddress inetAddress = wifiManager.nextElement();
                    stdOut.a(CFunc.a(R.string.netinfo_ipaddr) + ": ");
                    stdOut.b(CFunc.a(inetAddress.getAddress()));
                } while (true);
                break;
            } while (true);
        }
        catch (Exception exception) {
            stdOut.b(exception.getClass().getName() + ": " + exception.getMessage());
            return;
        }
    }
}

