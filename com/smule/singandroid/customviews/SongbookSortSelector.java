/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Typeface
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemSelectedListener
 *  android.widget.ArrayAdapter
 *  android.widget.LinearLayout
 *  android.widget.Spinner
 *  android.widget.SpinnerAdapter
 *  android.widget.TextView
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$SongbookSortType
 *  com.smule.singandroid.utils.TypefaceUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.models.SongbookSection;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.TypefaceUtils;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SongbookSortSelector
extends LinearLayout {
    private static final String e = SongbookSortSelector.class.getName();
    @ViewById
    protected Spinner a;
    protected SortType b;
    protected SortTypeSelectedListener c;
    AdapterView.OnItemSelectedListener d;

    public SongbookSortSelector(Context context) {
        super(context);
        this.d = new AdapterView.OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> adapterView, View view, int n, long l) {
                SongbookSortSelector.this.b = (SortType)((Object)adapterView.getItemAtPosition(n));
                if (SongbookSortSelector.this.c != null) {
                    SongbookSortSelector.this.c.a(SongbookSortSelector.this.b);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        this.a(context);
    }

    public SongbookSortSelector(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new ;
        this.a(context);
    }

    public static SortMenuType a(SongbookSection songbookSection) {
        return SortMenuType.a;
    }

    protected int a(Spinner spinner, SortType sortType) {
        spinner = spinner.getAdapter();
        for (int i = 0; i < spinner.getCount(); ++i) {
            if (spinner.getItem(i) != sortType) continue;
            return i;
        }
        return -1;
    }

    protected ArrayAdapter<SortType> a(SortMenuType object) {
        object = object.a();
        object = new CustomSpinnerAdapter(this.getContext(), 2130903435, object);
        object.setDropDownViewResource(2130903434);
        return object;
    }

    @AfterViews
    protected void a() {
        if (this.a == null) {
            return;
        }
        if (this.isInEditMode()) {
            this.a.setAdapter(this.a(SortMenuType.a));
        }
        AdapterView.OnItemSelectedListener onItemSelectedListener = this.d;
        this.a.setOnItemSelectedListener(onItemSelectedListener);
    }

    protected void a(Context context) {
        SortType.a(context);
    }

    protected void a(Spinner spinner, int n) {
        spinner.setOnItemSelectedListener(null);
        spinner.setSelection(n, false);
        spinner.setOnItemSelectedListener(this.d);
    }

    public SortType getSortType() {
        return this.b;
    }

    public void setSortMenuType(SortMenuType enum_) {
        ArrayAdapter<SortType> arrayAdapter = this.a((SortMenuType)enum_);
        this.a.setAdapter(arrayAdapter);
        enum_ = enum_.b();
        this.a.setSelection(arrayAdapter.getPosition((Object)enum_));
    }

    public void setSortType(SortType sortType) {
        int n = this.a(this.a, sortType);
        this.a(this.a, n);
        this.b = sortType;
    }

    public void setSortTypeSelectedListener(SortTypeSelectedListener sortTypeSelectedListener) {
        this.c = sortTypeSelectedListener;
    }

    protected class CustomSpinnerAdapter<T>
    extends ArrayAdapter<T> {
        public CustomSpinnerAdapter(Context context, int n, List<T> list) {
            super(context, n, list);
        }

        /*
         * Enabled aggressive block sorting
         */
        public View getDropDownView(int n, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = (TextView)super.getDropDownView(n, null, viewGroup);
                view.setTypeface(TypefaceUtils.b());
                view.setTextColor(this.getContext().getResources().getColor(2131689807));
            } else {
                view = (TextView)view;
            }
            view.setText((CharSequence)this.getItem(n).toString());
            return view;
        }
    }

    public static enum SortMenuType {
        a,
        b,
        c,
        d,
        e,
        f;
        

        private SortMenuType() {
        }

        public ArrayList<SortType> a() {
            ArrayList<SortType> arrayList = new ArrayList<SortType>();
            switch (.b[this.ordinal()]) {
                default: {
                    arrayList.add(SortType.b);
                    arrayList.add(SortType.c);
                    arrayList.add(SortType.d);
                    return arrayList;
                }
                case 1: 
                case 2: {
                    arrayList.add(SortType.b);
                    arrayList.add(SortType.c);
                    arrayList.add(SortType.d);
                    arrayList.add(SortType.g);
                    return arrayList;
                }
                case 3: {
                    arrayList.add(SortType.b);
                    arrayList.add(SortType.f);
                    arrayList.add(SortType.e);
                    return arrayList;
                }
                case 4: 
            }
            arrayList.add(SortType.b);
            arrayList.add(SortType.c);
            arrayList.add(SortType.d);
            arrayList.add(SortType.h);
            return arrayList;
        }

        public boolean a(SortType sortType) {
            return this.a().contains((Object)sortType);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public SortType b() {
            switch (.b[this.ordinal()]) {
                default: {
                    return SortType.c;
                }
                case 3: {
                    var2_1 = new SingServerValues().r();
                    var1_2 = -1;
                    switch (var2_1.hashCode()) {
                        case -978572497: {
                            if (var2_1.equals("topRated")) {
                                var1_2 = 0;
                                ** break;
                            }
                            ** GOTO lbl16
                        }
                        case 2114901046: {
                            if (var2_1.equals("mostPlayed")) {
                                var1_2 = 1;
                            }
                        }
lbl16: // 6 sources:
                        default: {
                            ** GOTO lbl21
                        }
                        case -934918565: 
                    }
                    if (var2_1.equals("recent")) {
                        var1_2 = 2;
                    }
lbl21: // 4 sources:
                    switch (var1_2) {
                        default: {
                            return SortType.b;
                        }
                        case 0: {
                            return SortType.e;
                        }
                        case 1: {
                            return SortType.f;
                        }
                        case 2: 
                    }
                    return SortType.b;
                }
                case 1: 
                case 2: {
                    return SortType.g;
                }
                case 4: 
            }
            return SortType.h;
        }
    }

    public static enum SortType {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h;
        
        private String i;

        private SortType() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public static void a(Context context) {
            SortType[] arrsortType = SortType.values();
            int n = arrsortType.length;
            int n2 = 0;
            while (n2 < n) {
                int n3;
                SortType sortType = arrsortType[n2];
                switch (.a[sortType.ordinal()]) {
                    default: {
                        Log.e(e, "Invalid SortType");
                        n3 = -1;
                        break;
                    }
                    case 1: {
                        n3 = 2131297538;
                        break;
                    }
                    case 2: {
                        n3 = 2131297540;
                        break;
                    }
                    case 3: {
                        n3 = 2131297534;
                        break;
                    }
                    case 4: {
                        n3 = 2131297536;
                        break;
                    }
                    case 5: {
                        n3 = 2131297537;
                        break;
                    }
                    case 6: {
                        n3 = 2131297539;
                        break;
                    }
                    case 7: {
                        n3 = 2131297535;
                        break;
                    }
                    case 8: {
                        n3 = -1;
                    }
                }
                String string2 = n3 != -1 ? context.getResources().getString(n3) : "UNDEFINED";
                sortType.i = string2;
                ++n2;
            }
        }

        public SingAnalytics.SongbookSortType a() {
            switch (.a[this.ordinal()]) {
                default: {
                    throw new InvalidParameterException("Invalid SongbookSortType: " + (Object)((Object)this));
                }
                case 1: {
                    return SingAnalytics.SongbookSortType.a;
                }
                case 2: {
                    return SingAnalytics.SongbookSortType.b;
                }
                case 3: {
                    return SingAnalytics.SongbookSortType.c;
                }
                case 4: {
                    return SingAnalytics.SongbookSortType.d;
                }
                case 5: {
                    return SingAnalytics.SongbookSortType.e;
                }
                case 6: {
                    return SingAnalytics.SongbookSortType.f;
                }
                case 7: 
            }
            return SingAnalytics.SongbookSortType.g;
        }

        public String toString() {
            return this.i;
        }
    }

    public static interface SortTypeSelectedListener {
        public void a(SortType var1);
    }

}

