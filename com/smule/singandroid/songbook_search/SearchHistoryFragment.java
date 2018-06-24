package com.smule.singandroid.songbook_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.utils.AutocompleteUtils;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class SearchHistoryFragment extends BaseFragment {
    private static final String f24136h = SearchHistoryFragment.class.getName();
    @ViewById
    protected MagicListView f24137e;
    @InstanceState
    protected boolean f24138f;
    private SearchFragment f24139g;

    class C48901 implements OnClickListener {
        final /* synthetic */ SearchHistoryFragment f24126a;

        C48901(SearchHistoryFragment searchHistoryFragment) {
            this.f24126a = searchHistoryFragment;
        }

        public void onClick(View view) {
            final TextAlertDialog textAlertDialog = new TextAlertDialog(this.f24126a.getActivity(), this.f24126a.getString(C1947R.string.clear_all_dialog_title), null);
            textAlertDialog.m19800a((int) C1947R.string.core_delete, (int) C1947R.string.core_cancel);
            textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
                final /* synthetic */ C48901 f24125b;

                public void mo6385a(CustomAlertDialog customAlertDialog) {
                    if (!(this.f24125b.f24126a.f24137e == null || this.f24125b.f24126a.f24137e.getWrappedAdapter() == null)) {
                        Analytics.m17851a(SearchClkContext.RECENT, this.f24125b.f24126a.f24137e.getWrappedAdapter().m18048d(), null, null);
                    }
                    if (this.f24125b.f24126a.f24139g != null) {
                        this.f24125b.f24126a.f24139g.f24076A = null;
                    }
                    AutocompleteUtils.m18950a(SingApplication.f());
                    textAlertDialog.dismiss();
                    this.f24125b.f24126a.getActivity().onBackPressed();
                }

                public void mo6386b(CustomAlertDialog customAlertDialog) {
                    textAlertDialog.dismiss();
                }
            });
            textAlertDialog.show();
        }
    }

    private class SearchHistoryAdapter extends MagicAdapter {
        final /* synthetic */ SearchHistoryFragment f24134c;

        public SearchHistoryAdapter(SearchHistoryFragment searchHistoryFragment, MagicDataSource magicDataSource) {
            this.f24134c = searchHistoryFragment;
            super(magicDataSource);
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return LayoutInflater.from(this.f24134c.getActivity()).inflate(C1947R.layout.autocomplete_item, this.f24134c.f24137e, false);
        }

        public void mo6419a(View view, final int i, int i2) {
            final TextView textView = (TextView) view.findViewById(C1947R.id.suggestion);
            ((ImageView) view.findViewById(C1947R.id.time_icon)).setVisibility(0);
            View findViewById = view.findViewById(C1947R.id.close_button);
            final String str = (String) m18027a(i);
            textView.setText(str);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchHistoryAdapter f24130d;

                public void onClick(View view) {
                    Analytics.m17851a(SearchClkContext.RECENT, 1, Integer.valueOf(i), textView.getText().toString());
                    AutocompleteUtils.m18955c(SingApplication.f(), textView.getText().toString());
                    this.f24130d.m18026a().mo6688a(str);
                    this.f24130d.f24134c.f24139g.f24076A = null;
                    this.f24130d.m18026a().m17666p();
                    if (this.f24130d.m18026a().m17661k() == 0) {
                        this.f24130d.f24134c.getActivity().onBackPressed();
                    }
                }
            });
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchHistoryAdapter f24133c;

                public void onClick(View view) {
                    Analytics.m17850a(SearchClkContext.RECENT, AutocompleteUtils.m18946a(SingApplication.f(), 50).size(), i, null, 0, textView.getText().toString(), null, null, null);
                    this.f24133c.f24134c.f24139g.f24093R = textView.getText().toString();
                    this.f24133c.f24134c.getActivity().onBackPressed();
                }
            });
        }
    }

    private class SearchHistoryDataSource extends MagicDataSource<String> {
        final /* synthetic */ SearchHistoryFragment f24135a;

        public SearchHistoryDataSource(SearchHistoryFragment searchHistoryFragment, Context context) {
            this.f24135a = searchHistoryFragment;
        }

        public Future<?> mo6243a(int i, int i2, FetchDataCallback<String> fetchDataCallback) {
            fetchDataCallback.mo6257a(AutocompleteUtils.m18946a(SingApplication.f(), 50), -1);
            return null;
        }
    }

    public static SearchHistoryFragment m25339a(SearchFragment searchFragment) {
        SearchHistoryFragment searchHistoryFragment_ = new SearchHistoryFragment_();
        searchHistoryFragment_.f24139g = searchFragment;
        return searchHistoryFragment_;
    }

    public String mo6383s() {
        return f24136h;
    }

    @AfterViews
    protected void m25340a() {
        if (this.f24139g == null) {
            getActivity().onBackPressed();
            return;
        }
        m25337A();
        m19850c((int) C1947R.string.search_history);
        this.f24137e.setMagicAdapter(new SearchHistoryAdapter(this, new SearchHistoryDataSource(this, getActivity())));
        this.f24139g.f24118z = null;
        this.f24139g.f24104h = null;
    }

    private void m25337A() {
        View inflate = LayoutInflater.from(getActivity()).inflate(C1947R.layout.songbook_search_autocomplete_layout, null, false);
        inflate.findViewById(C1947R.id.recent_listview).setVisibility(8);
        inflate.findViewById(C1947R.id.recent_header_title).setVisibility(8);
        inflate.findViewById(C1947R.id.trending_listview).setVisibility(8);
        inflate.findViewById(C1947R.id.trending_header).setVisibility(8);
        inflate.findViewById(C1947R.id.trending_progress_bar).setVisibility(8);
        TextView textView = (TextView) inflate.findViewById(C1947R.id.recent_header_button);
        textView.setText(C1947R.string.recent_clear_all);
        textView.setOnClickListener(new C48901(this));
        this.f24137e.addHeaderView(inflate);
    }

    @Click
    protected void m25343z() {
        getActivity().onBackPressed();
    }

    public boolean mo6400c() {
        if (this.f24139g != null) {
            if (this.f24139g.f24093R == null) {
                this.f24139g.m25312a(AutocompleteUtils.m18946a(SingApplication.f(), 50), "", true);
            }
            this.f24139g.f24085J.m25215b(true);
            this.f24139g.f24085J.m25214a(true);
        }
        return false;
    }
}
