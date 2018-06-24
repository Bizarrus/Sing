/*
 * Decompiled with CFR 0_123.
 */
package com.smule.samples.player;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SMError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.logger.TagLogger;
import com.smule.android.core.parameter.KeyedParameter;
import com.smule.android.core.state_machine.ICommand;
import com.smule.android.core.wait.WaitUtil;
import com.smule.samples.player.SimPlayerStateMachine;

public class SimPlayer {
    private SimPlayerStateMachine a;
    private IError b = null;
    private TagLogger c;

    public SimPlayer() throws SmuleException {
        this.c = new TagLogger(this.getClass().getSimpleName());
        this.a = new SimPlayerStateMachine();
    }

    private void a() throws SmuleException {
        this.c.a("initializing...");
        new PretendWorker(SimPlayerStateMachine.Command.a).start();
    }

    static /* synthetic */ void a(SimPlayer simPlayer) throws SmuleException {
        simPlayer.b();
    }

    /*
     * Exception decompiling
     */
    private /* varargs */ KeyedParameter[] a(SimPlayerStateMachine.Command var1_1, KeyedParameter ... var2_2) throws SmuleException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:396)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:474)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2880)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:837)
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

    private void b() throws SmuleException {
        this.c.a("initialization complete...");
        this.a(SimPlayerStateMachine.Command.b, new KeyedParameter[0]);
    }

    static /* synthetic */ void b(SimPlayer simPlayer) throws SmuleException {
        simPlayer.d();
    }

    private /* varargs */ void b(SimPlayerStateMachine.Command command, KeyedParameter ... arrkeyedParameter) throws SmuleException {
        switch (command) {
            default: 
        }
    }

    private void c() throws SmuleException {
        this.c.a("starting play...");
        new PretendWorker(SimPlayerStateMachine.Command.c).start();
    }

    static /* synthetic */ void c(SimPlayer simPlayer) throws SmuleException {
        simPlayer.f();
    }

    private void d() throws SmuleException {
        this.c.a("start play complete...");
        this.a(SimPlayerStateMachine.Command.d, new KeyedParameter[0]);
    }

    private void e() throws SmuleException {
        this.c.a("stopping play...");
        new PretendWorker(SimPlayerStateMachine.Command.e).start();
    }

    private void f() throws SmuleException {
        this.c.a("stop play complete...");
        this.a(SimPlayerStateMachine.Command.f, new KeyedParameter[0]);
    }

    private void g() throws SmuleException {
        this.c.a("closing player...");
        this.a.a((ICommand)SimPlayerStateMachine.Command.g, SMError.a);
        WaitUtil.a(500);
        if (this.b != null) {
            ErrorHelper.a(this.b, new KeyedParameter[0]);
        }
    }

    private class PretendWorker
    extends Thread {
        SimPlayerStateMachine.Command a;
        private TagLogger c;

        public PretendWorker(SimPlayerStateMachine.Command command) {
            this.c = new TagLogger(this.getClass().getSimpleName());
            this.a = command;
        }

        /*
         * Exception decompiling
         */
        @Override
        public void run() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[CASE]], but top level block is 1[TRYBLOCK]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:419)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:471)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2880)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:837)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:217)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:162)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:95)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:357)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:769)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:682)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:765)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:701)
            // org.benf.cfr.reader.Main.doJar(Main.java:134)
            // org.benf.cfr.reader.Main.main(Main.java:189)
            throw new IllegalStateException("Decompilation failed");
        }
    }

}

