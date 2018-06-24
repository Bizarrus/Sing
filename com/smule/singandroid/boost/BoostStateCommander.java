/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.boost;

import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.parameter.KeyedParameter;
import com.smule.android.core.parameter.ParameterHelper;
import com.smule.android.core.state_machine.IState;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.boost.BoostContext;
import com.smule.singandroid.boost.BoostError;
import com.smule.singandroid.boost.BoostParameterType;
import com.smule.singandroid.boost.BoostPresenter;
import com.smule.singandroid.boost.BoostStateMachine;
import java.util.ArrayList;
import java.util.List;

class BoostStateCommander {
    public static final String a = BoostStateCommander.class.getName();
    BoostStateMachine b = new BoostStateMachine();
    private BoostContext c;
    private int d;

    BoostStateCommander() throws SmuleException {
        this.h();
    }

    private void a(BoostStateMachine.Command command) {
        Log.a(a, "performAction_noOp_" + command.name());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(KeyedParameter[] arrkeyedParameter) throws SmuleException {
        Log.b(a, "performAction_boost...");
        if (arrkeyedParameter != null && arrkeyedParameter.length > 0) {
            Integer n = (Integer)ParameterHelper.a(BoostParameterType.a.name(), arrkeyedParameter);
            int n2 = n == null ? -1 : n;
            this.d = n2;
            if (this.d != 0) {
                throw new SmuleException((IError)BoostError.e, arrkeyedParameter);
            }
        }
    }

    /*
     * Exception decompiling
     */
    private /* varargs */ KeyedParameter[] a(BoostStateMachine.Command var1_1, KeyedParameter ... var2_2) throws SmuleException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: First case is not immediately after switch.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:366)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:423)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:217)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:162)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:95)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:357)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:769)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:701)
        // org.benf.cfr.reader.Main.doJar(Main.java:134)
        // org.benf.cfr.reader.Main.main(Main.java:189)
        throw new IllegalStateException("Decompilation failed");
    }

    private /* varargs */ void b(BoostStateMachine.Command command, KeyedParameter ... arrkeyedParameter) throws SmuleException {
        if (!(this.a() instanceof Enum)) {
            Log.e(a, "Expect getState() to be an Enum");
            return;
        }
        String string2 = BoostStateMachine.a((Enum)((Object)this.a()));
        switch (.a[command.ordinal()]) {
            default: {
                BoostPresenter.a(this.c, string2, (Object[])arrkeyedParameter);
                return;
            }
            case 1: {
                BoostPresenter.a(this.c, string2, new Object[0]);
                return;
            }
            case 2: {
                BoostPresenter.a(this.c, string2, new Object[0]);
                return;
            }
            case 4: {
                BoostPresenter.a(this.c, string2, new Object[0]);
                return;
            }
            case 3: 
        }
        BoostPresenter.a(this.c, string2, new Object[0]);
    }

    public static List<String> d() {
        int n;
        int n2 = 0;
        ArrayList<String> arrayList = new ArrayList<String>();
        BoostStateMachine.Command[] arrcommand = BoostStateMachine.Command.values();
        int n3 = arrcommand.length;
        for (n = 0; n < n3; ++n) {
            arrayList.add(arrcommand[n].a());
        }
        arrcommand = BoostStateMachine.State.values();
        n3 = arrcommand.length;
        for (n = n2; n < n3; ++n) {
            arrayList.add(arrcommand[n].a());
        }
        return arrayList;
    }

    private void e() {
        Log.a(a, "performAction_finish...");
    }

    private void f() throws SmuleException {
        Log.b(a, "performAction_cancel...");
    }

    private void g() throws SmuleException {
        Log.b(a, "performAction_startFreeBoost...");
        if (this.c.d.boost) {
            throw new SmuleException((IError)BoostError.a, new KeyedParameter[0]);
        }
        if (!this.c.a) {
            throw new SmuleException((IError)BoostError.b, new KeyedParameter[0]);
        }
        if (!this.c.b) {
            throw new SmuleException((IError)BoostError.c, new KeyedParameter[0]);
        }
    }

    private void h() {
        this.d = -1;
    }

    public IState a() {
        return this.b.a();
    }

    public void a(int n) throws SmuleException {
        KeyedParameter keyedParameter = new KeyedParameter(BoostParameterType.a.name(), n);
        this.a(BoostStateMachine.Command.d, keyedParameter);
    }

    void a(BoostContext boostContext) {
        this.c = boostContext;
    }

    void b() throws SmuleException {
        this.a(BoostStateMachine.Command.b, new KeyedParameter[0]);
    }

    public void c() throws SmuleException {
        this.a(BoostStateMachine.Command.e, new KeyedParameter[0]);
    }

}

