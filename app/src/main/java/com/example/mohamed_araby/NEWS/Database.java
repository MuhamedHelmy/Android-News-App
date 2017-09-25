package com.example.mohamed_araby.NEWS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mohamed_araby on 8/28/17.
 */

public class Database extends SQLiteOpenHelper {
    final  static String database_name="NEWSDB.db";
    final static int  database_version=1;
    final static String database_table_name="NEWS";
    final static String
                        col1="TITLE",
                        col2="URL";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + database_table_name + " (" +
                    col1+ " TEXT UNIQUE," +
                    col2+ " TEXT,"+
                    "ID "+"INTEGER PRIMARY KEY AUTOINCREMENT)";
            ;
    public Database(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+database_table_name);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String title ,String url){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,title);
        contentValues.put(col2,url);
       Long res= db.insert(database_table_name,null,contentValues);
        if(res==-1){
            return false;
        }
        return true;
    }


   public Cursor getalldata(){
       SQLiteDatabase db=this.getWritableDatabase();
       Cursor cursor=db.rawQuery("SELECT * FROM "+database_table_name,null);
       return  cursor;
   }


}
