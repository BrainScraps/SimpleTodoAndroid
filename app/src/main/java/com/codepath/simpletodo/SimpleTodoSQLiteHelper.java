package com.codepath.simpletodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by isaac on 12/20/14.
 */
public class SimpleTodoSQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_TODO_ITEMS = "todo_items";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TODO = "comment";
    public static final String COLUMN_IS_DONE = "is_done";

    private static final String DATABASE_NAME = "todo_items.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_TODO_ITEMS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TODO
            + " text not null, " + COLUMN_IS_DONE + "boolean);";

    public SimpleTodoSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SimpleTodoSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_ITEMS);
        onCreate(db);
    }
}
