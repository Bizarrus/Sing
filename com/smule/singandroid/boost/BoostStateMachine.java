/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.boost;

import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SMError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.state_machine.ICommand;
import com.smule.android.core.state_machine.IState;
import com.smule.android.core.state_machine.StateMachine;
import com.smule.singandroid.boost.BoostError;

public class BoostStateMachine
extends StateMachine {
    public BoostStateMachine() throws SmuleException {
        super(BoostStateMachine.class, StateMachine.CommonState.a);
        Command command = Command.b;
        Enum enum_ = StateMachine.CommonState.a;
        State state = State.i;
        Enum enum_2 = BoostError.a;
        State state2 = State.a;
        this.a((ICommand)command, new IState[]{enum_, state}, (IError)((Object)enum_2), (IState)state2);
        command = Command.b;
        enum_ = StateMachine.CommonState.a;
        state = State.i;
        enum_2 = BoostError.b;
        state2 = State.b;
        this.a((ICommand)command, new IState[]{enum_, state}, (IError)((Object)enum_2), (IState)state2);
        command = Command.b;
        enum_ = StateMachine.CommonState.a;
        state = State.i;
        enum_2 = BoostError.c;
        state2 = State.c;
        this.a((ICommand)command, new IState[]{enum_, state}, (IError)((Object)enum_2), (IState)state2);
        command = Command.b;
        enum_ = StateMachine.CommonState.a;
        state = State.i;
        enum_2 = SMError.a;
        state2 = State.d;
        this.a((ICommand)command, new IState[]{enum_, state}, (IError)((Object)enum_2), (IState)state2);
        this.a((ICommand)Command.d, State.d, (IError)SMError.a, (IState)State.g);
        this.a((ICommand)Command.d, State.d, (IError)BoostError.e, (IState)State.h);
        command = Command.e;
        enum_ = State.a;
        state = State.b;
        enum_2 = State.d;
        state2 = State.c;
        State state3 = State.g;
        State state4 = State.h;
        SMError sMError = SMError.a;
        State state5 = State.i;
        this.a((ICommand)command, new IState[]{enum_, state, enum_2, state2, state3, state4}, (IError)sMError, (IState)state5);
    }

    public static String a(Enum enum_) {
        return enum_.getClass().getCanonicalName() + "." + enum_.name();
    }

    public static enum Command implements ICommand
    {
        a,
        b,
        c,
        d,
        e;
        

        private Command() {
        }

        public String a() {
            return BoostStateMachine.a(this);
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
        h,
        i;
        

        private State() {
        }

        public String a() {
            return BoostStateMachine.a(this);
        }
    }

}

