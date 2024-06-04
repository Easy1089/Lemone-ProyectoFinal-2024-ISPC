package com.ispc.lemone.clases;

import java.io.Serializable;

public class InventarioMinimoPorProducto implements Serializable {

    private int id;
    private int inventarioMinimo;
    private String categoriaDeProducto;
    private String nombreProducto;
    private  String codigoDeProducto;
    public InventarioMinimoPorProducto() {
    }

    public InventarioMinimoPorProducto(int id, String codigoDeProducto,
                                       String nombreProducto, String categoriaDeProducto,
                                       int inventarioMinimo) {
        this.id = id;
        this.codigoDeProducto = codigoDeProducto;
        this.nombreProducto = nombreProducto;
        this.categoriaDeProducto = categoriaDeProducto;
        this.inventarioMinimo = inventarioMinimo;
    }

    public String getCategoriaProducto() {
        return categoriaDeProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public String getCodigoProducto() {
        return codigoDeProducto;
    }
    public int getInventarioMinimo() {
        return inventarioMinimo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setInventarioMinimo(int inventarioMinimo) {
        this.inventarioMinimo = inventarioMinimo;
    }
    public void setCodigoProducto(String codigoProducto) {
        this.codigoDeProducto = codigoProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public void setCategoriaDeProducto(String categoriaDeProducto) {
        this.categoriaDeProducto = categoriaDeProducto;
    }
}
