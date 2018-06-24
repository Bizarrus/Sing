/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.util.Pair
 */
package com.smule.singandroid.audio.core.exception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.exception.SmuleException;
import com.smule.singandroid.audio.core.parameter.KeyedParameter;
import com.smule.singandroid.audio.core.parameter.MessageParameterHandler;
import com.smule.singandroid.audio.core.state_machine.StateMachineErrorCode;
import com.smule.singandroid.audio.core.state_machine.StateMachineParameterType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StateMachineException
extends SmuleException {
    private String d = null;

    public StateMachineException(@NonNull StateMachineErrorCode stateMachineErrorCode) {
        super(stateMachineErrorCode);
    }

    protected static Pair<StateMachineParameterType, Object> a(@NonNull StateMachineParameterType stateMachineParameterType, @Nullable Object object) {
        return Pair.create((Object)((Object)stateMachineParameterType), (Object)object);
    }

    private String a(List<Pair<StateMachineParameterType, Object>> object) {
        if (object == null) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        ArrayList<KeyedParameter> arrayList = new ArrayList<KeyedParameter>(object.size());
        object = object.iterator();
        while (object.hasNext()) {
            Pair pair = (Pair)object.next();
            arrayList.add(new KeyedParameter(((StateMachineParameterType)((Object)pair.first)).toString(), pair.second));
        }
        return MessageParameterHandler.a(this.a.a(), arrayList);
    }

    @SafeVarargs
    protected final /* varargs */ String a(Pair<StateMachineParameterType, Object> ... arrpair) {
        if (this.d == null) {
            return this.a(Arrays.asList(arrpair));
        }
        ArrayList<Pair<StateMachineParameterType, Object>> arrayList = new ArrayList<Pair<StateMachineParameterType, Object>>(arrpair.length + 1);
        arrayList.add(StateMachineException.a(StateMachineParameterType.g, this.d));
        arrayList.addAll(Arrays.asList(arrpair));
        return this.a(arrayList);
    }
}

