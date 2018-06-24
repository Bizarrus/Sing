/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.RadioButton
 *  android.widget.TextView
 */
package com.smule.singandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class LanguagesListAdapter
extends BaseAdapter {
    private Context a;
    private List<Locale> b;
    private LayoutInflater c;
    private int d = -1;
    private LanguageSelectionCallback e;
    private Locale f;

    public LanguagesListAdapter(Context context, Locale locale) {
        this(context, locale, null);
    }

    public LanguagesListAdapter(Context context, Locale locale, LanguageSelectionCallback languageSelectionCallback) {
        this.a = context;
        this.f = locale;
        this.b = this.a(locale);
        this.c = LayoutInflater.from((Context)context);
        this.e = languageSelectionCallback;
    }

    private List<Locale> a(final Locale locale) {
        Object object = LocaleSettings.c();
        ArrayList<Locale> arrayList = new ArrayList<Locale>(object.size());
        object = object.iterator();
        while (object.hasNext()) {
            arrayList.add(LocaleSettings.a((String)object.next(), locale));
        }
        Collections.sort(arrayList, new Comparator<Locale>(){

            public int a(Locale locale3, Locale locale2) {
                if (locale.getLanguage().equals(locale3.getLanguage())) {
                    return -1;
                }
                if (locale.getLanguage().equals(locale2.getLanguage())) {
                    return 1;
                }
                return locale3.getDisplayLanguage(locale3).toLowerCase(locale3).compareTo(locale2.getDisplayLanguage(locale2).toLowerCase(locale2));
            }

            @Override
            public /* synthetic */ int compare(Object object, Object object2) {
                return this.a((Locale)object, (Locale)object2);
            }
        });
        int n = 0;
        do {
            block5 : {
                block4 : {
                    if (n > arrayList.size()) break block4;
                    if (!locale.getLanguage().equals(arrayList.get(n).getLanguage())) break block5;
                    this.d = n;
                }
                return arrayList;
            }
            ++n;
        } while (true);
    }

    private void a(View view) {
        this.d = (Integer)view.getTag();
        this.notifyDataSetChanged();
    }

    public Locale a() {
        if (this.d != -1) {
            return this.b.get(this.d);
        }
        return null;
    }

    public Locale a(int n) {
        return this.b.get(n);
    }

    public int getCount() {
        return this.b.size();
    }

    public /* synthetic */ Object getItem(int n) {
        return this.a(n);
    }

    public long getItemId(int n) {
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View view, ViewGroup object) {
        Object object2;
        if (view == null) {
            object2 = new ViewHolder();
            view = this.c.inflate(2130903260, (ViewGroup)object, false);
            ((ViewHolder)object2).b = (TextView)view.findViewById(2131755888);
            ((ViewHolder)object2).c = (TextView)view.findViewById(2131755889);
            ((ViewHolder)object2).d = (RadioButton)view.findViewById(2131755887);
            ((ViewHolder)object2).e = view.findViewById(2131755886);
            view.setTag(object2);
            object = object2;
        } else {
            object = (ViewHolder)view.getTag();
        }
        object2 = this.b.get(n);
        ((ViewHolder)object).b.setText((CharSequence)StringUtils.a(object2.getDisplayLanguage((Locale)object2)));
        ((ViewHolder)object).c.setText((CharSequence)StringUtils.a(object2.getDisplayLanguage(this.f)));
        object2 = ((ViewHolder)object).d;
        boolean bl = n == this.d;
        object2.setChecked(bl);
        ((ViewHolder)object).e.setTag((Object)n);
        ((ViewHolder)object).e.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                LanguagesListAdapter.this.a(view);
                if (LanguagesListAdapter.this.e != null) {
                    LanguagesListAdapter.this.e.a((Locale)LanguagesListAdapter.this.b.get((Integer)view.getTag()));
                }
            }
        });
        return view;
    }

    public static interface LanguageSelectionCallback {
        public void a(Locale var1);
    }

    private class ViewHolder {
        private TextView b;
        private TextView c;
        private RadioButton d;
        private View e;

        private ViewHolder() {
        }
    }

}

