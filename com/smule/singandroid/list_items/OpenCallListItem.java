package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils$ImageViewLoadOptimizer;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import java.util.concurrent.TimeUnit;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class OpenCallListItem extends VideoUploadingView {
    private static final String f23001a = OpenCallListItem.class.getName();
    private static long f23002b = TimeUnit.DAYS.toSeconds(365);
    @ViewById
    protected TextView f23003A;
    @ViewById
    protected View f23004B;
    private ExpandedPerformanceItemListener f23005E;
    private LocalizedShortNumberFormatter f23006F;
    private boolean f23007G;
    private boolean f23008H = false;
    private boolean f23009I;
    protected Context f23010c;
    protected ImageUtils$ImageViewLoadOptimizer f23011d = new ImageUtils$ImageViewLoadOptimizer();
    boolean f23012e;
    @ViewById
    protected View f23013f;
    @ViewById
    protected TextView f23014g;
    @ViewById
    protected TextView f23015h;
    @ViewById
    protected TextView f23016i;
    @ViewById
    protected TextView f23017j;
    @ViewById
    protected ProfileImageWithVIPBadge f23018k;
    @ViewById
    protected TextView f23019l;
    @ViewById
    protected Button f23020m;
    @ViewById
    protected ImageView f23021n;
    @ViewById
    protected TextView f23022o;
    @ViewById
    protected TextView f23023p;
    @ViewById
    protected View f23024q;
    @ViewById
    protected View f23025r;
    @ViewById
    protected View f23026s;
    @ViewById
    protected Button f23027t;
    @ViewById
    protected View f23028u;
    @ViewById
    protected View f23029v;
    @ViewById
    protected View f23030w;
    @ViewById
    protected TextView f23031x;
    @ViewById
    protected TextView f23032y;
    @ViewById
    protected View f23033z;

    public interface ExpandedPerformanceItemListener {
        void mo6559a(PerformanceV2 performanceV2, boolean z);

        void mo6560a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2);

        void mo6561b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2);

        void mo6562c(OpenCallListItem openCallListItem, PerformanceV2 performanceV2);

        void mo6563d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2);
    }

    class C46681 implements OnClickListener {
        final /* synthetic */ OpenCallListItem f23099a;

        C46681(OpenCallListItem openCallListItem) {
            this.f23099a = openCallListItem;
        }

        public void onClick(View view) {
            Log.b(OpenCallListItem.f23001a, "Join button clicked");
            this.f23099a.m24339c();
        }
    }

    class C46692 implements OnClickListener {
        final /* synthetic */ OpenCallListItem f23100a;

        C46692(OpenCallListItem openCallListItem) {
            this.f23100a = openCallListItem;
        }

        public void onClick(View view) {
            this.f23100a.m24341d();
        }
    }

    class C46703 implements OnClickListener {
        final /* synthetic */ OpenCallListItem f23101a;

        C46703(OpenCallListItem openCallListItem) {
            this.f23101a = openCallListItem;
        }

        public void onClick(View view) {
            this.f23101a.m24342e();
        }
    }

    class C46714 implements OnClickListener {
        final /* synthetic */ OpenCallListItem f23102a;

        C46714(OpenCallListItem openCallListItem) {
            this.f23102a = openCallListItem;
        }

        public void onClick(View view) {
            this.f23102a.m24343f();
        }
    }

    class C46725 implements PerformanceManager$PerformanceResponseCallback {
        final /* synthetic */ OpenCallListItem f23103a;

        C46725(OpenCallListItem openCallListItem) {
            this.f23103a = openCallListItem;
        }

        public void handleResponse(PerformanceResponse performanceResponse) {
            if (performanceResponse.a()) {
                this.f23103a.f23010c.startActivity(ChatShareInviteActivity.m22648a(this.f23103a.f23010c, this.f23103a.D, SearchClkContext.SHAREMESSAGE));
            } else if (performanceResponse.a.e() && (this.f23103a.f23010c instanceof BaseActivity)) {
                ((BaseActivity) this.f23103a.f23010c).a(performanceResponse.a.f, true, null);
            }
        }
    }

    public static OpenCallListItem m24333c(Context context) {
        OpenCallListItem a = OpenCallListItem_.m24387a(context);
        a.f23010c = context;
        return a;
    }

    public OpenCallListItem(Context context) {
        super(context);
        this.f23010c = context;
    }

    public void setIsBookmarkItem(boolean z) {
        this.f23008H = z;
    }

    public void setExpandedPerformanceListener(ExpandedPerformanceItemListener expandedPerformanceItemListener) {
        this.f23005E = expandedPerformanceItemListener;
    }

    public void m24338b(boolean z) {
        this.f23013f.setVisibility(z ? 0 : 8);
    }

    protected void m24339c() {
        if (this.f23005E != null) {
            if (this.D == null) {
                Log.d(f23001a, "onOpenCallItemJoinCallBack - mPerformance is null!");
            }
            this.f23005E.mo6560a(this, this.D);
        }
    }

    protected void m24341d() {
        Log.b(f23001a, "onExpandedPerformanceItemAlbumArtClickCallBack - called");
        if (this.f23005E != null) {
            this.f23005E.mo6561b(this, this.D);
        }
    }

    protected void m24342e() {
        Log.b(f23001a, "onExpandedPerformanceItemProfileClickCallBack - called");
        if (this.f23005E != null) {
            this.f23005E.mo6562c(this, this.D);
        }
    }

    protected void m24343f() {
        Log.b(f23001a, "onExpandedPerformanceItemMetaDataClickCallBack - called");
        if (this.f23005E != null) {
            this.f23005E.mo6563d(this, this.D);
        }
    }

    @Click
    protected void m24344g() {
        if (this.f23005E != null) {
            this.f23005E.mo6559a(this.D, this.f23007G);
        }
    }

    protected void m24345h() {
        if (this.D.songUid == null || this.f23012e) {
            this.f23021n.setVisibility(4);
            return;
        }
        ListingV2 e = StoreManager.m18378a().m18428e(this.D.songUid);
        if (e == null || !e.a()) {
            this.f23021n.setVisibility(4);
        } else {
            this.f23021n.setVisibility(0);
        }
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f23006F == null) {
            this.f23006F = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f23006F;
    }

    public void m24336a(PerformanceV2 performanceV2, boolean z) {
        if (performanceV2 == null) {
            Log.e(f23001a, "setOpenCall called with null open call");
            return;
        }
        String str;
        setPerformance(performanceV2);
        String str2 = "";
        if (this.D.e()) {
            int i;
            Resources resources = getResources();
            if (this.D.seed) {
                i = C1947R.string.opencall_created_group;
            } else {
                i = C1947R.string.opencall_joined_group;
            }
            str = str2;
            str2 = resources.getString(i);
        } else {
            str2 = getResources().getString(C1947R.string.opencall_sang_part);
            if (this.D.origTrackPartId > 0) {
                str = Integer.toString(this.D.origTrackPartId);
            } else {
                str = "1";
            }
        }
        Object customTypefaceSpan = new CustomTypefaceSpan(getContext(), this.f23016i.getTextSize(), C1947R.color.sub_heading_dark, TypefaceUtils.m26195c(getContext()));
        StyleReplacer styleReplacer = new StyleReplacer(str2, this.f23016i, new CustomTypefaceSpan(getContext(), this.f23016i.getTextSize(), C1947R.color.body_text_grey, TypefaceUtils.m26189a(getContext())), getResources());
        styleReplacer.m26184a("{0}", this.D.accountIcon.handle, customTypefaceSpan, null, this.D.accountIcon.c());
        styleReplacer.m26182a("{1}", str, null);
        styleReplacer.m26174a();
        if (this.D.message == null || this.D.message.trim().length() <= 0) {
            this.f23017j.setVisibility(8);
        } else {
            this.f23017j.setVisibility(0);
            this.f23017j.setText("“" + this.D.message.trim() + "”");
        }
        m24335w();
        this.f23018k.setProfilePicUrl(performanceV2.accountIcon.picUrl);
        this.f23018k.setVIP(performanceV2.accountIcon.a());
        if (performanceV2.n()) {
            this.f23020m.setVisibility(0);
            m24345h();
        } else {
            this.f23020m.setVisibility(8);
            this.f23021n.setVisibility(8);
        }
        this.f23020m.setOnClickListener(new C46681(this));
        this.f23023p.setText(MiscUtils.m25887a((long) performanceV2.createdAt, false, true, false));
        this.f23009I = z;
        setDividerVisibility(z);
    }

    private void setDividerVisibility(boolean z) {
        this.f23025r.setVisibility(z ? 8 : 0);
    }

    public void m24340c(boolean z) {
        this.f23027t.setVisibility(z ? 0 : 8);
        setExpireTime(z);
    }

    public void m24346i() {
        if (this.D != null) {
            m24340c(this.D.n());
        }
    }

    private void m24335w() {
        this.ae.f21796a.setOnClickListener(new C46692(this));
        this.f23018k.setOnClickListener(new C46703(this));
        this.f23029v.setOnClickListener(new C46714(this));
    }

    private boolean m24332b(PerformanceV2 performanceV2) {
        return performanceV2.n() && performanceV2.expireAt - (System.currentTimeMillis() / 1000) > f23002b;
    }

    public void m24347j() {
        int i;
        TextView textView = this.f23014g;
        if (this.D.isPrivate) {
            i = C1947R.drawable.icn_private;
        } else {
            i = 0;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    protected void setPerformance(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            Log.e(f23001a, "setPerformance called with null performance");
            return;
        }
        int i;
        setTag(performanceV2.performanceKey);
        this.D = performanceV2;
        this.f23014g.setText(performanceV2.title);
        this.f23015h.setText(PerformanceV2Util.m25933a(getResources(), this.D, true));
        this.f23014g.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        m24346i();
        String str = null;
        if (performanceV2.coverUrl != null) {
            str = performanceV2.coverUrl;
        } else if (performanceV2.songUid != null) {
            ListingV2 e = StoreManager.m18378a().m18428e(performanceV2.songUid);
            if (e != null) {
                str = e.song.googleCoverArtUrl;
            }
        }
        if (str == null || str.isEmpty()) {
            this.ae.f21796a.setImageResource(C1947R.drawable.icn_album_cover_play_large);
        } else {
            this.f23011d.m18975a(performanceV2.coverUrl, this.ae.f21796a, C1947R.drawable.icn_album_cover_play_large);
        }
        m23036a(performanceV2.performanceKey);
        if (performanceV2.d() && performanceV2.n()) {
            i = C1947R.drawable.icn_duet;
        } else if (performanceV2.e() && performanceV2.n()) {
            i = C1947R.drawable.icn_group;
        } else {
            i = C1947R.drawable.icn_solo;
        }
        this.f23020m.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
        this.f23020m.setText(getResources().getString(C1947R.string.core_join));
        this.f23031x.setText(getLocalizedFormatter().m18999a((long) performanceV2.totalListens, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        this.f23032y.setText(getLocalizedFormatter().m18999a((long) performanceV2.totalLoves, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        if (performanceV2.n()) {
            this.f23030w.setVisibility(0);
            if (m24332b(this.D)) {
                this.f23022o.setVisibility(4);
            } else {
                boolean z;
                if (this.f23027t.getVisibility() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                setExpireTime(z);
                this.f23022o.setVisibility(0);
            }
        } else {
            this.f23030w.setVisibility(8);
        }
        this.ae.m23378a(m23037a(this.D), (int) C1947R.drawable.noti_filmstrip);
    }

    private void setExpireTime(boolean z) {
        this.f23022o.setText(getResources().getString(C1947R.string.performances_left, new Object[]{MiscUtils.m25886a(this.D.expireAt, z, false)}));
    }

    public void setIsSectionFree(boolean z) {
        this.f23012e = z;
    }

    @Click
    protected void m24348k() {
        PerformanceManager.a().a(this.D.performanceKey, new C46725(this));
    }

    public void setHideBookmarkOption(boolean z) {
        this.f23007G = z;
    }

    public void m24337a(String str, int i, int i2) {
        this.f23003A.setText(str);
        this.f23003A.setCompoundDrawablesWithIntrinsicBounds(0, 0, i2, 0);
        this.f23004B.setBackgroundColor(i);
        this.f23033z.setVisibility(0);
        setDividerVisibility(true);
    }

    public void m24349l() {
        this.f23033z.setVisibility(8);
        setDividerVisibility(this.f23009I);
    }
}
