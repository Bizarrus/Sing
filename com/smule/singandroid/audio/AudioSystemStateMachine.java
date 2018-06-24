/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid.audio;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.logging.Log;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.exception.SmuleException;
import com.smule.singandroid.audio.core.exception.StateMachineConfigurationError;
import com.smule.singandroid.audio.core.exception.StateMachineTransitionException;
import com.smule.singandroid.audio.core.logger.TagLogger;
import com.smule.singandroid.audio.core.state_machine.CommandListener;
import com.smule.singandroid.audio.core.state_machine.CommandLogger;
import com.smule.singandroid.audio.core.state_machine.ICommand;
import com.smule.singandroid.audio.core.state_machine.IState;
import com.smule.singandroid.audio.core.state_machine.StateChangeListener;
import com.smule.singandroid.audio.core.state_machine.StateChangeLogger;
import com.smule.singandroid.audio.core.state_machine.StateMachine;
import com.smule.singandroid.audio.exception.NativeException;
import com.smule.singandroid.audio.exception.UninitializedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class AudioSystemStateMachine
extends StateMachine {
    private static final String a = AudioSystemStateMachine.class.getSimpleName();
    private static final Collection<State> b = Arrays.asList(State.f, State.h, State.i, State.j, State.l);
    private static final Collection<State> c = Arrays.asList(State.d, State.f, State.g, State.h, State.i, State.j, State.k, State.l, State.m, State.p, State.n);
    private static final Collection<State> f = Arrays.asList(State.f, State.h, State.i, State.j, State.k, State.l, State.m, State.n, State.o);
    private static final Collection<State> g = Arrays.asList(State.values());
    private static final Collection<Command> h = Arrays.asList(Command.values());
    ExecutorService d = Executors.newSingleThreadExecutor();

    AudioSystemStateMachine() throws StateMachineConfigurationError {
        super(State.a, new TransitionLogger(), new NonRealTimeCommandLogger());
        List list = Arrays.asList(Result.values());
        Collection<Result> collection = AudioSystemStateMachine.a(new ArrayList<Result>(list), Result.a);
        Arrays.asList(Result.b, Result.c, Result.d);
        this.a((IState)State.a, (ICommand)Command.k, (IError)Result.a, (IState)State.c);
        this.a((IState)State.a, (ICommand)Command.a, (IError)Result.a, (IState)State.c);
        this.a((IState)State.a, (ICommand)Command.a, collection, (IState)StateMachine.CommonState.a);
        this.a((IState)State.c, (ICommand)Command.b, (IError)Result.a, (IState)State.d);
        this.a((IState)State.c, (ICommand)Command.b, collection, (IState)State.e);
        this.a((IState)State.d, (ICommand)Command.c, (IError)Result.a, (IState)State.f);
        this.a((IState)State.d, (ICommand)Command.c, collection, (IState)State.g);
        this.a((IState)State.k, (ICommand)Command.k, list, (IState)State.g);
        this.a((IState)State.g, (ICommand)Command.j, list, (IState)State.e);
        this.a(State.f, Command.e, State.h);
        this.a(State.f, Command.f, State.i);
        this.a(State.h, Command.f, State.j);
        this.a(State.i, Command.e, State.j);
        this.a(State.f, Command.j, State.o);
        this.a(State.h, Command.j, State.o);
        this.a(State.i, Command.j, State.o);
        this.a(State.d, Command.j, State.e);
        this.a(State.j, Command.g, State.l);
        this.a(State.l, Command.h, State.j);
        this.a(Command.h, AudioSystemStateMachine.a(new ArrayList<State>(g), State.l), Result.a);
        this.a(State.j, Command.j, State.o);
        this.a(State.o, Command.b, State.f);
        this.a(State.f, Command.m, State.m);
        this.a(State.j, Command.m, State.m);
        this.a(State.m, Command.n, State.f);
        this.a(State.j, Command.l, State.p);
        this.a((IState)State.j, (ICommand)Command.k, list, (IState)State.p);
        this.a(State.o, Command.l, State.q);
        this.a((IState)State.o, (ICommand)Command.k, list, (IState)State.q);
        this.a(State.p, Command.j, State.q);
        list = Arrays.asList(State.f, State.i, State.o);
        List<State> list2 = Arrays.asList(State.f, State.h, State.i, State.j, State.l, State.n, State.o);
        List<State> list3 = Arrays.asList(State.j, State.o, State.n);
        this.a(Command.o, c, Result.a);
        this.a(Command.p, f, Result.a);
        this.a(Command.q, list3, Result.a);
        this.a(Command.s, f, Result.a);
        this.a(Command.t, list2, Result.a);
        this.a(Command.r, list, Result.a);
        this.a(State.h, Command.r, State.f);
        this.a(State.j, Command.r, State.i);
        this.a(Arrays.asList(Command.e, Command.f, Command.g, Command.h, Command.i, Command.j, Command.l), b, collection, (IState)State.k);
        this.a((ICommand)Command.b, (IState)State.o, collection, (IState)State.k);
        this.a((ICommand)Command.m, b, collection, (IState)State.k);
        this.a((ICommand)Command.o, c, collection, (IState)State.k);
        this.a((ICommand)Command.p, f, collection, (IState)State.k);
        this.a((ICommand)Command.q, list3, collection, (IState)State.k);
        this.a((ICommand)Command.r, list, collection, (IState)State.k);
        this.a((ICommand)Command.r, (IState)State.h, collection, (IState)State.k);
        this.a((ICommand)Command.r, (IState)State.j, collection, (IState)State.k);
        this.a((ICommand)Command.s, f, collection, (IState)State.k);
        this.a((ICommand)Command.t, list2, collection, (IState)State.k);
    }

    private static <E> Collection<E> a(Collection<E> collection, E e) {
        collection.remove(e);
        return collection;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void a(@NonNull Command var1_1, @Nullable Throwable var2_3) throws StateMachineTransitionException {
        if (var2_3 != null) ** GOTO lbl5
        try {
            this.b(var1_1, Result.a);
            return;
lbl5: // 1 sources:
            if (var2_3 instanceof UninitializedException) {
                this.b(var1_1, Result.c);
                return;
            }
            if (var2_3 instanceof NativeException) {
                this.b(var1_1, Result.d);
                return;
            }
        }
        catch (StateMachineTransitionException var1_2) {
            var1_2.initCause(var2_3);
            throw var1_2;
        }
        if (var2_3 instanceof SmuleException) {
            this.b(var1_1, ((SmuleException)var2_3).a());
            return;
        }
        Log.c(AudioSystemStateMachine.a, "Transitioning to error state with an unknown exception", var2_3);
        this.b(var1_1, Result.e);
    }

    private void a(Command command, Collection<State> collection, Result result) {
        this.a(command, collection, Collections.singleton(result));
    }

    private void a(Command command, Collection<State> object, Collection<Result> collection) {
        object = object.iterator();
        while (object.hasNext()) {
            State state = (State)object.next();
            this.a((ICommand)command, (IState)state, collection, (IState)state);
        }
    }

    private void a(State state, Command command, State state2) throws StateMachineConfigurationError {
        this.a((IState)state, (ICommand)command, (IError)Result.a, (IState)state2);
    }

    protected <Return, WorkerException extends Exception> Return a(@NonNull Command command, @NonNull CommandWorker<Return, Void, WorkerException> commandWorker) throws Exception, StateMachineTransitionException {
        return commandWorker.a(command, null);
    }

    <Return, CommandException extends Exception> Return a(Command command, GetWorker<Return, CommandException> getWorker) throws StateMachineTransitionException, Exception {
        return getWorker.a(command);
    }

    <Return, Parameters, WorkerException extends Exception> Future<Return> a(Command command, CallableWorker<Return, Parameters, WorkerException> callableWorker, final ExecutorService executorService) throws StateMachineTransitionException {
        return (Future)new CommandWorker<Future<Return>, CallableWorker<Return, Parameters, WorkerException>, RuntimeException>(){

            @NonNull
            @Override
            Future<Return> a(@Nullable CallableWorker<Return, Parameters, WorkerException> callableWorker) throws RejectedExecutionException, NullPointerException {
                return executorService.submit(callableWorker);
            }
        }.a(command, callableWorker);
    }

    <Return, Parameters, WorkerException extends Exception> Future<Return> a(Command command, CommandWorker<Return, Parameters, WorkerException> commandWorker, Parameters Parameters, Command command2, ExecutorService executorService, CommandDelegate commandDelegate) throws StateMachineTransitionException {
        return this.a(command, new CallableWorker<Return, Parameters, WorkerException>(commandWorker, command2, Parameters, commandDelegate), executorService);
    }

    <CommandException extends Exception> void a(Command command, BasicWorker<CommandException> basicWorker) throws StateMachineTransitionException, Exception {
        basicWorker.a(command);
    }

    abstract class BasicWorker<ExecuteException extends Exception>
    extends CommandWorker<Void, Void, ExecuteException> {
        BasicWorker() {
            super();
        }

        @Override
        Void a(Void void_) throws Exception {
            this.a();
            return null;
        }

        abstract void a() throws Exception;

        @Override
        void a(Command command) throws StateMachineTransitionException, Exception {
            super.a(command, null);
        }
    }

    private class CallableWorker<Return, Parameters, PerformException extends Exception>
    implements Callable<Return> {
        private CommandWorker<Return, Parameters, PerformException> b;
        private Command c;
        private Parameters d;
        private CommandDelegate e;

        public CallableWorker(CommandWorker<Return, Parameters, PerformException> commandWorker, Command command, Parameters Parameters, CommandDelegate commandDelegate) {
            this.b = commandWorker;
            this.c = command;
            this.d = Parameters;
            this.e = commandDelegate;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        @Override
        public Return call() throws Exception, StateMachineTransitionException {
            var1_1 = null;
            try {
                var2_3 = this.b.a(this.c, this.d);
                if (this.e == null) return var2_3;
                this.e.a(this.c, this.b, this.d, null);
            }
            catch (Exception var1_2) {
                try {
                    throw var1_2;
                }
                catch (Throwable var2_4) {}
                ** GOTO lbl-1000
                catch (Throwable var2_6) {}
lbl-1000: // 2 sources:
                {
                    if (this.e == null) throw var2_5;
                    this.e.a(this.c, this.b, this.d, var1_1);
                    throw var2_5;
                }
            }
            return var2_3;
        }
    }

    public static enum Command implements ICommand
    {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        i,
        j,
        k,
        l,
        m,
        n,
        o,
        p,
        q,
        r,
        s,
        t;
        

        private Command() {
        }
    }

    public static interface CommandDelegate {
        public <Return, Parameters, WorkerException extends Exception> void a(Command var1, CommandWorker<Return, Parameters, WorkerException> var2, Parameters var3, Exception var4);
    }

    public abstract class CommandWorker<Return, Parameters, PerformException extends Exception> {
        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public Return a(@NonNull Command command, @Nullable Parameters object) throws Exception, StateMachineTransitionException {
            AudioSystemStateMachine audioSystemStateMachine = AudioSystemStateMachine.this;
            synchronized (audioSystemStateMachine) {
                AudioSystemStateMachine.this.a(command);
                Throwable throwable = null;
                try {
                    object = this.a(object);
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    try {
                        throw throwable2;
                    }
                    catch (Throwable throwable3) {
                        AudioSystemStateMachine.this.a(command, throwable);
                        throw throwable3;
                    }
                }
                AudioSystemStateMachine.this.a(command, null);
                return (Return)object;
            }
        }

        @Nullable
        abstract Return a(@Nullable Parameters var1) throws Exception;
    }

    abstract class GetWorker<Return, ExecuteException extends Exception>
    extends CommandWorker<Return, Void, ExecuteException> {
        GetWorker() {
        }

        @Override
        Return a(Command command) throws StateMachineTransitionException, Exception {
            return super.a(command, null);
        }

        @Nullable
        @Override
        Return a(@Nullable Void void_) throws Exception {
            return this.b();
        }

        @Nullable
        abstract Return b() throws Exception;
    }

    public static class NonRealTimeCommandLogger
    extends CommandLogger {
        public NonRealTimeCommandLogger() {
            super(a, TagLogger.LogLevel.b);
        }

        @Override
        protected boolean b(@NonNull ICommand iCommand, @NonNull IError iError) {
            switch (.a[((Command)iCommand).ordinal()]) {
                default: {
                    return true;
                }
                case 1: 
                case 2: 
                case 3: 
                case 4: 
            }
            return false;
        }
    }

    public static enum Result implements IError
    {
        a,
        b,
        c,
        d,
        e;
        

        private Result() {
        }

        @Override
        public String a() {
            return this.toString();
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
        i,
        j,
        k,
        l,
        m,
        n,
        o,
        p,
        q;
        

        private State() {
        }
    }

    public static class TransitionLogger
    extends StateChangeLogger {
        TransitionLogger() {
            super(new TagLogger(a), TagLogger.LogLevel.b);
        }

        @Override
        protected boolean b(@NonNull IState iState, @NonNull IState iState2, @Nullable IError iError) {
            if (iState != iState2) {
                return true;
            }
            return false;
        }
    }

}

