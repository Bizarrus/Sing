package com.smule.android.core.state_machine;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.logger.TagLogger;
import com.smule.android.core.parameter.KeyedParameter;
import java.util.HashMap;

public class StateMachine {
    private TagLogger f15826a;
    private HashMap<String, IState> f15827b;
    private HashMap<String, IState> f15828c;
    private IState f15829d;
    private IState f15830e;
    private String f15831f;

    private enum CommonCommand implements ICommand {
        INJECT_STATE
    }

    public enum CommonState implements IState {
        UNKNOWN
    }

    public StateMachine(Class cls) throws SmuleException {
        this(cls, CommonState.UNKNOWN);
    }

    public StateMachine(Class cls, IState iState) throws SmuleException {
        this.f15826a = new TagLogger(getClass().getSimpleName());
        this.f15827b = new HashMap();
        this.f15828c = new HashMap();
        this.f15829d = CommonState.UNKNOWN;
        this.f15830e = CommonState.UNKNOWN;
        m17606a(cls);
        m17610b(iState);
        this.f15829d = iState;
        this.f15831f = cls.getSimpleName();
    }

    public void m17618a(ICommand iCommand, IState[] iStateArr, IError iError, IState iState) throws SmuleException {
        m17619a(new ICommand[]{iCommand}, iStateArr, new IError[]{iError}, iState);
    }

    public void m17619a(ICommand[] iCommandArr, IState[] iStateArr, IError[] iErrorArr, IState iState) throws SmuleException {
        for (ICommand iCommand : iCommandArr) {
            for (IState iState2 : iStateArr) {
                for (IError a : iErrorArr) {
                    m17617a(iCommand, iState2, a, iState);
                }
            }
        }
    }

    public void m17617a(ICommand iCommand, IState iState, IError iError, IState iState2) throws SmuleException {
        m17612c(iCommand);
        m17610b(iState);
        m17602a(iError);
        m17610b(iState2);
        String a = m17600a(iCommand, iState, iError);
        m17607a(a, iState2);
        m17611b(a, iState2);
        m17604a(iCommand, iState);
    }

    public void m17616a(ICommand iCommand, IError iError) throws SmuleException {
        m17603a(iCommand, iError, null);
    }

    public synchronized void m17615a(ICommand iCommand) throws SmuleException {
        m17612c(iCommand);
        if (!m17621b(iCommand)) {
            ErrorHelper.m17583a(StateMachineError.COMMAND_NOT_ALLOWED_IN_CURRENT_STATE, new KeyedParameter(StateMachineParameterType.NAME.toString(), this.f15831f), new KeyedParameter(StateMachineParameterType.COMMAND.toString(), iCommand), new KeyedParameter(StateMachineParameterType.STATE.toString(), this.f15829d));
        }
    }

    public IState m17620b(ICommand iCommand, IError iError) throws SmuleException {
        m17612c(iCommand);
        m17602a(iError);
        String a = m17600a(iCommand, this.f15829d, iError);
        IState iState = (IState) this.f15828c.get(a);
        this.f15826a.m17592b("Get next state (name/key/next state): " + this.f15831f + "/" + a + "/" + iState);
        if (iState == null) {
            ErrorHelper.m17583a(StateMachineError.NO_STATE_TRANSITION_FOR_KEY, new KeyedParameter(StateMachineParameterType.NAME.toString(), this.f15831f), new KeyedParameter(StateMachineParameterType.KEY.toString(), a));
        }
        return iState;
    }

    public IState m17614a() {
        return this.f15829d;
    }

    private void m17607a(String str, IState iState) throws SmuleException {
        if (this.f15828c.containsKey(str)) {
            ErrorHelper.m17583a(StateMachineError.DUPLICATE_STATE_TRANSITION_DEFINITION, new KeyedParameter(StateMachineParameterType.NAME.toString(), this.f15831f), new KeyedParameter(StateMachineParameterType.KEY.toString(), str), new KeyedParameter(StateMachineParameterType.OUTPUT.toString(), iState));
        }
    }

    private void m17611b(String str, IState iState) {
        this.f15828c.put(str, iState);
    }

    private boolean m17608a(String str) {
        return !this.f15827b.containsKey(str);
    }

    private void m17604a(ICommand iCommand, IState iState) {
        String b = m17609b(iCommand, iState);
        if (m17608a(b)) {
            this.f15827b.put(b, null);
        }
    }

    private synchronized void m17603a(ICommand iCommand, IError iError, IState iState) throws SmuleException {
        m17612c(iCommand);
        if (iCommand == CommonCommand.INJECT_STATE) {
            m17610b(iState);
            m17605a(iState);
        } else {
            m17602a(iError);
            m17605a(m17620b(iCommand, iError));
        }
    }

    private void m17605a(IState iState) throws SmuleException {
        if (iState != this.f15829d) {
            this.f15830e = this.f15829d;
            this.f15829d = iState;
            m17613c(iState);
            this.f15826a.m17592b("State Changed: " + this.f15830e.toString() + " -> " + iState.toString());
        }
    }

    private void m17606a(Class cls) throws SmuleException {
        if (cls == null) {
            ErrorHelper.m17583a(StateMachineError.INVALID_CLASS, new KeyedParameter[0]);
        }
    }

    private void m17610b(IState iState) throws SmuleException {
        if (iState == null) {
            ErrorHelper.m17583a(StateMachineError.INVALID_STATE, new KeyedParameter[0]);
        }
    }

    private void m17612c(ICommand iCommand) throws SmuleException {
        if (iCommand == null) {
            ErrorHelper.m17583a(StateMachineError.INVALID_COMMAND, new KeyedParameter[0]);
        }
    }

    private void m17602a(IError iError) throws SmuleException {
        if (iError == null) {
            ErrorHelper.m17583a(StateMachineError.INVALID_ERROR, new KeyedParameter[0]);
        }
    }

    public synchronized boolean m17621b(ICommand iCommand) {
        boolean z;
        z = iCommand != null && this.f15827b.containsKey(m17609b(iCommand, this.f15829d));
        return z;
    }

    private String m17609b(ICommand iCommand, IState iState) {
        return m17600a(iCommand, iState, null);
    }

    private String m17600a(ICommand iCommand, IState iState, IError iError) {
        if (iError == null) {
            return m17601a((Object) iCommand) + "," + m17601a((Object) iState);
        }
        return m17601a((Object) iCommand) + "," + m17601a((Object) iState) + "," + m17601a((Object) iError);
    }

    private String m17601a(Object obj) {
        return obj.getClass().getSimpleName() + "." + obj.toString();
    }

    private void m17613c(IState iState) throws SmuleException {
    }
}
