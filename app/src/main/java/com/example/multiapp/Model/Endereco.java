package com.example.multiapp.Model;

public class Endereco {

    // Atributos
    private final String logradouro;
    private final String complemento;
    private final String bairro;
    private final String localidade;
    private final String uf;
    private final String ibge;
    private final String gia;
    private final String estado;
    private final String regiao;

    // Construtor
    public Endereco(String logradouro, String complemento, String bairro, String localidade, String uf, String ibge, String gia, String estado, String regiao) {
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.estado = estado;
        this.regiao = regiao;
    }

    // Getters
    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getIbge() {
        return ibge;
    }

    public String getGia() {
        return gia;
    }

    public String getEstado() {
        return estado;
    }

    public String getRegiao() {
        return regiao;
    }
}
