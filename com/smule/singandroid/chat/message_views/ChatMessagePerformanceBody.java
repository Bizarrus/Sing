package com.smule.singandroid.chat.message_views;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.PerformanceChatMessage;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem.ChatMessageBodyViewInterface;
import com.smule.singandroid.customviews.PlayableItemDetailsView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.textviews.EllipsizingTextView;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessagePerformanceBody extends PlayableItemDetailsView implements ChatMessageBodyViewInterface, MediaPlayingViewInterface, PerformanceItemInterface {
    public static final String f21386a = ChatMessagePerformanceBody.class.getName();
    @ViewById
    protected View f21387b;
    @ViewById
    protected ProfileImageWithVIPBadge f21388c;
    @ViewById
    protected EllipsizingTextView f21389d;
    @ViewById
    protected View f21390e;
    @ViewById
    protected View f21391f;
    @ViewById
    protected ViewGroup f21392g;
    @ViewById
    protected Button f21393h;
    @ViewById
    protected View f21394i;
    @ViewById
    protected TextView f21395j;
    @ViewById
    protected TextView f21396k;
    @ViewById
    protected View f21397l;
    @ViewById
    protected ProgressBar f21398m;
    protected PerformanceChatMessage f21399n;
    protected Chat f21400o;
    protected PerformanceItemListener f21401p;
    private LocalizedShortNumberFormatter f21402w;
    private OnClickListener f21403x = new C43581(this);

    class C43581 implements OnClickListener {
        final /* synthetic */ ChatMessagePerformanceBody f21365a;

        C43581(ChatMessagePerformanceBody chatMessagePerformanceBody) {
            this.f21365a = chatMessagePerformanceBody;
        }

        public void onClick(View view) {
            if (this.f21365a.f21400o != null) {
                this.f21365a.f21400o.m19206b(this.f21365a.f21399n);
            }
        }
    }

    class C43592 implements OnClickListener {
        final /* synthetic */ ChatMessagePerformanceBody f21366a;

        C43592(ChatMessagePerformanceBody chatMessagePerformanceBody) {
            this.f21366a = chatMessagePerformanceBody;
        }

        public void onClick(View view) {
            this.f21366a.f21401p.mo6473b(this.f21366a, this.f21366a.v);
        }
    }

    class C43603 implements OnClickListener {
        final /* synthetic */ ChatMessagePerformanceBody f21367a;

        C43603(ChatMessagePerformanceBody chatMessagePerformanceBody) {
            this.f21367a = chatMessagePerformanceBody;
        }

        public void onClick(View view) {
            this.f21367a.f21401p.mo6472a(this.f21367a, this.f21367a.v);
        }
    }

    class C43614 implements OnLongClickListener {
        final /* synthetic */ ChatMessagePerformanceBody f21368a;

        C43614(ChatMessagePerformanceBody chatMessagePerformanceBody) {
            this.f21368a = chatMessagePerformanceBody;
        }

        public boolean onLongClick(View view) {
            this.f21368a.m23058h();
            return true;
        }
    }

    class C43625 implements OnClickListener {
        final /* synthetic */ ChatMessagePerformanceBody f21369a;

        C43625(ChatMessagePerformanceBody chatMessagePerformanceBody) {
            this.f21369a = chatMessagePerformanceBody;
        }

        public void onClick(View view) {
            this.f21369a.f21401p.mo6471a(this.f21369a, this.f21369a.v.accountIcon);
        }
    }

    class C43636 implements OnClickListener {
        final /* synthetic */ ChatMessagePerformanceBody f21370a;

        C43636(ChatMessagePerformanceBody chatMessagePerformanceBody) {
            this.f21370a = chatMessagePerformanceBody;
        }

        public void onClick(View view) {
            this.f21370a.f21401p.mo6471a(this.f21370a, this.f21370a.v.accountIcon);
        }
    }

    class C43647 implements OnClickListener {
        final /* synthetic */ ChatMessagePerformanceBody f21371a;

        C43647(ChatMessagePerformanceBody chatMessagePerformanceBody) {
            this.f21371a = chatMessagePerformanceBody;
        }

        public void onClick(View view) {
            Log.b("odietest", "defaultOnClickListener");
        }
    }

    private enum PerformanceErrorType {
        PERFORMANCE_DELETE,
        PERFORMANCE_FAIL_TO_LOAD
    }

    public ChatMessagePerformanceBody(Context context) {
        super(context);
    }

    public ChatMessagePerformanceBody(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChatMessagePerformanceBody(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m23060a(ChatMessage chatMessage, boolean z, Chat chat) {
        if (this.f21401p == null) {
            throw new RuntimeException("Make sure to call ChatMessagePerformanceBody.init first");
        }
        this.f21399n = (PerformanceChatMessage) chatMessage;
        this.f21400o = chat;
        if (this.f21399n.m19662p()) {
            m23051a(PerformanceErrorType.PERFORMANCE_DELETE);
        } else if (this.f21399n.m19387d() == State.READY && this.f21399n.m19661o() != null) {
            setPerformance(this.f21399n.m19661o());
        } else if (this.f21399n.m19387d() == State.ERROR) {
            m23051a(PerformanceErrorType.PERFORMANCE_FAIL_TO_LOAD);
        } else {
            m23056f();
        }
    }

    private void m23056f() {
        this.f21396k.setVisibility(8);
        this.f21398m.setVisibility(0);
        m23057g();
        setOnClickListener(false);
    }

    private void m23051a(PerformanceErrorType performanceErrorType) {
        this.f21396k.setVisibility(0);
        this.f21398m.setVisibility(8);
        m23057g();
        switch (performanceErrorType) {
            case PERFORMANCE_DELETE:
                this.f21396k.setText(C1947R.string.chat_performance_deleted);
                setOnClickListener(false);
                return;
            default:
                this.f21396k.setText(C1947R.string.chat_performance_error);
                setOnClickListener(this.f21403x);
                return;
        }
    }

    private void m23057g() {
        this.f21387b.setVisibility(8);
        this.f21390e.setVisibility(8);
        this.f21397l.setVisibility(4);
        this.f21392g.setVisibility(8);
    }

    @AfterViews
    protected void m23061c() {
        this.f21389d.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setOnClickListener(boolean z) {
        if (z) {
            setOnAlbumArtClickListener(new C43592(this));
            setOnClickListener(new C43603(this));
            setOnLongClickListener(new C43614(this));
            return;
        }
        setOnClickListener(null);
    }

    public void mo6775a(PerformanceV2 performanceV2, boolean z) {
        setPerformance(performanceV2);
    }

    public void setPerformance(PerformanceV2 performanceV2) {
        super.mo6775a(performanceV2, false);
        if (performanceV2.n()) {
            this.u.setText(getResources().getString(C1947R.string.chat_title_active_seed, new Object[]{MiscUtils.m25886a(this.v.expireAt, false, false)}));
        } else if (performanceV2.d() && performanceV2.seed) {
            TextView textView = this.u;
            Resources resources = getResources();
            Object[] objArr = new Object[1];
            objArr[0] = getResources().getQuantityString(C1947R.plurals.time_days, 0, new Object[]{Integer.valueOf(0)});
            textView.setText(resources.getString(C1947R.string.chat_title_active_seed, objArr));
        } else if (performanceV2.d() && !performanceV2.recentTracks.isEmpty()) {
            this.u.setText(PerformanceV2Util.m25933a(getResources(), this.v, true));
        } else if (performanceV2.e()) {
            this.u.setText(PerformanceV2Util.m25933a(getResources(), this.v, true));
        } else {
            this.u.setText(getResources().getString(C1947R.string.chat_title_solo_cover, new Object[]{performanceV2.accountIcon.handle}));
        }
        m23062d();
        m23063e();
        setOnClickListener(true);
    }

    public PerformanceV2 getPerformance() {
        return this.v;
    }

    protected void m23062d() {
        this.f21397l.setVisibility(0);
        this.f21396k.setVisibility(8);
        this.f21398m.setVisibility(8);
        int i = this.v.accountIcon.accountId == this.f21399n.m19385b() ? 1 : 0;
        if (this.v.n() || (this.v.d() && this.v.seed)) {
            String string;
            if (i != 0) {
                if (this.v.message != null && !this.v.message.isEmpty()) {
                    string = getResources().getString(C1947R.string.chat_performance_title_from_sender);
                } else if (this.v.n()) {
                    string = getResources().getString(C1947R.string.chat_performance_from_sender_no_blurb);
                } else {
                    this.f21387b.setVisibility(8);
                    this.f21390e.setVisibility(8);
                    return;
                }
                this.f21388c.setVisibility(8);
            } else {
                if (this.v.message == null || this.v.message.isEmpty()) {
                    string = "%1$s";
                } else {
                    string = getResources().getString(C1947R.string.chat_performance_title_from_other);
                }
                this.f21388c.setVisibility(0);
                this.f21388c.m23395a(this.v.accountIcon, new C43625(this));
            }
            this.f21387b.setVisibility(0);
            this.f21390e.setVisibility(0);
            if (string.isEmpty()) {
                this.f21389d.setText("");
                return;
            }
            OnClickListener c43636 = new C43636(this);
            OnClickListener c43647 = new C43647(this);
            Object customTypefaceSpan = new CustomTypefaceSpan(getContext(), this.f21389d.getTextSize(), C1947R.color.body_text_grey, TypefaceUtils.m26195c(getContext()));
            StyleReplacer styleReplacer = new StyleReplacer(string, this.f21389d, new CustomTypefaceSpan(getContext(), this.f21389d.getTextSize(), C1947R.color.body_text_grey, TypefaceUtils.m26189a(getContext())));
            styleReplacer.m26178a(c43647);
            styleReplacer.m26183a("%1$s", this.v.accountIcon.handle, customTypefaceSpan, c43636);
            styleReplacer.m26182a("%2$s", this.v.message, null);
            styleReplacer.m26174a();
            return;
        }
        this.f21387b.setVisibility(8);
        this.f21390e.setVisibility(8);
    }

    protected void m23063e() {
        if (this.v.d() && this.v.seed) {
            this.f21392g.setVisibility(0);
            if (this.v.n()) {
                this.f21395j.setCompoundDrawablesWithIntrinsicBounds(0, 0, C1947R.drawable.icn_duet, 0);
                this.f21395j.setText(C1947R.string.core_join);
                this.f21393h.setEnabled(true);
                return;
            }
            this.f21395j.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.f21395j.setText(C1947R.string.performances_open_call_expired);
            this.f21393h.setEnabled(false);
        } else if (this.v.n()) {
            this.f21392g.setVisibility(0);
            this.f21393h.setEnabled(true);
            this.f21395j.setCompoundDrawablesWithIntrinsicBounds(0, 0, C1947R.drawable.icn_group, 0);
            this.f21395j.setText(C1947R.string.core_join);
        } else {
            this.f21392g.setVisibility(8);
        }
    }

    public void setListener(PerformanceItemListener performanceItemListener) {
        this.f21401p = performanceItemListener;
    }

    public void setOnJoinListener(OnClickListener onClickListener) {
        this.f21393h.setOnClickListener(onClickListener);
    }

    public void setOnMoreListener(OnClickListener onClickListener) {
        this.f21394i.setOnClickListener(onClickListener);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f21402w == null) {
            this.f21402w = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f21402w;
    }

    private void m23058h() {
        ((ClipboardManager) getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(f21386a, this.v.webUrl));
        Toaster.a(getContext(), getContext().getResources().getString(C1947R.string.chat_copy), Toaster$Duration.SHORT);
    }
}
