package com.example.multiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.multiapp.databinding.ActivityCalculatorBinding;

public class CalculatorActivity extends AppCompatActivity {

    private ActivityCalculatorBinding binding;

    private EditText display;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Inicializando o binding
        binding = ActivityCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializando o display de resultado
        display = binding.display;

        //Configurando os botões
        setNumericButtons();
        setOperatorButtons();
    }

    private void setNumericButtons() {

        //recupera os ids dos botões numéricos
        int[] numericButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9
        };

        //configura o listener para os botões numéricos
        View.OnClickListener listener = view -> {
            Button button = (Button) view;
            currentInput += button.getText().toString();
            display.setText(currentInput);
        };

        for(int id: numericButtons){
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtons() {

        //recupera os ids dos botões de operador
        int[] operatorButtons = {
                R.id.btnAdd, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide, R.id.btnEqual, R.id.btnClear
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(this::handleOperator);
        }
    }

    private void handleOperator(View view) {


        Button button = (Button) view;
        String input = button.getText().toString();

        //verifica se o botão é um operador
        if (input.equals("C")) {
            // Limpar
            currentInput = "";
            operator = "";
            firstOperand = 0;
            display.setText("");
        } else if (input.equals("=")) {
            // Calcular
            if (!operator.isEmpty()) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = calculate(firstOperand, secondOperand, operator);
                display.setText(String.valueOf(result));
                currentInput = "";
                operator = "";
                firstOperand = 0;
            }
        } else {
            // Definir o operador
            firstOperand = Double.parseDouble(currentInput);
            operator = input.equals("x") ? "*" : input;
            currentInput = "";
        }
    }

    private double calculate(double firstOperand, double secondOperand, String operator) {

        // Calcula o resultado com base no operador
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                return firstOperand / secondOperand;
            default:
                return 0;
        }
    }

}