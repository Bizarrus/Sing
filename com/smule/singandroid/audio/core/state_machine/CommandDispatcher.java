/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.singandroid.audio.core.state_machine;

import android.support.annotation.NonNull;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.state_machine.CommandListener;
import com.smule.singandroid.audio.core.state_machine.ICommand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CommandDispatcher
implements CommandListener {
    private Collection<CommandListener> a;

    public CommandDispatcher() {
        this.a = new ArrayList<CommandListener>();
    }

    public CommandDispatcher(Collection<CommandListener> collection) throws IllegalArgumentException {
        if (collection == null) {
            throw new IllegalArgumentException("You must give me an actual (non-null) collection of CommandListeners");
        }
        this.a = collection;
    }

    @Override
    public void a(@NonNull ICommand iCommand, @NonNull IError iError) {
        Iterator<CommandListener> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(iCommand, iError);
        }
    }
}

