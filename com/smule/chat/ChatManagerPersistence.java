package com.smule.chat;

import android.content.SharedPreferences.Editor;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.managers.SparkManager.ActiveChatsCallback;
import com.smule.android.network.managers.SparkManager.ActiveChatsResponse;
import com.smule.android.network.models.SNPChat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.Type;
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

public class ChatManagerPersistence {
    private static final String f18102f = ChatManagerPersistence.class.getName();
    private XMPPDelegate f18103a;
    private ChatConfiguration f18104b;
    private File f18105c = new File(this.f18104b.a().getFilesDir(), "chat");
    private final LinkedHashSet<Smerializable> f18106d;
    private SmerializationConfiguration f18107e;

    public interface LoadCallback {
        void mo6327a(ArrayList<Chat> arrayList, ArrayList<GroupInfo> arrayList2, boolean z, ChatStatus chatStatus);
    }

    class C37041 implements Runnable {
        final /* synthetic */ ChatManagerPersistence f18089a;

        C37041(ChatManagerPersistence chatManagerPersistence) {
            this.f18089a = chatManagerPersistence;
        }

        public void run() {
            this.f18089a.m19349a();
        }
    }

    class C37063 implements Runnable {
        final /* synthetic */ ChatManagerPersistence f18092a;

        C37063(ChatManagerPersistence chatManagerPersistence) {
            this.f18092a = chatManagerPersistence;
        }

        public void run() {
            this.f18092a.m19349a();
        }
    }

    class C37085 implements FilenameFilter {
        final /* synthetic */ ChatManagerPersistence f18097a;

        C37085(ChatManagerPersistence chatManagerPersistence) {
            this.f18097a = chatManagerPersistence;
        }

        public boolean accept(File file, String str) {
            return str.endsWith(".data");
        }
    }

    public ChatManagerPersistence(XMPPDelegate xMPPDelegate, ChatConfiguration chatConfiguration) {
        this.f18103a = xMPPDelegate;
        this.f18104b = chatConfiguration;
        this.f18105c.mkdirs();
        this.f18106d = new LinkedHashSet();
        this.f18107e = new SmerializationConfiguration();
        this.f18107e.m19764a("PeerChat", PeerChat.class);
        this.f18107e.m19764a("GroupChat", GroupChat.class);
        this.f18107e.m19764a("GroupInfo", GroupInfo.class);
        this.f18107e.m19764a("TextChatMessage", TextChatMessage.class);
        this.f18107e.m19764a("GroupInvitationChatMessage", GroupInvitationChatMessage.class);
        this.f18107e.m19764a("GroupStatusChatMessage", GroupStatusChatMessage.class);
        this.f18107e.m19764a("PerformanceChatMessage", PerformanceChatMessage.class);
    }

    public void m19367a(Smerializable smerializable) {
        synchronized (this.f18106d) {
            boolean isEmpty = this.f18106d.isEmpty();
            this.f18106d.add(smerializable);
            if (isEmpty) {
                this.f18103a.a(100, new C37041(this));
            }
        }
    }

    public void m19370b(Smerializable smerializable) {
        synchronized (this.f18106d) {
            this.f18106d.remove(smerializable);
        }
        File e = m19363e(smerializable);
        if (e != null) {
            e.delete();
        }
    }

    public void m19368a(Collection<Smerializable> collection) {
        for (Smerializable c : collection) {
            m19371c(c);
        }
    }

    public void m19371c(Smerializable smerializable) {
        m19361d(smerializable);
    }

    public void m19366a(final LoadCallback loadCallback) {
        PriorityExecutor.f18318a.execute(new Runnable(this) {
            final /* synthetic */ ChatManagerPersistence f18091b;

            public void run() {
                this.f18091b.m19359c(loadCallback);
            }
        });
    }

    public void m19369b(LoadCallback loadCallback) {
        m19360d(loadCallback);
    }

    private void m19349a() {
        Smerializable b = m19356b();
        if (b != null) {
            m19361d(b);
            synchronized (this.f18106d) {
                if (!this.f18106d.isEmpty()) {
                    this.f18103a.b(new C37063(this));
                }
            }
            return;
        }
        Log.c(f18102f, "empty dirty queue");
    }

    private Smerializable m19356b() {
        Smerializable smerializable;
        synchronized (this.f18106d) {
            Iterator it = this.f18106d.iterator();
            if (it.hasNext()) {
                smerializable = (Smerializable) it.next();
                it.remove();
            } else {
                smerializable = null;
            }
        }
        return smerializable;
    }

    private void m19361d(Smerializable smerializable) {
        OutputStream smerializableOutputStream;
        Throwable e;
        OutputStream outputStream = null;
        File e2 = m19363e(smerializable);
        if (e2 != null) {
            OutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(e2);
                try {
                    smerializableOutputStream = new SmerializableOutputStream(this.f18107e, new BufferedOutputStream(fileOutputStream));
                } catch (IOException e3) {
                    e = e3;
                    smerializableOutputStream = null;
                    outputStream = fileOutputStream;
                    try {
                        Log.d(f18102f, "save error", e);
                        m19355a(smerializableOutputStream);
                        m19355a(outputStream);
                        e2.delete();
                    } catch (Throwable th) {
                        e = th;
                        fileOutputStream = outputStream;
                        outputStream = smerializableOutputStream;
                        m19355a(outputStream);
                        m19355a(fileOutputStream);
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    m19355a(outputStream);
                    m19355a(fileOutputStream);
                    throw e;
                }
                try {
                    smerializableOutputStream.m19754a(smerializable);
                    m19355a(smerializableOutputStream);
                    m19355a(fileOutputStream);
                } catch (IOException e4) {
                    e = e4;
                    outputStream = fileOutputStream;
                    Log.d(f18102f, "save error", e);
                    m19355a(smerializableOutputStream);
                    m19355a(outputStream);
                    e2.delete();
                } catch (Throwable th3) {
                    e = th3;
                    outputStream = smerializableOutputStream;
                    m19355a(outputStream);
                    m19355a(fileOutputStream);
                    throw e;
                }
            } catch (IOException e5) {
                e = e5;
                smerializableOutputStream = null;
                Log.d(f18102f, "save error", e);
                m19355a(smerializableOutputStream);
                m19355a(outputStream);
                e2.delete();
            } catch (Throwable th4) {
                e = th4;
                fileOutputStream = null;
                m19355a(outputStream);
                m19355a(fileOutputStream);
                throw e;
            }
        }
    }

    private File m19363e(Smerializable smerializable) {
        String O = smerializable.mo6315O();
        if (O == null) {
            return null;
        }
        return new File(this.f18105c, O);
    }

    private void m19355a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private void m19359c(final LoadCallback loadCallback) {
        Object a;
        long currentTimeMillis = System.currentTimeMillis();
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        for (File a2 : m19362d()) {
            a = m19348a(a2);
            if (a == null) {
                a = 1;
                break;
            }
            if (a instanceof Chat) {
                arrayList.add((Chat) a);
            } else if (a instanceof GroupInfo) {
                arrayList2.add((GroupInfo) a);
            }
        }
        a = null;
        if (a == null && m19364e()) {
            this.f18103a.b(new Runnable(this) {
                final /* synthetic */ ChatManagerPersistence f18096d;

                public void run() {
                    loadCallback.mo6327a(arrayList, arrayList2, false, ChatStatus.OK);
                }
            });
        } else {
            m19358c();
            m19360d(loadCallback);
        }
        Log.a(f18102f, "loaded in " + (System.currentTimeMillis() - currentTimeMillis));
    }

    private void m19358c() {
        for (File delete : m19362d()) {
            delete.delete();
        }
    }

    private File[] m19362d() {
        return this.f18105c.listFiles(new C37085(this));
    }

    private Object m19348a(File file) {
        InputStream fileInputStream;
        InputStream smerializableInputStream;
        Throwable e;
        Object c;
        Throwable th;
        InputStream inputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                smerializableInputStream = new SmerializableInputStream(this.f18107e, new BufferedInputStream(fileInputStream));
            } catch (Exception e2) {
                e = e2;
                smerializableInputStream = inputStream;
                try {
                    Log.d(f18102f, "error loading " + file.getName(), e);
                    m19354a(smerializableInputStream);
                    m19354a(fileInputStream);
                    return c;
                } catch (Throwable th2) {
                    th = th2;
                    m19354a(smerializableInputStream);
                    m19354a(fileInputStream);
                    throw th;
                }
            } catch (Throwable e3) {
                smerializableInputStream = inputStream;
                th = e3;
                m19354a(smerializableInputStream);
                m19354a(fileInputStream);
                throw th;
            }
            try {
                c = smerializableInputStream.m19752c();
                m19354a(smerializableInputStream);
                m19354a(fileInputStream);
            } catch (Exception e4) {
                e3 = e4;
                Log.d(f18102f, "error loading " + file.getName(), e3);
                m19354a(smerializableInputStream);
                m19354a(fileInputStream);
                return c;
            }
        } catch (Exception e5) {
            e3 = e5;
            smerializableInputStream = inputStream;
            fileInputStream = inputStream;
            Log.d(f18102f, "error loading " + file.getName(), e3);
            m19354a(smerializableInputStream);
            m19354a(fileInputStream);
            return c;
        } catch (Throwable e32) {
            smerializableInputStream = inputStream;
            fileInputStream = inputStream;
            th = e32;
            m19354a(smerializableInputStream);
            m19354a(fileInputStream);
            throw th;
        }
        return c;
    }

    private void m19354a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private void m19360d(final LoadCallback loadCallback) {
        SparkManager.m18359a().m18364a("ACCT", 200, new ActiveChatsCallback(this) {
            final /* synthetic */ ChatManagerPersistence f18101b;

            public void handleResponse(final ActiveChatsResponse activeChatsResponse) {
                SparkManager.m18359a().m18364a("GRP", 100, new ActiveChatsCallback(this) {
                    final /* synthetic */ C37106 f18099b;

                    public void handleResponse(ActiveChatsResponse activeChatsResponse) {
                        ChatStatus chatStatus;
                        ArrayList arrayList = new ArrayList();
                        if (activeChatsResponse.a() && activeChatsResponse.a()) {
                            chatStatus = ChatStatus.OK;
                            this.f18099b.f18101b.m19365f();
                            this.f18099b.f18101b.m19350a(Bucket.INBOX, activeChatsResponse.inbox, arrayList);
                            this.f18099b.f18101b.m19350a(Bucket.OTHER, activeChatsResponse.other, arrayList);
                            this.f18099b.f18101b.m19350a(Bucket.INBOX, activeChatsResponse.inbox, arrayList);
                            this.f18099b.f18101b.m19350a(Bucket.OTHER, activeChatsResponse.other, arrayList);
                        } else {
                            chatStatus = ChatStatus.NETWORK_ERROR;
                        }
                        loadCallback.mo6327a(arrayList, new ArrayList(), true, chatStatus);
                    }
                });
            }
        });
    }

    private void m19350a(Bucket bucket, List<SNPChat> list, ArrayList<Chat> arrayList) {
        for (SNPChat sNPChat : list) {
            Options options = new Options();
            options.f17954b = sNPChat.jid;
            options.f17953a = sNPChat.type.equals("ACCT") ? Type.PEER : Type.GROUP;
            options.f17957e = true;
            options.f17961i = bucket;
            if (options.f17953a == Type.PEER) {
                arrayList.add(new PeerChat(this.f18103a, options));
            } else {
                arrayList.add(new GroupChat(this.f18103a, options));
            }
        }
    }

    private boolean m19364e() {
        return this.f18104b.b().getBoolean("chat.restored", false);
    }

    private void m19365f() {
        Editor edit = this.f18104b.b().edit();
        edit.putBoolean("chat.restored", true);
        edit.apply();
    }
}
