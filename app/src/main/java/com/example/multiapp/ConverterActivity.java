package com.example.multiapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.multiapp.databinding.ActivityConverterBinding;

public class ConverterActivity extends AppCompatActivity {

    private ActivityConverterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        //Inicializando o binding
        binding = ActivityConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializando as views
        EditText inputValue = binding.inputValue;
        Spinner spinnerFrom = binding.spinnerFrom;
        Spinner spinnerTo = binding.spinnerTo;
        Button convertButton = binding.convertButton;
        TextView outputResult = binding.outputResult;

        // Definir os valores das unidades (aqui como exemplo, vamos converter temperatura)
        String[] temperatureUnits = {"Celsius", "Fahrenheit"};
        String[] distanceUnits = {"Metros", "Quilômetros"};

        // Definir adaptadores para os Spinners
        ArrayAdapter<String> temperatureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temperatureUnits);
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distanceUnits);

        // Definir o layout dos itens do Spinner
        temperatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Configurar o Spinner de unidades de entrada (spinnerFrom)
        spinnerFrom.setAdapter(temperatureAdapter); // Pode trocar entre temperatureAdapter e distanceAdapter
        spinnerTo.setAdapter(temperatureAdapter); // Pode trocar entre temperatureAdapter e distanceAdapter

        // Lógica para a conversão
        convertButton.setOnClickListener(v -> {
            String fromUnit = spinnerFrom.getSelectedItem().toString();
            String toUnit = spinnerTo.getSelectedItem().toString();

            String input = inputValue.getText().toString();

            // Verificar se o campo de entrada não está vazio
            if (!input.isEmpty()) {
                double value = Double.parseDouble(input);

                double result = convertUnits(value, fromUnit, toUnit);

                outputResult.setText("Resultado: " + result);
            } else {
                outputResult.setText("Por favor, insira um valor válido.");
            }
        });
    }

    // Metodo de conversão de unidades
    private double convertUnits(double value, String fromUnit, String toUnit) {
        double result = 0.0;

        // Conversão de temperatura
        if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            result = (value * 9/5) + 32;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = (value - 32) * 5/9;
        }

        // Conversão de distância
        else if (fromUnit.equals("Metros") && toUnit.equals("Quilômetros")) {
            result = value / 1000;
        } else if (fromUnit.equals("Quilômetros") && toUnit.equals("Metros")) {
            result = value * 1000;
        }

        return result;
    }
}