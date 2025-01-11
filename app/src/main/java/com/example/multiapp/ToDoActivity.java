package com.example.multiapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiapp.Adapter.ToDoAdapter;
import com.example.multiapp.Model.Task;
import com.example.multiapp.databinding.ActivityToDoBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ToDoActivity extends AppCompatActivity {

    private ActivityToDoBinding binding;
    private ArrayList<Task> taskList;
    private ToDoAdapter adapter;

    private static final String PREFS_NAME = "ToDoListPrefs";
    private static final String TASKS_KEY = "tasks";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inicializando o binding
        binding = ActivityToDoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Inicializando as views
        EditText editTask = binding.editTask;
        Button btnAdd = binding.btnAdd;
        RecyclerView taskRecyclerView = binding.taskRecyclerView;

        // Configurar RecyclerView
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Carregar tarefas salvas
        loadTasks();

        // Configurar o adaptador
        adapter = new ToDoAdapter(taskList, this);
        taskRecyclerView.setAdapter(adapter);

        // Adicionar nova tarefa
        btnAdd.setOnClickListener(v -> {
            String taskName = editTask.getText().toString().trim();
            // Verificar se a tarefa não está vazia
            if (!taskName.isEmpty()) {
                Task task = new Task(taskName);
                taskList.add(task);
                adapter.notifyDataSetChanged();
                editTask.setText("");
                saveTasks(); // Salvar a lista atualizada
            }
        });
    }

    private void loadTasks() {

        // Carregar tarefas do SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> taskSet = prefs.getStringSet(TASKS_KEY, new HashSet<>());
        taskList = new ArrayList<>();

        // Converter as strings de tarefas em objetos Task
        for (String taskString : taskSet) {
            taskList.add(Task.fromString(taskString));
        }
    }

    public void saveTasks() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> taskSet = new HashSet<>();

        for (Task task : taskList) {
            taskSet.add(task.toString());
        }

        editor.putStringSet(TASKS_KEY, taskSet);
        editor.apply();
    }
}