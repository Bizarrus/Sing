/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$Adapter
 *  android.support.v7.widget.RecyclerView$LayoutManager
 *  android.support.v7.widget.RecyclerView$OnChildAttachStateChangeListener
 *  android.support.v7.widget.RecyclerView$OnScrollListener
 *  android.support.v7.widget.RecyclerView$ViewHolder
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$SingModulePlacement
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.smule.singandroid;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsFragment;
import com.smule.singandroid.FindFriendsModule_;
import com.smule.singandroid.common.CenterLayoutManager;
import com.smule.singandroid.datasource.RecommendedFriendsCachedDataSource;
import com.smule.singandroid.list_items.FindFriendsModuleListItem;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

@EViewGroup
public class FindFriendsModule
extends LinearLayout {
    public static final String a = FindFriendsModule.class.getName();
    protected static int e = 0;
    protected Context b;
    protected BaseFragment c;
    protected int d = 720;
    protected SingAnalytics.SingModulePlacement f;
    protected int g = 0;
    protected int h = 0;
    protected final ArrayList<Integer> i = new ArrayList();
    @ViewById
    TextView j;
    @ViewById
    RecyclerView k;
    FindFriendsRecyclerAdapter l;

    public FindFriendsModule(Context context) {
        super(context);
        this.b = context;
    }

    public static FindFriendsModule a(Context object, BaseFragment baseFragment, SingAnalytics.SingModulePlacement singModulePlacement, int n) {
        object = FindFriendsModule_.a((Context)object);
        object.c = baseFragment;
        object.d = n;
        object.f = singModulePlacement;
        return object;
    }

    @AfterViews
    protected void a() {
        this.k.setLayoutManager((RecyclerView.LayoutManager)new CenterLayoutManager(this.getContext(), 0, false, CenterLayoutManager.ScrollType.b));
        this.l = new FindFriendsRecyclerAdapter(new RecommendedFriendsCachedDataSource(20));
        this.k.setAdapter((RecyclerView.Adapter)this.l);
        this.l.a();
        this.k.addOnScrollListener(new RecyclerView.OnScrollListener(){

            public void onScrollStateChanged(RecyclerView recyclerView, int n) {
                super.onScrollStateChanged(recyclerView, n);
                if (n == 0) {
                    FindFriendsModule.this.c();
                }
            }

            public void onScrolled(RecyclerView recyclerView, int n, int n2) {
                super.onScrolled(recyclerView, n, n2);
                FindFriendsModule.this.h += n;
            }
        });
        this.k.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener(){

            public void onChildViewAttachedToWindow(View view) {
                if (FindFriendsModule.e == 0) {
                    FindFriendsModule.e = view.getLayoutParams().width;
                }
                FindFriendsModule.this.k.clearOnChildAttachStateChangeListeners();
            }

            public void onChildViewDetachedFromWindow(View view) {
            }
        });
        this.j.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (FindFriendsModule.this.c != null) {
                    SingAnalytics.c((SingAnalytics.SingModulePlacement)FindFriendsModule.this.f);
                    FindFriendsModule.this.c.p().a(FindFriendsFragment.a());
                }
            }
        });
    }

    protected void a(ArrayList<Integer> object) {
        if (object.size() == 0 || this.l.getItemCount() == 0) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> arrayList2 = new ArrayList<String>();
        object = object.iterator();
        while (object.hasNext()) {
            Integer n = (Integer)object.next();
            arrayList.add(n.toString());
            arrayList2.add(this.l.a((int)n.intValue()).mRecId);
        }
        com.smule.android.logging.Analytics.a(TextUtils.join((CharSequence)",", arrayList), TextUtils.join((CharSequence)",", arrayList2), Analytics.d, Analytics.l, null);
    }

    public void b() {
        SingAnalytics.a((SingAnalytics.SingModulePlacement)this.f);
        this.c();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c() {
        ArrayList<Integer> arrayList;
        block6 : {
            block5 : {
                if (e == 0) break block5;
                arrayList = new ArrayList<Integer>();
                int n = this.h / e;
                int n2 = this.h % e;
                int n3 = n2 < e / 2 ? n : n + 1;
                arrayList.add(n3);
                int n4 = e / 10;
                if (n3 == n && n2 > n4) {
                    arrayList.add(n3 + 1);
                }
                if (n3 == n + 1 && n2 < e - n4) {
                    arrayList.add(0, n3 - 1);
                }
                if (!arrayList.equals(this.i)) break block6;
            }
            return;
        }
        this.i.clear();
        this.i.addAll(arrayList);
        this.a(arrayList);
    }

    public void setBaseFragment(BaseFragment baseFragment) {
        this.c = baseFragment;
    }

    public void setDeviceWidth(int n) {
        this.d = n;
    }

    public void setDisplayPosition(int n) {
        this.g = n;
    }

    public static class FindFriendsModulePlacer {
        public static final String a = FindFriendsModulePlacer.class.getName();
        protected static int b = 200;
        protected static int c = 3;
        public static int d = -2;
        public static int e = -1;
        protected static int f = 2;
        protected boolean g = true;
        protected ArrayList<Integer> h;
        protected ArrayList<Integer> i;
        protected int j;
        protected ArrayList<Integer> k;
        protected int l;
        protected boolean m = false;

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public FindFriendsModulePlacer(@NonNull String string2, @NonNull SingAnalytics.SingModulePlacement object, @Nullable Integer n, @Nullable Integer n2) {
            if (string2 != null && object != null) {
                try {
                    string2 = new JSONObject(string2).getJSONObject(object.a());
                    if (string2 == null) {
                        this.a(n, n2);
                        return;
                    }
                    object = new Object(){
                        @JsonProperty(value="repeatInterval")
                        public Integer repeatInterval;
                        @JsonProperty(value="startingPosition")
                        public Integer startingPosition;
                    };
                    object.startingPosition = string2.getInt("startingPosition");
                    object.repeatInterval = string2.getInt("repeatInterval");
                    this.a(object.startingPosition, object.repeatInterval);
                    return;
                }
                catch (JSONException jSONException) {
                    MagicCrittercism.a((Throwable)jSONException);
                    this.a(n, n2);
                    return;
                }
            }
            this.a(n, n2);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public static boolean a(@NonNull String string2, @NonNull SingAnalytics.SingModulePlacement object) {
            boolean bl2;
            boolean bl = bl2 = false;
            if (string2 == null) return bl;
            bl = bl2;
            if (object == null) return bl;
            object = object.a();
            try {
                string2 = new JSONObject(string2).getJSONObject((String)object);
                bl = bl2;
                if (string2 == null) return bl;
                return true;
            }
            catch (JSONException jSONException) {
                return false;
            }
        }

        public void a() {
            this.g = true;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        protected void a(@Nullable Integer var1_1, @Nullable Integer var2_2) {
            block13 : {
                var9_3 = true;
                var4_4 = 0;
                var3_5 = var1_1 != null && var1_1 >= 0 ? 1 : 0;
                if (var1_1 == null || var1_1 != FindFriendsModulePlacer.e) {
                    var9_3 = false;
                }
                this.m = var9_3;
                this.h = new ArrayList<E>();
                if (var2_2 != null && var2_2 >= FindFriendsModulePlacer.c) {
                    var3_5 = var3_5 != 0 ? var1_1 : var2_2 - 1;
                    while (var3_5 < FindFriendsModulePlacer.b) {
                        this.h.add(var3_5);
                        var3_5 += var2_2.intValue();
                    }
                } else if (var3_5 != 0) {
                    this.h.add(var1_1);
                }
                this.i = new ArrayList<E>();
                this.k = new ArrayList<E>();
                if (this.h.size() != 0) break block13;
                this.i.add(0, 0);
                this.k.add(0, 0);
                ** GOTO lbl30
            }
            var7_6 = this.h.get(this.h.size() - 1);
            var8_7 = FindFriendsModulePlacer.f;
            var5_8 = 0;
            var3_5 = 0;
            do {
                block14 : {
                    if (var5_8 < var7_6 + var8_7) break block14;
lbl30: // 2 sources:
                    this.j = this.i.get(this.i.size() - 1);
                    this.l = this.k.get(this.k.size() - 1);
                    return;
                }
                if (var3_5 >= this.h.size()) {
                    this.i.add(var5_8, var4_4);
                    this.k.add(var4_4, var5_8);
                    var6_9 = var4_4;
                    var4_4 = var3_5;
                    var3_5 = var6_9;
                } else if (var5_8 == this.h.get(var3_5)) {
                    this.i.add(var5_8, FindFriendsModulePlacer.d);
                    var6_9 = var3_5 + 1;
                    var3_5 = var4_4;
                    var4_4 = var6_9;
                } else {
                    this.i.add(var5_8, var4_4);
                    this.k.add(var4_4, var5_8);
                    var6_9 = var4_4 + 1;
                    var4_4 = var3_5;
                    var3_5 = var6_9;
                }
                var6_9 = var5_8 + 1;
                var5_8 = var4_4;
                var4_4 = var3_5;
                var3_5 = var5_8;
                var5_8 = var6_9;
            } while (true);
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean a(int n) {
            if (!this.g || this.b(n) != d) {
                return false;
            }
            return true;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public int b(int n) {
            if (!this.g) {
                return n;
            }
            if (n <= this.l) return this.i.get(n);
            n -= this.l - this.j;
            return n;
        }

        public void b() {
            this.g = false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public int c(int n) {
            if (!this.g) {
                return n;
            }
            if (n < this.j) return this.k.get(n);
            n -= this.j - this.l;
            return n;
        }

        public boolean c() {
            if (!this.g) {
                return false;
            }
            return this.m;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean d() {
            if (!this.g || !this.m && this.h.size() <= 0) {
                return false;
            }
            return true;
        }

        /*
         * Enabled aggressive block sorting
         */
        public Integer e() {
            if (!this.g || this.h.size() <= 0) {
                return null;
            }
            return this.h.get(0);
        }

    }

    protected class FindFriendsRecyclerAdapter
    extends RecyclerView.Adapter
    implements MagicDataSource.DataSourceObserver {
        private RecommendedFriendsCachedDataSource b;
        private boolean c;

        FindFriendsRecyclerAdapter(RecommendedFriendsCachedDataSource recommendedFriendsCachedDataSource) {
            this.c = false;
            this.b = recommendedFriendsCachedDataSource;
            recommendedFriendsCachedDataSource.a(this);
        }

        public RecommendationManager.RecommendedSingersResponse.RecAccountIcon a(int n) {
            return (RecommendationManager.RecommendedSingersResponse.RecAccountIcon)this.b.a(n);
        }

        public void a() {
            block3 : {
                block2 : {
                    if (this.b.i() == MagicDataSource.DataState.c) break block2;
                    if (this.b.k() != 0 || !this.b.l()) break block3;
                    this.b.o();
                    this.c = true;
                }
                return;
            }
            this.c = true;
            this.c(this.b);
        }

        @Override
        public void a(MagicDataSource magicDataSource) {
        }

        @Override
        public void a(MagicDataSource magicDataSource, List<Object> list) {
        }

        @Override
        public void b(MagicDataSource magicDataSource) {
        }

        @Override
        public void c(MagicDataSource magicDataSource) {
            if (!this.c) {
                throw new RuntimeException(this.getClass().getName() + ": DataSource was not setup yet. This should not get called before the constructor is done.");
            }
            if (magicDataSource.i() == MagicDataSource.DataState.b) {
                this.notifyDataSetChanged();
            }
        }

        @Override
        public void d(MagicDataSource magicDataSource) {
        }

        public int getItemCount() {
            return this.b.k();
        }

        public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int n) {
            FindFriendsModuleListItem findFriendsModuleListItem = (FindFriendsModuleListItem)viewHolder.itemView;
            final RecommendationManager.RecommendedSingersResponse.RecAccountIcon recAccountIcon = (RecommendationManager.RecommendedSingersResponse.RecAccountIcon)this.b.a(n);
            findFriendsModuleListItem.a(FindFriendsModule.this.b, recAccountIcon, n, false, true, new UserFollowListItem.UserFollowListItemListener(){

                @Override
                public void a(Analytics searchResultClkContext) {
                    com.smule.android.logging.Analytics.a(recAccountIcon.mRecId, Analytics.d, viewHolder.getAdapterPosition(), Analytics.l, null);
                    SingAnalytics.b((SingAnalytics.SingModulePlacement)FindFriendsModule.this.f);
                }

                @Override
                public void a(ProfileFragment profileFragment) {
                    if (FindFriendsModule.this.c != null) {
                        FindFriendsModule.this.c.p().a(profileFragment, profileFragment.t());
                    }
                }

                @Override
                public void a(boolean bl, boolean bl2) {
                }
            });
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup object, int n) {
            object = FindFriendsModuleListItem.a(FindFriendsModule.this.b);
            n = FindFriendsModule.this.getResources().getDimensionPixelSize(2131428173);
            object.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(FindFriendsModule.this.d - n * 2, FindFriendsModule.this.d / 2));
            object.requestLayout();
            return new FindFriendsRecyclerViewHolder((View)object);
        }

        public class FindFriendsRecyclerViewHolder
        extends RecyclerView.ViewHolder {
            public FindFriendsRecyclerViewHolder(View view) {
                super(view);
            }
        }

    }

}

