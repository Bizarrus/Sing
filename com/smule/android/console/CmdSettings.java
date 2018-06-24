/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteOpenHelper
 */
package com.smule.android.console;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.smule.android.console.CFunc;
import java.util.LinkedHashMap;

public class CmdSettings {
    private static final String[][] a = new String[][]{{DBName.a.name(), "INTEGER PRIMARY KEY"}, {DBName.b.name(), "TEXT"}};
    private static final String[][] b = new String[][]{{DBName.c.name(), "TEXT PRIMARY KEY"}, {DBName.d.name(), "TEXT"}};
    private DatabaseHelper c = new DatabaseHelper((Context)CFunc.a());
    private SQLiteDatabase d = this.c.getWritableDatabase();

    public CmdSettings() {
        this.c(this.d() - 200);
    }

    private int d() {
        int n = 0;
        Cursor cursor = this.d.rawQuery("select max(" + DBName.a.name() + ") from " + "cmdhistory", null);
        int n2 = n;
        if (cursor != null) {
            n2 = n;
            if (cursor.moveToFirst()) {
                n2 = cursor.getInt(0);
            }
        }
        cursor.close();
        return n2;
    }

    private int e() {
        int n = 0;
        Cursor cursor = this.d.rawQuery("select min(" + DBName.a.name() + ") from " + "cmdhistory", null);
        int n2 = n;
        if (cursor != null) {
            n2 = n;
            if (cursor.moveToFirst()) {
                n2 = cursor.getInt(0);
            }
        }
        cursor.close();
        return n2;
    }

    public String a(int n) {
        Object var3_2 = null;
        Object object = this.d;
        String string2 = DBName.b.name();
        String string3 = DBName.a.name() + "=" + n;
        string2 = object.query("cmdhistory", new String[]{string2}, string3, null, null, null, null);
        object = var3_2;
        if (string2 != null) {
            object = var3_2;
            if (string2.moveToFirst()) {
                object = string2.getString(0);
            }
        }
        string2.close();
        return object;
    }

    public String a(String string2) {
        Object var2_2 = null;
        SQLiteDatabase sQLiteDatabase = this.d;
        String string3 = DBName.d.name();
        string2 = DBName.c.name() + "='" + string2 + "'";
        sQLiteDatabase = sQLiteDatabase.query("setting", new String[]{string3}, string2, null, null, null, null);
        string2 = var2_2;
        if (sQLiteDatabase != null) {
            string2 = var2_2;
            if (sQLiteDatabase.moveToFirst()) {
                string2 = sQLiteDatabase.getString(0);
            }
        }
        sQLiteDatabase.close();
        return string2;
    }

    public void a() {
        if (this.d != null) {
            this.d.close();
            this.d = null;
        }
        if (this.c != null) {
            this.c.close();
            this.c = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Setting setting, Object object) {
        String string2 = null;
        if (object instanceof Boolean) {
            string2 = ((Boolean)object).booleanValue() ? "T" : "F";
        } else if (object instanceof Integer) {
            string2 = String.valueOf((Integer)object);
        } else if (object instanceof Long) {
            string2 = String.valueOf((Long)object);
        } else if (object instanceof String) {
            string2 = (String)object;
        }
        if (string2 != null) {
            this.a(setting.name(), string2);
        }
    }

    public void a(String string2, String string3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBName.d.name(), string3);
        if (this.d.update("setting", contentValues, DBName.c.name() + "='" + string2 + "'", null) != 1) {
            contentValues.put(DBName.c.name(), string2);
            this.d.insert("setting", null, contentValues);
        }
    }

    public boolean a(Setting object) {
        if ((object = this.a(object.name())) == null) {
            return false;
        }
        return object.equalsIgnoreCase("T");
    }

    public int b(Setting object) {
        if ((object = this.a(object.name())) == null) {
            return 0;
        }
        return CFunc.a((String)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public LinkedHashMap<Integer, String> b(int n) {
        LinkedHashMap<Integer, String> linkedHashMap = null;
        int n2 = this.d();
        n = n > 0 ? n2 - n : n2 - 200;
        Cursor cursor = this.d.query("cmdhistory", null, DBName.a.name() + ">" + n, null, null, null, DBName.a.name() + " ASC");
        LinkedHashMap<Integer, String> linkedHashMap2 = linkedHashMap;
        if (cursor.getCount() > 0) {
            linkedHashMap2 = linkedHashMap;
            if (cursor.moveToFirst()) {
                linkedHashMap2 = new LinkedHashMap<Integer, String>();
                do {
                    linkedHashMap2.put(cursor.getInt(0), cursor.getString(1));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return linkedHashMap2;
    }

    public void b(String string2) {
        int n = this.d();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBName.a.name(), Integer.valueOf(n + 1));
        contentValues.put(DBName.b.name(), string2);
        this.d.insert("cmdhistory", null, contentValues);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void c(int n) {
        block5 : {
            block4 : {
                if (n < 0) break block4;
                if (n == 0) {
                    this.d.delete("cmdhistory", null, null);
                    return;
                }
                this.d.delete("cmdhistory", DBName.a.name() + "<=" + n, null);
                n = this.e();
                if (n > 1) break block5;
            }
            return;
        }
        this.d.execSQL("update cmdhistory set " + DBName.a.name() + " = " + DBName.a.name() + " - " + (n - 1));
    }

    private static enum DBName {
        a,
        b,
        c,
        d;
        

        private DBName() {
        }
    }

    private static class DatabaseHelper
    extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, "consoleSetting.db", null, 1);
        }

        private static String a(String string2, String[][] arrstring) {
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("CREATE TABLE ").append(string2).append(" (");
            for (int i = 0; i < arrstring.length; ++i) {
                stringBuilder.append(arrstring[i][0]).append(" ").append(arrstring[i][1]);
                if (i >= arrstring.length - 1) continue;
                stringBuilder.append(",");
            }
            stringBuilder.append(");");
            return stringBuilder.toString();
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(DatabaseHelper.a("setting", b));
            sQLiteDatabase.execSQL(DatabaseHelper.a("cmdhistory", a));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int n, int n2) {
        }
    }

    public static enum Setting {
        a,
        b;
        

        private Setting() {
        }
    }

}

