package com.smule.singandroid.customviews;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.singandroid.C1947R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterToolbar extends Toolbar {
    public ActionBarCustomView f21591a;
    protected boolean f21592b;
    protected Menu f21593c;
    protected Object f21594d;
    protected boolean f21595e;
    protected final OnClickListener f21596f = new C43921(this);
    private ColorFader f21597g;
    private Drawable f21598h;
    private int f21599i = 0;

    class C43921 implements OnClickListener {
        final /* synthetic */ MasterToolbar f21582a;

        C43921(MasterToolbar masterToolbar) {
            this.f21582a = masterToolbar;
        }

        public void onClick(View view) {
            if (this.f21582a.f21594d != null) {
                Object tag = view.getTag(C1947R.integer.menu_item_tag);
                if (tag instanceof MenuItem) {
                    MenuItem menuItem = (MenuItem) tag;
                    if (!menuItem.isEnabled()) {
                        return;
                    }
                    if (this.f21582a.f21594d instanceof Activity) {
                        ((Activity) this.f21582a.f21594d).onOptionsItemSelected(menuItem);
                    } else if (this.f21582a.f21594d instanceof Fragment) {
                        ((Fragment) this.f21582a.f21594d).onOptionsItemSelected(menuItem);
                    }
                }
            }
        }
    }

    public class ColorFader {
        int f21583a;
        int f21584b;
        final /* synthetic */ MasterToolbar f21585c;

        public ColorFader(MasterToolbar masterToolbar, int i, int i2) {
            this.f21585c = masterToolbar;
            this.f21583a = i;
            this.f21584b = i2;
        }

        public int m23222a(float f) {
            return Color.argb(255, m23221a(Color.red(this.f21583a), Color.red(this.f21584b), f), m23221a(Color.green(this.f21583a), Color.green(this.f21584b), f), m23221a(Color.blue(this.f21583a), Color.blue(this.f21584b), f));
        }

        private int m23221a(int i, int i2, float f) {
            return (int) (((float) i) + (((float) (i2 - i)) * f));
        }
    }

    protected static class MagicMenu implements Menu {
        private final MasterToolbar f21586a;
        private final Map<Integer, MenuItem> f21587b = new HashMap();
        private final List<MenuItem> f21588c = new ArrayList();

        protected MagicMenu(MasterToolbar masterToolbar, Menu menu) {
            this.f21586a = masterToolbar;
            for (int i = 0; i < menu.size(); i++) {
                MagicMenuItem magicMenuItem = new MagicMenuItem(this, menu.getItem(i));
                this.f21588c.add(magicMenuItem);
                this.f21587b.put(Integer.valueOf(magicMenuItem.getItemId()), magicMenuItem);
            }
        }

        public MenuItem add(CharSequence charSequence) {
            return null;
        }

        public MenuItem add(int i) {
            return null;
        }

        public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
            return null;
        }

        public MenuItem add(int i, int i2, int i3, int i4) {
            return null;
        }

        public SubMenu addSubMenu(CharSequence charSequence) {
            return null;
        }

        public SubMenu addSubMenu(int i) {
            return null;
        }

        public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
            return null;
        }

        public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
            return null;
        }

        public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
            return 0;
        }

        public void removeItem(int i) {
        }

        public void removeGroup(int i) {
        }

        public void clear() {
        }

        public void setGroupCheckable(int i, boolean z, boolean z2) {
        }

        public void setGroupVisible(int i, boolean z) {
        }

        public void setGroupEnabled(int i, boolean z) {
        }

        public boolean hasVisibleItems() {
            return false;
        }

        public MenuItem findItem(int i) {
            return (MenuItem) this.f21587b.get(Integer.valueOf(i));
        }

        public int size() {
            return this.f21588c.size();
        }

        public MenuItem getItem(int i) {
            return (MenuItem) this.f21588c.get(i);
        }

        public void close() {
        }

        public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
            return false;
        }

        public boolean isShortcutKey(int i, KeyEvent keyEvent) {
            return false;
        }

        public boolean performIdentifierAction(int i, int i2) {
            return false;
        }

        public void setQwertyMode(boolean z) {
        }

        public void m23223a(MenuItem menuItem) {
            this.f21586a.m23232b();
        }
    }

    protected static class MagicMenuItem implements MenuItem {
        private MagicMenu f21589a;
        private MenuItem f21590b;

        protected MagicMenuItem(MagicMenu magicMenu, MenuItem menuItem) {
            this.f21589a = magicMenu;
            this.f21590b = menuItem;
        }

        public int getItemId() {
            return this.f21590b.getItemId();
        }

        public int getGroupId() {
            return this.f21590b.getGroupId();
        }

        public int getOrder() {
            return this.f21590b.getOrder();
        }

        public MenuItem setTitle(CharSequence charSequence) {
            this.f21590b.setTitle(charSequence);
            return this;
        }

        public MenuItem setTitle(int i) {
            this.f21590b.setTitle(i);
            return this;
        }

        public CharSequence getTitle() {
            return this.f21590b.getTitle();
        }

        public MenuItem setTitleCondensed(CharSequence charSequence) {
            this.f21590b.setTitleCondensed(charSequence);
            return this;
        }

        public CharSequence getTitleCondensed() {
            return this.f21590b.getTitleCondensed();
        }

        public MenuItem setIcon(Drawable drawable) {
            this.f21590b.setIcon(drawable);
            return this;
        }

        public MenuItem setIcon(int i) {
            this.f21590b.setIcon(i);
            return this;
        }

        public Drawable getIcon() {
            return this.f21590b.getIcon();
        }

        public MenuItem setIntent(Intent intent) {
            return null;
        }

        public Intent getIntent() {
            return null;
        }

        public MenuItem setShortcut(char c, char c2) {
            return null;
        }

        public MenuItem setNumericShortcut(char c) {
            return null;
        }

        public char getNumericShortcut() {
            return '\u0000';
        }

        public MenuItem setAlphabeticShortcut(char c) {
            return null;
        }

        public char getAlphabeticShortcut() {
            return '\u0000';
        }

        public MenuItem setCheckable(boolean z) {
            return null;
        }

        public boolean isCheckable() {
            return false;
        }

        public MenuItem setChecked(boolean z) {
            return null;
        }

        public boolean isChecked() {
            return false;
        }

        public MenuItem setVisible(boolean z) {
            this.f21590b.setVisible(z);
            if (this.f21590b.getActionView() != null) {
                this.f21590b.getActionView().setVisibility(z ? 0 : 8);
            }
            this.f21589a.m23223a(this);
            return this;
        }

        public boolean isVisible() {
            return this.f21590b.isVisible();
        }

        public MenuItem setEnabled(boolean z) {
            this.f21590b.setEnabled(z);
            this.f21589a.m23223a(this);
            return this;
        }

        public boolean isEnabled() {
            return this.f21590b.isEnabled();
        }

        public boolean hasSubMenu() {
            return false;
        }

        public SubMenu getSubMenu() {
            return null;
        }

        public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
            return null;
        }

        public ContextMenuInfo getMenuInfo() {
            return null;
        }

        public void setShowAsAction(int i) {
        }

        public MenuItem setShowAsActionFlags(int i) {
            return null;
        }

        public MenuItem setActionView(View view) {
            this.f21590b.setActionView(view);
            return this;
        }

        public MenuItem setActionView(int i) {
            return null;
        }

        public View getActionView() {
            return this.f21590b.getActionView();
        }

        public MenuItem setActionProvider(ActionProvider actionProvider) {
            return null;
        }

        public ActionProvider getActionProvider() {
            return null;
        }

        public boolean expandActionView() {
            return false;
        }

        public boolean collapseActionView() {
            return false;
        }

        public boolean isActionViewExpanded() {
            return false;
        }

        public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
            return null;
        }
    }

    public MasterToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23234c();
    }

    public MasterToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23234c();
    }

    public ImageView getToolbarNavigationIconView() {
        return this.f21591a.getLeftButton();
    }

    public ImageView getPreSearchToolbarNavigationIconView() {
        return this.f21591a.getPreSearchLeftButton();
    }

    public TextView getTitleView() {
        return this.f21591a.getTitleView();
    }

    public TextView getPreSearchTitleView() {
        return this.f21591a.getPreSearchTitleView();
    }

    public void setDisplayOptions(int i) {
        this.f21591a.setDisplayUpButton((i & 4) != 0);
    }

    public CharSequence getTitle() {
        return this.f21591a != null ? this.f21591a.getTitle() : getResources().getString(C1947R.string.app_name);
    }

    public void setTitle(int i) {
        this.f21591a.setTitle(getResources().getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (this.f21591a != null) {
            this.f21591a.setTitle(charSequence);
        }
    }

    public CharSequence getSubitle() {
        return this.f21591a.getSubTitle();
    }

    public void setSubtitle(int i) {
        this.f21591a.setSubTitle(getResources().getString(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (this.f21591a != null) {
            this.f21591a.setSubTitle(charSequence);
        }
    }

    public void m23231a(boolean z) {
        this.f21592b = z;
        this.f21593c = null;
    }

    public Menu getCustomMenu() {
        return this.f21593c;
    }

    public void m23226a() {
        if (this.f21593c == null && this.f21592b && this.f21594d != null) {
            Menu menu = new PopupMenu(getContext(), null).getMenu();
            if (this.f21594d instanceof Activity) {
                ((Activity) this.f21594d).onCreateOptionsMenu(menu);
            } else if (this.f21594d instanceof Fragment) {
                ((Fragment) this.f21594d).onCreateOptionsMenu(menu, new MenuInflater(getContext()));
            }
            this.f21593c = new MagicMenu(this, menu);
        }
        m23232b();
    }

    protected void m23232b() {
        this.f21591a.m23109a();
        if (this.f21593c != null) {
            for (int i = 0; i < this.f21593c.size(); i++) {
                View a;
                int i2;
                MenuItem item = this.f21593c.getItem(i);
                if (item.getIcon() != null) {
                    a = this.f21591a.m23107a(item.getIcon());
                } else if (item.getActionView() != null) {
                    a = this.f21591a.m23106a(item.getActionView());
                } else if (item.getTitle() != null) {
                    a = this.f21591a.m23108a(item.getTitle());
                } else {
                }
                if (item.isEnabled()) {
                    m23233b(item);
                    a.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    m23228a(item);
                    a.setAlpha(0.5f);
                }
                a.setTag(C1947R.integer.menu_item_tag, item);
                a.setOnClickListener(this.f21596f);
                item.setActionView(a);
                if (item.isVisible()) {
                    i2 = 0;
                } else {
                    i2 = 8;
                }
                a.setVisibility(i2);
            }
            this.f21591a.invalidate();
        }
    }

    protected void m23228a(MenuItem menuItem) {
        View actionView = menuItem.getActionView();
        if (actionView != null) {
            if (actionView instanceof TextView) {
                TextView textView = (TextView) actionView;
                textView.setTextColor(getResources().getColor(C1947R.color.button_toolbar_text_disabled));
                textView.setBackgroundColor(getResources().getColor(C1947R.color.background_button_grey));
            } else if (actionView instanceof ImageView) {
                if (actionView.findViewById(C1947R.id.button_next) != null) {
                    actionView.setBackgroundColor(getResources().getColor(C1947R.color.background_button_grey));
                } else {
                    ((ImageView) actionView).setColorFilter(getResources().getColor(C1947R.color.background_button_grey), Mode.SRC_ATOP);
                }
            } else if (actionView.getId() == C1947R.id.action_bar_done_button) {
                actionView.setBackgroundColor(getResources().getColor(C1947R.color.background_button_grey));
                ((TextView) actionView.findViewById(C1947R.id.button_done)).setTextColor(getResources().getColor(C1947R.color.button_text_disabled));
            }
        }
    }

    protected void m23233b(MenuItem menuItem) {
        View actionView = menuItem.getActionView();
        if (actionView != null) {
            if (actionView instanceof TextView) {
                TextView textView = (TextView) menuItem.getActionView();
                textView.setTextColor(getResources().getColor(C1947R.color.button_text));
                textView.setBackgroundColor(getResources().getColor(C1947R.color.background_button));
            } else if (actionView instanceof ImageView) {
                if (actionView.findViewById(C1947R.id.button_next) != null) {
                    actionView.setBackgroundColor(getResources().getColor(C1947R.color.background_button));
                } else {
                    ((ImageView) actionView).setColorFilter(getResources().getColor(17170445), Mode.ADD);
                }
            } else if (actionView.getId() == C1947R.id.action_bar_done_button) {
                actionView.setBackgroundColor(getResources().getColor(C1947R.color.background_button));
                ((TextView) actionView.findViewById(C1947R.id.button_done)).setTextColor(getResources().getColor(C1947R.color.button_text));
            }
        }
    }

    protected void m23234c() {
        this.f21597g = new ColorFader(this, getContext().getResources().getColor(C1947R.color.action_bar_content), getContext().getResources().getColor(C1947R.color.action_bar_content_highlight));
        m23236e();
    }

    public void setToolbarView(ActionBarCustomView actionBarCustomView) {
        this.f21591a = actionBarCustomView;
    }

    public ActionBarCustomView getToolbarView() {
        return this.f21591a;
    }

    public void m23235d() {
        m23236e();
    }

    public void setWhiteMode(boolean z) {
        this.f21595e = z;
        m23236e();
    }

    public void setActiveComponent(Object obj) {
        m23230a(obj, false);
    }

    public void m23230a(Object obj, boolean z) {
        if (!((obj instanceof Activity) || (obj instanceof Fragment))) {
            obj = null;
        }
        this.f21594d = obj;
        this.f21592b = z;
        this.f21593c = null;
        m23226a();
    }

    public void m23236e() {
        if (this.f21595e) {
            this.f21598h = getResources().getDrawable(C1947R.color.background_white);
        } else {
            this.f21598h = getContext().getResources().getDrawable(C1947R.drawable.action_bar_background);
        }
        setBackground(this.f21598h);
        this.f21599i = 0;
    }

    private Drawable m23224a(Drawable drawable) {
        if (drawable instanceof LayerDrawable) {
            return ((LayerDrawable) drawable).findDrawableByLayerId(C1947R.id.action_bar_top_drawable);
        }
        return null;
    }

    public int m23225a(int i) {
        return this.f21597g.m23222a(((float) i) / 255.0f);
    }

    public void m23227a(int i, boolean z) {
        if ((this.f21599i != i || z) && this.f21597g != null) {
            this.f21599i = i;
            m23229a(this, m23225a(i), 0);
            if (this.f21598h != null) {
                Drawable a = m23224a(this.f21598h);
                if (a != null) {
                    a.setAlpha(255 - i);
                }
            }
        }
    }

    protected void m23229a(ViewGroup viewGroup, int i, int i2) {
        for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if (childAt.getTag() == null || !childAt.getTag().equals("actionbar_nofade")) {
                if (childAt instanceof ActionMenuItemView) {
                    ActionMenuItemView actionMenuItemView = (ActionMenuItemView) childAt;
                    Drawable icon = actionMenuItemView.getItemData().getIcon();
                    if (icon != null) {
                        icon.setColorFilter(i, Mode.SRC_ATOP);
                        actionMenuItemView.setIcon(icon);
                    }
                } else if (childAt instanceof TextView) {
                    ((TextView) childAt).setTextColor(i);
                    ((TextView) childAt).setHintTextColor(i);
                } else if (childAt instanceof ImageView) {
                    ((ImageView) childAt).setColorFilter(i);
                } else if (childAt instanceof ViewGroup) {
                    m23229a((ViewGroup) childAt, i, i2 + 1);
                }
            }
        }
    }
}
