package com.smule.singandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserver;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataState;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.models.ContestData$Reward;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MagicPreferences.OnboardStatus;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.TrialSubscriptionActivity;
import com.smule.singandroid.TrialSubscriptionActivity_;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.datasource.OnboardingSongsDataSource;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.DeepLink.Hosts;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OnboardingSongsFragment extends BaseFragment implements DataSourceObserver {
    private static final String f22562n = OnboardingSongsFragment.class.getName();
    @ViewById
    protected MediaListView f22563e;
    @ViewById
    protected View f22564f;
    @ViewById
    protected LinearLayout f22565g;
    @ViewById
    protected TextView f22566h;
    @ViewById
    protected ProfileImageWithVIPBadge f22567i;
    @ViewById
    protected View f22568j;
    @ViewById
    protected View f22569k;
    @ViewById
    protected View f22570l;
    @ViewById
    protected View f22571m;
    private OnboardingSongsAdapter f22572o;
    private OnboardingSongsAdapterInterface f22573p = new OnboardingSongsAdapterInterface();
    private ArrayList<Integer> f22574q = new ArrayList();
    private TextAlertDialog f22575r;
    private SongbookEntry f22576s = null;
    private ConcurrentHashMap<String, Boolean> f22577t = new ConcurrentHashMap();

    class C45631 extends Operation {
        final /* synthetic */ OnboardingSongsFragment f22547a;

        C45631(OnboardingSongsFragment onboardingSongsFragment) {
            this.f22547a = onboardingSongsFragment;
        }

        public void m23988a(OperationLoader operationLoader) {
            SingApplication.d().a("OnboardingActivity.OP_WAIT_OPERATIONS");
            operationLoader.c(this.g);
        }
    }

    class C45642 implements CustomAlertDialogListener {
        final /* synthetic */ OnboardingSongsFragment f22548a;

        C45642(OnboardingSongsFragment onboardingSongsFragment) {
            this.f22548a = onboardingSongsFragment;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            this.f22548a.m24016z();
            this.f22548a.m24007a("NONE", "NONE");
            this.f22548a.m24012b(null);
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
        }
    }

    class C45653 implements CustomAlertDialogListener {
        final /* synthetic */ OnboardingSongsFragment f22549a;

        C45653(OnboardingSongsFragment onboardingSongsFragment) {
            this.f22549a = onboardingSongsFragment;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            this.f22549a.f22572o.m18054f();
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            this.f22549a.m24012b(null);
        }
    }

    public interface AdapterInterface {
        Context mo6846a();

        void mo6847a(ListingListItem listingListItem, SongbookEntry songbookEntry, int i);
    }

    public class OnboardingSongsAdapter extends MagicAdapter {
        AdapterInterface f22553c;
        final /* synthetic */ OnboardingSongsFragment f22554d;

        public OnboardingSongsAdapter(OnboardingSongsFragment onboardingSongsFragment, MagicDataSource magicDataSource, AdapterInterface adapterInterface) {
            this.f22554d = onboardingSongsFragment;
            super(magicDataSource);
            this.f22553c = adapterInterface;
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return ListingListItem.m24377a(this.f22553c.mo6846a());
        }

        public void mo6419a(View view, int i, int i2) {
            ListingListItem listingListItem = (ListingListItem) view;
            SongbookEntry songbookEntry = (SongbookEntry) m18027a(i);
            listingListItem.m24380a(songbookEntry, i == 0);
            this.f22553c.mo6847a(listingListItem, songbookEntry, i);
        }
    }

    private class OnboardingSongsAdapterInterface implements AdapterInterface {
        final /* synthetic */ OnboardingSongsFragment f22561a;

        private OnboardingSongsAdapterInterface(OnboardingSongsFragment onboardingSongsFragment) {
            this.f22561a = onboardingSongsFragment;
        }

        public Context mo6846a() {
            return this.f22561a.getActivity();
        }

        public void mo6847a(final ListingListItem listingListItem, final SongbookEntry songbookEntry, final int i) {
            OnClickListener c45671 = new OnClickListener(this) {
                final /* synthetic */ OnboardingSongsAdapterInterface f22557c;

                public void onClick(View view) {
                    this.f22557c.f22561a.f22576s = songbookEntry;
                    SingAnalytics.m26073a(songbookEntry, UserPath.ONBOARDING);
                    SingAnalytics.m26059a(i, songbookEntry);
                    this.f22557c.f22561a.m24007a(songbookEntry.mo6289c(), songbookEntry instanceof ArrangementVersionLiteEntry ? "ARR" : ContestData$Reward.TYPE_SONG);
                    this.f22557c.f22561a.m24014c(false);
                    this.f22557c.f22561a.m24016z();
                    this.f22557c.f22561a.m24012b(this.f22557c.f22561a.f22576s);
                }
            };
            OnClickListener c45682 = new OnClickListener(this) {
                final /* synthetic */ OnboardingSongsAdapterInterface f22560c;

                public void onClick(View view) {
                    boolean z = true;
                    if (!(songbookEntry instanceof ArrangementVersionLiteEntry)) {
                        Analytics.m17867a("pickasong", SongbookEntry.m18752b(songbookEntry), songbookEntry.mo6294h(), songbookEntry.m18770p(), null, SongbookEntry.m18749a(songbookEntry));
                        this.f22560c.f22561a.mo6443a(songbookEntry);
                    } else if (!listingListItem.m23046t()) {
                        listingListItem.setAlbumArtClickedState(true);
                        if (this.f22560c.f22561a.f22577t == null) {
                            this.f22560c.f22561a.f22577t = new ConcurrentHashMap();
                        }
                        if (this.f22560c.f22561a.f22577t.containsKey(songbookEntry.mo6289c())) {
                            this.f22560c.f22561a.m24008a(((Boolean) this.f22560c.f22561a.f22577t.get(songbookEntry.mo6289c())).booleanValue(), (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                            return;
                        }
                        int i = ((ArrangementVersionLiteEntry) songbookEntry).m18774a().removalCode;
                        if (i < 40 || i > 49) {
                            z = false;
                        }
                        this.f22560c.f22561a.f22577t.put(songbookEntry.mo6289c(), Boolean.valueOf(z));
                        this.f22560c.f22561a.m24008a(z, (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                    }
                }
            };
            listingListItem.setOnClickListener(c45671);
            listingListItem.setAlbumArtClickListener(c45682);
        }
    }

    public static OnboardingSongsFragment m24011b(boolean z) {
        OnboardingSongsFragment onboardingSongsFragment_ = new OnboardingSongsFragment_();
        Bundle bundle = new Bundle();
        bundle.putBoolean("TOPIC_SELECTED", z);
        onboardingSongsFragment_.setArguments(bundle);
        return onboardingSongsFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReferenceMonitor.a().a(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22574q = getArguments().getIntegerArrayList("topicIDs");
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    protected void mo6420v() {
        Analytics.m17907n();
    }

    @AfterViews
    void m24017a() {
        m24000B();
        m24014c(false);
        m23999A();
        SingApplication.d().a("OnboardingActivity.OP_WAIT_OPERATIONS", Collections.singletonList("InitAppTask.OP_LOCALIZE_SETTINGS"), new C45631(this)).a();
    }

    public boolean mo6400c() {
        mo6848a(new C45642(this));
        return true;
    }

    private void m24016z() {
        getActivity().getSharedPreferences("sing_prefs", 0).edit().putInt("ONBOARD_STATUS_KEY", OnboardStatus.FINISHED.ordinal()).apply();
    }

    private void m23999A() {
        OnboardingSongsDataSource onboardingSongsDataSource = new OnboardingSongsDataSource(this.f22574q);
        this.f22572o = new OnboardingSongsAdapter(this, new OnboardingSongsDataSource(this.f22574q), this.f22573p);
        this.f22563e.setMagicAdapter(this.f22572o);
        this.f22572o.mo6858a((DataSourceObserver) this);
        if (onboardingSongsDataSource.m17659i() == DataState.HAS_DATA) {
            m24014c(true);
        }
    }

    public String mo6383s() {
        return f22562n;
    }

    public void mo6254c(MagicDataSource magicDataSource) {
        if (magicDataSource.m17659i() != DataState.LOADING_FIRST_PAGE) {
            if (magicDataSource.m17659i() == DataState.HAS_DATA) {
                m24014c(true);
            } else if (magicDataSource.m17659i() == DataState.FIRST_PAGE_EMPTY) {
                m24012b(null);
            } else {
                mo6850c(new C45653(this));
            }
        }
    }

    public void mo6255d(MagicDataSource magicDataSource) {
    }

    public void mo6252a(MagicDataSource magicDataSource, List<Object> list) {
    }

    public void mo6251a(MagicDataSource magicDataSource) {
    }

    public void mo6253b(MagicDataSource magicDataSource) {
    }

    private void m24008a(boolean z, ArrangementVersionLiteEntry arrangementVersionLiteEntry, ListingListItem listingListItem) {
        this.f22577t.put(arrangementVersionLiteEntry.mo6289c(), Boolean.valueOf(z));
        if (z) {
            MediaPlayerServiceController.m24641a().m24660b(arrangementVersionLiteEntry.mo6289c());
            m24021a(listingListItem);
            return;
        }
        Analytics.m17867a("pickasong", SongbookEntry.m18752b((SongbookEntry) arrangementVersionLiteEntry), arrangementVersionLiteEntry.mo6294h(), arrangementVersionLiteEntry.m18770p(), null, SongbookEntry.m18749a((SongbookEntry) arrangementVersionLiteEntry));
        mo6443a((SongbookEntry) arrangementVersionLiteEntry);
    }

    protected void m24021a(ListingListItem listingListItem) {
        if (isAdded()) {
            listingListItem.setAlbumArtClickedState(false);
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.arrangement_copyright_violation_title), MessageFormat.format(getString(C1947R.string.arrangement_copyright_violation_detail), new Object[]{getString(C1947R.string.performance_copyright_violation_email)}), true, false);
            textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
            textAlertDialog.show();
        }
    }

    private void m24000B() {
        this.f22569k.setVisibility(8);
        this.f22568j.setVisibility(0);
        this.f22571m.setVisibility(0);
    }

    private void m24014c(boolean z) {
        if (isAdded()) {
            this.f22565g.setVisibility(z ? 8 : 0);
        }
    }

    @UiThread
    protected void mo6848a(CustomAlertDialogListener customAlertDialogListener) {
        if (isAdded()) {
            this.f22575r = new TextAlertDialog(getActivity(), getString(C1947R.string.core_are_you_sure), getString(C1947R.string.onboarding_cancel_detail));
            this.f22575r.m19806a(getString(C1947R.string.core_yes), getString(C1947R.string.core_no));
            this.f22575r.m19803a(customAlertDialogListener);
            this.f22575r.show();
        }
    }

    @UiThread
    protected void mo6849b(CustomAlertDialogListener customAlertDialogListener) {
        if (isAdded()) {
            this.f22575r = new TextAlertDialog(getActivity(), getString(C1947R.string.core_are_you_sure), getString(C1947R.string.onboarding_failure_title));
            this.f22575r.m19806a(getString(C1947R.string.core_yes), getString(C1947R.string.core_no));
            this.f22575r.m19803a(customAlertDialogListener);
            this.f22575r.show();
        }
    }

    @UiThread
    protected void mo6850c(CustomAlertDialogListener customAlertDialogListener) {
        if (isAdded()) {
            this.f22575r = new TextAlertDialog(getActivity(), getString(C1947R.string.onboarding_network_failure_title), getString(C1947R.string.onboarding_network_failure_body));
            this.f22575r.m19806a(getString(C1947R.string.onboarding_network_failure_retry), null);
            this.f22575r.m19803a(customAlertDialogListener);
            this.f22575r.show();
        }
    }

    private void m24012b(SongbookEntry songbookEntry) {
        Intent intent;
        Object obj = songbookEntry != null ? 1 : null;
        if (obj == null && TrialSubscriptionActivity.m21947a(getActivity())) {
            intent = new Intent(getActivity(), TrialSubscriptionActivity_.class);
        } else {
            intent = new Intent(getActivity(), MasterActivity_.class);
        }
        if (obj != null) {
            intent.setData(DeepLink.m25820a(Hosts.Onboarding, songbookEntry.mo6290d()));
            intent.putExtra("ONBOARDING_SONGBOOK_ENTRY", songbookEntry);
        }
        startActivity(intent);
    }

    private void m24007a(final String str, final String str2) {
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ OnboardingSongsFragment f22552c;

            public void run() {
                RecommendationManager.m18285a().m18291a(str, str2, true);
            }
        });
    }
}
