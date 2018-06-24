package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.api.ArrangementAPI$ListSortOrder;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.models.SongbookSection;
import com.smule.singandroid.utils.SingAnalytics.SongbookSortType;
import com.smule.singandroid.utils.TypefaceUtils;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SongbookSortSelector extends LinearLayout {
    private static final String f21985e = SongbookSortSelector.class.getName();
    @ViewById
    protected Spinner f21986a;
    protected SortType f21987b;
    protected SortTypeSelectedListener f21988c;
    OnItemSelectedListener f21989d = new C44421(this);

    public interface SortTypeSelectedListener {
        void mo6658a(SortType sortType);
    }

    class C44421 implements OnItemSelectedListener {
        final /* synthetic */ SongbookSortSelector f21964a;

        C44421(SongbookSortSelector songbookSortSelector) {
            this.f21964a = songbookSortSelector;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            this.f21964a.f21987b = (SortType) adapterView.getItemAtPosition(i);
            if (this.f21964a.f21988c != null) {
                this.f21964a.f21988c.mo6658a(this.f21964a.f21987b);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    protected class CustomSpinnerAdapter<T> extends ArrayAdapter<T> {
        final /* synthetic */ SongbookSortSelector f21967a;

        public CustomSpinnerAdapter(SongbookSortSelector songbookSortSelector, Context context, int i, List<T> list) {
            this.f21967a = songbookSortSelector;
            super(context, i, list);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            View view2;
            if (view == null) {
                view2 = (TextView) super.getDropDownView(i, null, viewGroup);
                view2.setTypeface(TypefaceUtils.m26191b());
                view2.setTextColor(getContext().getResources().getColor(C1947R.color.menu_title_grey));
            } else {
                view2 = (TextView) view;
            }
            view2.setText(getItem(i).toString());
            return view2;
        }
    }

    public enum SortMenuType {
        DEFAULT,
        NEW,
        RECOMMENDED,
        TRENDING,
        COMMUNITY,
        CURATED;

        public ArrayList<SortType> m23487a() {
            ArrayList<SortType> arrayList = new ArrayList();
            switch (this) {
                case RECOMMENDED:
                case TRENDING:
                    arrayList.add(SortType.MOST_RECENT);
                    arrayList.add(SortType.SONGS_ALPHA);
                    arrayList.add(SortType.ARTISTS_ALPHA);
                    arrayList.add(SortType.MOST_RELEVANT);
                    break;
                case COMMUNITY:
                    arrayList.add(SortType.MOST_RECENT);
                    arrayList.add(SortType.MOST_POPULAR);
                    arrayList.add(SortType.HIGHEST_RATED);
                    break;
                case CURATED:
                    arrayList.add(SortType.MOST_RECENT);
                    arrayList.add(SortType.SONGS_ALPHA);
                    arrayList.add(SortType.ARTISTS_ALPHA);
                    arrayList.add(SortType.FEATURED);
                    break;
                default:
                    arrayList.add(SortType.MOST_RECENT);
                    arrayList.add(SortType.SONGS_ALPHA);
                    arrayList.add(SortType.ARTISTS_ALPHA);
                    break;
            }
            return arrayList;
        }

        public boolean m23488a(SortType sortType) {
            return m23487a().contains(sortType);
        }

        public SortType m23489b() {
            switch (this) {
                case RECOMMENDED:
                case TRENDING:
                    return SortType.MOST_RELEVANT;
                case COMMUNITY:
                    String l = SingServerValues.m21691l();
                    Object obj = -1;
                    switch (l.hashCode()) {
                        case -978572497:
                            if (l.equals("topRated")) {
                                obj = null;
                                break;
                            }
                            break;
                        case -934918565:
                            if (l.equals("recent")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 2114901046:
                            if (l.equals("mostPlayed")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            return SortType.HIGHEST_RATED;
                        case 1:
                            return SortType.MOST_POPULAR;
                        case 2:
                            return SortType.MOST_RECENT;
                        default:
                            return SortType.MOST_RECENT;
                    }
                case CURATED:
                    return SortType.FEATURED;
                default:
                    return SortType.SONGS_ALPHA;
            }
        }
    }

    public enum SortType {
        UNDEFINED,
        MOST_RECENT,
        SONGS_ALPHA,
        ARTISTS_ALPHA,
        HIGHEST_RATED,
        MOST_POPULAR,
        MOST_RELEVANT,
        FEATURED;
        
        private String f21984i;

        public static void m23490a(Context context) {
            for (SortType sortType : values()) {
                int i;
                String string;
                switch (sortType) {
                    case MOST_RECENT:
                        i = C1947R.string.songbook_sort_option_most_recent;
                        break;
                    case SONGS_ALPHA:
                        i = C1947R.string.songbook_sort_option_songs_alpha;
                        break;
                    case ARTISTS_ALPHA:
                        i = C1947R.string.songbook_sort_option_artists_alpha;
                        break;
                    case HIGHEST_RATED:
                        i = C1947R.string.songbook_sort_option_highest_rated;
                        break;
                    case MOST_POPULAR:
                        i = C1947R.string.songbook_sort_option_most_popular;
                        break;
                    case MOST_RELEVANT:
                        i = C1947R.string.songbook_sort_option_most_relevant;
                        break;
                    case FEATURED:
                        i = C1947R.string.songbook_sort_option_featured;
                        break;
                    case UNDEFINED:
                        i = -1;
                        break;
                    default:
                        Log.e(SongbookSortSelector.f21985e, "Invalid SortType");
                        i = -1;
                        break;
                }
                if (i != -1) {
                    string = context.getResources().getString(i);
                } else {
                    string = "UNDEFINED";
                }
                sortType.f21984i = string;
            }
        }

        public String toString() {
            return this.f21984i;
        }

        public ArrangementAPI$ListSortOrder m23491a() {
            switch (this) {
                case HIGHEST_RATED:
                    return ArrangementAPI$ListSortOrder.RATING;
                case MOST_POPULAR:
                    return ArrangementAPI$ListSortOrder.PLAYED;
                default:
                    return ArrangementAPI$ListSortOrder.RECENT;
            }
        }

        public SongbookSortType m23492b() {
            switch (this) {
                case MOST_RECENT:
                    return SongbookSortType.MOST_RECENT;
                case SONGS_ALPHA:
                    return SongbookSortType.SONGS_ALPHA;
                case ARTISTS_ALPHA:
                    return SongbookSortType.ARTISTS_ALPHA;
                case HIGHEST_RATED:
                    return SongbookSortType.HIGHEST_RATED;
                case MOST_POPULAR:
                    return SongbookSortType.MOST_POPULAR;
                case MOST_RELEVANT:
                    return SongbookSortType.MOST_RELEVANT;
                case FEATURED:
                    return SongbookSortType.FEATURED;
                default:
                    throw new InvalidParameterException("Invalid SongbookSortType: " + this);
            }
        }
    }

    public static SortMenuType m23493a(SongbookSection songbookSection) {
        if (songbookSection.f23514c.equals("suggested_songs")) {
            return SortMenuType.RECOMMENDED;
        }
        if (songbookSection.f23514c.equals("trending_songs")) {
            return SortMenuType.TRENDING;
        }
        if (songbookSection.f23514c.equals("community_songs")) {
            return SortMenuType.COMMUNITY;
        }
        if (songbookSection.f23518g.size() > 0) {
            return SortMenuType.CURATED;
        }
        return SortMenuType.DEFAULT;
    }

    public SongbookSortSelector(Context context) {
        super(context);
        m23498a(context);
    }

    public SongbookSortSelector(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23498a(context);
    }

    protected void m23498a(Context context) {
        SortType.m23490a(context);
    }

    public void setSortMenuType(SortMenuType sortMenuType) {
        Object a = m23496a(sortMenuType);
        this.f21986a.setAdapter(a);
        this.f21986a.setSelection(a.getPosition(sortMenuType.m23489b()));
    }

    public void setSortType(SortType sortType) {
        m23499a(this.f21986a, m23495a(this.f21986a, sortType));
        this.f21987b = sortType;
    }

    public SortType getSortType() {
        return this.f21987b;
    }

    protected void m23499a(Spinner spinner, int i) {
        spinner.setOnItemSelectedListener(null);
        spinner.setSelection(i, false);
        spinner.setOnItemSelectedListener(this.f21989d);
    }

    protected int m23495a(Spinner spinner, SortType sortType) {
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i) == sortType) {
                return i;
            }
        }
        return -1;
    }

    protected ArrayAdapter<SortType> m23496a(SortMenuType sortMenuType) {
        ArrayAdapter<SortType> customSpinnerAdapter = new CustomSpinnerAdapter(this, getContext(), C1947R.layout.spinner_dropdown_view, sortMenuType.m23487a());
        customSpinnerAdapter.setDropDownViewResource(C1947R.layout.spinner_dropdown_item);
        return customSpinnerAdapter;
    }

    @AfterViews
    protected void m23497a() {
        if (this.f21986a != null) {
            if (isInEditMode()) {
                this.f21986a.setAdapter(m23496a(SortMenuType.DEFAULT));
            }
            this.f21986a.setOnItemSelectedListener(this.f21989d);
        }
    }

    public void setSortTypeSelectedListener(SortTypeSelectedListener sortTypeSelectedListener) {
        this.f21988c = sortTypeSelectedListener;
    }
}
