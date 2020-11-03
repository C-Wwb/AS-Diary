package com.example.fauziahrizky.diary.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDatabase extends SQLiteOpenHelper {
    public static final String databaseName = "user.db";
    public static final String tableName = "userTable";
    public static final String col_1 = "id";
    public static final String col_2 = "author";
    public static final String col_3 = "subject";
    public static final String col_4 = "description";
    public static final String col_5 = "dateTime";

    //this constructor for creating the database
    public SqliteDatabase(Context context) {
        super(context, databaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //creating table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tableName +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "author TEXT,subject TEXT,description TEXT,dateTime Date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + databaseName);//drop table if exist
        onCreate(sqLiteDatabase); //and create new table
    }

    //function for inserting on sqlite database
    public long insertData(String author, String subject, String description, String dateTime) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();//for accessing database data
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, author);
        contentValues.put(col_3, subject);
        contentValues.put(col_4, description);
        contentValues.put(col_5, dateTime);
        return sqLiteDatabase.insert(tableName, null, contentValues);
    }
    public Cursor display(){
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();//for accessing database data
        return sqliteDatabase.rawQuery("select * from "+tableName, null);
    }
    //for updating database data
    public boolean update(String author,String subject,String description,String dateTime,String id){
        try{
            SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(col_1, id);
            contentValues.put(col_2, author);
            contentValues.put(col_3, subject);
            contentValues.put(col_4, description);
            contentValues.put(col_5, dateTime);
            sqliteDatabase.update(tableName,contentValues,col_1+" =?", new String[]{id});
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    //for deleting database data
    public boolean delete(String id){
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        sqliteDatabase.delete(tableName,col_1+" = ?",new String[]{id});
        return true;
    }
}
