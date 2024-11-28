package com.acmerescue.demo;

public class Equipamento {
    private int id;
    private String nome;
    private double custoDiario;

    public Equipamento(int id, String nome, double custoDiario) {
        this.id = id;
        this.nome = nome;
        this.custoDiario = custoDiario;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getCustoDiario() {
        return custoDiario;
    }

    @Override
    public String toString() {
        return "Equipamento [id=" + id + ", nome=" + nome + ", custo diario=" + custoDiario + "]";
    }
}