/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 */
package com.smule.android.console;

import android.os.Handler;
import android.os.Message;
import com.smule.android.R;
import com.smule.android.console.CFunc;
import com.smule.android.console.ConstantData;

public class UiSideCommand {
    private Handler a;

    public UiSideCommand(Handler handler) {
        this.a = handler;
    }

    private void a(ConstantData.MsgType msgType) {
        this.a(msgType, null);
    }

    public void a() {
        this.a(ConstantData.MsgType.c);
    }

    public void a(int n) {
        this.a(ConstantData.MsgType.d, n);
    }

    public void a(ConstantData.MsgType msgType, Object object) {
        Message message = Message.obtain();
        message.what = msgType.ordinal();
        message.setTarget(this.a);
        message.obj = object;
        message.sendToTarget();
    }

    public void a(String string2) {
        this.a(ConstantData.MsgType.a, string2);
    }

    public void b() {
        this.a(ConstantData.MsgType.b);
    }

    public void b(String string2) {
        this.a(ConstantData.MsgType.a, string2 + "\n");
    }

    public void c() {
        this.a(ConstantData.MsgType.f);
    }

    public void c(String string2) {
        this.b(CFunc.a(R.string.error_prefix) + string2);
    }

    public void d() {
        this.a(ConstantData.MsgType.e);
    }
}

