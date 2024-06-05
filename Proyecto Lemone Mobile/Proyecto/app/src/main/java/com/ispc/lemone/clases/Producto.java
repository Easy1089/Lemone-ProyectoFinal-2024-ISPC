package com.ispc.lemone.clases;
import java.io.Serializable;
public class Producto implements Serializable  {
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private int inventarioMinimo;
    private int stockActual;
    private  boolean tieneStock;
    private double precioDeCosto;
    private double precioDeVenta;
    private CategoriaProducto categoriaProducto;
    private boolean activoActualmente;

    public Producto() {
    }

    public Producto(int id, String codigo, String nombre, String descripcion, int inventarioMinimo, double precioDeCosto, double precioDeVenta, CategoriaProducto categoriaProducto, boolean activoActualmente) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inventarioMinimo = inventarioMinimo;
        this.precioDeCosto = precioDeCosto;
        this.precioDeVenta = precioDeVenta;
        this.categoriaProducto = categoriaProducto;
        this.activoActualmente = activoActualmente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getInventarioMinimo() {
        return inventarioMinimo;
    }
    public int getStockActual() {
        return stockActual;
    }

    public boolean getAlertaDeStock() {
        return tieneStock;
    }

    public void setInventarioMinimo(int inventarioMinimo) {
        this.inventarioMinimo = inventarioMinimo;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public void setTieneStock(boolean tieneStock) {
        this.tieneStock = tieneStock;
    }

    public double getPrecioDeCosto() {
        return precioDeCosto;
    }

    public void setPrecioDeCosto(double precioDeCosto) {
        this.precioDeCosto = precioDeCosto;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public boolean isActivoActualmente() {
        return activoActualmente;
    }

    public void setActivoActualmente(boolean activoActualmente) {
        this.activoActualmente = activoActualmente;
    }

    public boolean getActivoActualmente() {
        return activoActualmente;
    }
}
