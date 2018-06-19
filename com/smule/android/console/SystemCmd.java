package com.smule.android.console;

import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.smule.android.C3482R;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class SystemCmd {
    private SystemCmd() {
    }

    public static void m17568a(StdOut stdOut) {
        WifiManager wifiManager = (WifiManager) CFunc.m17511a().getSystemService("wifi");
        if (wifiManager != null && wifiManager.isWifiEnabled() && wifiManager.getWifiState() == 3) {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
            stdOut.mo6237b(CFunc.m17512a(C3482R.string.netinfo_wifiinfo));
            stdOut.mo6236a(CFunc.m17512a(C3482R.string.netinfo_ssid) + ": ");
            stdOut.mo6237b(connectionInfo.getSSID());
            stdOut.mo6236a(CFunc.m17512a(C3482R.string.netinfo_ipaddr) + ": ");
            stdOut.mo6237b(CFunc.m17516b(dhcpInfo.ipAddress));
            stdOut.mo6236a(CFunc.m17512a(C3482R.string.netinfo_netmask) + ": ");
            stdOut.mo6237b(CFunc.m17516b(dhcpInfo.netmask));
            stdOut.mo6236a(CFunc.m17512a(C3482R.string.netinfo_gateway) + ": ");
            stdOut.mo6237b(CFunc.m17516b(dhcpInfo.gateway));
            stdOut.mo6236a(CFunc.m17512a(C3482R.string.netinfo_dns1) + ": ");
            stdOut.mo6237b(CFunc.m17516b(dhcpInfo.dns1));
            stdOut.mo6236a(CFunc.m17512a(C3482R.string.netinfo_dns2) + ": ");
            stdOut.mo6237b(CFunc.m17516b(dhcpInfo.dns2));
            return;
        }
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                stdOut.mo6236a(CFunc.m17512a(C3482R.string.netinfo_netintf_name) + ": ");
                stdOut.mo6237b(networkInterface.getDisplayName());
                Enumeration inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    stdOut.mo6236a(CFunc.m17512a(C3482R.string.netinfo_ipaddr) + ": ");
                    stdOut.mo6237b(CFunc.m17513a(inetAddress.getAddress()));
                }
            }
        } catch (Exception e) {
            stdOut.mo6237b(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
