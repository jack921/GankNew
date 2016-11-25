package com.gank.jack.ganknew.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.gank.jack.ganknew.base.MyApplication;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢汉杰
 */

public class GankSQLiteImpl {

    private static SQLiteDatabase gankdb=null;

    public static void getGankdb(){
        if(gankdb==null){
            GankSQLiteOpenHelper weatherSQLiteOpenHelper=
                    new GankSQLiteOpenHelper(MyApplication.getContext(),"gank.db",null,1);
            gankdb=weatherSQLiteOpenHelper.getReadableDatabase();
        }
    }

    //获取所有保存的数据
    public static List<Gank> getCollectGank() {
        try {
            getGankdb();
            Cursor cursor = gankdb.query("gank", null, null, null, null, null, null);
            List<Gank> listGank = new ArrayList<>();
            if (cursor.moveToFirst()) {
                int idColumn = cursor.getColumnIndex("id");
                int createdAtColumn = cursor.getColumnIndex("createAt");
                int descColumn = cursor.getColumnIndex("desc");
                int publishedAtColumn = cursor.getColumnIndex("publishedAt");
                int sourceColumn = cursor.getColumnIndex("source");
                int typeColumn = cursor.getColumnIndex("type");
                int urlColumn = cursor.getColumnIndex("url");
                int usedColumn = cursor.getColumnIndex("used");
                int whoColumn = cursor.getColumnIndex("who");
                do {
                    Gank gank = new Gank();
                    gank._id = cursor.getString(idColumn);
                    gank.createdAt = cursor.getString(createdAtColumn);
                    gank.desc = cursor.getString(descColumn);
                    gank.publishedAt = cursor.getString(publishedAtColumn);
                    gank.source = cursor.getString(sourceColumn);
                    gank.type = cursor.getString(typeColumn);
                    gank.url = cursor.getString(urlColumn);
                    gank.used = Boolean.getBoolean(cursor.getString(usedColumn));
                    gank.who = cursor.getString(whoColumn);
                    listGank.add(gank);
                } while (cursor.moveToNext());
                cursor.close();
                return listGank;
            }else{
                cursor.close();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //收藏gank
    public static boolean saveCollectGank(Gank gank){
        try{
            getGankdb();
            ContentValues values = new ContentValues();
            values.put("id", gank._id);
            values.put("createAt", gank.createdAt);
            values.put("url", gank.url);
            values.put("desc", gank.desc);
            values.put("publishedAt", gank.publishedAt);
            values.put("source", gank.source);
            values.put("type", gank.type);
            values.put("used", gank.used);
            values.put("who", gank.who);
            gankdb.insert("gank",null,values);
            return true;
        }catch (Exception e){
            LogUtil.e("gankexeception",e.getMessage());
            return false;
        }
    }

    //删除gank
    public static boolean deleteCollectGank(String id){
        try{
            getGankdb();
            gankdb.delete("gank","id=?",new String[]{id});
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //查看是否存在gank
    public static boolean queryCollectGank(String id){
        try{
            getGankdb();
            Cursor cursor = gankdb.query("gank", null,"id=?",new String[]{id}, null, null, null);
            if(cursor.moveToFirst()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }


}
