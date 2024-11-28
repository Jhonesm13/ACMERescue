package com.acmerescue.demo;

public class Evento {
    private long codigo;
    private String descricao;
    private String data;
    private double latitude;
    private double longitude;

    public Evento(long codigo, String descricao, String data, double latitude, double longitude) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.data = data;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
