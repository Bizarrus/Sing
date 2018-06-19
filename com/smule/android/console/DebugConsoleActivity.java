package com.smule.android.console;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.smule.android.C3482R;
import com.smule.android.console.ConstantData.ConsoleFontSize;
import com.smule.android.console.ConstantData.MsgType;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.utils.NotificationCenter;
import java.util.Observable;
import java.util.Observer;

public class DebugConsoleActivity extends Activity implements StdOut {
    private ConsoleScrollView f15758a;
    private ConsoleOutputTextView f15759b;
    private CommandDispatcher f15760c;
    private Observer f15761d = new C35101(this);
    private OnClickListener f15762e = new C35112(this);
    private OnKeyListener f15763f = new C35123(this);
    private Runnable f15764g = new C35134(this);
    private Runnable f15765h = new C35145(this);
    private final Handler f15766i = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ DebugConsoleActivity f15757a;

        public void handleMessage(Message message) {
            if (message.what == MsgType.STRING.ordinal()) {
                this.f15757a.mo6236a((String) message.obj);
            } else if (message.what == MsgType.CLEAR.ordinal()) {
                this.f15757a.m17558c();
            } else if (message.what == MsgType.SHOWSRES.ordinal()) {
                UiCmd.m17569a(this.f15757a.getWindowManager().getDefaultDisplay(), this.f15757a);
            } else if (message.what == MsgType.SHOWFONTSIZE.ordinal()) {
                int fontSize = this.f15757a.f15759b.getFontSize();
                this.f15757a.mo6237b(CFunc.m17512a(C3482R.string.current_fontsize) + ": " + ConsoleFontSize.m17549a(fontSize).name() + " (" + fontSize + ")");
            } else if (message.what == MsgType.SETFONTSIZE.ordinal()) {
                this.f15757a.m17552a(((Integer) message.obj).intValue());
            } else if (message.what == MsgType.EXIT.ordinal()) {
                this.f15757a.finish();
            }
        }
    };

    class C35101 implements Observer {
        final /* synthetic */ DebugConsoleActivity f15752a;

        C35101(DebugConsoleActivity debugConsoleActivity) {
            this.f15752a = debugConsoleActivity;
        }

        public void update(Observable observable, Object obj) {
            if (obj != null) {
                final Runnable runnable = (Runnable) obj;
                this.f15752a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ C35101 f15751b;

                    class C35081 implements Runnable {
                        final /* synthetic */ C35091 f15749a;

                        C35081(C35091 c35091) {
                            this.f15749a = c35091;
                        }

                        public void run() {
                            runnable.run();
                        }
                    }

                    public void run() {
                        new Handler().postDelayed(new C35081(this), 1000);
                    }
                });
            }
            this.f15752a.finish();
        }
    }

    class C35112 implements OnClickListener {
        final /* synthetic */ DebugConsoleActivity f15753a;

        C35112(DebugConsoleActivity debugConsoleActivity) {
            this.f15753a = debugConsoleActivity;
        }

        public void onClick(View view) {
            this.f15753a.m17551a();
        }
    }

    class C35123 implements OnKeyListener {
        final /* synthetic */ DebugConsoleActivity f15754a;

        C35123(DebugConsoleActivity debugConsoleActivity) {
            this.f15754a = debugConsoleActivity;
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66 || keyEvent.getAction() != 1) {
                return false;
            }
            this.f15754a.m17551a();
            return true;
        }
    }

    class C35134 implements Runnable {
        final /* synthetic */ DebugConsoleActivity f15755a;

        C35134(DebugConsoleActivity debugConsoleActivity) {
            this.f15755a = debugConsoleActivity;
        }

        public void run() {
            if (this.f15755a.f15759b.getViewableHeight() > this.f15755a.f15758a.getHeight()) {
                this.f15755a.f15758a.scrollTo(0, this.f15755a.f15759b.getHeight());
            }
        }
    }

    class C35145 implements Runnable {
        final /* synthetic */ DebugConsoleActivity f15756a;

        C35145(DebugConsoleActivity debugConsoleActivity) {
            this.f15756a = debugConsoleActivity;
        }

        public void run() {
            this.f15756a.f15758a.scrollTo(0, 0);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C3482R.layout.debug_console);
        CFunc.m17514a(getApplication());
        m17556b();
    }

    protected void onStart() {
        super.onStart();
        MagicNetwork.d().registerDebugCommands(this);
    }

    protected void onResume() {
        super.onResume();
        this.f15760c = new CommandDispatcher(this.f15766i);
        NotificationCenter.m19011a().m19014a("NOTIFICATION_CLOSE_CONSOLE", this.f15761d);
    }

    protected void onPause() {
        super.onPause();
        NotificationCenter.m19011a().m19016b("NOTIFICATION_CLOSE_CONSOLE", this.f15761d);
    }

    protected void onStop() {
        super.onStop();
        CmdInfo.f();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f15760c.m17540a(CmdInfo.b(CmdInfo$BuiltInCmd._ui_exit_.name()));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void m17551a() {
        EditText editText = (EditText) findViewById(C3482R.id.inputBox);
        String trim = editText.getText().toString().trim();
        CmdInfo b = CmdInfo.b(trim);
        if (b != null) {
            this.f15760c.m17540a(b);
        } else {
            boolean z = false;
            if (trim.startsWith("!")) {
                z = this.f15760c.m17542a(CFunc.m17510a(trim.substring(1)));
            }
            if (!z) {
                this.f15760c.m17540a(new CmdInfo(CmdInfo$BuiltInCmd._unknown_, trim, null));
            }
        }
        editText.setText("");
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return true;
    }

    private void m17556b() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        LinearLayout linearLayout = (LinearLayout) findViewById(C3482R.id.verticalLayout);
        this.f15759b = new ConsoleOutputTextView(this, displayMetrics.heightPixels);
        linearLayout.addView(this.f15759b);
        this.f15758a = (ConsoleScrollView) findViewById(C3482R.id.consoleScrollView);
        this.f15758a.setScrollViewListener(this.f15759b);
        ((ImageButton) findViewById(C3482R.id.inputOKButton)).setOnClickListener(this.f15762e);
        ((EditText) findViewById(C3482R.id.inputBox)).setOnKeyListener(this.f15763f);
    }

    public void mo6236a(String str) {
        this.f15759b.m17548a(str);
        this.f15758a.post(this.f15764g);
    }

    public void mo6237b(String str) {
        this.f15759b.m17548a(str);
        this.f15759b.m17548a("\n");
        this.f15758a.post(this.f15764g);
    }

    private void m17558c() {
        this.f15759b.m17546a();
        this.f15758a.post(this.f15765h);
    }

    private void m17552a(int i) {
        m17558c();
        this.f15759b.setFontSize(i);
    }
}
