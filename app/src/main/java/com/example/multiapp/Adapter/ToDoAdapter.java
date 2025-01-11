package com.example.multiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiapp.Model.Task;
import com.example.multiapp.R;
import com.example.multiapp.ToDoActivity;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.TaskViewHolder>{
    private final List<Task> taskList;
    private final Context context;

    public ToDoAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    // Cria um novo ViewHolder
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    // Vincula os dados da tarefa ao ViewHolder
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskText.setText(task.getTaskName());
        holder.checkBox.setChecked(task.isCompleted());

        // Configurar um listener para o CheckBox
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            // Atualizar o estado das tarefas no SharedPreferences
            ((ToDoActivity) context).saveTasks();
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    // ViewHolder para a tarefa
    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        CheckBox checkBox;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
