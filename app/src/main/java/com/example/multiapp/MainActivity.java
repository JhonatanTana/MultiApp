package com.example.multiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.multiapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        //Inicializando o binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializando os botoes
        Button btn_Calculator = binding.btnCalculator;
        Button btn_ToDo = binding.btnToDo;
        Button btn_Converter = binding.btnConverter;

        //Abrindo as novas Activity
        btn_Calculator.setOnClickListener( v -> {
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
        });

        btn_ToDo.setOnClickListener( v -> {
            Intent intent = new Intent(this, ToDoActivity.class);
            startActivity(intent);
        });

        btn_Converter.setOnClickListener( v -> {
            Intent intent = new Intent(this, ConverterActivity.class);
            startActivity(intent);
        });

    }
}