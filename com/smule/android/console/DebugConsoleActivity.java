/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  android.text.Editable
 *  android.util.DisplayMetrics
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnKeyListener
 *  android.view.WindowManager
 *  android.widget.EditText
 *  android.widget.ImageButton
 *  android.widget.LinearLayout
 */
package com.smule.android.console;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.smule.android.R;
import com.smule.android.console.CFunc;
import com.smule.android.console.CmdInfo;
import com.smule.android.console.CommandDispatcher;
import com.smule.android.console.ConsoleOutputTextView;
import com.smule.android.console.ConsoleScrollView;
import com.smule.android.console.ConstantData;
import com.smule.android.console.StdOut;
import com.smule.android.console.UiCmd;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.utils.NotificationCenter;
import java.util.Observable;
import java.util.Observer;

public class DebugConsoleActivity
extends Activity
implements StdOut {
    private ConsoleScrollView a;
    private ConsoleOutputTextView b;
    private CommandDispatcher c;
    private Observer d;
    private View.OnClickListener e;
    private View.OnKeyListener f;
    private Runnable g;
    private Runnable h;
    private final Handler i;

    public DebugConsoleActivity() {
        this.d = new Observer(){

            @Override
            public void update(Observable object, Object object2) {
                if (object2 != null) {
                    object = (Runnable)object2;
                    DebugConsoleActivity.this.runOnUiThread(new Runnable((Runnable)object){
                        final /* synthetic */ Runnable a;
                        {
                            this.a = runnable;
                        }

                        @Override
                        public void run() {
                            new Handler().postDelayed(new Runnable(){

                                @Override
                                public void run() {
                                    1.this.a.run();
                                }
                            }, 1000);
                        }

                    });
                }
                DebugConsoleActivity.this.finish();
            }

        };
        this.e = new View.OnClickListener(){

            public void onClick(View view) {
                DebugConsoleActivity.this.a();
            }
        };
        this.f = new View.OnKeyListener(){

            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                if (n == 66 && keyEvent.getAction() == 1) {
                    DebugConsoleActivity.this.a();
                    return true;
                }
                return false;
            }
        };
        this.g = new Runnable(){

            @Override
            public void run() {
                if (DebugConsoleActivity.this.b.getViewableHeight() > DebugConsoleActivity.this.a.getHeight()) {
                    DebugConsoleActivity.this.a.scrollTo(0, DebugConsoleActivity.this.b.getHeight());
                }
            }
        };
        this.h = new Runnable(){

            @Override
            public void run() {
                DebugConsoleActivity.this.a.scrollTo(0, 0);
            }
        };
        this.i = new Handler(Looper.getMainLooper()){

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                if (message.what == ConstantData.MsgType.a.ordinal()) {
                    DebugConsoleActivity.this.a((String)message.obj);
                    return;
                } else {
                    if (message.what == ConstantData.MsgType.b.ordinal()) {
                        DebugConsoleActivity.this.c();
                        return;
                    }
                    if (message.what == ConstantData.MsgType.c.ordinal()) {
                        UiCmd.a(DebugConsoleActivity.this.getWindowManager().getDefaultDisplay(), DebugConsoleActivity.this);
                        return;
                    }
                    if (message.what == ConstantData.MsgType.e.ordinal()) {
                        int n = DebugConsoleActivity.this.b.getFontSize();
                        DebugConsoleActivity.this.b(CFunc.a(R.string.current_fontsize) + ": " + ConstantData.ConsoleFontSize.a(n).name() + " (" + n + ")");
                        return;
                    }
                    if (message.what == ConstantData.MsgType.d.ordinal()) {
                        DebugConsoleActivity.this.a((Integer)message.obj);
                        return;
                    }
                    if (message.what != ConstantData.MsgType.f.ordinal()) return;
                    {
                        DebugConsoleActivity.this.finish();
                        return;
                    }
                }
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a() {
        EditText editText = (EditText)this.findViewById(R.id.inputBox);
        String string2 = editText.getText().toString().trim();
        CmdInfo cmdInfo = CmdInfo.b(string2);
        if (cmdInfo != null) {
            this.c.a(cmdInfo);
        } else {
            boolean bl = false;
            if (!string2.startsWith("!") || !(bl = this.c.a(CFunc.a(string2.substring(1))))) {
                this.c.a(new CmdInfo(CmdInfo.a, string2, null));
            }
        }
        editText.setText((CharSequence)"");
    }

    private void a(int n) {
        this.c();
        this.b.setFontSize(n);
    }

    private void b() {
        Display display = this.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        display = (LinearLayout)this.findViewById(R.id.verticalLayout);
        this.b = new ConsoleOutputTextView((Context)this, displayMetrics.heightPixels);
        display.addView((View)this.b);
        this.a = (ConsoleScrollView)this.findViewById(R.id.consoleScrollView);
        this.a.setScrollViewListener(this.b);
        ((ImageButton)this.findViewById(R.id.inputOKButton)).setOnClickListener(this.e);
        ((EditText)this.findViewById(R.id.inputBox)).setOnKeyListener(this.f);
    }

    private void c() {
        this.b.a();
        this.a.post(this.h);
    }

    @Override
    public void a(String string2) {
        this.b.a(string2);
        this.a.post(this.g);
    }

    @Override
    public void b(String string2) {
        this.b.a(string2);
        this.b.a("\n");
        this.a.post(this.g);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.debug_console);
        CFunc.a(this.getApplication());
        this.b();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.c.a(CmdInfo.b(CmdInfo.b.name()));
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (n == 4) {
            // empty if block
        }
        return super.onKeyDown(n, keyEvent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return true;
    }

    protected void onPause() {
        super.onPause();
        NotificationCenter.a().b("NOTIFICATION_CLOSE_CONSOLE", this.d);
    }

    public boolean onPrepareOptionsMenu(Menu menu2) {
        menu2.clear();
        return true;
    }

    protected void onResume() {
        super.onResume();
        this.c = new CommandDispatcher(this.i);
        NotificationCenter.a().a("NOTIFICATION_CLOSE_CONSOLE", this.d);
    }

    protected void onStart() {
        super.onStart();
        MagicNetwork.d().registerDebugCommands((Context)this);
    }

    protected void onStop() {
        super.onStop();
        CmdInfo.f();
    }

}

