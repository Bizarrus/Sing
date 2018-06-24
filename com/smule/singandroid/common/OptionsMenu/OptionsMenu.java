/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.support.annotation.VisibleForTesting
 *  android.widget.ArrayAdapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 */
package com.smule.singandroid.common.OptionsMenu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.VisibleForTesting;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SmuleException;
import com.smule.singandroid.common.OptionsMenu.MenuOption;
import com.smule.singandroid.common.OptionsMenu.OptionsMenuError;
import java.util.ArrayList;
import java.util.List;

public class OptionsMenu {
    private final ArrayList<MenuOption> a;
    private final ArrayList<String> b;
    private final MenuOption.OnClickListener c;
    private AlertDialog d;

    private OptionsMenu(ArrayList<MenuOption> arrayList, ArrayList<String> arrayList2, MenuOption.OnClickListener onClickListener) {
        this.a = arrayList;
        this.b = arrayList2;
        this.c = onClickListener;
    }

    public void a() {
        this.d.show();
    }

    public void b() {
        if (this.d != null) {
            this.d.dismiss();
        }
    }

    public static class Builder {
        @VisibleForTesting(otherwise=2)
        final ArrayList<MenuOption> a = new ArrayList();
        @VisibleForTesting(otherwise=2)
        final ArrayList<String> b = new ArrayList();
        @VisibleForTesting(otherwise=2)
        MenuOption.OnClickListener c = null;
        private String d;

        public Builder a(MenuOption.OnClickListener onClickListener) {
            this.c = onClickListener;
            return this;
        }

        public Builder a(String string2) {
            this.d = string2;
            return this;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public /* varargs */ Builder a(MenuOption ... arrmenuOption) {
            int n = arrmenuOption.length;
            int n2 = 0;
            while (n2 < n) {
                MenuOption menuOption = arrmenuOption[n2];
                try {
                    this.b.add(menuOption.a());
                    this.a.add(menuOption);
                }
                catch (SmuleException smuleException) {
                    smuleException.printStackTrace();
                }
                ++n2;
            }
            return this;
        }

        public OptionsMenu a(Context context) {
            final OptionsMenu optionsMenu = new OptionsMenu(this.a, this.b, this.c);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            context = new ArrayAdapter(context, 2130903079, (List)optionsMenu.b);
            context = builder.setTitle((CharSequence)this.d).setAdapter((ListAdapter)context, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    try {
                        ((MenuOption)optionsMenu.a.get(n)).a(optionsMenu);
                        return;
                    }
                    catch (SmuleException smuleException) {
                        if (smuleException.a() == OptionsMenuError.b) {
                            optionsMenu.b();
                            return;
                        }
                        smuleException.printStackTrace();
                        return;
                    }
                }
            }).create();
            context.getListView().setDividerHeight(0);
            context.getListView().setSelector(17170445);
            context.setOnCancelListener(new DialogInterface.OnCancelListener(){

                public void onCancel(DialogInterface dialogInterface) {
                    if (Builder.this.c != null) {
                        Builder.this.c.a(optionsMenu);
                    }
                }
            });
            optionsMenu.d = (AlertDialog)context;
            return optionsMenu;
        }

    }

}

