package com.example.multiapp.Preferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ToDoPreferences {

    private static final String PREF_NAME = "ToDoAppPrefs";
    private static final String TASK_LIST_KEY = "TaskList";

    private final SharedPreferences sharedPreferences;

    public ToDoPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    // Salvar tarefas no SharedPreferences
    public void saveTasks(ArrayList<String> taskList) {
        Gson gson = new Gson();
        String json = gson.toJson(taskList);
        sharedPreferences.edit().putString(TASK_LIST_KEY, json).apply();
    }

    // Carregar tarefas do SharedPreferences
    public ArrayList<String> loadTasks() {
        String json = sharedPreferences.getString(TASK_LIST_KEY, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
