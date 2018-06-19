package com.smule.android.console;

import android.os.Handler;
import android.os.Message;
import com.smule.android.C3482R;
import com.smule.android.console.ConstantData.MsgType;

public class UiSideCommand {
    private Handler f15774a;

    public UiSideCommand(Handler handler) {
        this.f15774a = handler;
    }

    public void m17573a(MsgType msgType, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = msgType.ordinal();
        obtain.setTarget(this.f15774a);
        obtain.obj = obj;
        obtain.sendToTarget();
    }

    private void m17570a(MsgType msgType) {
        m17573a(msgType, null);
    }

    public void m17574a(String str) {
        m17573a(MsgType.STRING, str);
    }

    public void m17576b(String str) {
        m17573a(MsgType.STRING, str + "\n");
    }

    public void m17578c(String str) {
        m17576b(CFunc.m17512a(C3482R.string.error_prefix) + str);
    }

    public void m17571a() {
        m17570a(MsgType.SHOWSRES);
    }

    public void m17575b() {
        m17570a(MsgType.CLEAR);
    }

    public void m17577c() {
        m17570a(MsgType.EXIT);
    }

    public void m17572a(int i) {
        m17573a(MsgType.SETFONTSIZE, Integer.valueOf(i));
    }

    public void m17579d() {
        m17570a(MsgType.SHOWFONTSIZE);
    }
}
