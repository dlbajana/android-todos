package com.dlbajana.todos.todos;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dlbajana.todos.todos.database.TaskDataSource;
import com.dlbajana.todos.todos.dialogs.SampleDialog;
import com.dlbajana.todos.todos.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SampleDialog.SampleDialogListener, TodoRecyclerAdapter.TodoViewHolder.TodoActionListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Task> mTasks;
    private TaskDataSource mTaskDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Task newTask = new Task("Go to the Store", false);
//        mTasks.add(newTask);

        mTaskDataSource = new TaskDataSource(this);

        mTasks = mTaskDataSource.read();

        mRecyclerView = findViewById(R.id.recycler_view_todos);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new TodoRecyclerAdapter(this, mTasks);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            showAddDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAddDialog() {
        SampleDialog sampleDialog = new SampleDialog();
        sampleDialog.show(getSupportFragmentManager(), "SampleDialog");
    }

    public void addTaskFromList(String title) {
        Task newTask = new Task(title, false);
        mTasks.add(newTask);

        mTaskDataSource.create(newTask);

        mAdapter.notifyItemInserted(mTasks.size());
    }

    public void removeTaskFromList(int position, Task task) {
        mTasks.remove(position);
        mTaskDataSource.delete(task);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onDialogPositiveClick(DialogInterface dialog, int id, String title) {
        addTaskFromList(title);
    }

    @Override
    public void onDialogNegativeClick(DialogInterface dialog, int id) {
        Toast.makeText(this, "You cancel the dialog box!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTodoDeleteClick(int position, Task task) {
        removeTaskFromList(position, task);
    }
}
