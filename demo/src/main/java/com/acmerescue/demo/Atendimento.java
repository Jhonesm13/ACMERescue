package com.acmerescue.demo;

import java.sql.Date;


public class Atendimento {
    
    private int cod;
    private int duracao;
    private String status;
    private Date inicio;

    public Atendimento(int cod, String status, Date inicio, int duracao) {
        this.cod = cod;
        this.duracao = duracao;
        this.status = status;
        this.inicio = inicio;
    }

    public int getCod() {
        return cod;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getStatus() {
        return status;
    }

    public Date getInicio() {
        return inicio;
    }
}
