package com.ispc.lemone.clases;

import java.io.Serializable;
import java.util.Date;

public class Orden implements Serializable {

    private int id;
    private String fecha;
    private Producto producto;
    private Persona persona;
    private int cantidad;
    private String tipoDeOperacion;
    private String nombrePersona;
    private String nombreProducto;
    private  String codigoDeProducto;
    public Orden() {
    }

    public Orden(int cantidad,  String nombrePersona,
                 String nombreProducto, String tipoDeOperacion,
                 String codigoDeProducto, String fecha) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombrePersona = nombrePersona;
        this.nombreProducto = nombreProducto;
        this.tipoDeOperacion = tipoDeOperacion;
        this.codigoDeProducto = codigoDeProducto;
        this.fecha = fecha;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getCodigoProducto() {
        return codigoDeProducto;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTipoDeOperacion() {
        return tipoDeOperacion;
    }

    public String getPersona() {
        return nombrePersona;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoDeOperacion = tipoOperacion;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoDeProducto = codigoProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }


}
