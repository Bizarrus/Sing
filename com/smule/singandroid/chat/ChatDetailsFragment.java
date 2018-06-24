/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.FragmentManager
 *  android.app.FragmentManager$BackStackEntry
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.BaseAdapter
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.TableLayout
 *  android.widget.TableLayout$LayoutParams
 *  android.widget.TableRow
 *  android.widget.TableRow$LayoutParams
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  com.smule.singandroid.utils.MiscUtils
 *  com.viewpagerindicator.CirclePageIndicator
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.chat.ChatAddMembersFragment;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatDetailsFragment_;
import com.smule.singandroid.chat.EditGroupNameFragment;
import com.smule.singandroid.chat.activator.ChatActivatorFragment;
import com.smule.singandroid.customviews.ExpandableHeightViewPager;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingView;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class ChatDetailsFragment
extends ChatActivatorFragment {
    public static final String g = ChatDetailsFragment.class.getName();
    @ViewById
    protected TextView h;
    @ViewById
    protected ToggleButton i;
    @ViewById
    protected FrameLayout j;
    @ViewById
    protected LinearLayout k;
    @ViewById
    protected LinearLayout l;
    @ViewById
    protected TextView m;
    @ViewById
    protected RelativeLayout n;
    @ViewById
    protected TextView o;
    @ViewById
    protected ExpandableHeightViewPager p;
    @ViewById
    protected CirclePageIndicator q;
    @ViewById
    protected View r;
    private MembersPagerAdapter s;
    private boolean t = false;
    private Map<Integer, TableLayout> u;
    private CompoundButton.OnCheckedChangeListener v;
    private ArrayList<AccountIcon> w;
    private Comparator<AccountIcon> x;

    public ChatDetailsFragment() {
        this.v = new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                ChatDetailsFragment.this.c(bl);
            }
        };
        this.w = new ArrayList();
        this.x = new Comparator<AccountIcon>(){

            public int a(AccountIcon accountIcon, AccountIcon accountIcon2) {
                return accountIcon.handle.toLowerCase().compareTo(accountIcon2.handle.toLowerCase());
            }

            @Override
            public /* synthetic */ int compare(Object object, Object object2) {
                return this.a((AccountIcon)object, (AccountIcon)object2);
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void J() {
        block5 : {
            block4 : {
                if (!this.isAdded()) break block4;
                if (this.M instanceof PeerChat && SingApplication.k().a(this.M.f())) {
                    this.H();
                    return;
                }
                this.K();
                this.L();
                this.a((CharSequence)this.getResources().getString(2131296673));
                if (this.M instanceof GroupChat) break block5;
            }
            return;
        }
        this.o.setText((CharSequence)((GroupChat)this.M).S());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void K() {
        boolean bl = true;
        boolean bl2 = true;
        this.i.setOnCheckedChangeListener(null);
        if (this.M instanceof PeerChat) {
            boolean bl3 = this.M.b() == Chat.Bucket.b;
            this.l.setVisibility(0);
            this.m.setText(2131296486);
            this.n.setVisibility(8);
            if (bl3) {
                this.k.setVisibility(0);
                this.j.setVisibility(8);
                this.h.setTextColor(this.getResources().getColor(2131689680));
                this.i.setChecked(false);
                this.i.setClickable(false);
            } else {
                this.k.setVisibility(8);
                this.j.setVisibility(0);
                this.h.setTextColor(this.getResources().getColor(2131689547));
                this.i.setClickable(true);
                ToggleButton toggleButton = this.i;
                if (this.M.o()) {
                    bl2 = false;
                }
                toggleButton.setChecked(bl2);
            }
        } else if (this.M instanceof GroupChat) {
            this.n.setVisibility(0);
            this.o.setText((CharSequence)((GroupChat)this.M).S());
            this.n.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatDetailsFragment.this.a(EditGroupNameFragment.a((GroupChat)ChatDetailsFragment.this.M, null, null));
                }
            });
            this.k.setVisibility(8);
            this.j.setVisibility(0);
            this.h.setTextColor(this.getResources().getColor(2131689547));
            this.l.setVisibility(8);
            this.m.setText(2131296487);
            this.i.setEnabled(true);
            ToggleButton toggleButton = this.i;
            bl2 = !this.M.o() ? bl : false;
            toggleButton.setChecked(bl2);
        }
        this.i.setOnCheckedChangeListener(this.v);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void L() {
        block11 : {
            if (!this.isAdded()) {
                return;
            }
            this.u = new HashMap<Integer, TableLayout>();
            this.w = new ArrayList<E>();
            if (!(this.M instanceof PeerChat)) break block11;
            this.w.add(this.M.a(this.M.f()));
            ** GOTO lbl-1000
        }
        if (this.M instanceof GroupChat) {
            var2_1 = ChatUtils.a(this.M.h());
            var1_2 = new ArrayList<AccountIcon>();
            var2_1 = var2_1.iterator();
        } else lbl-1000: // 3 sources:
        {
            do {
                this.w.add(0, new AccountIcon());
                if (this.M instanceof GroupChat && this.M.g().size() > 1) {
                    this.w.add(new AccountIcon());
                }
                if (this.w.size() <= 12) {
                    this.q.setVisibility(8);
                } else {
                    this.q.setVisibility(0);
                }
                if (this.s == null) {
                    this.s = new MembersPagerAdapter();
                }
                this.p.setAdapter((PagerAdapter)this.s);
                this.q.setStrokeWidth(0.0f);
                this.q.setViewPager((ViewPager)this.p);
                this.s.notifyDataSetChanged();
                this.p.setOffscreenPageLimit(2);
                return;
                break;
            } while (true);
        }
        while (var2_1.hasNext()) {
            var3_3 = (AccountIcon)var2_1.next();
            var4_4 = (GroupChat)this.M;
            if (var3_3.accountId == SingApplication.k().j()) {
                var2_1.remove();
                continue;
            }
            if (var4_4.b(var3_3.accountId) == GroupMemberStatus.b) {
                var1_2.add(var3_3);
                continue;
            }
            this.w.add(var3_3);
        }
        Collections.sort(this.w, this.x);
        Collections.sort(var1_2, this.x);
        this.w.addAll(var1_2);
        ** while (true)
    }

    /*
     * Enabled aggressive block sorting
     */
    private void M() {
        Iterator<TableLayout> iterator = this.u.values().iterator();
        block0 : while (iterator.hasNext()) {
            TableLayout tableLayout = iterator.next();
            int n = 0;
            do {
                if (n >= tableLayout.getChildCount()) continue block0;
                if (tableLayout.getChildAt(n) instanceof TableRow) {
                    TableRow tableRow = (TableRow)tableLayout.getChildAt(n);
                    for (int i = 0; i < tableRow.getChildCount(); ++i) {
                        Object object = tableRow.getChildAt(i);
                        if (object.getTag() instanceof ChatProfileImageAdapter.ViewHolder) {
                            object = (ChatProfileImageAdapter.ViewHolder)object.getTag();
                            object.c.setClickable(this.t);
                            if (this.t) {
                                object.c.setVisibility(0);
                                continue;
                            }
                            object.c.setVisibility(4);
                            continue;
                        }
                        if (object.findViewById(2131755371) == null) continue;
                        object = (ImageView)object.findViewById(2131755371);
                        if (this.t) {
                            object.setImageResource(2130837656);
                            continue;
                        }
                        object.setImageResource(2130837655);
                    }
                }
                ++n;
            } while (true);
            break;
        }
        return;
    }

    public static ChatDetailsFragment a(Chat chat) {
        ChatDetailsFragment chatDetailsFragment = ChatDetailsFragment_.J().a();
        chatDetailsFragment.e(chat);
        return chatDetailsFragment;
    }

    private void a(AccountIcon accountIcon, int n) {
        Object object = this.getResources().getString(2131296581, new Object[]{accountIcon.handle});
        object = new TextAlertDialog((Context)this.getActivity(), this.getResources().getString(2131296466), (String)object);
        object.a(2131296733, 2131296701);
        object.a(new CustomAlertDialog.CustomAlertDialogListener((TextAlertDialog)((Object)object), accountIcon, n){
            final /* synthetic */ TextAlertDialog a;
            final /* synthetic */ AccountIcon b;
            final /* synthetic */ int c;
            {
                this.a = textAlertDialog;
                this.b = accountIcon;
                this.c = n;
            }

            @Override
            public void a(CustomAlertDialog smuleDialog) {
                this.a.dismiss();
                smuleDialog = new BusyScreenDialog((Context)ChatDetailsFragment.this.getActivity(), 2131296582);
                smuleDialog.show();
                Set<AccountIcon> set = Collections.singleton(this.b);
                ((GroupChat)ChatDetailsFragment.this.M).b(set, new Completion<ChatStatus>((BusyScreenDialog)smuleDialog){
                    final /* synthetic */ BusyScreenDialog a;
                    {
                        this.a = busyScreenDialog;
                    }

                    @Override
                    public void a(ChatStatus chatStatus) {
                        this.a.dismiss();
                        ChatDetailsFragment.this.t = false;
                        if (chatStatus == ChatStatus.a) {
                            ChatDetailsFragment.this.L();
                            ChatDetailsFragment.this.p.setCurrentItem(5.this.c);
                            ChatAnalytics.a((GroupChat)ChatDetailsFragment.this.M, 5.this.b.accountId);
                            return;
                        }
                        ChatUtils.a((Context)ChatDetailsFragment.this.getActivity(), 2131296507, chatStatus);
                    }
                });
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                this.a.dismiss();
            }

        });
        object.show();
    }

    private void a(final BusyScreenDialog busyScreenDialog) {
        SingApplication.k().a(this.M, new Completion<ChatStatus>(){

            @Override
            public void a(ChatStatus chatStatus) {
                if (!ChatDetailsFragment.this.isAdded()) {
                    return;
                }
                busyScreenDialog.dismiss();
                ChatDetailsFragment.this.I();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(final boolean bl) {
        boolean bl2 = true;
        boolean bl3 = this.M.o();
        boolean bl4 = !bl;
        if (bl3 == bl4) {
            return;
        }
        final BusyScreenDialog busyScreenDialog = new BusyScreenDialog((Context)this.getActivity(), 2131296571);
        busyScreenDialog.show();
        Chat chat = this.M;
        bl4 = !bl ? bl2 : false;
        chat.a(bl4, new Completion<ChatStatus>(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(ChatStatus object) {
                if (object != ChatStatus.a && ChatDetailsFragment.this.isAdded()) {
                    ChatDetailsFragment.this.a(new Runnable((ChatStatus)((Object)object)){
                        final /* synthetic */ ChatStatus a;
                        {
                            this.a = chatStatus;
                        }

                        /*
                         * Enabled aggressive block sorting
                         */
                        @Override
                        public void run() {
                            busyScreenDialog.dismiss();
                            ChatDetailsFragment.this.i.setOnCheckedChangeListener(null);
                            ToggleButton toggleButton = ChatDetailsFragment.this.i;
                            boolean bl = !bl;
                            toggleButton.setChecked(bl);
                            ChatDetailsFragment.this.i.setOnCheckedChangeListener(ChatDetailsFragment.this.v);
                            ChatUtils.a((Context)ChatDetailsFragment.this.getActivity(), this.a);
                        }
                    });
                    return;
                }
                busyScreenDialog.dismiss();
                object = ChatDetailsFragment.this;
                int n = !bl ? 2131296492 : 2131296494;
                object.b(n);
                Chat chat = ChatDetailsFragment.this.M;
                object = bl ? ChatAnalytics.SettingToggleType.b : ChatAnalytics.SettingToggleType.a;
                ChatAnalytics.a(chat, (ChatAnalytics.SettingToggleType)object);
            }

        });
    }

    @Click
    protected void F() {
        Object object = this.getResources().getString(2131296469);
        object = new TextAlertDialog((Context)this.getActivity(), this.getResources().getString(2131296466), (String)object);
        object.a(2131296467, 2131296672);
        object.a(new CustomAlertDialog.CustomAlertDialogListener((TextAlertDialog)((Object)object)){
            final /* synthetic */ TextAlertDialog a;
            {
                this.a = textAlertDialog;
            }

            @Override
            public void a(CustomAlertDialog smuleDialog) {
                smuleDialog = new BusyScreenDialog((Context)ChatDetailsFragment.this.getActivity(), ChatDetailsFragment.this.getResources().getString(2131296473));
                smuleDialog.show();
                SingApplication.k().a(ChatDetailsFragment.this.M.f(), true, new Completion<ChatStatus>((BusyScreenDialog)smuleDialog){
                    final /* synthetic */ BusyScreenDialog a;
                    {
                        this.a = busyScreenDialog;
                    }

                    @Override
                    public void a(ChatStatus chatStatus) {
                        if (chatStatus == ChatStatus.a) {
                            ChatDetailsFragment.this.b(2131296472);
                            SingApplication.k().a(ChatDetailsFragment.this.M, new Completion<ChatStatus>(){

                                @Override
                                public void a(ChatStatus chatStatus) {
                                    1.this.a.dismiss();
                                    ChatDetailsFragment.this.I();
                                }
                            });
                            return;
                        }
                        ChatUtils.a((Context)ChatDetailsFragment.this.getActivity(), 2131296496, chatStatus);
                        this.a.dismiss();
                    }

                });
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                this.a.dismiss();
            }

        });
        object.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void G() {
        Object object;
        final boolean bl = true;
        if (this.M instanceof PeerChat) {
            object = this.getResources().getString(2131296563);
        } else if (this.M.g().size() == 1) {
            object = this.getResources().getString(2131296562);
        } else {
            object = this.getResources().getString(2131296561);
            bl = false;
        }
        object = new TextAlertDialog((Context)this.getActivity(), this.getResources().getString(2131296466), (String)object);
        int n = bl ? 2131296677 : 2131296557;
        object.a(n, 2131296672);
        object.a(new CustomAlertDialog.CustomAlertDialogListener((TextAlertDialog)((Object)object)){
            final /* synthetic */ TextAlertDialog b;
            {
                this.b = textAlertDialog;
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(CustomAlertDialog smuleDialog) {
                Activity activity = ChatDetailsFragment.this.getActivity();
                Resources resources = ChatDetailsFragment.this.getResources();
                int n = bl ? 2131296483 : 2131296557;
                BusyScreenDialog busyScreenDialog = new BusyScreenDialog((Context)activity, resources.getString(n));
                busyScreenDialog.show();
                ChatDetailsFragment.this.a(busyScreenDialog);
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                this.b.dismiss();
            }
        });
        object.show();
    }

    protected void H() {
        FragmentManager.BackStackEntry backStackEntry = this.getFragmentManager().getBackStackEntryAt(this.getFragmentManager().getBackStackEntryCount() - 2);
        this.getFragmentManager().popBackStackImmediate(backStackEntry.getName(), 1);
    }

    protected void I() {
        this.H();
    }

    @Override
    public void a(ChatStatus chatStatus) {
        super.a(chatStatus);
        this.H();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(List<AccountIcon> list, final ChatAddMembersFragment.ChatAddMembersCallback chatAddMembersCallback) {
        block5 : {
            block4 : {
                if (list.isEmpty()) break block4;
                AccountIconCache.a().a(list);
                if (this.M instanceof PeerChat) {
                    list.add(this.M.a(this.M.f()));
                    this.a(EditGroupNameFragment.a(null, list, null));
                    return;
                }
                if (this.M instanceof GroupChat) break block5;
            }
            return;
        }
        ((GroupChat)this.M).a(list, new Completion<ChatStatus>(){

            @Override
            public void a(ChatStatus chatStatus) {
                chatAddMembersCallback.a(chatStatus);
            }
        });
    }

    @Override
    public void b(Chat chat) {
        super.b(chat);
        this.r.setVisibility(8);
        this.J();
        ChatAnalytics.d(chat);
    }

    @Override
    public void onStop() {
        super.onStop();
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
    }

    public Chat t() {
        return this.M;
    }

    @Override
    public void w_() {
        super.w_();
        this.r.setVisibility(0);
    }

    @Override
    public String x() {
        return g;
    }

    class ChatProfileImageAdapter
    extends BaseAdapter {
        private Context b;
        private int c;
        private final int d;
        private final int e;
        private final int f;
        private final int g;

        public ChatProfileImageAdapter(Context context, int n) {
            this.d = 0;
            this.e = 1;
            this.f = 2;
            this.g = 3;
            this.b = context;
            this.c = n;
        }

        public int getCount() {
            return Math.min(ChatDetailsFragment.this.w.size() - this.c * 12, 12);
        }

        public Object getItem(int n) {
            return null;
        }

        public long getItemId(int n) {
            return 0;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public int getItemViewType(int n) {
            int n2 = 1;
            if (n == 0 && this.c == 0) {
                return 0;
            }
            if (!(ChatDetailsFragment.this.M instanceof GroupChat)) return 2;
            if (ChatDetailsFragment.this.M.g().size() <= 1) return 2;
            if (n != this.getCount() - 1) return 2;
            n = n2;
            if (this.c == ChatDetailsFragment.this.s.getCount() - 1) return n;
            return 2;
        }

        /*
         * Enabled aggressive block sorting
         */
        public View getView(final int n, View object, ViewGroup object2) {
            Object object3 = object;
            if (object == null) {
                if (this.getItemViewType(n) == 0) {
                    object3 = LayoutInflater.from((Context)this.b).inflate(2130903101, (ViewGroup)object2, false);
                } else if (this.getItemViewType(n) == 2) {
                    object3 = LayoutInflater.from((Context)this.b).inflate(2130903104, (ViewGroup)object2, false);
                } else {
                    if (this.getItemViewType(n) != 1) {
                        return null;
                    }
                    object3 = LayoutInflater.from((Context)this.b).inflate(2130903103, (ViewGroup)object2, false);
                }
            }
            if (this.getItemViewType(n) == 2) {
                object = (ViewHolder)object3.getTag();
                if (object == null) {
                    object = new ViewHolder((View)object3);
                }
                object2 = (AccountIcon)ChatDetailsFragment.this.w.get(this.c * 12 + n);
                object.a.setAccount((AccountIcon)object2);
                if (ChatDetailsFragment.this.M instanceof GroupChat && ((GroupChat)ChatDetailsFragment.this.M).b(object2.accountId) == GroupMemberStatus.b) {
                    object.a.setPending(true);
                    object.a.setVIP(false);
                } else {
                    object.a.setPending(false);
                }
                object.b.setText((CharSequence)object2.handle);
                object.c.setClickable(ChatDetailsFragment.this.t);
                if (ChatDetailsFragment.this.t) {
                    object.c.setVisibility(0);
                } else {
                    object.c.setVisibility(4);
                }
                object.c.setOnClickListener(new View.OnClickListener((AccountIcon)object2){
                    final /* synthetic */ AccountIcon a;
                    {
                        this.a = accountIcon;
                    }

                    public void onClick(View view) {
                        ChatDetailsFragment.this.a(this.a, ChatProfileImageAdapter.this.c);
                    }
                });
                object3.setOnClickListener(new View.OnClickListener((AccountIcon)object2){
                    final /* synthetic */ AccountIcon a;
                    {
                        this.a = accountIcon;
                    }

                    public void onClick(View view) {
                        ChatDetailsFragment.this.a(ProfileFragment.a(this.a));
                    }
                });
                object3.setTag(object);
                return object3;
            }
            if (this.getItemViewType(n) == 0) {
                object = (ImageView)object3.findViewById(2131755354);
                object2 = (TextView)object3.findViewById(2131755355);
                n = new SingServerValues().L();
                if (ChatDetailsFragment.this.M.g().size() == n) {
                    object.setAlpha(0.5f);
                    object2.setAlpha(0.5f);
                } else {
                    object.setAlpha(1.0f);
                    object2.setAlpha(1.0f);
                }
                object3.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        ChatAnalytics.f(ChatDetailsFragment.this.M);
                        if (ChatDetailsFragment.this.M.g().size() == n) {
                            ChatDetailsFragment.this.b(ChatDetailsFragment.this.getString(2131296568, new Object[]{n}));
                            return;
                        }
                        ChatDetailsFragment.this.t = false;
                        ChatDetailsFragment.this.a(ChatAddMembersFragment.a(ChatDetailsFragment.this));
                    }
                });
                return object3;
            }
            if (this.getItemViewType(n) != 1) return object3;
            object = (ImageView)object3.findViewById(2131755371);
            if (ChatDetailsFragment.this.t) {
                object.setImageResource(2130837656);
            } else {
                object.setImageResource(2130837655);
            }
            object3.setOnClickListener(new View.OnClickListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View object) {
                    ChatAnalytics.g(ChatDetailsFragment.this.M);
                    object = ChatDetailsFragment.this;
                    boolean bl = !ChatDetailsFragment.this.t;
                    ((ChatDetailsFragment)object).t = bl;
                    ChatDetailsFragment.this.M();
                }
            });
            return object3;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public class ViewHolder {
            public final ProfileImageWithVIPBadgeAndPendingView a;
            public final TextView b;
            public final RelativeLayout c;

            public ViewHolder(View view) {
                this.a = (ProfileImageWithVIPBadgeAndPendingView)view.findViewById(2131755372);
                this.b = (TextView)view.findViewById(2131755355);
                this.c = (RelativeLayout)view.findViewById(2131755373);
            }
        }

    }

    class MembersPagerAdapter
    extends PagerAdapter {
        MembersPagerAdapter() {
        }

        private void a(TableRow tableRow, int n) {
            View view = new View(tableRow.getContext());
            view.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(0, -2, (float)n));
            tableRow.addView(view);
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
            viewGroup.removeView((View)object);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public int getCount() {
            int n;
            int n2 = ChatDetailsFragment.this.w.size() / 12;
            if (ChatDetailsFragment.this.w.size() % 12 == 0) {
                n = 0;
                do {
                    return n + n2;
                    break;
                } while (true);
            }
            n = 1;
            return n + n2;
        }

        public int getItemPosition(Object object) {
            return -2;
        }

        public Object instantiateItem(ViewGroup viewGroup, int n) {
            ChatProfileImageAdapter chatProfileImageAdapter = new ChatProfileImageAdapter((Context)ChatDetailsFragment.this.getActivity(), n);
            TableLayout tableLayout = new TableLayout(viewGroup.getContext());
            tableLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            tableLayout.setStretchAllColumns(true);
            int n2 = 0;
            block0 : for (int i = 0; i < 3 && n2 == 0; ++i) {
                TableRow tableRow = new TableRow(viewGroup.getContext());
                tableRow.setLayoutParams((ViewGroup.LayoutParams)new TableLayout.LayoutParams(-1, -2));
                tableRow.setWeightSum(4.0f);
                tableLayout.addView((View)tableRow);
                if (ChatDetailsFragment.this.M instanceof PeerChat) {
                    this.a(tableRow, 1);
                }
                int n3 = 0;
                do {
                    block8 : {
                        int n4;
                        block7 : {
                            n4 = n2;
                            if (n3 >= 4) break block7;
                            n4 = i * 4 + n3;
                            View view = chatProfileImageAdapter.getView(n4, null, (ViewGroup)tableRow);
                            view.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(0, -2, 1.0f));
                            tableRow.addView(view);
                            if (n4 != chatProfileImageAdapter.getCount() - 1) break block8;
                            n4 = 1;
                        }
                        if (ChatDetailsFragment.this.M instanceof PeerChat) {
                            this.a(tableRow, 1);
                        }
                        n2 = n4;
                        continue block0;
                    }
                    ++n3;
                } while (true);
            }
            viewGroup.addView((View)tableLayout);
            ChatDetailsFragment.this.u.put(n, tableLayout);
            return tableLayout;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }
    }

}

