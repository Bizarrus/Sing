/*
 * Decompiled with CFR 0_123.
 */
package com.smule.samples.player;

import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SMError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.state_machine.ICommand;
import com.smule.android.core.state_machine.IState;
import com.smule.android.core.state_machine.StateMachine;
import com.smule.samples.player.SimPlayerError;

public class SimPlayerStateMachine
extends StateMachine {
    public SimPlayerStateMachine() throws SmuleException {
        super(SimPlayerStateMachine.class, State.a);
        this.a((ICommand)Command.a, State.a, (IError)SMError.a, (IState)State.b);
        this.a((ICommand)Command.b, State.b, (IError)SMError.a, (IState)State.c);
        this.a((ICommand)Command.b, State.b, (IError)SimPlayerError.a, (IState)State.a);
        this.a((ICommand)Command.c, State.c, (IError)SMError.a, (IState)State.d);
        this.a((ICommand)Command.d, State.d, (IError)SMError.a, (IState)State.e);
        this.a((ICommand)Command.d, State.d, (IError)SimPlayerError.b, (IState)State.c);
        this.a((ICommand)Command.e, State.e, (IError)SMError.a, (IState)State.f);
        this.a((ICommand)Command.f, State.f, (IError)SMError.a, (IState)State.c);
        this.a((ICommand)Command.f, State.f, (IError)SimPlayerError.c, (IState)State.e);
        this.a((ICommand)Command.g, State.c, (IError)SMError.a, (IState)State.g);
        this.a((ICommand)Command.g, State.g, (IError)SMError.a, (IState)State.h);
        this.a((ICommand)Command.g, State.g, (IError)SimPlayerError.d, (IState)State.c);
    }

    public static enum Command implements ICommand
    {
        a,
        b,
        c,
        d,
        e,
        f,
        g;
        

        private Command() {
        }
    }

    public static enum State implements IState
    {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h;
        

        private State() {
        }
    }

}

