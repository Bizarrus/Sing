/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 */
package com.smule.chat;

import android.content.Context;
import android.content.SharedPreferences;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.models.SNPChat;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInfo;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.chat.PeerChat;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.PriorityExecutor;
import com.smule.chat.TextChatMessage;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.smerialization.Smerializable;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import com.smule.chat.smerialization.SmerializationConfiguration;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.Future;

public class ChatManagerPersistence {
    private static final String e = ChatManagerPersistence.class.getName();
    private XMPPDelegate a;
    private File b;
    private final LinkedHashSet<Smerializable> c;
    private SmerializationConfiguration d;

    ChatManagerPersistence(XMPPDelegate xMPPDelegate) {
        this.a = xMPPDelegate;
        this.b = new File(xMPPDelegate.l().getFilesDir(), "chat");
        this.b.mkdirs();
        this.c = new LinkedHashSet();
        this.d = new SmerializationConfiguration();
        this.d.a("PeerChat", PeerChat.class);
        this.d.a("GroupChat", GroupChat.class);
        this.d.a("GroupInfo", GroupInfo.class);
        this.d.a("TextChatMessage", TextChatMessage.class);
        this.d.a("GroupInvitationChatMessage", GroupInvitationChatMessage.class);
        this.d.a("GroupStatusChatMessage", GroupStatusChatMessage.class);
        this.d.a("PerformanceChatMessage", PerformanceChatMessage.class);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private Object a(File var1_1) {
        var3_6 = new FileInputStream(var1_1);
        var5_8 = var2_7 = new SmerializableInputStream(this.d, new BufferedInputStream(var3_6));
        var4_9 = var3_6;
        var6_10 = var2_7.c();
        this.a(var2_7);
        this.a(var3_6);
        return var6_10;
        catch (Exception var6_11) {
            var2_7 = null;
            var3_6 = null;
lbl14: // 3 sources:
            var5_8 = var2_7;
            var4_9 = var3_6;
            Log.d(ChatManagerPersistence.e, "error loading " + var1_1.getName(), (Throwable)var6_12);
            this.a(var2_7);
            this.a(var3_6);
            return null;
        }
        catch (Throwable var1_2) {
            var5_8 = null;
            var3_6 = null;
lbl24: // 3 sources:
            do {
                this.a(var5_8);
                this.a(var3_6);
                throw var1_3;
                break;
            } while (true);
        }
        catch (Throwable var1_4) {
            var5_8 = null;
            ** GOTO lbl24
        }
        {
            catch (Throwable var1_5) {
                var3_6 = var4_9;
                ** continue;
            }
        }
        catch (Exception var6_13) {
            var2_7 = null;
            ** GOTO lbl14
        }
        catch (Exception var6_14) {
            ** GOTO lbl14
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a() {
        Object object = this.b();
        if (object == null) {
            Log.c(e, "empty dirty queue");
            return;
        }
        this.d((Smerializable)object);
        object = this.c;
        synchronized (object) {
            if (!this.c.isEmpty()) {
                this.a.b(new Runnable(){

                    @Override
                    public void run() {
                        ChatManagerPersistence.this.a();
                    }
                });
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Chat.Bucket bucket, List<SNPChat> object, ArrayList<Chat> arrayList) {
        Iterator iterator = object.iterator();
        while (iterator.hasNext()) {
            object = (SNPChat)iterator.next();
            Chat.Options options = new Chat.Options();
            options.b = object.jid;
            object = object.type.equals("ACCT") ? Chat.Type.a : Chat.Type.b;
            options.a = object;
            options.e = true;
            options.i = bucket;
            if (options.a == Chat.Type.a) {
                arrayList.add(new PeerChat(this.a, options));
                continue;
            }
            arrayList.add(new GroupChat(this.a, options));
        }
        return;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a(InputStream inputStream) {
        if (inputStream == null) return;
        try {
            inputStream.close();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a(OutputStream outputStream) {
        if (outputStream == null) return;
        try {
            outputStream.close();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Smerializable b() {
        LinkedHashSet<Smerializable> linkedHashSet = this.c;
        synchronized (linkedHashSet) {
            Iterator<Smerializable> iterator = this.c.iterator();
            if (iterator.hasNext()) {
                Smerializable smerializable = iterator.next();
                iterator.remove();
                return smerializable;
            }
            return null;
        }
    }

    private void c() {
        File[] arrfile = this.d();
        int n = arrfile.length;
        for (int i = 0; i < n; ++i) {
            arrfile[i].delete();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(final LoadCallback loadCallback) {
        ArrayList<GroupInfo> arrayList;
        long l;
        int n;
        ArrayList<Chat> arrayList2;
        block5 : {
            l = System.currentTimeMillis();
            arrayList2 = new ArrayList<Chat>();
            arrayList = new ArrayList<GroupInfo>();
            File[] arrfile = this.d();
            int n2 = arrfile.length;
            for (n = 0; n < n2; ++n) {
                Object object = this.a(arrfile[n]);
                if (object == null) {
                    n = 1;
                    break block5;
                }
                if (object instanceof Chat) {
                    arrayList2.add((Chat)object);
                    continue;
                }
                if (!(object instanceof GroupInfo)) continue;
                arrayList.add((GroupInfo)object);
            }
            n = 0;
        }
        if (n != 0 || !this.e()) {
            this.c();
            this.d(loadCallback);
        } else {
            this.a.b(new Runnable(){

                @Override
                public void run() {
                    loadCallback.a(arrayList2, arrayList, false, ChatStatus.a);
                }
            });
        }
        long l2 = System.currentTimeMillis();
        Log.a(e, "loaded in " + (l2 - l));
    }

    private void d(final LoadCallback loadCallback) {
        com.smule.android.network.managers.SparkManager.a().a("ACCT", 200, new SparkManager.ActiveChatsCallback(){

            @Override
            public void handleResponse(final SparkManager activeChatsResponse) {
                com.smule.android.network.managers.SparkManager.a().a("GRP", 100, new SparkManager.ActiveChatsCallback(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void handleResponse(SparkManager object) {
                        ArrayList<Chat> arrayList = new ArrayList<Chat>();
                        if (activeChatsResponse.a() && object.a()) {
                            ChatStatus chatStatus = ChatStatus.a;
                            ChatManagerPersistence.this.f();
                            ChatManagerPersistence.this.a(Chat.Bucket.a, activeChatsResponse.inbox, arrayList);
                            ChatManagerPersistence.this.a(Chat.Bucket.b, activeChatsResponse.other, arrayList);
                            ChatManagerPersistence.this.a(Chat.Bucket.a, object.inbox, arrayList);
                            ChatManagerPersistence.this.a(Chat.Bucket.b, object.other, arrayList);
                            object = chatStatus;
                        } else {
                            object = ChatStatus.b;
                        }
                        loadCallback.a(arrayList, new ArrayList<GroupInfo>(), true, (ChatStatus)((Object)object));
                    }
                });
            }

        });
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private void d(Smerializable var1_1) {
        var3_5 = null;
        var4_9 = null;
        var5_11 = this.e((Smerializable)var1_1);
        if (var5_11 == null) {
            return;
        }
        var2_12 = new FileOutputStream(var5_11);
        var4_9 = new SmerializableOutputStream(this.d, new BufferedOutputStream(var2_12));
        var4_9.a((Smerializable)var1_1);
        this.a(var4_9);
        this.a(var2_12);
        return;
        catch (IOException var3_6) {
            var1_1 = null;
            var2_12 = var4_9;
lbl19: // 4 sources:
            Log.d(ChatManagerPersistence.e, "save error", (Throwable)var3_5);
            this.a((OutputStream)var1_1);
            this.a(var2_12);
            var5_11.delete();
            return;
        }
        catch (Throwable var1_2) {
            var2_12 = null;
lbl26: // 4 sources:
            do {
                this.a((OutputStream)var3_5);
                this.a(var2_12);
                throw var1_1;
                break;
            } while (true);
        }
        catch (Throwable var1_3) {
            ** GOTO lbl26
        }
        catch (Throwable var1_4) {
            var3_5 = var4_9;
            ** GOTO lbl26
        }
        {
            catch (Throwable var4_10) {
                var3_5 = var1_1;
                var1_1 = var4_10;
                ** continue;
            }
        }
        catch (IOException var3_7) {
            var1_1 = null;
            ** GOTO lbl19
        }
        catch (IOException var3_8) {
            var1_1 = var4_9;
            ** GOTO lbl19
        }
    }

    private File[] d() {
        return this.b.listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File file, String string2) {
                return string2.endsWith(".data");
            }
        });
    }

    private File e(Smerializable object) {
        if ((object = object.O()) == null) {
            return null;
        }
        return new File(this.b, (String)object);
    }

    private boolean e() {
        return this.a.m().getBoolean("chat.restored", false);
    }

    private void f() {
        SharedPreferences.Editor editor = this.a.m().edit();
        editor.putBoolean("chat.restored", true);
        editor.apply();
    }

    public void a(final LoadCallback loadCallback) {
        PriorityExecutor.a.execute(new Runnable(){

            @Override
            public void run() {
                ChatManagerPersistence.this.c(loadCallback);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Smerializable smerializable) {
        LinkedHashSet<Smerializable> linkedHashSet = this.c;
        synchronized (linkedHashSet) {
            boolean bl = this.c.isEmpty();
            this.c.add(smerializable);
            if (bl) {
                this.a.a(100, new Runnable(){

                    @Override
                    public void run() {
                        ChatManagerPersistence.this.a();
                    }
                });
            }
            return;
        }
    }

    public void a(Collection<Smerializable> object) {
        object = object.iterator();
        while (object.hasNext()) {
            this.c((Smerializable)object.next());
        }
    }

    public void b(LoadCallback loadCallback) {
        this.d(loadCallback);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(Smerializable object) {
        LinkedHashSet<Smerializable> linkedHashSet = this.c;
        synchronized (linkedHashSet) {
            this.c.remove(object);
        }
        object = this.e((Smerializable)object);
        if (object != null) {
            object.delete();
        }
    }

    public void c(Smerializable smerializable) {
        this.d(smerializable);
    }

    public static interface LoadCallback {
        public void a(ArrayList<Chat> var1, ArrayList<GroupInfo> var2, boolean var3, ChatStatus var4);
    }

}

