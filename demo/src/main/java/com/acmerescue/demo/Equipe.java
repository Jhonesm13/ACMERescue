package com.acmerescue.demo;

public class Equipe {
    private Long numero; 
    private int quantidadeMembros; 
    private double latitude; 
    private double longitude; 

    public Equipe(Long numero, int quantidadeMembros, double latitude, double longitude) {
        this.numero = numero;
        this.quantidadeMembros = quantidadeMembros;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    
    public Long getNumero() {
        return numero;
    }

    public int getQuantidadeMembros() {
        return quantidadeMembros;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}

