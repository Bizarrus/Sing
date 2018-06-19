package com.smule.samples.player;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SMError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.logger.TagLogger;
import com.smule.android.core.parameter.KeyedParameter;
import com.smule.android.core.state_machine.ICommand;
import com.smule.android.core.wait.WaitUtil;
import com.smule.samples.player.SimPlayerStateMachine.Command;

public class SimPlayer {
    private SimPlayerStateMachine f18379a = new SimPlayerStateMachine();
    private IError f18380b = null;
    private TagLogger f18381c = new TagLogger(getClass().getSimpleName());

    private class PretendWorker extends Thread {
        Command f18376a;
        final /* synthetic */ SimPlayer f18377b;
        private TagLogger f18378c = new TagLogger(getClass().getSimpleName());

        public PretendWorker(SimPlayer simPlayer, Command command) {
            this.f18377b = simPlayer;
            this.f18376a = command;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r2 = this;
            r0 = com.smule.samples.player.SimPlayer.C37661.f18375a;	 Catch:{ SmuleException -> 0x0020 }
            r1 = r2.f18376a;	 Catch:{ SmuleException -> 0x0020 }
            r1 = r1.ordinal();	 Catch:{ SmuleException -> 0x0020 }
            r0 = r0[r1];	 Catch:{ SmuleException -> 0x0020 }
            switch(r0) {
                case 1: goto L_0x0015;
                case 2: goto L_0x000d;
                case 3: goto L_0x0022;
                case 4: goto L_0x000d;
                case 5: goto L_0x002d;
                default: goto L_0x000d;
            };
        L_0x000d:
            r0 = r2.f18378c;
            r1 = "PretendWorker finished";
            r0.m17592b(r1);
            return;
        L_0x0015:
            r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            com.smule.android.core.wait.WaitUtil.m17623a(r0);	 Catch:{ SmuleException -> 0x0020 }
            r0 = r2.f18377b;	 Catch:{ SmuleException -> 0x0020 }
            r0.m19768b();	 Catch:{ SmuleException -> 0x0020 }
            goto L_0x000d;
        L_0x0020:
            r0 = move-exception;
            goto L_0x000d;
        L_0x0022:
            r0 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
            com.smule.android.core.wait.WaitUtil.m17623a(r0);	 Catch:{ SmuleException -> 0x0020 }
            r0 = r2.f18377b;	 Catch:{ SmuleException -> 0x0020 }
            r0.m19773d();	 Catch:{ SmuleException -> 0x0020 }
            goto L_0x000d;
        L_0x002d:
            r0 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
            com.smule.android.core.wait.WaitUtil.m17623a(r0);	 Catch:{ SmuleException -> 0x0020 }
            r0 = r2.f18377b;	 Catch:{ SmuleException -> 0x0020 }
            r0.m19775f();	 Catch:{ SmuleException -> 0x0020 }
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.smule.samples.player.SimPlayer.PretendWorker.run():void");
        }
    }

    private synchronized KeyedParameter[] m19767a(Command command, KeyedParameter... keyedParameterArr) throws SmuleException {
        this.f18379a.m17615a((ICommand) command);
        try {
            switch (command) {
                case INITIALIZE:
                    m19765a();
                    break;
                case ON_INITIALIZE_COMPLETE:
                    if (this.f18380b != null) {
                        ErrorHelper.m17583a(this.f18380b, new KeyedParameter[0]);
                        break;
                    }
                    break;
                case PLAY:
                    m19771c();
                    break;
                case ON_PLAY_COMPLETE:
                    if (this.f18380b != null) {
                        ErrorHelper.m17583a(this.f18380b, new KeyedParameter[0]);
                        break;
                    }
                    break;
                case STOP:
                    m19774e();
                    break;
                case ON_STOP_COMPLETE:
                    if (this.f18380b != null) {
                        ErrorHelper.m17583a(this.f18380b, new KeyedParameter[0]);
                        break;
                    }
                    break;
                case CLOSE:
                    m19776g();
                    break;
            }
            this.f18379a.m17616a((ICommand) command, SMError.NO_ERROR);
            m19770b(command, keyedParameterArr);
        } catch (SmuleException e) {
            this.f18379a.m17616a((ICommand) command, e.m17588a());
            throw e;
        }
        return null;
    }

    private void m19770b(Command command, KeyedParameter... keyedParameterArr) throws SmuleException {
        switch (command) {
        }
    }

    private void m19765a() throws SmuleException {
        this.f18381c.m17591a("initializing...");
        new PretendWorker(this, Command.INITIALIZE).start();
    }

    private void m19768b() throws SmuleException {
        this.f18381c.m17591a("initialization complete...");
        m19767a(Command.ON_INITIALIZE_COMPLETE, new KeyedParameter[0]);
    }

    private void m19771c() throws SmuleException {
        this.f18381c.m17591a("starting play...");
        new PretendWorker(this, Command.PLAY).start();
    }

    private void m19773d() throws SmuleException {
        this.f18381c.m17591a("start play complete...");
        m19767a(Command.ON_PLAY_COMPLETE, new KeyedParameter[0]);
    }

    private void m19774e() throws SmuleException {
        this.f18381c.m17591a("stopping play...");
        new PretendWorker(this, Command.STOP).start();
    }

    private void m19775f() throws SmuleException {
        this.f18381c.m17591a("stop play complete...");
        m19767a(Command.ON_STOP_COMPLETE, new KeyedParameter[0]);
    }

    private void m19776g() throws SmuleException {
        this.f18381c.m17591a("closing player...");
        this.f18379a.m17616a(Command.CLOSE, SMError.NO_ERROR);
        WaitUtil.m17623a(500);
        if (this.f18380b != null) {
            ErrorHelper.m17583a(this.f18380b, new KeyedParameter[0]);
        }
    }
}
