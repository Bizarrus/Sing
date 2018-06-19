package com.smule.android.console;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedHashMap;

public class CmdSettings {
    private static final String[][] f15719a;
    private static final String[][] f15720b;
    private DatabaseHelper f15721c = new DatabaseHelper(CFunc.m17511a());
    private SQLiteDatabase f15722d = this.f15721c.getWritableDatabase();

    private enum DBName {
        history_id,
        cmd_string,
        s_name,
        s_value
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, "consoleSetting.db", null, 1);
        }

        private static String m17519a(String str, String[][] strArr) {
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("CREATE TABLE ").append(str).append(" (");
            for (int i = 0; i < strArr.length; i++) {
                stringBuilder.append(strArr[i][0]).append(" ").append(strArr[i][1]);
                if (i < strArr.length - 1) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append(");");
            return stringBuilder.toString();
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(m17519a("setting", CmdSettings.f15720b));
            sQLiteDatabase.execSQL(m17519a("cmdhistory", CmdSettings.f15719a));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    public enum Setting {
        ls_full_mode,
        console_font_size
    }

    static {
        r0 = new String[2][];
        r0[0] = new String[]{DBName.history_id.name(), "INTEGER PRIMARY KEY"};
        r0[1] = new String[]{DBName.cmd_string.name(), "TEXT"};
        f15719a = r0;
        r0 = new String[2][];
        r0[0] = new String[]{DBName.s_name.name(), "TEXT PRIMARY KEY"};
        r0[1] = new String[]{DBName.s_value.name(), "TEXT"};
        f15720b = r0;
    }

    public CmdSettings() {
        m17533c(m17522d() - 200);
    }

    public void m17526a() {
        if (this.f15722d != null) {
            this.f15722d.close();
            this.f15722d = null;
        }
        if (this.f15721c != null) {
            this.f15721c.close();
            this.f15721c = null;
        }
    }

    public void m17527a(Setting setting, Object obj) {
        String str = null;
        if (obj instanceof Boolean) {
            str = ((Boolean) obj).booleanValue() ? "T" : "F";
        } else if (obj instanceof Integer) {
            str = String.valueOf((Integer) obj);
        } else if (obj instanceof Long) {
            str = String.valueOf((Long) obj);
        } else if (obj instanceof String) {
            str = (String) obj;
        }
        if (str != null) {
            m17528a(setting.name(), str);
        }
    }

    public void m17528a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBName.s_value.name(), str2);
        if (this.f15722d.update("setting", contentValues, DBName.s_name.name() + "='" + str + "'", null) != 1) {
            contentValues.put(DBName.s_name.name(), str);
            this.f15722d.insert("setting", null, contentValues);
        }
    }

    public boolean m17529a(Setting setting) {
        String a = m17525a(setting.name());
        if (a == null) {
            return false;
        }
        return a.equalsIgnoreCase("T");
    }

    public int m17530b(Setting setting) {
        String a = m17525a(setting.name());
        if (a == null) {
            return 0;
        }
        return CFunc.m17510a(a);
    }

    public String m17525a(String str) {
        String str2 = null;
        Cursor query = this.f15722d.query("setting", new String[]{DBName.s_value.name()}, DBName.s_name.name() + "='" + str + "'", null, null, null, null);
        if (query != null && query.moveToFirst()) {
            str2 = query.getString(0);
        }
        query.close();
        return str2;
    }

    public void m17532b(String str) {
        int d = m17522d() + 1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBName.history_id.name(), Integer.valueOf(d));
        contentValues.put(DBName.cmd_string.name(), str);
        this.f15722d.insert("cmdhistory", null, contentValues);
    }

    public String m17524a(int i) {
        String str = null;
        Cursor query = this.f15722d.query("cmdhistory", new String[]{DBName.cmd_string.name()}, DBName.history_id.name() + "=" + i, null, null, null, null);
        if (query != null && query.moveToFirst()) {
            str = query.getString(0);
        }
        query.close();
        return str;
    }

    public LinkedHashMap<Integer, String> m17531b(int i) {
        LinkedHashMap<Integer, String> linkedHashMap = null;
        int d = m17522d();
        Cursor query = this.f15722d.query("cmdhistory", null, DBName.history_id.name() + ">" + (i > 0 ? d - i : d - 200), null, null, null, DBName.history_id.name() + " ASC");
        if (query.getCount() <= 0 || !query.moveToFirst()) {
            query.close();
            return linkedHashMap;
        }
        linkedHashMap = new LinkedHashMap();
        do {
            linkedHashMap.put(Integer.valueOf(query.getInt(0)), query.getString(1));
        } while (query.moveToNext());
        query.close();
        return linkedHashMap;
    }

    private int m17522d() {
        int i = 0;
        Cursor rawQuery = this.f15722d.rawQuery("select max(" + DBName.history_id.name() + ") from " + "cmdhistory", null);
        if (rawQuery != null && rawQuery.moveToFirst()) {
            i = rawQuery.getInt(0);
        }
        rawQuery.close();
        return i;
    }

    private int m17523e() {
        int i = 0;
        Cursor rawQuery = this.f15722d.rawQuery("select min(" + DBName.history_id.name() + ") from " + "cmdhistory", null);
        if (rawQuery != null && rawQuery.moveToFirst()) {
            i = rawQuery.getInt(0);
        }
        rawQuery.close();
        return i;
    }

    public void m17533c(int i) {
        if (i >= 0) {
            if (i == 0) {
                this.f15722d.delete("cmdhistory", null, null);
                return;
            }
            this.f15722d.delete("cmdhistory", DBName.history_id.name() + "<=" + i, null);
            int e = m17523e();
            if (e > 1) {
                this.f15722d.execSQL("update cmdhistory set " + DBName.history_id.name() + " = " + DBName.history_id.name() + " - " + (e - 1));
            }
        }
    }
}
