package com.dlbajana.todos.todos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.dlbajana.todos.todos.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDataSource {

    private Context mContext;
    private TodosSQLiteHelper mTodosSQLiteHelper;

    public TaskDataSource(Context context) {
        this.mContext = context;
        mTodosSQLiteHelper = new TodosSQLiteHelper(context);
    }

    private SQLiteDatabase open() {
        return mTodosSQLiteHelper.getReadableDatabase();
    }

    private void close(SQLiteDatabase database) {
        database.close();
    }

    public void create(Task task) {
        SQLiteDatabase database = open();
        database.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TodosSQLiteHelper.COLUMN_TASKS_TITLE, task.getTitle());
        contentValues.put(TodosSQLiteHelper.COLUMN_TASKS_COMPLETE, task.isCompleteIntegerValue());

        database.insert(TodosSQLiteHelper.TABLE_TASKS, null, contentValues);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public List<Task> read() {
        List<Task> tasks = new ArrayList<>();

        SQLiteDatabase database = open();

        String query = "SELECT " + TodosSQLiteHelper.COLUMN_TASKS_ID + ", " +
                TodosSQLiteHelper.COLUMN_TASKS_TITLE + ", " +
                TodosSQLiteHelper.COLUMN_TASKS_COMPLETE + " FROM " +
                TodosSQLiteHelper.TABLE_TASKS + ";";

        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Task task = new Task(
                    cursor.getInt(cursor.getColumnIndex(TodosSQLiteHelper.COLUMN_TASKS_ID)),
                    cursor.getString(cursor.getColumnIndex(TodosSQLiteHelper.COLUMN_TASKS_TITLE)),
                    cursor.getInt(cursor.getColumnIndex(TodosSQLiteHelper.COLUMN_TASKS_COMPLETE))
            );

            tasks.add(task);
        }

        cursor.close();
        close(database);
        return tasks;
    }

    public void delete(Task task) {
        SQLiteDatabase database = open();
        database.beginTransaction();

        String query = "DELETE FROM " + TodosSQLiteHelper.TABLE_TASKS + " WHERE " +
                TodosSQLiteHelper.COLUMN_TASKS_ID + " = " + task.getId() + ";";

        database.execSQL(query);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }
}
