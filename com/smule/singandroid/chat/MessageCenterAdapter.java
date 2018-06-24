/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.os.Looper
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.ChatUtils$GroupStatusCallback
 */
package com.smule.singandroid.chat;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.chat.PeerChat;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.chat.ChatDate;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import com.smule.singandroid.utils.ChatUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MessageCenterAdapter
extends ChatListView.ChatListViewAdapter {
    private final LayoutInflater a;
    private final List<Chat> b = new ArrayList<Chat>();
    private final Resources c;
    private Set<String> d = new HashSet<String>();
    private final Context e;
    private boolean f = false;

    public MessageCenterAdapter(Context context) {
        this.a = LayoutInflater.from((Context)context);
        this.c = context.getResources();
        this.e = context;
    }

    private boolean b(int n) {
        return this.d.contains(this.a(n).c());
    }

    @Override
    public int a() {
        Iterator<Chat> iterator = this.b.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (!iterator.next().m()) continue;
            ++n;
        }
        return n;
    }

    public Chat a(int n) {
        return this.b.get(n);
    }

    public ViewHolder a(ViewGroup viewGroup) {
        return new ViewHolder((View)viewGroup);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void a(final ViewHolder var1_1, int var2_2) {
        block24 : {
            block23 : {
                var8_3 = this.a(var2_2);
                var5_4 = var1_1.b;
                var3_5 = var8_3.d() == Chat.ChatState.a ? 0 : 8;
                var5_4.setVisibility(var3_5);
                var5_4 = var1_1.a;
                var3_5 = var8_3.d() == Chat.ChatState.a ? 0 : 8;
                var5_4.setVisibility(var3_5);
                var5_4 = var1_1.g;
                var3_5 = var8_3.m() != false ? 0 : 4;
                var5_4.setVisibility(var3_5);
                if (var8_3.d() != Chat.ChatState.c) ** GOTO lbl-1000
                var5_4 = com.smule.singandroid.utils.ChatUtils.a(var8_3);
                if (var8_3.e().a() || var5_4.isEmpty()) {
                    var3_5 = 1;
                } else lbl-1000: // 2 sources:
                {
                    var3_5 = 0;
                }
                if (var3_5 != 0) {
                    var5_4 = var1_1.d;
                    var2_2 = var8_3 instanceof PeerChat != false ? 2131296567 : 2131296565;
                    var5_4.setText(var2_2);
                    var1_1.e.setVisibility(0);
                    var1_1.e.setText(2131296566);
                    var1_1.f.setVisibility(8);
                    var1_1.a(false);
                    var1_1.c.setChat(var8_3);
                    return;
                }
                var6_6 = com.smule.singandroid.utils.ChatUtils.a(var8_3);
                var5_4 = var6_6;
                if (var6_6.isEmpty()) {
                    var5_4 = var8_3.d() == Chat.ChatState.a ? this.e.getString(2131296951) : this.e.getString(2131296564);
                }
                var1_1.d.setText((CharSequence)var5_4);
                var1_1.c.a(var8_3, this.e.getResources().getDimensionPixelSize(2131427561));
                if (var8_3.o() || var8_3.b() == Chat.Bucket.b) {
                    var1_1.d.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2130837962, 0);
                } else {
                    var1_1.d.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                if ((var7_7 = var8_3.l()) == null) break block23;
                var5_4 = var8_3.a(var7_7.b());
                var4_8 = com.smule.singandroid.utils.ChatUtils.b(var7_7);
                var6_6 = var4_8 != false ? this.c.getString(2131296734) : (var5_4 != null ? var5_4.handle : this.c.getString(2131296570));
                switch (.a[var7_7.a().ordinal()]) {
                    default: {
                        var5_4 = this.c.getString(2131296597, new Object[]{var6_6});
                        var3_5 = 0;
                        break block24;
                    }
                    case 1: {
                        var5_4 = ((GroupInvitationChatMessage)var7_7).p();
                        if (var5_4 != null && !var5_4.isEmpty()) {
                            if (var4_8) {
                                var5_4 = this.c.getString(2131296596, new Object[]{var6_6, this.c.getString(2131296553, new Object[]{var5_4})});
                                var3_5 = 0;
                                ** break;
                            }
                            var5_4 = this.c.getString(2131296553, new Object[]{var5_4});
                            var3_5 = 0;
                            ** break;
                        }
                        var5_4 = this.c.getString(2131296550);
                        var3_5 = 0;
                        ** break;
                    }
                    case 2: {
                        var5_4 = (TextChatMessage)var7_7;
                        if (var8_3.a() == Chat.Type.a) {
                            if (var4_8) {
                                var5_4 = this.c.getString(2131296596, new Object[]{var6_6, var5_4.o()});
                                var3_5 = 0;
                                ** break;
                            }
                            var5_4 = var5_4.o();
                            var3_5 = 0;
                            ** break;
                        }
                        var5_4 = this.c.getString(2131296595, new Object[]{var6_6, var5_4.o()});
                        var3_5 = 0;
                        ** break;
                    }
                    case 3: {
                        var5_4 = (PerformanceChatMessage)var7_7;
                        var5_4 = var5_4.d() == ChatMessage.State.b && var5_4.o() != null ? (var5_4.o().p() ? this.c.getString(2131296592, new Object[]{var5_4.o().title}) : this.c.getString(2131296593, new Object[]{var5_4.o().title})) : this.c.getString(2131296594);
                        if (var8_3.a() == Chat.Type.a) {
                            if (var4_8) {
                                var5_4 = this.c.getString(2131296596, new Object[]{var6_6, var5_4});
                                var3_5 = 0;
                                ** break;
                            }
                            var3_5 = 0;
                            ** break;
                        }
                        var5_4 = this.c.getString(2131296595, new Object[]{var6_6, var5_4});
                        var3_5 = 0;
                        ** break;
                    }
                    case 4: 
                }
                var5_4 = null;
                com.smule.singandroid.utils.ChatUtils.a(var8_3, (GroupStatusChatMessage)var7_7, new ChatUtils(){

                    public void a(final String string2, GroupStatusChatMessage groupStatusChatMessage) {
                        if (groupStatusChatMessage == var7_7) {
                            new Handler(MessageCenterAdapter.this.e.getMainLooper()).post(new Runnable(){

                                @Override
                                public void run() {
                                    var1_1.e.setVisibility(0);
                                    var1_1.e.setText((CharSequence)string2);
                                    ChatDate chatDate = new ChatDate(var7_7.c());
                                    var1_1.f.setText((CharSequence)chatDate.a());
                                }
                            });
                        }
                    }

                }, this.e);
                var3_5 = 1;
                ** break;
lbl90: // 10 sources:
                break block24;
            }
            var5_4 = null;
            var1_1.e.setVisibility(8);
            var3_5 = 0;
        }
        if (var3_5 == 0) {
            if (var5_4 != null) {
                var1_1.e.setVisibility(0);
                var1_1.e.setText((CharSequence)var5_4);
                var5_4 = new ChatDate(var7_7.c());
                var1_1.f.setVisibility(0);
                var1_1.f.setText((CharSequence)var5_4.a());
            } else {
                var1_1.e.setVisibility(8);
                var1_1.f.setVisibility(8);
            }
        }
        if (this.f) {
            if (this.b(var2_2)) {
                var1_1.a(true);
                var1_1.a();
                return;
            }
            var1_1.a(false);
            return;
        }
        var1_1.a(this.b(var2_2));
    }

    public void a(String string2) {
        this.d.add(string2);
    }

    @Override
    public void a(List<Chat> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
        }
        this.notifyDataSetChanged();
    }

    public void b() {
        this.d.clear();
    }

    public void b(String string2) {
        this.d.remove(string2);
    }

    public void c() {
        this.f = true;
        this.notifyDataSetChanged();
    }

    public void d() {
        if (this.f) {
            this.b();
        }
        this.f = false;
    }

    public int getCount() {
        return this.b.size();
    }

    public /* synthetic */ Object getItem(int n) {
        return this.a(n);
    }

    public long getItemId(int n) {
        if (this.b(n)) {
            return 0;
        }
        return 1;
    }

    public int getItemViewType(int n) {
        return super.getItemViewType(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View object, ViewGroup object2) {
        if (object == null) {
            object2 = this.a.inflate(2130903110, (ViewGroup)object2, false);
            object = this.a((ViewGroup)object2);
            object2.setTag(object);
        } else {
            ViewHolder viewHolder = (ViewHolder)object.getTag();
            object2 = object;
            object = viewHolder;
        }
        object.j = n;
        this.a((ViewHolder)object, n);
        return object2;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public class ViewHolder {
        public final View a;
        public final View b;
        public final ChatMultiplePortraitFlipView c;
        public final TextView d;
        public final TextView e;
        public final TextView f;
        public final ImageView g;
        public final RelativeLayout h;
        public boolean i;
        public int j;

        public ViewHolder(View view) {
            this.i = false;
            this.a = view.findViewById(2131755421);
            this.b = view.findViewById(2131755422);
            this.c = (ChatMultiplePortraitFlipView)view.findViewById(2131755346);
            this.d = (TextView)view.findViewById(2131755418);
            this.e = (TextView)view.findViewById(2131755419);
            this.f = (TextView)view.findViewById(2131755417);
            this.g = (ImageView)view.findViewById(2131755420);
            this.h = (RelativeLayout)view.findViewById(2131755416);
            this.c.a(4, 2, MessageCenterAdapter.this.e.getResources().getDimensionPixelSize(2131428177));
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a() {
            boolean bl = !this.i;
            this.i = bl;
            this.c.a();
            this.h.setActivated(this.i);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(boolean bl) {
            this.i = bl;
            this.h.setActivated(bl);
            ChatMultiplePortraitFlipView chatMultiplePortraitFlipView = this.c;
            bl = !bl;
            chatMultiplePortraitFlipView.setSide(bl);
        }
    }

}

