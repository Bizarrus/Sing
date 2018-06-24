/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.core.state_machine;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.logger.TagLogger;
import com.smule.android.core.parameter.KeyedParameter;
import com.smule.android.core.state_machine.ICommand;
import com.smule.android.core.state_machine.IState;
import com.smule.android.core.state_machine.StateMachineError;
import com.smule.android.core.state_machine.StateMachineParameterType;
import java.util.HashMap;

public class StateMachine {
    private TagLogger a;
    private HashMap<String, IState> b;
    private HashMap<String, IState> c;
    private IState d;
    private IState e;
    private String f;

    public StateMachine(Class class_) throws SmuleException {
        this(class_, CommonState.a);
    }

    public StateMachine(Class class_, IState iState) throws SmuleException {
        this.a = new TagLogger(this.getClass().getSimpleName());
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = CommonState.a;
        this.e = CommonState.a;
        this.a(class_);
        this.b(iState);
        this.d = iState;
        this.f = class_.getSimpleName();
    }

    private String a(ICommand iCommand, IState iState, IError iError) {
        if (iError == null) {
            return this.a((Object)iCommand) + "," + this.a((Object)iState);
        }
        return this.a((Object)iCommand) + "," + this.a((Object)iState) + "," + this.a((Object)iError);
    }

    private String a(Object object) {
        return object.getClass().getSimpleName() + "." + object.toString();
    }

    private void a(IError iError) throws SmuleException {
        if (iError == null) {
            ErrorHelper.a(StateMachineError.e, new KeyedParameter[0]);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(ICommand iCommand, IError iError, IState iState) throws SmuleException {
        synchronized (this) {
            this.c(iCommand);
            if (iCommand == CommonCommand.a) {
                void var3_3;
                this.b((IState)var3_3);
                this.a((IState)var3_3);
            } else {
                void var2_2;
                this.a((IError)var2_2);
                this.a(this.b(iCommand, (IError)var2_2));
            }
            return;
        }
    }

    private void a(ICommand object, IState iState) {
        if (this.a((String)(object = this.b((ICommand)object, iState)))) {
            this.b.put((String)object, null);
        }
    }

    private void a(IState iState) throws SmuleException {
        if (iState != this.d) {
            this.e = this.d;
            this.d = iState;
            this.c(iState);
            this.a.b("State Changed: " + this.e.toString() + " -> " + iState.toString());
        }
    }

    private void a(Class class_) throws SmuleException {
        if (class_ == null) {
            ErrorHelper.a(StateMachineError.a, new KeyedParameter[0]);
        }
    }

    private void a(String string2, IState iState) throws SmuleException {
        if (this.c.containsKey(string2)) {
            ErrorHelper.a(StateMachineError.f, new KeyedParameter(StateMachineParameterType.g.toString(), this.f), new KeyedParameter(StateMachineParameterType.a.toString(), string2), new KeyedParameter(StateMachineParameterType.b.toString(), iState));
        }
    }

    private boolean a(String string2) {
        if (!this.b.containsKey(string2)) {
            return true;
        }
        return false;
    }

    private String b(ICommand iCommand, IState iState) {
        return this.a(iCommand, iState, null);
    }

    private void b(IState iState) throws SmuleException {
        if (iState == null) {
            ErrorHelper.a(StateMachineError.b, new KeyedParameter[0]);
        }
    }

    private void b(String string2, IState iState) {
        this.c.put(string2, iState);
    }

    private void c(ICommand iCommand) throws SmuleException {
        if (iCommand == null) {
            ErrorHelper.a(StateMachineError.d, new KeyedParameter[0]);
        }
    }

    private void c(IState iState) throws SmuleException {
    }

    public IState a() {
        return this.d;
    }

    public void a(ICommand iCommand) throws SmuleException {
        synchronized (this) {
            this.c(iCommand);
            if (!this.b(iCommand)) {
                ErrorHelper.a(StateMachineError.h, new KeyedParameter(StateMachineParameterType.g.toString(), this.f), new KeyedParameter(StateMachineParameterType.c.toString(), iCommand), new KeyedParameter(StateMachineParameterType.d.toString(), this.d));
            }
            return;
        }
    }

    public void a(ICommand iCommand, IError iError) throws SmuleException {
        this.a(iCommand, iError, null);
    }

    public void a(ICommand iCommand, IState iState, IError object, IState iState2) throws SmuleException {
        this.c(iCommand);
        this.b(iState);
        this.a((IError)object);
        this.b(iState2);
        object = this.a(iCommand, iState, (IError)object);
        this.a((String)object, iState2);
        this.b((String)object, iState2);
        this.a(iCommand, iState);
    }

    public void a(ICommand iCommand, IState[] arriState, IError iError, IState iState) throws SmuleException {
        this.a(new ICommand[]{iCommand}, arriState, new IError[]{iError}, iState);
    }

    public void a(ICommand[] arriCommand, IState[] arriState, IError[] arriError, IState iState) throws SmuleException {
        for (ICommand iCommand : arriCommand) {
            for (IState iState2 : arriState) {
                int n = arriError.length;
                for (int i = 0; i < n; ++i) {
                    this.a(iCommand, iState2, arriError[i], iState);
                }
            }
        }
    }

    public IState b(ICommand object, IError object2) throws SmuleException {
        this.c((ICommand)object);
        this.a((IError)object2);
        object = this.a((ICommand)object, this.d, (IError)object2);
        object2 = this.c.get(object);
        this.a.b("Get next state (name/key/next state): " + this.f + "/" + (String)object + "/" + object2);
        if (object2 == null) {
            ErrorHelper.a(StateMachineError.g, new KeyedParameter(StateMachineParameterType.g.toString(), this.f), new KeyedParameter(StateMachineParameterType.a.toString(), object));
        }
        return object2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean b(ICommand iCommand) {
        synchronized (this) {
            String string2 = this.b(iCommand, this.d);
            if (iCommand == null) return false;
            boolean bl = this.b.containsKey(string2);
            if (!bl) return false;
            return true;
        }
    }

    private static enum CommonCommand implements ICommand
    {
        a;
        

        private CommonCommand() {
        }
    }

    public static enum CommonState implements IState
    {
        a;
        

        private CommonState() {
        }
    }

}

