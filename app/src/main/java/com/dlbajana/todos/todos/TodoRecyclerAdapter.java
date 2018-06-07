package com.dlbajana.todos.todos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dlbajana.todos.todos.database.TaskDataSource;
import com.dlbajana.todos.todos.database.TodosSQLiteHelper;
import com.dlbajana.todos.todos.models.Task;

import java.util.List;


public class TodoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Task> mTasks;
    private TodoViewHolder.TodoActionListener mActionListener;
    private TaskDataSource mTaskDataSource;

    public TodoRecyclerAdapter(TodoViewHolder.TodoActionListener actionListener, List<Task> tasks) {
        this.mTasks = tasks;
        this.mActionListener = actionListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task, parent, false);
        return new TodoViewHolder(todoViewHolder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TodoViewHolder) holder).bind(mTasks.get(position), position, mActionListener);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {

        public interface TodoActionListener {
            void onTodoDeleteClick(int position, Task task);
        }

        private TodoActionListener mTodoActionListener;
        private TextView tvTitle;
        private Button btnDelete;
        private int mPosition;
        private Task mTask;

        TodoViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.todo_title);
            btnDelete = itemView.findViewById(R.id.todo_delete_button);


            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTodoActionListener.onTodoDeleteClick(mPosition, mTask);
                }
            });
        }

        public void bind(Task task, int position, TodoActionListener todoActionListener) {
            tvTitle.setText(task.getTitle());
            this.mTask = task;
            this.mPosition = position;
            this.mTodoActionListener = todoActionListener;
        }
    }
}
