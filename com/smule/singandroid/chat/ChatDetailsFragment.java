package com.smule.singandroid.chat;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.chat.ChatAnalytics.SettingToggleType;
import com.smule.singandroid.chat.activator.ChatActivatorFragment;
import com.smule.singandroid.customviews.ExpandableHeightViewPager;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingView;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
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
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class ChatDetailsFragment extends ChatActivatorFragment {
    public static final String f20829e = ChatDetailsFragment.class.getName();
    @ViewById
    protected TextView f20830f;
    @ViewById
    protected ToggleButton f20831g;
    @ViewById
    protected FrameLayout f20832h;
    @ViewById
    protected LinearLayout f20833i;
    @ViewById
    protected LinearLayout f20834j;
    @ViewById
    protected TextView f20835k;
    @ViewById
    protected RelativeLayout f20836l;
    @ViewById
    protected TextView f20837m;
    @ViewById
    protected ExpandableHeightViewPager f20838n;
    @ViewById
    protected CirclePageIndicator f20839o;
    @ViewById
    protected View f20840p;
    private MembersPagerAdapter f20841q;
    private boolean f20842r = false;
    private Map<Integer, TableLayout> f20843s;
    private OnCheckedChangeListener f20844t = new C42451(this);
    private ArrayList<AccountIcon> f20845u = new ArrayList();
    private Comparator<AccountIcon> f20846v = new C42483(this);

    class C42451 implements OnCheckedChangeListener {
        final /* synthetic */ ChatDetailsFragment f20784a;

        C42451(ChatDetailsFragment chatDetailsFragment) {
            this.f20784a = chatDetailsFragment;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f20784a.m22476b(z);
        }
    }

    class C42483 implements Comparator<AccountIcon> {
        final /* synthetic */ ChatDetailsFragment f20790a;

        C42483(ChatDetailsFragment chatDetailsFragment) {
            this.f20790a = chatDetailsFragment;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m22444a((AccountIcon) obj, (AccountIcon) obj2);
        }

        public int m22444a(AccountIcon accountIcon, AccountIcon accountIcon2) {
            return accountIcon.handle.toLowerCase().compareTo(accountIcon2.handle.toLowerCase());
        }
    }

    class C42526 implements OnClickListener {
        final /* synthetic */ ChatDetailsFragment f20799a;

        C42526(ChatDetailsFragment chatDetailsFragment) {
            this.f20799a = chatDetailsFragment;
        }

        public void onClick(View view) {
            this.f20799a.mo6487a(EditGroupNameFragment.m22708a((GroupChat) this.f20799a.K, null, null));
        }
    }

    class ChatProfileImageAdapter extends BaseAdapter {
        final /* synthetic */ ChatDetailsFragment f20821a;
        private Context f20822b;
        private int f20823c;
        private final int f20824d = 0;
        private final int f20825e = 1;
        private final int f20826f = 2;
        private final int f20827g = 3;

        class C42614 implements OnClickListener {
            final /* synthetic */ ChatProfileImageAdapter f20816a;

            C42614(ChatProfileImageAdapter chatProfileImageAdapter) {
                this.f20816a = chatProfileImageAdapter;
            }

            public void onClick(View view) {
                ChatAnalytics.m22403g(this.f20816a.f20821a.K);
                this.f20816a.f20821a.f20842r = !this.f20816a.f20821a.f20842r;
                this.f20816a.f20821a.m22466H();
            }
        }

        public class ViewHolder {
            public final ProfileImageWithVIPBadgeAndPendingView f20817a;
            public final TextView f20818b;
            public final RelativeLayout f20819c;
            final /* synthetic */ ChatProfileImageAdapter f20820d;

            public ViewHolder(ChatProfileImageAdapter chatProfileImageAdapter, View view) {
                this.f20820d = chatProfileImageAdapter;
                this.f20817a = (ProfileImageWithVIPBadgeAndPendingView) view.findViewById(C1947R.id.chat_profile);
                this.f20818b = (TextView) view.findViewById(C1947R.id.handle_text);
                this.f20819c = (RelativeLayout) view.findViewById(C1947R.id.remove_button);
            }
        }

        public ChatProfileImageAdapter(ChatDetailsFragment chatDetailsFragment, Context context, int i) {
            this.f20821a = chatDetailsFragment;
            this.f20822b = context;
            this.f20823c = i;
        }

        public int getCount() {
            return Math.min(this.f20821a.f20845u.size() - (this.f20823c * 12), 12);
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                if (getItemViewType(i) == 0) {
                    view = LayoutInflater.from(this.f20822b).inflate(C1947R.layout.chat_details_add_user_grid_item, viewGroup, false);
                } else if (getItemViewType(i) == 2) {
                    view = LayoutInflater.from(this.f20822b).inflate(C1947R.layout.chat_details_user_profile_grid_item, viewGroup, false);
                } else if (getItemViewType(i) != 1) {
                    return null;
                } else {
                    view = LayoutInflater.from(this.f20822b).inflate(C1947R.layout.chat_details_remove_user_grid_item, viewGroup, false);
                }
            }
            if (getItemViewType(i) == 2) {
                ViewHolder viewHolder;
                ViewHolder viewHolder2 = (ViewHolder) view.getTag();
                if (viewHolder2 == null) {
                    viewHolder = new ViewHolder(this, view);
                } else {
                    viewHolder = viewHolder2;
                }
                final AccountIcon accountIcon = (AccountIcon) this.f20821a.f20845u.get((this.f20823c * 12) + i);
                viewHolder.f20817a.setAccount(accountIcon);
                if ((this.f20821a.K instanceof GroupChat) && ((GroupChat) this.f20821a.K).m19541b(accountIcon.accountId) == GroupMemberStatus.PENDING) {
                    viewHolder.f20817a.setPending(true);
                    viewHolder.f20817a.setVIP(false);
                } else {
                    viewHolder.f20817a.setPending(false);
                }
                viewHolder.f20818b.setText(accountIcon.handle);
                viewHolder.f20819c.setClickable(this.f20821a.f20842r);
                if (this.f20821a.f20842r) {
                    viewHolder.f20819c.setVisibility(0);
                } else {
                    viewHolder.f20819c.setVisibility(4);
                }
                viewHolder.f20819c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ChatProfileImageAdapter f20811b;

                    public void onClick(View view) {
                        this.f20811b.f20821a.m22468a(accountIcon, this.f20811b.f20823c);
                    }
                });
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ChatProfileImageAdapter f20813b;

                    public void onClick(View view) {
                        this.f20813b.f20821a.mo6487a(ProfileFragment.m21036a(accountIcon));
                    }
                });
                view.setTag(viewHolder);
                return view;
            } else if (getItemViewType(i) == 0) {
                r0 = (ImageView) view.findViewById(C1947R.id.chat_add);
                TextView textView = (TextView) view.findViewById(C1947R.id.handle_text);
                final int G = SingServerValues.m21669G();
                if (this.f20821a.K.mo6342g().size() == G) {
                    r0.setAlpha(0.5f);
                    textView.setAlpha(0.5f);
                } else {
                    r0.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                    textView.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ChatProfileImageAdapter f20815b;

                    public void onClick(View view) {
                        ChatAnalytics.m22402f(this.f20815b.f20821a.K);
                        if (this.f20815b.f20821a.K.mo6342g().size() == G) {
                            this.f20815b.f20821a.m19849b(this.f20815b.f20821a.getString(C1947R.string.chat_max_members_selected, new Object[]{Integer.valueOf(G)}));
                            return;
                        }
                        this.f20815b.f20821a.f20842r = false;
                        this.f20815b.f20821a.mo6487a(ChatAddMembersFragment.m22353a(this.f20815b.f20821a));
                    }
                });
                return view;
            } else if (getItemViewType(i) != 1) {
                return view;
            } else {
                r0 = (ImageView) view.findViewById(C1947R.id.chat_remove);
                if (this.f20821a.f20842r) {
                    r0.setImageResource(C1947R.drawable.btn_remove_circle_on);
                } else {
                    r0.setImageResource(C1947R.drawable.btn_remove_circle);
                }
                view.setOnClickListener(new C42614(this));
                return view;
            }
        }

        public int getItemViewType(int i) {
            if (i == 0 && this.f20823c == 0) {
                return 0;
            }
            if ((this.f20821a.K instanceof GroupChat) && this.f20821a.K.mo6342g().size() > 1 && i == getCount() - 1 && this.f20823c == this.f20821a.f20841q.getCount() - 1) {
                return 1;
            }
            return 2;
        }

        public int getViewTypeCount() {
            return 3;
        }
    }

    class MembersPagerAdapter extends PagerAdapter {
        final /* synthetic */ ChatDetailsFragment f20828a;

        MembersPagerAdapter(ChatDetailsFragment chatDetailsFragment) {
            this.f20828a = chatDetailsFragment;
        }

        public int getCount() {
            return (this.f20828a.f20845u.size() % 12 == 0 ? 0 : 1) + (this.f20828a.f20845u.size() / 12);
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ChatProfileImageAdapter chatProfileImageAdapter = new ChatProfileImageAdapter(this.f20828a, this.f20828a.getActivity(), i);
            View tableLayout = new TableLayout(viewGroup.getContext());
            tableLayout.setLayoutParams(new LayoutParams(-1, -2));
            tableLayout.setStretchAllColumns(true);
            Object obj = null;
            for (int i2 = 0; i2 < 3 && r0 == null; i2++) {
                View tableRow = new TableRow(viewGroup.getContext());
                tableRow.setLayoutParams(new TableLayout.LayoutParams(-1, -2));
                tableRow.setWeightSum(4.0f);
                tableLayout.addView(tableRow);
                if (this.f20828a.K instanceof PeerChat) {
                    m22462a(tableRow, 1);
                }
                for (int i3 = 0; i3 < 4; i3++) {
                    int i4 = (i2 * 4) + i3;
                    View view = chatProfileImageAdapter.getView(i4, null, tableRow);
                    view.setLayoutParams(new TableRow.LayoutParams(0, -2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    tableRow.addView(view);
                    if (i4 == chatProfileImageAdapter.getCount() - 1) {
                        obj = 1;
                        break;
                    }
                }
                if (this.f20828a.K instanceof PeerChat) {
                    m22462a(tableRow, 1);
                }
            }
            viewGroup.addView(tableLayout);
            this.f20828a.f20843s.put(Integer.valueOf(i), tableLayout);
            return tableLayout;
        }

        private void m22462a(TableRow tableRow, int i) {
            View view = new View(tableRow.getContext());
            view.setLayoutParams(new TableRow.LayoutParams(0, -2, (float) i));
            tableRow.addView(view);
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    private void m22476b(final boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean o = this.K.m19227o();
        if (z) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (o != z2) {
            final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(getActivity(), (int) C1947R.string.chat_mute_busy_message);
            busyScreenDialog.show();
            Chat chat = this.K;
            if (z) {
                z3 = false;
            }
            chat.m19203a(z3, new Completion<ChatStatus>(this) {
                final /* synthetic */ ChatDetailsFragment f20789c;

                public void m22442a(final ChatStatus chatStatus) {
                    if (chatStatus == ChatStatus.OK || !this.f20789c.isAdded()) {
                        busyScreenDialog.dismiss();
                        this.f20789c.m19846b(!z ? C1947R.string.chat_disabled_notifications_setting : C1947R.string.chat_enabled_notifications_setting);
                        ChatAnalytics.m22382a(this.f20789c.K, z ? SettingToggleType.OFF : SettingToggleType.ON);
                        return;
                    }
                    this.f20789c.m19838a(new Runnable(this) {
                        final /* synthetic */ C42472 f20786b;

                        public void run() {
                            busyScreenDialog.dismiss();
                            this.f20786b.f20789c.f20831g.setOnCheckedChangeListener(null);
                            this.f20786b.f20789c.f20831g.setChecked(!z);
                            this.f20786b.f20789c.f20831g.setOnCheckedChangeListener(this.f20786b.f20789c.f20844t);
                            ChatUtils.a(this.f20786b.f20789c.getActivity(), chatStatus);
                        }
                    });
                }
            });
        }
    }

    public String mo6383s() {
        return f20829e;
    }

    public static ChatDetailsFragment m22475b(Chat chat) {
        ChatDetailsFragment a = ChatDetailsFragment_.m22508E().m22507a();
        a.m22333d(chat);
        return a;
    }

    public void onStop() {
        super.onStop();
        MiscUtils.m25891a(getActivity(), true);
    }

    public void s_() {
        super.s_();
        this.f20840p.setVisibility(0);
    }

    public void a_(Chat chat) {
        super.a_(chat);
        this.f20840p.setVisibility(8);
        m22463E();
        ChatAnalytics.m22400d(chat);
    }

    public void mo6551a(ChatStatus chatStatus) {
        super.mo6551a(chatStatus);
        m22501C();
    }

    public void m22504a(List<AccountIcon> list, final ChatAddMembersCallback chatAddMembersCallback) {
        if (!list.isEmpty()) {
            AccountIconCache.m19106a().m19110a((Collection) list);
            if (this.K instanceof PeerChat) {
                list.add(this.K.m19186a(this.K.mo6341f()));
                mo6487a(EditGroupNameFragment.m22708a(null, (List) list, null));
            } else if (this.K instanceof GroupChat) {
                ((GroupChat) this.K).m19538a((Collection) list, new Completion<ChatStatus>(this) {
                    final /* synthetic */ ChatDetailsFragment f20792b;

                    public void m22445a(ChatStatus chatStatus) {
                        chatAddMembersCallback.mo6727a(chatStatus);
                    }
                });
            }
        }
    }

    public Chat m22506z() {
        return this.K;
    }

    private void m22468a(final AccountIcon accountIcon, final int i) {
        final TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getResources().getString(C1947R.string.chat_are_you_sure), getResources().getString(C1947R.string.chat_remove_user_subtitle, new Object[]{accountIcon.handle}));
        textAlertDialog.m19800a((int) C1947R.string.core_yes, (int) C1947R.string.core_no);
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ ChatDetailsFragment f20798d;

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                textAlertDialog.dismiss();
                final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(this.f20798d.getActivity(), (int) C1947R.string.chat_removing_user_busy_message);
                busyScreenDialog.show();
                ((GroupChat) this.f20798d.K).m19543b(Collections.singleton(accountIcon), new Completion<ChatStatus>(this) {
                    final /* synthetic */ C42515 f20794b;

                    public void m22447a(ChatStatus chatStatus) {
                        busyScreenDialog.dismiss();
                        this.f20794b.f20798d.f20842r = false;
                        if (chatStatus == ChatStatus.OK) {
                            this.f20794b.f20798d.m22465G();
                            this.f20794b.f20798d.f20838n.setCurrentItem(i);
                            ChatAnalytics.m22385a((GroupChat) this.f20794b.f20798d.K, accountIcon.accountId);
                            return;
                        }
                        ChatUtils.a(this.f20794b.f20798d.getActivity(), C1947R.string.chat_error_remove_member, chatStatus);
                    }
                });
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                textAlertDialog.dismiss();
            }
        });
        textAlertDialog.show();
    }

    private void m22463E() {
        if (!isAdded()) {
            return;
        }
        if ((this.K instanceof PeerChat) && SingApplication.j().a(this.K.mo6341f())) {
            m22501C();
            return;
        }
        m22464F();
        m22465G();
        mo6861a(getResources().getString(C1947R.string.core_chat_details));
        if (this.K instanceof GroupChat) {
            this.f20837m.setText(((GroupChat) this.K).mo6334S());
        }
    }

    private void m22464F() {
        boolean z = true;
        this.f20831g.setOnCheckedChangeListener(null);
        ToggleButton toggleButton;
        if (this.K instanceof PeerChat) {
            boolean z2 = this.K.mo6347b() == Bucket.OTHER;
            this.f20834j.setVisibility(0);
            this.f20835k.setText(C1947R.string.chat_details_allow_delete_title);
            this.f20836l.setVisibility(8);
            if (z2) {
                this.f20833i.setVisibility(0);
                this.f20832h.setVisibility(8);
                this.f20830f.setTextColor(getResources().getColor(C1947R.color.contextual_text));
                this.f20831g.setChecked(false);
                this.f20831g.setClickable(false);
            } else {
                this.f20833i.setVisibility(8);
                this.f20832h.setVisibility(0);
                this.f20830f.setTextColor(getResources().getColor(C1947R.color.body_text_grey));
                this.f20831g.setClickable(true);
                toggleButton = this.f20831g;
                if (this.K.m19227o()) {
                    z = false;
                }
                toggleButton.setChecked(z);
            }
        } else if (this.K instanceof GroupChat) {
            this.f20836l.setVisibility(0);
            this.f20837m.setText(((GroupChat) this.K).mo6334S());
            this.f20836l.setOnClickListener(new C42526(this));
            this.f20833i.setVisibility(8);
            this.f20832h.setVisibility(0);
            this.f20830f.setTextColor(getResources().getColor(C1947R.color.body_text_grey));
            this.f20834j.setVisibility(8);
            this.f20835k.setText(C1947R.string.chat_details_allow_leave_title);
            this.f20831g.setEnabled(true);
            toggleButton = this.f20831g;
            if (this.K.m19227o()) {
                z = false;
            }
            toggleButton.setChecked(z);
        }
        this.f20831g.setOnCheckedChangeListener(this.f20844t);
    }

    private void m22465G() {
        if (isAdded()) {
            this.f20843s = new HashMap();
            this.f20845u = new ArrayList();
            if (this.K instanceof PeerChat) {
                this.f20845u.add(this.K.m19186a(this.K.mo6341f()));
            } else if (this.K instanceof GroupChat) {
                List a = ChatUtils.a(this.K.m19220h());
                Collection arrayList = new ArrayList();
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    AccountIcon accountIcon = (AccountIcon) it.next();
                    GroupChat groupChat = (GroupChat) this.K;
                    if (accountIcon.accountId == SingApplication.j().j()) {
                        it.remove();
                    } else if (groupChat.m19541b(accountIcon.accountId) == GroupMemberStatus.PENDING) {
                        arrayList.add(accountIcon);
                    } else {
                        this.f20845u.add(accountIcon);
                    }
                }
                Collections.sort(this.f20845u, this.f20846v);
                Collections.sort(arrayList, this.f20846v);
                this.f20845u.addAll(arrayList);
            }
            this.f20845u.add(0, new AccountIcon());
            if ((this.K instanceof GroupChat) && this.K.mo6342g().size() > 1) {
                this.f20845u.add(new AccountIcon());
            }
            if (this.f20845u.size() <= 12) {
                this.f20839o.setVisibility(8);
            } else {
                this.f20839o.setVisibility(0);
            }
            if (this.f20841q == null) {
                this.f20841q = new MembersPagerAdapter(this);
            }
            this.f20838n.setAdapter(this.f20841q);
            this.f20839o.setStrokeWidth(0.0f);
            this.f20839o.setViewPager(this.f20838n);
            this.f20841q.notifyDataSetChanged();
            this.f20838n.setOffscreenPageLimit(2);
        }
    }

    @Click
    protected void m22499A() {
        final TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getResources().getString(C1947R.string.chat_are_you_sure), getResources().getString(C1947R.string.chat_block_subtitle));
        textAlertDialog.m19800a((int) C1947R.string.chat_block, (int) C1947R.string.core_cancel);
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ ChatDetailsFragment f20804b;

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(this.f20804b.getActivity(), this.f20804b.getResources().getString(C1947R.string.chat_blocking_busy_message));
                busyScreenDialog.show();
                SingApplication.j().a(this.f20804b.K.mo6341f(), true, new Completion<ChatStatus>(this) {
                    final /* synthetic */ C42557 f20802b;

                    class C42531 implements Completion<ChatStatus> {
                        final /* synthetic */ C42541 f20800a;

                        C42531(C42541 c42541) {
                            this.f20800a = c42541;
                        }

                        public void m22451a(ChatStatus chatStatus) {
                            busyScreenDialog.dismiss();
                            this.f20800a.f20802b.f20804b.m22502D();
                        }
                    }

                    public void m22453a(ChatStatus chatStatus) {
                        if (chatStatus == ChatStatus.OK) {
                            this.f20802b.f20804b.m19846b((int) C1947R.string.chat_blocked_user);
                            SingApplication.j().a(this.f20802b.f20804b.K, new C42531(this));
                            return;
                        }
                        ChatUtils.a(this.f20802b.f20804b.getActivity(), C1947R.string.chat_error_block, chatStatus);
                        busyScreenDialog.dismiss();
                    }
                });
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                textAlertDialog.dismiss();
            }
        });
        textAlertDialog.show();
    }

    @Click
    protected void m22500B() {
        String string;
        boolean z = true;
        if (this.K instanceof PeerChat) {
            string = getResources().getString(C1947R.string.chat_leave_peer);
        } else if (this.K.mo6342g().size() == 1) {
            string = getResources().getString(C1947R.string.chat_leave_group_subtitle_last);
        } else {
            string = getResources().getString(C1947R.string.chat_leave_group_subtitle);
            z = false;
        }
        final TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getResources().getString(C1947R.string.chat_are_you_sure), string);
        textAlertDialog.m19800a(z ? C1947R.string.core_delete : C1947R.string.chat_leave, (int) C1947R.string.core_cancel);
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ ChatDetailsFragment f20807c;

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                BusyScreenDialog busyScreenDialog = new BusyScreenDialog(this.f20807c.getActivity(), this.f20807c.getResources().getString(z ? C1947R.string.chat_deleting_busy_message : C1947R.string.chat_leave));
                busyScreenDialog.show();
                this.f20807c.m22473a(busyScreenDialog);
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                textAlertDialog.dismiss();
            }
        });
        textAlertDialog.show();
    }

    private void m22473a(final BusyScreenDialog busyScreenDialog) {
        SingApplication.j().a(this.K, new Completion<ChatStatus>(this) {
            final /* synthetic */ ChatDetailsFragment f20809b;

            public void m22459a(ChatStatus chatStatus) {
                if (this.f20809b.isAdded()) {
                    busyScreenDialog.dismiss();
                    this.f20809b.m22502D();
                }
            }
        });
    }

    protected void m22501C() {
        getFragmentManager().popBackStackImmediate(getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 2).getName(), 1);
    }

    protected void m22502D() {
        m22501C();
    }

    private void m22466H() {
        for (TableLayout tableLayout : this.f20843s.values()) {
            for (int i = 0; i < tableLayout.getChildCount(); i++) {
                if (tableLayout.getChildAt(i) instanceof TableRow) {
                    TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                    for (int i2 = 0; i2 < tableRow.getChildCount(); i2++) {
                        View childAt = tableRow.getChildAt(i2);
                        if (childAt.getTag() instanceof ViewHolder) {
                            ViewHolder viewHolder = (ViewHolder) childAt.getTag();
                            viewHolder.f20819c.setClickable(this.f20842r);
                            if (this.f20842r) {
                                viewHolder.f20819c.setVisibility(0);
                            } else {
                                viewHolder.f20819c.setVisibility(4);
                            }
                        } else if (childAt.findViewById(C1947R.id.chat_remove) != null) {
                            ImageView imageView = (ImageView) childAt.findViewById(C1947R.id.chat_remove);
                            if (this.f20842r) {
                                imageView.setImageResource(C1947R.drawable.btn_remove_circle_on);
                            } else {
                                imageView.setImageResource(C1947R.drawable.btn_remove_circle);
                            }
                        }
                    }
                }
            }
        }
    }
}
