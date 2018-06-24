/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.packet.Element
 *  org.jivesoftware.smack.provider.ExtensionElementProvider
 *  org.jivesoftware.smackx.muc.packet.Destroy
 *  org.jivesoftware.smackx.muc.packet.MUCItem
 *  org.jivesoftware.smackx.muc.packet.MUCUser
 *  org.jivesoftware.smackx.muc.packet.MUCUser$Decline
 *  org.jivesoftware.smackx.muc.packet.MUCUser$Invite
 *  org.jivesoftware.smackx.muc.packet.MUCUser$Status
 *  org.jivesoftware.smackx.muc.provider.MUCParserUtils
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package com.smule.chat;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.muc.provider.MUCParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SmuleMUCUserProvider
extends ExtensionElementProvider<MUCUser> {
    /*
     * Exception decompiling
     */
    public static MUCItem a(XmlPullParser var0) throws XmlPullParserException, IOException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:486)
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

    private static MUCUser.Invite c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean bl = false;
        MUCUser.Invite invite = new MUCUser.Invite();
        invite.a(xmlPullParser.getAttributeValue("", "from"));
        invite.c(xmlPullParser.getAttributeValue("", "to"));
        while (!bl) {
            int n = xmlPullParser.next();
            if (n == 2) {
                if (!xmlPullParser.getName().equals("reason")) continue;
                invite.b(xmlPullParser.nextText());
                continue;
            }
            if (n != 3 || !xmlPullParser.getName().equals("invite")) continue;
            bl = true;
        }
        return invite;
    }

    private static MUCUser.Decline d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean bl = false;
        MUCUser.Decline decline = new MUCUser.Decline();
        decline.a(xmlPullParser.getAttributeValue("", "from"));
        decline.c(xmlPullParser.getAttributeValue("", "to"));
        while (!bl) {
            int n = xmlPullParser.next();
            if (n == 2) {
                if (!xmlPullParser.getName().equals("reason")) continue;
                decline.b(xmlPullParser.nextText());
                continue;
            }
            if (n != 3 || !xmlPullParser.getName().equals("decline")) continue;
            bl = true;
        }
        return decline;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public MUCUser a(XmlPullParser var1_1, int var2_2) throws XmlPullParserException, IOException {
        var4_3 = new MUCUser();
        block20 : do {
            switch (var1_1.next()) {
                default: {
                    continue block20;
                }
                case 2: {
                    var5_5 = var1_1.getName();
                    var3_4 = -1;
                    switch (var5_5.hashCode()) {
                        case -1183699191: {
                            if (var5_5.equals("invite")) {
                                var3_4 = 0;
                                ** break;
                            }
                            ** GOTO lbl33
                        }
                        case 3242771: {
                            if (var5_5.equals("item")) {
                                var3_4 = 1;
                                ** break;
                            }
                            ** GOTO lbl33
                        }
                        case 1216985755: {
                            if (var5_5.equals("password")) {
                                var3_4 = 2;
                                ** break;
                            }
                            ** GOTO lbl33
                        }
                        case -892481550: {
                            if (var5_5.equals("status")) {
                                var3_4 = 3;
                                ** break;
                            }
                            ** GOTO lbl33
                        }
                        case 1542349558: {
                            if (var5_5.equals("decline")) {
                                var3_4 = 4;
                            }
                        }
lbl33: // 12 sources:
                        default: {
                            ** GOTO lbl38
                        }
                        case 1557372922: 
                    }
                    if (var5_5.equals("destroy")) {
                        var3_4 = 5;
                    }
lbl38: // 4 sources:
                    switch (var3_4) {
                        default: {
                            continue block20;
                        }
                        case 0: {
                            var4_3.a(SmuleMUCUserProvider.c(var1_1));
                            continue block20;
                        }
                        case 1: {
                            var4_3.a(SmuleMUCUserProvider.a(var1_1));
                            continue block20;
                        }
                        case 2: {
                            var4_3.a(var1_1.nextText());
                            continue block20;
                        }
                        case 3: {
                            var4_3.a(MUCUser.Status.a((String)var1_1.getAttributeValue("", "code")));
                            continue block20;
                        }
                        case 4: {
                            var4_3.a(SmuleMUCUserProvider.d(var1_1));
                            continue block20;
                        }
                        case 5: 
                    }
                    var4_3.a(MUCParserUtils.b((XmlPullParser)var1_1));
                    continue block20;
                }
                case 3: 
            }
            if (var1_1.getDepth() == var2_2) return var4_3;
        } while (true);
    }

    public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
        return this.a(xmlPullParser, n);
    }
}

