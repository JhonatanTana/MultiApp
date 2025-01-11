package com.example.multiapp.Model;

import androidx.annotation.NonNull;

public class Task {

    private final String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @NonNull
    public String toString() {
        return taskName + "|" + isCompleted;
    }

    public static Task fromString(String taskString) {
        String[] parts = taskString.split("\\|");
        Task task = new Task(parts[0]);
        task.setCompleted(Boolean.parseBoolean(parts[1]));
        return task;
    }
}
