/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid.audio.core.state_machine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.logging.Log;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.exception.SmuleException;
import com.smule.singandroid.audio.core.exception.StateMachineConfigurationError;
import com.smule.singandroid.audio.core.exception.StateMachineInternalError;
import com.smule.singandroid.audio.core.exception.StateMachineTransitionException;
import com.smule.singandroid.audio.core.state_machine.CommandListener;
import com.smule.singandroid.audio.core.state_machine.ICommand;
import com.smule.singandroid.audio.core.state_machine.IState;
import com.smule.singandroid.audio.core.state_machine.StateChangeListener;
import com.smule.singandroid.audio.core.state_machine.StateMachineErrorCode;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class StateMachine {
    static final String e = StateMachine.class.getSimpleName();
    private final HashMap<String, IState> a = new HashMap();
    private final HashMap<String, IState> b = new HashMap();
    private volatile IState c = CommonState.a;
    private volatile IState d = CommonState.a;
    @Nullable
    private StateChangeListener f;
    @Nullable
    private CommandListener g;

    public StateMachine() throws SmuleException {
        this(null, null);
    }

    public StateMachine(@NonNull IState iState, @Nullable StateChangeListener stateChangeListener, @Nullable CommandListener commandListener) {
        this.b(iState);
        this.c = iState;
        this.f = stateChangeListener;
        this.g = commandListener;
    }

    public StateMachine(@Nullable StateChangeListener stateChangeListener, @Nullable CommandListener commandListener) {
        this(CommonState.a, stateChangeListener, commandListener);
    }

    private static IState a(@NonNull ICommand object, @NonNull IError object2, @NonNull IState iState, @NonNull HashMap<String, IState> hashMap) throws StateMachineTransitionException {
        object = StateMachine.a((ICommand)object, iState, (IError)object2);
        object2 = hashMap.get(object);
        if (object2 == null) {
            throw new StateMachineTransitionException(StateMachineErrorCode.g, (String)object);
        }
        return object2;
    }

    private static String a(@NonNull ICommand iCommand, @NonNull IState iState, @Nullable IError iError) {
        if (iError == null) {
            return StateMachine.a(iCommand) + "," + StateMachine.a(iState);
        }
        return StateMachine.a(iCommand) + "," + StateMachine.a(iState) + "," + StateMachine.a(iError);
    }

    private static String a(@NonNull Object object) {
        return object.getClass().getSimpleName() + "." + object.toString();
    }

    private void a(IError iError) throws StateMachineInternalError {
        if (iError == null) {
            throw new StateMachineInternalError(this, StateMachineErrorCode.e);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(@NonNull ICommand iCommand, @Nullable IError iError, @Nullable IState iState) throws StateMachineTransitionException {
        synchronized (this) {
            IState iState2;
            if (iCommand == CommonCommand.a) {
                this.b(iState2);
                this.a(iState2);
            } else {
                void var2_2;
                this.b(iCommand);
                this.a((IError)var2_2);
                iState2 = this.c;
                IState iState3 = StateMachine.a(iCommand, (IError)var2_2, this.c, this.b);
                this.a(iState3);
                if (this.g != null) {
                    this.g.a(iCommand, (IError)var2_2);
                }
                if (this.f != null) {
                    this.f.a(iState2, iState3, (IError)var2_2);
                }
            }
            return;
        }
    }

    private void a(ICommand object, IState iState) {
        if (this.a((String)(object = StateMachine.b((ICommand)object, iState)))) {
            this.a.put((String)object, null);
        }
    }

    private static void a(@NonNull ICommand iCommand, IState iState, HashMap<String, IState> hashMap) throws StateMachineTransitionException {
        if (!StateMachine.b(iCommand, iState, hashMap)) {
            throw new StateMachineTransitionException(StateMachineErrorCode.h, iCommand, iState);
        }
    }

    private void a(@NonNull IState iState) {
        synchronized (this) {
            if (iState != this.c) {
                this.d = this.c;
                this.c = iState;
                this.c(iState);
            }
            return;
        }
    }

    private void a(String string2, IState iState) throws StateMachineConfigurationError {
        if (this.b.containsKey(string2)) {
            throw new StateMachineConfigurationError(StateMachineErrorCode.f, this.getClass(), string2, iState);
        }
    }

    private boolean a(String string2) {
        if (!this.a.containsKey(string2)) {
            return true;
        }
        return false;
    }

    private static String b(@NonNull ICommand iCommand, @NonNull IState iState) {
        return StateMachine.a(iCommand, iState, null);
    }

    private void b(ICommand iCommand) throws StateMachineInternalError {
        if (iCommand == null) {
            throw new StateMachineInternalError(this, StateMachineErrorCode.d);
        }
    }

    private void b(IState iState) throws StateMachineInternalError {
        if (iState == null) {
            throw new StateMachineInternalError(this, StateMachineErrorCode.b);
        }
    }

    private void b(String string2, IState iState) {
        this.b.put(string2, iState);
    }

    private static boolean b(ICommand iCommand, IState iState, HashMap<String, IState> hashMap) {
        if (iCommand == null || iState == null || hashMap == null) {
            return false;
        }
        return hashMap.containsKey(StateMachine.b(iCommand, iState));
    }

    private void c(IState iState) {
    }

    public IState B() {
        return this.c;
    }

    public IState C() {
        return this.d;
    }

    public void a(@Nullable CommandListener commandListener) {
        if (this.g != null) {
            Log.b(e, "Removing CommandListener " + this.g.getClass().getSimpleName());
        }
        this.g = commandListener;
    }

    public void a(ICommand iCommand) throws StateMachineTransitionException {
        this.b(iCommand);
        StateMachine.a(iCommand, this.B(), this.a);
    }

    public void a(@NonNull ICommand iCommand, @NonNull IState iState, @NonNull IError object, @NonNull IState iState2) throws StateMachineConfigurationError, StateMachineInternalError {
        this.b(iCommand);
        this.b(iState);
        this.a((IError)object);
        this.b(iState2);
        object = StateMachine.a(iCommand, iState, (IError)object);
        this.a((String)object, iState2);
        this.b((String)object, iState2);
        this.a(iCommand, iState);
    }

    public void a(ICommand iCommand, IState iState, Collection<? extends IError> collection, IState iState2) throws StateMachineConfigurationError {
        this.a(Collections.singletonList(iCommand), Collections.singletonList(iState), collection, iState2);
    }

    public void a(ICommand iCommand, Collection<? extends IState> collection, Collection<? extends IError> collection2, IState iState) throws StateMachineConfigurationError {
        this.a(Collections.singletonList(iCommand), collection, collection2, iState);
    }

    public void a(IState iState, ICommand iCommand, IError iError, IState iState2) throws StateMachineConfigurationError {
        this.a(iCommand, iState, iError, iState2);
    }

    public void a(IState iState, ICommand iCommand, Collection<? extends IError> collection, IState iState2) throws StateMachineConfigurationError {
        this.a(iState, Collections.singletonList(iCommand), collection, iState2);
    }

    public void a(IState iState, Collection<? extends ICommand> object, Collection<? extends IError> collection, IState iState2) throws StateMachineConfigurationError {
        object = object.iterator();
        while (object.hasNext()) {
            ICommand iCommand = (ICommand)object.next();
            Iterator<? extends IError> iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.a(iState, iCommand, iterator.next(), iState2);
            }
        }
    }

    public void a(@Nullable StateChangeListener stateChangeListener) {
        if (this.f != null) {
            Log.b(e, "Removing StateChangeListener " + this.f.getClass().getSimpleName());
        }
        this.f = stateChangeListener;
    }

    public void a(Collection<? extends ICommand> object, Collection<? extends IState> collection, Collection<? extends IError> collection2, IState iState) throws StateMachineConfigurationError {
        object = object.iterator();
        while (object.hasNext()) {
            ICommand iCommand = (ICommand)object.next();
            for (IState iState2 : collection) {
                Iterator<? extends IError> iterator = collection2.iterator();
                while (iterator.hasNext()) {
                    this.a(iCommand, iState2, iterator.next(), iState);
                }
            }
        }
    }

    public void b(ICommand iCommand, IError iError) throws StateMachineTransitionException {
        this.a(iCommand, iError, null);
    }

    public static enum CommonCommand implements ICommand
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

