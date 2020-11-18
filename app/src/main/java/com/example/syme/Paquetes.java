package com.example.syme;

public class Paquetes {

    private String numPaquete;
    private String titPaquete;
    private String desPaquete;

    public Paquetes() {
    }

    public Paquetes(String numPaquete, String titPaquete, String desPaquete) {
        this.numPaquete = numPaquete;
        this.titPaquete = titPaquete;
        this.desPaquete = desPaquete;
    }

    public String getNumPaquete() {
        return numPaquete;
    }

    public void setNumPaquete(String numPaquete) {
        this.numPaquete = numPaquete;
    }

    public String getTitPaquete() {
        return titPaquete;
    }

    public void setTitPaquete(String titPaquete) {
        this.titPaquete = titPaquete;
    }

    public String getDesPaquete() {
        return desPaquete;
    }

    public void setDesPaquete(String desPaquete) {
        this.desPaquete = desPaquete;
    }

}
