package com.example.multiapp.Service;

import com.example.multiapp.Model.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json/")
    Call<Endereco> SearchCEP(@Path("cep") String cep);
}
