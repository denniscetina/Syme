package com.example.syme;

public class Producto {
    private String nombreProductos;
    private String PrecioProducto;
    private String urlFotoP;

    public Producto() {
    }

    public Producto(String nombreProductos, String precioProducto, String urlFotoP) {
        nombreProductos = nombreProductos;
        PrecioProducto = precioProducto;
        urlFotoP = urlFotoP;
    }

    public String getNombreProductos() {
        return nombreProductos;
    }

    public void setNombreProductos(String nombreProductos) {
        this.nombreProductos = nombreProductos;
    }

    public String getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        PrecioProducto = precioProducto;
    }

    public String getUrlFotoP() {
        return urlFotoP;
    }

    public void setUrlFotoP(String urlFotoP) {
        this.urlFotoP = urlFotoP;
    }
}
