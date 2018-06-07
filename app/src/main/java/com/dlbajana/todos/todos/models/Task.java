package com.dlbajana.todos.todos.models;


public class Task {

    private int id;
    private String mTitle;
    private boolean mComplete;

    public Task(String title, boolean complete) {
        this.id = 0;
        this.mTitle = title;
        this.mComplete = complete;
    }

    public Task(int id, String mTitle, boolean mComplete) {
        this.id = id;
        this.mTitle = mTitle;
        this.mComplete = mComplete;
    }

    public Task(int id, String mTitle, int mComplete) {
        this.id = id;
        this.mTitle = mTitle;
        this.mComplete = (mComplete == 1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean isComplete() {
        return mComplete;
    }

    public void setComplete(boolean mComplete) {
        this.mComplete = mComplete;
    }

    public int isCompleteIntegerValue() {
        return isComplete() ? 1 : 0;
    }
}
