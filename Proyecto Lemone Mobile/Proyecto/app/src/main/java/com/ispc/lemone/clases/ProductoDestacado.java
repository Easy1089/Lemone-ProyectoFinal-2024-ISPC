package com.ispc.lemone.clases;

import java.io.Serializable;
import java.util.Date;

public class ProductoDestacado {
    private long fechaDesde;
    private long fechaHasta;
    private int idProducto;
    private String nombreProducto;

    // Constructor con long fechas y idProducto
    public ProductoDestacado(long fechaDesde, long fechaHasta, int idProducto) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.idProducto = idProducto;
    }

    // Constructor con todos los campos
    public ProductoDestacado(long fechaDesde, long fechaHasta, int idProducto, String nombreProducto) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
    }

    // Getters y Setters
    public long getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(long fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public long getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(long fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
