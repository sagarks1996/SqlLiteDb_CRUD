package com.example.sqllitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DATABASE NAME";
    public static final int DATABASE_Version = 1;
    public static final String TABLE_NAME = "usertable";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    public static final String CREATE_TABLE = " CREATE TABLE " +TABLE_NAME+ "( " +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            +NAME+ " VARCHAR(255)," +PASSWORD+ " VARCHAR(255));";

  //  public static final String  CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ "(" +ID+"INTEGER PRIMARY KEY AUTO INCREMENT,"
     //       +NAME+ "VARCHAR(255),"+PASSWORD+"VARCHAR(255));";

    public static  final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;
    public final Context context;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_Version);
        this.context = context;
        Log.d("insertion","query:"+CREATE_TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Log.d("insertion","table created");
            Toast.makeText(context,"done",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.d("insertion","table creation failed"+e);
            Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"done",Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
        }

    }
}
