package com.dlbajana.todos.todos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class TodosSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "todos.db";
    private static final int DB_VERSION = 19;

    // TASKS TABLE STRUCTURE
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_TASKS_ID = "id";
    public static final String COLUMN_TASKS_TITLE = "title";
    public static final String COLUMN_TASKS_COMPLETE = "complete";

    public static final String CREATE_TASKS =
            "CREATE TABLE " + TABLE_TASKS + "(" +
                    COLUMN_TASKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASKS_TITLE + " VARCHAR(255), " +
                    COLUMN_TASKS_COMPLETE + " INTEGER(11))";

    private static final String DELETE_TASKS =
            "DROP TABLE IF EXISTS " + TABLE_TASKS;

    TodosSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TASKS);
        onCreate(db);
    }
}
