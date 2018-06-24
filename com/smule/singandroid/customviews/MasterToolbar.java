/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Color
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.support.v7.view.menu.ActionMenuItemView
 *  android.support.v7.view.menu.MenuItemImpl
 *  android.support.v7.widget.Toolbar
 *  android.util.AttributeSet
 *  android.view.ActionProvider
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MenuItem$OnActionExpandListener
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.SubMenu
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.PopupMenu
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 */
package com.smule.singandroid.customviews;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.singandroid.customviews.ActionBarCustomView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterToolbar
extends Toolbar {
    public ActionBarCustomView a;
    protected boolean b;
    protected Menu c;
    protected Object d;
    protected FadeMode e = FadeMode.a;
    protected final View.OnClickListener f;
    private boolean g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private ColorFader k;
    private Drawable l;
    private int m;

    public MasterToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                block5 : {
                    block4 : {
                        if (MasterToolbar.this.d == null || !((object = object.getTag(2131623964)) instanceof MenuItem) || !(object = (MenuItem)object).isEnabled()) break block4;
                        if (MasterToolbar.this.d instanceof Activity) {
                            ((Activity)MasterToolbar.this.d).onOptionsItemSelected((MenuItem)object);
                            return;
                        }
                        if (MasterToolbar.this.d instanceof Fragment) break block5;
                    }
                    return;
                }
                ((Fragment)MasterToolbar.this.d).onOptionsItemSelected((MenuItem)object);
            }
        };
        this.m = 0;
        this.c();
    }

    public MasterToolbar(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.f = new ;
        this.m = 0;
        this.c();
    }

    private Drawable a(Drawable drawable2) {
        if (drawable2 instanceof LayerDrawable) {
            return ((LayerDrawable)drawable2).findDrawableByLayerId(2131756830);
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void f() {
        if (this.j) {
            return;
        }
        int n = this.g ? (int)this.getResources().getDimension(2131428165) : 0;
        this.setPadding(0, 0, n, 0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.getLayoutParams();
        layoutParams.height = this.getResources().getDimensionPixelSize(2131427385);
        this.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int a(int n) {
        float f;
        ColorFader colorFader = this.k;
        if (this.h && this.i) {
            f = 1.0f;
            do {
                return colorFader.a(f);
                break;
            } while (true);
        }
        f = (float)n / 255.0f;
        return colorFader.a(f);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a() {
        if (this.c == null && this.b && this.d != null) {
            Menu menu2 = new PopupMenu(this.getContext(), null).getMenu();
            if (this.d instanceof Activity) {
                ((Activity)this.d).onCreateOptionsMenu(menu2);
            } else if (this.d instanceof Fragment) {
                ((Fragment)this.d).onCreateOptionsMenu(menu2, new MenuInflater(this.getContext()));
            }
            this.c = new MagicMenu(this, menu2);
        }
        this.b();
    }

    public void a(int n, boolean bl) {
        this.l = new ColorDrawable(n);
        this.setBackground(this.l);
        this.setLightIcons(bl);
        this.b(this.m, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(MenuItem menuItem) {
        block7 : {
            block6 : {
                if ((menuItem = menuItem.getActionView()) == null) break block6;
                if (menuItem instanceof TextView) {
                    menuItem = (TextView)menuItem;
                    menuItem.setTextColor(this.getResources().getColor(2131689581));
                    menuItem.setBackgroundColor(this.getResources().getColor(2131689503));
                    return;
                }
                if (menuItem instanceof ImageView) {
                    if (menuItem.findViewById(2131755235) != null) {
                        menuItem.setBackgroundColor(this.getResources().getColor(2131689503));
                        return;
                    }
                    ((ImageView)menuItem).setColorFilter(this.getResources().getColor(2131689503), PorterDuff.Mode.SRC_ATOP);
                    return;
                }
                if (menuItem.getId() == 2131755676) break block7;
            }
            return;
        }
        menuItem.setBackgroundColor(this.getResources().getColor(2131689503));
        ((TextView)menuItem.findViewById(2131755677)).setTextColor(this.getResources().getColor(2131689578));
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(ViewGroup viewGroup, int n, int n2, int n3) {
        int n4 = 0;
        while (n4 < viewGroup.getChildCount()) {
            View view = viewGroup.getChildAt(n4);
            if (view.getTag() == null || !view.getTag().equals("actionbar_nofade")) {
                if (view instanceof ActionMenuItemView) {
                    Drawable drawable2 = (view = (ActionMenuItemView)view).getItemData().getIcon();
                    if (drawable2 != null) {
                        drawable2.setColorFilter(n, PorterDuff.Mode.SRC_ATOP);
                        view.setIcon(drawable2);
                    }
                } else if (view instanceof TextView) {
                    if (this.e == FadeMode.c && view == this.getTitleView()) {
                        view.setAlpha((float)n2 / 255.0f);
                    } else {
                        view.setAlpha(1.0f);
                    }
                    ((TextView)view).setTextColor(n);
                    ((TextView)view).setHintTextColor(n);
                } else if (view instanceof ImageView) {
                    ((ImageView)view).setColorFilter(n);
                } else if (view instanceof ViewGroup) {
                    this.a((ViewGroup)view, n, n2, n3 + 1);
                }
            }
            ++n4;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Object object, boolean bl) {
        Object object2 = object;
        if (!(object instanceof Activity)) {
            object2 = object instanceof Fragment ? object : null;
        }
        this.d = object2;
        this.b = bl;
        this.c = null;
        this.a();
    }

    public void a(boolean bl) {
        this.b = bl;
        this.c = null;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b() {
        this.a.a();
        if (this.c == null) {
            return;
        }
        int n = 0;
        do {
            block11 : {
                ImageView imageView;
                MenuItem menuItem;
                block9 : {
                    block10 : {
                        block8 : {
                            if (n >= this.c.size()) {
                                this.a.invalidate();
                                return;
                            }
                            menuItem = this.c.getItem(n);
                            if (menuItem.getIcon() == null) break block8;
                            imageView = this.a.a(menuItem.getIcon());
                            break block9;
                        }
                        if (menuItem.getActionView() == null) break block10;
                        imageView = this.a.a(menuItem.getActionView());
                        break block9;
                    }
                    if (menuItem.getTitle() == null) break block11;
                    imageView = this.a.a(menuItem.getTitle());
                }
                if (menuItem.isEnabled()) {
                    this.b(menuItem);
                    imageView.setAlpha(1.0f);
                } else {
                    this.a(menuItem);
                    imageView.setAlpha(0.5f);
                }
                imageView.setTag(2131623964, (Object)menuItem);
                imageView.setOnClickListener(this.f);
                menuItem.setActionView((View)imageView);
                int n2 = menuItem.isVisible() ? 0 : 8;
                imageView.setVisibility(n2);
            }
            ++n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b(int n, boolean bl) {
        Drawable drawable2;
        block7 : {
            block6 : {
                if (this.m == n && !bl || this.k == null) break block6;
                this.m = n;
                if (this.e == FadeMode.c) {
                    this.getBackground().setAlpha(n);
                } else {
                    this.getBackground().setAlpha(255);
                }
                this.a((ViewGroup)this, this.a(n), n, 0);
                if (this.l != null && (drawable2 = this.a(this.l)) != null) break block7;
            }
            return;
        }
        if (this.e == FadeMode.c) {
            drawable2.setAlpha(0);
            return;
        }
        drawable2.setAlpha(255 - n);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b(MenuItem menuItem) {
        View view;
        block7 : {
            block6 : {
                view = menuItem.getActionView();
                if (view == null) break block6;
                if (view instanceof TextView) {
                    menuItem = (TextView)menuItem.getActionView();
                    menuItem.setTextColor(this.getResources().getColor(2131689577));
                    menuItem.setBackgroundColor(this.getResources().getColor(2131689500));
                    return;
                }
                if (view instanceof ImageView) {
                    if (view.findViewById(2131755235) != null) {
                        view.setBackgroundColor(this.getResources().getColor(2131689500));
                        return;
                    }
                    ((ImageView)view).setColorFilter(this.getResources().getColor(17170445), PorterDuff.Mode.ADD);
                    return;
                }
                if (view.getId() == 2131755676) break block7;
            }
            return;
        }
        view.setBackgroundColor(this.getResources().getColor(2131689500));
        ((TextView)view.findViewById(2131755677)).setTextColor(this.getResources().getColor(2131689577));
    }

    public void b(boolean bl) {
        this.j = bl;
        if (bl) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.getLayoutParams();
            layoutParams.height = 0;
            this.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.getLayoutParams();
        layoutParams.height = this.getResources().getDimensionPixelSize(2131427385);
        this.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }

    protected void c() {
        this.k = new ColorFader(this.getContext().getResources().getColor(2131689485), this.getContext().getResources().getColor(2131689486));
        this.e();
    }

    public void d() {
        this.e();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void e() {
        switch (.a[this.e.ordinal()]) {
            default: {
                this.l = this.getContext().getResources().getDrawable(2130837588);
                break;
            }
            case 1: {
                this.l = this.getResources().getDrawable(2131689516);
                break;
            }
            case 2: {
                if (this.l != null) break;
                this.l = this.getResources().getDrawable(2131689484);
            }
        }
        this.setBackground(this.l);
        this.m = 0;
    }

    public Menu getCustomMenu() {
        return this.c;
    }

    public FadeMode getFadeMode() {
        return this.e;
    }

    public TextView getPreSearchTitleView() {
        return this.a.getPreSearchTitleView();
    }

    public ImageView getPreSearchToolbarNavigationIconView() {
        return this.a.getPreSearchLeftButton();
    }

    public CharSequence getSubitle() {
        return this.a.getSubTitle();
    }

    public CharSequence getTitle() {
        if (this.a != null) {
            return this.a.getTitle();
        }
        return this.getResources().getString(2131296402);
    }

    public TextView getTitleView() {
        return this.a.getTitleView();
    }

    public ImageView getToolbarNavigationIconView() {
        return this.a.getLeftButton();
    }

    public ActionBarCustomView getToolbarView() {
        return this.a;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDisplayOptions(int n) {
        ActionBarCustomView actionBarCustomView = this.a;
        boolean bl = (n & 4) != 0;
        actionBarCustomView.setDisplayUpButton(bl);
    }

    public void setFadeMode(FadeMode fadeMode) {
        this.e = fadeMode;
        this.e();
    }

    public void setHasRightPadding(boolean bl) {
        this.g = bl;
        this.f();
    }

    public void setIsInProfile(boolean bl) {
        this.h = bl;
        this.f();
    }

    public void setLightIcons(boolean bl) {
        this.i = bl;
    }

    public void setSubtitle(int n) {
        this.a.setSubTitle(this.getResources().getString(n));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (this.a != null) {
            this.a.setSubTitle(charSequence);
        }
    }

    public void setTitle(int n) {
        this.a.setTitle(this.getResources().getString(n));
    }

    public void setTitle(CharSequence charSequence) {
        if (this.a != null) {
            this.a.setTitle(charSequence);
        }
    }

    public void setToolbarView(ActionBarCustomView actionBarCustomView) {
        this.a = actionBarCustomView;
    }

    public class ColorFader {
        int a;
        int b;

        public ColorFader(int n, int n2) {
            this.a = n;
            this.b = n2;
        }

        private int a(int n, int n2, float f) {
            return (int)((float)n + (float)(n2 - n) * f);
        }

        public int a(float f) {
            return Color.argb((int)255, (int)this.a(Color.red((int)this.a), Color.red((int)this.b), f), (int)this.a(Color.green((int)this.a), Color.green((int)this.b), f), (int)this.a(Color.blue((int)this.a), Color.blue((int)this.b), f));
        }
    }

    public static enum FadeMode {
        a,
        b,
        c;
        

        private FadeMode() {
        }
    }

    protected static class MagicMenu
    implements Menu {
        private final MasterToolbar a;
        private final Map<Integer, MenuItem> b = new HashMap<Integer, MenuItem>();
        private final List<MenuItem> c = new ArrayList<MenuItem>();

        protected MagicMenu(MasterToolbar object, Menu menu2) {
            this.a = object;
            for (int i = 0; i < menu2.size(); ++i) {
                object = new MagicMenuItem(this, menu2.getItem(i));
                this.c.add((MenuItem)object);
                this.b.put(object.getItemId(), (MenuItem)object);
            }
        }

        public void a(MenuItem menuItem) {
            this.a.b();
        }

        public MenuItem add(int n) {
            return null;
        }

        public MenuItem add(int n, int n2, int n3, int n4) {
            return null;
        }

        public MenuItem add(int n, int n2, int n3, CharSequence charSequence) {
            return null;
        }

        public MenuItem add(CharSequence charSequence) {
            return null;
        }

        public int addIntentOptions(int n, int n2, int n3, ComponentName componentName, Intent[] arrintent, Intent intent, int n4, MenuItem[] arrmenuItem) {
            return 0;
        }

        public SubMenu addSubMenu(int n) {
            return null;
        }

        public SubMenu addSubMenu(int n, int n2, int n3, int n4) {
            return null;
        }

        public SubMenu addSubMenu(int n, int n2, int n3, CharSequence charSequence) {
            return null;
        }

        public SubMenu addSubMenu(CharSequence charSequence) {
            return null;
        }

        public void clear() {
        }

        public void close() {
        }

        public MenuItem findItem(int n) {
            return this.b.get(n);
        }

        public MenuItem getItem(int n) {
            return this.c.get(n);
        }

        public boolean hasVisibleItems() {
            return false;
        }

        public boolean isShortcutKey(int n, KeyEvent keyEvent) {
            return false;
        }

        public boolean performIdentifierAction(int n, int n2) {
            return false;
        }

        public boolean performShortcut(int n, KeyEvent keyEvent, int n2) {
            return false;
        }

        public void removeGroup(int n) {
        }

        public void removeItem(int n) {
        }

        public void setGroupCheckable(int n, boolean bl, boolean bl2) {
        }

        public void setGroupEnabled(int n, boolean bl) {
        }

        public void setGroupVisible(int n, boolean bl) {
        }

        public void setQwertyMode(boolean bl) {
        }

        public int size() {
            return this.c.size();
        }
    }

    protected static class MagicMenuItem
    implements MenuItem {
        private MagicMenu a;
        private MenuItem b;

        protected MagicMenuItem(MagicMenu magicMenu, MenuItem menuItem) {
            this.a = magicMenu;
            this.b = menuItem;
        }

        public boolean collapseActionView() {
            return false;
        }

        public boolean expandActionView() {
            return false;
        }

        public ActionProvider getActionProvider() {
            return null;
        }

        public View getActionView() {
            return this.b.getActionView();
        }

        public char getAlphabeticShortcut() {
            return '\u0000';
        }

        public int getGroupId() {
            return this.b.getGroupId();
        }

        public Drawable getIcon() {
            return this.b.getIcon();
        }

        public Intent getIntent() {
            return null;
        }

        public int getItemId() {
            return this.b.getItemId();
        }

        public ContextMenu.ContextMenuInfo getMenuInfo() {
            return null;
        }

        public char getNumericShortcut() {
            return '\u0000';
        }

        public int getOrder() {
            return this.b.getOrder();
        }

        public SubMenu getSubMenu() {
            return null;
        }

        public CharSequence getTitle() {
            return this.b.getTitle();
        }

        public CharSequence getTitleCondensed() {
            return this.b.getTitleCondensed();
        }

        public boolean hasSubMenu() {
            return false;
        }

        public boolean isActionViewExpanded() {
            return false;
        }

        public boolean isCheckable() {
            return false;
        }

        public boolean isChecked() {
            return false;
        }

        public boolean isEnabled() {
            return this.b.isEnabled();
        }

        public boolean isVisible() {
            return this.b.isVisible();
        }

        public MenuItem setActionProvider(ActionProvider actionProvider) {
            return null;
        }

        public MenuItem setActionView(int n) {
            return null;
        }

        public MenuItem setActionView(View view) {
            this.b.setActionView(view);
            return this;
        }

        public MenuItem setAlphabeticShortcut(char c) {
            return null;
        }

        public MenuItem setCheckable(boolean bl) {
            return null;
        }

        public MenuItem setChecked(boolean bl) {
            return null;
        }

        public MenuItem setEnabled(boolean bl) {
            this.b.setEnabled(bl);
            this.a.a(this);
            return this;
        }

        public MenuItem setIcon(int n) {
            this.b.setIcon(n);
            return this;
        }

        public MenuItem setIcon(Drawable drawable2) {
            this.b.setIcon(drawable2);
            return this;
        }

        public MenuItem setIntent(Intent intent) {
            return null;
        }

        public MenuItem setNumericShortcut(char c) {
            return null;
        }

        public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
            return null;
        }

        public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            return null;
        }

        public MenuItem setShortcut(char c, char c2) {
            return null;
        }

        public void setShowAsAction(int n) {
        }

        public MenuItem setShowAsActionFlags(int n) {
            return null;
        }

        public MenuItem setTitle(int n) {
            this.b.setTitle(n);
            return this;
        }

        public MenuItem setTitle(CharSequence charSequence) {
            this.b.setTitle(charSequence);
            return this;
        }

        public MenuItem setTitleCondensed(CharSequence charSequence) {
            this.b.setTitleCondensed(charSequence);
            return this;
        }

        /*
         * Enabled aggressive block sorting
         */
        public MenuItem setVisible(boolean bl) {
            this.b.setVisible(bl);
            if (this.b.getActionView() != null) {
                View view = this.b.getActionView();
                int n = bl ? 0 : 8;
                view.setVisibility(n);
            }
            this.a.a(this);
            return this;
        }
    }

}

