package com.example.dipen.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dipen on 2/20/2018.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "Message.db";
    private final static int VERSION_NUM = 3;
    public final static String TABLE_NAME = "TB_CHAT";
    public final static String KEY_ID = "_id";
    public final static String KEY_MESSAGE = "message";


    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null , VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +KEY_MESSAGE+" TEXT );");
        Log.i("ChatDatabaseHelper","Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + i + " newVersion= " + i1);
    }
}
