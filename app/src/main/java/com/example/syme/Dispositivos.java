package com.example.syme;

public class Dispositivos {
    private String Tipo;
    private String Estado;

    public Dispositivos(String tipo, String estado) {
        Tipo = tipo;
        Estado = estado;
    }

    public Dispositivos() {
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
