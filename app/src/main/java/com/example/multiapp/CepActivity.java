package com.example.multiapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.multiapp.Model.Endereco;
import com.example.multiapp.Model.RetrofitHelper;
import com.example.multiapp.Service.CEPService;
import com.example.multiapp.databinding.ActivityCepBinding;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CepActivity extends AppCompatActivity {

    private ActivityCepBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inicializando o binding
        binding = ActivityCepBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Recuperando os elementos do layout
        EditText inp_cep = binding.inputCep;
        Button search = binding.btnSearchCep;


        //Criando o evento de click do botão
        search.setOnClickListener(view -> {
            String cep = inp_cep.getText().toString().trim();

            //Verificando se o CEP está vazio
            if (!cep.isEmpty()) {
                searchCep(cep);
            } else {
                Snackbar.make(this, view, "Digite um CEP válido", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void searchCep(String cep) {

        //Criando uma instancia do retrofit
        CEPService service = RetrofitHelper.getInstance().create(CEPService.class);
        Call<Endereco> call = service.SearchCEP(cep);

        //Executando a chamada
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Endereco> call, @NonNull Response<Endereco> response) {
                Log.e("CepActivity", "CEP encontrado: " + response.body());
                if (response.isSuccessful() && response.body() != null) {
                    //Recuperando o endereço
                    Endereco endereco = response.body();
                    binding.txtRua.setText(endereco.getLogradouro());
                    binding.txtBairro.setText(endereco.getBairro());
                    binding.txtCidade.setText(endereco.getLocalidade());
                    binding.txtUf.setText(endereco.getUf());
                    binding.txtEstado.setText(endereco.getEstado());
                    binding.txtRegiao.setText(endereco.getRegiao());
                    binding.txtIbge.setText(endereco.getIbge());
                    binding.txtGia.setText(endereco.getGia());
                    binding.txtComplemento.setText(endereco.getComplemento());
                    binding.txtUf.setText(endereco.getUf());
                }
            }

            //Verificando se houve erro na chamada
            @Override
            public void onFailure(@NonNull Call<Endereco> call, @NonNull Throwable throwable) {
                Log.e("CepActivity", "Erro ao buscar o CEP", throwable);
                Toast.makeText(CepActivity.this, "Erro ao buscar o CEP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}