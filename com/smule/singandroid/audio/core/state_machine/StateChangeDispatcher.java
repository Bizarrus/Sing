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
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.state_machine.IState;
import com.smule.singandroid.audio.core.state_machine.StateChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StateChangeDispatcher
implements StateChangeListener {
    private Collection<StateChangeListener> a;

    public StateChangeDispatcher() {
        this.a = new ArrayList<StateChangeListener>();
    }

    public StateChangeDispatcher(Collection<StateChangeListener> collection) throws IllegalArgumentException {
        if (collection == null) {
            throw new IllegalArgumentException("You must give me an actual (non-null) collection of StateChangeListeners");
        }
        this.a = collection;
    }

    @Override
    public void a(@NonNull IState iState, @NonNull IState iState2, @Nullable IError iError) {
        Iterator<StateChangeListener> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(iState, iState2, iError);
        }
    }
}

