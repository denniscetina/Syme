package com.example.syme;

public class VentasP{
    private String categoria;
    private String descripcionV;

    public VentasP(String categoria, String descripcionV) {
        this.categoria = categoria;
        this.descripcionV = descripcionV;
    }

    public VentasP() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcionV() {
        return descripcionV;
    }

    public void setDescripcionV(String descripcionV) {
        this.descripcionV = descripcionV;
    }
}
