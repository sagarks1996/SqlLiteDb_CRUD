package com.example.sqllitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {


    DatabaseHelper databaseHelper;

    public DatabaseAdapter(Context context) {

        databaseHelper = new DatabaseHelper(context);

    }

    public long insertdata(String username, String password){

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME,username);
        contentValues.put(DatabaseHelper.PASSWORD,password);
        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
        return id;

    }






    public String getdata(){

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String[] column = {DatabaseHelper.ID,DatabaseHelper.NAME,DatabaseHelper.PASSWORD};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_NAME,column,null,null,null,null,null);

        StringBuffer stringBuffer = new StringBuffer();

        while (cursor.moveToNext()){

            int columnid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));
            String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PASSWORD));
            stringBuffer.append(columnid +" "+ name +" "+ password +"\n");

        }
        return stringBuffer.toString();

    }



    public  int updatename(String oldname, String newname){

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME,newname);
        String[] wherArgs = {oldname};
        int count = sqLiteDatabase.update(DatabaseHelper.TABLE_NAME,contentValues,DatabaseHelper.NAME+ "=? ",wherArgs);
        return count;
    }


    public int deletefield (String username){

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String[] whereArgs = {username};

        int count = sqLiteDatabase.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.NAME + "=?",whereArgs);
        return count;
    }


}
