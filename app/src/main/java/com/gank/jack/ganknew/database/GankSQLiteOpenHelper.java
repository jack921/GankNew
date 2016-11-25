package com.gank.jack.ganknew.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 谢汉杰
 */

public class GankSQLiteOpenHelper extends SQLiteOpenHelper{

    private String createGank="create table gank("+
                    "id text,"+
                    "createAt text,"+
                    "desc text,"+
                    "publishedAt text,"+
                    "source text,"+
                    "type text,"+
                    "url text,"+
                    "used text,"+
                    "who text"+
                    ")";

    public GankSQLiteOpenHelper(Context context, String name,CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createGank);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
